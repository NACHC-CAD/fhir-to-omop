package org.nachc.tools.fhirtoomop.util.db.connection.type;

public enum ConnectionDbmsType {
	MSSQL("sql server"),
	POSTGRESQL("postgres");
	
	private String name;
	
	private ConnectionDbmsType(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public static ConnectionDbmsType get(String typeString) {
		if("sql server".equals(typeString)) {
			return MSSQL;
		} else if ("postgres".equals(typeString)) {
			return POSTGRESQL;
		} else {
			throw new RuntimeException("Could not get connection for DMBS type: " + typeString);
		}
	}
}
