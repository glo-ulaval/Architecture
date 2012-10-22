package cours.ulaval.glo4003.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cours.ulaval.glo4003.controller.model.ScheduleModel;
import cours.ulaval.glo4003.controller.model.SectionModel;
import cours.ulaval.glo4003.domain.Schedule;
import cours.ulaval.glo4003.domain.Section;
import cours.ulaval.glo4003.domain.Semester;
import cours.ulaval.glo4003.domain.repository.CourseRepository;
import cours.ulaval.glo4003.domain.repository.OfferingRepository;
import cours.ulaval.glo4003.domain.repository.ScheduleRepository;

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
		ModelAndView mv = new ModelAndView("schedule");

		List<ScheduleModel> scheduleModels = new ArrayList<ScheduleModel>();

		for (Schedule schedule : scheduleRepository.findAll()) {
			scheduleModels.add(new ScheduleModel(schedule));
		}

		mv.addObject("schedules", scheduleModels);
		return mv;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView scheduleById(@PathVariable String id)
			throws Exception {
		ModelAndView mv = new ModelAndView("schedulebyid");

		List<Section> sections = new ArrayList<Section>(scheduleRepository.findById(id).getSections().values());

		mv.addObject("schedule", scheduleRepository.findById(id));
		mv.addObject("sections", sections);

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
		Schedule schedule = new Schedule(year + "-" + semester);
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

	@RequestMapping(value = "/addsection/{id}/{year}/{semester}", method = RequestMethod.POST)
	public ModelAndView postSection(@PathVariable String id, @PathVariable String year, @PathVariable Semester semester,
			@ModelAttribute("section") SectionModel section)
			throws Exception {
		Schedule schedule = scheduleRepository.findById(id);
		schedule.add(section.convertToSection());

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

		return mv;
	}

	@RequestMapping(value = "/editsection/{id}/{year}/{semester}/{sectionNrc}", method = RequestMethod.GET)
	public ModelAndView editSection(@PathVariable String id, @PathVariable String year, @PathVariable Semester semester,
			@PathVariable String sectionNrc) {
		ModelAndView mv = new ModelAndView("editsection");
		mv.addObject("semester", semester);
		mv.addObject("year", year);
		mv.addObject("id", id);

		Schedule schedule = scheduleRepository.findById(id);
		Section section = schedule.getSections().get(sectionNrc);
		mv.addObject("section", new SectionModel(section));

		return mv;
	}

	@RequestMapping(value = "/delete/{scheduleId}", method = RequestMethod.GET)
	public ModelAndView deleteSchedule(@PathVariable String scheduleId)
			throws Exception {
		ModelAndView mv = new ModelAndView("deleteschedule");

		scheduleRepository.delete(scheduleId);

		return mv;
	}

	@RequestMapping(value = "/deletesection/{scheduleId}/{year}/{semester}/{sectionNrc}", method = RequestMethod.GET)
	public ModelAndView deleteSection(@PathVariable String scheduleId, @PathVariable String sectionNrc,
			@PathVariable String year, @PathVariable Semester semester) {
		ModelAndView mv = new ModelAndView("createschedule");

		try {
			Schedule schedule = scheduleRepository.findById(scheduleId);
			schedule.delete(sectionNrc);
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

	private List<SectionModel> getSections(Schedule schedule) {
		List<SectionModel> sections = new ArrayList<SectionModel>();
		for (Section sectionInSchedule : schedule.getSections().values()) {
			sections.add(new SectionModel(sectionInSchedule));
		}
		return sections;
	}
}
