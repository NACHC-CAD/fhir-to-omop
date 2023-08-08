INSERT INTO @webapiSchemaName.source (
    source_id, 
    source_name, 
    source_key, 
    source_connection, 
    source_dialect,
    is_cache_enabled
) 
SELECT 
    @sourceId, 
    '@sourceName', 
    '@sourceKey', 
    '@sourceConnection',
    'spark',
    true
;


