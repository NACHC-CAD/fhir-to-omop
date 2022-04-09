use synthea_dev;

use synthea_micro;

select count(*) from person;

select
	v.visit_concept_id,
	c.concept_name,
	c.standard_concept,
	c.domain_id,
	count(*) as "count"
from
	visit_occurrence v
	join concept c on c.concept_id = v.visit_concept_id 
group by
	v.visit_concept_id,
	c.concept_name,
	c.standard_concept,
	c.domain_id
order by count desc
;


select * from concept where concept_id = 32035;


		SELECT 'VISIT_OCCURRENCE.ADMITTING_SOURCE_CONCEPT_ID' AS violating_field, cdmTable.* 
		FROM synthea_micro.dbo.VISIT_OCCURRENCE cdmTable
		
		WHERE cdmTable.ADMITTING_SOURCE_CONCEPT_ID IS NULL;

exec sp_columns visit_occurrence;

select * from cdm_source;

select * from concept where concept_id = 756265;

select
	v.admitted_from_concept_id,
	count(*)
from
	visit_occurrence v
group by v.admitted_from_concept_id
;

update visit_occurrence set admitted_from_concept_id = 0 where admitted_from_concept_id is null;

select * from care_site;




	SELECT 'VISIT_OCCURRENCE.VISIT_CONCEPT_ID' AS violating_field, cdmTable.* 
		  FROM synthea_micro.dbo.VISIT_OCCURRENCE cdmTable
		  LEFT JOIN synthea_micro.dbo.concept co
		    ON cdmTable.VISIT_CONCEPT_ID = co.concept_id
		  
		 WHERE co.concept_id != 0 AND co.domain_id NOT IN ('Visit')
;

select
	v.visit_concept_id,
	c.concept_name,
	c.standard_concept,
	c.domain_id,
	count(*) as "count"
from
	visit_occurrence v
	join concept c on c.concept_id = v.visit_concept_id 
group by
	v.visit_concept_id,
	c.concept_name,
	c.standard_concept,
	c.domain_id
order by count desc
;


select 
	*
from
	concept
where
	lower(concept_name) like '%encounter for check up%'
order by concept_name
;


select * from concept where concept_id = 32271;

select * from concept where concept_id = 32693;

select * from provider;

truncate table provider;
insert into provider (
	provider_id,
	provider_name
) values (
	1,
	'Not Available'
);

select top 10 * from visit_occurrence;



drop table if exists prev_visit_temp;
select 
	person_id,
	visit_start_date, 
	visit_occurrence_id,
	lag(visit_occurrence_id, 1) over (
		partition by person_id
		order by visit_start_date
	) as preceding_visit_occurrence_id
into prev_visit_temp
from
	visit_occurrence
;

drop index if exists prev_visit_temp.prev_visit_temp_idx;
create index prev_visit_temp_idx on prev_visit_temp(visit_occurrence_id);

update 
	visit_occurrence
set 
	preceding_visit_occurrence_id = t.preceding_visit_occurrence_id
from 
	visit_occurrence v
	join prev_visit_temp t on t.visit_occurrence_id = v.visit_occurrence_id
;

select count(*) from person;

update visit_occurrence set preceding_visit_occurrence_id = visit_occurrence_id where preceding_visit_occurrence_id is null;

		SELECT 'DRUG_EXPOSURE.DRUG_CONCEPT_ID' AS violating_field, cdmTable.* 
		  FROM synthea_micro.dbo.DRUG_EXPOSURE cdmTable
		  
		  join synthea_micro.dbo.concept co ON cdmTable.DRUG_CONCEPT_ID = co.concept_id
		  WHERE co.concept_id != 0 AND (co.standard_concept != 'S' OR co.invalid_reason IS NOT NULL ) 


select distinct
	c.*
from
	drug_exposure d
	join concept c on d.drug_concept_id = c.concept_id 
where
	c.standard_concept != 'S'
;

select
	standard_concept,
	count(*) cnt
from
	concept 
group by standard_concept
order by standard_concept
;


select * from concept where concept_id = 38000177;

select count(*) from condition_occurrence;

select top 100 * from VISIT_OCCURRENCE;

select count(*) from person;



		SELECT 'OBSERVATION.UNIT_CONCEPT_ID' AS violating_field, cdmTable.* 
		FROM synthea_micro.dbo.OBSERVATION cdmTable
		
		WHERE cdmTable.UNIT_CONCEPT_ID IS NULL

select distinct
	o.observation_concept_id,
	c.*
from
	observation o
	join concept c on o.observation_concept_id = c.concept_id 
where
	unit_concept_id is null


		SELECT 'OBSERVATION.UNIT_CONCEPT_ID' AS violating_field, cdmTable.* 
		  FROM synthea_micro.dbo.OBSERVATION cdmTable
		  LEFT JOIN synthea_micro.dbo.concept co
		    ON cdmTable.UNIT_CONCEPT_ID = co.concept_id
		  
		 WHERE co.concept_id != 0 AND co.domain_id NOT IN ('Unit')



		SELECT 'OBSERVATION.OBSERVATION_CONCEPT_ID' AS violating_field, cdmTable.* 
		  FROM synthea_micro.dbo.OBSERVATION cdmTable
		  
		  join synthea_micro.dbo.concept co ON cdmTable.OBSERVATION_CONCEPT_ID = co.concept_id
		  WHERE co.concept_id != 0 AND (co.standard_concept != 'S' OR co.invalid_reason IS NOT NULL ) 




select * from concept where concept_id = 40623280;

select distinct o.observation_concept_id, c.*
		FROM synthea_micro.dbo.OBSERVATION o
		join concept c on c.concept_id = o.observation_concept_id
		WHERE o.OBSERVATION_SOURCE_VALUE IS NULL





select * from concept where lower(concept_name) like 'procedure_occurrence' 
and domain_id = 'Metadata'
union all
select * from concept where lower(concept_name) like 'observation' 
and domain_id = 'Metadata'
order by domain_id;


select distinct domain_id from concept
order by domain_id;

select * from location;

truncate table location;

insert into location (
	location_id,
	address_1,
	city,
	state,
	zip,
	county,
	location_source_value,
	country_concept_id,
	country_source_value
) values (
	1,
	'Location Unknow',
	'Unknown City',
	'??',
	'00000',
	'Unknown County',
	'Unknown',
	42046186,
	'USA'
)

select * from concept where lower(concept_name) like 'united states'

select distinct concept_class_id from concept order by concept_class_id;


select * from provider;


SELECT 'PERSON.RACE_CONCEPT_ID' AS violating_field, cdmTable.* 
		  FROM synthea_micro.dbo.PERSON cdmTable
		  
		  join synthea_micro.dbo.concept co ON cdmTable.RACE_CONCEPT_ID = co.concept_id
		  WHERE co.concept_id != 0 AND (co.standard_concept != 'S' OR co.invalid_reason IS NOT NULL ) 



select * from concept where concept_id = 8522;

select * from visit_detail;



