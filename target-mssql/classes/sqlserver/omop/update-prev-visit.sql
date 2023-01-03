
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

update visit_occurrence set preceding_visit_occurrence_id = visit_occurrence_id where preceding_visit_occurrence_id is null;

