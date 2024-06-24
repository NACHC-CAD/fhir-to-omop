//
// Data Value Object (DVO) for fhir_to_omop_race
//

package org.nachc.tools.omop.yaorma.dvo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;
import java.math.BigDecimal;

import org.yaorma.dvo.Dvo;

public class FhirToOmopRaceDvo implements Dvo {

    //
    // tableName
    //
    
    public static final String TABLE_NAME = "fhir_to_omop_race";
    
    //
    // schemaName
    //
    
    public static final String SCHEMA_NAME = "synthea_micro.dbo";
    
    //
    // columnNames
    //
    
    public static final String[] COLUMN_NAMES = {
        "omop_code",
        "omop_display",
        "depth",
        "parent_code",
        "parent_display",
        "code",
        "display",
        "is_abstract",
        "definition"
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
        "omopCode",
        "omopDisplay",
        "depth",
        "parentCode",
        "parentDisplay",
        "code",
        "display",
        "isAbstract",
        "definition"
    };
    
    //
    // javaNamesProper
    //
    
    public static final String[] JAVA_NAMES_PROPER = {
        "OmopCode",
        "OmopDisplay",
        "Depth",
        "ParentCode",
        "ParentDisplay",
        "Code",
        "Display",
        "IsAbstract",
        "Definition"
    };
    
    
    //
    // member variables
    //
    
    private HashMap<String, String> descriptions = new HashMap<String, String>();
    
    private Integer omopCode;
    
    private String omopDisplay;
    
    private Integer depth;
    
    private String parentCode;
    
    private String parentDisplay;
    
    private String code;
    
    private String display;
    
    private String isAbstract;
    
    private String definition;
    
    //
    // trivial getters and setters
    //
    
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
    
    // depth
    
    public void setDepth(Integer val) {
        this.depth = val;
    }
    
    public Integer getDepth() {
        return this.depth;
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
    
    // code
    
    public void setCode(String val) {
        this.code = val;
    }
    
    public String getCode() {
        return this.code;
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
    
    // definition
    
    public void setDefinition(String val) {
        this.definition = val;
    }
    
    public String getDefinition() {
        return this.definition;
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
