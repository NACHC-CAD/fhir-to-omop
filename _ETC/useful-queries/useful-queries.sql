use synthea_dev;
use synthea_micro;

select count(*) from concept;


select distinct
	p.person_id,
	p.person_source_value,
	con.*
from 
	procedure_occurrence po
	join person p on p.person_id = po.person_id
	left outer join concept con on con.concept_id = po.procedure_concept_id
where 1=1
--	and domain_id != 'Procedure'
order by domain_id
;


-- STUFF FOR MEASUREMENT DQD

select count(*) from person;

select * from person;

select * from concept where concept_id = 3020630;

select * from measurement where measurement_concept_id = 3020630;

-- concepts failing protein in plasma/blood [g/dL]
select	
	p.person_id,
	p.person_source_value,
	c.concept_name,
	m.measurement_id,
	m.measurement_concept_id,
	m.value_as_number,
	uc.concept_name as units
from
	measurement m
	join person p on m.person_id = p.person_id
	join concept c on m.measurement_concept_id = c.concept_id 
	join concept uc on m.unit_concept_id = uc.concept_id
where 1=1
	and m.measurement_concept_id = 3020630
	and value_as_number > 15
order by 
	p.person_source_value,
	m.value_as_number desc
;


select * from concept where concept_id = 8713;

-- measurement type

select * from measurement;

select distinct 
--	p.person_id,
--	p.person_source_value,
	c.concept_name,
--	m.measurement_id,
	m.measurement_concept_id,
--	m.value_as_number,
	c.concept_name as type,
	c.domain_id,
	c.concept_class_id,
	c.vocabulary_id
from
	measurement m
	join person p on m.person_id = p.person_id
	join concept c on m.measurement_concept_id = c.concept_id 
	join concept tc on m.measurement_type_concept_id = tc.concept_id
where 1=1
	and tc.concept_id = 0
order by 
-- 	p.person_source_value,
-- 	m.value_as_number desc
	c.domain_id,
	c.concept_class_id,
	c.vocabulary_id,
	c.concept_name
;

select distinct 
	c.concept_name,
	m.measurement_concept_id,
	c.concept_name as type,
	c.domain_id,
	c.concept_class_id,
	c.vocabulary_id
from
	measurement m
	join person p on m.person_id = p.person_id
	join concept c on m.measurement_concept_id = c.concept_id 
	join concept tc on m.measurement_type_concept_id = tc.concept_id
where 1=1
	and tc.concept_id = 0
order by 
	c.domain_id,
	c.concept_class_id,
	c.vocabulary_id,
	c.concept_name
;



select * from concept where concept_id = 3004249;

select
	measurement_type_concept_id,
	count(*)
from
	measurement
group by measurement_type_concept_id
order by measurement_type_concept_id
;

select * from concept where concept_id in (4024958,3004249);

-- measurment unit id


select * from measurement where unit_concept_id = 0;




select distinct 
	c.concept_name,
	m.measurement_concept_id,
	m.value_as_number,
	c.concept_name as type,
	c.domain_id,
	c.concept_class_id,
	c.vocabulary_id
from
	measurement m
	join person p on m.person_id = p.person_id
	join concept c on m.measurement_concept_id = c.concept_id 
	join concept tc on m.measurement_type_concept_id = tc.concept_id
where 1=1
	and m.unit_concept_id = 0
order by 
	c.domain_id,
	c.concept_class_id,
	c.vocabulary_id,
	c.concept_name
;




select distinct 
	unit_concept_id
from 
	measurement
;

select distinct 
	m.unit_concept_id,
	c.*
from 
	measurement m
	join concept c on c.concept_id = m.unit_concept_id;
;

select * from concept 
where 1=1
	and vocabulary_id = 'UCUM'
--	and domain_id = 'Unit'
	and lower(concept_name) like '%score%'
order by concept_name
;


select * from concept where lower(concept_name) like '%score%'

select * from measurement where unit_concept_id = 0;


select distinct 
	c.concept_name,
	m.measurement_concept_id,
	m.value_as_number,
	m.unit_concept_id,
	c.concept_name as type,
	c.domain_id,
	c.concept_class_id,
	c.vocabulary_id
from
	measurement m
	join person p on m.person_id = p.person_id
	join concept c on m.measurement_concept_id = c.concept_id 
	join concept tc on m.measurement_type_concept_id = tc.concept_id
where 1=1
	and m.unit_concept_id = 0
order by 
	c.domain_id,
	c.concept_class_id,
	c.vocabulary_id,
	c.concept_name
;


select distinct 
	c.concept_name,
	m.measurement_concept_id,
	max(m.value_as_number),
	m.unit_concept_id,
	c.concept_name as type,
	uc.concept_name as units,
	c.domain_id,
	c.concept_class_id,
	c.vocabulary_id
from
	measurement m
	join person p on m.person_id = p.person_id
	join concept c on m.measurement_concept_id = c.concept_id 
	join concept tc on m.measurement_type_concept_id = tc.concept_id
	join concept uc on m.unit_concept_id = uc.concept_id
where 1=1
group by c.concept_name,2,4,5,6,7,8,9
order by 
	c.domain_id,
	c.concept_class_id,
	c.vocabulary_id,
	c.concept_name
;

-- concepts failing protein in plasma/blood [g/dL]
select	
	p.person_id,
	p.person_source_value,
	c.concept_name,
	m.measurement_id,
	m.measurement_concept_id,
	m.value_as_number,
	uc.concept_name as units
from
	measurement m
	join person p on m.person_id = p.person_id
	join concept c on m.measurement_concept_id = c.concept_id 
	join concept uc on m.unit_concept_id = uc.concept_id
where 1=1
--	and m.measurement_concept_id = 3001802
	and lower(c.concept_name) like '%pressure%'
order by 
	p.person_source_value,
	m.value_as_number desc
;

select 
	m.measurement_id,
	m.measurement_concept_id,
	m.unit_concept_id,
--	c.concept_name,
	uc.*
from
	measurement m
--	join concept c on m.measurement_concept_id = c.concept_id
	join concept uc on m.unit_concept_id = uc.concept_id
where 1=1
	and m.measurement_id = 1901
;




commit;

select * from concept where concept_id = 0;

select * from concept where lower(concept_name) like '%foo%'


go
begin transaction;

commit transaction;

select count(*) from person;

select * from concept where concept_id > 2000000000;



update concept set concept_name = concept_name where concept_id in (select max(concept_id) from concept);

select max(person_id) from person;


create sequence concept_concept_id
start with 2000000000
increment by 1
;

select next value for concept_concept_id as foo;


select distinct
--	m.measurement_concept_id,
	c.concept_name as measurement_name,
--	m.unit_concept_id,
--	c.concept_name,
	uc.concept_name as units_concept_name,
	uc.*
from
	measurement m
	join concept c on m.measurement_concept_id = c.concept_id
	join person p on m.person_id = p.person_id
	join concept uc on m.unit_concept_id = uc.concept_id
where 1=1
	and uc.concept_id > 2000000000
order by concept_name
;


delete from concept where concept_id > 2000000000;

select * from concept where concept_id >= 2000000000;

select count(*) from person;

select concept_id, count(*) cnt from concept
where concept_id > 2000000000
group by concept_id
order by cnt desc;

select vocabulary_id, concept_code, count(*) cnt from concept where concept_id > 2000000000
group by vocabulary_id, concept_code
order by cnt desc;

select distinct 
	p.person_source_value,
	mc.concept_name as measurement,
	uc.concept_name as units
from
	measurement m
	join person p on m.person_id = p.person_id 
	join concept mc on mc.concept_id = m.measurement_concept_id
	join concept uc on uc.concept_id = m.unit_concept_id
where
	uc.concept_id > 2000000000
order by 
	p.person_source_value
;



		SELECT 'MEASUREMENT.UNIT_CONCEPT_ID' AS violating_field, cdmTable.* 
		  FROM synthea_micro.dbo.MEASUREMENT cdmTable
		  
		  join synthea_micro.dbo.concept co ON cdmTable.UNIT_CONCEPT_ID = co.concept_id
		  WHERE co.concept_id != 0 AND (co.standard_concept != 'S' OR co.invalid_reason IS NOT NULL ) 

select * from concept where concept_id = 8773;


		SELECT 'MEASUREMENT.MEASUREMENT_CONCEPT_ID' AS violating_field, cdmTable.* 
		FROM synthea_micro.dbo.MEASUREMENT cdmTable
		
		LEFT JOIN synthea_micro.dbo.CONCEPT fkTable
		ON cdmTable.MEASUREMENT_CONCEPT_ID = fkTable.CONCEPT_ID
		WHERE fkTable.CONCEPT_ID IS NULL AND cdmTable.MEASUREMENT_CONCEPT_ID IS NOT NULL 

		SELECT 'MEASUREMENT.UNIT_CONCEPT_ID' AS violating_field, cdmTable.* 
		FROM synthea_micro.dbo.MEASUREMENT cdmTable
		
		LEFT JOIN synthea_micro.dbo.CONCEPT fkTable
		ON cdmTable.UNIT_CONCEPT_ID = fkTable.CONCEPT_ID
		WHERE fkTable.CONCEPT_ID IS NULL AND cdmTable.UNIT_CONCEPT_ID IS NOT NULL 


