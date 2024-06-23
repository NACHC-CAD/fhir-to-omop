package org.nachc.tools.fhirtoomop.tools.build.driver.impl;

import org.nachc.tools.fhirtoomop.tools.build.driver.DriverParameters;

public class SqlServerDriver_12_6 implements DriverParameters {

	@Override
	public String getName() {
		return "sqlServer_12_6";
	}

	@Override
	public String getFileName() {
		return "mssql-jdbc-12.6.2.jre11.jar";
	}

	@Override
	public String getUrl() {
		return "https://www.dropbox.com/scl/fi/qyd8o6taim9q7ui52ftae/mssql-jdbc-12.6.2.jre11.jar?rlkey=kpo4w8n6uf21bj8ojfbc4k71k&st=2atiqqv7&dl=1";
	}

}
