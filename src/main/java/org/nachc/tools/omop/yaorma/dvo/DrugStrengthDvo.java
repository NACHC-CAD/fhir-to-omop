//
// Data Value Object (DVO) for DRUG_STRENGTH
//

package org.nachc.tools.omop.yaorma.dvo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;
import java.math.BigDecimal;

import org.yaorma.dvo.Dvo;

public class DrugStrengthDvo implements Dvo {

    //
    // tableName
    //
    
    public static final String TABLE_NAME = "DRUG_STRENGTH";
    
    //
    // schemaName
    //
    
    public static final String SCHEMA_NAME = "cdm_f2o_build";
    
    //
    // columnNames
    //
    
    public static final String[] COLUMN_NAMES = {
        "drug_concept_id",
        "ingredient_concept_id",
        "amount_value",
        "amount_unit_concept_id",
        "numerator_value",
        "numerator_unit_concept_id",
        "denominator_value",
        "denominator_unit_concept_id",
        "box_size",
        "valid_start_date",
        "valid_end_date",
        "invalid_reason"
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
        "drugConceptId",
        "ingredientConceptId",
        "amountValue",
        "amountUnitConceptId",
        "numeratorValue",
        "numeratorUnitConceptId",
        "denominatorValue",
        "denominatorUnitConceptId",
        "boxSize",
        "validStartDate",
        "validEndDate",
        "invalidReason"
    };
    
    //
    // javaNamesProper
    //
    
    public static final String[] JAVA_NAMES_PROPER = {
        "DrugConceptId",
        "IngredientConceptId",
        "AmountValue",
        "AmountUnitConceptId",
        "NumeratorValue",
        "NumeratorUnitConceptId",
        "DenominatorValue",
        "DenominatorUnitConceptId",
        "BoxSize",
        "ValidStartDate",
        "ValidEndDate",
        "InvalidReason"
    };
    
    
    //
    // member variables
    //
    
    private HashMap<String, String> descriptions = new HashMap<String, String>();
    
    private Integer drugConceptId;
    
    private Integer ingredientConceptId;
    
    private BigDecimal amountValue;
    
    private Integer amountUnitConceptId;
    
    private BigDecimal numeratorValue;
    
    private Integer numeratorUnitConceptId;
    
    private BigDecimal denominatorValue;
    
    private Integer denominatorUnitConceptId;
    
    private Integer boxSize;
    
    private Date validStartDate;
    
    private Date validEndDate;
    
    private String invalidReason;
    
    //
    // trivial getters and setters
    //
    
    // drugConceptId
    
    public void setDrugConceptId(Integer val) {
        this.drugConceptId = val;
    }
    
    public Integer getDrugConceptId() {
        return this.drugConceptId;
    }
    
    // ingredientConceptId
    
    public void setIngredientConceptId(Integer val) {
        this.ingredientConceptId = val;
    }
    
    public Integer getIngredientConceptId() {
        return this.ingredientConceptId;
    }
    
    // amountValue
    
    public void setAmountValue(BigDecimal val) {
        this.amountValue = val;
    }
    
    public BigDecimal getAmountValue() {
        return this.amountValue;
    }
    
    // amountUnitConceptId
    
    public void setAmountUnitConceptId(Integer val) {
        this.amountUnitConceptId = val;
    }
    
    public Integer getAmountUnitConceptId() {
        return this.amountUnitConceptId;
    }
    
    // numeratorValue
    
    public void setNumeratorValue(BigDecimal val) {
        this.numeratorValue = val;
    }
    
    public BigDecimal getNumeratorValue() {
        return this.numeratorValue;
    }
    
    // numeratorUnitConceptId
    
    public void setNumeratorUnitConceptId(Integer val) {
        this.numeratorUnitConceptId = val;
    }
    
    public Integer getNumeratorUnitConceptId() {
        return this.numeratorUnitConceptId;
    }
    
    // denominatorValue
    
    public void setDenominatorValue(BigDecimal val) {
        this.denominatorValue = val;
    }
    
    public BigDecimal getDenominatorValue() {
        return this.denominatorValue;
    }
    
    // denominatorUnitConceptId
    
    public void setDenominatorUnitConceptId(Integer val) {
        this.denominatorUnitConceptId = val;
    }
    
    public Integer getDenominatorUnitConceptId() {
        return this.denominatorUnitConceptId;
    }
    
    // boxSize
    
    public void setBoxSize(Integer val) {
        this.boxSize = val;
    }
    
    public Integer getBoxSize() {
        return this.boxSize;
    }
    
    // validStartDate
    
    public void setValidStartDate(Date val) {
        this.validStartDate = val;
    }
    
    public Date getValidStartDate() {
        return this.validStartDate;
    }
    
    // validEndDate
    
    public void setValidEndDate(Date val) {
        this.validEndDate = val;
    }
    
    public Date getValidEndDate() {
        return this.validEndDate;
    }
    
    // invalidReason
    
    public void setInvalidReason(String val) {
        this.invalidReason = val;
    }
    
    public String getInvalidReason() {
        return this.invalidReason;
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
