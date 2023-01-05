drop table if exists fhir_resource;

create table fhir_resource (
	patient_id varchar(80),
    resource_type varchar(1028),
    resource_name varchar(156)
);



