//
// Data Value Object (DVO) for concept_class
//

package org.nachc.tools.omop.yaorma.dvo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;

import org.yaorma.dvo.Dvo;

public class ConceptClassDvo implements Dvo {

    //
    // tableName
    //
    
    public static final String TABLE_NAME = "concept_class";
    
    //
    // schemaName
    //
    
    public static final String SCHEMA_NAME = "synthea_omop.dbo";
    
    //
    // columnNames
    //
    
    public static final String[] COLUMN_NAMES = {
        "concept_class_concept_id",
        "concept_class_id",
        "concept_class_name"
    };
    
    //
    // primaryKeyColumnNames
    //
    
    public static final String[] PRIMARY_KEY_COLUMN_NAMES = {
        "concept_class_id"
    };
    
    //
    // javaNames
    //
    
    public static final String[] JAVA_NAMES = {
        "conceptClassConceptId",
        "conceptClassId",
        "conceptClassName"
    };
    
    //
    // javaNamesProper
    //
    
    public static final String[] JAVA_NAMES_PROPER = {
        "ConceptClassConceptId",
        "ConceptClassId",
        "ConceptClassName"
    };
    
    
    //
    // member variables
    //
    
    private HashMap<String, String> descriptions = new HashMap<String, String>();
    
    private Integer conceptClassConceptId;
    
    private String conceptClassId;
    
    private String conceptClassName;
    
    private ConceptDvo conceptClassConceptDvo;
    
    //
    // trivial getters and setters
    //
    
    // conceptClassConceptId
    
    public void setConceptClassConceptId(Integer val) {
        this.conceptClassConceptId = val;
    }
    
    public Integer getConceptClassConceptId() {
        return this.conceptClassConceptId;
    }
    
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
    
    // conceptClassConceptDvo
    
    public void setConceptClassConceptDvo(ConceptDvo dvo) {
        this.conceptClassConceptDvo = dvo;
    }
    
    public ConceptDvo getConceptClassConceptDvo() {
        return this.conceptClassConceptDvo;
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
            getConceptClassId()  == null ? null: getConceptClassId() + ""
        };
        return rtn;
    }
}
