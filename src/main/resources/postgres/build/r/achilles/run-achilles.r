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

dbms <- "postgresql"
user <- "ohdsi_admin_user" 
password <- "app1" 
server <- "localhost/OHDSI" 
port <- "5432"
pathToDriver <- "C:\\_YES\\databases\\postgres\\drivers\\42.3.3"  
extraSettings <- ""

cdmVersion <- "5.4" 
cdmDatabaseSchema <- "etl_synthea_1k"
resultsDatabaseSchema <- "etl_synthea_1k_ach_res"

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
print("Done running Achilles")
sink(file=NULL)
print("Done running Achilles")
