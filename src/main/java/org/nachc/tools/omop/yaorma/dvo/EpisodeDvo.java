//
// Data Value Object (DVO) for episode
//

package org.nachc.tools.omop.yaorma.dvo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;

import org.yaorma.dvo.Dvo;

public class EpisodeDvo implements Dvo {

    //
    // tableName
    //
    
    public static final String TABLE_NAME = "episode";
    
    //
    // schemaName
    //
    
    public static final String SCHEMA_NAME = "synthea_omop";
    
    //
    // columnNames
    //
    
    public static final String[] COLUMN_NAMES = {
        "episode_concept_id",
        "episode_end_date",
        "episode_end_datetime",
        "episode_id",
        "episode_number",
        "episode_object_concept_id",
        "episode_parent_id",
        "episode_source_concept_id",
        "episode_source_value",
        "episode_start_date",
        "episode_start_datetime",
        "episode_type_concept_id",
        "person_id"
    };
    
    //
    // primaryKeyColumnNames
    //
    
    public static final String[] PRIMARY_KEY_COLUMN_NAMES = {
        "episode_id"
    };
    
    //
    // javaNames
    //
    
    public static final String[] JAVA_NAMES = {
        "episodeConceptId",
        "episodeEndDate",
        "episodeEndDatetime",
        "episodeId",
        "episodeNumber",
        "episodeObjectConceptId",
        "episodeParentId",
        "episodeSourceConceptId",
        "episodeSourceValue",
        "episodeStartDate",
        "episodeStartDatetime",
        "episodeTypeConceptId",
        "personId"
    };
    
    //
    // javaNamesProper
    //
    
    public static final String[] JAVA_NAMES_PROPER = {
        "EpisodeConceptId",
        "EpisodeEndDate",
        "EpisodeEndDatetime",
        "EpisodeId",
        "EpisodeNumber",
        "EpisodeObjectConceptId",
        "EpisodeParentId",
        "EpisodeSourceConceptId",
        "EpisodeSourceValue",
        "EpisodeStartDate",
        "EpisodeStartDatetime",
        "EpisodeTypeConceptId",
        "PersonId"
    };
    
    
    //
    // member variables
    //
    
    private HashMap<String, String> descriptions = new HashMap<String, String>();
    
    private Integer episodeConceptId;
    
    private String episodeEndDate;
    
    private String episodeEndDatetime;
    
    private String episodeId;
    
    private Integer episodeNumber;
    
    private Integer episodeObjectConceptId;
    
    private String episodeParentId;
    
    private Integer episodeSourceConceptId;
    
    private String episodeSourceValue;
    
    private String episodeStartDate;
    
    private String episodeStartDatetime;
    
    private Integer episodeTypeConceptId;
    
    private Integer personId;
    
    private ConceptDvo episodeConceptDvo;
    
    private ConceptDvo episodeObjectConceptDvo;
    
    private ConceptDvo episodeSourceConceptDvo;
    
    private ConceptDvo episodeTypeConceptDvo;
    
    private PersonDvo personDvo;
    
    private ArrayList<EpisodeEventDvo> episodeEventEpisodeList = new ArrayList<EpisodeEventDvo>();
    
    //
    // trivial getters and setters
    //
    
    // episodeConceptId
    
    public void setEpisodeConceptId(Integer val) {
        this.episodeConceptId = val;
    }
    
    public Integer getEpisodeConceptId() {
        return this.episodeConceptId;
    }
    
    // episodeEndDate
    
    public void setEpisodeEndDate(String val) {
        this.episodeEndDate = val;
    }
    
    public String getEpisodeEndDate() {
        return this.episodeEndDate;
    }
    
    // episodeEndDatetime
    
    public void setEpisodeEndDatetime(String val) {
        this.episodeEndDatetime = val;
    }
    
    public String getEpisodeEndDatetime() {
        return this.episodeEndDatetime;
    }
    
    // episodeId
    
    public void setEpisodeId(String val) {
        this.episodeId = val;
    }
    
    public String getEpisodeId() {
        return this.episodeId;
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
    
    // episodeParentId
    
    public void setEpisodeParentId(String val) {
        this.episodeParentId = val;
    }
    
    public String getEpisodeParentId() {
        return this.episodeParentId;
    }
    
    // episodeSourceConceptId
    
    public void setEpisodeSourceConceptId(Integer val) {
        this.episodeSourceConceptId = val;
    }
    
    public Integer getEpisodeSourceConceptId() {
        return this.episodeSourceConceptId;
    }
    
    // episodeSourceValue
    
    public void setEpisodeSourceValue(String val) {
        this.episodeSourceValue = val;
    }
    
    public String getEpisodeSourceValue() {
        return this.episodeSourceValue;
    }
    
    // episodeStartDate
    
    public void setEpisodeStartDate(String val) {
        this.episodeStartDate = val;
    }
    
    public String getEpisodeStartDate() {
        return this.episodeStartDate;
    }
    
    // episodeStartDatetime
    
    public void setEpisodeStartDatetime(String val) {
        this.episodeStartDatetime = val;
    }
    
    public String getEpisodeStartDatetime() {
        return this.episodeStartDatetime;
    }
    
    // episodeTypeConceptId
    
    public void setEpisodeTypeConceptId(Integer val) {
        this.episodeTypeConceptId = val;
    }
    
    public Integer getEpisodeTypeConceptId() {
        return this.episodeTypeConceptId;
    }
    
    // personId
    
    public void setPersonId(Integer val) {
        this.personId = val;
    }
    
    public Integer getPersonId() {
        return this.personId;
    }
    
    // episodeConceptDvo
    
    public void setEpisodeConceptDvo(ConceptDvo dvo) {
        this.episodeConceptDvo = dvo;
    }
    
    public ConceptDvo getEpisodeConceptDvo() {
        return this.episodeConceptDvo;
    }
    
    // episodeObjectConceptDvo
    
    public void setEpisodeObjectConceptDvo(ConceptDvo dvo) {
        this.episodeObjectConceptDvo = dvo;
    }
    
    public ConceptDvo getEpisodeObjectConceptDvo() {
        return this.episodeObjectConceptDvo;
    }
    
    // episodeSourceConceptDvo
    
    public void setEpisodeSourceConceptDvo(ConceptDvo dvo) {
        this.episodeSourceConceptDvo = dvo;
    }
    
    public ConceptDvo getEpisodeSourceConceptDvo() {
        return this.episodeSourceConceptDvo;
    }
    
    // episodeTypeConceptDvo
    
    public void setEpisodeTypeConceptDvo(ConceptDvo dvo) {
        this.episodeTypeConceptDvo = dvo;
    }
    
    public ConceptDvo getEpisodeTypeConceptDvo() {
        return this.episodeTypeConceptDvo;
    }
    
    // personDvo
    
    public void setPersonDvo(PersonDvo dvo) {
        this.personDvo = dvo;
    }
    
    public PersonDvo getPersonDvo() {
        return this.personDvo;
    }
    
    public ArrayList<EpisodeEventDvo> getEpisodeEventEpisodeList() {
        return episodeEventEpisodeList;
    }
    
    public void setEpisodeEventEpisodeList(ArrayList<EpisodeEventDvo> list) {
        this.episodeEventEpisodeList = list;
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
            getEpisodeId()  == null ? null: getEpisodeId() + ""
        };
        return rtn;
    }
}
