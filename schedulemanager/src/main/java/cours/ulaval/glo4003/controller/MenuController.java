package cours.ulaval.glo4003.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MenuController {

	@RequestMapping(value = "/menu", method = RequestMethod.GET)
	public String menu(ModelMap model, Principal principal) {

		String name = principal.getName();
		model.addAttribute("username", name);
		return "menu";
	}

	@RequestMapping(value = "/loginfailed", method = RequestMethod.GET)
	public ModelAndView loginFailed(ModelMap model) {

		ModelAndView mv = new ModelAndView("home");
		mv.addObject("loginError", "Le nom d'utilisateur ou le mot de passe est incorrecte. Veuillez réessayer.");
		return mv;

	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logout(ModelMap model) {

		ModelAndView mv = new ModelAndView("home");
		return mv;

	}

}
