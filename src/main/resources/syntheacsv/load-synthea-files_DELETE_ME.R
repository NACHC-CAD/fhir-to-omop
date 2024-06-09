# ---
#
# This file has been adapted from the documentation for the ETL-Synthea project:
# https://github.com/OHDSI/ETL-Synthea
#
# ---

print("Installing NACHC-CAD/ETL-Synthea...")
devtools::install_github("NACHC-CAD/ETL-Synthea")
print("Done installing.")
print("Setting parameters...")

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

print("Loading synthea tables...")
ETLSyntheaBuilder::LoadSyntheaTables(
	connectionDetails = cd, 
	syntheaSchema = syntheaSchema, 
	syntheaFileLoc = syntheaFileLoc
)

print("Creating Vocab Mapping tables...")
ETLSyntheaBuilder::CreateVocabTables(
	connectionDetails = cd, 
	cdmSchema = cdmSchema, 
	syntheaSchema = syntheaSchema, 
	cdmVersion = cdmVersion, 
	syntheaVersion = syntheaVersion
)

print("Done.")








