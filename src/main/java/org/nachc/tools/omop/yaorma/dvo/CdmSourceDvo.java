//
// Data Value Object (DVO) for cdm_source
//

package org.nachc.tools.omop.yaorma.dvo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;

import org.yaorma.dvo.Dvo;

public class CdmSourceDvo implements Dvo {

    //
    // tableName
    //
    
    public static final String TABLE_NAME = "cdm_source";
    
    //
    // schemaName
    //
    
    public static final String SCHEMA_NAME = "synthea_omop";
    
    //
    // columnNames
    //
    
    public static final String[] COLUMN_NAMES = {
        "cdm_etl_reference",
        "cdm_holder",
        "cdm_release_date",
        "cdm_source_abbreviation",
        "cdm_source_name",
        "cdm_version",
        "cdm_version_concept_id",
        "source_description",
        "source_documentation_reference",
        "source_release_date",
        "vocabulary_version"
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
        "cdmEtlReference",
        "cdmHolder",
        "cdmReleaseDate",
        "cdmSourceAbbreviation",
        "cdmSourceName",
        "cdmVersion",
        "cdmVersionConceptId",
        "sourceDescription",
        "sourceDocumentationReference",
        "sourceReleaseDate",
        "vocabularyVersion"
    };
    
    //
    // javaNamesProper
    //
    
    public static final String[] JAVA_NAMES_PROPER = {
        "CdmEtlReference",
        "CdmHolder",
        "CdmReleaseDate",
        "CdmSourceAbbreviation",
        "CdmSourceName",
        "CdmVersion",
        "CdmVersionConceptId",
        "SourceDescription",
        "SourceDocumentationReference",
        "SourceReleaseDate",
        "VocabularyVersion"
    };
    
    
    //
    // member variables
    //
    
    private HashMap<String, String> descriptions = new HashMap<String, String>();
    
    private String cdmEtlReference;
    
    private String cdmHolder;
    
    private Date cdmReleaseDate;
    
    private String cdmSourceAbbreviation;
    
    private String cdmSourceName;
    
    private String cdmVersion;
    
    private Integer cdmVersionConceptId;
    
    private String sourceDescription;
    
    private String sourceDocumentationReference;
    
    private Date sourceReleaseDate;
    
    private String vocabularyVersion;
    
    private ConceptDvo cdmVersionConceptDvo;
    
    //
    // trivial getters and setters
    //
    
    // cdmEtlReference
    
    public void setCdmEtlReference(String val) {
        this.cdmEtlReference = val;
    }
    
    public String getCdmEtlReference() {
        return this.cdmEtlReference;
    }
    
    // cdmHolder
    
    public void setCdmHolder(String val) {
        this.cdmHolder = val;
    }
    
    public String getCdmHolder() {
        return this.cdmHolder;
    }
    
    // cdmReleaseDate
    
    public void setCdmReleaseDate(Date val) {
        this.cdmReleaseDate = val;
    }
    
    public Date getCdmReleaseDate() {
        return this.cdmReleaseDate;
    }
    
    // cdmSourceAbbreviation
    
    public void setCdmSourceAbbreviation(String val) {
        this.cdmSourceAbbreviation = val;
    }
    
    public String getCdmSourceAbbreviation() {
        return this.cdmSourceAbbreviation;
    }
    
    // cdmSourceName
    
    public void setCdmSourceName(String val) {
        this.cdmSourceName = val;
    }
    
    public String getCdmSourceName() {
        return this.cdmSourceName;
    }
    
    // cdmVersion
    
    public void setCdmVersion(String val) {
        this.cdmVersion = val;
    }
    
    public String getCdmVersion() {
        return this.cdmVersion;
    }
    
    // cdmVersionConceptId
    
    public void setCdmVersionConceptId(Integer val) {
        this.cdmVersionConceptId = val;
    }
    
    public Integer getCdmVersionConceptId() {
        return this.cdmVersionConceptId;
    }
    
    // sourceDescription
    
    public void setSourceDescription(String val) {
        this.sourceDescription = val;
    }
    
    public String getSourceDescription() {
        return this.sourceDescription;
    }
    
    // sourceDocumentationReference
    
    public void setSourceDocumentationReference(String val) {
        this.sourceDocumentationReference = val;
    }
    
    public String getSourceDocumentationReference() {
        return this.sourceDocumentationReference;
    }
    
    // sourceReleaseDate
    
    public void setSourceReleaseDate(Date val) {
        this.sourceReleaseDate = val;
    }
    
    public Date getSourceReleaseDate() {
        return this.sourceReleaseDate;
    }
    
    // vocabularyVersion
    
    public void setVocabularyVersion(String val) {
        this.vocabularyVersion = val;
    }
    
    public String getVocabularyVersion() {
        return this.vocabularyVersion;
    }
    
    // cdmVersionConceptDvo
    
    public void setCdmVersionConceptDvo(ConceptDvo dvo) {
        this.cdmVersionConceptDvo = dvo;
    }
    
    public ConceptDvo getCdmVersionConceptDvo() {
        return this.cdmVersionConceptDvo;
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
