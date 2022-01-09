//
// Data Value Object (DVO) for death
//

package org.nachc.tools.omop.yaorma.dvo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;

import org.yaorma.dvo.Dvo;

public class DeathDvo implements Dvo {

    //
    // tableName
    //
    
    public static final String TABLE_NAME = "death";
    
    //
    // schemaName
    //
    
    public static final String SCHEMA_NAME = "synthea_omop";
    
    //
    // columnNames
    //
    
    public static final String[] COLUMN_NAMES = {
        "cause_concept_id",
        "cause_source_concept_id",
        "cause_source_value",
        "death_date",
        "death_datetime",
        "death_type_concept_id",
        "person_id"
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
        "causeConceptId",
        "causeSourceConceptId",
        "causeSourceValue",
        "deathDate",
        "deathDatetime",
        "deathTypeConceptId",
        "personId"
    };
    
    //
    // javaNamesProper
    //
    
    public static final String[] JAVA_NAMES_PROPER = {
        "CauseConceptId",
        "CauseSourceConceptId",
        "CauseSourceValue",
        "DeathDate",
        "DeathDatetime",
        "DeathTypeConceptId",
        "PersonId"
    };
    
    
    //
    // member variables
    //
    
    private HashMap<String, String> descriptions = new HashMap<String, String>();
    
    private Integer causeConceptId;
    
    private Integer causeSourceConceptId;
    
    private String causeSourceValue;
    
    private String deathDate;
    
    private String deathDatetime;
    
    private Integer deathTypeConceptId;
    
    private Integer personId;
    
    private ConceptDvo causeConceptDvo;
    
    private ConceptDvo causeSourceConceptDvo;
    
    private ConceptDvo deathTypeConceptDvo;
    
    private PersonDvo personDvo;
    
    //
    // trivial getters and setters
    //
    
    // causeConceptId
    
    public void setCauseConceptId(Integer val) {
        this.causeConceptId = val;
    }
    
    public Integer getCauseConceptId() {
        return this.causeConceptId;
    }
    
    // causeSourceConceptId
    
    public void setCauseSourceConceptId(Integer val) {
        this.causeSourceConceptId = val;
    }
    
    public Integer getCauseSourceConceptId() {
        return this.causeSourceConceptId;
    }
    
    // causeSourceValue
    
    public void setCauseSourceValue(String val) {
        this.causeSourceValue = val;
    }
    
    public String getCauseSourceValue() {
        return this.causeSourceValue;
    }
    
    // deathDate
    
    public void setDeathDate(String val) {
        this.deathDate = val;
    }
    
    public String getDeathDate() {
        return this.deathDate;
    }
    
    // deathDatetime
    
    public void setDeathDatetime(String val) {
        this.deathDatetime = val;
    }
    
    public String getDeathDatetime() {
        return this.deathDatetime;
    }
    
    // deathTypeConceptId
    
    public void setDeathTypeConceptId(Integer val) {
        this.deathTypeConceptId = val;
    }
    
    public Integer getDeathTypeConceptId() {
        return this.deathTypeConceptId;
    }
    
    // personId
    
    public void setPersonId(Integer val) {
        this.personId = val;
    }
    
    public Integer getPersonId() {
        return this.personId;
    }
    
    // causeConceptDvo
    
    public void setCauseConceptDvo(ConceptDvo dvo) {
        this.causeConceptDvo = dvo;
    }
    
    public ConceptDvo getCauseConceptDvo() {
        return this.causeConceptDvo;
    }
    
    // causeSourceConceptDvo
    
    public void setCauseSourceConceptDvo(ConceptDvo dvo) {
        this.causeSourceConceptDvo = dvo;
    }
    
    public ConceptDvo getCauseSourceConceptDvo() {
        return this.causeSourceConceptDvo;
    }
    
    // deathTypeConceptDvo
    
    public void setDeathTypeConceptDvo(ConceptDvo dvo) {
        this.deathTypeConceptDvo = dvo;
    }
    
    public ConceptDvo getDeathTypeConceptDvo() {
        return this.deathTypeConceptDvo;
    }
    
    // personDvo
    
    public void setPersonDvo(PersonDvo dvo) {
        this.personDvo = dvo;
    }
    
    public PersonDvo getPersonDvo() {
        return this.personDvo;
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
