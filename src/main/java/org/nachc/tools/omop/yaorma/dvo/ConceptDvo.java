//
// Data Value Object (DVO) for concept
//

package org.nachc.tools.omop.yaorma.dvo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;

import org.yaorma.dvo.Dvo;

public class ConceptDvo implements Dvo {

    //
    // tableName
    //
    
    public static final String TABLE_NAME = "concept";
    
    //
    // schemaName
    //
    
    public static final String SCHEMA_NAME = "synthea_omop.dbo";
    
    //
    // columnNames
    //
    
    public static final String[] COLUMN_NAMES = {
        "concept_class_id",
        "concept_code",
        "concept_id",
        "concept_name",
        "domain_id",
        "invalid_reason",
        "standard_concept",
        "valid_end_date",
        "valid_start_date",
        "vocabulary_id"
    };
    
    //
    // primaryKeyColumnNames
    //
    
    public static final String[] PRIMARY_KEY_COLUMN_NAMES = {
        "concept_id"
    };
    
    //
    // javaNames
    //
    
    public static final String[] JAVA_NAMES = {
        "conceptClassId",
        "conceptCode",
        "conceptId",
        "conceptName",
        "domainId",
        "invalidReason",
        "standardConcept",
        "validEndDate",
        "validStartDate",
        "vocabularyId"
    };
    
    //
    // javaNamesProper
    //
    
    public static final String[] JAVA_NAMES_PROPER = {
        "ConceptClassId",
        "ConceptCode",
        "ConceptId",
        "ConceptName",
        "DomainId",
        "InvalidReason",
        "StandardConcept",
        "ValidEndDate",
        "ValidStartDate",
        "VocabularyId"
    };
    
    
    //
    // member variables
    //
    
    private HashMap<String, String> descriptions = new HashMap<String, String>();
    
    private String conceptClassId;
    
    private String conceptCode;
    
    private Integer conceptId;
    
    private String conceptName;
    
    private String domainId;
    
    private String invalidReason;
    
    private String standardConcept;
    
    private Date validEndDate;
    
    private Date validStartDate;
    
    private String vocabularyId;
    
    private ArrayList<CareSiteDvo> careSitePlaceOfServiceConceptList = new ArrayList<CareSiteDvo>();
    
    private ArrayList<CdmSourceDvo> cdmSourceCdmVersionConceptList = new ArrayList<CdmSourceDvo>();
    
    private ArrayList<CohortDefinitionDvo> cohortDefinitionDefinitionTypeConceptList = new ArrayList<CohortDefinitionDvo>();
    
    private ArrayList<CohortDefinitionDvo> cohortDefinitionSubjectConceptList = new ArrayList<CohortDefinitionDvo>();
    
    private ArrayList<ConceptAncestorDvo> conceptAncestorAncestorConceptList = new ArrayList<ConceptAncestorDvo>();
    
    private ArrayList<ConceptAncestorDvo> conceptAncestorDescendantConceptList = new ArrayList<ConceptAncestorDvo>();
    
    private ArrayList<ConceptClassDvo> conceptClassConceptClassConceptList = new ArrayList<ConceptClassDvo>();
    
    private ArrayList<ConceptSynonymDvo> conceptSynonymConceptList = new ArrayList<ConceptSynonymDvo>();
    
    private ArrayList<ConceptSynonymDvo> conceptSynonymLanguageConceptList = new ArrayList<ConceptSynonymDvo>();
    
    private ArrayList<ConditionEraDvo> conditionEraConditionConceptList = new ArrayList<ConditionEraDvo>();
    
    private ArrayList<ConditionOccurrenceDvo> conditionOccurrenceConditionConceptList = new ArrayList<ConditionOccurrenceDvo>();
    
    private ArrayList<ConditionOccurrenceDvo> conditionOccurrenceConditionTypeConceptList = new ArrayList<ConditionOccurrenceDvo>();
    
    private ArrayList<ConditionOccurrenceDvo> conditionOccurrenceConditionStatusConceptList = new ArrayList<ConditionOccurrenceDvo>();
    
    private ArrayList<ConditionOccurrenceDvo> conditionOccurrenceConditionSourceConceptList = new ArrayList<ConditionOccurrenceDvo>();
    
    private ArrayList<CostDvo> costCostTypeConceptList = new ArrayList<CostDvo>();
    
    private ArrayList<CostDvo> costCurrencyConceptList = new ArrayList<CostDvo>();
    
    private ArrayList<CostDvo> costRevenueCodeConceptList = new ArrayList<CostDvo>();
    
    private ArrayList<CostDvo> costDrgConceptList = new ArrayList<CostDvo>();
    
    private ArrayList<DeathDvo> deathDeathTypeConceptList = new ArrayList<DeathDvo>();
    
    private ArrayList<DeathDvo> deathCauseConceptList = new ArrayList<DeathDvo>();
    
    private ArrayList<DeathDvo> deathCauseSourceConceptList = new ArrayList<DeathDvo>();
    
    private ArrayList<DeviceExposureDvo> deviceExposureDeviceConceptList = new ArrayList<DeviceExposureDvo>();
    
    private ArrayList<DeviceExposureDvo> deviceExposureDeviceTypeConceptList = new ArrayList<DeviceExposureDvo>();
    
    private ArrayList<DeviceExposureDvo> deviceExposureDeviceSourceConceptList = new ArrayList<DeviceExposureDvo>();
    
    private ArrayList<DeviceExposureDvo> deviceExposureUnitConceptList = new ArrayList<DeviceExposureDvo>();
    
    private ArrayList<DeviceExposureDvo> deviceExposureUnitSourceConceptList = new ArrayList<DeviceExposureDvo>();
    
    private ArrayList<DomainDvo> domainDomainConceptList = new ArrayList<DomainDvo>();
    
    private ArrayList<DoseEraDvo> doseEraDrugConceptList = new ArrayList<DoseEraDvo>();
    
    private ArrayList<DoseEraDvo> doseEraUnitConceptList = new ArrayList<DoseEraDvo>();
    
    private ArrayList<DrugEraDvo> drugEraDrugConceptList = new ArrayList<DrugEraDvo>();
    
    private ArrayList<DrugExposureDvo> drugExposureDrugConceptList = new ArrayList<DrugExposureDvo>();
    
    private ArrayList<DrugExposureDvo> drugExposureDrugTypeConceptList = new ArrayList<DrugExposureDvo>();
    
    private ArrayList<DrugExposureDvo> drugExposureRouteConceptList = new ArrayList<DrugExposureDvo>();
    
    private ArrayList<DrugExposureDvo> drugExposureDrugSourceConceptList = new ArrayList<DrugExposureDvo>();
    
    private ArrayList<DrugStrengthDvo> drugStrengthDrugConceptList = new ArrayList<DrugStrengthDvo>();
    
    private ArrayList<DrugStrengthDvo> drugStrengthIngredientConceptList = new ArrayList<DrugStrengthDvo>();
    
    private ArrayList<DrugStrengthDvo> drugStrengthAmountUnitConceptList = new ArrayList<DrugStrengthDvo>();
    
    private ArrayList<DrugStrengthDvo> drugStrengthNumeratorUnitConceptList = new ArrayList<DrugStrengthDvo>();
    
    private ArrayList<DrugStrengthDvo> drugStrengthDenominatorUnitConceptList = new ArrayList<DrugStrengthDvo>();
    
    private ArrayList<EpisodeDvo> episodeEpisodeConceptList = new ArrayList<EpisodeDvo>();
    
    private ArrayList<EpisodeDvo> episodeEpisodeObjectConceptList = new ArrayList<EpisodeDvo>();
    
    private ArrayList<EpisodeDvo> episodeEpisodeTypeConceptList = new ArrayList<EpisodeDvo>();
    
    private ArrayList<EpisodeDvo> episodeEpisodeSourceConceptList = new ArrayList<EpisodeDvo>();
    
    private ArrayList<EpisodeEventDvo> episodeEventEpisodeEventFieldConceptList = new ArrayList<EpisodeEventDvo>();
    
    private ArrayList<FactRelationshipDvo> factRelationshipDomainConceptId1List = new ArrayList<FactRelationshipDvo>();
    
    private ArrayList<FactRelationshipDvo> factRelationshipDomainConceptId2List = new ArrayList<FactRelationshipDvo>();
    
    private ArrayList<FactRelationshipDvo> factRelationshipRelationshipConceptList = new ArrayList<FactRelationshipDvo>();
    
    private ArrayList<LocationDvo> locationCountryConceptList = new ArrayList<LocationDvo>();
    
    private ArrayList<MeasurementDvo> measurementMeasurementConceptList = new ArrayList<MeasurementDvo>();
    
    private ArrayList<MeasurementDvo> measurementMeasurementTypeConceptList = new ArrayList<MeasurementDvo>();
    
    private ArrayList<MeasurementDvo> measurementOperatorConceptList = new ArrayList<MeasurementDvo>();
    
    private ArrayList<MeasurementDvo> measurementValueAsConceptList = new ArrayList<MeasurementDvo>();
    
    private ArrayList<MeasurementDvo> measurementUnitConceptList = new ArrayList<MeasurementDvo>();
    
    private ArrayList<MeasurementDvo> measurementMeasurementSourceConceptList = new ArrayList<MeasurementDvo>();
    
    private ArrayList<MeasurementDvo> measurementUnitSourceConceptList = new ArrayList<MeasurementDvo>();
    
    private ArrayList<MeasurementDvo> measurementMeasEventFieldConceptList = new ArrayList<MeasurementDvo>();
    
    private ArrayList<MetadataDvo> metadataMetadataConceptList = new ArrayList<MetadataDvo>();
    
    private ArrayList<MetadataDvo> metadataMetadataTypeConceptList = new ArrayList<MetadataDvo>();
    
    private ArrayList<MetadataDvo> metadataValueAsConceptList = new ArrayList<MetadataDvo>();
    
    private ArrayList<NoteDvo> noteNoteTypeConceptList = new ArrayList<NoteDvo>();
    
    private ArrayList<NoteDvo> noteNoteClassConceptList = new ArrayList<NoteDvo>();
    
    private ArrayList<NoteDvo> noteEncodingConceptList = new ArrayList<NoteDvo>();
    
    private ArrayList<NoteDvo> noteLanguageConceptList = new ArrayList<NoteDvo>();
    
    private ArrayList<NoteDvo> noteNoteEventFieldConceptList = new ArrayList<NoteDvo>();
    
    private ArrayList<NoteNlpDvo> noteNlpSectionConceptList = new ArrayList<NoteNlpDvo>();
    
    private ArrayList<NoteNlpDvo> noteNlpNoteNlpConceptList = new ArrayList<NoteNlpDvo>();
    
    private ArrayList<NoteNlpDvo> noteNlpNoteNlpSourceConceptList = new ArrayList<NoteNlpDvo>();
    
    private ArrayList<ObservationDvo> observationObservationConceptList = new ArrayList<ObservationDvo>();
    
    private ArrayList<ObservationDvo> observationObservationTypeConceptList = new ArrayList<ObservationDvo>();
    
    private ArrayList<ObservationDvo> observationValueAsConceptList = new ArrayList<ObservationDvo>();
    
    private ArrayList<ObservationDvo> observationQualifierConceptList = new ArrayList<ObservationDvo>();
    
    private ArrayList<ObservationDvo> observationUnitConceptList = new ArrayList<ObservationDvo>();
    
    private ArrayList<ObservationDvo> observationObservationSourceConceptList = new ArrayList<ObservationDvo>();
    
    private ArrayList<ObservationDvo> observationObsEventFieldConceptList = new ArrayList<ObservationDvo>();
    
    private ArrayList<ObservationPeriodDvo> observationPeriodPeriodTypeConceptList = new ArrayList<ObservationPeriodDvo>();
    
    private ArrayList<PayerPlanPeriodDvo> payerPlanPeriodPayerConceptList = new ArrayList<PayerPlanPeriodDvo>();
    
    private ArrayList<PayerPlanPeriodDvo> payerPlanPeriodPayerSourceConceptList = new ArrayList<PayerPlanPeriodDvo>();
    
    private ArrayList<PayerPlanPeriodDvo> payerPlanPeriodPlanConceptList = new ArrayList<PayerPlanPeriodDvo>();
    
    private ArrayList<PayerPlanPeriodDvo> payerPlanPeriodPlanSourceConceptList = new ArrayList<PayerPlanPeriodDvo>();
    
    private ArrayList<PayerPlanPeriodDvo> payerPlanPeriodSponsorConceptList = new ArrayList<PayerPlanPeriodDvo>();
    
    private ArrayList<PayerPlanPeriodDvo> payerPlanPeriodSponsorSourceConceptList = new ArrayList<PayerPlanPeriodDvo>();
    
    private ArrayList<PayerPlanPeriodDvo> payerPlanPeriodStopReasonConceptList = new ArrayList<PayerPlanPeriodDvo>();
    
    private ArrayList<PayerPlanPeriodDvo> payerPlanPeriodStopReasonSourceConceptList = new ArrayList<PayerPlanPeriodDvo>();
    
    private ArrayList<PersonDvo> personGenderConceptList = new ArrayList<PersonDvo>();
    
    private ArrayList<PersonDvo> personRaceConceptList = new ArrayList<PersonDvo>();
    
    private ArrayList<PersonDvo> personEthnicityConceptList = new ArrayList<PersonDvo>();
    
    private ArrayList<PersonDvo> personGenderSourceConceptList = new ArrayList<PersonDvo>();
    
    private ArrayList<PersonDvo> personRaceSourceConceptList = new ArrayList<PersonDvo>();
    
    private ArrayList<PersonDvo> personEthnicitySourceConceptList = new ArrayList<PersonDvo>();
    
    private ArrayList<ProcedureOccurrenceDvo> procedureOccurrenceProcedureConceptList = new ArrayList<ProcedureOccurrenceDvo>();
    
    private ArrayList<ProcedureOccurrenceDvo> procedureOccurrenceProcedureTypeConceptList = new ArrayList<ProcedureOccurrenceDvo>();
    
    private ArrayList<ProcedureOccurrenceDvo> procedureOccurrenceModifierConceptList = new ArrayList<ProcedureOccurrenceDvo>();
    
    private ArrayList<ProcedureOccurrenceDvo> procedureOccurrenceProcedureSourceConceptList = new ArrayList<ProcedureOccurrenceDvo>();
    
    private ArrayList<ProviderDvo> providerSpecialtyConceptList = new ArrayList<ProviderDvo>();
    
    private ArrayList<ProviderDvo> providerGenderConceptList = new ArrayList<ProviderDvo>();
    
    private ArrayList<ProviderDvo> providerSpecialtySourceConceptList = new ArrayList<ProviderDvo>();
    
    private ArrayList<ProviderDvo> providerGenderSourceConceptList = new ArrayList<ProviderDvo>();
    
    private ArrayList<RelationshipDvo> relationshipRelationshipConceptList = new ArrayList<RelationshipDvo>();
    
    private ArrayList<SourceToConceptMapDvo> sourceToConceptMapSourceConceptList = new ArrayList<SourceToConceptMapDvo>();
    
    private ArrayList<SourceToConceptMapDvo> sourceToConceptMapTargetConceptList = new ArrayList<SourceToConceptMapDvo>();
    
    private ArrayList<SpecimenDvo> specimenSpecimenConceptList = new ArrayList<SpecimenDvo>();
    
    private ArrayList<SpecimenDvo> specimenSpecimenTypeConceptList = new ArrayList<SpecimenDvo>();
    
    private ArrayList<SpecimenDvo> specimenUnitConceptList = new ArrayList<SpecimenDvo>();
    
    private ArrayList<SpecimenDvo> specimenAnatomicSiteConceptList = new ArrayList<SpecimenDvo>();
    
    private ArrayList<SpecimenDvo> specimenDiseaseStatusConceptList = new ArrayList<SpecimenDvo>();
    
    private ArrayList<VisitDetailDvo> visitDetailVisitDetailConceptList = new ArrayList<VisitDetailDvo>();
    
    private ArrayList<VisitDetailDvo> visitDetailVisitDetailTypeConceptList = new ArrayList<VisitDetailDvo>();
    
    private ArrayList<VisitDetailDvo> visitDetailVisitDetailSourceConceptList = new ArrayList<VisitDetailDvo>();
    
    private ArrayList<VisitDetailDvo> visitDetailAdmittedFromConceptList = new ArrayList<VisitDetailDvo>();
    
    private ArrayList<VisitDetailDvo> visitDetailDischargedToConceptList = new ArrayList<VisitDetailDvo>();
    
    private ArrayList<VisitOccurrenceDvo> visitOccurrenceVisitConceptList = new ArrayList<VisitOccurrenceDvo>();
    
    private ArrayList<VisitOccurrenceDvo> visitOccurrenceVisitTypeConceptList = new ArrayList<VisitOccurrenceDvo>();
    
    private ArrayList<VisitOccurrenceDvo> visitOccurrenceVisitSourceConceptList = new ArrayList<VisitOccurrenceDvo>();
    
    private ArrayList<VisitOccurrenceDvo> visitOccurrenceAdmittedFromConceptList = new ArrayList<VisitOccurrenceDvo>();
    
    private ArrayList<VisitOccurrenceDvo> visitOccurrenceDischargedToConceptList = new ArrayList<VisitOccurrenceDvo>();
    
    //
    // trivial getters and setters
    //
    
    // conceptClassId
    
    public void setConceptClassId(String val) {
        this.conceptClassId = val;
    }
    
    public String getConceptClassId() {
        return this.conceptClassId;
    }
    
    // conceptCode
    
    public void setConceptCode(String val) {
        this.conceptCode = val;
    }
    
    public String getConceptCode() {
        return this.conceptCode;
    }
    
    // conceptId
    
    public void setConceptId(Integer val) {
        this.conceptId = val;
    }
    
    public Integer getConceptId() {
        return this.conceptId;
    }
    
    // conceptName
    
    public void setConceptName(String val) {
        this.conceptName = val;
    }
    
    public String getConceptName() {
        return this.conceptName;
    }
    
    // domainId
    
    public void setDomainId(String val) {
        this.domainId = val;
    }
    
    public String getDomainId() {
        return this.domainId;
    }
    
    // invalidReason
    
    public void setInvalidReason(String val) {
        this.invalidReason = val;
    }
    
    public String getInvalidReason() {
        return this.invalidReason;
    }
    
    // standardConcept
    
    public void setStandardConcept(String val) {
        this.standardConcept = val;
    }
    
    public String getStandardConcept() {
        return this.standardConcept;
    }
    
    // validEndDate
    
    public void setValidEndDate(Date val) {
        this.validEndDate = val;
    }
    
    public Date getValidEndDate() {
        return this.validEndDate;
    }
    
    // validStartDate
    
    public void setValidStartDate(Date val) {
        this.validStartDate = val;
    }
    
    public Date getValidStartDate() {
        return this.validStartDate;
    }
    
    // vocabularyId
    
    public void setVocabularyId(String val) {
        this.vocabularyId = val;
    }
    
    public String getVocabularyId() {
        return this.vocabularyId;
    }
    
    public ArrayList<CareSiteDvo> getCareSitePlaceOfServiceConceptList() {
        return careSitePlaceOfServiceConceptList;
    }
    
    public void setCareSitePlaceOfServiceConceptList(ArrayList<CareSiteDvo> list) {
        this.careSitePlaceOfServiceConceptList = list;
    }
    
    public ArrayList<CdmSourceDvo> getCdmSourceCdmVersionConceptList() {
        return cdmSourceCdmVersionConceptList;
    }
    
    public void setCdmSourceCdmVersionConceptList(ArrayList<CdmSourceDvo> list) {
        this.cdmSourceCdmVersionConceptList = list;
    }
    
    public ArrayList<CohortDefinitionDvo> getCohortDefinitionDefinitionTypeConceptList() {
        return cohortDefinitionDefinitionTypeConceptList;
    }
    
    public void setCohortDefinitionDefinitionTypeConceptList(ArrayList<CohortDefinitionDvo> list) {
        this.cohortDefinitionDefinitionTypeConceptList = list;
    }
    
    public ArrayList<CohortDefinitionDvo> getCohortDefinitionSubjectConceptList() {
        return cohortDefinitionSubjectConceptList;
    }
    
    public void setCohortDefinitionSubjectConceptList(ArrayList<CohortDefinitionDvo> list) {
        this.cohortDefinitionSubjectConceptList = list;
    }
    
    public ArrayList<ConceptAncestorDvo> getConceptAncestorAncestorConceptList() {
        return conceptAncestorAncestorConceptList;
    }
    
    public void setConceptAncestorAncestorConceptList(ArrayList<ConceptAncestorDvo> list) {
        this.conceptAncestorAncestorConceptList = list;
    }
    
    public ArrayList<ConceptAncestorDvo> getConceptAncestorDescendantConceptList() {
        return conceptAncestorDescendantConceptList;
    }
    
    public void setConceptAncestorDescendantConceptList(ArrayList<ConceptAncestorDvo> list) {
        this.conceptAncestorDescendantConceptList = list;
    }
    
    public ArrayList<ConceptClassDvo> getConceptClassConceptClassConceptList() {
        return conceptClassConceptClassConceptList;
    }
    
    public void setConceptClassConceptClassConceptList(ArrayList<ConceptClassDvo> list) {
        this.conceptClassConceptClassConceptList = list;
    }
    
    public ArrayList<ConceptSynonymDvo> getConceptSynonymConceptList() {
        return conceptSynonymConceptList;
    }
    
    public void setConceptSynonymConceptList(ArrayList<ConceptSynonymDvo> list) {
        this.conceptSynonymConceptList = list;
    }
    
    public ArrayList<ConceptSynonymDvo> getConceptSynonymLanguageConceptList() {
        return conceptSynonymLanguageConceptList;
    }
    
    public void setConceptSynonymLanguageConceptList(ArrayList<ConceptSynonymDvo> list) {
        this.conceptSynonymLanguageConceptList = list;
    }
    
    public ArrayList<ConditionEraDvo> getConditionEraConditionConceptList() {
        return conditionEraConditionConceptList;
    }
    
    public void setConditionEraConditionConceptList(ArrayList<ConditionEraDvo> list) {
        this.conditionEraConditionConceptList = list;
    }
    
    public ArrayList<ConditionOccurrenceDvo> getConditionOccurrenceConditionConceptList() {
        return conditionOccurrenceConditionConceptList;
    }
    
    public void setConditionOccurrenceConditionConceptList(ArrayList<ConditionOccurrenceDvo> list) {
        this.conditionOccurrenceConditionConceptList = list;
    }
    
    public ArrayList<ConditionOccurrenceDvo> getConditionOccurrenceConditionTypeConceptList() {
        return conditionOccurrenceConditionTypeConceptList;
    }
    
    public void setConditionOccurrenceConditionTypeConceptList(ArrayList<ConditionOccurrenceDvo> list) {
        this.conditionOccurrenceConditionTypeConceptList = list;
    }
    
    public ArrayList<ConditionOccurrenceDvo> getConditionOccurrenceConditionStatusConceptList() {
        return conditionOccurrenceConditionStatusConceptList;
    }
    
    public void setConditionOccurrenceConditionStatusConceptList(ArrayList<ConditionOccurrenceDvo> list) {
        this.conditionOccurrenceConditionStatusConceptList = list;
    }
    
    public ArrayList<ConditionOccurrenceDvo> getConditionOccurrenceConditionSourceConceptList() {
        return conditionOccurrenceConditionSourceConceptList;
    }
    
    public void setConditionOccurrenceConditionSourceConceptList(ArrayList<ConditionOccurrenceDvo> list) {
        this.conditionOccurrenceConditionSourceConceptList = list;
    }
    
    public ArrayList<CostDvo> getCostCostTypeConceptList() {
        return costCostTypeConceptList;
    }
    
    public void setCostCostTypeConceptList(ArrayList<CostDvo> list) {
        this.costCostTypeConceptList = list;
    }
    
    public ArrayList<CostDvo> getCostCurrencyConceptList() {
        return costCurrencyConceptList;
    }
    
    public void setCostCurrencyConceptList(ArrayList<CostDvo> list) {
        this.costCurrencyConceptList = list;
    }
    
    public ArrayList<CostDvo> getCostRevenueCodeConceptList() {
        return costRevenueCodeConceptList;
    }
    
    public void setCostRevenueCodeConceptList(ArrayList<CostDvo> list) {
        this.costRevenueCodeConceptList = list;
    }
    
    public ArrayList<CostDvo> getCostDrgConceptList() {
        return costDrgConceptList;
    }
    
    public void setCostDrgConceptList(ArrayList<CostDvo> list) {
        this.costDrgConceptList = list;
    }
    
    public ArrayList<DeathDvo> getDeathDeathTypeConceptList() {
        return deathDeathTypeConceptList;
    }
    
    public void setDeathDeathTypeConceptList(ArrayList<DeathDvo> list) {
        this.deathDeathTypeConceptList = list;
    }
    
    public ArrayList<DeathDvo> getDeathCauseConceptList() {
        return deathCauseConceptList;
    }
    
    public void setDeathCauseConceptList(ArrayList<DeathDvo> list) {
        this.deathCauseConceptList = list;
    }
    
    public ArrayList<DeathDvo> getDeathCauseSourceConceptList() {
        return deathCauseSourceConceptList;
    }
    
    public void setDeathCauseSourceConceptList(ArrayList<DeathDvo> list) {
        this.deathCauseSourceConceptList = list;
    }
    
    public ArrayList<DeviceExposureDvo> getDeviceExposureDeviceConceptList() {
        return deviceExposureDeviceConceptList;
    }
    
    public void setDeviceExposureDeviceConceptList(ArrayList<DeviceExposureDvo> list) {
        this.deviceExposureDeviceConceptList = list;
    }
    
    public ArrayList<DeviceExposureDvo> getDeviceExposureDeviceTypeConceptList() {
        return deviceExposureDeviceTypeConceptList;
    }
    
    public void setDeviceExposureDeviceTypeConceptList(ArrayList<DeviceExposureDvo> list) {
        this.deviceExposureDeviceTypeConceptList = list;
    }
    
    public ArrayList<DeviceExposureDvo> getDeviceExposureDeviceSourceConceptList() {
        return deviceExposureDeviceSourceConceptList;
    }
    
    public void setDeviceExposureDeviceSourceConceptList(ArrayList<DeviceExposureDvo> list) {
        this.deviceExposureDeviceSourceConceptList = list;
    }
    
    public ArrayList<DeviceExposureDvo> getDeviceExposureUnitConceptList() {
        return deviceExposureUnitConceptList;
    }
    
    public void setDeviceExposureUnitConceptList(ArrayList<DeviceExposureDvo> list) {
        this.deviceExposureUnitConceptList = list;
    }
    
    public ArrayList<DeviceExposureDvo> getDeviceExposureUnitSourceConceptList() {
        return deviceExposureUnitSourceConceptList;
    }
    
    public void setDeviceExposureUnitSourceConceptList(ArrayList<DeviceExposureDvo> list) {
        this.deviceExposureUnitSourceConceptList = list;
    }
    
    public ArrayList<DomainDvo> getDomainDomainConceptList() {
        return domainDomainConceptList;
    }
    
    public void setDomainDomainConceptList(ArrayList<DomainDvo> list) {
        this.domainDomainConceptList = list;
    }
    
    public ArrayList<DoseEraDvo> getDoseEraDrugConceptList() {
        return doseEraDrugConceptList;
    }
    
    public void setDoseEraDrugConceptList(ArrayList<DoseEraDvo> list) {
        this.doseEraDrugConceptList = list;
    }
    
    public ArrayList<DoseEraDvo> getDoseEraUnitConceptList() {
        return doseEraUnitConceptList;
    }
    
    public void setDoseEraUnitConceptList(ArrayList<DoseEraDvo> list) {
        this.doseEraUnitConceptList = list;
    }
    
    public ArrayList<DrugEraDvo> getDrugEraDrugConceptList() {
        return drugEraDrugConceptList;
    }
    
    public void setDrugEraDrugConceptList(ArrayList<DrugEraDvo> list) {
        this.drugEraDrugConceptList = list;
    }
    
    public ArrayList<DrugExposureDvo> getDrugExposureDrugConceptList() {
        return drugExposureDrugConceptList;
    }
    
    public void setDrugExposureDrugConceptList(ArrayList<DrugExposureDvo> list) {
        this.drugExposureDrugConceptList = list;
    }
    
    public ArrayList<DrugExposureDvo> getDrugExposureDrugTypeConceptList() {
        return drugExposureDrugTypeConceptList;
    }
    
    public void setDrugExposureDrugTypeConceptList(ArrayList<DrugExposureDvo> list) {
        this.drugExposureDrugTypeConceptList = list;
    }
    
    public ArrayList<DrugExposureDvo> getDrugExposureRouteConceptList() {
        return drugExposureRouteConceptList;
    }
    
    public void setDrugExposureRouteConceptList(ArrayList<DrugExposureDvo> list) {
        this.drugExposureRouteConceptList = list;
    }
    
    public ArrayList<DrugExposureDvo> getDrugExposureDrugSourceConceptList() {
        return drugExposureDrugSourceConceptList;
    }
    
    public void setDrugExposureDrugSourceConceptList(ArrayList<DrugExposureDvo> list) {
        this.drugExposureDrugSourceConceptList = list;
    }
    
    public ArrayList<DrugStrengthDvo> getDrugStrengthDrugConceptList() {
        return drugStrengthDrugConceptList;
    }
    
    public void setDrugStrengthDrugConceptList(ArrayList<DrugStrengthDvo> list) {
        this.drugStrengthDrugConceptList = list;
    }
    
    public ArrayList<DrugStrengthDvo> getDrugStrengthIngredientConceptList() {
        return drugStrengthIngredientConceptList;
    }
    
    public void setDrugStrengthIngredientConceptList(ArrayList<DrugStrengthDvo> list) {
        this.drugStrengthIngredientConceptList = list;
    }
    
    public ArrayList<DrugStrengthDvo> getDrugStrengthAmountUnitConceptList() {
        return drugStrengthAmountUnitConceptList;
    }
    
    public void setDrugStrengthAmountUnitConceptList(ArrayList<DrugStrengthDvo> list) {
        this.drugStrengthAmountUnitConceptList = list;
    }
    
    public ArrayList<DrugStrengthDvo> getDrugStrengthNumeratorUnitConceptList() {
        return drugStrengthNumeratorUnitConceptList;
    }
    
    public void setDrugStrengthNumeratorUnitConceptList(ArrayList<DrugStrengthDvo> list) {
        this.drugStrengthNumeratorUnitConceptList = list;
    }
    
    public ArrayList<DrugStrengthDvo> getDrugStrengthDenominatorUnitConceptList() {
        return drugStrengthDenominatorUnitConceptList;
    }
    
    public void setDrugStrengthDenominatorUnitConceptList(ArrayList<DrugStrengthDvo> list) {
        this.drugStrengthDenominatorUnitConceptList = list;
    }
    
    public ArrayList<EpisodeDvo> getEpisodeEpisodeConceptList() {
        return episodeEpisodeConceptList;
    }
    
    public void setEpisodeEpisodeConceptList(ArrayList<EpisodeDvo> list) {
        this.episodeEpisodeConceptList = list;
    }
    
    public ArrayList<EpisodeDvo> getEpisodeEpisodeObjectConceptList() {
        return episodeEpisodeObjectConceptList;
    }
    
    public void setEpisodeEpisodeObjectConceptList(ArrayList<EpisodeDvo> list) {
        this.episodeEpisodeObjectConceptList = list;
    }
    
    public ArrayList<EpisodeDvo> getEpisodeEpisodeTypeConceptList() {
        return episodeEpisodeTypeConceptList;
    }
    
    public void setEpisodeEpisodeTypeConceptList(ArrayList<EpisodeDvo> list) {
        this.episodeEpisodeTypeConceptList = list;
    }
    
    public ArrayList<EpisodeDvo> getEpisodeEpisodeSourceConceptList() {
        return episodeEpisodeSourceConceptList;
    }
    
    public void setEpisodeEpisodeSourceConceptList(ArrayList<EpisodeDvo> list) {
        this.episodeEpisodeSourceConceptList = list;
    }
    
    public ArrayList<EpisodeEventDvo> getEpisodeEventEpisodeEventFieldConceptList() {
        return episodeEventEpisodeEventFieldConceptList;
    }
    
    public void setEpisodeEventEpisodeEventFieldConceptList(ArrayList<EpisodeEventDvo> list) {
        this.episodeEventEpisodeEventFieldConceptList = list;
    }
    
    public ArrayList<FactRelationshipDvo> getFactRelationshipDomainConceptId1List() {
        return factRelationshipDomainConceptId1List;
    }
    
    public void setFactRelationshipDomainConceptId1List(ArrayList<FactRelationshipDvo> list) {
        this.factRelationshipDomainConceptId1List = list;
    }
    
    public ArrayList<FactRelationshipDvo> getFactRelationshipDomainConceptId2List() {
        return factRelationshipDomainConceptId2List;
    }
    
    public void setFactRelationshipDomainConceptId2List(ArrayList<FactRelationshipDvo> list) {
        this.factRelationshipDomainConceptId2List = list;
    }
    
    public ArrayList<FactRelationshipDvo> getFactRelationshipRelationshipConceptList() {
        return factRelationshipRelationshipConceptList;
    }
    
    public void setFactRelationshipRelationshipConceptList(ArrayList<FactRelationshipDvo> list) {
        this.factRelationshipRelationshipConceptList = list;
    }
    
    public ArrayList<LocationDvo> getLocationCountryConceptList() {
        return locationCountryConceptList;
    }
    
    public void setLocationCountryConceptList(ArrayList<LocationDvo> list) {
        this.locationCountryConceptList = list;
    }
    
    public ArrayList<MeasurementDvo> getMeasurementMeasurementConceptList() {
        return measurementMeasurementConceptList;
    }
    
    public void setMeasurementMeasurementConceptList(ArrayList<MeasurementDvo> list) {
        this.measurementMeasurementConceptList = list;
    }
    
    public ArrayList<MeasurementDvo> getMeasurementMeasurementTypeConceptList() {
        return measurementMeasurementTypeConceptList;
    }
    
    public void setMeasurementMeasurementTypeConceptList(ArrayList<MeasurementDvo> list) {
        this.measurementMeasurementTypeConceptList = list;
    }
    
    public ArrayList<MeasurementDvo> getMeasurementOperatorConceptList() {
        return measurementOperatorConceptList;
    }
    
    public void setMeasurementOperatorConceptList(ArrayList<MeasurementDvo> list) {
        this.measurementOperatorConceptList = list;
    }
    
    public ArrayList<MeasurementDvo> getMeasurementValueAsConceptList() {
        return measurementValueAsConceptList;
    }
    
    public void setMeasurementValueAsConceptList(ArrayList<MeasurementDvo> list) {
        this.measurementValueAsConceptList = list;
    }
    
    public ArrayList<MeasurementDvo> getMeasurementUnitConceptList() {
        return measurementUnitConceptList;
    }
    
    public void setMeasurementUnitConceptList(ArrayList<MeasurementDvo> list) {
        this.measurementUnitConceptList = list;
    }
    
    public ArrayList<MeasurementDvo> getMeasurementMeasurementSourceConceptList() {
        return measurementMeasurementSourceConceptList;
    }
    
    public void setMeasurementMeasurementSourceConceptList(ArrayList<MeasurementDvo> list) {
        this.measurementMeasurementSourceConceptList = list;
    }
    
    public ArrayList<MeasurementDvo> getMeasurementUnitSourceConceptList() {
        return measurementUnitSourceConceptList;
    }
    
    public void setMeasurementUnitSourceConceptList(ArrayList<MeasurementDvo> list) {
        this.measurementUnitSourceConceptList = list;
    }
    
    public ArrayList<MeasurementDvo> getMeasurementMeasEventFieldConceptList() {
        return measurementMeasEventFieldConceptList;
    }
    
    public void setMeasurementMeasEventFieldConceptList(ArrayList<MeasurementDvo> list) {
        this.measurementMeasEventFieldConceptList = list;
    }
    
    public ArrayList<MetadataDvo> getMetadataMetadataConceptList() {
        return metadataMetadataConceptList;
    }
    
    public void setMetadataMetadataConceptList(ArrayList<MetadataDvo> list) {
        this.metadataMetadataConceptList = list;
    }
    
    public ArrayList<MetadataDvo> getMetadataMetadataTypeConceptList() {
        return metadataMetadataTypeConceptList;
    }
    
    public void setMetadataMetadataTypeConceptList(ArrayList<MetadataDvo> list) {
        this.metadataMetadataTypeConceptList = list;
    }
    
    public ArrayList<MetadataDvo> getMetadataValueAsConceptList() {
        return metadataValueAsConceptList;
    }
    
    public void setMetadataValueAsConceptList(ArrayList<MetadataDvo> list) {
        this.metadataValueAsConceptList = list;
    }
    
    public ArrayList<NoteDvo> getNoteNoteTypeConceptList() {
        return noteNoteTypeConceptList;
    }
    
    public void setNoteNoteTypeConceptList(ArrayList<NoteDvo> list) {
        this.noteNoteTypeConceptList = list;
    }
    
    public ArrayList<NoteDvo> getNoteNoteClassConceptList() {
        return noteNoteClassConceptList;
    }
    
    public void setNoteNoteClassConceptList(ArrayList<NoteDvo> list) {
        this.noteNoteClassConceptList = list;
    }
    
    public ArrayList<NoteDvo> getNoteEncodingConceptList() {
        return noteEncodingConceptList;
    }
    
    public void setNoteEncodingConceptList(ArrayList<NoteDvo> list) {
        this.noteEncodingConceptList = list;
    }
    
    public ArrayList<NoteDvo> getNoteLanguageConceptList() {
        return noteLanguageConceptList;
    }
    
    public void setNoteLanguageConceptList(ArrayList<NoteDvo> list) {
        this.noteLanguageConceptList = list;
    }
    
    public ArrayList<NoteDvo> getNoteNoteEventFieldConceptList() {
        return noteNoteEventFieldConceptList;
    }
    
    public void setNoteNoteEventFieldConceptList(ArrayList<NoteDvo> list) {
        this.noteNoteEventFieldConceptList = list;
    }
    
    public ArrayList<NoteNlpDvo> getNoteNlpSectionConceptList() {
        return noteNlpSectionConceptList;
    }
    
    public void setNoteNlpSectionConceptList(ArrayList<NoteNlpDvo> list) {
        this.noteNlpSectionConceptList = list;
    }
    
    public ArrayList<NoteNlpDvo> getNoteNlpNoteNlpConceptList() {
        return noteNlpNoteNlpConceptList;
    }
    
    public void setNoteNlpNoteNlpConceptList(ArrayList<NoteNlpDvo> list) {
        this.noteNlpNoteNlpConceptList = list;
    }
    
    public ArrayList<NoteNlpDvo> getNoteNlpNoteNlpSourceConceptList() {
        return noteNlpNoteNlpSourceConceptList;
    }
    
    public void setNoteNlpNoteNlpSourceConceptList(ArrayList<NoteNlpDvo> list) {
        this.noteNlpNoteNlpSourceConceptList = list;
    }
    
    public ArrayList<ObservationDvo> getObservationObservationConceptList() {
        return observationObservationConceptList;
    }
    
    public void setObservationObservationConceptList(ArrayList<ObservationDvo> list) {
        this.observationObservationConceptList = list;
    }
    
    public ArrayList<ObservationDvo> getObservationObservationTypeConceptList() {
        return observationObservationTypeConceptList;
    }
    
    public void setObservationObservationTypeConceptList(ArrayList<ObservationDvo> list) {
        this.observationObservationTypeConceptList = list;
    }
    
    public ArrayList<ObservationDvo> getObservationValueAsConceptList() {
        return observationValueAsConceptList;
    }
    
    public void setObservationValueAsConceptList(ArrayList<ObservationDvo> list) {
        this.observationValueAsConceptList = list;
    }
    
    public ArrayList<ObservationDvo> getObservationQualifierConceptList() {
        return observationQualifierConceptList;
    }
    
    public void setObservationQualifierConceptList(ArrayList<ObservationDvo> list) {
        this.observationQualifierConceptList = list;
    }
    
    public ArrayList<ObservationDvo> getObservationUnitConceptList() {
        return observationUnitConceptList;
    }
    
    public void setObservationUnitConceptList(ArrayList<ObservationDvo> list) {
        this.observationUnitConceptList = list;
    }
    
    public ArrayList<ObservationDvo> getObservationObservationSourceConceptList() {
        return observationObservationSourceConceptList;
    }
    
    public void setObservationObservationSourceConceptList(ArrayList<ObservationDvo> list) {
        this.observationObservationSourceConceptList = list;
    }
    
    public ArrayList<ObservationDvo> getObservationObsEventFieldConceptList() {
        return observationObsEventFieldConceptList;
    }
    
    public void setObservationObsEventFieldConceptList(ArrayList<ObservationDvo> list) {
        this.observationObsEventFieldConceptList = list;
    }
    
    public ArrayList<ObservationPeriodDvo> getObservationPeriodPeriodTypeConceptList() {
        return observationPeriodPeriodTypeConceptList;
    }
    
    public void setObservationPeriodPeriodTypeConceptList(ArrayList<ObservationPeriodDvo> list) {
        this.observationPeriodPeriodTypeConceptList = list;
    }
    
    public ArrayList<PayerPlanPeriodDvo> getPayerPlanPeriodPayerConceptList() {
        return payerPlanPeriodPayerConceptList;
    }
    
    public void setPayerPlanPeriodPayerConceptList(ArrayList<PayerPlanPeriodDvo> list) {
        this.payerPlanPeriodPayerConceptList = list;
    }
    
    public ArrayList<PayerPlanPeriodDvo> getPayerPlanPeriodPayerSourceConceptList() {
        return payerPlanPeriodPayerSourceConceptList;
    }
    
    public void setPayerPlanPeriodPayerSourceConceptList(ArrayList<PayerPlanPeriodDvo> list) {
        this.payerPlanPeriodPayerSourceConceptList = list;
    }
    
    public ArrayList<PayerPlanPeriodDvo> getPayerPlanPeriodPlanConceptList() {
        return payerPlanPeriodPlanConceptList;
    }
    
    public void setPayerPlanPeriodPlanConceptList(ArrayList<PayerPlanPeriodDvo> list) {
        this.payerPlanPeriodPlanConceptList = list;
    }
    
    public ArrayList<PayerPlanPeriodDvo> getPayerPlanPeriodPlanSourceConceptList() {
        return payerPlanPeriodPlanSourceConceptList;
    }
    
    public void setPayerPlanPeriodPlanSourceConceptList(ArrayList<PayerPlanPeriodDvo> list) {
        this.payerPlanPeriodPlanSourceConceptList = list;
    }
    
    public ArrayList<PayerPlanPeriodDvo> getPayerPlanPeriodSponsorConceptList() {
        return payerPlanPeriodSponsorConceptList;
    }
    
    public void setPayerPlanPeriodSponsorConceptList(ArrayList<PayerPlanPeriodDvo> list) {
        this.payerPlanPeriodSponsorConceptList = list;
    }
    
    public ArrayList<PayerPlanPeriodDvo> getPayerPlanPeriodSponsorSourceConceptList() {
        return payerPlanPeriodSponsorSourceConceptList;
    }
    
    public void setPayerPlanPeriodSponsorSourceConceptList(ArrayList<PayerPlanPeriodDvo> list) {
        this.payerPlanPeriodSponsorSourceConceptList = list;
    }
    
    public ArrayList<PayerPlanPeriodDvo> getPayerPlanPeriodStopReasonConceptList() {
        return payerPlanPeriodStopReasonConceptList;
    }
    
    public void setPayerPlanPeriodStopReasonConceptList(ArrayList<PayerPlanPeriodDvo> list) {
        this.payerPlanPeriodStopReasonConceptList = list;
    }
    
    public ArrayList<PayerPlanPeriodDvo> getPayerPlanPeriodStopReasonSourceConceptList() {
        return payerPlanPeriodStopReasonSourceConceptList;
    }
    
    public void setPayerPlanPeriodStopReasonSourceConceptList(ArrayList<PayerPlanPeriodDvo> list) {
        this.payerPlanPeriodStopReasonSourceConceptList = list;
    }
    
    public ArrayList<PersonDvo> getPersonGenderConceptList() {
        return personGenderConceptList;
    }
    
    public void setPersonGenderConceptList(ArrayList<PersonDvo> list) {
        this.personGenderConceptList = list;
    }
    
    public ArrayList<PersonDvo> getPersonRaceConceptList() {
        return personRaceConceptList;
    }
    
    public void setPersonRaceConceptList(ArrayList<PersonDvo> list) {
        this.personRaceConceptList = list;
    }
    
    public ArrayList<PersonDvo> getPersonEthnicityConceptList() {
        return personEthnicityConceptList;
    }
    
    public void setPersonEthnicityConceptList(ArrayList<PersonDvo> list) {
        this.personEthnicityConceptList = list;
    }
    
    public ArrayList<PersonDvo> getPersonGenderSourceConceptList() {
        return personGenderSourceConceptList;
    }
    
    public void setPersonGenderSourceConceptList(ArrayList<PersonDvo> list) {
        this.personGenderSourceConceptList = list;
    }
    
    public ArrayList<PersonDvo> getPersonRaceSourceConceptList() {
        return personRaceSourceConceptList;
    }
    
    public void setPersonRaceSourceConceptList(ArrayList<PersonDvo> list) {
        this.personRaceSourceConceptList = list;
    }
    
    public ArrayList<PersonDvo> getPersonEthnicitySourceConceptList() {
        return personEthnicitySourceConceptList;
    }
    
    public void setPersonEthnicitySourceConceptList(ArrayList<PersonDvo> list) {
        this.personEthnicitySourceConceptList = list;
    }
    
    public ArrayList<ProcedureOccurrenceDvo> getProcedureOccurrenceProcedureConceptList() {
        return procedureOccurrenceProcedureConceptList;
    }
    
    public void setProcedureOccurrenceProcedureConceptList(ArrayList<ProcedureOccurrenceDvo> list) {
        this.procedureOccurrenceProcedureConceptList = list;
    }
    
    public ArrayList<ProcedureOccurrenceDvo> getProcedureOccurrenceProcedureTypeConceptList() {
        return procedureOccurrenceProcedureTypeConceptList;
    }
    
    public void setProcedureOccurrenceProcedureTypeConceptList(ArrayList<ProcedureOccurrenceDvo> list) {
        this.procedureOccurrenceProcedureTypeConceptList = list;
    }
    
    public ArrayList<ProcedureOccurrenceDvo> getProcedureOccurrenceModifierConceptList() {
        return procedureOccurrenceModifierConceptList;
    }
    
    public void setProcedureOccurrenceModifierConceptList(ArrayList<ProcedureOccurrenceDvo> list) {
        this.procedureOccurrenceModifierConceptList = list;
    }
    
    public ArrayList<ProcedureOccurrenceDvo> getProcedureOccurrenceProcedureSourceConceptList() {
        return procedureOccurrenceProcedureSourceConceptList;
    }
    
    public void setProcedureOccurrenceProcedureSourceConceptList(ArrayList<ProcedureOccurrenceDvo> list) {
        this.procedureOccurrenceProcedureSourceConceptList = list;
    }
    
    public ArrayList<ProviderDvo> getProviderSpecialtyConceptList() {
        return providerSpecialtyConceptList;
    }
    
    public void setProviderSpecialtyConceptList(ArrayList<ProviderDvo> list) {
        this.providerSpecialtyConceptList = list;
    }
    
    public ArrayList<ProviderDvo> getProviderGenderConceptList() {
        return providerGenderConceptList;
    }
    
    public void setProviderGenderConceptList(ArrayList<ProviderDvo> list) {
        this.providerGenderConceptList = list;
    }
    
    public ArrayList<ProviderDvo> getProviderSpecialtySourceConceptList() {
        return providerSpecialtySourceConceptList;
    }
    
    public void setProviderSpecialtySourceConceptList(ArrayList<ProviderDvo> list) {
        this.providerSpecialtySourceConceptList = list;
    }
    
    public ArrayList<ProviderDvo> getProviderGenderSourceConceptList() {
        return providerGenderSourceConceptList;
    }
    
    public void setProviderGenderSourceConceptList(ArrayList<ProviderDvo> list) {
        this.providerGenderSourceConceptList = list;
    }
    
    public ArrayList<RelationshipDvo> getRelationshipRelationshipConceptList() {
        return relationshipRelationshipConceptList;
    }
    
    public void setRelationshipRelationshipConceptList(ArrayList<RelationshipDvo> list) {
        this.relationshipRelationshipConceptList = list;
    }
    
    public ArrayList<SourceToConceptMapDvo> getSourceToConceptMapSourceConceptList() {
        return sourceToConceptMapSourceConceptList;
    }
    
    public void setSourceToConceptMapSourceConceptList(ArrayList<SourceToConceptMapDvo> list) {
        this.sourceToConceptMapSourceConceptList = list;
    }
    
    public ArrayList<SourceToConceptMapDvo> getSourceToConceptMapTargetConceptList() {
        return sourceToConceptMapTargetConceptList;
    }
    
    public void setSourceToConceptMapTargetConceptList(ArrayList<SourceToConceptMapDvo> list) {
        this.sourceToConceptMapTargetConceptList = list;
    }
    
    public ArrayList<SpecimenDvo> getSpecimenSpecimenConceptList() {
        return specimenSpecimenConceptList;
    }
    
    public void setSpecimenSpecimenConceptList(ArrayList<SpecimenDvo> list) {
        this.specimenSpecimenConceptList = list;
    }
    
    public ArrayList<SpecimenDvo> getSpecimenSpecimenTypeConceptList() {
        return specimenSpecimenTypeConceptList;
    }
    
    public void setSpecimenSpecimenTypeConceptList(ArrayList<SpecimenDvo> list) {
        this.specimenSpecimenTypeConceptList = list;
    }
    
    public ArrayList<SpecimenDvo> getSpecimenUnitConceptList() {
        return specimenUnitConceptList;
    }
    
    public void setSpecimenUnitConceptList(ArrayList<SpecimenDvo> list) {
        this.specimenUnitConceptList = list;
    }
    
    public ArrayList<SpecimenDvo> getSpecimenAnatomicSiteConceptList() {
        return specimenAnatomicSiteConceptList;
    }
    
    public void setSpecimenAnatomicSiteConceptList(ArrayList<SpecimenDvo> list) {
        this.specimenAnatomicSiteConceptList = list;
    }
    
    public ArrayList<SpecimenDvo> getSpecimenDiseaseStatusConceptList() {
        return specimenDiseaseStatusConceptList;
    }
    
    public void setSpecimenDiseaseStatusConceptList(ArrayList<SpecimenDvo> list) {
        this.specimenDiseaseStatusConceptList = list;
    }
    
    public ArrayList<VisitDetailDvo> getVisitDetailVisitDetailConceptList() {
        return visitDetailVisitDetailConceptList;
    }
    
    public void setVisitDetailVisitDetailConceptList(ArrayList<VisitDetailDvo> list) {
        this.visitDetailVisitDetailConceptList = list;
    }
    
    public ArrayList<VisitDetailDvo> getVisitDetailVisitDetailTypeConceptList() {
        return visitDetailVisitDetailTypeConceptList;
    }
    
    public void setVisitDetailVisitDetailTypeConceptList(ArrayList<VisitDetailDvo> list) {
        this.visitDetailVisitDetailTypeConceptList = list;
    }
    
    public ArrayList<VisitDetailDvo> getVisitDetailVisitDetailSourceConceptList() {
        return visitDetailVisitDetailSourceConceptList;
    }
    
    public void setVisitDetailVisitDetailSourceConceptList(ArrayList<VisitDetailDvo> list) {
        this.visitDetailVisitDetailSourceConceptList = list;
    }
    
    public ArrayList<VisitDetailDvo> getVisitDetailAdmittedFromConceptList() {
        return visitDetailAdmittedFromConceptList;
    }
    
    public void setVisitDetailAdmittedFromConceptList(ArrayList<VisitDetailDvo> list) {
        this.visitDetailAdmittedFromConceptList = list;
    }
    
    public ArrayList<VisitDetailDvo> getVisitDetailDischargedToConceptList() {
        return visitDetailDischargedToConceptList;
    }
    
    public void setVisitDetailDischargedToConceptList(ArrayList<VisitDetailDvo> list) {
        this.visitDetailDischargedToConceptList = list;
    }
    
    public ArrayList<VisitOccurrenceDvo> getVisitOccurrenceVisitConceptList() {
        return visitOccurrenceVisitConceptList;
    }
    
    public void setVisitOccurrenceVisitConceptList(ArrayList<VisitOccurrenceDvo> list) {
        this.visitOccurrenceVisitConceptList = list;
    }
    
    public ArrayList<VisitOccurrenceDvo> getVisitOccurrenceVisitTypeConceptList() {
        return visitOccurrenceVisitTypeConceptList;
    }
    
    public void setVisitOccurrenceVisitTypeConceptList(ArrayList<VisitOccurrenceDvo> list) {
        this.visitOccurrenceVisitTypeConceptList = list;
    }
    
    public ArrayList<VisitOccurrenceDvo> getVisitOccurrenceVisitSourceConceptList() {
        return visitOccurrenceVisitSourceConceptList;
    }
    
    public void setVisitOccurrenceVisitSourceConceptList(ArrayList<VisitOccurrenceDvo> list) {
        this.visitOccurrenceVisitSourceConceptList = list;
    }
    
    public ArrayList<VisitOccurrenceDvo> getVisitOccurrenceAdmittedFromConceptList() {
        return visitOccurrenceAdmittedFromConceptList;
    }
    
    public void setVisitOccurrenceAdmittedFromConceptList(ArrayList<VisitOccurrenceDvo> list) {
        this.visitOccurrenceAdmittedFromConceptList = list;
    }
    
    public ArrayList<VisitOccurrenceDvo> getVisitOccurrenceDischargedToConceptList() {
        return visitOccurrenceDischargedToConceptList;
    }
    
    public void setVisitOccurrenceDischargedToConceptList(ArrayList<VisitOccurrenceDvo> list) {
        this.visitOccurrenceDischargedToConceptList = list;
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
            getConceptId()  == null ? null: getConceptId() + ""
        };
        return rtn;
    }
}
