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
  cdmVersion = cdmVersion, 
  connectionDetails = connectionDetails,
  cdmDatabaseSchema = cdmDatabaseSchema,
  resultsDatabaseSchema = resultsDatabaseSchema
)


