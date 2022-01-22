package org.nachc.tools.fhirtoomop.util.fhir.general;

public class FhirUtil {

	public static String getIdUnqualified(String id) {
		// TODO: (JEG) There is probably a method in the HAPI API that does this more
		// cleanly
		String str = id;
		if (str.indexOf('/') > 0) {
			str = str.substring((str.indexOf('/') + 1), str.length());
			if (str.indexOf('/') > 0) {
				str = str.substring(0, str.indexOf('/'));
				return str;
			}
		}
		return id;
	}
	
}
