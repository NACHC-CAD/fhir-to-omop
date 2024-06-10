# ---
#
# This file has been adapted from the documentation for the ETL-Synthea project:
# https://github.com/OHDSI/ETL-Synthea
#
# The SHA code used to specify the version used here is for the 2023-03-10 commit.  
#
# ---

print("Installing NACHC-CAD/ETL-Synthea...")
devtools::install_github("NACHC-CAD/ETL-Synthea@9e324c67b999f961603de2865d1fe6fbecd58091")
print("Done installing.")
print("Setting parameters...")

library(ETLSyntheaBuilder)

cd <- DatabaseConnector::createConnectionDetails(
  dbms     = "@dbms", 
  server   = "@server", 
  user     = "@uid", 
  password = "@pwd", 
  port     =  @port, 
  pathToDriver = "@pathToDriver",
  extraSettings = "@extraSettings"
)

cdmSchema      <- "@FullySpecifiedSchemaName"
cdmVersion     <- "@CdmVersion"
syntheaVersion <- "@SyntheaVersion"
syntheaSchema  <- "@SyntheaCsvNativeSchema"
syntheaFileLoc <- "@SyntheaCsvFilesDir"

print("")
print("")
print("# * * *")
print("#")
print("# CREATING TABLES")
print("#")
print("# * * *")
print("")
print("")

ETLSyntheaBuilder::CreateSyntheaTables(
	connectionDetails = cd, 
	syntheaSchema = syntheaSchema, 
	syntheaVersion = syntheaVersion
)

print("")
print("")
print("# * * *")
print("#")
print("# LOADING DATA")
print("#")
print("# * * *")
print("")
print("")

ETLSyntheaBuilder::LoadSyntheaTables(
	connectionDetails = cd, 
	syntheaSchema = syntheaSchema, 
	syntheaFileLoc = syntheaFileLoc
)

# ETLSyntheaBuilder::LoadEventTables(connectionDetails = cd, cdmSchema = cdmSchema, syntheaSchema = syntheaSchema, cdmVersion = cdmVersion, syntheaVersion = syntheaVersion)

print("")
print("")
print("# * * *")
print("#")
print("# Done.")
print("#")
print("# * * *")
print("")
print("")







