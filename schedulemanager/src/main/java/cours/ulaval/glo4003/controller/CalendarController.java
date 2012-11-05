package cours.ulaval.glo4003.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/calendar")
public class CalendarController {

	@RequestMapping(value = "")
	public String offering() {
		return "calendar";
	}

	@RequestMapping(value = "2")
	public String offering2() {
		return "calendartest";
	}
}
