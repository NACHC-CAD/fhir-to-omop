use synthea_omop;

SET GLOBAL local_infile=1;

load data local infile 'C:\\test\\fhir-to-omop\\RaceAndEthnicityCDC-OMOP-MAPPING-Race.csv'
into table fhir_to_omop_race
fields terminated by ','
optionally enclosed by '"'
lines terminated by '\r\n'
ignore 1 lines
;

select * from fhir_to_omop_race;

select count(*) from fhir_to_omop_race;

load data local infile 'C:\\test\\fhir-to-omop\\RaceAndEthnicityCDC-OMOP-MAPPING-Eth.csv'
into table fhir_to_omop_ethnicity
fields terminated by ','
optionally enclosed by '"'
lines terminated by '\r\n'
ignore 1 lines
;

select * from fhir_to_omop_ethnicity;

select count(*) from fhir_to_omop_ethnicity;



