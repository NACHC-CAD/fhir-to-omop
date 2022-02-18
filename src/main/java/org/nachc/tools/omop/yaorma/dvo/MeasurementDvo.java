//
// Data Value Object (DVO) for measurement
//

package org.nachc.tools.omop.yaorma.dvo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;

import org.yaorma.dvo.Dvo;

public class MeasurementDvo implements Dvo {

    //
    // tableName
    //
    
    public static final String TABLE_NAME = "measurement";
    
    //
    // schemaName
    //
    
    public static final String SCHEMA_NAME = "synthea_omop.dbo";
    
    //
    // columnNames
    //
    
    public static final String[] COLUMN_NAMES = {
        "meas_event_field_concept_id",
        "measurement_concept_id",
        "measurement_date",
        "measurement_datetime",
        "measurement_event_id",
        "measurement_id",
        "measurement_source_concept_id",
        "measurement_source_value",
        "measurement_time",
        "measurement_type_concept_id",
        "operator_concept_id",
        "person_id",
        "provider_id",
        "range_high",
        "range_low",
        "unit_concept_id",
        "unit_source_concept_id",
        "unit_source_value",
        "value_as_concept_id",
        "value_as_number",
        "value_source_value",
        "visit_detail_id",
        "visit_occurrence_id"
    };
    
    //
    // primaryKeyColumnNames
    //
    
    public static final String[] PRIMARY_KEY_COLUMN_NAMES = {
        "measurement_id"
    };
    
    //
    // javaNames
    //
    
    public static final String[] JAVA_NAMES = {
        "measEventFieldConceptId",
        "measurementConceptId",
        "measurementDate",
        "measurementDatetime",
        "measurementEventId",
        "measurementId",
        "measurementSourceConceptId",
        "measurementSourceValue",
        "measurementTime",
        "measurementTypeConceptId",
        "operatorConceptId",
        "personId",
        "providerId",
        "rangeHigh",
        "rangeLow",
        "unitConceptId",
        "unitSourceConceptId",
        "unitSourceValue",
        "valueAsConceptId",
        "valueAsNumber",
        "valueSourceValue",
        "visitDetailId",
        "visitOccurrenceId"
    };
    
    //
    // javaNamesProper
    //
    
    public static final String[] JAVA_NAMES_PROPER = {
        "MeasEventFieldConceptId",
        "MeasurementConceptId",
        "MeasurementDate",
        "MeasurementDatetime",
        "MeasurementEventId",
        "MeasurementId",
        "MeasurementSourceConceptId",
        "MeasurementSourceValue",
        "MeasurementTime",
        "MeasurementTypeConceptId",
        "OperatorConceptId",
        "PersonId",
        "ProviderId",
        "RangeHigh",
        "RangeLow",
        "UnitConceptId",
        "UnitSourceConceptId",
        "UnitSourceValue",
        "ValueAsConceptId",
        "ValueAsNumber",
        "ValueSourceValue",
        "VisitDetailId",
        "VisitOccurrenceId"
    };
    
    
    //
    // member variables
    //
    
    private HashMap<String, String> descriptions = new HashMap<String, String>();
    
    private Integer measEventFieldConceptId;
    
    private Integer measurementConceptId;
    
    private Date measurementDate;
    
    private String measurementDatetime;
    
    private String measurementEventId;
    
    private Integer measurementId;
    
    private Integer measurementSourceConceptId;
    
    private String measurementSourceValue;
    
    private String measurementTime;
    
    private Integer measurementTypeConceptId;
    
    private Integer operatorConceptId;
    
    private Integer personId;
    
    private Integer providerId;
    
    private String rangeHigh;
    
    private String rangeLow;
    
    private Integer unitConceptId;
    
    private Integer unitSourceConceptId;
    
    private String unitSourceValue;
    
    private Integer valueAsConceptId;
    
    private String valueAsNumber;
    
    private String valueSourceValue;
    
    private Integer visitDetailId;
    
    private Integer visitOccurrenceId;
    
    private ConceptDvo measurementConceptDvo;
    
    private ConceptDvo measurementSourceConceptDvo;
    
    private ConceptDvo measurementTypeConceptDvo;
    
    private ConceptDvo measEventFieldConceptDvo;
    
    private ConceptDvo operatorConceptDvo;
    
    private PersonDvo personDvo;
    
    private ProviderDvo providerDvo;
    
    private ConceptDvo unitConceptDvo;
    
    private ConceptDvo unitSourceConceptDvo;
    
    private ConceptDvo valueAsConceptDvo;
    
    private VisitDetailDvo visitDetailDvo;
    
    private VisitOccurrenceDvo visitOccurrenceDvo;
    
    //
    // trivial getters and setters
    //
    
    // measEventFieldConceptId
    
    public void setMeasEventFieldConceptId(Integer val) {
        this.measEventFieldConceptId = val;
    }
    
    public Integer getMeasEventFieldConceptId() {
        return this.measEventFieldConceptId;
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
    
    public void setMeasurementDatetime(String val) {
        this.measurementDatetime = val;
    }
    
    public String getMeasurementDatetime() {
        return this.measurementDatetime;
    }
    
    // measurementEventId
    
    public void setMeasurementEventId(String val) {
        this.measurementEventId = val;
    }
    
    public String getMeasurementEventId() {
        return this.measurementEventId;
    }
    
    // measurementId
    
    public void setMeasurementId(Integer val) {
        this.measurementId = val;
    }
    
    public Integer getMeasurementId() {
        return this.measurementId;
    }
    
    // measurementSourceConceptId
    
    public void setMeasurementSourceConceptId(Integer val) {
        this.measurementSourceConceptId = val;
    }
    
    public Integer getMeasurementSourceConceptId() {
        return this.measurementSourceConceptId;
    }
    
    // measurementSourceValue
    
    public void setMeasurementSourceValue(String val) {
        this.measurementSourceValue = val;
    }
    
    public String getMeasurementSourceValue() {
        return this.measurementSourceValue;
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
    
    // personId
    
    public void setPersonId(Integer val) {
        this.personId = val;
    }
    
    public Integer getPersonId() {
        return this.personId;
    }
    
    // providerId
    
    public void setProviderId(Integer val) {
        this.providerId = val;
    }
    
    public Integer getProviderId() {
        return this.providerId;
    }
    
    // rangeHigh
    
    public void setRangeHigh(String val) {
        this.rangeHigh = val;
    }
    
    public String getRangeHigh() {
        return this.rangeHigh;
    }
    
    // rangeLow
    
    public void setRangeLow(String val) {
        this.rangeLow = val;
    }
    
    public String getRangeLow() {
        return this.rangeLow;
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
    
    // valueAsConceptId
    
    public void setValueAsConceptId(Integer val) {
        this.valueAsConceptId = val;
    }
    
    public Integer getValueAsConceptId() {
        return this.valueAsConceptId;
    }
    
    // valueAsNumber
    
    public void setValueAsNumber(String val) {
        this.valueAsNumber = val;
    }
    
    public String getValueAsNumber() {
        return this.valueAsNumber;
    }
    
    // valueSourceValue
    
    public void setValueSourceValue(String val) {
        this.valueSourceValue = val;
    }
    
    public String getValueSourceValue() {
        return this.valueSourceValue;
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
    
    // measurementConceptDvo
    
    public void setMeasurementConceptDvo(ConceptDvo dvo) {
        this.measurementConceptDvo = dvo;
    }
    
    public ConceptDvo getMeasurementConceptDvo() {
        return this.measurementConceptDvo;
    }
    
    // measurementSourceConceptDvo
    
    public void setMeasurementSourceConceptDvo(ConceptDvo dvo) {
        this.measurementSourceConceptDvo = dvo;
    }
    
    public ConceptDvo getMeasurementSourceConceptDvo() {
        return this.measurementSourceConceptDvo;
    }
    
    // measurementTypeConceptDvo
    
    public void setMeasurementTypeConceptDvo(ConceptDvo dvo) {
        this.measurementTypeConceptDvo = dvo;
    }
    
    public ConceptDvo getMeasurementTypeConceptDvo() {
        return this.measurementTypeConceptDvo;
    }
    
    // measEventFieldConceptDvo
    
    public void setMeasEventFieldConceptDvo(ConceptDvo dvo) {
        this.measEventFieldConceptDvo = dvo;
    }
    
    public ConceptDvo getMeasEventFieldConceptDvo() {
        return this.measEventFieldConceptDvo;
    }
    
    // operatorConceptDvo
    
    public void setOperatorConceptDvo(ConceptDvo dvo) {
        this.operatorConceptDvo = dvo;
    }
    
    public ConceptDvo getOperatorConceptDvo() {
        return this.operatorConceptDvo;
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
    
    // valueAsConceptDvo
    
    public void setValueAsConceptDvo(ConceptDvo dvo) {
        this.valueAsConceptDvo = dvo;
    }
    
    public ConceptDvo getValueAsConceptDvo() {
        return this.valueAsConceptDvo;
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
            getMeasurementId()  == null ? null: getMeasurementId() + ""
        };
        return rtn;
    }
}
