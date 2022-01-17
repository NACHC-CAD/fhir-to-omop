package org.nachc.tools.fhirtoomop.util.fhirtoomop.id;

import java.sql.Connection;

import org.yaorma.database.Data;
import org.yaorma.database.Database;

public class FhirToOmopIdGenerator {

	// TODO: (JEG) Keeping this simple for now is something more robust is not
	// required for our use cases.

	public static Integer getId(String tableName, String idName, Connection conn) {
		String sqlString = "select max(" + idName + ") as id from " + tableName;
		Data data = Database.query(sqlString, conn);
		if (data.size() == 0) {
			return 1;
		} else {
			Integer rtn = data.get(0).getInt("id");
			if (rtn == null) {
				return 1;
			} else {
				return rtn + 1;
			}
		}
	}

}
