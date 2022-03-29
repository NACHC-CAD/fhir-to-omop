package org.nachc.tools.fhirtoomop.fhir.parser.encounter;

import java.util.Date;

import org.hl7.fhir.dstu3.model.Coding;
import org.hl7.fhir.dstu3.model.Encounter;
import org.nachc.tools.fhirtoomop.fhir.util.id.FhirUtil;

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
		return FhirUtil.getIdUnqualified(this.enc.getId());
	}

	public String getEncounterIdFullyQualified() {
		return this.enc.getId();
	}

	public String getEncounterIdUncAndQual() {
		String rtn = "";
		rtn += getEncounterId() + "|";
		rtn += getEncounterIdFullyQualified();
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
		} catch (NullPointerException npe) {
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
