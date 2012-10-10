package cours.ulaval.glo4003.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cours.ulaval.glo4003.domain.Course;
import cours.ulaval.glo4003.domain.CourseRepository;
import cours.ulaval.glo4003.domain.Offering;
import cours.ulaval.glo4003.domain.OfferingRepository;
import cours.ulaval.glo4003.domain.Semester;

@Controller
@RequestMapping(value = "/offering")
public class OfferingController {

	private static final String SUCCESS = "success";

	@Inject
	CourseRepository courseRepository;

	@Inject
	OfferingRepository offeringRepository;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView offering()
			throws Exception {
		ModelAndView mv = new ModelAndView("offering");
		mv.addObject("years", offeringRepository.findYears());
		return mv;
	}

	@RequestMapping(value = "/{year}/{semester}", method = RequestMethod.GET)
	public ModelAndView offeringBySemester(@PathVariable String year, @PathVariable Semester semester)
			throws Exception {

		Offering offering = offeringRepository.find(year, semester);

		List<Course> courses = courseRepository.findByOffering(offering);

		ModelAndView mv = new ModelAndView("offeringbysemester");
		mv.addObject("year", year);
		mv.addObject("semester", semester);
		mv.addObject("courses", courses);

		return mv;
	}

	@RequestMapping(value = "/{year}/deletecourse")
	public ModelAndView deleteCourse(@PathVariable String year,
			@RequestParam(required = true, value = "semester") String semester,
			@RequestParam(required = true, value = "acronym") String acronym) {

		ModelAndView mv = new ModelAndView("offeringbysemester");

		try {
			Offering offering = offeringRepository.find(year, Semester.valueOf(semester));
			offering.removeCourse(acronym);

			List<Course> courses = courseRepository.findByOffering(offering);

			mv.addObject("year", year);
			mv.addObject("semester", semester);
			mv.addObject("courses", courses);
			mv.addObject("error", SUCCESS);
		} catch (Exception e) {
			mv.addObject("error", e.getMessage());
		}

		return mv;
	}

	@RequestMapping(value = "/{year}/availablecourses")
	public ModelAndView availableCourses(@PathVariable String year,
			@RequestParam(required = true, value = "semester") String semester)
			throws Exception {

		ModelAndView mv = new ModelAndView("availablecourses");
		mv.addObject("year", year);
		mv.addObject("semester", semester);
		mv.addObject("courses", courseRepository.findAll());

		return mv;
	}

	@RequestMapping(value = "/{year}/addcourse")
	public ModelAndView addCourseFromAvailableCourses(@PathVariable String year,
			@RequestParam(required = true, value = "semester") String semester,
			@RequestParam(required = true, value = "acronym") String acronym) {

		ModelAndView mv = new ModelAndView("offeringbysemester");

		try {
			Semester semesterValue = Semester.valueOf(semester);
			if (!offeringRepository.containsOfferingFor(year, semesterValue)) {
				offeringRepository.store(new Offering(year, semesterValue));
			}
			Offering offering = offeringRepository.find(year, semesterValue);
			offering.addCourse(acronym);

			List<Course> courses = courseRepository.findByOffering(offering);

			mv.addObject("year", year);
			mv.addObject("semester", semester);
			mv.addObject("courses", courses);
			mv.addObject("error", SUCCESS);
		} catch (Exception e) {
			mv.addObject("error", e.getMessage());
		}

		return mv;
	}
}
