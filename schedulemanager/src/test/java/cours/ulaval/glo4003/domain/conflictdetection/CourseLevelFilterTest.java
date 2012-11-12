package cours.ulaval.glo4003.domain.conflictdetection;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import cours.ulaval.glo4003.domain.ProgramSheet;
import cours.ulaval.glo4003.domain.Schedule;
import cours.ulaval.glo4003.domain.Section;
import cours.ulaval.glo4003.domain.TimeSlot;
import cours.ulaval.glo4003.domain.conflictdetection.conflict.Conflict;
import cours.ulaval.glo4003.persistence.XMLProgramSheetRepository;

public class CourseLevelFilterTest {

	private ProgramSheet iftProgamSheetMock;
	private ProgramSheet gloProgamSheetMock;
	private XMLProgramSheetRepository repositoryMock;
	private Schedule scheduleMock;

	@Before
	public void setUp() {
		iftProgamSheetMock = mock(ProgramSheet.class);
		gloProgamSheetMock = mock(ProgramSheet.class);

		repositoryMock = mock(XMLProgramSheetRepository.class);
		when(repositoryMock.getProgramSheetGLO()).thenReturn(gloProgamSheetMock);
		when(repositoryMock.getProgramSheetIFT()).thenReturn(iftProgamSheetMock);

		TimeSlot timeSlotMock = mock(TimeSlot.class);
		when(timeSlotMock.isOverlapping(any(TimeSlot.class))).thenReturn(true);
		List<TimeSlot> timeSlotsMocks = Arrays.asList(timeSlotMock);

		Section aSectionMock = mock(Section.class);
		when(aSectionMock.getCourseAcronym()).thenReturn("IFT-1000");
		when(aSectionMock.getCoursesAndLabTimeSlots()).thenReturn(timeSlotsMocks);
		Section anotherSectionMock = mock(Section.class);
		when(anotherSectionMock.getCourseAcronym()).thenReturn("GLO-1000");
		when(anotherSectionMock.getCoursesAndLabTimeSlots()).thenReturn(timeSlotsMocks);
		List<Section> sectionsMocks = Arrays.asList(aSectionMock, anotherSectionMock);

		scheduleMock = mock(Schedule.class);
		when(scheduleMock.getSectionsList()).thenReturn(sectionsMocks);
	}

	@Test
	public void canInstantiateACourseLevelFilter() {
		CourseLevelFilter filter = new CourseLevelFilter();

		assertNotNull(filter);
	}

	@Test
	public void canDetectSameLevelCourseConflictInIFTProgramSheet() {
		when(iftProgamSheetMock.areCoursesSameLevel("IFT-1000", "GLO-1000")).thenReturn(true);
		when(gloProgamSheetMock.areCoursesSameLevel("IFT-1000", "GLO-1000")).thenReturn(false);

		CourseLevelFilter filter = new CourseLevelFilter();
		filter.setRepository(repositoryMock);

		filter.run(scheduleMock);

		verify(iftProgamSheetMock).areCoursesSameLevel("IFT-1000", "GLO-1000");
		verify(scheduleMock).addAll(anyListOf(Conflict.class));
	}

	@Test
	public void canDetectSameLevelCourseConflictInGLOProgramSheet() {
		when(iftProgamSheetMock.areCoursesSameLevel("IFT-1000", "GLO-1000")).thenReturn(false);
		when(gloProgamSheetMock.areCoursesSameLevel("IFT-1000", "GLO-1000")).thenReturn(true);

		CourseLevelFilter filter = new CourseLevelFilter();
		filter.setRepository(repositoryMock);

		filter.run(scheduleMock);

		verify(gloProgamSheetMock).areCoursesSameLevel("IFT-1000", "GLO-1000");
		verify(scheduleMock).addAll(anyListOf(Conflict.class));
	}
}
