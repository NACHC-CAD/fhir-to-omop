use ohdsi;

EXEC sp_MSforeachtable "ALTER TABLE ? NOCHECK CONSTRAINT all";

drop table if exists cohort_definition;

drop table if exists cohort;


SELECT 
    'ALTER TABLE [' +  OBJECT_SCHEMA_NAME(parent_object_id) +
    '].[' + OBJECT_NAME(parent_object_id) + 
    '] DROP CONSTRAINT [' + name + ']'
FROM sys.foreign_keys
WHERE referenced_object_id = object_id('cohort_definition')

SELECT * 
    FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS
    WHERE lower(CONSTRAINT_NAME) like '%cohort%' 
	
