package org.nachc.tools.fhirtoomop.omop.person.factory.builder.drugexposure;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.dstu3.model.Coding;
import org.nachc.tools.fhirtoomop.fhir.parser.medicationrequest.MedicationRequestParser;
import org.nachc.tools.fhirtoomop.fhir.patient.FhirPatient;
import org.nachc.tools.fhirtoomop.omop.person.OmopPerson;
import org.nachc.tools.fhirtoomop.omop.util.id.FhirToOmopIdGenerator;
import org.nachc.tools.fhirtoomop.util.mapping.impl.FhirToOmopConceptMapper;
import org.nachc.tools.omop.yaorma.dvo.ConceptDvo;
import org.nachc.tools.omop.yaorma.dvo.DrugExposureDvo;

public class OmopDrugExposureBuilder {

	//
	// instance variables
	//

	private OmopPerson omopPerson;

	private FhirPatient fhirPatient;

	private Connection conn;

	//
	// constructor
	//

	public OmopDrugExposureBuilder(FhirPatient fhirPatient, OmopPerson omopPersonEverything, Connection conn) {
		this.omopPerson = omopPersonEverything;
		this.fhirPatient = fhirPatient;
		this.conn = conn;
	}

	public void build() {
		buildDrugExposureList();
	}
	
	//
	// method to get all of the medication dvos
	//

	public void buildDrugExposureList() {
		List<DrugExposureDvo> rtn = new ArrayList<DrugExposureDvo>();
		List<MedicationRequestParser> medReqList = fhirPatient.getMedicationRequestList();
		for (MedicationRequestParser medReq : medReqList) {
			DrugExposureDvo dvo = this.getDrugExposureDvo(medReq);
			rtn.add(dvo);
		}
		this.omopPerson.setDrugExposureList(rtn);
	}

	//
	// method to generate the dvo
	//

	private DrugExposureDvo getDrugExposureDvo(MedicationRequestParser medReq) {
		DrugExposureDvo dvo = new DrugExposureDvo();
		// primary key
		Integer drugExposureId = FhirToOmopIdGenerator.getId("drug_exposure", "drug_exposure_id", conn);
		dvo.setDrugExposureId(drugExposureId);
		// patient id
		Integer omopPatientId = this.omopPerson.getPerson().getPersonId();
		dvo.setPersonId(omopPatientId);
		// encounter id
		String fhirEncounterId = medReq.getEncounterId();
		Integer omopVisitId = this.omopPerson.getOmopEncounterId(fhirEncounterId);
		dvo.setVisitOccurrenceId(omopVisitId);
		// drug concept id
		Coding medicationCoding = medReq.getMedication();
		ConceptDvo conceptDvo = FhirToOmopConceptMapper.getOmopConceptForFhirCoding(medicationCoding, conn);
		if (conceptDvo != null) {
			dvo.setDrugConceptId(conceptDvo.getConceptId());
		} else {
			dvo.setDrugConceptId(0);
		}
		// drug type
		dvo.setDrugTypeConceptId(0);
		// start date
		dvo.setDrugExposureStartDate(medReq.getStartDate());
		dvo.setDrugExposureEndDate(medReq.getStartDate());
		if (medReq.getStartDate() == null) {
			dvo.setDrugExposureStartDate(medReq.getMedicationRequest().getAuthoredOn());
			dvo.setDrugExposureEndDate(medReq.getMedicationRequest().getAuthoredOn());
		}
		return dvo;
	}

}
