package org.nachc.tools.fhirtoomop.util.fhir.parser.codesystem;

import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.dstu3.model.CodeSystem;
import org.hl7.fhir.dstu3.model.CodeSystem.ConceptDefinitionComponent;

import com.nach.core.util.fhir.parser.FhirJsonParser;

public class CodeSystemParser {

	private String json;
	
	private CodeSystem codeSystem;
	
	public CodeSystemParser(String json) {
		this.json = json;
		this.codeSystem = FhirJsonParser.parse(json, CodeSystem.class);
	}
	
	public List<CodeParser> getConcepts() {
		List<CodeParser> rtn = new ArrayList<CodeParser>();
		List<ConceptDefinitionComponent> defList = this.codeSystem.getConcept();
		for(ConceptDefinitionComponent def : defList) {
			CodeParser code = new CodeParser(def);
			rtn.add(code);
		}
		return rtn;
	}
	
}
