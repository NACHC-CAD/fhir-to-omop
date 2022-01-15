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
    
    public static final String SCHEMA_NAME = "synthea_omop";
    
    //
    // columnNames
    //
    
    public static final String[] COLUMN_NAMES = {
        "note_nlp_id",
        "note_id",
        "section_concept_id",
        "snippet",
        "offset",
        "lexical_variant",
        "note_nlp_concept_id",
        "note_nlp_source_concept_id",
        "nlp_system",
        "nlp_date",
        "nlp_datetime",
        "term_exists",
        "term_temporal",
        "term_modifiers"
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
        "noteNlpId",
        "noteId",
        "sectionConceptId",
        "snippet",
        "offset",
        "lexicalVariant",
        "noteNlpConceptId",
        "noteNlpSourceConceptId",
        "nlpSystem",
        "nlpDate",
        "nlpDatetime",
        "termExists",
        "termTemporal",
        "termModifiers"
    };
    
    //
    // javaNamesProper
    //
    
    public static final String[] JAVA_NAMES_PROPER = {
        "NoteNlpId",
        "NoteId",
        "SectionConceptId",
        "Snippet",
        "Offset",
        "LexicalVariant",
        "NoteNlpConceptId",
        "NoteNlpSourceConceptId",
        "NlpSystem",
        "NlpDate",
        "NlpDatetime",
        "TermExists",
        "TermTemporal",
        "TermModifiers"
    };
    
    
    //
    // member variables
    //
    
    private HashMap<String, String> descriptions = new HashMap<String, String>();
    
    private Integer noteNlpId;
    
    private Integer noteId;
    
    private Integer sectionConceptId;
    
    private String snippet;
    
    private String offset;
    
    private String lexicalVariant;
    
    private Integer noteNlpConceptId;
    
    private Integer noteNlpSourceConceptId;
    
    private String nlpSystem;
    
    private Date nlpDate;
    
    private String nlpDatetime;
    
    private String termExists;
    
    private String termTemporal;
    
    private String termModifiers;
    
    private ConceptDvo noteNlpConceptDvo;
    
    private ConceptDvo noteNlpSourceConceptDvo;
    
    private ConceptDvo sectionConceptDvo;
    
    //
    // trivial getters and setters
    //
    
    // noteNlpId
    
    public void setNoteNlpId(Integer val) {
        this.noteNlpId = val;
    }
    
    public Integer getNoteNlpId() {
        return this.noteNlpId;
    }
    
    // noteId
    
    public void setNoteId(Integer val) {
        this.noteId = val;
    }
    
    public Integer getNoteId() {
        return this.noteId;
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
    
    // offset
    
    public void setOffset(String val) {
        this.offset = val;
    }
    
    public String getOffset() {
        return this.offset;
    }
    
    // lexicalVariant
    
    public void setLexicalVariant(String val) {
        this.lexicalVariant = val;
    }
    
    public String getLexicalVariant() {
        return this.lexicalVariant;
    }
    
    // noteNlpConceptId
    
    public void setNoteNlpConceptId(Integer val) {
        this.noteNlpConceptId = val;
    }
    
    public Integer getNoteNlpConceptId() {
        return this.noteNlpConceptId;
    }
    
    // noteNlpSourceConceptId
    
    public void setNoteNlpSourceConceptId(Integer val) {
        this.noteNlpSourceConceptId = val;
    }
    
    public Integer getNoteNlpSourceConceptId() {
        return this.noteNlpSourceConceptId;
    }
    
    // nlpSystem
    
    public void setNlpSystem(String val) {
        this.nlpSystem = val;
    }
    
    public String getNlpSystem() {
        return this.nlpSystem;
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
    
    // termExists
    
    public void setTermExists(String val) {
        this.termExists = val;
    }
    
    public String getTermExists() {
        return this.termExists;
    }
    
    // termTemporal
    
    public void setTermTemporal(String val) {
        this.termTemporal = val;
    }
    
    public String getTermTemporal() {
        return this.termTemporal;
    }
    
    // termModifiers
    
    public void setTermModifiers(String val) {
        this.termModifiers = val;
    }
    
    public String getTermModifiers() {
        return this.termModifiers;
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
