# ---
#
# This file has been addapted from the documentation for the ETL-Synthea project:
# https://github.com/OHDSI/ETL-Synthea
#
# The SHA code used to specify the version used here is for the 2022-12-02 commit.  
#
# ---

devtools::install_github("OHDSI/ETL-Synthea@bae1c8eca8dba7ba1129f6c5f758b985c5458405")

library(ETLSyntheaBuilder)

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
vocabFileLoc   <- "C:\\_YES\\tools\\terminology\\fhir-to-omop\\for-testing\\csv"

ETLSyntheaBuilder::CreateCDMTables(connectionDetails = cd, cdmSchema = cdmSchema, cdmVersion = cdmVersion)

ETLSyntheaBuilder::CreateSyntheaTables(connectionDetails = cd, syntheaSchema = syntheaSchema, syntheaVersion = syntheaVersion)

ETLSyntheaBuilder::LoadSyntheaTables(connectionDetails = cd, syntheaSchema = syntheaSchema, syntheaFileLoc = syntheaFileLoc)

ETLSyntheaBuilder::LoadVocabFromCsv(connectionDetails = cd, cdmSchema = cdmSchema, vocabFileLoc = vocabFileLoc)

ETLSyntheaBuilder::LoadEventTables(connectionDetails = cd, cdmSchema = cdmSchema, syntheaSchema = syntheaSchema, cdmVersion = cdmVersion, syntheaVersion = syntheaVersion)








