:: -----------------------
::
:: bat file to install and build projects for WebAPI and Atlas
::   TODO: NEED TO ADD TAG AND CHECKOUT FOR fhir-to-omop
::
:: -----------------------

@echo off

::
:: Clone fhir-to-omop
::

call jv 11

echo.
echo.
echo Cloning fhir-to-omop
rmdir fhir-to-omop
git clone https://github.com/NACHC-CAD/fhir-to-omop
cd fhir-to-omop
call mvn clean 
call mvn install
echo Done with fhir-to-omop mvn clean install 
cd ..
echo.
echo.

call jv 8

::
:: Clone WebAPI
::

echo.
echo.
echo Cloning WebAPI
rmdir WebAPI
git clone https://github.com/OHDSI/WebAPI
cd WebAPI
git checkout v2.12.0
echo.
echo.
mkdir WebAPIConfig
copy ..\fhir-to-omop\docs\ohdsi-tools\webapi\WebAPIConfig\settings.xml .\WebAPIConfig
call mvn clean   -DskipUnitTests -DskipITtests -s WebAPIConfig/settings.xml -P webapi-postgresql
call mvn package -DskipUnitTests -DskipITtests -s WebAPIConfig/settings.xml -P webapi-postgresql
cd ..

::
:: Clone Atlas
::

echo Cloning Atlas
rmdir atlas 
git clone https://github.com/OHDSI/Atlas
rename Atlas atlas
cd atlas
git checkout v2.12.0
cd ..
echo.
echo.
echo Done.
echo.
echo.

