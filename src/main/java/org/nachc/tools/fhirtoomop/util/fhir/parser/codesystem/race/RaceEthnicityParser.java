package org.nachc.tools.fhirtoomop.util.fhir.parser.codesystem.race;

import java.util.List;

import org.nachc.tools.fhirtoomop.util.fhir.parser.codesystem.CodeParser;
import org.nachc.tools.fhirtoomop.util.fhir.parser.codesystem.CodeSystemParser;

public class RaceEthnicityParser {

	private String json;
	
	private CodeSystemParser codeSystem;
	
	public RaceEthnicityParser(String json) {
		this.json = json;
		this.codeSystem = new CodeSystemParser(json);
	}
	
	public List<CodeParser> getRaces() {
		List<CodeParser> codes = this.codeSystem.getConcepts();
		for(CodeParser code : codes) {
			if("1000-9".equals(code.getCode())) {
				return code.getChildren();
			}
		}
		return null;
	}

}
