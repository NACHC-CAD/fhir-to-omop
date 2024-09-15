package org.nachc.tools.fhirtoomop.tools.build.postgres.build;

import java.io.File;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.List;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FHIR01a_MoveRaceEthFiles {

	private File sqlFile;

//	private static final String SQL_FILE_NAME = "/sqlserver/terminology/mappings/race-eth/load-mapping-data.sql";

	private static final String SQL_FILE_NAME = "/postgres/build/FHIR02_LoadFhirRaceEthMappings.sql";

	private static final String ETH_FILE_NAME = "/sqlserver/terminology/mappings/race-eth/data/RaceAndEthnicityCDC-OMOP-MAPPING-Eth.txt";

	private static final String RACE_FILE_NAME = "/sqlserver/terminology/mappings/race-eth/data/RaceAndEthnicityCDC-OMOP-MAPPING-Race.txt";
	
	public File getSqlFile() {
		return this.sqlFile;
	}

	public void exec(String destDir) {
		moveFile(ETH_FILE_NAME, destDir);
		moveFile(RACE_FILE_NAME, destDir);
		this.sqlFile = moveFile(SQL_FILE_NAME, destDir);
	}

	private File moveFile(String filePath, String destDir) {
		InputStream in = FileUtil.getInputStream(filePath);
		String fileName = filePath.substring(filePath.lastIndexOf("/") + 1, filePath.length());
		File file = new File(destDir, fileName);
		log.info("Writing file to: " + FileUtil.getCanonicalPath(file));
		FileUtil.write(in, file);
		return file;
	}

}
