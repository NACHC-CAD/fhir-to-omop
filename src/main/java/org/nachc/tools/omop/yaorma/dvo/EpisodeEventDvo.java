//
// Data Value Object (DVO) for episode_event
//

package org.nachc.tools.omop.yaorma.dvo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;

import org.yaorma.dvo.Dvo;

public class EpisodeEventDvo implements Dvo {

    //
    // tableName
    //
    
    public static final String TABLE_NAME = "episode_event";
    
    //
    // schemaName
    //
    
    public static final String SCHEMA_NAME = "synthea_omop.dbo";
    
    //
    // columnNames
    //
    
    public static final String[] COLUMN_NAMES = {
        "episode_event_field_concept_id",
        "episode_id",
        "event_id"
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
        "episodeEventFieldConceptId",
        "episodeId",
        "eventId"
    };
    
    //
    // javaNamesProper
    //
    
    public static final String[] JAVA_NAMES_PROPER = {
        "EpisodeEventFieldConceptId",
        "EpisodeId",
        "EventId"
    };
    
    
    //
    // member variables
    //
    
    private HashMap<String, String> descriptions = new HashMap<String, String>();
    
    private Integer episodeEventFieldConceptId;
    
    private String episodeId;
    
    private String eventId;
    
    private ConceptDvo episodeEventFieldConceptDvo;
    
    private EpisodeDvo episodeDvo;
    
    //
    // trivial getters and setters
    //
    
    // episodeEventFieldConceptId
    
    public void setEpisodeEventFieldConceptId(Integer val) {
        this.episodeEventFieldConceptId = val;
    }
    
    public Integer getEpisodeEventFieldConceptId() {
        return this.episodeEventFieldConceptId;
    }
    
    // episodeId
    
    public void setEpisodeId(String val) {
        this.episodeId = val;
    }
    
    public String getEpisodeId() {
        return this.episodeId;
    }
    
    // eventId
    
    public void setEventId(String val) {
        this.eventId = val;
    }
    
    public String getEventId() {
        return this.eventId;
    }
    
    // episodeEventFieldConceptDvo
    
    public void setEpisodeEventFieldConceptDvo(ConceptDvo dvo) {
        this.episodeEventFieldConceptDvo = dvo;
    }
    
    public ConceptDvo getEpisodeEventFieldConceptDvo() {
        return this.episodeEventFieldConceptDvo;
    }
    
    // episodeDvo
    
    public void setEpisodeDvo(EpisodeDvo dvo) {
        this.episodeDvo = dvo;
    }
    
    public EpisodeDvo getEpisodeDvo() {
        return this.episodeDvo;
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
