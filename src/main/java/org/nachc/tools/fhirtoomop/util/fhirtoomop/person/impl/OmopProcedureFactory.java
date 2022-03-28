package org.nachc.tools.fhirtoomop.util.fhirtoomop.person.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.nachc.tools.fhirtoomop.util.fhir.parser.patienteverything.PatientEverythingParser;
import org.nachc.tools.fhirtoomop.util.fhir.parser.procedure.ProcedureParser;
import org.nachc.tools.fhirtoomop.util.fhirtoomop.person.OmopPersonEverything;
import org.nachc.tools.omop.yaorma.dvo.ProcedureOccurrenceDvo;

public class OmopProcedureFactory {

	//
	// instance variables
	//

	private OmopPersonEverything omopPersonEverything;

	private Connection conn;

	//
	// constructor
	//

	public OmopProcedureFactory(OmopPersonEverything omopPersonEverything, Connection conn) {
		this.omopPersonEverything = omopPersonEverything;
		this.conn = conn;
	}

	public List<ProcedureOccurrenceDvo> getProcedureOccurrenceList(PatientEverythingParser src) {
		List<ProcedureOccurrenceDvo> rtn = new ArrayList<ProcedureOccurrenceDvo>();
		List<ProcedureParser> list = src.getProcedureList();
		for (ProcedureParser parser : list) {
			ProcedureOccurrenceDvo dvo = this.getDvo(parser);
			rtn.add(dvo);
		}
		return rtn;
	}

	private ProcedureOccurrenceDvo getDvo(ProcedureParser parser) {
		ProcedureOccurrenceDvo rtn = new ProcedureOccurrenceDvo();
		return rtn;
	}

}
