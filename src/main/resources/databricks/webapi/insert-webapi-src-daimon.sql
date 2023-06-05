
--
-- insert three source_daimon records
-- 

-- cdm database
INSERT INTO @webApiSchemaName.source_daimon (source_daimon_id, source_id, daimon_type, table_qualifier, priority) 
SELECT @sourceDaimonId01, @sourceId, 0, '@cdmSchemaName', 0
;

-- vocab database
INSERT INTO @webApiSchemaName.source_daimon (source_daimon_id, source_id, daimon_type, table_qualifier, priority) 
SELECT @sourceDaimonId02, @sourceId, 1, '@vocabSchemaName', 10
;

-- achillies results database
INSERT INTO @webApiSchemaName.source_daimon (source_daimon_id, source_id, daimon_type, table_qualifier, priority) 
SELECT @sourceDaimonId03, @sourceId, 2, '@achillesResultsSchemaName', 0
;


