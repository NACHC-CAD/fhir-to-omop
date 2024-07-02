package org.nachc.tools.fhirtoomop.util.params;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

import org.nachc.tools.fhirtoomop.util.db.connection.type.ConnectionDbmsType;
import org.yaorma.util.time.TimeUtil;

import com.nach.core.util.file.FileUtil;
import com.nach.core.util.props.PropertiesUtil;
import com.nach.core.util.string.StringUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AppParams extends AppParamsInitialization {

	// ---
	// GLOBAL PARAMETERS
	// ---

	public static String getDbmsName() {
		return get("DbmsName");
	}
	
	public static String getCdmVersion() {
		return get("CdmVersion");
	}

	public static String getExportDir() {
		return get("ExportDir");
	}

	// ---
	// DATABASE CONNECTION INFORMATION FOR THE CDM INSTANCE
	// ---

	public static String getBootstrapUrl() {
		return get("BootstrapUrl");
	}

	public static String getUrl() {
		return get("Url");
	}

	public static String getUid() {
		return get("Uid");
	}

	public static String getPwd() {
		return get("Pwd");
	}

	public static String getServerName() {
		return get("ServerName");
	}
	
	public static String getFullySpecifiedCdmSchemaName() {
		return get("FullySpecifiedCdmSchemaName");
	}

	// ---
	// DATABASE DRIVER
	// ---
	
	public static String getDatabaseDriverName() {
		return get("DatabaseDriverName");
	}

	public static String getDatabaseDriverPath() {
		return get("DatabaseDrvierPath");
	}

	// ---
	// POSTGRES CONNECTION INFORMATION (FOR WEBAPI INFORMATION)
	// ---
	
	public static String getPostgresBootstrapUrl() {
		return get("PostgresBootstrapUrl");
	}

	public static String getPostgresBootstrapUid() {
		return get("PostgresBootstrapUid");
	}

	public static String getPostgresBootstrapPwd() {
		return get("PostgresBootstrapPwd");
	}
	
	public static String getPostgresBootstrapServer() {
		return get("PostgresBootstrapServer");
	}
	
	public static String getPostgresBootstrapPort() {
		return get("PostgresBootstrapPort");
	}
	
	public static String getPostgresBootstrapDatabaseName() {
		return get("PostgresBootstrapDatabaseName");
	}
	
	public static String getPostgresBootstrapSchemaName() {
		return get("PostgresBootstrapSchemaName");
	}
	
	public static String getPostgresBootstrapPathToDriver() {
		return get("PostgresBootstrapPathToDriver");
	}

	// ---
	// VALUES USED TO CREATE CDM_SOURCE RECORD
	// ---
	
	public static String getCdmSourceName() {
		String rtn = get("CdmSourceName");
		return rtn;
	}
	
	public static String getCdmSourceAbbreviation() {
		String rtn = get("CdmSourcebbreviation");
		return rtn;
	}
	
	public static String getCdmHolder() {
		String rtn = get("CdmHolder");
		return rtn;
	}
	
	public static String getCdmSourceDescription() {
		String rtn = get("CdmSourceDescription");
		return rtn;
	}
	
	public static String getSourceDocumentationReference() {
		String rtn = get("CdmSourceDocumentationReference");
		return rtn;
	}
	
	public static String getCdmEtlReference() {
		String rtn = get("CdmEtlReference");
		return rtn;
	}
	
	public static String getSourceReleaseDate() {
		String rtn = get("SourceReleaseDate");
		return rtn;
	}
	
	public static String getCdmReleaseDate() {
		String rtn = get("CdmReleaseDate");
		return rtn;
	}
	
	public static String getVocabularyVersion() {
		String rtn = get("VocabularyVersion");
		return rtn;
	}
	
	public static String getCdmVersionConceptId() {
		String rtn = get("CdmVersionConceptId");
		return rtn;
	}

	// ---
	// TERMINOLOGY
	// ---
	
	public static String getTerminologyRootDir() {
		return get("TerminologyRootDir");
	}

	public static String getTerminologyDownloadIfNotFound() {
		return get("TerminologyDownloadIfNotFound");
	}

	public static String getTerminologyDownloadUrl() {
		return get("TerminologyDownloadUrl");
	}

	// ---
	// WEBAPI
	// ---
	
	public static String getAtlasDataSourceName() {
		return get("AtlasDataSourceName");
	}
	
	public static String getAtlasDataSourceKey() {
		return get("AtlasDataSourceKey");
	}

	// results
	public static String getAchillesResultsDatabase() {
		return get("AchillesResultsDatabase");
	}
	
	public static String getAchillesResultsSchema() {
		return get("AchillesResultsSchema");
	}
	
	public static String getFullySpecifiedAchillesResultsSchemaName() {
		String db = getAchillesResultsDatabase();
		String schema = getAchillesResultsSchema();
		String rtn = db + "." + schema;
		return rtn;
	}
	
	// temp
	public static String getAchillesTempDatabase() {
		return get("AchillesTempDatabase");
	}
	
	public static String getAchillesTempSchema() {
		return get("AchillesTempSchema");
	}
	
	public static String getFullySpecifiedAchillesTempSchemaName() {
		String db = getAchillesTempDatabase();
		String schema = getAchillesTempSchema();
		String rtn = db + "." + schema;
		return rtn;
	}

	// vocab
	public static String getFullySpecifiedAchillesVocabDatabase() {
		return get("AchillesVocabDatabase");
	}
	
	public static String getAchillesVocabSchema() {
		return get("AchillesVocabSchema");
	}
	
	public static String getAtlasCdmUrl() {
		return get("AtlasCdmUrl");
	}

	public static String getAchillesVocabSchemaName() {
		String db = getFullySpecifiedAchillesVocabDatabase();
		String schema = getAchillesVocabSchema();
		String rtn = db + "." + schema;
		return rtn;
	}

	// ---
	// CDM CSV
	// ---
	
	public static String getCdmCsvZipFileLocation() {
		return get("CdmCsvZipFileLocation");
	}
	
	public static String getCdmCsvZipFileName() {
		return get("CdmCsvZipFileName");
	}
	
	public static String getCdmCsvDownloadUrl() {
		return get("CdmCsvDownloadUrl");
	}

	public static boolean getCdmCsvDownloadIfNotFound() {
		String str = get("CdmCsvDownloadIfNotFound");
		if("true".equalsIgnoreCase(str)) {
			return true;
		} else {
			return false;
		}
	}
	
	// ---
	// DQD
	// ---
	
	public static String getDqdResultsSchemaName() {
		String rtn = get("DqdResultsSchemaName");
		return rtn;
	}

	// ------------------------------------------------------------------------
	// Everything beyond this is legacy and needs to be refactored
	// ------------------------------------------------------------------------

	// ---
	// DATABASE AND SCHEMA NAMES
	// ---
	
	public static String getDatabaseName() {
		String rtn = getFullySpecifiedCdmSchemaName();
		return getDatabasePart(rtn);
	}

	public static String getSchemaName() {
		String rtn = getFullySpecifiedCdmSchemaName();
		return getSchemaPart(rtn);
	}

	public static String getDatabasePart(String schemaName) {
		String rtn = schemaName;
		if (rtn.indexOf(".") > 0) {
			rtn = rtn.trim().substring(0, rtn.trim().indexOf("."));
		}
		return rtn;
	}

	public static String getSchemaPart(String schemaName) {
		String rtn = schemaName;
		if (rtn.indexOf(".") > 0) {
			rtn = rtn.trim().substring(rtn.indexOf(".") + 1, rtn.length());
		}
		return rtn;
	}

	// ---
	//
	// upload files stuff
	//
	// ---

	// directory where fhir patients live
	public static String getFhirPatientsDirName() {
		String fileName = get("fhirPatientsDir");
		return fileName;
	}

	// directory where fhir patients live
	public static String getSyntheaPatientsDirName() {
		String fileName = get("syntheaPatientsDir");
		return fileName;
	}

	// max number of connections to use for upload
	public static int getMaxNumberOfConnectionsForUpload() {
		String str = get("maxNumberOfConnectionsForUpload");
		Integer rtn = StringUtil.parseInt(str);
		return rtn;
	}

	// max number of threads to use for upload
	public static int getMaxNumberOfThreadsForUpload() {
		String str = get("maxNumberOfThreadsForUpload");
		Integer rtn = StringUtil.parseInt(str);
		return rtn;
	}

	// cache size
	public static int getConceptCacheSize() {
		String str = get("conceptCacheSize");
		Integer rtn = StringUtil.parseInt(str);
		return rtn;
	}

	public static int getMaxNumberOfWorkersForUpload() {
		String str = AppParams.get("maxNumberOfWorkersForUpload");
		Integer rtn = Integer.parseInt(str);
		return rtn;
	}

	// ---
	//
	// Download files stuff
	//
	// ---

	public static File getFhirPatientIdDir() {
		String dirName = AppParams.getDownloadPatientIdDir();
		File file = new File(dirName);
		return file;
	}

	public static String getFhirPatientServerUrl() {
		return AppParams.get("fhirPatientServerUrl");
	}

	public static int getDownloadNumberOfPatientsPerThread() {
		String str = get("downloadNumberOfPatientsPerThread");
		Integer rtn = StringUtil.parseInt(str);
		return rtn;
	}

	public static int getDownloadMaxNumberOfActiveWorkers() {
		String str = get("downloadMaxNumberOfActiveWorkers");
		Integer rtn = StringUtil.parseInt(str);
		return rtn;
	}

	public static int getDownloadNumberOfPatientsPerWorker() {
		String str = get("downloadNumberOfPatientsPerWorker");
		Integer rtn = StringUtil.parseInt(str);
		return rtn;
	}

	public static String getDownloadOutputDir() {
		return get("downloadOutputDir");
	}

	public static int getDownloadRetryCount() {
		String str = get("downloadRetryCount");
		Integer rtn = StringUtil.parseInt(str);
		return rtn;
	}

	public static String getDownloadPatientIdDir() {
		return get("downloadPatientIdDir");
	}

	public static String getDownloadInputDir() {
		return get("downloadInputDir");
	}

	public static Date getDateNotFound() {
		return TimeUtil.getDateForYyyy_Mm_Dd("1700-01-01");
	}

	public static ConnectionDbmsType getDbmsType() {
		String typeString = getDbmsName();
		ConnectionDbmsType rtn = ConnectionDbmsType.get(typeString);
		return rtn;
	}

	// ---
	//
	// webapi parameters
	//
	// ---
	
	public static String getWebApiConnectionString() {
		return get("WebApiConnectionString");
	}

	public static String getWebApiSourceName() {
		return get("WebApiSourceName");
	}

	public static String getWebApiSourceKey() {
		return get("WebApiSourceKey");
	}

	public static String getWebApiSourceJdbcUrl() {
		return get("WebApiSourceJdbcUrl");
	}

	public static String getWebApiSourceDialect() {
		return get("WebApiSourceDialect");
	}

	public static String getSyntheaCsvJdbcLocation() {
		return get("SyntheaCsvJdbcLocation");
	}
	
	public static String getSyntheaCsvJdbcDownloadUrl() {
		return get("SyntheaCsvJdbcDownloadUrl");
	}

	public static boolean getSyntheaCsvDownloadJdbcDriverIfNotFound() {
		String str = get("SyntheaCsvDownloadJdbcDriverIfNotFound");
		if("true".equalsIgnoreCase(str)) {
			return true;
		} else {
			return false;
		}
	}

	public static String getSyntheaCsvJdbcDriverName() {
		return get("SyntheaCsvJdbcDriverName");
	}

	public static String getSyntheaCsvNativeSchema() {
		return get("SyntheaCsvNativeSchema");
	}
	
	public static String getSyntheaCsvNativeDatabase() {
		return get("SyntheaCsvNativeDatabase");
	}
	
	public static String getPort() {
		return get("Port");
	}

	public static String getSyntheaVersion() {
		return get("SyntheaVersion");
	}
	
	public static String getSyntehsCsvUid() {
		return get("SyntehsCsvUid");
	}
	
	public static String getSyntehsCsvPwd() {
		return get("SyntheaCsvPwd");
	}
	
	public static String getJdbcExtraSettings() {
		return get("JdbcExtraSettings");
	}
	
	public static String getSyntheaCsvFilesDir() {
		return get("SyntheaCsvFilesDir");
	}
	
	public static String getSyntheaCsvTestFilesUrl() {
		return get("SyntheaCsvTestFilesUrl");
	}
	
	public static boolean getSyntheaCsvDownloadTestFilesIfNotFound() {
		String str = get("SyntheaCsvDownloadTestFilesIfNotFound");
		if("true".equals(str)) {
			return true;
		} else {
			return false;
		}
	}
	
	
}
