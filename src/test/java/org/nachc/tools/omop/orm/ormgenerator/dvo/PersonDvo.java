//
// Data Value Object (DVO) for PERSON
//

package org.nachc.tools.omop.orm.ormgenerator.dvo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;

import org.yaorma.dvo.Dvo;

public class PersonDvo implements Dvo {

    //
    // tableName
    //
    
    public static final String TABLE_NAME = "PERSON";
    
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
    
    private Date birthDatetime;
    
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
    
    public void setBirthDatetime(Date val) {
        this.birthDatetime = val;
    }
    
    public Date getBirthDatetime() {
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
