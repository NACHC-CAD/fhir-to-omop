package org.nachc.tools.fhirtoomop.util.db.mysql.util;

import java.sql.Connection;

import org.yaorma.database.Data;
import org.yaorma.database.Database;
import org.yaorma.database.Row;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DeleteAllForeignKeysForSchema {

	public static void exec(String schemaName, Connection conn) {
		Data foreignKeysList = GetForeignKeyConstraintsForSchema.exec(schemaName, conn);
		for(Row row : foreignKeysList) {
			String sqlString = "";
			sqlString += "alter table " + row.get("tableSchema") + "." + row.get("tableName") + " ";
			sqlString += "drop foreign key " + row.get("constraintName");
			log.info("! ! ! DROPPING FOREIGN KEY ! ! ! ");
			log.info(sqlString);
			Database.update(sqlString, conn);
		}
	}

}
