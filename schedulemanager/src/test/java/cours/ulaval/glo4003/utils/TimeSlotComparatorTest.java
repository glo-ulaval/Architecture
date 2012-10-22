package cours.ulaval.glo4003.utils;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import cours.ulaval.glo4003.controller.model.CourseSlotModel;

public class TimeSlotComparatorTest {

	@Mock
	private CourseSlotModel mockedCourseSlotModel;

	@Mock
	private CourseSlotModel mockedEqualCourseSlotModel;

	@Mock
	private CourseSlotModel mockedLaterCourseSlotModel;

	@Mock
	private CourseSlotModel mockedSoonerCourseSlotModel;

	private static final String A_TIME = "9:30";
	private static final String AN_EQUAL_TIME = "9:30";
	private static final String A_LATER_TIME = "10:30";
	private static final String A_SOONER_TIME = "8:30";

	private static final int IS_SOONER = -1;
	private static final int IS_LATER_OR_EQUAL = 1;

	private TimeSlotComparator comparator;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		comparator = new TimeSlotComparator();

		when(mockedCourseSlotModel.getTimeSlotStart()).thenReturn(A_TIME);
		when(mockedEqualCourseSlotModel.getTimeSlotStart()).thenReturn(AN_EQUAL_TIME);
		when(mockedLaterCourseSlotModel.getTimeSlotStart()).thenReturn(A_LATER_TIME);
		when(mockedSoonerCourseSlotModel.getTimeSlotStart()).thenReturn(A_SOONER_TIME);
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
	public void canCompareWithSoonerCourseSlots() {
		assertEquals(IS_LATER_OR_EQUAL, comparator.compare(mockedCourseSlotModel, mockedSoonerCourseSlotModel));
	}

	@Test
	public void canCompareWithLaterCourseSlots() {
		assertEquals(IS_SOONER, comparator.compare(mockedCourseSlotModel, mockedLaterCourseSlotModel));
	}
}
