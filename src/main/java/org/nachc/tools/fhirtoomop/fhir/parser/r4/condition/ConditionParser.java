package org.nachc.tools.fhirtoomop.fhir.parser.r4.condition;

import java.util.Date;

import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Condition;
// import org.hl7.fhir.r4.model.Condition.ConditionClinicalStatus;
// import org.hl7.fhir.r4.model.Condition.ConditionVerificationStatus;
import org.nachc.tools.fhirtoomop.fhir.util.id.FhirUtil;

public class ConditionParser {

	private Condition con;

	public ConditionParser(Condition con) {
		this.con = con;
	}

	public String getConditionId() {
		return FhirUtil.getIdUnqualified(this.con.getId());
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

	public Date getStartDate() {
		try {
			return this.con.getOnsetDateTimeType().getValue();
		} catch (Exception exp) {
			return null;
		}
	}

	public Date getEndDate() {
		try {
			return this.con.getAbatementDateTimeType().getValue();
		} catch (Exception exp) {
			return null;
		}
	}

	/*
	public Date getAssertedDate() {
		return this.con.getAssertedDate();
	}

	public ConditionClinicalStatus getClinicalStatus() {
		return this.con.getClinicalStatus();
	}

	public ConditionVerificationStatus verificationStatus() {
		return this.con.getVerificationStatus();
	}
	*/

}
