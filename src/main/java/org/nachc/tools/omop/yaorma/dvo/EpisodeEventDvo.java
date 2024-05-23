//
// Data Value Object (DVO) for EPISODE_EVENT
//

package org.nachc.tools.omop.yaorma.dvo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;
import java.math.BigDecimal;

import org.yaorma.dvo.Dvo;

public class EpisodeEventDvo implements Dvo {

    //
    // tableName
    //
    
    public static final String TABLE_NAME = "EPISODE_EVENT";
    
    //
    // schemaName
    //
    
    public static final String SCHEMA_NAME = "synthea_micro.dbo";
    
    //
    // columnNames
    //
    
    public static final String[] COLUMN_NAMES = {
        "episode_id",
        "event_id",
        "episode_event_field_concept_id"
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
        "eventId",
        "episodeEventFieldConceptId"
    };
    
    //
    // javaNamesProper
    //
    
    public static final String[] JAVA_NAMES_PROPER = {
        "EpisodeId",
        "EventId",
        "EpisodeEventFieldConceptId"
    };
    
    
    //
    // member variables
    //
    
    private HashMap<String, String> descriptions = new HashMap<String, String>();
    
    private Long episodeId;
    
    private Long eventId;
    
    private Integer episodeEventFieldConceptId;
    
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
    
    // eventId
    
    public void setEventId(Long val) {
        this.eventId = val;
    }
    
    public Long getEventId() {
        return this.eventId;
    }
    
    // episodeEventFieldConceptId
    
    public void setEpisodeEventFieldConceptId(Integer val) {
        this.episodeEventFieldConceptId = val;
    }
    
    public Integer getEpisodeEventFieldConceptId() {
        return this.episodeEventFieldConceptId;
    }
    
    //
    // implementation of Dvo
    //
    
    public String getTableName() {
        return TABLE_NAME;
    };
    
    public String getSchemaName() {
        return org.nachc.tools.fhirtoomop.util.params.AppParams.getFullySpecifiedSchemaName();
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
