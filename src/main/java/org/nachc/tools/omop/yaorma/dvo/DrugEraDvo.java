//
// Data Value Object (DVO) for DRUG_ERA
//

package org.nachc.tools.omop.yaorma.dvo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;
import java.math.BigDecimal;

import org.yaorma.dvo.Dvo;

public class DrugEraDvo implements Dvo {

    //
    // tableName
    //
    
    public static final String TABLE_NAME = "DRUG_ERA";
    
    //
    // schemaName
    //
    
    public static final String SCHEMA_NAME = "etl_synthea_1k";
    
    //
    // columnNames
    //
    
    public static final String[] COLUMN_NAMES = {
        "drug_era_id",
        "person_id",
        "drug_concept_id",
        "drug_era_start_date",
        "drug_era_end_date",
        "drug_exposure_count",
        "gap_days"
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
        "drugEraId",
        "personId",
        "drugConceptId",
        "drugEraStartDate",
        "drugEraEndDate",
        "drugExposureCount",
        "gapDays"
    };
    
    //
    // javaNamesProper
    //
    
    public static final String[] JAVA_NAMES_PROPER = {
        "DrugEraId",
        "PersonId",
        "DrugConceptId",
        "DrugEraStartDate",
        "DrugEraEndDate",
        "DrugExposureCount",
        "GapDays"
    };
    
    
    //
    // member variables
    //
    
    private HashMap<String, String> descriptions = new HashMap<String, String>();
    
    private Integer drugEraId;
    
    private Integer personId;
    
    private Integer drugConceptId;
    
    private Date drugEraStartDate;
    
    private Date drugEraEndDate;
    
    private Integer drugExposureCount;
    
    private Integer gapDays;
    
    //
    // trivial getters and setters
    //
    
    // drugEraId
    
    public void setDrugEraId(Integer val) {
        this.drugEraId = val;
    }
    
    public Integer getDrugEraId() {
        return this.drugEraId;
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
    
    // drugEraStartDate
    
    public void setDrugEraStartDate(Date val) {
        this.drugEraStartDate = val;
    }
    
    public Date getDrugEraStartDate() {
        return this.drugEraStartDate;
    }
    
    // drugEraEndDate
    
    public void setDrugEraEndDate(Date val) {
        this.drugEraEndDate = val;
    }
    
    public Date getDrugEraEndDate() {
        return this.drugEraEndDate;
    }
    
    // drugExposureCount
    
    public void setDrugExposureCount(Integer val) {
        this.drugExposureCount = val;
    }
    
    public Integer getDrugExposureCount() {
        return this.drugExposureCount;
    }
    
    // gapDays
    
    public void setGapDays(Integer val) {
        this.gapDays = val;
    }
    
    public Integer getGapDays() {
        return this.gapDays;
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
