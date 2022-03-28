package org.nachc.tools.fhirtoomop.util.fhir.parser.procedure;

import org.hl7.fhir.dstu3.model.Procedure;
import org.nachc.tools.fhirtoomop.util.fhir.general.FhirUtil;

public class ProcedureParser {

	private Procedure proc;
	
	public ProcedureParser(Procedure proc) {
		this.proc = proc;
	}

	public String getProcedureId() {
		return FhirUtil.getIdUnqualified(this.proc.getId());
	}
	
}
