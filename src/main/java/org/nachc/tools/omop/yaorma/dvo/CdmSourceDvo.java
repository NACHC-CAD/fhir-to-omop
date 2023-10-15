//
// Data Value Object (DVO) for CDM_SOURCE
//

package org.nachc.tools.omop.yaorma.dvo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;
import java.math.BigDecimal;

import org.yaorma.dvo.Dvo;

public class CdmSourceDvo implements Dvo {

    //
    // tableName
    //
    
    public static final String TABLE_NAME = "CDM_SOURCE";
    
    //
    // schemaName
    //
    
    public static final String SCHEMA_NAME = "etl_synthea_1k";
    
    //
    // columnNames
    //
    
    public static final String[] COLUMN_NAMES = {
        "cdm_source_name",
        "cdm_source_abbreviation",
        "cdm_holder",
        "source_description",
        "source_documentation_reference",
        "cdm_etl_reference",
        "source_release_date",
        "cdm_release_date",
        "cdm_version",
        "cdm_version_concept_id",
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
        "cdmSourceName",
        "cdmSourceAbbreviation",
        "cdmHolder",
        "sourceDescription",
        "sourceDocumentationReference",
        "cdmEtlReference",
        "sourceReleaseDate",
        "cdmReleaseDate",
        "cdmVersion",
        "cdmVersionConceptId",
        "vocabularyVersion"
    };
    
    //
    // javaNamesProper
    //
    
    public static final String[] JAVA_NAMES_PROPER = {
        "CdmSourceName",
        "CdmSourceAbbreviation",
        "CdmHolder",
        "SourceDescription",
        "SourceDocumentationReference",
        "CdmEtlReference",
        "SourceReleaseDate",
        "CdmReleaseDate",
        "CdmVersion",
        "CdmVersionConceptId",
        "VocabularyVersion"
    };
    
    
    //
    // member variables
    //
    
    private HashMap<String, String> descriptions = new HashMap<String, String>();
    
    private String cdmSourceName;
    
    private String cdmSourceAbbreviation;
    
    private String cdmHolder;
    
    private String sourceDescription;
    
    private String sourceDocumentationReference;
    
    private String cdmEtlReference;
    
    private Date sourceReleaseDate;
    
    private Date cdmReleaseDate;
    
    private String cdmVersion;
    
    private Integer cdmVersionConceptId;
    
    private String vocabularyVersion;
    
    //
    // trivial getters and setters
    //
    
    // cdmSourceName
    
    public void setCdmSourceName(String val) {
        this.cdmSourceName = val;
    }
    
    public String getCdmSourceName() {
        return this.cdmSourceName;
    }
    
    // cdmSourceAbbreviation
    
    public void setCdmSourceAbbreviation(String val) {
        this.cdmSourceAbbreviation = val;
    }
    
    public String getCdmSourceAbbreviation() {
        return this.cdmSourceAbbreviation;
    }
    
    // cdmHolder
    
    public void setCdmHolder(String val) {
        this.cdmHolder = val;
    }
    
    public String getCdmHolder() {
        return this.cdmHolder;
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
    
    // cdmEtlReference
    
    public void setCdmEtlReference(String val) {
        this.cdmEtlReference = val;
    }
    
    public String getCdmEtlReference() {
        return this.cdmEtlReference;
    }
    
    // sourceReleaseDate
    
    public void setSourceReleaseDate(Date val) {
        this.sourceReleaseDate = val;
    }
    
    public Date getSourceReleaseDate() {
        return this.sourceReleaseDate;
    }
    
    // cdmReleaseDate
    
    public void setCdmReleaseDate(Date val) {
        this.cdmReleaseDate = val;
    }
    
    public Date getCdmReleaseDate() {
        return this.cdmReleaseDate;
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
    
    // vocabularyVersion
    
    public void setVocabularyVersion(String val) {
        this.vocabularyVersion = val;
    }
    
    public String getVocabularyVersion() {
        return this.vocabularyVersion;
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
