
truncate table <ohdsiDbName>.care_site;
insert into <ohdsiDbName>.care_site (
	care_site_id,
	care_site_name,
	place_of_service_concept_id,
	location_id,
	care_site_source_value,
	place_of_service_source_value
) values (
	1,
	'Not Available',
	null,
	1,
	null,
	null
);

truncate table <ohdsiDbName>.provider;
insert into <ohdsiDbName>.provider (
	provider_id,
	provider_name
) values (
	1,
	'Not Available'
);

truncate table <ohdsiDbName>.location;
insert into <ohdsiDbName>.location (
	location_id,
	address_1,
	city,
	state,
	zip,
	county,
	location_source_value,
	country_concept_id,
	country_source_value
) values (
	1,
	'Location Unknow',
	'Unknown City',
	'??',
	'00000',
	'Unknown County',
	'Unknown',
	42046186,
	'USA'
);

