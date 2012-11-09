package cours.ulaval.glo4003.controller;

import java.util.ArrayList;

import javax.inject.Inject;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cours.ulaval.glo4003.controller.model.CalendarModel;
import cours.ulaval.glo4003.controller.model.SectionModel;
import cours.ulaval.glo4003.domain.Schedule;
import cours.ulaval.glo4003.domain.Section;
import cours.ulaval.glo4003.domain.Semester;
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

		mv.addObject("sections", JSON);
		return mv;
	}

	@RequestMapping(value = "/editsection/{id}/{year}/{semester}/{sectionNrc}", method = RequestMethod.GET)
	public ModelAndView editSection(@PathVariable String id, @PathVariable String year, @PathVariable Semester semester,
			@PathVariable String sectionNrc) {
		ModelAndView mv = new ModelAndView("partialViews/editSectionPartial");
		mv.addObject("semester", semester);
		mv.addObject("year", year);
		mv.addObject("id", id);

		Schedule schedule = scheduleRepository.findById(id);
		Section section = schedule.getSections().get(sectionNrc);
		mv.addObject("section", new SectionModel(section));

		return mv;
	}
}
