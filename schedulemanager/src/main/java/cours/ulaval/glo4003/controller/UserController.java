package cours.ulaval.glo4003.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cours.ulaval.glo4003.controller.model.UserModel;
import cours.ulaval.glo4003.domain.User;
import cours.ulaval.glo4003.domain.repository.UserRepository;

@Controller
public class UserController {

	@Inject
	UserRepository userRepository;

	@RequestMapping(value = "/createuser", method = RequestMethod.GET)
	public ModelAndView getCreateUser() {
		ModelAndView mv = new ModelAndView("createuser");

		return mv;
	}

	@RequestMapping(value = "/createuser", method = RequestMethod.POST)
	public ModelAndView createUser(@ModelAttribute("user") UserModel user) {
		ModelAndView mv = new ModelAndView("menu");

		User userToCreate = user.convertToUser();
		try {
			userRepository.store(userToCreate);
			mv.addObject("error", ControllerMessages.SUCCESS);
		} catch (Exception e) {
			mv.addObject("error", e.getMessage());
		}
		return mv;
	}

}
