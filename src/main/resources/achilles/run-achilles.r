# ---
# 
# Script to run Achilles
#
# ---

sink(file="./SINK.TXT")
library(SqlRender)
library(Achilles)

dbms <- "@dbms"
user <- "@user" 
password <- "@password" 
connectionString <- "@connectionString"
pathToDriver <- "@pathToDriver"  

cdmVersion <- "@cdmVersion" 
cdmDatabaseSchema <- "@cdmDatabaseSchema"
resultsDatabaseSchema <- "@resultsDatabaseSchema"

connectionDetails <- DatabaseConnector::createConnectionDetails(
  dbms = dbms, 
  user = user, 
  password = password, 
  pathToDriver = pathToDriver,
  connectionString = connectionString
)


print("Running Achilles")
Achilles::achilles(
  connectionDetails = connectionDetails,
  cdmVersion = cdmVersion, 
  cdmDatabaseSchema = cdmDatabaseSchema,
  resultsDatabaseSchema = resultsDatabaseSchema
)

sink(file=NULL)
print("Done running Achilles")
