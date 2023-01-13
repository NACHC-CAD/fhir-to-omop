copy <dbName>.domain
from '@terminologiesRootFolder/DOMAIN.csv'
  DELIMITER E'\t'
  QUOTE E'\b' 
  ESCAPE '\' 
  CSV HEADER
;


copy <dbName>.concept
from '@terminologiesRootFolder/CONCEPT.csv'
  DELIMITER E'\t'
  QUOTE E'\b' 
  ESCAPE '\' 
  CSV HEADER
;

copy <dbName>.concept_relationship
from '@terminologiesRootFolder/CONCEPT_RELATIONSHIP.csv'
  DELIMITER E'\t'
  QUOTE E'\b' 
  ESCAPE '\' 
  CSV HEADER
;

copy <dbName>.concept_ancestor
from '@terminologiesRootFolder/CONCEPT_ANCESTOR.csv'
  DELIMITER E'\t'
  QUOTE E'\b' 
  ESCAPE '\' 
  CSV HEADER
;

copy <dbName>.concept_synonym
from '@terminologiesRootFolder/CONCEPT_SYNONYM.csv'
  DELIMITER E'\t'
  QUOTE E'\b' 
  ESCAPE '\' 
  CSV HEADER
;

copy <dbName>.drug_strength
from '@terminologiesRootFolder/DRUG_STRENGTH.csv'
  DELIMITER E'\t'
  QUOTE E'\b' 
  ESCAPE '\' 
  CSV HEADER
;

copy <dbName>.relationship
from '@terminologiesRootFolder/RELATIONSHIP.csv'
  DELIMITER E'\t'
  QUOTE E'\b' 
  ESCAPE '\' 
  CSV HEADER
;

copy <dbName>.concept_class
from '@terminologiesRootFolder/CONCEPT_CLASS.csv'
  DELIMITER E'\t'
  QUOTE E'\b' 
  ESCAPE '\' 
  CSV HEADER
;

copy <dbName>.vocabulary
from '@terminologiesRootFolder/VOCABULARY.csv'
  DELIMITER E'\t'
  QUOTE E'\b' 
  ESCAPE '\' 
  CSV HEADER
;

