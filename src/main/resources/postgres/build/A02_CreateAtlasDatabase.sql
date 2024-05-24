CREATE DATABASE "<ohdsiDbName>"
    WITH
    OWNER = <ohdsiDbOwner>
    ENCODING = 'UTF8'
    LC_COLLATE = 'English_United States.1252'
    LC_CTYPE = 'English_United States.1252'
    TABLESPACE = pg_default
    TEMPLATE = template0
    CONNECTION LIMIT = -1;

COMMENT ON DATABASE "<ohdsiDbName>"
    IS '<ohdsiDbName> WebAPI database';

GRANT ALL ON DATABASE "<ohdsiDbName>" TO <ohdsiAdminUid>;

GRANT TEMPORARY ON DATABASE "<ohdsiDbName>" TO <ohdsiAppUid>;

GRANT TEMPORARY, CONNECT ON DATABASE "<ohdsiDbName>" TO PUBLIC;    

