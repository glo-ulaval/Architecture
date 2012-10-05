package cours.ulaval.glo4003.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cours.ulaval.glo4003.model.TimeSlot;

@Controller
@RequestMapping(value = "/schedule")
public class ScheduleController {

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView schedule() {
		List<String> years = new ArrayList<String>();
		years.add("2011-2012");
		years.add("2012-2013");

		ModelAndView mv = new ModelAndView("schedule");
		mv.addObject("years", years);
		return mv;
	}

	@RequestMapping(value = "/{year}", method = RequestMethod.GET)
	public ModelAndView scheduleByYear(@PathVariable String year)
			throws Exception {
		List<TimeSlot> slots = new ArrayList<TimeSlot>();
		TimeSlot slot = new TimeSlot(Calendar.getInstance(), 2);
		slots.add(slot);

		ModelAndView mv = new ModelAndView("schedulebyyear");
		mv.addObject("year", year);
		mv.addObject("slots", slots);

		return mv;
	}
}