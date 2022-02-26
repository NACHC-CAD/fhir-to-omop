alter table fhir_resource add index fhir_resource_name_pat (
    resource_name,
	patient_id
);

alter table fhir_resource add index fhir_resource_pat_name (
	patient_id,
    resource_name
);

