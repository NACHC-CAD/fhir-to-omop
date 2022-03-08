use ohdsi;

select * from cdm_source;


insert into cdm_source (
	cdm_source_name, 
	cdm_source_abbreviation,
	cdm_holder,
	source_description,
	source_documentation_reference,
	cdm_etl_reference,
	source_release_date,
	cdm_release_date,
	cdm_version,
	vocabulary_version,
	cdm_version_concept_id
) values (
	'NACHC Test',
	'NACHC-TEST',
	'johngresh@curlewconsulting.com',
	'Test database',
	null, 
	null, 
	'2021-12-01',
	'2021-12-01',
	'5.4',
	'v5.0 04-FEB-22',

);


select * from concept
where lower(concept_name) like '%vocab%version%'
order by concept_name
;

select * from concept
where lower(concept_name) like '%cdm_version%'
order by concept_name
;



