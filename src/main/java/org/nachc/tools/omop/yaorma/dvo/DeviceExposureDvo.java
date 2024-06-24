//
// Data Value Object (DVO) for DEVICE_EXPOSURE
//

package org.nachc.tools.omop.yaorma.dvo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;
import java.math.BigDecimal;

import org.yaorma.dvo.Dvo;

public class DeviceExposureDvo implements Dvo {

    //
    // tableName
    //
    
    public static final String TABLE_NAME = "DEVICE_EXPOSURE";
    
    //
    // schemaName
    //
    
    public static final String SCHEMA_NAME = "synthea_micro.dbo";
    
    //
    // columnNames
    //
    
    public static final String[] COLUMN_NAMES = {
        "device_exposure_id",
        "person_id",
        "device_concept_id",
        "device_exposure_start_date",
        "device_exposure_start_datetime",
        "device_exposure_end_date",
        "device_exposure_end_datetime",
        "device_type_concept_id",
        "unique_device_id",
        "production_id",
        "quantity",
        "provider_id",
        "visit_occurrence_id",
        "visit_detail_id",
        "device_source_value",
        "device_source_concept_id",
        "unit_concept_id",
        "unit_source_value",
        "unit_source_concept_id"
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
        "deviceExposureId",
        "personId",
        "deviceConceptId",
        "deviceExposureStartDate",
        "deviceExposureStartDatetime",
        "deviceExposureEndDate",
        "deviceExposureEndDatetime",
        "deviceTypeConceptId",
        "uniqueDeviceId",
        "productionId",
        "quantity",
        "providerId",
        "visitOccurrenceId",
        "visitDetailId",
        "deviceSourceValue",
        "deviceSourceConceptId",
        "unitConceptId",
        "unitSourceValue",
        "unitSourceConceptId"
    };
    
    //
    // javaNamesProper
    //
    
    public static final String[] JAVA_NAMES_PROPER = {
        "DeviceExposureId",
        "PersonId",
        "DeviceConceptId",
        "DeviceExposureStartDate",
        "DeviceExposureStartDatetime",
        "DeviceExposureEndDate",
        "DeviceExposureEndDatetime",
        "DeviceTypeConceptId",
        "UniqueDeviceId",
        "ProductionId",
        "Quantity",
        "ProviderId",
        "VisitOccurrenceId",
        "VisitDetailId",
        "DeviceSourceValue",
        "DeviceSourceConceptId",
        "UnitConceptId",
        "UnitSourceValue",
        "UnitSourceConceptId"
    };
    
    
    //
    // member variables
    //
    
    private HashMap<String, String> descriptions = new HashMap<String, String>();
    
    private Integer deviceExposureId;
    
    private Integer personId;
    
    private Integer deviceConceptId;
    
    private Date deviceExposureStartDate;
    
    private Date deviceExposureStartDatetime;
    
    private Date deviceExposureEndDate;
    
    private Date deviceExposureEndDatetime;
    
    private Integer deviceTypeConceptId;
    
    private String uniqueDeviceId;
    
    private String productionId;
    
    private Integer quantity;
    
    private Integer providerId;
    
    private Integer visitOccurrenceId;
    
    private Integer visitDetailId;
    
    private String deviceSourceValue;
    
    private Integer deviceSourceConceptId;
    
    private Integer unitConceptId;
    
    private String unitSourceValue;
    
    private Integer unitSourceConceptId;
    
    //
    // trivial getters and setters
    //
    
    // deviceExposureId
    
    public void setDeviceExposureId(Integer val) {
        this.deviceExposureId = val;
    }
    
    public Integer getDeviceExposureId() {
        return this.deviceExposureId;
    }
    
    // personId
    
    public void setPersonId(Integer val) {
        this.personId = val;
    }
    
    public Integer getPersonId() {
        return this.personId;
    }
    
    // deviceConceptId
    
    public void setDeviceConceptId(Integer val) {
        this.deviceConceptId = val;
    }
    
    public Integer getDeviceConceptId() {
        return this.deviceConceptId;
    }
    
    // deviceExposureStartDate
    
    public void setDeviceExposureStartDate(Date val) {
        this.deviceExposureStartDate = val;
    }
    
    public Date getDeviceExposureStartDate() {
        return this.deviceExposureStartDate;
    }
    
    // deviceExposureStartDatetime
    
    public void setDeviceExposureStartDatetime(Date val) {
        this.deviceExposureStartDatetime = val;
    }
    
    public Date getDeviceExposureStartDatetime() {
        return this.deviceExposureStartDatetime;
    }
    
    // deviceExposureEndDate
    
    public void setDeviceExposureEndDate(Date val) {
        this.deviceExposureEndDate = val;
    }
    
    public Date getDeviceExposureEndDate() {
        return this.deviceExposureEndDate;
    }
    
    // deviceExposureEndDatetime
    
    public void setDeviceExposureEndDatetime(Date val) {
        this.deviceExposureEndDatetime = val;
    }
    
    public Date getDeviceExposureEndDatetime() {
        return this.deviceExposureEndDatetime;
    }
    
    // deviceTypeConceptId
    
    public void setDeviceTypeConceptId(Integer val) {
        this.deviceTypeConceptId = val;
    }
    
    public Integer getDeviceTypeConceptId() {
        return this.deviceTypeConceptId;
    }
    
    // uniqueDeviceId
    
    public void setUniqueDeviceId(String val) {
        this.uniqueDeviceId = val;
    }
    
    public String getUniqueDeviceId() {
        return this.uniqueDeviceId;
    }
    
    // productionId
    
    public void setProductionId(String val) {
        this.productionId = val;
    }
    
    public String getProductionId() {
        return this.productionId;
    }
    
    // quantity
    
    public void setQuantity(Integer val) {
        this.quantity = val;
    }
    
    public Integer getQuantity() {
        return this.quantity;
    }
    
    // providerId
    
    public void setProviderId(Integer val) {
        this.providerId = val;
    }
    
    public Integer getProviderId() {
        return this.providerId;
    }
    
    // visitOccurrenceId
    
    public void setVisitOccurrenceId(Integer val) {
        this.visitOccurrenceId = val;
    }
    
    public Integer getVisitOccurrenceId() {
        return this.visitOccurrenceId;
    }
    
    // visitDetailId
    
    public void setVisitDetailId(Integer val) {
        this.visitDetailId = val;
    }
    
    public Integer getVisitDetailId() {
        return this.visitDetailId;
    }
    
    // deviceSourceValue
    
    public void setDeviceSourceValue(String val) {
        this.deviceSourceValue = val;
    }
    
    public String getDeviceSourceValue() {
        return this.deviceSourceValue;
    }
    
    // deviceSourceConceptId
    
    public void setDeviceSourceConceptId(Integer val) {
        this.deviceSourceConceptId = val;
    }
    
    public Integer getDeviceSourceConceptId() {
        return this.deviceSourceConceptId;
    }
    
    // unitConceptId
    
    public void setUnitConceptId(Integer val) {
        this.unitConceptId = val;
    }
    
    public Integer getUnitConceptId() {
        return this.unitConceptId;
    }
    
    // unitSourceValue
    
    public void setUnitSourceValue(String val) {
        this.unitSourceValue = val;
    }
    
    public String getUnitSourceValue() {
        return this.unitSourceValue;
    }
    
    // unitSourceConceptId
    
    public void setUnitSourceConceptId(Integer val) {
        this.unitSourceConceptId = val;
    }
    
    public Integer getUnitSourceConceptId() {
        return this.unitSourceConceptId;
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
