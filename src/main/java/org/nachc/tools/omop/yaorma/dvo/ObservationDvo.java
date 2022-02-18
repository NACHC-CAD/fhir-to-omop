//
// Data Value Object (DVO) for observation
//

package org.nachc.tools.omop.yaorma.dvo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;

import org.yaorma.dvo.Dvo;

public class ObservationDvo implements Dvo {

    //
    // tableName
    //
    
    public static final String TABLE_NAME = "observation";
    
    //
    // schemaName
    //
    
    public static final String SCHEMA_NAME = "synthea_omop.dbo";
    
    //
    // columnNames
    //
    
    public static final String[] COLUMN_NAMES = {
        "obs_event_field_concept_id",
        "observation_concept_id",
        "observation_date",
        "observation_datetime",
        "observation_event_id",
        "observation_id",
        "observation_source_concept_id",
        "observation_source_value",
        "observation_type_concept_id",
        "person_id",
        "provider_id",
        "qualifier_concept_id",
        "qualifier_source_value",
        "unit_concept_id",
        "unit_source_value",
        "value_as_concept_id",
        "value_as_number",
        "value_as_string",
        "value_source_value",
        "visit_detail_id",
        "visit_occurrence_id"
    };
    
    //
    // primaryKeyColumnNames
    //
    
    public static final String[] PRIMARY_KEY_COLUMN_NAMES = {
        "observation_id"
    };
    
    //
    // javaNames
    //
    
    public static final String[] JAVA_NAMES = {
        "obsEventFieldConceptId",
        "observationConceptId",
        "observationDate",
        "observationDatetime",
        "observationEventId",
        "observationId",
        "observationSourceConceptId",
        "observationSourceValue",
        "observationTypeConceptId",
        "personId",
        "providerId",
        "qualifierConceptId",
        "qualifierSourceValue",
        "unitConceptId",
        "unitSourceValue",
        "valueAsConceptId",
        "valueAsNumber",
        "valueAsString",
        "valueSourceValue",
        "visitDetailId",
        "visitOccurrenceId"
    };
    
    //
    // javaNamesProper
    //
    
    public static final String[] JAVA_NAMES_PROPER = {
        "ObsEventFieldConceptId",
        "ObservationConceptId",
        "ObservationDate",
        "ObservationDatetime",
        "ObservationEventId",
        "ObservationId",
        "ObservationSourceConceptId",
        "ObservationSourceValue",
        "ObservationTypeConceptId",
        "PersonId",
        "ProviderId",
        "QualifierConceptId",
        "QualifierSourceValue",
        "UnitConceptId",
        "UnitSourceValue",
        "ValueAsConceptId",
        "ValueAsNumber",
        "ValueAsString",
        "ValueSourceValue",
        "VisitDetailId",
        "VisitOccurrenceId"
    };
    
    
    //
    // member variables
    //
    
    private HashMap<String, String> descriptions = new HashMap<String, String>();
    
    private Integer obsEventFieldConceptId;
    
    private Integer observationConceptId;
    
    private Date observationDate;
    
    private String observationDatetime;
    
    private String observationEventId;
    
    private Integer observationId;
    
    private Integer observationSourceConceptId;
    
    private String observationSourceValue;
    
    private Integer observationTypeConceptId;
    
    private Integer personId;
    
    private Integer providerId;
    
    private Integer qualifierConceptId;
    
    private String qualifierSourceValue;
    
    private Integer unitConceptId;
    
    private String unitSourceValue;
    
    private Integer valueAsConceptId;
    
    private String valueAsNumber;
    
    private String valueAsString;
    
    private String valueSourceValue;
    
    private Integer visitDetailId;
    
    private Integer visitOccurrenceId;
    
    private ConceptDvo observationConceptDvo;
    
    private ConceptDvo observationSourceConceptDvo;
    
    private ConceptDvo observationTypeConceptDvo;
    
    private ConceptDvo obsEventFieldConceptDvo;
    
    private PersonDvo personDvo;
    
    private ProviderDvo providerDvo;
    
    private ConceptDvo qualifierConceptDvo;
    
    private ConceptDvo unitConceptDvo;
    
    private ConceptDvo valueAsConceptDvo;
    
    private VisitDetailDvo visitDetailDvo;
    
    private VisitOccurrenceDvo visitOccurrenceDvo;
    
    //
    // trivial getters and setters
    //
    
    // obsEventFieldConceptId
    
    public void setObsEventFieldConceptId(Integer val) {
        this.obsEventFieldConceptId = val;
    }
    
    public Integer getObsEventFieldConceptId() {
        return this.obsEventFieldConceptId;
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
    
    public void setObservationDatetime(String val) {
        this.observationDatetime = val;
    }
    
    public String getObservationDatetime() {
        return this.observationDatetime;
    }
    
    // observationEventId
    
    public void setObservationEventId(String val) {
        this.observationEventId = val;
    }
    
    public String getObservationEventId() {
        return this.observationEventId;
    }
    
    // observationId
    
    public void setObservationId(Integer val) {
        this.observationId = val;
    }
    
    public Integer getObservationId() {
        return this.observationId;
    }
    
    // observationSourceConceptId
    
    public void setObservationSourceConceptId(Integer val) {
        this.observationSourceConceptId = val;
    }
    
    public Integer getObservationSourceConceptId() {
        return this.observationSourceConceptId;
    }
    
    // observationSourceValue
    
    public void setObservationSourceValue(String val) {
        this.observationSourceValue = val;
    }
    
    public String getObservationSourceValue() {
        return this.observationSourceValue;
    }
    
    // observationTypeConceptId
    
    public void setObservationTypeConceptId(Integer val) {
        this.observationTypeConceptId = val;
    }
    
    public Integer getObservationTypeConceptId() {
        return this.observationTypeConceptId;
    }
    
    // personId
    
    public void setPersonId(Integer val) {
        this.personId = val;
    }
    
    public Integer getPersonId() {
        return this.personId;
    }
    
    // providerId
    
    public void setProviderId(Integer val) {
        this.providerId = val;
    }
    
    public Integer getProviderId() {
        return this.providerId;
    }
    
    // qualifierConceptId
    
    public void setQualifierConceptId(Integer val) {
        this.qualifierConceptId = val;
    }
    
    public Integer getQualifierConceptId() {
        return this.qualifierConceptId;
    }
    
    // qualifierSourceValue
    
    public void setQualifierSourceValue(String val) {
        this.qualifierSourceValue = val;
    }
    
    public String getQualifierSourceValue() {
        return this.qualifierSourceValue;
    }
    
    // unitConceptId
    
    public void setUnitConceptId(Integer val) {
        this.unitConceptId = val;
    }
    
    public Integer getUnitConceptId() {
        return this.unitConceptId;
    }
    
    // unitSourceValue
    
    public void setUnitSourceValue(String val) {
        this.unitSourceValue = val;
    }
    
    public String getUnitSourceValue() {
        return this.unitSourceValue;
    }
    
    // valueAsConceptId
    
    public void setValueAsConceptId(Integer val) {
        this.valueAsConceptId = val;
    }
    
    public Integer getValueAsConceptId() {
        return this.valueAsConceptId;
    }
    
    // valueAsNumber
    
    public void setValueAsNumber(String val) {
        this.valueAsNumber = val;
    }
    
    public String getValueAsNumber() {
        return this.valueAsNumber;
    }
    
    // valueAsString
    
    public void setValueAsString(String val) {
        this.valueAsString = val;
    }
    
    public String getValueAsString() {
        return this.valueAsString;
    }
    
    // valueSourceValue
    
    public void setValueSourceValue(String val) {
        this.valueSourceValue = val;
    }
    
    public String getValueSourceValue() {
        return this.valueSourceValue;
    }
    
    // visitDetailId
    
    public void setVisitDetailId(Integer val) {
        this.visitDetailId = val;
    }
    
    public Integer getVisitDetailId() {
        return this.visitDetailId;
    }
    
    // visitOccurrenceId
    
    public void setVisitOccurrenceId(Integer val) {
        this.visitOccurrenceId = val;
    }
    
    public Integer getVisitOccurrenceId() {
        return this.visitOccurrenceId;
    }
    
    // observationConceptDvo
    
    public void setObservationConceptDvo(ConceptDvo dvo) {
        this.observationConceptDvo = dvo;
    }
    
    public ConceptDvo getObservationConceptDvo() {
        return this.observationConceptDvo;
    }
    
    // observationSourceConceptDvo
    
    public void setObservationSourceConceptDvo(ConceptDvo dvo) {
        this.observationSourceConceptDvo = dvo;
    }
    
    public ConceptDvo getObservationSourceConceptDvo() {
        return this.observationSourceConceptDvo;
    }
    
    // observationTypeConceptDvo
    
    public void setObservationTypeConceptDvo(ConceptDvo dvo) {
        this.observationTypeConceptDvo = dvo;
    }
    
    public ConceptDvo getObservationTypeConceptDvo() {
        return this.observationTypeConceptDvo;
    }
    
    // obsEventFieldConceptDvo
    
    public void setObsEventFieldConceptDvo(ConceptDvo dvo) {
        this.obsEventFieldConceptDvo = dvo;
    }
    
    public ConceptDvo getObsEventFieldConceptDvo() {
        return this.obsEventFieldConceptDvo;
    }
    
    // personDvo
    
    public void setPersonDvo(PersonDvo dvo) {
        this.personDvo = dvo;
    }
    
    public PersonDvo getPersonDvo() {
        return this.personDvo;
    }
    
    // providerDvo
    
    public void setProviderDvo(ProviderDvo dvo) {
        this.providerDvo = dvo;
    }
    
    public ProviderDvo getProviderDvo() {
        return this.providerDvo;
    }
    
    // qualifierConceptDvo
    
    public void setQualifierConceptDvo(ConceptDvo dvo) {
        this.qualifierConceptDvo = dvo;
    }
    
    public ConceptDvo getQualifierConceptDvo() {
        return this.qualifierConceptDvo;
    }
    
    // unitConceptDvo
    
    public void setUnitConceptDvo(ConceptDvo dvo) {
        this.unitConceptDvo = dvo;
    }
    
    public ConceptDvo getUnitConceptDvo() {
        return this.unitConceptDvo;
    }
    
    // valueAsConceptDvo
    
    public void setValueAsConceptDvo(ConceptDvo dvo) {
        this.valueAsConceptDvo = dvo;
    }
    
    public ConceptDvo getValueAsConceptDvo() {
        return this.valueAsConceptDvo;
    }
    
    // visitDetailDvo
    
    public void setVisitDetailDvo(VisitDetailDvo dvo) {
        this.visitDetailDvo = dvo;
    }
    
    public VisitDetailDvo getVisitDetailDvo() {
        return this.visitDetailDvo;
    }
    
    // visitOccurrenceDvo
    
    public void setVisitOccurrenceDvo(VisitOccurrenceDvo dvo) {
        this.visitOccurrenceDvo = dvo;
    }
    
    public VisitOccurrenceDvo getVisitOccurrenceDvo() {
        return this.visitOccurrenceDvo;
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
            getObservationId()  == null ? null: getObservationId() + ""
        };
        return rtn;
    }
}
