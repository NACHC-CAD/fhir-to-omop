//
// Data Value Object (DVO) for fact_relationship
//

package org.nachc.tools.omop.yaorma.dvo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;

import org.yaorma.dvo.Dvo;

public class FactRelationshipDvo implements Dvo {

    //
    // tableName
    //
    
    public static final String TABLE_NAME = "fact_relationship";
    
    //
    // schemaName
    //
    
    public static final String SCHEMA_NAME = "synthea_omop";
    
    //
    // columnNames
    //
    
    public static final String[] COLUMN_NAMES = {
        "domain_concept_id_1",
        "domain_concept_id_2",
        "fact_id_1",
        "fact_id_2",
        "relationship_concept_id"
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
        "domainConceptId1",
        "domainConceptId2",
        "factId1",
        "factId2",
        "relationshipConceptId"
    };
    
    //
    // javaNamesProper
    //
    
    public static final String[] JAVA_NAMES_PROPER = {
        "DomainConceptId1",
        "DomainConceptId2",
        "FactId1",
        "FactId2",
        "RelationshipConceptId"
    };
    
    
    //
    // member variables
    //
    
    private HashMap<String, String> descriptions = new HashMap<String, String>();
    
    private Integer domainConceptId1;
    
    private Integer domainConceptId2;
    
    private Integer factId1;
    
    private Integer factId2;
    
    private Integer relationshipConceptId;
    
    private ConceptDvo domainConceptId1Dvo;
    
    private ConceptDvo domainConceptId2Dvo;
    
    private ConceptDvo relationshipConceptDvo;
    
    //
    // trivial getters and setters
    //
    
    // domainConceptId1
    
    public void setDomainConceptId1(Integer val) {
        this.domainConceptId1 = val;
    }
    
    public Integer getDomainConceptId1() {
        return this.domainConceptId1;
    }
    
    // domainConceptId2
    
    public void setDomainConceptId2(Integer val) {
        this.domainConceptId2 = val;
    }
    
    public Integer getDomainConceptId2() {
        return this.domainConceptId2;
    }
    
    // factId1
    
    public void setFactId1(Integer val) {
        this.factId1 = val;
    }
    
    public Integer getFactId1() {
        return this.factId1;
    }
    
    // factId2
    
    public void setFactId2(Integer val) {
        this.factId2 = val;
    }
    
    public Integer getFactId2() {
        return this.factId2;
    }
    
    // relationshipConceptId
    
    public void setRelationshipConceptId(Integer val) {
        this.relationshipConceptId = val;
    }
    
    public Integer getRelationshipConceptId() {
        return this.relationshipConceptId;
    }
    
    // domainConceptId1Dvo
    
    public void setDomainConceptId1Dvo(ConceptDvo dvo) {
        this.domainConceptId1Dvo = dvo;
    }
    
    public ConceptDvo getDomainConceptId1Dvo() {
        return this.domainConceptId1Dvo;
    }
    
    // domainConceptId2Dvo
    
    public void setDomainConceptId2Dvo(ConceptDvo dvo) {
        this.domainConceptId2Dvo = dvo;
    }
    
    public ConceptDvo getDomainConceptId2Dvo() {
        return this.domainConceptId2Dvo;
    }
    
    // relationshipConceptDvo
    
    public void setRelationshipConceptDvo(ConceptDvo dvo) {
        this.relationshipConceptDvo = dvo;
    }
    
    public ConceptDvo getRelationshipConceptDvo() {
        return this.relationshipConceptDvo;
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
