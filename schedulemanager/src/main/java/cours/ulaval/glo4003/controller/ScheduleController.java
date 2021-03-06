package cours.ulaval.glo4003.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cours.ulaval.glo4003.controller.model.CalendarModel;
import cours.ulaval.glo4003.controller.model.ScheduleInformationModel;
import cours.ulaval.glo4003.controller.model.SectionModel;
import cours.ulaval.glo4003.controller.model.TimeSlotModel;
import cours.ulaval.glo4003.domain.Notification;
import cours.ulaval.glo4003.domain.Role;
import cours.ulaval.glo4003.domain.Schedule;
import cours.ulaval.glo4003.domain.ScheduleGenerator;
import cours.ulaval.glo4003.domain.ScheduleStatus;
import cours.ulaval.glo4003.domain.Section;
import cours.ulaval.glo4003.domain.Semester;
import cours.ulaval.glo4003.domain.Time;
import cours.ulaval.glo4003.domain.TimeDedicated;
import cours.ulaval.glo4003.domain.TimeSlot;
import cours.ulaval.glo4003.domain.TimeSlot.DayOfWeek;
import cours.ulaval.glo4003.domain.User;
import cours.ulaval.glo4003.domain.exception.FailedScheduleGenerationException;
import cours.ulaval.glo4003.domain.repository.CourseRepository;
import cours.ulaval.glo4003.domain.repository.OfferingRepository;
import cours.ulaval.glo4003.domain.repository.ScheduleRepository;
import cours.ulaval.glo4003.domain.repository.UserRepository;

@Controller
@RequestMapping(value = "/schedule")
public class ScheduleController {

	@Inject
	private CourseRepository courseRepository;

	@Inject
	private OfferingRepository offeringRepository;

	@Inject
	private ScheduleRepository scheduleRepository;

	@Inject
	private UserRepository userRepository;

	@Inject
	private ScheduleGenerator generator;

	@Inject
	private ObjectMapper mapper;

	@Inject
	JavaMailSenderImpl mailSender;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView schedule(Principal principal) {
		ModelAndView mv = new ModelAndView("schedule");

		List<ScheduleInformationModel> scheduleModels = new ArrayList<ScheduleInformationModel>();
		Map<String, String> statuses = new HashMap<String, String>();

		for (Schedule schedule : scheduleRepository.findAll()) {
			schedule.calculateScore();
			scheduleModels.add(new ScheduleInformationModel(schedule));
			statuses.put(schedule.getId(), String.valueOf(schedule.getStatus(principal.getName())));
		}

		mv.addObject("statuses", statuses);
		mv.addObject("schedules", scheduleModels);
		return mv;
	}

	@RequestMapping(value = "/{id}/{view}", method = RequestMethod.GET)
	public ModelAndView scheduleView(@PathVariable String id, @PathVariable String view, Principal principal) throws Exception {
		Schedule schedule = scheduleRepository.findById(id);
		CalendarModel calendarModel = new CalendarModel(schedule);

		ModelAndView mv;
		if (view.contentEquals("calendar")) {
			mv = new ModelAndView("schedulecalendar");
			mv.addObject("schedule", convertToJson(calendarModel));
			mv.addObject("id", schedule.getId());
		} else {
			mv = new ModelAndView("schedulelist");
			mv.addObject("schedule", calendarModel);
		}

		return mv;
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView addSchedule() throws Exception {
		ModelAndView mv = new ModelAndView("addschedule");
		mv.addObject("years", offeringRepository.findYears());

		return mv;
	}

	@RequestMapping(value = "/add/{year}/{semester}", method = RequestMethod.GET)
	public ModelAndView addSchedule(@PathVariable String year, @PathVariable Semester semester) throws Exception {
		Schedule schedule = new Schedule(scheduleRepository.getId(year, semester));
		schedule.setYear(year);
		schedule.setSemester(semester);

		scheduleRepository.store(schedule);

		ModelAndView mv = new ModelAndView("createschedule");
		mv.addObject("year", year);
		mv.addObject("semester", semester);
		mv.addObject("id", schedule.getId());
		mv.addObject("courses", courseRepository.findByOffering(offeringRepository.find(year)));

		return mv;
	}

	@RequestMapping(value = "/{id}/reuseschedule", method = RequestMethod.GET)
	public ModelAndView reuseSchedule(@PathVariable String id, Principal principal) throws Exception {
		ModelAndView mv = new ModelAndView("scheduleselection");

		List<ScheduleInformationModel> scheduleModels = new ArrayList<ScheduleInformationModel>();

		for (Schedule schedule : scheduleRepository.findAll()) {
			schedule.calculateScore();
			scheduleModels.add(new ScheduleInformationModel(schedule));
		}

		mv.addObject("schedules", scheduleModels);
		mv.addObject("id", id);
		return mv;
	}

	@RequestMapping(value = "/{id}/reuseschedule/{oldid}", method = RequestMethod.GET)
	public ModelAndView reuseSelectedSchedule(@PathVariable String id, @PathVariable String oldid, Principal principal) throws Exception {
		Schedule schedule = scheduleRepository.findById(id);
		Schedule selectedScheduleToReuse = scheduleRepository.findById(oldid);
		schedule.copySectionsFromOtherSchedule(selectedScheduleToReuse);
		CalendarModel calendarModel = new CalendarModel(schedule);

		scheduleRepository.store(schedule);

		ModelAndView mv;
		mv = new ModelAndView("schedulelist");
		mv.addObject("schedule", calendarModel);

		return mv;
	}

	@RequestMapping(value = "/generate/{id}", method = RequestMethod.GET)
	public ModelAndView generateSchedule(@PathVariable String id, Principal principal) throws Exception {
		Schedule schedule = scheduleRepository.findById(id);

		try {
			Schedule generatedSchedule = generator.generateSchedule(schedule.getSectionsList());
			generatedSchedule.setPersonInCharge(schedule.getPersonInCharge());
			generatedSchedule.setSemester(schedule.getSemester());
			generatedSchedule.setYear(schedule.getYear());
		} catch (FailedScheduleGenerationException e) {
			ModelAndView mv = new ModelAndView("createschedule");
			mv.addObject("error", e.getMessage());
			mv.addObject("year", schedule.getYear());
			mv.addObject("semester", schedule.getSemester());
			mv.addObject("id", id);
			mv.addObject("courses", courseRepository.findByOffering(offeringRepository.find(schedule.getYear())));
			mv.addObject("sections", getSections(schedule));
			return mv;
		}
		scheduleRepository.store(schedule);

		CalendarModel calendarModel = new CalendarModel(schedule);
		ModelAndView mv;
		mv = new ModelAndView("schedulelist");
		mv.addObject("schedule", calendarModel);

		return mv;
	}

	@RequestMapping(value = "/accept/{scheduleId}", method = RequestMethod.POST)
	@ResponseBody
	public String acceptSchedule(@PathVariable String scheduleId, Principal principal, String status) {
		Schedule schedule = scheduleRepository.findById(scheduleId);
		User user = userRepository.findByIdul(principal.getName());
		ScheduleStatus statusEnumValue = Enum.valueOf(ScheduleStatus.class, status);
		if (statusEnumValue == ScheduleStatus.Accepted) {
			user.acceptSchedule(schedule);
		} else if (statusEnumValue == ScheduleStatus.Refused) {
			user.refuseSchedule(schedule);
		}

		try {
			scheduleRepository.store(schedule);
		} catch (Exception e) {
		}

		return ControllerMessages.SUCCESS;
	}

	@RequestMapping(value = "/delete/{scheduleId}", method = RequestMethod.GET)
	public ModelAndView deleteSchedule(@PathVariable String scheduleId, Principal principal) {
		Boolean error = false;
		String errorMessage = "";
		try {
			scheduleRepository.delete(scheduleId);
		} catch (Exception e) {
			error = true;
			errorMessage = e.getMessage();
		}

		ModelAndView mv = schedule(principal);
		if (error) {
			mv.addObject("error", errorMessage);
		} else {
			mv.addObject("error", ControllerMessages.SUCCESS);
		}

		return mv;
	}

	// TODO possible découpage du controlleur ici (création d'un
	// sectionController)

	@RequestMapping(value = "/addsection/{id}/{year}/{semester}", method = RequestMethod.POST)
	public ModelAndView postSection(@PathVariable String id, @PathVariable String year, @PathVariable Semester semester,
			@ModelAttribute("section") SectionModel section) throws Exception {
		Schedule schedule = scheduleRepository.findById(id);
		schedule.add(section.convertToSection());
		scheduleRepository.store(schedule);

		ModelAndView mv = new ModelAndView("createschedule");
		mv.addObject("year", year);
		mv.addObject("semester", semester);
		mv.addObject("id", id);
		mv.addObject("courses", courseRepository.findByOffering(offeringRepository.find(year)));

		List<SectionModel> sections = getSections(schedule);
		mv.addObject("sections", sections);

		return mv;
	}

	@RequestMapping(value = "/addsection/{id}/{year}/{semester}", method = RequestMethod.GET)
	public ModelAndView addSection(@PathVariable String id, @PathVariable String year, @PathVariable Semester semester,
			@RequestParam(required = true, value = "acronym") String acronym) {
		ModelAndView mv = new ModelAndView("addsection");
		mv.addObject("acronym", acronym);
		mv.addObject("course", courseRepository.findByAcronym(acronym));
		mv.addObject("semester", semester);
		mv.addObject("year", year);
		mv.addObject("id", id);
		Map<String, String> teachers = new HashMap<String, String>();
		for (User teacher : userRepository.findByRole(Role.ROLE_Enseignant)) {
			teachers.put(teacher.getIdul(), teacher.getName());
		}
		mv.addObject("teachers", teachers);

		return mv;
	}

	@RequestMapping(value = "/proposelabsection/{id}/{year}/{semester}", method = RequestMethod.POST)
	public ModelAndView proposeLabSection(@PathVariable String id, @PathVariable String year, @PathVariable Semester semester, String teachers,
			int labHours, String courseAcronym) throws Exception {
		ModelAndView mv = new ModelAndView("partialViews/proposedCourse");

		Schedule schedule = scheduleRepository.findById(id);
		Section section = new Section();
		List<String> teachersList = mapper.readValue(teachers, new TypeReference<List<String>>() {
		});
		section.setTeachers(teachersList);
		section.setTimeDedicated(new TimeDedicated(0, labHours, 0));
		section.setCourseAcronym(courseAcronym);

		mv.addObject("isLab", true);
		mv.addObject("timeSlots", getTimeSlotModels(generator.proposeTimeSlotsForSectionForLab(section, schedule)));

		return mv;
	}

	@RequestMapping(value = "/proposesection/{id}/{year}/{semester}", method = RequestMethod.POST)
	public ModelAndView proposeSection(@PathVariable String id, @PathVariable String year, @PathVariable Semester semester, String teachers,
			int courseHours, String courseAcronym) throws Exception {
		ModelAndView mv = new ModelAndView("partialViews/proposedCourse");

		Schedule schedule = scheduleRepository.findById(id);
		Section section = new Section();
		List<String> teachersList = mapper.readValue(teachers, new TypeReference<List<String>>() {
		});
		section.setTeachers(teachersList);
		section.setTimeDedicated(new TimeDedicated(courseHours, 0, 0));
		section.setCourseAcronym(courseAcronym);

		mv.addObject("isLab", false);
		mv.addObject("timeSlots", getTimeSlotModels(generator.proposeTimeSlotsForSectionForCourses(section, schedule)));

		return mv;
	}

	@RequestMapping(value = "/editsection/{id}/{year}/{semester}/{sectionNrc}/{view}", method = RequestMethod.GET)
	public ModelAndView editSection(@PathVariable String id, @PathVariable String year, @PathVariable Semester semester,
			@PathVariable String sectionNrc, @PathVariable String view) {
		ModelAndView mv = new ModelAndView("editsection");
		mv.addObject("semester", semester);
		mv.addObject("year", year);
		mv.addObject("id", id);
		mv.addObject("view", view);

		Schedule schedule = scheduleRepository.findById(id);
		Section section = schedule.getSections().get(sectionNrc);
		mv.addObject("section", new SectionModel(section));

		return mv;
	}

	@RequestMapping(value = "/editsection/{id}/{year}/{semester}/{sectionNrc}/{view}", method = RequestMethod.POST)
	public ModelAndView postEditSectionAndReturnToLastView(@PathVariable String id, @PathVariable String year, @PathVariable Semester semester,
			@PathVariable String sectionNrc, @PathVariable String view, @ModelAttribute("section") SectionModel section, Principal principal) throws Exception {
		Schedule schedule = scheduleRepository.findById(id);
		updateSectionAndSaveToSchedule(sectionNrc, section, schedule);

		return scheduleView(id, view, principal);
	}

	@RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView updateSection(@PathVariable String id, String nrc, String oldDay, String oldTimeStart, String newDay, String newTimeStart,
			String duration, Principal principal) throws Exception {
		TimeSlot newTimeSlot = new TimeSlot(new Time(getHour(newTimeStart), getMinutes(newTimeStart)), getDuration(duration), getDayOfWeek(newDay));

		Time oldTmStart = new Time(getHour(oldTimeStart), getMinutes(oldTimeStart));

		List<TimeSlot> courseTimeSlots = scheduleRepository.findById(id).getSections().get(nrc).getCourseTimeSlots();

		Schedule schedule = scheduleRepository.findById(id);
		for (TimeSlot slot : courseTimeSlots) {
			if (getDayOfWeekAbreviation(slot) == oldDay || slot.getStartTime().equals(oldTmStart)) {
				courseTimeSlots.remove(slot);
				courseTimeSlots.add(newTimeSlot);
				schedule.getSections().get(nrc).setCourseTimeSlots(courseTimeSlots);
			}
		}

		TimeSlot labTimeSlot = scheduleRepository.findById(id).getSections().get(nrc).getLabTimeSlot();
		if (labTimeSlot != null) {
			if (getDayOfWeekAbreviation(labTimeSlot) == oldDay || labTimeSlot.getStartTime().equals(oldTmStart)) {
				schedule.getSections().get(nrc).setLabTimeSlot(newTimeSlot);
			}
		}

		scheduleRepository.store(schedule);

		return scheduleView(id, "calendar", principal);
	}

	@RequestMapping(value = "/deletesection/{scheduleId}/{year}/{semester}/{sectionNrc}", method = RequestMethod.GET)
	public ModelAndView deleteSection(@PathVariable String scheduleId, @PathVariable String sectionNrc, @PathVariable String year,
			@PathVariable Semester semester) {
		ModelAndView mv = new ModelAndView("createschedule");

		try {
			Schedule schedule = scheduleRepository.findById(scheduleId);
			schedule.delete(sectionNrc);
			scheduleRepository.store(schedule);
			mv.addObject("error", ControllerMessages.SUCCESS);
			mv.addObject("year", year);
			mv.addObject("semester", semester);
			mv.addObject("id", scheduleId);
			mv.addObject("courses", courseRepository.findByOffering(offeringRepository.find(year)));
			mv.addObject("sections", getSections(schedule));

		} catch (Exception e) {
			mv.addObject("error", e.getMessage());
		}

		return mv;
	}

	@RequestMapping(value = "/{id}/sendEmail", method = RequestMethod.POST)
	@ResponseBody
	public String sendNewScheduleReadyNotificationEmail(@PathVariable String id) {
		for (String idul : scheduleRepository.findById(id).getConcernedUsers()) {
			User user = userRepository.findByIdul(idul);
			if (user.hasValidEmailAdress()) {
				sendEmailTo(user.getEmailAddress());
			}
		}

		return "success";
	}

	private String convertToJson(CalendarModel calendarModel) {
		String JSON = "";
		try {
			JSON = mapper.writeValueAsString(calendarModel);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON;
	}

	private void updateSectionAndSaveToSchedule(String sectionNrc, SectionModel section, Schedule schedule) throws Exception {
		schedule.delete(sectionNrc);
		section.setNrc(sectionNrc);
		schedule.add(section.convertToSection());
		scheduleRepository.store(schedule);
	}

	private List<TimeSlotModel> getTimeSlotModels(List<TimeSlot> timeSlots) {
		List<TimeSlotModel> slotsModels = new ArrayList<TimeSlotModel>();
		for (TimeSlot slot : timeSlots) {
			slotsModels.add(new TimeSlotModel(slot));
		}

		return slotsModels;
	}

	private String getDayOfWeekAbreviation(TimeSlot slot) {
		return slot.getDayOfWeek().toString().toLowerCase().substring(0, 2);
	}

	private int getDuration(String duration) {
		return Integer.parseInt(duration);
	}

	private int getHour(String newTimeStart) {
		return Integer.parseInt(newTimeStart.split(":")[0]);
	}

	private int getMinutes(String newTimeStart) {
		return Integer.parseInt(newTimeStart.split(":")[1]);
	}

	private DayOfWeek getDayOfWeek(String newDay) {
		DayOfWeek newDayOfWeek = null;
		if (newDay.equals("mon")) {
			newDayOfWeek = DayOfWeek.MONDAY;
		} else if (newDay.equals("tue")) {
			newDayOfWeek = DayOfWeek.TUESDAY;
		} else if (newDay.equals("wed")) {
			newDayOfWeek = DayOfWeek.WEDNESDAY;
		} else if (newDay.equals("thu")) {
			newDayOfWeek = DayOfWeek.THURSDAY;
		} else if (newDay.equals("fri")) {
			newDayOfWeek = DayOfWeek.FRIDAY;
		}
		return newDayOfWeek;
	}

	private void sendEmailTo(String emailAddress) {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setFrom("noreply@gmail.com");
		msg.setTo(emailAddress);
		msg.setSubject("Schedule Manager - Nouvel horaire");
		msg.setText(Notification.NEW_SCHEDULE);
		try {
			mailSender.send(msg);
		} catch (MailException ex) {
			System.err.println(ex.getMessage());
		}
	}

	private List<SectionModel> getSections(Schedule schedule) {
		List<SectionModel> sections = new ArrayList<SectionModel>();
		for (Section sectionInSchedule : schedule.getSections().values()) {
			sections.add(new SectionModel(sectionInSchedule));
		}
		return sections;
	}

}
