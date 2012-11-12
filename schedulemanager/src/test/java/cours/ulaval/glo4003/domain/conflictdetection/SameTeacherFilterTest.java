package cours.ulaval.glo4003.domain.conflictdetection;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

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
	private List<String> teachers;
	private List<Section> sections;

	@Before
	public void setUp() throws Exception {
		teachers = Arrays.asList(TEACHER);

		Section section = mock(Section.class);
		Section section2 = mock(Section.class);

		when(section.getNrc()).thenReturn("AN_NRC");
		when(section.getTeachers()).thenReturn(Arrays.asList(TEACHER));
		when(section.isOverlaping(any(Section.class))).thenReturn(true);

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
		verify(schedule, times(1)).add(any(Conflict.class));
	}
}
