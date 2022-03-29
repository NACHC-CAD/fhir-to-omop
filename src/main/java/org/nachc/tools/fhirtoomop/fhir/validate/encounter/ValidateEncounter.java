package org.nachc.tools.fhirtoomop.fhir.validate.encounter;

import java.util.ArrayList;
import java.util.List;

import org.nachc.tools.fhirtoomop.fhir.parser.encounter.EncounterParser;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ValidateEncounter {

	private EncounterParser enc;

	private List<String> err = new ArrayList<String>();

	public ValidateEncounter(EncounterParser encounterParser) {
		this.enc = encounterParser;
	}

	public List<String> getErr() {
		return this.err;
	}

	public boolean isValid() {
		return err.size() == 0;
	}

	public ValidateEncounter validate() {
		if(enc.getEncounterId() == null) {
			err.add("Encounter: id is null");
		}
		if(enc.getStartDate() == null) {
			err.add("Encounter: start date is null");
		}
		if(enc.getEndDate() == null) {
			err.add("Encounter: end date is null");
		}
		log.info("Encounter: (id)        " + enc.getEncounterId());
		log.info("Encounter: (startDate) " + enc.getStartDate());
		log.info("Encounter: (endDate)   " + enc.getEndDate());
		return this;
	}
	
}
