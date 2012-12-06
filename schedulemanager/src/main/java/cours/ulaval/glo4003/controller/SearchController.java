package cours.ulaval.glo4003.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cours.ulaval.glo4003.controller.model.CourseModel;
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

		List<CourseModel> foundCourses = new ArrayList<CourseModel>();
		String[] splitKeywords = keywords.split(KEYWORDS_DELIMITER);
		for (String keyword : splitKeywords) {
			for (Course course : courseRepository.findAll()) {
				if (Pattern.compile(Pattern.quote(keyword), Pattern.CASE_INSENSITIVE).matcher(course.getDescription()).find()) {
					CourseModel newCourse = createNewCourseModel(keyword, course);
					if (!foundCourses.contains(newCourse)) {
						foundCourses.add(newCourse);
					}
				}
			}
		}

		mv.addObject("keywords", keywords);
		mv.addObject("courses", foundCourses);
		return mv;
	}

	private CourseModel createNewCourseModel(String keyword, Course course) {
		CourseModel newCourse = new CourseModel();
		newCourse.setDescription(getHighlitedDescription(keyword, course));
		newCourse.setAcronym(course.getAcronym());
		newCourse.setTitle(course.getTitle());
		return newCourse;
	}

	private String getHighlitedDescription(String keyword, Course course) {
		return course.getDescription().replaceAll("(?i)" + keyword, "<span class=\"brightyellow\">" + keyword + "</span>");
	}
}
