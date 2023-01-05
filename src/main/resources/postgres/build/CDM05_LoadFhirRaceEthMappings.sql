truncate table fhir_to_omop_race;

copy fhir_to_omop_race 
from '<RACE_FILE>'
  DELIMITER E'\t'
  QUOTE E'\b' 
  ESCAPE '\' 
  CSV HEADER
;

truncate table fhir_to_omop_ethnicity;

copy fhir_to_omop_ethnicity 
from '<ETH_FILE>'
  DELIMITER E'\t'
  QUOTE E'\b' 
  ESCAPE '\' 
  CSV HEADER
;

select * from fhir_to_omop_ethnicity limit 10;

select count(*) from fhir_to_omop_ethnicity;

select * from fhir_to_omop_race limit 10;

select count(*) from fhir_to_omop_race;



