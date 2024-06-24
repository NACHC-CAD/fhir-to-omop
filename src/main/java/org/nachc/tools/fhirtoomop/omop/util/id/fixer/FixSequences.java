package org.nachc.tools.fhirtoomop.omop.util.id.fixer;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.util.db.connection.OmopDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.yaorma.database.Database;

import lombok.extern.slf4j.Slf4j;

// TODO: FIXME -- This is a temp solution created the day before a conference. It needs to be generalized.  

@Slf4j
public class FixSequences {

	private static final String[] SEQ_NAMES = {
			"concept_concept_id", 
			"condition_occurrence_condition_occurrence_id",
			"drug_exposure_drug_exposure_id",
			"measurement_measurement_id",
			"observation_observation_id",
			"person_person_id",
			"procedure_occurrence_procedure_occurrence_id",
			"visit_occurrence_visit_occurrence_id",
			"observation_period_observation_period_id"
		};
		
	private static final String[] COL_NAMES = {
			"concept_id", 
			"condition_occurrence_id",
			"drug_exposure_id",
			"measurement_id",
			"observation_id",
			"person_id",
			"procedure_occurrence_id",
			"visit_occurrence_id",
			"observation_period_id"
		};
		
	private static final String[] TABLE_NAMES = {
			"concept", 
			"condition_occurrence",
			"drug_exposure",
			"measurement",
			"observation",
			"person",
			"procedure_occurrence",
			"visit_occurrence",
			"observation_period"
		};
		
	public static void exec() {
		Connection conn = OmopDatabaseConnectionFactory.getCdmConnection();
		try {
			String dbName = getDbName();
			int cnt = -1;
			for(String seqName : SEQ_NAMES) {
				cnt++;
				String colName = COL_NAMES[cnt];
				String tableName = TABLE_NAMES[cnt];
				int maxKey = getMaxKey(tableName, colName, conn);
				int seqVal = getSeqVal(seqName, conn);
				if(seqVal <= maxKey) {
					updateSeq(seqName, maxKey, conn);
				}
			}
			Database.commit(conn);
		} finally {
			Database.close(conn);
		}
	}

	private static String getDbName() {
		String schema = AppParams.getFullySpecifiedCdmSchemaName();
		if (schema.trim().endsWith(".dbo")) {
			schema = schema.substring(0, schema.indexOf(".dbo"));
		}
		return schema;
	}

	private static int getMaxKey(String tableName, String colName, Connection conn) {
		String sqlString = "select max(" + colName + ") as val from " + tableName;
		String valString = Database.queryForFirst(sqlString, "val", conn);
		Integer rtn = Integer.parseInt(valString);
		return rtn;
	}

	private static int getSeqVal(String seqName, Connection conn) {
		String sqlString = "select current_value as val from sys.sequences where lower(name) = '" + seqName + "'";
		String valString = Database.queryForFirst(sqlString, "val", conn);
		Integer rtn = Integer.parseInt(valString);
		return rtn;
	}

	private static void updateSeq(String seqName, int maxKey, Connection conn) {
		log.info("UPDATING SEQUENCE: " + seqName);
		String sqlString = "alter sequence " + seqName + " restart with " + (maxKey + 1);
		Database.update(sqlString, conn);
		log.info("SEQ UPDATED TO: " + (maxKey + 1));		
	}

}
