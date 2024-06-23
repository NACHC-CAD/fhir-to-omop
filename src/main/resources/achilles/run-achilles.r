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
password <- "@pwd" 
server <- "@server" 
port <- "@port"
pathToDriver <- "@pathToDriver"  
extraSettings <- ""

cdmVersion <- "@cdmVersion" 
cdmDatabaseSchema <- "@cdmDbName"
resultsDatabaseSchema <- "@resultsDbName"

connectionDetails <- DatabaseConnector::createConnectionDetails(
  dbms = dbms, 
  user = user, 
  password = password, 
  server = server, 
  port = port, 
  pathToDriver = pathToDriver,
  extraSettings = extraSettings 
)


print("Running Achilles")
Achilles::achilles(
  cdmVersion = cdmVersion, 
  connectionDetails = connectionDetails,
  cdmDatabaseSchema = cdmDatabaseSchema,
  resultsDatabaseSchema = resultsDatabaseSchema
)

sink(file=NULL)
print("Done running Achilles")
