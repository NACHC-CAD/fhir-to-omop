INSERT INTO @webapiSchemaName.source (
    source_id, 
    source_name, 
    source_key, 
    source_connection, 
    source_dialect
) 
SELECT 
    @sourceId, 
    '@sourceName', 
    '@sourceKey', 
    '@sourceConnection',
    'spark'
;


