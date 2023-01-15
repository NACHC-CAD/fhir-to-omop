//
// Data Value Object (DVO) for PROVIDER
//

package org.nachc.tools.omop.yaorma.dvo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;
import java.math.BigDecimal;

import org.yaorma.dvo.Dvo;

public class ProviderDvo implements Dvo {

    //
    // tableName
    //
    
    public static final String TABLE_NAME = "PROVIDER";
    
    //
    // schemaName
    //
    
    public static final String SCHEMA_NAME = "cdm_f2o_build";
    
    //
    // columnNames
    //
    
    public static final String[] COLUMN_NAMES = {
        "provider_id",
        "provider_name",
        "npi",
        "dea",
        "specialty_concept_id",
        "care_site_id",
        "year_of_birth",
        "gender_concept_id",
        "provider_source_value",
        "specialty_source_value",
        "specialty_source_concept_id",
        "gender_source_value",
        "gender_source_concept_id"
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
        "providerId",
        "providerName",
        "npi",
        "dea",
        "specialtyConceptId",
        "careSiteId",
        "yearOfBirth",
        "genderConceptId",
        "providerSourceValue",
        "specialtySourceValue",
        "specialtySourceConceptId",
        "genderSourceValue",
        "genderSourceConceptId"
    };
    
    //
    // javaNamesProper
    //
    
    public static final String[] JAVA_NAMES_PROPER = {
        "ProviderId",
        "ProviderName",
        "Npi",
        "Dea",
        "SpecialtyConceptId",
        "CareSiteId",
        "YearOfBirth",
        "GenderConceptId",
        "ProviderSourceValue",
        "SpecialtySourceValue",
        "SpecialtySourceConceptId",
        "GenderSourceValue",
        "GenderSourceConceptId"
    };
    
    
    //
    // member variables
    //
    
    private HashMap<String, String> descriptions = new HashMap<String, String>();
    
    private Integer providerId;
    
    private String providerName;
    
    private String npi;
    
    private String dea;
    
    private Integer specialtyConceptId;
    
    private Integer careSiteId;
    
    private Integer yearOfBirth;
    
    private Integer genderConceptId;
    
    private String providerSourceValue;
    
    private String specialtySourceValue;
    
    private Integer specialtySourceConceptId;
    
    private String genderSourceValue;
    
    private Integer genderSourceConceptId;
    
    //
    // trivial getters and setters
    //
    
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
    
    // npi
    
    public void setNpi(String val) {
        this.npi = val;
    }
    
    public String getNpi() {
        return this.npi;
    }
    
    // dea
    
    public void setDea(String val) {
        this.dea = val;
    }
    
    public String getDea() {
        return this.dea;
    }
    
    // specialtyConceptId
    
    public void setSpecialtyConceptId(Integer val) {
        this.specialtyConceptId = val;
    }
    
    public Integer getSpecialtyConceptId() {
        return this.specialtyConceptId;
    }
    
    // careSiteId
    
    public void setCareSiteId(Integer val) {
        this.careSiteId = val;
    }
    
    public Integer getCareSiteId() {
        return this.careSiteId;
    }
    
    // yearOfBirth
    
    public void setYearOfBirth(Integer val) {
        this.yearOfBirth = val;
    }
    
    public Integer getYearOfBirth() {
        return this.yearOfBirth;
    }
    
    // genderConceptId
    
    public void setGenderConceptId(Integer val) {
        this.genderConceptId = val;
    }
    
    public Integer getGenderConceptId() {
        return this.genderConceptId;
    }
    
    // providerSourceValue
    
    public void setProviderSourceValue(String val) {
        this.providerSourceValue = val;
    }
    
    public String getProviderSourceValue() {
        return this.providerSourceValue;
    }
    
    // specialtySourceValue
    
    public void setSpecialtySourceValue(String val) {
        this.specialtySourceValue = val;
    }
    
    public String getSpecialtySourceValue() {
        return this.specialtySourceValue;
    }
    
    // specialtySourceConceptId
    
    public void setSpecialtySourceConceptId(Integer val) {
        this.specialtySourceConceptId = val;
    }
    
    public Integer getSpecialtySourceConceptId() {
        return this.specialtySourceConceptId;
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
