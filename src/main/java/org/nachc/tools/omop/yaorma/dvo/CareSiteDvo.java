//
// Data Value Object (DVO) for CARE_SITE
//

package org.nachc.tools.omop.yaorma.dvo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;
import java.math.BigDecimal;

import org.yaorma.dvo.Dvo;

public class CareSiteDvo implements Dvo {

    //
    // tableName
    //
    
    public static final String TABLE_NAME = "CARE_SITE";
    
    //
    // schemaName
    //
    
    public static final String SCHEMA_NAME = "synthea_micro.dbo";
    
    //
    // columnNames
    //
    
    public static final String[] COLUMN_NAMES = {
        "care_site_id",
        "care_site_name",
        "place_of_service_concept_id",
        "location_id",
        "care_site_source_value",
        "place_of_service_source_value"
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
        "careSiteId",
        "careSiteName",
        "placeOfServiceConceptId",
        "locationId",
        "careSiteSourceValue",
        "placeOfServiceSourceValue"
    };
    
    //
    // javaNamesProper
    //
    
    public static final String[] JAVA_NAMES_PROPER = {
        "CareSiteId",
        "CareSiteName",
        "PlaceOfServiceConceptId",
        "LocationId",
        "CareSiteSourceValue",
        "PlaceOfServiceSourceValue"
    };
    
    
    //
    // member variables
    //
    
    private HashMap<String, String> descriptions = new HashMap<String, String>();
    
    private Integer careSiteId;
    
    private String careSiteName;
    
    private Integer placeOfServiceConceptId;
    
    private Integer locationId;
    
    private String careSiteSourceValue;
    
    private String placeOfServiceSourceValue;
    
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
    
    // careSiteName
    
    public void setCareSiteName(String val) {
        this.careSiteName = val;
    }
    
    public String getCareSiteName() {
        return this.careSiteName;
    }
    
    // placeOfServiceConceptId
    
    public void setPlaceOfServiceConceptId(Integer val) {
        this.placeOfServiceConceptId = val;
    }
    
    public Integer getPlaceOfServiceConceptId() {
        return this.placeOfServiceConceptId;
    }
    
    // locationId
    
    public void setLocationId(Integer val) {
        this.locationId = val;
    }
    
    public Integer getLocationId() {
        return this.locationId;
    }
    
    // careSiteSourceValue
    
    public void setCareSiteSourceValue(String val) {
        this.careSiteSourceValue = val;
    }
    
    public String getCareSiteSourceValue() {
        return this.careSiteSourceValue;
    }
    
    // placeOfServiceSourceValue
    
    public void setPlaceOfServiceSourceValue(String val) {
        this.placeOfServiceSourceValue = val;
    }
    
    public String getPlaceOfServiceSourceValue() {
        return this.placeOfServiceSourceValue;
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
