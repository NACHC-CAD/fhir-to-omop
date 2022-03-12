use synthea_omop;

select count(*) from person;

use synthea_omop;

select * from VOCABULARY;

select top 10 * from concept;

select top 10 * from person;

select count(*) from concept;

select count(*) from person;

select * from person;

select * from MEASUREMENT;

select * from observation;

select * from vocabulary order by vocabulary_id;

select * from vocabulary where lower(vocabulary_id) = 'none';

select * from concept where concept_id = '756265';

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
	   'https://github.com/NACHC-CAD/fhir-to-omop',
       null, 
       '2021-12-01',
       '2021-12-01',
       '5.4',
       'v5.0 04-FEB-22',
       756265
);

select * from cdm_source;

commit;


