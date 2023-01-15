package org.nachc.tools.fhirtoomop.omop.person.factory.builder.person;

import java.sql.Connection;

import org.hl7.fhir.dstu3.model.Coding;
import org.hl7.fhir.dstu3.model.Enumerations.AdministrativeGender;
import org.nachc.tools.fhirtoomop.fhir.parser.patient.PatientParser;
import org.nachc.tools.fhirtoomop.fhir.patient.FhirPatient;
import org.nachc.tools.fhirtoomop.omop.person.OmopPerson;
import org.nachc.tools.fhirtoomop.omop.util.id.FhirToOmopIdGenerator;
import org.nachc.tools.fhirtoomop.util.mapping.EthnicityMapping;
import org.nachc.tools.fhirtoomop.util.mapping.GenderMapping;
import org.nachc.tools.fhirtoomop.util.mapping.RaceMapping;
import org.nachc.tools.omop.yaorma.dvo.ConceptDvo;
import org.nachc.tools.omop.yaorma.dvo.PersonDvo;

public class OmopPersonBuilder {

	private FhirPatient fhirPatient;

	private OmopPerson omopPerson;

	private Connection conn;

	public OmopPersonBuilder(FhirPatient fhirPatient, OmopPerson omopPerson, Connection conn) {
		this.fhirPatient = fhirPatient;
		this.omopPerson = omopPerson;
		this.conn = conn;
	}

	public void build() {
		PersonDvo dvo = new PersonDvo();
		PatientParser patient = fhirPatient.getPatient();
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
		// other values
		if(dvo.getLocationId() == null) {
			dvo.setLocationId(1);
		}
		if(dvo.getCareSiteId() == null) {
			dvo.setCareSiteId(1);
		}
		if(dvo.getProviderId() == null) {
			dvo.setProviderId(1);
		}
		if(dvo.getRaceSourceValue() == null) {
			dvo.setRaceSourceValue("Not Available");
		}
		// done
		this.omopPerson.setPerson(dvo);
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
					dvo.setRaceSourceConceptId(raceId);
					dvo.setRaceSourceValue(code);
				}
			}
		}
		if (dvo.getRaceConceptId() == null) {
			dvo.setRaceConceptId(0);
			dvo.setRaceSourceConceptId(0);
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
					dvo.setEthnicitySourceConceptId(ethId);
					dvo.setEthnicitySourceValue(code);
				}
			}
		}
		if (dvo.getEthnicityConceptId() == null) {
			dvo.setEthnicityConceptId(0);
		}
	}

	private void mapGender(PatientParser patient, PersonDvo dvo, Connection conn) {
		AdministrativeGender gender = patient.getGender();
		Integer genderId = GenderMapping.getOmopConceptForFhirCode(gender);
		dvo.setGenderConceptId(genderId);
		dvo.setGenderSourceConceptId(genderId);
		dvo.setGenderSourceValue(gender.toCode());
		if (dvo.getGenderConceptId() == null) {
			dvo.setGenderConceptId(0);
		}
	}

	private void mapBirthDay(PatientParser patient, PersonDvo dvo, Connection conn) {
		dvo.setYearOfBirth(patient.getBirthYear());
		dvo.setMonthOfBirth(patient.getBirthMonth());
		dvo.setDayOfBirth(patient.getBirthDay());
		dvo.setBirthDatetime(patient.getBirthDate());
	}

}
