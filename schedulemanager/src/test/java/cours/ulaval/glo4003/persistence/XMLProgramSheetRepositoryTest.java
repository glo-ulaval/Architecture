package cours.ulaval.glo4003.persistence;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import cours.ulaval.glo4003.domain.ProgramSheet;
import cours.ulaval.glo4003.utils.ConfigManager;

public class XMLProgramSheetRepositoryTest {

	private XMLProgramSheetRepository repository;
	private XMLSerializer<ProgramSheetXMLWrapper> mockedSerializer;

	private ProgramSheet programSheetGLO = mock(ProgramSheet.class);
	private ProgramSheet programSheetIFT = mock(ProgramSheet.class);

	@Before
	public void setUp() throws Exception {
		mockedSerializer = mock(XMLSerializer.class);
		ProgramSheetXMLWrapper dto = new ProgramSheetXMLWrapper();
		dto.setProgramSheetGLO(programSheetGLO);
		dto.setProgramSheetIFT(programSheetIFT);
		ConfigManager resourcesPaths = ConfigManager.getConfigManager();
		when(mockedSerializer.deserialize(resourcesPaths.getProgramSheetFilePath())).thenReturn(dto);

		repository = new XMLProgramSheetRepository(mockedSerializer);
	}

	@Test
	public void canInstantiateRepository() throws Exception {
		XMLProgramSheetRepository repository = new XMLProgramSheetRepository();

		assertNotNull(repository);
	}

	@Test
	public void canGetProgramSheetGLO() {
		assertEquals(programSheetGLO, repository.getProgramSheetGLO());
	}

	@Test
	public void canGetProgramSheetIFT() {
		assertEquals(programSheetIFT, repository.getProgramSheetIFT());
	}
}
