use master;

DECLARE @dbName varchar(64) = 'omop_test';
DECLARE @uid varchar(64) = 'omop_test_user';
DECLARE @pwd varchar(64) = 'foobar';

drop login @uid;

drop database if exists @dbName;

create database @dbName;

use @dbName;

create login @uid with password = '@pwd';

create user @uid for login @uid with default_schema = @dbName;

exec sp_addrolemember N'db_accessadmin', N'@uid';
exec sp_addrolemember N'db_backupoperator', N'@uid';
exec sp_addrolemember N'db_datareader', N'@uid';
exec sp_addrolemember N'db_datawriter', N'@uid';
exec sp_addrolemember N'db_ddladmin', N'@uid';
exec sp_addrolemember N'db_owner', N'@uid';
exec sp_addrolemember N'db_securityadmin', N'@uid';

select * from sys.sysusers where name = '@uid';

use @dbName;

create table foo (
	bar integer
);

select * from foo;

drop table foo;

select * from sys.server_principals sp where sp.name = '@uid'



