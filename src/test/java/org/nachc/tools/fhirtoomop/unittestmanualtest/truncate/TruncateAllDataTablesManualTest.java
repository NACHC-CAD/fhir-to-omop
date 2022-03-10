package org.nachc.tools.fhirtoomop.unittestmanualtest.truncate;

import org.nachc.tools.fhirtoomop.util.db.truncatedatatables.TruncateAllDataTables;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TruncateAllDataTablesManualTest {

	public static void main(String[] args) {
		TruncateAllDataTables.exec();
		log.info("Done.");
	}

}
