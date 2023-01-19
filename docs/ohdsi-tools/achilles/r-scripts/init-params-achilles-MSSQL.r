# ---
#
# Configuration for Achilles
# Change the parameters below to use your configuration.  
# Run this script before running run-achilles.r
#
# ---

dbms <- "sql server"
user <- "synthea_micro" 
password <- "Sneaker01" 
server <- "localhost" 
port <- "1433"
pathToDriver <- "D:\\NACHC\\SYNTHEA\\DQD\\resources\\jar\\sqlserver-jar"  # location of the mssql-jdbc-10.2.0.jre8.jar
extraSettings <- ";databaseName=master;integratedSecurity=true;encrypt=false"

cdmVersion <- "5.4" 
cdmDatabaseSchema <- "synthea_micro.dbo"
resultsDatabaseSchema <- "synthea_micro_achilles_results.dbo"



