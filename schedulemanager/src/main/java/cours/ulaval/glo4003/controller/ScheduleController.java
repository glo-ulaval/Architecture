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
		// scheduleRepository.findAll();

		ModelAndView mv = new ModelAndView("schedule");
		mv.addObject("schedules", schedules);
		return mv;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView scheduleByYear(@PathVariable String id)
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

	@RequestMapping(value = "/add/{year}", method = RequestMethod.GET)
	public ModelAndView addSchedule(@PathVariable String year)
			throws Exception {
		ModelAndView mv = new ModelAndView("createschedule");
		mv.addObject("year", year);
		mv.addObject("courses", courseRepository.findByOffering(offeringRepository.find(year)));

		return mv;
	}

	@RequestMapping(value = "/add/{year}", method = RequestMethod.POST)
	public ModelAndView postSchedule(@PathVariable String year)
			throws Exception {
		return addSchedule(year);
	}

	@RequestMapping(value = "/add/{year}/addsection", method = RequestMethod.GET)
	public ModelAndView addSection(@PathVariable String year, @RequestParam(required = true, value = "acronym") String acronym) {
		ModelAndView mv = new ModelAndView("addsection");
		mv.addObject("acronym", acronym);
		mv.addObject("course", courseRepository.findByAcronym(acronym));
		mv.addObject("year", year);

		return mv;
	}
}