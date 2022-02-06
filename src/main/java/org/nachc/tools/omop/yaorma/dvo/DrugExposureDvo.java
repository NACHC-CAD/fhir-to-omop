//
// Data Value Object (DVO) for drug_exposure
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
    
    public static final String TABLE_NAME = "drug_exposure";
    
    //
    // schemaName
    //
    
    public static final String SCHEMA_NAME = "synthea_omop";
    
    //
    // columnNames
    //
    
    public static final String[] COLUMN_NAMES = {
        "days_supply",
        "dose_unit_source_value",
        "drug_concept_id",
        "drug_exposure_end_date",
        "drug_exposure_end_datetime",
        "drug_exposure_id",
        "drug_exposure_start_date",
        "drug_exposure_start_datetime",
        "drug_source_concept_id",
        "drug_source_value",
        "drug_type_concept_id",
        "lot_number",
        "person_id",
        "provider_id",
        "quantity",
        "refills",
        "route_concept_id",
        "route_source_value",
        "sig",
        "stop_reason",
        "verbatim_end_date",
        "visit_detail_id",
        "visit_occurrence_id"
    };
    
    //
    // primaryKeyColumnNames
    //
    
    public static final String[] PRIMARY_KEY_COLUMN_NAMES = {
        "drug_exposure_id"
    };
    
    //
    // javaNames
    //
    
    public static final String[] JAVA_NAMES = {
        "daysSupply",
        "doseUnitSourceValue",
        "drugConceptId",
        "drugExposureEndDate",
        "drugExposureEndDatetime",
        "drugExposureId",
        "drugExposureStartDate",
        "drugExposureStartDatetime",
        "drugSourceConceptId",
        "drugSourceValue",
        "drugTypeConceptId",
        "lotNumber",
        "personId",
        "providerId",
        "quantity",
        "refills",
        "routeConceptId",
        "routeSourceValue",
        "sig",
        "stopReason",
        "verbatimEndDate",
        "visitDetailId",
        "visitOccurrenceId"
    };
    
    //
    // javaNamesProper
    //
    
    public static final String[] JAVA_NAMES_PROPER = {
        "DaysSupply",
        "DoseUnitSourceValue",
        "DrugConceptId",
        "DrugExposureEndDate",
        "DrugExposureEndDatetime",
        "DrugExposureId",
        "DrugExposureStartDate",
        "DrugExposureStartDatetime",
        "DrugSourceConceptId",
        "DrugSourceValue",
        "DrugTypeConceptId",
        "LotNumber",
        "PersonId",
        "ProviderId",
        "Quantity",
        "Refills",
        "RouteConceptId",
        "RouteSourceValue",
        "Sig",
        "StopReason",
        "VerbatimEndDate",
        "VisitDetailId",
        "VisitOccurrenceId"
    };
    
    
    //
    // member variables
    //
    
    private HashMap<String, String> descriptions = new HashMap<String, String>();
    
    private Integer daysSupply;
    
    private String doseUnitSourceValue;
    
    private Integer drugConceptId;
    
    private Date drugExposureEndDate;
    
    private String drugExposureEndDatetime;
    
    private Integer drugExposureId;
    
    private Date drugExposureStartDate;
    
    private String drugExposureStartDatetime;
    
    private Integer drugSourceConceptId;
    
    private String drugSourceValue;
    
    private Integer drugTypeConceptId;
    
    private String lotNumber;
    
    private Integer personId;
    
    private Integer providerId;
    
    private String quantity;
    
    private Integer refills;
    
    private Integer routeConceptId;
    
    private String routeSourceValue;
    
    private String sig;
    
    private String stopReason;
    
    private Date verbatimEndDate;
    
    private Integer visitDetailId;
    
    private Integer visitOccurrenceId;
    
    private ConceptDvo drugConceptDvo;
    
    private ConceptDvo drugSourceConceptDvo;
    
    private ConceptDvo drugTypeConceptDvo;
    
    private PersonDvo personDvo;
    
    private ProviderDvo providerDvo;
    
    private ConceptDvo routeConceptDvo;
    
    private VisitDetailDvo visitDetailDvo;
    
    private VisitOccurrenceDvo visitOccurrenceDvo;
    
    //
    // trivial getters and setters
    //
    
    // daysSupply
    
    public void setDaysSupply(Integer val) {
        this.daysSupply = val;
    }
    
    public Integer getDaysSupply() {
        return this.daysSupply;
    }
    
    // doseUnitSourceValue
    
    public void setDoseUnitSourceValue(String val) {
        this.doseUnitSourceValue = val;
    }
    
    public String getDoseUnitSourceValue() {
        return this.doseUnitSourceValue;
    }
    
    // drugConceptId
    
    public void setDrugConceptId(Integer val) {
        this.drugConceptId = val;
    }
    
    public Integer getDrugConceptId() {
        return this.drugConceptId;
    }
    
    // drugExposureEndDate
    
    public void setDrugExposureEndDate(Date val) {
        this.drugExposureEndDate = val;
    }
    
    public Date getDrugExposureEndDate() {
        return this.drugExposureEndDate;
    }
    
    // drugExposureEndDatetime
    
    public void setDrugExposureEndDatetime(String val) {
        this.drugExposureEndDatetime = val;
    }
    
    public String getDrugExposureEndDatetime() {
        return this.drugExposureEndDatetime;
    }
    
    // drugExposureId
    
    public void setDrugExposureId(Integer val) {
        this.drugExposureId = val;
    }
    
    public Integer getDrugExposureId() {
        return this.drugExposureId;
    }
    
    // drugExposureStartDate
    
    public void setDrugExposureStartDate(Date val) {
        this.drugExposureStartDate = val;
    }
    
    public Date getDrugExposureStartDate() {
        return this.drugExposureStartDate;
    }
    
    // drugExposureStartDatetime
    
    public void setDrugExposureStartDatetime(String val) {
        this.drugExposureStartDatetime = val;
    }
    
    public String getDrugExposureStartDatetime() {
        return this.drugExposureStartDatetime;
    }
    
    // drugSourceConceptId
    
    public void setDrugSourceConceptId(Integer val) {
        this.drugSourceConceptId = val;
    }
    
    public Integer getDrugSourceConceptId() {
        return this.drugSourceConceptId;
    }
    
    // drugSourceValue
    
    public void setDrugSourceValue(String val) {
        this.drugSourceValue = val;
    }
    
    public String getDrugSourceValue() {
        return this.drugSourceValue;
    }
    
    // drugTypeConceptId
    
    public void setDrugTypeConceptId(Integer val) {
        this.drugTypeConceptId = val;
    }
    
    public Integer getDrugTypeConceptId() {
        return this.drugTypeConceptId;
    }
    
    // lotNumber
    
    public void setLotNumber(String val) {
        this.lotNumber = val;
    }
    
    public String getLotNumber() {
        return this.lotNumber;
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
    
    // quantity
    
    public void setQuantity(String val) {
        this.quantity = val;
    }
    
    public String getQuantity() {
        return this.quantity;
    }
    
    // refills
    
    public void setRefills(Integer val) {
        this.refills = val;
    }
    
    public Integer getRefills() {
        return this.refills;
    }
    
    // routeConceptId
    
    public void setRouteConceptId(Integer val) {
        this.routeConceptId = val;
    }
    
    public Integer getRouteConceptId() {
        return this.routeConceptId;
    }
    
    // routeSourceValue
    
    public void setRouteSourceValue(String val) {
        this.routeSourceValue = val;
    }
    
    public String getRouteSourceValue() {
        return this.routeSourceValue;
    }
    
    // sig
    
    public void setSig(String val) {
        this.sig = val;
    }
    
    public String getSig() {
        return this.sig;
    }
    
    // stopReason
    
    public void setStopReason(String val) {
        this.stopReason = val;
    }
    
    public String getStopReason() {
        return this.stopReason;
    }
    
    // verbatimEndDate
    
    public void setVerbatimEndDate(Date val) {
        this.verbatimEndDate = val;
    }
    
    public Date getVerbatimEndDate() {
        return this.verbatimEndDate;
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
    
    // drugConceptDvo
    
    public void setDrugConceptDvo(ConceptDvo dvo) {
        this.drugConceptDvo = dvo;
    }
    
    public ConceptDvo getDrugConceptDvo() {
        return this.drugConceptDvo;
    }
    
    // drugSourceConceptDvo
    
    public void setDrugSourceConceptDvo(ConceptDvo dvo) {
        this.drugSourceConceptDvo = dvo;
    }
    
    public ConceptDvo getDrugSourceConceptDvo() {
        return this.drugSourceConceptDvo;
    }
    
    // drugTypeConceptDvo
    
    public void setDrugTypeConceptDvo(ConceptDvo dvo) {
        this.drugTypeConceptDvo = dvo;
    }
    
    public ConceptDvo getDrugTypeConceptDvo() {
        return this.drugTypeConceptDvo;
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
    
    // routeConceptDvo
    
    public void setRouteConceptDvo(ConceptDvo dvo) {
        this.routeConceptDvo = dvo;
    }
    
    public ConceptDvo getRouteConceptDvo() {
        return this.routeConceptDvo;
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
            getDrugExposureId()  == null ? null: getDrugExposureId() + ""
        };
        return rtn;
    }
}
