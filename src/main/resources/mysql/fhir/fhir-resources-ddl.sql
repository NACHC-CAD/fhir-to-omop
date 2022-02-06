use synthea_omop;

drop table if exists fhir_resource;

create table fhir_resource (
	patient_id varchar(255),
    resource_type varchar(1024),
    resource_name varchar(128)
);

