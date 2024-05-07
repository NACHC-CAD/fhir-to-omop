:: ------
:: This script will export all of the tables in the given database/schema.  
:: This script was used to create the demo_cdm.zip file.  
:: ------
@echo off
SET PGPASSWORD=mypass
SET DBNAME=postgres
SET SCHEMA=demo_cdm
SET USERNAME=postgres
SET HOST=127.0.0.1
SET PORT=5432

:: Create a directory for the exports
IF NOT EXIST "%SCHEMA%" MKDIR "%SCHEMA%"

:: Export each table to a CSV file
FOR /F "tokens=*" %%G IN ('psql -U %USERNAME% -h %HOST% -p %PORT% -d %DBNAME% -t -c "SELECT tablename FROM pg_tables WHERE schemaname='%SCHEMA%';"') DO (
    echo Exporting %%G
    psql -U %USERNAME% -h %HOST% -p %PORT% -d %DBNAME% -c "COPY (SELECT * FROM %SCHEMA%.%%G) TO STDOUT WITH CSV HEADER" > "%SCHEMA%\%%G.csv"
)

echo Done exporting tables from schema %SCHEMA%.