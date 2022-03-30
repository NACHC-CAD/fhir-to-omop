use synthea_micro;

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
