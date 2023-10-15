//
// Data Value Object (DVO) for CONCEPT_RELATIONSHIP
//

package org.nachc.tools.omop.yaorma.dvo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;
import java.math.BigDecimal;

import org.yaorma.dvo.Dvo;

public class ConceptRelationshipDvo implements Dvo {

    //
    // tableName
    //
    
    public static final String TABLE_NAME = "CONCEPT_RELATIONSHIP";
    
    //
    // schemaName
    //
    
    public static final String SCHEMA_NAME = "etl_synthea_1k";
    
    //
    // columnNames
    //
    
    public static final String[] COLUMN_NAMES = {
        "concept_id_1",
        "concept_id_2",
        "relationship_id",
        "valid_start_date",
        "valid_end_date",
        "invalid_reason"
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
        "conceptId1",
        "conceptId2",
        "relationshipId",
        "validStartDate",
        "validEndDate",
        "invalidReason"
    };
    
    //
    // javaNamesProper
    //
    
    public static final String[] JAVA_NAMES_PROPER = {
        "ConceptId1",
        "ConceptId2",
        "RelationshipId",
        "ValidStartDate",
        "ValidEndDate",
        "InvalidReason"
    };
    
    
    //
    // member variables
    //
    
    private HashMap<String, String> descriptions = new HashMap<String, String>();
    
    private Integer conceptId1;
    
    private Integer conceptId2;
    
    private String relationshipId;
    
    private Date validStartDate;
    
    private Date validEndDate;
    
    private String invalidReason;
    
    //
    // trivial getters and setters
    //
    
    // conceptId1
    
    public void setConceptId1(Integer val) {
        this.conceptId1 = val;
    }
    
    public Integer getConceptId1() {
        return this.conceptId1;
    }
    
    // conceptId2
    
    public void setConceptId2(Integer val) {
        this.conceptId2 = val;
    }
    
    public Integer getConceptId2() {
        return this.conceptId2;
    }
    
    // relationshipId
    
    public void setRelationshipId(String val) {
        this.relationshipId = val;
    }
    
    public String getRelationshipId() {
        return this.relationshipId;
    }
    
    // validStartDate
    
    public void setValidStartDate(Date val) {
        this.validStartDate = val;
    }
    
    public Date getValidStartDate() {
        return this.validStartDate;
    }
    
    // validEndDate
    
    public void setValidEndDate(Date val) {
        this.validEndDate = val;
    }
    
    public Date getValidEndDate() {
        return this.validEndDate;
    }
    
    // invalidReason
    
    public void setInvalidReason(String val) {
        this.invalidReason = val;
    }
    
    public String getInvalidReason() {
        return this.invalidReason;
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
