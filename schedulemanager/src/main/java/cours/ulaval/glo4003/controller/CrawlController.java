package cours.ulaval.glo4003.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cours.ulaval.glo4003.domain.repository.CourseRepository;
import cours.ulaval.glo4003.utils.crawlers.Crawler;

@Controller
@RequestMapping(value = "/crawl")
public class CrawlController {

	@Inject
	private CourseRepository courseRepository;

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView crawl() {
		ModelAndView mv = new ModelAndView("menu");

		try {
			Crawler crawler = new Crawler(courseRepository);
			crawler.crawl();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return mv;
	}
}
