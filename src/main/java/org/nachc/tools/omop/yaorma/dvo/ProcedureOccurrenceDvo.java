//
// Data Value Object (DVO) for PROCEDURE_OCCURRENCE
//

package org.nachc.tools.omop.yaorma.dvo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;
import java.math.BigDecimal;

import org.yaorma.dvo.Dvo;

public class ProcedureOccurrenceDvo implements Dvo {

    //
    // tableName
    //
    
    public static final String TABLE_NAME = "PROCEDURE_OCCURRENCE";
    
    //
    // schemaName
    //
    
    public static final String SCHEMA_NAME = "etl_synthea_1k";
    
    //
    // columnNames
    //
    
    public static final String[] COLUMN_NAMES = {
        "procedure_occurrence_id",
        "person_id",
        "procedure_concept_id",
        "procedure_date",
        "procedure_datetime",
        "procedure_end_date",
        "procedure_end_datetime",
        "procedure_type_concept_id",
        "modifier_concept_id",
        "quantity",
        "provider_id",
        "visit_occurrence_id",
        "visit_detail_id",
        "procedure_source_value",
        "procedure_source_concept_id",
        "modifier_source_value"
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
        "procedureOccurrenceId",
        "personId",
        "procedureConceptId",
        "procedureDate",
        "procedureDatetime",
        "procedureEndDate",
        "procedureEndDatetime",
        "procedureTypeConceptId",
        "modifierConceptId",
        "quantity",
        "providerId",
        "visitOccurrenceId",
        "visitDetailId",
        "procedureSourceValue",
        "procedureSourceConceptId",
        "modifierSourceValue"
    };
    
    //
    // javaNamesProper
    //
    
    public static final String[] JAVA_NAMES_PROPER = {
        "ProcedureOccurrenceId",
        "PersonId",
        "ProcedureConceptId",
        "ProcedureDate",
        "ProcedureDatetime",
        "ProcedureEndDate",
        "ProcedureEndDatetime",
        "ProcedureTypeConceptId",
        "ModifierConceptId",
        "Quantity",
        "ProviderId",
        "VisitOccurrenceId",
        "VisitDetailId",
        "ProcedureSourceValue",
        "ProcedureSourceConceptId",
        "ModifierSourceValue"
    };
    
    
    //
    // member variables
    //
    
    private HashMap<String, String> descriptions = new HashMap<String, String>();
    
    private Integer procedureOccurrenceId;
    
    private Integer personId;
    
    private Integer procedureConceptId;
    
    private Date procedureDate;
    
    private Date procedureDatetime;
    
    private Date procedureEndDate;
    
    private Date procedureEndDatetime;
    
    private Integer procedureTypeConceptId;
    
    private Integer modifierConceptId;
    
    private Integer quantity;
    
    private Integer providerId;
    
    private Integer visitOccurrenceId;
    
    private Integer visitDetailId;
    
    private String procedureSourceValue;
    
    private Integer procedureSourceConceptId;
    
    private String modifierSourceValue;
    
    //
    // trivial getters and setters
    //
    
    // procedureOccurrenceId
    
    public void setProcedureOccurrenceId(Integer val) {
        this.procedureOccurrenceId = val;
    }
    
    public Integer getProcedureOccurrenceId() {
        return this.procedureOccurrenceId;
    }
    
    // personId
    
    public void setPersonId(Integer val) {
        this.personId = val;
    }
    
    public Integer getPersonId() {
        return this.personId;
    }
    
    // procedureConceptId
    
    public void setProcedureConceptId(Integer val) {
        this.procedureConceptId = val;
    }
    
    public Integer getProcedureConceptId() {
        return this.procedureConceptId;
    }
    
    // procedureDate
    
    public void setProcedureDate(Date val) {
        this.procedureDate = val;
    }
    
    public Date getProcedureDate() {
        return this.procedureDate;
    }
    
    // procedureDatetime
    
    public void setProcedureDatetime(Date val) {
        this.procedureDatetime = val;
    }
    
    public Date getProcedureDatetime() {
        return this.procedureDatetime;
    }
    
    // procedureEndDate
    
    public void setProcedureEndDate(Date val) {
        this.procedureEndDate = val;
    }
    
    public Date getProcedureEndDate() {
        return this.procedureEndDate;
    }
    
    // procedureEndDatetime
    
    public void setProcedureEndDatetime(Date val) {
        this.procedureEndDatetime = val;
    }
    
    public Date getProcedureEndDatetime() {
        return this.procedureEndDatetime;
    }
    
    // procedureTypeConceptId
    
    public void setProcedureTypeConceptId(Integer val) {
        this.procedureTypeConceptId = val;
    }
    
    public Integer getProcedureTypeConceptId() {
        return this.procedureTypeConceptId;
    }
    
    // modifierConceptId
    
    public void setModifierConceptId(Integer val) {
        this.modifierConceptId = val;
    }
    
    public Integer getModifierConceptId() {
        return this.modifierConceptId;
    }
    
    // quantity
    
    public void setQuantity(Integer val) {
        this.quantity = val;
    }
    
    public Integer getQuantity() {
        return this.quantity;
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
    
    // procedureSourceValue
    
    public void setProcedureSourceValue(String val) {
        this.procedureSourceValue = val;
    }
    
    public String getProcedureSourceValue() {
        return this.procedureSourceValue;
    }
    
    // procedureSourceConceptId
    
    public void setProcedureSourceConceptId(Integer val) {
        this.procedureSourceConceptId = val;
    }
    
    public Integer getProcedureSourceConceptId() {
        return this.procedureSourceConceptId;
    }
    
    // modifierSourceValue
    
    public void setModifierSourceValue(String val) {
        this.modifierSourceValue = val;
    }
    
    public String getModifierSourceValue() {
        return this.modifierSourceValue;
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
