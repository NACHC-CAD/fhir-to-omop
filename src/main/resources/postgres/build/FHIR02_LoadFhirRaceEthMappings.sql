truncate table <ohdsiDbName>.fhir_to_omop_race;

copy <ohdsiDbName>.fhir_to_omop_race 
from '<RACE_FILE>'
  DELIMITER E'\t'
  QUOTE E'\b' 
  ESCAPE '\' 
  CSV HEADER
;

truncate table <ohdsiDbName>.fhir_to_omop_ethnicity;

copy <ohdsiDbName>.fhir_to_omop_ethnicity 
from '<ETH_FILE>'
  DELIMITER E'\t'
  QUOTE E'\b' 
  ESCAPE '\' 
  CSV HEADER
;

select * from <ohdsiDbName>.fhir_to_omop_ethnicity limit 10;

select count(*) from <ohdsiDbName>.fhir_to_omop_ethnicity;

select * from <ohdsiDbName>.fhir_to_omop_race limit 10;

select count(*) from <ohdsiDbName>.fhir_to_omop_race;



