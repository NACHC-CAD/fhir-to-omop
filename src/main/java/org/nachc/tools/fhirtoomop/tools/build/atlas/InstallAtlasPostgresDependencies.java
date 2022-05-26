package org.nachc.tools.fhirtoomop.tools.build.atlas;

import org.nachc.tools.fhirtoomop.tools.build.atlas.impl.AtlasInstallCreateDatabase;
import org.nachc.tools.fhirtoomop.tools.build.atlas.impl.AtlasInstallCreateWebApiSchema;
import org.nachc.tools.fhirtoomop.tools.build.atlas.impl.AtlasInstallInitPostgresCreateUsers;
import org.nachc.tools.fhirtoomop.tools.build.atlas.impl.BurnAtlasToTheGround;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InstallAtlasPostgresDependencies {

	public static void main(String[] args) {
		exec();
	}
	
	public static void exec() {
		log.info("Installing Atlas Dependencies...");
		// get rid of any previous instance
		log.info("Burning Atlas to the ground...");
		BurnAtlasToTheGround.exec();
		// create the users
		log.info("Creating PostgreSql users for Atlas...");
		AtlasInstallInitPostgresCreateUsers.exec();
		// create the database
		log.info("Creating OHDSI database for Atlas...");
		AtlasInstallCreateDatabase.exec();
		// create the webapi schema
		log.info("Creating schema for Atlas...");
		AtlasInstallCreateWebApiSchema.exec();
		log.info("Done installing Atlas dependencies.");
		log.info("Done.");
	}
	
}
