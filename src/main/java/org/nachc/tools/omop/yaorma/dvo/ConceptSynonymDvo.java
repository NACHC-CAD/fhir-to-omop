//
// Data Value Object (DVO) for CONCEPT_SYNONYM
//

package org.nachc.tools.omop.yaorma.dvo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;
import java.math.BigDecimal;

import org.yaorma.dvo.Dvo;

public class ConceptSynonymDvo implements Dvo {

    //
    // tableName
    //
    
    public static final String TABLE_NAME = "CONCEPT_SYNONYM";
    
    //
    // schemaName
    //
    
    public static final String SCHEMA_NAME = "cdm_f2o_build";
    
    //
    // columnNames
    //
    
    public static final String[] COLUMN_NAMES = {
        "concept_id",
        "concept_synonym_name",
        "language_concept_id"
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
        "conceptId",
        "conceptSynonymName",
        "languageConceptId"
    };
    
    //
    // javaNamesProper
    //
    
    public static final String[] JAVA_NAMES_PROPER = {
        "ConceptId",
        "ConceptSynonymName",
        "LanguageConceptId"
    };
    
    
    //
    // member variables
    //
    
    private HashMap<String, String> descriptions = new HashMap<String, String>();
    
    private Integer conceptId;
    
    private String conceptSynonymName;
    
    private Integer languageConceptId;
    
    //
    // trivial getters and setters
    //
    
    // conceptId
    
    public void setConceptId(Integer val) {
        this.conceptId = val;
    }
    
    public Integer getConceptId() {
        return this.conceptId;
    }
    
    // conceptSynonymName
    
    public void setConceptSynonymName(String val) {
        this.conceptSynonymName = val;
    }
    
    public String getConceptSynonymName() {
        return this.conceptSynonymName;
    }
    
    // languageConceptId
    
    public void setLanguageConceptId(Integer val) {
        this.languageConceptId = val;
    }
    
    public Integer getLanguageConceptId() {
        return this.languageConceptId;
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
