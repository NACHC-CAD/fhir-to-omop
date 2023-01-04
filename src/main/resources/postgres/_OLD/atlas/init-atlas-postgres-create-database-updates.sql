COMMENT ON DATABASE "<ohdsiDbName>"
    IS '<ohdsiDbName> WebAPI database';

GRANT ALL ON DATABASE "<ohdsiDbName>" TO <ohdsiAdminUid>;

GRANT TEMPORARY ON DATABASE "<ohdsiDbName>" TO <ohdsiAppUid>;

GRANT TEMPORARY, CONNECT ON DATABASE "<ohdsiDbName>" TO PUBLIC;