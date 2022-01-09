use synthea_omop;

drop user if exists 'synthea';

create user 'synthea'@'%' identified by 'sneeker';

grant all privileges on synthea_omop to 'synthea'@'%';

flush privileges;



