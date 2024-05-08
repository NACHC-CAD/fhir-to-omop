DROP TABLE IF EXISTS @FullySpecifiedAchillesResultsSchema.achilles_analysis;
IF OBJECT_ID('@FullySpecifiedAchillesResultsSchema.achilles_analysis', 'U') IS NULL
CREATE TABLE @FullySpecifiedAchillesResultsSchema.achilles_analysis
(
	analysis_id int,
	distribution int,
	distributed_field varchar(255),
	analysis_name varchar(255),
	stratum_1_name varchar(255),
	stratum_2_name varchar(255),
	stratum_3_name varchar(255),
	stratum_4_name varchar(255),
	stratum_5_name varchar(255),
	is_default int,
	category varchar(255)
);

