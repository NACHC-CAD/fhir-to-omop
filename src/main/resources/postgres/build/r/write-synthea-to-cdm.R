# ---
#
# This file has been addapted from the documentation for the ETL-Synthea project:
# https://github.com/OHDSI/ETL-Synthea
#
# ---

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

print("Running ETL of data from synthea tables to CDM...")
ETLSyntheaBuilder::LoadEventTables(connectionDetails = cd, cdmSchema = cdmSchema, syntheaSchema = syntheaSchema, cdmVersion = cdmVersion, syntheaVersion = syntheaVersion)

print("Done.")








