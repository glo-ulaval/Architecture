package cours.ulaval.glo4003.controller;

import javax.inject.Inject;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cours.ulaval.glo4003.domain.Notification;
import cours.ulaval.glo4003.domain.User;
import cours.ulaval.glo4003.domain.repository.UserRepository;

@Controller
@RequestMapping(value = "/notification")
public class NotificationController {

	@Inject
	private UserRepository userRepository;

	@Inject
	private ObjectMapper mapper;

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public String notification(String idul) {
		String msg = "[";
		User user = userRepository.findByIdul(idul);

		for (Notification notification : user.getNotifications()) {
			msg += convertToJson(notification);

			if (user.getNotifications().indexOf(notification) < user.getNotifications().size() - 1) {
				msg += ",";
			}
		}

		msg += "]";

		return msg;
	}

	@RequestMapping(value = "/{idul}/{id}", method = RequestMethod.GET)
	public ModelAndView handleNotification(@PathVariable int id, @PathVariable String idul) throws Exception {
		User user = userRepository.findByIdul(idul);

		for (Notification notification : user.getNotifications()) {
			if (notification.getId() == id) {
				user.removeNotification(notification);
				userRepository.store(user);
				return new ModelAndView("redirect:" + notification.getPath());
			}
		}
		ModelAndView mv = new ModelAndView("menu");
		mv.addObject("error", "L'usager ou la notification sont introuvables. Veuillez contacter Jonathan Rochette");
		return mv;
	}

	private String convertToJson(Notification notification) {
		String JSON = "";
		try {
			JSON = mapper.writeValueAsString(notification);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON;
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public void setObjectMapper(ObjectMapper mapper) {
		this.mapper = mapper;
	}

}
