//
// Data Value Object (DVO) for OBSERVATION_PERIOD
//

package org.nachc.tools.omop.yaorma.dvo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;
import java.math.BigDecimal;

import org.yaorma.dvo.Dvo;

public class ObservationPeriodDvo implements Dvo {

    //
    // tableName
    //
    
    public static final String TABLE_NAME = "OBSERVATION_PERIOD";
    
    //
    // schemaName
    //
    
    public static final String SCHEMA_NAME = "synthea_micro.dbo";
    
    //
    // columnNames
    //
    
    public static final String[] COLUMN_NAMES = {
        "observation_period_id",
        "person_id",
        "observation_period_start_date",
        "observation_period_end_date",
        "period_type_concept_id"
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
        "observationPeriodId",
        "personId",
        "observationPeriodStartDate",
        "observationPeriodEndDate",
        "periodTypeConceptId"
    };
    
    //
    // javaNamesProper
    //
    
    public static final String[] JAVA_NAMES_PROPER = {
        "ObservationPeriodId",
        "PersonId",
        "ObservationPeriodStartDate",
        "ObservationPeriodEndDate",
        "PeriodTypeConceptId"
    };
    
    
    //
    // member variables
    //
    
    private HashMap<String, String> descriptions = new HashMap<String, String>();
    
    private Integer observationPeriodId;
    
    private Integer personId;
    
    private Date observationPeriodStartDate;
    
    private Date observationPeriodEndDate;
    
    private Integer periodTypeConceptId;
    
    //
    // trivial getters and setters
    //
    
    // observationPeriodId
    
    public void setObservationPeriodId(Integer val) {
        this.observationPeriodId = val;
    }
    
    public Integer getObservationPeriodId() {
        return this.observationPeriodId;
    }
    
    // personId
    
    public void setPersonId(Integer val) {
        this.personId = val;
    }
    
    public Integer getPersonId() {
        return this.personId;
    }
    
    // observationPeriodStartDate
    
    public void setObservationPeriodStartDate(Date val) {
        this.observationPeriodStartDate = val;
    }
    
    public Date getObservationPeriodStartDate() {
        return this.observationPeriodStartDate;
    }
    
    // observationPeriodEndDate
    
    public void setObservationPeriodEndDate(Date val) {
        this.observationPeriodEndDate = val;
    }
    
    public Date getObservationPeriodEndDate() {
        return this.observationPeriodEndDate;
    }
    
    // periodTypeConceptId
    
    public void setPeriodTypeConceptId(Integer val) {
        this.periodTypeConceptId = val;
    }
    
    public Integer getPeriodTypeConceptId() {
        return this.periodTypeConceptId;
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
