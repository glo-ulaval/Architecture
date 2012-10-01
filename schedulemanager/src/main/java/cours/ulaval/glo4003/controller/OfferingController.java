package cours.ulaval.glo4003.controller;

import java.util.ArrayList;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cours.ulaval.glo4003.model.Course;
import cours.ulaval.glo4003.model.CourseRepository;
import cours.ulaval.glo4003.model.Offering;
import cours.ulaval.glo4003.model.OfferingRepository;

@Controller
public class OfferingController {

	@Inject
	CourseRepository courseRepository;

	@Inject
	OfferingRepository offeringRepository;
	
	@RequestMapping(value = "/offering")
	public ModelAndView offering() throws Exception {

		ModelAndView mv = new ModelAndView("offering");
		mv.addObject("years", offeringRepository.findYears());
		return mv;
	}
	
	@RequestMapping(value = "/offering/{year}", method = RequestMethod.GET )
	public ModelAndView offeringByYear(@PathVariable String year) throws Exception {

		Offering offering = offeringRepository.find(year);
		ArrayList<Course> courses = new ArrayList<Course>();
		for(String acronym : offering.getAcronyms()){
//			courses.add(courseRepository)
		}
				
		ModelAndView mv = new ModelAndView("offering");
		mv.addObject("year", year);
//		mv.addObject("offering", offering.);
		return mv;
	}
}
