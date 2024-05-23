//
// Data Value Object (DVO) for PAYER_PLAN_PERIOD
//

package org.nachc.tools.omop.yaorma.dvo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;
import java.math.BigDecimal;

import org.yaorma.dvo.Dvo;

public class PayerPlanPeriodDvo implements Dvo {

    //
    // tableName
    //
    
    public static final String TABLE_NAME = "PAYER_PLAN_PERIOD";
    
    //
    // schemaName
    //
    
    public static final String SCHEMA_NAME = "synthea_micro.dbo";
    
    //
    // columnNames
    //
    
    public static final String[] COLUMN_NAMES = {
        "payer_plan_period_id",
        "person_id",
        "payer_plan_period_start_date",
        "payer_plan_period_end_date",
        "payer_concept_id",
        "payer_source_value",
        "payer_source_concept_id",
        "plan_concept_id",
        "plan_source_value",
        "plan_source_concept_id",
        "sponsor_concept_id",
        "sponsor_source_value",
        "sponsor_source_concept_id",
        "family_source_value",
        "stop_reason_concept_id",
        "stop_reason_source_value",
        "stop_reason_source_concept_id"
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
        "payerPlanPeriodId",
        "personId",
        "payerPlanPeriodStartDate",
        "payerPlanPeriodEndDate",
        "payerConceptId",
        "payerSourceValue",
        "payerSourceConceptId",
        "planConceptId",
        "planSourceValue",
        "planSourceConceptId",
        "sponsorConceptId",
        "sponsorSourceValue",
        "sponsorSourceConceptId",
        "familySourceValue",
        "stopReasonConceptId",
        "stopReasonSourceValue",
        "stopReasonSourceConceptId"
    };
    
    //
    // javaNamesProper
    //
    
    public static final String[] JAVA_NAMES_PROPER = {
        "PayerPlanPeriodId",
        "PersonId",
        "PayerPlanPeriodStartDate",
        "PayerPlanPeriodEndDate",
        "PayerConceptId",
        "PayerSourceValue",
        "PayerSourceConceptId",
        "PlanConceptId",
        "PlanSourceValue",
        "PlanSourceConceptId",
        "SponsorConceptId",
        "SponsorSourceValue",
        "SponsorSourceConceptId",
        "FamilySourceValue",
        "StopReasonConceptId",
        "StopReasonSourceValue",
        "StopReasonSourceConceptId"
    };
    
    
    //
    // member variables
    //
    
    private HashMap<String, String> descriptions = new HashMap<String, String>();
    
    private Integer payerPlanPeriodId;
    
    private Integer personId;
    
    private Date payerPlanPeriodStartDate;
    
    private Date payerPlanPeriodEndDate;
    
    private Integer payerConceptId;
    
    private String payerSourceValue;
    
    private Integer payerSourceConceptId;
    
    private Integer planConceptId;
    
    private String planSourceValue;
    
    private Integer planSourceConceptId;
    
    private Integer sponsorConceptId;
    
    private String sponsorSourceValue;
    
    private Integer sponsorSourceConceptId;
    
    private String familySourceValue;
    
    private Integer stopReasonConceptId;
    
    private String stopReasonSourceValue;
    
    private Integer stopReasonSourceConceptId;
    
    //
    // trivial getters and setters
    //
    
    // payerPlanPeriodId
    
    public void setPayerPlanPeriodId(Integer val) {
        this.payerPlanPeriodId = val;
    }
    
    public Integer getPayerPlanPeriodId() {
        return this.payerPlanPeriodId;
    }
    
    // personId
    
    public void setPersonId(Integer val) {
        this.personId = val;
    }
    
    public Integer getPersonId() {
        return this.personId;
    }
    
    // payerPlanPeriodStartDate
    
    public void setPayerPlanPeriodStartDate(Date val) {
        this.payerPlanPeriodStartDate = val;
    }
    
    public Date getPayerPlanPeriodStartDate() {
        return this.payerPlanPeriodStartDate;
    }
    
    // payerPlanPeriodEndDate
    
    public void setPayerPlanPeriodEndDate(Date val) {
        this.payerPlanPeriodEndDate = val;
    }
    
    public Date getPayerPlanPeriodEndDate() {
        return this.payerPlanPeriodEndDate;
    }
    
    // payerConceptId
    
    public void setPayerConceptId(Integer val) {
        this.payerConceptId = val;
    }
    
    public Integer getPayerConceptId() {
        return this.payerConceptId;
    }
    
    // payerSourceValue
    
    public void setPayerSourceValue(String val) {
        this.payerSourceValue = val;
    }
    
    public String getPayerSourceValue() {
        return this.payerSourceValue;
    }
    
    // payerSourceConceptId
    
    public void setPayerSourceConceptId(Integer val) {
        this.payerSourceConceptId = val;
    }
    
    public Integer getPayerSourceConceptId() {
        return this.payerSourceConceptId;
    }
    
    // planConceptId
    
    public void setPlanConceptId(Integer val) {
        this.planConceptId = val;
    }
    
    public Integer getPlanConceptId() {
        return this.planConceptId;
    }
    
    // planSourceValue
    
    public void setPlanSourceValue(String val) {
        this.planSourceValue = val;
    }
    
    public String getPlanSourceValue() {
        return this.planSourceValue;
    }
    
    // planSourceConceptId
    
    public void setPlanSourceConceptId(Integer val) {
        this.planSourceConceptId = val;
    }
    
    public Integer getPlanSourceConceptId() {
        return this.planSourceConceptId;
    }
    
    // sponsorConceptId
    
    public void setSponsorConceptId(Integer val) {
        this.sponsorConceptId = val;
    }
    
    public Integer getSponsorConceptId() {
        return this.sponsorConceptId;
    }
    
    // sponsorSourceValue
    
    public void setSponsorSourceValue(String val) {
        this.sponsorSourceValue = val;
    }
    
    public String getSponsorSourceValue() {
        return this.sponsorSourceValue;
    }
    
    // sponsorSourceConceptId
    
    public void setSponsorSourceConceptId(Integer val) {
        this.sponsorSourceConceptId = val;
    }
    
    public Integer getSponsorSourceConceptId() {
        return this.sponsorSourceConceptId;
    }
    
    // familySourceValue
    
    public void setFamilySourceValue(String val) {
        this.familySourceValue = val;
    }
    
    public String getFamilySourceValue() {
        return this.familySourceValue;
    }
    
    // stopReasonConceptId
    
    public void setStopReasonConceptId(Integer val) {
        this.stopReasonConceptId = val;
    }
    
    public Integer getStopReasonConceptId() {
        return this.stopReasonConceptId;
    }
    
    // stopReasonSourceValue
    
    public void setStopReasonSourceValue(String val) {
        this.stopReasonSourceValue = val;
    }
    
    public String getStopReasonSourceValue() {
        return this.stopReasonSourceValue;
    }
    
    // stopReasonSourceConceptId
    
    public void setStopReasonSourceConceptId(Integer val) {
        this.stopReasonSourceConceptId = val;
    }
    
    public Integer getStopReasonSourceConceptId() {
        return this.stopReasonSourceConceptId;
    }
    
    //
    // implementation of Dvo
    //
    
    public String getTableName() {
        return TABLE_NAME;
    };
    
    public String getSchemaName() {
        return org.nachc.tools.fhirtoomop.util.params.AppParams.getFullySpecifiedSchemaName();
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
