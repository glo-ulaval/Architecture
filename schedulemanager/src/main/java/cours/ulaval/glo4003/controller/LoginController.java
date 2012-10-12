package cours.ulaval.glo4003.controller;

import java.security.Principal;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cours.ulaval.glo4003.domain.User;
import cours.ulaval.glo4003.domain.repository.UserRepository;

@Controller
public class LoginController {

	@Inject
	UserRepository userRepository;

	@RequestMapping(value = "/menu", method = RequestMethod.GET)
	public String menu(ModelMap model, Principal principal, HttpServletRequest request) {

		User user = userRepository.findByIdul(principal.getName());
		request.getSession().setAttribute("user", user);

		return "menu";
	}

	@RequestMapping(value = "/loginfailed", method = RequestMethod.GET)
	public ModelAndView loginFailed(ModelMap model) {

		ModelAndView mv = new ModelAndView("home");
		mv.addObject("loginError", "Le nom d'utilisateur ou le mot de passe est incorrect. Veuillez réessayer.");
		return mv;

	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logout(ModelMap model) {

		ModelAndView mv = new ModelAndView("home");
		return mv;

	}

}
