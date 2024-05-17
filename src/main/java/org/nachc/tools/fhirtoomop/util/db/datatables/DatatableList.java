package org.nachc.tools.fhirtoomop.util.db.datatables;

import java.util.Arrays;
import java.util.List;

public class DatatableList {

	private static final String[] TABLE_NAMES = {
			"drug_exposure",
			"procedure_occurrence",
			"device_exposure",
			"measurement",
			"observation",
			"note",
			"visit_detail",
			"observation_period",
			"observation",
			"condition_occurrence",
			"visit_occurrence",
			"provider",
			"person",
			"fhir_resource",
			"cdm_source"
	};

	public static String[] getDatatableArray() {
		return TABLE_NAMES;
	}

	public static List<String> getDatatableList() {
		return Arrays.asList(TABLE_NAMES);
	}

}
