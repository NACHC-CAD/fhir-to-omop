//
// Data Value Object (DVO) for note
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
    
    public static final String TABLE_NAME = "note";
    
    //
    // schemaName
    //
    
    public static final String SCHEMA_NAME = "synthea_omop.dbo";
    
    //
    // columnNames
    //
    
    public static final String[] COLUMN_NAMES = {
        "encoding_concept_id",
        "language_concept_id",
        "note_class_concept_id",
        "note_date",
        "note_datetime",
        "note_event_field_concept_id",
        "note_event_id",
        "note_id",
        "note_source_value",
        "note_text",
        "note_title",
        "note_type_concept_id",
        "person_id",
        "provider_id",
        "visit_detail_id",
        "visit_occurrence_id"
    };
    
    //
    // primaryKeyColumnNames
    //
    
    public static final String[] PRIMARY_KEY_COLUMN_NAMES = {
        "note_id"
    };
    
    //
    // javaNames
    //
    
    public static final String[] JAVA_NAMES = {
        "encodingConceptId",
        "languageConceptId",
        "noteClassConceptId",
        "noteDate",
        "noteDatetime",
        "noteEventFieldConceptId",
        "noteEventId",
        "noteId",
        "noteSourceValue",
        "noteText",
        "noteTitle",
        "noteTypeConceptId",
        "personId",
        "providerId",
        "visitDetailId",
        "visitOccurrenceId"
    };
    
    //
    // javaNamesProper
    //
    
    public static final String[] JAVA_NAMES_PROPER = {
        "EncodingConceptId",
        "LanguageConceptId",
        "NoteClassConceptId",
        "NoteDate",
        "NoteDatetime",
        "NoteEventFieldConceptId",
        "NoteEventId",
        "NoteId",
        "NoteSourceValue",
        "NoteText",
        "NoteTitle",
        "NoteTypeConceptId",
        "PersonId",
        "ProviderId",
        "VisitDetailId",
        "VisitOccurrenceId"
    };
    
    
    //
    // member variables
    //
    
    private HashMap<String, String> descriptions = new HashMap<String, String>();
    
    private Integer encodingConceptId;
    
    private Integer languageConceptId;
    
    private Integer noteClassConceptId;
    
    private Date noteDate;
    
    private String noteDatetime;
    
    private Integer noteEventFieldConceptId;
    
    private String noteEventId;
    
    private Integer noteId;
    
    private String noteSourceValue;
    
    private String noteText;
    
    private String noteTitle;
    
    private Integer noteTypeConceptId;
    
    private Integer personId;
    
    private Integer providerId;
    
    private Integer visitDetailId;
    
    private Integer visitOccurrenceId;
    
    private ConceptDvo encodingConceptDvo;
    
    private ConceptDvo languageConceptDvo;
    
    private ConceptDvo noteClassConceptDvo;
    
    private ConceptDvo noteEventFieldConceptDvo;
    
    private ConceptDvo noteTypeConceptDvo;
    
    private PersonDvo personDvo;
    
    private ProviderDvo providerDvo;
    
    private VisitDetailDvo visitDetailDvo;
    
    private VisitOccurrenceDvo visitOccurrenceDvo;
    
    //
    // trivial getters and setters
    //
    
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
    
    // noteClassConceptId
    
    public void setNoteClassConceptId(Integer val) {
        this.noteClassConceptId = val;
    }
    
    public Integer getNoteClassConceptId() {
        return this.noteClassConceptId;
    }
    
    // noteDate
    
    public void setNoteDate(Date val) {
        this.noteDate = val;
    }
    
    public Date getNoteDate() {
        return this.noteDate;
    }
    
    // noteDatetime
    
    public void setNoteDatetime(String val) {
        this.noteDatetime = val;
    }
    
    public String getNoteDatetime() {
        return this.noteDatetime;
    }
    
    // noteEventFieldConceptId
    
    public void setNoteEventFieldConceptId(Integer val) {
        this.noteEventFieldConceptId = val;
    }
    
    public Integer getNoteEventFieldConceptId() {
        return this.noteEventFieldConceptId;
    }
    
    // noteEventId
    
    public void setNoteEventId(String val) {
        this.noteEventId = val;
    }
    
    public String getNoteEventId() {
        return this.noteEventId;
    }
    
    // noteId
    
    public void setNoteId(Integer val) {
        this.noteId = val;
    }
    
    public Integer getNoteId() {
        return this.noteId;
    }
    
    // noteSourceValue
    
    public void setNoteSourceValue(String val) {
        this.noteSourceValue = val;
    }
    
    public String getNoteSourceValue() {
        return this.noteSourceValue;
    }
    
    // noteText
    
    public void setNoteText(String val) {
        this.noteText = val;
    }
    
    public String getNoteText() {
        return this.noteText;
    }
    
    // noteTitle
    
    public void setNoteTitle(String val) {
        this.noteTitle = val;
    }
    
    public String getNoteTitle() {
        return this.noteTitle;
    }
    
    // noteTypeConceptId
    
    public void setNoteTypeConceptId(Integer val) {
        this.noteTypeConceptId = val;
    }
    
    public Integer getNoteTypeConceptId() {
        return this.noteTypeConceptId;
    }
    
    // personId
    
    public void setPersonId(Integer val) {
        this.personId = val;
    }
    
    public Integer getPersonId() {
        return this.personId;
    }
    
    // providerId
    
    public void setProviderId(Integer val) {
        this.providerId = val;
    }
    
    public Integer getProviderId() {
        return this.providerId;
    }
    
    // visitDetailId
    
    public void setVisitDetailId(Integer val) {
        this.visitDetailId = val;
    }
    
    public Integer getVisitDetailId() {
        return this.visitDetailId;
    }
    
    // visitOccurrenceId
    
    public void setVisitOccurrenceId(Integer val) {
        this.visitOccurrenceId = val;
    }
    
    public Integer getVisitOccurrenceId() {
        return this.visitOccurrenceId;
    }
    
    // encodingConceptDvo
    
    public void setEncodingConceptDvo(ConceptDvo dvo) {
        this.encodingConceptDvo = dvo;
    }
    
    public ConceptDvo getEncodingConceptDvo() {
        return this.encodingConceptDvo;
    }
    
    // languageConceptDvo
    
    public void setLanguageConceptDvo(ConceptDvo dvo) {
        this.languageConceptDvo = dvo;
    }
    
    public ConceptDvo getLanguageConceptDvo() {
        return this.languageConceptDvo;
    }
    
    // noteClassConceptDvo
    
    public void setNoteClassConceptDvo(ConceptDvo dvo) {
        this.noteClassConceptDvo = dvo;
    }
    
    public ConceptDvo getNoteClassConceptDvo() {
        return this.noteClassConceptDvo;
    }
    
    // noteEventFieldConceptDvo
    
    public void setNoteEventFieldConceptDvo(ConceptDvo dvo) {
        this.noteEventFieldConceptDvo = dvo;
    }
    
    public ConceptDvo getNoteEventFieldConceptDvo() {
        return this.noteEventFieldConceptDvo;
    }
    
    // noteTypeConceptDvo
    
    public void setNoteTypeConceptDvo(ConceptDvo dvo) {
        this.noteTypeConceptDvo = dvo;
    }
    
    public ConceptDvo getNoteTypeConceptDvo() {
        return this.noteTypeConceptDvo;
    }
    
    // personDvo
    
    public void setPersonDvo(PersonDvo dvo) {
        this.personDvo = dvo;
    }
    
    public PersonDvo getPersonDvo() {
        return this.personDvo;
    }
    
    // providerDvo
    
    public void setProviderDvo(ProviderDvo dvo) {
        this.providerDvo = dvo;
    }
    
    public ProviderDvo getProviderDvo() {
        return this.providerDvo;
    }
    
    // visitDetailDvo
    
    public void setVisitDetailDvo(VisitDetailDvo dvo) {
        this.visitDetailDvo = dvo;
    }
    
    public VisitDetailDvo getVisitDetailDvo() {
        return this.visitDetailDvo;
    }
    
    // visitOccurrenceDvo
    
    public void setVisitOccurrenceDvo(VisitOccurrenceDvo dvo) {
        this.visitOccurrenceDvo = dvo;
    }
    
    public VisitOccurrenceDvo getVisitOccurrenceDvo() {
        return this.visitOccurrenceDvo;
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
            getNoteId()  == null ? null: getNoteId() + ""
        };
        return rtn;
    }
}
