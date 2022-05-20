# ---
#
# run-dqd.r
#
# Run the init-parameters.r script before running this script.   
#
# Script to run the Data Quality Dashboard (DQD)
# 
# The output of this script is a single JSON file that can then be rendered with the run-dqd.r script 
# to view the results as a web page in a browser.  
# 
# Prior to running this script you will need to download and install the jdbc driver For MS Sql Server.  
# This is entered as the pathToDriver variable below.  
#
# ---

# ---
# 
# CREATE THE CONNECTION OBJECT AND RUN THE JOB
# 
# ---

# create connection details object
connectionDetails <- DatabaseConnector::createConnectionDetails(
  dbms = dbms, 
  user = user, 
  password = password, 
  server = server, 
  port = port, 
  pathToDriver = pathToDriver,
  extraSettings = extraSettings 
)

# (OPTIONAL): if writing to table and using Redshift, bulk loading can be initialized
# Sys.setenv (
#   "AWS_ACCESS_KEY_ID" = "",
#   "AWS_SECRET_ACCESS_KEY" = "",
#   "AWS_DEFAULT_REGION" = "",
#   "AWS_BUCKET_NAME" = "",
#   "AWS_OBJECT_KEY" = "",
#   "AWS_SSE_TYPE" = "AES256",
#   "USE_MPP_BULK_LOAD" = TRUE
# )

# ---
#
# run the job
#
# ---

DataQualityDashboard::executeDqChecks (
  connectionDetails = connectionDetails, 
  cdmDatabaseSchema = cdmDatabaseSchema, 
  resultsDatabaseSchema = resultsDatabaseSchema,
  cdmSourceName = cdmSourceName, 
  numThreads = numThreads,
  sqlOnly = sqlOnly, 
  outputFolder = outputFolder, 
  outputFile = outputFile,
  verboseMode = verboseMode,
  writeToTable = writeToTable,
  checkLevels = checkLevels,
  tablesToExclude = tablesToExclude,
  checkNames = checkNames,
  cdmVersion = "5.4"
)

# (OPTIONAL) inspect logs
# ParallelLogger::launchLogViewer(
#   logFileName = file.path(outputFolder, cdmSourceName, 
#   sprintf("log_DqDashboard_%s.txt", cdmSourceName))
# )

# (OPTIONAL) if you want to write the JSON file to the results table separately
# jsonFilePath <- ""
# DataQualityDashboard::writeJsonResultsToTable(
#   connectionDetails = connectionDetails, 
#   resultsDatabaseSchema = resultsDatabaseSchema, 
#   jsonFilePath = jsonFilePath
# )





