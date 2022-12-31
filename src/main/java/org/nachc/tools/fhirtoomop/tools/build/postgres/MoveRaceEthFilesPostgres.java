package org.nachc.tools.fhirtoomop.tools.build.postgres;

import java.io.File;
import java.nio.file.FileSystems;
import java.util.List;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MoveRaceEthFilesPostgres {

	private File sqlFile;
	
	private static final String DST_DIR = "/temp/fhir-to-omop";
	
	private static final String SQL_FILE = "/postgres/terminology/mappings/race-eth/load-mapping-data.sql";
	
	private static final String DATA_DIR = "/postgres/terminology/mappings/race-eth/data";
	
	public File getSqlFile() {
		return this.sqlFile;
	}
	
	public static void main(String[] args) {
		log.info("Moving file...");
		new MoveRaceEthFilesPostgres().exec();
		log.info("Done moving file.");
	}
	
	public void exec() {
		List<String> fileNames = FileUtil.listResources(DATA_DIR, getClass());
		String sql = getMappingDataSql();
		log.info("Got sqlString: \n" + sql);
		for (String fileName : fileNames) {
			log.info(fileName);
			try {
				String sep = FileSystems.getDefault().getSeparator();
				int start = fileName.lastIndexOf(sep);
				if(start == -1) {
					start = fileName.lastIndexOf("/");
				}
				int end = fileName.length();
				String dstFileName = fileName.substring(start, end);
				File file = new File(DST_DIR, dstFileName);
				if(fileName.endsWith("Eth.txt")) {
					sql = sql.replace("<ETH_FILE>", FileUtil.getCanonicalPath(file));
				}
				if(fileName.endsWith("Race.txt")) {
					sql = sql.replace("<RACE_FILE>", FileUtil.getCanonicalPath(file));
				}
				String txt = FileUtil.getAsString(fileName);
				log.info("Writing to: " + FileUtil.getCanonicalPath(file));
				FileUtil.write(txt, file);
			} catch (Exception exp) {
				log.info("Skipping: " + fileName);
			}
		}
		this.sqlFile = new File(DST_DIR, "load-mapping-data.sql");
		FileUtil.write(sql, this.sqlFile);
		log.info("------------------------");
		log.info("SQL FILE WRITTEN TO: " + FileUtil.getCanonicalPath(this.sqlFile));
		log.info("------------------------");
		log.info("Sql String: \n" + FileUtil.getAsString(sqlFile));
		log.info("------------------------");
	}

	private String getMappingDataSql() {
		String sqlString = FileUtil.getAsString(SQL_FILE);
		sqlString = sqlString.replace("@cdmDatabaseSchema", "public");
		return sqlString;
	}
	
}
