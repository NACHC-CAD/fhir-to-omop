-- * * *
--
-- sql script to initiate postgresql
-- 
-- * * *


-- Role: <ohdsiAdminUid>
DROP ROLE IF EXISTS <ohdsiAdminUid>;

CREATE ROLE <ohdsiAdminUid> WITH
  LOGIN
  NOSUPERUSER
  INHERIT
  NOCREATEDB
  NOCREATEROLE
  NOREPLICATION
  PASSWORD '<ohdsiAdminPwd>';

-- Role: ohdsi_admin_user
DROP ROLE IF EXISTS <ohdsiAdminUserUid>;

CREATE ROLE <ohdsiAdminUserUid> WITH
  LOGIN
  SUPERUSER
  INHERIT
  CREATEDB
  CREATEROLE
  REPLICATION
  PASSWORD '<ohdsiAdminUserPwd>';

-- Role: ohdsi_app
DROP ROLE IF EXISTS <ohdsiAppUid>;

CREATE ROLE <ohdsiAppUid> WITH
  LOGIN
  NOSUPERUSER
  INHERIT
  NOCREATEDB
  NOCREATEROLE
  NOREPLICATION
  PASSWORD '<ohdsiAppPwd>';

-- Role: ohdsi_app_user
DROP ROLE IF EXISTS <ohdsiAppUserUid>;

CREATE ROLE <ohdsiAppUserUid> WITH
  LOGIN
  NOSUPERUSER
  INHERIT
  NOCREATEDB
  NOCREATEROLE
  NOREPLICATION
  PASSWORD '<ohdsiAppUserPwd>';

GRANT ohdsi_app TO ohdsi_app_user;

COMMENT ON ROLE <ohdsiAppUserUid> IS 'Application user account for OHDSI applications';









