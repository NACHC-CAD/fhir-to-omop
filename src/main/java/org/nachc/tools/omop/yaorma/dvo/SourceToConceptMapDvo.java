//
// Data Value Object (DVO) for source_to_concept_map
//

package org.nachc.tools.omop.yaorma.dvo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;

import org.yaorma.dvo.Dvo;

public class SourceToConceptMapDvo implements Dvo {

    //
    // tableName
    //
    
    public static final String TABLE_NAME = "source_to_concept_map";
    
    //
    // schemaName
    //
    
    public static final String SCHEMA_NAME = "synthea_omop";
    
    //
    // columnNames
    //
    
    public static final String[] COLUMN_NAMES = {
        "invalid_reason",
        "source_code",
        "source_code_description",
        "source_concept_id",
        "source_vocabulary_id",
        "target_concept_id",
        "target_vocabulary_id",
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
        "invalidReason",
        "sourceCode",
        "sourceCodeDescription",
        "sourceConceptId",
        "sourceVocabularyId",
        "targetConceptId",
        "targetVocabularyId",
        "validEndDate",
        "validStartDate"
    };
    
    //
    // javaNamesProper
    //
    
    public static final String[] JAVA_NAMES_PROPER = {
        "InvalidReason",
        "SourceCode",
        "SourceCodeDescription",
        "SourceConceptId",
        "SourceVocabularyId",
        "TargetConceptId",
        "TargetVocabularyId",
        "ValidEndDate",
        "ValidStartDate"
    };
    
    
    //
    // member variables
    //
    
    private HashMap<String, String> descriptions = new HashMap<String, String>();
    
    private String invalidReason;
    
    private String sourceCode;
    
    private String sourceCodeDescription;
    
    private Integer sourceConceptId;
    
    private String sourceVocabularyId;
    
    private Integer targetConceptId;
    
    private String targetVocabularyId;
    
    private Date validEndDate;
    
    private Date validStartDate;
    
    private ConceptDvo sourceConceptDvo;
    
    private ConceptDvo targetConceptDvo;
    
    private VocabularyDvo targetVocabularyDvo;
    
    //
    // trivial getters and setters
    //
    
    // invalidReason
    
    public void setInvalidReason(String val) {
        this.invalidReason = val;
    }
    
    public String getInvalidReason() {
        return this.invalidReason;
    }
    
    // sourceCode
    
    public void setSourceCode(String val) {
        this.sourceCode = val;
    }
    
    public String getSourceCode() {
        return this.sourceCode;
    }
    
    // sourceCodeDescription
    
    public void setSourceCodeDescription(String val) {
        this.sourceCodeDescription = val;
    }
    
    public String getSourceCodeDescription() {
        return this.sourceCodeDescription;
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
    
    // sourceConceptDvo
    
    public void setSourceConceptDvo(ConceptDvo dvo) {
        this.sourceConceptDvo = dvo;
    }
    
    public ConceptDvo getSourceConceptDvo() {
        return this.sourceConceptDvo;
    }
    
    // targetConceptDvo
    
    public void setTargetConceptDvo(ConceptDvo dvo) {
        this.targetConceptDvo = dvo;
    }
    
    public ConceptDvo getTargetConceptDvo() {
        return this.targetConceptDvo;
    }
    
    // targetVocabularyDvo
    
    public void setTargetVocabularyDvo(VocabularyDvo dvo) {
        this.targetVocabularyDvo = dvo;
    }
    
    public VocabularyDvo getTargetVocabularyDvo() {
        return this.targetVocabularyDvo;
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
