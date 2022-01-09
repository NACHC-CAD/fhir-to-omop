//
// Data Value Object (DVO) for specimen
//

package org.nachc.tools.omop.yaorma.dvo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;

import org.yaorma.dvo.Dvo;

public class SpecimenDvo implements Dvo {

    //
    // tableName
    //
    
    public static final String TABLE_NAME = "specimen";
    
    //
    // schemaName
    //
    
    public static final String SCHEMA_NAME = "synthea_omop";
    
    //
    // columnNames
    //
    
    public static final String[] COLUMN_NAMES = {
        "anatomic_site_concept_id",
        "anatomic_site_source_value",
        "disease_status_concept_id",
        "disease_status_source_value",
        "person_id",
        "quantity",
        "specimen_concept_id",
        "specimen_date",
        "specimen_datetime",
        "specimen_id",
        "specimen_source_id",
        "specimen_source_value",
        "specimen_type_concept_id",
        "unit_concept_id",
        "unit_source_value"
    };
    
    //
    // primaryKeyColumnNames
    //
    
    public static final String[] PRIMARY_KEY_COLUMN_NAMES = {
        "specimen_id"
    };
    
    //
    // javaNames
    //
    
    public static final String[] JAVA_NAMES = {
        "anatomicSiteConceptId",
        "anatomicSiteSourceValue",
        "diseaseStatusConceptId",
        "diseaseStatusSourceValue",
        "personId",
        "quantity",
        "specimenConceptId",
        "specimenDate",
        "specimenDatetime",
        "specimenId",
        "specimenSourceId",
        "specimenSourceValue",
        "specimenTypeConceptId",
        "unitConceptId",
        "unitSourceValue"
    };
    
    //
    // javaNamesProper
    //
    
    public static final String[] JAVA_NAMES_PROPER = {
        "AnatomicSiteConceptId",
        "AnatomicSiteSourceValue",
        "DiseaseStatusConceptId",
        "DiseaseStatusSourceValue",
        "PersonId",
        "Quantity",
        "SpecimenConceptId",
        "SpecimenDate",
        "SpecimenDatetime",
        "SpecimenId",
        "SpecimenSourceId",
        "SpecimenSourceValue",
        "SpecimenTypeConceptId",
        "UnitConceptId",
        "UnitSourceValue"
    };
    
    
    //
    // member variables
    //
    
    private HashMap<String, String> descriptions = new HashMap<String, String>();
    
    private Integer anatomicSiteConceptId;
    
    private String anatomicSiteSourceValue;
    
    private Integer diseaseStatusConceptId;
    
    private String diseaseStatusSourceValue;
    
    private Integer personId;
    
    private String quantity;
    
    private Integer specimenConceptId;
    
    private String specimenDate;
    
    private String specimenDatetime;
    
    private Integer specimenId;
    
    private String specimenSourceId;
    
    private String specimenSourceValue;
    
    private Integer specimenTypeConceptId;
    
    private Integer unitConceptId;
    
    private String unitSourceValue;
    
    private ConceptDvo anatomicSiteConceptDvo;
    
    private ConceptDvo diseaseStatusConceptDvo;
    
    private PersonDvo personDvo;
    
    private ConceptDvo specimenConceptDvo;
    
    private ConceptDvo specimenTypeConceptDvo;
    
    private ConceptDvo unitConceptDvo;
    
    //
    // trivial getters and setters
    //
    
    // anatomicSiteConceptId
    
    public void setAnatomicSiteConceptId(Integer val) {
        this.anatomicSiteConceptId = val;
    }
    
    public Integer getAnatomicSiteConceptId() {
        return this.anatomicSiteConceptId;
    }
    
    // anatomicSiteSourceValue
    
    public void setAnatomicSiteSourceValue(String val) {
        this.anatomicSiteSourceValue = val;
    }
    
    public String getAnatomicSiteSourceValue() {
        return this.anatomicSiteSourceValue;
    }
    
    // diseaseStatusConceptId
    
    public void setDiseaseStatusConceptId(Integer val) {
        this.diseaseStatusConceptId = val;
    }
    
    public Integer getDiseaseStatusConceptId() {
        return this.diseaseStatusConceptId;
    }
    
    // diseaseStatusSourceValue
    
    public void setDiseaseStatusSourceValue(String val) {
        this.diseaseStatusSourceValue = val;
    }
    
    public String getDiseaseStatusSourceValue() {
        return this.diseaseStatusSourceValue;
    }
    
    // personId
    
    public void setPersonId(Integer val) {
        this.personId = val;
    }
    
    public Integer getPersonId() {
        return this.personId;
    }
    
    // quantity
    
    public void setQuantity(String val) {
        this.quantity = val;
    }
    
    public String getQuantity() {
        return this.quantity;
    }
    
    // specimenConceptId
    
    public void setSpecimenConceptId(Integer val) {
        this.specimenConceptId = val;
    }
    
    public Integer getSpecimenConceptId() {
        return this.specimenConceptId;
    }
    
    // specimenDate
    
    public void setSpecimenDate(String val) {
        this.specimenDate = val;
    }
    
    public String getSpecimenDate() {
        return this.specimenDate;
    }
    
    // specimenDatetime
    
    public void setSpecimenDatetime(String val) {
        this.specimenDatetime = val;
    }
    
    public String getSpecimenDatetime() {
        return this.specimenDatetime;
    }
    
    // specimenId
    
    public void setSpecimenId(Integer val) {
        this.specimenId = val;
    }
    
    public Integer getSpecimenId() {
        return this.specimenId;
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
    
    // specimenTypeConceptId
    
    public void setSpecimenTypeConceptId(Integer val) {
        this.specimenTypeConceptId = val;
    }
    
    public Integer getSpecimenTypeConceptId() {
        return this.specimenTypeConceptId;
    }
    
    // unitConceptId
    
    public void setUnitConceptId(Integer val) {
        this.unitConceptId = val;
    }
    
    public Integer getUnitConceptId() {
        return this.unitConceptId;
    }
    
    // unitSourceValue
    
    public void setUnitSourceValue(String val) {
        this.unitSourceValue = val;
    }
    
    public String getUnitSourceValue() {
        return this.unitSourceValue;
    }
    
    // anatomicSiteConceptDvo
    
    public void setAnatomicSiteConceptDvo(ConceptDvo dvo) {
        this.anatomicSiteConceptDvo = dvo;
    }
    
    public ConceptDvo getAnatomicSiteConceptDvo() {
        return this.anatomicSiteConceptDvo;
    }
    
    // diseaseStatusConceptDvo
    
    public void setDiseaseStatusConceptDvo(ConceptDvo dvo) {
        this.diseaseStatusConceptDvo = dvo;
    }
    
    public ConceptDvo getDiseaseStatusConceptDvo() {
        return this.diseaseStatusConceptDvo;
    }
    
    // personDvo
    
    public void setPersonDvo(PersonDvo dvo) {
        this.personDvo = dvo;
    }
    
    public PersonDvo getPersonDvo() {
        return this.personDvo;
    }
    
    // specimenConceptDvo
    
    public void setSpecimenConceptDvo(ConceptDvo dvo) {
        this.specimenConceptDvo = dvo;
    }
    
    public ConceptDvo getSpecimenConceptDvo() {
        return this.specimenConceptDvo;
    }
    
    // specimenTypeConceptDvo
    
    public void setSpecimenTypeConceptDvo(ConceptDvo dvo) {
        this.specimenTypeConceptDvo = dvo;
    }
    
    public ConceptDvo getSpecimenTypeConceptDvo() {
        return this.specimenTypeConceptDvo;
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
            getSpecimenId()  == null ? null: getSpecimenId() + ""
        };
        return rtn;
    }
}
