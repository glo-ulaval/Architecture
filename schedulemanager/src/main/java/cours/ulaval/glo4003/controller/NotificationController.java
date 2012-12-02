package cours.ulaval.glo4003.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cours.ulaval.glo4003.domain.repository.UserRepository;

@Controller
@RequestMapping(value = "/notification")
public class NotificationController {

	@Inject
	private UserRepository userRepository;

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public String notification(String idul) {
		return "voici une notification";
	}

}
