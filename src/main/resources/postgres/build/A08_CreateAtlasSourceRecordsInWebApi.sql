INSERT INTO webapi.source (source_id, source_name, source_key, source_connection, source_dialect) 
SELECT nextval('webapi.source_sequence'), '<atlasDataSourceName>', '<atlasDataSourceKey>', '<atlasCdmUrl>', 'postgresql';

INSERT INTO webapi.source_daimon (source_daimon_id, source_id, daimon_type, table_qualifier, priority) 
SELECT nextval('webapi.source_daimon_sequence'), source_id, 0, '<atlasCdm>', 0
FROM webapi.source
WHERE source_key = '<atlasDataSourceKey>'
;

INSERT INTO webapi.source_daimon (source_daimon_id, source_id, daimon_type, table_qualifier, priority) 
SELECT nextval('webapi.source_daimon_sequence'), source_id, 1, '<atlasCdm>', 10
FROM webapi.source
WHERE source_key = '<atlasDataSourceKey>'
;

INSERT INTO webapi.source_daimon (source_daimon_id, source_id, daimon_type, table_qualifier, priority) 
SELECT nextval('webapi.source_daimon_sequence'), source_id, 2, '<atlasResults>', 1
FROM webapi.source
WHERE source_key = '<atlasDataSourceKey>'
;

INSERT INTO webapi.source_daimon (source_daimon_id, source_id, daimon_type, table_qualifier, priority) 
SELECT nextval('webapi.source_daimon_sequence'), source_id, 5, '<atlasTemp>', 0
FROM webapi.source
WHERE source_key = '<atlasDataSourceKey>'
;

