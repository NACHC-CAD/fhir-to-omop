truncate table domain;
bulk insert  domain
from  '@terminologiesRootFolder/DOMAIN.csv'
with ( 
  FIELDTERMINATOR = '\t',
-- optionally enclosed by '"'
  ROWTERMINATOR = '0x0a',
  FIRSTROW = 2
);


truncate table concept;
bulk insert  concept
from  '@terminologiesRootFolder/CONCEPT.csv'
with ( 
  FIELDTERMINATOR = '\t',
-- optionally enclosed by '"'
  ROWTERMINATOR = '0x0a',
  FIRSTROW = 2
);

truncate table concept_relationship;
bulk insert  concept_relationship
from  '@terminologiesRootFolder/CONCEPT_RELATIONSHIP.csv'
with ( 
  FIELDTERMINATOR = '\t',
-- optionally enclosed by '"'
  ROWTERMINATOR = '0x0a',
  FIRSTROW = 2
);

truncate table concept_ancestor;
bulk insert  concept_ancestor
from  '@terminologiesRootFolder/CONCEPT_ANCESTOR.csv'
with ( 
  FIELDTERMINATOR = '\t',
-- optionally enclosed by '"'
  ROWTERMINATOR = '0x0a',
  FIRSTROW = 2
);

truncate table concept_synonym;
bulk insert  concept_synonym
from  '@terminologiesRootFolder/CONCEPT_SYNONYM.csv'
with ( 
  FIELDTERMINATOR = '\t',
-- optionally enclosed by '"'
  ROWTERMINATOR = '0x0a',
  FIRSTROW = 2
);

truncate table drug_strength;
bulk insert  drug_strength
from  '@terminologiesRootFolder/DRUG_STRENGTH.csv'
with ( 
  FIELDTERMINATOR = '\t',
-- optionally enclosed by '"'
  ROWTERMINATOR = '0x0a',
  FIRSTROW = 2
);

truncate table relationship;
bulk insert  relationship
from  '@terminologiesRootFolder/RELATIONSHIP.csv'
with ( 
  FIELDTERMINATOR = '\t',
-- optionally enclosed by '"'
  ROWTERMINATOR = '0x0a',
  FIRSTROW = 2
);

truncate table concept_class;
bulk insert  concept_class
from  '@terminologiesRootFolder/CONCEPT_CLASS.csv'
with ( 
  FIELDTERMINATOR = '\t',
-- optionally enclosed by '"'
  ROWTERMINATOR = '0x0a',
  FIRSTROW = 2
);

truncate table vocabulary;
bulk insert  vocabulary
from  '@terminologiesRootFolder/VOCABULARY.csv'
with ( 
  FIELDTERMINATOR = '\t',
-- optionally enclosed by '"'
  ROWTERMINATOR = '0x0a',
  FIRSTROW = 2
);






