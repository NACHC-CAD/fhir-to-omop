//
// Data Value Object (DVO) for provider
//

package org.nachc.tools.omop.yaorma.dvo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;

import org.yaorma.dvo.Dvo;

public class ProviderDvo implements Dvo {

    //
    // tableName
    //
    
    public static final String TABLE_NAME = "provider";
    
    //
    // schemaName
    //
    
    public static final String SCHEMA_NAME = "synthea_omop";
    
    //
    // columnNames
    //
    
    public static final String[] COLUMN_NAMES = {
        "care_site_id",
        "dea",
        "gender_concept_id",
        "gender_source_concept_id",
        "gender_source_value",
        "npi",
        "provider_id",
        "provider_name",
        "provider_source_value",
        "specialty_concept_id",
        "specialty_source_concept_id",
        "specialty_source_value",
        "year_of_birth"
    };
    
    //
    // primaryKeyColumnNames
    //
    
    public static final String[] PRIMARY_KEY_COLUMN_NAMES = {
        "provider_id"
    };
    
    //
    // javaNames
    //
    
    public static final String[] JAVA_NAMES = {
        "careSiteId",
        "dea",
        "genderConceptId",
        "genderSourceConceptId",
        "genderSourceValue",
        "npi",
        "providerId",
        "providerName",
        "providerSourceValue",
        "specialtyConceptId",
        "specialtySourceConceptId",
        "specialtySourceValue",
        "yearOfBirth"
    };
    
    //
    // javaNamesProper
    //
    
    public static final String[] JAVA_NAMES_PROPER = {
        "CareSiteId",
        "Dea",
        "GenderConceptId",
        "GenderSourceConceptId",
        "GenderSourceValue",
        "Npi",
        "ProviderId",
        "ProviderName",
        "ProviderSourceValue",
        "SpecialtyConceptId",
        "SpecialtySourceConceptId",
        "SpecialtySourceValue",
        "YearOfBirth"
    };
    
    
    //
    // member variables
    //
    
    private HashMap<String, String> descriptions = new HashMap<String, String>();
    
    private Integer careSiteId;
    
    private String dea;
    
    private Integer genderConceptId;
    
    private Integer genderSourceConceptId;
    
    private String genderSourceValue;
    
    private String npi;
    
    private Integer providerId;
    
    private String providerName;
    
    private String providerSourceValue;
    
    private Integer specialtyConceptId;
    
    private Integer specialtySourceConceptId;
    
    private String specialtySourceValue;
    
    private Integer yearOfBirth;
    
    private CareSiteDvo careSiteDvo;
    
    private ConceptDvo genderConceptDvo;
    
    private ConceptDvo genderSourceConceptDvo;
    
    private ConceptDvo specialtyConceptDvo;
    
    private ConceptDvo specialtySourceConceptDvo;
    
    private ArrayList<ConditionOccurrenceDvo> conditionOccurrenceProviderList = new ArrayList<ConditionOccurrenceDvo>();
    
    private ArrayList<DeviceExposureDvo> deviceExposureProviderList = new ArrayList<DeviceExposureDvo>();
    
    private ArrayList<DrugExposureDvo> drugExposureProviderList = new ArrayList<DrugExposureDvo>();
    
    private ArrayList<MeasurementDvo> measurementProviderList = new ArrayList<MeasurementDvo>();
    
    private ArrayList<NoteDvo> noteProviderList = new ArrayList<NoteDvo>();
    
    private ArrayList<ObservationDvo> observationProviderList = new ArrayList<ObservationDvo>();
    
    private ArrayList<PersonDvo> personProviderList = new ArrayList<PersonDvo>();
    
    private ArrayList<ProcedureOccurrenceDvo> procedureOccurrenceProviderList = new ArrayList<ProcedureOccurrenceDvo>();
    
    private ArrayList<VisitDetailDvo> visitDetailProviderList = new ArrayList<VisitDetailDvo>();
    
    private ArrayList<VisitOccurrenceDvo> visitOccurrenceProviderList = new ArrayList<VisitOccurrenceDvo>();
    
    //
    // trivial getters and setters
    //
    
    // careSiteId
    
    public void setCareSiteId(Integer val) {
        this.careSiteId = val;
    }
    
    public Integer getCareSiteId() {
        return this.careSiteId;
    }
    
    // dea
    
    public void setDea(String val) {
        this.dea = val;
    }
    
    public String getDea() {
        return this.dea;
    }
    
    // genderConceptId
    
    public void setGenderConceptId(Integer val) {
        this.genderConceptId = val;
    }
    
    public Integer getGenderConceptId() {
        return this.genderConceptId;
    }
    
    // genderSourceConceptId
    
    public void setGenderSourceConceptId(Integer val) {
        this.genderSourceConceptId = val;
    }
    
    public Integer getGenderSourceConceptId() {
        return this.genderSourceConceptId;
    }
    
    // genderSourceValue
    
    public void setGenderSourceValue(String val) {
        this.genderSourceValue = val;
    }
    
    public String getGenderSourceValue() {
        return this.genderSourceValue;
    }
    
    // npi
    
    public void setNpi(String val) {
        this.npi = val;
    }
    
    public String getNpi() {
        return this.npi;
    }
    
    // providerId
    
    public void setProviderId(Integer val) {
        this.providerId = val;
    }
    
    public Integer getProviderId() {
        return this.providerId;
    }
    
    // providerName
    
    public void setProviderName(String val) {
        this.providerName = val;
    }
    
    public String getProviderName() {
        return this.providerName;
    }
    
    // providerSourceValue
    
    public void setProviderSourceValue(String val) {
        this.providerSourceValue = val;
    }
    
    public String getProviderSourceValue() {
        return this.providerSourceValue;
    }
    
    // specialtyConceptId
    
    public void setSpecialtyConceptId(Integer val) {
        this.specialtyConceptId = val;
    }
    
    public Integer getSpecialtyConceptId() {
        return this.specialtyConceptId;
    }
    
    // specialtySourceConceptId
    
    public void setSpecialtySourceConceptId(Integer val) {
        this.specialtySourceConceptId = val;
    }
    
    public Integer getSpecialtySourceConceptId() {
        return this.specialtySourceConceptId;
    }
    
    // specialtySourceValue
    
    public void setSpecialtySourceValue(String val) {
        this.specialtySourceValue = val;
    }
    
    public String getSpecialtySourceValue() {
        return this.specialtySourceValue;
    }
    
    // yearOfBirth
    
    public void setYearOfBirth(Integer val) {
        this.yearOfBirth = val;
    }
    
    public Integer getYearOfBirth() {
        return this.yearOfBirth;
    }
    
    // careSiteDvo
    
    public void setCareSiteDvo(CareSiteDvo dvo) {
        this.careSiteDvo = dvo;
    }
    
    public CareSiteDvo getCareSiteDvo() {
        return this.careSiteDvo;
    }
    
    // genderConceptDvo
    
    public void setGenderConceptDvo(ConceptDvo dvo) {
        this.genderConceptDvo = dvo;
    }
    
    public ConceptDvo getGenderConceptDvo() {
        return this.genderConceptDvo;
    }
    
    // genderSourceConceptDvo
    
    public void setGenderSourceConceptDvo(ConceptDvo dvo) {
        this.genderSourceConceptDvo = dvo;
    }
    
    public ConceptDvo getGenderSourceConceptDvo() {
        return this.genderSourceConceptDvo;
    }
    
    // specialtyConceptDvo
    
    public void setSpecialtyConceptDvo(ConceptDvo dvo) {
        this.specialtyConceptDvo = dvo;
    }
    
    public ConceptDvo getSpecialtyConceptDvo() {
        return this.specialtyConceptDvo;
    }
    
    // specialtySourceConceptDvo
    
    public void setSpecialtySourceConceptDvo(ConceptDvo dvo) {
        this.specialtySourceConceptDvo = dvo;
    }
    
    public ConceptDvo getSpecialtySourceConceptDvo() {
        return this.specialtySourceConceptDvo;
    }
    
    public ArrayList<ConditionOccurrenceDvo> getConditionOccurrenceProviderList() {
        return conditionOccurrenceProviderList;
    }
    
    public void setConditionOccurrenceProviderList(ArrayList<ConditionOccurrenceDvo> list) {
        this.conditionOccurrenceProviderList = list;
    }
    
    public ArrayList<DeviceExposureDvo> getDeviceExposureProviderList() {
        return deviceExposureProviderList;
    }
    
    public void setDeviceExposureProviderList(ArrayList<DeviceExposureDvo> list) {
        this.deviceExposureProviderList = list;
    }
    
    public ArrayList<DrugExposureDvo> getDrugExposureProviderList() {
        return drugExposureProviderList;
    }
    
    public void setDrugExposureProviderList(ArrayList<DrugExposureDvo> list) {
        this.drugExposureProviderList = list;
    }
    
    public ArrayList<MeasurementDvo> getMeasurementProviderList() {
        return measurementProviderList;
    }
    
    public void setMeasurementProviderList(ArrayList<MeasurementDvo> list) {
        this.measurementProviderList = list;
    }
    
    public ArrayList<NoteDvo> getNoteProviderList() {
        return noteProviderList;
    }
    
    public void setNoteProviderList(ArrayList<NoteDvo> list) {
        this.noteProviderList = list;
    }
    
    public ArrayList<ObservationDvo> getObservationProviderList() {
        return observationProviderList;
    }
    
    public void setObservationProviderList(ArrayList<ObservationDvo> list) {
        this.observationProviderList = list;
    }
    
    public ArrayList<PersonDvo> getPersonProviderList() {
        return personProviderList;
    }
    
    public void setPersonProviderList(ArrayList<PersonDvo> list) {
        this.personProviderList = list;
    }
    
    public ArrayList<ProcedureOccurrenceDvo> getProcedureOccurrenceProviderList() {
        return procedureOccurrenceProviderList;
    }
    
    public void setProcedureOccurrenceProviderList(ArrayList<ProcedureOccurrenceDvo> list) {
        this.procedureOccurrenceProviderList = list;
    }
    
    public ArrayList<VisitDetailDvo> getVisitDetailProviderList() {
        return visitDetailProviderList;
    }
    
    public void setVisitDetailProviderList(ArrayList<VisitDetailDvo> list) {
        this.visitDetailProviderList = list;
    }
    
    public ArrayList<VisitOccurrenceDvo> getVisitOccurrenceProviderList() {
        return visitOccurrenceProviderList;
    }
    
    public void setVisitOccurrenceProviderList(ArrayList<VisitOccurrenceDvo> list) {
        this.visitOccurrenceProviderList = list;
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
            getProviderId()  == null ? null: getProviderId() + ""
        };
        return rtn;
    }
}
