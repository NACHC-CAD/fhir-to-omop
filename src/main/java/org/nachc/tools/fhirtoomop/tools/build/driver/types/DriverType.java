package org.nachc.tools.fhirtoomop.tools.build.driver.types;

import org.nachc.tools.fhirtoomop.tools.build.driver.DriverParameters;
import org.nachc.tools.fhirtoomop.tools.build.driver.impl.PostgresDriver;
import org.nachc.tools.fhirtoomop.tools.build.driver.impl.SqlServerDriver_12_6;

import lombok.Getter;

@Getter
public enum DriverType {
	POSTGRES(new PostgresDriver()), 
	SQL_SERVER_12_6(new SqlServerDriver_12_6());

	private DriverParameters params;

	private DriverType(DriverParameters params) {
		this.params = params;
	}

	public static DriverType getDriverType(String name) {
        for (DriverType type : DriverType.values()) {
            if (type.params.getName().equalsIgnoreCase(name)) {
                return type;
            }
        }
        return null;
	}
}
