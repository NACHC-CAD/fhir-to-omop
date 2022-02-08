package org.nachc.tools.fhirtoomop.util.db.mysql.util;

import java.sql.Connection;
import java.util.ArrayList;

import org.nachc.tools.fhirtoomop.util.db.mysql.util.myisam.RunnableForConvertTablesToMyIsam;
import org.yaorma.database.Data;
import org.yaorma.database.Row;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConvertAllTablesToMyIsam {

	private ArrayList<Thread> threads = new ArrayList<Thread>();

	public void exec(String schemaName, Connection conn) {
		Data data = GetTablesForSchema.exec(schemaName, conn);
		for (Row row : data) {
			String tableName = row.get("tableName");
			RunnableForConvertTablesToMyIsam run = new RunnableForConvertTablesToMyIsam(schemaName, tableName);
			Thread thread = new Thread(run);
			this.threads.add(thread);
		}
		for(Thread thread : this.threads) {
			thread.start();
		}
		for(Thread thread : this.threads) {
			try {
				thread.join();
			} catch(Exception exp) {
				log.warn("COULD NOT JOIN THREAD");
			}
		}
	}

}
