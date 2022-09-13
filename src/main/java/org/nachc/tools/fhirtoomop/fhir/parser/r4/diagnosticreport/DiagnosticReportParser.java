package org.nachc.tools.fhirtoomop.fhir.parser.r4.diagnosticreport;

import java.util.List;

import org.hl7.fhir.r4.model.DiagnosticReport;
import org.hl7.fhir.r4.model.Reference;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DiagnosticReportParser {

	private DiagnosticReport diagnosticReport;
	
	public String getId() {
		try {
			return this.diagnosticReport.getId();
		} catch(Exception exp) {
			return null;
		}
	}
	
	public void getResultList() {
		List<Reference> resultList = diagnosticReport.getResult();
	}

}
