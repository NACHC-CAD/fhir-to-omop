//
// Data Value Object (DVO) for CONCEPT_ANCESTOR
//

package org.nachc.tools.omop.yaorma.dvo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;
import java.math.BigDecimal;

import org.yaorma.dvo.Dvo;

public class ConceptAncestorDvo implements Dvo {

    //
    // tableName
    //
    
    public static final String TABLE_NAME = "CONCEPT_ANCESTOR";
    
    //
    // schemaName
    //
    
    public static final String SCHEMA_NAME = "synthea_micro.dbo";
    
    //
    // columnNames
    //
    
    public static final String[] COLUMN_NAMES = {
        "ancestor_concept_id",
        "descendant_concept_id",
        "min_levels_of_separation",
        "max_levels_of_separation"
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
        "ancestorConceptId",
        "descendantConceptId",
        "minLevelsOfSeparation",
        "maxLevelsOfSeparation"
    };
    
    //
    // javaNamesProper
    //
    
    public static final String[] JAVA_NAMES_PROPER = {
        "AncestorConceptId",
        "DescendantConceptId",
        "MinLevelsOfSeparation",
        "MaxLevelsOfSeparation"
    };
    
    
    //
    // member variables
    //
    
    private HashMap<String, String> descriptions = new HashMap<String, String>();
    
    private Integer ancestorConceptId;
    
    private Integer descendantConceptId;
    
    private Integer minLevelsOfSeparation;
    
    private Integer maxLevelsOfSeparation;
    
    //
    // trivial getters and setters
    //
    
    // ancestorConceptId
    
    public void setAncestorConceptId(Integer val) {
        this.ancestorConceptId = val;
    }
    
    public Integer getAncestorConceptId() {
        return this.ancestorConceptId;
    }
    
    // descendantConceptId
    
    public void setDescendantConceptId(Integer val) {
        this.descendantConceptId = val;
    }
    
    public Integer getDescendantConceptId() {
        return this.descendantConceptId;
    }
    
    // minLevelsOfSeparation
    
    public void setMinLevelsOfSeparation(Integer val) {
        this.minLevelsOfSeparation = val;
    }
    
    public Integer getMinLevelsOfSeparation() {
        return this.minLevelsOfSeparation;
    }
    
    // maxLevelsOfSeparation
    
    public void setMaxLevelsOfSeparation(Integer val) {
        this.maxLevelsOfSeparation = val;
    }
    
    public Integer getMaxLevelsOfSeparation() {
        return this.maxLevelsOfSeparation;
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
