package org.nachc.tools.fhirtoomop.omop.util.constants;

public class OmopConceptConstants {

	// observation stuff
	
	public static int getObsIsFromPhysicalExaminationConceptId() {
		return 44818701;
	}
	
	public static int getObsIsLabResultMeasurementConceptId() {
		return 44818702;
	}

	public static int getObsIsFromEhrEncounterRecord() {
		return 32827;
	}
	
	public static int getIsScalarMeasurementUnitsConceptId() {
		return 44777566;
	}
	
	public static Integer getObservationTableConceptId() {
		return 1147304;
	}
	
	// visit stuff
	
	public static int getVisitIsOtherType() {
		return 32271;
	}
	
	public static int getVisitTypeIsFromEmr() {
		return 32035;
	}
	
	public static int getDefaultVisitAdmittedFrom() {
		return 32693;
	}
	
	public static int getDefaultDischargedTo() {
		return 32693;
	}
	
	// rx stuff
	
	public static int getDefaultRxType() {
		return 38000177;
	}

}
