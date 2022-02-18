//
// Data Value Object (DVO) for drug_strength
//

package org.nachc.tools.omop.yaorma.dvo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;

import org.yaorma.dvo.Dvo;

public class DrugStrengthDvo implements Dvo {

    //
    // tableName
    //
    
    public static final String TABLE_NAME = "drug_strength";
    
    //
    // schemaName
    //
    
    public static final String SCHEMA_NAME = "synthea_omop.dbo";
    
    //
    // columnNames
    //
    
    public static final String[] COLUMN_NAMES = {
        "amount_unit_concept_id",
        "amount_value",
        "box_size",
        "denominator_unit_concept_id",
        "denominator_value",
        "drug_concept_id",
        "ingredient_concept_id",
        "invalid_reason",
        "numerator_unit_concept_id",
        "numerator_value",
        "valid_end_date",
        "valid_start_date"
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
        "amountUnitConceptId",
        "amountValue",
        "boxSize",
        "denominatorUnitConceptId",
        "denominatorValue",
        "drugConceptId",
        "ingredientConceptId",
        "invalidReason",
        "numeratorUnitConceptId",
        "numeratorValue",
        "validEndDate",
        "validStartDate"
    };
    
    //
    // javaNamesProper
    //
    
    public static final String[] JAVA_NAMES_PROPER = {
        "AmountUnitConceptId",
        "AmountValue",
        "BoxSize",
        "DenominatorUnitConceptId",
        "DenominatorValue",
        "DrugConceptId",
        "IngredientConceptId",
        "InvalidReason",
        "NumeratorUnitConceptId",
        "NumeratorValue",
        "ValidEndDate",
        "ValidStartDate"
    };
    
    
    //
    // member variables
    //
    
    private HashMap<String, String> descriptions = new HashMap<String, String>();
    
    private Integer amountUnitConceptId;
    
    private String amountValue;
    
    private Integer boxSize;
    
    private Integer denominatorUnitConceptId;
    
    private String denominatorValue;
    
    private Integer drugConceptId;
    
    private Integer ingredientConceptId;
    
    private String invalidReason;
    
    private Integer numeratorUnitConceptId;
    
    private String numeratorValue;
    
    private Date validEndDate;
    
    private Date validStartDate;
    
    private ConceptDvo amountUnitConceptDvo;
    
    private ConceptDvo denominatorUnitConceptDvo;
    
    private ConceptDvo drugConceptDvo;
    
    private ConceptDvo ingredientConceptDvo;
    
    private ConceptDvo numeratorUnitConceptDvo;
    
    //
    // trivial getters and setters
    //
    
    // amountUnitConceptId
    
    public void setAmountUnitConceptId(Integer val) {
        this.amountUnitConceptId = val;
    }
    
    public Integer getAmountUnitConceptId() {
        return this.amountUnitConceptId;
    }
    
    // amountValue
    
    public void setAmountValue(String val) {
        this.amountValue = val;
    }
    
    public String getAmountValue() {
        return this.amountValue;
    }
    
    // boxSize
    
    public void setBoxSize(Integer val) {
        this.boxSize = val;
    }
    
    public Integer getBoxSize() {
        return this.boxSize;
    }
    
    // denominatorUnitConceptId
    
    public void setDenominatorUnitConceptId(Integer val) {
        this.denominatorUnitConceptId = val;
    }
    
    public Integer getDenominatorUnitConceptId() {
        return this.denominatorUnitConceptId;
    }
    
    // denominatorValue
    
    public void setDenominatorValue(String val) {
        this.denominatorValue = val;
    }
    
    public String getDenominatorValue() {
        return this.denominatorValue;
    }
    
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
    
    // invalidReason
    
    public void setInvalidReason(String val) {
        this.invalidReason = val;
    }
    
    public String getInvalidReason() {
        return this.invalidReason;
    }
    
    // numeratorUnitConceptId
    
    public void setNumeratorUnitConceptId(Integer val) {
        this.numeratorUnitConceptId = val;
    }
    
    public Integer getNumeratorUnitConceptId() {
        return this.numeratorUnitConceptId;
    }
    
    // numeratorValue
    
    public void setNumeratorValue(String val) {
        this.numeratorValue = val;
    }
    
    public String getNumeratorValue() {
        return this.numeratorValue;
    }
    
    // validEndDate
    
    public void setValidEndDate(Date val) {
        this.validEndDate = val;
    }
    
    public Date getValidEndDate() {
        return this.validEndDate;
    }
    
    // validStartDate
    
    public void setValidStartDate(Date val) {
        this.validStartDate = val;
    }
    
    public Date getValidStartDate() {
        return this.validStartDate;
    }
    
    // amountUnitConceptDvo
    
    public void setAmountUnitConceptDvo(ConceptDvo dvo) {
        this.amountUnitConceptDvo = dvo;
    }
    
    public ConceptDvo getAmountUnitConceptDvo() {
        return this.amountUnitConceptDvo;
    }
    
    // denominatorUnitConceptDvo
    
    public void setDenominatorUnitConceptDvo(ConceptDvo dvo) {
        this.denominatorUnitConceptDvo = dvo;
    }
    
    public ConceptDvo getDenominatorUnitConceptDvo() {
        return this.denominatorUnitConceptDvo;
    }
    
    // drugConceptDvo
    
    public void setDrugConceptDvo(ConceptDvo dvo) {
        this.drugConceptDvo = dvo;
    }
    
    public ConceptDvo getDrugConceptDvo() {
        return this.drugConceptDvo;
    }
    
    // ingredientConceptDvo
    
    public void setIngredientConceptDvo(ConceptDvo dvo) {
        this.ingredientConceptDvo = dvo;
    }
    
    public ConceptDvo getIngredientConceptDvo() {
        return this.ingredientConceptDvo;
    }
    
    // numeratorUnitConceptDvo
    
    public void setNumeratorUnitConceptDvo(ConceptDvo dvo) {
        this.numeratorUnitConceptDvo = dvo;
    }
    
    public ConceptDvo getNumeratorUnitConceptDvo() {
        return this.numeratorUnitConceptDvo;
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
