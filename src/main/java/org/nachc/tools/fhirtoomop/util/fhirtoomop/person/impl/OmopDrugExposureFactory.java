package org.nachc.tools.fhirtoomop.util.fhirtoomop.person.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.dstu3.model.Coding;
import org.nachc.tools.fhirtoomop.util.fhir.parser.medicationrequest.MedicationRequestParser;
import org.nachc.tools.fhirtoomop.util.fhirtoomop.id.FhirToOmopIdGenerator;
import org.nachc.tools.fhirtoomop.util.fhirtoomop.person.OmopPersonEverythingFactory;
import org.nachc.tools.fhirtoomop.util.mapping.impl.FhirToOmopConceptMapper;
import org.nachc.tools.omop.yaorma.dvo.ConceptDvo;
import org.nachc.tools.omop.yaorma.dvo.DrugExposureDvo;

public class OmopDrugExposureFactory {

	//
	// instance variables
	//

	private OmopPersonEverythingFactory omopPersonEverything;

	private Connection conn;

	//
	// constructor
	//

	public OmopDrugExposureFactory(OmopPersonEverythingFactory omopPersonEverything, Connection conn) {
		this.omopPersonEverything = omopPersonEverything;
		this.conn = conn;
	}

	//
	// method to get all of the medication dvos
	//

	public List<DrugExposureDvo> getDrugExposureList() {
		List<DrugExposureDvo> rtn = new ArrayList<DrugExposureDvo>();
		List<MedicationRequestParser> medReqList = omopPersonEverything.getFhirPatientEverything().getMedicationRequestList();
		for (MedicationRequestParser medReq : medReqList) {
			DrugExposureDvo dvo = this.getDrugExposureDvo(medReq);
			rtn.add(dvo);
		}
		return rtn;
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
		Integer omopPatientId = this.omopPersonEverything.getOmopPatientId();
		dvo.setPersonId(omopPatientId);
		// encounter id
		String fhirEncounterId = medReq.getEncounterId();
		Integer omopVisitId = this.omopPersonEverything.getOmopEncounterId(fhirEncounterId);
		dvo.setVisitOccurrenceId(omopVisitId);
		// drug concept id
		Coding medicationCoding = medReq.getMedication();
		ConceptDvo conceptDvo = FhirToOmopConceptMapper.getOmopConceptForFhirCoding(medicationCoding, conn);
		if(conceptDvo != null) {
			dvo.setDrugConceptId(conceptDvo.getConceptId());
		} else {
			dvo.setDrugConceptId(0);
		}
		// drug type
		dvo.setDrugTypeConceptId(0);
		// start date
		dvo.setDrugExposureStartDate(medReq.getStartDate());
		dvo.setDrugExposureEndDate(medReq.getStartDate());
		return dvo;
	}

}
