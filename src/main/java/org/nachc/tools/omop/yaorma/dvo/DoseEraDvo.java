//
// Data Value Object (DVO) for dose_era
//

package org.nachc.tools.omop.yaorma.dvo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;

import org.yaorma.dvo.Dvo;

public class DoseEraDvo implements Dvo {

    //
    // tableName
    //
    
    public static final String TABLE_NAME = "dose_era";
    
    //
    // schemaName
    //
    
    public static final String SCHEMA_NAME = "synthea_omop.dbo";
    
    //
    // columnNames
    //
    
    public static final String[] COLUMN_NAMES = {
        "dose_era_end_date",
        "dose_era_id",
        "dose_era_start_date",
        "dose_value",
        "drug_concept_id",
        "person_id",
        "unit_concept_id"
    };
    
    //
    // primaryKeyColumnNames
    //
    
    public static final String[] PRIMARY_KEY_COLUMN_NAMES = {
        "dose_era_id"
    };
    
    //
    // javaNames
    //
    
    public static final String[] JAVA_NAMES = {
        "doseEraEndDate",
        "doseEraId",
        "doseEraStartDate",
        "doseValue",
        "drugConceptId",
        "personId",
        "unitConceptId"
    };
    
    //
    // javaNamesProper
    //
    
    public static final String[] JAVA_NAMES_PROPER = {
        "DoseEraEndDate",
        "DoseEraId",
        "DoseEraStartDate",
        "DoseValue",
        "DrugConceptId",
        "PersonId",
        "UnitConceptId"
    };
    
    
    //
    // member variables
    //
    
    private HashMap<String, String> descriptions = new HashMap<String, String>();
    
    private String doseEraEndDate;
    
    private Integer doseEraId;
    
    private String doseEraStartDate;
    
    private String doseValue;
    
    private Integer drugConceptId;
    
    private Integer personId;
    
    private Integer unitConceptId;
    
    private ConceptDvo drugConceptDvo;
    
    private PersonDvo personDvo;
    
    private ConceptDvo unitConceptDvo;
    
    //
    // trivial getters and setters
    //
    
    // doseEraEndDate
    
    public void setDoseEraEndDate(String val) {
        this.doseEraEndDate = val;
    }
    
    public String getDoseEraEndDate() {
        return this.doseEraEndDate;
    }
    
    // doseEraId
    
    public void setDoseEraId(Integer val) {
        this.doseEraId = val;
    }
    
    public Integer getDoseEraId() {
        return this.doseEraId;
    }
    
    // doseEraStartDate
    
    public void setDoseEraStartDate(String val) {
        this.doseEraStartDate = val;
    }
    
    public String getDoseEraStartDate() {
        return this.doseEraStartDate;
    }
    
    // doseValue
    
    public void setDoseValue(String val) {
        this.doseValue = val;
    }
    
    public String getDoseValue() {
        return this.doseValue;
    }
    
    // drugConceptId
    
    public void setDrugConceptId(Integer val) {
        this.drugConceptId = val;
    }
    
    public Integer getDrugConceptId() {
        return this.drugConceptId;
    }
    
    // personId
    
    public void setPersonId(Integer val) {
        this.personId = val;
    }
    
    public Integer getPersonId() {
        return this.personId;
    }
    
    // unitConceptId
    
    public void setUnitConceptId(Integer val) {
        this.unitConceptId = val;
    }
    
    public Integer getUnitConceptId() {
        return this.unitConceptId;
    }
    
    // drugConceptDvo
    
    public void setDrugConceptDvo(ConceptDvo dvo) {
        this.drugConceptDvo = dvo;
    }
    
    public ConceptDvo getDrugConceptDvo() {
        return this.drugConceptDvo;
    }
    
    // personDvo
    
    public void setPersonDvo(PersonDvo dvo) {
        this.personDvo = dvo;
    }
    
    public PersonDvo getPersonDvo() {
        return this.personDvo;
    }
    
    // unitConceptDvo
    
    public void setUnitConceptDvo(ConceptDvo dvo) {
        this.unitConceptDvo = dvo;
    }
    
    public ConceptDvo getUnitConceptDvo() {
        return this.unitConceptDvo;
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
            getDoseEraId()  == null ? null: getDoseEraId() + ""
        };
        return rtn;
    }
}
