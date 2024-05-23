//
// Data Value Object (DVO) for VISIT_DETAIL
//

package org.nachc.tools.omop.yaorma.dvo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;
import java.math.BigDecimal;

import org.yaorma.dvo.Dvo;

public class VisitDetailDvo implements Dvo {

    //
    // tableName
    //
    
    public static final String TABLE_NAME = "VISIT_DETAIL";
    
    //
    // schemaName
    //
    
    public static final String SCHEMA_NAME = "synthea_micro.dbo";
    
    //
    // columnNames
    //
    
    public static final String[] COLUMN_NAMES = {
        "visit_detail_id",
        "person_id",
        "visit_detail_concept_id",
        "visit_detail_start_date",
        "visit_detail_start_datetime",
        "visit_detail_end_date",
        "visit_detail_end_datetime",
        "visit_detail_type_concept_id",
        "provider_id",
        "care_site_id",
        "visit_detail_source_value",
        "visit_detail_source_concept_id",
        "admitted_from_concept_id",
        "admitted_from_source_value",
        "discharged_to_source_value",
        "discharged_to_concept_id",
        "preceding_visit_detail_id",
        "parent_visit_detail_id",
        "visit_occurrence_id"
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
        "visitDetailId",
        "personId",
        "visitDetailConceptId",
        "visitDetailStartDate",
        "visitDetailStartDatetime",
        "visitDetailEndDate",
        "visitDetailEndDatetime",
        "visitDetailTypeConceptId",
        "providerId",
        "careSiteId",
        "visitDetailSourceValue",
        "visitDetailSourceConceptId",
        "admittedFromConceptId",
        "admittedFromSourceValue",
        "dischargedToSourceValue",
        "dischargedToConceptId",
        "precedingVisitDetailId",
        "parentVisitDetailId",
        "visitOccurrenceId"
    };
    
    //
    // javaNamesProper
    //
    
    public static final String[] JAVA_NAMES_PROPER = {
        "VisitDetailId",
        "PersonId",
        "VisitDetailConceptId",
        "VisitDetailStartDate",
        "VisitDetailStartDatetime",
        "VisitDetailEndDate",
        "VisitDetailEndDatetime",
        "VisitDetailTypeConceptId",
        "ProviderId",
        "CareSiteId",
        "VisitDetailSourceValue",
        "VisitDetailSourceConceptId",
        "AdmittedFromConceptId",
        "AdmittedFromSourceValue",
        "DischargedToSourceValue",
        "DischargedToConceptId",
        "PrecedingVisitDetailId",
        "ParentVisitDetailId",
        "VisitOccurrenceId"
    };
    
    
    //
    // member variables
    //
    
    private HashMap<String, String> descriptions = new HashMap<String, String>();
    
    private Integer visitDetailId;
    
    private Integer personId;
    
    private Integer visitDetailConceptId;
    
    private Date visitDetailStartDate;
    
    private Date visitDetailStartDatetime;
    
    private Date visitDetailEndDate;
    
    private Date visitDetailEndDatetime;
    
    private Integer visitDetailTypeConceptId;
    
    private Integer providerId;
    
    private Integer careSiteId;
    
    private String visitDetailSourceValue;
    
    private Integer visitDetailSourceConceptId;
    
    private Integer admittedFromConceptId;
    
    private String admittedFromSourceValue;
    
    private String dischargedToSourceValue;
    
    private Integer dischargedToConceptId;
    
    private Integer precedingVisitDetailId;
    
    private Integer parentVisitDetailId;
    
    private Integer visitOccurrenceId;
    
    //
    // trivial getters and setters
    //
    
    // visitDetailId
    
    public void setVisitDetailId(Integer val) {
        this.visitDetailId = val;
    }
    
    public Integer getVisitDetailId() {
        return this.visitDetailId;
    }
    
    // personId
    
    public void setPersonId(Integer val) {
        this.personId = val;
    }
    
    public Integer getPersonId() {
        return this.personId;
    }
    
    // visitDetailConceptId
    
    public void setVisitDetailConceptId(Integer val) {
        this.visitDetailConceptId = val;
    }
    
    public Integer getVisitDetailConceptId() {
        return this.visitDetailConceptId;
    }
    
    // visitDetailStartDate
    
    public void setVisitDetailStartDate(Date val) {
        this.visitDetailStartDate = val;
    }
    
    public Date getVisitDetailStartDate() {
        return this.visitDetailStartDate;
    }
    
    // visitDetailStartDatetime
    
    public void setVisitDetailStartDatetime(Date val) {
        this.visitDetailStartDatetime = val;
    }
    
    public Date getVisitDetailStartDatetime() {
        return this.visitDetailStartDatetime;
    }
    
    // visitDetailEndDate
    
    public void setVisitDetailEndDate(Date val) {
        this.visitDetailEndDate = val;
    }
    
    public Date getVisitDetailEndDate() {
        return this.visitDetailEndDate;
    }
    
    // visitDetailEndDatetime
    
    public void setVisitDetailEndDatetime(Date val) {
        this.visitDetailEndDatetime = val;
    }
    
    public Date getVisitDetailEndDatetime() {
        return this.visitDetailEndDatetime;
    }
    
    // visitDetailTypeConceptId
    
    public void setVisitDetailTypeConceptId(Integer val) {
        this.visitDetailTypeConceptId = val;
    }
    
    public Integer getVisitDetailTypeConceptId() {
        return this.visitDetailTypeConceptId;
    }
    
    // providerId
    
    public void setProviderId(Integer val) {
        this.providerId = val;
    }
    
    public Integer getProviderId() {
        return this.providerId;
    }
    
    // careSiteId
    
    public void setCareSiteId(Integer val) {
        this.careSiteId = val;
    }
    
    public Integer getCareSiteId() {
        return this.careSiteId;
    }
    
    // visitDetailSourceValue
    
    public void setVisitDetailSourceValue(String val) {
        this.visitDetailSourceValue = val;
    }
    
    public String getVisitDetailSourceValue() {
        return this.visitDetailSourceValue;
    }
    
    // visitDetailSourceConceptId
    
    public void setVisitDetailSourceConceptId(Integer val) {
        this.visitDetailSourceConceptId = val;
    }
    
    public Integer getVisitDetailSourceConceptId() {
        return this.visitDetailSourceConceptId;
    }
    
    // admittedFromConceptId
    
    public void setAdmittedFromConceptId(Integer val) {
        this.admittedFromConceptId = val;
    }
    
    public Integer getAdmittedFromConceptId() {
        return this.admittedFromConceptId;
    }
    
    // admittedFromSourceValue
    
    public void setAdmittedFromSourceValue(String val) {
        this.admittedFromSourceValue = val;
    }
    
    public String getAdmittedFromSourceValue() {
        return this.admittedFromSourceValue;
    }
    
    // dischargedToSourceValue
    
    public void setDischargedToSourceValue(String val) {
        this.dischargedToSourceValue = val;
    }
    
    public String getDischargedToSourceValue() {
        return this.dischargedToSourceValue;
    }
    
    // dischargedToConceptId
    
    public void setDischargedToConceptId(Integer val) {
        this.dischargedToConceptId = val;
    }
    
    public Integer getDischargedToConceptId() {
        return this.dischargedToConceptId;
    }
    
    // precedingVisitDetailId
    
    public void setPrecedingVisitDetailId(Integer val) {
        this.precedingVisitDetailId = val;
    }
    
    public Integer getPrecedingVisitDetailId() {
        return this.precedingVisitDetailId;
    }
    
    // parentVisitDetailId
    
    public void setParentVisitDetailId(Integer val) {
        this.parentVisitDetailId = val;
    }
    
    public Integer getParentVisitDetailId() {
        return this.parentVisitDetailId;
    }
    
    // visitOccurrenceId
    
    public void setVisitOccurrenceId(Integer val) {
        this.visitOccurrenceId = val;
    }
    
    public Integer getVisitOccurrenceId() {
        return this.visitOccurrenceId;
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
