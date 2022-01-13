package org.nachc.tools.fhirtoomop.util.fhir.parser.codesystem;

import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.dstu3.model.CodeSystem.ConceptDefinitionComponent;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CodeParser {

	//
	// instance variables
	//
	
	private ConceptDefinitionComponent def;

	private CodeParser parent;
	
	//
	// constructors
	//
	
	public CodeParser(ConceptDefinitionComponent def) {
		this(def, null);
	}

	public CodeParser(ConceptDefinitionComponent def, CodeParser parent) {
		this.def = def;
		this.parent = parent;
	}
	
	//
	// trivial getters
	//
	
	public ConceptDefinitionComponent getConceptDefinitionComponent() {
		return this.def;
	}

	public CodeParser getParent() {
		return this.parent;
	}
	
	//
	// getters
	//
	
	public String getCode() {
		return this.def.getCode();
	}

	public String getDisplay() {
		return this.def.getDisplay();
	}
	
	public String getDefinition() {
		return this.def.getDefinition();
	}
	
	public String getParentDisplay() {
		if(this.parent == null) {
			return null;
		} else {
			return parent.getDisplay();
		}
	}
	
	public List<CodeParser> getChildren() {
		return getChildren(null, this, new ArrayList<CodeParser>());
	}
	
	private List<CodeParser> getChildren(CodeParser parent, CodeParser child, List<CodeParser> rtn) {
		List<ConceptDefinitionComponent> defs = child.def.getConcept();
		if(defs == null || defs.size() == 0) {
			return rtn;
		} else {
			for(ConceptDefinitionComponent def : defs) {
				CodeParser code = new CodeParser(def, parent);
				code.parent = child;
				log.info(code.getParentDisplay() + "\t" + code.getDisplay());
				rtn.add(code);
				getChildren(child, code, rtn);
			}
			return rtn;
		}
	}
	
}
