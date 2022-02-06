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
        "birth_datetime",
        "care_site_id",
        "day_of_birth",
        "ethnicity_concept_id",
        "ethnicity_source_concept_id",
        "ethnicity_source_value",
        "gender_concept_id",
        "gender_source_concept_id",
        "gender_source_value",
        "location_id",
        "month_of_birth",
        "person_id",
        "person_source_value",
        "provider_id",
        "race_concept_id",
        "race_source_concept_id",
        "race_source_value",
        "year_of_birth"
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
        "birthDatetime",
        "careSiteId",
        "dayOfBirth",
        "ethnicityConceptId",
        "ethnicitySourceConceptId",
        "ethnicitySourceValue",
        "genderConceptId",
        "genderSourceConceptId",
        "genderSourceValue",
        "locationId",
        "monthOfBirth",
        "personId",
        "personSourceValue",
        "providerId",
        "raceConceptId",
        "raceSourceConceptId",
        "raceSourceValue",
        "yearOfBirth"
    };
    
    //
    // javaNamesProper
    //
    
    public static final String[] JAVA_NAMES_PROPER = {
        "BirthDatetime",
        "CareSiteId",
        "DayOfBirth",
        "EthnicityConceptId",
        "EthnicitySourceConceptId",
        "EthnicitySourceValue",
        "GenderConceptId",
        "GenderSourceConceptId",
        "GenderSourceValue",
        "LocationId",
        "MonthOfBirth",
        "PersonId",
        "PersonSourceValue",
        "ProviderId",
        "RaceConceptId",
        "RaceSourceConceptId",
        "RaceSourceValue",
        "YearOfBirth"
    };
    
    
    //
    // member variables
    //
    
    private HashMap<String, String> descriptions = new HashMap<String, String>();
    
    private String birthDatetime;
    
    private Integer careSiteId;
    
    private Integer dayOfBirth;
    
    private Integer ethnicityConceptId;
    
    private Integer ethnicitySourceConceptId;
    
    private String ethnicitySourceValue;
    
    private Integer genderConceptId;
    
    private Integer genderSourceConceptId;
    
    private String genderSourceValue;
    
    private Integer locationId;
    
    private Integer monthOfBirth;
    
    private Integer personId;
    
    private String personSourceValue;
    
    private Integer providerId;
    
    private Integer raceConceptId;
    
    private Integer raceSourceConceptId;
    
    private String raceSourceValue;
    
    private Integer yearOfBirth;
    
    private CareSiteDvo careSiteDvo;
    
    private ConceptDvo ethnicityConceptDvo;
    
    private ConceptDvo ethnicitySourceConceptDvo;
    
    private ConceptDvo genderConceptDvo;
    
    private ConceptDvo genderSourceConceptDvo;
    
    private LocationDvo locationDvo;
    
    private ProviderDvo providerDvo;
    
    private ConceptDvo raceConceptDvo;
    
    private ConceptDvo raceSourceConceptDvo;
    
    private ArrayList<ConditionEraDvo> conditionEraPersonList = new ArrayList<ConditionEraDvo>();
    
    private ArrayList<ConditionOccurrenceDvo> conditionOccurrencePersonList = new ArrayList<ConditionOccurrenceDvo>();
    
    private ArrayList<DeathDvo> deathPersonList = new ArrayList<DeathDvo>();
    
    private ArrayList<DeviceExposureDvo> deviceExposurePersonList = new ArrayList<DeviceExposureDvo>();
    
    private ArrayList<DoseEraDvo> doseEraPersonList = new ArrayList<DoseEraDvo>();
    
    private ArrayList<DrugEraDvo> drugEraPersonList = new ArrayList<DrugEraDvo>();
    
    private ArrayList<DrugExposureDvo> drugExposurePersonList = new ArrayList<DrugExposureDvo>();
    
    private ArrayList<EpisodeDvo> episodePersonList = new ArrayList<EpisodeDvo>();
    
    private ArrayList<MeasurementDvo> measurementPersonList = new ArrayList<MeasurementDvo>();
    
    private ArrayList<NoteDvo> notePersonList = new ArrayList<NoteDvo>();
    
    private ArrayList<ObservationDvo> observationPersonList = new ArrayList<ObservationDvo>();
    
    private ArrayList<ObservationPeriodDvo> observationPeriodPersonList = new ArrayList<ObservationPeriodDvo>();
    
    private ArrayList<PayerPlanPeriodDvo> payerPlanPeriodPayerPlanPeriodList = new ArrayList<PayerPlanPeriodDvo>();
    
    private ArrayList<PayerPlanPeriodDvo> payerPlanPeriodPersonList = new ArrayList<PayerPlanPeriodDvo>();
    
    private ArrayList<ProcedureOccurrenceDvo> procedureOccurrencePersonList = new ArrayList<ProcedureOccurrenceDvo>();
    
    private ArrayList<SpecimenDvo> specimenPersonList = new ArrayList<SpecimenDvo>();
    
    private ArrayList<VisitDetailDvo> visitDetailPersonList = new ArrayList<VisitDetailDvo>();
    
    private ArrayList<VisitOccurrenceDvo> visitOccurrencePersonList = new ArrayList<VisitOccurrenceDvo>();
    
    //
    // trivial getters and setters
    //
    
    // birthDatetime
    
    public void setBirthDatetime(String val) {
        this.birthDatetime = val;
    }
    
    public String getBirthDatetime() {
        return this.birthDatetime;
    }
    
    // careSiteId
    
    public void setCareSiteId(Integer val) {
        this.careSiteId = val;
    }
    
    public Integer getCareSiteId() {
        return this.careSiteId;
    }
    
    // dayOfBirth
    
    public void setDayOfBirth(Integer val) {
        this.dayOfBirth = val;
    }
    
    public Integer getDayOfBirth() {
        return this.dayOfBirth;
    }
    
    // ethnicityConceptId
    
    public void setEthnicityConceptId(Integer val) {
        this.ethnicityConceptId = val;
    }
    
    public Integer getEthnicityConceptId() {
        return this.ethnicityConceptId;
    }
    
    // ethnicitySourceConceptId
    
    public void setEthnicitySourceConceptId(Integer val) {
        this.ethnicitySourceConceptId = val;
    }
    
    public Integer getEthnicitySourceConceptId() {
        return this.ethnicitySourceConceptId;
    }
    
    // ethnicitySourceValue
    
    public void setEthnicitySourceValue(String val) {
        this.ethnicitySourceValue = val;
    }
    
    public String getEthnicitySourceValue() {
        return this.ethnicitySourceValue;
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
    
    // locationId
    
    public void setLocationId(Integer val) {
        this.locationId = val;
    }
    
    public Integer getLocationId() {
        return this.locationId;
    }
    
    // monthOfBirth
    
    public void setMonthOfBirth(Integer val) {
        this.monthOfBirth = val;
    }
    
    public Integer getMonthOfBirth() {
        return this.monthOfBirth;
    }
    
    // personId
    
    public void setPersonId(Integer val) {
        this.personId = val;
    }
    
    public Integer getPersonId() {
        return this.personId;
    }
    
    // personSourceValue
    
    public void setPersonSourceValue(String val) {
        this.personSourceValue = val;
    }
    
    public String getPersonSourceValue() {
        return this.personSourceValue;
    }
    
    // providerId
    
    public void setProviderId(Integer val) {
        this.providerId = val;
    }
    
    public Integer getProviderId() {
        return this.providerId;
    }
    
    // raceConceptId
    
    public void setRaceConceptId(Integer val) {
        this.raceConceptId = val;
    }
    
    public Integer getRaceConceptId() {
        return this.raceConceptId;
    }
    
    // raceSourceConceptId
    
    public void setRaceSourceConceptId(Integer val) {
        this.raceSourceConceptId = val;
    }
    
    public Integer getRaceSourceConceptId() {
        return this.raceSourceConceptId;
    }
    
    // raceSourceValue
    
    public void setRaceSourceValue(String val) {
        this.raceSourceValue = val;
    }
    
    public String getRaceSourceValue() {
        return this.raceSourceValue;
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
    
    public ArrayList<ConditionEraDvo> getConditionEraPersonList() {
        return conditionEraPersonList;
    }
    
    public void setConditionEraPersonList(ArrayList<ConditionEraDvo> list) {
        this.conditionEraPersonList = list;
    }
    
    public ArrayList<ConditionOccurrenceDvo> getConditionOccurrencePersonList() {
        return conditionOccurrencePersonList;
    }
    
    public void setConditionOccurrencePersonList(ArrayList<ConditionOccurrenceDvo> list) {
        this.conditionOccurrencePersonList = list;
    }
    
    public ArrayList<DeathDvo> getDeathPersonList() {
        return deathPersonList;
    }
    
    public void setDeathPersonList(ArrayList<DeathDvo> list) {
        this.deathPersonList = list;
    }
    
    public ArrayList<DeviceExposureDvo> getDeviceExposurePersonList() {
        return deviceExposurePersonList;
    }
    
    public void setDeviceExposurePersonList(ArrayList<DeviceExposureDvo> list) {
        this.deviceExposurePersonList = list;
    }
    
    public ArrayList<DoseEraDvo> getDoseEraPersonList() {
        return doseEraPersonList;
    }
    
    public void setDoseEraPersonList(ArrayList<DoseEraDvo> list) {
        this.doseEraPersonList = list;
    }
    
    public ArrayList<DrugEraDvo> getDrugEraPersonList() {
        return drugEraPersonList;
    }
    
    public void setDrugEraPersonList(ArrayList<DrugEraDvo> list) {
        this.drugEraPersonList = list;
    }
    
    public ArrayList<DrugExposureDvo> getDrugExposurePersonList() {
        return drugExposurePersonList;
    }
    
    public void setDrugExposurePersonList(ArrayList<DrugExposureDvo> list) {
        this.drugExposurePersonList = list;
    }
    
    public ArrayList<EpisodeDvo> getEpisodePersonList() {
        return episodePersonList;
    }
    
    public void setEpisodePersonList(ArrayList<EpisodeDvo> list) {
        this.episodePersonList = list;
    }
    
    public ArrayList<MeasurementDvo> getMeasurementPersonList() {
        return measurementPersonList;
    }
    
    public void setMeasurementPersonList(ArrayList<MeasurementDvo> list) {
        this.measurementPersonList = list;
    }
    
    public ArrayList<NoteDvo> getNotePersonList() {
        return notePersonList;
    }
    
    public void setNotePersonList(ArrayList<NoteDvo> list) {
        this.notePersonList = list;
    }
    
    public ArrayList<ObservationDvo> getObservationPersonList() {
        return observationPersonList;
    }
    
    public void setObservationPersonList(ArrayList<ObservationDvo> list) {
        this.observationPersonList = list;
    }
    
    public ArrayList<ObservationPeriodDvo> getObservationPeriodPersonList() {
        return observationPeriodPersonList;
    }
    
    public void setObservationPeriodPersonList(ArrayList<ObservationPeriodDvo> list) {
        this.observationPeriodPersonList = list;
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
    
    public ArrayList<ProcedureOccurrenceDvo> getProcedureOccurrencePersonList() {
        return procedureOccurrencePersonList;
    }
    
    public void setProcedureOccurrencePersonList(ArrayList<ProcedureOccurrenceDvo> list) {
        this.procedureOccurrencePersonList = list;
    }
    
    public ArrayList<SpecimenDvo> getSpecimenPersonList() {
        return specimenPersonList;
    }
    
    public void setSpecimenPersonList(ArrayList<SpecimenDvo> list) {
        this.specimenPersonList = list;
    }
    
    public ArrayList<VisitDetailDvo> getVisitDetailPersonList() {
        return visitDetailPersonList;
    }
    
    public void setVisitDetailPersonList(ArrayList<VisitDetailDvo> list) {
        this.visitDetailPersonList = list;
    }
    
    public ArrayList<VisitOccurrenceDvo> getVisitOccurrencePersonList() {
        return visitOccurrencePersonList;
    }
    
    public void setVisitOccurrencePersonList(ArrayList<VisitOccurrenceDvo> list) {
        this.visitOccurrencePersonList = list;
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
