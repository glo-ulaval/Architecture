package cours.ulaval.glo4003.domain.conflictdetection;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import cours.ulaval.glo4003.domain.Schedule;
import cours.ulaval.glo4003.domain.Section;
import cours.ulaval.glo4003.domain.conflictdetection.conflict.Conflict;

public class SameTeacherFilterTest {

	private static final String TEACHER = "teacher1";

	private Schedule schedule;
	private SameTeacherFilter filter;
	private List<Section> sections;
	private List<Conflict> conflicts;

	@Before
	public void setUp() throws Exception {
		Section section = mock(Section.class);
		Section section2 = mock(Section.class);
		conflicts = new ArrayList<Conflict>();

		when(section.getNrc()).thenReturn("AN_NRC");
		when(section.getTeachers()).thenReturn(Arrays.asList(TEACHER));
		when(section.generateSameTeacherConflicts(section2)).thenReturn(conflicts);

		when(section2.getNrc()).thenReturn("AN_NRC2");
		when(section2.hasTeacher(TEACHER)).thenReturn(true);

		sections = Arrays.asList(section, section2);
		schedule = mock(Schedule.class);
		when(schedule.getSectionsList()).thenReturn(sections);

		filter = new SameTeacherFilter();
	}

	@Test
	public void canVerifyIsThereIsAConflictForTwoSectionForATeacher() {
		filter.run(schedule);

		verify(schedule, times(1)).addAll(any(List.class));
		assertEquals(conflicts, schedule.getConflicts());
	}
}
