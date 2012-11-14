package cours.ulaval.glo4003.controller;

import java.lang.reflect.Method;
import java.security.Principal;
import java.util.List;

import org.apache.commons.lang.WordUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cours.ulaval.glo4003.controller.model.CourseSlotModel;
import cours.ulaval.glo4003.controller.model.SortedSlotsCache;
import cours.ulaval.glo4003.controller.model.SortedSlotsModel;

@Controller
@RequestMapping(value = "/conflict")
public class ConflictController {

	@RequestMapping(value = "/{day}/{timeSlotId}")
	public ModelAndView getConflict(@PathVariable String day, @PathVariable Integer timeSlotId, Principal principal) throws Exception {
		ModelAndView mv = new ModelAndView("conflict");
		SortedSlotsModel model = SortedSlotsCache.getCache().getCachedValue(principal.getName());

		Method method = model.getClass().getMethod("get" + WordUtils.capitalize(day));
		List<CourseSlotModel> courseSlots = (List<CourseSlotModel>) method.invoke(model);

		mv.addObject("conflicts", courseSlots.get(timeSlotId).getConflicts());

		return mv;
	}
}
