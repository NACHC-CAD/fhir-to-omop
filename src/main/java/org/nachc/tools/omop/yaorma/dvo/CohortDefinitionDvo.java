//
// Data Value Object (DVO) for COHORT_DEFINITION
//

package org.nachc.tools.omop.yaorma.dvo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;
import java.math.BigDecimal;

import org.yaorma.dvo.Dvo;

public class CohortDefinitionDvo implements Dvo {

    //
    // tableName
    //
    
    public static final String TABLE_NAME = "COHORT_DEFINITION";
    
    //
    // schemaName
    //
    
    public static final String SCHEMA_NAME = "synthea_micro.dbo";
    
    //
    // columnNames
    //
    
    public static final String[] COLUMN_NAMES = {
        "cohort_definition_id",
        "cohort_definition_name",
        "cohort_definition_description",
        "definition_type_concept_id",
        "cohort_definition_syntax",
        "subject_concept_id",
        "cohort_initiation_date"
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
        "cohortDefinitionId",
        "cohortDefinitionName",
        "cohortDefinitionDescription",
        "definitionTypeConceptId",
        "cohortDefinitionSyntax",
        "subjectConceptId",
        "cohortInitiationDate"
    };
    
    //
    // javaNamesProper
    //
    
    public static final String[] JAVA_NAMES_PROPER = {
        "CohortDefinitionId",
        "CohortDefinitionName",
        "CohortDefinitionDescription",
        "DefinitionTypeConceptId",
        "CohortDefinitionSyntax",
        "SubjectConceptId",
        "CohortInitiationDate"
    };
    
    
    //
    // member variables
    //
    
    private HashMap<String, String> descriptions = new HashMap<String, String>();
    
    private Integer cohortDefinitionId;
    
    private String cohortDefinitionName;
    
    private String cohortDefinitionDescription;
    
    private Integer definitionTypeConceptId;
    
    private String cohortDefinitionSyntax;
    
    private Integer subjectConceptId;
    
    private Date cohortInitiationDate;
    
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
    
    // cohortDefinitionName
    
    public void setCohortDefinitionName(String val) {
        this.cohortDefinitionName = val;
    }
    
    public String getCohortDefinitionName() {
        return this.cohortDefinitionName;
    }
    
    // cohortDefinitionDescription
    
    public void setCohortDefinitionDescription(String val) {
        this.cohortDefinitionDescription = val;
    }
    
    public String getCohortDefinitionDescription() {
        return this.cohortDefinitionDescription;
    }
    
    // definitionTypeConceptId
    
    public void setDefinitionTypeConceptId(Integer val) {
        this.definitionTypeConceptId = val;
    }
    
    public Integer getDefinitionTypeConceptId() {
        return this.definitionTypeConceptId;
    }
    
    // cohortDefinitionSyntax
    
    public void setCohortDefinitionSyntax(String val) {
        this.cohortDefinitionSyntax = val;
    }
    
    public String getCohortDefinitionSyntax() {
        return this.cohortDefinitionSyntax;
    }
    
    // subjectConceptId
    
    public void setSubjectConceptId(Integer val) {
        this.subjectConceptId = val;
    }
    
    public Integer getSubjectConceptId() {
        return this.subjectConceptId;
    }
    
    // cohortInitiationDate
    
    public void setCohortInitiationDate(Date val) {
        this.cohortInitiationDate = val;
    }
    
    public Date getCohortInitiationDate() {
        return this.cohortInitiationDate;
    }
    
    //
    // implementation of Dvo
    //
    
    public String getTableName() {
        return TABLE_NAME;
    };
    
    public String getSchemaName() {
        return org.nachc.tools.fhirtoomop.util.params.AppParams.getFullySpecifiedCdmSchemaName();
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
