//
// Data Value Object (DVO) for CONCEPT_CLASS
//

package org.nachc.tools.omop.yaorma.dvo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;
import java.math.BigDecimal;

import org.yaorma.dvo.Dvo;

public class ConceptClassDvo implements Dvo {

    //
    // tableName
    //
    
    public static final String TABLE_NAME = "CONCEPT_CLASS";
    
    //
    // schemaName
    //
    
    public static final String SCHEMA_NAME = "synthea_micro.dbo";
    
    //
    // columnNames
    //
    
    public static final String[] COLUMN_NAMES = {
        "concept_class_id",
        "concept_class_name",
        "concept_class_concept_id"
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
        "conceptClassId",
        "conceptClassName",
        "conceptClassConceptId"
    };
    
    //
    // javaNamesProper
    //
    
    public static final String[] JAVA_NAMES_PROPER = {
        "ConceptClassId",
        "ConceptClassName",
        "ConceptClassConceptId"
    };
    
    
    //
    // member variables
    //
    
    private HashMap<String, String> descriptions = new HashMap<String, String>();
    
    private String conceptClassId;
    
    private String conceptClassName;
    
    private Integer conceptClassConceptId;
    
    //
    // trivial getters and setters
    //
    
    // conceptClassId
    
    public void setConceptClassId(String val) {
        this.conceptClassId = val;
    }
    
    public String getConceptClassId() {
        return this.conceptClassId;
    }
    
    // conceptClassName
    
    public void setConceptClassName(String val) {
        this.conceptClassName = val;
    }
    
    public String getConceptClassName() {
        return this.conceptClassName;
    }
    
    // conceptClassConceptId
    
    public void setConceptClassConceptId(Integer val) {
        this.conceptClassConceptId = val;
    }
    
    public Integer getConceptClassConceptId() {
        return this.conceptClassConceptId;
    }
    
    //
    // implementation of Dvo
    //
    
    public String getTableName() {
        return TABLE_NAME;
    };
    
    public String getSchemaName() {
        return org.nachc.tools.fhirtoomop.util.params.AppParams.getFullySpecifiedCdmSchemaName();
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
