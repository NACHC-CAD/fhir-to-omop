//
// Data Value Object (DVO) for LOCATION
//

package org.nachc.tools.omop.yaorma.dvo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;
import java.math.BigDecimal;

import org.yaorma.dvo.Dvo;

public class LocationDvo implements Dvo {

    //
    // tableName
    //
    
    public static final String TABLE_NAME = "LOCATION";
    
    //
    // schemaName
    //
    
    public static final String SCHEMA_NAME = "synthea_micro.dbo";
    
    //
    // columnNames
    //
    
    public static final String[] COLUMN_NAMES = {
        "location_id",
        "address_1",
        "address_2",
        "city",
        "state",
        "zip",
        "county",
        "location_source_value",
        "country_concept_id",
        "country_source_value",
        "latitude",
        "longitude"
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
        "locationId",
        "address1",
        "address2",
        "city",
        "state",
        "zip",
        "county",
        "locationSourceValue",
        "countryConceptId",
        "countrySourceValue",
        "latitude",
        "longitude"
    };
    
    //
    // javaNamesProper
    //
    
    public static final String[] JAVA_NAMES_PROPER = {
        "LocationId",
        "Address1",
        "Address2",
        "City",
        "State",
        "Zip",
        "County",
        "LocationSourceValue",
        "CountryConceptId",
        "CountrySourceValue",
        "Latitude",
        "Longitude"
    };
    
    
    //
    // member variables
    //
    
    private HashMap<String, String> descriptions = new HashMap<String, String>();
    
    private Integer locationId;
    
    private String address1;
    
    private String address2;
    
    private String city;
    
    private String state;
    
    private String zip;
    
    private String county;
    
    private String locationSourceValue;
    
    private Integer countryConceptId;
    
    private String countrySourceValue;
    
    private BigDecimal latitude;
    
    private BigDecimal longitude;
    
    //
    // trivial getters and setters
    //
    
    // locationId
    
    public void setLocationId(Integer val) {
        this.locationId = val;
    }
    
    public Integer getLocationId() {
        return this.locationId;
    }
    
    // address1
    
    public void setAddress1(String val) {
        this.address1 = val;
    }
    
    public String getAddress1() {
        return this.address1;
    }
    
    // address2
    
    public void setAddress2(String val) {
        this.address2 = val;
    }
    
    public String getAddress2() {
        return this.address2;
    }
    
    // city
    
    public void setCity(String val) {
        this.city = val;
    }
    
    public String getCity() {
        return this.city;
    }
    
    // state
    
    public void setState(String val) {
        this.state = val;
    }
    
    public String getState() {
        return this.state;
    }
    
    // zip
    
    public void setZip(String val) {
        this.zip = val;
    }
    
    public String getZip() {
        return this.zip;
    }
    
    // county
    
    public void setCounty(String val) {
        this.county = val;
    }
    
    public String getCounty() {
        return this.county;
    }
    
    // locationSourceValue
    
    public void setLocationSourceValue(String val) {
        this.locationSourceValue = val;
    }
    
    public String getLocationSourceValue() {
        return this.locationSourceValue;
    }
    
    // countryConceptId
    
    public void setCountryConceptId(Integer val) {
        this.countryConceptId = val;
    }
    
    public Integer getCountryConceptId() {
        return this.countryConceptId;
    }
    
    // countrySourceValue
    
    public void setCountrySourceValue(String val) {
        this.countrySourceValue = val;
    }
    
    public String getCountrySourceValue() {
        return this.countrySourceValue;
    }
    
    // latitude
    
    public void setLatitude(BigDecimal val) {
        this.latitude = val;
    }
    
    public BigDecimal getLatitude() {
        return this.latitude;
    }
    
    // longitude
    
    public void setLongitude(BigDecimal val) {
        this.longitude = val;
    }
    
    public BigDecimal getLongitude() {
        return this.longitude;
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
