//
// Data Value Object (DVO) for EPISODE
//

package org.nachc.tools.omop.yaorma.dvo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;
import java.math.BigDecimal;

import org.yaorma.dvo.Dvo;

public class EpisodeDvo implements Dvo {

    //
    // tableName
    //
    
    public static final String TABLE_NAME = "EPISODE";
    
    //
    // schemaName
    //
    
    public static final String SCHEMA_NAME = "synthea_micro.dbo";
    
    //
    // columnNames
    //
    
    public static final String[] COLUMN_NAMES = {
        "episode_id",
        "person_id",
        "episode_concept_id",
        "episode_start_date",
        "episode_start_datetime",
        "episode_end_date",
        "episode_end_datetime",
        "episode_parent_id",
        "episode_number",
        "episode_object_concept_id",
        "episode_type_concept_id",
        "episode_source_value",
        "episode_source_concept_id"
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
        "episodeId",
        "personId",
        "episodeConceptId",
        "episodeStartDate",
        "episodeStartDatetime",
        "episodeEndDate",
        "episodeEndDatetime",
        "episodeParentId",
        "episodeNumber",
        "episodeObjectConceptId",
        "episodeTypeConceptId",
        "episodeSourceValue",
        "episodeSourceConceptId"
    };
    
    //
    // javaNamesProper
    //
    
    public static final String[] JAVA_NAMES_PROPER = {
        "EpisodeId",
        "PersonId",
        "EpisodeConceptId",
        "EpisodeStartDate",
        "EpisodeStartDatetime",
        "EpisodeEndDate",
        "EpisodeEndDatetime",
        "EpisodeParentId",
        "EpisodeNumber",
        "EpisodeObjectConceptId",
        "EpisodeTypeConceptId",
        "EpisodeSourceValue",
        "EpisodeSourceConceptId"
    };
    
    
    //
    // member variables
    //
    
    private HashMap<String, String> descriptions = new HashMap<String, String>();
    
    private Long episodeId;
    
    private Integer personId;
    
    private Integer episodeConceptId;
    
    private Date episodeStartDate;
    
    private Date episodeStartDatetime;
    
    private Date episodeEndDate;
    
    private Date episodeEndDatetime;
    
    private Long episodeParentId;
    
    private Integer episodeNumber;
    
    private Integer episodeObjectConceptId;
    
    private Integer episodeTypeConceptId;
    
    private String episodeSourceValue;
    
    private Integer episodeSourceConceptId;
    
    //
    // trivial getters and setters
    //
    
    // episodeId
    
    public void setEpisodeId(Long val) {
        this.episodeId = val;
    }
    
    public Long getEpisodeId() {
        return this.episodeId;
    }
    
    // personId
    
    public void setPersonId(Integer val) {
        this.personId = val;
    }
    
    public Integer getPersonId() {
        return this.personId;
    }
    
    // episodeConceptId
    
    public void setEpisodeConceptId(Integer val) {
        this.episodeConceptId = val;
    }
    
    public Integer getEpisodeConceptId() {
        return this.episodeConceptId;
    }
    
    // episodeStartDate
    
    public void setEpisodeStartDate(Date val) {
        this.episodeStartDate = val;
    }
    
    public Date getEpisodeStartDate() {
        return this.episodeStartDate;
    }
    
    // episodeStartDatetime
    
    public void setEpisodeStartDatetime(Date val) {
        this.episodeStartDatetime = val;
    }
    
    public Date getEpisodeStartDatetime() {
        return this.episodeStartDatetime;
    }
    
    // episodeEndDate
    
    public void setEpisodeEndDate(Date val) {
        this.episodeEndDate = val;
    }
    
    public Date getEpisodeEndDate() {
        return this.episodeEndDate;
    }
    
    // episodeEndDatetime
    
    public void setEpisodeEndDatetime(Date val) {
        this.episodeEndDatetime = val;
    }
    
    public Date getEpisodeEndDatetime() {
        return this.episodeEndDatetime;
    }
    
    // episodeParentId
    
    public void setEpisodeParentId(Long val) {
        this.episodeParentId = val;
    }
    
    public Long getEpisodeParentId() {
        return this.episodeParentId;
    }
    
    // episodeNumber
    
    public void setEpisodeNumber(Integer val) {
        this.episodeNumber = val;
    }
    
    public Integer getEpisodeNumber() {
        return this.episodeNumber;
    }
    
    // episodeObjectConceptId
    
    public void setEpisodeObjectConceptId(Integer val) {
        this.episodeObjectConceptId = val;
    }
    
    public Integer getEpisodeObjectConceptId() {
        return this.episodeObjectConceptId;
    }
    
    // episodeTypeConceptId
    
    public void setEpisodeTypeConceptId(Integer val) {
        this.episodeTypeConceptId = val;
    }
    
    public Integer getEpisodeTypeConceptId() {
        return this.episodeTypeConceptId;
    }
    
    // episodeSourceValue
    
    public void setEpisodeSourceValue(String val) {
        this.episodeSourceValue = val;
    }
    
    public String getEpisodeSourceValue() {
        return this.episodeSourceValue;
    }
    
    // episodeSourceConceptId
    
    public void setEpisodeSourceConceptId(Integer val) {
        this.episodeSourceConceptId = val;
    }
    
    public Integer getEpisodeSourceConceptId() {
        return this.episodeSourceConceptId;
    }
    
    //
    // implementation of Dvo
    //
    
    public String getTableName() {
        return TABLE_NAME;
    };
    
    public String getSchemaName() {
        return org.nachc.tools.fhirtoomop.util.params.AppParams.getFullySpecifiedCdmSchemaName();
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
