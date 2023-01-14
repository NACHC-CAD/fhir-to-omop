//
// Data Value Object (DVO) for DRUG_EXPOSURE
//

package org.nachc.tools.omop.yaorma.dvo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;

import org.yaorma.dvo.Dvo;

public class DrugExposureDvo implements Dvo {

    //
    // tableName
    //
    
    public static final String TABLE_NAME = "DRUG_EXPOSURE";
    
    //
    // schemaName
    //
    
    public static final String SCHEMA_NAME = "synthea_omop.dbo";
    
    //
    // columnNames
    //
    
    public static final String[] COLUMN_NAMES = {
        "drug_exposure_id",
        "person_id",
        "drug_concept_id",
        "drug_exposure_start_date",
        "drug_exposure_start_datetime",
        "drug_exposure_end_date",
        "drug_exposure_end_datetime",
        "verbatim_end_date",
        "drug_type_concept_id",
        "stop_reason",
        "refills",
        "quantity",
        "days_supply",
        "sig",
        "route_concept_id",
        "lot_number",
        "provider_id",
        "visit_occurrence_id",
        "visit_detail_id",
        "drug_source_value",
        "drug_source_concept_id",
        "route_source_value",
        "dose_unit_source_value"
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
        "drugExposureId",
        "personId",
        "drugConceptId",
        "drugExposureStartDate",
        "drugExposureStartDatetime",
        "drugExposureEndDate",
        "drugExposureEndDatetime",
        "verbatimEndDate",
        "drugTypeConceptId",
        "stopReason",
        "refills",
        "quantity",
        "daysSupply",
        "sig",
        "routeConceptId",
        "lotNumber",
        "providerId",
        "visitOccurrenceId",
        "visitDetailId",
        "drugSourceValue",
        "drugSourceConceptId",
        "routeSourceValue",
        "doseUnitSourceValue"
    };
    
    //
    // javaNamesProper
    //
    
    public static final String[] JAVA_NAMES_PROPER = {
        "DrugExposureId",
        "PersonId",
        "DrugConceptId",
        "DrugExposureStartDate",
        "DrugExposureStartDatetime",
        "DrugExposureEndDate",
        "DrugExposureEndDatetime",
        "VerbatimEndDate",
        "DrugTypeConceptId",
        "StopReason",
        "Refills",
        "Quantity",
        "DaysSupply",
        "Sig",
        "RouteConceptId",
        "LotNumber",
        "ProviderId",
        "VisitOccurrenceId",
        "VisitDetailId",
        "DrugSourceValue",
        "DrugSourceConceptId",
        "RouteSourceValue",
        "DoseUnitSourceValue"
    };
    
    
    //
    // member variables
    //
    
    private HashMap<String, String> descriptions = new HashMap<String, String>();
    
    private Integer drugExposureId;
    
    private Integer personId;
    
    private Integer drugConceptId;
    
    private Date drugExposureStartDate;
    
    private Date drugExposureStartDatetime;
    
    private Date drugExposureEndDate;
    
    private Date drugExposureEndDatetime;
    
    private Date verbatimEndDate;
    
    private Integer drugTypeConceptId;
    
    private String stopReason;
    
    private Integer refills;
    
    private String quantity;
    
    private Integer daysSupply;
    
    private String sig;
    
    private Integer routeConceptId;
    
    private String lotNumber;
    
    private Integer providerId;
    
    private Integer visitOccurrenceId;
    
    private Integer visitDetailId;
    
    private String drugSourceValue;
    
    private Integer drugSourceConceptId;
    
    private String routeSourceValue;
    
    private String doseUnitSourceValue;
    
    //
    // trivial getters and setters
    //
    
    // drugExposureId
    
    public void setDrugExposureId(Integer val) {
        this.drugExposureId = val;
    }
    
    public Integer getDrugExposureId() {
        return this.drugExposureId;
    }
    
    // personId
    
    public void setPersonId(Integer val) {
        this.personId = val;
    }
    
    public Integer getPersonId() {
        return this.personId;
    }
    
    // drugConceptId
    
    public void setDrugConceptId(Integer val) {
        this.drugConceptId = val;
    }
    
    public Integer getDrugConceptId() {
        return this.drugConceptId;
    }
    
    // drugExposureStartDate
    
    public void setDrugExposureStartDate(Date val) {
        this.drugExposureStartDate = val;
    }
    
    public Date getDrugExposureStartDate() {
        return this.drugExposureStartDate;
    }
    
    // drugExposureStartDatetime
    
    public void setDrugExposureStartDatetime(Date val) {
        this.drugExposureStartDatetime = val;
    }
    
    public Date getDrugExposureStartDatetime() {
        return this.drugExposureStartDatetime;
    }
    
    // drugExposureEndDate
    
    public void setDrugExposureEndDate(Date val) {
        this.drugExposureEndDate = val;
    }
    
    public Date getDrugExposureEndDate() {
        return this.drugExposureEndDate;
    }
    
    // drugExposureEndDatetime
    
    public void setDrugExposureEndDatetime(Date val) {
        this.drugExposureEndDatetime = val;
    }
    
    public Date getDrugExposureEndDatetime() {
        return this.drugExposureEndDatetime;
    }
    
    // verbatimEndDate
    
    public void setVerbatimEndDate(Date val) {
        this.verbatimEndDate = val;
    }
    
    public Date getVerbatimEndDate() {
        return this.verbatimEndDate;
    }
    
    // drugTypeConceptId
    
    public void setDrugTypeConceptId(Integer val) {
        this.drugTypeConceptId = val;
    }
    
    public Integer getDrugTypeConceptId() {
        return this.drugTypeConceptId;
    }
    
    // stopReason
    
    public void setStopReason(String val) {
        this.stopReason = val;
    }
    
    public String getStopReason() {
        return this.stopReason;
    }
    
    // refills
    
    public void setRefills(Integer val) {
        this.refills = val;
    }
    
    public Integer getRefills() {
        return this.refills;
    }
    
    // quantity
    
    public void setQuantity(String val) {
        this.quantity = val;
    }
    
    public String getQuantity() {
        return this.quantity;
    }
    
    // daysSupply
    
    public void setDaysSupply(Integer val) {
        this.daysSupply = val;
    }
    
    public Integer getDaysSupply() {
        return this.daysSupply;
    }
    
    // sig
    
    public void setSig(String val) {
        this.sig = val;
    }
    
    public String getSig() {
        return this.sig;
    }
    
    // routeConceptId
    
    public void setRouteConceptId(Integer val) {
        this.routeConceptId = val;
    }
    
    public Integer getRouteConceptId() {
        return this.routeConceptId;
    }
    
    // lotNumber
    
    public void setLotNumber(String val) {
        this.lotNumber = val;
    }
    
    public String getLotNumber() {
        return this.lotNumber;
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
    
    // drugSourceValue
    
    public void setDrugSourceValue(String val) {
        this.drugSourceValue = val;
    }
    
    public String getDrugSourceValue() {
        return this.drugSourceValue;
    }
    
    // drugSourceConceptId
    
    public void setDrugSourceConceptId(Integer val) {
        this.drugSourceConceptId = val;
    }
    
    public Integer getDrugSourceConceptId() {
        return this.drugSourceConceptId;
    }
    
    // routeSourceValue
    
    public void setRouteSourceValue(String val) {
        this.routeSourceValue = val;
    }
    
    public String getRouteSourceValue() {
        return this.routeSourceValue;
    }
    
    // doseUnitSourceValue
    
    public void setDoseUnitSourceValue(String val) {
        this.doseUnitSourceValue = val;
    }
    
    public String getDoseUnitSourceValue() {
        return this.doseUnitSourceValue;
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
