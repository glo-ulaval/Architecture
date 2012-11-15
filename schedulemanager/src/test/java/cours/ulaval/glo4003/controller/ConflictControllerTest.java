package cours.ulaval.glo4003.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import cours.ulaval.glo4003.controller.model.CourseSlotModel;
import cours.ulaval.glo4003.controller.model.SortedSlotsCache;
import cours.ulaval.glo4003.controller.model.SortedSlotsModel;
import cours.ulaval.glo4003.domain.Time;
import cours.ulaval.glo4003.domain.TimeSlot;
import cours.ulaval.glo4003.domain.TimeSlot.DayOfWeek;
import cours.ulaval.glo4003.domain.conflictdetection.conflict.Conflict;
import cours.ulaval.glo4003.domain.conflictdetection.conflict.UnavailableTeacherConflict;

public class ConflictControllerTest {

	private final static String A_DAY = "monday";
	private final static String A_USERNAME = "NATAW";
	private Principal principal;

	@Before
	public void setUp() {
		principal = mock(Principal.class);
		when(principal.getName()).thenReturn(A_USERNAME);
		SortedSlotsModel model = new SortedSlotsModel();
		CourseSlotModel courseSlot = new CourseSlotModel();
		courseSlot.addConflict(new UnavailableTeacherConflict("98765", A_USERNAME, new TimeSlot(new Time(8, 30), 2, DayOfWeek.MONDAY)));
		model.setMonday(Arrays.asList(courseSlot));
		SortedSlotsCache.getCache().setCachedValue(A_USERNAME, model);
	}

	@After
	public void tearDown() {
		SortedSlotsCache.getCache().clearCache();
	}

	@Test
	public void canGetConflicts() throws Exception {
		ConflictController controller = new ConflictController();
		ModelAndView mv = controller.getConflict(A_DAY, 0, principal);

		assertEquals(1, ((List<Conflict>) mv.getModel().get("conflicts")).size());
	}
}
