package org.nachc.tools.fhirtoomop.util.fhir.parser.codesystem;

import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.dstu3.model.CodeSystem.ConceptDefinitionComponent;
import org.hl7.fhir.dstu3.model.CodeSystem.ConceptPropertyComponent;
import org.hl7.fhir.dstu3.model.Property;

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

	//
	// method to get if concept is abstract
	//
	
	public boolean isAbstract() {
		if(this.def == null) {
			return false;
		}
		try {
			List<ConceptPropertyComponent> props = this.def.getProperty();
			for(ConceptPropertyComponent prop : props) {
				if("abstract".equals(prop.getCode()) && prop.getValueBooleanType() != null && prop.getValueBooleanType().getValue() == true) {
					return true;
				}
			}
			return false;
		} catch(Exception exp) {
			return false;
		}
	}
	
	//
	// methods to get children
	//
	
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
				log.debug(code.getParentDisplay() + "\t" + code.getDisplay());
				rtn.add(code);
				getChildren(child, code, rtn);
			}
			return rtn;
		}
	}
	
}
