use master;

drop login omop_test_user;

drop database if exists omop_test;

select 'database and login dropped';

create database omop_test;

use omop_test;

create login omop_test_user with password = 'foobar';

create user omop_test_user for login omop_test_user 
with default_schema = omop_test
;

exec sp_addrolemember N'db_accessadmin', N'omop_test_user';
exec sp_addrolemember N'db_backupoperator', N'omop_test_user';
exec sp_addrolemember N'db_datareader', N'omop_test_user';
exec sp_addrolemember N'db_datawriter', N'omop_test_user';
exec sp_addrolemember N'db_ddladmin', N'omop_test_user';
exec sp_addrolemember N'db_owner', N'omop_test_user';
exec sp_addrolemember N'db_securityadmin', N'omop_test_user';

select * from sys.sysusers where name = 'omop_test_user';

use omop_test;

create table foo (
	bar integer
);

select * from foo;
