//
// Data Value Object (DVO) for NOTE
//

package org.nachc.tools.omop.yaorma.dvo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;

import org.yaorma.dvo.Dvo;

public class NoteDvo implements Dvo {

    //
    // tableName
    //
    
    public static final String TABLE_NAME = "NOTE";
    
    //
    // schemaName
    //
    
    public static final String SCHEMA_NAME = "synthea_omop.dbo";
    
    //
    // columnNames
    //
    
    public static final String[] COLUMN_NAMES = {
        "note_id",
        "person_id",
        "note_date",
        "note_datetime",
        "note_type_concept_id",
        "note_class_concept_id",
        "note_title",
        "note_text",
        "encoding_concept_id",
        "language_concept_id",
        "provider_id",
        "visit_occurrence_id",
        "visit_detail_id",
        "note_source_value",
        "note_event_id",
        "note_event_field_concept_id"
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
        "noteId",
        "personId",
        "noteDate",
        "noteDatetime",
        "noteTypeConceptId",
        "noteClassConceptId",
        "noteTitle",
        "noteText",
        "encodingConceptId",
        "languageConceptId",
        "providerId",
        "visitOccurrenceId",
        "visitDetailId",
        "noteSourceValue",
        "noteEventId",
        "noteEventFieldConceptId"
    };
    
    //
    // javaNamesProper
    //
    
    public static final String[] JAVA_NAMES_PROPER = {
        "NoteId",
        "PersonId",
        "NoteDate",
        "NoteDatetime",
        "NoteTypeConceptId",
        "NoteClassConceptId",
        "NoteTitle",
        "NoteText",
        "EncodingConceptId",
        "LanguageConceptId",
        "ProviderId",
        "VisitOccurrenceId",
        "VisitDetailId",
        "NoteSourceValue",
        "NoteEventId",
        "NoteEventFieldConceptId"
    };
    
    
    //
    // member variables
    //
    
    private HashMap<String, String> descriptions = new HashMap<String, String>();
    
    private Integer noteId;
    
    private Integer personId;
    
    private Date noteDate;
    
    private Date noteDatetime;
    
    private Integer noteTypeConceptId;
    
    private Integer noteClassConceptId;
    
    private String noteTitle;
    
    private String noteText;
    
    private Integer encodingConceptId;
    
    private Integer languageConceptId;
    
    private Integer providerId;
    
    private Integer visitOccurrenceId;
    
    private Integer visitDetailId;
    
    private String noteSourceValue;
    
    private String noteEventId;
    
    private Integer noteEventFieldConceptId;
    
    //
    // trivial getters and setters
    //
    
    // noteId
    
    public void setNoteId(Integer val) {
        this.noteId = val;
    }
    
    public Integer getNoteId() {
        return this.noteId;
    }
    
    // personId
    
    public void setPersonId(Integer val) {
        this.personId = val;
    }
    
    public Integer getPersonId() {
        return this.personId;
    }
    
    // noteDate
    
    public void setNoteDate(Date val) {
        this.noteDate = val;
    }
    
    public Date getNoteDate() {
        return this.noteDate;
    }
    
    // noteDatetime
    
    public void setNoteDatetime(Date val) {
        this.noteDatetime = val;
    }
    
    public Date getNoteDatetime() {
        return this.noteDatetime;
    }
    
    // noteTypeConceptId
    
    public void setNoteTypeConceptId(Integer val) {
        this.noteTypeConceptId = val;
    }
    
    public Integer getNoteTypeConceptId() {
        return this.noteTypeConceptId;
    }
    
    // noteClassConceptId
    
    public void setNoteClassConceptId(Integer val) {
        this.noteClassConceptId = val;
    }
    
    public Integer getNoteClassConceptId() {
        return this.noteClassConceptId;
    }
    
    // noteTitle
    
    public void setNoteTitle(String val) {
        this.noteTitle = val;
    }
    
    public String getNoteTitle() {
        return this.noteTitle;
    }
    
    // noteText
    
    public void setNoteText(String val) {
        this.noteText = val;
    }
    
    public String getNoteText() {
        return this.noteText;
    }
    
    // encodingConceptId
    
    public void setEncodingConceptId(Integer val) {
        this.encodingConceptId = val;
    }
    
    public Integer getEncodingConceptId() {
        return this.encodingConceptId;
    }
    
    // languageConceptId
    
    public void setLanguageConceptId(Integer val) {
        this.languageConceptId = val;
    }
    
    public Integer getLanguageConceptId() {
        return this.languageConceptId;
    }
    
    // providerId
    
    public void setProviderId(Integer val) {
        this.providerId = val;
    }
    
    public Integer getProviderId() {
        return this.providerId;
    }
    
    // visitOccurrenceId
    
    public void setVisitOccurrenceId(Integer val) {
        this.visitOccurrenceId = val;
    }
    
    public Integer getVisitOccurrenceId() {
        return this.visitOccurrenceId;
    }
    
    // visitDetailId
    
    public void setVisitDetailId(Integer val) {
        this.visitDetailId = val;
    }
    
    public Integer getVisitDetailId() {
        return this.visitDetailId;
    }
    
    // noteSourceValue
    
    public void setNoteSourceValue(String val) {
        this.noteSourceValue = val;
    }
    
    public String getNoteSourceValue() {
        return this.noteSourceValue;
    }
    
    // noteEventId
    
    public void setNoteEventId(String val) {
        this.noteEventId = val;
    }
    
    public String getNoteEventId() {
        return this.noteEventId;
    }
    
    // noteEventFieldConceptId
    
    public void setNoteEventFieldConceptId(Integer val) {
        this.noteEventFieldConceptId = val;
    }
    
    public Integer getNoteEventFieldConceptId() {
        return this.noteEventFieldConceptId;
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
