package org.nachc.tools.fhirtoomop.tools.syntheacsv.impl;

import java.io.File;

import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.nachc.tools.fhirtoomop.util.win.r.RunRFileAsBat;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UploadSyntheaCsvFilesToOhdsi {

	public static void main(String[] args) {
		exec();
		log.info("Done.");
	}

	public static void exec() {
		try {
			log.info("\n\n\n-----------------------------------");
			log.info("Creating tables for Synthea CSV import.");
			// get the parameters
			String dbms = AppParams.getDbmsName();
			String server = AppParams.getServerName();
			String uid = AppParams.getSyntehsCsvUid();
			String pwd = AppParams.getSyntehsCsvPwd();
			String port = AppParams.getPort();
			String pathToDriver = getPathToDriver();
			String fullySpecifiedSchemaName = AppParams.getFullySpecifiedSchemaName();
			String cdmVersion = AppParams.getCdmVersion();
			String syntheaVersion = AppParams.getSyntheaVersion();
			String syntheaCsvNativeSchema = AppParams.getSyntheaCsvNativeSchema();
			String syntheaCsvFilesDir = AppParams.getSyntheaPatientsDirName();
			String extraSettings = AppParams.getJdbcExtraSettings();
			// get the script
			String str = FileUtil.getAsString("/syntheacsv/load-synthea-files.r");
			// update the script
			str = str.replace("@dbms", dbms);
			str = str.replace("@server", server);
			str = str.replace("@uid", uid);
			str = str.replace("@pwd", pwd);
			str = str.replace("@port", port);
			str = str.replace("@pathToDriver", pathToDriver);
			str = str.replace("@FullySpecifiedSchemaName", fullySpecifiedSchemaName);
			str = str.replace("@CdmVersion", cdmVersion);
			str = str.replace("@SyntheaVersion", syntheaVersion);
			str = str.replace("@SyntheaCsvNativeSchema", syntheaCsvNativeSchema);
			str = str.replace("@SyntheaCsvFilesDir", syntheaCsvFilesDir);
			str = str.replace("@extraSettings", extraSettings);
			// log the script
			log.info("RSCRIPT: \n" + str);
			// run the script
			RunRFileAsBat.run(str);
			log.info("Done creating tables for Synthea CSV import.");
		} catch (Exception exp) {
			throw (new RuntimeException(exp));
		}
	}

	private static String getPathToDriver() {
		String dir = AppParams.getSyntheaCsvJdbcLocation();
		File file = new File(dir);
		String rtn = FileUtil.getCanonicalPath(file);
		rtn = rtn.replace("\\", "\\\\");
		return rtn;
	}
	
}
