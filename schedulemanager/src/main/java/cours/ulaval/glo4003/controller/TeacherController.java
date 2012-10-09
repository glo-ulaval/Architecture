package cours.ulaval.glo4003.controller;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cours.ulaval.glo4003.controller.model.AvailabilityModel;

@Controller
@RequestMapping(value = "/availabilities")
public class TeacherController {

	@RequestMapping(value = "")
	public String availibilities() {
		return "availabilities";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String availibilities(@RequestBody String availibilityJSON) {

		ObjectMapper mapper = new ObjectMapper();
		AvailabilityModel availibility;
		try {
			availibility = mapper.readValue(availibilityJSON, AvailabilityModel.class);
			System.out.println("AVAILIBITY RECEIVED !!! : " + availibility.getMonday().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "availabilities";
	}

}
