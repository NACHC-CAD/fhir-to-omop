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
    
    public static final String SCHEMA_NAME = "synthea_omop.dbo";
    
    //
    // columnNames
    //
    
    public static final String[] COLUMN_NAMES = {
        "condition_concept_id",
        "condition_end_date",
        "condition_end_datetime",
        "condition_occurrence_id",
        "condition_source_concept_id",
        "condition_source_value",
        "condition_start_date",
        "condition_start_datetime",
        "condition_status_concept_id",
        "condition_status_source_value",
        "condition_type_concept_id",
        "person_id",
        "provider_id",
        "stop_reason",
        "visit_detail_id",
        "visit_occurrence_id"
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
        "conditionConceptId",
        "conditionEndDate",
        "conditionEndDatetime",
        "conditionOccurrenceId",
        "conditionSourceConceptId",
        "conditionSourceValue",
        "conditionStartDate",
        "conditionStartDatetime",
        "conditionStatusConceptId",
        "conditionStatusSourceValue",
        "conditionTypeConceptId",
        "personId",
        "providerId",
        "stopReason",
        "visitDetailId",
        "visitOccurrenceId"
    };
    
    //
    // javaNamesProper
    //
    
    public static final String[] JAVA_NAMES_PROPER = {
        "ConditionConceptId",
        "ConditionEndDate",
        "ConditionEndDatetime",
        "ConditionOccurrenceId",
        "ConditionSourceConceptId",
        "ConditionSourceValue",
        "ConditionStartDate",
        "ConditionStartDatetime",
        "ConditionStatusConceptId",
        "ConditionStatusSourceValue",
        "ConditionTypeConceptId",
        "PersonId",
        "ProviderId",
        "StopReason",
        "VisitDetailId",
        "VisitOccurrenceId"
    };
    
    
    //
    // member variables
    //
    
    private HashMap<String, String> descriptions = new HashMap<String, String>();
    
    private Integer conditionConceptId;
    
    private Date conditionEndDate;
    
    private String conditionEndDatetime;
    
    private Integer conditionOccurrenceId;
    
    private Integer conditionSourceConceptId;
    
    private String conditionSourceValue;
    
    private Date conditionStartDate;
    
    private String conditionStartDatetime;
    
    private Integer conditionStatusConceptId;
    
    private String conditionStatusSourceValue;
    
    private Integer conditionTypeConceptId;
    
    private Integer personId;
    
    private Integer providerId;
    
    private String stopReason;
    
    private Integer visitDetailId;
    
    private Integer visitOccurrenceId;
    
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
    
    // conditionConceptId
    
    public void setConditionConceptId(Integer val) {
        this.conditionConceptId = val;
    }
    
    public Integer getConditionConceptId() {
        return this.conditionConceptId;
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
    
    // conditionOccurrenceId
    
    public void setConditionOccurrenceId(Integer val) {
        this.conditionOccurrenceId = val;
    }
    
    public Integer getConditionOccurrenceId() {
        return this.conditionOccurrenceId;
    }
    
    // conditionSourceConceptId
    
    public void setConditionSourceConceptId(Integer val) {
        this.conditionSourceConceptId = val;
    }
    
    public Integer getConditionSourceConceptId() {
        return this.conditionSourceConceptId;
    }
    
    // conditionSourceValue
    
    public void setConditionSourceValue(String val) {
        this.conditionSourceValue = val;
    }
    
    public String getConditionSourceValue() {
        return this.conditionSourceValue;
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
    
    // conditionStatusConceptId
    
    public void setConditionStatusConceptId(Integer val) {
        this.conditionStatusConceptId = val;
    }
    
    public Integer getConditionStatusConceptId() {
        return this.conditionStatusConceptId;
    }
    
    // conditionStatusSourceValue
    
    public void setConditionStatusSourceValue(String val) {
        this.conditionStatusSourceValue = val;
    }
    
    public String getConditionStatusSourceValue() {
        return this.conditionStatusSourceValue;
    }
    
    // conditionTypeConceptId
    
    public void setConditionTypeConceptId(Integer val) {
        this.conditionTypeConceptId = val;
    }
    
    public Integer getConditionTypeConceptId() {
        return this.conditionTypeConceptId;
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
    
    // stopReason
    
    public void setStopReason(String val) {
        this.stopReason = val;
    }
    
    public String getStopReason() {
        return this.stopReason;
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
