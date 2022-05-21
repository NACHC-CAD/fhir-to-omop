# ---
# 
# Script to run Achilles
# Run the install (once) and init (each time) scripts before running this script.
#
# ---

connectionDetails <- DatabaseConnector::createConnectionDetails(
  dbms = dbms, 
  user = user, 
  password = password, 
  server = server, 
  port = port, 
  pathToDriver = pathToDriver,
  extraSettings = extraSettings 
)


Achilles::achilles(
  cdmVersion = "5.4", 
  connectionDetails = connectionDetails,
  cdmDatabaseSchema = "synthea_micro.dbo",
  resultsDatabaseSchema = "synthea_micro_achilles_results.dbo"
)


