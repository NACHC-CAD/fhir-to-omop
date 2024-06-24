//
// Data Value Object (DVO) for OBSERVATION
//

package org.nachc.tools.omop.yaorma.dvo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;
import java.math.BigDecimal;

import org.yaorma.dvo.Dvo;

public class ObservationDvo implements Dvo {

    //
    // tableName
    //
    
    public static final String TABLE_NAME = "OBSERVATION";
    
    //
    // schemaName
    //
    
    public static final String SCHEMA_NAME = "synthea_micro.dbo";
    
    //
    // columnNames
    //
    
    public static final String[] COLUMN_NAMES = {
        "observation_id",
        "person_id",
        "observation_concept_id",
        "observation_date",
        "observation_datetime",
        "observation_type_concept_id",
        "value_as_number",
        "value_as_string",
        "value_as_concept_id",
        "qualifier_concept_id",
        "unit_concept_id",
        "provider_id",
        "visit_occurrence_id",
        "visit_detail_id",
        "observation_source_value",
        "observation_source_concept_id",
        "unit_source_value",
        "qualifier_source_value",
        "value_source_value",
        "observation_event_id",
        "obs_event_field_concept_id"
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
        "observationId",
        "personId",
        "observationConceptId",
        "observationDate",
        "observationDatetime",
        "observationTypeConceptId",
        "valueAsNumber",
        "valueAsString",
        "valueAsConceptId",
        "qualifierConceptId",
        "unitConceptId",
        "providerId",
        "visitOccurrenceId",
        "visitDetailId",
        "observationSourceValue",
        "observationSourceConceptId",
        "unitSourceValue",
        "qualifierSourceValue",
        "valueSourceValue",
        "observationEventId",
        "obsEventFieldConceptId"
    };
    
    //
    // javaNamesProper
    //
    
    public static final String[] JAVA_NAMES_PROPER = {
        "ObservationId",
        "PersonId",
        "ObservationConceptId",
        "ObservationDate",
        "ObservationDatetime",
        "ObservationTypeConceptId",
        "ValueAsNumber",
        "ValueAsString",
        "ValueAsConceptId",
        "QualifierConceptId",
        "UnitConceptId",
        "ProviderId",
        "VisitOccurrenceId",
        "VisitDetailId",
        "ObservationSourceValue",
        "ObservationSourceConceptId",
        "UnitSourceValue",
        "QualifierSourceValue",
        "ValueSourceValue",
        "ObservationEventId",
        "ObsEventFieldConceptId"
    };
    
    
    //
    // member variables
    //
    
    private HashMap<String, String> descriptions = new HashMap<String, String>();
    
    private Integer observationId;
    
    private Integer personId;
    
    private Integer observationConceptId;
    
    private Date observationDate;
    
    private Date observationDatetime;
    
    private Integer observationTypeConceptId;
    
    private BigDecimal valueAsNumber;
    
    private String valueAsString;
    
    private Integer valueAsConceptId;
    
    private Integer qualifierConceptId;
    
    private Integer unitConceptId;
    
    private Integer providerId;
    
    private Integer visitOccurrenceId;
    
    private Integer visitDetailId;
    
    private String observationSourceValue;
    
    private Integer observationSourceConceptId;
    
    private String unitSourceValue;
    
    private String qualifierSourceValue;
    
    private String valueSourceValue;
    
    private Long observationEventId;
    
    private Integer obsEventFieldConceptId;
    
    //
    // trivial getters and setters
    //
    
    // observationId
    
    public void setObservationId(Integer val) {
        this.observationId = val;
    }
    
    public Integer getObservationId() {
        return this.observationId;
    }
    
    // personId
    
    public void setPersonId(Integer val) {
        this.personId = val;
    }
    
    public Integer getPersonId() {
        return this.personId;
    }
    
    // observationConceptId
    
    public void setObservationConceptId(Integer val) {
        this.observationConceptId = val;
    }
    
    public Integer getObservationConceptId() {
        return this.observationConceptId;
    }
    
    // observationDate
    
    public void setObservationDate(Date val) {
        this.observationDate = val;
    }
    
    public Date getObservationDate() {
        return this.observationDate;
    }
    
    // observationDatetime
    
    public void setObservationDatetime(Date val) {
        this.observationDatetime = val;
    }
    
    public Date getObservationDatetime() {
        return this.observationDatetime;
    }
    
    // observationTypeConceptId
    
    public void setObservationTypeConceptId(Integer val) {
        this.observationTypeConceptId = val;
    }
    
    public Integer getObservationTypeConceptId() {
        return this.observationTypeConceptId;
    }
    
    // valueAsNumber
    
    public void setValueAsNumber(BigDecimal val) {
        this.valueAsNumber = val;
    }
    
    public BigDecimal getValueAsNumber() {
        return this.valueAsNumber;
    }
    
    // valueAsString
    
    public void setValueAsString(String val) {
        this.valueAsString = val;
    }
    
    public String getValueAsString() {
        return this.valueAsString;
    }
    
    // valueAsConceptId
    
    public void setValueAsConceptId(Integer val) {
        this.valueAsConceptId = val;
    }
    
    public Integer getValueAsConceptId() {
        return this.valueAsConceptId;
    }
    
    // qualifierConceptId
    
    public void setQualifierConceptId(Integer val) {
        this.qualifierConceptId = val;
    }
    
    public Integer getQualifierConceptId() {
        return this.qualifierConceptId;
    }
    
    // unitConceptId
    
    public void setUnitConceptId(Integer val) {
        this.unitConceptId = val;
    }
    
    public Integer getUnitConceptId() {
        return this.unitConceptId;
    }
    
    // providerId
    
    public void setProviderId(Integer val) {
        this.providerId = val;
    }
    
    public Integer getProviderId() {
        return this.providerId;
    }
    
    // visitOccurrenceId
    
    public void setVisitOccurrenceId(Integer val) {
        this.visitOccurrenceId = val;
    }
    
    public Integer getVisitOccurrenceId() {
        return this.visitOccurrenceId;
    }
    
    // visitDetailId
    
    public void setVisitDetailId(Integer val) {
        this.visitDetailId = val;
    }
    
    public Integer getVisitDetailId() {
        return this.visitDetailId;
    }
    
    // observationSourceValue
    
    public void setObservationSourceValue(String val) {
        this.observationSourceValue = val;
    }
    
    public String getObservationSourceValue() {
        return this.observationSourceValue;
    }
    
    // observationSourceConceptId
    
    public void setObservationSourceConceptId(Integer val) {
        this.observationSourceConceptId = val;
    }
    
    public Integer getObservationSourceConceptId() {
        return this.observationSourceConceptId;
    }
    
    // unitSourceValue
    
    public void setUnitSourceValue(String val) {
        this.unitSourceValue = val;
    }
    
    public String getUnitSourceValue() {
        return this.unitSourceValue;
    }
    
    // qualifierSourceValue
    
    public void setQualifierSourceValue(String val) {
        this.qualifierSourceValue = val;
    }
    
    public String getQualifierSourceValue() {
        return this.qualifierSourceValue;
    }
    
    // valueSourceValue
    
    public void setValueSourceValue(String val) {
        this.valueSourceValue = val;
    }
    
    public String getValueSourceValue() {
        return this.valueSourceValue;
    }
    
    // observationEventId
    
    public void setObservationEventId(Long val) {
        this.observationEventId = val;
    }
    
    public Long getObservationEventId() {
        return this.observationEventId;
    }
    
    // obsEventFieldConceptId
    
    public void setObsEventFieldConceptId(Integer val) {
        this.obsEventFieldConceptId = val;
    }
    
    public Integer getObsEventFieldConceptId() {
        return this.obsEventFieldConceptId;
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
