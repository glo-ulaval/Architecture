package cours.ulaval.glo4003.controller;

import java.util.ArrayList;

import javax.inject.Inject;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cours.ulaval.glo4003.controller.model.CalendarModel;
import cours.ulaval.glo4003.domain.Section;
import cours.ulaval.glo4003.domain.repository.ScheduleRepository;

@Controller
@RequestMapping(value = "/calendar")
public class CalendarController {

	@Inject
	private ScheduleRepository scheduleRepository;

	private ObjectMapper mapper = new ObjectMapper();

	@RequestMapping(value = "/{id}")
	public ModelAndView calendarById(@PathVariable String id) {

		ModelAndView mv = new ModelAndView("calendar");

		CalendarModel calendarModel = new CalendarModel(new ArrayList<Section>(scheduleRepository.findById(id).getSections().values()));

		String JSON = "";
		try {
			JSON = mapper.writeValueAsString(calendarModel);
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(JSON);

		mv.addObject("sections", JSON);
		return mv;
	}
}
