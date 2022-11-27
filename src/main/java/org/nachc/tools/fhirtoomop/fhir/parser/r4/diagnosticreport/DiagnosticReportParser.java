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

	public static List<DiagnosticReportParser> getParserList(List<DiagnosticReport> list) {
		ArrayList<DiagnosticReportParser> rtn = new ArrayList<DiagnosticReportParser>();
		for(DiagnosticReport report : list) {
			DiagnosticReportParser parser = new DiagnosticReportParser(report);
			rtn.add(parser);
		}
		return rtn;
	}
	
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
	
	public List<Coding> getCodings() {
		try {
			List<Coding> codings = this.diagnosticReport.getCode().getCoding();
			return codings;
		} catch(Exception exp) {
			return new ArrayList<Coding>();
		}
	}
	
	public Coding getFirstCoding() {
		List<Coding> codings = this.getCodings();
		if(codings.size() > 0) {
			return codings.get(0);
		} else {
			return null;
		}
	}

	public String getFirstCodingSystem() {
		List<Coding> codings = this.getCodings();
		if(codings.size() > 0) {
			Coding coding = codings.get(0);
			return coding.getSystem();
		} else {
			return null;
		}
	}

	public String getFirstCodingCode() {
		List<Coding> codings = this.getCodings();
		if(codings.size() > 0) {
			Coding coding = codings.get(0);
			return coding.getCode();
		} else {
			return null;
		}
	}

	public String getFirstCodingDisplay() {
		List<Coding> codings = this.getCodings();
		if(codings.size() > 0) {
			Coding coding = codings.get(0);
			return coding.getDisplay();
		} else {
			return null;
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
