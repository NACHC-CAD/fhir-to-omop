package org.nachc.tools.fhirtoomop.tools.build.postgres;

import java.io.File;
import java.io.InputStream;
import java.sql.Connection;

import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.yaorma.database.Database;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoadMappingTablesPostgres {

	public static void exec(File sqlFile, Connection conn) {
		log.info("-----------------------");
		log.info("LOAD RACE/ETH SQL FILE: " + FileUtil.getCanonicalPath(sqlFile));
		log.info("-----------------------");
		InputStream IS = FileUtil.getInputStream(sqlFile);
		log.info("Running script...");
		Database.executeSqlScript(IS, conn);
		log.info("Done running script.");
		log.info("Done creating database tables.");
	}

}
