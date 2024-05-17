# ---
#
# Configuration for Achilles
# Change the parameters below to use your configuration.  
# Run this script before running run-achilles.r
#
# ---

setwd("C:\\_YES\\workspace\\fhir-to-omop\\src\\main\\resources\\achilles\\generate-sql")

dbms <- "sql server"
user <- "synthea_micro" 
password <- "Sneaker01" 
server <- "localhost" 
port <- "1433"
pathToDriver <- "C:\\Program Files\\Microsoft SQL Server\\160\\Tools\\Binn\\jar"  
extraSettings <- ""

cdmVersion <- "5.4" 
cdmDatabaseSchema <- "synthea_micro.dbo"
resultsDatabaseSchema <- "synthea_micro_ach_res.dbo"



