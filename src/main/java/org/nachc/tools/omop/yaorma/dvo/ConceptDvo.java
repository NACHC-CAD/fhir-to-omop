//
// Data Value Object (DVO) for CONCEPT
//

package org.nachc.tools.omop.yaorma.dvo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;
import java.math.BigDecimal;

import org.yaorma.dvo.Dvo;

public class ConceptDvo implements Dvo {

    //
    // tableName
    //
    
    public static final String TABLE_NAME = "CONCEPT";
    
    //
    // schemaName
    //
    
    public static final String SCHEMA_NAME = "etl_synthea_1k";
    
    //
    // columnNames
    //
    
    public static final String[] COLUMN_NAMES = {
        "concept_id",
        "concept_name",
        "domain_id",
        "vocabulary_id",
        "concept_class_id",
        "standard_concept",
        "concept_code",
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
        "conceptId",
        "conceptName",
        "domainId",
        "vocabularyId",
        "conceptClassId",
        "standardConcept",
        "conceptCode",
        "validStartDate",
        "validEndDate",
        "invalidReason"
    };
    
    //
    // javaNamesProper
    //
    
    public static final String[] JAVA_NAMES_PROPER = {
        "ConceptId",
        "ConceptName",
        "DomainId",
        "VocabularyId",
        "ConceptClassId",
        "StandardConcept",
        "ConceptCode",
        "ValidStartDate",
        "ValidEndDate",
        "InvalidReason"
    };
    
    
    //
    // member variables
    //
    
    private HashMap<String, String> descriptions = new HashMap<String, String>();
    
    private Integer conceptId;
    
    private String conceptName;
    
    private String domainId;
    
    private String vocabularyId;
    
    private String conceptClassId;
    
    private String standardConcept;
    
    private String conceptCode;
    
    private Date validStartDate;
    
    private Date validEndDate;
    
    private String invalidReason;
    
    //
    // trivial getters and setters
    //
    
    // conceptId
    
    public void setConceptId(Integer val) {
        this.conceptId = val;
    }
    
    public Integer getConceptId() {
        return this.conceptId;
    }
    
    // conceptName
    
    public void setConceptName(String val) {
        this.conceptName = val;
    }
    
    public String getConceptName() {
        return this.conceptName;
    }
    
    // domainId
    
    public void setDomainId(String val) {
        this.domainId = val;
    }
    
    public String getDomainId() {
        return this.domainId;
    }
    
    // vocabularyId
    
    public void setVocabularyId(String val) {
        this.vocabularyId = val;
    }
    
    public String getVocabularyId() {
        return this.vocabularyId;
    }
    
    // conceptClassId
    
    public void setConceptClassId(String val) {
        this.conceptClassId = val;
    }
    
    public String getConceptClassId() {
        return this.conceptClassId;
    }
    
    // standardConcept
    
    public void setStandardConcept(String val) {
        this.standardConcept = val;
    }
    
    public String getStandardConcept() {
        return this.standardConcept;
    }
    
    // conceptCode
    
    public void setConceptCode(String val) {
        this.conceptCode = val;
    }
    
    public String getConceptCode() {
        return this.conceptCode;
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
