package cours.ulaval.glo4003.controllers.model.utils;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import cours.ulaval.glo4003.controller.model.CourseSlotModel;
import cours.ulaval.glo4003.controller.model.utils.TimeSlotComparator;

public class TimeSlotComparatorTest {

	@Mock
	private CourseSlotModel mockedCourseSlotModel;

	@Mock
	private CourseSlotModel mockedEqualCourseSlotModel;

	@Mock
	private CourseSlotModel mockedLaterCourseSlotModel;

	@Mock
	private CourseSlotModel mockedSoonerCourseSlotModel;

	@Mock
	private CourseSlotModel mockedLaterDayCourseSlotModel;

	@Mock
	private CourseSlotModel mockedSoonerDayCourseSlotModel;

	private static final String A_TIME = "9:30";
	private static final String AN_EQUAL_TIME = "9:30";
	private static final String A_LATER_TIME = "10:30";
	private static final String A_SOONER_TIME = "8:30";

	private static final String A_DAY_OF_WEEK = "TUESDAY";
	private static final String A_LATER_DAY_OF_WEEK = "MONDAY";
	private static final String A_SOONER_DAY_OF_WEEK = "FRIDAY";

	private static final int IS_SOONER = -1;
	private static final int IS_LATER_OR_EQUAL = 1;

	private TimeSlotComparator comparator;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		comparator = new TimeSlotComparator();

		when(mockedCourseSlotModel.getTimeSlotStart()).thenReturn(A_TIME);
		when(mockedCourseSlotModel.getDayOfWeek()).thenReturn(A_DAY_OF_WEEK);

		when(mockedEqualCourseSlotModel.getTimeSlotStart()).thenReturn(AN_EQUAL_TIME);
		when(mockedEqualCourseSlotModel.getDayOfWeek()).thenReturn(A_DAY_OF_WEEK);

		when(mockedLaterCourseSlotModel.getTimeSlotStart()).thenReturn(A_LATER_TIME);
		when(mockedLaterCourseSlotModel.getDayOfWeek()).thenReturn(A_DAY_OF_WEEK);

		when(mockedSoonerCourseSlotModel.getTimeSlotStart()).thenReturn(A_SOONER_TIME);
		when(mockedSoonerCourseSlotModel.getDayOfWeek()).thenReturn(A_DAY_OF_WEEK);

		when(mockedLaterDayCourseSlotModel.getTimeSlotStart()).thenReturn(AN_EQUAL_TIME);
		when(mockedLaterDayCourseSlotModel.getDayOfWeek()).thenReturn(A_LATER_DAY_OF_WEEK);

		when(mockedSoonerDayCourseSlotModel.getTimeSlotStart()).thenReturn(AN_EQUAL_TIME);
		when(mockedSoonerDayCourseSlotModel.getDayOfWeek()).thenReturn(A_SOONER_DAY_OF_WEEK);
	}

	@Test
	public void canInstantiateAComparatorCourseTimeSlot() {
		assertNotNull(comparator);
	}

	@Test
	public void canCompareTwoEqualCourseSlots() {
		assertEquals(IS_LATER_OR_EQUAL, comparator.compare(mockedCourseSlotModel, mockedEqualCourseSlotModel));
	}

	@Test
	public void canCompareWithSoonerTimeCourseSlots() {
		assertEquals(IS_LATER_OR_EQUAL, comparator.compare(mockedCourseSlotModel, mockedSoonerCourseSlotModel));
	}

	@Test
	public void canCompareWithLaterTimeCourseSlots() {
		assertEquals(IS_SOONER, comparator.compare(mockedCourseSlotModel, mockedLaterCourseSlotModel));
	}

	@Test
	public void canCompareWithLaterDayCourseSlots() {
		assertEquals(IS_LATER_OR_EQUAL, comparator.compare(mockedCourseSlotModel, mockedLaterDayCourseSlotModel));
	}

	@Test
	public void canCompareWithSoonerDayCourseSlots() {
		assertEquals(IS_SOONER, comparator.compare(mockedCourseSlotModel, mockedSoonerDayCourseSlotModel));
	}
}
