package org.nachc.tools.fhirtoomop.util.mapping.impl.cache;

import java.sql.Connection;

import org.junit.Test;
import org.nachc.tools.fhirtoomop.util.db.connection.OmopDatabaseConnectionFactory;
import org.yaorma.util.time.Timer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StandardConceptCacheIntegrationTest {

	@Test
	public void shouldCreateCache() {
		log.info("Starting test...");
		Connection conn = OmopDatabaseConnectionFactory.getCdmConnection();
		Timer timer = new Timer();
		try {
			timer.start();
			StandardConceptCache.init(conn);
			timer.stop();
		} finally {
			OmopDatabaseConnectionFactory.close(conn);
		}
		log.info("--------------------");
		log.info("elapsed: " + timer.getElapsedString());
		log.info("--------------------");
		log.info("Done.");
	}
	
}
