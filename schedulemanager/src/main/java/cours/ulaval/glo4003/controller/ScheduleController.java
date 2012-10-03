package cours.ulaval.glo4003.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ScheduleController {

	@RequestMapping(value = "/schedule")
	public ModelAndView schedule() {
		List<String> years = new ArrayList<String>();
		years.add("2011-2012");
		years.add("2012-2013");

		ModelAndView mv = new ModelAndView("schedule");
		mv.addObject("years", years);
		return mv;
	}
}