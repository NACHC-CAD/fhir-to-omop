package org.nachc.tools.fhirtoomop.util.db.connection.type;

public enum ConnectionDbmsType {
	MSSQL,
	POSTGRESQL;
	
	public static ConnectionDbmsType get(String typeString) {
		if("mssql".equals(typeString)) {
			return MSSQL;
		} else if ("postgres".equals(typeString)) {
			return POSTGRESQL;
		} else {
			throw new RuntimeException("Could not get connection for DMBS type: " + typeString);
		}
	}
}
