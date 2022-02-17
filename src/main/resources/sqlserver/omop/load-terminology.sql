
use synthea_omop;

bulk insert  domain
from  'C:\\_WORKSPACES\\nachc\\_CURRENT\\_PROJECT\\cosmos\\omop-concepts\\vocabulary_download_v5_{9259d46c-23e7-4760-b8d6-cddd5d86be7d}_1631306538104\\DOMAIN.csv'
with ( 
  FIELDTERMINATOR = '\t',
-- optionally enclosed by '"'
  ROWTERMINATOR = '0x0a',
  FIRSTROW = 2
);


bulk insert  concept
from  'C:\\_WORKSPACES\\nachc\\_CURRENT\\_PROJECT\\cosmos\\omop-concepts\\vocabulary_download_v5_{9259d46c-23e7-4760-b8d6-cddd5d86be7d}_1631306538104\\CONCEPT.csv'
with ( 
  FIELDTERMINATOR = '\t',
-- optionally enclosed by '"'
  ROWTERMINATOR = '0x0a',
  FIRSTROW = 2
);

bulk insert  concept_relationship
from  'C:\\_WORKSPACES\\nachc\\_CURRENT\\_PROJECT\\cosmos\\omop-concepts\\vocabulary_download_v5_{9259d46c-23e7-4760-b8d6-cddd5d86be7d}_1631306538104\\CONCEPT_RELATIONSHIP.csv'
with ( 
  FIELDTERMINATOR = '\t',
-- optionally enclosed by '"'
  ROWTERMINATOR = '0x0a',
  FIRSTROW = 2
);

bulk insert  concept_ancestor
from  'C:\\_WORKSPACES\\nachc\\_CURRENT\\_PROJECT\\cosmos\\omop-concepts\\vocabulary_download_v5_{9259d46c-23e7-4760-b8d6-cddd5d86be7d}_1631306538104\\CONCEPT_ANCESTOR.csv'
with ( 
  FIELDTERMINATOR = '\t',
-- optionally enclosed by '"'
  ROWTERMINATOR = '0x0a',
  FIRSTROW = 2
);

bulk insert  concept_synonym
from  'C:\\_WORKSPACES\\nachc\\_CURRENT\\_PROJECT\\cosmos\\omop-concepts\\vocabulary_download_v5_{9259d46c-23e7-4760-b8d6-cddd5d86be7d}_1631306538104\\CONCEPT_SYNONYM.csv'
with ( 
  FIELDTERMINATOR = '\t',
-- optionally enclosed by '"'
  ROWTERMINATOR = '0x0a',
  FIRSTROW = 2
);

bulk insert  drug_strength
from  'C:\\_WORKSPACES\\nachc\\_CURRENT\\_PROJECT\\cosmos\\omop-concepts\\vocabulary_download_v5_{9259d46c-23e7-4760-b8d6-cddd5d86be7d}_1631306538104\\DRUG_STRENGTH.csv'
with ( 
  FIELDTERMINATOR = '\t',
-- optionally enclosed by '"'
  ROWTERMINATOR = '0x0a',
  FIRSTROW = 2
);

/*
bulk insert  
from  'C:\\_WORKSPACES\\nachc\\_CURRENT\\_PROJECT\\cosmos\\omop-concepts\\vocabulary_download_v5_{9259d46c-23e7-4760-b8d6-cddd5d86be7d}_1631306538104\\CONCEPT_CPT4.csv'
with ( concept_cpt4
  FIELDTERMINATOR = '\t',
-- optionally enclosed by '"'
  ROWTERMINATOR = '0x0a',
  FIRSTROW = 2
);
*/

bulk insert  relationship
from  'C:\\_WORKSPACES\\nachc\\_CURRENT\\_PROJECT\\cosmos\\omop-concepts\\vocabulary_download_v5_{9259d46c-23e7-4760-b8d6-cddd5d86be7d}_1631306538104\\RELATIONSHIP.csv'
with ( 
  FIELDTERMINATOR = '\t',
-- optionally enclosed by '"'
  ROWTERMINATOR = '0x0a',
  FIRSTROW = 2
);

bulk insert  concept_class
from  'C:\\_WORKSPACES\\nachc\\_CURRENT\\_PROJECT\\cosmos\\omop-concepts\\vocabulary_download_v5_{9259d46c-23e7-4760-b8d6-cddd5d86be7d}_1631306538104\\CONCEPT_CLASS.csv'
with ( 
  FIELDTERMINATOR = '\t',
-- optionally enclosed by '"'
  ROWTERMINATOR = '0x0a',
  FIRSTROW = 2
);

bulk insert  vocabulary
from  'C:\\_WORKSPACES\\nachc\\_CURRENT\\_PROJECT\\cosmos\\omop-concepts\\vocabulary_download_v5_{9259d46c-23e7-4760-b8d6-cddd5d86be7d}_1631306538104\\VOCABULARY.csv'
with ( 
  FIELDTERMINATOR = '\t',
-- optionally enclosed by '"'
  ROWTERMINATOR = '0x0a',
  FIRSTROW = 2
);






