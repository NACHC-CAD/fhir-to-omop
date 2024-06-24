//
// Data Value Object (DVO) for MEASUREMENT
//

package org.nachc.tools.omop.yaorma.dvo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;
import java.math.BigDecimal;

import org.yaorma.dvo.Dvo;

public class MeasurementDvo implements Dvo {

    //
    // tableName
    //
    
    public static final String TABLE_NAME = "MEASUREMENT";
    
    //
    // schemaName
    //
    
    public static final String SCHEMA_NAME = "synthea_micro.dbo";
    
    //
    // columnNames
    //
    
    public static final String[] COLUMN_NAMES = {
        "measurement_id",
        "person_id",
        "measurement_concept_id",
        "measurement_date",
        "measurement_datetime",
        "measurement_time",
        "measurement_type_concept_id",
        "operator_concept_id",
        "value_as_number",
        "value_as_concept_id",
        "unit_concept_id",
        "range_low",
        "range_high",
        "provider_id",
        "visit_occurrence_id",
        "visit_detail_id",
        "measurement_source_value",
        "measurement_source_concept_id",
        "unit_source_value",
        "unit_source_concept_id",
        "value_source_value",
        "measurement_event_id",
        "meas_event_field_concept_id"
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
        "measurementId",
        "personId",
        "measurementConceptId",
        "measurementDate",
        "measurementDatetime",
        "measurementTime",
        "measurementTypeConceptId",
        "operatorConceptId",
        "valueAsNumber",
        "valueAsConceptId",
        "unitConceptId",
        "rangeLow",
        "rangeHigh",
        "providerId",
        "visitOccurrenceId",
        "visitDetailId",
        "measurementSourceValue",
        "measurementSourceConceptId",
        "unitSourceValue",
        "unitSourceConceptId",
        "valueSourceValue",
        "measurementEventId",
        "measEventFieldConceptId"
    };
    
    //
    // javaNamesProper
    //
    
    public static final String[] JAVA_NAMES_PROPER = {
        "MeasurementId",
        "PersonId",
        "MeasurementConceptId",
        "MeasurementDate",
        "MeasurementDatetime",
        "MeasurementTime",
        "MeasurementTypeConceptId",
        "OperatorConceptId",
        "ValueAsNumber",
        "ValueAsConceptId",
        "UnitConceptId",
        "RangeLow",
        "RangeHigh",
        "ProviderId",
        "VisitOccurrenceId",
        "VisitDetailId",
        "MeasurementSourceValue",
        "MeasurementSourceConceptId",
        "UnitSourceValue",
        "UnitSourceConceptId",
        "ValueSourceValue",
        "MeasurementEventId",
        "MeasEventFieldConceptId"
    };
    
    
    //
    // member variables
    //
    
    private HashMap<String, String> descriptions = new HashMap<String, String>();
    
    private Integer measurementId;
    
    private Integer personId;
    
    private Integer measurementConceptId;
    
    private Date measurementDate;
    
    private Date measurementDatetime;
    
    private String measurementTime;
    
    private Integer measurementTypeConceptId;
    
    private Integer operatorConceptId;
    
    private BigDecimal valueAsNumber;
    
    private Integer valueAsConceptId;
    
    private Integer unitConceptId;
    
    private BigDecimal rangeLow;
    
    private BigDecimal rangeHigh;
    
    private Integer providerId;
    
    private Integer visitOccurrenceId;
    
    private Integer visitDetailId;
    
    private String measurementSourceValue;
    
    private Integer measurementSourceConceptId;
    
    private String unitSourceValue;
    
    private Integer unitSourceConceptId;
    
    private String valueSourceValue;
    
    private Long measurementEventId;
    
    private Integer measEventFieldConceptId;
    
    //
    // trivial getters and setters
    //
    
    // measurementId
    
    public void setMeasurementId(Integer val) {
        this.measurementId = val;
    }
    
    public Integer getMeasurementId() {
        return this.measurementId;
    }
    
    // personId
    
    public void setPersonId(Integer val) {
        this.personId = val;
    }
    
    public Integer getPersonId() {
        return this.personId;
    }
    
    // measurementConceptId
    
    public void setMeasurementConceptId(Integer val) {
        this.measurementConceptId = val;
    }
    
    public Integer getMeasurementConceptId() {
        return this.measurementConceptId;
    }
    
    // measurementDate
    
    public void setMeasurementDate(Date val) {
        this.measurementDate = val;
    }
    
    public Date getMeasurementDate() {
        return this.measurementDate;
    }
    
    // measurementDatetime
    
    public void setMeasurementDatetime(Date val) {
        this.measurementDatetime = val;
    }
    
    public Date getMeasurementDatetime() {
        return this.measurementDatetime;
    }
    
    // measurementTime
    
    public void setMeasurementTime(String val) {
        this.measurementTime = val;
    }
    
    public String getMeasurementTime() {
        return this.measurementTime;
    }
    
    // measurementTypeConceptId
    
    public void setMeasurementTypeConceptId(Integer val) {
        this.measurementTypeConceptId = val;
    }
    
    public Integer getMeasurementTypeConceptId() {
        return this.measurementTypeConceptId;
    }
    
    // operatorConceptId
    
    public void setOperatorConceptId(Integer val) {
        this.operatorConceptId = val;
    }
    
    public Integer getOperatorConceptId() {
        return this.operatorConceptId;
    }
    
    // valueAsNumber
    
    public void setValueAsNumber(BigDecimal val) {
        this.valueAsNumber = val;
    }
    
    public BigDecimal getValueAsNumber() {
        return this.valueAsNumber;
    }
    
    // valueAsConceptId
    
    public void setValueAsConceptId(Integer val) {
        this.valueAsConceptId = val;
    }
    
    public Integer getValueAsConceptId() {
        return this.valueAsConceptId;
    }
    
    // unitConceptId
    
    public void setUnitConceptId(Integer val) {
        this.unitConceptId = val;
    }
    
    public Integer getUnitConceptId() {
        return this.unitConceptId;
    }
    
    // rangeLow
    
    public void setRangeLow(BigDecimal val) {
        this.rangeLow = val;
    }
    
    public BigDecimal getRangeLow() {
        return this.rangeLow;
    }
    
    // rangeHigh
    
    public void setRangeHigh(BigDecimal val) {
        this.rangeHigh = val;
    }
    
    public BigDecimal getRangeHigh() {
        return this.rangeHigh;
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
    
    // measurementSourceValue
    
    public void setMeasurementSourceValue(String val) {
        this.measurementSourceValue = val;
    }
    
    public String getMeasurementSourceValue() {
        return this.measurementSourceValue;
    }
    
    // measurementSourceConceptId
    
    public void setMeasurementSourceConceptId(Integer val) {
        this.measurementSourceConceptId = val;
    }
    
    public Integer getMeasurementSourceConceptId() {
        return this.measurementSourceConceptId;
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
    
    // valueSourceValue
    
    public void setValueSourceValue(String val) {
        this.valueSourceValue = val;
    }
    
    public String getValueSourceValue() {
        return this.valueSourceValue;
    }
    
    // measurementEventId
    
    public void setMeasurementEventId(Long val) {
        this.measurementEventId = val;
    }
    
    public Long getMeasurementEventId() {
        return this.measurementEventId;
    }
    
    // measEventFieldConceptId
    
    public void setMeasEventFieldConceptId(Integer val) {
        this.measEventFieldConceptId = val;
    }
    
    public Integer getMeasEventFieldConceptId() {
        return this.measEventFieldConceptId;
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
