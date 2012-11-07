package cours.ulaval.glo4003.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cours.ulaval.glo4003.controller.model.AvailabilityModel;
import cours.ulaval.glo4003.domain.Availability;
import cours.ulaval.glo4003.domain.User;
import cours.ulaval.glo4003.domain.repository.AvailabilityRepository;

@Controller
@RequestMapping(value = "/availabilities")
public class AvailabilitiesController {

	private ObjectMapper mapper = new ObjectMapper();

	@Inject
	private AvailabilityRepository repository;

	@RequestMapping(value = "")
	public ModelAndView availabilities(HttpServletRequest request) {

		User user = (User) request.getSession().getAttribute("user");
		Availability availability = repository.findByIdul(user.getIdul());
		ModelAndView mv = new ModelAndView("availabilities");
		if (availability != null) {
			AvailabilityModel model = availability.getModel();

			String availabilitiesJSON;
			try {
				availabilitiesJSON = mapper.writeValueAsString(model);

				mv.addObject("availabilitiesJSON", availabilitiesJSON);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return mv;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public String availabilities(@RequestBody String availabilityJSON, HttpServletRequest request) {

		User user = (User) request.getSession().getAttribute("user");

		AvailabilityModel model;
		try {
			model = mapper.readValue(availabilityJSON, AvailabilityModel.class);
			Availability availability = new Availability(model, user.getIdul());
			repository.store(availability);
		} catch (Exception e) {
			return "Une erreur est survenue, veuillez réessayer.";
		}

		return "Vos disponibilités ont été enregistrées avec succès";
	}

}
