use synthea_omop;

SET GLOBAL local_infile=1;
SET GLOBAL connect_timeout=28800;
SET GLOBAL net_read_timeout=28800;

show variables like 'wait_timeout';
show variables like 'max_allowed_packet';
show variables like 'local_infile';
show variables like 'connect_timeout';
show variables like 'net_read_timeout';

load data local infile 'C:\\_WORKSPACES\\nachc\\_PROJECT\\cosmos\\omop-concepts\\vocabulary_download_v5_{9259d46c-23e7-4760-b8d6-cddd5d86be7d}_1631306538104\\DOMAIN.csv'
into table domain
fields terminated by '\t'
enclosed by '"'
lines terminated by '\n'
ignore 1 lines
;

load data local infile 'C:\\_WORKSPACES\\nachc\\_PROJECT\\cosmos\\omop-concepts\\vocabulary_download_v5_{9259d46c-23e7-4760-b8d6-cddd5d86be7d}_1631306538104\\CONCEPT.csv'
into table concept
fields terminated by '\t'
enclosed by '"'
lines terminated by '\n'
ignore 1 lines
;

load data local infile 'C:\\_WORKSPACES\\nachc\\_PROJECT\\cosmos\\omop-concepts\\vocabulary_download_v5_{9259d46c-23e7-4760-b8d6-cddd5d86be7d}_1631306538104\\CONCEPT_RELATIONSHIP.csv'
into table concept_relationship
fields terminated by '\t'
enclosed by '"'
lines terminated by '\n'
ignore 1 lines
;

load data local infile 'C:\\_WORKSPACES\\nachc\\_PROJECT\\cosmos\\omop-concepts\\vocabulary_download_v5_{9259d46c-23e7-4760-b8d6-cddd5d86be7d}_1631306538104\\CONCEPT_ANCESTOR.csv'
into table concept_ancestor
fields terminated by '\t'
enclosed by '"'
lines terminated by '\n'
ignore 1 lines
;

load data local infile 'C:\\_WORKSPACES\\nachc\\_PROJECT\\cosmos\\omop-concepts\\vocabulary_download_v5_{9259d46c-23e7-4760-b8d6-cddd5d86be7d}_1631306538104\\CONCEPT_SYNONYM.csv'
into table concept_synonym
fields terminated by '\t'
enclosed by '"'
lines terminated by '\n'
ignore 1 lines
;

load data local infile 'C:\\_WORKSPACES\\nachc\\_PROJECT\\cosmos\\omop-concepts\\vocabulary_download_v5_{9259d46c-23e7-4760-b8d6-cddd5d86be7d}_1631306538104\\DRUG_STRENGTH.csv'
into table drug_strength
fields terminated by '\t'
enclosed by '"'
lines terminated by '\n'
ignore 1 lines
;

/*
load data local infile 'C:\\_WORKSPACES\\nachc\\_PROJECT\\cosmos\\omop-concepts\\vocabulary_download_v5_{9259d46c-23e7-4760-b8d6-cddd5d86be7d}_1631306538104\\CONCEPT_CPT4.csv'
into table concept_cpt4
fields terminated by '\t'
enclosed by '"'
lines terminated by '\n'
ignore 1 lines
;
*/

load data local infile 'C:\\_WORKSPACES\\nachc\\_PROJECT\\cosmos\\omop-concepts\\vocabulary_download_v5_{9259d46c-23e7-4760-b8d6-cddd5d86be7d}_1631306538104\\RELATIONSHIP.csv'
into table relationship
fields terminated by '\t'
enclosed by '"'
lines terminated by '\n'
ignore 1 lines
;

load data local infile 'C:\\_WORKSPACES\\nachc\\_PROJECT\\cosmos\\omop-concepts\\vocabulary_download_v5_{9259d46c-23e7-4760-b8d6-cddd5d86be7d}_1631306538104\\CONCEPT_CLASS.csv'
into table concept_class
fields terminated by '\t'
enclosed by '"'
lines terminated by '\n'
ignore 1 lines
;

load data local infile 'C:\\_WORKSPACES\\nachc\\_PROJECT\\cosmos\\omop-concepts\\vocabulary_download_v5_{9259d46c-23e7-4760-b8d6-cddd5d86be7d}_1631306538104\\VOCABULARY.csv'
into table vocabulary
fields terminated by '\t'
enclosed by '"'
lines terminated by '\n'
ignore 1 lines
;






