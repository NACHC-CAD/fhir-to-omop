//
// Data Value Object (DVO) for cohort
//

package org.nachc.tools.omop.yaorma.dvo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;

import org.yaorma.dvo.Dvo;

public class CohortDvo implements Dvo {

    //
    // tableName
    //
    
    public static final String TABLE_NAME = "cohort";
    
    //
    // schemaName
    //
    
    public static final String SCHEMA_NAME = "synthea_omop";
    
    //
    // columnNames
    //
    
    public static final String[] COLUMN_NAMES = {
        "cohort_definition_id",
        "subject_id",
        "cohort_start_date",
        "cohort_end_date"
    };
    
    //
    // primaryKeyColumnNames
    //
    
    public static final String[] PRIMARY_KEY_COLUMN_NAMES = {
        "cohort_definition_id"
    };
    
    //
    // javaNames
    //
    
    public static final String[] JAVA_NAMES = {
        "cohortDefinitionId",
        "subjectId",
        "cohortStartDate",
        "cohortEndDate"
    };
    
    //
    // javaNamesProper
    //
    
    public static final String[] JAVA_NAMES_PROPER = {
        "CohortDefinitionId",
        "SubjectId",
        "CohortStartDate",
        "CohortEndDate"
    };
    
    
    //
    // member variables
    //
    
    private HashMap<String, String> descriptions = new HashMap<String, String>();
    
    private Integer cohortDefinitionId;
    
    private Integer subjectId;
    
    private Date cohortStartDate;
    
    private Date cohortEndDate;
    
    private ArrayList<CohortDefinitionDvo> cohortDefinitionCohortDefinitionList = new ArrayList<CohortDefinitionDvo>();
    
    //
    // trivial getters and setters
    //
    
    // cohortDefinitionId
    
    public void setCohortDefinitionId(Integer val) {
        this.cohortDefinitionId = val;
    }
    
    public Integer getCohortDefinitionId() {
        return this.cohortDefinitionId;
    }
    
    // subjectId
    
    public void setSubjectId(Integer val) {
        this.subjectId = val;
    }
    
    public Integer getSubjectId() {
        return this.subjectId;
    }
    
    // cohortStartDate
    
    public void setCohortStartDate(Date val) {
        this.cohortStartDate = val;
    }
    
    public Date getCohortStartDate() {
        return this.cohortStartDate;
    }
    
    // cohortEndDate
    
    public void setCohortEndDate(Date val) {
        this.cohortEndDate = val;
    }
    
    public Date getCohortEndDate() {
        return this.cohortEndDate;
    }
    
    public ArrayList<CohortDefinitionDvo> getCohortDefinitionCohortDefinitionList() {
        return cohortDefinitionCohortDefinitionList;
    }
    
    public void setCohortDefinitionCohortDefinitionList(ArrayList<CohortDefinitionDvo> list) {
        this.cohortDefinitionCohortDefinitionList = list;
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
            getCohortDefinitionId()  == null ? null: getCohortDefinitionId() + ""
        };
        return rtn;
    }
}
