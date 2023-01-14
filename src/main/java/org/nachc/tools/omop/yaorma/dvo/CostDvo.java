//
// Data Value Object (DVO) for COST
//

package org.nachc.tools.omop.yaorma.dvo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;

import org.yaorma.dvo.Dvo;

public class CostDvo implements Dvo {

    //
    // tableName
    //
    
    public static final String TABLE_NAME = "COST";
    
    //
    // schemaName
    //
    
    public static final String SCHEMA_NAME = "synthea_omop.dbo";
    
    //
    // columnNames
    //
    
    public static final String[] COLUMN_NAMES = {
        "cost_id",
        "cost_event_id",
        "cost_domain_id",
        "cost_type_concept_id",
        "currency_concept_id",
        "total_charge",
        "total_cost",
        "total_paid",
        "paid_by_payer",
        "paid_by_patient",
        "paid_patient_copay",
        "paid_patient_coinsurance",
        "paid_patient_deductible",
        "paid_by_primary",
        "paid_ingredient_cost",
        "paid_dispensing_fee",
        "payer_plan_period_id",
        "amount_allowed",
        "revenue_code_concept_id",
        "revenue_code_source_value",
        "drg_concept_id",
        "drg_source_value"
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
        "costId",
        "costEventId",
        "costDomainId",
        "costTypeConceptId",
        "currencyConceptId",
        "totalCharge",
        "totalCost",
        "totalPaid",
        "paidByPayer",
        "paidByPatient",
        "paidPatientCopay",
        "paidPatientCoinsurance",
        "paidPatientDeductible",
        "paidByPrimary",
        "paidIngredientCost",
        "paidDispensingFee",
        "payerPlanPeriodId",
        "amountAllowed",
        "revenueCodeConceptId",
        "revenueCodeSourceValue",
        "drgConceptId",
        "drgSourceValue"
    };
    
    //
    // javaNamesProper
    //
    
    public static final String[] JAVA_NAMES_PROPER = {
        "CostId",
        "CostEventId",
        "CostDomainId",
        "CostTypeConceptId",
        "CurrencyConceptId",
        "TotalCharge",
        "TotalCost",
        "TotalPaid",
        "PaidByPayer",
        "PaidByPatient",
        "PaidPatientCopay",
        "PaidPatientCoinsurance",
        "PaidPatientDeductible",
        "PaidByPrimary",
        "PaidIngredientCost",
        "PaidDispensingFee",
        "PayerPlanPeriodId",
        "AmountAllowed",
        "RevenueCodeConceptId",
        "RevenueCodeSourceValue",
        "DrgConceptId",
        "DrgSourceValue"
    };
    
    
    //
    // member variables
    //
    
    private HashMap<String, String> descriptions = new HashMap<String, String>();
    
    private Integer costId;
    
    private Integer costEventId;
    
    private String costDomainId;
    
    private Integer costTypeConceptId;
    
    private Integer currencyConceptId;
    
    private String totalCharge;
    
    private String totalCost;
    
    private String totalPaid;
    
    private String paidByPayer;
    
    private String paidByPatient;
    
    private String paidPatientCopay;
    
    private String paidPatientCoinsurance;
    
    private String paidPatientDeductible;
    
    private String paidByPrimary;
    
    private String paidIngredientCost;
    
    private String paidDispensingFee;
    
    private Integer payerPlanPeriodId;
    
    private String amountAllowed;
    
    private Integer revenueCodeConceptId;
    
    private String revenueCodeSourceValue;
    
    private Integer drgConceptId;
    
    private String drgSourceValue;
    
    //
    // trivial getters and setters
    //
    
    // costId
    
    public void setCostId(Integer val) {
        this.costId = val;
    }
    
    public Integer getCostId() {
        return this.costId;
    }
    
    // costEventId
    
    public void setCostEventId(Integer val) {
        this.costEventId = val;
    }
    
    public Integer getCostEventId() {
        return this.costEventId;
    }
    
    // costDomainId
    
    public void setCostDomainId(String val) {
        this.costDomainId = val;
    }
    
    public String getCostDomainId() {
        return this.costDomainId;
    }
    
    // costTypeConceptId
    
    public void setCostTypeConceptId(Integer val) {
        this.costTypeConceptId = val;
    }
    
    public Integer getCostTypeConceptId() {
        return this.costTypeConceptId;
    }
    
    // currencyConceptId
    
    public void setCurrencyConceptId(Integer val) {
        this.currencyConceptId = val;
    }
    
    public Integer getCurrencyConceptId() {
        return this.currencyConceptId;
    }
    
    // totalCharge
    
    public void setTotalCharge(String val) {
        this.totalCharge = val;
    }
    
    public String getTotalCharge() {
        return this.totalCharge;
    }
    
    // totalCost
    
    public void setTotalCost(String val) {
        this.totalCost = val;
    }
    
    public String getTotalCost() {
        return this.totalCost;
    }
    
    // totalPaid
    
    public void setTotalPaid(String val) {
        this.totalPaid = val;
    }
    
    public String getTotalPaid() {
        return this.totalPaid;
    }
    
    // paidByPayer
    
    public void setPaidByPayer(String val) {
        this.paidByPayer = val;
    }
    
    public String getPaidByPayer() {
        return this.paidByPayer;
    }
    
    // paidByPatient
    
    public void setPaidByPatient(String val) {
        this.paidByPatient = val;
    }
    
    public String getPaidByPatient() {
        return this.paidByPatient;
    }
    
    // paidPatientCopay
    
    public void setPaidPatientCopay(String val) {
        this.paidPatientCopay = val;
    }
    
    public String getPaidPatientCopay() {
        return this.paidPatientCopay;
    }
    
    // paidPatientCoinsurance
    
    public void setPaidPatientCoinsurance(String val) {
        this.paidPatientCoinsurance = val;
    }
    
    public String getPaidPatientCoinsurance() {
        return this.paidPatientCoinsurance;
    }
    
    // paidPatientDeductible
    
    public void setPaidPatientDeductible(String val) {
        this.paidPatientDeductible = val;
    }
    
    public String getPaidPatientDeductible() {
        return this.paidPatientDeductible;
    }
    
    // paidByPrimary
    
    public void setPaidByPrimary(String val) {
        this.paidByPrimary = val;
    }
    
    public String getPaidByPrimary() {
        return this.paidByPrimary;
    }
    
    // paidIngredientCost
    
    public void setPaidIngredientCost(String val) {
        this.paidIngredientCost = val;
    }
    
    public String getPaidIngredientCost() {
        return this.paidIngredientCost;
    }
    
    // paidDispensingFee
    
    public void setPaidDispensingFee(String val) {
        this.paidDispensingFee = val;
    }
    
    public String getPaidDispensingFee() {
        return this.paidDispensingFee;
    }
    
    // payerPlanPeriodId
    
    public void setPayerPlanPeriodId(Integer val) {
        this.payerPlanPeriodId = val;
    }
    
    public Integer getPayerPlanPeriodId() {
        return this.payerPlanPeriodId;
    }
    
    // amountAllowed
    
    public void setAmountAllowed(String val) {
        this.amountAllowed = val;
    }
    
    public String getAmountAllowed() {
        return this.amountAllowed;
    }
    
    // revenueCodeConceptId
    
    public void setRevenueCodeConceptId(Integer val) {
        this.revenueCodeConceptId = val;
    }
    
    public Integer getRevenueCodeConceptId() {
        return this.revenueCodeConceptId;
    }
    
    // revenueCodeSourceValue
    
    public void setRevenueCodeSourceValue(String val) {
        this.revenueCodeSourceValue = val;
    }
    
    public String getRevenueCodeSourceValue() {
        return this.revenueCodeSourceValue;
    }
    
    // drgConceptId
    
    public void setDrgConceptId(Integer val) {
        this.drgConceptId = val;
    }
    
    public Integer getDrgConceptId() {
        return this.drgConceptId;
    }
    
    // drgSourceValue
    
    public void setDrgSourceValue(String val) {
        this.drgSourceValue = val;
    }
    
    public String getDrgSourceValue() {
        return this.drgSourceValue;
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
