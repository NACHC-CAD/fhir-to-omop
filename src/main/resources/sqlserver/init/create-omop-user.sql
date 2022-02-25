
create login omop_test_login with password = 'foobar';

create user omop_test_user for login omop_test_login with default_schema = omop_test;

exec sp_addrolemember N'db_datareader', N'omop_test_user';

exec sp_addrolemember N'db_datawriter', N'omop_test_user';

select * from sys.sysusers where name = 'omop_test_user';
