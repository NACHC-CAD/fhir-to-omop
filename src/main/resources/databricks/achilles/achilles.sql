/* * * *
 
This file was created by running the Achilles R-Script with the sqlonly flag set to true.  
  
# ---
# 
# Script to run Achilles
# Run the install (once) and then run this script to run achilles.
#
# ---

sink(file="./SINK.TXT")
library(SqlRender)
library(Achilles)

# check pkgbuild
pkgbuild::check_build_tools()

# test that SqlRenderer was install and works
translate("SELECT TOP 10 * FROM person;", "spark")

dbms <- "spark"
user <- "token" 
password <- "<ADD_TOKEN_HERE>" 
server <- "nachc-databricks.cloud.databricks.com" 
port <- "443"
pathToDriver <- "C:\\_YES\\databases\\databricks\\drivers\\"  
extraSettings <- ""

cdmVersion <- "5.3" 
cdmDatabaseSchema <- "<CDM_DATABASE_NAME>"
resultsDatabaseSchema <- "<ACHILLES_RESULTS_DATABASE_NAME>"

connectionDetails <- DatabaseConnector::createConnectionDetails(
  dbms = dbms, 
  user = user, 
  password = password, 
  server = server, 
  port = port, 
  pathToDriver = pathToDriver,
  extraSettings = extraSettings
)


print("Running Achilles")
Achilles::achilles(
  verboseMode = TRUE,
  sqlOnly = TRUE,
  cdmVersion = cdmVersion, 
  connectionDetails = connectionDetails,
  cdmDatabaseSchema = cdmDatabaseSchema,
  resultsDatabaseSchema = resultsDatabaseSchema,
  outputFolder = "C:\\temp\\achilles"
)
print("Done running Achilles")
sink(file=NULL)
print("Done running Achilles")


* * * */

--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_0
USING DELTA
AS
SELECT
0 as analysis_id, CAST('' AS STRING) as stratum_1, CAST('1.7' AS STRING) as stratum_2, 
CAST(CURRENT_DATE AS STRING) as stratum_3,
cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
COUNT(distinct person_id) as count_value
FROM
<CDM_DATABASE_NAME>.person;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_0
 ZORDER BY stratum_1;
--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_0
USING DELTA
AS
SELECT
0 as analysis_id, CAST('' AS STRING) as stratum_1, 
cast(null as STRING) as stratum_2, cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
COUNT(distinct person_id) as count_value, 
 cast(null as float) as min_value,
 cast(null as float) as max_value,
 cast(null as float) as avg_value,
 cast(null as float) as stdev_value,
 cast(null as float) as median_value,
 cast(null as float) as p10_value,
 cast(null as float) as p25_value,
 cast(null as float) as p75_value,
 cast(null as float) as p90_value
FROM
<CDM_DATABASE_NAME>.person;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_0
 ZORDER BY stratum_1;

CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1
USING DELTA
AS
SELECT
1 as analysis_id, 
cast(null as STRING) as stratum_1, cast(null as STRING) as stratum_2, cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
COUNT(distinct person_id) as count_value
FROM
<CDM_DATABASE_NAME>.person;

--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_2
USING DELTA
AS
SELECT
2 as analysis_id, 
CAST(gender_concept_id AS STRING) as stratum_1, 
cast(null as STRING) as stratum_2, cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
COUNT(distinct person_id) as count_value
FROM
<CDM_DATABASE_NAME>.person
group by GENDER_CONCEPT_ID;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_2
 ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_3
USING DELTA
AS
SELECT
3 as analysis_id, CAST(year_of_birth AS STRING) as stratum_1, 
cast(null as STRING) as stratum_2, cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
COUNT(distinct person_id) as count_value
FROM
<CDM_DATABASE_NAME>.person
group by YEAR_OF_BIRTH;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_3
 ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_4
USING DELTA
AS
SELECT
4 as analysis_id, CAST(RACE_CONCEPT_ID AS STRING) as stratum_1, 
cast(null as STRING) as stratum_2, cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
COUNT(distinct person_id) as count_value
FROM
<CDM_DATABASE_NAME>.person
group by RACE_CONCEPT_ID;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_4
 ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_5
USING DELTA
AS
SELECT
5 as analysis_id, CAST(ETHNICITY_CONCEPT_ID AS STRING) as stratum_1, 
cast(null as STRING) as stratum_2, cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
COUNT(distinct person_id) as count_value
FROM
<CDM_DATABASE_NAME>.person
group by ETHNICITY_CONCEPT_ID;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_5
 ZORDER BY stratum_1;

CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_7
USING DELTA
AS
SELECT
7 as analysis_id, 
cast(null as STRING) as stratum_1, cast(null as STRING) as stratum_2, cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
COUNT(p1.person_id) as count_value
FROM
<CDM_DATABASE_NAME>.person p1
 left join <CDM_DATABASE_NAME>.provider pr1
 on p1.provider_id = pr1.provider_id
where p1.provider_id is not null
 and pr1.provider_id is null;

CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_8
USING DELTA
AS
SELECT
8 as analysis_id, 
cast(null as STRING) as stratum_1, cast(null as STRING) as stratum_2, cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
COUNT(p1.person_id) as count_value
FROM
<CDM_DATABASE_NAME>.person p1
 left join <CDM_DATABASE_NAME>.location l1
 on p1.location_id = l1.location_id
where p1.location_id is not null
 and l1.location_id is null;

CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_9
USING DELTA
AS
SELECT
9 as analysis_id, 
cast(null as STRING) as stratum_1, cast(null as STRING) as stratum_2, cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
COUNT(p1.person_id) as count_value
FROM
<CDM_DATABASE_NAME>.person p1
 left join <CDM_DATABASE_NAME>.care_site cs1
 on p1.care_site_id = cs1.care_site_id
where p1.care_site_id is not null
 and cs1.care_site_id is null;

--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_10
USING DELTA
AS
SELECT
10 as analysis_id, CAST(year_of_birth AS STRING) as stratum_1,
 CAST(gender_concept_id AS STRING) as stratum_2,
 cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
 COUNT(distinct person_id) as count_value
FROM
<CDM_DATABASE_NAME>.person
group by YEAR_OF_BIRTH, gender_concept_id;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_10
 ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_11
USING DELTA
AS
SELECT
11 as analysis_id, CAST(P.year_of_birth AS STRING) as stratum_1,
 CAST(P.gender_concept_id AS STRING) as stratum_2,
 cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
 COUNT(distinct P.person_id) as count_value
FROM
<CDM_DATABASE_NAME>.person P
where not exists
(
 select 1
 from <CDM_DATABASE_NAME>.death D
 where P.person_id = D.person_id
)
group by P.YEAR_OF_BIRTH, P.gender_concept_id;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_11
 ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_12
USING DELTA
AS
SELECT
12 as analysis_id, CAST(RACE_CONCEPT_ID AS STRING) as stratum_1, CAST(ETHNICITY_CONCEPT_ID AS STRING) as stratum_2, 
cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
COUNT(distinct person_id) as count_value
FROM
<CDM_DATABASE_NAME>.person
group by RACE_CONCEPT_ID,ETHNICITY_CONCEPT_ID;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_12
 ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(stratum_1)
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_101
USING DELTA
AS
WITH rawData  AS (
 select
 year(op1.index_date) - p1.YEAR_OF_BIRTH as stratum_1,
 COUNT(p1.person_id) as count_value
 from <CDM_DATABASE_NAME>.person p1
 inner join (select person_id, MIN(observation_period_start_date) as index_date from <CDM_DATABASE_NAME>.observation_period group by PERSON_ID) op1
 on p1.PERSON_ID = op1.PERSON_ID
 group by year(op1.index_date) - p1.YEAR_OF_BIRTH
)
 SELECT
101 as analysis_id,
 CAST(stratum_1 AS STRING) as stratum_1,
 cast(null as STRING) as stratum_2,
 cast(null as STRING) as stratum_3,
 cast(null as STRING) as stratum_4,
 cast(null as STRING) as stratum_5,
 count_value
FROM
rawData;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_101
 ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(stratum_1)
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_102
USING DELTA
AS
WITH rawData  AS (
 select
 p1.gender_concept_id as stratum_1,
 year(op1.index_date) - p1.YEAR_OF_BIRTH as stratum_2,
 COUNT(p1.person_id) as count_value
 from <CDM_DATABASE_NAME>.person p1
 inner join (select person_id, MIN(observation_period_start_date) as index_date from <CDM_DATABASE_NAME>.observation_period group by PERSON_ID) op1
 on p1.PERSON_ID = op1.PERSON_ID
 group by p1.gender_concept_id, year(op1.index_date) - p1.YEAR_OF_BIRTH)
 SELECT
102 as analysis_id,
 CAST(stratum_1 AS STRING) as stratum_1,
 cast(stratum_2 as STRING) as stratum_2,
 cast(null as STRING) as stratum_3,
 cast(null as STRING) as stratum_4,
 cast(null as STRING) as stratum_5,
 count_value
FROM
rawData;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_102
 ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(count_value)
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_103
USING DELTA
AS
WITH rawData (person_id, age_value)  AS (
select p.person_id, 
 MIN(YEAR(observation_period_start_date)) - P.YEAR_OF_BIRTH as age_value
 from <CDM_DATABASE_NAME>.person p
 JOIN <CDM_DATABASE_NAME>.observation_period op on p.person_id = op.person_id
 group by p.person_id, p.year_of_birth
),
overallStats (avg_value, stdev_value, min_value, max_value, total) as
(
 select CAST(avg(1.0 * age_value) AS FLOAT) as avg_value,
 CAST(STDDEV(age_value) AS FLOAT) as stdev_value,
 min(age_value) as min_value,
 max(age_value) as max_value,
 COUNT(*) as total
 FROM rawData
),
ageStats (age_value, total, rn) as
(
 select age_value, COUNT(*) as total, row_number() over (order by age_value) as rn
 from rawData
 group by age_value
),
ageStatsPrior (age_value, total, accumulated) as
(
 select s.age_value, s.total, sum(p.total) as accumulated
 from ageStats s
 join ageStats p on p.rn <= s.rn
 group by s.age_value, s.total, s.rn
),
tempResults as
(
 select 103 as analysis_id,
 o.total as count_value,
 o.min_value,
 o.max_value,
 o.avg_value,
 o.stdev_value,
 MIN(case when p.accumulated >= .50 * o.total then age_value end) as median_value,
 MIN(case when p.accumulated >= .10 * o.total then age_value end) as p10_value,
 MIN(case when p.accumulated >= .25 * o.total then age_value end) as p25_value,
 MIN(case when p.accumulated >= .75 * o.total then age_value end) as p75_value,
 MIN(case when p.accumulated >= .90 * o.total then age_value end) as p90_value
 --INTO #tempResults
 from ageStatsPrior p
 CROSS JOIN overallStats o
 GROUP BY o.total, o.min_value, o.max_value, o.avg_value, o.stdev_value
)
 SELECT
analysis_id, 
cast(null as STRING) as stratum_1, cast(null as STRING) as stratum_2, cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5, 
count_value, min_value, max_value, avg_value, stdev_value, median_value, p10_value, p25_value, p75_value, p90_value
FROM
tempResults;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_103
 ZORDER BY count_value;

--HINT DISTRIBUTE_ON_KEY(stratum_1)
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_104
USING DELTA
AS
WITH rawData (gender_concept_id, age_value)  AS (
 select p.gender_concept_id, MIN(YEAR(observation_period_start_date)) - P.YEAR_OF_BIRTH as age_value
 from <CDM_DATABASE_NAME>.person p
 JOIN <CDM_DATABASE_NAME>.observation_period op on p.person_id = op.person_id
 group by p.person_id,p.gender_concept_id, p.year_of_birth
),
overallStats (gender_concept_id, avg_value, stdev_value, min_value, max_value, total) as
(
 select gender_concept_id,
 CAST(avg(1.0 * age_value) AS FLOAT) as avg_value,
 CAST(STDDEV(age_value) AS FLOAT) as stdev_value,
 min(age_value) as min_value,
 max(age_value) as max_value,
 COUNT(*) as total
 FROM rawData
 group by gender_concept_id
),
ageStats (gender_concept_id, age_value, total, rn) as
(
 select gender_concept_id, age_value, COUNT(*) as total, row_number() over (order by age_value) as rn
 FROM rawData
 group by gender_concept_id, age_value
),
ageStatsPrior (gender_concept_id, age_value, total, accumulated) as
(
 select s.gender_concept_id, s.age_value, s.total, sum(p.total) as accumulated
 from ageStats s
 join ageStats p on s.gender_concept_id = p.gender_concept_id and p.rn <= s.rn
 group by s.gender_concept_id, s.age_value, s.total, s.rn
)
 SELECT
104 as analysis_id,
 CAST(o.gender_concept_id AS STRING) as stratum_1,
 o.total as count_value,
 o.min_value,
 o.max_value,
 o.avg_value,
 o.stdev_value,
 MIN(case when p.accumulated >= .50 * o.total then age_value end) as median_value,
 MIN(case when p.accumulated >= .10 * o.total then age_value end) as p10_value,
 MIN(case when p.accumulated >= .25 * o.total then age_value end) as p25_value,
 MIN(case when p.accumulated >= .75 * o.total then age_value end) as p75_value,
 MIN(case when p.accumulated >= .90 * o.total then age_value end) as p90_value
FROM
ageStatsPrior p
join overallStats o on p.gender_concept_id = o.gender_concept_id
GROUP BY o.gender_concept_id, o.total, o.min_value, o.max_value, o.avg_value, o.stdev_value
;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_104
 ZORDER BY stratum_1;
--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_104
USING DELTA
AS
SELECT
analysis_id, stratum_1, 
cast(null as STRING) as stratum_2, cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
count_value, min_value, max_value, avg_value, stdev_value, median_value, p10_value, p25_value, p75_value, p90_value
FROM
<ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_104
;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_104
 ZORDER BY stratum_1;
truncate table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_104;
drop table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_104;

--HINT DISTRIBUTE_ON_KEY(count_value) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempObs_105
USING DELTA
AS
SELECT
count_value, rn 
FROM
(
 select datediff(op.observation_period_end_date,op.observation_period_start_date) as count_value,
 ROW_NUMBER() over (PARTITION by op.person_id order by op.observation_period_start_date asc) as rn
 from <CDM_DATABASE_NAME>.observation_period op
) A
where rn = 1;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempObs_105
 ZORDER BY count_value;
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1ostatsView_105
USING DELTA
AS
SELECT
count_value, COUNT(*) as total, row_number() over (order by count_value) as rn
FROM
<ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempObs_105
group by count_value;
--HINT DISTRIBUTE_ON_KEY(count_value)
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_105
USING DELTA
AS
WITH overallStats (avg_value, stdev_value, min_value, max_value, total)  AS (
 select CAST(avg(1.0 * count_value) AS FLOAT) as avg_value,
 CAST(STDDEV(count_value) AS FLOAT) as stdev_value,
 min(count_value) as min_value,
 max(count_value) as max_value,
 COUNT(*) as total
 from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempObs_105
),
priorStats (count_value, total, accumulated) as
(
 select s.count_value, s.total, sum(p.total) as accumulated
 from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1ostatsView_105 s
 join <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1ostatsView_105 p on p.rn <= s.rn
 group by s.count_value, s.total, s.rn
)
 SELECT
105 as analysis_id,
 o.total as count_value,
 o.min_value,
 o.max_value,
 o.avg_value,
 o.stdev_value,
 MIN(case when p.accumulated >= .50 * o.total then count_value end) as median_value,
 MIN(case when p.accumulated >= .10 * o.total then count_value end) as p10_value,
 MIN(case when p.accumulated >= .25 * o.total then count_value end) as p25_value,
 MIN(case when p.accumulated >= .75 * o.total then count_value end) as p75_value,
 MIN(case when p.accumulated >= .90 * o.total then count_value end) as p90_value
FROM
priorStats p
CROSS JOIN overallStats o
GROUP BY o.total, o.min_value, o.max_value, o.avg_value, o.stdev_value
;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_105
 ZORDER BY count_value;
--HINT DISTRIBUTE_ON_KEY(count_value) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_105
USING DELTA
AS
SELECT
analysis_id,
cast(null as STRING) as stratum_1, cast(null as STRING) as stratum_2, cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5, count_value,
min_value, max_value, avg_value, stdev_value, median_value, p10_value, p25_value, p75_value, p90_value
FROM
<ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_105
;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_105
 ZORDER BY count_value;
truncate table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempObs_105;
drop table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempObs_105;
truncate table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1ostatsView_105;
drop table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1ostatsView_105;
truncate table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_105;
drop table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_105;

--HINT DISTRIBUTE_ON_KEY(gender_concept_id) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1orawData_106
USING DELTA
AS
SELECT
p.gender_concept_id, op.count_value
FROM
(
 select person_id, datediff(op.observation_period_end_date,op.observation_period_start_date) as count_value,
 ROW_NUMBER() over (PARTITION by op.person_id order by op.observation_period_start_date asc) as rn
 from <CDM_DATABASE_NAME>.observation_period op
) op
JOIN <CDM_DATABASE_NAME>.person p on op.person_id = p.person_id
where op.rn = 1
;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1orawData_106
 ZORDER BY gender_concept_id;
--HINT DISTRIBUTE_ON_KEY(gender_concept_id)
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_106
USING DELTA
AS
WITH overallStats (gender_concept_id, avg_value, stdev_value, min_value, max_value, total)  AS (
 select gender_concept_id,
 CAST(avg(1.0 * count_value) AS FLOAT) as avg_value,
 CAST(STDDEV(count_value) AS FLOAT) as stdev_value,
 min(count_value) as min_value,
 max(count_value) as max_value,
 COUNT(*) as total
 FROM <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1orawData_106
 group by gender_concept_id
),
statsView (gender_concept_id, count_value, total, rn) as
(
 select gender_concept_id, count_value, COUNT(*) as total, row_number() over (order by count_value) as rn
 FROM <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1orawData_106
 group by gender_concept_id, count_value
),
priorStats (gender_concept_id,count_value, total, accumulated) as
(
 select s.gender_concept_id, s.count_value, s.total, sum(p.total) as accumulated
 from statsView s
 join statsView p on s.gender_concept_id = p.gender_concept_id and p.rn <= s.rn
 group by s.gender_concept_id, s.count_value, s.total, s.rn
)
 SELECT
106 as analysis_id,
 CAST(o.gender_concept_id AS STRING) as gender_concept_id,
 o.total as count_value,
 o.min_value,
 o.max_value,
 o.avg_value,
 o.stdev_value,
 MIN(case when p.accumulated >= .50 * o.total then count_value end) as median_value,
 MIN(case when p.accumulated >= .10 * o.total then count_value end) as p10_value,
 MIN(case when p.accumulated >= .25 * o.total then count_value end) as p25_value,
 MIN(case when p.accumulated >= .75 * o.total then count_value end) as p75_value,
 MIN(case when p.accumulated >= .90 * o.total then count_value end) as p90_value
FROM
priorStats p
join overallStats o on p.gender_concept_id = o.gender_concept_id
GROUP BY o.gender_concept_id, o.total, o.min_value, o.max_value, o.avg_value, o.stdev_value
;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_106
 ZORDER BY gender_concept_id;
--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_106
USING DELTA
AS
SELECT
analysis_id, gender_concept_id as stratum_1, 
cast(null as STRING) as stratum_2, cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
count_value, min_value, max_value, avg_value, stdev_value, median_value, p10_value, p25_value, p75_value, p90_value
FROM
<ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_106
;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_106
 ZORDER BY stratum_1;
truncate table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1orawData_106;
drop table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1orawData_106;
truncate table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_106;
drop table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_106;

--HINT DISTRIBUTE_ON_KEY(age_decile)
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_107
USING DELTA
AS
WITH rawData (age_decile, count_value)  AS (
 select floor((year(op.OBSERVATION_PERIOD_START_DATE) - p.YEAR_OF_BIRTH)/10) as age_decile,
 datediff(op.observation_period_end_date,op.observation_period_start_date) as count_value
 FROM
 (
 select person_id, 
 op.observation_period_start_date,
 op.observation_period_end_date,
 ROW_NUMBER() over (PARTITION by op.person_id order by op.observation_period_start_date asc) as rn
 from <CDM_DATABASE_NAME>.observation_period op
 ) op
 JOIN <CDM_DATABASE_NAME>.person p on op.person_id = p.person_id
 where op.rn = 1
),
overallStats (age_decile, avg_value, stdev_value, min_value, max_value, total) as
(
 select age_decile,
 CAST(avg(1.0 * count_value) AS FLOAT) as avg_value,
 CAST(STDDEV(count_value) AS FLOAT) as stdev_value,
 min(count_value) as min_value,
 max(count_value) as max_value,
 COUNT(*) as total
 from rawData
 group by age_decile
),
statsView (age_decile, count_value, total, rn) as
(
 select age_decile,
 count_value, 
 COUNT(*) as total, 
 row_number() over (order by count_value) as rn
 FROM rawData
 group by age_decile, count_value
),
priorStats (age_decile,count_value, total, accumulated) as
(
 select s.age_decile, s.count_value, s.total, sum(p.total) as accumulated
 from statsView s
 join statsView p on s.age_decile = p.age_decile and p.rn <= s.rn
 group by s.age_decile, s.count_value, s.total, s.rn
)
 SELECT
107 as analysis_id,
 CAST(o.age_decile AS STRING) as age_decile,
 o.total as count_value,
 o.min_value,
 o.max_value,
 o.avg_value,
 o.stdev_value,
 MIN(case when p.accumulated >= .50 * o.total then count_value else o.max_value end) as median_value,
 MIN(case when p.accumulated >= .10 * o.total then count_value else o.max_value end) as p10_value,
 MIN(case when p.accumulated >= .25 * o.total then count_value else o.max_value end) as p25_value,
 MIN(case when p.accumulated >= .75 * o.total then count_value else o.max_value end) as p75_value,
 MIN(case when p.accumulated >= .90 * o.total then count_value else o.max_value end) as p90_value
FROM
priorStats p
join overallStats o on p.age_decile = o.age_decile
GROUP BY o.age_decile, o.total, o.min_value, o.max_value, o.avg_value, o.stdev_value
;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_107
 ZORDER BY age_decile;
--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_107
USING DELTA
AS
SELECT
analysis_id, age_decile as stratum_1, 
cast(null as STRING) as stratum_2, cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
count_value, min_value, max_value, avg_value, stdev_value, median_value, p10_value, p25_value, p75_value, p90_value
FROM
<ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_107
;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_107
 ZORDER BY stratum_1;
truncate table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_107;
drop table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_107;

--HINT DISTRIBUTE_ON_KEY(stratum_1)
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_108
USING DELTA
AS
WITH rawData  AS (
 select
 floor(datediff(op1.observation_period_end_date,op1.observation_period_start_date)/30) as stratum_1,
 COUNT(distinct p1.person_id) as count_value
 from <CDM_DATABASE_NAME>.person p1
 inner join
 (select person_id,
 OBSERVATION_PERIOD_START_DATE,
 OBSERVATION_PERIOD_END_DATE,
 ROW_NUMBER() over (PARTITION by person_id order by observation_period_start_date asc) as rn1
 from <CDM_DATABASE_NAME>.observation_period
 ) op1
 on p1.PERSON_ID = op1.PERSON_ID
 where op1.rn1 = 1
 group by floor(datediff(op1.observation_period_end_date,op1.observation_period_start_date)/30)
)
 SELECT
108 as analysis_id,
 CAST(stratum_1 AS STRING) as stratum_1,
 cast(null as STRING) as stratum_2,
 cast(null as STRING) as stratum_3,
 cast(null as STRING) as stratum_4,
 cast(null as STRING) as stratum_5,
 count_value
FROM
rawData;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_108
 ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(stratum_1)
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_109
USING DELTA
AS
WITH century   AS (SELECT  CAST('19' as STRING) num union select '20' num), 
tens as (select '0' num union select '1' num union select '2' num union select '3' num union select '4' num union select '5' num union select '6' num union select '7' num union select '8' num union select '9' num),
ones as (select '0' num union select '1' num union select '2' num union select '3' num union select '4' num union select '5' num union select '6' num union select '7' num union select '8' num union select '9' num),
months as (select '01' as num union select '02' num union select '03' num union select '04' num union select '05' num union select '06' num union select '07' num union select '08' num union select '09' num union select '10' num union select '11' num union select '12' num),
date_keys as (select concat(century.num, tens.num, ones.num,months.num) obs_month from century cross join tens cross join ones cross join months),
-- From date_keys, we just need each year and the first and last day of each year
ymd as (
select cast(SUBSTR(obs_month,4) as integer) as obs_year,
 min(cast(SUBSTR(SUBSTR(obs_month,6),-2) as integer)) as month_start,
 1 as day_start,
 max(cast(SUBSTR(SUBSTR(obs_month,6),-2) as integer)) as month_end,
 31 as day_end
 from date_keys
 where SUBSTR(SUBSTR(obs_month,6),-2) in ('01','12')
 group by SUBSTR(obs_month,4)
),
-- This gives us each year and the first and last day of the year 
year_ranges as (
select obs_year,
 to_date(cast(obs_year as string) || '-' || cast(month_start as string) || '-' || cast(day_start as string)) obs_year_start,
 to_date(cast(obs_year as string) || '-' || cast(month_end as string) || '-' || cast(day_end as string)) obs_year_end
 from ymd
 where obs_year >= (select min(year(observation_period_start_date)) from <CDM_DATABASE_NAME>.observation_period)
 and obs_year <= (select max(year(observation_period_start_date)) from <CDM_DATABASE_NAME>.observation_period)
) 
 SELECT
109 AS analysis_id, 
 CAST(yr.obs_year AS STRING) AS stratum_1,
 CAST(NULL AS STRING) AS stratum_2, 
 CAST(NULL AS STRING) AS stratum_3, 
 CAST(NULL AS STRING) AS stratum_4, 
 CAST(NULL AS STRING) AS stratum_5,
 COUNT(DISTINCT op.person_id) AS count_value
FROM
<CDM_DATABASE_NAME>.observation_period op
CROSS JOIN 
 year_ranges yr
WHERE
 op.observation_period_start_date <= yr.obs_year_start
AND
 op.observation_period_end_date >= yr.obs_year_end
GROUP BY 
 yr.obs_year;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_109
 ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_110
USING DELTA
AS
SELECT
110 as analysis_id, 
 CAST(t1.obs_month AS STRING) as stratum_1,
 cast(null as STRING) as stratum_2, cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
 COUNT(distinct op1.PERSON_ID) as count_value
FROM
<CDM_DATABASE_NAME>.observation_period op1
join 
(
 SELECT DISTINCT 
 YEAR(observation_period_start_date)*100 + MONTH(observation_period_start_date) AS obs_month,
 to_date(cast(YEAR(observation_period_start_date) as string) || '-' || cast(MONTH(observation_period_start_date) as string) || '-' || cast(1 as string))
 AS obs_month_start,
 last_day(observation_period_start_date) AS obs_month_end
 FROM <CDM_DATABASE_NAME>.observation_period
) t1 on op1.observation_period_start_date <= t1.obs_month_start
 and op1.observation_period_end_date >= t1.obs_month_end
group by t1.obs_month;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_110
 ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(stratum_1)
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_111
USING DELTA
AS
WITH rawData  AS (
 select
 YEAR(observation_period_start_date)*100 + month(OBSERVATION_PERIOD_START_DATE) as stratum_1,
 COUNT(distinct op1.PERSON_ID) as count_value
 from
 <CDM_DATABASE_NAME>.observation_period op1
 group by YEAR(observation_period_start_date)*100 + month(OBSERVATION_PERIOD_START_DATE)
)
 SELECT
111 as analysis_id,
 CAST(stratum_1 AS STRING) as stratum_1,
 cast(null as STRING) as stratum_2,
 cast(null as STRING) as stratum_3,
 cast(null as STRING) as stratum_4,
 cast(null as STRING) as stratum_5,
 count_value
FROM
rawData;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_111
 ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(stratum_1)
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_112
USING DELTA
AS
WITH rawData  AS (
 select
 YEAR(observation_period_end_date)*100 + month(observation_period_end_date) as stratum_1,
 COUNT(distinct op1.PERSON_ID) as count_value
 from
 <CDM_DATABASE_NAME>.observation_period op1
 group by YEAR(observation_period_end_date)*100 + month(observation_period_end_date)
)
 SELECT
112 as analysis_id,
 CAST(stratum_1 AS STRING) as stratum_1,
 cast(null as STRING) as stratum_2,
 cast(null as STRING) as stratum_3,
 cast(null as STRING) as stratum_4,
 cast(null as STRING) as stratum_5,
 count_value
FROM
rawData;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_112
 ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_113
USING DELTA
AS
SELECT
113 as analysis_id, 
 CAST(op1.num_periods AS STRING) as stratum_1, 
 cast(null as STRING) as stratum_2, cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
 COUNT(distinct op1.PERSON_ID) as count_value
FROM
(select person_id, COUNT(OBSERVATION_period_start_date) as num_periods from <CDM_DATABASE_NAME>.observation_period group by PERSON_ID) op1
group by op1.num_periods;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_113
 ZORDER BY stratum_1;

CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_114
USING DELTA
AS
SELECT
114 as analysis_id, 
 cast(null as STRING) as stratum_1, cast(null as STRING) as stratum_2, cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
 COUNT(distinct p1.PERSON_ID) as count_value
FROM
<CDM_DATABASE_NAME>.person p1
 inner join (select person_id, MIN(year(OBSERVATION_period_start_date)) as first_obs_year from <CDM_DATABASE_NAME>.observation_period group by PERSON_ID) op1
 on p1.person_id = op1.person_id
where p1.year_of_birth > op1.first_obs_year;

CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_115
USING DELTA
AS
SELECT
115 as analysis_id, 
 cast(null as STRING) as stratum_1, cast(null as STRING) as stratum_2, cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
 COUNT(op1.PERSON_ID) as count_value
FROM
<CDM_DATABASE_NAME>.observation_period op1
where op1.observation_period_end_date < op1.observation_period_start_date;

CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otemp_dates_116
USING DELTA
AS
SELECT
distinct 
 YEAR(observation_period_start_date) as obs_year 
FROM
<CDM_DATABASE_NAME>.observation_period
;
--HINT DISTRIBUTE_ON_KEY(stratum_1)
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_116
USING DELTA
AS
WITH rawData  AS (
 select
 t1.obs_year as stratum_1,
 p1.gender_concept_id as stratum_2,
 floor((t1.obs_year - p1.year_of_birth)/10) as stratum_3,
 COUNT(distinct p1.PERSON_ID) as count_value
 from
 <CDM_DATABASE_NAME>.person p1
 inner join
 <CDM_DATABASE_NAME>.observation_period op1
 on p1.person_id = op1.person_id
 ,
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otemp_dates_116 t1
 where year(op1.OBSERVATION_PERIOD_START_DATE) <= t1.obs_year
 and year(op1.OBSERVATION_PERIOD_END_DATE) >= t1.obs_year
 group by t1.obs_year,
 p1.gender_concept_id,
 floor((t1.obs_year - p1.year_of_birth)/10)
)
 SELECT
116 as analysis_id,
 CAST(stratum_1 AS STRING) as stratum_1,
 cast(stratum_2 as STRING) as stratum_2,
 cast(stratum_3 as STRING) as stratum_3,
 cast(null as STRING) as stratum_4,
 cast(null as STRING) as stratum_5,
 count_value
FROM
rawData;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_116
 ZORDER BY stratum_1;
TRUNCATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otemp_dates_116;
DROP TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otemp_dates_116;

--HINT DISTRIBUTE_ON_KEY(stratum_1)
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_117 
USING DELTA
AS
WITH century   AS (SELECT  CAST('19' as STRING) num union select '20' num), 
tens as (select '0' num union select '1' num union select '2' num union select '3' num union select '4' num union select '5' num union select '6' num union select '7' num union select '8' num union select '9' num),
ones as (select '0' num union select '1' num union select '2' num union select '3' num union select '4' num union select '5' num union select '6' num union select '7' num union select '8' num union select '9' num),
months as (select '01' as num union select '02' num union select '03' num union select '04' num union select '05' num union select '06' num union select '07' num union select '08' num union select '09' num union select '10' num union select '11' num union select '12' num),
date_keys as (select cast(concat(century.num, tens.num, ones.num,months.num) as int) obs_month from century cross join tens cross join ones cross join months)
 SELECT
117 as analysis_id, 
 CAST(t1.obs_month AS STRING) as stratum_1,
 cast(null as STRING) as stratum_2, cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
 COALESCE(COUNT(distinct op1.PERSON_ID),0) as count_value
FROM
date_keys t1
left join
 (select t2.obs_month, op2.*
 from <CDM_DATABASE_NAME>.observation_period op2, date_keys t2
 where year(op2.observation_period_start_date)*100 + month(op2.observation_period_start_date) <= t2.obs_month
 and year(op2.observation_period_end_date)*100 + month(op2.observation_period_end_date) >= t2.obs_month
 ) op1 on op1.obs_month = t1.obs_month
group by t1.obs_month
having COALESCE(COUNT(distinct op1.PERSON_ID),0) > 0;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_117 
 ZORDER BY stratum_1;

CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_118
USING DELTA
AS
SELECT
118 as analysis_id,
 cast(null as STRING) as stratum_1, cast(null as STRING) as stratum_2, cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
 COUNT(op1.PERSON_ID) as count_value
FROM
<CDM_DATABASE_NAME>.observation_period op1
 left join <CDM_DATABASE_NAME>.person p1
 on p1.person_id = op1.person_id
where p1.person_id is null;

--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_119
USING DELTA
AS
SELECT
119 as analysis_id,
 CAST(op1.period_type_concept_id AS STRING) as stratum_1,
 cast(null as STRING) as stratum_2, cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
 COUNT(*) as count_value
FROM
<CDM_DATABASE_NAME>.observation_period op1
group by op1.period_type_concept_id;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_119
 ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_200
USING DELTA
AS
SELECT
200 AS analysis_id,
 CAST(vo.visit_concept_id AS STRING) AS stratum_1,
 CAST(NULL AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 COUNT(DISTINCT vo.person_id) AS count_value
FROM
<CDM_DATABASE_NAME>.visit_occurrence vo
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 vo.person_id = op.person_id
AND 
 vo.visit_start_date >= op.observation_period_start_date
AND 
 vo.visit_start_date <= op.observation_period_end_date
GROUP BY 
 vo.visit_concept_id;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_200
 ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_201
USING DELTA
AS
SELECT
201 AS analysis_id,
 CAST(vo.visit_concept_id AS STRING) AS stratum_1,
 CAST(NULL AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 COUNT(vo.person_id) AS count_value
FROM
<CDM_DATABASE_NAME>.visit_occurrence vo
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 vo.person_id = op.person_id
AND 
 vo.visit_start_date >= op.observation_period_start_date
AND 
 vo.visit_start_date <= op.observation_period_end_date
GROUP BY 
 vo.visit_concept_id;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_201
 ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(stratum_1)
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_202
USING DELTA
AS
WITH rawData  AS (
SELECT 
 vo.visit_concept_id AS stratum_1,
 YEAR(vo.visit_start_date) * 100 + MONTH(vo.visit_start_date) AS stratum_2,
 COUNT(DISTINCT vo.person_id) AS count_value
FROM 
 <CDM_DATABASE_NAME>.visit_occurrence vo
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 vo.person_id = op.person_id
AND 
 vo.visit_start_date >= op.observation_period_start_date
AND 
 vo.visit_start_date <= op.observation_period_end_date
GROUP BY 
 vo.visit_concept_id,
 YEAR(vo.visit_start_date) * 100 + MONTH(vo.visit_start_date)
)
 SELECT
202 as analysis_id,
 CAST(stratum_1 AS STRING) AS stratum_1,
 CAST(stratum_2 AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 count_value
FROM
rawData;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_202
 ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(count_value)
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_203
USING DELTA
AS
WITH rawData(person_id, count_value)  AS (
SELECT 
 vo.person_id,
 COUNT(DISTINCT vo.visit_concept_id) AS count_value
FROM 
 <CDM_DATABASE_NAME>.visit_occurrence vo
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 vo.person_id = op.person_id
AND 
 vo.visit_start_date >= op.observation_period_start_date
AND 
 vo.visit_start_date <= op.observation_period_end_date
GROUP BY 
 vo.person_id
),
overallStats (avg_value, stdev_value, min_value, max_value, total) as
(
 select CAST(avg(1.0 * count_value) AS FLOAT) as avg_value,
 CAST(STDDEV(count_value) AS FLOAT) as stdev_value,
 min(count_value) as min_value,
 max(count_value) as max_value,
 COUNT(*) as total
 from rawData
),
statsView (count_value, total, rn) as
(
 select count_value, 
 COUNT(*) as total, 
 row_number() over (order by count_value) as rn
 FROM rawData
 group by count_value
),
priorStats (count_value, total, accumulated) as
(
 select s.count_value, s.total, sum(p.total) as accumulated
 from statsView s
 join statsView p on p.rn <= s.rn
 group by s.count_value, s.total, s.rn
)
 SELECT
203 as analysis_id,
 o.total as count_value,
 o.min_value,
 o.max_value,
 o.avg_value,
 o.stdev_value,
 MIN(case when p.accumulated >= .50 * o.total then count_value else o.max_value end) as median_value,
 MIN(case when p.accumulated >= .10 * o.total then count_value else o.max_value end) as p10_value,
 MIN(case when p.accumulated >= .25 * o.total then count_value else o.max_value end) as p25_value,
 MIN(case when p.accumulated >= .75 * o.total then count_value else o.max_value end) as p75_value,
 MIN(case when p.accumulated >= .90 * o.total then count_value else o.max_value end) as p90_value
FROM
priorStats p
CROSS JOIN overallStats o
GROUP BY o.total, o.min_value, o.max_value, o.avg_value, o.stdev_value
;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_203
 ZORDER BY count_value;
--HINT DISTRIBUTE_ON_KEY(count_value) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_203
USING DELTA
AS
SELECT
analysis_id, 
cast(null as STRING) as stratum_1, cast(null as STRING) as stratum_2, cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
count_value, min_value, max_value, avg_value, stdev_value, median_value, p10_value, p25_value, p75_value, p90_value
FROM
<ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_203
;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_203
 ZORDER BY count_value;
truncate table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_203;
drop table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_203;

--HINT DISTRIBUTE_ON_KEY(stratum_1)
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_204
USING DELTA
AS
WITH rawData  AS (
SELECT 
 vo.visit_concept_id AS stratum_1,
 YEAR(vo.visit_start_date) AS stratum_2,
 p.gender_concept_id AS stratum_3,
 FLOOR((YEAR(vo.visit_start_date) - p.year_of_birth) / 10) AS stratum_4,
 COUNT(DISTINCT p.person_id) AS count_value
FROM 
 <CDM_DATABASE_NAME>.person p
JOIN 
 <CDM_DATABASE_NAME>.visit_occurrence vo 
ON 
 p.person_id = vo.person_id
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 vo.person_id = op.person_id
AND 
 vo.visit_start_date >= op.observation_period_start_date
AND 
 vo.visit_start_date <= op.observation_period_end_date
GROUP BY 
 vo.visit_concept_id,
 YEAR(vo.visit_start_date),
 p.gender_concept_id,
 FLOOR((YEAR(vo.visit_start_date) - p.year_of_birth) / 10)
)
 SELECT
204 AS analysis_id,
 CAST(stratum_1 AS STRING) AS stratum_1,
 CAST(stratum_2 as STRING) AS stratum_2,
 CAST(stratum_3 as STRING) AS stratum_3,
 CAST(stratum_4 as STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 count_value
FROM
rawData;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_204
 ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(stratum1_id)
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_206
USING DELTA
AS
WITH rawData (stratum1_id, stratum2_id, count_value)  AS (
SELECT 
 v.visit_concept_id AS stratum1_id,
 p.gender_concept_id AS stratum2_id,
 v.visit_start_year - p.year_of_birth AS count_value
FROM 
 <CDM_DATABASE_NAME>.person p
JOIN (
 SELECT 
 vo.person_id,
 vo.visit_concept_id,
 MIN(YEAR(vo.visit_start_date)) AS visit_start_year
 FROM 
 <CDM_DATABASE_NAME>.visit_occurrence vo
 JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
 ON 
 vo.person_id = op.person_id
 AND 
 vo.visit_start_date >= op.observation_period_start_date
 AND 
 vo.visit_start_date <= op.observation_period_end_date
 GROUP BY 
 vo.person_id,
 vo.visit_concept_id
 ) v
ON 
 p.person_id = v.person_id
),
overallStats (stratum1_id, stratum2_id, avg_value, stdev_value, min_value, max_value, total) as
(
 select stratum1_id,
 stratum2_id,
 CAST(avg(1.0 * count_value) AS FLOAT) as avg_value,
 CAST(STDDEV(count_value) AS FLOAT) as stdev_value,
 min(count_value) as min_value,
 max(count_value) as max_value,
 COUNT(*) as total
 FROM rawData
 group by stratum1_id, stratum2_id
),
statsView (stratum1_id, stratum2_id, count_value, total, rn) as
(
 select stratum1_id, stratum2_id, count_value, COUNT(*) as total, row_number() over (partition by stratum1_id, stratum2_id order by count_value) as rn
 FROM rawData
 group by stratum1_id, stratum2_id, count_value
),
priorStats (stratum1_id, stratum2_id, count_value, total, accumulated) as
(
 select s.stratum1_id, s.stratum2_id, s.count_value, s.total, sum(p.total) as accumulated
 from statsView s
 join statsView p on s.stratum1_id = p.stratum1_id and s.stratum2_id = p.stratum2_id and p.rn <= s.rn
 group by s.stratum1_id, s.stratum2_id, s.count_value, s.total, s.rn
)
 SELECT
206 as analysis_id,
 CAST(o.stratum1_id AS STRING) AS stratum1_id,
 CAST(o.stratum2_id AS STRING) AS stratum2_id,
 o.total as count_value,
 o.min_value,
 o.max_value,
 o.avg_value,
 o.stdev_value,
 MIN(case when p.accumulated >= .50 * o.total then count_value else o.max_value end) as median_value,
 MIN(case when p.accumulated >= .10 * o.total then count_value else o.max_value end) as p10_value,
 MIN(case when p.accumulated >= .25 * o.total then count_value else o.max_value end) as p25_value,
 MIN(case when p.accumulated >= .75 * o.total then count_value else o.max_value end) as p75_value,
 MIN(case when p.accumulated >= .90 * o.total then count_value else o.max_value end) as p90_value
FROM
priorStats p
join overallStats o on p.stratum1_id = o.stratum1_id and p.stratum2_id = o.stratum2_id 
GROUP BY o.stratum1_id, o.stratum2_id, o.total, o.min_value, o.max_value, o.avg_value, o.stdev_value
;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_206
 ZORDER BY stratum1_id;
--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_206
USING DELTA
AS
SELECT
analysis_id, stratum1_id as stratum_1, stratum2_id as stratum_2, 
cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
count_value, min_value, max_value, avg_value, stdev_value, median_value, p10_value, p25_value, p75_value, p90_value
FROM
<ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_206
;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_206
 ZORDER BY stratum_1;
truncate table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_206;
drop table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_206;

CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_207
USING DELTA
AS
SELECT
207 as analysis_id, 
 cast(null as STRING) as stratum_1, cast(null as STRING) as stratum_2, cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
 COUNT(vo1.PERSON_ID) as count_value
FROM
<CDM_DATABASE_NAME>.visit_occurrence vo1
 left join <CDM_DATABASE_NAME>.person p1
 on p1.person_id = vo1.person_id
where p1.person_id is null;

CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_209
USING DELTA
AS
SELECT
209 as analysis_id,
 cast(null as STRING) as stratum_1, cast(null as STRING) as stratum_2, cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
 COUNT(vo1.PERSON_ID) as count_value
FROM
<CDM_DATABASE_NAME>.visit_occurrence vo1
 left join <CDM_DATABASE_NAME>.care_site cs1
 on vo1.care_site_id = cs1.care_site_id
where vo1.care_site_id is not null
 and cs1.care_site_id is null;

CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_210
USING DELTA
AS
SELECT
210 AS analysis_id,
 CAST(NULL AS STRING) AS stratum_1,
 CAST(NULL AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 COUNT(*) AS count_value
FROM
<CDM_DATABASE_NAME>.visit_occurrence vo
LEFT JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 vo.person_id = op.person_id
AND 
 vo.visit_start_date >= op.observation_period_start_date
AND 
 vo.visit_start_date <= op.observation_period_end_date
WHERE 
 op.person_id IS NULL;

CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_211
USING DELTA
AS
SELECT
211 as analysis_id, 
 cast(null as STRING) as stratum_1, cast(null as STRING) as stratum_2, cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
 COUNT(vo1.PERSON_ID) as count_value
FROM
<CDM_DATABASE_NAME>.visit_occurrence vo1
where visit_end_date < visit_start_date;

--HINT DISTRIBUTE_ON_KEY(stratum_1)
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_212
USING DELTA
AS
WITH rawData  AS (
 select
 YEAR(visit_start_date) as stratum_1,
 p1.gender_concept_id as stratum_2,
 floor((year(visit_start_date) - p1.year_of_birth)/10) as stratum_3,
 COUNT(distinct p1.PERSON_ID) as count_value
 from <CDM_DATABASE_NAME>.person p1
 inner join
 <CDM_DATABASE_NAME>.visit_occurrence vo1
 on p1.person_id = vo1.person_id
 group by
 YEAR(visit_start_date),
 p1.gender_concept_id,
 floor((year(visit_start_date) - p1.year_of_birth)/10)
)
 SELECT
212 as analysis_id,
 CAST(stratum_1 AS STRING) as stratum_1,
 cast(stratum_2 as STRING) as stratum_2,
 cast(stratum_3 as STRING) as stratum_3,
 cast(null as STRING) as stratum_4,
 cast(null as STRING) as stratum_5,
 count_value
FROM
rawData;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_212
 ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(stratum_id)
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_213
USING DELTA
AS
WITH rawData(stratum_id, count_value)  AS (
 select visit_concept_id AS stratum_id, datediff(visit_end_date,visit_start_date) as count_value
 from <CDM_DATABASE_NAME>.visit_occurrence vo inner join 
 <CDM_DATABASE_NAME>.observation_period op on vo.person_id = op.person_id
 -- only include events that occur during observation period
 where vo.visit_start_date >= op.observation_period_start_date and
 COALESCE(vo.visit_end_date,vo.visit_start_date) <= op.observation_period_end_date
),
overallStats (stratum_id, avg_value, stdev_value, min_value, max_value, total) as
(
 select stratum_id,
 CAST(avg(1.0 * count_value) AS FLOAT) as avg_value,
 CAST(STDDEV(count_value) AS FLOAT) as stdev_value,
 min(count_value) as min_value,
 max(count_value) as max_value,
 COUNT(*) as total
 FROM rawData
 group by stratum_id
),
statsView (stratum_id, count_value, total, rn) as
(
 select stratum_id, count_value, COUNT(*) as total, row_number() over (order by count_value) as rn
 FROM rawData
 group by stratum_id, count_value
),
priorStats (stratum_id, count_value, total, accumulated) as
(
 select s.stratum_id, s.count_value, s.total, sum(p.total) as accumulated
 from statsView s
 join statsView p on s.stratum_id = p.stratum_id and p.rn <= s.rn
 group by s.stratum_id, s.count_value, s.total, s.rn
)
 SELECT
213 as analysis_id,
 CAST(o.stratum_id AS STRING) AS stratum_id,
 o.total as count_value,
 o.min_value,
 o.max_value,
 o.avg_value,
 o.stdev_value,
 MIN(case when p.accumulated >= .50 * o.total then count_value else o.max_value end) as median_value,
 MIN(case when p.accumulated >= .10 * o.total then count_value else o.max_value end) as p10_value,
 MIN(case when p.accumulated >= .25 * o.total then count_value else o.max_value end) as p25_value,
 MIN(case when p.accumulated >= .75 * o.total then count_value else o.max_value end) as p75_value,
 MIN(case when p.accumulated >= .90 * o.total then count_value else o.max_value end) as p90_value
FROM
priorStats p
join overallStats o on p.stratum_id = o.stratum_id
GROUP BY o.stratum_id, o.total, o.min_value, o.max_value, o.avg_value, o.stdev_value
;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_213
 ZORDER BY stratum_id;
--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_213
USING DELTA
AS
SELECT
analysis_id, stratum_id as stratum_1, 
cast(null as STRING) as stratum_2, cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
count_value, min_value, max_value, avg_value, stdev_value, median_value, p10_value, p25_value, p75_value, p90_value
FROM
<ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_213
;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_213
 ZORDER BY stratum_1;
truncate table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_213;
drop table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_213;

--HINT DISTRIBUTE_ON_KEY(stratum_1)
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_220
USING DELTA
AS
WITH rawData  AS (
SELECT 
 YEAR(vo.visit_start_date) * 100 + MONTH(vo.visit_start_date) AS stratum_1,
 COUNT(vo.person_id) AS count_value
FROM 
 <CDM_DATABASE_NAME>.visit_occurrence vo
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 vo.person_id = op.person_id
AND 
 vo.visit_start_date >= op.observation_period_start_date
AND 
 vo.visit_start_date <= op.observation_period_end_date
GROUP BY 
 YEAR(vo.visit_start_date) * 100 + MONTH(vo.visit_start_date)
)
 SELECT
220 AS analysis_id,
 CAST(stratum_1 AS STRING) AS stratum_1,
 CAST(NULL AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 count_value
FROM
rawData;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_220
 ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(stratum_1)
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_221
USING DELTA
AS
WITH rawData  AS (
SELECT 
 YEAR(vo.visit_start_date) AS stratum_1,
 COUNT(DISTINCT vo.person_id) AS count_value
FROM 
 <CDM_DATABASE_NAME>.visit_occurrence vo
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 vo.person_id = op.person_id
AND 
 vo.visit_start_date >= op.observation_period_start_date
AND 
 vo.visit_start_date <= op.observation_period_end_date
GROUP BY 
 YEAR(vo.visit_start_date)
)
 SELECT
221 AS analysis_id,
 CAST(stratum_1 AS STRING) AS stratum_1,
 CAST(NULL AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 count_value
FROM
rawData;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_221
 ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_225
USING DELTA
AS
SELECT
225 AS analysis_id,
 CAST(vo.visit_source_concept_id AS STRING) AS stratum_1,
 CAST(NULL AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 COUNT(*) AS count_value
FROM
<CDM_DATABASE_NAME>.visit_occurrence vo
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 vo.person_id = op.person_id
AND 
 vo.visit_start_date >= op.observation_period_start_date
AND 
 vo.visit_start_date <= op.observation_period_end_date
GROUP BY 
 visit_source_concept_id;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_225
 ZORDER BY stratum_1;

CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_226
USING DELTA
AS
SELECT
226 as analysis_id, 
 CAST(v.visit_concept_id AS STRING) as stratum_1,
 v.cdm_table as stratum_2,
 cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
 v.record_count as count_value
FROM
(
 select 'drug_exposure' cdm_table, coalesce(visit_concept_id,0) visit_concept_id, count(*) record_count
 from <CDM_DATABASE_NAME>.drug_exposure t
 left join <CDM_DATABASE_NAME>.visit_occurrence v on t.visit_occurrence_id = v.visit_occurrence_id
 group by visit_concept_id
 union
 select 'condition_occurrence' cdm_table, coalesce(visit_concept_id,0) visit_concept_id, count(*) record_count
 from <CDM_DATABASE_NAME>.condition_occurrence t
 left join <CDM_DATABASE_NAME>.visit_occurrence v on t.visit_occurrence_id = v.visit_occurrence_id
 group by visit_concept_id
 union
 select 'device_exposure' cdm_table, coalesce(visit_concept_id,0) visit_concept_id, count(*) record_count
 from <CDM_DATABASE_NAME>.device_exposure t
 left join <CDM_DATABASE_NAME>.visit_occurrence v on t.visit_occurrence_id = v.visit_occurrence_id
 group by visit_concept_id
 union
 select 'procedure_occurrence' cdm_table, coalesce(visit_concept_id,0) visit_concept_id, count(*) record_count
 from <CDM_DATABASE_NAME>.procedure_occurrence t
 left join <CDM_DATABASE_NAME>.visit_occurrence v on t.visit_occurrence_id = v.visit_occurrence_id
 group by visit_concept_id
 union
 select 'measurement' cdm_table, coalesce(visit_concept_id,0) visit_concept_id, count(*) record_count
 from <CDM_DATABASE_NAME>.measurement t
 left join <CDM_DATABASE_NAME>.visit_occurrence v on t.visit_occurrence_id = v.visit_occurrence_id
 group by visit_concept_id
 union
 select 'observation' cdm_table, coalesce(visit_concept_id,0) visit_concept_id, count(*) record_count
 from <CDM_DATABASE_NAME>.observation t
 left join <CDM_DATABASE_NAME>.visit_occurrence v on t.visit_occurrence_id = v.visit_occurrence_id
 group by visit_concept_id
) v;

CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_230
USING DELTA
AS
SELECT
230 AS analysis_id,
 CAST(NULL AS STRING) AS stratum_1,
 CAST(NULL AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 COUNT(*) AS count_value
FROM
<CDM_DATABASE_NAME>.visit_occurrence vo
LEFT JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 vo.person_id = op.person_id
AND 
 vo.visit_start_date >= op.observation_period_start_date
AND 
 vo.visit_start_date <= op.observation_period_end_date;

DROP VIEW IF EXISTS op_outside  ; 
CREATE TEMPORARY VIEW op_outside   AS (SELECT 
 COUNT(DISTINCT vo.person_id) AS person_count
FROM 
 <CDM_DATABASE_NAME>.visit_occurrence vo
LEFT JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 vo.person_id = op.person_id
AND 
 vo.visit_start_date >= op.observation_period_start_date
AND 
 vo.visit_start_date <= op.observation_period_end_date
WHERE
 op.person_id IS NULL
);
DROP VIEW IF EXISTS vo_total ; 
CREATE TEMPORARY VIEW vo_total  AS (SELECT
 COUNT(DISTINCT person_id) person_count
FROM
 <CDM_DATABASE_NAME>.visit_occurrence
);
 CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_231
USING DELTA
AS
(SELECT
231 AS analysis_id,
 CASE WHEN vo.person_count != 0 THEN 
 CAST(CAST(1.0*op.person_count/vo.person_count AS FLOAT) AS STRING) 
 ELSE
 CAST(NULL AS STRING) 
 END AS stratum_1, 
 CAST(op.person_count AS STRING) AS stratum_2,
 CAST(vo.person_count AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 SIGN(op.person_count) AS count_value
FROM
op_outside op
CROSS JOIN 
 vo_total vo);

DROP VIEW IF EXISTS op_outside  ; 
CREATE TEMPORARY VIEW op_outside   AS (SELECT 
 COUNT(*) AS record_count
FROM 
 <CDM_DATABASE_NAME>.visit_occurrence vo
LEFT JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 vo.person_id = op.person_id
AND 
 vo.visit_start_date >= op.observation_period_start_date
AND 
 vo.visit_start_date <= op.observation_period_end_date
WHERE
 op.person_id IS NULL
);
DROP VIEW IF EXISTS vo_total ; 
CREATE TEMPORARY VIEW vo_total  AS (SELECT
 COUNT(*) record_count
FROM
 <CDM_DATABASE_NAME>.visit_occurrence
);
 CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_232
USING DELTA
AS
(SELECT
232 AS analysis_id,
 CASE WHEN vo.record_count != 0 THEN
 CAST(CAST(1.0*op.record_count/vo.record_count AS FLOAT) AS STRING) 
 ELSE 
 CAST(NULL AS STRING)
 END AS stratum_1, 
 CAST(op.record_count AS STRING) AS stratum_2,
 CAST(vo.record_count AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 SIGN(op.record_count) AS count_value
FROM
op_outside op
CROSS JOIN 
 vo_total vo);

CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_300
USING DELTA
AS
SELECT
300 as analysis_id, 
cast(null as STRING) as stratum_1, cast(null as STRING) as stratum_2, cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
COUNT(distinct provider_id) as count_value
FROM
<CDM_DATABASE_NAME>.provider;

--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_301
USING DELTA
AS
SELECT
301 as analysis_id,
CAST(specialty_concept_id AS STRING) as stratum_1, 
cast(null as STRING) as stratum_2, cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
COUNT(distinct provider_id) as count_value
FROM
<CDM_DATABASE_NAME>.provider
group by specialty_CONCEPT_ID;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_301
 ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_303 
USING DELTA
AS
SELECT
303 as analysis_id,
 cast(p.specialty_concept_id AS STRING) AS stratum_1,
 cast(vo.visit_concept_id AS STRING) AS stratum_2,
 cast(null as STRING) as stratum_3,
 cast(null as STRING) as stratum_4,
 cast(null as STRING) as stratum_5, 
 COUNT(*) AS count_value
FROM
<CDM_DATABASE_NAME>.provider p
 join <CDM_DATABASE_NAME>.visit_occurrence vo
 on vo.provider_id = p.provider_id
 group by p.specialty_concept_id, visit_concept_id;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_303 
  ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_325 
USING DELTA
AS
SELECT
325 as analysis_id,
 cast(specialty_source_concept_id AS STRING) AS stratum_1,
 cast(null AS STRING) AS stratum_2,
 cast(null as STRING) as stratum_3,
 cast(null as STRING) as stratum_4,
 cast(null as STRING) as stratum_5, 
 COUNT(*) AS count_value
FROM
<CDM_DATABASE_NAME>.provider
 group by specialty_source_concept_id;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_325 
  ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_400
USING DELTA
AS
SELECT
400 AS analysis_id,
 CAST(co.condition_concept_id AS STRING) AS stratum_1,
 CAST(NULL AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 COUNT(DISTINCT co.person_id) AS count_value
FROM
<CDM_DATABASE_NAME>.condition_occurrence co
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 co.person_id = op.person_id
AND 
 co.condition_start_date >= op.observation_period_start_date
AND 
 co.condition_start_date <= op.observation_period_end_date
GROUP BY 
 co.condition_concept_id;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_400
 ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_401
USING DELTA
AS
SELECT
401 AS analysis_id,
 CAST(co.condition_concept_id AS STRING) AS stratum_1,
 CAST(NULL AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 COUNT(co.person_id) AS count_value
FROM
<CDM_DATABASE_NAME>.condition_occurrence co
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 co.person_id = op.person_id
AND 
 co.condition_start_date >= op.observation_period_start_date
AND 
 co.condition_start_date <= op.observation_period_end_date
GROUP BY 
 co.condition_concept_id;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_401
 ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(stratum_1)
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_402
USING DELTA
AS
WITH rawData  AS (
SELECT 
 co.condition_concept_id AS stratum_1,
 YEAR(co.condition_start_date) * 100 + MONTH(co.condition_start_date) AS stratum_2,
 COUNT(DISTINCT co.person_id) AS count_value
FROM 
 <CDM_DATABASE_NAME>.condition_occurrence co
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 co.person_id = op.person_id
AND 
 co.condition_start_date >= op.observation_period_start_date
AND 
 co.condition_start_date <= op.observation_period_end_date
GROUP BY 
 co.condition_concept_id,
 YEAR(co.condition_start_date) * 100 + MONTH(co.condition_start_date)
)
 SELECT
402 AS analysis_id,
 CAST(stratum_1 AS STRING) AS stratum_1,
 CAST(stratum_2 AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 count_value
FROM
rawData;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_402
 ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(count_value)
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_403
USING DELTA
AS
WITH rawData(person_id, count_value)  AS (
SELECT 
 co.person_id,
 COUNT(DISTINCT co.condition_concept_id) AS count_value
FROM 
 <CDM_DATABASE_NAME>.condition_occurrence co
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 co.person_id = op.person_id
AND 
 co.condition_start_date >= op.observation_period_start_date
AND 
 co.condition_start_date <= op.observation_period_end_date
GROUP BY 
 co.person_id
),
overallStats (avg_value, stdev_value, min_value, max_value, total) as
(
 select CAST(avg(1.0 * count_value) AS FLOAT) as avg_value,
 CAST(STDDEV(count_value) AS FLOAT) as stdev_value,
 min(count_value) as min_value,
 max(count_value) as max_value,
 COUNT(*) as total
 from rawData
),
statsView (count_value, total, rn) as
(
 select count_value, 
 COUNT(*) as total, 
 row_number() over (order by count_value) as rn
 FROM rawData
 group by count_value
),
priorStats (count_value, total, accumulated) as
(
 select s.count_value, s.total, sum(p.total) as accumulated
 from statsView s
 join statsView p on p.rn <= s.rn
 group by s.count_value, s.total, s.rn
)
 SELECT
403 as analysis_id,
 o.total as count_value,
 o.min_value,
 o.max_value,
 o.avg_value,
 o.stdev_value,
 MIN(case when p.accumulated >= .50 * o.total then count_value else o.max_value end) as median_value,
 MIN(case when p.accumulated >= .10 * o.total then count_value else o.max_value end) as p10_value,
 MIN(case when p.accumulated >= .25 * o.total then count_value else o.max_value end) as p25_value,
 MIN(case when p.accumulated >= .75 * o.total then count_value else o.max_value end) as p75_value,
 MIN(case when p.accumulated >= .90 * o.total then count_value else o.max_value end) as p90_value
FROM
priorStats p
CROSS JOIN overallStats o
GROUP BY o.total, o.min_value, o.max_value, o.avg_value, o.stdev_value
;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_403
 ZORDER BY count_value;
--HINT DISTRIBUTE_ON_KEY(count_value) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_403
USING DELTA
AS
SELECT
analysis_id, 
cast(null as STRING) as stratum_1, cast(null as STRING) as stratum_2, cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
count_value, min_value, max_value, avg_value, stdev_value, median_value, p10_value, p25_value, p75_value, p90_value
FROM
<ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_403
;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_403
 ZORDER BY count_value;
truncate table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_403;
drop table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_403;

--HINT DISTRIBUTE_ON_KEY(stratum_1)
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_404
USING DELTA
AS
WITH rawData  AS (
SELECT 
 co.condition_concept_id AS stratum_1,
 YEAR(co.condition_start_date) AS stratum_2,
 p.gender_concept_id AS stratum_3,
 FLOOR((YEAR(co.condition_start_date) - p.year_of_birth) / 10) AS stratum_4,
 COUNT(DISTINCT p.person_id) AS count_value
FROM 
 <CDM_DATABASE_NAME>.person p
JOIN 
 <CDM_DATABASE_NAME>.condition_occurrence co 
ON 
 p.person_id = co.person_id
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 co.person_id = op.person_id
AND 
 co.condition_start_date >= op.observation_period_start_date
AND 
 co.condition_start_date <= op.observation_period_end_date
GROUP BY 
 co.condition_concept_id,
 YEAR(co.condition_start_date),
 p.gender_concept_id,
 FLOOR((YEAR(co.condition_start_date) - p.year_of_birth) / 10)
)
 SELECT
404 AS analysis_id,
 CAST(stratum_1 AS STRING) AS stratum_1,
 CAST(stratum_2 AS STRING) AS stratum_2,
 CAST(stratum_3 AS STRING) AS stratum_3,
 CAST(stratum_4 AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 count_value
FROM
rawData;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_404
 ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_405
USING DELTA
AS
SELECT
405 AS analysis_id,
 CAST(co.condition_concept_id AS STRING) AS stratum_1,
 CAST(co.condition_type_concept_id AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 COUNT(co.person_id) AS count_value
FROM
<CDM_DATABASE_NAME>.condition_occurrence co
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 co.person_id = op.person_id
AND 
 co.condition_start_date >= op.observation_period_start_date
AND 
 co.condition_start_date <= op.observation_period_end_date
GROUP BY 
 co.condition_CONCEPT_ID,
 co.condition_type_concept_id;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_405
 ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(subject_id) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1orawData_406
USING DELTA
AS
SELECT
c.condition_concept_id AS subject_id,
 p.gender_concept_id,
 (c.condition_start_year - p.year_of_birth) AS count_value
FROM
<CDM_DATABASE_NAME>.person p
JOIN (
 SELECT 
 co.person_id,
 co.condition_concept_id,
 MIN(YEAR(co.condition_start_date)) AS condition_start_year
 FROM 
 <CDM_DATABASE_NAME>.condition_occurrence co
 JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
 ON 
 co.person_id = op.person_id
 AND 
 co.condition_start_date >= op.observation_period_start_date
 AND 
 co.condition_start_date <= op.observation_period_end_date
 GROUP BY 
 co.person_id,
 co.condition_concept_id
 ) c 
ON 
 p.person_id = c.person_id;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1orawData_406
 ZORDER BY subject_id;
--HINT DISTRIBUTE_ON_KEY(stratum1_id)
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_406
USING DELTA
AS
WITH overallStats (stratum1_id, stratum2_id, avg_value, stdev_value, min_value, max_value, total)  AS (
 select subject_id as stratum1_id,
 gender_concept_id as stratum2_id,
 CAST(avg(1.0 * count_value) AS FLOAT) as avg_value,
 CAST(STDDEV(count_value) AS FLOAT) as stdev_value,
 min(count_value) as min_value,
 max(count_value) as max_value,
 COUNT(*) as total
 FROM <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1orawData_406
 group by subject_id, gender_concept_id
),
statsView (stratum1_id, stratum2_id, count_value, total, rn) as
(
 select subject_id as stratum1_id, gender_concept_id as stratum2_id, count_value, COUNT(*) as total, row_number() over (partition by subject_id, gender_concept_id order by count_value) as rn
 FROM <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1orawData_406
 group by subject_id, gender_concept_id, count_value
),
priorStats (stratum1_id, stratum2_id, count_value, total, accumulated) as
(
 select s.stratum1_id, s.stratum2_id, s.count_value, s.total, sum(p.total) as accumulated
 from statsView s
 join statsView p on s.stratum1_id = p.stratum1_id and s.stratum2_id = p.stratum2_id and p.rn <= s.rn
 group by s.stratum1_id, s.stratum2_id, s.count_value, s.total, s.rn
)
 SELECT
406 as analysis_id,
 CAST(o.stratum1_id AS STRING) AS stratum1_id,
 CAST(o.stratum2_id AS STRING) AS stratum2_id,
 o.total as count_value,
 o.min_value,
 o.max_value,
 o.avg_value,
 o.stdev_value,
 MIN(case when p.accumulated >= .50 * o.total then count_value else o.max_value end) as median_value,
 MIN(case when p.accumulated >= .10 * o.total then count_value else o.max_value end) as p10_value,
 MIN(case when p.accumulated >= .25 * o.total then count_value else o.max_value end) as p25_value,
 MIN(case when p.accumulated >= .75 * o.total then count_value else o.max_value end) as p75_value,
 MIN(case when p.accumulated >= .90 * o.total then count_value else o.max_value end) as p90_value
FROM
priorStats p
join overallStats o on p.stratum1_id = o.stratum1_id and p.stratum2_id = o.stratum2_id 
GROUP BY o.stratum1_id, o.stratum2_id, o.total, o.min_value, o.max_value, o.avg_value, o.stdev_value
;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_406
 ZORDER BY stratum1_id;
--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_406
USING DELTA
AS
SELECT
analysis_id, stratum1_id as stratum_1, stratum2_id as stratum_2, 
cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
count_value, min_value, max_value, avg_value, stdev_value, median_value, p10_value, p25_value, p75_value, p90_value
FROM
<ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_406
;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_406
 ZORDER BY stratum_1;
truncate table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_406;
drop table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_406;
truncate Table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1orawData_406;
drop table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1orawData_406;

CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_409
USING DELTA
AS
SELECT
409 as analysis_id, 
 cast(null as STRING) as stratum_1, cast(null as STRING) as stratum_2, cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
 COUNT(co1.PERSON_ID) as count_value
FROM
<CDM_DATABASE_NAME>.condition_occurrence co1
 left join <CDM_DATABASE_NAME>.person p1
 on p1.person_id = co1.person_id
where p1.person_id is null;

CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_410
USING DELTA
AS
SELECT
410 as analysis_id,
 cast(null as STRING) as stratum_1, cast(null as STRING) as stratum_2, cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
 COUNT(co1.PERSON_ID) as count_value
FROM
<CDM_DATABASE_NAME>.condition_occurrence co1
 left join <CDM_DATABASE_NAME>.observation_period op1
 on op1.person_id = co1.person_id
 and co1.condition_start_date >= op1.observation_period_start_date
 and co1.condition_start_date <= op1.observation_period_end_date
where op1.person_id is null;

CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_411
USING DELTA
AS
SELECT
411 as analysis_id, 
 cast(null as STRING) as stratum_1, cast(null as STRING) as stratum_2, cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
 COUNT(co1.PERSON_ID) as count_value
FROM
<CDM_DATABASE_NAME>.condition_occurrence co1
where co1.condition_end_date < co1.condition_start_date;

CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_412
USING DELTA
AS
SELECT
412 as analysis_id, 
 cast(null as STRING) as stratum_1, cast(null as STRING) as stratum_2, cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
 COUNT(co1.PERSON_ID) as count_value
FROM
<CDM_DATABASE_NAME>.condition_occurrence co1
 left join <CDM_DATABASE_NAME>.provider p1
 on p1.provider_id = co1.provider_id
where co1.provider_id is not null
 and p1.provider_id is null;

CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_413
USING DELTA
AS
SELECT
413 as analysis_id, 
 cast(null as STRING) as stratum_1, cast(null as STRING) as stratum_2, cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
 COUNT(co1.PERSON_ID) as count_value
FROM
<CDM_DATABASE_NAME>.condition_occurrence co1
 left join <CDM_DATABASE_NAME>.visit_occurrence vo1
 on co1.visit_occurrence_id = vo1.visit_occurrence_id
where co1.visit_occurrence_id is not null
 and vo1.visit_occurrence_id is null;

--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_414
USING DELTA
AS
SELECT
414 AS analysis_id,
 CAST(co.condition_status_concept_id AS STRING) AS stratum_1,
 CAST(NULL AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 COUNT(*) AS count_value
FROM
<CDM_DATABASE_NAME>.condition_occurrence co
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 co.person_id = op.person_id
AND 
 co.condition_start_date >= op.observation_period_start_date
AND 
 co.condition_start_date <= op.observation_period_end_date
GROUP BY 
 co.condition_status_concept_id;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_414
 ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_415
USING DELTA
AS
SELECT
415 AS analysis_id,
 CAST(co.condition_type_concept_id AS STRING) AS stratum_1,
 CAST(NULL AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 COUNT(*) AS count_value
FROM
<CDM_DATABASE_NAME>.condition_occurrence co
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 co.person_id = op.person_id
AND 
 co.condition_start_date >= op.observation_period_start_date
AND 
 co.condition_start_date <= op.observation_period_end_date
GROUP BY 
 co.condition_type_concept_id;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_415
 ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_416
USING DELTA
AS
SELECT
416 AS analysis_id,
 CAST(co.condition_status_concept_id AS STRING) AS stratum_1,
 CAST(co.condition_type_concept_id AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 COUNT(*) AS count_value
FROM
<CDM_DATABASE_NAME>.condition_occurrence co
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 co.person_id = op.person_id
AND 
 co.condition_start_date >= op.observation_period_start_date
AND 
 co.condition_start_date <= op.observation_period_end_date
GROUP BY 
 co.condition_status_concept_id, co.condition_type_concept_id;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_416
 ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(stratum_1)
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_420
USING DELTA
AS
WITH rawData  AS (
SELECT 
 YEAR(co.condition_start_date) * 100 + MONTH(co.condition_start_date) AS stratum_1,
 COUNT(co.person_id) AS count_value
FROM 
 <CDM_DATABASE_NAME>.condition_occurrence co
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 co.person_id = op.person_id
AND 
 co.condition_start_date >= op.observation_period_start_date
AND 
 co.condition_start_date <= op.observation_period_end_date
GROUP BY 
 YEAR(co.condition_start_date) * 100 + MONTH(co.condition_start_date)
)
 SELECT
420 AS analysis_id,
 CAST(stratum_1 AS STRING) AS stratum_1,
 CAST(NULL AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 count_value
FROM
rawData;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_420
 ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_425
USING DELTA
AS
SELECT
425 AS analysis_id,
 CAST(co.condition_source_concept_id AS STRING) AS stratum_1,
 CAST(NULL AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 COUNT(*) AS count_value
FROM
<CDM_DATABASE_NAME>.condition_occurrence co
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 co.person_id = op.person_id
AND 
 co.condition_start_date >= op.observation_period_start_date
AND 
 co.condition_start_date <= op.observation_period_end_date
GROUP BY 
 co.condition_source_concept_id;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_425
 ZORDER BY stratum_1;

CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_430
USING DELTA
AS
SELECT
430 AS analysis_id,
 CAST(NULL AS STRING) AS stratum_1,
 CAST(NULL AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 COUNT(*) AS count_value
FROM
<CDM_DATABASE_NAME>.condition_occurrence co
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 op.person_id = co.person_id
AND 
 co.condition_start_date >= op.observation_period_start_date
AND 
 co.condition_start_date <= op.observation_period_end_date;

DROP VIEW IF EXISTS op_outside  ; 
CREATE TEMPORARY VIEW op_outside   AS (SELECT 
 COUNT(DISTINCT co.person_id) AS person_count
FROM 
 <CDM_DATABASE_NAME>.condition_occurrence co
LEFT JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 co.person_id = op.person_id
AND 
 co.condition_start_date >= op.observation_period_start_date
AND 
 co.condition_start_date <= op.observation_period_end_date
WHERE
 op.person_id IS NULL
);
DROP VIEW IF EXISTS co_total ; 
CREATE TEMPORARY VIEW co_total  AS (SELECT
 COUNT(DISTINCT person_id) person_count
FROM
 <CDM_DATABASE_NAME>.condition_occurrence
);
 CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_431
USING DELTA
AS
(SELECT
431 AS analysis_id,
 CASE WHEN co.person_count != 0 THEN
 CAST(CAST(1.0*op.person_count/co.person_count AS FLOAT) AS STRING) 
 ELSE 
 CAST(NULL AS STRING) 
 END AS stratum_1, 
 CAST(op.person_count AS STRING) AS stratum_2,
 CAST(co.person_count AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 SIGN(op.person_count) AS count_value
FROM
op_outside op
CROSS JOIN 
 co_total co);

DROP VIEW IF EXISTS op_outside  ; 
CREATE TEMPORARY VIEW op_outside   AS (SELECT 
 COUNT(*) AS record_count
FROM 
 <CDM_DATABASE_NAME>.condition_occurrence co
LEFT JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 co.person_id = op.person_id
AND 
 co.condition_start_date >= op.observation_period_start_date
AND 
 co.condition_start_date <= op.observation_period_end_date
WHERE
 op.person_id IS NULL
);
DROP VIEW IF EXISTS co_total ; 
CREATE TEMPORARY VIEW co_total  AS (SELECT
 COUNT(*) record_count
FROM
 <CDM_DATABASE_NAME>.condition_occurrence
);
 CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_432
USING DELTA
AS
(SELECT
432 AS analysis_id,
 CASE WHEN co.record_count != 0 THEN 
 CAST(CAST(1.0*op.record_count/co.record_count AS FLOAT) AS STRING) 
 ELSE
 CAST(NULL AS STRING)
 END AS stratum_1, 
 CAST(op.record_count AS STRING) AS stratum_2,
 CAST(co.record_count AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 SIGN(op.record_count) AS count_value
FROM
op_outside op
CROSS JOIN 
 co_total co);

--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_500
USING DELTA
AS
SELECT
500 AS analysis_id,
 CAST(d.cause_concept_id AS STRING) AS stratum_1,
 CAST(NULL AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 COUNT(DISTINCT d.person_id) AS count_value
FROM
<CDM_DATABASE_NAME>.death d
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 d.person_id = op.person_id
AND 
 d.death_date >= op.observation_period_start_date
AND 
 d.death_date <= op.observation_period_end_date 
GROUP BY 
 d.cause_concept_id;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_500
 ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_501
USING DELTA
AS
SELECT
501 AS analysis_id,
 CAST(d.cause_concept_id AS STRING) AS stratum_1,
 CAST(NULL AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 COUNT(d.person_id) AS count_value
FROM
<CDM_DATABASE_NAME>.death d
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 d.person_id = op.person_id
AND 
 d.death_date >= op.observation_period_start_date
AND 
 d.death_date <= op.observation_period_end_date 
GROUP BY 
 d.cause_concept_id;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_501
 ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(stratum_1)
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_502
USING DELTA
AS
WITH rawData  AS (
SELECT 
 YEAR(d.death_date) * 100 + MONTH(d.death_date) AS stratum_1,
 COUNT(DISTINCT d.person_id) AS count_value
FROM 
 <CDM_DATABASE_NAME>.death d
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 d.person_id = op.person_id
AND 
 d.death_date >= op.observation_period_start_date
AND 
 d.death_date <= op.observation_period_end_date 
GROUP BY 
 YEAR(d.death_date) * 100 + MONTH(d.death_date)
)
 SELECT
502 AS analysis_id,
 CAST(stratum_1 AS STRING) AS stratum_1,
 CAST(NULL AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 count_value
FROM
rawData;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_502
 ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(stratum_1)
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_504
USING DELTA
AS
WITH rawData  AS (
SELECT 
 YEAR(d.death_date) AS stratum_1,
 p.gender_concept_id AS stratum_2,
 FLOOR((YEAR(d.death_date) - p.year_of_birth) / 10) AS stratum_3,
 COUNT(DISTINCT p.person_id) AS count_value
FROM 
 <CDM_DATABASE_NAME>.person p
JOIN 
 <CDM_DATABASE_NAME>.death d 
ON 
 p.person_id = d.person_id
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 d.person_id = op.person_id
AND 
 d.death_date >= op.observation_period_start_date
AND 
 d.death_date <= op.observation_period_end_date 
GROUP BY 
 YEAR(d.death_date),
 p.gender_concept_id,
 FLOOR((YEAR(d.death_date) - p.year_of_birth) / 10)
)
 SELECT
504 AS analysis_id,
 CAST(stratum_1 AS STRING) AS stratum_1,
 CAST(stratum_2 AS STRING) AS stratum_2,
 CAST(stratum_3 AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 count_value
FROM
rawData;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_504
 ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_505
USING DELTA
AS
SELECT
505 AS analysis_id,
 CAST(d.death_type_concept_id AS STRING) AS stratum_1,
 CAST(NULL AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 COUNT(d.person_id) AS count_value
FROM
<CDM_DATABASE_NAME>.death d
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 d.person_id = op.person_id
AND 
 d.death_date >= op.observation_period_start_date
AND 
 d.death_date <= op.observation_period_end_date 
GROUP BY 
 d.death_type_concept_id;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_505
 ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(stratum_id)
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_506
USING DELTA
AS
WITH rawData(stratum_id, count_value)  AS (
SELECT 
 p.gender_concept_id AS stratum_id,
 d.death_year - p.year_of_birth AS count_value
FROM 
 <CDM_DATABASE_NAME>.person p
JOIN (
 SELECT 
 d.person_id,
 MIN(YEAR(d.death_date)) AS death_year
 FROM 
 <CDM_DATABASE_NAME>.death d
 JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
 ON 
 d.person_id = op.person_id
 AND 
 d.death_date >= op.observation_period_start_date
 AND 
 d.death_date <= op.observation_period_end_date 
 GROUP BY 
 d.person_id
 ) d
ON 
 p.person_id = d.person_id
),
overallStats (stratum_id, avg_value, stdev_value, min_value, max_value, total) as
(
 select stratum_id,
 CAST(avg(1.0 * count_value) AS FLOAT) as avg_value,
 CAST(STDDEV(count_value) AS FLOAT) as stdev_value,
 min(count_value) as min_value,
 max(count_value) as max_value,
 COUNT(*) as total
 FROM rawData
 group by stratum_id
),
statsView (stratum_id, count_value, total, rn) as
(
 select stratum_id, count_value, COUNT(*) as total, row_number() over (order by count_value) as rn
 FROM rawData
 group by stratum_id, count_value
),
priorStats (stratum_id, count_value, total, accumulated) as
(
 select s.stratum_id, s.count_value, s.total, sum(p.total) as accumulated
 from statsView s
 join statsView p on s.stratum_id = p.stratum_id and p.rn <= s.rn
 group by s.stratum_id, s.count_value, s.total, s.rn
)
 SELECT
506 as analysis_id,
 CAST(o.stratum_id AS STRING) AS stratum_id,
 o.total as count_value,
 o.min_value,
 o.max_value,
 o.avg_value,
 o.stdev_value,
 MIN(case when p.accumulated >= .50 * o.total then count_value else o.max_value end) as median_value,
 MIN(case when p.accumulated >= .10 * o.total then count_value else o.max_value end) as p10_value,
 MIN(case when p.accumulated >= .25 * o.total then count_value else o.max_value end) as p25_value,
 MIN(case when p.accumulated >= .75 * o.total then count_value else o.max_value end) as p75_value,
 MIN(case when p.accumulated >= .90 * o.total then count_value else o.max_value end) as p90_value
FROM
priorStats p
join overallStats o on p.stratum_id = o.stratum_id
GROUP BY o.stratum_id, o.total, o.min_value, o.max_value, o.avg_value, o.stdev_value
;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_506
 ZORDER BY stratum_id;
--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_506
USING DELTA
AS
SELECT
analysis_id, stratum_id as stratum_1, 
cast(null as STRING) as stratum_2, cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
count_value, min_value, max_value, avg_value, stdev_value, median_value, p10_value, p25_value, p75_value, p90_value
FROM
<ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_506
;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_506
 ZORDER BY stratum_1;
truncate table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_506;
drop table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_506;

CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_509
USING DELTA
AS
SELECT
509 as analysis_id, 
 cast(null as STRING) as stratum_1, cast(null as STRING) as stratum_2, cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
 COUNT(d1.PERSON_ID) as count_value
FROM
<CDM_DATABASE_NAME>.death d1
 left join <CDM_DATABASE_NAME>.person p1
 on d1.person_id = p1.person_id
where p1.person_id is null;

CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_510
USING DELTA
AS
SELECT
510 as analysis_id, 
 cast(null as STRING) as stratum_1, cast(null as STRING) as stratum_2, cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
 COUNT(d1.PERSON_ID) as count_value
FROM
<CDM_DATABASE_NAME>.death d1
 left join <CDM_DATABASE_NAME>.observation_period op1
 on d1.person_id = op1.person_id
 and d1.death_date >= op1.observation_period_start_date
 and d1.death_date <= op1.observation_period_end_date
where op1.person_id is null;

--HINT DISTRIBUTE_ON_KEY(count_value) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_511
USING DELTA
AS
SELECT
511 AS analysis_id,
 CAST(NULL AS STRING) AS stratum_1,
 CAST(NULL AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 COUNT(count_value) AS count_value,
 MIN(count_value) AS min_value,
 MAX(count_value) AS max_value,
 CAST(AVG(1.0 * count_value) AS FLOAT) AS avg_value,
 CAST(STDDEV(count_value) AS FLOAT) AS stdev_value,
 MAX(CASE WHEN p1 <= 0.50 THEN count_value ELSE - 9999 END) AS median_value,
 MAX(CASE WHEN p1 <= 0.10 THEN count_value ELSE - 9999 END) AS p10_value,
 MAX(CASE WHEN p1 <= 0.25 THEN count_value ELSE - 9999 END) AS p25_value,
 MAX(CASE WHEN p1 <= 0.75 THEN count_value ELSE - 9999 END) AS p75_value,
 MAX(CASE WHEN p1 <= 0.90 THEN count_value ELSE - 9999 END) AS p90_value
FROM
(
SELECT 
 datediff(co.max_date,d.death_date) AS count_value,
 1.0 * (ROW_NUMBER() OVER (ORDER BY datediff(co.max_date,d.death_date))) / (COUNT(*) OVER () + 1) AS p1
FROM 
 <CDM_DATABASE_NAME>.death d
JOIN (
 SELECT 
 co.person_id,
 MAX(co.condition_start_date) AS max_date
 FROM 
 <CDM_DATABASE_NAME>.condition_occurrence co
 JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
 ON 
 co.person_id = op.person_id
 AND 
 co.condition_start_date >= op.observation_period_start_date
 AND 
 co.condition_start_date <= op.observation_period_end_date 
 GROUP BY 
 co.person_id
 ) co 
ON d.person_id = co.person_id
 ) t1;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_511
 ZORDER BY count_value;

--HINT DISTRIBUTE_ON_KEY(count_value)
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_512
USING DELTA
AS
WITH rawData(count_value)  AS (
SELECT 
 datediff(de.max_date,d.death_date) AS count_value
FROM 
 <CDM_DATABASE_NAME>.death d
JOIN (
 SELECT 
 de.person_id,
 MAX(de.drug_exposure_start_date) AS max_date
 FROM 
 <CDM_DATABASE_NAME>.drug_exposure de
 JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
 ON 
 de.person_id = op.person_id
 AND 
 de.drug_exposure_start_date >= op.observation_period_start_date
 AND 
 de.drug_exposure_start_date <= op.observation_period_end_date 
 GROUP BY 
 de.person_id
 ) de
ON 
 d.person_id = de.person_id
),
overallStats (avg_value, stdev_value, min_value, max_value, total) as
(
 select CAST(avg(1.0 * count_value) AS FLOAT) as avg_value,
 CAST(STDDEV(count_value) AS FLOAT) as stdev_value,
 min(count_value) as min_value,
 max(count_value) as max_value,
 COUNT(*) as total
 from rawData
),
statsView (count_value, total, rn) as
(
 select count_value, 
 COUNT(*) as total, 
 row_number() over (order by count_value) as rn
 FROM rawData
 group by count_value
),
priorStats (count_value, total, accumulated) as
(
 select s.count_value, s.total, sum(p.total) as accumulated
 from statsView s
 join statsView p on p.rn <= s.rn
 group by s.count_value, s.total, s.rn
)
 SELECT
512 as analysis_id,
 o.total as count_value,
 o.min_value,
 o.max_value,
 o.avg_value,
 o.stdev_value,
 MIN(case when p.accumulated >= .50 * o.total then count_value else o.max_value end) as median_value,
 MIN(case when p.accumulated >= .10 * o.total then count_value else o.max_value end) as p10_value,
 MIN(case when p.accumulated >= .25 * o.total then count_value else o.max_value end) as p25_value,
 MIN(case when p.accumulated >= .75 * o.total then count_value else o.max_value end) as p75_value,
 MIN(case when p.accumulated >= .90 * o.total then count_value else o.max_value end) as p90_value
FROM
priorStats p
CROSS JOIN overallStats o
GROUP BY o.total, o.min_value, o.max_value, o.avg_value, o.stdev_value
;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_512
 ZORDER BY count_value;
--HINT DISTRIBUTE_ON_KEY(count_value) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_512
USING DELTA
AS
SELECT
analysis_id, 
cast(null as STRING) as stratum_1, cast(null as STRING) as stratum_2, cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
count_value, min_value, max_value, avg_value, stdev_value, median_value, p10_value, p25_value, p75_value, p90_value
FROM
<ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_512
;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_512
 ZORDER BY count_value;
truncate table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_512;
drop table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_512;

--HINT DISTRIBUTE_ON_KEY(count_value)
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_513
USING DELTA
AS
WITH rawData(count_value)  AS (
SELECT 
 datediff(vo.max_date,d.death_date) AS count_value
FROM 
 <CDM_DATABASE_NAME>.death d
JOIN (
 SELECT 
 vo.person_id,
 MAX(vo.visit_start_date) AS max_date
 FROM 
 <CDM_DATABASE_NAME>.visit_occurrence vo
 JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
 ON 
 vo.person_id = op.person_id
 AND 
 vo.visit_start_date >= op.observation_period_start_date
 AND 
 vo.visit_start_date <= op.observation_period_end_date 
 GROUP BY 
 vo.person_id
 ) vo
ON 
 d.person_id = vo.person_id
),
overallStats (avg_value, stdev_value, min_value, max_value, total) as
(
 select CAST(avg(1.0 * count_value) AS FLOAT) as avg_value,
 CAST(STDDEV(count_value) AS FLOAT) as stdev_value,
 min(count_value) as min_value,
 max(count_value) as max_value,
 COUNT(*) as total
 from rawData
),
statsView (count_value, total, rn) as
(
 select count_value, 
 COUNT(*) as total, 
 row_number() over (order by count_value) as rn
 FROM rawData
 group by count_value
),
priorStats (count_value, total, accumulated) as
(
 select s.count_value, s.total, sum(p.total) as accumulated
 from statsView s
 join statsView p on p.rn <= s.rn
 group by s.count_value, s.total, s.rn
)
 SELECT
513 as analysis_id,
 o.total as count_value,
 o.min_value,
 o.max_value,
 o.avg_value,
 o.stdev_value,
 MIN(case when p.accumulated >= .50 * o.total then count_value else o.max_value end) as median_value,
 MIN(case when p.accumulated >= .10 * o.total then count_value else o.max_value end) as p10_value,
 MIN(case when p.accumulated >= .25 * o.total then count_value else o.max_value end) as p25_value,
 MIN(case when p.accumulated >= .75 * o.total then count_value else o.max_value end) as p75_value,
 MIN(case when p.accumulated >= .90 * o.total then count_value else o.max_value end) as p90_value
FROM
priorStats p
CROSS JOIN overallStats o
GROUP BY o.total, o.min_value, o.max_value, o.avg_value, o.stdev_value
;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_513
 ZORDER BY count_value;
--HINT DISTRIBUTE_ON_KEY(count_value) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_513
USING DELTA
AS
SELECT
analysis_id, 
cast(null as STRING) as stratum_1, cast(null as STRING) as stratum_2, cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
count_value, min_value, max_value, avg_value, stdev_value, median_value, p10_value, p25_value, p75_value, p90_value
FROM
<ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_513
;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_513
 ZORDER BY count_value;
truncate table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_513;
drop table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_513;

--HINT DISTRIBUTE_ON_KEY(count_value)
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_514
USING DELTA
AS
WITH rawData(count_value)  AS (
SELECT 
 datediff(po.max_date,d.death_date) AS count_value
FROM 
 <CDM_DATABASE_NAME>.death d
JOIN (
 SELECT 
 po.person_id,
 MAX(po.procedure_date) AS max_date
 FROM 
 <CDM_DATABASE_NAME>.procedure_occurrence po
 JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
 ON 
 po.person_id = op.person_id
 AND 
 po.procedure_date >= op.observation_period_start_date
 AND 
 po.procedure_date <= op.observation_period_end_date 
 GROUP BY 
 po.person_id
 ) po
ON 
 d.person_id = po.person_id
),
overallStats (avg_value, stdev_value, min_value, max_value, total) as
(
 select CAST(avg(1.0 * count_value) AS FLOAT) as avg_value,
 CAST(STDDEV(count_value) AS FLOAT) as stdev_value,
 min(count_value) as min_value,
 max(count_value) as max_value,
 COUNT(*) as total
 from rawData
),
statsView (count_value, total, rn) as
(
 select count_value, 
 COUNT(*) as total, 
 row_number() over (order by count_value) as rn
 FROM rawData
 group by count_value
),
priorStats (count_value, total, accumulated) as
(
 select s.count_value, s.total, sum(p.total) as accumulated
 from statsView s
 join statsView p on p.rn <= s.rn
 group by s.count_value, s.total, s.rn
)
 SELECT
514 as analysis_id,
 o.total as count_value,
 o.min_value,
 o.max_value,
 o.avg_value,
 o.stdev_value,
 MIN(case when p.accumulated >= .50 * o.total then count_value else o.max_value end) as median_value,
 MIN(case when p.accumulated >= .10 * o.total then count_value else o.max_value end) as p10_value,
 MIN(case when p.accumulated >= .25 * o.total then count_value else o.max_value end) as p25_value,
 MIN(case when p.accumulated >= .75 * o.total then count_value else o.max_value end) as p75_value,
 MIN(case when p.accumulated >= .90 * o.total then count_value else o.max_value end) as p90_value
FROM
priorStats p
CROSS JOIN overallStats o
GROUP BY o.total, o.min_value, o.max_value, o.avg_value, o.stdev_value
;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_514
 ZORDER BY count_value;
--HINT DISTRIBUTE_ON_KEY(count_value) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_514
USING DELTA
AS
SELECT
analysis_id, 
cast(null as STRING) as stratum_1, cast(null as STRING) as stratum_2, cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
count_value, min_value, max_value, avg_value, stdev_value, median_value, p10_value, p25_value, p75_value, p90_value
FROM
<ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_514
;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_514
 ZORDER BY count_value;
truncate table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_514;
drop table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_514;

--HINT DISTRIBUTE_ON_KEY(count_value)
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_515
USING DELTA
AS
WITH rawData(count_value)  AS (
SELECT 
 datediff(o.max_date,d.death_date) AS count_value
FROM 
 <CDM_DATABASE_NAME>.death d
JOIN (
 SELECT 
 o.person_id,
 MAX(o.observation_date) AS max_date
 FROM 
 <CDM_DATABASE_NAME>.observation o
 JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
 ON 
 o.person_id = op.person_id
 AND 
 o.observation_date >= op.observation_period_start_date
 AND 
 o.observation_date <= op.observation_period_end_date 
 GROUP BY 
 o.person_id
 ) o
ON 
 d.person_id = o.person_id
),
overallStats (avg_value, stdev_value, min_value, max_value, total) as
(
 select CAST(avg(1.0 * count_value) AS FLOAT) as avg_value,
 CAST(STDDEV(count_value) AS FLOAT) as stdev_value,
 min(count_value) as min_value,
 max(count_value) as max_value,
 COUNT(*) as total
 from rawData
),
statsView (count_value, total, rn) as
(
 select count_value, 
 COUNT(*) as total, 
 row_number() over (order by count_value) as rn
 FROM rawData
 group by count_value
),
priorStats (count_value, total, accumulated) as
(
 select s.count_value, s.total, sum(p.total) as accumulated
 from statsView s
 join statsView p on p.rn <= s.rn
 group by s.count_value, s.total, s.rn
)
 SELECT
515 as analysis_id,
 o.total as count_value,
 o.min_value,
 o.max_value,
 o.avg_value,
 o.stdev_value,
 MIN(case when p.accumulated >= .50 * o.total then count_value else o.max_value end) as median_value,
 MIN(case when p.accumulated >= .10 * o.total then count_value else o.max_value end) as p10_value,
 MIN(case when p.accumulated >= .25 * o.total then count_value else o.max_value end) as p25_value,
 MIN(case when p.accumulated >= .75 * o.total then count_value else o.max_value end) as p75_value,
 MIN(case when p.accumulated >= .90 * o.total then count_value else o.max_value end) as p90_value
FROM
priorStats p
CROSS JOIN overallStats o
GROUP BY o.total, o.min_value, o.max_value, o.avg_value, o.stdev_value
;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_515
 ZORDER BY count_value;
--HINT DISTRIBUTE_ON_KEY(count_value) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_515
USING DELTA
AS
SELECT
analysis_id, 
cast(null as STRING) as stratum_1, cast(null as STRING) as stratum_2, cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
count_value, min_value, max_value, avg_value, stdev_value, median_value, p10_value, p25_value, p75_value, p90_value
FROM
<ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_515
;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_515
 ZORDER BY count_value;
truncate table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_515;
drop table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_515;

--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_525 
USING DELTA
AS
SELECT
525 as analysis_id,
 cast(cause_source_concept_id AS STRING) AS stratum_1,
 cast(null AS STRING) AS stratum_2,
 cast(null as STRING) as stratum_3,
 cast(null as STRING) as stratum_4,
 cast(null as STRING) as stratum_5,
 COUNT(*) AS count_value
FROM
<CDM_DATABASE_NAME>.death
 group by cause_source_concept_id;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_525 
  ZORDER BY stratum_1;

CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_530
USING DELTA
AS
SELECT
530 AS analysis_id,
 CAST(NULL AS STRING) AS stratum_1,
 CAST(NULL AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 COUNT(*) AS count_value
FROM
<CDM_DATABASE_NAME>.death d
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 d.person_id = op.person_id
AND 
 d.death_date >= op.observation_period_start_date
AND 
 d.death_date <= op.observation_period_end_date;

DROP VIEW IF EXISTS op_outside  ; 
CREATE TEMPORARY VIEW op_outside   AS (SELECT 
 COUNT(DISTINCT d.person_id) AS person_count
FROM 
 <CDM_DATABASE_NAME>.death d
LEFT JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 d.person_id = op.person_id
AND 
 d.death_date >= op.observation_period_start_date
AND 
 d.death_date <= op.observation_period_end_date
WHERE
 op.person_id IS NULL
);
DROP VIEW IF EXISTS death_total ; 
CREATE TEMPORARY VIEW death_total  AS (SELECT
 COUNT(DISTINCT person_id) person_count
FROM
 <CDM_DATABASE_NAME>.death
);
 CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_531
USING DELTA
AS
(SELECT
531 AS analysis_id,
 CASE WHEN dt.person_count != 0 THEN
 CAST(CAST(1.0*op.person_count/dt.person_count AS FLOAT) AS STRING)
 ELSE 
 CAST(NULL AS STRING)
 END AS stratum_1, 
 CAST(op.person_count AS STRING) AS stratum_2,
 CAST(dt.person_count AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 SIGN(op.person_count) AS count_value
FROM
op_outside op
CROSS JOIN 
 death_total dt);

DROP VIEW IF EXISTS op_outside  ; 
CREATE TEMPORARY VIEW op_outside   AS (SELECT 
 COUNT(*) AS record_count
FROM 
 <CDM_DATABASE_NAME>.death d
LEFT JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 d.person_id = op.person_id
AND 
 d.death_date >= op.observation_period_start_date
AND 
 d.death_date <= op.observation_period_end_date
WHERE
 op.person_id IS NULL
);
DROP VIEW IF EXISTS death_total ; 
CREATE TEMPORARY VIEW death_total  AS (SELECT
 COUNT(*) record_count
FROM
 <CDM_DATABASE_NAME>.death
);
 CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_532
USING DELTA
AS
(SELECT
532 AS analysis_id,
 CASE WHEN dt.record_count != 0 THEN
 CAST(CAST(1.0*op.record_count/dt.record_count AS NUMERIC(7,6)) AS STRING) 
 ELSE 
 CAST(NULL AS STRING)
 END AS stratum_1, 
 CAST(op.record_count AS STRING) AS stratum_2,
 CAST(dt.record_count AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 SIGN(op.record_count) AS count_value
FROM
op_outside op
CROSS JOIN 
 death_total dt);

--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_600
USING DELTA
AS
SELECT
600 AS analysis_id,
 CAST(po.procedure_concept_id AS STRING) AS stratum_1,
 CAST(NULL AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 COUNT(DISTINCT po.person_id) AS count_value
FROM
<CDM_DATABASE_NAME>.procedure_occurrence po
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 po.person_id = op.person_id
AND 
 po.procedure_date >= op.observation_period_start_date
AND 
 po.procedure_date <= op.observation_period_end_date
GROUP BY 
 po.procedure_concept_id;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_600
 ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_601
USING DELTA
AS
SELECT
601 AS analysis_id,
 CAST(po.procedure_concept_id AS STRING) AS stratum_1,
 CAST(NULL AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 COUNT(po.person_id) AS count_value
FROM
<CDM_DATABASE_NAME>.procedure_occurrence po
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 po.person_id = op.person_id
AND 
 po.procedure_date >= op.observation_period_start_date
AND 
 po.procedure_date <= op.observation_period_end_date
GROUP BY 
 po.procedure_concept_id;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_601
 ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(stratum_1)
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_602
USING DELTA
AS
WITH rawData  AS (
SELECT 
 po.procedure_concept_id AS stratum_1,
 YEAR(po.procedure_date) * 100 + MONTH(po.procedure_date) AS stratum_2,
 COUNT(DISTINCT po.person_id) AS count_value
FROM 
 <CDM_DATABASE_NAME>.procedure_occurrence po
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 po.person_id = op.person_id
AND 
 po.procedure_date >= op.observation_period_start_date
AND 
 po.procedure_date <= op.observation_period_end_date
GROUP BY 
 po.procedure_concept_id,
 YEAR(po.procedure_date) * 100 + MONTH(po.procedure_date)
)
 SELECT
602 AS analysis_id,
 CAST(stratum_1 AS STRING) AS stratum_1,
 CAST(stratum_2 AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 count_value
FROM
rawData;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_602
 ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(count_value)
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_603
USING DELTA
AS
WITH rawData(count_value)  AS (
SELECT 
 COUNT(DISTINCT po.procedure_concept_id) AS count_value
FROM 
 <CDM_DATABASE_NAME>.procedure_occurrence po
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 po.person_id = op.person_id
AND 
 po.procedure_date >= op.observation_period_start_date
AND 
 po.procedure_date <= op.observation_period_end_date
GROUP BY 
 po.person_id
),
overallStats (avg_value, stdev_value, min_value, max_value, total) as
(
 select CAST(avg(1.0 * count_value) AS FLOAT) as avg_value,
 CAST(STDDEV(count_value) AS FLOAT) as stdev_value,
 min(count_value) as min_value,
 max(count_value) as max_value,
 COUNT(*) as total
 from rawData
),
statsView (count_value, total, rn) as
(
 select count_value, 
 COUNT(*) as total, 
 row_number() over (order by count_value) as rn
 FROM rawData
 group by count_value
),
priorStats (count_value, total, accumulated) as
(
 select s.count_value, s.total, sum(p.total) as accumulated
 from statsView s
 join statsView p on p.rn <= s.rn
 group by s.count_value, s.total, s.rn
)
 SELECT
603 as analysis_id,
 o.total as count_value,
 o.min_value,
 o.max_value,
 o.avg_value,
 o.stdev_value,
 MIN(case when p.accumulated >= .50 * o.total then count_value else o.max_value end) as median_value,
 MIN(case when p.accumulated >= .10 * o.total then count_value else o.max_value end) as p10_value,
 MIN(case when p.accumulated >= .25 * o.total then count_value else o.max_value end) as p25_value,
 MIN(case when p.accumulated >= .75 * o.total then count_value else o.max_value end) as p75_value,
 MIN(case when p.accumulated >= .90 * o.total then count_value else o.max_value end) as p90_value
FROM
priorStats p
CROSS JOIN overallStats o
GROUP BY o.total, o.min_value, o.max_value, o.avg_value, o.stdev_value
;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_603
 ZORDER BY count_value;
--HINT DISTRIBUTE_ON_KEY(count_value) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_603
USING DELTA
AS
SELECT
analysis_id, 
cast(null as STRING) as stratum_1, cast(null as STRING) as stratum_2, cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
count_value, min_value, max_value, avg_value, stdev_value, median_value, p10_value, p25_value, p75_value, p90_value
FROM
<ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_603
;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_603
 ZORDER BY count_value;
truncate table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_603;
drop table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_603;

--HINT DISTRIBUTE_ON_KEY(stratum_1)
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_604
USING DELTA
AS
WITH rawData  AS (
SELECT 
 po.procedure_concept_id AS stratum_1,
 YEAR(po.procedure_date) AS stratum_2,
 p.gender_concept_id AS stratum_3,
 FLOOR((YEAR(po.procedure_date) - p.year_of_birth) / 10) AS stratum_4,
 COUNT(DISTINCT p.person_id) AS count_value
FROM 
 <CDM_DATABASE_NAME>.person p
JOIN 
 <CDM_DATABASE_NAME>.procedure_occurrence po 
ON 
 p.person_id = po.person_id
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 po.person_id = op.person_id
AND 
 po.procedure_date >= op.observation_period_start_date
AND 
 po.procedure_date <= op.observation_period_end_date
GROUP BY 
 po.procedure_concept_id,
 YEAR(po.procedure_date),
 p.gender_concept_id,
 FLOOR((YEAR(po.procedure_date) - p.year_of_birth) / 10)
)
 SELECT
604 AS analysis_id,
 CAST(stratum_1 AS STRING) AS stratum_1,
 CAST(stratum_2 AS STRING) AS stratum_2,
 CAST(stratum_3 AS STRING) AS stratum_3,
 CAST(stratum_4 AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 count_value
FROM
rawData;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_604
 ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_605
USING DELTA
AS
SELECT
605 AS analysis_id,
 CAST(po.procedure_CONCEPT_ID AS STRING) AS stratum_1,
 CAST(po.procedure_type_concept_id AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 COUNT(po.person_id) AS count_value
FROM
<CDM_DATABASE_NAME>.procedure_occurrence po
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 po.person_id = op.person_id
AND 
 po.procedure_date >= op.observation_period_start_date
AND 
 po.procedure_date <= op.observation_period_end_date
GROUP BY 
 po.procedure_CONCEPT_ID,
 po.procedure_type_concept_id;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_605
 ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(subject_id) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1orawData_606
USING DELTA
AS
SELECT
po.procedure_concept_id AS subject_id,
 p.gender_concept_id,
 po.procedure_start_year - p.year_of_birth AS count_value
FROM
<CDM_DATABASE_NAME>.person p
JOIN (
 SELECT 
 po.person_id,
 po.procedure_concept_id,
 MIN(YEAR(po.procedure_date)) AS procedure_start_year
 FROM 
 <CDM_DATABASE_NAME>.procedure_occurrence po
 JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
 ON 
 po.person_id = op.person_id
 AND 
 po.procedure_date >= op.observation_period_start_date
 AND 
 po.procedure_date <= op.observation_period_end_date
 GROUP BY 
 po.person_id,
 po.procedure_concept_id
 ) po 
ON 
 p.person_id = po.person_id
;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1orawData_606
 ZORDER BY subject_id;
--HINT DISTRIBUTE_ON_KEY(stratum1_id)
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_606
USING DELTA
AS
WITH overallStats (stratum1_id, stratum2_id, avg_value, stdev_value, min_value, max_value, total)  AS (
 select subject_id as stratum1_id,
 gender_concept_id as stratum2_id,
 CAST(avg(1.0 * count_value) AS FLOAT) as avg_value,
 CAST(STDDEV(count_value) AS FLOAT) as stdev_value,
 min(count_value) as min_value,
 max(count_value) as max_value,
 COUNT(*) as total
 FROM <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1orawData_606
 group by subject_id, gender_concept_id
),
statsView (stratum1_id, stratum2_id, count_value, total, rn) as
(
 select subject_id as stratum1_id, gender_concept_id as stratum2_id, count_value, COUNT(*) as total, row_number() over (partition by subject_id, gender_concept_id order by count_value) as rn
 FROM <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1orawData_606
 group by subject_id, gender_concept_id, count_value
),
priorStats (stratum1_id, stratum2_id, count_value, total, accumulated) as
(
 select s.stratum1_id, s.stratum2_id, s.count_value, s.total, sum(p.total) as accumulated
 from statsView s
 join statsView p on s.stratum1_id = p.stratum1_id and s.stratum2_id = p.stratum2_id and p.rn <= s.rn
 group by s.stratum1_id, s.stratum2_id, s.count_value, s.total, s.rn
)
 SELECT
606 as analysis_id,
 CAST(o.stratum1_id AS STRING) AS stratum1_id,
 CAST(o.stratum2_id AS STRING) AS stratum2_id,
 o.total as count_value,
 o.min_value,
 o.max_value,
 o.avg_value,
 o.stdev_value,
 MIN(case when p.accumulated >= .50 * o.total then count_value else o.max_value end) as median_value,
 MIN(case when p.accumulated >= .10 * o.total then count_value else o.max_value end) as p10_value,
 MIN(case when p.accumulated >= .25 * o.total then count_value else o.max_value end) as p25_value,
 MIN(case when p.accumulated >= .75 * o.total then count_value else o.max_value end) as p75_value,
 MIN(case when p.accumulated >= .90 * o.total then count_value else o.max_value end) as p90_value
FROM
priorStats p
join overallStats o on p.stratum1_id = o.stratum1_id and p.stratum2_id = o.stratum2_id 
GROUP BY o.stratum1_id, o.stratum2_id, o.total, o.min_value, o.max_value, o.avg_value, o.stdev_value
;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_606
 ZORDER BY stratum1_id;
--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_606
USING DELTA
AS
SELECT
analysis_id, stratum1_id as stratum_1, stratum2_id as stratum_2, 
cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
count_value, min_value, max_value, avg_value, stdev_value, median_value, p10_value, p25_value, p75_value, p90_value
FROM
<ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_606
;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_606
 ZORDER BY stratum_1;
truncate table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_606;
drop table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_606;
truncate table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1orawData_606;
drop table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1orawData_606;

CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_609
USING DELTA
AS
SELECT
609 as analysis_id, 
 cast(null as STRING) as stratum_1, cast(null as STRING) as stratum_2, cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
 COUNT(po1.PERSON_ID) as count_value
FROM
<CDM_DATABASE_NAME>.procedure_occurrence po1
 left join <CDM_DATABASE_NAME>.person p1
 on p1.person_id = po1.person_id
where p1.person_id is null;

CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_610
USING DELTA
AS
SELECT
610 as analysis_id, 
 cast(null as STRING) as stratum_1, cast(null as STRING) as stratum_2, cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
 COUNT(po1.PERSON_ID) as count_value
FROM
<CDM_DATABASE_NAME>.procedure_occurrence po1
 left join <CDM_DATABASE_NAME>.observation_period op1
 on op1.person_id = po1.person_id
 and po1.procedure_date >= op1.observation_period_start_date
 and po1.procedure_date <= op1.observation_period_end_date
where op1.person_id is null;

CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_612
USING DELTA
AS
SELECT
612 as analysis_id, 
 cast(null as STRING) as stratum_1, cast(null as STRING) as stratum_2, cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
 COUNT(po1.PERSON_ID) as count_value
FROM
<CDM_DATABASE_NAME>.procedure_occurrence po1
 left join <CDM_DATABASE_NAME>.provider p1
 on p1.provider_id = po1.provider_id
where po1.provider_id is not null
 and p1.provider_id is null;

CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_613
USING DELTA
AS
SELECT
613 as analysis_id, 
 cast(null as STRING) as stratum_1, cast(null as STRING) as stratum_2, cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
 COUNT(po1.PERSON_ID) as count_value
FROM
<CDM_DATABASE_NAME>.procedure_occurrence po1
 left join <CDM_DATABASE_NAME>.visit_occurrence vo1
 on po1.visit_occurrence_id = vo1.visit_occurrence_id
where po1.visit_occurrence_id is not null
 and vo1.visit_occurrence_id is null;

--HINT DISTRIBUTE_ON_KEY(stratum_1)
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_620
USING DELTA
AS
WITH rawData  AS (
SELECT 
 YEAR(po.procedure_date) * 100 + MONTH(po.procedure_date) AS stratum_1,
 COUNT(po.person_id) AS count_value
FROM
 <CDM_DATABASE_NAME>.procedure_occurrence po
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 po.person_id = op.person_id
AND 
 po.procedure_date >= op.observation_period_start_date
AND 
 po.procedure_date <= op.observation_period_end_date
GROUP BY 
 YEAR(po.procedure_date)*100 + MONTH(po.procedure_date)
)
 SELECT
620 AS analysis_id,
 CAST(stratum_1 AS STRING) AS stratum_1,
 CAST(NULL AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 count_value
FROM
rawData;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_620
 ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_625
USING DELTA
AS
SELECT
625 AS analysis_id,
 CAST(po.procedure_source_concept_id AS STRING) AS stratum_1,
 CAST(NULL AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 COUNT(*) AS count_value
FROM
<CDM_DATABASE_NAME>.procedure_occurrence po
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 po.person_id = op.person_id
AND 
 po.procedure_date >= op.observation_period_start_date
AND 
 po.procedure_date <= op.observation_period_end_date
GROUP BY 
 po.procedure_source_concept_id;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_625
 ZORDER BY stratum_1;

CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_630
USING DELTA
AS
SELECT
630 AS analysis_id,
 CAST(NULL AS STRING) AS stratum_1,
 CAST(NULL AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 COUNT(*) AS count_value
FROM
<CDM_DATABASE_NAME>.procedure_occurrence po
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 po.person_id = op.person_id
AND 
 po.procedure_date >= op.observation_period_start_date
AND 
 po.procedure_date <= op.observation_period_end_date;

DROP VIEW IF EXISTS op_outside  ; 
CREATE TEMPORARY VIEW op_outside   AS (SELECT 
 COUNT(DISTINCT po.person_id) AS person_count
FROM 
 <CDM_DATABASE_NAME>.procedure_occurrence po
LEFT JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 po.person_id = op.person_id
AND 
 po.procedure_date >= op.observation_period_start_date
AND 
 po.procedure_date <= op.observation_period_end_date
WHERE
 op.person_id IS NULL
);
DROP VIEW IF EXISTS po_total ; 
CREATE TEMPORARY VIEW po_total  AS (SELECT
 COUNT(DISTINCT person_id) person_count
FROM
 <CDM_DATABASE_NAME>.procedure_occurrence
);
 CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_631
USING DELTA
AS
(SELECT
631 AS analysis_id,
 CASE WHEN po.person_count != 0 THEN 
 CAST(CAST(1.0*op.person_count/po.person_count AS FLOAT) AS STRING) 
 ELSE 
 CAST(NULL AS STRING) 
 END AS stratum_1, 
 CAST(op.person_count AS STRING) AS stratum_2,
 CAST(po.person_count AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 SIGN(op.person_count) AS count_value
FROM
op_outside op
CROSS JOIN 
 po_total po);

DROP VIEW IF EXISTS op_outside  ; 
CREATE TEMPORARY VIEW op_outside   AS (SELECT 
 COUNT(*) AS record_count
FROM 
 <CDM_DATABASE_NAME>.procedure_occurrence po
LEFT JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 po.person_id = op.person_id
AND 
 po.procedure_date >= op.observation_period_start_date
AND 
 po.procedure_date <= op.observation_period_end_date
WHERE
 op.person_id IS NULL
);
DROP VIEW IF EXISTS po_total ; 
CREATE TEMPORARY VIEW po_total  AS (SELECT
 COUNT(*) record_count
FROM
 <CDM_DATABASE_NAME>.procedure_occurrence
);
 CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_632
USING DELTA
AS
(SELECT
632 AS analysis_id,
 CASE WHEN po.record_count != 0 THEN 
 CAST(CAST(1.0*op.record_count/po.record_count AS FLOAT) AS STRING) 
 ELSE 
 CAST(NULL AS STRING) 
 END AS stratum_1, 
 CAST(op.record_count AS STRING) AS stratum_2,
 CAST(po.record_count AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 SIGN(op.record_count) AS count_value
FROM
op_outside op
CROSS JOIN 
 po_total po);

--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_691
USING DELTA
AS
SELECT
691 AS analysis_id,
 CAST(po.procedure_concept_id AS STRING) AS stratum_1,
 CAST(po.prc_cnt AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 SUM(COUNT(po.person_id)) OVER (PARTITION BY po.procedure_concept_id ORDER BY po.prc_cnt DESC) AS count_value
FROM
(
 SELECT 
 po.procedure_concept_id,
 COUNT(po.procedure_occurrence_id) AS prc_cnt,
 po.person_id
 FROM 
 <CDM_DATABASE_NAME>.procedure_occurrence po
 JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
 ON 
 po.person_id = op.person_id
 AND 
 po.procedure_date >= op.observation_period_start_date
 AND 
 po.procedure_date <= op.observation_period_end_date
 GROUP BY 
 po.person_id,
 po.procedure_concept_id
 ) po
GROUP BY 
 po.procedure_concept_id,
 po.prc_cnt;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_691
 ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_700
USING DELTA
AS
SELECT
700 AS analysis_id,
 CAST(de.drug_concept_id AS STRING) AS stratum_1,
 CAST(NULL AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 COUNT(DISTINCT de.person_id) AS count_value
FROM
<CDM_DATABASE_NAME>.drug_exposure de
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 de.person_id = op.person_id
AND 
 de.drug_exposure_start_date >= op.observation_period_start_date
AND 
 de.drug_exposure_start_date <= op.observation_period_end_date
GROUP BY 
 de.drug_concept_id;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_700
 ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_701
USING DELTA
AS
SELECT
701 AS analysis_id,
 CAST(de.drug_concept_id AS STRING) AS stratum_1,
 CAST(NULL AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 COUNT(de.person_id) AS count_value
FROM
<CDM_DATABASE_NAME>.drug_exposure de
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 de.person_id = op.person_id
AND 
 de.drug_exposure_start_date >= op.observation_period_start_date
AND 
 de.drug_exposure_start_date <= op.observation_period_end_date
GROUP BY 
 de.drug_concept_id;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_701
 ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(stratum_1)
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_702
USING DELTA
AS
WITH rawData  AS (
SELECT 
 de.drug_concept_id AS stratum_1,
 YEAR(de.drug_exposure_start_date) * 100 + MONTH(de.drug_exposure_start_date) AS stratum_2,
 COUNT(DISTINCT de.person_id) AS count_value
FROM 
 <CDM_DATABASE_NAME>.drug_exposure de
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 de.person_id = op.person_id
AND 
 de.drug_exposure_start_date >= op.observation_period_start_date
AND 
 de.drug_exposure_start_date <= op.observation_period_end_date
GROUP BY 
 de.drug_concept_id,
 YEAR(de.drug_exposure_start_date) * 100 + MONTH(de.drug_exposure_start_date)
)
 SELECT
702 AS analysis_id,
 CAST(stratum_1 AS STRING) AS stratum_1,
 CAST(stratum_2 AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 count_value
FROM
rawData;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_702
 ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(count_value)
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_703
USING DELTA
AS
WITH rawData(count_value)  AS (
SELECT 
 COUNT(DISTINCT de.drug_concept_id) AS count_value
FROM 
 <CDM_DATABASE_NAME>.drug_exposure de
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 de.person_id = op.person_id
AND 
 de.drug_exposure_start_date >= op.observation_period_start_date
AND 
 de.drug_exposure_start_date <= op.observation_period_end_date
GROUP BY 
 de.person_id
),
overallStats (avg_value, stdev_value, min_value, max_value, total) as
(
 select CAST(avg(1.0 * count_value) AS FLOAT) as avg_value,
 CAST(STDDEV(count_value) AS FLOAT) as stdev_value,
 min(count_value) as min_value,
 max(count_value) as max_value,
 COUNT(*) as total
 from rawData
),
statsView (count_value, total, rn) as
(
 select count_value, 
 COUNT(*) as total, 
 row_number() over (order by count_value) as rn
 FROM rawData
 group by count_value
),
priorStats (count_value, total, accumulated) as
(
 select s.count_value, s.total, sum(p.total) as accumulated
 from statsView s
 join statsView p on p.rn <= s.rn
 group by s.count_value, s.total, s.rn
)
 SELECT
703 as analysis_id,
 o.total as count_value,
 o.min_value,
 o.max_value,
 o.avg_value,
 o.stdev_value,
 MIN(case when p.accumulated >= .50 * o.total then count_value else o.max_value end) as median_value,
 MIN(case when p.accumulated >= .10 * o.total then count_value else o.max_value end) as p10_value,
 MIN(case when p.accumulated >= .25 * o.total then count_value else o.max_value end) as p25_value,
 MIN(case when p.accumulated >= .75 * o.total then count_value else o.max_value end) as p75_value,
 MIN(case when p.accumulated >= .90 * o.total then count_value else o.max_value end) as p90_value
FROM
priorStats p
CROSS JOIN overallStats o
GROUP BY o.total, o.min_value, o.max_value, o.avg_value, o.stdev_value
;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_703
 ZORDER BY count_value;
--HINT DISTRIBUTE_ON_KEY(count_value) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_703
USING DELTA
AS
SELECT
analysis_id, 
cast(null as STRING) as stratum_1, cast(null as STRING) as stratum_2, cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
count_value, min_value, max_value, avg_value, stdev_value, median_value, p10_value, p25_value, p75_value, p90_value
FROM
<ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_703
;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_703
 ZORDER BY count_value;
truncate table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_703;
drop table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_703;

--HINT DISTRIBUTE_ON_KEY(stratum_1)
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_704
USING DELTA
AS
WITH rawData  AS (
SELECT 
 de.drug_concept_id AS stratum_1,
 YEAR(de.drug_exposure_start_date) AS stratum_2,
 p.gender_concept_id AS stratum_3,
 FLOOR((YEAR(de.drug_exposure_start_date) - p.year_of_birth) / 10) AS stratum_4,
 COUNT(DISTINCT p.person_id) AS count_value
FROM 
 <CDM_DATABASE_NAME>.person p
JOIN 
 <CDM_DATABASE_NAME>.drug_exposure de 
ON 
 p.person_id = de.person_id
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 de.person_id = op.person_id
AND 
 de.drug_exposure_start_date >= op.observation_period_start_date
AND 
 de.drug_exposure_start_date <= op.observation_period_end_date
GROUP BY 
 de.drug_concept_id,
 YEAR(de.drug_exposure_start_date),
 p.gender_concept_id,
 FLOOR((YEAR(de.drug_exposure_start_date) - p.year_of_birth) / 10)
)
 SELECT
704 AS analysis_id,
 CAST(stratum_1 AS STRING) AS stratum_1,
 CAST(stratum_2 AS STRING) AS stratum_2,
 CAST(stratum_3 AS STRING) AS stratum_3,
 CAST(stratum_4 AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 count_value
FROM
rawData;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_704
 ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_705
USING DELTA
AS
SELECT
705 AS analysis_id,
 CAST(de.drug_concept_id AS STRING) AS stratum_1,
 CAST(de.drug_type_concept_id AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 COUNT(de.person_id) AS count_value
FROM
<CDM_DATABASE_NAME>.drug_exposure de
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 de.person_id = op.person_id
AND 
 de.drug_exposure_start_date >= op.observation_period_start_date
AND 
 de.drug_exposure_start_date <= op.observation_period_end_date
GROUP BY 
 de.drug_CONCEPT_ID,
 de.drug_type_concept_id;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_705
 ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(subject_id) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1orawData_706
USING DELTA
AS
SELECT
de.drug_concept_id AS subject_id,
 p.gender_concept_id,
 de.drug_start_year - p.year_of_birth AS count_value
FROM
<CDM_DATABASE_NAME>.person p
JOIN (
 SELECT 
 de.person_id,
 de.drug_concept_id,
 MIN(YEAR(de.drug_exposure_start_date)) AS drug_start_year
 FROM 
 <CDM_DATABASE_NAME>.drug_exposure de
 JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
 ON 
 de.person_id = op.person_id
 AND 
 de.drug_exposure_start_date >= op.observation_period_start_date
 AND 
 de.drug_exposure_start_date <= op.observation_period_end_date
 GROUP BY 
 de.person_id,
 de.drug_concept_id
 ) de 
ON 
 p.person_id = de.person_id
;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1orawData_706
 ZORDER BY subject_id;
--HINT DISTRIBUTE_ON_KEY(stratum1_id)
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_706
USING DELTA
AS
WITH overallStats (stratum1_id, stratum2_id, avg_value, stdev_value, min_value, max_value, total)  AS (
 select subject_id as stratum1_id,
 gender_concept_id as stratum2_id,
 CAST(avg(1.0 * count_value) AS FLOAT) as avg_value,
 CAST(STDDEV(count_value) AS FLOAT) as stdev_value,
 min(count_value) as min_value,
 max(count_value) as max_value,
 COUNT(*) as total
 FROM <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1orawData_706
 group by subject_id, gender_concept_id
),
statsView (stratum1_id, stratum2_id, count_value, total, rn) as
(
 select subject_id as stratum1_id, gender_concept_id as stratum2_id, count_value, COUNT(*) as total, row_number() over (partition by subject_id, gender_concept_id order by count_value) as rn
 FROM <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1orawData_706
 group by subject_id, gender_concept_id, count_value
),
priorStats (stratum1_id, stratum2_id, count_value, total, accumulated) as
(
 select s.stratum1_id, s.stratum2_id, s.count_value, s.total, sum(p.total) as accumulated
 from statsView s
 join statsView p on s.stratum1_id = p.stratum1_id and s.stratum2_id = p.stratum2_id and p.rn <= s.rn
 group by s.stratum1_id, s.stratum2_id, s.count_value, s.total, s.rn
)
 SELECT
706 as analysis_id,
 CAST(o.stratum1_id AS STRING) AS stratum1_id,
 CAST(o.stratum2_id AS STRING) AS stratum2_id,
 o.total as count_value,
 o.min_value,
 o.max_value,
 o.avg_value,
 o.stdev_value,
 MIN(case when p.accumulated >= .50 * o.total then count_value else o.max_value end) as median_value,
 MIN(case when p.accumulated >= .10 * o.total then count_value else o.max_value end) as p10_value,
 MIN(case when p.accumulated >= .25 * o.total then count_value else o.max_value end) as p25_value,
 MIN(case when p.accumulated >= .75 * o.total then count_value else o.max_value end) as p75_value,
 MIN(case when p.accumulated >= .90 * o.total then count_value else o.max_value end) as p90_value
FROM
priorStats p
join overallStats o on p.stratum1_id = o.stratum1_id and p.stratum2_id = o.stratum2_id 
GROUP BY o.stratum1_id, o.stratum2_id, o.total, o.min_value, o.max_value, o.avg_value, o.stdev_value
;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_706
 ZORDER BY stratum1_id;
--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_706
USING DELTA
AS
SELECT
analysis_id, stratum1_id as stratum_1, stratum2_id as stratum_2, 
cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
count_value, min_value, max_value, avg_value, stdev_value, median_value, p10_value, p25_value, p75_value, p90_value
FROM
<ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_706
;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_706
 ZORDER BY stratum_1;
truncate table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1orawData_706;
drop table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1orawData_706;
truncate table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_706;
drop table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_706;

CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_709
USING DELTA
AS
SELECT
709 as analysis_id, 
 cast(null as STRING) as stratum_1, cast(null as STRING) as stratum_2, cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
 COUNT(de1.PERSON_ID) as count_value
FROM
<CDM_DATABASE_NAME>.drug_exposure de1
 left join <CDM_DATABASE_NAME>.person p1
 on p1.person_id = de1.person_id
where p1.person_id is null;

CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_710
USING DELTA
AS
SELECT
710 as analysis_id, 
 cast(null as STRING) as stratum_1, cast(null as STRING) as stratum_2, cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
 COUNT(de1.PERSON_ID) as count_value
FROM
<CDM_DATABASE_NAME>.drug_exposure de1
 left join <CDM_DATABASE_NAME>.observation_period op1
 on op1.person_id = de1.person_id
 and de1.drug_exposure_start_date >= op1.observation_period_start_date
 and de1.drug_exposure_start_date <= op1.observation_period_end_date
where op1.person_id is null;

CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_711
USING DELTA
AS
SELECT
711 as analysis_id, 
 cast(null as STRING) as stratum_1, cast(null as STRING) as stratum_2, cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
 COUNT(de1.PERSON_ID) as count_value
FROM
<CDM_DATABASE_NAME>.drug_exposure de1
where de1.drug_exposure_end_date < de1.drug_exposure_start_date;

CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_712
USING DELTA
AS
SELECT
712 as analysis_id, 
 cast(null as STRING) as stratum_1, cast(null as STRING) as stratum_2, cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
 COUNT(de1.PERSON_ID) as count_value
FROM
<CDM_DATABASE_NAME>.drug_exposure de1
 left join <CDM_DATABASE_NAME>.provider p1
 on p1.provider_id = de1.provider_id
where de1.provider_id is not null
 and p1.provider_id is null;

CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_713
USING DELTA
AS
SELECT
713 as analysis_id, 
 cast(null as STRING) as stratum_1, cast(null as STRING) as stratum_2, cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
 COUNT(de1.PERSON_ID) as count_value
FROM
<CDM_DATABASE_NAME>.drug_exposure de1
 left join <CDM_DATABASE_NAME>.visit_occurrence vo1
 on de1.visit_occurrence_id = vo1.visit_occurrence_id
where de1.visit_occurrence_id is not null
 and vo1.visit_occurrence_id is null;

--HINT DISTRIBUTE_ON_KEY(stratum_id)
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_715
USING DELTA
AS
WITH rawData(stratum_id, count_value)  AS (
SELECT 
 de.drug_concept_id AS stratum_id,
 de.days_supply AS count_value
FROM 
 <CDM_DATABASE_NAME>.drug_exposure de
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 de.person_id = op.person_id
AND 
 de.drug_exposure_start_date >= op.observation_period_start_date
AND 
 de.drug_exposure_start_date <= op.observation_period_end_date
WHERE 
 de.days_supply IS NOT NULL
),
overallStats (stratum_id, avg_value, stdev_value, min_value, max_value, total) as
(
 select stratum_id,
 CAST(avg(1.0 * count_value) AS FLOAT) as avg_value,
 CAST(STDDEV(count_value) AS FLOAT) as stdev_value,
 min(count_value) as min_value,
 max(count_value) as max_value,
 COUNT(*) as total
 FROM rawData
 group by stratum_id
),
statsView (stratum_id, count_value, total, rn) as
(
 select stratum_id, count_value, COUNT(*) as total, row_number() over (partition by stratum_id order by count_value) as rn
 FROM rawData
 group by stratum_id, count_value
),
priorStats (stratum_id, count_value, total, accumulated) as
(
 select s.stratum_id, s.count_value, s.total, sum(p.total) as accumulated
 from statsView s
 join statsView p on s.stratum_id = p.stratum_id and p.rn <= s.rn
 group by s.stratum_id, s.count_value, s.total, s.rn
)
 SELECT
715 as analysis_id,
 CAST(o.stratum_id AS STRING) AS stratum_id,
 o.total as count_value,
 o.min_value,
 o.max_value,
 o.avg_value,
 o.stdev_value,
 MIN(case when p.accumulated >= .50 * o.total then count_value else o.max_value end) as median_value,
 MIN(case when p.accumulated >= .10 * o.total then count_value else o.max_value end) as p10_value,
 MIN(case when p.accumulated >= .25 * o.total then count_value else o.max_value end) as p25_value,
 MIN(case when p.accumulated >= .75 * o.total then count_value else o.max_value end) as p75_value,
 MIN(case when p.accumulated >= .90 * o.total then count_value else o.max_value end) as p90_value
FROM
priorStats p
join overallStats o on p.stratum_id = o.stratum_id
GROUP BY o.stratum_id, o.total, o.min_value, o.max_value, o.avg_value, o.stdev_value
;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_715
 ZORDER BY stratum_id;
--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_715
USING DELTA
AS
SELECT
analysis_id, stratum_id as stratum_1, 
cast(null as STRING) as stratum_2, cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
count_value, min_value, max_value, avg_value, stdev_value, median_value, p10_value, p25_value, p75_value, p90_value
FROM
<ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_715
;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_715
 ZORDER BY stratum_1;
truncate table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_715;
drop table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_715;

--HINT DISTRIBUTE_ON_KEY(stratum_id)
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_716
USING DELTA
AS
WITH rawData(stratum_id, count_value)  AS (
SELECT 
 de.drug_concept_id AS stratum_id,
 de.refills AS count_value
FROM 
 <CDM_DATABASE_NAME>.drug_exposure de
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 de.person_id = op.person_id
AND 
 de.drug_exposure_start_date >= op.observation_period_start_date
AND 
 de.drug_exposure_start_date <= op.observation_period_end_date
WHERE 
 de.refills IS NOT NULL
),
overallStats (stratum_id, avg_value, stdev_value, min_value, max_value, total) as
(
 select stratum_id,
 CAST(avg(1.0 * count_value) AS FLOAT) as avg_value,
 CAST(STDDEV(count_value) AS FLOAT) as stdev_value,
 min(count_value) as min_value,
 max(count_value) as max_value,
 COUNT(*) as total
 FROM rawData
 group by stratum_id
),
statsView (stratum_id, count_value, total, rn) as
(
 select stratum_id, count_value, COUNT(*) as total, row_number() over (partition by stratum_id order by count_value) as rn
 FROM rawData
 group by stratum_id, count_value
),
priorStats (stratum_id, count_value, total, accumulated) as
(
 select s.stratum_id, s.count_value, s.total, sum(p.total) as accumulated
 from statsView s
 join statsView p on s.stratum_id = p.stratum_id and p.rn <= s.rn
 group by s.stratum_id, s.count_value, s.total, s.rn
)
 SELECT
716 as analysis_id,
 CAST(o.stratum_id AS STRING) AS stratum_id,
 o.total as count_value,
 o.min_value,
 o.max_value,
 o.avg_value,
 o.stdev_value,
 MIN(case when p.accumulated >= .50 * o.total then count_value else o.max_value end) as median_value,
 MIN(case when p.accumulated >= .10 * o.total then count_value else o.max_value end) as p10_value,
 MIN(case when p.accumulated >= .25 * o.total then count_value else o.max_value end) as p25_value,
 MIN(case when p.accumulated >= .75 * o.total then count_value else o.max_value end) as p75_value,
 MIN(case when p.accumulated >= .90 * o.total then count_value else o.max_value end) as p90_value
FROM
priorStats p
join overallStats o on p.stratum_id = o.stratum_id
GROUP BY o.stratum_id, o.total, o.min_value, o.max_value, o.avg_value, o.stdev_value
;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_716
 ZORDER BY stratum_id;
--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_716
USING DELTA
AS
SELECT
analysis_id, stratum_id as stratum_1, 
cast(null as STRING) as stratum_2, cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
count_value, min_value, max_value, avg_value, stdev_value, median_value, p10_value, p25_value, p75_value, p90_value
FROM
<ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_716
;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_716
 ZORDER BY stratum_1;
truncate table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_716;
drop table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_716;

--HINT DISTRIBUTE_ON_KEY(stratum_id)
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_717
USING DELTA
AS
WITH rawData(stratum_id, count_value)  AS (
SELECT 
 de.drug_concept_id AS stratum_id,
 CAST(de.quantity AS FLOAT) AS count_value
FROM 
 <CDM_DATABASE_NAME>.drug_exposure de
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 de.person_id = op.person_id
AND 
 de.drug_exposure_start_date >= op.observation_period_start_date
AND 
 de.drug_exposure_start_date <= op.observation_period_end_date
WHERE 
 de.quantity IS NOT NULL
),
overallStats (stratum_id, avg_value, stdev_value, min_value, max_value, total) as
(
 select stratum_id,
 CAST(avg(1.0 * count_value) AS FLOAT) as avg_value,
 CAST(STDDEV(count_value) AS FLOAT) as stdev_value,
 min(count_value) as min_value,
 max(count_value) as max_value,
 COUNT(*) as total
 FROM rawData
 group by stratum_id
),
statsView (stratum_id, count_value, total, rn) as
(
 select stratum_id, count_value, COUNT(*) as total, row_number() over (order by count_value) as rn
 FROM rawData
 group by stratum_id, count_value
),
priorStats (stratum_id, count_value, total, accumulated) as
(
 select s.stratum_id, s.count_value, s.total, sum(p.total) as accumulated
 from statsView s
 join statsView p on s.stratum_id = p.stratum_id and p.rn <= s.rn
 group by s.stratum_id, s.count_value, s.total, s.rn
)
 SELECT
717 as analysis_id,
 CAST(o.stratum_id AS STRING) AS stratum_id,
 o.total as count_value,
 o.min_value,
 o.max_value,
 o.avg_value,
 o.stdev_value,
 MIN(case when p.accumulated >= .50 * o.total then count_value else o.max_value end) as median_value,
 MIN(case when p.accumulated >= .10 * o.total then count_value else o.max_value end) as p10_value,
 MIN(case when p.accumulated >= .25 * o.total then count_value else o.max_value end) as p25_value,
 MIN(case when p.accumulated >= .75 * o.total then count_value else o.max_value end) as p75_value,
 MIN(case when p.accumulated >= .90 * o.total then count_value else o.max_value end) as p90_value
FROM
priorStats p
join overallStats o on p.stratum_id = o.stratum_id
GROUP BY o.stratum_id, o.total, o.min_value, o.max_value, o.avg_value, o.stdev_value
;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_717
 ZORDER BY stratum_id;
--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_717
USING DELTA
AS
SELECT
analysis_id, stratum_id as stratum_1, 
cast(null as STRING) as stratum_2, cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
count_value, min_value, max_value, avg_value, stdev_value, median_value, p10_value, p25_value, p75_value, p90_value
FROM
<ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_717
;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_717
 ZORDER BY stratum_1;
truncate table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_717;
drop table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_717;

--HINT DISTRIBUTE_ON_KEY(stratum_1)
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_720
USING DELTA
AS
WITH rawData  AS (
SELECT 
 YEAR(de.drug_exposure_start_date) * 100 + MONTH(de.drug_exposure_start_date) AS stratum_1,
 COUNT(de.person_id) AS count_value
FROM 
 <CDM_DATABASE_NAME>.drug_exposure de
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 de.person_id = op.person_id
AND 
 de.drug_exposure_start_date >= op.observation_period_start_date
AND 
 de.drug_exposure_start_date <= op.observation_period_end_date 
GROUP BY 
 YEAR(de.drug_exposure_start_date)*100 + MONTH(de.drug_exposure_start_date)
)
 SELECT
720 AS analysis_id,
 CAST(stratum_1 AS STRING) AS stratum_1,
 CAST(NULL AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 count_value
FROM
rawData;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_720
 ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_725
USING DELTA
AS
SELECT
725 AS analysis_id,
 CAST(de.drug_source_concept_id AS STRING) AS stratum_1,
 CAST(NULL AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 COUNT(*) AS count_value
FROM
<CDM_DATABASE_NAME>.drug_exposure de
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 de.person_id = op.person_id
AND 
 de.drug_exposure_start_date >= op.observation_period_start_date
AND 
 de.drug_exposure_start_date <= op.observation_period_end_date
GROUP BY 
 de.drug_source_concept_id;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_725
 ZORDER BY stratum_1;

CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_730
USING DELTA
AS
SELECT
730 AS analysis_id,
 CAST(NULL AS STRING) AS stratum_1,
 CAST(NULL AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 COUNT(*) AS count_value
FROM
<CDM_DATABASE_NAME>.drug_exposure de
JOIN 
 <CDM_DATABASE_NAME>.observation_period op
ON 
 de.person_id = op.person_id
AND 
 de.drug_exposure_start_date >= op.observation_period_start_date
AND 
 de.drug_exposure_start_date <= op.observation_period_end_date;

DROP VIEW IF EXISTS op_outside  ; 
CREATE TEMPORARY VIEW op_outside   AS (SELECT 
 COUNT(DISTINCT de.person_id) AS person_count
FROM 
 <CDM_DATABASE_NAME>.drug_exposure de
LEFT JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 de.person_id = op.person_id
AND 
 de.drug_exposure_start_date >= op.observation_period_start_date
AND 
 de.drug_exposure_start_date <= op.observation_period_end_date
WHERE
 op.person_id IS NULL
);
DROP VIEW IF EXISTS de_total ; 
CREATE TEMPORARY VIEW de_total  AS (SELECT
 COUNT(DISTINCT person_id) person_count
FROM
 <CDM_DATABASE_NAME>.drug_exposure
);
 CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_731
USING DELTA
AS
(SELECT
731 AS analysis_id,
 CASE WHEN de.person_count != 0 THEN 
 CAST(CAST(1.0*op.person_count/de.person_count AS FLOAT) AS STRING) 
 ELSE 
 CAST(NULL AS STRING)
 END AS stratum_1, 
 CAST(op.person_count AS STRING) AS stratum_2,
 CAST(de.person_count AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 SIGN(op.person_count) AS count_value
FROM
op_outside op
CROSS JOIN 
 de_total de);

DROP VIEW IF EXISTS op_outside  ; 
CREATE TEMPORARY VIEW op_outside   AS (SELECT 
 COUNT(*) AS record_count
FROM 
 <CDM_DATABASE_NAME>.drug_exposure de
LEFT JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 de.person_id = op.person_id
AND 
 de.drug_exposure_start_date >= op.observation_period_start_date
AND 
 de.drug_exposure_start_date <= op.observation_period_end_date
WHERE
 op.person_id IS NULL
);
DROP VIEW IF EXISTS de_total ; 
CREATE TEMPORARY VIEW de_total  AS (SELECT
 COUNT(*) record_count
FROM
 <CDM_DATABASE_NAME>.drug_exposure
);
 CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_732
USING DELTA
AS
(SELECT
732 AS analysis_id,
 CASE WHEN de.record_count != 0 THEN 
 CAST(CAST(1.0*op.record_count/de.record_count AS FLOAT) AS STRING) 
 ELSE 
 CAST(NULL AS STRING) 
 END AS stratum_1, 
 CAST(op.record_count AS STRING) AS stratum_2,
 CAST(de.record_count AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 SIGN(op.record_count) AS count_value
FROM
op_outside op
CROSS JOIN 
 de_total de);

--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_791
USING DELTA
AS
SELECT
791 AS analysis_id,
 CAST(de.drug_concept_id AS STRING) AS stratum_1,
 CAST(de.drg_cnt AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 SUM(COUNT(de.person_id)) OVER (PARTITION BY de.drug_concept_id ORDER BY de.drg_cnt DESC) AS count_value
FROM
(
 SELECT 
 de.drug_concept_id,
 COUNT(de.drug_exposure_id) AS drg_cnt,
 de.person_id
 FROM 
 <CDM_DATABASE_NAME>.drug_exposure de
 JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
 ON 
 de.person_id = op.person_id
 AND 
 de.drug_exposure_start_date >= op.observation_period_start_date
 AND 
 de.drug_exposure_start_date <= op.observation_period_end_date
 GROUP BY 
 de.person_id,
 de.drug_concept_id
 ) de
GROUP BY 
 de.drug_concept_id, 
 de.drg_cnt;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_791
 ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_800
USING DELTA
AS
SELECT
800 AS analysis_id,
 CAST(o.observation_concept_id AS STRING) AS stratum_1,
 CAST(NULL AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 COUNT(DISTINCT o.person_id) AS count_value
FROM
<CDM_DATABASE_NAME>.observation o
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 o.person_id = op.person_id
AND 
 o.observation_date >= op.observation_period_start_date
AND 
 o.observation_date <= op.observation_period_end_date
GROUP BY 
 o.observation_concept_id;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_800
 ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_801
USING DELTA
AS
SELECT
801 AS analysis_id,
 CAST(o.observation_concept_id AS STRING) AS stratum_1,
 CAST(NULL AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 COUNT(o.person_id) AS count_value
FROM
<CDM_DATABASE_NAME>.observation o
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 o.person_id = op.person_id
AND 
 o.observation_date >= op.observation_period_start_date
AND 
 o.observation_date <= op.observation_period_end_date
GROUP BY 
 o.observation_concept_id;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_801
 ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(stratum_1)
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_802
USING DELTA
AS
WITH rawData  AS (
SELECT 
 o.observation_concept_id AS stratum_1,
 YEAR(o.observation_date) * 100 + MONTH(o.observation_date) AS stratum_2,
 COUNT(DISTINCT o.person_id) AS count_value
FROM 
 <CDM_DATABASE_NAME>.observation o
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 o.person_id = op.person_id
AND 
 o.observation_date >= op.observation_period_start_date
AND 
 o.observation_date <= op.observation_period_end_date
GROUP BY 
 o.observation_concept_id,
 YEAR(o.observation_date) * 100 + MONTH(o.observation_date)
)
 SELECT
802 AS analysis_id,
 CAST(stratum_1 AS STRING) AS stratum_1,
 CAST(stratum_2 AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 count_value
FROM
rawData;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_802
 ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(count_value)
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_803
USING DELTA
AS
WITH rawData(count_value)  AS (
SELECT 
 COUNT(DISTINCT o.observation_concept_id) AS count_value
FROM 
 <CDM_DATABASE_NAME>.observation o
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 o.person_id = op.person_id
AND 
 o.observation_date >= op.observation_period_start_date
AND 
 o.observation_date <= op.observation_period_end_date
GROUP BY 
 o.person_id
),
overallStats (avg_value, stdev_value, min_value, max_value, total) as
(
 select CAST(avg(1.0 * count_value) AS FLOAT) as avg_value,
 CAST(STDDEV(count_value) AS FLOAT) as stdev_value,
 min(count_value) as min_value,
 max(count_value) as max_value,
 COUNT(*) as total
 from rawData
),
statsView (count_value, total, rn) as
(
 select count_value, 
 COUNT(*) as total, 
 row_number() over (order by count_value) as rn
 FROM rawData
 group by count_value
),
priorStats (count_value, total, accumulated) as
(
 select s.count_value, s.total, sum(p.total) as accumulated
 from statsView s
 join statsView p on p.rn <= s.rn
 group by s.count_value, s.total, s.rn
)
 SELECT
803 as analysis_id,
 o.total as count_value,
 o.min_value,
 o.max_value,
 o.avg_value,
 o.stdev_value,
 MIN(case when p.accumulated >= .50 * o.total then count_value else o.max_value end) as median_value,
 MIN(case when p.accumulated >= .10 * o.total then count_value else o.max_value end) as p10_value,
 MIN(case when p.accumulated >= .25 * o.total then count_value else o.max_value end) as p25_value,
 MIN(case when p.accumulated >= .75 * o.total then count_value else o.max_value end) as p75_value,
 MIN(case when p.accumulated >= .90 * o.total then count_value else o.max_value end) as p90_value
FROM
priorStats p
CROSS JOIN overallStats o
GROUP BY o.total, o.min_value, o.max_value, o.avg_value, o.stdev_value
;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_803
 ZORDER BY count_value;
--HINT DISTRIBUTE_ON_KEY(count_value) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_803
USING DELTA
AS
SELECT
analysis_id, 
cast(null as STRING) as stratum_1, cast(null as STRING) as stratum_2, cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
count_value, min_value, max_value, avg_value, stdev_value, median_value, p10_value, p25_value, p75_value, p90_value
FROM
<ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_803
;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_803
 ZORDER BY count_value;
truncate table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_803;
drop table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_803;

--HINT DISTRIBUTE_ON_KEY(stratum_1)
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_804
USING DELTA
AS
WITH rawData  AS (
SELECT 
 o.observation_concept_id AS stratum_1,
 YEAR(o.observation_date) AS stratum_2,
 p.gender_concept_id AS stratum_3,
 FLOOR((YEAR(o.observation_date) - p.year_of_birth) / 10) AS stratum_4,
 COUNT(DISTINCT p.person_id) AS count_value
FROM 
 <CDM_DATABASE_NAME>.person p
JOIN 
 <CDM_DATABASE_NAME>.observation o 
ON 
 p.person_id = o.person_id
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 o.person_id = op.person_id
AND 
 o.observation_date >= op.observation_period_start_date
AND 
 o.observation_date <= op.observation_period_end_date
GROUP BY 
 o.observation_concept_id,
 YEAR(o.observation_date),
 p.gender_concept_id,
 FLOOR((YEAR(o.observation_date) - p.year_of_birth) / 10)
)
 SELECT
804 AS analysis_id,
 CAST(stratum_1 AS STRING) AS stratum_1,
 CAST(stratum_2 AS STRING) AS stratum_2,
 CAST(stratum_3 AS STRING) AS stratum_3,
 CAST(stratum_4 AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 count_value
FROM
rawData;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_804
 ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_805
USING DELTA
AS
SELECT
805 AS analysis_id,
 CAST(o.observation_concept_id AS STRING) AS stratum_1,
 CAST(o.observation_type_concept_id AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 COUNT(o.person_id) AS count_value
FROM
<CDM_DATABASE_NAME>.observation o
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 o.person_id = op.person_id
AND 
 o.observation_date >= op.observation_period_start_date
AND 
 o.observation_date <= op.observation_period_end_date
GROUP BY 
 o.observation_concept_id,
 o.observation_type_concept_id;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_805
 ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(subject_id) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1orawData_806
USING DELTA
AS
SELECT
o.observation_concept_id AS subject_id,
 p.gender_concept_id,
 o.observation_start_year - p.year_of_birth AS count_value
FROM
<CDM_DATABASE_NAME>.person p
JOIN (
 SELECT 
 o.person_id,
 o.observation_concept_id,
 MIN(YEAR(o.observation_date)) AS observation_start_year
 FROM 
 <CDM_DATABASE_NAME>.observation o
 JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
 ON 
 o.person_id = op.person_id
 AND 
 o.observation_date >= op.observation_period_start_date
 AND 
 o.observation_date <= op.observation_period_end_date
 GROUP BY 
 o.person_id,
 o.observation_concept_id
 ) o
ON 
 p.person_id = o.person_id
;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1orawData_806
 ZORDER BY subject_id;
--HINT DISTRIBUTE_ON_KEY(stratum1_id)
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_806
USING DELTA
AS
WITH overallStats (stratum1_id, stratum2_id, avg_value, stdev_value, min_value, max_value, total)  AS (
 select subject_id as stratum1_id,
 gender_concept_id as stratum2_id,
 CAST(avg(1.0 * count_value) AS FLOAT) as avg_value,
 CAST(STDDEV(count_value) AS FLOAT) as stdev_value,
 min(count_value) as min_value,
 max(count_value) as max_value,
 COUNT(*) as total
 FROM <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1orawData_806
 group by subject_id, gender_concept_id
),
statsView (stratum1_id, stratum2_id, count_value, total, rn) as
(
 select subject_id as stratum1_id, gender_concept_id as stratum2_id, count_value, COUNT(*) as total, row_number() over (partition by subject_id, gender_concept_id order by count_value) as rn
 FROM <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1orawData_806
 group by subject_id, gender_concept_id, count_value
),
priorStats (stratum1_id, stratum2_id, count_value, total, accumulated) as
(
 select s.stratum1_id, s.stratum2_id, s.count_value, s.total, sum(p.total) as accumulated
 from statsView s
 join statsView p on s.stratum1_id = p.stratum1_id and s.stratum2_id = p.stratum2_id and p.rn <= s.rn
 group by s.stratum1_id, s.stratum2_id, s.count_value, s.total, s.rn
)
 SELECT
806 as analysis_id,
 CAST(o.stratum1_id AS STRING) AS stratum1_id,
 CAST(o.stratum2_id AS STRING) AS stratum2_id,
 o.total as count_value,
 o.min_value,
 o.max_value,
 o.avg_value,
 o.stdev_value,
 MIN(case when p.accumulated >= .50 * o.total then count_value else o.max_value end) as median_value,
 MIN(case when p.accumulated >= .10 * o.total then count_value else o.max_value end) as p10_value,
 MIN(case when p.accumulated >= .25 * o.total then count_value else o.max_value end) as p25_value,
 MIN(case when p.accumulated >= .75 * o.total then count_value else o.max_value end) as p75_value,
 MIN(case when p.accumulated >= .90 * o.total then count_value else o.max_value end) as p90_value
FROM
priorStats p
join overallStats o on p.stratum1_id = o.stratum1_id and p.stratum2_id = o.stratum2_id 
GROUP BY o.stratum1_id, o.stratum2_id, o.total, o.min_value, o.max_value, o.avg_value, o.stdev_value
;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_806
 ZORDER BY stratum1_id;
--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_806
USING DELTA
AS
SELECT
analysis_id, stratum1_id as stratum_1, stratum2_id as stratum_2, 
cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
count_value, min_value, max_value, avg_value, stdev_value, median_value, p10_value, p25_value, p75_value, p90_value
FROM
<ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_806
;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_806
 ZORDER BY stratum_1;
truncate table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1orawData_806;
drop table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1orawData_806;
truncate table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_806;
drop table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_806;

--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_807
USING DELTA
AS
SELECT
807 AS analysis_id,
 CAST(o.observation_concept_id AS STRING) AS stratum_1,
 CAST(o.unit_concept_id AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 COUNT(o.person_id) AS count_value
FROM
<CDM_DATABASE_NAME>.observation o
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 o.person_id = op.person_id
AND 
 o.observation_date >= op.observation_period_start_date
AND 
 o.observation_date <= op.observation_period_end_date
GROUP BY 
 o.observation_CONCEPT_ID,
 o.unit_concept_id;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_807
 ZORDER BY stratum_1;

CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_809
USING DELTA
AS
SELECT
809 as analysis_id, 
 cast(null as STRING) as stratum_1, cast(null as STRING) as stratum_2, cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
 COUNT(o1.PERSON_ID) as count_value
FROM
<CDM_DATABASE_NAME>.observation o1
 left join <CDM_DATABASE_NAME>.person p1
 on p1.person_id = o1.person_id
where p1.person_id is null;

CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_810
USING DELTA
AS
SELECT
810 as analysis_id, 
 cast(null as STRING) as stratum_1, cast(null as STRING) as stratum_2, cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
 COUNT(o1.PERSON_ID) as count_value
FROM
<CDM_DATABASE_NAME>.observation o1
 left join <CDM_DATABASE_NAME>.observation_period op1
 on op1.person_id = o1.person_id
 and o1.observation_date >= op1.observation_period_start_date
 and o1.observation_date <= op1.observation_period_end_date
where op1.person_id is null;

CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_812
USING DELTA
AS
SELECT
812 as analysis_id,
 cast(null as STRING) as stratum_1, cast(null as STRING) as stratum_2, cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5, 
 COUNT(o1.PERSON_ID) as count_value
FROM
<CDM_DATABASE_NAME>.observation o1
 left join <CDM_DATABASE_NAME>.provider p1
 on p1.provider_id = o1.provider_id
where o1.provider_id is not null
 and p1.provider_id is null;

CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_813
USING DELTA
AS
SELECT
813 as analysis_id, 
 cast(null as STRING) as stratum_1, cast(null as STRING) as stratum_2, cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
 COUNT(o1.PERSON_ID) as count_value
FROM
<CDM_DATABASE_NAME>.observation o1
 left join <CDM_DATABASE_NAME>.visit_occurrence vo1
 on o1.visit_occurrence_id = vo1.visit_occurrence_id
where o1.visit_occurrence_id is not null
 and vo1.visit_occurrence_id is null;

CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_814
USING DELTA
AS
SELECT
814 as analysis_id, 
 cast(null as STRING) as stratum_1, cast(null as STRING) as stratum_2, cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
 COUNT(o1.PERSON_ID) as count_value
FROM
<CDM_DATABASE_NAME>.observation o1
where o1.value_as_number is null
 and o1.value_as_string is null
 and o1.value_as_concept_id is null;

--HINT DISTRIBUTE_ON_KEY(stratum1_id) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1ooverallStats_815
USING DELTA
AS
SELECT
o.subject_id AS stratum1_id,
 o.unit_concept_id AS stratum2_id,
 CAST(AVG(1.0 * o.count_value) AS FLOAT) AS avg_value,
 CAST(STDDEV(count_value) AS FLOAT) AS stdev_value,
 MIN(o.count_value) AS min_value,
 MAX(o.count_value) AS max_value,
 COUNT(*) AS total
FROM
(
 SELECT 
 o.observation_concept_id AS subject_id,
 o.unit_concept_id,
 CAST(o.value_as_number AS FLOAT) AS count_value
 FROM 
 <CDM_DATABASE_NAME>.observation o
 JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
 ON 
 o.person_id = op.person_id
 AND 
 o.observation_date >= op.observation_period_start_date
 AND 
 o.observation_date <= op.observation_period_end_date
 WHERE 
 o.unit_concept_id IS NOT NULL
 AND 
 o.value_as_number IS NOT NULL
 ) o
GROUP BY 
 o.subject_id,
 o.unit_concept_id;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1ooverallStats_815
 ZORDER BY stratum1_id;
--HINT DISTRIBUTE_ON_KEY(stratum1_id) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1ostatsView_815
USING DELTA
AS
SELECT
o.subject_id AS stratum1_id,
 o.unit_concept_id AS stratum2_id,
 o.count_value,
 COUNT(*) AS total,
 ROW_NUMBER() OVER (PARTITION BY o.subject_id,o.unit_concept_id ORDER BY o.count_value) AS rn
FROM
(
 SELECT 
 o.observation_concept_id AS subject_id,
 o.unit_concept_id,
 CAST(o.value_as_number AS FLOAT) AS count_value
 FROM 
 <CDM_DATABASE_NAME>.observation o
 JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
 ON 
 o.person_id = op.person_id
 AND 
 o.observation_date >= op.observation_period_start_date
 AND 
 o.observation_date <= op.observation_period_end_date
 WHERE 
 o.unit_concept_id IS NOT NULL
 AND 
 o.value_as_number IS NOT NULL
 ) o
GROUP BY 
 o.subject_id,
 o.unit_concept_id,
 o.count_value;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1ostatsView_815
 ZORDER BY stratum1_id;
--HINT DISTRIBUTE_ON_KEY(stratum1_id)
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_815
USING DELTA
AS
WITH priorStats (stratum1_id, stratum2_id, count_value, total, accumulated)  AS (
 select s.stratum1_id, s.stratum2_id, s.count_value, s.total, sum(p.total) as accumulated
 from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1ostatsView_815 s
 join <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1ostatsView_815 p on s.stratum1_id = p.stratum1_id and s.stratum2_id = p.stratum2_id and p.rn <= s.rn
 group by s.stratum1_id, s.stratum2_id, s.count_value, s.total, s.rn
)
 SELECT
815 as analysis_id,
 CAST(o.stratum1_id AS STRING) AS stratum1_id,
 CAST(o.stratum2_id AS STRING) AS stratum2_id,
 o.total as count_value,
 o.min_value,
 o.max_value,
 o.avg_value,
 o.stdev_value,
 MIN(case when p.accumulated >= .50 * o.total then count_value else o.max_value end) as median_value,
 MIN(case when p.accumulated >= .10 * o.total then count_value else o.max_value end) as p10_value,
 MIN(case when p.accumulated >= .25 * o.total then count_value else o.max_value end) as p25_value,
 MIN(case when p.accumulated >= .75 * o.total then count_value else o.max_value end) as p75_value,
 MIN(case when p.accumulated >= .90 * o.total then count_value else o.max_value end) as p90_value
FROM
priorStats p
join <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1ooverallStats_815 o on p.stratum1_id = o.stratum1_id and p.stratum2_id = o.stratum2_id 
GROUP BY o.stratum1_id, o.stratum2_id, o.total, o.min_value, o.max_value, o.avg_value, o.stdev_value
;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_815
 ZORDER BY stratum1_id;
--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_815
USING DELTA
AS
SELECT
analysis_id, stratum1_id as stratum_1, stratum2_id as stratum_2, 
cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
count_value, min_value, max_value, avg_value, stdev_value, median_value, p10_value, p25_value, p75_value, p90_value
FROM
<ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_815
;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_815
 ZORDER BY stratum_1;
truncate table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1ooverallStats_815;
drop table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1ooverallStats_815;
truncate table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1ostatsView_815;
drop table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1ostatsView_815;
truncate table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_815;
drop table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_815;

--HINT DISTRIBUTE_ON_KEY(stratum_1)
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_820
USING DELTA
AS
WITH rawData  AS (
SELECT 
 YEAR(o.observation_date) * 100 + MONTH(o.observation_date) AS stratum_1,
 COUNT(o.person_id) AS count_value
FROM 
 <CDM_DATABASE_NAME>.observation o
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 o.person_id = op.person_id
AND 
 o.observation_date >= op.observation_period_start_date
AND 
 o.observation_date <= op.observation_period_end_date
GROUP BY 
 YEAR(o.observation_date) * 100 + MONTH(o.observation_date)
)
 SELECT
820 AS analysis_id,
 CAST(stratum_1 AS STRING) AS stratum_1,
 CAST(NULL AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 count_value
FROM
rawData;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_820
 ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_822
USING DELTA
AS
SELECT
822 AS analysis_id,
 CAST(o.observation_concept_id AS STRING) AS stratum_1,
 CAST(o.value_as_concept_id AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 COUNT(*) AS count_value
FROM
<CDM_DATABASE_NAME>.observation o
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 o.person_id = op.person_id
AND 
 o.observation_date >= op.observation_period_start_date
AND 
 o.observation_date <= op.observation_period_end_date
GROUP BY 
 o.observation_concept_id,
 o.value_as_concept_id;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_822
 ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_823
USING DELTA
AS
SELECT
823 AS analysis_id,
 CAST(o.observation_concept_id AS STRING) AS stratum_1,
 CAST(o.qualifier_concept_id AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 COUNT(*) AS count_value
FROM
<CDM_DATABASE_NAME>.observation o
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 o.person_id = op.person_id
AND 
 o.observation_date >= op.observation_period_start_date
AND 
 o.observation_date <= op.observation_period_end_date
GROUP BY 
 o.observation_concept_id,
 o.qualifier_concept_id;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_823
 ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_825
USING DELTA
AS
SELECT
825 AS analysis_id,
 CAST(o.observation_source_concept_id AS STRING) AS stratum_1,
 CAST(NULL AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 COUNT(*) AS count_value
FROM
<CDM_DATABASE_NAME>.observation o
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 o.person_id = op.person_id
AND 
 o.observation_date >= op.observation_period_start_date
AND 
 o.observation_date <= op.observation_period_end_date
GROUP BY 
 o.observation_source_concept_id;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_825
 ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_826
USING DELTA
AS
SELECT
826 AS analysis_id,
 CAST(o.value_as_concept_id AS STRING) AS stratum_1,
 CAST(NULL AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 COUNT(*) AS count_value
FROM
<CDM_DATABASE_NAME>.observation o
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 o.person_id = op.person_id
AND 
 o.observation_date >= op.observation_period_start_date
AND 
 o.observation_date <= op.observation_period_end_date
GROUP BY 
 o.value_as_concept_id;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_826
 ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_827
USING DELTA
AS
SELECT
827 AS analysis_id,
 CAST(o.unit_concept_id AS STRING) AS stratum_1,
 CAST(NULL AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 COUNT(*) AS count_value
FROM
<CDM_DATABASE_NAME>.observation o
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 o.person_id = op.person_id
AND 
 o.observation_date >= op.observation_period_start_date
AND 
 o.observation_date <= op.observation_period_end_date
GROUP BY 
 o.unit_concept_id;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_827
 ZORDER BY stratum_1;

CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_830
USING DELTA
AS
SELECT
830 AS analysis_id,
 CAST(NULL AS STRING) AS stratum_1,
 CAST(NULL AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 COUNT(*) AS count_value
FROM
<CDM_DATABASE_NAME>.observation o
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 o.person_id = op.person_id
AND 
 o.observation_date >= op.observation_period_start_date
AND 
 o.observation_date <= op.observation_period_end_date;

DROP VIEW IF EXISTS op_outside  ; 
CREATE TEMPORARY VIEW op_outside   AS (SELECT 
 COUNT(DISTINCT o.person_id) AS person_count
FROM 
 <CDM_DATABASE_NAME>.observation o
LEFT JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 o.person_id = op.person_id
AND 
 o.observation_date >= op.observation_period_start_date
AND 
 o.observation_date <= op.observation_period_end_date
WHERE
 op.person_id IS NULL
);
DROP VIEW IF EXISTS o_total ; 
CREATE TEMPORARY VIEW o_total  AS (SELECT
 COUNT(DISTINCT person_id) person_count
FROM
 <CDM_DATABASE_NAME>.observation
);
 CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_831
USING DELTA
AS
(SELECT
831 AS analysis_id,
 CASE WHEN ot.person_count != 0 THEN 
 CAST(CAST(1.0*op.person_count/ot.person_count AS FLOAT) AS STRING) 
 ELSE 
 CAST(NULL AS STRING) 
 END AS stratum_1, 
 CAST(op.person_count AS STRING) AS stratum_2,
 CAST(ot.person_count AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 SIGN(op.person_count) AS count_value
FROM
op_outside op
CROSS JOIN 
 o_total ot);

DROP VIEW IF EXISTS op_outside  ; 
CREATE TEMPORARY VIEW op_outside   AS (SELECT 
 COUNT(*) AS record_count
FROM 
 <CDM_DATABASE_NAME>.observation o
LEFT JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 o.person_id = op.person_id
AND 
 o.observation_date >= op.observation_period_start_date
AND 
 o.observation_date <= op.observation_period_end_date
WHERE
 op.person_id IS NULL
);
DROP VIEW IF EXISTS o_total ; 
CREATE TEMPORARY VIEW o_total  AS (SELECT
 COUNT(*) record_count
FROM
 <CDM_DATABASE_NAME>.observation
);
 CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_832
USING DELTA
AS
(SELECT
832 AS analysis_id,
 CASE WHEN ot.record_count != 0 THEN 
 CAST(CAST(1.0*op.record_count/ot.record_count AS FLOAT) AS STRING) 
 ELSE 
 CAST(NULL AS STRING) 
 END AS stratum_1, 
 CAST(op.record_count AS STRING) AS stratum_2,
 CAST(ot.record_count AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 SIGN(op.record_count) AS count_value
FROM
op_outside op
CROSS JOIN 
 o_total ot);

--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_891
USING DELTA
AS
SELECT
891 AS analysis_id,
 CAST(o.observation_concept_id AS STRING) AS stratum_1,
 CAST(o.obs_cnt AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 SUM(COUNT(o.person_id)) OVER (PARTITION BY o.observation_concept_id ORDER BY o.obs_cnt DESC) AS count_value
FROM
(
 SELECT 
 o.observation_concept_id,
 COUNT(o.observation_id) AS obs_cnt,
 o.person_id
 FROM 
 <CDM_DATABASE_NAME>.observation o
 JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
 ON 
 o.person_id = op.person_id
 AND 
 o.observation_date >= op.observation_period_start_date
 AND 
 o.observation_date <= op.observation_period_end_date
 GROUP BY 
 o.person_id,
 o.observation_concept_id
 ) o
GROUP BY 
 o.observation_concept_id, 
 o.obs_cnt;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_891
 ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_900
USING DELTA
AS
SELECT
900 AS analysis_id,
 CAST(de.drug_concept_id AS STRING) AS stratum_1,
 CAST(NULL AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 COUNT(DISTINCT de.person_id) AS count_value
FROM
<CDM_DATABASE_NAME>.drug_era de
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 de.person_id = op.person_id
AND 
 de.drug_era_start_date >= op.observation_period_start_date
AND 
 de.drug_era_start_date <= op.observation_period_end_date
GROUP BY 
 de.drug_concept_id;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_900
 ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_901
USING DELTA
AS
SELECT
901 AS analysis_id,
 CAST(de.drug_concept_id AS STRING) AS stratum_1,
 CAST(NULL AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 COUNT(de.person_id) AS count_value
FROM
<CDM_DATABASE_NAME>.drug_era de
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 de.person_id = op.person_id
AND 
 de.drug_era_start_date >= op.observation_period_start_date
AND 
 de.drug_era_start_date <= op.observation_period_end_date
GROUP BY 
 de.drug_concept_id;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_901
 ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(stratum_1)
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_902
USING DELTA
AS
WITH rawData  AS (
SELECT 
 de.drug_concept_id AS stratum_1,
 YEAR(de.drug_era_start_date) * 100 + MONTH(de.drug_era_start_date) AS stratum_2,
 COUNT(DISTINCT de.person_id) AS count_value
FROM 
 <CDM_DATABASE_NAME>.drug_era de
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 de.person_id = op.person_id
AND 
 de.drug_era_start_date >= op.observation_period_start_date
AND 
 de.drug_era_start_date <= op.observation_period_end_date
GROUP BY 
 de.drug_concept_id,
 YEAR(de.drug_era_start_date) * 100 + MONTH(de.drug_era_start_date)
)
 SELECT
902 AS analysis_id,
 CAST(stratum_1 AS STRING) AS stratum_1,
 CAST(stratum_2 AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 count_value
FROM
rawData;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_902
 ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(count_value)
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_903
USING DELTA
AS
WITH rawData(count_value)  AS (
SELECT
 COUNT(DISTINCT de.drug_concept_id) AS count_value
FROM 
 <CDM_DATABASE_NAME>.drug_era de
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 de.person_id = op.person_id
AND 
 de.drug_era_start_date >= op.observation_period_start_date
AND 
 de.drug_era_start_date <= op.observation_period_end_date
GROUP BY 
 de.person_id
),
overallStats (avg_value, stdev_value, min_value, max_value, total) as
(
 select CAST(avg(1.0 * count_value) AS FLOAT) as avg_value,
 CAST(STDDEV(count_value) AS FLOAT) as stdev_value,
 min(count_value) as min_value,
 max(count_value) as max_value,
 COUNT(*) as total
 from rawData
),
statsView (count_value, total, rn) as
(
 select count_value, 
 COUNT(*) as total, 
 row_number() over (order by count_value) as rn
 FROM rawData
 group by count_value
),
priorStats (count_value, total, accumulated) as
(
 select s.count_value, s.total, sum(p.total) as accumulated
 from statsView s
 join statsView p on p.rn <= s.rn
 group by s.count_value, s.total, s.rn
)
 SELECT
903 as analysis_id,
 o.total as count_value,
 o.min_value,
 o.max_value,
 o.avg_value,
 o.stdev_value,
 MIN(case when p.accumulated >= .50 * o.total then count_value else o.max_value end) as median_value,
 MIN(case when p.accumulated >= .10 * o.total then count_value else o.max_value end) as p10_value,
 MIN(case when p.accumulated >= .25 * o.total then count_value else o.max_value end) as p25_value,
 MIN(case when p.accumulated >= .75 * o.total then count_value else o.max_value end) as p75_value,
 MIN(case when p.accumulated >= .90 * o.total then count_value else o.max_value end) as p90_value
FROM
priorStats p
CROSS JOIN overallStats o
GROUP BY o.total, o.min_value, o.max_value, o.avg_value, o.stdev_value
;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_903
 ZORDER BY count_value;
--HINT DISTRIBUTE_ON_KEY(count_value) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_903
USING DELTA
AS
SELECT
analysis_id, 
cast(null as STRING) as stratum_1, cast(null as STRING) as stratum_2, cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
count_value, min_value, max_value, avg_value, stdev_value, median_value, p10_value, p25_value, p75_value, p90_value
FROM
<ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_903
;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_903
 ZORDER BY count_value;
truncate table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_903;
drop table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_903;

--HINT DISTRIBUTE_ON_KEY(stratum_1)
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_904
USING DELTA
AS
WITH rawData  AS (
SELECT 
 de.drug_concept_id AS stratum_1,
 YEAR(de.drug_era_start_date) AS stratum_2,
 p.gender_concept_id AS stratum_3,
 FLOOR((YEAR(de.drug_era_start_date) - p.year_of_birth) / 10) AS stratum_4,
 COUNT(DISTINCT p.person_id) AS count_value
FROM 
 <CDM_DATABASE_NAME>.person p
JOIN 
 <CDM_DATABASE_NAME>.drug_era de 
ON 
 p.person_id = de.person_id
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 de.person_id = op.person_id
AND 
 de.drug_era_start_date >= op.observation_period_start_date
AND 
 de.drug_era_start_date <= op.observation_period_end_date
GROUP BY 
 de.drug_concept_id,
 YEAR(de.drug_era_start_date),
 p.gender_concept_id,
 FLOOR((YEAR(de.drug_era_start_date) - p.year_of_birth) / 10)
)
 SELECT
904 AS analysis_id,
 CAST(stratum_1 AS STRING) AS stratum_1,
 CAST(stratum_2 AS STRING) AS stratum_2,
 CAST(stratum_3 AS STRING) AS stratum_3,
 CAST(stratum_4 AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 count_value
FROM
rawData;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_904
 ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(subject_id) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1orawData_906
USING DELTA
AS
SELECT
de.drug_concept_id AS subject_id,
 p.gender_concept_id,
 de.drug_start_year - p.year_of_birth AS count_value
FROM
<CDM_DATABASE_NAME>.person p
JOIN (
 SELECT 
 de.person_id,
 de.drug_concept_id,
 MIN(YEAR(de.drug_era_start_date)) AS drug_start_year
 FROM 
 <CDM_DATABASE_NAME>.drug_era de
 JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
 ON 
 de.person_id = op.person_id
 AND 
 de.drug_era_start_date >= op.observation_period_start_date
 AND 
 de.drug_era_start_date <= op.observation_period_end_date 
 GROUP BY 
 de.person_id,
 de.drug_concept_id
 ) de 
ON 
 p.person_id = de.person_id
;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1orawData_906
 ZORDER BY subject_id;
--HINT DISTRIBUTE_ON_KEY(stratum1_id)
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_906
USING DELTA
AS
WITH overallStats (stratum1_id, stratum2_id, avg_value, stdev_value, min_value, max_value, total)  AS (
 select subject_id as stratum1_id,
 gender_concept_id as stratum2_id,
 CAST(avg(1.0 * count_value) AS FLOAT) as avg_value,
 CAST(STDDEV(count_value) AS FLOAT) as stdev_value,
 min(count_value) as min_value,
 max(count_value) as max_value,
 COUNT(*) as total
 FROM <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1orawData_906
 group by subject_id, gender_concept_id
),
statsView (stratum1_id, stratum2_id, count_value, total, rn) as
(
 select subject_id as stratum1_id, gender_concept_id as stratum2_id, count_value, COUNT(*) as total, row_number() over (partition by subject_id, gender_concept_id order by count_value) as rn
 FROM <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1orawData_906
 group by subject_id, gender_concept_id, count_value
),
priorStats (stratum1_id, stratum2_id, count_value, total, accumulated) as
(
 select s.stratum1_id, s.stratum2_id, s.count_value, s.total, sum(p.total) as accumulated
 from statsView s
 join statsView p on s.stratum1_id = p.stratum1_id and s.stratum2_id = p.stratum2_id and p.rn <= s.rn
 group by s.stratum1_id, s.stratum2_id, s.count_value, s.total, s.rn
)
 SELECT
906 as analysis_id,
 CAST(o.stratum1_id AS STRING) AS stratum1_id,
 CAST(o.stratum2_id AS STRING) AS stratum2_id,
 o.total as count_value,
 o.min_value,
 o.max_value,
 o.avg_value,
 o.stdev_value,
 MIN(case when p.accumulated >= .50 * o.total then count_value else o.max_value end) as median_value,
 MIN(case when p.accumulated >= .10 * o.total then count_value else o.max_value end) as p10_value,
 MIN(case when p.accumulated >= .25 * o.total then count_value else o.max_value end) as p25_value,
 MIN(case when p.accumulated >= .75 * o.total then count_value else o.max_value end) as p75_value,
 MIN(case when p.accumulated >= .90 * o.total then count_value else o.max_value end) as p90_value
FROM
priorStats p
join overallStats o on p.stratum1_id = o.stratum1_id and p.stratum2_id = o.stratum2_id 
GROUP BY o.stratum1_id, o.stratum2_id, o.total, o.min_value, o.max_value, o.avg_value, o.stdev_value
;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_906
 ZORDER BY stratum1_id;
--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_906
USING DELTA
AS
SELECT
analysis_id, stratum1_id as stratum_1, stratum2_id as stratum_2, 
cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
count_value, min_value, max_value, avg_value, stdev_value, median_value, p10_value, p25_value, p75_value, p90_value
FROM
<ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_906
;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_906
 ZORDER BY stratum_1;
truncate table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1orawData_906;
drop table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1orawData_906;
truncate table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_906;
drop table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_906;

--HINT DISTRIBUTE_ON_KEY(stratum_1)
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_907
USING DELTA
AS
WITH rawData(stratum1_id, count_value)  AS (
SELECT 
 de.drug_concept_id AS stratum1_id,
 datediff(de.drug_era_end_date,de.drug_era_start_date) AS count_value
FROM 
 <CDM_DATABASE_NAME>.drug_era de
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 de.person_id = op.person_id
AND 
 de.drug_era_start_date >= op.observation_period_start_date
AND 
 de.drug_era_start_date <= op.observation_period_end_date
),
overallStats (stratum1_id, avg_value, stdev_value, min_value, max_value, total) as
(
 select stratum1_id, 
 CAST(avg(1.0 * count_value) AS FLOAT) as avg_value,
 CAST(STDDEV(count_value) AS FLOAT) as stdev_value,
 min(count_value) as min_value,
 max(count_value) as max_value,
 COUNT(*) as total
 from rawData
 group by stratum1_id
),
statsView (stratum1_id, count_value, total, rn) as
(
 select stratum1_id, 
 count_value, 
 COUNT(*) as total, 
 row_number() over (partition by stratum1_id order by count_value) as rn
 FROM rawData
 group by stratum1_id, count_value
),
priorStats (stratum1_id, count_value, total, accumulated) as
(
 select s.stratum1_id, s.count_value, s.total, sum(p.total) as accumulated
 from statsView s
 join statsView p on s.stratum1_id = p.stratum1_id and p.rn <= s.rn
 group by s.stratum1_id, s.count_value, s.total, s.rn
)
 SELECT
907 as analysis_id,
 CAST(p.stratum1_id AS STRING) as stratum_1,
 o.total as count_value,
 o.min_value,
 o.max_value,
 o.avg_value,
 o.stdev_value,
 MIN(case when p.accumulated >= .50 * o.total then count_value else o.max_value end) as median_value,
 MIN(case when p.accumulated >= .10 * o.total then count_value else o.max_value end) as p10_value,
 MIN(case when p.accumulated >= .25 * o.total then count_value else o.max_value end) as p25_value,
 MIN(case when p.accumulated >= .75 * o.total then count_value else o.max_value end) as p75_value,
 MIN(case when p.accumulated >= .90 * o.total then count_value else o.max_value end) as p90_value
FROM
priorStats p
join overallStats o on p.stratum1_id = o.stratum1_id
GROUP BY p.stratum1_id, o.total, o.min_value, o.max_value, o.avg_value, o.stdev_value
;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_907
 ZORDER BY stratum_1;
--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_907
USING DELTA
AS
SELECT
analysis_id, stratum_1, 
cast(null as STRING) as stratum_2, cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
count_value, min_value, max_value, avg_value, stdev_value, median_value, p10_value, p25_value, p75_value, p90_value
FROM
<ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_907
;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_907
 ZORDER BY stratum_1;
truncate table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_907;
drop table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_907;

CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_908
USING DELTA
AS
SELECT
908 as analysis_id, 
 cast(null as STRING) as stratum_1, cast(null as STRING) as stratum_2, cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
 COUNT(de1.PERSON_ID) as count_value
FROM
<CDM_DATABASE_NAME>.drug_era de1
 left join <CDM_DATABASE_NAME>.person p1
 on p1.person_id = de1.person_id
where p1.person_id is null;

CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_910
USING DELTA
AS
SELECT
910 AS analysis_id,
 CAST(NULL AS STRING) AS stratum_1,
 CAST(NULL AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 COUNT(*) AS count_value
FROM
<CDM_DATABASE_NAME>.drug_era de
LEFT JOIN 
 <CDM_DATABASE_NAME>.observation_period op
ON 
 de.person_id = op.person_id
AND 
 de.drug_era_start_date >= op.observation_period_start_date
AND 
 de.drug_era_start_date <= op.observation_period_end_date
WHERE 
 op.person_id IS NULL;

CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_911
USING DELTA
AS
SELECT
911 as analysis_id, 
 cast(null as STRING) as stratum_1, cast(null as STRING) as stratum_2, cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
 COUNT(de1.PERSON_ID) as count_value
FROM
<CDM_DATABASE_NAME>.drug_era de1
where de1.drug_era_end_date < de1.drug_era_start_date;

--HINT DISTRIBUTE_ON_KEY(stratum_1)
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_920
USING DELTA
AS
WITH rawData  AS (
SELECT 
 YEAR(de.drug_era_start_date) * 100 + MONTH(de.drug_era_start_date) AS stratum_1,
 COUNT(de.person_id) AS count_value
FROM 
 <CDM_DATABASE_NAME>.drug_era de
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 de.person_id = op.person_id
AND 
 de.drug_era_start_date >= op.observation_period_start_date
AND 
 de.drug_era_start_date <= op.observation_period_end_date
GROUP BY 
 YEAR(de.drug_era_start_date)*100 + MONTH(de.drug_era_start_date)
)
 SELECT
920 AS analysis_id,
 CAST(stratum_1 AS STRING) AS stratum_1,
 CAST(NULL AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 count_value
FROM
rawData;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_920
 ZORDER BY stratum_1;

CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_930
USING DELTA
AS
SELECT
930 AS analysis_id,
 CAST(NULL AS STRING) AS stratum_1,
 CAST(NULL AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 COUNT(*) AS count_value
FROM
<CDM_DATABASE_NAME>.drug_era de
JOIN 
 <CDM_DATABASE_NAME>.observation_period op
ON 
 de.person_id = op.person_id
AND 
 de.drug_era_start_date >= op.observation_period_start_date
AND 
 de.drug_era_start_date <= op.observation_period_end_date;

DROP VIEW IF EXISTS op_outside  ; 
CREATE TEMPORARY VIEW op_outside   AS (SELECT 
 COUNT(DISTINCT de.person_id) AS person_count
FROM 
 <CDM_DATABASE_NAME>.drug_era de
LEFT JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 de.person_id = op.person_id
AND 
 de.drug_era_start_date >= op.observation_period_start_date
AND 
 de.drug_era_start_date <= op.observation_period_end_date
WHERE
 op.person_id IS NULL
);
DROP VIEW IF EXISTS de_total ; 
CREATE TEMPORARY VIEW de_total  AS (SELECT
 COUNT(DISTINCT person_id) person_count
FROM
 <CDM_DATABASE_NAME>.drug_era
);
 CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_931
USING DELTA
AS
(SELECT
931 AS analysis_id,
 CASE WHEN det.person_count != 0 THEN 
 CAST(CAST(1.0*op.person_count/det.person_count AS FLOAT) AS STRING) 
 ELSE 
 CAST(NULL AS STRING) 
 END AS stratum_1, 
 CAST(op.person_count AS STRING) AS stratum_2,
 CAST(det.person_count AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 SIGN(op.person_count) AS count_value
FROM
op_outside op
CROSS JOIN 
 de_total det);

DROP VIEW IF EXISTS op_outside  ; 
CREATE TEMPORARY VIEW op_outside   AS (SELECT 
 COUNT(*) AS record_count
FROM 
 <CDM_DATABASE_NAME>.drug_era de
LEFT JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 de.person_id = op.person_id
AND 
 de.drug_era_start_date >= op.observation_period_start_date
AND 
 de.drug_era_start_date <= op.observation_period_end_date
WHERE
 op.person_id IS NULL
);
DROP VIEW IF EXISTS de_total ; 
CREATE TEMPORARY VIEW de_total  AS (SELECT
 COUNT(*) record_count
FROM
 <CDM_DATABASE_NAME>.drug_era
);
 CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_932
USING DELTA
AS
(SELECT
932 AS analysis_id,
 CASE WHEN det.record_count != 0 THEN 
 CAST(CAST(1.0*op.record_count/det.record_count AS FLOAT) AS STRING) 
 ELSE
 CAST(NULL AS STRING)
 END AS stratum_1, 
 CAST(op.record_count AS STRING) AS stratum_2,
 CAST(det.record_count AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 SIGN(op.record_count) AS count_value
FROM
op_outside op
CROSS JOIN 
 de_total det);

--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1000
USING DELTA
AS
SELECT
1000 AS analysis_id,
 CAST(ce.condition_concept_id AS STRING) AS stratum_1,
 CAST(NULL AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 COUNT(DISTINCT ce.person_id) AS count_value
FROM
<CDM_DATABASE_NAME>.condition_era ce
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 ce.person_id = op.person_id
AND 
 ce.condition_era_start_date >= op.observation_period_start_date
AND 
 ce.condition_era_start_date <= op.observation_period_end_date 
GROUP BY 
 ce.condition_concept_id;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1000
 ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1001
USING DELTA
AS
SELECT
1001 AS analysis_id,
 CAST(ce.condition_concept_id AS STRING) AS stratum_1,
 CAST(NULL AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 COUNT(ce.person_id) AS count_value
FROM
<CDM_DATABASE_NAME>.condition_era ce
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 ce.person_id = op.person_id
AND 
 ce.condition_era_start_date >= op.observation_period_start_date
AND 
 ce.condition_era_start_date <= op.observation_period_end_date 
GROUP BY 
 ce.condition_concept_id;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1001
 ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(stratum_1)
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1002
USING DELTA
AS
WITH rawData  AS (
SELECT 
 ce.condition_concept_id AS stratum_1,
 YEAR(ce.condition_era_start_date) * 100 + MONTH(ce.condition_era_start_date) AS stratum_2,
 COUNT(DISTINCT ce.person_id) AS count_value
FROM 
 <CDM_DATABASE_NAME>.condition_era ce
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 ce.person_id = op.person_id
AND 
 ce.condition_era_start_date >= op.observation_period_start_date
AND 
 ce.condition_era_start_date <= op.observation_period_end_date 
GROUP BY 
 ce.condition_concept_id,
 YEAR(ce.condition_era_start_date) * 100 + MONTH(ce.condition_era_start_date)
)
 SELECT
1002 as analysis_id,
 CAST(stratum_1 AS STRING) AS stratum_1,
 CAST(stratum_2 AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 count_value
FROM
rawData;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1002
 ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(count_value)
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_1003
USING DELTA
AS
WITH rawData(count_value)  AS (
SELECT 
 COUNT(DISTINCT ce.condition_concept_id) AS count_value
FROM 
 <CDM_DATABASE_NAME>.condition_era ce
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 ce.person_id = op.person_id
AND 
 ce.condition_era_start_date >= op.observation_period_start_date
AND 
 ce.condition_era_start_date <= op.observation_period_end_date 
GROUP BY 
 ce.person_id
),
overallStats (avg_value, stdev_value, min_value, max_value, total) as
(
 select CAST(avg(1.0 * count_value) AS FLOAT) as avg_value,
 CAST(STDDEV(count_value) AS FLOAT) as stdev_value,
 min(count_value) as min_value,
 max(count_value) as max_value,
 COUNT(*) as total
 from rawData
),
statsView (count_value, total, rn) as
(
 select count_value, 
 COUNT(*) as total, 
 row_number() over (order by count_value) as rn
 FROM rawData
 group by count_value
),
priorStats (count_value, total, accumulated) as
(
 select s.count_value, s.total, sum(p.total) as accumulated
 from statsView s
 join statsView p on p.rn <= s.rn
 group by s.count_value, s.total, s.rn
)
 SELECT
1003 as analysis_id,
 o.total as count_value,
 o.min_value,
 o.max_value,
 o.avg_value,
 o.stdev_value,
 MIN(case when p.accumulated >= .50 * o.total then count_value else o.max_value end) as median_value,
 MIN(case when p.accumulated >= .10 * o.total then count_value else o.max_value end) as p10_value,
 MIN(case when p.accumulated >= .25 * o.total then count_value else o.max_value end) as p25_value,
 MIN(case when p.accumulated >= .75 * o.total then count_value else o.max_value end) as p75_value,
 MIN(case when p.accumulated >= .90 * o.total then count_value else o.max_value end) as p90_value
FROM
priorStats p
CROSS JOIN overallStats o
GROUP BY o.total, o.min_value, o.max_value, o.avg_value, o.stdev_value
;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_1003
 ZORDER BY count_value;
--HINT DISTRIBUTE_ON_KEY(count_value) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_1003
USING DELTA
AS
SELECT
analysis_id, 
cast(null as STRING) as stratum_1, cast(null as STRING) as stratum_2, cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
count_value, min_value, max_value, avg_value, stdev_value, median_value, p10_value, p25_value, p75_value, p90_value
FROM
<ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_1003
;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_1003
 ZORDER BY count_value;
truncate table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_1003;
drop table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_1003;

--HINT DISTRIBUTE_ON_KEY(stratum_1)
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1004
USING DELTA
AS
WITH rawData  AS (
SELECT 
 ce.condition_concept_id AS stratum_1,
 YEAR(ce.condition_era_start_date) AS stratum_2,
 p.gender_concept_id AS stratum_3,
 FLOOR((YEAR(ce.condition_era_start_date) - p.year_of_birth) / 10) AS stratum_4,
 COUNT(DISTINCT p.person_id) AS count_value
FROM 
 <CDM_DATABASE_NAME>.person p
JOIN 
 <CDM_DATABASE_NAME>.condition_era ce 
ON 
 p.person_id = ce.person_id
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 ce.person_id = op.person_id
AND 
 ce.condition_era_start_date >= op.observation_period_start_date
AND 
 ce.condition_era_start_date <= op.observation_period_end_date 
GROUP BY 
 ce.condition_concept_id,
 YEAR(ce.condition_era_start_date),
 p.gender_concept_id,
 FLOOR((YEAR(ce.condition_era_start_date) - p.year_of_birth) / 10)
)
 SELECT
1004 AS analysis_id,
 CAST(stratum_1 AS STRING) AS stratum_1,
 CAST(stratum_2 AS STRING) AS stratum_2,
 CAST(stratum_3 AS STRING) AS stratum_3,
 CAST(stratum_4 AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 count_value
FROM
rawData;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1004
 ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(subject_id) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1orawData_1006
USING DELTA
AS
SELECT
ce.condition_concept_id AS subject_id,
 p.gender_concept_id,
 ce.condition_start_year - p.year_of_birth AS count_value
FROM
<CDM_DATABASE_NAME>.person p
JOIN (
 SELECT 
 ce.person_id,
 ce.condition_concept_id,
 MIN(YEAR(ce.condition_era_start_date)) AS condition_start_year
 FROM 
 <CDM_DATABASE_NAME>.condition_era ce
 JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
 ON 
 ce.person_id = op.person_id
 AND 
 ce.condition_era_start_date >= op.observation_period_start_date
 AND 
 ce.condition_era_start_date <= op.observation_period_end_date 
 GROUP BY 
 ce.person_id,
 ce.condition_concept_id
 ) ce 
ON 
 p.person_id = ce.person_id
;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1orawData_1006
 ZORDER BY subject_id;
--HINT DISTRIBUTE_ON_KEY(stratum1_id)
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_1006
USING DELTA
AS
WITH overallStats (stratum1_id, stratum2_id, avg_value, stdev_value, min_value, max_value, total)  AS (
 select subject_id as stratum1_id,
 gender_concept_id as stratum2_id,
 CAST(avg(1.0 * count_value) AS FLOAT) as avg_value,
 CAST(STDDEV(count_value) AS FLOAT) as stdev_value,
 min(count_value) as min_value,
 max(count_value) as max_value,
 COUNT(*) as total
 FROM <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1orawData_1006
 group by subject_id, gender_concept_id
),
statsView (stratum1_id, stratum2_id, count_value, total, rn) as
(
 select subject_id as stratum1_id, gender_concept_id as stratum2_id, count_value, COUNT(*) as total, row_number() over (partition by subject_id, gender_concept_id order by count_value) as rn
 FROM <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1orawData_1006
 group by subject_id, gender_concept_id, count_value
),
priorStats (stratum1_id, stratum2_id, count_value, total, accumulated) as
(
 select s.stratum1_id, s.stratum2_id, s.count_value, s.total, sum(p.total) as accumulated
 from statsView s
 join statsView p on s.stratum1_id = p.stratum1_id and s.stratum2_id = p.stratum2_id and p.rn <= s.rn
 group by s.stratum1_id, s.stratum2_id, s.count_value, s.total, s.rn
)
 SELECT
1006 as analysis_id,
 CAST(o.stratum1_id AS STRING) AS stratum1_id,
 CAST(o.stratum2_id AS STRING) AS stratum2_id,
 o.total as count_value,
 o.min_value,
 o.max_value,
 o.avg_value,
 o.stdev_value,
 MIN(case when p.accumulated >= .50 * o.total then count_value else o.max_value end) as median_value,
 MIN(case when p.accumulated >= .10 * o.total then count_value else o.max_value end) as p10_value,
 MIN(case when p.accumulated >= .25 * o.total then count_value else o.max_value end) as p25_value,
 MIN(case when p.accumulated >= .75 * o.total then count_value else o.max_value end) as p75_value,
 MIN(case when p.accumulated >= .90 * o.total then count_value else o.max_value end) as p90_value
FROM
priorStats p
join overallStats o on p.stratum1_id = o.stratum1_id and p.stratum2_id = o.stratum2_id 
GROUP BY o.stratum1_id, o.stratum2_id, o.total, o.min_value, o.max_value, o.avg_value, o.stdev_value
;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_1006
 ZORDER BY stratum1_id;
--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_1006
USING DELTA
AS
SELECT
analysis_id, stratum1_id as stratum_1, stratum2_id as stratum_2, 
cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
count_value, min_value, max_value, avg_value, stdev_value, median_value, p10_value, p25_value, p75_value, p90_value
FROM
<ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_1006
;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_1006
 ZORDER BY stratum_1;
truncate table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1orawData_1006;
drop table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1orawData_1006;
truncate table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_1006;
drop table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_1006;

--HINT DISTRIBUTE_ON_KEY(stratum_1)
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_1007
USING DELTA
AS
WITH rawData(stratum1_id, count_value)  AS (
SELECT 
 ce.condition_concept_id AS stratum1_id,
 datediff(ce.condition_era_end_date,ce.condition_era_start_date) AS count_value
FROM 
 <CDM_DATABASE_NAME>.condition_era ce
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 ce.person_id = op.person_id
AND 
 ce.condition_era_start_date >= op.observation_period_start_date
AND 
 ce.condition_era_start_date <= op.observation_period_end_date 
),
overallStats (stratum1_id, avg_value, stdev_value, min_value, max_value, total) as
(
 select stratum1_id, 
 CAST(avg(1.0 * count_value) AS FLOAT) as avg_value,
 CAST(STDDEV(count_value) AS FLOAT) as stdev_value,
 min(count_value) as min_value,
 max(count_value) as max_value,
 COUNT(*) as total
 from rawData
 group by stratum1_id
),
statsView (stratum1_id, count_value, total, rn) as
(
 select stratum1_id, 
 count_value, 
 COUNT(*) as total, 
 row_number() over (partition by stratum1_id order by count_value) as rn
 FROM rawData
 group by stratum1_id, count_value
),
priorStats (stratum1_id, count_value, total, accumulated) as
(
 select s.stratum1_id, s.count_value, s.total, sum(p.total) as accumulated
 from statsView s
 join statsView p on s.stratum1_id = p.stratum1_id and p.rn <= s.rn
 group by s.stratum1_id, s.count_value, s.total, s.rn
)
 SELECT
1007 as analysis_id,
 CAST(p.stratum1_id AS STRING) as stratum_1,
 o.total as count_value,
 o.min_value,
 o.max_value,
 o.avg_value,
 o.stdev_value,
 MIN(case when p.accumulated >= .50 * o.total then count_value else o.max_value end) as median_value,
 MIN(case when p.accumulated >= .10 * o.total then count_value else o.max_value end) as p10_value,
 MIN(case when p.accumulated >= .25 * o.total then count_value else o.max_value end) as p25_value,
 MIN(case when p.accumulated >= .75 * o.total then count_value else o.max_value end) as p75_value,
 MIN(case when p.accumulated >= .90 * o.total then count_value else o.max_value end) as p90_value
FROM
priorStats p
join overallStats o on p.stratum1_id = o.stratum1_id
GROUP BY p.stratum1_id, o.total, o.min_value, o.max_value, o.avg_value, o.stdev_value
;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_1007
 ZORDER BY stratum_1;
--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_1007
USING DELTA
AS
SELECT
analysis_id, stratum_1, 
cast(null as STRING) as stratum_2, cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
count_value, min_value, max_value, avg_value, stdev_value, median_value, p10_value, p25_value, p75_value, p90_value
FROM
<ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_1007
;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_1007
 ZORDER BY stratum_1;
truncate table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_1007;
drop table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_1007;

CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1008
USING DELTA
AS
SELECT
1008 as analysis_id, 
 cast(null as STRING) as stratum_1, cast(null as STRING) as stratum_2, cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
 COUNT(ce1.PERSON_ID) as count_value
FROM
<CDM_DATABASE_NAME>.condition_era ce1
 left join <CDM_DATABASE_NAME>.person p1
 on p1.person_id = ce1.person_id
where p1.person_id is null;

CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1010
USING DELTA
AS
SELECT
1010 AS analysis_id,
 CAST(NULL AS STRING) AS stratum_1,
 CAST(NULL AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 COUNT(*) AS count_value
FROM
<CDM_DATABASE_NAME>.condition_era ce
LEFT JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 ce.person_id = op.person_id
AND 
 ce.condition_era_start_date >= op.observation_period_start_date
AND 
 ce.condition_era_start_date <= op.observation_period_end_date
WHERE 
 op.person_id IS NULL;

CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1011
USING DELTA
AS
SELECT
1011 as analysis_id, 
 cast(null as STRING) as stratum_1, cast(null as STRING) as stratum_2, cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
 COUNT(ce1.PERSON_ID) as count_value
FROM
<CDM_DATABASE_NAME>.condition_era ce1
where ce1.condition_era_end_date < ce1.condition_era_start_date;

--HINT DISTRIBUTE_ON_KEY(stratum_1)
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1020
USING DELTA
AS
WITH rawData  AS (
SELECT 
 YEAR(ce.condition_era_start_date) * 100 + MONTH(ce.condition_era_start_date) AS stratum_1,
 COUNT(ce.person_id) AS count_value
FROM 
 <CDM_DATABASE_NAME>.condition_era ce
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 ce.person_id = op.person_id
AND 
 ce.condition_era_start_date >= op.observation_period_start_date
AND 
 ce.condition_era_start_date <= op.observation_period_end_date 
GROUP BY 
 YEAR(ce.condition_era_start_date)*100 + MONTH(ce.condition_era_start_date)
)
 SELECT
1020 AS analysis_id,
 CAST(stratum_1 AS STRING) AS stratum_1,
 CAST(NULL AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 count_value
FROM
rawData;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1020
 ZORDER BY stratum_1;

CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1030
USING DELTA
AS
SELECT
1030 AS analysis_id,
 CAST(NULL AS STRING) AS stratum_1,
 CAST(NULL AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 COUNT(*) AS count_value
FROM
<CDM_DATABASE_NAME>.condition_era ce
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 ce.person_id = op.person_id
AND 
 ce.condition_era_start_date >= op.observation_period_start_date
AND 
 ce.condition_era_start_date <= op.observation_period_end_date;

DROP VIEW IF EXISTS op_outside  ; 
CREATE TEMPORARY VIEW op_outside   AS (SELECT 
 COUNT(DISTINCT ce.person_id) AS person_count
FROM 
 <CDM_DATABASE_NAME>.condition_era ce
LEFT JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 ce.person_id = op.person_id
AND 
 ce.condition_era_start_date >= op.observation_period_start_date
AND 
 ce.condition_era_start_date <= op.observation_period_end_date
WHERE
 op.person_id IS NULL
);
DROP VIEW IF EXISTS ce_total ; 
CREATE TEMPORARY VIEW ce_total  AS (SELECT
 COUNT(DISTINCT person_id) person_count
FROM
 <CDM_DATABASE_NAME>.condition_era
);
 CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1031
USING DELTA
AS
(SELECT
1031 AS analysis_id,
 CASE WHEN cet.person_count != 0 THEN 
 CAST(CAST(1.0*op.person_count/cet.person_count AS FLOAT) AS STRING) 
 ELSE 
 CAST(NULL AS STRING) 
 END AS stratum_1, 
 CAST(op.person_count AS STRING) AS stratum_2,
 CAST(cet.person_count AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 SIGN(op.person_count) AS count_value
FROM
op_outside op
CROSS JOIN 
 ce_total cet);

DROP VIEW IF EXISTS op_outside  ; 
CREATE TEMPORARY VIEW op_outside   AS (SELECT 
 COUNT(*) AS record_count
FROM 
 <CDM_DATABASE_NAME>.condition_era ce
LEFT JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 ce.person_id = op.person_id
AND 
 ce.condition_era_start_date >= op.observation_period_start_date
AND 
 ce.condition_era_start_date <= op.observation_period_end_date
WHERE
 op.person_id IS NULL
);
DROP VIEW IF EXISTS ce_total ; 
CREATE TEMPORARY VIEW ce_total  AS (SELECT
 COUNT(*) record_count
FROM
 <CDM_DATABASE_NAME>.condition_era
);
 CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1032
USING DELTA
AS
(SELECT
1032 AS analysis_id,
 CASE WHEN cet.record_count != 0 THEN
 CAST(CAST(1.0*op.record_count/cet.record_count AS FLOAT) AS STRING) 
 ELSE 
 CAST(NULL AS STRING) 
 END AS stratum_1, 
 CAST(op.record_count AS STRING) AS stratum_2,
 CAST(cet.record_count AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 SIGN(op.record_count) AS count_value
FROM
op_outside op
CROSS JOIN 
 ce_total cet);

--HINT DISTRIBUTE_ON_KEY(stratum_1)
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1100
USING DELTA
AS
WITH rawData  AS (
 select
 SUBSTR(l1.zip,3) as stratum_1,
 COUNT(distinct person_id) as count_value
 from <CDM_DATABASE_NAME>.person p1
 inner join <CDM_DATABASE_NAME>.location l1
 on p1.location_id = l1.location_id
 where p1.location_id is not null
 and l1.zip is not null
 group by SUBSTR(l1.zip,3)
)
 SELECT
1100 as analysis_id,
 CAST(stratum_1 AS STRING) as stratum_1,
 cast(null as STRING) as stratum_2,
 cast(null as STRING) as stratum_3,
 cast(null as STRING) as stratum_4,
 cast(null as STRING) as stratum_5,
 count_value
FROM
rawData;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1100
 ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1101
USING DELTA
AS
SELECT
1101 as analysis_id, 
 CAST(l1.state AS STRING) as stratum_1, 
 cast(null as STRING) as stratum_2, cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
 COUNT(distinct person_id) as count_value
FROM
<CDM_DATABASE_NAME>.person p1
 inner join <CDM_DATABASE_NAME>.location l1
 on p1.location_id = l1.location_id
where p1.location_id is not null
 and l1.state is not null
group by l1.state;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1101
 ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(stratum_1)
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1102
USING DELTA
AS
WITH rawData  AS (
 select
 SUBSTR(l1.zip,3) as stratum_1,
 COUNT(distinct care_site_id) as count_value
 from <CDM_DATABASE_NAME>.care_site cs1
 inner join <CDM_DATABASE_NAME>.location l1
 on cs1.location_id = l1.location_id
 where cs1.location_id is not null
 and l1.zip is not null
 group by SUBSTR(l1.zip,3)
)
 SELECT
1102 as analysis_id,
 CAST(stratum_1 AS STRING) as stratum_1,
 cast(null as STRING) as stratum_2,
 cast(null as STRING) as stratum_3,
 cast(null as STRING) as stratum_4,
 cast(null as STRING) as stratum_5,
 count_value
FROM
rawData;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1102
 ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1103
USING DELTA
AS
SELECT
1103 as analysis_id, 
 CAST(l1.state AS STRING) as stratum_1, 
 cast(null as STRING) as stratum_2, cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
 COUNT(distinct care_site_id) as count_value
FROM
<CDM_DATABASE_NAME>.care_site cs1
 inner join <CDM_DATABASE_NAME>.location l1
 on cs1.location_id = l1.location_id
where cs1.location_id is not null
 and l1.state is not null
group by l1.state;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1103
 ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1200
USING DELTA
AS
SELECT
1200 as analysis_id, 
 CAST(cs1.place_of_service_concept_id AS STRING) as stratum_1, 
 cast(null as STRING) as stratum_2, cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
 COUNT(person_id) as count_value
FROM
<CDM_DATABASE_NAME>.person p1
 inner join <CDM_DATABASE_NAME>.care_site cs1
 on p1.care_site_id = cs1.care_site_id
where p1.care_site_id is not null
 and cs1.place_of_service_concept_id is not null
group by cs1.place_of_service_concept_id;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1200
 ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1201
USING DELTA
AS
SELECT
1201 as analysis_id, 
 CAST(cs1.place_of_service_concept_id AS STRING) as stratum_1, 
 cast(null as STRING) as stratum_2, cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
 COUNT(visit_occurrence_id) as count_value
FROM
<CDM_DATABASE_NAME>.visit_occurrence vo1
 inner join <CDM_DATABASE_NAME>.care_site cs1
 on vo1.care_site_id = cs1.care_site_id
where vo1.care_site_id is not null
 and cs1.place_of_service_concept_id is not null
group by cs1.place_of_service_concept_id;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1201
 ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1202
USING DELTA
AS
SELECT
1202 as analysis_id, 
 CAST(cs1.place_of_service_concept_id AS STRING) as stratum_1,
 cast(null as STRING) as stratum_2, cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
 COUNT(care_site_id) as count_value
FROM
<CDM_DATABASE_NAME>.care_site cs1
where cs1.place_of_service_concept_id is not null
group by cs1.place_of_service_concept_id;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1202
 ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1203
USING DELTA
AS
SELECT
1203 AS analysis_id,
 CAST(vo.discharge_to_concept_id AS STRING) AS stratum_1,
 CAST(NULL AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 COUNT(*) AS count_value
FROM
<CDM_DATABASE_NAME>.visit_occurrence vo
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 vo.person_id = op.person_id
AND 
 vo.visit_start_date >= op.observation_period_start_date
AND 
 vo.visit_start_date <= op.observation_period_end_date
WHERE 
 vo.discharge_to_concept_id != 0
GROUP BY 
 vo.discharge_to_concept_id;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1203
 ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1300
USING DELTA
AS
SELECT
1300 AS analysis_id,
 CAST(vd.visit_detail_concept_id AS STRING) AS stratum_1,
 CAST(NULL AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 COUNT(DISTINCT vd.person_id) AS count_value
FROM
<CDM_DATABASE_NAME>.visit_detail vd
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 vd.person_id = op.person_id
AND 
 vd.visit_detail_start_date >= op.observation_period_start_date 
AND 
 vd.visit_detail_start_date <= op.observation_period_end_date
GROUP BY 
 vd.visit_detail_concept_id;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1300
 ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1301
USING DELTA
AS
SELECT
1301 AS analysis_id,
 CAST(vd.visit_detail_concept_id AS STRING) AS stratum_1,
 CAST(NULL AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 COUNT(vd.person_id) AS count_value
FROM
<CDM_DATABASE_NAME>.visit_detail vd
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 vd.person_id = op.person_id
AND 
 vd.visit_detail_start_date >= op.observation_period_start_date 
AND 
 vd.visit_detail_start_date <= op.observation_period_end_date
GROUP BY 
 vd.visit_detail_concept_id;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1301
 ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(stratum_1)
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1302
USING DELTA
AS
WITH rawData  AS (
SELECT
 vd.visit_detail_concept_id AS stratum_1,
 YEAR(vd.visit_detail_start_date)*100 + MONTH(vd.visit_detail_start_date) AS stratum_2,
 COUNT(DISTINCT vd.person_id) AS count_value
FROM
 <CDM_DATABASE_NAME>.visit_detail vd 
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 vd.person_id = op.person_id
AND 
 vd.visit_detail_start_date >= op.observation_period_start_date 
AND 
 vd.visit_detail_start_date <= op.observation_period_end_date
GROUP BY 
 vd.visit_detail_concept_id,
 YEAR(vd.visit_detail_start_date)*100 + MONTH(vd.visit_detail_start_date)
)
 SELECT
1302 AS analysis_id,
 CAST(stratum_1 AS STRING) AS stratum_1,
 CAST(stratum_2 AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 count_value
FROM
rawData;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1302
 ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(count_value)
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_1303
USING DELTA
AS
WITH rawData(person_id, count_value)  AS (
SELECT 
 vd.person_id,
 COUNT(DISTINCT vd.visit_detail_concept_id) AS count_value
FROM 
 <CDM_DATABASE_NAME>.visit_detail vd
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 vd.person_id = op.person_id
AND 
 vd.visit_detail_start_date >= op.observation_period_start_date 
AND 
 vd.visit_detail_start_date <= op.observation_period_end_date
GROUP BY 
 vd.person_id
),
overallStats (avg_value, stdev_value, min_value, max_value, total) AS
(
SELECT 
 CAST(AVG(1.0 * count_value) AS FLOAT) AS avg_value,
 CAST(STDDEV(count_value) AS FLOAT) AS stdev_value,
 MIN(count_value) AS min_value,
 MAX(count_value) AS max_value,
 COUNT(*) AS total
FROM 
 rawData
),
statsView (count_value, total, rn) AS
(
SELECT 
 count_value,
 COUNT(*) AS total,
 ROW_NUMBER() OVER (ORDER BY count_value) AS rn
FROM 
 rawData
GROUP BY 
 count_value
),
priorStats (count_value, total, accumulated) AS
(
SELECT 
 s.count_value,
 s.total,
 SUM(p.total) AS accumulated
FROM 
 statsView s
JOIN 
 statsView p ON p.rn <= s.rn
GROUP BY 
 s.count_value,
 s.total,
 s.rn
)
 SELECT
1303 AS analysis_id,
 o.total AS count_value,
 o.min_value,
 o.max_value,
 o.avg_value,
 o.stdev_value,
 MIN(CASE WHEN p.accumulated >= .50 * o.total THEN count_value ELSE o.max_value END) AS median_value,
 MIN(CASE WHEN p.accumulated >= .10 * o.total THEN count_value ELSE o.max_value END) AS p10_value,
 MIN(CASE WHEN p.accumulated >= .25 * o.total THEN count_value ELSE o.max_value END) AS p25_value,
 MIN(CASE WHEN p.accumulated >= .75 * o.total THEN count_value ELSE o.max_value END) AS p75_value,
 MIN(CASE WHEN p.accumulated >= .90 * o.total THEN count_value ELSE o.max_value END) AS p90_value
FROM
priorStats p
CROSS JOIN 
 overallStats o
GROUP BY 
 o.total,
 o.min_value,
 o.max_value,
 o.avg_value,
 o.stdev_value
;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_1303
 ZORDER BY count_value;
--HINT DISTRIBUTE_ON_KEY(count_value) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_1303
USING DELTA
AS
SELECT
analysis_id,
 CAST(NULL AS STRING) AS stratum_1,
 CAST(NULL AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 count_value,
 min_value,
 max_value,
 avg_value,
 stdev_value,
 median_value,
 p10_value,
 p25_value,
 p75_value,
 p90_value
FROM
<ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_1303
;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_1303
 ZORDER BY count_value;
TRUNCATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_1303;
DROP TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_1303;

--HINT DISTRIBUTE_ON_KEY(stratum_1)
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1304
USING DELTA
AS
WITH rawData  AS (
SELECT 
 vd.visit_detail_concept_id AS stratum_1,
 YEAR(vd.visit_detail_start_date) AS stratum_2,
 p.gender_concept_id AS stratum_3,
 FLOOR((YEAR(vd.visit_detail_start_date) - p.year_of_birth) / 10) AS stratum_4,
 COUNT(DISTINCT p.person_id) AS count_value
FROM 
 <CDM_DATABASE_NAME>.person p
JOIN 
 <CDM_DATABASE_NAME>.visit_detail vd 
ON 
 p.person_id = vd.person_id 
JOIN
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 vd.person_id = op.person_id
AND 
 vd.visit_detail_start_date >= op.observation_period_start_date 
AND 
 vd.visit_detail_start_date <= op.observation_period_end_date
GROUP BY 
 vd.visit_detail_concept_id,
 YEAR(vd.visit_detail_start_date),
 p.gender_concept_id,
 FLOOR((YEAR(vd.visit_detail_start_date) - p.year_of_birth)/10)
)
 SELECT
1304 AS analysis_id,
 CAST(stratum_1 AS STRING) AS stratum_1,
 CAST(stratum_2 AS STRING) AS stratum_2,
 CAST(stratum_3 AS STRING) AS stratum_3,
 CAST(stratum_4 AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 count_value
FROM
rawData;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1304
 ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(stratum1_id)
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_1306
USING DELTA
AS
WITH rawData (stratum1_id, stratum2_id, count_value)  AS (
SELECT 
 vd.visit_detail_concept_id AS stratum1_id,
 p.gender_concept_id AS stratum2_id,
 vd.visit_detail_start_year - p.year_of_birth AS count_value
FROM 
 <CDM_DATABASE_NAME>.person p
JOIN (
 SELECT 
 vd.person_id,
 vd.visit_detail_concept_id,
 MIN(YEAR(vd.visit_detail_start_date)) AS visit_detail_start_year
 FROM 
 <CDM_DATABASE_NAME>.visit_detail vd
 JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
 ON 
 vd.person_id = op.person_id
 AND 
 vd.visit_detail_start_date >= op.observation_period_start_date 
 AND 
 vd.visit_detail_start_date <= op.observation_period_end_date
 GROUP BY 
 vd.person_id,
 vd.visit_detail_concept_id
 ) vd 
ON 
 p.person_id = vd.person_id
),
overallStats (stratum1_id, stratum2_id, avg_value, stdev_value, min_value, max_value, total) AS
(
SELECT 
 stratum1_id,
 stratum2_id,
 CAST(AVG(1.0 * count_value) AS FLOAT) AS avg_value,
 CAST(STDDEV(count_value) AS FLOAT) AS stdev_value,
 MIN(count_value) AS min_value,
 MAX(count_value) AS max_value,
 COUNT(*) AS total
FROM 
 rawData
GROUP BY 
 stratum1_id,
 stratum2_id
),
statsView (stratum1_id, stratum2_id, count_value, total, rn) AS
(
SELECT 
 stratum1_id,
 stratum2_id,
 count_value,
 COUNT(*) AS total,
 ROW_NUMBER() OVER (PARTITION BY stratum1_id,stratum2_id ORDER BY count_value) AS rn
FROM 
 rawData
GROUP BY 
 stratum1_id,
 stratum2_id,
 count_value
),
priorStats (stratum1_id, stratum2_id, count_value, total, accumulated) AS
(
SELECT 
 s.stratum1_id,
 s.stratum2_id,
 s.count_value,
 s.total,
 SUM(p.total) AS accumulated
FROM 
 statsView s
JOIN 
 statsView p ON s.stratum1_id = p.stratum1_id 
 AND s.stratum2_id = p.stratum2_id 
 AND p.rn <= s.rn
GROUP BY 
 s.stratum1_id,
 s.stratum2_id,
 s.count_value,
 s.total,
 s.rn
)
 SELECT
1306 AS analysis_id,
 CAST(o.stratum1_id AS STRING) AS stratum1_id,
 CAST(o.stratum2_id AS STRING) AS stratum2_id,
 o.total as count_value,
 o.min_value,
 o.max_value,
 o.avg_value,
 o.stdev_value,
 MIN(CASE WHEN p.accumulated >= .50 * o.total THEN count_value ELSE o.max_value END) AS median_value,
 MIN(CASE WHEN p.accumulated >= .10 * o.total THEN count_value ELSE o.max_value END) AS p10_value,
 MIN(CASE WHEN p.accumulated >= .25 * o.total THEN count_value ELSE o.max_value END) AS p25_value,
 MIN(CASE WHEN p.accumulated >= .75 * o.total THEN count_value ELSE o.max_value END) AS p75_value,
 MIN(CASE WHEN p.accumulated >= .90 * o.total THEN count_value ELSE o.max_value END) AS p90_value
FROM
priorStats p
JOIN 
 overallStats o ON p.stratum1_id = o.stratum1_id AND p.stratum2_id = o.stratum2_id 
GROUP BY 
 o.stratum1_id, 
 o.stratum2_id, 
 o.total, 
 o.min_value, 
 o.max_value, 
 o.avg_value, 
 o.stdev_value
;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_1306
 ZORDER BY stratum1_id;
--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_1306
USING DELTA
AS
SELECT
analysis_id,
 stratum1_id AS stratum_1,
 stratum2_id AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 count_value,
 min_value,
 max_value,
 avg_value,
 stdev_value,
 median_value,
 p10_value,
 p25_value,
 p75_value,
 p90_value
FROM
<ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_1306
;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_1306
 ZORDER BY stratum_1;
TRUNCATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_1306;
DROP TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_1306;

CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1307
USING DELTA
AS
SELECT
1307 AS analysis_id,
 CAST(NULL AS STRING) AS stratum_1,
 CAST(NULL AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 COUNT(vd.person_id) AS count_value
FROM
<CDM_DATABASE_NAME>.visit_detail vd
LEFT JOIN 
 <CDM_DATABASE_NAME>.person p 
ON 
 p.person_id = vd.person_id
WHERE 
 p.person_id IS NULL;

CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1309
USING DELTA
AS
SELECT
1309 AS analysis_id,
 CAST(NULL AS STRING) AS stratum_1,
 CAST(NULL AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 COUNT(vd.person_id) AS count_value
FROM
<CDM_DATABASE_NAME>.visit_detail vd
LEFT JOIN 
 <CDM_DATABASE_NAME>.care_site cs 
ON 
 vd.care_site_id = cs.care_site_id
WHERE 
 vd.care_site_id IS NOT NULL 
AND 
 cs.care_site_id IS NULL;

CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1310
USING DELTA
AS
SELECT
1310 AS analysis_id,
 CAST(NULL AS STRING) AS stratum_1,
 CAST(NULL AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 COUNT(*) AS count_value
FROM
<CDM_DATABASE_NAME>.visit_detail vd
LEFT JOIN 
 <CDM_DATABASE_NAME>.observation_period op
ON 
 op.person_id = vd.person_id
AND 
 vd.visit_detail_start_date >= op.observation_period_start_date
AND 
 vd.visit_detail_start_date <= op.observation_period_end_date
WHERE 
 op.person_id IS NULL;

CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1311
USING DELTA
AS
SELECT
1311 AS analysis_id,
 CAST(NULL AS STRING) AS stratum_1,
 CAST(NULL AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 COUNT(person_id) AS count_value
FROM
<CDM_DATABASE_NAME>.visit_detail
WHERE 
 visit_detail_end_date < visit_detail_start_date;

--HINT DISTRIBUTE_ON_KEY(stratum_1)
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1312
USING DELTA
AS
WITH rawData  AS (
SELECT 
 YEAR(vd.visit_detail_start_date) AS stratum_1,
 p.gender_concept_id AS stratum_2,
 FLOOR((YEAR(vd.visit_detail_start_date) - p.year_of_birth) / 10) AS stratum_3,
 COUNT(DISTINCT vd.person_id) AS count_value
FROM 
 <CDM_DATABASE_NAME>.person p
JOIN 
 <CDM_DATABASE_NAME>.visit_detail vd 
ON 
 vd.person_id = p.person_id
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 vd.person_id = op.person_id
AND 
 vd.visit_detail_start_date >= op.observation_period_start_date 
AND 
 vd.visit_detail_start_date <= op.observation_period_end_date
GROUP BY 
 YEAR(vd.visit_detail_start_date),
 p.gender_concept_id,
 FLOOR((YEAR(vd.visit_detail_start_date) - p.year_of_birth) / 10)
)
 SELECT
1312 AS analysis_id,
 CAST(stratum_1 AS STRING) AS stratum_1,
 CAST(stratum_2 AS STRING) AS stratum_2,
 CAST(stratum_3 AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 count_value
FROM
rawData;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1312
 ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(stratum_id)
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_1313
USING DELTA
AS
WITH rawData(stratum_id, count_value)  AS (
SELECT 
 vd.visit_detail_concept_id AS stratum_id,
 datediff(vd.visit_detail_END_date,vd.visit_detail_start_date) AS count_value
FROM 
 <CDM_DATABASE_NAME>.visit_detail vd
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 vd.person_id = op.person_id
AND 
 vd.visit_detail_start_date >= op.observation_period_start_date 
AND 
 vd.visit_detail_start_date <= op.observation_period_end_date
),
overallStats (stratum_id, avg_value, stdev_value, min_value, max_value, total) AS
(
SELECT 
 stratum_id,
 CAST(AVG(1.0 * count_value) AS FLOAT) AS avg_value,
 CAST(STDDEV(count_value) AS FLOAT) AS stdev_value,
 MIN(count_value) AS min_value,
 MAX(count_value) AS max_value,
 COUNT(*) AS total
FROM 
 rawData
GROUP BY 
 stratum_id
),
statsView (stratum_id, count_value, total, rn) AS
(
SELECT 
 stratum_id,
 count_value,
 COUNT(*) AS total,
 ROW_NUMBER() OVER (ORDER BY count_value) AS rn
FROM 
 rawData
GROUP BY 
 stratum_id,
 count_value
),
priorStats (stratum_id, count_value, total, accumulated) AS
(
SELECT 
 s.stratum_id,
 s.count_value,
 s.total,
 SUM(p.total) AS accumulated
FROM 
 statsView s
JOIN 
 statsView p 
ON 
 s.stratum_id = p.stratum_id
AND 
 p.rn <= s.rn
GROUP BY 
 s.stratum_id,
 s.count_value,
 s.total,
 s.rn
)
 SELECT
1313 AS analysis_id,
 CAST(o.stratum_id AS STRING) AS stratum_id,
 o.total as count_value,
 o.min_value,
 o.max_value,
 o.avg_value,
 o.stdev_value,
 MIN(CASE WHEN p.accumulated >= .50 * o.total THEN count_value ELSE o.max_value END) AS median_value,
 MIN(CASE WHEN p.accumulated >= .10 * o.total THEN count_value ELSE o.max_value END) AS p10_value,
 MIN(CASE WHEN p.accumulated >= .25 * o.total THEN count_value ELSE o.max_value END) AS p25_value,
 MIN(CASE WHEN p.accumulated >= .75 * o.total THEN count_value ELSE o.max_value END) AS p75_value,
 MIN(CASE WHEN p.accumulated >= .90 * o.total THEN count_value ELSE o.max_value END) AS p90_value
FROM
priorStats p
JOIN 
 overallStats o ON p.stratum_id = o.stratum_id
GROUP BY 
 o.stratum_id, 
 o.total, 
 o.min_value, 
 o.max_value, 
 o.avg_value, 
 o.stdev_value
;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_1313
 ZORDER BY stratum_id;
--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_1313
USING DELTA
AS
SELECT
analysis_id,
 stratum_id AS stratum_1,
 CAST(NULL AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 count_value,
 min_value,
 max_value,
 avg_value,
 stdev_value,
 median_value,
 p10_value,
 p25_value,
 p75_value,
 p90_value
FROM
<ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_1313;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_1313
 ZORDER BY stratum_1;
TRUNCATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_1313;
DROP TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_1313;

--HINT DISTRIBUTE_ON_KEY(stratum_1)
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1320
USING DELTA
AS
WITH rawData  AS (
SELECT 
 YEAR(vd.visit_detail_start_date) * 100 + MONTH(vd.visit_detail_start_date) AS stratum_1,
 COUNT(vd.person_id) AS count_value
FROM 
 <CDM_DATABASE_NAME>.visit_detail vd
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 vd.person_id = op.person_id
AND 
 vd.visit_detail_start_date >= op.observation_period_start_date 
AND 
 vd.visit_detail_start_date <= op.observation_period_end_date
GROUP BY 
 YEAR(vd.visit_detail_start_date) * 100 + MONTH(vd.visit_detail_start_date)
)
 SELECT
1320 AS analysis_id,
 CAST(stratum_1 AS STRING) AS stratum_1,
 CAST(NULL AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 count_value
FROM
rawData;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1320
 ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(stratum_1)
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1321
USING DELTA
AS
WITH rawData  AS (
SELECT 
 YEAR(vd.visit_detail_start_date) AS stratum_1,
 COUNT(DISTINCT vd.person_id) AS count_value
FROM 
 <CDM_DATABASE_NAME>.visit_detail vd
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 vd.person_id = op.person_id
AND 
 vd.visit_detail_start_date >= op.observation_period_start_date 
AND 
 vd.visit_detail_start_date <= op.observation_period_end_date
GROUP BY 
 YEAR(vd.visit_detail_start_date)
)
 SELECT
1321 AS analysis_id,
 CAST(stratum_1 AS STRING) AS stratum_1,
 CAST(NULL AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 count_value
FROM
rawData;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1321
 ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1325
USING DELTA
AS
SELECT
1325 AS analysis_id,
 CAST(vd.visit_detail_source_concept_id AS STRING) AS stratum_1,
 CAST(NULL AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 COUNT(*) AS count_value
FROM
<CDM_DATABASE_NAME>.visit_detail vd
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 vd.person_id = op.person_id
AND 
 vd.visit_detail_start_date >= op.observation_period_start_date 
AND 
 vd.visit_detail_start_date <= op.observation_period_end_date
GROUP BY 
 visit_detail_source_concept_id;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1325
 ZORDER BY stratum_1;

CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1326
USING DELTA
AS
SELECT
1326 AS analysis_id,
 CAST(v.visit_detail_concept_id AS STRING) AS stratum_1,
 v.cdm_table AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 v.record_count AS count_value
FROM
(
 SELECT 'drug_exposure' cdm_table,
 COALESCE(vd.visit_detail_concept_id, 0) visit_detail_concept_id,
 COUNT(*) record_count
 FROM 
 <CDM_DATABASE_NAME>.drug_exposure de
 LEFT JOIN 
 <CDM_DATABASE_NAME>.visit_detail vd 
 ON 
 de.visit_occurrence_id = vd.visit_occurrence_id
 GROUP BY 
 vd.visit_detail_concept_id
 UNION
 SELECT 
 'condition_occurrence' cdm_table,
 COALESCE(vd.visit_detail_concept_id, 0) visit_detail_concept_id,
 COUNT(*) record_count
 FROM 
 <CDM_DATABASE_NAME>.condition_occurrence co
 LEFT JOIN 
 <CDM_DATABASE_NAME>.visit_detail vd 
 ON 
 co.visit_occurrence_id = vd.visit_occurrence_id
 GROUP BY 
 vd.visit_detail_concept_id
 UNION
 SELECT 
 'device_exposure' cdm_table,
 COALESCE(visit_detail_concept_id, 0) visit_detail_concept_id,
 COUNT(*) record_count
 FROM 
 <CDM_DATABASE_NAME>.device_exposure de
 LEFT JOIN 
 <CDM_DATABASE_NAME>.visit_detail vd 
 ON 
 de.visit_occurrence_id = vd.visit_occurrence_id
 GROUP BY 
 vd.visit_detail_concept_id
 UNION
 SELECT 
 'procedure_occurrence' cdm_table,
 COALESCE(vd.visit_detail_concept_id, 0) visit_detail_concept_id,
 COUNT(*) record_count
 FROM 
 <CDM_DATABASE_NAME>.procedure_occurrence po
 LEFT JOIN 
 <CDM_DATABASE_NAME>.visit_detail vd 
 ON 
 po.visit_occurrence_id = vd.visit_occurrence_id
 GROUP BY 
 vd.visit_detail_concept_id
 UNION
 SELECT 
 'measurement' cdm_table,
 COALESCE(vd.visit_detail_concept_id, 0) visit_detail_concept_id,
 COUNT(*) record_count
 FROM 
 <CDM_DATABASE_NAME>.measurement m
 LEFT JOIN 
 <CDM_DATABASE_NAME>.visit_detail vd 
 ON 
 m.visit_occurrence_id = vd.visit_occurrence_id
 GROUP BY 
 vd.visit_detail_concept_id
 UNION
 SELECT 
 'observation' cdm_table,
 COALESCE(vd.visit_detail_concept_id, 0) visit_detail_concept_id,
 COUNT(*) record_count
 FROM 
 <CDM_DATABASE_NAME>.observation o
 LEFT JOIN 
 <CDM_DATABASE_NAME>.visit_detail vd 
 ON 
 o.visit_occurrence_id = vd.visit_occurrence_id
 GROUP BY 
 vd.visit_detail_concept_id
 ) v;

CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1330
USING DELTA
AS
SELECT
1330 AS analysis_id,
 CAST(NULL AS STRING) AS stratum_1,
 CAST(NULL AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 COUNT(*) AS count_value
FROM
<CDM_DATABASE_NAME>.visit_detail vd
JOIN 
 <CDM_DATABASE_NAME>.observation_period op
ON 
 op.person_id = vd.person_id
AND 
 vd.visit_detail_start_date >= op.observation_period_start_date
AND 
 vd.visit_detail_start_date <= op.observation_period_end_date;

DROP VIEW IF EXISTS op_outside  ; 
CREATE TEMPORARY VIEW op_outside   AS (SELECT 
 COUNT(DISTINCT vd.person_id) AS person_count
FROM 
 <CDM_DATABASE_NAME>.visit_detail vd
LEFT JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 vd.person_id = op.person_id
AND 
 vd.visit_detail_start_date >= op.observation_period_start_date
AND 
 vd.visit_detail_start_date <= op.observation_period_end_date
WHERE
 op.person_id IS NULL
);
DROP VIEW IF EXISTS vd_total ; 
CREATE TEMPORARY VIEW vd_total  AS (SELECT
 COUNT(DISTINCT person_id) person_count
FROM
 <CDM_DATABASE_NAME>.visit_detail
);
 CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1331
USING DELTA
AS
(SELECT
1331 AS analysis_id,
 CASE WHEN vdt.person_count != 0 THEN 
 CAST(CAST(1.0*op.person_count/vdt.person_count AS FLOAT) AS STRING) 
 ELSE 
 CAST(NULL AS STRING)
 END AS stratum_1, 
 CAST(op.person_count AS STRING) AS stratum_2,
 CAST(vdt.person_count AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 SIGN(op.person_count) AS count_value
FROM
op_outside op
CROSS JOIN 
 vd_total vdt);

DROP VIEW IF EXISTS op_outside  ; 
CREATE TEMPORARY VIEW op_outside   AS (SELECT 
 COUNT(*) AS record_count
FROM 
 <CDM_DATABASE_NAME>.visit_detail vd
LEFT JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 vd.person_id = op.person_id
AND 
 vd.visit_detail_start_date >= op.observation_period_start_date
AND 
 vd.visit_detail_start_date <= op.observation_period_end_date
WHERE
 op.person_id IS NULL
);
DROP VIEW IF EXISTS vd_total ; 
CREATE TEMPORARY VIEW vd_total  AS (SELECT
 COUNT(*) record_count
FROM
 <CDM_DATABASE_NAME>.visit_detail
);
 CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1332
USING DELTA
AS
(SELECT
1332 AS analysis_id,
 CASE WHEN vdt.record_count != 0 THEN 
 CAST(CAST(1.0*op.record_count/vdt.record_count AS FLOAT) AS STRING) 
 ELSE 
 CAST(NULL AS STRING) 
 END AS stratum_1, 
 CAST(op.record_count AS STRING) AS stratum_2,
 CAST(vdt.record_count AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 SIGN(op.record_count) AS count_value
FROM
op_outside op
CROSS JOIN 
 vd_total vdt);

--HINT DISTRIBUTE_ON_KEY(stratum_1)
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_1406
USING DELTA
AS
WITH rawData(stratum1_id, count_value)  AS (
 select p1.gender_concept_id as stratum1_id,
 datediff(ppp1.payer_plan_period_end_date,ppp1.payer_plan_period_start_date) as count_value
 from <CDM_DATABASE_NAME>.person p1
 inner join 
 (select person_id, 
 payer_plan_period_START_DATE, 
 payer_plan_period_END_DATE, 
 ROW_NUMBER() over (PARTITION by person_id order by payer_plan_period_start_date asc) as rn1
 from <CDM_DATABASE_NAME>.payer_plan_period
 ) ppp1
 on p1.PERSON_ID = ppp1.PERSON_ID
 where ppp1.rn1 = 1
),
overallStats (stratum1_id, avg_value, stdev_value, min_value, max_value, total) as
(
 select stratum1_id, 
 CAST(avg(1.0 * count_value) AS FLOAT) as avg_value,
 CAST(STDDEV(count_value) AS FLOAT) as stdev_value,
 min(count_value) as min_value,
 max(count_value) as max_value,
 COUNT(*) as total
 from rawData
 group by stratum1_id
),
statsView (stratum1_id, count_value, total, rn) as
(
 select stratum1_id, 
 count_value, 
 COUNT(*) as total, 
 row_number() over (partition by stratum1_id order by count_value) as rn
 FROM rawData
 group by stratum1_id, count_value
),
priorStats (stratum1_id, count_value, total, accumulated) as
(
 select s.stratum1_id, s.count_value, s.total, sum(p.total) as accumulated
 from statsView s
 join statsView p on s.stratum1_id = p.stratum1_id and p.rn <= s.rn
 group by s.stratum1_id, s.count_value, s.total, s.rn
)
 SELECT
1406 as analysis_id,
 CAST(p.stratum1_id AS STRING) as stratum_1,
 o.total as count_value,
 o.min_value,
 o.max_value,
 o.avg_value,
 o.stdev_value,
 MIN(case when p.accumulated >= .50 * o.total then count_value else o.max_value end) as median_value,
 MIN(case when p.accumulated >= .10 * o.total then count_value else o.max_value end) as p10_value,
 MIN(case when p.accumulated >= .25 * o.total then count_value else o.max_value end) as p25_value,
 MIN(case when p.accumulated >= .75 * o.total then count_value else o.max_value end) as p75_value,
 MIN(case when p.accumulated >= .90 * o.total then count_value else o.max_value end) as p90_value
FROM
priorStats p
join overallStats o on p.stratum1_id = o.stratum1_id
GROUP BY p.stratum1_id, o.total, o.min_value, o.max_value, o.avg_value, o.stdev_value
;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_1406
 ZORDER BY stratum_1;
--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_1406
USING DELTA
AS
SELECT
analysis_id, stratum_1, 
cast(null as STRING) as stratum_2, cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
count_value, min_value, max_value, avg_value, stdev_value, median_value, p10_value, p25_value, p75_value, p90_value
FROM
<ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_1406
;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_1406
 ZORDER BY stratum_1;
truncate table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_1406;
drop table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_1406;

--HINT DISTRIBUTE_ON_KEY(stratum_id)
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_1407
USING DELTA
AS
WITH rawData(stratum_id, count_value)  AS (
 select floor((year(ppp1.payer_plan_period_START_DATE) - p1.YEAR_OF_BIRTH)/10) as stratum_id,
 datediff(ppp1.payer_plan_period_end_date,ppp1.payer_plan_period_start_date) as count_value
 from <CDM_DATABASE_NAME>.person p1
 inner join 
 (select person_id, 
 payer_plan_period_START_DATE, 
 payer_plan_period_END_DATE, 
 ROW_NUMBER() over (PARTITION by person_id order by payer_plan_period_start_date asc) as rn1
 from <CDM_DATABASE_NAME>.payer_plan_period
 ) ppp1
 on p1.PERSON_ID = ppp1.PERSON_ID
 where ppp1.rn1 = 1
),
overallStats (stratum_id, avg_value, stdev_value, min_value, max_value, total) as
(
 select stratum_id,
 CAST(avg(1.0 * count_value) AS FLOAT) as avg_value,
 CAST(STDDEV(count_value) AS FLOAT) as stdev_value,
 min(count_value) as min_value,
 max(count_value) as max_value,
 COUNT(*) as total
 FROM rawData
 group by stratum_id
),
statsView (stratum_id, count_value, total, rn) as
(
 select stratum_id, count_value, COUNT(*) as total, row_number() over (order by count_value) as rn
 FROM rawData
 group by stratum_id, count_value
),
priorStats (stratum_id, count_value, total, accumulated) as
(
 select s.stratum_id, s.count_value, s.total, sum(p.total) as accumulated
 from statsView s
 join statsView p on s.stratum_id = p.stratum_id and p.rn <= s.rn
 group by s.stratum_id, s.count_value, s.total, s.rn
)
 SELECT
1407 as analysis_id,
 CAST(o.stratum_id AS STRING) AS stratum_id,
 o.total as count_value,
 o.min_value,
 o.max_value,
 o.avg_value,
 o.stdev_value,
 MIN(case when p.accumulated >= .50 * o.total then count_value else o.max_value end) as median_value,
 MIN(case when p.accumulated >= .10 * o.total then count_value else o.max_value end) as p10_value,
 MIN(case when p.accumulated >= .25 * o.total then count_value else o.max_value end) as p25_value,
 MIN(case when p.accumulated >= .75 * o.total then count_value else o.max_value end) as p75_value,
 MIN(case when p.accumulated >= .90 * o.total then count_value else o.max_value end) as p90_value
FROM
priorStats p
join overallStats o on p.stratum_id = o.stratum_id
GROUP BY o.stratum_id, o.total, o.min_value, o.max_value, o.avg_value, o.stdev_value
;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_1407
 ZORDER BY stratum_id;
--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_1407
USING DELTA
AS
SELECT
analysis_id, stratum_id as stratum_1, 
cast(null as STRING) as stratum_2, cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
count_value, min_value, max_value, avg_value, stdev_value, median_value, p10_value, p25_value, p75_value, p90_value
FROM
<ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_1407
;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_1407
 ZORDER BY stratum_1;
truncate table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_1407;
drop table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_1407;

--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1408
USING DELTA
AS
SELECT
1408 as analysis_id, 
 CAST(floor(datediff(ppp1.payer_plan_period_end_date,ppp1.payer_plan_period_start_date)/30) AS STRING) as stratum_1,
 cast(null as STRING) as stratum_2, cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
 COUNT(distinct p1.person_id) as count_value
FROM
<CDM_DATABASE_NAME>.person p1
 inner join 
 (select person_id, 
 payer_plan_period_START_DATE, 
 payer_plan_period_END_DATE, 
 ROW_NUMBER() over (PARTITION by person_id order by payer_plan_period_start_date asc) as rn1
 from <CDM_DATABASE_NAME>.payer_plan_period
 ) ppp1
 on p1.PERSON_ID = ppp1.PERSON_ID
 where ppp1.rn1 = 1
group by CAST(floor(datediff(ppp1.payer_plan_period_end_date,ppp1.payer_plan_period_start_date)/30) AS STRING);
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1408
 ZORDER BY stratum_1;

CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otemp_dates_1409
USING DELTA
AS
SELECT
distinct 
 YEAR(payer_plan_period_start_date) as obs_year 
FROM
<CDM_DATABASE_NAME>.payer_plan_period
;
--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1409
USING DELTA
AS
SELECT
1409 as analysis_id, 
 CAST(t1.obs_year AS STRING) as stratum_1, 
 cast(null as STRING) as stratum_2, cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
 COUNT(distinct p1.PERSON_ID) as count_value
FROM
<CDM_DATABASE_NAME>.person p1
 inner join 
 <CDM_DATABASE_NAME>.payer_plan_period ppp1
 on p1.person_id = ppp1.person_id
 ,
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otemp_dates_1409 t1 
where year(ppp1.payer_plan_period_START_DATE) <= t1.obs_year
 and year(ppp1.payer_plan_period_END_DATE) >= t1.obs_year
group by t1.obs_year
;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1409
 ZORDER BY stratum_1;
truncate table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otemp_dates_1409;
drop table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otemp_dates_1409;

--HINT DISTRIBUTE_ON_KEY(obs_month) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otemp_dates_1410
USING DELTA
AS
SELECT
DISTINCT 
 YEAR(payer_plan_period_start_date)*100 + MONTH(payer_plan_period_start_date) AS obs_month,
 to_date(cast(YEAR(payer_plan_period_start_date) as string) || '-' || cast(MONTH(payer_plan_period_start_date) as string) || '-' || cast(1 as string)) as obs_month_start,
 last_day(payer_plan_period_start_date) as obs_month_end
FROM
<CDM_DATABASE_NAME>.payer_plan_period
;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otemp_dates_1410
 ZORDER BY obs_month;
--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1410
USING DELTA
AS
SELECT
1410 as analysis_id, 
 CAST(obs_month AS STRING) as stratum_1,
 cast(null as STRING) as stratum_2, cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
 COUNT(distinct p1.PERSON_ID) as count_value
FROM
<CDM_DATABASE_NAME>.person p1
 inner join 
 <CDM_DATABASE_NAME>.payer_plan_period ppp1
 on p1.person_id = ppp1.person_id
 ,
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otemp_dates_1410
where ppp1.payer_plan_period_START_DATE <= obs_month_start
 and ppp1.payer_plan_period_END_DATE >= obs_month_end
group by obs_month
;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1410
 ZORDER BY stratum_1;
TRUNCATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otemp_dates_1410;
DROP TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otemp_dates_1410;

--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1411
USING DELTA
AS
SELECT
1411 as analysis_id, 
 to_date(cast(YEAR(payer_plan_period_start_date) as string) || '-' || cast(MONTH(payer_plan_period_start_date) as string) || '-' || cast(1 as string)) as stratum_1,
 cast(null as STRING) as stratum_2, cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
 COUNT(distinct p1.PERSON_ID) as count_value
FROM
<CDM_DATABASE_NAME>.person p1
 inner join <CDM_DATABASE_NAME>.payer_plan_period ppp1
 on p1.person_id = ppp1.person_id
group by to_date(cast(YEAR(payer_plan_period_start_date) as string) || '-' || cast(MONTH(payer_plan_period_start_date) as string) || '-' || cast(1 as string));
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1411
 ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1412
USING DELTA
AS
SELECT
1412 as analysis_id, 
 to_date(cast(YEAR(payer_plan_period_start_date) as string) || '-' || cast(MONTH(payer_plan_period_start_date) as string) || '-' || cast(1 as string)) as stratum_1,
 cast(null as STRING) as stratum_2, cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
 COUNT(distinct p1.PERSON_ID) as count_value
FROM
<CDM_DATABASE_NAME>.person p1
 inner join <CDM_DATABASE_NAME>.payer_plan_period ppp1
 on p1.person_id = ppp1.person_id
group by to_date(cast(YEAR(payer_plan_period_start_date) as string) || '-' || cast(MONTH(payer_plan_period_start_date) as string) || '-' || cast(1 as string));
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1412
 ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1413
USING DELTA
AS
SELECT
1413 as analysis_id, 
 CAST(ppp1.num_periods AS STRING) as stratum_1,
 cast(null as STRING) as stratum_2, cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
 COUNT(distinct p1.PERSON_ID) as count_value
FROM
<CDM_DATABASE_NAME>.person p1
 inner join (select person_id, COUNT(payer_plan_period_start_date) as num_periods from <CDM_DATABASE_NAME>.payer_plan_period group by PERSON_ID) ppp1
 on p1.person_id = ppp1.person_id
group by ppp1.num_periods;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1413
 ZORDER BY stratum_1;

CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1414
USING DELTA
AS
SELECT
1414 as analysis_id, 
 cast(null as STRING) as stratum_1, cast(null as STRING) as stratum_2, cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
 COUNT(distinct p1.PERSON_ID) as count_value
FROM
<CDM_DATABASE_NAME>.person p1
 inner join (select person_id, MIN(year(payer_plan_period_start_date)) as first_obs_year from <CDM_DATABASE_NAME>.payer_plan_period group by PERSON_ID) ppp1
 on p1.person_id = ppp1.person_id
where p1.year_of_birth > ppp1.first_obs_year;

CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1415
USING DELTA
AS
SELECT
1415 as analysis_id, 
 cast(null as STRING) as stratum_1, cast(null as STRING) as stratum_2, cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
 COUNT(ppp1.PERSON_ID) as count_value
FROM
<CDM_DATABASE_NAME>.payer_plan_period ppp1
where ppp1.payer_plan_period_end_date < ppp1.payer_plan_period_start_date;

--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1425 
USING DELTA
AS
SELECT
1425 as analysis_id,
 cast(payer_source_concept_id AS STRING) AS stratum_1,
 cast(null AS STRING) AS stratum_2,
 cast(null as STRING) as stratum_3,
 cast(null as STRING) as stratum_4,
 cast(null as STRING) as stratum_5,
 COUNT(*) AS count_value
FROM
<CDM_DATABASE_NAME>.payer_plan_period
 group by payer_source_concept_id;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1425 
  ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1800
USING DELTA
AS
SELECT
1800 AS analysis_id,
 CAST(m.measurement_concept_id AS STRING) AS stratum_1,
 CAST(NULL AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 COUNT(DISTINCT m.person_id) AS count_value
FROM
<CDM_DATABASE_NAME>.measurement m
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 m.person_id = op.person_id
AND 
 m.measurement_date >= op.observation_period_start_date
AND 
 m.measurement_date <= op.observation_period_end_date 
GROUP BY 
 m.measurement_concept_id;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1800
 ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1801
USING DELTA
AS
SELECT
1801 AS analysis_id,
 CAST(m.measurement_concept_id AS STRING) AS stratum_1,
 CAST(NULL AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 COUNT(m.person_id) AS count_value
FROM
<CDM_DATABASE_NAME>.measurement m
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 m.person_id = op.person_id
AND 
 m.measurement_date >= op.observation_period_start_date
AND 
 m.measurement_date <= op.observation_period_end_date 
GROUP BY 
 m.measurement_concept_id;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1801
 ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(stratum_1)
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1802
USING DELTA
AS
WITH rawData  AS (
SELECT 
 m.measurement_concept_id AS stratum_1,
 YEAR(m.measurement_date) * 100 + MONTH(m.measurement_date) AS stratum_2,
 COUNT(DISTINCT m.person_id) AS count_value
FROM 
 <CDM_DATABASE_NAME>.measurement m
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 m.person_id = op.person_id
AND 
 m.measurement_date >= op.observation_period_start_date
AND 
 m.measurement_date <= op.observation_period_end_date 
GROUP BY 
 m.measurement_concept_id,
 YEAR(m.measurement_date) * 100 + MONTH(m.measurement_date)
)
 SELECT
1802 AS analysis_id,
 CAST(stratum_1 AS STRING) AS stratum_1,
 CAST(stratum_2 AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 count_value
FROM
rawData;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1802
 ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(count_value)
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_1803
USING DELTA
AS
WITH rawData(count_value)  AS (
SELECT 
 COUNT(DISTINCT m.measurement_concept_id) AS count_value
FROM 
 <CDM_DATABASE_NAME>.measurement m
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 m.person_id = op.person_id
AND 
 m.measurement_date >= op.observation_period_start_date
AND 
 m.measurement_date <= op.observation_period_end_date 
GROUP BY 
 m.person_id
),
overallStats (avg_value, stdev_value, min_value, max_value, total) as
(
 select CAST(avg(1.0 * count_value) AS FLOAT) as avg_value,
 CAST(STDDEV(count_value) AS FLOAT) as stdev_value,
 min(count_value) as min_value,
 max(count_value) as max_value,
 COUNT(*) as total
 from rawData
),
statsView (count_value, total, rn) as
(
 select count_value, 
 COUNT(*) as total, 
 row_number() over (order by count_value) as rn
 FROM rawData
 group by count_value
),
priorStats (count_value, total, accumulated) as
(
 select s.count_value, s.total, sum(p.total) as accumulated
 from statsView s
 join statsView p on p.rn <= s.rn
 group by s.count_value, s.total, s.rn
)
 SELECT
1803 as analysis_id,
 o.total as count_value,
 o.min_value,
 o.max_value,
 o.avg_value,
 o.stdev_value,
 MIN(case when p.accumulated >= .50 * o.total then count_value else o.max_value end) as median_value,
 MIN(case when p.accumulated >= .10 * o.total then count_value else o.max_value end) as p10_value,
 MIN(case when p.accumulated >= .25 * o.total then count_value else o.max_value end) as p25_value,
 MIN(case when p.accumulated >= .75 * o.total then count_value else o.max_value end) as p75_value,
 MIN(case when p.accumulated >= .90 * o.total then count_value else o.max_value end) as p90_value
FROM
priorStats p
CROSS JOIN overallStats o
GROUP BY o.total, o.min_value, o.max_value, o.avg_value, o.stdev_value
;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_1803
 ZORDER BY count_value;
--HINT DISTRIBUTE_ON_KEY(count_value) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_1803
USING DELTA
AS
SELECT
analysis_id, 
cast(null as STRING) as stratum_1, cast(null as STRING) as stratum_2, cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
count_value, min_value, max_value, avg_value, stdev_value, median_value, p10_value, p25_value, p75_value, p90_value
FROM
<ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_1803
;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_1803
 ZORDER BY count_value;
truncate table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_1803;
drop table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_1803;

--HINT DISTRIBUTE_ON_KEY(stratum_1)
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1804
USING DELTA
AS
WITH rawData  AS (
SELECT 
 m.measurement_concept_id AS stratum_1,
 YEAR(m.measurement_date) AS stratum_2,
 p.gender_concept_id AS stratum_3,
 FLOOR((YEAR(m.measurement_date) - p.year_of_birth) / 10) AS stratum_4,
 COUNT(DISTINCT p.person_id) AS count_value
FROM 
 <CDM_DATABASE_NAME>.person p
JOIN 
 <CDM_DATABASE_NAME>.measurement m 
ON 
 p.person_id = m.person_id
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 m.person_id = op.person_id
AND 
 m.measurement_date >= op.observation_period_start_date
AND 
 m.measurement_date <= op.observation_period_end_date 
GROUP BY 
 m.measurement_concept_id,
 YEAR(m.measurement_date),
 p.gender_concept_id,
 FLOOR((YEAR(m.measurement_date) - p.year_of_birth) / 10)
)
 SELECT
1804 AS analysis_id,
 CAST(stratum_1 AS STRING) AS stratum_1,
 CAST(stratum_2 AS STRING) AS stratum_2,
 CAST(stratum_3 AS STRING) AS stratum_3,
 CAST(stratum_4 AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 count_value
FROM
rawData;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1804
 ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1805
USING DELTA
AS
SELECT
1805 AS analysis_id,
 CAST(m.measurement_concept_id AS STRING) AS stratum_1,
 CAST(m.measurement_type_concept_id AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 COUNT(m.person_id) AS count_value
FROM
<CDM_DATABASE_NAME>.measurement m
 JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
 ON 
 m.person_id = op.person_id
 AND 
 m.measurement_date >= op.observation_period_start_date
 AND 
 m.measurement_date <= op.observation_period_end_date 
GROUP BY 
 m.measurement_concept_id,
 m.measurement_type_concept_id;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1805
 ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(subject_id) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1orawData_1806
USING DELTA
AS
SELECT
o.measurement_concept_id AS subject_id,
 p.gender_concept_id,
 o.measurement_start_year - p.year_of_birth AS count_value
FROM
<CDM_DATABASE_NAME>.person p
JOIN (
 SELECT 
 m.person_id,
 m.measurement_concept_id,
 MIN(YEAR(m.measurement_date)) AS measurement_start_year
 FROM 
 <CDM_DATABASE_NAME>.measurement m
 JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
 ON 
 m.person_id = op.person_id
 AND 
 m.measurement_date >= op.observation_period_start_date
 AND 
 m.measurement_date <= op.observation_period_end_date 
 GROUP BY 
 m.person_id,
 m.measurement_concept_id
 ) o
ON 
 p.person_id = o.person_id
;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1orawData_1806
 ZORDER BY subject_id;
--HINT DISTRIBUTE_ON_KEY(stratum1_id)
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_1806
USING DELTA
AS
WITH overallStats (stratum1_id, stratum2_id, avg_value, stdev_value, min_value, max_value, total)  AS (
 select subject_id as stratum1_id,
 gender_concept_id as stratum2_id,
 CAST(avg(1.0 * count_value) AS FLOAT) as avg_value,
 CAST(STDDEV(count_value) AS FLOAT) as stdev_value,
 min(count_value) as min_value,
 max(count_value) as max_value,
 COUNT(*) as total
 FROM <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1orawData_1806
 group by subject_id, gender_concept_id
),
statsView (stratum1_id, stratum2_id, count_value, total, rn) as
(
 select subject_id as stratum1_id, gender_concept_id as stratum2_id, count_value, COUNT(*) as total, row_number() over (partition by subject_id, gender_concept_id order by count_value) as rn
 FROM <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1orawData_1806
 group by subject_id, gender_concept_id, count_value
),
priorStats (stratum1_id, stratum2_id, count_value, total, accumulated) as
(
 select s.stratum1_id, s.stratum2_id, s.count_value, s.total, sum(p.total) as accumulated
 from statsView s
 join statsView p on s.stratum1_id = p.stratum1_id and s.stratum2_id = p.stratum2_id and p.rn <= s.rn
 group by s.stratum1_id, s.stratum2_id, s.count_value, s.total, s.rn
)
 SELECT
1806 as analysis_id,
 CAST(o.stratum1_id AS STRING) AS stratum1_id,
 CAST(o.stratum2_id AS STRING) AS stratum2_id,
 o.total as count_value,
 o.min_value,
 o.max_value,
 o.avg_value,
 o.stdev_value,
 MIN(case when p.accumulated >= .50 * o.total then count_value else o.max_value end) as median_value,
 MIN(case when p.accumulated >= .10 * o.total then count_value else o.max_value end) as p10_value,
 MIN(case when p.accumulated >= .25 * o.total then count_value else o.max_value end) as p25_value,
 MIN(case when p.accumulated >= .75 * o.total then count_value else o.max_value end) as p75_value,
 MIN(case when p.accumulated >= .90 * o.total then count_value else o.max_value end) as p90_value
FROM
priorStats p
join overallStats o on p.stratum1_id = o.stratum1_id and p.stratum2_id = o.stratum2_id 
GROUP BY o.stratum1_id, o.stratum2_id, o.total, o.min_value, o.max_value, o.avg_value, o.stdev_value
;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_1806
 ZORDER BY stratum1_id;
--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_1806
USING DELTA
AS
SELECT
analysis_id, stratum1_id as stratum_1, stratum2_id as stratum_2, 
cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
count_value, min_value, max_value, avg_value, stdev_value, median_value, p10_value, p25_value, p75_value, p90_value
FROM
<ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_1806
;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_1806
 ZORDER BY stratum_1;
truncate table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1orawData_1806;
drop table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1orawData_1806;
truncate table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_1806;
drop table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_1806;

--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1807
USING DELTA
AS
SELECT
1807 AS analysis_id,
 CAST(m.measurement_concept_id AS STRING) AS stratum_1,
 CAST(m.unit_concept_id AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 COUNT(m.person_id) AS count_value
FROM
<CDM_DATABASE_NAME>.measurement m
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 m.person_id = op.person_id
AND 
 m.measurement_date >= op.observation_period_start_date
AND 
 m.measurement_date <= op.observation_period_end_date 
GROUP BY 
 m.measurement_concept_id,
 m.unit_concept_id;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1807
 ZORDER BY stratum_1;

CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1809
USING DELTA
AS
SELECT
1809 as analysis_id, 
 cast(null as STRING) as stratum_1, cast(null as STRING) as stratum_2, cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
 COUNT(m.PERSON_ID) as count_value
FROM
<CDM_DATABASE_NAME>.measurement m
 left join <CDM_DATABASE_NAME>.person p1 on p1.person_id = m.person_id
where p1.person_id is null;

CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1810
USING DELTA
AS
SELECT
1810 as analysis_id, 
 cast(null as STRING) as stratum_1, cast(null as STRING) as stratum_2, cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
 COUNT(m.PERSON_ID) as count_value
FROM
<CDM_DATABASE_NAME>.measurement m
 left join <CDM_DATABASE_NAME>.observation_period op on op.person_id = m.person_id
 and m.measurement_date >= op.observation_period_start_date
 and m.measurement_date <= op.observation_period_end_date
where op.person_id is null;

CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1811
USING DELTA
AS
SELECT
1811 AS analysis_id,
 CAST(NULL AS STRING) AS stratum_1,
 CAST(NULL AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 COUNT(m.person_id) AS count_value
FROM
<CDM_DATABASE_NAME>.measurement m
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 m.person_id = op.person_id
AND 
 m.measurement_date >= op.observation_period_start_date
AND 
 m.measurement_date <= op.observation_period_end_date 
WHERE 
 m.value_as_number IS NOT NULL
OR 
 m.value_as_concept_id != 0;

CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1812
USING DELTA
AS
SELECT
1812 as analysis_id, 
 cast(null as STRING) as stratum_1, cast(null as STRING) as stratum_2, cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
 COUNT(m.PERSON_ID) as count_value
FROM
<CDM_DATABASE_NAME>.measurement m
 left join <CDM_DATABASE_NAME>.provider p on p.provider_id = m.provider_id
where m.provider_id is not null
 and p.provider_id is null;

CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1813
USING DELTA
AS
SELECT
1813 as analysis_id, 
cast(null as STRING) as stratum_1, cast(null as STRING) as stratum_2, cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
COUNT(m.PERSON_ID) as count_value
FROM
<CDM_DATABASE_NAME>.measurement m
 left join <CDM_DATABASE_NAME>.visit_occurrence vo on m.visit_occurrence_id = vo.visit_occurrence_id
where m.visit_occurrence_id is not null
 and vo.visit_occurrence_id is null;

CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1814
USING DELTA
AS
SELECT
1814 AS analysis_id,
 CAST(NULL AS STRING) AS stratum_1,
 CAST(NULL AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 COUNT(m.person_id) AS count_value
FROM
<CDM_DATABASE_NAME>.measurement m
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 m.person_id = op.person_id
AND 
 m.measurement_date >= op.observation_period_start_date
AND 
 m.measurement_date <= op.observation_period_end_date 
WHERE 
 m.value_as_number IS NULL
AND 
 (m.value_as_concept_id IS NULL OR m.value_as_concept_id = 0);

--HINT DISTRIBUTE_ON_KEY(stratum1_id) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1ostatsView_1815
USING DELTA
AS
SELECT
m.subject_id AS stratum1_id,
 m.unit_concept_id AS stratum2_id,
 m.count_value,
 COUNT(*) AS total,
 ROW_NUMBER() OVER (PARTITION BY m.subject_id,m.unit_concept_id ORDER BY m.count_value) AS rn
FROM
(
 SELECT 
 m.measurement_concept_id AS subject_id,
 m.unit_concept_id,
 CAST(m.value_as_number AS FLOAT) AS count_value
 FROM 
 <CDM_DATABASE_NAME>.measurement m
 JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
 ON 
 m.person_id = op.person_id
 AND 
 m.measurement_date >= op.observation_period_start_date
 AND 
 m.measurement_date <= op.observation_period_end_date 
 WHERE 
 m.unit_concept_id IS NOT NULL
 AND 
 m.value_as_number IS NOT NULL
 ) m
GROUP BY 
 m.subject_id, 
 m.unit_concept_id, 
 m.count_value
;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1ostatsView_1815
 ZORDER BY stratum1_id;
--HINT DISTRIBUTE_ON_KEY(stratum1_id) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_1815
USING DELTA
AS
SELECT
1815 as analysis_id,
 CAST(o.stratum1_id AS STRING) AS stratum1_id,
 CAST(o.stratum2_id AS STRING) AS stratum2_id,
 o.total as count_value,
 o.min_value,
 o.max_value,
 o.avg_value,
 o.stdev_value,
 MIN(case when p.accumulated >= .50 * o.total then count_value else o.max_value end) as median_value,
 MIN(case when p.accumulated >= .10 * o.total then count_value else o.max_value end) as p10_value,
 MIN(case when p.accumulated >= .25 * o.total then count_value else o.max_value end) as p25_value,
 MIN(case when p.accumulated >= .75 * o.total then count_value else o.max_value end) as p75_value,
 MIN(case when p.accumulated >= .90 * o.total then count_value else o.max_value end) as p90_value
FROM
(
 select s.stratum1_id, s.stratum2_id, s.count_value, s.total, sum(p.total) as accumulated
 from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1ostatsView_1815 s
 join <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1ostatsView_1815 p on s.stratum1_id = p.stratum1_id and s.stratum2_id = p.stratum2_id and p.rn <= s.rn
 group by s.stratum1_id, s.stratum2_id, s.count_value, s.total, s.rn
) p
join 
(
 SELECT 
 m.subject_id AS stratum1_id,
 m.unit_concept_id AS stratum2_id,
 CAST(AVG(1.0 * m.count_value) AS FLOAT) AS avg_value,
 CAST(STDDEV(m.count_value) AS FLOAT) AS stdev_value,
 MIN(m.count_value) AS min_value,
 MAX(m.count_value) AS max_value,
 COUNT(*) AS total
 FROM 
 (
 SELECT 
 m.measurement_concept_id AS subject_id,
 m.unit_concept_id,
 CAST(m.value_as_number AS FLOAT) AS count_value
 FROM 
 <CDM_DATABASE_NAME>.measurement m
 JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
 ON 
 m.person_id = op.person_id
 AND 
 m.measurement_date >= op.observation_period_start_date
 AND 
 m.measurement_date <= op.observation_period_end_date 
 WHERE 
 m.unit_concept_id IS NOT NULL
 AND 
 m.value_as_number IS NOT NULL
 ) m
 GROUP BY 
 m.subject_id, 
 m.unit_concept_id
) o on p.stratum1_id = o.stratum1_id and p.stratum2_id = o.stratum2_id 
GROUP BY o.stratum1_id, o.stratum2_id, o.total, o.min_value, o.max_value, o.avg_value, o.stdev_value
;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_1815
 ZORDER BY stratum1_id;
--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_1815
USING DELTA
AS
SELECT
analysis_id, stratum1_id as stratum_1, stratum2_id as stratum_2, 
cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
count_value, min_value, max_value, avg_value, stdev_value, median_value, p10_value, p25_value, p75_value, p90_value
FROM
<ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_1815
;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_1815
 ZORDER BY stratum_1;
truncate table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1ostatsView_1815;
drop table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1ostatsView_1815;
truncate table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_1815;
drop table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_1815;

--HINT DISTRIBUTE_ON_KEY(stratum1_id) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1ooverallStats_1816
USING DELTA
AS
SELECT
m.subject_id AS stratum1_id,
 m.unit_concept_id AS stratum2_id,
 CAST(AVG(1.0 * m.count_value) AS FLOAT) AS avg_value,
 CAST(STDDEV(m.count_value) AS FLOAT) AS stdev_value,
 MIN(m.count_value) AS min_value,
 MAX(m.count_value) AS max_value,
 COUNT(*) AS total
FROM
(
 SELECT 
 m.measurement_concept_id AS subject_id,
 m.unit_concept_id,
 CAST(m.range_low AS FLOAT) AS count_value
 FROM 
 <CDM_DATABASE_NAME>.measurement m
 JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
 ON 
 m.person_id = op.person_id
 AND 
 m.measurement_date >= op.observation_period_start_date
 AND 
 m.measurement_date <= op.observation_period_end_date 
 WHERE 
 m.unit_concept_id IS NOT NULL
 AND 
 m.value_as_number IS NOT NULL
 AND 
 m.range_low IS NOT NULL
 AND 
 m.range_high IS NOT NULL
 ) m
GROUP BY 
 m.subject_id, 
 m.unit_concept_id
;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1ooverallStats_1816
 ZORDER BY stratum1_id;
--HINT DISTRIBUTE_ON_KEY(stratum1_id) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1ostatsView_1816
USING DELTA
AS
SELECT
m.subject_id AS stratum1_id,
 m.unit_concept_id AS stratum2_id,
 m.count_value,
 COUNT(*) AS total,
 ROW_NUMBER() OVER (PARTITION BY m.subject_id,m.unit_concept_id ORDER BY m.count_value) AS rn
FROM
(
 SELECT 
 m.measurement_concept_id AS subject_id,
 m.unit_concept_id,
 CAST(m.range_low AS FLOAT) AS count_value
 FROM 
 <CDM_DATABASE_NAME>.measurement m
 JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
 ON 
 m.person_id = op.person_id
 AND 
 m.measurement_date >= op.observation_period_start_date
 AND 
 m.measurement_date <= op.observation_period_end_date 
 WHERE 
 m.unit_concept_id IS NOT NULL
 AND 
 m.value_as_number IS NOT NULL
 AND 
 m.range_low IS NOT NULL
 AND 
 m.range_high IS NOT NULL
 ) m
GROUP BY 
 m.subject_id, 
 m.unit_concept_id, 
 m.count_value
;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1ostatsView_1816
 ZORDER BY stratum1_id;
--HINT DISTRIBUTE_ON_KEY(stratum1_id) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_1816
USING DELTA
AS
SELECT
1816 as analysis_id,
 CAST(o.stratum1_id AS STRING) AS stratum1_id,
 CAST(o.stratum2_id AS STRING) AS stratum2_id,
 o.total as count_value,
 o.min_value,
 o.max_value,
 o.avg_value,
 o.stdev_value,
 MIN(case when p.accumulated >= .50 * o.total then count_value else o.max_value end) as median_value,
 MIN(case when p.accumulated >= .10 * o.total then count_value else o.max_value end) as p10_value,
 MIN(case when p.accumulated >= .25 * o.total then count_value else o.max_value end) as p25_value,
 MIN(case when p.accumulated >= .75 * o.total then count_value else o.max_value end) as p75_value,
 MIN(case when p.accumulated >= .90 * o.total then count_value else o.max_value end) as p90_value
FROM
(
 select s.stratum1_id, s.stratum2_id, s.count_value, s.total, sum(p.total) as accumulated
 from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1ostatsView_1816 s
 join <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1ostatsView_1816 p on s.stratum1_id = p.stratum1_id and s.stratum2_id = p.stratum2_id and p.rn <= s.rn
 group by s.stratum1_id, s.stratum2_id, s.count_value, s.total, s.rn
) p
join <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1ooverallStats_1816 o on p.stratum1_id = o.stratum1_id and p.stratum2_id = o.stratum2_id 
GROUP BY o.stratum1_id, o.stratum2_id, o.total, o.min_value, o.max_value, o.avg_value, o.stdev_value
;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_1816
 ZORDER BY stratum1_id;
--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_1816
USING DELTA
AS
SELECT
analysis_id, stratum1_id as stratum_1, stratum2_id as stratum_2, 
 cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
 count_value, min_value, max_value, avg_value, stdev_value, median_value, p10_value, p25_value, p75_value, p90_value
FROM
<ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_1816
;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_1816
 ZORDER BY stratum_1;
truncate table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1ooverallStats_1816;
drop table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1ooverallStats_1816;
truncate table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1ostatsView_1816;
drop table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1ostatsView_1816;
truncate table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_1816;
drop table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_1816;

--HINT DISTRIBUTE_ON_KEY(stratum1_id) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1ooverallStats_1817
USING DELTA
AS
SELECT
m.subject_id AS stratum1_id,
 m.unit_concept_id AS stratum2_id,
 CAST(AVG(1.0 * m.count_value) AS FLOAT) AS avg_value,
 CAST(STDDEV(m.count_value) AS FLOAT) AS stdev_value,
 MIN(m.count_value) AS min_value,
 MAX(m.count_value) AS max_value,
 COUNT(*) AS total
FROM
(
 SELECT 
 measurement_concept_id AS subject_id,
 unit_concept_id,
 CAST(range_high AS FLOAT) AS count_value
 FROM 
 <CDM_DATABASE_NAME>.measurement m
 JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
 ON 
 m.person_id = op.person_id
 AND 
 m.measurement_date >= op.observation_period_start_date
 AND 
 m.measurement_date <= op.observation_period_end_date 
 WHERE 
 m.unit_concept_id IS NOT NULL
 AND 
 m.value_as_number IS NOT NULL
 AND 
 m.range_low IS NOT NULL
 AND 
 m.range_high IS NOT NULL
 ) m
GROUP BY 
 m.subject_id, 
 m.unit_concept_id
;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1ooverallStats_1817
 ZORDER BY stratum1_id;
--HINT DISTRIBUTE_ON_KEY(stratum1_id) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1ostatsView_1817
USING DELTA
AS
SELECT
m.subject_id AS stratum1_id,
 m.unit_concept_id AS stratum2_id,
 m.count_value,
 COUNT(*) AS total,
 ROW_NUMBER() OVER (PARTITION BY m.subject_id,m.unit_concept_id ORDER BY m.count_value) AS rn
FROM
(
 SELECT 
 m.measurement_concept_id AS subject_id,
 m.unit_concept_id,
 CAST(m.range_high AS FLOAT) AS count_value
 FROM 
 <CDM_DATABASE_NAME>.measurement m
 JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
 ON 
 m.person_id = op.person_id
 AND 
 m.measurement_date >= op.observation_period_start_date
 AND 
 m.measurement_date <= op.observation_period_end_date 
 WHERE 
 m.unit_concept_id IS NOT NULL
 AND 
 m.value_as_number IS NOT NULL
 AND 
 m.range_low IS NOT NULL
 AND 
 m.range_high IS NOT NULL
 ) m
GROUP BY 
 m.subject_id, 
 m.unit_concept_id, 
 m.count_value
;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1ostatsView_1817
 ZORDER BY stratum1_id;
--HINT DISTRIBUTE_ON_KEY(stratum1_id) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_1817
USING DELTA
AS
SELECT
1817 as analysis_id,
 CAST(o.stratum1_id AS STRING) AS stratum1_id,
 CAST(o.stratum2_id AS STRING) AS stratum2_id,
 o.total as count_value,
 o.min_value,
 o.max_value,
 o.avg_value,
 o.stdev_value,
 MIN(case when p.accumulated >= .50 * o.total then count_value else o.max_value end) as median_value,
 MIN(case when p.accumulated >= .10 * o.total then count_value else o.max_value end) as p10_value,
 MIN(case when p.accumulated >= .25 * o.total then count_value else o.max_value end) as p25_value,
 MIN(case when p.accumulated >= .75 * o.total then count_value else o.max_value end) as p75_value,
 MIN(case when p.accumulated >= .90 * o.total then count_value else o.max_value end) as p90_value
FROM
(
 select s.stratum1_id, s.stratum2_id, s.count_value, s.total, sum(p.total) as accumulated
 from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1ostatsView_1817 s
 join <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1ostatsView_1817 p on s.stratum1_id = p.stratum1_id and s.stratum2_id = p.stratum2_id and p.rn <= s.rn
 group by s.stratum1_id, s.stratum2_id, s.count_value, s.total, s.rn
) p
join <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1ooverallStats_1817 o on p.stratum1_id = o.stratum1_id and p.stratum2_id = o.stratum2_id
GROUP BY o.stratum1_id, o.stratum2_id, o.total, o.min_value, o.max_value, o.avg_value, o.stdev_value
;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_1817
 ZORDER BY stratum1_id;
--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_1817
USING DELTA
AS
SELECT
analysis_id, stratum1_id as stratum_1, stratum2_id as stratum_2, 
 cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
 count_value, min_value, max_value, avg_value, stdev_value, median_value, p10_value, p25_value, p75_value, p90_value
FROM
<ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_1817
;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_1817
 ZORDER BY stratum_1;
truncate table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1ooverallStats_1817;
drop table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1ooverallStats_1817;
truncate table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1ostatsView_1817;
drop table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1ostatsView_1817;
truncate table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_1817;
drop table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_1817;

--HINT DISTRIBUTE_ON_KEY(person_id) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1orawData_1818
USING DELTA
AS
SELECT
m.person_id,
 m.measurement_concept_id,
 m.unit_concept_id,
 CAST(CASE 
 WHEN m.value_as_number < m.range_low
 THEN 'Below Range Low'
 WHEN m.value_as_number >= m.range_low AND m.value_as_number <= m.range_high
 THEN 'Within Range'
 WHEN m.value_as_number > m.range_high
 THEN 'Above Range High'
 ELSE 'Other'
 END AS STRING) AS stratum_3
FROM
<CDM_DATABASE_NAME>.measurement m
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 m.person_id = op.person_id
AND 
 m.measurement_date >= op.observation_period_start_date
AND 
 m.measurement_date <= op.observation_period_end_date 
WHERE 
 m.value_as_number IS NOT NULL
AND 
 m.unit_concept_id IS NOT NULL
AND 
 m.range_low IS NOT NULL
AND 
 m.range_high IS NOT NULL;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1orawData_1818
 ZORDER BY person_id;
--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1818
USING DELTA
AS
SELECT
1818 AS analysis_id,
 CAST(measurement_concept_id AS STRING) AS stratum_1,
 CAST(unit_concept_id AS STRING) AS stratum_2,
 CAST(stratum_3 AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 COUNT(person_id) AS count_value
FROM
<ACHILLES_RESULTS_DATABASE_NAME>.knevvj1orawData_1818
GROUP BY 
 measurement_concept_id,
 unit_concept_id,
 stratum_3;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1818
 ZORDER BY stratum_1;
TRUNCATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1orawData_1818;
DROP TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1orawData_1818;

CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1819
USING DELTA
AS
SELECT
1819 AS analysis_id,
 CAST(m.measurement_concept_id AS STRING) AS stratum_1,
 CAST(NULL AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 COUNT(m.person_id) AS count_value
FROM
<CDM_DATABASE_NAME>.measurement m
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 m.person_id = op.person_id
AND 
 m.measurement_date >= op.observation_period_start_date
AND 
 m.measurement_date <= op.observation_period_end_date 
WHERE 
 m.value_as_number IS NOT NULL
OR 
 m.value_as_concept_id != 0
GROUP BY 
 m.measurement_concept_id;

--HINT DISTRIBUTE_ON_KEY(stratum_1)
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1820
USING DELTA
AS
WITH rawData  AS (
SELECT 
 YEAR(m.measurement_date) * 100 + MONTH(m.measurement_date) AS stratum_1,
 COUNT(m.person_id) AS count_value
FROM 
 <CDM_DATABASE_NAME>.measurement m
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 m.person_id = op.person_id
AND 
 m.measurement_date >= op.observation_period_start_date
AND 
 m.measurement_date <= op.observation_period_end_date 
GROUP BY 
 YEAR(m.measurement_date) * 100 + MONTH(m.measurement_date)
)
 SELECT
1820 AS analysis_id,
 CAST(stratum_1 AS STRING) AS stratum_1,
 CAST(NULL AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 count_value
FROM
rawData;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1820
 ZORDER BY stratum_1;

CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1821
USING DELTA
AS
SELECT
1821 as analysis_id, 
 cast(null as STRING) as stratum_1, cast(null as STRING) as stratum_2, cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
 COUNT(m.PERSON_ID) as count_value
FROM
<CDM_DATABASE_NAME>.measurement m
where m.value_as_number is null;

--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1822
USING DELTA
AS
SELECT
1822 AS analysis_id,
 CAST(m.measurement_concept_id AS STRING) AS stratum_1,
 CAST(m.value_as_concept_id AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 COUNT(*) AS count_value
FROM
<CDM_DATABASE_NAME>.measurement m
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 m.person_id = op.person_id
AND 
 m.measurement_date >= op.observation_period_start_date
AND 
 m.measurement_date <= op.observation_period_end_date 
GROUP BY 
 m.measurement_concept_id,
 m.value_as_concept_id;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1822
 ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1823
USING DELTA
AS
SELECT
1823 AS analysis_id,
 CAST(m.measurement_concept_id AS STRING) AS stratum_1,
 CAST(m.operator_concept_id AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 COUNT(*) AS count_value
FROM
<CDM_DATABASE_NAME>.measurement m
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 m.person_id = op.person_id
AND 
 m.measurement_date >= op.observation_period_start_date
AND 
 m.measurement_date <= op.observation_period_end_date 
GROUP BY 
 m.measurement_concept_id,
 m.operator_concept_id;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1823
 ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1825
USING DELTA
AS
SELECT
1825 AS analysis_id,
 CAST(m.measurement_source_concept_id AS STRING) AS stratum_1,
 CAST(NULL AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 COUNT(*) AS count_value
FROM
<CDM_DATABASE_NAME>.measurement m
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 m.person_id = op.person_id
AND 
 m.measurement_date >= op.observation_period_start_date
AND 
 m.measurement_date <= op.observation_period_end_date 
GROUP BY 
 m.measurement_source_concept_id;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1825
 ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1826
USING DELTA
AS
SELECT
1826 AS analysis_id,
 CAST(m.value_as_concept_id AS STRING) AS stratum_1,
 CAST(NULL AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 COUNT(*) AS count_value
FROM
<CDM_DATABASE_NAME>.measurement m
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 m.person_id = op.person_id
AND 
 m.measurement_date >= op.observation_period_start_date
AND 
 m.measurement_date <= op.observation_period_end_date 
GROUP BY 
 m.value_as_concept_id;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1826
 ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1827
USING DELTA
AS
SELECT
1827 AS analysis_id,
 CAST(m.unit_concept_id AS STRING) AS stratum_1,
 CAST(NULL AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 COUNT(*) AS count_value
FROM
<CDM_DATABASE_NAME>.measurement m
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 m.person_id = op.person_id
AND 
 m.measurement_date >= op.observation_period_start_date
AND 
 m.measurement_date <= op.observation_period_end_date 
GROUP BY 
 m.unit_concept_id;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1827
 ZORDER BY stratum_1;

CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1830
USING DELTA
AS
SELECT
1830 AS analysis_id,
 CAST(NULL AS STRING) AS stratum_1,
 CAST(NULL AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 COUNT(*) AS count_value
FROM
<CDM_DATABASE_NAME>.measurement m
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 m.person_id = op.person_id
AND 
 m.measurement_date >= op.observation_period_start_date
AND 
 m.measurement_date <= op.observation_period_end_date;

DROP VIEW IF EXISTS op_outside  ; 
CREATE TEMPORARY VIEW op_outside   AS (SELECT 
 COUNT(DISTINCT m.person_id) AS person_count
FROM 
 <CDM_DATABASE_NAME>.measurement m
LEFT JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 m.person_id = op.person_id
AND 
 m.measurement_date >= op.observation_period_start_date
AND 
 m.measurement_date <= op.observation_period_end_date
WHERE
 op.person_id IS NULL
);
DROP VIEW IF EXISTS m_total ; 
CREATE TEMPORARY VIEW m_total  AS (SELECT
 COUNT(DISTINCT person_id) person_count
FROM
 <CDM_DATABASE_NAME>.measurement
);
 CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1831
USING DELTA
AS
(SELECT
1831 AS analysis_id,
 CASE WHEN mt.person_count != 0 THEN 
 CAST(CAST(1.0*op.person_count/mt.person_count AS FLOAT) AS STRING) 
 ELSE 
 CAST(NULL AS STRING) 
 END AS stratum_1, 
 CAST(op.person_count AS STRING) AS stratum_2,
 CAST(mt.person_count AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 SIGN(op.person_count) AS count_value
FROM
op_outside op
CROSS JOIN 
 m_total mt);

DROP VIEW IF EXISTS op_outside  ; 
CREATE TEMPORARY VIEW op_outside   AS (SELECT 
 COUNT(*) AS record_count
FROM 
 <CDM_DATABASE_NAME>.measurement m
LEFT JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 m.person_id = op.person_id
AND 
 m.measurement_date >= op.observation_period_start_date
AND 
 m.measurement_date <= op.observation_period_end_date
WHERE
 op.person_id IS NULL
);
DROP VIEW IF EXISTS m_total ; 
CREATE TEMPORARY VIEW m_total  AS (SELECT
 COUNT(*) record_count
FROM
 <CDM_DATABASE_NAME>.measurement
);
 CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1832
USING DELTA
AS
(SELECT
1832 AS analysis_id,
 CASE WHEN mt.record_count != 0 THEN 
 CAST(CAST(1.0*op.record_count/mt.record_count AS FLOAT) AS STRING) 
 ELSE 
 CAST(NULL AS STRING) 
 END AS stratum_1, 
 CAST(op.record_count AS STRING) AS stratum_2,
 CAST(mt.record_count AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 SIGN(op.record_count) AS count_value
FROM
op_outside op
CROSS JOIN 
 m_total mt);

CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1833
USING DELTA
AS
SELECT
1833 AS analysis_id,
 m.measurement_concept_id AS stratum_1,
 CAST(SUM(CASE WHEN m.value_as_number IS NULL 
 AND COALESCE(m.value_as_concept_id,0) = 0 
 THEN 1 ELSE 0 END) AS STRING) AS stratum_2,
 CAST(CAST(1.0*SUM(CASE WHEN m.value_as_number IS NULL AND COALESCE(m.value_as_concept_id,0) = 0 
 THEN 1 ELSE 0 END)/(CASE WHEN COUNT(*)=0 THEN 1 ELSE COUNT(*) END) AS FLOAT) AS STRING) AS stratum_3, 
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 COUNT(*) AS count_value
FROM
<CDM_DATABASE_NAME>.measurement m
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 m.person_id = op.person_id
AND 
 m.measurement_date >= op.observation_period_start_date
AND 
 m.measurement_date <= op.observation_period_end_date
GROUP BY 
 m.measurement_concept_id;

--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1891
USING DELTA
AS
SELECT
1891 AS analysis_id,
 CAST(m.measurement_concept_id AS STRING) AS stratum_1,
 CAST(m.meas_cnt AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 SUM(COUNT(m.person_id)) OVER (PARTITION BY m.measurement_concept_id ORDER BY m.meas_cnt DESC) AS count_value
FROM
(
 SELECT 
 m.measurement_concept_id,
 COUNT(m.measurement_id) AS meas_cnt,
 m.person_id
 FROM 
 <CDM_DATABASE_NAME>.measurement m
 JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
 ON 
 m.person_id = op.person_id
 AND 
 m.measurement_date >= op.observation_period_start_date
 AND 
 m.measurement_date <= op.observation_period_end_date 
 GROUP BY 
 m.person_id,
 m.measurement_concept_id
 ) m
GROUP BY 
 m.measurement_concept_id,
 m.meas_cnt;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1891
 ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1900
USING DELTA
AS
SELECT
1900 as analysis_id, 
 cast(table_name as STRING) as stratum_1, 
 cast(column_name as STRING) as stratum_2, 
 source_value as stratum_3, 
 cast(null as STRING) as stratum_4, 
 cast(null as STRING) as stratum_5,
cnt as count_value
FROM
(
 select 'measurement' as table_name, 'measurement_source_value' as column_name, measurement_source_value as source_value, COUNT(*) as cnt from <CDM_DATABASE_NAME>.measurement where measurement_concept_id = 0 group by measurement_source_value 
 union
 select 'measurement' as table_name, 'unit_source_value' as column_name, unit_source_value as source_value, COUNT(*) as cnt from <CDM_DATABASE_NAME>.measurement where unit_concept_id = 0 group by unit_source_value 
 union
 select 'procedure_occurrence' as table_name,'procedure_source_value' as column_name, procedure_source_value as source_value, COUNT(*) as cnt from <CDM_DATABASE_NAME>.procedure_occurrence where procedure_concept_id = 0 group by procedure_source_value 
 union
 select 'procedure_occurrence' as table_name,'modifier_source_value' as column_name, modifier_source_value as source_value, COUNT(*) as cnt from <CDM_DATABASE_NAME>.procedure_occurrence where modifier_concept_id = 0 group by modifier_source_value 
 union
 select 'drug_exposure' as table_name, 'drug_source_value' as column_name, drug_source_value as source_value, COUNT(*) as cnt from <CDM_DATABASE_NAME>.drug_exposure where drug_concept_id = 0 group by drug_source_value 
 union
 select 'drug_exposure' as table_name, 'route_source_value' as column_name, route_source_value as source_value, COUNT(*) as cnt from <CDM_DATABASE_NAME>.drug_exposure where route_concept_id = 0 group by route_source_value 
 union
 select 'condition_occurrence' as table_name, 'condition_source_value' as column_name, condition_source_value as source_value, COUNT(*) as cnt from <CDM_DATABASE_NAME>.condition_occurrence where condition_concept_id = 0 group by condition_source_value 
 union
 select 'condition_occurrence' as table_name, 'condition_status_source_value' as column_name, condition_status_source_value as source_value, COUNT(*) as cnt from <CDM_DATABASE_NAME>.condition_occurrence where condition_status_concept_id = 0 group by condition_status_source_value 
 union
 select 'observation' as table_name, 'observation_source_value' as column_name, observation_source_value as source_value, COUNT(*) as cnt from <CDM_DATABASE_NAME>.observation where observation_concept_id = 0 group by observation_source_value 
 union
 select 'observation' as table_name, 'unit_source_value' as column_name, unit_source_value as source_value, COUNT(*) as cnt from <CDM_DATABASE_NAME>.observation where unit_concept_id = 0 group by unit_source_value 
 union
 select 'observation' as table_name, 'qualifier_source_value' as column_name, qualifier_source_value as source_value, COUNT(*) as cnt from <CDM_DATABASE_NAME>.observation where qualifier_concept_id = 0 group by qualifier_source_value
 union
 select 'payer_plan_period' as table_name, 'payer_source_value' as column_name, payer_source_value as source_value, COUNT(*) as cnt from <CDM_DATABASE_NAME>.payer_plan_period where payer_concept_id = 0 group by payer_source_value 
 union
 select 'payer_plan_period' as table_name, 'plan_source_value' as column_name, plan_source_value as source_value, COUNT(*) as cnt from <CDM_DATABASE_NAME>.payer_plan_period where plan_concept_id = 0 group by plan_source_value 
 union
 select 'payer_plan_period' as table_name, 'sponsor_source_value' as column_name, sponsor_source_value as source_value, COUNT(*) as cnt from <CDM_DATABASE_NAME>.payer_plan_period where sponsor_concept_id = 0 group by sponsor_source_value 
 union
 select 'payer_plan_period' as table_name, 'stop_reason_source_value' as column_name, stop_reason_source_value as source_value, COUNT(*) as cnt from <CDM_DATABASE_NAME>.payer_plan_period where stop_reason_concept_id = 0 group by stop_reason_source_value 
 union
 select 'provider' as table_name, 'specialty_source_value' as column_name, specialty_source_value as source_value, COUNT(*) as cnt from <CDM_DATABASE_NAME>.provider where specialty_concept_id = 0 group by specialty_source_value
 union 
 select 'provider' as table_name, 'gender_source_value' as column_name, gender_source_value as source_value, COUNT(*) as cnt from <CDM_DATABASE_NAME>.provider where gender_concept_id = 0 group by gender_source_value
 union 
 select 'person' as table_name, 'gender_source_value' as column_name, gender_source_value as source_value, COUNT(*) as cnt from <CDM_DATABASE_NAME>.person where gender_concept_id = 0 group by gender_source_value 
 union
 select 'person' as table_name, 'race_source_value' as column_name, race_source_value as source_value, COUNT(*) as cnt from <CDM_DATABASE_NAME>.person where race_concept_id = 0 group by race_source_value 
 union
 select 'person' as table_name, 'ethnicity_source_value' as column_name, ethnicity_source_value as source_value, COUNT(*) as cnt from <CDM_DATABASE_NAME>.person where ethnicity_concept_id = 0 group by ethnicity_source_value 
 union
 select 'specimen' as table_name, 'specimen_source_value' as column_name, specimen_source_value as source_value, COUNT(*) as cnt from <CDM_DATABASE_NAME>.specimen where specimen_concept_id = 0 group by specimen_source_value 
 union
 select 'specimen' as table_name, 'unit_source_value' as column_name, unit_source_value as source_value, COUNT(*) as cnt from <CDM_DATABASE_NAME>.specimen where unit_concept_id = 0 group by unit_source_value 
 union
 select 'specimen' as table_name, 'anatomic_site_source_value' as column_name, anatomic_site_source_value as source_value, COUNT(*) as cnt from <CDM_DATABASE_NAME>.specimen where anatomic_site_concept_id = 0 group by anatomic_site_source_value 
 union
 select 'specimen' as table_name, 'disease_status_source_value' as column_name, disease_status_source_value as source_value, COUNT(*) as cnt from <CDM_DATABASE_NAME>.specimen where disease_status_concept_id = 0 group by disease_status_source_value 
 union
 select 'visit_occurrence' as table_name, 'visit_source_value' as column_name, visit_source_value as source_value, COUNT(*) as cnt from <CDM_DATABASE_NAME>.visit_occurrence where visit_concept_id = 0 group by visit_source_value
 union
 select 'visit_occurrence' as table_name, 'admitting_source_value' as column_name, admitting_source_value as source_value, COUNT(*) as cnt from <CDM_DATABASE_NAME>.visit_occurrence where admitting_source_concept_id = 0 group by admitting_source_value
 union
 select 'visit_occurrence' as table_name, 'discharge_to_source_value' as column_name, discharge_to_source_value as source_value, COUNT(*) as cnt from <CDM_DATABASE_NAME>.visit_occurrence where discharge_to_concept_id = 0 group by discharge_to_source_value
 union
 select 'device_exposure' as table_name, 'device_source_value' as column_name, device_source_value as source_value, COUNT(*) as cnt from <CDM_DATABASE_NAME>.device_exposure where device_concept_id = 0 group by device_source_value
 union
 select 'death' as table_name, 'cause_source_value' as column_name, cause_source_value as source_value, COUNT(*) as cnt from <CDM_DATABASE_NAME>.death where cause_concept_id = 0 group by cause_source_value
) a
where cnt >= 1;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1900
  ZORDER BY stratum_1;

CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_2000
USING DELTA
AS
SELECT
2000 AS analysis_id,
 CAST(NULL AS STRING) AS stratum_1,
 CAST(NULL AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 CAST(d.cnt AS BIGINT) AS count_value
FROM
(
SELECT COUNT(*) cnt
FROM (
 SELECT DISTINCT person_id
 FROM (
 SELECT
 co.person_id
 FROM
 <CDM_DATABASE_NAME>.condition_occurrence co
 JOIN
 <CDM_DATABASE_NAME>.observation_period op
 ON
 co.person_id = op.person_id
 AND
 co.condition_start_date >= op.observation_period_start_date
 AND
 co.condition_start_date <= op.observation_period_end_date
 ) a
 INTERSECT
 SELECT DISTINCT person_id
 FROM (
 SELECT
 de.person_id
 FROM
 <CDM_DATABASE_NAME>.drug_exposure de
 JOIN
 <CDM_DATABASE_NAME>.observation_period op
 ON
 de.person_id = op.person_id
 AND
 de.drug_exposure_start_date >= op.observation_period_start_date
 AND
 de.drug_exposure_start_date <= op.observation_period_end_date
 ) b
 ) c
) d;

CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_2001
USING DELTA
AS
SELECT
2001 AS analysis_id,
 CAST(NULL AS STRING) AS stratum_1,
 CAST(NULL AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 CAST(d.cnt AS BIGINT) AS count_value
FROM
(
SELECT COUNT(*) cnt
FROM (
 SELECT DISTINCT person_id
 FROM (
 SELECT
 co.person_id
 FROM
 <CDM_DATABASE_NAME>.condition_occurrence co
 JOIN
 <CDM_DATABASE_NAME>.observation_period op
 ON
 co.person_id = op.person_id
 AND
 co.condition_start_date >= op.observation_period_start_date
 AND
 co.condition_start_date <= op.observation_period_end_date
 ) a
 INTERSECT
 SELECT DISTINCT person_id
 FROM (
 SELECT
 po.person_id
 FROM
 <CDM_DATABASE_NAME>.procedure_occurrence po
 JOIN
 <CDM_DATABASE_NAME>.observation_period op
 ON
 po.person_id = op.person_id
 AND
 po.procedure_date >= op.observation_period_start_date
 AND
 po.procedure_date <= op.observation_period_end_date
 ) b
 ) c
) d;

CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_2002
USING DELTA
AS
SELECT
2002 AS analysis_id,
 CAST(NULL AS STRING) AS stratum_1,
 CAST(NULL AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 CAST(e.cnt AS BIGINT) AS count_value
FROM
(
SELECT COUNT(*) cnt
FROM (
 SELECT DISTINCT person_id
 FROM (
 SELECT
 m.person_id
 FROM
 <CDM_DATABASE_NAME>.measurement m
 JOIN
 <CDM_DATABASE_NAME>.observation_period op
 ON
 m.person_id = op.person_id
 AND
 m.measurement_date >= op.observation_period_start_date
 AND
 m.measurement_date <= op.observation_period_end_date
 ) a
 INTERSECT
 SELECT DISTINCT person_id
 FROM (
 SELECT
 co.person_id
 FROM
 <CDM_DATABASE_NAME>.condition_occurrence co
 JOIN
 <CDM_DATABASE_NAME>.observation_period op
 ON
 co.person_id = op.person_id
 AND
 co.condition_start_date >= op.observation_period_start_date
 AND
 co.condition_start_date <= op.observation_period_end_date
 ) b
 INTERSECT
 SELECT DISTINCT person_id
 FROM (
 SELECT
 de.person_id
 FROM
 <CDM_DATABASE_NAME>.drug_exposure de
 JOIN
 <CDM_DATABASE_NAME>.observation_period op
 ON
 de.person_id = op.person_id
 AND
 de.drug_exposure_start_date >= op.observation_period_start_date
 AND
 de.drug_exposure_start_date <= op.observation_period_end_date
 ) c
 ) d
) e;

CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_2003
USING DELTA
AS
SELECT
2003 AS analysis_id,
 CAST(NULL AS STRING) AS stratum_1,
 CAST(NULL AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 COUNT(DISTINCT vo.person_id) AS count_value
FROM
<CDM_DATABASE_NAME>.visit_occurrence vo
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 vo.person_id = op.person_id
AND 
 vo.visit_start_date >= op.observation_period_start_date
AND 
 vo.visit_start_date <= op.observation_period_end_date;

CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oconoc 
USING DELTA
AS
SELECT
distinct person_id 
FROM
<CDM_DATABASE_NAME>.condition_occurrence;
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odrexp 
USING DELTA
AS
SELECT
distinct person_id 
FROM
<CDM_DATABASE_NAME>.drug_exposure;
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odvexp 
USING DELTA
AS
SELECT
distinct person_id 
FROM
<CDM_DATABASE_NAME>.device_exposure;
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1omsmt 
USING DELTA
AS
SELECT
distinct person_id 
FROM
<CDM_DATABASE_NAME>.measurement;
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odeath 
USING DELTA
AS
SELECT
distinct person_id 
FROM
<CDM_DATABASE_NAME>.death;
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oprococ 
USING DELTA
AS
SELECT
distinct person_id 
FROM
<CDM_DATABASE_NAME>.procedure_occurrence;
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oobs 
USING DELTA
AS
SELECT
distinct person_id 
FROM
<CDM_DATABASE_NAME>.observation;
DROP VIEW IF EXISTS rawData ; 
CREATE TEMPORARY VIEW rawData  AS (select 2004 as analysis_id,
 CAST('0000001' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oobs) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('0000010' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oprococ) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('0000011' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oprococ intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oobs) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('0000100' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odeath) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('0000101' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odeath intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oobs) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('0000110' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odeath intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oprococ) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('0000111' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odeath intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oprococ intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oobs) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('0001000' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1omsmt) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('0001001' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1omsmt intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oobs) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('0001010' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1omsmt intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oprococ) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('0001011' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1omsmt intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oprococ intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oobs) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('0001100' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1omsmt intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odeath) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('0001101' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1omsmt intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odeath intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oobs) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('0001110' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1omsmt intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odeath intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oprococ) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('0001111' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1omsmt intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odeath intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oprococ intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oobs) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('0010000' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odvexp) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('0010001' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odvexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oobs) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('0010010' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odvexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oprococ) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('0010011' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odvexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oprococ intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oobs) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('0010100' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odvexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odeath) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('0010101' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odvexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odeath intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oobs) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('0010110' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odvexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odeath intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oprococ) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('0010111' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odvexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odeath intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oprococ intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oobs) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('0011000' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odvexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1omsmt) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('0011001' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odvexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1omsmt intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oobs) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('0011010' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odvexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1omsmt intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oprococ) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('0011011' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odvexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1omsmt intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oprococ intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oobs) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('0011100' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odvexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1omsmt intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odeath) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('0011101' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odvexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1omsmt intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odeath intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oobs) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('0011110' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odvexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1omsmt intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odeath intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oprococ) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('0011111' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odvexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1omsmt intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odeath intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oprococ intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oobs) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('0100000' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odrexp) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('0100001' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odrexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oobs) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('0100010' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odrexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oprococ) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('0100011' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odrexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oprococ intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oobs) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('0100100' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odrexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odeath) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('0100101' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odrexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odeath intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oobs) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('0100110' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odrexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odeath intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oprococ) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('0100111' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odrexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odeath intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oprococ intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oobs) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('0101000' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odrexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1omsmt) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('0101001' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odrexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1omsmt intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oobs) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('0101010' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odrexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1omsmt intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oprococ) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('0101011' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odrexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1omsmt intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oprococ intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oobs) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('0101100' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odrexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1omsmt intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odeath) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('0101101' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odrexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1omsmt intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odeath intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oobs) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('0101110' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odrexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1omsmt intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odeath intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oprococ) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('0101111' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odrexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1omsmt intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odeath intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oprococ intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oobs) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('0110000' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odrexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odvexp) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('0110001' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odrexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odvexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oobs) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('0110010' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odrexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odvexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oprococ) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('0110011' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odrexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odvexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oprococ intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oobs) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('0110100' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odrexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odvexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odeath) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('0110101' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odrexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odvexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odeath intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oobs) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('0110110' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odrexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odvexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odeath intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oprococ) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('0110111' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odrexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odvexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odeath intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oprococ intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oobs) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('0111000' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odrexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odvexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1omsmt) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('0111001' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odrexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odvexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1omsmt intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oobs) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('0111010' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odrexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odvexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1omsmt intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oprococ) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('0111011' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odrexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odvexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1omsmt intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oprococ intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oobs) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('0111100' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odrexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odvexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1omsmt intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odeath) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('0111101' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odrexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odvexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1omsmt intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odeath intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oobs) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('0111110' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odrexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odvexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1omsmt intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odeath intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oprococ) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('0111111' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odrexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odvexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1omsmt intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odeath intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oprococ intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oobs) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('1000000' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oconoc) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('1000001' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oconoc intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oobs) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('1000010' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oconoc intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oprococ) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('1000011' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oconoc intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oprococ intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oobs) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('1000100' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oconoc intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odeath) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('1000101' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oconoc intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odeath intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oobs) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('1000110' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oconoc intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odeath intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oprococ) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('1000111' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oconoc intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odeath intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oprococ intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oobs) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('1001000' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oconoc intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1omsmt) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('1001001' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oconoc intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1omsmt intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oobs) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('1001010' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oconoc intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1omsmt intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oprococ) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('1001011' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oconoc intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1omsmt intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oprococ intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oobs) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('1001100' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oconoc intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1omsmt intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odeath) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('1001101' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oconoc intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1omsmt intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odeath intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oobs) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('1001110' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oconoc intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1omsmt intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odeath intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oprococ) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('1001111' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oconoc intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1omsmt intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odeath intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oprococ intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oobs) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('1010000' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oconoc intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odvexp) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('1010001' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oconoc intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odvexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oobs) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('1010010' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oconoc intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odvexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oprococ) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('1010011' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oconoc intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odvexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oprococ intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oobs) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('1010100' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oconoc intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odvexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odeath) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('1010101' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oconoc intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odvexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odeath intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oobs) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('1010110' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oconoc intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odvexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odeath intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oprococ) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('1010111' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oconoc intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odvexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odeath intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oprococ intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oobs) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('1011000' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oconoc intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odvexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1omsmt) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('1011001' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oconoc intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odvexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1omsmt intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oobs) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('1011010' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oconoc intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odvexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1omsmt intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oprococ) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('1011011' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oconoc intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odvexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1omsmt intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oprococ intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oobs) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('1011100' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oconoc intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odvexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1omsmt intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odeath) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('1011101' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oconoc intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odvexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1omsmt intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odeath intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oobs) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('1011110' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oconoc intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odvexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1omsmt intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odeath intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oprococ) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('1011111' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oconoc intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odvexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1omsmt intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odeath intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oprococ intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oobs) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('1100000' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oconoc intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odrexp) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('1100001' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oconoc intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odrexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oobs) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('1100010' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oconoc intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odrexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oprococ) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('1100011' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oconoc intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odrexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oprococ intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oobs) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('1100100' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oconoc intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odrexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odeath) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('1100101' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oconoc intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odrexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odeath intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oobs) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('1100110' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oconoc intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odrexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odeath intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oprococ) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('1100111' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oconoc intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odrexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odeath intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oprococ intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oobs) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('1101000' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oconoc intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odrexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1omsmt) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('1101001' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oconoc intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odrexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1omsmt intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oobs) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('1101010' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oconoc intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odrexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1omsmt intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oprococ) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('1101011' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oconoc intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odrexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1omsmt intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oprococ intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oobs) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('1101100' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oconoc intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odrexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1omsmt intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odeath) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('1101101' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oconoc intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odrexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1omsmt intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odeath intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oobs) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('1101110' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oconoc intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odrexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1omsmt intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odeath intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oprococ) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('1101111' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oconoc intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odrexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1omsmt intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odeath intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oprococ intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oobs) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('1110000' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oconoc intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odrexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odvexp) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('1110001' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oconoc intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odrexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odvexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oobs) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('1110010' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oconoc intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odrexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odvexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oprococ) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('1110011' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oconoc intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odrexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odvexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oprococ intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oobs) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('1110100' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oconoc intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odrexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odvexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odeath) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('1110101' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oconoc intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odrexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odvexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odeath intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oobs) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('1110110' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oconoc intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odrexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odvexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odeath intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oprococ) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('1110111' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oconoc intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odrexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odvexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odeath intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oprococ intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oobs) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('1111000' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oconoc intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odrexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odvexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1omsmt) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('1111001' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oconoc intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odrexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odvexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1omsmt intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oobs) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('1111010' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oconoc intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odrexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odvexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1omsmt intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oprococ) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('1111011' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oconoc intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odrexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odvexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1omsmt intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oprococ intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oobs) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('1111100' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oconoc intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odrexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odvexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1omsmt intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odeath) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('1111101' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oconoc intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odrexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odvexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1omsmt intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odeath intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oobs) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('1111110' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oconoc intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odrexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odvexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1omsmt intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odeath intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oprococ) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb UNION ALL
select 2004 as analysis_id,
 CAST('1111111' AS STRING) as stratum_1,
 cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as STRING) as stratum_2,
 CAST(NULL AS STRING) as stratum_3,
 CAST(NULL AS STRING) as stratum_4,
 CAST(NULL AS STRING) as stratum_5,
 personIntersection.count_value
 from
 (select count(*) as count_value from(select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oconoc intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odrexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odvexp intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1omsmt intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1odeath intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oprococ intersect select person_id from <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1oobs) as subquery) as personIntersection,
 (select count(distinct(person_id)) as totalPersons from <CDM_DATABASE_NAME>.person) as totalPersonsDb);
 CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_2004 
USING DELTA
AS
(SELECT
* 
FROM
rawData);

--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_2100
USING DELTA
AS
SELECT
2100 AS analysis_id,
 CAST(de.device_concept_id AS STRING) AS stratum_1,
 CAST(NULL AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 COUNT(DISTINCT de.person_id) AS count_value
FROM
<CDM_DATABASE_NAME>.device_exposure de
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 de.person_id = op.person_id
AND 
 de.device_exposure_start_date >= op.observation_period_start_date
AND 
 de.device_exposure_start_date <= op.observation_period_end_date 
GROUP BY 
 de.device_concept_id;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_2100
 ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_2101
USING DELTA
AS
SELECT
2101 AS analysis_id,
 CAST(de.device_concept_id AS STRING) AS stratum_1,
 CAST(NULL AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 COUNT(de.person_id) AS count_value
FROM
<CDM_DATABASE_NAME>.device_exposure de
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 de.person_id = op.person_id
AND 
 de.device_exposure_start_date >= op.observation_period_start_date
AND 
 de.device_exposure_start_date <= op.observation_period_end_date 
GROUP BY 
 de.device_concept_id;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_2101
 ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(stratum_1)
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_2102
USING DELTA
AS
WITH rawData  AS (
SELECT 
 de.device_concept_id AS stratum_1,
 YEAR(de.device_exposure_start_date) * 100 + MONTH(de.device_exposure_start_date) AS stratum_2,
 COUNT(DISTINCT de.person_id) AS count_value
FROM 
 <CDM_DATABASE_NAME>.device_exposure de
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 de.person_id = op.person_id
AND 
 de.device_exposure_start_date >= op.observation_period_start_date
AND 
 de.device_exposure_start_date <= op.observation_period_end_date 
GROUP BY 
 de.device_concept_id,
 YEAR(de.device_exposure_start_date) * 100 + MONTH(de.device_exposure_start_date)
)
 SELECT
2102 AS analysis_id,
 CAST(stratum_1 AS STRING) AS stratum_1,
 CAST(stratum_2 AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 count_value
FROM
rawData;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_2102
 ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(stratum_1)
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_2104
USING DELTA
AS
WITH rawData  AS (
SELECT 
 de.device_concept_id AS stratum_1,
 YEAR(de.device_exposure_start_date) AS stratum_2,
 p.gender_concept_id AS stratum_3,
 FLOOR((YEAR(de.device_exposure_start_date) - p.year_of_birth) / 10) AS stratum_4,
 COUNT(DISTINCT p.person_id) AS count_value
FROM 
 <CDM_DATABASE_NAME>.person p
JOIN 
 <CDM_DATABASE_NAME>.device_exposure de 
ON 
 p.person_id = de.person_id
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 de.person_id = op.person_id
AND 
 de.device_exposure_start_date >= op.observation_period_start_date
AND 
 de.device_exposure_start_date <= op.observation_period_end_date 
GROUP BY 
 de.device_concept_id,
 YEAR(de.device_exposure_start_date),
 p.gender_concept_id,
 FLOOR((YEAR(de.device_exposure_start_date) - p.year_of_birth) / 10)
)
 SELECT
2104 AS analysis_id,
 CAST(stratum_1 AS STRING) AS stratum_1,
 CAST(stratum_2 AS STRING) AS stratum_2,
 CAST(stratum_3 AS STRING) AS stratum_3,
 CAST(stratum_4 AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 count_value
FROM
rawData;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_2104
 ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_2105
USING DELTA
AS
SELECT
2105 AS analysis_id,
 CAST(de.device_concept_id AS STRING) AS stratum_1,
 CAST(de.device_type_concept_id AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 COUNT(de.person_id) AS count_value
FROM
<CDM_DATABASE_NAME>.device_exposure de
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 de.person_id = op.person_id
AND 
 de.device_exposure_start_date >= op.observation_period_start_date
AND 
 de.device_exposure_start_date <= op.observation_period_end_date 
GROUP BY 
 de.device_concept_id,
 de.device_type_concept_id;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_2105
 ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(subject_id) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1orawData_2106
USING DELTA
AS
SELECT
o.device_concept_id AS subject_id,
 p.gender_concept_id,
 o.device_exposure_start_year - p.year_of_birth AS count_value
FROM
<CDM_DATABASE_NAME>.person p
JOIN (
 SELECT 
 d.person_id,
 d.device_concept_id,
 MIN(YEAR(d.device_exposure_start_date)) AS device_exposure_start_year
 FROM 
 <CDM_DATABASE_NAME>.device_exposure d
 JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
 ON 
 d.person_id = op.person_id
 AND 
 d.device_exposure_start_date >= op.observation_period_start_date
 AND 
 d.device_exposure_start_date <= op.observation_period_end_date 
 GROUP BY 
 d.person_id,
 d.device_concept_id
 ) o
ON 
 p.person_id = o.person_id
;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1orawData_2106
 ZORDER BY subject_id;
--HINT DISTRIBUTE_ON_KEY(stratum1_id)
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_2106
USING DELTA
AS
WITH overallStats (stratum1_id, stratum2_id, avg_value, stdev_value, min_value, max_value, total)  AS (
 select subject_id as stratum1_id,
 gender_concept_id as stratum2_id,
 CAST(avg(1.0 * count_value) AS FLOAT) as avg_value,
 CAST(STDDEV(count_value) AS FLOAT) as stdev_value,
 min(count_value) as min_value,
 max(count_value) as max_value,
 COUNT(*) as total
 FROM <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1orawData_2106
 group by subject_id, gender_concept_id
),
statsView (stratum1_id, stratum2_id, count_value, total, rn) as
(
 select subject_id as stratum1_id, gender_concept_id as stratum2_id, count_value, COUNT(*) as total, row_number() over (partition by subject_id, gender_concept_id order by count_value) as rn
 FROM <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1orawData_2106
 group by subject_id, gender_concept_id, count_value
),
priorStats (stratum1_id, stratum2_id, count_value, total, accumulated) as
(
 select s.stratum1_id, s.stratum2_id, s.count_value, s.total, sum(p.total) as accumulated
 from statsView s
 join statsView p on s.stratum1_id = p.stratum1_id and s.stratum2_id = p.stratum2_id and p.rn <= s.rn
 group by s.stratum1_id, s.stratum2_id, s.count_value, s.total, s.rn
)
 SELECT
2106 as analysis_id,
 CAST(o.stratum1_id AS STRING) AS stratum1_id,
 CAST(o.stratum2_id AS STRING) AS stratum2_id,
 o.total as count_value,
 o.min_value,
 o.max_value,
 o.avg_value,
 o.stdev_value,
 MIN(case when p.accumulated >= .50 * o.total then count_value else o.max_value end) as median_value,
 MIN(case when p.accumulated >= .10 * o.total then count_value else o.max_value end) as p10_value,
 MIN(case when p.accumulated >= .25 * o.total then count_value else o.max_value end) as p25_value,
 MIN(case when p.accumulated >= .75 * o.total then count_value else o.max_value end) as p75_value,
 MIN(case when p.accumulated >= .90 * o.total then count_value else o.max_value end) as p90_value
FROM
priorStats p
join overallStats o on p.stratum1_id = o.stratum1_id and p.stratum2_id = o.stratum2_id 
GROUP BY o.stratum1_id, o.stratum2_id, o.total, o.min_value, o.max_value, o.avg_value, o.stdev_value
;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_2106
 ZORDER BY stratum1_id;
--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_2106
USING DELTA
AS
SELECT
analysis_id, stratum1_id as stratum_1, stratum2_id as stratum_2, 
cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
count_value, min_value, max_value, avg_value, stdev_value, median_value, p10_value, p25_value, p75_value, p90_value
FROM
<ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_2106
;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_2106
 ZORDER BY stratum_1;
truncate table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1orawData_2106;
drop table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1orawData_2106;
truncate table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_2106;
drop table <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1otempResults_2106;

CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_2110
USING DELTA
AS
SELECT
2110 AS analysis_id,
 CAST(NULL AS STRING) AS stratum_1,
 CAST(NULL AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 COUNT(*) AS count_value
FROM
<CDM_DATABASE_NAME>.device_exposure de
LEFT JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 de.person_id = op.person_id
AND 
 de.device_exposure_start_date >= op.observation_period_start_date
AND 
 de.device_exposure_start_date <= op.observation_period_end_date 
WHERE
 op.person_id IS NULL;

--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_2125
USING DELTA
AS
SELECT
2125 AS analysis_id,
 CAST(de.device_source_concept_id AS STRING) AS stratum_1,
 CAST(NULL AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 COUNT(*) AS count_value
FROM
<CDM_DATABASE_NAME>.device_exposure de
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 de.person_id = op.person_id
AND 
 de.device_exposure_start_date >= op.observation_period_start_date
AND 
 de.device_exposure_start_date <= op.observation_period_end_date 
GROUP BY 
 de.device_source_concept_id;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_2125
 ZORDER BY stratum_1;

CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_2130
USING DELTA
AS
SELECT
2130 AS analysis_id,
 CAST(NULL AS STRING) AS stratum_1,
 CAST(NULL AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 COUNT(*) AS count_value
FROM
<CDM_DATABASE_NAME>.device_exposure de
JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 de.person_id = op.person_id
AND 
 de.device_exposure_start_date >= op.observation_period_start_date
AND 
 de.device_exposure_start_date <= op.observation_period_end_date;

DROP VIEW IF EXISTS op_outside  ; 
CREATE TEMPORARY VIEW op_outside   AS (SELECT 
 COUNT(DISTINCT de.person_id) AS person_count
FROM 
 <CDM_DATABASE_NAME>.device_exposure de
LEFT JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 de.person_id = op.person_id
AND 
 de.device_exposure_start_date >= op.observation_period_start_date
AND 
 de.device_exposure_start_date <= op.observation_period_end_date
WHERE
 op.person_id IS NULL
);
DROP VIEW IF EXISTS de_total ; 
CREATE TEMPORARY VIEW de_total  AS (SELECT
 COUNT(DISTINCT person_id) person_count
FROM
 <CDM_DATABASE_NAME>.device_exposure
);
 CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_2131
USING DELTA
AS
(SELECT
2131 AS analysis_id,
 CASE WHEN det.person_count != 0 THEN
 CAST(CAST(1.0*op.person_count/det.person_count AS FLOAT) AS STRING) 
 ELSE
 CAST(NULL AS STRING) 
 END AS stratum_1, 
 CAST(op.person_count AS STRING) AS stratum_2,
 CAST(det.person_count AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 SIGN(op.person_count) AS count_value
FROM
op_outside op
CROSS JOIN 
 de_total det);

DROP VIEW IF EXISTS op_outside  ; 
CREATE TEMPORARY VIEW op_outside   AS (SELECT 
 COUNT(*) AS record_count
FROM 
 <CDM_DATABASE_NAME>.device_exposure de
LEFT JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
ON 
 de.person_id = op.person_id
AND 
 de.device_exposure_start_date >= op.observation_period_start_date
AND 
 de.device_exposure_start_date <= op.observation_period_end_date
WHERE
 op.person_id IS NULL
);
DROP VIEW IF EXISTS de_total ; 
CREATE TEMPORARY VIEW de_total  AS (SELECT
 COUNT(*) record_count
FROM
 <CDM_DATABASE_NAME>.device_exposure
);
 CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_2132
USING DELTA
AS
(SELECT
2132 AS analysis_id,
 CASE WHEN det.record_count != 0 THEN
 CAST(CAST(1.0*op.record_count/det.record_count AS FLOAT) AS STRING) 
 ELSE 
 CAST(NULL AS STRING)
 END AS stratum_1, 
 CAST(op.record_count AS STRING) AS stratum_2,
 CAST(det.record_count AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 SIGN(op.record_count) AS count_value
FROM
op_outside op
CROSS JOIN 
 de_total det);

--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_2191
USING DELTA
AS
SELECT
2191 AS analysis_id,
 CAST(d.device_concept_id AS STRING) AS stratum_1,
 CAST(d.device_count AS STRING) AS stratum_2,
 CAST(NULL AS STRING) AS stratum_3,
 CAST(NULL AS STRING) AS stratum_4,
 CAST(NULL AS STRING) AS stratum_5,
 SUM(COUNT(d.person_id)) OVER (PARTITION BY d.device_concept_id ORDER BY d.device_count DESC) AS count_value
FROM
(
 SELECT 
 d.device_concept_id,
 COUNT(d.device_exposure_id) AS device_count,
 d.person_id
 FROM 
 <CDM_DATABASE_NAME>.device_exposure d
 JOIN 
 <CDM_DATABASE_NAME>.observation_period op 
 ON 
 d.person_id = op.person_id
 AND 
 d.device_exposure_start_date >= op.observation_period_start_date
 AND 
 d.device_exposure_start_date <= op.observation_period_end_date 
 GROUP BY 
 d.person_id,
 d.device_concept_id
 ) d
GROUP BY 
 d.device_concept_id,
 d.device_count;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_2191
 ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_2200
USING DELTA
AS
SELECT
2200 as analysis_id, 
 CAST(m.note_type_CONCEPT_ID AS STRING) as stratum_1,
 cast(null as STRING) as stratum_2, cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
 COUNT(distinct m.PERSON_ID) as count_value
FROM
<CDM_DATABASE_NAME>.note m
group by m.note_type_CONCEPT_ID;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_2200
 ZORDER BY stratum_1;

--HINT DISTRIBUTE_ON_KEY(stratum_1) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_2201
USING DELTA
AS
SELECT
2201 as analysis_id, 
 CAST(m.note_type_CONCEPT_ID AS STRING) as stratum_1,
 cast(null as STRING) as stratum_2, cast(null as STRING) as stratum_3, cast(null as STRING) as stratum_4, cast(null as STRING) as stratum_5,
 COUNT(m.PERSON_ID) as count_value
FROM
<CDM_DATABASE_NAME>.note m
group by m.note_type_CONCEPT_ID;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_2201
 ZORDER BY stratum_1;

DROP TABLE IF EXISTS <ACHILLES_RESULTS_DATABASE_NAME>.achilles_results;
--HINT DISTRIBUTE_ON_KEY(analysis_id) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.achilles_results
USING DELTA
AS
SELECT
analysis_id, stratum_1, stratum_2, stratum_3, stratum_4, stratum_5, count_value
FROM
(
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_0 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_2 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_3 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_4 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_5 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_7 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_8 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_9 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_10 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_11 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_12 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_101 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_102 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_108 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_109 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_110 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_111 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_112 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_113 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_114 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_115 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_116 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_117 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_118 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_119 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_200 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_201 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_202 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_204 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_207 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_209 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_210 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_211 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_212 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_220 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_221 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_225 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_226 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_230 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_231 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_232 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_300 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_301 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_303 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_325 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_400 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_401 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_402 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_404 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_405 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_409 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_410 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_411 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_412 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_413 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_414 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_415 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_416 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_420 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_425 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_430 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_431 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_432 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_500 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_501 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_502 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_504 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_505 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_509 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_510 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_525 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_530 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_531 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_532 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_600 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_601 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_602 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_604 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_605 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_609 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_610 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_612 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_613 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_620 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_625 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_630 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_631 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_632 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_691 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_700 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_701 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_702 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_704 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_705 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_709 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_710 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_711 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_712 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_713 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_720 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_725 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_730 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_731 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_732 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_791 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_800 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_801 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_802 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_804 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_805 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_807 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_809 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_810 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_812 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_813 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_814 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_820 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_822 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_823 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_825 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_826 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_827 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_830 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_831 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_832 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_891 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_900 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_901 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_902 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_904 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_908 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_910 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_911 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_920 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_930 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_931 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_932 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1000 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1001 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1002 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1004 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1008 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1010 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1011 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1020 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1030 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1031 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1032 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1100 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1101 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1102 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1103 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1200 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1201 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1202 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1203 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1300 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1301 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1302 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1304 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1307 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1309 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1310 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1311 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1312 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1320 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1321 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1325 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1326 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1330 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1331 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1332 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1408 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1409 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1410 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1411 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1412 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1413 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1414 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1415 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1425 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1800 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1801 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1802 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1804 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1805 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1807 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1809 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1810 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1811 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1812 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1813 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1814 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1818 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1819 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1820 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1821 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1822 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1823 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1825 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1826 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1827 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1830 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1831 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1832 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1833 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1891 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_1900 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_2000 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_2001 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_2002 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_2003 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_2004 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_2100 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_2101 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_2102 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_2104 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_2105 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_2110 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_2125 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_2130 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_2131 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_2132 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_2191 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_2200 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_2201
) Q
 where count_value > 5;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.achilles_results
 ZORDER BY analysis_id;

DROP TABLE IF EXISTS <ACHILLES_RESULTS_DATABASE_NAME>.achilles_results_dist;
--HINT DISTRIBUTE_ON_KEY(analysis_id) 
CREATE TABLE <ACHILLES_RESULTS_DATABASE_NAME>.achilles_results_dist
USING DELTA
AS
SELECT
analysis_id, stratum_1, stratum_2, stratum_3, stratum_4, stratum_5, count_value, min_value, max_value, avg_value, stdev_value, median_value, p10_value, p25_value, p75_value, p90_value
FROM
(
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value, cast(min_value as float) as min_value, cast(max_value as float) as max_value, cast(avg_value as float) as avg_value, cast(stdev_value as float) as stdev_value, cast(median_value as float) as median_value, cast(p10_value as float) as p10_value, cast(p25_value as float) as p25_value, cast(p75_value as float) as p75_value, cast(p90_value as float) as p90_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_0 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value, cast(min_value as float) as min_value, cast(max_value as float) as max_value, cast(avg_value as float) as avg_value, cast(stdev_value as float) as stdev_value, cast(median_value as float) as median_value, cast(p10_value as float) as p10_value, cast(p25_value as float) as p25_value, cast(p75_value as float) as p75_value, cast(p90_value as float) as p90_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_103 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value, cast(min_value as float) as min_value, cast(max_value as float) as max_value, cast(avg_value as float) as avg_value, cast(stdev_value as float) as stdev_value, cast(median_value as float) as median_value, cast(p10_value as float) as p10_value, cast(p25_value as float) as p25_value, cast(p75_value as float) as p75_value, cast(p90_value as float) as p90_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_104 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value, cast(min_value as float) as min_value, cast(max_value as float) as max_value, cast(avg_value as float) as avg_value, cast(stdev_value as float) as stdev_value, cast(median_value as float) as median_value, cast(p10_value as float) as p10_value, cast(p25_value as float) as p25_value, cast(p75_value as float) as p75_value, cast(p90_value as float) as p90_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_105 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value, cast(min_value as float) as min_value, cast(max_value as float) as max_value, cast(avg_value as float) as avg_value, cast(stdev_value as float) as stdev_value, cast(median_value as float) as median_value, cast(p10_value as float) as p10_value, cast(p25_value as float) as p25_value, cast(p75_value as float) as p75_value, cast(p90_value as float) as p90_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_106 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value, cast(min_value as float) as min_value, cast(max_value as float) as max_value, cast(avg_value as float) as avg_value, cast(stdev_value as float) as stdev_value, cast(median_value as float) as median_value, cast(p10_value as float) as p10_value, cast(p25_value as float) as p25_value, cast(p75_value as float) as p75_value, cast(p90_value as float) as p90_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_107 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value, cast(min_value as float) as min_value, cast(max_value as float) as max_value, cast(avg_value as float) as avg_value, cast(stdev_value as float) as stdev_value, cast(median_value as float) as median_value, cast(p10_value as float) as p10_value, cast(p25_value as float) as p25_value, cast(p75_value as float) as p75_value, cast(p90_value as float) as p90_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_203 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value, cast(min_value as float) as min_value, cast(max_value as float) as max_value, cast(avg_value as float) as avg_value, cast(stdev_value as float) as stdev_value, cast(median_value as float) as median_value, cast(p10_value as float) as p10_value, cast(p25_value as float) as p25_value, cast(p75_value as float) as p75_value, cast(p90_value as float) as p90_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_206 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value, cast(min_value as float) as min_value, cast(max_value as float) as max_value, cast(avg_value as float) as avg_value, cast(stdev_value as float) as stdev_value, cast(median_value as float) as median_value, cast(p10_value as float) as p10_value, cast(p25_value as float) as p25_value, cast(p75_value as float) as p75_value, cast(p90_value as float) as p90_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_213 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value, cast(min_value as float) as min_value, cast(max_value as float) as max_value, cast(avg_value as float) as avg_value, cast(stdev_value as float) as stdev_value, cast(median_value as float) as median_value, cast(p10_value as float) as p10_value, cast(p25_value as float) as p25_value, cast(p75_value as float) as p75_value, cast(p90_value as float) as p90_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_403 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value, cast(min_value as float) as min_value, cast(max_value as float) as max_value, cast(avg_value as float) as avg_value, cast(stdev_value as float) as stdev_value, cast(median_value as float) as median_value, cast(p10_value as float) as p10_value, cast(p25_value as float) as p25_value, cast(p75_value as float) as p75_value, cast(p90_value as float) as p90_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_406 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value, cast(min_value as float) as min_value, cast(max_value as float) as max_value, cast(avg_value as float) as avg_value, cast(stdev_value as float) as stdev_value, cast(median_value as float) as median_value, cast(p10_value as float) as p10_value, cast(p25_value as float) as p25_value, cast(p75_value as float) as p75_value, cast(p90_value as float) as p90_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_506 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value, cast(min_value as float) as min_value, cast(max_value as float) as max_value, cast(avg_value as float) as avg_value, cast(stdev_value as float) as stdev_value, cast(median_value as float) as median_value, cast(p10_value as float) as p10_value, cast(p25_value as float) as p25_value, cast(p75_value as float) as p75_value, cast(p90_value as float) as p90_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_511 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value, cast(min_value as float) as min_value, cast(max_value as float) as max_value, cast(avg_value as float) as avg_value, cast(stdev_value as float) as stdev_value, cast(median_value as float) as median_value, cast(p10_value as float) as p10_value, cast(p25_value as float) as p25_value, cast(p75_value as float) as p75_value, cast(p90_value as float) as p90_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_512 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value, cast(min_value as float) as min_value, cast(max_value as float) as max_value, cast(avg_value as float) as avg_value, cast(stdev_value as float) as stdev_value, cast(median_value as float) as median_value, cast(p10_value as float) as p10_value, cast(p25_value as float) as p25_value, cast(p75_value as float) as p75_value, cast(p90_value as float) as p90_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_513 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value, cast(min_value as float) as min_value, cast(max_value as float) as max_value, cast(avg_value as float) as avg_value, cast(stdev_value as float) as stdev_value, cast(median_value as float) as median_value, cast(p10_value as float) as p10_value, cast(p25_value as float) as p25_value, cast(p75_value as float) as p75_value, cast(p90_value as float) as p90_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_514 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value, cast(min_value as float) as min_value, cast(max_value as float) as max_value, cast(avg_value as float) as avg_value, cast(stdev_value as float) as stdev_value, cast(median_value as float) as median_value, cast(p10_value as float) as p10_value, cast(p25_value as float) as p25_value, cast(p75_value as float) as p75_value, cast(p90_value as float) as p90_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_515 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value, cast(min_value as float) as min_value, cast(max_value as float) as max_value, cast(avg_value as float) as avg_value, cast(stdev_value as float) as stdev_value, cast(median_value as float) as median_value, cast(p10_value as float) as p10_value, cast(p25_value as float) as p25_value, cast(p75_value as float) as p75_value, cast(p90_value as float) as p90_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_603 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value, cast(min_value as float) as min_value, cast(max_value as float) as max_value, cast(avg_value as float) as avg_value, cast(stdev_value as float) as stdev_value, cast(median_value as float) as median_value, cast(p10_value as float) as p10_value, cast(p25_value as float) as p25_value, cast(p75_value as float) as p75_value, cast(p90_value as float) as p90_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_606 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value, cast(min_value as float) as min_value, cast(max_value as float) as max_value, cast(avg_value as float) as avg_value, cast(stdev_value as float) as stdev_value, cast(median_value as float) as median_value, cast(p10_value as float) as p10_value, cast(p25_value as float) as p25_value, cast(p75_value as float) as p75_value, cast(p90_value as float) as p90_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_703 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value, cast(min_value as float) as min_value, cast(max_value as float) as max_value, cast(avg_value as float) as avg_value, cast(stdev_value as float) as stdev_value, cast(median_value as float) as median_value, cast(p10_value as float) as p10_value, cast(p25_value as float) as p25_value, cast(p75_value as float) as p75_value, cast(p90_value as float) as p90_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_706 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value, cast(min_value as float) as min_value, cast(max_value as float) as max_value, cast(avg_value as float) as avg_value, cast(stdev_value as float) as stdev_value, cast(median_value as float) as median_value, cast(p10_value as float) as p10_value, cast(p25_value as float) as p25_value, cast(p75_value as float) as p75_value, cast(p90_value as float) as p90_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_715 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value, cast(min_value as float) as min_value, cast(max_value as float) as max_value, cast(avg_value as float) as avg_value, cast(stdev_value as float) as stdev_value, cast(median_value as float) as median_value, cast(p10_value as float) as p10_value, cast(p25_value as float) as p25_value, cast(p75_value as float) as p75_value, cast(p90_value as float) as p90_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_716 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value, cast(min_value as float) as min_value, cast(max_value as float) as max_value, cast(avg_value as float) as avg_value, cast(stdev_value as float) as stdev_value, cast(median_value as float) as median_value, cast(p10_value as float) as p10_value, cast(p25_value as float) as p25_value, cast(p75_value as float) as p75_value, cast(p90_value as float) as p90_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_717 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value, cast(min_value as float) as min_value, cast(max_value as float) as max_value, cast(avg_value as float) as avg_value, cast(stdev_value as float) as stdev_value, cast(median_value as float) as median_value, cast(p10_value as float) as p10_value, cast(p25_value as float) as p25_value, cast(p75_value as float) as p75_value, cast(p90_value as float) as p90_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_803 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value, cast(min_value as float) as min_value, cast(max_value as float) as max_value, cast(avg_value as float) as avg_value, cast(stdev_value as float) as stdev_value, cast(median_value as float) as median_value, cast(p10_value as float) as p10_value, cast(p25_value as float) as p25_value, cast(p75_value as float) as p75_value, cast(p90_value as float) as p90_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_806 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value, cast(min_value as float) as min_value, cast(max_value as float) as max_value, cast(avg_value as float) as avg_value, cast(stdev_value as float) as stdev_value, cast(median_value as float) as median_value, cast(p10_value as float) as p10_value, cast(p25_value as float) as p25_value, cast(p75_value as float) as p75_value, cast(p90_value as float) as p90_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_815 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value, cast(min_value as float) as min_value, cast(max_value as float) as max_value, cast(avg_value as float) as avg_value, cast(stdev_value as float) as stdev_value, cast(median_value as float) as median_value, cast(p10_value as float) as p10_value, cast(p25_value as float) as p25_value, cast(p75_value as float) as p75_value, cast(p90_value as float) as p90_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_903 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value, cast(min_value as float) as min_value, cast(max_value as float) as max_value, cast(avg_value as float) as avg_value, cast(stdev_value as float) as stdev_value, cast(median_value as float) as median_value, cast(p10_value as float) as p10_value, cast(p25_value as float) as p25_value, cast(p75_value as float) as p75_value, cast(p90_value as float) as p90_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_906 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value, cast(min_value as float) as min_value, cast(max_value as float) as max_value, cast(avg_value as float) as avg_value, cast(stdev_value as float) as stdev_value, cast(median_value as float) as median_value, cast(p10_value as float) as p10_value, cast(p25_value as float) as p25_value, cast(p75_value as float) as p75_value, cast(p90_value as float) as p90_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_907 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value, cast(min_value as float) as min_value, cast(max_value as float) as max_value, cast(avg_value as float) as avg_value, cast(stdev_value as float) as stdev_value, cast(median_value as float) as median_value, cast(p10_value as float) as p10_value, cast(p25_value as float) as p25_value, cast(p75_value as float) as p75_value, cast(p90_value as float) as p90_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_1003 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value, cast(min_value as float) as min_value, cast(max_value as float) as max_value, cast(avg_value as float) as avg_value, cast(stdev_value as float) as stdev_value, cast(median_value as float) as median_value, cast(p10_value as float) as p10_value, cast(p25_value as float) as p25_value, cast(p75_value as float) as p75_value, cast(p90_value as float) as p90_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_1006 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value, cast(min_value as float) as min_value, cast(max_value as float) as max_value, cast(avg_value as float) as avg_value, cast(stdev_value as float) as stdev_value, cast(median_value as float) as median_value, cast(p10_value as float) as p10_value, cast(p25_value as float) as p25_value, cast(p75_value as float) as p75_value, cast(p90_value as float) as p90_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_1007 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value, cast(min_value as float) as min_value, cast(max_value as float) as max_value, cast(avg_value as float) as avg_value, cast(stdev_value as float) as stdev_value, cast(median_value as float) as median_value, cast(p10_value as float) as p10_value, cast(p25_value as float) as p25_value, cast(p75_value as float) as p75_value, cast(p90_value as float) as p90_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_1303 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value, cast(min_value as float) as min_value, cast(max_value as float) as max_value, cast(avg_value as float) as avg_value, cast(stdev_value as float) as stdev_value, cast(median_value as float) as median_value, cast(p10_value as float) as p10_value, cast(p25_value as float) as p25_value, cast(p75_value as float) as p75_value, cast(p90_value as float) as p90_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_1306 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value, cast(min_value as float) as min_value, cast(max_value as float) as max_value, cast(avg_value as float) as avg_value, cast(stdev_value as float) as stdev_value, cast(median_value as float) as median_value, cast(p10_value as float) as p10_value, cast(p25_value as float) as p25_value, cast(p75_value as float) as p75_value, cast(p90_value as float) as p90_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_1313 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value, cast(min_value as float) as min_value, cast(max_value as float) as max_value, cast(avg_value as float) as avg_value, cast(stdev_value as float) as stdev_value, cast(median_value as float) as median_value, cast(p10_value as float) as p10_value, cast(p25_value as float) as p25_value, cast(p75_value as float) as p75_value, cast(p90_value as float) as p90_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_1406 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value, cast(min_value as float) as min_value, cast(max_value as float) as max_value, cast(avg_value as float) as avg_value, cast(stdev_value as float) as stdev_value, cast(median_value as float) as median_value, cast(p10_value as float) as p10_value, cast(p25_value as float) as p25_value, cast(p75_value as float) as p75_value, cast(p90_value as float) as p90_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_1407 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value, cast(min_value as float) as min_value, cast(max_value as float) as max_value, cast(avg_value as float) as avg_value, cast(stdev_value as float) as stdev_value, cast(median_value as float) as median_value, cast(p10_value as float) as p10_value, cast(p25_value as float) as p25_value, cast(p75_value as float) as p75_value, cast(p90_value as float) as p90_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_1803 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value, cast(min_value as float) as min_value, cast(max_value as float) as max_value, cast(avg_value as float) as avg_value, cast(stdev_value as float) as stdev_value, cast(median_value as float) as median_value, cast(p10_value as float) as p10_value, cast(p25_value as float) as p25_value, cast(p75_value as float) as p75_value, cast(p90_value as float) as p90_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_1806 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value, cast(min_value as float) as min_value, cast(max_value as float) as max_value, cast(avg_value as float) as avg_value, cast(stdev_value as float) as stdev_value, cast(median_value as float) as median_value, cast(p10_value as float) as p10_value, cast(p25_value as float) as p25_value, cast(p75_value as float) as p75_value, cast(p90_value as float) as p90_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_1815 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value, cast(min_value as float) as min_value, cast(max_value as float) as max_value, cast(avg_value as float) as avg_value, cast(stdev_value as float) as stdev_value, cast(median_value as float) as median_value, cast(p10_value as float) as p10_value, cast(p25_value as float) as p25_value, cast(p75_value as float) as p75_value, cast(p90_value as float) as p90_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_1816 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value, cast(min_value as float) as min_value, cast(max_value as float) as max_value, cast(avg_value as float) as avg_value, cast(stdev_value as float) as stdev_value, cast(median_value as float) as median_value, cast(p10_value as float) as p10_value, cast(p25_value as float) as p25_value, cast(p75_value as float) as p75_value, cast(p90_value as float) as p90_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_1817 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as STRING) as stratum_1, cast(stratum_2 as STRING) as stratum_2, cast(stratum_3 as STRING) as stratum_3, cast(stratum_4 as STRING) as stratum_4, cast(stratum_5 as STRING) as stratum_5, cast(count_value as bigint) as count_value, cast(min_value as float) as min_value, cast(max_value as float) as max_value, cast(avg_value as float) as avg_value, cast(stdev_value as float) as stdev_value, cast(median_value as float) as median_value, cast(p10_value as float) as p10_value, cast(p25_value as float) as p25_value, cast(p75_value as float) as p75_value, cast(p90_value as float) as p90_value from
 <ACHILLES_RESULTS_DATABASE_NAME>.knevvj1os_tmpach_dist_2106
) Q
 where count_value > 5;
OPTIMIZE <ACHILLES_RESULTS_DATABASE_NAME>.achilles_results_dist
 ZORDER BY analysis_id;

-- drop index <ACHILLES_RESULTS_DATABASE_NAME>.idx_ar_aid;

-- drop index <ACHILLES_RESULTS_DATABASE_NAME>.idx_ar_s1;

-- drop index <ACHILLES_RESULTS_DATABASE_NAME>.idx_ar_s2;

-- drop index <ACHILLES_RESULTS_DATABASE_NAME>.idx_ar_aid_s1;

-- drop index <ACHILLES_RESULTS_DATABASE_NAME>.idx_ar_aid_s1234;

-- drop index <ACHILLES_RESULTS_DATABASE_NAME>.idx_ard_aid;

-- drop index <ACHILLES_RESULTS_DATABASE_NAME>.idx_ard_s1;

-- drop index <ACHILLES_RESULTS_DATABASE_NAME>.idx_ard_s2;

-- spark does not support indexes

-- spark does not support indexes

-- spark does not support indexes

-- spark does not support indexes

-- spark does not support indexes

-- spark does not support indexes

-- spark does not support indexes

-- spark does not support indexes


