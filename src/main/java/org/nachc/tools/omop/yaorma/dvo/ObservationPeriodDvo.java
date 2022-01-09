//
// Data Value Object (DVO) for observation_period
//

package org.nachc.tools.omop.yaorma.dvo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;

import org.yaorma.dvo.Dvo;

public class ObservationPeriodDvo implements Dvo {

    //
    // tableName
    //
    
    public static final String TABLE_NAME = "observation_period";
    
    //
    // schemaName
    //
    
    public static final String SCHEMA_NAME = "synthea_omop";
    
    //
    // columnNames
    //
    
    public static final String[] COLUMN_NAMES = {
        "observation_period_end_date",
        "observation_period_id",
        "observation_period_start_date",
        "period_type_concept_id",
        "person_id"
    };
    
    //
    // primaryKeyColumnNames
    //
    
    public static final String[] PRIMARY_KEY_COLUMN_NAMES = {
        "observation_period_id"
    };
    
    //
    // javaNames
    //
    
    public static final String[] JAVA_NAMES = {
        "observationPeriodEndDate",
        "observationPeriodId",
        "observationPeriodStartDate",
        "periodTypeConceptId",
        "personId"
    };
    
    //
    // javaNamesProper
    //
    
    public static final String[] JAVA_NAMES_PROPER = {
        "ObservationPeriodEndDate",
        "ObservationPeriodId",
        "ObservationPeriodStartDate",
        "PeriodTypeConceptId",
        "PersonId"
    };
    
    
    //
    // member variables
    //
    
    private HashMap<String, String> descriptions = new HashMap<String, String>();
    
    private String observationPeriodEndDate;
    
    private Integer observationPeriodId;
    
    private String observationPeriodStartDate;
    
    private Integer periodTypeConceptId;
    
    private Integer personId;
    
    private ConceptDvo periodTypeConceptDvo;
    
    private PersonDvo personDvo;
    
    //
    // trivial getters and setters
    //
    
    // observationPeriodEndDate
    
    public void setObservationPeriodEndDate(String val) {
        this.observationPeriodEndDate = val;
    }
    
    public String getObservationPeriodEndDate() {
        return this.observationPeriodEndDate;
    }
    
    // observationPeriodId
    
    public void setObservationPeriodId(Integer val) {
        this.observationPeriodId = val;
    }
    
    public Integer getObservationPeriodId() {
        return this.observationPeriodId;
    }
    
    // observationPeriodStartDate
    
    public void setObservationPeriodStartDate(String val) {
        this.observationPeriodStartDate = val;
    }
    
    public String getObservationPeriodStartDate() {
        return this.observationPeriodStartDate;
    }
    
    // periodTypeConceptId
    
    public void setPeriodTypeConceptId(Integer val) {
        this.periodTypeConceptId = val;
    }
    
    public Integer getPeriodTypeConceptId() {
        return this.periodTypeConceptId;
    }
    
    // personId
    
    public void setPersonId(Integer val) {
        this.personId = val;
    }
    
    public Integer getPersonId() {
        return this.personId;
    }
    
    // periodTypeConceptDvo
    
    public void setPeriodTypeConceptDvo(ConceptDvo dvo) {
        this.periodTypeConceptDvo = dvo;
    }
    
    public ConceptDvo getPeriodTypeConceptDvo() {
        return this.periodTypeConceptDvo;
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
            getObservationPeriodId()  == null ? null: getObservationPeriodId() + ""
        };
        return rtn;
    }
}
