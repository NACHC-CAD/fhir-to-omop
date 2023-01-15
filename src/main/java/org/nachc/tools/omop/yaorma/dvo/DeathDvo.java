//
// Data Value Object (DVO) for DEATH
//

package org.nachc.tools.omop.yaorma.dvo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;
import java.math.BigDecimal;

import org.yaorma.dvo.Dvo;

public class DeathDvo implements Dvo {

    //
    // tableName
    //
    
    public static final String TABLE_NAME = "DEATH";
    
    //
    // schemaName
    //
    
    public static final String SCHEMA_NAME = "cdm_f2o_build";
    
    //
    // columnNames
    //
    
    public static final String[] COLUMN_NAMES = {
        "person_id",
        "death_date",
        "death_datetime",
        "death_type_concept_id",
        "cause_concept_id",
        "cause_source_value",
        "cause_source_concept_id"
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
        "personId",
        "deathDate",
        "deathDatetime",
        "deathTypeConceptId",
        "causeConceptId",
        "causeSourceValue",
        "causeSourceConceptId"
    };
    
    //
    // javaNamesProper
    //
    
    public static final String[] JAVA_NAMES_PROPER = {
        "PersonId",
        "DeathDate",
        "DeathDatetime",
        "DeathTypeConceptId",
        "CauseConceptId",
        "CauseSourceValue",
        "CauseSourceConceptId"
    };
    
    
    //
    // member variables
    //
    
    private HashMap<String, String> descriptions = new HashMap<String, String>();
    
    private Integer personId;
    
    private Date deathDate;
    
    private Date deathDatetime;
    
    private Integer deathTypeConceptId;
    
    private Integer causeConceptId;
    
    private String causeSourceValue;
    
    private Integer causeSourceConceptId;
    
    //
    // trivial getters and setters
    //
    
    // personId
    
    public void setPersonId(Integer val) {
        this.personId = val;
    }
    
    public Integer getPersonId() {
        return this.personId;
    }
    
    // deathDate
    
    public void setDeathDate(Date val) {
        this.deathDate = val;
    }
    
    public Date getDeathDate() {
        return this.deathDate;
    }
    
    // deathDatetime
    
    public void setDeathDatetime(Date val) {
        this.deathDatetime = val;
    }
    
    public Date getDeathDatetime() {
        return this.deathDatetime;
    }
    
    // deathTypeConceptId
    
    public void setDeathTypeConceptId(Integer val) {
        this.deathTypeConceptId = val;
    }
    
    public Integer getDeathTypeConceptId() {
        return this.deathTypeConceptId;
    }
    
    // causeConceptId
    
    public void setCauseConceptId(Integer val) {
        this.causeConceptId = val;
    }
    
    public Integer getCauseConceptId() {
        return this.causeConceptId;
    }
    
    // causeSourceValue
    
    public void setCauseSourceValue(String val) {
        this.causeSourceValue = val;
    }
    
    public String getCauseSourceValue() {
        return this.causeSourceValue;
    }
    
    // causeSourceConceptId
    
    public void setCauseSourceConceptId(Integer val) {
        this.causeSourceConceptId = val;
    }
    
    public Integer getCauseSourceConceptId() {
        return this.causeSourceConceptId;
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
