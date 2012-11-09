package cours.ulaval.glo4003.persistence;

import javax.xml.bind.annotation.XmlRootElement;

import cours.ulaval.glo4003.domain.ProgramSheet;

@XmlRootElement(name = "programsheets")
public class ProgramSheetXMLWrapper {

	private ProgramSheet programSheetIFT;
	private ProgramSheet programSheetGLO;

	public ProgramSheet getProgramSheetIFT() {
		return programSheetIFT;
	}

	public void setProgramSheetIFT(ProgramSheet programSheet) {
		this.programSheetIFT = programSheet;
	}

	public ProgramSheet getProgramSheetGLO() {
		return programSheetGLO;
	}

	public void setProgramSheetGLO(ProgramSheet programSheetGLO) {
		this.programSheetGLO = programSheetGLO;
	}

}
