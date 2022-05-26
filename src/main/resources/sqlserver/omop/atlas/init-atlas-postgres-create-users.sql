-- * * *
--
-- sql script to initiate postgresql
-- 
-- * * *


-- Role: ohdsi_admin
DROP ROLE IF EXISTS ohdsi_admin;

CREATE ROLE ohdsi_admin WITH
  LOGIN
  NOSUPERUSER
  INHERIT
  NOCREATEDB
  NOCREATEROLE
  NOREPLICATION
  PASSWORD 'stripedbass';

-- Role: ohdsi_admin_user
DROP ROLE IF EXISTS ohdsi_admin_user;

CREATE ROLE ohdsi_admin_user WITH
  LOGIN
  SUPERUSER
  INHERIT
  CREATEDB
  CREATEROLE
  REPLICATION
  PASSWORD 'stripedbass';

-- Role: ohdsi_app
DROP ROLE IF EXISTS ohdsi_app;

CREATE ROLE ohdsi_app WITH
  LOGIN
  NOSUPERUSER
  INHERIT
  NOCREATEDB
  NOCREATEROLE
  NOREPLICATION
  PASSWORD 'stripedbass';

-- Role: ohdsi_app_user
DROP ROLE IF EXISTS ohdsi_app_user;

CREATE ROLE ohdsi_app_user WITH
  LOGIN
  NOSUPERUSER
  INHERIT
  NOCREATEDB
  NOCREATEROLE
  NOREPLICATION
  PASSWORD 'stripedbass';

GRANT ohdsi_app TO ohdsi_app_user;

COMMENT ON ROLE ohdsi_app_user IS 'Application user account for OHDSI applications';









