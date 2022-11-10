package org.nachc.tools.fhirtoomop.fhir.parser.valueset;

import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.dstu3.model.ValueSet;
import org.hl7.fhir.dstu3.model.Coding;
import org.hl7.fhir.dstu3.model.ValueSet.ConceptReferenceComponent;
import org.hl7.fhir.dstu3.model.ValueSet.ConceptSetComponent;
import org.hl7.fhir.dstu3.model.ValueSet.ValueSetExpansionContainsComponent;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Slf4j
public class ValueSetParser {
	
	private ValueSet valueSet;
	
	public ValueSetParser(ValueSet valueSet) {
		this.valueSet = valueSet;
	}
	
	public String getName() {
		return valueSet.getName();
	}

	public List<Coding> getConcepts() {
		ArrayList<Coding> rtn = new ArrayList<>();
		List<ConceptSetComponent> component = valueSet.getCompose().getInclude();
		log.info("Got " + component.size() + " values");
		log.info("Got values");
		for(ConceptSetComponent comp : component) {
			List<ConceptReferenceComponent> concepts =  comp.getConcept();
			for(ConceptReferenceComponent concept : concepts) {
				Coding coding = new Coding();
				coding.setSystem(comp.getSystem());
				coding.setCode(concept.getCode());
				coding.setDisplay(concept.getDisplay());
				rtn.add(coding);
			}
		}
		List<ValueSetExpansionContainsComponent> expansionList = valueSet.getExpansion().getContains();
		for(ValueSetExpansionContainsComponent expansion : expansionList) {
			Coding coding = new Coding();
			coding.setSystem(expansion.getSystem());
			coding.setCode(expansion.getCode());
			coding.setDisplay(expansion.getDisplay());
			rtn.add(coding);
		}
		return rtn;
	}
	
}
