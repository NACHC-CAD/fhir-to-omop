package org.nachc.tools.fhirtoomop.util.fhirtoomop.person.impl;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.util.List;

import org.junit.Test;
import org.nachc.tools.fhirtoomop.unittesttools.TestParams;
import org.nachc.tools.fhirtoomop.util.db.mysql.MySqlDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.fhirtoomop.person.OmopPersonEverythingFactory;
import org.nachc.tools.omop.yaorma.dvo.ConditionOccurrenceDvo;
import org.yaorma.database.Database;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OmopConditionFactoryIntegrationTest {

	@Test
	public void shouldGetDvo() {
		log.info("Starting test...");
		Connection conn = MySqlDatabaseConnectionFactory.getSyntheaConnection();
		try {
			String json = TestParams.getPersonEverythingJson();
			OmopPersonEverythingFactory person = new OmopPersonEverythingFactory(json, conn);
			List<ConditionOccurrenceDvo> conditionList = person.getConditionOccurrenceList();
			log.info("Got " + conditionList.size() + " conditions.");
			assertTrue(conditionList.size() == 5);
			ConditionOccurrenceDvo dvo = conditionList.get(0);
			log.info("id:          " + dvo.getConditionOccurrenceId());
			log.info("startDate:   " + dvo.getConditionStartDate());
			log.info("endDate:     " + dvo.getConditionEndDate());
			log.info("sourceValue: " + dvo.getConditionSourceValue());
			log.info("conceptId:   " + dvo.getConditionConceptId());
		} finally {
			MySqlDatabaseConnectionFactory.close(conn);
		}
		log.info("Done.");
	}

}
