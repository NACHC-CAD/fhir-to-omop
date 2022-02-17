drop login synthea_omop;

drop user synthea_omop;

create login synthea_omop
with password='sneeker';

create user synthea_omop for login synthea_omop
with default_schema = synthea_omop
;

exec sp_addrolemember N'db_datareader', N'synthea_omop';

exec sp_addrolemember N'db_datawriter', N'synthea_omop';

go

select * from sys.sysusers where name = 'synthea_omop';
