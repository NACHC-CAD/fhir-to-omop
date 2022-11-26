package org.nachc.tools.fhirtoomop.fhir.parser.r4.diagnosticreport;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.DiagnosticReport;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public class DiagnosticReportParser {

	private DiagnosticReport diagnosticReport;
	
	public DiagnosticReportParser(DiagnosticReport diagnosticReport) {
		this.diagnosticReport = diagnosticReport;
	}
	
	public String getId() {
		try {
			return this.diagnosticReport.getId();
		} catch(Exception exp) {
			return null;
		}
	}
	
	public List<String> getCodes() {
		try {
			ArrayList<String> rtn = new ArrayList<String>();
			List<Coding> codings = this.diagnosticReport.getCode().getCoding();
			for(Coding coding : codings) {
				String code = coding.getCode();
				rtn.add(code);
			}
			return rtn;
		} catch(Exception exp) {
			return new ArrayList<String>();
		}
	}

	public String getStatusDisplay() {
		try {
			String rtn = this.diagnosticReport.getStatus().getDisplay();
			return rtn;
		} catch(NullPointerException npe) {
			return null;
		}
	}
	
	public Date getStart() {
		try {
			Date rtn = this.diagnosticReport.getEffectivePeriod().getStart();
			return rtn;
		} catch(NullPointerException npe) {
			return null;
		}
	}
}
