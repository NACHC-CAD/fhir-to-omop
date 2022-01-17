package org.nachc.tools.fhirtoomop.util.fhir.parser.encounter;

import java.util.Date;

import org.hl7.fhir.dstu3.model.Coding;
import org.hl7.fhir.dstu3.model.Encounter;

public class EncounterParser {

	//
	// instance variables
	//

	private Encounter enc;

	//
	// constructor
	//

	public EncounterParser(Encounter enc) {
		this.enc = enc;
	}

	//
	// id stuff
	//

	public String getEncounterId() {
		return this.enc.getId();
	}

	public String getEncounterIdUnqualified() {
		// TODO: (JEG) There is probably a method in the HAPI API that does this more
		// cleanly
		String str = this.enc.getId();
		if (str.indexOf('/') > 0) {
			str = str.substring((str.indexOf('/') + 1), str.length());
			if (str.indexOf('/') > 0) {
				str = str.substring(0, str.indexOf('/'));
				return str;
			}
		}
		return this.enc.getId();
	}

	public String getEncounterIdUncAndQual() {
		String rtn = "";
		rtn += getEncounterIdUnqualified() + "|";
		rtn += getEncounterId();
		return rtn;
	}

	//
	// type
	//

	public Coding getEncounterType() {
		// TODO: (JEG) Just getting first for now
		try {
			Coding rtn = this.enc.getTypeFirstRep().getCodingFirstRep();
			return rtn;
		} catch(NullPointerException npe) {
			return null;
		}
	}

	//
	// dates
	//

	public Date getStartDate() {
		return this.enc.getPeriod().getStart();
	}

	public Date getEndDate() {
		return this.enc.getPeriod().getEnd();
	}

}
