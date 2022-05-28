package org.nachc.tools.fhirtoomop.tools.build.atlas;

import org.nachc.tools.fhirtoomop.tools.build.atlas.impl.CreateAtlasPostgresDependencies;
import org.nachc.tools.fhirtoomop.tools.build.atlas.impl.CreateAtlasSqlServerDependencies;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InstallAtlasDependencies {

	public static void main(String[] args) {
		log.info("Starting main...");
		exec();
		log.info("Done.");
	}

	public static void exec() {
		log.info("Installing Atlas Dependencies...");
		CreateAtlasPostgresDependencies.exec();
		CreateAtlasSqlServerDependencies.exec();
		log.info("Done installing Atlas dependencies...");
	}

}
