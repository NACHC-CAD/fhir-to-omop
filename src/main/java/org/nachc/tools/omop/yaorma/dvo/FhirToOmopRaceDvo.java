//
// Data Value Object (DVO) for fhir_to_omop_race
//

package org.nachc.tools.omop.yaorma.dvo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;

import org.yaorma.dvo.Dvo;

public class FhirToOmopRaceDvo implements Dvo {

    //
    // tableName
    //
    
    public static final String TABLE_NAME = "fhir_to_omop_race";
    
    //
    // schemaName
    //
    
    public static final String SCHEMA_NAME = "synthea_omop";
    
    //
    // columnNames
    //
    
    public static final String[] COLUMN_NAMES = {
        "code",
        "definition",
        "depth",
        "display",
        "is_abstract",
        "omop_code",
        "omop_display",
        "parent_code",
        "parent_display"
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
        "code",
        "definition",
        "depth",
        "display",
        "isAbstract",
        "omopCode",
        "omopDisplay",
        "parentCode",
        "parentDisplay"
    };
    
    //
    // javaNamesProper
    //
    
    public static final String[] JAVA_NAMES_PROPER = {
        "Code",
        "Definition",
        "Depth",
        "Display",
        "IsAbstract",
        "OmopCode",
        "OmopDisplay",
        "ParentCode",
        "ParentDisplay"
    };
    
    
    //
    // member variables
    //
    
    private HashMap<String, String> descriptions = new HashMap<String, String>();
    
    private String code;
    
    private String definition;
    
    private Integer depth;
    
    private String display;
    
    private String isAbstract;
    
    private Integer omopCode;
    
    private String omopDisplay;
    
    private String parentCode;
    
    private String parentDisplay;
    
    //
    // trivial getters and setters
    //
    
    // code
    
    public void setCode(String val) {
        this.code = val;
    }
    
    public String getCode() {
        return this.code;
    }
    
    // definition
    
    public void setDefinition(String val) {
        this.definition = val;
    }
    
    public String getDefinition() {
        return this.definition;
    }
    
    // depth
    
    public void setDepth(Integer val) {
        this.depth = val;
    }
    
    public Integer getDepth() {
        return this.depth;
    }
    
    // display
    
    public void setDisplay(String val) {
        this.display = val;
    }
    
    public String getDisplay() {
        return this.display;
    }
    
    // isAbstract
    
    public void setIsAbstract(String val) {
        this.isAbstract = val;
    }
    
    public String getIsAbstract() {
        return this.isAbstract;
    }
    
    // omopCode
    
    public void setOmopCode(Integer val) {
        this.omopCode = val;
    }
    
    public Integer getOmopCode() {
        return this.omopCode;
    }
    
    // omopDisplay
    
    public void setOmopDisplay(String val) {
        this.omopDisplay = val;
    }
    
    public String getOmopDisplay() {
        return this.omopDisplay;
    }
    
    // parentCode
    
    public void setParentCode(String val) {
        this.parentCode = val;
    }
    
    public String getParentCode() {
        return this.parentCode;
    }
    
    // parentDisplay
    
    public void setParentDisplay(String val) {
        this.parentDisplay = val;
    }
    
    public String getParentDisplay() {
        return this.parentDisplay;
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
