package cours.ulaval.glo4003.controller;

import javax.inject.Inject;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

	private String convertToJson(Notification notification) {
		String JSON = "";
		try {
			JSON = mapper.writeValueAsString(notification);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON;
	}

}
