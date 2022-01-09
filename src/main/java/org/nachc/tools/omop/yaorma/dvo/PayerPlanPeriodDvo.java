//
// Data Value Object (DVO) for payer_plan_period
//

package org.nachc.tools.omop.yaorma.dvo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;

import org.yaorma.dvo.Dvo;

public class PayerPlanPeriodDvo implements Dvo {

    //
    // tableName
    //
    
    public static final String TABLE_NAME = "payer_plan_period";
    
    //
    // schemaName
    //
    
    public static final String SCHEMA_NAME = "synthea_omop";
    
    //
    // columnNames
    //
    
    public static final String[] COLUMN_NAMES = {
        "family_source_value",
        "payer_concept_id",
        "payer_plan_period_end_date",
        "payer_plan_period_id",
        "payer_plan_period_start_date",
        "payer_source_concept_id",
        "payer_source_value",
        "person_id",
        "plan_concept_id",
        "plan_source_concept_id",
        "plan_source_value",
        "sponsor_concept_id",
        "sponsor_source_concept_id",
        "sponsor_source_value",
        "stop_reason_concept_id",
        "stop_reason_source_concept_id",
        "stop_reason_source_value"
    };
    
    //
    // primaryKeyColumnNames
    //
    
    public static final String[] PRIMARY_KEY_COLUMN_NAMES = {
        "payer_plan_period_id"
    };
    
    //
    // javaNames
    //
    
    public static final String[] JAVA_NAMES = {
        "familySourceValue",
        "payerConceptId",
        "payerPlanPeriodEndDate",
        "payerPlanPeriodId",
        "payerPlanPeriodStartDate",
        "payerSourceConceptId",
        "payerSourceValue",
        "personId",
        "planConceptId",
        "planSourceConceptId",
        "planSourceValue",
        "sponsorConceptId",
        "sponsorSourceConceptId",
        "sponsorSourceValue",
        "stopReasonConceptId",
        "stopReasonSourceConceptId",
        "stopReasonSourceValue"
    };
    
    //
    // javaNamesProper
    //
    
    public static final String[] JAVA_NAMES_PROPER = {
        "FamilySourceValue",
        "PayerConceptId",
        "PayerPlanPeriodEndDate",
        "PayerPlanPeriodId",
        "PayerPlanPeriodStartDate",
        "PayerSourceConceptId",
        "PayerSourceValue",
        "PersonId",
        "PlanConceptId",
        "PlanSourceConceptId",
        "PlanSourceValue",
        "SponsorConceptId",
        "SponsorSourceConceptId",
        "SponsorSourceValue",
        "StopReasonConceptId",
        "StopReasonSourceConceptId",
        "StopReasonSourceValue"
    };
    
    
    //
    // member variables
    //
    
    private HashMap<String, String> descriptions = new HashMap<String, String>();
    
    private String familySourceValue;
    
    private Integer payerConceptId;
    
    private String payerPlanPeriodEndDate;
    
    private Integer payerPlanPeriodId;
    
    private String payerPlanPeriodStartDate;
    
    private Integer payerSourceConceptId;
    
    private String payerSourceValue;
    
    private Integer personId;
    
    private Integer planConceptId;
    
    private Integer planSourceConceptId;
    
    private String planSourceValue;
    
    private Integer sponsorConceptId;
    
    private Integer sponsorSourceConceptId;
    
    private String sponsorSourceValue;
    
    private Integer stopReasonConceptId;
    
    private Integer stopReasonSourceConceptId;
    
    private String stopReasonSourceValue;
    
    private ConceptDvo payerConceptDvo;
    
    private PersonDvo payerPlanPeriodDvo;
    
    private ConceptDvo payerSourceConceptDvo;
    
    private PersonDvo personDvo;
    
    private ConceptDvo planConceptDvo;
    
    private ConceptDvo planSourceConceptDvo;
    
    private ConceptDvo sponsorConceptDvo;
    
    private ConceptDvo sponsorSourceConceptDvo;
    
    private ConceptDvo stopReasonConceptDvo;
    
    private ConceptDvo stopReasonSourceConceptDvo;
    
    //
    // trivial getters and setters
    //
    
    // familySourceValue
    
    public void setFamilySourceValue(String val) {
        this.familySourceValue = val;
    }
    
    public String getFamilySourceValue() {
        return this.familySourceValue;
    }
    
    // payerConceptId
    
    public void setPayerConceptId(Integer val) {
        this.payerConceptId = val;
    }
    
    public Integer getPayerConceptId() {
        return this.payerConceptId;
    }
    
    // payerPlanPeriodEndDate
    
    public void setPayerPlanPeriodEndDate(String val) {
        this.payerPlanPeriodEndDate = val;
    }
    
    public String getPayerPlanPeriodEndDate() {
        return this.payerPlanPeriodEndDate;
    }
    
    // payerPlanPeriodId
    
    public void setPayerPlanPeriodId(Integer val) {
        this.payerPlanPeriodId = val;
    }
    
    public Integer getPayerPlanPeriodId() {
        return this.payerPlanPeriodId;
    }
    
    // payerPlanPeriodStartDate
    
    public void setPayerPlanPeriodStartDate(String val) {
        this.payerPlanPeriodStartDate = val;
    }
    
    public String getPayerPlanPeriodStartDate() {
        return this.payerPlanPeriodStartDate;
    }
    
    // payerSourceConceptId
    
    public void setPayerSourceConceptId(Integer val) {
        this.payerSourceConceptId = val;
    }
    
    public Integer getPayerSourceConceptId() {
        return this.payerSourceConceptId;
    }
    
    // payerSourceValue
    
    public void setPayerSourceValue(String val) {
        this.payerSourceValue = val;
    }
    
    public String getPayerSourceValue() {
        return this.payerSourceValue;
    }
    
    // personId
    
    public void setPersonId(Integer val) {
        this.personId = val;
    }
    
    public Integer getPersonId() {
        return this.personId;
    }
    
    // planConceptId
    
    public void setPlanConceptId(Integer val) {
        this.planConceptId = val;
    }
    
    public Integer getPlanConceptId() {
        return this.planConceptId;
    }
    
    // planSourceConceptId
    
    public void setPlanSourceConceptId(Integer val) {
        this.planSourceConceptId = val;
    }
    
    public Integer getPlanSourceConceptId() {
        return this.planSourceConceptId;
    }
    
    // planSourceValue
    
    public void setPlanSourceValue(String val) {
        this.planSourceValue = val;
    }
    
    public String getPlanSourceValue() {
        return this.planSourceValue;
    }
    
    // sponsorConceptId
    
    public void setSponsorConceptId(Integer val) {
        this.sponsorConceptId = val;
    }
    
    public Integer getSponsorConceptId() {
        return this.sponsorConceptId;
    }
    
    // sponsorSourceConceptId
    
    public void setSponsorSourceConceptId(Integer val) {
        this.sponsorSourceConceptId = val;
    }
    
    public Integer getSponsorSourceConceptId() {
        return this.sponsorSourceConceptId;
    }
    
    // sponsorSourceValue
    
    public void setSponsorSourceValue(String val) {
        this.sponsorSourceValue = val;
    }
    
    public String getSponsorSourceValue() {
        return this.sponsorSourceValue;
    }
    
    // stopReasonConceptId
    
    public void setStopReasonConceptId(Integer val) {
        this.stopReasonConceptId = val;
    }
    
    public Integer getStopReasonConceptId() {
        return this.stopReasonConceptId;
    }
    
    // stopReasonSourceConceptId
    
    public void setStopReasonSourceConceptId(Integer val) {
        this.stopReasonSourceConceptId = val;
    }
    
    public Integer getStopReasonSourceConceptId() {
        return this.stopReasonSourceConceptId;
    }
    
    // stopReasonSourceValue
    
    public void setStopReasonSourceValue(String val) {
        this.stopReasonSourceValue = val;
    }
    
    public String getStopReasonSourceValue() {
        return this.stopReasonSourceValue;
    }
    
    // payerConceptDvo
    
    public void setPayerConceptDvo(ConceptDvo dvo) {
        this.payerConceptDvo = dvo;
    }
    
    public ConceptDvo getPayerConceptDvo() {
        return this.payerConceptDvo;
    }
    
    // payerPlanPeriodDvo
    
    public void setPayerPlanPeriodDvo(PersonDvo dvo) {
        this.payerPlanPeriodDvo = dvo;
    }
    
    public PersonDvo getPayerPlanPeriodDvo() {
        return this.payerPlanPeriodDvo;
    }
    
    // payerSourceConceptDvo
    
    public void setPayerSourceConceptDvo(ConceptDvo dvo) {
        this.payerSourceConceptDvo = dvo;
    }
    
    public ConceptDvo getPayerSourceConceptDvo() {
        return this.payerSourceConceptDvo;
    }
    
    // personDvo
    
    public void setPersonDvo(PersonDvo dvo) {
        this.personDvo = dvo;
    }
    
    public PersonDvo getPersonDvo() {
        return this.personDvo;
    }
    
    // planConceptDvo
    
    public void setPlanConceptDvo(ConceptDvo dvo) {
        this.planConceptDvo = dvo;
    }
    
    public ConceptDvo getPlanConceptDvo() {
        return this.planConceptDvo;
    }
    
    // planSourceConceptDvo
    
    public void setPlanSourceConceptDvo(ConceptDvo dvo) {
        this.planSourceConceptDvo = dvo;
    }
    
    public ConceptDvo getPlanSourceConceptDvo() {
        return this.planSourceConceptDvo;
    }
    
    // sponsorConceptDvo
    
    public void setSponsorConceptDvo(ConceptDvo dvo) {
        this.sponsorConceptDvo = dvo;
    }
    
    public ConceptDvo getSponsorConceptDvo() {
        return this.sponsorConceptDvo;
    }
    
    // sponsorSourceConceptDvo
    
    public void setSponsorSourceConceptDvo(ConceptDvo dvo) {
        this.sponsorSourceConceptDvo = dvo;
    }
    
    public ConceptDvo getSponsorSourceConceptDvo() {
        return this.sponsorSourceConceptDvo;
    }
    
    // stopReasonConceptDvo
    
    public void setStopReasonConceptDvo(ConceptDvo dvo) {
        this.stopReasonConceptDvo = dvo;
    }
    
    public ConceptDvo getStopReasonConceptDvo() {
        return this.stopReasonConceptDvo;
    }
    
    // stopReasonSourceConceptDvo
    
    public void setStopReasonSourceConceptDvo(ConceptDvo dvo) {
        this.stopReasonSourceConceptDvo = dvo;
    }
    
    public ConceptDvo getStopReasonSourceConceptDvo() {
        return this.stopReasonSourceConceptDvo;
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
            getPayerPlanPeriodId()  == null ? null: getPayerPlanPeriodId() + ""
        };
        return rtn;
    }
}
