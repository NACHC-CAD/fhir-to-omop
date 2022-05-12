truncate table fhir_to_omop_race;

bulk insert fhir_to_omop_race 
from '<RACE_FILE>'
with (
  FIELDTERMINATOR = '\t',
  FIELDQUOTE = '"',
  ROWTERMINATOR = '0x0d0a',
  FIRSTROW = 2
)
;

truncate table fhir_to_omop_ethnicity;

bulk insert fhir_to_omop_ethnicity 
from '<ETH_FILE>'
with (
  FIELDTERMINATOR = '\t',
  FIELDQUOTE = '"',
  ROWTERMINATOR = '0x0d0a',
  FIRSTROW = 2
)
;

select top 5 * from fhir_to_omop_ethnicity;

select count(*) from fhir_to_omop_ethnicity;

select top 5 * from fhir_to_omop_race;

select count(*) from fhir_to_omop_race;



