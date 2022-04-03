use synthea_dev;

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
--	p.person_id,
--	p.person_source_value,
	c.concept_name,
--	m.measurement_id,
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
-- 	p.person_source_value,
-- 	m.value_as_number desc
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


