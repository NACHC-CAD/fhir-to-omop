//
// Data Value Object (DVO) for note_nlp
//

package org.nachc.tools.omop.yaorma.dvo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;

import org.yaorma.dvo.Dvo;

public class NoteNlpDvo implements Dvo {

    //
    // tableName
    //
    
    public static final String TABLE_NAME = "note_nlp";
    
    //
    // schemaName
    //
    
    public static final String SCHEMA_NAME = "synthea_omop.dbo";
    
    //
    // columnNames
    //
    
    public static final String[] COLUMN_NAMES = {
        "lexical_variant",
        "nlp_date",
        "nlp_datetime",
        "nlp_system",
        "note_id",
        "note_nlp_concept_id",
        "note_nlp_id",
        "note_nlp_source_concept_id",
        "offset",
        "section_concept_id",
        "snippet",
        "term_exists",
        "term_modifiers",
        "term_temporal"
    };
    
    //
    // primaryKeyColumnNames
    //
    
    public static final String[] PRIMARY_KEY_COLUMN_NAMES = {
        "note_nlp_id"
    };
    
    //
    // javaNames
    //
    
    public static final String[] JAVA_NAMES = {
        "lexicalVariant",
        "nlpDate",
        "nlpDatetime",
        "nlpSystem",
        "noteId",
        "noteNlpConceptId",
        "noteNlpId",
        "noteNlpSourceConceptId",
        "offset",
        "sectionConceptId",
        "snippet",
        "termExists",
        "termModifiers",
        "termTemporal"
    };
    
    //
    // javaNamesProper
    //
    
    public static final String[] JAVA_NAMES_PROPER = {
        "LexicalVariant",
        "NlpDate",
        "NlpDatetime",
        "NlpSystem",
        "NoteId",
        "NoteNlpConceptId",
        "NoteNlpId",
        "NoteNlpSourceConceptId",
        "Offset",
        "SectionConceptId",
        "Snippet",
        "TermExists",
        "TermModifiers",
        "TermTemporal"
    };
    
    
    //
    // member variables
    //
    
    private HashMap<String, String> descriptions = new HashMap<String, String>();
    
    private String lexicalVariant;
    
    private Date nlpDate;
    
    private String nlpDatetime;
    
    private String nlpSystem;
    
    private Integer noteId;
    
    private Integer noteNlpConceptId;
    
    private Integer noteNlpId;
    
    private Integer noteNlpSourceConceptId;
    
    private String offset;
    
    private Integer sectionConceptId;
    
    private String snippet;
    
    private String termExists;
    
    private String termModifiers;
    
    private String termTemporal;
    
    private ConceptDvo noteNlpConceptDvo;
    
    private ConceptDvo noteNlpSourceConceptDvo;
    
    private ConceptDvo sectionConceptDvo;
    
    //
    // trivial getters and setters
    //
    
    // lexicalVariant
    
    public void setLexicalVariant(String val) {
        this.lexicalVariant = val;
    }
    
    public String getLexicalVariant() {
        return this.lexicalVariant;
    }
    
    // nlpDate
    
    public void setNlpDate(Date val) {
        this.nlpDate = val;
    }
    
    public Date getNlpDate() {
        return this.nlpDate;
    }
    
    // nlpDatetime
    
    public void setNlpDatetime(String val) {
        this.nlpDatetime = val;
    }
    
    public String getNlpDatetime() {
        return this.nlpDatetime;
    }
    
    // nlpSystem
    
    public void setNlpSystem(String val) {
        this.nlpSystem = val;
    }
    
    public String getNlpSystem() {
        return this.nlpSystem;
    }
    
    // noteId
    
    public void setNoteId(Integer val) {
        this.noteId = val;
    }
    
    public Integer getNoteId() {
        return this.noteId;
    }
    
    // noteNlpConceptId
    
    public void setNoteNlpConceptId(Integer val) {
        this.noteNlpConceptId = val;
    }
    
    public Integer getNoteNlpConceptId() {
        return this.noteNlpConceptId;
    }
    
    // noteNlpId
    
    public void setNoteNlpId(Integer val) {
        this.noteNlpId = val;
    }
    
    public Integer getNoteNlpId() {
        return this.noteNlpId;
    }
    
    // noteNlpSourceConceptId
    
    public void setNoteNlpSourceConceptId(Integer val) {
        this.noteNlpSourceConceptId = val;
    }
    
    public Integer getNoteNlpSourceConceptId() {
        return this.noteNlpSourceConceptId;
    }
    
    // offset
    
    public void setOffset(String val) {
        this.offset = val;
    }
    
    public String getOffset() {
        return this.offset;
    }
    
    // sectionConceptId
    
    public void setSectionConceptId(Integer val) {
        this.sectionConceptId = val;
    }
    
    public Integer getSectionConceptId() {
        return this.sectionConceptId;
    }
    
    // snippet
    
    public void setSnippet(String val) {
        this.snippet = val;
    }
    
    public String getSnippet() {
        return this.snippet;
    }
    
    // termExists
    
    public void setTermExists(String val) {
        this.termExists = val;
    }
    
    public String getTermExists() {
        return this.termExists;
    }
    
    // termModifiers
    
    public void setTermModifiers(String val) {
        this.termModifiers = val;
    }
    
    public String getTermModifiers() {
        return this.termModifiers;
    }
    
    // termTemporal
    
    public void setTermTemporal(String val) {
        this.termTemporal = val;
    }
    
    public String getTermTemporal() {
        return this.termTemporal;
    }
    
    // noteNlpConceptDvo
    
    public void setNoteNlpConceptDvo(ConceptDvo dvo) {
        this.noteNlpConceptDvo = dvo;
    }
    
    public ConceptDvo getNoteNlpConceptDvo() {
        return this.noteNlpConceptDvo;
    }
    
    // noteNlpSourceConceptDvo
    
    public void setNoteNlpSourceConceptDvo(ConceptDvo dvo) {
        this.noteNlpSourceConceptDvo = dvo;
    }
    
    public ConceptDvo getNoteNlpSourceConceptDvo() {
        return this.noteNlpSourceConceptDvo;
    }
    
    // sectionConceptDvo
    
    public void setSectionConceptDvo(ConceptDvo dvo) {
        this.sectionConceptDvo = dvo;
    }
    
    public ConceptDvo getSectionConceptDvo() {
        return this.sectionConceptDvo;
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
            getNoteNlpId()  == null ? null: getNoteNlpId() + ""
        };
        return rtn;
    }
}
