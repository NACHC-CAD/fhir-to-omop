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

# local files
pathToDriver <- "D:\\NACHC\\SYNTHEA\\DQD\\resources\\jar\\sqlserver-jar"  # location of the mssql-jdbc-10.2.0.jre8.jar
outputFolder <- "D:\\NACHC\\SYNTHEA\\DQD\\output"                         # location where output file will be written
outputFile <- "results.json"                                              # file for results json

# database connectivity
dbms <- "sql server"
user <- "synthea_user" 
password <- "sneeker" 
server <- "localhost" 
port <- "1433"
pathToDriver <- pathToDriver
extraSettings <- ";databaseName=master;integratedSecurity=true;encrypt=false"

# database schemas
cdmDatabaseSchema <- "synthea_micro.dbo"                       # your omop instance
resultsDatabaseSchema <- "synthea_micro_dqd_results.dbo"       # instance where results will be written
cdmSourceName <- "SYNTHEA_MICRO_Test_Database"                 # a human readable name for your CDM source

# config parameters
numThreads <- 1       # number of threads to run, 3 seems to work well on Redshift
sqlOnly <- FALSE      # set to TRUE if you just want to get the SQL scripts and not actually run the queries
verboseMode <- TRUE   # set to TRUE if you want to see activity written to the console
writeToTable <- TRUE  # set to FALSE if you want to skip writing to a SQL table in the results schema

# dqd parameters
checkLevels <- c("TABLE", "FIELD", "CONCEPT")  # which DQ check levels to run
checkNames <- c()                              # which DQ checks to run, names can be found in inst/csv/OMOP_CDM_v5.3.1_Check_Desciptions.csv
tablesToExclude <- c()                         # which CDM tables to exclude?



