package org.nachc.tools.fhirtoomop.fhir.parser.valueset;

import org.hl7.fhir.dstu3.model.ValueSet;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValueSetParser {
	
	private ValueSet valueSet;
	
	public ValueSetParser(ValueSet valueSet) {
		this.valueSet = valueSet;
	}
	
	public String getName() {
		return valueSet.getName();
	}

}
