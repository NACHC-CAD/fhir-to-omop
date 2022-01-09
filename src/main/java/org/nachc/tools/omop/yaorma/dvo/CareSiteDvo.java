//
// Data Value Object (DVO) for care_site
//

package org.nachc.tools.omop.yaorma.dvo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;

import org.yaorma.dvo.Dvo;

public class CareSiteDvo implements Dvo {

    //
    // tableName
    //
    
    public static final String TABLE_NAME = "care_site";
    
    //
    // schemaName
    //
    
    public static final String SCHEMA_NAME = "synthea_omop";
    
    //
    // columnNames
    //
    
    public static final String[] COLUMN_NAMES = {
        "care_site_id",
        "care_site_name",
        "care_site_source_value",
        "location_id",
        "place_of_service_concept_id",
        "place_of_service_source_value"
    };
    
    //
    // primaryKeyColumnNames
    //
    
    public static final String[] PRIMARY_KEY_COLUMN_NAMES = {
        "care_site_id"
    };
    
    //
    // javaNames
    //
    
    public static final String[] JAVA_NAMES = {
        "careSiteId",
        "careSiteName",
        "careSiteSourceValue",
        "locationId",
        "placeOfServiceConceptId",
        "placeOfServiceSourceValue"
    };
    
    //
    // javaNamesProper
    //
    
    public static final String[] JAVA_NAMES_PROPER = {
        "CareSiteId",
        "CareSiteName",
        "CareSiteSourceValue",
        "LocationId",
        "PlaceOfServiceConceptId",
        "PlaceOfServiceSourceValue"
    };
    
    
    //
    // member variables
    //
    
    private HashMap<String, String> descriptions = new HashMap<String, String>();
    
    private Integer careSiteId;
    
    private String careSiteName;
    
    private String careSiteSourceValue;
    
    private Integer locationId;
    
    private Integer placeOfServiceConceptId;
    
    private String placeOfServiceSourceValue;
    
    private LocationDvo locationDvo;
    
    private ConceptDvo placeOfServiceConceptDvo;
    
    private ArrayList<PersonDvo> personCareSiteList = new ArrayList<PersonDvo>();
    
    private ArrayList<VisitOccurrenceDvo> visitOccurrenceCareSiteList = new ArrayList<VisitOccurrenceDvo>();
    
    private ArrayList<VisitDetailDvo> visitDetailCareSiteList = new ArrayList<VisitDetailDvo>();
    
    private ArrayList<ProviderDvo> providerCareSiteList = new ArrayList<ProviderDvo>();
    
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
    
    // careSiteSourceValue
    
    public void setCareSiteSourceValue(String val) {
        this.careSiteSourceValue = val;
    }
    
    public String getCareSiteSourceValue() {
        return this.careSiteSourceValue;
    }
    
    // locationId
    
    public void setLocationId(Integer val) {
        this.locationId = val;
    }
    
    public Integer getLocationId() {
        return this.locationId;
    }
    
    // placeOfServiceConceptId
    
    public void setPlaceOfServiceConceptId(Integer val) {
        this.placeOfServiceConceptId = val;
    }
    
    public Integer getPlaceOfServiceConceptId() {
        return this.placeOfServiceConceptId;
    }
    
    // placeOfServiceSourceValue
    
    public void setPlaceOfServiceSourceValue(String val) {
        this.placeOfServiceSourceValue = val;
    }
    
    public String getPlaceOfServiceSourceValue() {
        return this.placeOfServiceSourceValue;
    }
    
    // locationDvo
    
    public void setLocationDvo(LocationDvo dvo) {
        this.locationDvo = dvo;
    }
    
    public LocationDvo getLocationDvo() {
        return this.locationDvo;
    }
    
    // placeOfServiceConceptDvo
    
    public void setPlaceOfServiceConceptDvo(ConceptDvo dvo) {
        this.placeOfServiceConceptDvo = dvo;
    }
    
    public ConceptDvo getPlaceOfServiceConceptDvo() {
        return this.placeOfServiceConceptDvo;
    }
    
    public ArrayList<PersonDvo> getPersonCareSiteList() {
        return personCareSiteList;
    }
    
    public void setPersonCareSiteList(ArrayList<PersonDvo> list) {
        this.personCareSiteList = list;
    }
    
    public ArrayList<VisitOccurrenceDvo> getVisitOccurrenceCareSiteList() {
        return visitOccurrenceCareSiteList;
    }
    
    public void setVisitOccurrenceCareSiteList(ArrayList<VisitOccurrenceDvo> list) {
        this.visitOccurrenceCareSiteList = list;
    }
    
    public ArrayList<VisitDetailDvo> getVisitDetailCareSiteList() {
        return visitDetailCareSiteList;
    }
    
    public void setVisitDetailCareSiteList(ArrayList<VisitDetailDvo> list) {
        this.visitDetailCareSiteList = list;
    }
    
    public ArrayList<ProviderDvo> getProviderCareSiteList() {
        return providerCareSiteList;
    }
    
    public void setProviderCareSiteList(ArrayList<ProviderDvo> list) {
        this.providerCareSiteList = list;
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
            getCareSiteId()  == null ? null: getCareSiteId() + ""
        };
        return rtn;
    }
}
