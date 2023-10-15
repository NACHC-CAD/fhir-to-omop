//
// Data Value Object (DVO) for DOSE_ERA
//

package org.nachc.tools.omop.yaorma.dvo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;
import java.math.BigDecimal;

import org.yaorma.dvo.Dvo;

public class DoseEraDvo implements Dvo {

    //
    // tableName
    //
    
    public static final String TABLE_NAME = "DOSE_ERA";
    
    //
    // schemaName
    //
    
    public static final String SCHEMA_NAME = "etl_synthea_1k";
    
    //
    // columnNames
    //
    
    public static final String[] COLUMN_NAMES = {
        "dose_era_id",
        "person_id",
        "drug_concept_id",
        "unit_concept_id",
        "dose_value",
        "dose_era_start_date",
        "dose_era_end_date"
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
        "doseEraId",
        "personId",
        "drugConceptId",
        "unitConceptId",
        "doseValue",
        "doseEraStartDate",
        "doseEraEndDate"
    };
    
    //
    // javaNamesProper
    //
    
    public static final String[] JAVA_NAMES_PROPER = {
        "DoseEraId",
        "PersonId",
        "DrugConceptId",
        "UnitConceptId",
        "DoseValue",
        "DoseEraStartDate",
        "DoseEraEndDate"
    };
    
    
    //
    // member variables
    //
    
    private HashMap<String, String> descriptions = new HashMap<String, String>();
    
    private Integer doseEraId;
    
    private Integer personId;
    
    private Integer drugConceptId;
    
    private Integer unitConceptId;
    
    private BigDecimal doseValue;
    
    private Date doseEraStartDate;
    
    private Date doseEraEndDate;
    
    //
    // trivial getters and setters
    //
    
    // doseEraId
    
    public void setDoseEraId(Integer val) {
        this.doseEraId = val;
    }
    
    public Integer getDoseEraId() {
        return this.doseEraId;
    }
    
    // personId
    
    public void setPersonId(Integer val) {
        this.personId = val;
    }
    
    public Integer getPersonId() {
        return this.personId;
    }
    
    // drugConceptId
    
    public void setDrugConceptId(Integer val) {
        this.drugConceptId = val;
    }
    
    public Integer getDrugConceptId() {
        return this.drugConceptId;
    }
    
    // unitConceptId
    
    public void setUnitConceptId(Integer val) {
        this.unitConceptId = val;
    }
    
    public Integer getUnitConceptId() {
        return this.unitConceptId;
    }
    
    // doseValue
    
    public void setDoseValue(BigDecimal val) {
        this.doseValue = val;
    }
    
    public BigDecimal getDoseValue() {
        return this.doseValue;
    }
    
    // doseEraStartDate
    
    public void setDoseEraStartDate(Date val) {
        this.doseEraStartDate = val;
    }
    
    public Date getDoseEraStartDate() {
        return this.doseEraStartDate;
    }
    
    // doseEraEndDate
    
    public void setDoseEraEndDate(Date val) {
        this.doseEraEndDate = val;
    }
    
    public Date getDoseEraEndDate() {
        return this.doseEraEndDate;
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
