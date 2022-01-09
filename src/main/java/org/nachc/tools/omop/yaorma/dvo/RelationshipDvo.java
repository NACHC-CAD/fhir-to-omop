//
// Data Value Object (DVO) for relationship
//

package org.nachc.tools.omop.yaorma.dvo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;

import org.yaorma.dvo.Dvo;

public class RelationshipDvo implements Dvo {

    //
    // tableName
    //
    
    public static final String TABLE_NAME = "relationship";
    
    //
    // schemaName
    //
    
    public static final String SCHEMA_NAME = "synthea_omop";
    
    //
    // columnNames
    //
    
    public static final String[] COLUMN_NAMES = {
        "defines_ancestry",
        "is_hierarchical",
        "relationship_concept_id",
        "relationship_id",
        "relationship_name",
        "reverse_relationship_id"
    };
    
    //
    // primaryKeyColumnNames
    //
    
    public static final String[] PRIMARY_KEY_COLUMN_NAMES = {
        "relationship_id"
    };
    
    //
    // javaNames
    //
    
    public static final String[] JAVA_NAMES = {
        "definesAncestry",
        "isHierarchical",
        "relationshipConceptId",
        "relationshipId",
        "relationshipName",
        "reverseRelationshipId"
    };
    
    //
    // javaNamesProper
    //
    
    public static final String[] JAVA_NAMES_PROPER = {
        "DefinesAncestry",
        "IsHierarchical",
        "RelationshipConceptId",
        "RelationshipId",
        "RelationshipName",
        "ReverseRelationshipId"
    };
    
    
    //
    // member variables
    //
    
    private HashMap<String, String> descriptions = new HashMap<String, String>();
    
    private String definesAncestry;
    
    private String isHierarchical;
    
    private Integer relationshipConceptId;
    
    private String relationshipId;
    
    private String relationshipName;
    
    private String reverseRelationshipId;
    
    private ConceptDvo relationshipConceptDvo;
    
    private ArrayList<ConceptRelationshipDvo> conceptRelationshipRelationshipList = new ArrayList<ConceptRelationshipDvo>();
    
    //
    // trivial getters and setters
    //
    
    // definesAncestry
    
    public void setDefinesAncestry(String val) {
        this.definesAncestry = val;
    }
    
    public String getDefinesAncestry() {
        return this.definesAncestry;
    }
    
    // isHierarchical
    
    public void setIsHierarchical(String val) {
        this.isHierarchical = val;
    }
    
    public String getIsHierarchical() {
        return this.isHierarchical;
    }
    
    // relationshipConceptId
    
    public void setRelationshipConceptId(Integer val) {
        this.relationshipConceptId = val;
    }
    
    public Integer getRelationshipConceptId() {
        return this.relationshipConceptId;
    }
    
    // relationshipId
    
    public void setRelationshipId(String val) {
        this.relationshipId = val;
    }
    
    public String getRelationshipId() {
        return this.relationshipId;
    }
    
    // relationshipName
    
    public void setRelationshipName(String val) {
        this.relationshipName = val;
    }
    
    public String getRelationshipName() {
        return this.relationshipName;
    }
    
    // reverseRelationshipId
    
    public void setReverseRelationshipId(String val) {
        this.reverseRelationshipId = val;
    }
    
    public String getReverseRelationshipId() {
        return this.reverseRelationshipId;
    }
    
    // relationshipConceptDvo
    
    public void setRelationshipConceptDvo(ConceptDvo dvo) {
        this.relationshipConceptDvo = dvo;
    }
    
    public ConceptDvo getRelationshipConceptDvo() {
        return this.relationshipConceptDvo;
    }
    
    public ArrayList<ConceptRelationshipDvo> getConceptRelationshipRelationshipList() {
        return conceptRelationshipRelationshipList;
    }
    
    public void setConceptRelationshipRelationshipList(ArrayList<ConceptRelationshipDvo> list) {
        this.conceptRelationshipRelationshipList = list;
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
            getRelationshipId()  == null ? null: getRelationshipId() + ""
        };
        return rtn;
    }
}
