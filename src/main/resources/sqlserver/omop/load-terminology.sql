bulk insert  domain
from  '@terminologiesRootFolder/DOMAIN.csv'
with ( 
  FIELDTERMINATOR = '\t',
-- optionally enclosed by '"'
  ROWTERMINATOR = '0x0a',
  FIRSTROW = 2
);


bulk insert  concept
from  '@terminologiesRootFolder/CONCEPT.csv'
with ( 
  FIELDTERMINATOR = '\t',
-- optionally enclosed by '"'
  ROWTERMINATOR = '0x0a',
  FIRSTROW = 2
);

bulk insert  concept_relationship
from  '@terminologiesRootFolder/CONCEPT_RELATIONSHIP.csv'
with ( 
  FIELDTERMINATOR = '\t',
-- optionally enclosed by '"'
  ROWTERMINATOR = '0x0a',
  FIRSTROW = 2
);

bulk insert  concept_ancestor
from  '@terminologiesRootFolder/CONCEPT_ANCESTOR.csv'
with ( 
  FIELDTERMINATOR = '\t',
-- optionally enclosed by '"'
  ROWTERMINATOR = '0x0a',
  FIRSTROW = 2
);

bulk insert  concept_synonym
from  '@terminologiesRootFolder/CONCEPT_SYNONYM.csv'
with ( 
  FIELDTERMINATOR = '\t',
-- optionally enclosed by '"'
  ROWTERMINATOR = '0x0a',
  FIRSTROW = 2
);

bulk insert  drug_strength
from  '@terminologiesRootFolder/DRUG_STRENGTH.csv'
with ( 
  FIELDTERMINATOR = '\t',
-- optionally enclosed by '"'
  ROWTERMINATOR = '0x0a',
  FIRSTROW = 2
);

bulk insert  relationship
from  '@terminologiesRootFolder/RELATIONSHIP.csv'
with ( 
  FIELDTERMINATOR = '\t',
-- optionally enclosed by '"'
  ROWTERMINATOR = '0x0a',
  FIRSTROW = 2
);

bulk insert  concept_class
from  '@terminologiesRootFolder/CONCEPT_CLASS.csv'
with ( 
  FIELDTERMINATOR = '\t',
-- optionally enclosed by '"'
  ROWTERMINATOR = '0x0a',
  FIRSTROW = 2
);

bulk insert  vocabulary
from  '@terminologiesRootFolder/VOCABULARY.csv'
with ( 
  FIELDTERMINATOR = '\t',
-- optionally enclosed by '"'
  ROWTERMINATOR = '0x0a',
  FIRSTROW = 2
);






