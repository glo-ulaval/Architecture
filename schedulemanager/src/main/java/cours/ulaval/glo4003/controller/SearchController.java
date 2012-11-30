package cours.ulaval.glo4003.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cours.ulaval.glo4003.domain.Course;
import cours.ulaval.glo4003.domain.repository.CourseRepository;

@Controller
@RequestMapping(value = "/search")
public class SearchController {

	private static final String KEYWORDS_DELIMITER = "\\s";
	@Inject
	private CourseRepository courseRepository;

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView search(@RequestParam(required = true, value = "keywords") String keywords) throws Exception {
		ModelAndView mv = new ModelAndView("searchresults");

		List<Course> foundCourses = new ArrayList<Course>();
		String[] splitKeywords = keywords.split(KEYWORDS_DELIMITER);
		for (String keyword : splitKeywords) {
			for (Course course : courseRepository.findAll()) {
				if (course.getDescription().contains(keyword)) {
					course.setDescription(getHighlitedDescription(keyword, course));
					if (!foundCourses.contains(course)) {
						foundCourses.add(course);
					}
				}
			}
		}

		mv.addObject("keywords", keywords);
		mv.addObject("courses", foundCourses);
		return mv;
	}

	private String getHighlitedDescription(String keyword, Course course) {
		return course.getDescription().replace(keyword, "<span class=\"brightyellow\">" + keyword + "</span>");
	}
}
