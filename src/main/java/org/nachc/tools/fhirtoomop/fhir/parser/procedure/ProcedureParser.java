package org.nachc.tools.fhirtoomop.fhir.parser.procedure;

import java.util.Date;

import org.hl7.fhir.dstu3.model.Coding;
import org.hl7.fhir.dstu3.model.Procedure;
import org.nachc.tools.fhirtoomop.fhir.patient.FhirPatient;
import org.nachc.tools.fhirtoomop.fhir.util.id.FhirUtil;

public class ProcedureParser {

	private Procedure proc;

	private FhirPatient fhirPatient;

	public ProcedureParser(Procedure proc, FhirPatient fhirPatient) {
		this.proc = proc;
		this.fhirPatient = fhirPatient;
	}

	public String getProcedureId() {
		return FhirUtil.getIdUnqualified(this.proc.getId());
	}

	public Coding getProcedure() {
		try {
			return this.proc.getCode().getCoding().get(0);
		} catch (Exception exp) {
			return null;
		}
	}

	public String getProcedureSystem() {
		try {
			return getProcedure().getSystem();
		} catch(Exception exp) {
			return null;
		}
	}
	
	public String getProcedureCode() {
		try {
			return getProcedure().getCode();
		} catch(Exception exp) {
			return null;
		}
	}
	
	public String getProcedureDisplay() {
		try {
			return getProcedure().getDisplay();
		} catch(Exception exp) {
			return null;
		}
	}
	
	public Date getStartDate() {
		try {
			return this.proc.getPerformedPeriod().getStart();
		} catch(Exception exp) {
			return null;
		}
	}
	
	public Date getEndDate() {
		try {
			return this.proc.getPerformedPeriod().getEnd();
		} catch(Exception exp) {
			return null;
		}
	}
	
}
