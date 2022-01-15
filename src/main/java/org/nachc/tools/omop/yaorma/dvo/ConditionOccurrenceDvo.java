//
// Data Value Object (DVO) for condition_occurrence
//

package org.nachc.tools.omop.yaorma.dvo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;

import org.yaorma.dvo.Dvo;

public class ConditionOccurrenceDvo implements Dvo {

    //
    // tableName
    //
    
    public static final String TABLE_NAME = "condition_occurrence";
    
    //
    // schemaName
    //
    
    public static final String SCHEMA_NAME = "synthea_omop";
    
    //
    // columnNames
    //
    
    public static final String[] COLUMN_NAMES = {
        "condition_occurrence_id",
        "person_id",
        "condition_concept_id",
        "condition_start_date",
        "condition_start_datetime",
        "condition_end_date",
        "condition_end_datetime",
        "condition_type_concept_id",
        "condition_status_concept_id",
        "stop_reason",
        "provider_id",
        "visit_occurrence_id",
        "visit_detail_id",
        "condition_source_value",
        "condition_source_concept_id",
        "condition_status_source_value"
    };
    
    //
    // primaryKeyColumnNames
    //
    
    public static final String[] PRIMARY_KEY_COLUMN_NAMES = {
        "condition_occurrence_id"
    };
    
    //
    // javaNames
    //
    
    public static final String[] JAVA_NAMES = {
        "conditionOccurrenceId",
        "personId",
        "conditionConceptId",
        "conditionStartDate",
        "conditionStartDatetime",
        "conditionEndDate",
        "conditionEndDatetime",
        "conditionTypeConceptId",
        "conditionStatusConceptId",
        "stopReason",
        "providerId",
        "visitOccurrenceId",
        "visitDetailId",
        "conditionSourceValue",
        "conditionSourceConceptId",
        "conditionStatusSourceValue"
    };
    
    //
    // javaNamesProper
    //
    
    public static final String[] JAVA_NAMES_PROPER = {
        "ConditionOccurrenceId",
        "PersonId",
        "ConditionConceptId",
        "ConditionStartDate",
        "ConditionStartDatetime",
        "ConditionEndDate",
        "ConditionEndDatetime",
        "ConditionTypeConceptId",
        "ConditionStatusConceptId",
        "StopReason",
        "ProviderId",
        "VisitOccurrenceId",
        "VisitDetailId",
        "ConditionSourceValue",
        "ConditionSourceConceptId",
        "ConditionStatusSourceValue"
    };
    
    
    //
    // member variables
    //
    
    private HashMap<String, String> descriptions = new HashMap<String, String>();
    
    private Integer conditionOccurrenceId;
    
    private Integer personId;
    
    private Integer conditionConceptId;
    
    private Date conditionStartDate;
    
    private String conditionStartDatetime;
    
    private Date conditionEndDate;
    
    private String conditionEndDatetime;
    
    private Integer conditionTypeConceptId;
    
    private Integer conditionStatusConceptId;
    
    private String stopReason;
    
    private Integer providerId;
    
    private Integer visitOccurrenceId;
    
    private Integer visitDetailId;
    
    private String conditionSourceValue;
    
    private Integer conditionSourceConceptId;
    
    private String conditionStatusSourceValue;
    
    private ConceptDvo conditionConceptDvo;
    
    private ConceptDvo conditionSourceConceptDvo;
    
    private ConceptDvo conditionStatusConceptDvo;
    
    private ConceptDvo conditionTypeConceptDvo;
    
    private PersonDvo personDvo;
    
    private ProviderDvo providerDvo;
    
    private VisitDetailDvo visitDetailDvo;
    
    private VisitOccurrenceDvo visitOccurrenceDvo;
    
    //
    // trivial getters and setters
    //
    
    // conditionOccurrenceId
    
    public void setConditionOccurrenceId(Integer val) {
        this.conditionOccurrenceId = val;
    }
    
    public Integer getConditionOccurrenceId() {
        return this.conditionOccurrenceId;
    }
    
    // personId
    
    public void setPersonId(Integer val) {
        this.personId = val;
    }
    
    public Integer getPersonId() {
        return this.personId;
    }
    
    // conditionConceptId
    
    public void setConditionConceptId(Integer val) {
        this.conditionConceptId = val;
    }
    
    public Integer getConditionConceptId() {
        return this.conditionConceptId;
    }
    
    // conditionStartDate
    
    public void setConditionStartDate(Date val) {
        this.conditionStartDate = val;
    }
    
    public Date getConditionStartDate() {
        return this.conditionStartDate;
    }
    
    // conditionStartDatetime
    
    public void setConditionStartDatetime(String val) {
        this.conditionStartDatetime = val;
    }
    
    public String getConditionStartDatetime() {
        return this.conditionStartDatetime;
    }
    
    // conditionEndDate
    
    public void setConditionEndDate(Date val) {
        this.conditionEndDate = val;
    }
    
    public Date getConditionEndDate() {
        return this.conditionEndDate;
    }
    
    // conditionEndDatetime
    
    public void setConditionEndDatetime(String val) {
        this.conditionEndDatetime = val;
    }
    
    public String getConditionEndDatetime() {
        return this.conditionEndDatetime;
    }
    
    // conditionTypeConceptId
    
    public void setConditionTypeConceptId(Integer val) {
        this.conditionTypeConceptId = val;
    }
    
    public Integer getConditionTypeConceptId() {
        return this.conditionTypeConceptId;
    }
    
    // conditionStatusConceptId
    
    public void setConditionStatusConceptId(Integer val) {
        this.conditionStatusConceptId = val;
    }
    
    public Integer getConditionStatusConceptId() {
        return this.conditionStatusConceptId;
    }
    
    // stopReason
    
    public void setStopReason(String val) {
        this.stopReason = val;
    }
    
    public String getStopReason() {
        return this.stopReason;
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
    
    // conditionSourceValue
    
    public void setConditionSourceValue(String val) {
        this.conditionSourceValue = val;
    }
    
    public String getConditionSourceValue() {
        return this.conditionSourceValue;
    }
    
    // conditionSourceConceptId
    
    public void setConditionSourceConceptId(Integer val) {
        this.conditionSourceConceptId = val;
    }
    
    public Integer getConditionSourceConceptId() {
        return this.conditionSourceConceptId;
    }
    
    // conditionStatusSourceValue
    
    public void setConditionStatusSourceValue(String val) {
        this.conditionStatusSourceValue = val;
    }
    
    public String getConditionStatusSourceValue() {
        return this.conditionStatusSourceValue;
    }
    
    // conditionConceptDvo
    
    public void setConditionConceptDvo(ConceptDvo dvo) {
        this.conditionConceptDvo = dvo;
    }
    
    public ConceptDvo getConditionConceptDvo() {
        return this.conditionConceptDvo;
    }
    
    // conditionSourceConceptDvo
    
    public void setConditionSourceConceptDvo(ConceptDvo dvo) {
        this.conditionSourceConceptDvo = dvo;
    }
    
    public ConceptDvo getConditionSourceConceptDvo() {
        return this.conditionSourceConceptDvo;
    }
    
    // conditionStatusConceptDvo
    
    public void setConditionStatusConceptDvo(ConceptDvo dvo) {
        this.conditionStatusConceptDvo = dvo;
    }
    
    public ConceptDvo getConditionStatusConceptDvo() {
        return this.conditionStatusConceptDvo;
    }
    
    // conditionTypeConceptDvo
    
    public void setConditionTypeConceptDvo(ConceptDvo dvo) {
        this.conditionTypeConceptDvo = dvo;
    }
    
    public ConceptDvo getConditionTypeConceptDvo() {
        return this.conditionTypeConceptDvo;
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
            getConditionOccurrenceId()  == null ? null: getConditionOccurrenceId() + ""
        };
        return rtn;
    }
}
