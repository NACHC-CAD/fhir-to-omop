
drop sequence if exists concept_concept_id; 
create sequence concept_concept_id
start with 2000000001
increment by 1
;

drop sequence if exists condition_occurrence_condition_occurrence_id;
create sequence condition_occurrence_condition_occurrence_id
start with 0
increment by 1
;

drop sequence if exists drug_exposure_drug_exposure_id;
create sequence drug_exposure_drug_exposure_id
start with 0
increment by 1
;

drop sequence if exists measurement_measurement_id;
create sequence measurement_measurement_id
start with 0
increment by 1
;

drop sequence if exists observation_observation_id;
create sequence observation_observation_id
start with 0
increment by 1
;

drop sequence if exists person_person_id;
create sequence person_person_id
start with 0
increment by 1
;

drop sequence if exists procedure_occurrence_procedure_occurrence_id;
create sequence procedure_occurrence_procedure_occurrence_id
start with 0
increment by 1
;

drop sequence if exists visit_occurrence_visit_occurrence_id;
create sequence visit_occurrence_visit_occurrence_id
start with 0
increment by 1
;

drop sequence if exists observation_period_observation_period_id;
create sequence observation_period_observation_period_id
start with 0
increment by 1
;


