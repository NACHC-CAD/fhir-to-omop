//
// Data Value Object (DVO) for cost
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
    
    public static final String TABLE_NAME = "cost";
    
    //
    // schemaName
    //
    
    public static final String SCHEMA_NAME = "synthea_omop.dbo";
    
    //
    // columnNames
    //
    
    public static final String[] COLUMN_NAMES = {
        "amount_allowed",
        "cost_domain_id",
        "cost_event_id",
        "cost_id",
        "cost_type_concept_id",
        "currency_concept_id",
        "drg_concept_id",
        "drg_source_value",
        "paid_by_patient",
        "paid_by_payer",
        "paid_by_primary",
        "paid_dispensing_fee",
        "paid_ingredient_cost",
        "paid_patient_coinsurance",
        "paid_patient_copay",
        "paid_patient_deductible",
        "payer_plan_period_id",
        "revenue_code_concept_id",
        "revenue_code_source_value",
        "total_charge",
        "total_cost",
        "total_paid"
    };
    
    //
    // primaryKeyColumnNames
    //
    
    public static final String[] PRIMARY_KEY_COLUMN_NAMES = {
        "cost_id"
    };
    
    //
    // javaNames
    //
    
    public static final String[] JAVA_NAMES = {
        "amountAllowed",
        "costDomainId",
        "costEventId",
        "costId",
        "costTypeConceptId",
        "currencyConceptId",
        "drgConceptId",
        "drgSourceValue",
        "paidByPatient",
        "paidByPayer",
        "paidByPrimary",
        "paidDispensingFee",
        "paidIngredientCost",
        "paidPatientCoinsurance",
        "paidPatientCopay",
        "paidPatientDeductible",
        "payerPlanPeriodId",
        "revenueCodeConceptId",
        "revenueCodeSourceValue",
        "totalCharge",
        "totalCost",
        "totalPaid"
    };
    
    //
    // javaNamesProper
    //
    
    public static final String[] JAVA_NAMES_PROPER = {
        "AmountAllowed",
        "CostDomainId",
        "CostEventId",
        "CostId",
        "CostTypeConceptId",
        "CurrencyConceptId",
        "DrgConceptId",
        "DrgSourceValue",
        "PaidByPatient",
        "PaidByPayer",
        "PaidByPrimary",
        "PaidDispensingFee",
        "PaidIngredientCost",
        "PaidPatientCoinsurance",
        "PaidPatientCopay",
        "PaidPatientDeductible",
        "PayerPlanPeriodId",
        "RevenueCodeConceptId",
        "RevenueCodeSourceValue",
        "TotalCharge",
        "TotalCost",
        "TotalPaid"
    };
    
    
    //
    // member variables
    //
    
    private HashMap<String, String> descriptions = new HashMap<String, String>();
    
    private String amountAllowed;
    
    private String costDomainId;
    
    private Integer costEventId;
    
    private Integer costId;
    
    private Integer costTypeConceptId;
    
    private Integer currencyConceptId;
    
    private Integer drgConceptId;
    
    private String drgSourceValue;
    
    private String paidByPatient;
    
    private String paidByPayer;
    
    private String paidByPrimary;
    
    private String paidDispensingFee;
    
    private String paidIngredientCost;
    
    private String paidPatientCoinsurance;
    
    private String paidPatientCopay;
    
    private String paidPatientDeductible;
    
    private Integer payerPlanPeriodId;
    
    private Integer revenueCodeConceptId;
    
    private String revenueCodeSourceValue;
    
    private String totalCharge;
    
    private String totalCost;
    
    private String totalPaid;
    
    private DomainDvo costDomainDvo;
    
    private ConceptDvo costTypeConceptDvo;
    
    private ConceptDvo currencyConceptDvo;
    
    private ConceptDvo drgConceptDvo;
    
    private ConceptDvo revenueCodeConceptDvo;
    
    //
    // trivial getters and setters
    //
    
    // amountAllowed
    
    public void setAmountAllowed(String val) {
        this.amountAllowed = val;
    }
    
    public String getAmountAllowed() {
        return this.amountAllowed;
    }
    
    // costDomainId
    
    public void setCostDomainId(String val) {
        this.costDomainId = val;
    }
    
    public String getCostDomainId() {
        return this.costDomainId;
    }
    
    // costEventId
    
    public void setCostEventId(Integer val) {
        this.costEventId = val;
    }
    
    public Integer getCostEventId() {
        return this.costEventId;
    }
    
    // costId
    
    public void setCostId(Integer val) {
        this.costId = val;
    }
    
    public Integer getCostId() {
        return this.costId;
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
    
    // paidByPatient
    
    public void setPaidByPatient(String val) {
        this.paidByPatient = val;
    }
    
    public String getPaidByPatient() {
        return this.paidByPatient;
    }
    
    // paidByPayer
    
    public void setPaidByPayer(String val) {
        this.paidByPayer = val;
    }
    
    public String getPaidByPayer() {
        return this.paidByPayer;
    }
    
    // paidByPrimary
    
    public void setPaidByPrimary(String val) {
        this.paidByPrimary = val;
    }
    
    public String getPaidByPrimary() {
        return this.paidByPrimary;
    }
    
    // paidDispensingFee
    
    public void setPaidDispensingFee(String val) {
        this.paidDispensingFee = val;
    }
    
    public String getPaidDispensingFee() {
        return this.paidDispensingFee;
    }
    
    // paidIngredientCost
    
    public void setPaidIngredientCost(String val) {
        this.paidIngredientCost = val;
    }
    
    public String getPaidIngredientCost() {
        return this.paidIngredientCost;
    }
    
    // paidPatientCoinsurance
    
    public void setPaidPatientCoinsurance(String val) {
        this.paidPatientCoinsurance = val;
    }
    
    public String getPaidPatientCoinsurance() {
        return this.paidPatientCoinsurance;
    }
    
    // paidPatientCopay
    
    public void setPaidPatientCopay(String val) {
        this.paidPatientCopay = val;
    }
    
    public String getPaidPatientCopay() {
        return this.paidPatientCopay;
    }
    
    // paidPatientDeductible
    
    public void setPaidPatientDeductible(String val) {
        this.paidPatientDeductible = val;
    }
    
    public String getPaidPatientDeductible() {
        return this.paidPatientDeductible;
    }
    
    // payerPlanPeriodId
    
    public void setPayerPlanPeriodId(Integer val) {
        this.payerPlanPeriodId = val;
    }
    
    public Integer getPayerPlanPeriodId() {
        return this.payerPlanPeriodId;
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
    
    // costDomainDvo
    
    public void setCostDomainDvo(DomainDvo dvo) {
        this.costDomainDvo = dvo;
    }
    
    public DomainDvo getCostDomainDvo() {
        return this.costDomainDvo;
    }
    
    // costTypeConceptDvo
    
    public void setCostTypeConceptDvo(ConceptDvo dvo) {
        this.costTypeConceptDvo = dvo;
    }
    
    public ConceptDvo getCostTypeConceptDvo() {
        return this.costTypeConceptDvo;
    }
    
    // currencyConceptDvo
    
    public void setCurrencyConceptDvo(ConceptDvo dvo) {
        this.currencyConceptDvo = dvo;
    }
    
    public ConceptDvo getCurrencyConceptDvo() {
        return this.currencyConceptDvo;
    }
    
    // drgConceptDvo
    
    public void setDrgConceptDvo(ConceptDvo dvo) {
        this.drgConceptDvo = dvo;
    }
    
    public ConceptDvo getDrgConceptDvo() {
        return this.drgConceptDvo;
    }
    
    // revenueCodeConceptDvo
    
    public void setRevenueCodeConceptDvo(ConceptDvo dvo) {
        this.revenueCodeConceptDvo = dvo;
    }
    
    public ConceptDvo getRevenueCodeConceptDvo() {
        return this.revenueCodeConceptDvo;
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
            getCostId()  == null ? null: getCostId() + ""
        };
        return rtn;
    }
}
