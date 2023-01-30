
devtools::install_github("OHDSI/ETL-Synthea")

library(ETLSyntheaBuilder)

# We are loading a version 5.4 CDM into a local PostgreSQL database schema called "OHDSI/etl_synthea_1k".
# The ETLSyntheaBuilder package leverages the OHDSI/CommonDataModel package for CDM creation.
# Valid CDM versions are determined by executing CommonDataModel::listSupportedVersions().
# The strings representing supported CDM versions are currently "5.3" and "5.4". 
# The Synthea version we use in this example is 2.7.0.  Since Synthea's MASTER branch is always active,
# the only other version we support is 3.0.0.
# The schema to load the Synthea tables is called "native".
# The schema to load the Vocabulary and CDM tables is "cdm_synthea10".  
# The username and pw are "postgres" and "ohdsi".
# The Synthea files are in included in the fhir-to-omop install and are located at C:\\_YES\\workspace\\fhir-to-omop\\src\\main\\resources\\test\\fhir\\csv\\synthmass-1k\\synthea_sample_data_csv_apr2020\\csv
# The Vocabulary CSV files need to be downloaded to the directory 

# For those interested in seeing the CDM changes from 5.3 to 5.4, please see: http://ohdsi.github.io/CommonDataModel/cdm54Changes.html

cd <- DatabaseConnector::createConnectionDetails(
  dbms     = "postgresql", 
  server   = "localhost/OHDSI", 
  user     = "postgres", 
  password = "ohdsi", 
  port     = 5432, 
  pathToDriver = "C:\\_YES\\databases\\postgres\\drivers\\42.3.3"  
)

cdmSchema      <- "etl_synthea_1k"
cdmVersion     <- "5.4"
syntheaVersion <- "2.7.0"
syntheaSchema  <- "etl_synthea_1k_synthea_native"
syntheaFileLoc <- "C:\\_YES\\workspace\\fhir-to-omop\\src\\main\\resources\\test\\fhir\\csv\\synthmass-1k\\synthea_sample_data_csv_apr2020\\csv"
vocabFileLoc   <- "C:\\_YES\\tools\\terminology\\fhir-to-omop\\for-testing"

ETLSyntheaBuilder::CreateCDMTables(connectionDetails = cd, cdmSchema = cdmSchema, cdmVersion = cdmVersion)

ETLSyntheaBuilder::CreateSyntheaTables(connectionDetails = cd, syntheaSchema = syntheaSchema, syntheaVersion = syntheaVersion)

ETLSyntheaBuilder::LoadSyntheaTables(connectionDetails = cd, syntheaSchema = syntheaSchema, syntheaFileLoc = syntheaFileLoc)

ETLSyntheaBuilder::LoadVocabFromCsv(connectionDetails = cd, cdmSchema = cdmSchema, vocabFileLoc = vocabFileLoc)

ETLSyntheaBuilder::LoadEventTables(connectionDetails = cd, cdmSchema = cdmSchema, syntheaSchema = syntheaSchema, cdmVersion = cdmVersion, syntheaVersion = syntheaVersion)








