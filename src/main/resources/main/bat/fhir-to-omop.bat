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

java -jar  -Dosgi.requiredJavaVersion=1.8 -Dosgi.instance.area.default=@user.home/eclipse-workspace -XX:+UseG1GC -XX:+UseStringDeduplication -Dosgi.requiredJavaVersion=11 -Dosgi.dataAreaRequiresExplicitInit=true -Xms1g -Xmx16g fhir-to-omop.jar %1 %2

echo.
echo.
echo Done.
echo.
echo.
git