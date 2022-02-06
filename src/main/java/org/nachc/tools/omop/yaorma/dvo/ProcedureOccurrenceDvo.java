//
// Data Value Object (DVO) for procedure_occurrence
//

package org.nachc.tools.omop.yaorma.dvo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;

import org.yaorma.dvo.Dvo;

public class ProcedureOccurrenceDvo implements Dvo {

    //
    // tableName
    //
    
    public static final String TABLE_NAME = "procedure_occurrence";
    
    //
    // schemaName
    //
    
    public static final String SCHEMA_NAME = "synthea_omop";
    
    //
    // columnNames
    //
    
    public static final String[] COLUMN_NAMES = {
        "modifier_concept_id",
        "modifier_source_value",
        "person_id",
        "procedure_concept_id",
        "procedure_date",
        "procedure_datetime",
        "procedure_end_date",
        "procedure_end_datetime",
        "procedure_occurrence_id",
        "procedure_source_concept_id",
        "procedure_source_value",
        "procedure_type_concept_id",
        "provider_id",
        "quantity",
        "visit_detail_id",
        "visit_occurrence_id"
    };
    
    //
    // primaryKeyColumnNames
    //
    
    public static final String[] PRIMARY_KEY_COLUMN_NAMES = {
        "procedure_occurrence_id"
    };
    
    //
    // javaNames
    //
    
    public static final String[] JAVA_NAMES = {
        "modifierConceptId",
        "modifierSourceValue",
        "personId",
        "procedureConceptId",
        "procedureDate",
        "procedureDatetime",
        "procedureEndDate",
        "procedureEndDatetime",
        "procedureOccurrenceId",
        "procedureSourceConceptId",
        "procedureSourceValue",
        "procedureTypeConceptId",
        "providerId",
        "quantity",
        "visitDetailId",
        "visitOccurrenceId"
    };
    
    //
    // javaNamesProper
    //
    
    public static final String[] JAVA_NAMES_PROPER = {
        "ModifierConceptId",
        "ModifierSourceValue",
        "PersonId",
        "ProcedureConceptId",
        "ProcedureDate",
        "ProcedureDatetime",
        "ProcedureEndDate",
        "ProcedureEndDatetime",
        "ProcedureOccurrenceId",
        "ProcedureSourceConceptId",
        "ProcedureSourceValue",
        "ProcedureTypeConceptId",
        "ProviderId",
        "Quantity",
        "VisitDetailId",
        "VisitOccurrenceId"
    };
    
    
    //
    // member variables
    //
    
    private HashMap<String, String> descriptions = new HashMap<String, String>();
    
    private Integer modifierConceptId;
    
    private String modifierSourceValue;
    
    private Integer personId;
    
    private Integer procedureConceptId;
    
    private Date procedureDate;
    
    private String procedureDatetime;
    
    private Date procedureEndDate;
    
    private String procedureEndDatetime;
    
    private Integer procedureOccurrenceId;
    
    private Integer procedureSourceConceptId;
    
    private String procedureSourceValue;
    
    private Integer procedureTypeConceptId;
    
    private Integer providerId;
    
    private Integer quantity;
    
    private Integer visitDetailId;
    
    private Integer visitOccurrenceId;
    
    private ConceptDvo modifierConceptDvo;
    
    private PersonDvo personDvo;
    
    private ConceptDvo procedureConceptDvo;
    
    private ConceptDvo procedureSourceConceptDvo;
    
    private ConceptDvo procedureTypeConceptDvo;
    
    private ProviderDvo providerDvo;
    
    private VisitDetailDvo visitDetailDvo;
    
    private VisitOccurrenceDvo visitOccurrenceDvo;
    
    //
    // trivial getters and setters
    //
    
    // modifierConceptId
    
    public void setModifierConceptId(Integer val) {
        this.modifierConceptId = val;
    }
    
    public Integer getModifierConceptId() {
        return this.modifierConceptId;
    }
    
    // modifierSourceValue
    
    public void setModifierSourceValue(String val) {
        this.modifierSourceValue = val;
    }
    
    public String getModifierSourceValue() {
        return this.modifierSourceValue;
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
    
    public void setProcedureDatetime(String val) {
        this.procedureDatetime = val;
    }
    
    public String getProcedureDatetime() {
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
    
    public void setProcedureEndDatetime(String val) {
        this.procedureEndDatetime = val;
    }
    
    public String getProcedureEndDatetime() {
        return this.procedureEndDatetime;
    }
    
    // procedureOccurrenceId
    
    public void setProcedureOccurrenceId(Integer val) {
        this.procedureOccurrenceId = val;
    }
    
    public Integer getProcedureOccurrenceId() {
        return this.procedureOccurrenceId;
    }
    
    // procedureSourceConceptId
    
    public void setProcedureSourceConceptId(Integer val) {
        this.procedureSourceConceptId = val;
    }
    
    public Integer getProcedureSourceConceptId() {
        return this.procedureSourceConceptId;
    }
    
    // procedureSourceValue
    
    public void setProcedureSourceValue(String val) {
        this.procedureSourceValue = val;
    }
    
    public String getProcedureSourceValue() {
        return this.procedureSourceValue;
    }
    
    // procedureTypeConceptId
    
    public void setProcedureTypeConceptId(Integer val) {
        this.procedureTypeConceptId = val;
    }
    
    public Integer getProcedureTypeConceptId() {
        return this.procedureTypeConceptId;
    }
    
    // providerId
    
    public void setProviderId(Integer val) {
        this.providerId = val;
    }
    
    public Integer getProviderId() {
        return this.providerId;
    }
    
    // quantity
    
    public void setQuantity(Integer val) {
        this.quantity = val;
    }
    
    public Integer getQuantity() {
        return this.quantity;
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
    
    // modifierConceptDvo
    
    public void setModifierConceptDvo(ConceptDvo dvo) {
        this.modifierConceptDvo = dvo;
    }
    
    public ConceptDvo getModifierConceptDvo() {
        return this.modifierConceptDvo;
    }
    
    // personDvo
    
    public void setPersonDvo(PersonDvo dvo) {
        this.personDvo = dvo;
    }
    
    public PersonDvo getPersonDvo() {
        return this.personDvo;
    }
    
    // procedureConceptDvo
    
    public void setProcedureConceptDvo(ConceptDvo dvo) {
        this.procedureConceptDvo = dvo;
    }
    
    public ConceptDvo getProcedureConceptDvo() {
        return this.procedureConceptDvo;
    }
    
    // procedureSourceConceptDvo
    
    public void setProcedureSourceConceptDvo(ConceptDvo dvo) {
        this.procedureSourceConceptDvo = dvo;
    }
    
    public ConceptDvo getProcedureSourceConceptDvo() {
        return this.procedureSourceConceptDvo;
    }
    
    // procedureTypeConceptDvo
    
    public void setProcedureTypeConceptDvo(ConceptDvo dvo) {
        this.procedureTypeConceptDvo = dvo;
    }
    
    public ConceptDvo getProcedureTypeConceptDvo() {
        return this.procedureTypeConceptDvo;
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
            getProcedureOccurrenceId()  == null ? null: getProcedureOccurrenceId() + ""
        };
        return rtn;
    }
}
