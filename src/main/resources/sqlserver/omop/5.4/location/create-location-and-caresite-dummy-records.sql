truncate table care_site;
insert into care_site (
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

truncate table provider;
insert into provider (
	provider_id,
	provider_name
) values (
	1,
	'Not Available'
);

truncate table location;
insert into location (
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


