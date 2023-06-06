
DROP SCHEMA IF EXISTS <webApiSchema> CASCADE;

CREATE SCHEMA IF NOT EXISTS <webApiSchema>
    AUTHORIZATION <ohdsiAdminUid>;

COMMENT ON SCHEMA <webApiSchema>
    IS 'Schema containing tables to support WebAPI functionality';

GRANT USAGE ON SCHEMA <webApiSchema> TO PUBLIC;

GRANT ALL ON SCHEMA <webApiSchema> TO <ohdsiAdminUid>;

GRANT ALL ON SCHEMA <webApiSchema> TO <ohdsiAdminUserUid>;

GRANT USAGE ON SCHEMA <webApiSchema> TO <ohdsiAppUid>;

ALTER DEFAULT PRIVILEGES FOR ROLE postgres IN SCHEMA <webApiSchema>
GRANT ALL ON TABLES TO <ohdsiAdminUserUid>;

ALTER DEFAULT PRIVILEGES FOR ROLE postgres IN SCHEMA <webApiSchema>
GRANT INSERT, SELECT, UPDATE, DELETE, REFERENCES, TRIGGER ON TABLES TO <ohdsiAppUid>;

ALTER DEFAULT PRIVILEGES FOR ROLE postgres IN SCHEMA <webApiSchema>
GRANT USAGE, SELECT ON SEQUENCES TO <ohdsiAppUid>;

ALTER DEFAULT PRIVILEGES FOR ROLE postgres IN SCHEMA <webApiSchema>
GRANT EXECUTE ON FUNCTIONS TO <ohdsiAppUid>;

ALTER DEFAULT PRIVILEGES FOR ROLE postgres IN SCHEMA <webApiSchema>
GRANT USAGE ON TYPES TO <ohdsiAppUid>;


