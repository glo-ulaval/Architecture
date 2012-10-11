package cours.ulaval.glo4003.controller;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cours.ulaval.glo4003.controller.model.AvailabilityModel;

@Controller
@RequestMapping(value = "/availabilities")
public class TeacherController {

	@RequestMapping(value = "")
	public String availabilities() {
		return "availabilities";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public String availabilities(@RequestBody String availabilityJSON) {

		ObjectMapper mapper = new ObjectMapper();
		AvailabilityModel availability;
		try {
			availability = mapper.readValue(availabilityJSON, AvailabilityModel.class);
		} catch (Exception e) {
			return "Une erreur est survenue, veuillez réessayer.";
		}

		return "Vos disponibilités ont été enregistrés avec succès";
	}

}
