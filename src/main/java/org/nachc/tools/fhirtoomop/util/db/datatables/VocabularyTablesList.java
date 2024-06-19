package org.nachc.tools.fhirtoomop.util.db.datatables;

import java.util.Arrays;
import java.util.List;

public class VocabularyTablesList {

	private static final String[] TABLE_NAMES = {
			"CONCEPT",
			"CONCEPT_ANCESTOR",
			"CONCEPT_CLASS",
			"CONCEPT_RELATIONSHIP",
			"CONCEPT_SYNONYM",
			"DOMAIN",
			"DRUG_STRENGTH",
			"RELATIONSHIP",
			"VOCABULARY"
	};

	public static String[] getTables() {
		return TABLE_NAMES;
	}

	public static List<String> getTablesList() {
		return Arrays.asList(TABLE_NAMES);
	}

}
