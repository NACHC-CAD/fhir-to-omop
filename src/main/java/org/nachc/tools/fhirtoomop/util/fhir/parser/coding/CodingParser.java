package org.nachc.tools.fhirtoomop.util.fhir.parser.coding;

import org.hl7.fhir.dstu3.model.Coding;

public class CodingParser {

	public static String getAsPipeDelimited(Coding coding) {
		String rtn = "";
		rtn += coding.getCode() + "|";
		rtn += coding.getDisplay() + "|";
		rtn += coding.getSystem();
		return rtn;
	}
	
}
