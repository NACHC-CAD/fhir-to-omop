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

public class FhirToOmopPersonParser {

	public static PersonDvo getPerson(PatientEverythingParser personEverything, Connection conn) {
		PersonDvo dvo = new PersonDvo();
		PatientParser patient = personEverything.getPatient();
		// person_id
		Integer personId = FhirToOmopIdGenerator.getId("person", "person_id");
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

	private static void mapRace(PatientParser patient, PersonDvo dvo, Connection conn) {
		Coding coding = patient.getRace();
		if (coding != null) {
			String code = coding.getCode();
			if (code != null) {
				ConceptDvo race = new RaceMapping(conn).getOmopConceptForFhirCode(code);
				if (race != null) {
					Integer raceId = race.getConceptId();
					dvo.setRaceConceptId(raceId);
					dvo.setRaceSourceValue(CodingParser.getAsPipeDelimited(patient.getRace()));
				}
			}
		}
	}

	private static void mapEthnicity(PatientParser patient, PersonDvo dvo, Connection conn) {
		Coding coding = patient.getEthnicity();
		if (coding != null) {
			String code = coding.getCode();
			if (code != null) {
				ConceptDvo eth = new EthnicityMapping(conn).getOmopConceptForFhirCode(code);
				if (eth != null) {
					Integer ethId = eth.getConceptId();
					dvo.setEthnicityConceptId(ethId);
					dvo.setEthnicitySourceValue(CodingParser.getAsPipeDelimited(patient.getEthnicity()));
				}
			}
		}
	}

	private static void mapGender(PatientParser patient, PersonDvo dvo, Connection conn) {
		AdministrativeGender gender = patient.getGender();
		Integer genderId = GenderMapping.getOmopConceptForFhirCode(gender);
		dvo.setGenderConceptId(genderId);
		dvo.setGenderSourceValue(gender.toCode());
	}

	private static void mapBirthDay(PatientParser patient, PersonDvo dvo, Connection conn) {
		Integer birthYear = patient.getBirthYear();
		dvo.setYearOfBirth(birthYear);
	}

}
