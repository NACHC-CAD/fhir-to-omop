# ---
#
# This file has been adapted from the documentation for the ETL-Synthea project:
# https://github.com/OHDSI/ETL-Synthea
#
# The SHA code used to specify the version used here is for the 2023-03-10 commit.  
#
# ---

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

print("")
print("")
print("# * * *")
print("#")
print("# CREATING MAP AND ROLLUP TABLES")
print("#")
print("# * * *")
print("")
print("")

ETLSyntheaBuilder::CreateMapAndRollupTables(
	connectionDetails = cd, 
	cdmSchema = cdmSchema, 
	syntheaSchema = syntheaSchema, 
	cdmVersion = cdmVersion, 
	syntheaVersion = syntheaVersion
)

print("")
print("")
print("# * * *")
print("#")
print("# LOADING EVENT TABLES")
print("#")
print("# * * *")
print("")
print("")

ETLSyntheaBuilder::LoadEventTables(
	connectionDetails = cd, 
	cdmSchema = cdmSchema, 
	syntheaSchema = syntheaSchema, 
	cdmVersion = cdmVersion, 
	syntheaVersion = 
	syntheaVersion
)

print("")
print("")
print("# * * *")
print("#")
print("# Done.")
print("#")
print("# * * *")
print("")
print("")







