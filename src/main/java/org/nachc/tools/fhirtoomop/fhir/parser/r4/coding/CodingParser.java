package org.nachc.tools.fhirtoomop.fhir.parser.r4.coding;

import org.hl7.fhir.r4.model.Coding;

public class CodingParser {

	public static String getAsPipeDelimited(Coding coding) {
		String rtn = "";
		rtn += coding.getCode() + "|";
		rtn += coding.getDisplay() + "|";
		rtn += coding.getSystem();
		return rtn;
	}
	
}
