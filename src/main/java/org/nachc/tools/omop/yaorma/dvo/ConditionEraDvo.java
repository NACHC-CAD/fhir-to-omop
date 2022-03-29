//
// Data Value Object (DVO) for condition_era
//

package org.nachc.tools.omop.yaorma.dvo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;

import org.yaorma.dvo.Dvo;

public class ConditionEraDvo implements Dvo {

    //
    // tableName
    //
    
    public static final String TABLE_NAME = "condition_era";
    
    //
    // schemaName
    //
    
    public static final String SCHEMA_NAME = "synthea_omop.dbo";
    
    //
    // columnNames
    //
    
    public static final String[] COLUMN_NAMES = {
        "condition_concept_id",
        "condition_era_end_date",
        "condition_era_id",
        "condition_era_start_date",
        "condition_occurrence_count",
        "person_id"
    };
    
    //
    // primaryKeyColumnNames
    //
    
    public static final String[] PRIMARY_KEY_COLUMN_NAMES = {
        "condition_era_id"
    };
    
    //
    // javaNames
    //
    
    public static final String[] JAVA_NAMES = {
        "conditionConceptId",
        "conditionEraEndDate",
        "conditionEraId",
        "conditionEraStartDate",
        "conditionOccurrenceCount",
        "personId"
    };
    
    //
    // javaNamesProper
    //
    
    public static final String[] JAVA_NAMES_PROPER = {
        "ConditionConceptId",
        "ConditionEraEndDate",
        "ConditionEraId",
        "ConditionEraStartDate",
        "ConditionOccurrenceCount",
        "PersonId"
    };
    
    
    //
    // member variables
    //
    
    private HashMap<String, String> descriptions = new HashMap<String, String>();
    
    private Integer conditionConceptId;
    
    private String conditionEraEndDate;
    
    private Integer conditionEraId;
    
    private String conditionEraStartDate;
    
    private Integer conditionOccurrenceCount;
    
    private Integer personId;
    
    private ConceptDvo conditionConceptDvo;
    
    private PersonDvo personDvo;
    
    //
    // trivial getters and setters
    //
    
    // conditionConceptId
    
    public void setConditionConceptId(Integer val) {
        this.conditionConceptId = val;
    }
    
    public Integer getConditionConceptId() {
        return this.conditionConceptId;
    }
    
    // conditionEraEndDate
    
    public void setConditionEraEndDate(String val) {
        this.conditionEraEndDate = val;
    }
    
    public String getConditionEraEndDate() {
        return this.conditionEraEndDate;
    }
    
    // conditionEraId
    
    public void setConditionEraId(Integer val) {
        this.conditionEraId = val;
    }
    
    public Integer getConditionEraId() {
        return this.conditionEraId;
    }
    
    // conditionEraStartDate
    
    public void setConditionEraStartDate(String val) {
        this.conditionEraStartDate = val;
    }
    
    public String getConditionEraStartDate() {
        return this.conditionEraStartDate;
    }
    
    // conditionOccurrenceCount
    
    public void setConditionOccurrenceCount(Integer val) {
        this.conditionOccurrenceCount = val;
    }
    
    public Integer getConditionOccurrenceCount() {
        return this.conditionOccurrenceCount;
    }
    
    // personId
    
    public void setPersonId(Integer val) {
        this.personId = val;
    }
    
    public Integer getPersonId() {
        return this.personId;
    }
    
    // conditionConceptDvo
    
    public void setConditionConceptDvo(ConceptDvo dvo) {
        this.conditionConceptDvo = dvo;
    }
    
    public ConceptDvo getConditionConceptDvo() {
        return this.conditionConceptDvo;
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
            getConditionEraId()  == null ? null: getConditionEraId() + ""
        };
        return rtn;
    }
}
