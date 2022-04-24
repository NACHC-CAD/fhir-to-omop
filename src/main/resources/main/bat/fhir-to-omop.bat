@echo off
echo.
echo.
echo # ----------------------------------------
echo #
echo # running fhir to omop
echo #
echo # ----------------------------------------
echo.

set workingDir=%CD%

echo Working Directory: %workingDir%

java -jar fhir-to-omop.jar %1 %2

echo.
echo.
echo Done.
echo.
echo.
