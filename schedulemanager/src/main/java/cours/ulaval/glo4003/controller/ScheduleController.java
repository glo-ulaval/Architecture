package cours.ulaval.glo4003.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cours.ulaval.glo4003.domain.CourseRepository;
import cours.ulaval.glo4003.domain.OfferingRepository;
import cours.ulaval.glo4003.domain.Schedule;
import cours.ulaval.glo4003.domain.ScheduleRepository;
import cours.ulaval.glo4003.domain.Semester;

@Controller
@RequestMapping(value = "/schedule")
public class ScheduleController {

	@Inject
	private CourseRepository courseRepository;

	@Inject
	private OfferingRepository offeringRepository;

	@Inject
	private ScheduleRepository scheduleRepository;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView schedule() {
		List<Schedule> schedules = new ArrayList<Schedule>();
		Schedule schedule = new Schedule("unID");
		schedule.setYear("2011-2012");
		schedules.add(schedule);

		ModelAndView mv = new ModelAndView("schedule");
		mv.addObject("schedules", schedules);
		return mv;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView scheduleById(@PathVariable String id)
			throws Exception {
		ModelAndView mv = new ModelAndView("schedulebyid");
		mv.addObject("schedule", scheduleRepository.findById(id));

		return mv;
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView addSchedule()
			throws Exception {
		ModelAndView mv = new ModelAndView("addschedule");
		mv.addObject("years", offeringRepository.findYears());

		return mv;
	}

	@RequestMapping(value = "/add/{year}/{semester}", method = RequestMethod.GET)
	public ModelAndView addSchedule(@PathVariable String year, @PathVariable Semester semester)
			throws Exception {
		ModelAndView mv = new ModelAndView("createschedule");
		mv.addObject("year", year);
		mv.addObject("semester", semester);
		mv.addObject("courses", courseRepository.findByOffering(offeringRepository.find(year, semester)));

		return mv;
	}

	@RequestMapping(value = "/add/{year}/{semester}", method = RequestMethod.POST)
	public ModelAndView postSchedule(@PathVariable String year, @PathVariable Semester semester,
			@RequestParam(required = true, value = "personInCharge") String personInCharge,
			@RequestParam(required = true, value = "teachers") List<String> teachers,
			@RequestParam(required = true, value = "teachMode") String teachMode,
			@RequestParam(required = true, value = "acronym") String acronym,
			@RequestParam(required = true, value = "group") String group,
			@RequestParam(required = false, value = "hoursInClass") Integer hoursInClass,
			@RequestParam(required = false, value = "hoursInLab") Integer hoursInLab,
			@RequestParam(required = true, value = "hoursAtHome") Integer hoursAtHome,
			@RequestParam(required = false, value = "days") List<String> days,
			@RequestParam(required = false, value = "timeSlotStart") List<String> timeSlotsStarts,
			@RequestParam(required = false, value = "timeSlotEnd") List<String> timeSlotsEnds,
			@RequestParam(required = false, value = "laboTimeSlotStart") String laboTimeSlotStart,
			@RequestParam(required = false, value = "laboTimeSlotEnd") String laboTimeSlotEnd)
			throws Exception {
		return addSchedule(year, semester);
	}

	@RequestMapping(value = "/add/{year}/{semester}/addsection", method = RequestMethod.GET)
	public ModelAndView addSection(@PathVariable String year, @PathVariable Semester semester,
			@RequestParam(required = true, value = "acronym") String acronym) {
		ModelAndView mv = new ModelAndView("addsection");
		mv.addObject("acronym", acronym);
		mv.addObject("course", courseRepository.findByAcronym(acronym));
		mv.addObject("semester", semester);
		mv.addObject("year", year);

		return mv;
	}

	@RequestMapping(value = "/delete/{scheduleId}", method = RequestMethod.GET)
	public ModelAndView deleteSchedule(@PathVariable String scheduleId)
			throws Exception {
		ModelAndView mv = new ModelAndView("delete");

		scheduleRepository.delete(scheduleId);

		return mv;
	}

	@RequestMapping(value = "/delete/{scheduleId}/{sectionNrc}", method = RequestMethod.GET)
	public ModelAndView deleteSection(@PathVariable String scheduleId, @PathVariable String sectionNrc) {
		ModelAndView mv = new ModelAndView("delete");

		Schedule schedule = scheduleRepository.findById(scheduleId);
		schedule.delete(sectionNrc);

		return mv;
	}

}