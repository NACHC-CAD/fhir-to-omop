drop table if exists @cdmDatabaseSchema.fhir_to_omop_race;

create table @cdmDatabaseSchema.fhir_to_omop_race (
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

drop table if exists @cdmDatabaseSchema.fhir_to_omop_ethnicity;

create table @cdmDatabaseSchema.fhir_to_omop_ethnicity (
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



