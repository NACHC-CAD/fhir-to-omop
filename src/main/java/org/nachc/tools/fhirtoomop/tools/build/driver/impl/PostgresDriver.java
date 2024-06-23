package org.nachc.tools.fhirtoomop.tools.build.driver.impl;

import org.nachc.tools.fhirtoomop.tools.build.driver.DriverParameters;

public class PostgresDriver implements DriverParameters {

	@Override
	public String getName() {
		return "postgresql";
	}

	@Override
	public String getFileName() {
		return "postgresql-42.3.3.jar";
	}

	@Override
	public String getUrl() {
		return "https://www.dropbox.com/scl/fi/drg1kgckeykub44uwgeay/postgresql-42.3.3.jar?rlkey=xopakpf2zokbjorl2pmtkat2a&st=e2ou9gxh&dl=1";
	}

}
