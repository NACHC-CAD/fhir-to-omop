# ---
# 
# Script to run Achilles
# Run the install (once) and then run this script to run achilles.
#
# ---

sink(file="./SINK.TXT")
library(SqlRender)
library(Achilles)

# check pkgbuild
pkgbuild::check_build_tools()
# test that SqlRenderer was install and works
translate("SELECT TOP 10 * FROM person;", "postgresql")

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
