package cours.ulaval.glo4003.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/conflict")
public class ConflictController {

	@RequestMapping(value = "/{day}/{id}")
	public ModelAndView getConflict(@PathVariable String day, @PathVariable Integer id) {
		ModelAndView mv = new ModelAndView("conflict");

		return mv;
	}

}
