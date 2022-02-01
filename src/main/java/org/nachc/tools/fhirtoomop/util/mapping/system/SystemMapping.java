package org.nachc.tools.fhirtoomop.util.mapping.system;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SystemMapping {

	// TODO: NEED TO MOVE THIS TO PROPERTIES FILE OR DB WHEN WE GET AN ACTUAL MAPPING
	
	public static String getOmopSystemForFhirSystem(String system) {
		if ("http://snomed.info/sct".equals(system)) {
			return "SNOMED";
		} else if("http://www.nlm.nih.gov/research/umls/rxnorm".equals(system)) {
			return "RxNorm";
		} else {
			log.warn("WARNING: UNKNON SYSTEM: " + system);
			return null;
		}
	}

}
