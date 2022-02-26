truncate table fhir_to_omop_race;

bulk insert fhir_to_omop_race 
from 'C:\\test\\fhir-to-omop\\RaceAndEthnicityCDC-OMOP-MAPPING-Race.txt'
with (
  FIELDTERMINATOR = '\t',
  FIELDQUOTE = '"',
  ROWTERMINATOR = '0x0d0a',
  FIRSTROW = 2
)
;

truncate table fhir_to_omop_ethnicity;

bulk insert fhir_to_omop_ethnicity 
from 'C:\\test\\fhir-to-omop\\RaceAndEthnicityCDC-OMOP-MAPPING-Eth.txt'
with (
  FIELDTERMINATOR = '\t',
  FIELDQUOTE = '"',
  ROWTERMINATOR = '0x0d0a',
  FIRSTROW = 2
)
;

select * from fhir_to_omop_ethnicity;

select count(*) from fhir_to_omop_ethnicity;

select * from fhir_to_omop_race;

select count(*) from fhir_to_omop_race;



