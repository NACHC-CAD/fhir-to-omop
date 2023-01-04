copy domain
from '@terminologiesRootFolder/DOMAIN.csv'
  DELIMITER E'\t'
  QUOTE E'\b' 
  ESCAPE '\' 
  CSV HEADER
;


copy concept
from '@terminologiesRootFolder/CONCEPT.csv'
  DELIMITER E'\t'
  QUOTE E'\b' 
  ESCAPE '\' 
  CSV HEADER
;

copy concept_relationship
from '@terminologiesRootFolder/CONCEPT_RELATIONSHIP.csv'
  DELIMITER E'\t'
  QUOTE E'\b' 
  ESCAPE '\' 
  CSV HEADER
;

copy concept_ancestor
from '@terminologiesRootFolder/CONCEPT_ANCESTOR.csv'
  DELIMITER E'\t'
  QUOTE E'\b' 
  ESCAPE '\' 
  CSV HEADER
;

copy concept_synonym
from '@terminologiesRootFolder/CONCEPT_SYNONYM.csv'
  DELIMITER E'\t'
  QUOTE E'\b' 
  ESCAPE '\' 
  CSV HEADER
;

copy drug_strength
from '@terminologiesRootFolder/DRUG_STRENGTH.csv'
  DELIMITER E'\t'
  QUOTE E'\b' 
  ESCAPE '\' 
  CSV HEADER
;

copy relationship
from '@terminologiesRootFolder/RELATIONSHIP.csv'
  DELIMITER E'\t'
  QUOTE E'\b' 
  ESCAPE '\' 
  CSV HEADER
;

copy concept_class
from '@terminologiesRootFolder/CONCEPT_CLASS.csv'
  DELIMITER E'\t'
  QUOTE E'\b' 
  ESCAPE '\' 
  CSV HEADER
;

copy vocabulary
from '@terminologiesRootFolder/VOCABULARY.csv'
  DELIMITER E'\t'
  QUOTE E'\b' 
  ESCAPE '\' 
  CSV HEADER
;

