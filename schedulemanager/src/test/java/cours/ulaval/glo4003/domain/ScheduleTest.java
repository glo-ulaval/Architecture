package cours.ulaval.glo4003.domain;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import cours.ulaval.glo4003.domain.Schedule;
import cours.ulaval.glo4003.domain.Section;

public class ScheduleTest {

	private static String AN_ID = "anId";
	private static String AN_NRC = "anNRC";

	private Schedule schedule;
	private Section section;

	@Before
	public void setUp() {
		section = mock(Section.class);
		when(section.getNrc()).thenReturn(AN_NRC);

		schedule = new Schedule(AN_ID);
	}

	@Test
	public void canAddASection() {
		schedule.add(section);

		assertEquals(section, schedule.getSections().get(AN_NRC));
	}

	@Test
	public void cannotAddTheSameSection() {
		schedule.add(section);

		schedule.add(section);

		assertEquals(1, schedule.getSections().size());
	}
}
