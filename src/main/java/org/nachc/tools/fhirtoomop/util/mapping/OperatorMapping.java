package org.nachc.tools.fhirtoomop.util.mapping;

public class OperatorMapping {

	public static int get(String val) {
		if (">".equals(val)) {
			return 4172704;
		}
		if (">=".equals(val)) {
			return 4171755;
		}
		if ("<".equals(val)) {
			return 4171756;
		}
		if ("<=".equals(val)) {
			return 4171754;
		}
		return 4172703;
	}

}
