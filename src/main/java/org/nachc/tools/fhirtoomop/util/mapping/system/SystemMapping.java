package org.nachc.tools.fhirtoomop.util.mapping.system;

import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SystemMapping {

	// TODO: NEED TO MOVE THIS TO PROPERTIES FILE OR DB WHEN WE GET AN ACTUAL MAPPING
	
	public static String getOmopSystemForFhirSystem(String system) {
		if ("http://snomed.info/sct".equals(system)) {
			return "SNOMED";
		} else if("http://www.nlm.nih.gov/research/umls/rxnorm".equals(system)) {
			return "RxNorm";
		} else if("http://loinc.org".equals(system)) {
			return "LOINC";
		} else if("http://unitsofmeasure.org".equals(system) || "http://unitsofmeasure.org/".equals(system)) {
			return "UCUM";
		} else {
			log.warn("WARNING: UNKNON SYSTEM: " + system);
			if(system == null) {
				system = "<NULL>";
			}
			if(system.length() > 20) {
				system = system.substring(20);
			}
			return system;
		}
	}
	
	public static List<String> getVocabularyIdList() {
		ArrayList<String> rtn = new ArrayList<String>();
		rtn.add("SNOMED");
		rtn.add("RxNorm");
		rtn.add("LOINC");
		rtn.add("UCUM");
		return rtn;
	}

}
