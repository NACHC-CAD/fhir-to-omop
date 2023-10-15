//
// Data Value Object (DVO) for SPECIMEN
//

package org.nachc.tools.omop.yaorma.dvo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;
import java.math.BigDecimal;

import org.yaorma.dvo.Dvo;

public class SpecimenDvo implements Dvo {

    //
    // tableName
    //
    
    public static final String TABLE_NAME = "SPECIMEN";
    
    //
    // schemaName
    //
    
    public static final String SCHEMA_NAME = "etl_synthea_1k";
    
    //
    // columnNames
    //
    
    public static final String[] COLUMN_NAMES = {
        "specimen_id",
        "person_id",
        "specimen_concept_id",
        "specimen_type_concept_id",
        "specimen_date",
        "specimen_datetime",
        "quantity",
        "unit_concept_id",
        "anatomic_site_concept_id",
        "disease_status_concept_id",
        "specimen_source_id",
        "specimen_source_value",
        "unit_source_value",
        "anatomic_site_source_value",
        "disease_status_source_value"
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
        "specimenId",
        "personId",
        "specimenConceptId",
        "specimenTypeConceptId",
        "specimenDate",
        "specimenDatetime",
        "quantity",
        "unitConceptId",
        "anatomicSiteConceptId",
        "diseaseStatusConceptId",
        "specimenSourceId",
        "specimenSourceValue",
        "unitSourceValue",
        "anatomicSiteSourceValue",
        "diseaseStatusSourceValue"
    };
    
    //
    // javaNamesProper
    //
    
    public static final String[] JAVA_NAMES_PROPER = {
        "SpecimenId",
        "PersonId",
        "SpecimenConceptId",
        "SpecimenTypeConceptId",
        "SpecimenDate",
        "SpecimenDatetime",
        "Quantity",
        "UnitConceptId",
        "AnatomicSiteConceptId",
        "DiseaseStatusConceptId",
        "SpecimenSourceId",
        "SpecimenSourceValue",
        "UnitSourceValue",
        "AnatomicSiteSourceValue",
        "DiseaseStatusSourceValue"
    };
    
    
    //
    // member variables
    //
    
    private HashMap<String, String> descriptions = new HashMap<String, String>();
    
    private Integer specimenId;
    
    private Integer personId;
    
    private Integer specimenConceptId;
    
    private Integer specimenTypeConceptId;
    
    private Date specimenDate;
    
    private Date specimenDatetime;
    
    private BigDecimal quantity;
    
    private Integer unitConceptId;
    
    private Integer anatomicSiteConceptId;
    
    private Integer diseaseStatusConceptId;
    
    private String specimenSourceId;
    
    private String specimenSourceValue;
    
    private String unitSourceValue;
    
    private String anatomicSiteSourceValue;
    
    private String diseaseStatusSourceValue;
    
    //
    // trivial getters and setters
    //
    
    // specimenId
    
    public void setSpecimenId(Integer val) {
        this.specimenId = val;
    }
    
    public Integer getSpecimenId() {
        return this.specimenId;
    }
    
    // personId
    
    public void setPersonId(Integer val) {
        this.personId = val;
    }
    
    public Integer getPersonId() {
        return this.personId;
    }
    
    // specimenConceptId
    
    public void setSpecimenConceptId(Integer val) {
        this.specimenConceptId = val;
    }
    
    public Integer getSpecimenConceptId() {
        return this.specimenConceptId;
    }
    
    // specimenTypeConceptId
    
    public void setSpecimenTypeConceptId(Integer val) {
        this.specimenTypeConceptId = val;
    }
    
    public Integer getSpecimenTypeConceptId() {
        return this.specimenTypeConceptId;
    }
    
    // specimenDate
    
    public void setSpecimenDate(Date val) {
        this.specimenDate = val;
    }
    
    public Date getSpecimenDate() {
        return this.specimenDate;
    }
    
    // specimenDatetime
    
    public void setSpecimenDatetime(Date val) {
        this.specimenDatetime = val;
    }
    
    public Date getSpecimenDatetime() {
        return this.specimenDatetime;
    }
    
    // quantity
    
    public void setQuantity(BigDecimal val) {
        this.quantity = val;
    }
    
    public BigDecimal getQuantity() {
        return this.quantity;
    }
    
    // unitConceptId
    
    public void setUnitConceptId(Integer val) {
        this.unitConceptId = val;
    }
    
    public Integer getUnitConceptId() {
        return this.unitConceptId;
    }
    
    // anatomicSiteConceptId
    
    public void setAnatomicSiteConceptId(Integer val) {
        this.anatomicSiteConceptId = val;
    }
    
    public Integer getAnatomicSiteConceptId() {
        return this.anatomicSiteConceptId;
    }
    
    // diseaseStatusConceptId
    
    public void setDiseaseStatusConceptId(Integer val) {
        this.diseaseStatusConceptId = val;
    }
    
    public Integer getDiseaseStatusConceptId() {
        return this.diseaseStatusConceptId;
    }
    
    // specimenSourceId
    
    public void setSpecimenSourceId(String val) {
        this.specimenSourceId = val;
    }
    
    public String getSpecimenSourceId() {
        return this.specimenSourceId;
    }
    
    // specimenSourceValue
    
    public void setSpecimenSourceValue(String val) {
        this.specimenSourceValue = val;
    }
    
    public String getSpecimenSourceValue() {
        return this.specimenSourceValue;
    }
    
    // unitSourceValue
    
    public void setUnitSourceValue(String val) {
        this.unitSourceValue = val;
    }
    
    public String getUnitSourceValue() {
        return this.unitSourceValue;
    }
    
    // anatomicSiteSourceValue
    
    public void setAnatomicSiteSourceValue(String val) {
        this.anatomicSiteSourceValue = val;
    }
    
    public String getAnatomicSiteSourceValue() {
        return this.anatomicSiteSourceValue;
    }
    
    // diseaseStatusSourceValue
    
    public void setDiseaseStatusSourceValue(String val) {
        this.diseaseStatusSourceValue = val;
    }
    
    public String getDiseaseStatusSourceValue() {
        return this.diseaseStatusSourceValue;
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
