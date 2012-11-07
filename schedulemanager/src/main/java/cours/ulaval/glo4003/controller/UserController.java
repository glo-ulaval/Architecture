package cours.ulaval.glo4003.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cours.ulaval.glo4003.domain.repository.UserRepository;

@Controller
public class UserController {

	@Inject
	UserRepository userRepository;

	@RequestMapping(value = "/createuser", method = RequestMethod.GET)
	public ModelAndView createUser() {
		ModelAndView mv = new ModelAndView("createuser");

		return mv;
	}

}
