package org.nachc.tools.fhirtoomop.util.fhirtoomop.person.impl;

import java.sql.Connection;

import org.hl7.fhir.dstu3.model.Coding;
import org.hl7.fhir.dstu3.model.Enumerations.AdministrativeGender;
import org.nachc.tools.fhirtoomop.util.fhir.parser.coding.CodingParser;
import org.nachc.tools.fhirtoomop.util.fhir.parser.patient.PatientParser;
import org.nachc.tools.fhirtoomop.util.fhir.parser.patienteverything.PatientEverythingParser;
import org.nachc.tools.fhirtoomop.util.fhirtoomop.id.FhirToOmopIdGenerator;
import org.nachc.tools.fhirtoomop.util.mapping.EthnicityMapping;
import org.nachc.tools.fhirtoomop.util.mapping.GenderMapping;
import org.nachc.tools.fhirtoomop.util.mapping.RaceMapping;
import org.nachc.tools.omop.yaorma.dvo.ConceptDvo;
import org.nachc.tools.omop.yaorma.dvo.PersonDvo;

public class OmopPersonFactory {

	private PatientEverythingParser patientEverything;
	
	private PersonDvo personDvo;
	
	private Connection conn;

	public OmopPersonFactory(PatientEverythingParser personEverything, Connection conn) {
		this.patientEverything = personEverything;
		this.conn = conn;
		this.personDvo = initPerson();
	}

	public PersonDvo getPerson() {
		return this.personDvo;
	}
	
	
	public PersonDvo initPerson() {
		PersonDvo dvo = new PersonDvo();
		PatientParser patient = patientEverything.getPatient();
		// person_id
		Integer personId = FhirToOmopIdGenerator.getId("person", "person_id", this.conn);
		dvo.setPersonId(personId);
		// person_source_value
		dvo.setPersonSourceValue(patient.getId());
		// mappings
		mapRace(patient, dvo, conn);
		mapEthnicity(patient, dvo, conn);
		mapGender(patient, dvo, conn);
		mapBirthDay(patient, dvo, conn);
		// done
		return dvo;
	}

	private void mapRace(PatientParser patient, PersonDvo dvo, Connection conn) {
		Coding coding = patient.getRace();
		if (coding != null) {
			String code = coding.getCode();
			if (code != null) {
				ConceptDvo race = new RaceMapping(conn).getOmopConceptForFhirCode(code);
				if (race != null) {
					Integer raceId = race.getConceptId();
					dvo.setRaceConceptId(raceId);
					dvo.setRaceSourceValue(code);
				}
			}
		}
	}

	private void mapEthnicity(PatientParser patient, PersonDvo dvo, Connection conn) {
		Coding coding = patient.getEthnicity();
		if (coding != null) {
			String code = coding.getCode();
			if (code != null) {
				ConceptDvo eth = new EthnicityMapping(conn).getOmopConceptForFhirCode(code);
				if (eth != null) {
					Integer ethId = eth.getConceptId();
					dvo.setEthnicityConceptId(ethId);
					dvo.setEthnicitySourceValue(code);
				}
			}
		}
	}

	private void mapGender(PatientParser patient, PersonDvo dvo, Connection conn) {
		AdministrativeGender gender = patient.getGender();
		Integer genderId = GenderMapping.getOmopConceptForFhirCode(gender);
		dvo.setGenderConceptId(genderId);
		dvo.setGenderSourceValue(gender.toCode());
	}

	private void mapBirthDay(PatientParser patient, PersonDvo dvo, Connection conn) {
		Integer birthYear = patient.getBirthYear();
		dvo.setYearOfBirth(birthYear);
	}

}
