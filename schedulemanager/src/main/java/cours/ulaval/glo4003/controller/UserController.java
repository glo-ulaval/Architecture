package cours.ulaval.glo4003.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cours.ulaval.glo4003.controller.model.UserModel;
import cours.ulaval.glo4003.domain.Role;
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

	@RequestMapping(value = "/edituser", method = RequestMethod.GET)
	public ModelAndView getUsers() {
		ModelAndView mv = new ModelAndView("edituserchoice");

		buildUserModel(mv);

		return mv;
	}

	@RequestMapping(value = "/getUserRoles", method = RequestMethod.POST)
	public ModelAndView editUser(String userToChange, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("edituserroles");

		User user = userRepository.findByIdul(userToChange);
		UserModel userModel = new UserModel(user);

		mv.addObject("user", userToChange);
		mv.addObject("roles", userModel.getRoles());

		return mv;
	}

	@RequestMapping(value = "/changeroles", method = RequestMethod.POST)
	public ModelAndView changeRoles(String userIdul, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("edituserchoice");

		User user = userRepository.findByIdul(userIdul);
		List<Role> roles = new ArrayList<Role>();

		if (request.getParameter("roleAdmin") != null) {
			roles.add(Role.ROLE_Administrateur);
		}
		if (request.getParameter("roleDirecteur") != null) {
			roles.add(Role.ROLE_Directeur);
		}
		if (request.getParameter("roleEnseignant") != null) {
			roles.add(Role.ROLE_Enseignant);
		}
		if (request.getParameter("roleResponsable") != null) {
			roles.add(Role.ROLE_Responsable);
		}
		if (request.getParameter("roleUsager") != null) {
			roles.add(Role.ROLE_Usager);
		}

		user.setRoles(roles);

		try {
			userRepository.store(user);
			mv.addObject("error", ControllerMessages.SUCCESS);
		} catch (Exception e) {
			mv.addObject("error", e.getMessage());
		}
		buildUserModel(mv);
		return mv;
	}

	public void buildUserModel(ModelAndView mv) {
		List<User> users = new ArrayList<User>(userRepository.findAll());
		List<UserModel> usersModel = new ArrayList<UserModel>();

		for (User user : users) {
			UserModel model = new UserModel();
			model.setIdul(user.getIdul());
			model.setName(user.getName());

			usersModel.add(model);
		}

		mv.addObject("users", usersModel);

	}
}
