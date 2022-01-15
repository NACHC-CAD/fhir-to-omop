//
// Data Value Object (DVO) for visit_occurrence
//

package org.nachc.tools.omop.yaorma.dvo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;

import org.yaorma.dvo.Dvo;

public class VisitOccurrenceDvo implements Dvo {

    //
    // tableName
    //
    
    public static final String TABLE_NAME = "visit_occurrence";
    
    //
    // schemaName
    //
    
    public static final String SCHEMA_NAME = "synthea_omop";
    
    //
    // columnNames
    //
    
    public static final String[] COLUMN_NAMES = {
        "visit_occurrence_id",
        "person_id",
        "visit_concept_id",
        "visit_start_date",
        "visit_start_datetime",
        "visit_end_date",
        "visit_end_datetime",
        "visit_type_concept_id",
        "provider_id",
        "care_site_id",
        "visit_source_value",
        "visit_source_concept_id",
        "admitted_from_concept_id",
        "admitted_from_source_value",
        "discharged_to_concept_id",
        "discharged_to_source_value",
        "preceding_visit_occurrence_id"
    };
    
    //
    // primaryKeyColumnNames
    //
    
    public static final String[] PRIMARY_KEY_COLUMN_NAMES = {
        "visit_occurrence_id"
    };
    
    //
    // javaNames
    //
    
    public static final String[] JAVA_NAMES = {
        "visitOccurrenceId",
        "personId",
        "visitConceptId",
        "visitStartDate",
        "visitStartDatetime",
        "visitEndDate",
        "visitEndDatetime",
        "visitTypeConceptId",
        "providerId",
        "careSiteId",
        "visitSourceValue",
        "visitSourceConceptId",
        "admittedFromConceptId",
        "admittedFromSourceValue",
        "dischargedToConceptId",
        "dischargedToSourceValue",
        "precedingVisitOccurrenceId"
    };
    
    //
    // javaNamesProper
    //
    
    public static final String[] JAVA_NAMES_PROPER = {
        "VisitOccurrenceId",
        "PersonId",
        "VisitConceptId",
        "VisitStartDate",
        "VisitStartDatetime",
        "VisitEndDate",
        "VisitEndDatetime",
        "VisitTypeConceptId",
        "ProviderId",
        "CareSiteId",
        "VisitSourceValue",
        "VisitSourceConceptId",
        "AdmittedFromConceptId",
        "AdmittedFromSourceValue",
        "DischargedToConceptId",
        "DischargedToSourceValue",
        "PrecedingVisitOccurrenceId"
    };
    
    
    //
    // member variables
    //
    
    private HashMap<String, String> descriptions = new HashMap<String, String>();
    
    private Integer visitOccurrenceId;
    
    private Integer personId;
    
    private Integer visitConceptId;
    
    private Date visitStartDate;
    
    private String visitStartDatetime;
    
    private Date visitEndDate;
    
    private String visitEndDatetime;
    
    private Integer visitTypeConceptId;
    
    private Integer providerId;
    
    private Integer careSiteId;
    
    private String visitSourceValue;
    
    private Integer visitSourceConceptId;
    
    private Integer admittedFromConceptId;
    
    private String admittedFromSourceValue;
    
    private Integer dischargedToConceptId;
    
    private String dischargedToSourceValue;
    
    private Integer precedingVisitOccurrenceId;
    
    private ConceptDvo admittedFromConceptDvo;
    
    private CareSiteDvo careSiteDvo;
    
    private ConceptDvo dischargedToConceptDvo;
    
    private PersonDvo personDvo;
    
    private VisitOccurrenceDvo precedingVisitOccurrenceDvo;
    
    private ProviderDvo providerDvo;
    
    private ConceptDvo visitConceptDvo;
    
    private ConceptDvo visitSourceConceptDvo;
    
    private ConceptDvo visitTypeConceptDvo;
    
    private ArrayList<VisitOccurrenceDvo> visitOccurrencePrecedingVisitOccurrenceList = new ArrayList<VisitOccurrenceDvo>();
    
    private ArrayList<VisitDetailDvo> visitDetailVisitOccurrenceList = new ArrayList<VisitDetailDvo>();
    
    private ArrayList<ConditionOccurrenceDvo> conditionOccurrenceVisitOccurrenceList = new ArrayList<ConditionOccurrenceDvo>();
    
    private ArrayList<DrugExposureDvo> drugExposureVisitOccurrenceList = new ArrayList<DrugExposureDvo>();
    
    private ArrayList<ProcedureOccurrenceDvo> procedureOccurrenceVisitOccurrenceList = new ArrayList<ProcedureOccurrenceDvo>();
    
    private ArrayList<DeviceExposureDvo> deviceExposureVisitOccurrenceList = new ArrayList<DeviceExposureDvo>();
    
    private ArrayList<MeasurementDvo> measurementVisitOccurrenceList = new ArrayList<MeasurementDvo>();
    
    private ArrayList<ObservationDvo> observationVisitOccurrenceList = new ArrayList<ObservationDvo>();
    
    private ArrayList<NoteDvo> noteVisitOccurrenceList = new ArrayList<NoteDvo>();
    
    //
    // trivial getters and setters
    //
    
    // visitOccurrenceId
    
    public void setVisitOccurrenceId(Integer val) {
        this.visitOccurrenceId = val;
    }
    
    public Integer getVisitOccurrenceId() {
        return this.visitOccurrenceId;
    }
    
    // personId
    
    public void setPersonId(Integer val) {
        this.personId = val;
    }
    
    public Integer getPersonId() {
        return this.personId;
    }
    
    // visitConceptId
    
    public void setVisitConceptId(Integer val) {
        this.visitConceptId = val;
    }
    
    public Integer getVisitConceptId() {
        return this.visitConceptId;
    }
    
    // visitStartDate
    
    public void setVisitStartDate(Date val) {
        this.visitStartDate = val;
    }
    
    public Date getVisitStartDate() {
        return this.visitStartDate;
    }
    
    // visitStartDatetime
    
    public void setVisitStartDatetime(String val) {
        this.visitStartDatetime = val;
    }
    
    public String getVisitStartDatetime() {
        return this.visitStartDatetime;
    }
    
    // visitEndDate
    
    public void setVisitEndDate(Date val) {
        this.visitEndDate = val;
    }
    
    public Date getVisitEndDate() {
        return this.visitEndDate;
    }
    
    // visitEndDatetime
    
    public void setVisitEndDatetime(String val) {
        this.visitEndDatetime = val;
    }
    
    public String getVisitEndDatetime() {
        return this.visitEndDatetime;
    }
    
    // visitTypeConceptId
    
    public void setVisitTypeConceptId(Integer val) {
        this.visitTypeConceptId = val;
    }
    
    public Integer getVisitTypeConceptId() {
        return this.visitTypeConceptId;
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
    
    // visitSourceValue
    
    public void setVisitSourceValue(String val) {
        this.visitSourceValue = val;
    }
    
    public String getVisitSourceValue() {
        return this.visitSourceValue;
    }
    
    // visitSourceConceptId
    
    public void setVisitSourceConceptId(Integer val) {
        this.visitSourceConceptId = val;
    }
    
    public Integer getVisitSourceConceptId() {
        return this.visitSourceConceptId;
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
    
    // dischargedToConceptId
    
    public void setDischargedToConceptId(Integer val) {
        this.dischargedToConceptId = val;
    }
    
    public Integer getDischargedToConceptId() {
        return this.dischargedToConceptId;
    }
    
    // dischargedToSourceValue
    
    public void setDischargedToSourceValue(String val) {
        this.dischargedToSourceValue = val;
    }
    
    public String getDischargedToSourceValue() {
        return this.dischargedToSourceValue;
    }
    
    // precedingVisitOccurrenceId
    
    public void setPrecedingVisitOccurrenceId(Integer val) {
        this.precedingVisitOccurrenceId = val;
    }
    
    public Integer getPrecedingVisitOccurrenceId() {
        return this.precedingVisitOccurrenceId;
    }
    
    // admittedFromConceptDvo
    
    public void setAdmittedFromConceptDvo(ConceptDvo dvo) {
        this.admittedFromConceptDvo = dvo;
    }
    
    public ConceptDvo getAdmittedFromConceptDvo() {
        return this.admittedFromConceptDvo;
    }
    
    // careSiteDvo
    
    public void setCareSiteDvo(CareSiteDvo dvo) {
        this.careSiteDvo = dvo;
    }
    
    public CareSiteDvo getCareSiteDvo() {
        return this.careSiteDvo;
    }
    
    // dischargedToConceptDvo
    
    public void setDischargedToConceptDvo(ConceptDvo dvo) {
        this.dischargedToConceptDvo = dvo;
    }
    
    public ConceptDvo getDischargedToConceptDvo() {
        return this.dischargedToConceptDvo;
    }
    
    // personDvo
    
    public void setPersonDvo(PersonDvo dvo) {
        this.personDvo = dvo;
    }
    
    public PersonDvo getPersonDvo() {
        return this.personDvo;
    }
    
    // precedingVisitOccurrenceDvo
    
    public void setPrecedingVisitOccurrenceDvo(VisitOccurrenceDvo dvo) {
        this.precedingVisitOccurrenceDvo = dvo;
    }
    
    public VisitOccurrenceDvo getPrecedingVisitOccurrenceDvo() {
        return this.precedingVisitOccurrenceDvo;
    }
    
    // providerDvo
    
    public void setProviderDvo(ProviderDvo dvo) {
        this.providerDvo = dvo;
    }
    
    public ProviderDvo getProviderDvo() {
        return this.providerDvo;
    }
    
    // visitConceptDvo
    
    public void setVisitConceptDvo(ConceptDvo dvo) {
        this.visitConceptDvo = dvo;
    }
    
    public ConceptDvo getVisitConceptDvo() {
        return this.visitConceptDvo;
    }
    
    // visitSourceConceptDvo
    
    public void setVisitSourceConceptDvo(ConceptDvo dvo) {
        this.visitSourceConceptDvo = dvo;
    }
    
    public ConceptDvo getVisitSourceConceptDvo() {
        return this.visitSourceConceptDvo;
    }
    
    // visitTypeConceptDvo
    
    public void setVisitTypeConceptDvo(ConceptDvo dvo) {
        this.visitTypeConceptDvo = dvo;
    }
    
    public ConceptDvo getVisitTypeConceptDvo() {
        return this.visitTypeConceptDvo;
    }
    
    public ArrayList<VisitOccurrenceDvo> getVisitOccurrencePrecedingVisitOccurrenceList() {
        return visitOccurrencePrecedingVisitOccurrenceList;
    }
    
    public void setVisitOccurrencePrecedingVisitOccurrenceList(ArrayList<VisitOccurrenceDvo> list) {
        this.visitOccurrencePrecedingVisitOccurrenceList = list;
    }
    
    public ArrayList<VisitDetailDvo> getVisitDetailVisitOccurrenceList() {
        return visitDetailVisitOccurrenceList;
    }
    
    public void setVisitDetailVisitOccurrenceList(ArrayList<VisitDetailDvo> list) {
        this.visitDetailVisitOccurrenceList = list;
    }
    
    public ArrayList<ConditionOccurrenceDvo> getConditionOccurrenceVisitOccurrenceList() {
        return conditionOccurrenceVisitOccurrenceList;
    }
    
    public void setConditionOccurrenceVisitOccurrenceList(ArrayList<ConditionOccurrenceDvo> list) {
        this.conditionOccurrenceVisitOccurrenceList = list;
    }
    
    public ArrayList<DrugExposureDvo> getDrugExposureVisitOccurrenceList() {
        return drugExposureVisitOccurrenceList;
    }
    
    public void setDrugExposureVisitOccurrenceList(ArrayList<DrugExposureDvo> list) {
        this.drugExposureVisitOccurrenceList = list;
    }
    
    public ArrayList<ProcedureOccurrenceDvo> getProcedureOccurrenceVisitOccurrenceList() {
        return procedureOccurrenceVisitOccurrenceList;
    }
    
    public void setProcedureOccurrenceVisitOccurrenceList(ArrayList<ProcedureOccurrenceDvo> list) {
        this.procedureOccurrenceVisitOccurrenceList = list;
    }
    
    public ArrayList<DeviceExposureDvo> getDeviceExposureVisitOccurrenceList() {
        return deviceExposureVisitOccurrenceList;
    }
    
    public void setDeviceExposureVisitOccurrenceList(ArrayList<DeviceExposureDvo> list) {
        this.deviceExposureVisitOccurrenceList = list;
    }
    
    public ArrayList<MeasurementDvo> getMeasurementVisitOccurrenceList() {
        return measurementVisitOccurrenceList;
    }
    
    public void setMeasurementVisitOccurrenceList(ArrayList<MeasurementDvo> list) {
        this.measurementVisitOccurrenceList = list;
    }
    
    public ArrayList<ObservationDvo> getObservationVisitOccurrenceList() {
        return observationVisitOccurrenceList;
    }
    
    public void setObservationVisitOccurrenceList(ArrayList<ObservationDvo> list) {
        this.observationVisitOccurrenceList = list;
    }
    
    public ArrayList<NoteDvo> getNoteVisitOccurrenceList() {
        return noteVisitOccurrenceList;
    }
    
    public void setNoteVisitOccurrenceList(ArrayList<NoteDvo> list) {
        this.noteVisitOccurrenceList = list;
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
            getVisitOccurrenceId()  == null ? null: getVisitOccurrenceId() + ""
        };
        return rtn;
    }
}
