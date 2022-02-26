Authorization files (uids/pwds/tokens/etc) go here.  

THIS SHOULD BE THE ONLY FILE IN THIS DIRECTORY

To use fhir-to-omop tools add a file called auth.properties that looks something like the following:

bootstrapUrl=jdbc:sqlserver://localhost;databaseName=master;integratedSecurity=true;encrypt=false

url=jdbc:sqlserver://localhost:1433
uid=omop_test
pwd=foobar
syntheaDb=omop_test.dbo

