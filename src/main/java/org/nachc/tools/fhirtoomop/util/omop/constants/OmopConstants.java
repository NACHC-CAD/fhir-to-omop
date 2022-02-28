package org.nachc.tools.fhirtoomop.util.omop.constants;

import java.sql.Connection;
import java.util.HashMap;

import org.yaorma.database.Database;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OmopConstants {

	private static HashMap<String, String> constants = new HashMap<String, String>();

	public static String getMesurementIdFieldCode(Connection conn) {
		String key = "measurementIdField";
		String sqlString = "select concept_id from concept where lower(concept_name) = 'measurement.measurement_id' and domain_id = 'Metadata' and vocabulary_id = 'CDM' and concept_code = 'CDM776'";
		return getConstant(sqlString, "conceptId", key, conn);
	}

	public static String getObservationIdFieldCode(Connection conn) {
		String key = "measurementIdField";
		String sqlString = "select concept_id from concept where lower(concept_name) = 'observation.observation_id' and domain_id = 'Metadata' and vocabulary_id = 'CDM' and concept_code = 'CDM809'";
		return getConstant(sqlString, "conceptId", key, conn);
	}

	private static String getConstant(String sqlString, String colName, String key, Connection conn) {
		String val = constants.get(key);
		if (val != null) {
			return val;
		}
		log.info("GETTING CONSTANT FROM DATABASE");
		val = Database.queryForFirst(sqlString, colName, conn);
		if (val == null) {
			log.info(key);
			log.info(colName);
			log.info(sqlString);
			throw new RuntimeException("Could not create constant");
		} else {
			constants.put(key, val);
			return val;
		}
	}

}
