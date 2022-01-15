//
// Data Value Object (DVO) for person
//

package org.nachc.tools.omop.yaorma.dvo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;

import org.yaorma.dvo.Dvo;

public class PersonDvo implements Dvo {

    //
    // tableName
    //
    
    public static final String TABLE_NAME = "person";
    
    //
    // schemaName
    //
    
    public static final String SCHEMA_NAME = "synthea_omop";
    
    //
    // columnNames
    //
    
    public static final String[] COLUMN_NAMES = {
        "person_id",
        "gender_concept_id",
        "year_of_birth",
        "month_of_birth",
        "day_of_birth",
        "birth_datetime",
        "race_concept_id",
        "ethnicity_concept_id",
        "location_id",
        "provider_id",
        "care_site_id",
        "person_source_value",
        "gender_source_value",
        "gender_source_concept_id",
        "race_source_value",
        "race_source_concept_id",
        "ethnicity_source_value",
        "ethnicity_source_concept_id"
    };
    
    //
    // primaryKeyColumnNames
    //
    
    public static final String[] PRIMARY_KEY_COLUMN_NAMES = {
        "person_id"
    };
    
    //
    // javaNames
    //
    
    public static final String[] JAVA_NAMES = {
        "personId",
        "genderConceptId",
        "yearOfBirth",
        "monthOfBirth",
        "dayOfBirth",
        "birthDatetime",
        "raceConceptId",
        "ethnicityConceptId",
        "locationId",
        "providerId",
        "careSiteId",
        "personSourceValue",
        "genderSourceValue",
        "genderSourceConceptId",
        "raceSourceValue",
        "raceSourceConceptId",
        "ethnicitySourceValue",
        "ethnicitySourceConceptId"
    };
    
    //
    // javaNamesProper
    //
    
    public static final String[] JAVA_NAMES_PROPER = {
        "PersonId",
        "GenderConceptId",
        "YearOfBirth",
        "MonthOfBirth",
        "DayOfBirth",
        "BirthDatetime",
        "RaceConceptId",
        "EthnicityConceptId",
        "LocationId",
        "ProviderId",
        "CareSiteId",
        "PersonSourceValue",
        "GenderSourceValue",
        "GenderSourceConceptId",
        "RaceSourceValue",
        "RaceSourceConceptId",
        "EthnicitySourceValue",
        "EthnicitySourceConceptId"
    };
    
    
    //
    // member variables
    //
    
    private HashMap<String, String> descriptions = new HashMap<String, String>();
    
    private Integer personId;
    
    private Integer genderConceptId;
    
    private Integer yearOfBirth;
    
    private Integer monthOfBirth;
    
    private Integer dayOfBirth;
    
    private String birthDatetime;
    
    private Integer raceConceptId;
    
    private Integer ethnicityConceptId;
    
    private Integer locationId;
    
    private Integer providerId;
    
    private Integer careSiteId;
    
    private String personSourceValue;
    
    private String genderSourceValue;
    
    private Integer genderSourceConceptId;
    
    private String raceSourceValue;
    
    private Integer raceSourceConceptId;
    
    private String ethnicitySourceValue;
    
    private Integer ethnicitySourceConceptId;
    
    private CareSiteDvo careSiteDvo;
    
    private ConceptDvo ethnicityConceptDvo;
    
    private ConceptDvo ethnicitySourceConceptDvo;
    
    private ConceptDvo genderConceptDvo;
    
    private ConceptDvo genderSourceConceptDvo;
    
    private LocationDvo locationDvo;
    
    private ProviderDvo providerDvo;
    
    private ConceptDvo raceConceptDvo;
    
    private ConceptDvo raceSourceConceptDvo;
    
    private ArrayList<ObservationPeriodDvo> observationPeriodPersonList = new ArrayList<ObservationPeriodDvo>();
    
    private ArrayList<VisitOccurrenceDvo> visitOccurrencePersonList = new ArrayList<VisitOccurrenceDvo>();
    
    private ArrayList<VisitDetailDvo> visitDetailPersonList = new ArrayList<VisitDetailDvo>();
    
    private ArrayList<ConditionOccurrenceDvo> conditionOccurrencePersonList = new ArrayList<ConditionOccurrenceDvo>();
    
    private ArrayList<DrugExposureDvo> drugExposurePersonList = new ArrayList<DrugExposureDvo>();
    
    private ArrayList<ProcedureOccurrenceDvo> procedureOccurrencePersonList = new ArrayList<ProcedureOccurrenceDvo>();
    
    private ArrayList<DeviceExposureDvo> deviceExposurePersonList = new ArrayList<DeviceExposureDvo>();
    
    private ArrayList<MeasurementDvo> measurementPersonList = new ArrayList<MeasurementDvo>();
    
    private ArrayList<ObservationDvo> observationPersonList = new ArrayList<ObservationDvo>();
    
    private ArrayList<DeathDvo> deathPersonList = new ArrayList<DeathDvo>();
    
    private ArrayList<NoteDvo> notePersonList = new ArrayList<NoteDvo>();
    
    private ArrayList<SpecimenDvo> specimenPersonList = new ArrayList<SpecimenDvo>();
    
    private ArrayList<PayerPlanPeriodDvo> payerPlanPeriodPayerPlanPeriodList = new ArrayList<PayerPlanPeriodDvo>();
    
    private ArrayList<PayerPlanPeriodDvo> payerPlanPeriodPersonList = new ArrayList<PayerPlanPeriodDvo>();
    
    private ArrayList<DrugEraDvo> drugEraPersonList = new ArrayList<DrugEraDvo>();
    
    private ArrayList<DoseEraDvo> doseEraPersonList = new ArrayList<DoseEraDvo>();
    
    private ArrayList<ConditionEraDvo> conditionEraPersonList = new ArrayList<ConditionEraDvo>();
    
    private ArrayList<EpisodeDvo> episodePersonList = new ArrayList<EpisodeDvo>();
    
    //
    // trivial getters and setters
    //
    
    // personId
    
    public void setPersonId(Integer val) {
        this.personId = val;
    }
    
    public Integer getPersonId() {
        return this.personId;
    }
    
    // genderConceptId
    
    public void setGenderConceptId(Integer val) {
        this.genderConceptId = val;
    }
    
    public Integer getGenderConceptId() {
        return this.genderConceptId;
    }
    
    // yearOfBirth
    
    public void setYearOfBirth(Integer val) {
        this.yearOfBirth = val;
    }
    
    public Integer getYearOfBirth() {
        return this.yearOfBirth;
    }
    
    // monthOfBirth
    
    public void setMonthOfBirth(Integer val) {
        this.monthOfBirth = val;
    }
    
    public Integer getMonthOfBirth() {
        return this.monthOfBirth;
    }
    
    // dayOfBirth
    
    public void setDayOfBirth(Integer val) {
        this.dayOfBirth = val;
    }
    
    public Integer getDayOfBirth() {
        return this.dayOfBirth;
    }
    
    // birthDatetime
    
    public void setBirthDatetime(String val) {
        this.birthDatetime = val;
    }
    
    public String getBirthDatetime() {
        return this.birthDatetime;
    }
    
    // raceConceptId
    
    public void setRaceConceptId(Integer val) {
        this.raceConceptId = val;
    }
    
    public Integer getRaceConceptId() {
        return this.raceConceptId;
    }
    
    // ethnicityConceptId
    
    public void setEthnicityConceptId(Integer val) {
        this.ethnicityConceptId = val;
    }
    
    public Integer getEthnicityConceptId() {
        return this.ethnicityConceptId;
    }
    
    // locationId
    
    public void setLocationId(Integer val) {
        this.locationId = val;
    }
    
    public Integer getLocationId() {
        return this.locationId;
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
    
    // personSourceValue
    
    public void setPersonSourceValue(String val) {
        this.personSourceValue = val;
    }
    
    public String getPersonSourceValue() {
        return this.personSourceValue;
    }
    
    // genderSourceValue
    
    public void setGenderSourceValue(String val) {
        this.genderSourceValue = val;
    }
    
    public String getGenderSourceValue() {
        return this.genderSourceValue;
    }
    
    // genderSourceConceptId
    
    public void setGenderSourceConceptId(Integer val) {
        this.genderSourceConceptId = val;
    }
    
    public Integer getGenderSourceConceptId() {
        return this.genderSourceConceptId;
    }
    
    // raceSourceValue
    
    public void setRaceSourceValue(String val) {
        this.raceSourceValue = val;
    }
    
    public String getRaceSourceValue() {
        return this.raceSourceValue;
    }
    
    // raceSourceConceptId
    
    public void setRaceSourceConceptId(Integer val) {
        this.raceSourceConceptId = val;
    }
    
    public Integer getRaceSourceConceptId() {
        return this.raceSourceConceptId;
    }
    
    // ethnicitySourceValue
    
    public void setEthnicitySourceValue(String val) {
        this.ethnicitySourceValue = val;
    }
    
    public String getEthnicitySourceValue() {
        return this.ethnicitySourceValue;
    }
    
    // ethnicitySourceConceptId
    
    public void setEthnicitySourceConceptId(Integer val) {
        this.ethnicitySourceConceptId = val;
    }
    
    public Integer getEthnicitySourceConceptId() {
        return this.ethnicitySourceConceptId;
    }
    
    // careSiteDvo
    
    public void setCareSiteDvo(CareSiteDvo dvo) {
        this.careSiteDvo = dvo;
    }
    
    public CareSiteDvo getCareSiteDvo() {
        return this.careSiteDvo;
    }
    
    // ethnicityConceptDvo
    
    public void setEthnicityConceptDvo(ConceptDvo dvo) {
        this.ethnicityConceptDvo = dvo;
    }
    
    public ConceptDvo getEthnicityConceptDvo() {
        return this.ethnicityConceptDvo;
    }
    
    // ethnicitySourceConceptDvo
    
    public void setEthnicitySourceConceptDvo(ConceptDvo dvo) {
        this.ethnicitySourceConceptDvo = dvo;
    }
    
    public ConceptDvo getEthnicitySourceConceptDvo() {
        return this.ethnicitySourceConceptDvo;
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
    
    // locationDvo
    
    public void setLocationDvo(LocationDvo dvo) {
        this.locationDvo = dvo;
    }
    
    public LocationDvo getLocationDvo() {
        return this.locationDvo;
    }
    
    // providerDvo
    
    public void setProviderDvo(ProviderDvo dvo) {
        this.providerDvo = dvo;
    }
    
    public ProviderDvo getProviderDvo() {
        return this.providerDvo;
    }
    
    // raceConceptDvo
    
    public void setRaceConceptDvo(ConceptDvo dvo) {
        this.raceConceptDvo = dvo;
    }
    
    public ConceptDvo getRaceConceptDvo() {
        return this.raceConceptDvo;
    }
    
    // raceSourceConceptDvo
    
    public void setRaceSourceConceptDvo(ConceptDvo dvo) {
        this.raceSourceConceptDvo = dvo;
    }
    
    public ConceptDvo getRaceSourceConceptDvo() {
        return this.raceSourceConceptDvo;
    }
    
    public ArrayList<ObservationPeriodDvo> getObservationPeriodPersonList() {
        return observationPeriodPersonList;
    }
    
    public void setObservationPeriodPersonList(ArrayList<ObservationPeriodDvo> list) {
        this.observationPeriodPersonList = list;
    }
    
    public ArrayList<VisitOccurrenceDvo> getVisitOccurrencePersonList() {
        return visitOccurrencePersonList;
    }
    
    public void setVisitOccurrencePersonList(ArrayList<VisitOccurrenceDvo> list) {
        this.visitOccurrencePersonList = list;
    }
    
    public ArrayList<VisitDetailDvo> getVisitDetailPersonList() {
        return visitDetailPersonList;
    }
    
    public void setVisitDetailPersonList(ArrayList<VisitDetailDvo> list) {
        this.visitDetailPersonList = list;
    }
    
    public ArrayList<ConditionOccurrenceDvo> getConditionOccurrencePersonList() {
        return conditionOccurrencePersonList;
    }
    
    public void setConditionOccurrencePersonList(ArrayList<ConditionOccurrenceDvo> list) {
        this.conditionOccurrencePersonList = list;
    }
    
    public ArrayList<DrugExposureDvo> getDrugExposurePersonList() {
        return drugExposurePersonList;
    }
    
    public void setDrugExposurePersonList(ArrayList<DrugExposureDvo> list) {
        this.drugExposurePersonList = list;
    }
    
    public ArrayList<ProcedureOccurrenceDvo> getProcedureOccurrencePersonList() {
        return procedureOccurrencePersonList;
    }
    
    public void setProcedureOccurrencePersonList(ArrayList<ProcedureOccurrenceDvo> list) {
        this.procedureOccurrencePersonList = list;
    }
    
    public ArrayList<DeviceExposureDvo> getDeviceExposurePersonList() {
        return deviceExposurePersonList;
    }
    
    public void setDeviceExposurePersonList(ArrayList<DeviceExposureDvo> list) {
        this.deviceExposurePersonList = list;
    }
    
    public ArrayList<MeasurementDvo> getMeasurementPersonList() {
        return measurementPersonList;
    }
    
    public void setMeasurementPersonList(ArrayList<MeasurementDvo> list) {
        this.measurementPersonList = list;
    }
    
    public ArrayList<ObservationDvo> getObservationPersonList() {
        return observationPersonList;
    }
    
    public void setObservationPersonList(ArrayList<ObservationDvo> list) {
        this.observationPersonList = list;
    }
    
    public ArrayList<DeathDvo> getDeathPersonList() {
        return deathPersonList;
    }
    
    public void setDeathPersonList(ArrayList<DeathDvo> list) {
        this.deathPersonList = list;
    }
    
    public ArrayList<NoteDvo> getNotePersonList() {
        return notePersonList;
    }
    
    public void setNotePersonList(ArrayList<NoteDvo> list) {
        this.notePersonList = list;
    }
    
    public ArrayList<SpecimenDvo> getSpecimenPersonList() {
        return specimenPersonList;
    }
    
    public void setSpecimenPersonList(ArrayList<SpecimenDvo> list) {
        this.specimenPersonList = list;
    }
    
    public ArrayList<PayerPlanPeriodDvo> getPayerPlanPeriodPayerPlanPeriodList() {
        return payerPlanPeriodPayerPlanPeriodList;
    }
    
    public void setPayerPlanPeriodPayerPlanPeriodList(ArrayList<PayerPlanPeriodDvo> list) {
        this.payerPlanPeriodPayerPlanPeriodList = list;
    }
    
    public ArrayList<PayerPlanPeriodDvo> getPayerPlanPeriodPersonList() {
        return payerPlanPeriodPersonList;
    }
    
    public void setPayerPlanPeriodPersonList(ArrayList<PayerPlanPeriodDvo> list) {
        this.payerPlanPeriodPersonList = list;
    }
    
    public ArrayList<DrugEraDvo> getDrugEraPersonList() {
        return drugEraPersonList;
    }
    
    public void setDrugEraPersonList(ArrayList<DrugEraDvo> list) {
        this.drugEraPersonList = list;
    }
    
    public ArrayList<DoseEraDvo> getDoseEraPersonList() {
        return doseEraPersonList;
    }
    
    public void setDoseEraPersonList(ArrayList<DoseEraDvo> list) {
        this.doseEraPersonList = list;
    }
    
    public ArrayList<ConditionEraDvo> getConditionEraPersonList() {
        return conditionEraPersonList;
    }
    
    public void setConditionEraPersonList(ArrayList<ConditionEraDvo> list) {
        this.conditionEraPersonList = list;
    }
    
    public ArrayList<EpisodeDvo> getEpisodePersonList() {
        return episodePersonList;
    }
    
    public void setEpisodePersonList(ArrayList<EpisodeDvo> list) {
        this.episodePersonList = list;
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
            getPersonId()  == null ? null: getPersonId() + ""
        };
        return rtn;
    }
}
