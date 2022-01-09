//
// Data Value Object (DVO) for device_exposure
//

package org.nachc.tools.omop.yaorma.dvo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;

import org.yaorma.dvo.Dvo;

public class DeviceExposureDvo implements Dvo {

    //
    // tableName
    //
    
    public static final String TABLE_NAME = "device_exposure";
    
    //
    // schemaName
    //
    
    public static final String SCHEMA_NAME = "synthea_omop";
    
    //
    // columnNames
    //
    
    public static final String[] COLUMN_NAMES = {
        "device_concept_id",
        "device_exposure_end_date",
        "device_exposure_end_datetime",
        "device_exposure_id",
        "device_exposure_start_date",
        "device_exposure_start_datetime",
        "device_source_concept_id",
        "device_source_value",
        "device_type_concept_id",
        "person_id",
        "production_id",
        "provider_id",
        "quantity",
        "unique_device_id",
        "unit_concept_id",
        "unit_source_concept_id",
        "unit_source_value",
        "visit_detail_id",
        "visit_occurrence_id"
    };
    
    //
    // primaryKeyColumnNames
    //
    
    public static final String[] PRIMARY_KEY_COLUMN_NAMES = {
        "device_exposure_id"
    };
    
    //
    // javaNames
    //
    
    public static final String[] JAVA_NAMES = {
        "deviceConceptId",
        "deviceExposureEndDate",
        "deviceExposureEndDatetime",
        "deviceExposureId",
        "deviceExposureStartDate",
        "deviceExposureStartDatetime",
        "deviceSourceConceptId",
        "deviceSourceValue",
        "deviceTypeConceptId",
        "personId",
        "productionId",
        "providerId",
        "quantity",
        "uniqueDeviceId",
        "unitConceptId",
        "unitSourceConceptId",
        "unitSourceValue",
        "visitDetailId",
        "visitOccurrenceId"
    };
    
    //
    // javaNamesProper
    //
    
    public static final String[] JAVA_NAMES_PROPER = {
        "DeviceConceptId",
        "DeviceExposureEndDate",
        "DeviceExposureEndDatetime",
        "DeviceExposureId",
        "DeviceExposureStartDate",
        "DeviceExposureStartDatetime",
        "DeviceSourceConceptId",
        "DeviceSourceValue",
        "DeviceTypeConceptId",
        "PersonId",
        "ProductionId",
        "ProviderId",
        "Quantity",
        "UniqueDeviceId",
        "UnitConceptId",
        "UnitSourceConceptId",
        "UnitSourceValue",
        "VisitDetailId",
        "VisitOccurrenceId"
    };
    
    
    //
    // member variables
    //
    
    private HashMap<String, String> descriptions = new HashMap<String, String>();
    
    private Integer deviceConceptId;
    
    private String deviceExposureEndDate;
    
    private String deviceExposureEndDatetime;
    
    private Integer deviceExposureId;
    
    private String deviceExposureStartDate;
    
    private String deviceExposureStartDatetime;
    
    private Integer deviceSourceConceptId;
    
    private String deviceSourceValue;
    
    private Integer deviceTypeConceptId;
    
    private Integer personId;
    
    private String productionId;
    
    private Integer providerId;
    
    private Integer quantity;
    
    private String uniqueDeviceId;
    
    private Integer unitConceptId;
    
    private Integer unitSourceConceptId;
    
    private String unitSourceValue;
    
    private Integer visitDetailId;
    
    private Integer visitOccurrenceId;
    
    private ConceptDvo deviceConceptDvo;
    
    private ConceptDvo deviceSourceConceptDvo;
    
    private ConceptDvo deviceTypeConceptDvo;
    
    private PersonDvo personDvo;
    
    private ProviderDvo providerDvo;
    
    private ConceptDvo unitConceptDvo;
    
    private ConceptDvo unitSourceConceptDvo;
    
    private VisitDetailDvo visitDetailDvo;
    
    private VisitOccurrenceDvo visitOccurrenceDvo;
    
    //
    // trivial getters and setters
    //
    
    // deviceConceptId
    
    public void setDeviceConceptId(Integer val) {
        this.deviceConceptId = val;
    }
    
    public Integer getDeviceConceptId() {
        return this.deviceConceptId;
    }
    
    // deviceExposureEndDate
    
    public void setDeviceExposureEndDate(String val) {
        this.deviceExposureEndDate = val;
    }
    
    public String getDeviceExposureEndDate() {
        return this.deviceExposureEndDate;
    }
    
    // deviceExposureEndDatetime
    
    public void setDeviceExposureEndDatetime(String val) {
        this.deviceExposureEndDatetime = val;
    }
    
    public String getDeviceExposureEndDatetime() {
        return this.deviceExposureEndDatetime;
    }
    
    // deviceExposureId
    
    public void setDeviceExposureId(Integer val) {
        this.deviceExposureId = val;
    }
    
    public Integer getDeviceExposureId() {
        return this.deviceExposureId;
    }
    
    // deviceExposureStartDate
    
    public void setDeviceExposureStartDate(String val) {
        this.deviceExposureStartDate = val;
    }
    
    public String getDeviceExposureStartDate() {
        return this.deviceExposureStartDate;
    }
    
    // deviceExposureStartDatetime
    
    public void setDeviceExposureStartDatetime(String val) {
        this.deviceExposureStartDatetime = val;
    }
    
    public String getDeviceExposureStartDatetime() {
        return this.deviceExposureStartDatetime;
    }
    
    // deviceSourceConceptId
    
    public void setDeviceSourceConceptId(Integer val) {
        this.deviceSourceConceptId = val;
    }
    
    public Integer getDeviceSourceConceptId() {
        return this.deviceSourceConceptId;
    }
    
    // deviceSourceValue
    
    public void setDeviceSourceValue(String val) {
        this.deviceSourceValue = val;
    }
    
    public String getDeviceSourceValue() {
        return this.deviceSourceValue;
    }
    
    // deviceTypeConceptId
    
    public void setDeviceTypeConceptId(Integer val) {
        this.deviceTypeConceptId = val;
    }
    
    public Integer getDeviceTypeConceptId() {
        return this.deviceTypeConceptId;
    }
    
    // personId
    
    public void setPersonId(Integer val) {
        this.personId = val;
    }
    
    public Integer getPersonId() {
        return this.personId;
    }
    
    // productionId
    
    public void setProductionId(String val) {
        this.productionId = val;
    }
    
    public String getProductionId() {
        return this.productionId;
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
    
    // uniqueDeviceId
    
    public void setUniqueDeviceId(String val) {
        this.uniqueDeviceId = val;
    }
    
    public String getUniqueDeviceId() {
        return this.uniqueDeviceId;
    }
    
    // unitConceptId
    
    public void setUnitConceptId(Integer val) {
        this.unitConceptId = val;
    }
    
    public Integer getUnitConceptId() {
        return this.unitConceptId;
    }
    
    // unitSourceConceptId
    
    public void setUnitSourceConceptId(Integer val) {
        this.unitSourceConceptId = val;
    }
    
    public Integer getUnitSourceConceptId() {
        return this.unitSourceConceptId;
    }
    
    // unitSourceValue
    
    public void setUnitSourceValue(String val) {
        this.unitSourceValue = val;
    }
    
    public String getUnitSourceValue() {
        return this.unitSourceValue;
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
    
    // deviceConceptDvo
    
    public void setDeviceConceptDvo(ConceptDvo dvo) {
        this.deviceConceptDvo = dvo;
    }
    
    public ConceptDvo getDeviceConceptDvo() {
        return this.deviceConceptDvo;
    }
    
    // deviceSourceConceptDvo
    
    public void setDeviceSourceConceptDvo(ConceptDvo dvo) {
        this.deviceSourceConceptDvo = dvo;
    }
    
    public ConceptDvo getDeviceSourceConceptDvo() {
        return this.deviceSourceConceptDvo;
    }
    
    // deviceTypeConceptDvo
    
    public void setDeviceTypeConceptDvo(ConceptDvo dvo) {
        this.deviceTypeConceptDvo = dvo;
    }
    
    public ConceptDvo getDeviceTypeConceptDvo() {
        return this.deviceTypeConceptDvo;
    }
    
    // personDvo
    
    public void setPersonDvo(PersonDvo dvo) {
        this.personDvo = dvo;
    }
    
    public PersonDvo getPersonDvo() {
        return this.personDvo;
    }
    
    // providerDvo
    
    public void setProviderDvo(ProviderDvo dvo) {
        this.providerDvo = dvo;
    }
    
    public ProviderDvo getProviderDvo() {
        return this.providerDvo;
    }
    
    // unitConceptDvo
    
    public void setUnitConceptDvo(ConceptDvo dvo) {
        this.unitConceptDvo = dvo;
    }
    
    public ConceptDvo getUnitConceptDvo() {
        return this.unitConceptDvo;
    }
    
    // unitSourceConceptDvo
    
    public void setUnitSourceConceptDvo(ConceptDvo dvo) {
        this.unitSourceConceptDvo = dvo;
    }
    
    public ConceptDvo getUnitSourceConceptDvo() {
        return this.unitSourceConceptDvo;
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
            getDeviceExposureId()  == null ? null: getDeviceExposureId() + ""
        };
        return rtn;
    }
}
