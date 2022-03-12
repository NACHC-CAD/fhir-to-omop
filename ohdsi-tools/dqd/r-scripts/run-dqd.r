
pathToDriver <- "C:\\test\\jar\\sqlserver-jar"
outputFolder <- "C:\\test\\ohdsi\\dqd"

# fill out the connection details -----------------------------------------------------------------------
connectionDetails <- DatabaseConnector::createConnectionDetails(dbms = "sql server", 
                                                                user = "synthea_user", 
                                                                password = "sneeker", 
                                                                server = "localhost", 
                                                                port = "1433", 
                                                                pathToDriver = pathToDriver,
                                                                extraSettings = ";databaseName=master;integratedSecurity=true;encrypt=false")


cdmDatabaseSchema <- "synthea_omop.dbo" # the fully qualified database schema name of the CDM
resultsDatabaseSchema <- "synthea_omop_dqd_results.dbo" # the fully qualified database schema name of the results schema (that you can write to)
cdmSourceName <- "SYNTHEA_Test_Database" # a human readable name for your CDM source

# determine how many threads (concurrent SQL sessions) to use ----------------------------------------
numThreads <- 1 # on Redshift, 3 seems to work well

# specify if you want to execute the queries or inspect them ------------------------------------------
sqlOnly <- FALSE # set to TRUE if you just want to get the SQL scripts and not actually run the queries

# where should the logs go? -------------------------------------------------------------------------
outputFolder <- outputFolder
outputFile <- "results.json"

# logging type -------------------------------------------------------------------------------------
verboseMode <- TRUE # set to TRUE if you want to see activity written to the console

# write results to table? ------------------------------------------------------------------------------
writeToTable <- TRUE # set to FALSE if you want to skip writing to a SQL table in the results schema

# if writing to table and using Redshift, bulk loading can be initialized -------------------------------

# Sys.setenv("AWS_ACCESS_KEY_ID" = "",
#            "AWS_SECRET_ACCESS_KEY" = "",
#            "AWS_DEFAULT_REGION" = "",
#            "AWS_BUCKET_NAME" = "",
#            "AWS_OBJECT_KEY" = "",
#            "AWS_SSE_TYPE" = "AES256",
#            "USE_MPP_BULK_LOAD" = TRUE)

# which DQ check levels to run -------------------------------------------------------------------
checkLevels <- c("TABLE", "FIELD", "CONCEPT")

# which DQ checks to run? ------------------------------------

checkNames <- c() # Names can be found in inst/csv/OMOP_CDM_v5.3.1_Check_Desciptions.csv

# which CDM tables to exclude? ------------------------------------

tablesToExclude <- c() 

# run the job --------------------------------------------------------------------------------------
DataQualityDashboard::executeDqChecks(connectionDetails = connectionDetails, 
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
                                      checkNames = checkNames)

# inspect logs ----------------------------------------------------------------------------
# ParallelLogger::launchLogViewer(logFileName = file.path(outputFolder, cdmSourceName, 
#                                                         sprintf("log_DqDashboard_%s.txt", cdmSourceName)))

# (OPTIONAL) if you want to write the JSON file to the results table separately -----------------------------
# jsonFilePath <- ""
# DataQualityDashboard::writeJsonResultsToTable(connectionDetails = connectionDetails, 
#                                               resultsDatabaseSchema = resultsDatabaseSchema, 
#                                               jsonFilePath = jsonFilePath)




