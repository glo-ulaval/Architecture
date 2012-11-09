package cours.ulaval.glo4003.persistence;

import cours.ulaval.glo4003.domain.ProgramSheet;
import cours.ulaval.glo4003.domain.repository.ProgramSheetRepository;
import cours.ulaval.glo4003.utils.ConfigManager;

public class XMLProgramSheetRepository implements ProgramSheetRepository {

	private XMLSerializer<ProgramSheetXMLWrapper> serializer;
	private ProgramSheet programSheetIFT;
	private ProgramSheet programSheetGLO;

	public XMLProgramSheetRepository() throws Exception {
		serializer = new XMLSerializer<ProgramSheetXMLWrapper>(ProgramSheetXMLWrapper.class);
		parseXML();
	}

	public ProgramSheet getProgramSheetGLO() {
		return programSheetGLO;
	}

	public ProgramSheet getProgramSheetIFT() {
		return programSheetIFT;
	}

	private void parseXML() throws Exception {
		ProgramSheetXMLWrapper wrapper = serializer.deserialize(ConfigManager.getConfigManager().getProgramSheetFilePath());
		programSheetIFT = wrapper.getProgramSheetIFT();
		programSheetGLO = wrapper.getProgramSheetGLO();
	}

	// Do not use : for test purpose only
	protected XMLProgramSheetRepository(XMLSerializer<ProgramSheetXMLWrapper> serializer) throws Exception {
		this.serializer = serializer;
		parseXML();
	}
}
