package org.nachc.tools.fhirtoomop.fhir.parser.r4.procedure;

import java.util.Date;

import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Procedure;
import org.nachc.tools.fhirtoomop.fhir.patient.r4.FhirPatient;
import org.nachc.tools.fhirtoomop.fhir.util.id.FhirUtil;
import org.yaorma.util.time.TimeUtil;

public class ProcedureParser {

	private Procedure proc;

	private FhirPatient fhirPatient;

	public ProcedureParser(Procedure proc, FhirPatient fhirPatient) {
		this.proc = proc;
		this.fhirPatient = fhirPatient;
	}

	//
	// encounter
	//

	public String getEncounterId() {
		try {
			String ref = this.proc.getContext().getReference();
			if (ref.indexOf('/') < 0) {
				return ref;
			} else {
				return ref.split("/")[1];
			}
		} catch (Exception exp) {
			return null;
		}
	}

	//
	// patient
	//

	public String getPatientId() {
		try {
			return this.fhirPatient.getPatient().getId();
		} catch (Exception exp) {
			return null;
		}
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
	
	public String getStartDateAsString() {
		return TimeUtil.format(getStartDate(), "yyyy-MM-dd");
	}
	
	public Date getEndDate() {
		try {
			Date rtn = this.proc.getPerformedPeriod().getEnd();
			if(rtn == null) {
				rtn = getStartDate();
			}
			return rtn;
		} catch(Exception exp) {
			return null;
		}
	}
	
	public String getEndDateAsString() {
		return TimeUtil.format(getEndDate(), "yyyy-MM-dd");
	}
	
}
