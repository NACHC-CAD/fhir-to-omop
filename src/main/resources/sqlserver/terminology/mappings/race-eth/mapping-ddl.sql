drop table if exists fhir_to_omop_race;

create table fhir_to_omop_race (
	omop_code integer,
	omop_display varchar(255),
	depth integer,
	parent_code varchar(50),
	parent_display varchar(255),
	code varchar(50),
	display varchar(255),
	is_abstract bit,
	definition varchar(1024)
);

drop table if exists fhir_to_omop_ethnicity;

create table fhir_to_omop_ethnicity (
	omop_code integer,
	omop_display varchar(255),
	depth integer,
	parent_code varchar(50),
	parent_display varchar(255),
	code varchar(50),
	display varchar(255),
	is_abstract bit,
	definition varchar(1024)
);



