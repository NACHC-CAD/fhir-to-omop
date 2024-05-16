//
// Data Value Object (DVO) for SOURCE_TO_CONCEPT_MAP
//

package org.nachc.tools.omop.yaorma.dvo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;
import java.math.BigDecimal;

import org.yaorma.dvo.Dvo;

public class SourceToConceptMapDvo implements Dvo {

    //
    // tableName
    //
    
    public static final String TABLE_NAME = "SOURCE_TO_CONCEPT_MAP";
    
    //
    // schemaName
    //
    
    public static final String SCHEMA_NAME = "synthea_micro.dbo";
    
    //
    // columnNames
    //
    
    public static final String[] COLUMN_NAMES = {
        "source_code",
        "source_concept_id",
        "source_vocabulary_id",
        "source_code_description",
        "target_concept_id",
        "target_vocabulary_id",
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
        "sourceCode",
        "sourceConceptId",
        "sourceVocabularyId",
        "sourceCodeDescription",
        "targetConceptId",
        "targetVocabularyId",
        "validStartDate",
        "validEndDate",
        "invalidReason"
    };
    
    //
    // javaNamesProper
    //
    
    public static final String[] JAVA_NAMES_PROPER = {
        "SourceCode",
        "SourceConceptId",
        "SourceVocabularyId",
        "SourceCodeDescription",
        "TargetConceptId",
        "TargetVocabularyId",
        "ValidStartDate",
        "ValidEndDate",
        "InvalidReason"
    };
    
    
    //
    // member variables
    //
    
    private HashMap<String, String> descriptions = new HashMap<String, String>();
    
    private String sourceCode;
    
    private Integer sourceConceptId;
    
    private String sourceVocabularyId;
    
    private String sourceCodeDescription;
    
    private Integer targetConceptId;
    
    private String targetVocabularyId;
    
    private Date validStartDate;
    
    private Date validEndDate;
    
    private String invalidReason;
    
    //
    // trivial getters and setters
    //
    
    // sourceCode
    
    public void setSourceCode(String val) {
        this.sourceCode = val;
    }
    
    public String getSourceCode() {
        return this.sourceCode;
    }
    
    // sourceConceptId
    
    public void setSourceConceptId(Integer val) {
        this.sourceConceptId = val;
    }
    
    public Integer getSourceConceptId() {
        return this.sourceConceptId;
    }
    
    // sourceVocabularyId
    
    public void setSourceVocabularyId(String val) {
        this.sourceVocabularyId = val;
    }
    
    public String getSourceVocabularyId() {
        return this.sourceVocabularyId;
    }
    
    // sourceCodeDescription
    
    public void setSourceCodeDescription(String val) {
        this.sourceCodeDescription = val;
    }
    
    public String getSourceCodeDescription() {
        return this.sourceCodeDescription;
    }
    
    // targetConceptId
    
    public void setTargetConceptId(Integer val) {
        this.targetConceptId = val;
    }
    
    public Integer getTargetConceptId() {
        return this.targetConceptId;
    }
    
    // targetVocabularyId
    
    public void setTargetVocabularyId(String val) {
        this.targetVocabularyId = val;
    }
    
    public String getTargetVocabularyId() {
        return this.targetVocabularyId;
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
