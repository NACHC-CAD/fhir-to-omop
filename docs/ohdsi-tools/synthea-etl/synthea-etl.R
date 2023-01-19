# ---
#
# Datafile was downloaded from: https://synthea.mitre.org/downloads
#
# ---


devtools::install_github("OHDSI/ETL-Synthea")

library(ETLSyntheaBuilder)

# We are loading a version 5.4 CDM into a local PostgreSQL database called "synthea10".
# The ETLSyntheaBuilder package leverages the OHDSI/CommonDataModel package for CDM creation.
# Valid CDM versions are determined by executing CommonDataModel::listSupportedVersions().
# The strings representing supported CDM versions are currently "5.3" and "5.4". 
# The Synthea version we use in this example is 2.7.0.  Since Synthea's MASTER branch is always active,
# the only other version we support is 3.0.0.
# The schema to load the Synthea tables is called "native".
# The schema to load the Vocabulary and CDM tables is "cdm_synthea10".  
# The username and pw are "postgres" and "lollipop".
# The Synthea and Vocabulary CSV files are located in /tmp/synthea/output/csv and /tmp/Vocabulary_20181119, respectively.

# For those interested in seeing the CDM changes from 5.3 to 5.4, please see: http://ohdsi.github.io/CommonDataModel/cdm54Changes.html

cd <- DatabaseConnector::createConnectionDetails(
  dbms     = "postgresql", 
  server   = "localhost/OHDSI", 
  user     = "postgres", 
  password = "ohdsi", 
  port     = 5432, 
  pathToDriver = "D:\\_WORKSPACES\\nachc\\_CURRENT\\drivers\\postgresql\\42.3.3"  
)

cdmSchema      <- "cdm_f2o_build"
cdmVersion     <- "5.4"
syntheaVersion <- "2.7.0"
syntheaSchema  <- "synthea_native"
syntheaFileLoc <- "C:\\_YES\\workspace\\synthea-etl\\10k_synthea_covid19_csv\\10k_synthea_covid19_csv"
vocabFileLoc   <- "C:\\fhir-to-omop\\terminology\\zip"

ETLSyntheaBuilder::CreateCDMTables(connectionDetails = cd, cdmSchema = cdmSchema, cdmVersion = cdmVersion)

ETLSyntheaBuilder::CreateSyntheaTables(connectionDetails = cd, syntheaSchema = syntheaSchema, syntheaVersion = syntheaVersion)

ETLSyntheaBuilder::LoadSyntheaTables(connectionDetails = cd, syntheaSchema = syntheaSchema, syntheaFileLoc = syntheaFileLoc)

ETLSyntheaBuilder::LoadVocabFromCsv(connectionDetails = cd, cdmSchema = cdmSchema, vocabFileLoc = vocabFileLoc)

ETLSyntheaBuilder::LoadEventTables(connectionDetails = cd, cdmSchema = cdmSchema, syntheaSchema = syntheaSchema, cdmVersion = cdmVersion, syntheaVersion = syntheaVersion)








