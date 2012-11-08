package cours.ulaval.glo4003.controller;

import java.security.Principal;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
	public ModelAndView createUser(@ModelAttribute("user") UserModel model, Principal principal) {
		ModelAndView mv = new ModelAndView("menu");

		User userToCreate = model.convertToUser();
		try {
			userRepository.store(userToCreate);
			mv.addObject("error", ControllerMessages.SUCCESS);
		} catch (Exception e) {
			mv.addObject("error", e.getMessage());
		}

		mv.addObject("user", userRepository.findByIdul(principal.getName()));
		mv.addObject("confirm", false);

		return mv;
	}

	@RequestMapping(value = "/profile/{idul}", method = RequestMethod.GET)
	public ModelAndView getProfile(@PathVariable String idul) {
		ModelAndView mv = new ModelAndView("profile");

		User user = userRepository.findByIdul(idul);
		UserModel model = new UserModel(user);
		mv.addObject("user", model);

		return mv;
	}

	@RequestMapping(value = "/profile/{idul}", method = RequestMethod.POST)
	public ModelAndView saveProfile(@PathVariable String idul, @ModelAttribute("user") UserModel model, Principal principal) {
		ModelAndView mv = new ModelAndView("menu");

		User user = userRepository.findByIdul(idul);
		user.setName(model.getName());
		user.setPassword(model.getPassword());
		try {
			userRepository.store(user);
			mv.addObject("error", ControllerMessages.SUCCESS);
		} catch (Exception e) {
			mv.addObject("error", e.getMessage());
		}

		mv.addObject("user", userRepository.findByIdul(principal.getName()));
		mv.addObject("confirm", false);

		return mv;
	}
}
