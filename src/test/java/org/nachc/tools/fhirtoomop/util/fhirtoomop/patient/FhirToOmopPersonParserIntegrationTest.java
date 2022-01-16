package org.nachc.tools.fhirtoomop.util.fhirtoomop.patient;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;

import org.junit.Test;
import org.nachc.tools.fhirtoomop.util.db.mysql.MySqlDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.fhirtoomop.person.FhirToOmopPersonParser;
import org.nachc.tools.fhirtoomop.util.omop.datafactory.OmopConceptFactory;
import org.nachc.tools.omop.yaorma.dvo.ConceptDvo;
import org.nachc.tools.omop.yaorma.dvo.PersonDvo;
import org.yaorma.database.Database;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FhirToOmopPersonParserIntegrationTest {

	@Test
	public void shouldGetOmopPerson() {
		log.info("Starting test...");
		// get a connection
		log.info("Getting connection...");
		Connection conn = MySqlDatabaseConnectionFactory.getSyntheaConnection();
		log.info("Got connection");
		try {
			// get the test data and create the parser
			String json = FileUtil.getAsString("/fhir/patient/everything/everything-patient.json");
			PersonDvo person = FhirToOmopPersonParser.getPerson(json, conn);
			// person id
			int id = person.getPersonId();
			log.info("PersonId: " + id);
			assertTrue(id > 0);
			// person source id
			String personSourceId = person.getPersonSourceValue();
			log.info("SourceId: " + personSourceId);
			assertTrue(personSourceId.equals("6f7acde5-db81-4361-82cf-886893a3280c"));
			// race
			Integer raceId = person.getRaceConceptId();
			log.info("Got race_id: " + raceId);
			ConceptDvo raceDvo = OmopConceptFactory.getConcept(raceId, conn);
			String raceFromOmop = raceDvo.getConceptName();
			log.info("raceFromOmop: " + raceFromOmop);
			assertTrue(raceFromOmop.equals("White"));
			String raceFromSource = person.getRaceSourceValue();
			log.info("raceFromSource: " + raceFromSource);
			assertTrue(raceFromSource.equals("2106-3|White|urn:oid:2.16.840.1.113883.6.238"));
			// ethnicity
			Integer ethId = person.getEthnicityConceptId();
			log.info("Got eth_id: " + ethId);
			ConceptDvo ethDvo = OmopConceptFactory.getConcept(ethId, conn);
			log.info("ethFromOmop: " + ethDvo.getConceptName());
			assertTrue("Not Hispanic or Latino".equals(ethDvo.getConceptName()));
			String ethFromSource = person.getEthnicitySourceValue();
			log.info("ethFromSource: " + ethFromSource);
			assertTrue(ethFromSource.equals("2186-5|Not Hispanic or Latino|urn:oid:2.16.840.1.113883.6.238"));
		} finally {
			Database.close(conn);
		}
		// done
		log.info("Done.");
	}
	
}
