//
// Data Value Object (DVO) for fhir_resource
//

package org.nachc.tools.omop.yaorma.dvo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;
import java.math.BigDecimal;

import org.yaorma.dvo.Dvo;

public class FhirResourceDvo implements Dvo {

    //
    // tableName
    //
    
    public static final String TABLE_NAME = "fhir_resource";
    
    //
    // schemaName
    //
    
    public static final String SCHEMA_NAME = "synthea_micro.dbo";
    
    //
    // columnNames
    //
    
    public static final String[] COLUMN_NAMES = {
        "patient_id",
        "resource_type",
        "resource_name"
    };
    
    //
    // primaryKeyColumnNames
    //
    
    public static final String[] PRIMARY_KEY_COLUMN_NAMES = {
    };
    
    //
    // javaNames
    //
    
    public static final String[] JAVA_NAMES = {
        "patientId",
        "resourceType",
        "resourceName"
    };
    
    //
    // javaNamesProper
    //
    
    public static final String[] JAVA_NAMES_PROPER = {
        "PatientId",
        "ResourceType",
        "ResourceName"
    };
    
    
    //
    // member variables
    //
    
    private HashMap<String, String> descriptions = new HashMap<String, String>();
    
    private String patientId;
    
    private String resourceType;
    
    private String resourceName;
    
    //
    // trivial getters and setters
    //
    
    // patientId
    
    public void setPatientId(String val) {
        this.patientId = val;
    }
    
    public String getPatientId() {
        return this.patientId;
    }
    
    // resourceType
    
    public void setResourceType(String val) {
        this.resourceType = val;
    }
    
    public String getResourceType() {
        return this.resourceType;
    }
    
    // resourceName
    
    public void setResourceName(String val) {
        this.resourceName = val;
    }
    
    public String getResourceName() {
        return this.resourceName;
    }
    
    //
    // implementation of Dvo
    //
    
    public String getTableName() {
        return TABLE_NAME;
    };
    
    public String getSchemaName() {
        return SCHEMA_NAME;
    };
    
    public String[] getColumnNames() {
        return COLUMN_NAMES;
    };
    
    public String[] getPrimaryKeyColumnNames() {
        return PRIMARY_KEY_COLUMN_NAMES;
    };
    
    public String[] getJavaNames() {
        return JAVA_NAMES;
    };
    
    public String[] getJavaNamesProper() {
        return JAVA_NAMES_PROPER;
    };
    
    public void setDescriptions(HashMap<String, String> descriptions) {
        this.descriptions = descriptions;
    }
    
    public HashMap<String, String> getDescriptions() {
        return this.descriptions;
    }
    
    public void addDescription(String javaName, String value) {
        this.descriptions.put(javaName, value);
    }
    
    public String getDescription(String javaName) {
        return this.descriptions.get(javaName);
    }
    
    public String[] getPrimaryKeyValues() {
        String[] rtn = new String[] {
        };
        return rtn;
    }
}
