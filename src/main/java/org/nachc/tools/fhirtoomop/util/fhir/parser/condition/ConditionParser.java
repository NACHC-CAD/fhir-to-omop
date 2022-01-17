package org.nachc.tools.fhirtoomop.util.fhir.parser.condition;

import org.hl7.fhir.dstu3.model.Coding;
import org.hl7.fhir.dstu3.model.Condition;

public class ConditionParser {

	private Condition con;

	public ConditionParser(Condition con) {
		this.con = con;
	}

	public Coding getCoding() {
		// TODO: (JEG) code can have multiple codings
		// (were just taking the first one for now)
		try {
			return this.con.getCode().getCodingFirstRep();
		} catch (NullPointerException exp) {
			return null;
		}
	}

	public String getCode() {
		return getCoding() == null ? null : getCoding().getCode();
	}

	public String getSystem() {
		return getCoding() == null ? null : getCoding().getSystem();
	}

	public String getDisplay() {
		return getCoding() == null ? null : getCoding().getDisplay();
	}

}
