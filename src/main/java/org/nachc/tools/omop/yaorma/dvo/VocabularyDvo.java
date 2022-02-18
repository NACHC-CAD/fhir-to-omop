//
// Data Value Object (DVO) for vocabulary
//

package org.nachc.tools.omop.yaorma.dvo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;

import org.yaorma.dvo.Dvo;

public class VocabularyDvo implements Dvo {

    //
    // tableName
    //
    
    public static final String TABLE_NAME = "vocabulary";
    
    //
    // schemaName
    //
    
    public static final String SCHEMA_NAME = "synthea_omop.dbo";
    
    //
    // columnNames
    //
    
    public static final String[] COLUMN_NAMES = {
        "vocabulary_concept_id",
        "vocabulary_id",
        "vocabulary_name",
        "vocabulary_reference",
        "vocabulary_version"
    };
    
    //
    // primaryKeyColumnNames
    //
    
    public static final String[] PRIMARY_KEY_COLUMN_NAMES = {
        "vocabulary_id"
    };
    
    //
    // javaNames
    //
    
    public static final String[] JAVA_NAMES = {
        "vocabularyConceptId",
        "vocabularyId",
        "vocabularyName",
        "vocabularyReference",
        "vocabularyVersion"
    };
    
    //
    // javaNamesProper
    //
    
    public static final String[] JAVA_NAMES_PROPER = {
        "VocabularyConceptId",
        "VocabularyId",
        "VocabularyName",
        "VocabularyReference",
        "VocabularyVersion"
    };
    
    
    //
    // member variables
    //
    
    private HashMap<String, String> descriptions = new HashMap<String, String>();
    
    private Integer vocabularyConceptId;
    
    private String vocabularyId;
    
    private String vocabularyName;
    
    private String vocabularyReference;
    
    private String vocabularyVersion;
    
    private ArrayList<SourceToConceptMapDvo> sourceToConceptMapTargetVocabularyList = new ArrayList<SourceToConceptMapDvo>();
    
    //
    // trivial getters and setters
    //
    
    // vocabularyConceptId
    
    public void setVocabularyConceptId(Integer val) {
        this.vocabularyConceptId = val;
    }
    
    public Integer getVocabularyConceptId() {
        return this.vocabularyConceptId;
    }
    
    // vocabularyId
    
    public void setVocabularyId(String val) {
        this.vocabularyId = val;
    }
    
    public String getVocabularyId() {
        return this.vocabularyId;
    }
    
    // vocabularyName
    
    public void setVocabularyName(String val) {
        this.vocabularyName = val;
    }
    
    public String getVocabularyName() {
        return this.vocabularyName;
    }
    
    // vocabularyReference
    
    public void setVocabularyReference(String val) {
        this.vocabularyReference = val;
    }
    
    public String getVocabularyReference() {
        return this.vocabularyReference;
    }
    
    // vocabularyVersion
    
    public void setVocabularyVersion(String val) {
        this.vocabularyVersion = val;
    }
    
    public String getVocabularyVersion() {
        return this.vocabularyVersion;
    }
    
    public ArrayList<SourceToConceptMapDvo> getSourceToConceptMapTargetVocabularyList() {
        return sourceToConceptMapTargetVocabularyList;
    }
    
    public void setSourceToConceptMapTargetVocabularyList(ArrayList<SourceToConceptMapDvo> list) {
        this.sourceToConceptMapTargetVocabularyList = list;
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
            getVocabularyId()  == null ? null: getVocabularyId() + ""
        };
        return rtn;
    }
}
