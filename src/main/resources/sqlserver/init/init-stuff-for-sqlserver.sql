use master;

drop login synthea_omop;

drop user synthea_omop;

create login synthea_omop
with password='<PASSWORD>';

go

drop database if exists synthea_omop;

create database synthea_omop;

use synthea_omop;

create user synthea_omop for login synthea_omop
with default_schema = synthea_omop
;

exec sp_addrolemember N'db_datareader', N'synthea_omop';

exec sp_addrolemember N'db_datawriter', N'synthea_omop';

select * from sys.sysusers where name = 'synthea_omop';
