/* * * *
 * 
 * This script was created by running Achilles with sqlOnly = TRUE.  
 * The "if exists" clauses were added to the drop indexes statements at the very bottom of the script
 * to get rid of errors these statements were giving.  
 * 
 * This script was generated 2024-05-08 using the scripts from the fhir-to-omop documentation 
 * for Achilles.  
 * 
 * * * */

-- 0	cdm name, version of Achilles and date when pre-computations were executed

--HINT DISTRIBUTE_ON_KEY(stratum_1)
select 0 as analysis_id,  CAST('' AS VARCHAR(255)) as stratum_1, CAST('1.7.2' AS VARCHAR(255)) as stratum_2, 
CAST(GETDATE() AS VARCHAR(255)) as stratum_3,
cast(null as varchar(255)) as stratum_4, cast(null as varchar(255)) as stratum_5,
COUNT_BIG(distinct person_id) as count_value
into #s_tmpach_0
from @FullySpecifiedCdmSchema.person;

--HINT DISTRIBUTE_ON_KEY(stratum_1)
select 0 as analysis_id, CAST('' AS VARCHAR(255)) as stratum_1, 
cast(null as varchar(255)) as stratum_2, cast(null as varchar(255)) as stratum_3, cast(null as varchar(255)) as stratum_4, cast(null as varchar(255)) as stratum_5,
COUNT_BIG(distinct person_id) as count_value, 
  cast(null as float) as min_value,
	cast(null as float) as max_value,
	cast(null as float) as avg_value,
	cast(null as float) as stdev_value,
	cast(null as float) as median_value,
	cast(null as float) as p10_value,
	cast(null as float) as p25_value,
	cast(null as float) as p75_value,
	cast(null as float) as p90_value
into #s_tmpach_dist_0
from @FullySpecifiedCdmSchema.person;


-- 1	Number of persons

select 1 as analysis_id,  
cast(null as varchar(255)) as stratum_1, cast(null as varchar(255)) as stratum_2, cast(null as varchar(255)) as stratum_3, cast(null as varchar(255)) as stratum_4, cast(null as varchar(255)) as stratum_5,
COUNT_BIG(distinct person_id) as count_value
into #s_tmpach_1
from @FullySpecifiedCdmSchema.person;


-- 2	Number of persons by gender

--HINT DISTRIBUTE_ON_KEY(stratum_1)
select 2 as analysis_id, 
CAST(gender_concept_id AS VARCHAR(255)) as stratum_1, 
cast(null as varchar(255)) as stratum_2, cast(null as varchar(255)) as stratum_3, cast(null as varchar(255)) as stratum_4, cast(null as varchar(255)) as stratum_5,
COUNT_BIG(distinct person_id) as count_value
into #s_tmpach_2
from @FullySpecifiedCdmSchema.person
group by GENDER_CONCEPT_ID;


-- 3	Number of persons by year of birth

--HINT DISTRIBUTE_ON_KEY(stratum_1)
select 3 as analysis_id,  CAST(year_of_birth AS VARCHAR(255)) as stratum_1, 
cast(null as varchar(255)) as stratum_2, cast(null as varchar(255)) as stratum_3, cast(null as varchar(255)) as stratum_4, cast(null as varchar(255)) as stratum_5,
COUNT_BIG(distinct person_id) as count_value
into #s_tmpach_3
from @FullySpecifiedCdmSchema.person
group by YEAR_OF_BIRTH;


-- 4	Number of persons by race

--HINT DISTRIBUTE_ON_KEY(stratum_1)
select 4 as analysis_id,  CAST(RACE_CONCEPT_ID AS VARCHAR(255)) as stratum_1, 
cast(null as varchar(255)) as stratum_2, cast(null as varchar(255)) as stratum_3, cast(null as varchar(255)) as stratum_4, cast(null as varchar(255)) as stratum_5,
COUNT_BIG(distinct person_id) as count_value
into #s_tmpach_4
from @FullySpecifiedCdmSchema.person
group by RACE_CONCEPT_ID;


-- 5	Number of persons by ethnicity

--HINT DISTRIBUTE_ON_KEY(stratum_1)
select 5 as analysis_id,  CAST(ETHNICITY_CONCEPT_ID AS VARCHAR(255)) as stratum_1, 
cast(null as varchar(255)) as stratum_2, cast(null as varchar(255)) as stratum_3, cast(null as varchar(255)) as stratum_4, cast(null as varchar(255)) as stratum_5,
COUNT_BIG(distinct person_id) as count_value
into #s_tmpach_5
from @FullySpecifiedCdmSchema.person
group by ETHNICITY_CONCEPT_ID;


-- 10	Number of all persons by year of birth and by gender

--HINT DISTRIBUTE_ON_KEY(stratum_1)
select 10 as analysis_id,  CAST(year_of_birth AS VARCHAR(255)) as stratum_1,
  CAST(gender_concept_id AS VARCHAR(255)) as stratum_2,
  cast(null as varchar(255)) as stratum_3, cast(null as varchar(255)) as stratum_4, cast(null as varchar(255)) as stratum_5,
  COUNT_BIG(distinct person_id) as count_value
into #s_tmpach_10
from @FullySpecifiedCdmSchema.person
group by YEAR_OF_BIRTH, gender_concept_id;


-- 11	Number of non-deceased persons by year of birth and by gender

--HINT DISTRIBUTE_ON_KEY(stratum_1)
select 11 as analysis_id,  CAST(P.year_of_birth AS VARCHAR(255)) as stratum_1,
  CAST(P.gender_concept_id AS VARCHAR(255)) as stratum_2,
  cast(null as varchar(255)) as stratum_3, cast(null as varchar(255)) as stratum_4, cast(null as varchar(255)) as stratum_5,
  COUNT_BIG(distinct P.person_id) as count_value
into #s_tmpach_11
from @FullySpecifiedCdmSchema.person P
where not exists
(
  select 1
  from @FullySpecifiedCdmSchema.death D
  where P.person_id = D.person_id
)
group by P.YEAR_OF_BIRTH, P.gender_concept_id;


-- 12	Number of persons by race and ethnicity

--HINT DISTRIBUTE_ON_KEY(stratum_1)
select 12 as analysis_id, CAST(RACE_CONCEPT_ID AS VARCHAR(255)) as stratum_1, CAST(ETHNICITY_CONCEPT_ID AS VARCHAR(255)) as stratum_2, 
cast(null as varchar(255)) as stratum_3, cast(null as varchar(255)) as stratum_4, cast(null as varchar(255)) as stratum_5,
COUNT_BIG(distinct person_id) as count_value
into #s_tmpach_12
from @FullySpecifiedCdmSchema.person
group by RACE_CONCEPT_ID,ETHNICITY_CONCEPT_ID;


-- 101	Number of persons by age, with age at first observation period

--HINT DISTRIBUTE_ON_KEY(stratum_1)
WITH rawData AS (
  select
    year(op1.index_date) - p1.YEAR_OF_BIRTH as stratum_1,
    COUNT_BIG(p1.person_id) as count_value
  from @FullySpecifiedCdmSchema.person p1
    inner join (select person_id, MIN(observation_period_start_date) as index_date from @FullySpecifiedCdmSchema.observation_period group by PERSON_ID) op1
    on p1.PERSON_ID = op1.PERSON_ID
  group by year(op1.index_date) - p1.YEAR_OF_BIRTH
)
SELECT
  101 as analysis_id,
  CAST(stratum_1 AS VARCHAR(255)) as stratum_1,
  cast(null as varchar(255)) as stratum_2,
  cast(null as varchar(255)) as stratum_3,
  cast(null as varchar(255)) as stratum_4,
  cast(null as varchar(255)) as stratum_5,
  count_value
INTO #s_tmpach_101
FROM rawData;


-- 102	Number of persons by gender by age, with age at first observation period

--HINT DISTRIBUTE_ON_KEY(stratum_1)
WITH rawData AS (
  select
    p1.gender_concept_id as stratum_1,
    year(op1.index_date) - p1.YEAR_OF_BIRTH as stratum_2,
    COUNT_BIG(p1.person_id) as count_value
  from @FullySpecifiedCdmSchema.person p1
    inner join (select person_id, MIN(observation_period_start_date) as index_date from @FullySpecifiedCdmSchema.observation_period group by PERSON_ID) op1
    on p1.PERSON_ID = op1.PERSON_ID
  group by p1.gender_concept_id, year(op1.index_date) - p1.YEAR_OF_BIRTH)
SELECT
  102 as analysis_id,
  CAST(stratum_1 AS VARCHAR(255)) as stratum_1,
  cast(stratum_2 as varchar(255)) as stratum_2,
  cast(null as varchar(255)) as stratum_3,
  cast(null as varchar(255)) as stratum_4,
  cast(null as varchar(255)) as stratum_5,
  count_value
into #s_tmpach_102
FROM rawData;


-- 103	Distribution of age at first observation period

--HINT DISTRIBUTE_ON_KEY(count_value)
with rawData (person_id, age_value) as
(
select p.person_id, 
  MIN(YEAR(observation_period_start_date)) - P.YEAR_OF_BIRTH as age_value
  from @FullySpecifiedCdmSchema.person p
  JOIN @FullySpecifiedCdmSchema.observation_period op on p.person_id = op.person_id
  group by p.person_id, p.year_of_birth
),
overallStats (avg_value, stdev_value, min_value, max_value, total) as
(
  select CAST(avg(1.0 * age_value) AS FLOAT) as avg_value,
  CAST(stdev(age_value) AS FLOAT) as stdev_value,
  min(age_value) as min_value,
  max(age_value) as max_value,
  count_big(*) as total
  FROM rawData
),
ageStats (age_value, total, rn) as
(
  select age_value, count_big(*) as total, row_number() over (order by age_value) as rn
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
select analysis_id, 
cast(null as varchar(255)) as stratum_1, cast(null as varchar(255)) as stratum_2, cast(null as varchar(255)) as stratum_3, cast(null as varchar(255)) as stratum_4, cast(null as varchar(255)) as stratum_5, 
count_value, min_value, max_value, avg_value, stdev_value, median_value, p10_value, p25_value, p75_value, p90_value
into #s_tmpach_dist_103
from tempResults
;


-- 104	Distribution of age at first observation period by gender

--HINT DISTRIBUTE_ON_KEY(stratum_1) 
with rawData (gender_concept_id, age_value) as
(
  select p.gender_concept_id, MIN(YEAR(observation_period_start_date)) - P.YEAR_OF_BIRTH as age_value
	from @FullySpecifiedCdmSchema.person p
	JOIN @FullySpecifiedCdmSchema.observation_period op on p.person_id = op.person_id
	group by p.person_id,p.gender_concept_id, p.year_of_birth
),
overallStats (gender_concept_id, avg_value, stdev_value, min_value, max_value, total) as
(
  select gender_concept_id,
  CAST(avg(1.0 * age_value) AS FLOAT) as avg_value,
  CAST(stdev(age_value) AS FLOAT) as stdev_value,
  min(age_value) as min_value,
  max(age_value) as max_value,
  count_big(*) as total
  FROM rawData
  group by gender_concept_id
),
ageStats (gender_concept_id, age_value, total, rn) as
(
  select gender_concept_id, age_value, count_big(*) as total, row_number() over (order by age_value) as rn
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
select 104 as analysis_id,
  CAST(o.gender_concept_id AS VARCHAR(255)) as stratum_1,
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
INTO #tempResults_104
from ageStatsPrior p
join overallStats o on p.gender_concept_id = o.gender_concept_id
GROUP BY o.gender_concept_id, o.total, o.min_value, o.max_value, o.avg_value, o.stdev_value
;

--HINT DISTRIBUTE_ON_KEY(stratum_1) 
select analysis_id, stratum_1, 
cast(null as varchar(255)) as stratum_2, cast(null as varchar(255)) as stratum_3, cast(null as varchar(255)) as stratum_4, cast(null as varchar(255)) as stratum_5,
count_value, min_value, max_value, avg_value, stdev_value, median_value, p10_value, p25_value, p75_value, p90_value
into #s_tmpach_dist_104
from #tempResults_104
;

truncate table #tempResults_104;
drop table #tempResults_104;


-- 105	Length of observation (days) of first observation period

--HINT DISTRIBUTE_ON_KEY(count_value)
select count_value, rn 
into #tempObs_105
FROM
(
  select DATEDIFF(dd,op.observation_period_start_date, op.observation_period_end_date) as count_value,
	  ROW_NUMBER() over (PARTITION by op.person_id order by op.observation_period_start_date asc) as rn
  from @FullySpecifiedCdmSchema.observation_period op
) A
where rn = 1;
	
select count_value, count_big(*) as total, row_number() over (order by count_value) as rn
into #statsView_105
FROM #tempObs_105
group by count_value;

--HINT DISTRIBUTE_ON_KEY(count_value)
with overallStats (avg_value, stdev_value, min_value, max_value, total) as
(
  select CAST(avg(1.0 * count_value) AS FLOAT) as avg_value,
  CAST(stdev(count_value) AS FLOAT) as stdev_value,
  min(count_value) as min_value,
  max(count_value) as max_value,
  count_big(*) as total
  from #tempObs_105
),
priorStats (count_value, total, accumulated) as
(
  select s.count_value, s.total, sum(p.total) as accumulated
  from #statsView_105 s
  join #statsView_105 p on p.rn <= s.rn
  group by s.count_value, s.total, s.rn
)
select 105 as analysis_id,
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
into #tempResults_105
from priorStats p
CROSS JOIN overallStats o
GROUP BY o.total, o.min_value, o.max_value, o.avg_value, o.stdev_value
;

--HINT DISTRIBUTE_ON_KEY(count_value)
select analysis_id,
cast(null as varchar(255)) as stratum_1, cast(null as varchar(255)) as stratum_2, cast(null as varchar(255)) as stratum_3, cast(null as varchar(255)) as stratum_4, cast(null as varchar(255)) as stratum_5, count_value,
min_value, max_value, avg_value, stdev_value, median_value, p10_value, p25_value, p75_value, p90_value
into #s_tmpach_dist_105
from #tempResults_105
;

truncate table #tempObs_105;
drop table #tempObs_105;

truncate table #statsView_105;
drop table #statsView_105;

truncate table #tempResults_105;
drop table #tempResults_105;


-- 106	Length of observation (days) of first observation period by gender

--HINT DISTRIBUTE_ON_KEY(gender_concept_id)
select p.gender_concept_id, op.count_value
into #rawData_106
FROM
(
  select person_id, DATEDIFF(dd,op.observation_period_start_date, op.observation_period_end_date) as count_value,
    ROW_NUMBER() over (PARTITION by op.person_id order by op.observation_period_start_date asc) as rn
  from @FullySpecifiedCdmSchema.observation_period op
) op
JOIN @FullySpecifiedCdmSchema.person p on op.person_id = p.person_id
where op.rn = 1
;

--HINT DISTRIBUTE_ON_KEY(gender_concept_id)
with overallStats (gender_concept_id, avg_value, stdev_value, min_value, max_value, total) as
(
  select gender_concept_id,
    CAST(avg(1.0 * count_value) AS FLOAT) as avg_value,
    CAST(stdev(count_value) AS FLOAT) as stdev_value,
    min(count_value) as min_value,
    max(count_value) as max_value,
    count_big(*) as total
  FROM #rawData_106
  group by gender_concept_id
),
statsView (gender_concept_id, count_value, total, rn) as
(
  select gender_concept_id, count_value, count_big(*) as total, row_number() over (order by count_value) as rn
  FROM #rawData_106
  group by gender_concept_id, count_value
),
priorStats (gender_concept_id,count_value, total, accumulated) as
(
  select s.gender_concept_id, s.count_value, s.total, sum(p.total) as accumulated
  from statsView s
  join statsView p on s.gender_concept_id = p.gender_concept_id and p.rn <= s.rn
  group by s.gender_concept_id, s.count_value, s.total, s.rn
)
select 106 as analysis_id,
  CAST(o.gender_concept_id AS VARCHAR(255)) as gender_concept_id,
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
INTO #tempResults_106
from priorStats p
join overallStats o on p.gender_concept_id = o.gender_concept_id
GROUP BY o.gender_concept_id, o.total, o.min_value, o.max_value, o.avg_value, o.stdev_value
;

--HINT DISTRIBUTE_ON_KEY(stratum_1)
select analysis_id, gender_concept_id as stratum_1, 
cast(null as varchar(255)) as stratum_2, cast(null as varchar(255)) as stratum_3, cast(null as varchar(255)) as stratum_4, cast(null as varchar(255)) as stratum_5,
count_value, min_value, max_value, avg_value, stdev_value, median_value, p10_value, p25_value, p75_value, p90_value
into #s_tmpach_dist_106
FROM #tempResults_106
;

truncate table #rawData_106;
drop table #rawData_106;

truncate table #tempResults_106;
drop table #tempResults_106;


-- 107	Length of observation (days) of first observation period by age decile

--HINT DISTRIBUTE_ON_KEY(age_decile)
with rawData (age_decile, count_value) as
(
  select floor((year(op.OBSERVATION_PERIOD_START_DATE) - p.YEAR_OF_BIRTH)/10) as age_decile,
    DATEDIFF(dd,op.observation_period_start_date, op.observation_period_end_date) as count_value
  FROM
  (
    select person_id, 
  		op.observation_period_start_date,
  		op.observation_period_end_date,
      ROW_NUMBER() over (PARTITION by op.person_id order by op.observation_period_start_date asc) as rn
    from @FullySpecifiedCdmSchema.observation_period op
  ) op
  JOIN @FullySpecifiedCdmSchema.person p on op.person_id = p.person_id
  where op.rn = 1
),
overallStats (age_decile, avg_value, stdev_value, min_value, max_value, total) as
(
  select age_decile,
    CAST(avg(1.0 * count_value) AS FLOAT) as avg_value,
    CAST(stdev(count_value) AS FLOAT) as stdev_value,
    min(count_value) as min_value,
    max(count_value) as max_value,
    count_big(*) as total
  from rawData
  group by age_decile
),
statsView (age_decile, count_value, total, rn) as
(
  select age_decile,
    count_value, 
		count_big(*) as total, 
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
select 107 as analysis_id,
  CAST(o.age_decile AS VARCHAR(255)) as age_decile,
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
into #tempResults_107
from priorStats p
join overallStats o on p.age_decile = o.age_decile
GROUP BY o.age_decile, o.total, o.min_value, o.max_value, o.avg_value, o.stdev_value
;

--HINT DISTRIBUTE_ON_KEY(stratum_1)
select analysis_id, age_decile as stratum_1, 
cast(null as varchar(255)) as stratum_2, cast(null as varchar(255)) as stratum_3, cast(null as varchar(255)) as stratum_4, cast(null as varchar(255)) as stratum_5,
count_value, min_value, max_value, avg_value, stdev_value, median_value, p10_value, p25_value, p75_value, p90_value
into #s_tmpach_dist_107
FROM #tempResults_107
;

truncate table #tempResults_107;
drop table #tempResults_107;


-- 108	Number of persons by length of observation period, in 30d increments

--HINT DISTRIBUTE_ON_KEY(stratum_1)
WITH rawData AS (
  select
    floor(DATEDIFF(dd, op1.observation_period_start_date, op1.observation_period_end_date)/30) as stratum_1,
    COUNT_BIG(distinct p1.person_id) as count_value
  from @FullySpecifiedCdmSchema.person p1
    inner join
    (select person_id,
      OBSERVATION_PERIOD_START_DATE,
      OBSERVATION_PERIOD_END_DATE,
      ROW_NUMBER() over (PARTITION by person_id order by observation_period_start_date asc) as rn1
       from @FullySpecifiedCdmSchema.observation_period
    ) op1
    on p1.PERSON_ID = op1.PERSON_ID
    where op1.rn1 = 1
  group by floor(DATEDIFF(dd, op1.observation_period_start_date, op1.observation_period_end_date)/30)
)
SELECT
  108 as analysis_id,
  CAST(stratum_1 AS VARCHAR(255)) as stratum_1,
  cast(null as varchar(255)) as stratum_2,
  cast(null as varchar(255)) as stratum_3,
  cast(null as varchar(255)) as stratum_4,
  cast(null as varchar(255)) as stratum_5,
  count_value
into #s_tmpach_108
FROM rawData;


-- 109	Number of persons with continuous observation in each year

--HINT DISTRIBUTE_ON_KEY(stratum_1)
-- generating date key sequences in a cross-dialect compatible fashion
with century as (select '19' num union select '20' num), 
tens as (select '0' num union select '1' num union select '2' num union select '3' num union select '4' num union select '5' num union select '6' num union select '7' num union select '8' num union select '9' num),
ones as (select '0' num union select '1' num union select '2' num union select '3' num union select '4' num union select '5' num union select '6' num union select '7' num union select '8' num union select '9' num),
months as (select '01' as num union select '02' num union select '03' num union select '04' num union select '05' num union select '06' num union select '07' num union select '08' num union select '09' num union select '10' num union select '11' num union select '12' num),
date_keys as (select concat(century.num, tens.num, ones.num,months.num)  obs_month from century cross join tens cross join ones cross join months),
-- From date_keys, we just need each year and the first and last day of each year
ymd as (
select cast(left(obs_month,4) as integer)               as obs_year,
       min(cast(right(left(obs_month,6),2) as integer)) as month_start,
       1                                                as day_start,
       max(cast(right(left(obs_month,6),2) as integer)) as month_end,
       31                                               as day_end
  from date_keys
 where right(left(obs_month,6),2) in ('01','12')
 group by left(obs_month,4)
),
-- This gives us each year and the first and last day of the year 
year_ranges as (
select obs_year,
       datefromparts(obs_year,month_start,day_start) obs_year_start,
       datefromparts(obs_year,month_end,day_end) obs_year_end
  from ymd
 where obs_year >= (select min(year(observation_period_start_date)) from @FullySpecifiedCdmSchema.observation_period)
   and obs_year <= (select max(year(observation_period_start_date)) from @FullySpecifiedCdmSchema.observation_period)
) 
SELECT 
	109                               AS analysis_id,  
	CAST(yr.obs_year AS VARCHAR(255)) AS stratum_1,
	CAST(NULL AS VARCHAR(255))        AS stratum_2, 
	CAST(NULL AS VARCHAR(255))        AS stratum_3, 
	CAST(NULL AS VARCHAR(255))        AS stratum_4, 
	CAST(NULL AS VARCHAR(255))        AS stratum_5,
	COUNT_BIG(DISTINCT op.person_id)  AS count_value
INTO 
	#s_tmpach_109
FROM 
	@FullySpecifiedCdmSchema.observation_period op
CROSS JOIN 
	year_ranges yr
WHERE
	op.observation_period_start_date <= yr.obs_year_start
AND
	op.observation_period_end_date   >= yr.obs_year_end
GROUP BY 
	yr.obs_year
;


-- 110	Number of persons with continuous observation in each month
-- Note: using temp table instead of nested query because this gives vastly improved performance in Oracle

--HINT DISTRIBUTE_ON_KEY(stratum_1)
SELECT
  110 as analysis_id,  
	CAST(t1.obs_month AS VARCHAR(255)) as stratum_1,
	cast(null as varchar(255)) as stratum_2, cast(null as varchar(255)) as stratum_3, cast(null as varchar(255)) as stratum_4, cast(null as varchar(255)) as stratum_5,
	COUNT_BIG(distinct op1.PERSON_ID) as count_value
into #s_tmpach_110
FROM
@FullySpecifiedCdmSchema.observation_period op1
join 
(
  SELECT DISTINCT 
    YEAR(observation_period_start_date)*100 + MONTH(observation_period_start_date) AS obs_month,
    DATEFROMPARTS(YEAR(observation_period_start_date), MONTH(observation_period_start_date), 1)
    AS obs_month_start,
    EOMONTH(observation_period_start_date) AS obs_month_end
  FROM @FullySpecifiedCdmSchema.observation_period
) t1 on	op1.observation_period_start_date <= t1.obs_month_start
	and	op1.observation_period_end_date >= t1.obs_month_end
group by t1.obs_month;




-- 111	Number of persons by observation period start month

--HINT DISTRIBUTE_ON_KEY(stratum_1)
WITH rawData AS (
  select
    YEAR(observation_period_start_date)*100 + month(OBSERVATION_PERIOD_START_DATE) as stratum_1,
    COUNT_BIG(distinct op1.PERSON_ID) as count_value
  from
    @FullySpecifiedCdmSchema.observation_period op1
  group by YEAR(observation_period_start_date)*100 + month(OBSERVATION_PERIOD_START_DATE)
)
SELECT
  111 as analysis_id,
  CAST(stratum_1 AS VARCHAR(255)) as stratum_1,
  cast(null as varchar(255)) as stratum_2,
  cast(null as varchar(255)) as stratum_3,
  cast(null as varchar(255)) as stratum_4,
  cast(null as varchar(255)) as stratum_5,
  count_value
into #s_tmpach_111
FROM rawData;


-- 112	Number of persons by observation period end month

--HINT DISTRIBUTE_ON_KEY(stratum_1)
WITH rawData AS (
  select
    YEAR(observation_period_end_date)*100 + month(observation_period_end_date) as stratum_1,
    COUNT_BIG(distinct op1.PERSON_ID) as count_value
  from
    @FullySpecifiedCdmSchema.observation_period op1
  group by YEAR(observation_period_end_date)*100 + month(observation_period_end_date)
)
SELECT
  112 as analysis_id,
  CAST(stratum_1 AS VARCHAR(255)) as stratum_1,
  cast(null as varchar(255)) as stratum_2,
  cast(null as varchar(255)) as stratum_3,
  cast(null as varchar(255)) as stratum_4,
  cast(null as varchar(255)) as stratum_5,
  count_value
into #s_tmpach_112
FROM rawData;


-- 113	Number of persons by number of observation periods

--HINT DISTRIBUTE_ON_KEY(stratum_1)
select 113 as analysis_id,  
	CAST(op1.num_periods AS VARCHAR(255)) as stratum_1, 
	cast(null as varchar(255)) as stratum_2, cast(null as varchar(255)) as stratum_3, cast(null as varchar(255)) as stratum_4, cast(null as varchar(255)) as stratum_5,
	COUNT_BIG(distinct op1.PERSON_ID) as count_value
into #s_tmpach_113
from
	(select person_id, COUNT_BIG(OBSERVATION_period_start_date) as num_periods from @FullySpecifiedCdmSchema.observation_period group by PERSON_ID) op1
group by op1.num_periods
;


-- 116	Number of persons with at least one day of observation in each year by gender and age decile
-- Note: using temp table instead of nested query because this gives vastly improved performance in Oracle



select distinct 
  YEAR(observation_period_start_date) as obs_year 
INTO
  #temp_dates_116
from 
  @FullySpecifiedCdmSchema.observation_period
;

--HINT DISTRIBUTE_ON_KEY(stratum_1)
WITH rawData AS (
  select
    t1.obs_year as stratum_1,
    p1.gender_concept_id as stratum_2,
    floor((t1.obs_year - p1.year_of_birth)/10) as stratum_3,
    COUNT_BIG(distinct p1.PERSON_ID) as count_value
  from
    @FullySpecifiedCdmSchema.person p1
    inner join
    @FullySpecifiedCdmSchema.observation_period op1
    on p1.person_id = op1.person_id
    ,
    #temp_dates_116 t1
  where year(op1.OBSERVATION_PERIOD_START_DATE) <= t1.obs_year
    and year(op1.OBSERVATION_PERIOD_END_DATE) >= t1.obs_year
  group by t1.obs_year,
    p1.gender_concept_id,
    floor((t1.obs_year - p1.year_of_birth)/10)
)
SELECT
  116 as analysis_id,
  CAST(stratum_1 AS VARCHAR(255)) as stratum_1,
  cast(stratum_2 as varchar(255)) as stratum_2,
  cast(stratum_3 as varchar(255)) as stratum_3,
  cast(null as varchar(255)) as stratum_4,
  cast(null as varchar(255)) as stratum_5,
  count_value
into #s_tmpach_116
FROM rawData;

TRUNCATE TABLE #temp_dates_116;
DROP TABLE #temp_dates_116;


-- 117	Number of persons with at least one day of observation in each month

--HINT DISTRIBUTE_ON_KEY(stratum_1)
-- generating date key sequences in a cross-dialect compatible fashion
with century as (select '19' num union select '20' num), 
tens as (select '0' num union select '1' num union select '2' num union select '3' num union select '4' num union select '5' num union select '6' num union select '7' num union select '8' num union select '9' num),
ones as (select '0' num union select '1' num union select '2' num union select '3' num union select '4' num union select '5' num union select '6' num union select '7' num union select '8' num union select '9' num),
months as (select '01' as num union select '02' num union select '03' num union select '04' num union select '05' num union select '06' num union select '07' num union select '08' num union select '09' num union select '10' num union select '11' num union select '12' num),
date_keys as (select cast(concat(century.num, tens.num, ones.num,months.num) as int) obs_month from century cross join tens cross join ones cross join months)
SELECT
  117 as analysis_id,  
	CAST(t1.obs_month AS VARCHAR(255)) as stratum_1,
	cast(null as varchar(255)) as stratum_2, cast(null as varchar(255)) as stratum_3, cast(null as varchar(255)) as stratum_4, cast(null as varchar(255)) as stratum_5,
	COALESCE(COUNT_BIG(distinct op1.PERSON_ID),0) as count_value
into #s_tmpach_117	
FROM date_keys t1
left join
  (select t2.obs_month, op2.*
    from @FullySpecifiedCdmSchema.observation_period op2, date_keys t2
    where year(op2.observation_period_start_date)*100 + month(op2.observation_period_start_date) <= t2.obs_month
    and year(op2.observation_period_end_date)*100 + month(op2.observation_period_end_date) >= t2.obs_month
  ) op1 on op1.obs_month = t1.obs_month
group by t1.obs_month
having COALESCE(COUNT_BIG(distinct op1.PERSON_ID),0) > 0;


-- 119  Number of observation period records by period_type_concept_id

--HINT DISTRIBUTE_ON_KEY(stratum_1)
select 119 as analysis_id,
  CAST(op1.period_type_concept_id AS VARCHAR(255)) as stratum_1,
  cast(null as varchar(255)) as stratum_2, cast(null as varchar(255)) as stratum_3, cast(null as varchar(255)) as stratum_4, cast(null as varchar(255)) as stratum_5,
  COUNT_BIG(*) as count_value
into #s_tmpach_119
from
  @FullySpecifiedCdmSchema.observation_period op1
group by op1.period_type_concept_id
;


-- 200	Number of persons with at least one visit occurrence, by visit_concept_id
-- restricted to visits overlapping with observation period

--HINT DISTRIBUTE_ON_KEY(stratum_1)
SELECT 
	200 AS analysis_id,
	CAST(vo.visit_concept_id AS VARCHAR(255)) AS stratum_1,
	CAST(NULL AS VARCHAR(255)) AS stratum_2,
	CAST(NULL AS VARCHAR(255)) AS stratum_3,
	CAST(NULL AS VARCHAR(255)) AS stratum_4,
	CAST(NULL AS VARCHAR(255)) AS stratum_5,
	COUNT_BIG(DISTINCT vo.person_id) AS count_value
INTO 
	#s_tmpach_200
FROM 
	@FullySpecifiedCdmSchema.visit_occurrence vo
JOIN 
	@FullySpecifiedCdmSchema.observation_period op 
ON 
	vo.person_id = op.person_id
AND 
	vo.visit_start_date >= op.observation_period_start_date
AND 
	vo.visit_start_date <= op.observation_period_end_date
GROUP BY 
	vo.visit_concept_id;


-- 201	Number of visit occurrence records, by visit_concept_id
-- restricted to visits overlapping with observation period

--HINT DISTRIBUTE_ON_KEY(stratum_1)
SELECT 
	201 AS analysis_id,
	CAST(vo.visit_concept_id AS VARCHAR(255)) AS stratum_1,
	CAST(NULL AS VARCHAR(255)) AS stratum_2,
	CAST(NULL AS VARCHAR(255)) AS stratum_3,
	CAST(NULL AS VARCHAR(255)) AS stratum_4,
	CAST(NULL AS VARCHAR(255)) AS stratum_5,
	COUNT_BIG(vo.person_id) AS count_value
INTO 
	#s_tmpach_201
FROM 
	@FullySpecifiedCdmSchema.visit_occurrence vo
JOIN 
	@FullySpecifiedCdmSchema.observation_period op 
ON 
	vo.person_id = op.person_id
AND 
	vo.visit_start_date >= op.observation_period_start_date
AND 
	vo.visit_start_date <= op.observation_period_end_date
GROUP BY 
	vo.visit_concept_id;


-- 202	Number of persons by visit occurrence start month, by visit_concept_id

--HINT DISTRIBUTE_ON_KEY(stratum_1)
WITH rawData AS (
SELECT 
	vo.visit_concept_id AS stratum_1,
	YEAR(vo.visit_start_date) * 100 + MONTH(vo.visit_start_date) AS stratum_2,
	COUNT_BIG(DISTINCT vo.person_id) AS count_value
FROM 
	@FullySpecifiedCdmSchema.visit_occurrence vo
JOIN 
	@FullySpecifiedCdmSchema.observation_period op 
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
  CAST(stratum_1 AS VARCHAR(255)) AS stratum_1,
  CAST(stratum_2 AS VARCHAR(255)) AS stratum_2,
  CAST(NULL AS VARCHAR(255)) AS stratum_3,
  CAST(NULL AS VARCHAR(255)) AS stratum_4,
  CAST(NULL AS VARCHAR(255)) AS stratum_5,
  count_value
INTO 
	#s_tmpach_202
FROM 
	rawData;


-- 203	Number of distinct visit occurrence concepts per person

--HINT DISTRIBUTE_ON_KEY(count_value)
with rawData(person_id, count_value) as
(
SELECT 
	vo.person_id,
	COUNT_BIG(DISTINCT vo.visit_concept_id) AS count_value
FROM 
	@FullySpecifiedCdmSchema.visit_occurrence vo
JOIN 
	@FullySpecifiedCdmSchema.observation_period op 
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
    CAST(stdev(count_value) AS FLOAT) as stdev_value,
    min(count_value) as min_value,
    max(count_value) as max_value,
    count_big(*) as total
  from rawData
),
statsView (count_value, total, rn) as
(
  select count_value, 
  	count_big(*) as total, 
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
select 203 as analysis_id,
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
INTO #tempResults_203
from priorStats p
CROSS JOIN overallStats o
GROUP BY o.total, o.min_value, o.max_value, o.avg_value, o.stdev_value
;

--HINT DISTRIBUTE_ON_KEY(count_value)
select analysis_id, 
cast(null as varchar(255)) as stratum_1, cast(null as varchar(255)) as stratum_2, cast(null as varchar(255)) as stratum_3, cast(null as varchar(255)) as stratum_4, cast(null as varchar(255)) as stratum_5,
count_value, min_value, max_value, avg_value, stdev_value, median_value, p10_value, p25_value, p75_value, p90_value
into #s_tmpach_dist_203
FROM #tempResults_203
;

truncate table #tempResults_203;
drop table #tempResults_203;


-- 204	Number of persons with at least one visit occurrence, by visit_concept_id by calendar year by gender by age decile

--HINT DISTRIBUTE_ON_KEY(stratum_1)
WITH rawData AS (
SELECT 
	vo.visit_concept_id AS stratum_1,
	YEAR(vo.visit_start_date) AS stratum_2,
	p.gender_concept_id AS stratum_3,
	FLOOR((YEAR(vo.visit_start_date) - p.year_of_birth) / 10) AS stratum_4,
	COUNT_BIG(DISTINCT p.person_id) AS count_value
FROM 
	@FullySpecifiedCdmSchema.person p
JOIN 
	@FullySpecifiedCdmSchema.visit_occurrence vo 
ON 
	p.person_id = vo.person_id
JOIN 
	@FullySpecifiedCdmSchema.observation_period op 
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
	CAST(stratum_1 AS VARCHAR(255)) AS stratum_1,
	CAST(stratum_2 as varchar(255)) AS stratum_2,
	CAST(stratum_3 as varchar(255)) AS stratum_3,
	CAST(stratum_4 as varchar(255)) AS stratum_4,
	CAST(NULL AS VARCHAR(255)) AS stratum_5,
	count_value
INTO 
	#s_tmpach_204
FROM 
	rawData;


-- 206	Distribution of age by visit_concept_id

--HINT DISTRIBUTE_ON_KEY(stratum1_id)
WITH rawData (stratum1_id, stratum2_id, count_value) AS (
SELECT 
	v.visit_concept_id AS stratum1_id,
	p.gender_concept_id AS stratum2_id,
	v.visit_start_year - p.year_of_birth AS count_value
FROM 
	@FullySpecifiedCdmSchema.person p
JOIN (
	SELECT 
		vo.person_id,
		vo.visit_concept_id,
		MIN(YEAR(vo.visit_start_date)) AS visit_start_year
	FROM 
		@FullySpecifiedCdmSchema.visit_occurrence vo
	JOIN 
		@FullySpecifiedCdmSchema.observation_period op 
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
    CAST(stdev(count_value) AS FLOAT) as stdev_value,
    min(count_value) as min_value,
    max(count_value) as max_value,
    count_big(*) as total
  FROM rawData
	group by stratum1_id, stratum2_id
),
statsView (stratum1_id, stratum2_id, count_value, total, rn) as
(
  select stratum1_id, stratum2_id, count_value, count_big(*) as total, row_number() over (partition by stratum1_id, stratum2_id order by count_value) as rn
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
select 206 as analysis_id,
  CAST(o.stratum1_id AS VARCHAR(255)) AS stratum1_id,
  CAST(o.stratum2_id AS VARCHAR(255)) AS stratum2_id,
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
into #tempResults_206
from priorStats p
join overallStats o on p.stratum1_id = o.stratum1_id and p.stratum2_id = o.stratum2_id 
GROUP BY o.stratum1_id, o.stratum2_id, o.total, o.min_value, o.max_value, o.avg_value, o.stdev_value
;

--HINT DISTRIBUTE_ON_KEY(stratum_1)
select analysis_id, stratum1_id as stratum_1, stratum2_id as stratum_2, 
cast(null as varchar(255)) as stratum_3, cast(null as varchar(255)) as stratum_4, cast(null as varchar(255)) as stratum_5,
count_value, min_value, max_value, avg_value, stdev_value, median_value, p10_value, p25_value, p75_value, p90_value
into #s_tmpach_dist_206
from #tempResults_206
;

truncate table #tempResults_206;
drop table #tempResults_206;


--207	Number of visit records with invalid person_id


select 207 as analysis_id,  
	cast(null as varchar(255)) as stratum_1, cast(null as varchar(255)) as stratum_2, cast(null as varchar(255)) as stratum_3, cast(null as varchar(255)) as stratum_4, cast(null as varchar(255)) as stratum_5,
	COUNT_BIG(vo1.PERSON_ID) as count_value
into #s_tmpach_207
from
	@FullySpecifiedCdmSchema.visit_occurrence vo1
	left join @FullySpecifiedCdmSchema.person p1
	on p1.person_id = vo1.person_id
where p1.person_id is null
;


-- 209	Number of visit records with invalid care_site_id


select 209 as analysis_id,
	cast(null as varchar(255)) as stratum_1, cast(null as varchar(255)) as stratum_2, cast(null as varchar(255)) as stratum_3, cast(null as varchar(255)) as stratum_4, cast(null as varchar(255)) as stratum_5,
	COUNT_BIG(vo1.PERSON_ID) as count_value
into #s_tmpach_209
from
	@FullySpecifiedCdmSchema.visit_occurrence vo1
	left join @FullySpecifiedCdmSchema.care_site cs1
	on vo1.care_site_id = cs1.care_site_id
where vo1.care_site_id is not null
	and cs1.care_site_id is null
;


-- 210 Number of visit_occurrence records outside a valid observation period

SELECT 
	210 AS analysis_id,
	CAST(NULL AS VARCHAR(255)) AS stratum_1,
	CAST(NULL AS VARCHAR(255)) AS stratum_2,
	CAST(NULL AS VARCHAR(255)) AS stratum_3,
	CAST(NULL AS VARCHAR(255)) AS stratum_4,
	CAST(NULL AS VARCHAR(255)) AS stratum_5,
	COUNT_BIG(*) AS count_value
INTO 
	#s_tmpach_210
FROM 
	@FullySpecifiedCdmSchema.visit_occurrence vo
LEFT JOIN 
	@FullySpecifiedCdmSchema.observation_period op 
ON 
	vo.person_id = op.person_id
AND 
	vo.visit_start_date >= op.observation_period_start_date
AND 
	vo.visit_start_date <= op.observation_period_end_date
WHERE 
	op.person_id IS NULL
;


-- 212	Number of persons with at least one visit occurrence by calendar year by gender by age decile

--HINT DISTRIBUTE_ON_KEY(stratum_1)
WITH rawData AS (
  select
    YEAR(visit_start_date) as stratum_1,
    p1.gender_concept_id as stratum_2,
    floor((year(visit_start_date) - p1.year_of_birth)/10) as stratum_3,
    COUNT_BIG(distinct p1.PERSON_ID) as count_value
  from @FullySpecifiedCdmSchema.person p1
  inner join
  @FullySpecifiedCdmSchema.visit_occurrence vo1
  on p1.person_id = vo1.person_id
  group by
    YEAR(visit_start_date),
    p1.gender_concept_id,
    floor((year(visit_start_date) - p1.year_of_birth)/10)
)
SELECT
  212 as analysis_id,
  CAST(stratum_1 AS VARCHAR(255)) as stratum_1,
  cast(stratum_2 as varchar(255)) as stratum_2,
  cast(stratum_3 as varchar(255)) as stratum_3,
  cast(null as varchar(255)) as stratum_4,
  cast(null as varchar(255)) as stratum_5,
  count_value
into #s_tmpach_212
FROM rawData;


-- 213	Distribution of length of stay by visit_concept_id
-- restrict to visits inside observation period

--HINT DISTRIBUTE_ON_KEY(stratum_id) 
with rawData(stratum_id, count_value) as
(
  select visit_concept_id AS stratum_id, datediff(dd,visit_start_date,visit_end_date) as count_value
  from @FullySpecifiedCdmSchema.visit_occurrence vo inner join 
  @FullySpecifiedCdmSchema.observation_period op on vo.person_id = op.person_id
  -- only include events that occur during observation period
  where vo.visit_start_date >= op.observation_period_start_date and
  isnull(vo.visit_end_date,vo.visit_start_date) <= op.observation_period_end_date
),
overallStats (stratum_id, avg_value, stdev_value, min_value, max_value, total) as
(
  select stratum_id,
    CAST(avg(1.0 * count_value) AS FLOAT) as avg_value,
    CAST(stdev(count_value) AS FLOAT) as stdev_value,
    min(count_value) as min_value,
    max(count_value) as max_value,
    count_big(*) as total
  FROM rawData
  group by stratum_id
),
statsView (stratum_id, count_value, total, rn) as
(
  select stratum_id, count_value, count_big(*) as total, row_number() over (order by count_value) as rn
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
select 213 as analysis_id,
  CAST(o.stratum_id AS VARCHAR(255)) AS stratum_id,
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
into #tempResults_213
from priorStats p
join overallStats o on p.stratum_id = o.stratum_id
GROUP BY o.stratum_id, o.total, o.min_value, o.max_value, o.avg_value, o.stdev_value
;

--HINT DISTRIBUTE_ON_KEY(stratum_1) 
select analysis_id, stratum_id as stratum_1, 
cast(null as varchar(255)) as stratum_2, cast(null as varchar(255)) as stratum_3, cast(null as varchar(255)) as stratum_4, cast(null as varchar(255)) as stratum_5,
count_value, min_value, max_value, avg_value, stdev_value, median_value, p10_value, p25_value, p75_value, p90_value
into #s_tmpach_dist_213
from #tempResults_213
;

truncate table #tempResults_213;
drop table #tempResults_213;


-- 220	Number of visit occurrence records by condition occurrence start month

--HINT DISTRIBUTE_ON_KEY(stratum_1)
WITH rawData AS (
SELECT 
	YEAR(vo.visit_start_date) * 100 + MONTH(vo.visit_start_date) AS stratum_1,
	COUNT_BIG(vo.person_id) AS count_value
FROM 
	@FullySpecifiedCdmSchema.visit_occurrence vo
JOIN 
	@FullySpecifiedCdmSchema.observation_period op 
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
	CAST(stratum_1 AS VARCHAR(255)) AS stratum_1,
	CAST(NULL AS VARCHAR(255)) AS stratum_2,
	CAST(NULL AS VARCHAR(255)) AS stratum_3,
	CAST(NULL AS VARCHAR(255)) AS stratum_4,
	CAST(NULL AS VARCHAR(255)) AS stratum_5,
	count_value
INTO 
	#s_tmpach_220
FROM 
	rawData;


-- 221	Number of persons by visit start year 

--HINT DISTRIBUTE_ON_KEY(stratum_1)
WITH rawData AS (
SELECT 
	YEAR(vo.visit_start_date) AS stratum_1,
	COUNT_BIG(DISTINCT vo.person_id) AS count_value
FROM 
	@FullySpecifiedCdmSchema.visit_occurrence vo
JOIN 
	@FullySpecifiedCdmSchema.observation_period op 
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
	CAST(stratum_1 AS VARCHAR(255)) AS stratum_1,
	CAST(NULL AS VARCHAR(255)) AS stratum_2,
	CAST(NULL AS VARCHAR(255)) AS stratum_3,
	CAST(NULL AS VARCHAR(255)) AS stratum_4,
	CAST(NULL AS VARCHAR(255)) AS stratum_5,
	count_value
into 
	#s_tmpach_221
FROM 
	rawData;


-- 225	Number of visit_occurrence records, by visit_source_concept_id

--HINT DISTRIBUTE_ON_KEY(stratum_1)
SELECT 
	225 AS analysis_id,
	CAST(vo.visit_source_concept_id AS VARCHAR(255)) AS stratum_1,
	CAST(NULL AS VARCHAR(255)) AS stratum_2,
	CAST(NULL AS VARCHAR(255)) AS stratum_3,
	CAST(NULL AS VARCHAR(255)) AS stratum_4,
	CAST(NULL AS VARCHAR(255)) AS stratum_5,
	COUNT_BIG(*) AS count_value
INTO 
	#s_tmpach_225
FROM 
	@FullySpecifiedCdmSchema.visit_occurrence vo
JOIN 
	@FullySpecifiedCdmSchema.observation_period op 
ON 
	vo.person_id = op.person_id
AND 
	vo.visit_start_date >= op.observation_period_start_date
AND 
	vo.visit_start_date <= op.observation_period_end_date
GROUP BY 
	visit_source_concept_id;


-- 226	Number of records by domain by visit concept id

select 226 as analysis_id, 
	CAST(v.visit_concept_id AS VARCHAR(255)) as stratum_1,
	v.cdm_table as stratum_2,
	cast(null as varchar(255)) as stratum_3, cast(null as varchar(255)) as stratum_4, cast(null as varchar(255)) as stratum_5,
	v.record_count as count_value
into #s_tmpach_226
from (
  select 'drug_exposure' cdm_table, coalesce(visit_concept_id,0) visit_concept_id, count(*) record_count
  from @FullySpecifiedCdmSchema.drug_exposure t
  left join @FullySpecifiedCdmSchema.visit_occurrence v on t.visit_occurrence_id = v.visit_occurrence_id
  group by visit_concept_id
  union
  select 'condition_occurrence' cdm_table, coalesce(visit_concept_id,0) visit_concept_id, count(*) record_count
  from @FullySpecifiedCdmSchema.condition_occurrence t
  left join @FullySpecifiedCdmSchema.visit_occurrence v on t.visit_occurrence_id = v.visit_occurrence_id
  group by visit_concept_id
  union
  select 'device_exposure' cdm_table, coalesce(visit_concept_id,0) visit_concept_id, count(*) record_count
  from @FullySpecifiedCdmSchema.device_exposure t
  left join @FullySpecifiedCdmSchema.visit_occurrence v on t.visit_occurrence_id = v.visit_occurrence_id
  group by visit_concept_id
  union
  select 'procedure_occurrence' cdm_table, coalesce(visit_concept_id,0) visit_concept_id, count(*) record_count
  from @FullySpecifiedCdmSchema.procedure_occurrence t
  left join @FullySpecifiedCdmSchema.visit_occurrence v on t.visit_occurrence_id = v.visit_occurrence_id
  group by visit_concept_id
  union
  select 'measurement' cdm_table, coalesce(visit_concept_id,0) visit_concept_id, count(*) record_count
  from @FullySpecifiedCdmSchema.measurement t
  left join @FullySpecifiedCdmSchema.visit_occurrence v on t.visit_occurrence_id = v.visit_occurrence_id
  group by visit_concept_id
  union
  select 'observation' cdm_table, coalesce(visit_concept_id,0) visit_concept_id, count(*) record_count
  from @FullySpecifiedCdmSchema.observation t
  left join @FullySpecifiedCdmSchema.visit_occurrence v on t.visit_occurrence_id = v.visit_occurrence_id
  group by visit_concept_id
) v
;


-- 300	Number of providers


select 300 as analysis_id,  
cast(null as varchar(255)) as stratum_1, cast(null as varchar(255)) as stratum_2, cast(null as varchar(255)) as stratum_3, cast(null as varchar(255)) as stratum_4, cast(null as varchar(255)) as stratum_5,
COUNT_BIG(distinct provider_id) as count_value
into #s_tmpach_300
from @FullySpecifiedCdmSchema.provider;


-- 301	Number of providers by specialty concept_id

--HINT DISTRIBUTE_ON_KEY(stratum_1)
select 301 as analysis_id,
CAST(specialty_concept_id AS VARCHAR(255)) as stratum_1, 
cast(null as varchar(255)) as stratum_2, cast(null as varchar(255)) as stratum_3, cast(null as varchar(255)) as stratum_4, cast(null as varchar(255)) as stratum_5,
COUNT_BIG(distinct provider_id) as count_value
into #s_tmpach_301
from @FullySpecifiedCdmSchema.provider
group by specialty_CONCEPT_ID;


-- 303	Number of provider records, by specialty_concept_id, visit_concept_id

--HINT DISTRIBUTE_ON_KEY(stratum_1)
select 303 as analysis_id,
       cast(p.specialty_concept_id AS varchar(255)) AS stratum_1,
       cast(vo.visit_concept_id    AS varchar(255)) AS stratum_2,
       cast(null as varchar(255)) as stratum_3,
       cast(null as varchar(255)) as stratum_4,
       cast(null as varchar(255)) as stratum_5, 
       count_big(*) AS count_value
  into #s_tmpach_303 
  from @FullySpecifiedCdmSchema.provider p
  join @FullySpecifiedCdmSchema.visit_occurrence vo
    on vo.provider_id = p.provider_id
 group by p.specialty_concept_id, visit_concept_id;
 
 


-- 325	Number of provider records, by specialty_source_concept_id

--HINT DISTRIBUTE_ON_KEY(stratum_1)
select 325 as analysis_id,
       cast(specialty_source_concept_id AS varchar(255)) AS stratum_1,
       cast(null AS varchar(255)) AS stratum_2,
       cast(null as varchar(255)) as stratum_3,
       cast(null as varchar(255)) as stratum_4,
       cast(null as varchar(255)) as stratum_5, 
       count_big(*) AS count_value
  into #s_tmpach_325 
  from @FullySpecifiedCdmSchema.provider
 group by specialty_source_concept_id;
 
 


-- 400	Number of persons with at least one condition occurrence, by condition_concept_id

--HINT DISTRIBUTE_ON_KEY(stratum_1)
SELECT 
	400 AS analysis_id,
	CAST(co.condition_concept_id AS VARCHAR(255)) AS stratum_1,
	CAST(NULL AS VARCHAR(255)) AS stratum_2,
	CAST(NULL AS VARCHAR(255)) AS stratum_3,
	CAST(NULL AS VARCHAR(255)) AS stratum_4,
	CAST(NULL AS VARCHAR(255)) AS stratum_5,
	COUNT_BIG(DISTINCT co.person_id) AS count_value
INTO 
	#s_tmpach_400
FROM 
	@FullySpecifiedCdmSchema.condition_occurrence co
JOIN 
	@FullySpecifiedCdmSchema.observation_period op 
ON 
	co.person_id = op.person_id
AND 
	co.condition_start_date >= op.observation_period_start_date
AND 
	co.condition_start_date <= op.observation_period_end_date
GROUP BY 
	co.condition_concept_id;


-- 401	Number of condition occurrence records, by condition_concept_id

--HINT DISTRIBUTE_ON_KEY(stratum_1)
SELECT 
	401 AS analysis_id,
	CAST(co.condition_concept_id AS VARCHAR(255)) AS stratum_1,
	CAST(NULL AS VARCHAR(255)) AS stratum_2,
	CAST(NULL AS VARCHAR(255)) AS stratum_3,
	CAST(NULL AS VARCHAR(255)) AS stratum_4,
	CAST(NULL AS VARCHAR(255)) AS stratum_5,
	COUNT_BIG(co.person_id) AS count_value
INTO 
	#s_tmpach_401
FROM 
	@FullySpecifiedCdmSchema.condition_occurrence co
JOIN 
	@FullySpecifiedCdmSchema.observation_period op 
ON 
	co.person_id = op.person_id
AND 
	co.condition_start_date >= op.observation_period_start_date
AND 
	co.condition_start_date <= op.observation_period_end_date
GROUP BY 
	co.condition_concept_id;


-- 402	Number of persons by condition occurrence start month, by condition_concept_id

--HINT DISTRIBUTE_ON_KEY(stratum_1)
WITH rawData AS (
SELECT 
	co.condition_concept_id AS stratum_1,
	YEAR(co.condition_start_date) * 100 + MONTH(co.condition_start_date) AS stratum_2,
	COUNT_BIG(DISTINCT co.person_id) AS count_value
FROM 
	@FullySpecifiedCdmSchema.condition_occurrence co
JOIN 
	@FullySpecifiedCdmSchema.observation_period op 
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
  CAST(stratum_1 AS VARCHAR(255)) AS stratum_1,
  CAST(stratum_2 AS VARCHAR(255)) AS stratum_2,
  CAST(NULL AS VARCHAR(255)) AS stratum_3,
  CAST(NULL AS VARCHAR(255)) AS stratum_4,
  CAST(NULL AS VARCHAR(255)) AS stratum_5,
  count_value
INTO 
	#s_tmpach_402
FROM 
	rawData;


-- 403	Number of distinct condition occurrence concepts per person

--HINT DISTRIBUTE_ON_KEY(count_value)
with rawData(person_id, count_value) as
(
SELECT 
	co.person_id,
	COUNT_BIG(DISTINCT co.condition_concept_id) AS count_value
FROM 
	@FullySpecifiedCdmSchema.condition_occurrence co
JOIN 
	@FullySpecifiedCdmSchema.observation_period op 
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
    CAST(stdev(count_value) AS FLOAT) as stdev_value,
    min(count_value) as min_value,
    max(count_value) as max_value,
    count_big(*) as total
  from rawData
),
statsView (count_value, total, rn) as
(
  select count_value, 
  	count_big(*) as total, 
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
select 403 as analysis_id,
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
into #tempResults_403
from priorStats p
CROSS JOIN overallStats o
GROUP BY o.total, o.min_value, o.max_value, o.avg_value, o.stdev_value
;

--HINT DISTRIBUTE_ON_KEY(count_value)
select analysis_id, 
cast(null as varchar(255)) as stratum_1, cast(null as varchar(255)) as stratum_2, cast(null as varchar(255)) as stratum_3, cast(null as varchar(255)) as stratum_4, cast(null as varchar(255)) as stratum_5,
count_value, min_value, max_value, avg_value, stdev_value, median_value, p10_value, p25_value, p75_value, p90_value
into #s_tmpach_dist_403
from #tempResults_403
;

truncate table #tempResults_403;
drop table #tempResults_403;


-- 404	Number of persons with at least one condition occurrence, by condition_concept_id by calendar year by gender by age decile

--HINT DISTRIBUTE_ON_KEY(stratum_1)
WITH rawData AS (
SELECT 
	co.condition_concept_id AS stratum_1,
	YEAR(co.condition_start_date) AS stratum_2,
	p.gender_concept_id AS stratum_3,
	FLOOR((YEAR(co.condition_start_date) - p.year_of_birth) / 10) AS stratum_4,
	COUNT_BIG(DISTINCT p.person_id) AS count_value
FROM 
	@FullySpecifiedCdmSchema.person p
JOIN 
	@FullySpecifiedCdmSchema.condition_occurrence co 
ON 
	p.person_id = co.person_id
JOIN 
	@FullySpecifiedCdmSchema.observation_period op 
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
	CAST(stratum_1 AS VARCHAR(255)) AS stratum_1,
	CAST(stratum_2 AS VARCHAR(255)) AS stratum_2,
	CAST(stratum_3 AS VARCHAR(255)) AS stratum_3,
	CAST(stratum_4 AS VARCHAR(255)) AS stratum_4,
	CAST(NULL AS VARCHAR(255)) AS stratum_5,
	count_value
INTO 
	#s_tmpach_404
FROM 
	rawData;


-- 405	Number of condition occurrence records, by condition_concept_id by condition_type_concept_id

--HINT DISTRIBUTE_ON_KEY(stratum_1)
SELECT 
	405 AS analysis_id,
	CAST(co.condition_concept_id AS VARCHAR(255)) AS stratum_1,
	CAST(co.condition_type_concept_id AS VARCHAR(255)) AS stratum_2,
	CAST(NULL AS VARCHAR(255)) AS stratum_3,
	CAST(NULL AS VARCHAR(255)) AS stratum_4,
	CAST(NULL AS VARCHAR(255)) AS stratum_5,
	COUNT_BIG(co.person_id) AS count_value
INTO 
	#s_tmpach_405
FROM 
	@FullySpecifiedCdmSchema.condition_occurrence co
JOIN 
	@FullySpecifiedCdmSchema.observation_period op 
ON 
	co.person_id = op.person_id
AND 
	co.condition_start_date >= op.observation_period_start_date
AND 
	co.condition_start_date <= op.observation_period_end_date
GROUP BY 
	co.condition_CONCEPT_ID,
	co.condition_type_concept_id;


-- 406	Distribution of age by condition_concept_id

--HINT DISTRIBUTE_ON_KEY(subject_id)
SELECT 
	c.condition_concept_id AS subject_id,
	p.gender_concept_id,
	(c.condition_start_year - p.year_of_birth) AS count_value
INTO 
	#rawData_406
FROM 
	@FullySpecifiedCdmSchema.person p
JOIN (
	SELECT 
		co.person_id,
		co.condition_concept_id,
		MIN(YEAR(co.condition_start_date)) AS condition_start_year
	FROM 
		@FullySpecifiedCdmSchema.condition_occurrence co
	JOIN 
		@FullySpecifiedCdmSchema.observation_period op 
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

--HINT DISTRIBUTE_ON_KEY(stratum1_id)
with overallStats (stratum1_id, stratum2_id, avg_value, stdev_value, min_value, max_value, total) as
(
  select subject_id as stratum1_id,
    gender_concept_id as stratum2_id,
    CAST(avg(1.0 * count_value) AS FLOAT) as avg_value,
    CAST(stdev(count_value) AS FLOAT) as stdev_value,
    min(count_value) as min_value,
    max(count_value) as max_value,
    count_big(*) as total
  FROM #rawData_406
	group by subject_id, gender_concept_id
),
statsView (stratum1_id, stratum2_id, count_value, total, rn) as
(
  select subject_id as stratum1_id, gender_concept_id as stratum2_id, count_value, count_big(*) as total, row_number() over (partition by subject_id, gender_concept_id order by count_value) as rn
  FROM #rawData_406
  group by subject_id, gender_concept_id, count_value
),
priorStats (stratum1_id, stratum2_id, count_value, total, accumulated) as
(
  select s.stratum1_id, s.stratum2_id, s.count_value, s.total, sum(p.total) as accumulated
  from statsView s
  join statsView p on s.stratum1_id = p.stratum1_id and s.stratum2_id = p.stratum2_id and p.rn <= s.rn
  group by s.stratum1_id, s.stratum2_id, s.count_value, s.total, s.rn
)
select 406 as analysis_id,
  CAST(o.stratum1_id AS VARCHAR(255)) AS stratum1_id,
  CAST(o.stratum2_id AS VARCHAR(255)) AS stratum2_id,
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
INTO #tempResults_406
from priorStats p
join overallStats o on p.stratum1_id = o.stratum1_id and p.stratum2_id = o.stratum2_id 
GROUP BY o.stratum1_id, o.stratum2_id, o.total, o.min_value, o.max_value, o.avg_value, o.stdev_value
;

--HINT DISTRIBUTE_ON_KEY(stratum_1)
select analysis_id, stratum1_id as stratum_1, stratum2_id as stratum_2, 
cast(null as varchar(255)) as stratum_3, cast(null as varchar(255)) as stratum_4, cast(null as varchar(255)) as stratum_5,
count_value, min_value, max_value, avg_value, stdev_value, median_value, p10_value, p25_value, p75_value, p90_value
into #s_tmpach_dist_406
from #tempResults_406
;

truncate table #tempResults_406;
drop table #tempResults_406;

truncate Table #rawData_406;
drop table #rawData_406;


-- 414	Number of condition occurrence records, by condition_status_concept_id

--HINT DISTRIBUTE_ON_KEY(stratum_1)
SELECT 
	414 AS analysis_id,
	CAST(co.condition_status_concept_id AS VARCHAR(255)) AS stratum_1,
	CAST(NULL AS VARCHAR(255)) AS stratum_2,
	CAST(NULL AS VARCHAR(255)) AS stratum_3,
	CAST(NULL AS VARCHAR(255)) AS stratum_4,
	CAST(NULL AS VARCHAR(255)) AS stratum_5,
	COUNT_BIG(*) AS count_value
INTO 
	#s_tmpach_414
FROM 
	@FullySpecifiedCdmSchema.condition_occurrence co
JOIN 
	@FullySpecifiedCdmSchema.observation_period op 
ON 
	co.person_id = op.person_id
AND 
	co.condition_start_date >= op.observation_period_start_date
AND 
	co.condition_start_date <= op.observation_period_end_date
GROUP BY 
	co.condition_status_concept_id;


-- 415	Number of condition occurrence records, by condition_type_concept_id

--HINT DISTRIBUTE_ON_KEY(stratum_1)
SELECT 
	415 AS analysis_id,
	CAST(co.condition_type_concept_id AS VARCHAR(255)) AS stratum_1,
	CAST(NULL AS VARCHAR(255)) AS stratum_2,
	CAST(NULL AS VARCHAR(255)) AS stratum_3,
	CAST(NULL AS VARCHAR(255)) AS stratum_4,
	CAST(NULL AS VARCHAR(255)) AS stratum_5,
	COUNT_BIG(*) AS count_value
INTO 
	#s_tmpach_415
FROM 
	@FullySpecifiedCdmSchema.condition_occurrence co
JOIN 
	@FullySpecifiedCdmSchema.observation_period op 
ON 
	co.person_id = op.person_id
AND 
	co.condition_start_date >= op.observation_period_start_date
AND 
	co.condition_start_date <= op.observation_period_end_date
GROUP BY 
	co.condition_type_concept_id;


-- 416	Number of condition occurrence records, by condition_status_concept_id, condition_type_concept_id

--HINT DISTRIBUTE_ON_KEY(stratum_1)
SELECT 
	416 AS analysis_id,
	CAST(co.condition_status_concept_id AS VARCHAR(255)) AS stratum_1,
	CAST(co.condition_type_concept_id AS VARCHAR(255))   AS stratum_2,
	CAST(NULL AS VARCHAR(255)) AS stratum_3,
	CAST(NULL AS VARCHAR(255)) AS stratum_4,
	CAST(NULL AS VARCHAR(255)) AS stratum_5,
	COUNT_BIG(*) AS count_value
INTO 
	#s_tmpach_416
FROM 
	@FullySpecifiedCdmSchema.condition_occurrence co
JOIN 
	@FullySpecifiedCdmSchema.observation_period op 
ON 
	co.person_id = op.person_id
AND 
	co.condition_start_date >= op.observation_period_start_date
AND 
	co.condition_start_date <= op.observation_period_end_date
GROUP BY 
	co.condition_status_concept_id, co.condition_type_concept_id;


-- 420	Number of condition occurrence records by condition occurrence start month

--HINT DISTRIBUTE_ON_KEY(stratum_1)
WITH rawData AS (
SELECT 
	YEAR(co.condition_start_date) * 100 + MONTH(co.condition_start_date) AS stratum_1,
	COUNT_BIG(co.person_id) AS count_value
FROM 
	@FullySpecifiedCdmSchema.condition_occurrence co
JOIN 
	@FullySpecifiedCdmSchema.observation_period op 
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
	CAST(stratum_1 AS VARCHAR(255)) AS stratum_1,
	CAST(NULL AS VARCHAR(255)) AS stratum_2,
	CAST(NULL AS VARCHAR(255)) AS stratum_3,
	CAST(NULL AS VARCHAR(255)) AS stratum_4,
	CAST(NULL AS VARCHAR(255)) AS stratum_5,
	count_value
INTO 
	#s_tmpach_420
FROM 
	rawData;


-- 425	Number of condition_occurrence records, by condition_source_concept_id

--HINT DISTRIBUTE_ON_KEY(stratum_1)
SELECT 
	425 AS analysis_id,
	CAST(co.condition_source_concept_id AS VARCHAR(255)) AS stratum_1,
	CAST(NULL AS VARCHAR(255)) AS stratum_2,
	CAST(NULL AS VARCHAR(255)) AS stratum_3,
	CAST(NULL AS VARCHAR(255)) AS stratum_4,
	CAST(NULL AS VARCHAR(255)) AS stratum_5,
	COUNT_BIG(*) AS count_value
INTO 
	#s_tmpach_425
FROM 
	@FullySpecifiedCdmSchema.condition_occurrence co
JOIN 
	@FullySpecifiedCdmSchema.observation_period op 
ON 
	co.person_id = op.person_id
AND 
	co.condition_start_date >= op.observation_period_start_date
AND 
	co.condition_start_date <= op.observation_period_end_date
GROUP BY 
	co.condition_source_concept_id;
 



-- 500	Number of persons with death, by cause_concept_id

--HINT DISTRIBUTE_ON_KEY(stratum_1)
SELECT 
	500 AS analysis_id,
	CAST(d.cause_concept_id AS VARCHAR(255)) AS stratum_1,
	CAST(NULL AS VARCHAR(255)) AS stratum_2,
	CAST(NULL AS VARCHAR(255)) AS stratum_3,
	CAST(NULL AS VARCHAR(255)) AS stratum_4,
	CAST(NULL AS VARCHAR(255)) AS stratum_5,
	COUNT_BIG(DISTINCT d.person_id) AS count_value
INTO 
	#s_tmpach_500
FROM 
	@FullySpecifiedCdmSchema.death d
JOIN 
	@FullySpecifiedCdmSchema.observation_period op 
ON 
	d.person_id = op.person_id
AND 
	d.death_date >= op.observation_period_start_date
AND 
	d.death_date <= op.observation_period_end_date	
GROUP BY 
	d.cause_concept_id;


-- 501	Number of records of death, by cause_concept_id

--HINT DISTRIBUTE_ON_KEY(stratum_1)
SELECT 
	501 AS analysis_id,
	CAST(d.cause_concept_id AS VARCHAR(255)) AS stratum_1,
	CAST(NULL AS VARCHAR(255)) AS stratum_2,
	CAST(NULL AS VARCHAR(255)) AS stratum_3,
	CAST(NULL AS VARCHAR(255)) AS stratum_4,
	CAST(NULL AS VARCHAR(255)) AS stratum_5,
	COUNT_BIG(d.person_id) AS count_value
INTO 
	#s_tmpach_501
FROM 
	@FullySpecifiedCdmSchema.death d
JOIN 
	@FullySpecifiedCdmSchema.observation_period op 
ON 
	d.person_id = op.person_id
AND 
	d.death_date >= op.observation_period_start_date
AND 
	d.death_date <= op.observation_period_end_date	
GROUP BY 
	d.cause_concept_id;


-- 502	Number of persons by death month

--HINT DISTRIBUTE_ON_KEY(stratum_1)
WITH rawData AS (
SELECT 
	YEAR(d.death_date) * 100 + MONTH(d.death_date) AS stratum_1,
	COUNT_BIG(DISTINCT d.person_id) AS count_value
FROM 
	@FullySpecifiedCdmSchema.death d
JOIN 
	@FullySpecifiedCdmSchema.observation_period op 
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
  CAST(stratum_1 AS VARCHAR(255)) AS stratum_1,
  CAST(NULL AS VARCHAR(255)) AS stratum_2,
  CAST(NULL AS VARCHAR(255)) AS stratum_3,
  CAST(NULL AS VARCHAR(255)) AS stratum_4,
  CAST(NULL AS VARCHAR(255)) AS stratum_5,
  count_value
INTO 
	#s_tmpach_502
FROM 
	rawData;


-- 504	Number of persons with a death, by calendar year by gender by age decile

--HINT DISTRIBUTE_ON_KEY(stratum_1)
WITH rawData AS (
SELECT 
	YEAR(d.death_date) AS stratum_1,
	p.gender_concept_id AS stratum_2,
	FLOOR((YEAR(d.death_date) - p.year_of_birth) / 10) AS stratum_3,
	COUNT_BIG(DISTINCT p.person_id) AS count_value
FROM 
	@FullySpecifiedCdmSchema.person p
JOIN 
	@FullySpecifiedCdmSchema.death d 
ON 
	p.person_id = d.person_id
JOIN 
	@FullySpecifiedCdmSchema.observation_period op 
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
	CAST(stratum_1 AS VARCHAR(255)) AS stratum_1,
	CAST(stratum_2 AS VARCHAR(255)) AS stratum_2,
	CAST(stratum_3 AS VARCHAR(255)) AS stratum_3,
	CAST(NULL AS VARCHAR(255)) AS stratum_4,
	CAST(NULL AS VARCHAR(255)) AS stratum_5,
	count_value
INTO 
	#s_tmpach_504
FROM 
	rawData;


-- 505	Number of death records, by death_type_concept_id

--HINT DISTRIBUTE_ON_KEY(stratum_1)
SELECT 
	505 AS analysis_id,
	CAST(d.death_type_concept_id AS VARCHAR(255)) AS stratum_1,
	CAST(NULL AS VARCHAR(255)) AS stratum_2,
	CAST(NULL AS VARCHAR(255)) AS stratum_3,
	CAST(NULL AS VARCHAR(255)) AS stratum_4,
	CAST(NULL AS VARCHAR(255)) AS stratum_5,
	COUNT_BIG(d.person_id) AS count_value
INTO 
	#s_tmpach_505
FROM 
	@FullySpecifiedCdmSchema.death d
JOIN 
	@FullySpecifiedCdmSchema.observation_period op 
ON 
	d.person_id = op.person_id
AND 
	d.death_date >= op.observation_period_start_date
AND 
	d.death_date <= op.observation_period_end_date	
GROUP BY 
	d.death_type_concept_id;


-- 506	Distribution of age at death by gender


--HINT DISTRIBUTE_ON_KEY(stratum_id)
WITH rawData(stratum_id, count_value) AS
(
SELECT 
	p.gender_concept_id AS stratum_id,
	d.death_year - p.year_of_birth AS count_value
FROM 
	@FullySpecifiedCdmSchema.person p
JOIN (
	SELECT 
		d.person_id,
		MIN(YEAR(d.death_date)) AS death_year
	FROM 
		@FullySpecifiedCdmSchema.death d
	JOIN 
		@FullySpecifiedCdmSchema.observation_period op 
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
    CAST(stdev(count_value) AS FLOAT) as stdev_value,
    min(count_value) as min_value,
    max(count_value) as max_value,
    count_big(*) as total
  FROM rawData
  group by stratum_id
),
statsView (stratum_id, count_value, total, rn) as
(
  select stratum_id, count_value, count_big(*) as total, row_number() over (order by count_value) as rn
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
select 506 as analysis_id,
  CAST(o.stratum_id AS VARCHAR(255)) AS stratum_id,
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
into #tempResults_506
from priorStats p
join overallStats o on p.stratum_id = o.stratum_id
GROUP BY o.stratum_id, o.total, o.min_value, o.max_value, o.avg_value, o.stdev_value
;

--HINT DISTRIBUTE_ON_KEY(stratum_1)
select analysis_id, stratum_id as stratum_1, 
cast(null as varchar(255)) as stratum_2, cast(null as varchar(255)) as stratum_3, cast(null as varchar(255)) as stratum_4, cast(null as varchar(255)) as stratum_5,
count_value, min_value, max_value, avg_value, stdev_value, median_value, p10_value, p25_value, p75_value, p90_value
into #s_tmpach_dist_506
from #tempResults_506
;

truncate table #tempResults_506;

drop table #tempResults_506;


-- 511	Distribution of time from death to last condition

--HINT DISTRIBUTE_ON_KEY(count_value)
SELECT 
	511 AS analysis_id,
	CAST(NULL AS VARCHAR(255)) AS stratum_1,
	CAST(NULL AS VARCHAR(255)) AS stratum_2,
	CAST(NULL AS VARCHAR(255)) AS stratum_3,
	CAST(NULL AS VARCHAR(255)) AS stratum_4,
	CAST(NULL AS VARCHAR(255)) AS stratum_5,
	COUNT_BIG(count_value) AS count_value,
	MIN(count_value) AS min_value,
	MAX(count_value) AS max_value,
	CAST(AVG(1.0 * count_value) AS FLOAT) AS avg_value,
	CAST(STDEV(count_value) AS FLOAT) AS stdev_value,
	MAX(CASE WHEN p1 <= 0.50 THEN count_value ELSE - 9999 END) AS median_value,
	MAX(CASE WHEN p1 <= 0.10 THEN count_value ELSE - 9999 END) AS p10_value,
	MAX(CASE WHEN p1 <= 0.25 THEN count_value ELSE - 9999 END) AS p25_value,
	MAX(CASE WHEN p1 <= 0.75 THEN count_value ELSE - 9999 END) AS p75_value,
	MAX(CASE WHEN p1 <= 0.90 THEN count_value ELSE - 9999 END) AS p90_value
INTO 
	#s_tmpach_dist_511
FROM (
SELECT 
	DATEDIFF(dd, d.death_date, co.max_date) AS count_value,
	1.0 * (ROW_NUMBER() OVER (ORDER BY DATEDIFF(dd, d.death_date, co.max_date))) / (COUNT_BIG(*) OVER () + 1) AS p1
FROM 
	@FullySpecifiedCdmSchema.death d
JOIN (
	SELECT 
		co.person_id,
		MAX(co.condition_start_date) AS max_date
	FROM 
		@FullySpecifiedCdmSchema.condition_occurrence co
	JOIN 
		@FullySpecifiedCdmSchema.observation_period op 
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


-- 512	Distribution of time from death to last drug

--HINT DISTRIBUTE_ON_KEY(count_value)
WITH rawData(count_value) AS (
SELECT 
	DATEDIFF(dd, d.death_date, de.max_date) AS count_value
FROM 
	@FullySpecifiedCdmSchema.death d
JOIN (
	SELECT 
		de.person_id,
		MAX(de.drug_exposure_start_date) AS max_date
	FROM 
		@FullySpecifiedCdmSchema.drug_exposure de
	JOIN 
		@FullySpecifiedCdmSchema.observation_period op 
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
    CAST(stdev(count_value) AS FLOAT) as stdev_value,
    min(count_value) as min_value,
    max(count_value) as max_value,
    count_big(*) as total
  from rawData
),
statsView (count_value, total, rn) as
(
  select count_value, 
  	count_big(*) as total, 
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
select 512 as analysis_id,
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
into #tempResults_512
from priorStats p
CROSS JOIN overallStats o
GROUP BY o.total, o.min_value, o.max_value, o.avg_value, o.stdev_value
;

--HINT DISTRIBUTE_ON_KEY(count_value)
select analysis_id, 
cast(null as varchar(255)) as stratum_1, cast(null as varchar(255)) as stratum_2, cast(null as varchar(255)) as stratum_3, cast(null as varchar(255)) as stratum_4, cast(null as varchar(255)) as stratum_5,
count_value, min_value, max_value, avg_value, stdev_value, median_value, p10_value, p25_value, p75_value, p90_value
into #s_tmpach_dist_512
FROM #tempResults_512
;

truncate table #tempResults_512;

drop table #tempResults_512;


-- 513	Distribution of time from death to last visit

--HINT DISTRIBUTE_ON_KEY(count_value)
WITH rawData(count_value) AS (
SELECT 
	DATEDIFF(dd, d.death_date, vo.max_date) AS count_value
FROM 
	@FullySpecifiedCdmSchema.death d
JOIN (
	SELECT 
		vo.person_id,
		MAX(vo.visit_start_date) AS max_date
	FROM 
		@FullySpecifiedCdmSchema.visit_occurrence vo
	JOIN 
		@FullySpecifiedCdmSchema.observation_period op 
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
    CAST(stdev(count_value) AS FLOAT) as stdev_value,
    min(count_value) as min_value,
    max(count_value) as max_value,
    count_big(*) as total
  from rawData
),
statsView (count_value, total, rn) as
(
  select count_value, 
  	count_big(*) as total, 
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
select 513 as analysis_id,
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
into #tempResults_513
from priorStats p
CROSS JOIN overallStats o
GROUP BY o.total, o.min_value, o.max_value, o.avg_value, o.stdev_value
;

--HINT DISTRIBUTE_ON_KEY(count_value)
select analysis_id, 
cast(null as varchar(255)) as stratum_1, cast(null as varchar(255)) as stratum_2, cast(null as varchar(255)) as stratum_3, cast(null as varchar(255)) as stratum_4, cast(null as varchar(255)) as stratum_5,
count_value, min_value, max_value, avg_value, stdev_value, median_value, p10_value, p25_value, p75_value, p90_value
into #s_tmpach_dist_513
from #tempResults_513
;

truncate table #tempResults_513;

drop table #tempResults_513;


-- 514	Distribution of time from death to last procedure

--HINT DISTRIBUTE_ON_KEY(count_value)
WITH rawData(count_value) AS (
SELECT 
	DATEDIFF(dd, d.death_date, po.max_date) AS count_value
FROM 
	@FullySpecifiedCdmSchema.death d
JOIN (
	SELECT 
		po.person_id,
		MAX(po.procedure_date) AS max_date
	FROM 
		@FullySpecifiedCdmSchema.procedure_occurrence po
	JOIN 
		@FullySpecifiedCdmSchema.observation_period op 
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
    CAST(stdev(count_value) AS FLOAT) as stdev_value,
    min(count_value) as min_value,
    max(count_value) as max_value,
    count_big(*) as total
  from rawData
),
statsView (count_value, total, rn) as
(
  select count_value, 
  	count_big(*) as total, 
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
select 514 as analysis_id,
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
into #tempResults_514
from priorStats p
CROSS JOIN overallStats o
GROUP BY o.total, o.min_value, o.max_value, o.avg_value, o.stdev_value
;

--HINT DISTRIBUTE_ON_KEY(count_value)
select analysis_id, 
cast(null as varchar(255)) as stratum_1, cast(null as varchar(255)) as stratum_2, cast(null as varchar(255)) as stratum_3, cast(null as varchar(255)) as stratum_4, cast(null as varchar(255)) as stratum_5,
count_value, min_value, max_value, avg_value, stdev_value, median_value, p10_value, p25_value, p75_value, p90_value
into #s_tmpach_dist_514
from #tempResults_514
;

truncate table #tempResults_514;

drop table #tempResults_514;


-- 515	Distribution of time from death to last observation

--HINT DISTRIBUTE_ON_KEY(count_value)
WITH rawData(count_value) AS (
SELECT 
	DATEDIFF(dd, d.death_date, o.max_date) AS count_value
FROM 
	@FullySpecifiedCdmSchema.death d
JOIN (
	SELECT 
		o.person_id,
		MAX(o.observation_date) AS max_date
	FROM 
		@FullySpecifiedCdmSchema.observation o
	JOIN 
		@FullySpecifiedCdmSchema.observation_period op 
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
    CAST(stdev(count_value) AS FLOAT) as stdev_value,
    min(count_value) as min_value,
    max(count_value) as max_value,
    count_big(*) as total
  from rawData
),
statsView (count_value, total, rn) as
(
  select count_value, 
  	count_big(*) as total, 
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
select 515 as analysis_id,
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
into #tempResults_515
from priorStats p
CROSS JOIN overallStats o
GROUP BY o.total, o.min_value, o.max_value, o.avg_value, o.stdev_value
;

--HINT DISTRIBUTE_ON_KEY(count_value)
select analysis_id, 
cast(null as varchar(255)) as stratum_1, cast(null as varchar(255)) as stratum_2, cast(null as varchar(255)) as stratum_3, cast(null as varchar(255)) as stratum_4, cast(null as varchar(255)) as stratum_5,
count_value, min_value, max_value, avg_value, stdev_value, median_value, p10_value, p25_value, p75_value, p90_value
into #s_tmpach_dist_515
from #tempResults_515
;

truncate table #tempResults_515;
drop table #tempResults_515;


-- 525	Number of death records, by cause_source_concept_id

--HINT DISTRIBUTE_ON_KEY(stratum_1)
select 525 as analysis_id,
       cast(cause_source_concept_id AS varchar(255)) AS stratum_1,
       cast(null AS varchar(255)) AS stratum_2,
       cast(null as varchar(255)) as stratum_3,
       cast(null as varchar(255)) as stratum_4,
       cast(null as varchar(255)) as stratum_5,
       count_big(*) AS count_value
  into #s_tmpach_525 
  from @FullySpecifiedCdmSchema.death
 group by cause_source_concept_id;


-- 600	Number of persons with at least one procedure occurrence, by procedure_concept_id

--HINT DISTRIBUTE_ON_KEY(stratum_1)
SELECT 
	600 AS analysis_id,
	CAST(po.procedure_concept_id AS VARCHAR(255)) AS stratum_1,
	CAST(NULL AS VARCHAR(255)) AS stratum_2,
	CAST(NULL AS VARCHAR(255)) AS stratum_3,
	CAST(NULL AS VARCHAR(255)) AS stratum_4,
	CAST(NULL AS VARCHAR(255)) AS stratum_5,
	COUNT_BIG(DISTINCT po.person_id) AS count_value
INTO 
	#s_tmpach_600
FROM 
	@FullySpecifiedCdmSchema.procedure_occurrence po
JOIN 
	@FullySpecifiedCdmSchema.observation_period op 
ON 
	po.person_id = op.person_id
AND 
	po.procedure_date >= op.observation_period_start_date
AND 
	po.procedure_date <= op.observation_period_end_date
GROUP BY 
	po.procedure_concept_id;


-- 601	Number of procedure occurrence records, by procedure_concept_id

--HINT DISTRIBUTE_ON_KEY(stratum_1)
SELECT 
	601 AS analysis_id,
	CAST(po.procedure_concept_id AS VARCHAR(255)) AS stratum_1,
	CAST(NULL AS VARCHAR(255)) AS stratum_2,
	CAST(NULL AS VARCHAR(255)) AS stratum_3,
	CAST(NULL AS VARCHAR(255)) AS stratum_4,
	CAST(NULL AS VARCHAR(255)) AS stratum_5,
	COUNT_BIG(po.person_id) AS count_value
INTO 
	#s_tmpach_601
FROM 
	@FullySpecifiedCdmSchema.procedure_occurrence po
JOIN 
	@FullySpecifiedCdmSchema.observation_period op 
ON 
	po.person_id = op.person_id
AND 
	po.procedure_date >= op.observation_period_start_date
AND 
	po.procedure_date <= op.observation_period_end_date
GROUP BY 
	po.procedure_concept_id;


-- 602	Number of persons by procedure occurrence start month, by procedure_concept_id

--HINT DISTRIBUTE_ON_KEY(stratum_1)
WITH rawData AS (
SELECT 
	po.procedure_concept_id AS stratum_1,
	YEAR(po.procedure_date) * 100 + MONTH(po.procedure_date) AS stratum_2,
	COUNT_BIG(DISTINCT po.person_id) AS count_value
FROM 
	@FullySpecifiedCdmSchema.procedure_occurrence po
JOIN 
	@FullySpecifiedCdmSchema.observation_period op 
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
  CAST(stratum_1 AS VARCHAR(255)) AS stratum_1,
  CAST(stratum_2 AS varchar(255)) AS stratum_2,
  CAST(NULL AS VARCHAR(255)) AS stratum_3,
  CAST(NULL AS VARCHAR(255)) AS stratum_4,
  CAST(NULL AS VARCHAR(255)) AS stratum_5,
  count_value
INTO 
	#s_tmpach_602
FROM 
	rawData;


-- 603	Number of distinct procedure occurrence concepts per person

--HINT DISTRIBUTE_ON_KEY(count_value)
WITH rawData(count_value) AS
(
SELECT 
	COUNT_BIG(DISTINCT po.procedure_concept_id) AS count_value
FROM 
	@FullySpecifiedCdmSchema.procedure_occurrence po
JOIN 
	@FullySpecifiedCdmSchema.observation_period op 
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
    CAST(stdev(count_value) AS FLOAT) as stdev_value,
    min(count_value) as min_value,
    max(count_value) as max_value,
    count_big(*) as total
  from rawData
),
statsView (count_value, total, rn) as
(
  select count_value, 
  	count_big(*) as total, 
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
select 603 as analysis_id,
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
into #tempResults_603
from priorStats p
CROSS JOIN overallStats o
GROUP BY o.total, o.min_value, o.max_value, o.avg_value, o.stdev_value
;

--HINT DISTRIBUTE_ON_KEY(count_value)
select analysis_id, 
cast(null as varchar(255)) as stratum_1, cast(null as varchar(255)) as stratum_2, cast(null as varchar(255)) as stratum_3, cast(null as varchar(255)) as stratum_4, cast(null as varchar(255)) as stratum_5,
count_value, min_value, max_value, avg_value, stdev_value, median_value, p10_value, p25_value, p75_value, p90_value
into #s_tmpach_dist_603
from #tempResults_603
;

truncate table #tempResults_603;
drop table #tempResults_603;


-- 604	Number of persons with at least one procedure occurrence, by procedure_concept_id by calendar year by gender by age decile

--HINT DISTRIBUTE_ON_KEY(stratum_1)
WITH rawData AS (
SELECT 
	po.procedure_concept_id AS stratum_1,
	YEAR(po.procedure_date) AS stratum_2,
	p.gender_concept_id AS stratum_3,
	FLOOR((YEAR(po.procedure_date) - p.year_of_birth) / 10) AS stratum_4,
	COUNT_BIG(DISTINCT p.person_id) AS count_value
FROM 
	@FullySpecifiedCdmSchema.person p
JOIN 
	@FullySpecifiedCdmSchema.procedure_occurrence po 
ON 
	p.person_id = po.person_id
JOIN 
	@FullySpecifiedCdmSchema.observation_period op 
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
	CAST(stratum_1 AS VARCHAR(255)) AS stratum_1,
	CAST(stratum_2 AS VARCHAR(255)) AS stratum_2,
	CAST(stratum_3 AS VARCHAR(255)) AS stratum_3,
	CAST(stratum_4 AS VARCHAR(255)) AS stratum_4,
	CAST(NULL AS VARCHAR(255)) AS stratum_5,
	count_value
INTO 
	#s_tmpach_604
FROM 
	rawData;


-- 605	Number of procedure occurrence records, by procedure_concept_id by procedure_type_concept_id

--HINT DISTRIBUTE_ON_KEY(stratum_1)
SELECT 
	605 AS analysis_id,
	CAST(po.procedure_CONCEPT_ID AS VARCHAR(255)) AS stratum_1,
	CAST(po.procedure_type_concept_id AS VARCHAR(255)) AS stratum_2,
	CAST(NULL AS VARCHAR(255)) AS stratum_3,
	CAST(NULL AS VARCHAR(255)) AS stratum_4,
	CAST(NULL AS VARCHAR(255)) AS stratum_5,
	COUNT_BIG(po.person_id) AS count_value
INTO 
	#s_tmpach_605
FROM 
	@FullySpecifiedCdmSchema.procedure_occurrence po
JOIN 
	@FullySpecifiedCdmSchema.observation_period op 
ON 
	po.person_id = op.person_id
AND 
	po.procedure_date >= op.observation_period_start_date
AND 
	po.procedure_date <= op.observation_period_end_date
GROUP BY 
	po.procedure_CONCEPT_ID,
	po.procedure_type_concept_id;


-- 606	Distribution of age by procedure_concept_id

--HINT DISTRIBUTE_ON_KEY(subject_id)
SELECT 
	po.procedure_concept_id AS subject_id,
	p.gender_concept_id,
	po.procedure_start_year - p.year_of_birth AS count_value
INTO 
	#rawData_606
FROM 
	@FullySpecifiedCdmSchema.person p
JOIN (
	SELECT 
		po.person_id,
		po.procedure_concept_id,
		MIN(YEAR(po.procedure_date)) AS procedure_start_year
	FROM 
		@FullySpecifiedCdmSchema.procedure_occurrence po
	JOIN 
		@FullySpecifiedCdmSchema.observation_period op 
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

--HINT DISTRIBUTE_ON_KEY(stratum1_id)
with overallStats (stratum1_id, stratum2_id, avg_value, stdev_value, min_value, max_value, total) as
(
  select subject_id as stratum1_id,
    gender_concept_id as stratum2_id,
    CAST(avg(1.0 * count_value) AS FLOAT) as avg_value,
    CAST(stdev(count_value) AS FLOAT) as stdev_value,
    min(count_value) as min_value,
    max(count_value) as max_value,
    count_big(*) as total
  FROM #rawData_606
	group by subject_id, gender_concept_id
),
statsView (stratum1_id, stratum2_id, count_value, total, rn) as
(
  select subject_id as stratum1_id, gender_concept_id as stratum2_id, count_value, count_big(*) as total, row_number() over (partition by subject_id, gender_concept_id order by count_value) as rn
  FROM #rawData_606
  group by subject_id, gender_concept_id, count_value
),
priorStats (stratum1_id, stratum2_id, count_value, total, accumulated) as
(
  select s.stratum1_id, s.stratum2_id, s.count_value, s.total, sum(p.total) as accumulated
  from statsView s
  join statsView p on s.stratum1_id = p.stratum1_id and s.stratum2_id = p.stratum2_id and p.rn <= s.rn
  group by s.stratum1_id, s.stratum2_id, s.count_value, s.total, s.rn
)
select 606 as analysis_id,
  CAST(o.stratum1_id AS VARCHAR(255)) AS stratum1_id,
  CAST(o.stratum2_id AS VARCHAR(255)) AS stratum2_id,
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
into #tempResults_606
from priorStats p
join overallStats o on p.stratum1_id = o.stratum1_id and p.stratum2_id = o.stratum2_id 
GROUP BY o.stratum1_id, o.stratum2_id, o.total, o.min_value, o.max_value, o.avg_value, o.stdev_value
;

--HINT DISTRIBUTE_ON_KEY(stratum_1)
select analysis_id, stratum1_id as stratum_1, stratum2_id as stratum_2, 
cast(null as varchar(255)) as stratum_3, cast(null as varchar(255)) as stratum_4, cast(null as varchar(255)) as stratum_5,
count_value, min_value, max_value, avg_value, stdev_value, median_value, p10_value, p25_value, p75_value, p90_value
into #s_tmpach_dist_606
from #tempResults_606
;

truncate table #tempResults_606;
drop table #tempResults_606;
truncate table #rawData_606;
drop table #rawData_606;


-- 620	Number of procedure occurrence records by condition occurrence start month

--HINT DISTRIBUTE_ON_KEY(stratum_1)
WITH rawData AS (
SELECT 
	YEAR(po.procedure_date) * 100 + MONTH(po.procedure_date) AS stratum_1,
	COUNT_BIG(po.person_id) AS count_value
FROM
	@FullySpecifiedCdmSchema.procedure_occurrence po
JOIN 
	@FullySpecifiedCdmSchema.observation_period op 
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
	CAST(stratum_1 AS VARCHAR(255)) AS stratum_1,
	CAST(NULL AS VARCHAR(255)) AS stratum_2,
	CAST(NULL AS VARCHAR(255)) AS stratum_3,
	CAST(NULL AS VARCHAR(255)) AS stratum_4,
	CAST(NULL AS VARCHAR(255)) AS stratum_5,
	count_value
INTO 
	#s_tmpach_620
FROM 
	rawData;


-- 625	Number of procedure_occurrence records, by procedure_source_concept_id

--HINT DISTRIBUTE_ON_KEY(stratum_1)
SELECT 
	625 AS analysis_id,
	CAST(po.procedure_source_concept_id AS VARCHAR(255)) AS stratum_1,
	CAST(NULL AS VARCHAR(255)) AS stratum_2,
	CAST(NULL AS VARCHAR(255)) AS stratum_3,
	CAST(NULL AS VARCHAR(255)) AS stratum_4,
	CAST(NULL AS VARCHAR(255)) AS stratum_5,
	COUNT_BIG(*) AS count_value
INTO 
	#s_tmpach_625
FROM 
	@FullySpecifiedCdmSchema.procedure_occurrence po
JOIN 
	@FullySpecifiedCdmSchema.observation_period op 
ON 
	po.person_id = op.person_id
AND 
	po.procedure_date >= op.observation_period_start_date
AND 
	po.procedure_date <= op.observation_period_end_date
GROUP BY 
	po.procedure_source_concept_id;


-- 630	Number of procedure_occurrence records inside a valid observation period

SELECT 
	630 AS analysis_id,
	CAST(NULL AS VARCHAR(255)) AS stratum_1,
	CAST(NULL AS VARCHAR(255)) AS stratum_2,
	CAST(NULL AS VARCHAR(255)) AS stratum_3,
	CAST(NULL AS VARCHAR(255)) AS stratum_4,
	CAST(NULL AS VARCHAR(255)) AS stratum_5,
	COUNT_BIG(*) AS count_value
INTO 
	#s_tmpach_630
FROM 
	@FullySpecifiedCdmSchema.procedure_occurrence po
JOIN 
	@FullySpecifiedCdmSchema.observation_period op 
ON 
	po.person_id = op.person_id
AND 
	po.procedure_date >= op.observation_period_start_date
AND 
	po.procedure_date <= op.observation_period_end_date
;


-- 691	Number of persons that have at least x procedures

--HINT DISTRIBUTE_ON_KEY(stratum_1)
SELECT 
	691 AS analysis_id,
	CAST(po.procedure_concept_id AS VARCHAR(255)) AS stratum_1,
	CAST(po.prc_cnt AS VARCHAR(255)) AS stratum_2,
	CAST(NULL AS VARCHAR(255)) AS stratum_3,
	CAST(NULL AS VARCHAR(255)) AS stratum_4,
	CAST(NULL AS VARCHAR(255)) AS stratum_5,
	SUM(COUNT(po.person_id)) OVER (PARTITION BY po.procedure_concept_id ORDER BY po.prc_cnt DESC) AS count_value
INTO 
	#s_tmpach_691
FROM (
	SELECT 
		po.procedure_concept_id,
		COUNT(po.procedure_occurrence_id) AS prc_cnt,
		po.person_id
	FROM 
		@FullySpecifiedCdmSchema.procedure_occurrence po
	JOIN 
		@FullySpecifiedCdmSchema.observation_period op 
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


-- 700	Number of persons with at least one drug occurrence, by drug_concept_id

--HINT DISTRIBUTE_ON_KEY(stratum_1)
SELECT 
	700 AS analysis_id,
	CAST(de.drug_concept_id AS VARCHAR(255)) AS stratum_1,
	CAST(NULL AS VARCHAR(255)) AS stratum_2,
	CAST(NULL AS VARCHAR(255)) AS stratum_3,
	CAST(NULL AS VARCHAR(255)) AS stratum_4,
	CAST(NULL AS VARCHAR(255)) AS stratum_5,
	COUNT_BIG(DISTINCT de.person_id) AS count_value
INTO 
	#s_tmpach_700
FROM 
	@FullySpecifiedCdmSchema.drug_exposure de
JOIN 
	@FullySpecifiedCdmSchema.observation_period op 
ON 
	de.person_id = op.person_id
AND 
	de.drug_exposure_start_date >= op.observation_period_start_date
AND 
	de.drug_exposure_start_date <= op.observation_period_end_date
GROUP BY 
	de.drug_concept_id;


-- 701	Number of drug occurrence records, by drug_concept_id

--HINT DISTRIBUTE_ON_KEY(stratum_1)
SELECT 
	701 AS analysis_id,
	CAST(de.drug_concept_id AS VARCHAR(255)) AS stratum_1,
	CAST(NULL AS VARCHAR(255)) AS stratum_2,
	CAST(NULL AS VARCHAR(255)) AS stratum_3,
	CAST(NULL AS VARCHAR(255)) AS stratum_4,
	CAST(NULL AS VARCHAR(255)) AS stratum_5,
	COUNT_BIG(de.person_id) AS count_value
INTO 
	#s_tmpach_701
FROM 
	@FullySpecifiedCdmSchema.drug_exposure de
JOIN 
	@FullySpecifiedCdmSchema.observation_period op 
ON 
	de.person_id = op.person_id
AND 
	de.drug_exposure_start_date >= op.observation_period_start_date
AND 
	de.drug_exposure_start_date <= op.observation_period_end_date
GROUP BY 
	de.drug_concept_id;


-- 702	Number of persons by drug occurrence start month, by drug_concept_id

--HINT DISTRIBUTE_ON_KEY(stratum_1)
WITH rawData AS (
SELECT 
	de.drug_concept_id AS stratum_1,
	YEAR(de.drug_exposure_start_date) * 100 + MONTH(de.drug_exposure_start_date) AS stratum_2,
	COUNT_BIG(DISTINCT de.person_id) AS count_value
FROM 
	@FullySpecifiedCdmSchema.drug_exposure de
JOIN 
	@FullySpecifiedCdmSchema.observation_period op 
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
	CAST(stratum_1 AS VARCHAR(255)) AS stratum_1,
	CAST(stratum_2 AS VARCHAR(255)) AS stratum_2,
	CAST(NULL AS VARCHAR(255)) AS stratum_3,
	CAST(NULL AS VARCHAR(255)) AS stratum_4,
	CAST(NULL AS VARCHAR(255)) AS stratum_5,
	count_value
INTO 
	#s_tmpach_702
FROM 
	rawData;


-- 703	Number of distinct drug exposure concepts per person

--HINT DISTRIBUTE_ON_KEY(count_value)
WITH rawData(count_value) AS
(
SELECT 
	COUNT_BIG(DISTINCT de.drug_concept_id) AS count_value
FROM 
	@FullySpecifiedCdmSchema.drug_exposure de
JOIN 
	@FullySpecifiedCdmSchema.observation_period op 
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
    CAST(stdev(count_value) AS FLOAT) as stdev_value,
    min(count_value) as min_value,
    max(count_value) as max_value,
    count_big(*) as total
  from rawData
),
statsView (count_value, total, rn) as
(
  select count_value, 
  	count_big(*) as total, 
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
select 703 as analysis_id,
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
into #tempResults_703
from priorStats p
CROSS JOIN overallStats o
GROUP BY o.total, o.min_value, o.max_value, o.avg_value, o.stdev_value
;

--HINT DISTRIBUTE_ON_KEY(count_value)
select analysis_id, 
cast(null as varchar(255)) as stratum_1, cast(null as varchar(255)) as stratum_2, cast(null as varchar(255)) as stratum_3, cast(null as varchar(255)) as stratum_4, cast(null as varchar(255)) as stratum_5,
count_value, min_value, max_value, avg_value, stdev_value, median_value, p10_value, p25_value, p75_value, p90_value
into #s_tmpach_dist_703
from #tempResults_703
;

truncate table #tempResults_703;
drop table #tempResults_703;


-- 704	Number of persons with at least one drug occurrence, by drug_concept_id by calendar year by gender by age decile

--HINT DISTRIBUTE_ON_KEY(stratum_1)
WITH rawData AS (
SELECT 
	de.drug_concept_id AS stratum_1,
	YEAR(de.drug_exposure_start_date) AS stratum_2,
	p.gender_concept_id AS stratum_3,
	FLOOR((YEAR(de.drug_exposure_start_date) - p.year_of_birth) / 10) AS stratum_4,
	COUNT_BIG(DISTINCT p.person_id) AS count_value
FROM 
	@FullySpecifiedCdmSchema.person p
JOIN 
	@FullySpecifiedCdmSchema.drug_exposure de 
ON 
	p.person_id = de.person_id
JOIN 
	@FullySpecifiedCdmSchema.observation_period op 
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
	CAST(stratum_1 AS VARCHAR(255)) AS stratum_1,
	CAST(stratum_2 AS VARCHAR(255)) AS stratum_2,
	CAST(stratum_3 AS VARCHAR(255)) AS stratum_3,
	CAST(stratum_4 AS VARCHAR(255)) AS stratum_4,
	CAST(NULL AS VARCHAR(255)) AS stratum_5,
	count_value
INTO 
	#s_tmpach_704
FROM 
	rawData;


-- 705	Number of drug occurrence records, by drug_concept_id by drug_type_concept_id

--HINT DISTRIBUTE_ON_KEY(stratum_1)
SELECT 
	705 AS analysis_id,
	CAST(de.drug_concept_id AS VARCHAR(255)) AS stratum_1,
	CAST(de.drug_type_concept_id AS VARCHAR(255)) AS stratum_2,
	CAST(NULL AS VARCHAR(255)) AS stratum_3,
	CAST(NULL AS VARCHAR(255)) AS stratum_4,
	CAST(NULL AS VARCHAR(255)) AS stratum_5,
	COUNT_BIG(de.person_id) AS count_value
INTO 
	#s_tmpach_705
FROM 
	@FullySpecifiedCdmSchema.drug_exposure de
JOIN 
	@FullySpecifiedCdmSchema.observation_period op 
ON 
	de.person_id = op.person_id
AND 
	de.drug_exposure_start_date >= op.observation_period_start_date
AND 
	de.drug_exposure_start_date <= op.observation_period_end_date
GROUP BY 
	de.drug_CONCEPT_ID,
	de.drug_type_concept_id;


-- 706	Distribution of age by drug_concept_id

--HINT DISTRIBUTE_ON_KEY(subject_id)
SELECT 
	de.drug_concept_id AS subject_id,
	p.gender_concept_id,
	de.drug_start_year - p.year_of_birth AS count_value
INTO 
	#rawData_706
FROM 
	@FullySpecifiedCdmSchema.person p
JOIN (
	SELECT 
		de.person_id,
		de.drug_concept_id,
		MIN(YEAR(de.drug_exposure_start_date)) AS drug_start_year
	FROM 
		@FullySpecifiedCdmSchema.drug_exposure de
	JOIN 
		@FullySpecifiedCdmSchema.observation_period op 
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

--HINT DISTRIBUTE_ON_KEY(stratum1_id)
with overallStats (stratum1_id, stratum2_id, avg_value, stdev_value, min_value, max_value, total) as
(
  select subject_id as stratum1_id,
    gender_concept_id as stratum2_id,
    CAST(avg(1.0 * count_value) AS FLOAT) as avg_value,
    CAST(stdev(count_value) AS FLOAT) as stdev_value,
    min(count_value) as min_value,
    max(count_value) as max_value,
    count_big(*) as total
  FROM #rawData_706
	group by subject_id, gender_concept_id
),
statsView (stratum1_id, stratum2_id, count_value, total, rn) as
(
  select subject_id as stratum1_id, gender_concept_id as stratum2_id, count_value, count_big(*) as total, row_number() over (partition by subject_id, gender_concept_id order by count_value) as rn
  FROM #rawData_706
  group by subject_id, gender_concept_id, count_value
),
priorStats (stratum1_id, stratum2_id, count_value, total, accumulated) as
(
  select s.stratum1_id, s.stratum2_id, s.count_value, s.total, sum(p.total) as accumulated
  from statsView s
  join statsView p on s.stratum1_id = p.stratum1_id and s.stratum2_id = p.stratum2_id and p.rn <= s.rn
  group by s.stratum1_id, s.stratum2_id, s.count_value, s.total, s.rn
)
select 706 as analysis_id,
  CAST(o.stratum1_id AS VARCHAR(255)) AS stratum1_id,
  CAST(o.stratum2_id AS VARCHAR(255)) AS stratum2_id,
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
into #tempResults_706
from priorStats p
join overallStats o on p.stratum1_id = o.stratum1_id and p.stratum2_id = o.stratum2_id 
GROUP BY o.stratum1_id, o.stratum2_id, o.total, o.min_value, o.max_value, o.avg_value, o.stdev_value
;

--HINT DISTRIBUTE_ON_KEY(stratum_1)
select analysis_id, stratum1_id as stratum_1, stratum2_id as stratum_2, 
cast(null as varchar(255)) as stratum_3, cast(null as varchar(255)) as stratum_4, cast(null as varchar(255)) as stratum_5,
count_value, min_value, max_value, avg_value, stdev_value, median_value, p10_value, p25_value, p75_value, p90_value
into #s_tmpach_dist_706
from #tempResults_706
;


truncate table #rawData_706;
drop table #rawData_706;

truncate table #tempResults_706;
drop table #tempResults_706;


-- 715	Distribution of days_supply by drug_concept_id

--HINT DISTRIBUTE_ON_KEY(stratum_id)
WITH rawData(stratum_id, count_value) AS (
SELECT 
	de.drug_concept_id AS stratum_id,
	de.days_supply AS count_value
FROM 
	@FullySpecifiedCdmSchema.drug_exposure de
JOIN 
	@FullySpecifiedCdmSchema.observation_period op 
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
    CAST(stdev(count_value) AS FLOAT) as stdev_value,
    min(count_value) as min_value,
    max(count_value) as max_value,
    count_big(*) as total
  FROM rawData
	group by stratum_id
),
statsView (stratum_id, count_value, total, rn) as
(
  select stratum_id, count_value, count_big(*) as total, row_number() over (partition by stratum_id order by count_value) as rn
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
select 715 as analysis_id,
  CAST(o.stratum_id AS VARCHAR(255)) AS stratum_id,
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
into #tempResults_715
from priorStats p
join overallStats o on p.stratum_id = o.stratum_id
GROUP BY o.stratum_id, o.total, o.min_value, o.max_value, o.avg_value, o.stdev_value
;

--HINT DISTRIBUTE_ON_KEY(stratum_1)
select analysis_id, stratum_id as stratum_1, 
cast(null as varchar(255)) as stratum_2, cast(null as varchar(255)) as stratum_3, cast(null as varchar(255)) as stratum_4, cast(null as varchar(255)) as stratum_5,
count_value, min_value, max_value, avg_value, stdev_value, median_value, p10_value, p25_value, p75_value, p90_value
into #s_tmpach_dist_715
from #tempResults_715
;

truncate table #tempResults_715;
drop table #tempResults_715;


-- 716	Distribution of refills by drug_concept_id

--HINT DISTRIBUTE_ON_KEY(stratum_id)
WITH rawData(stratum_id, count_value) AS (
SELECT 
	de.drug_concept_id AS stratum_id,
	de.refills AS count_value
FROM 
	@FullySpecifiedCdmSchema.drug_exposure de
JOIN 
	@FullySpecifiedCdmSchema.observation_period op 
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
    CAST(stdev(count_value) AS FLOAT) as stdev_value,
    min(count_value) as min_value,
    max(count_value) as max_value,
    count_big(*) as total
  FROM rawData
	group by stratum_id
),
statsView (stratum_id, count_value, total, rn) as
(
  select stratum_id, count_value, count_big(*) as total, row_number() over (partition by stratum_id order by count_value) as rn
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
select 716 as analysis_id,
  CAST(o.stratum_id AS VARCHAR(255)) AS stratum_id,
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
into #tempResults_716
from priorStats p
join overallStats o on p.stratum_id = o.stratum_id
GROUP BY o.stratum_id, o.total, o.min_value, o.max_value, o.avg_value, o.stdev_value
;

--HINT DISTRIBUTE_ON_KEY(stratum_1)
select analysis_id, stratum_id as stratum_1, 
cast(null as varchar(255)) as stratum_2, cast(null as varchar(255)) as stratum_3, cast(null as varchar(255)) as stratum_4, cast(null as varchar(255)) as stratum_5,
count_value, min_value, max_value, avg_value, stdev_value, median_value, p10_value, p25_value, p75_value, p90_value
into #s_tmpach_dist_716
from #tempResults_716
;

truncate table #tempResults_716;
drop table #tempResults_716;


-- 717	Distribution of quantity by drug_concept_id

--HINT DISTRIBUTE_ON_KEY(stratum_id)
WITH rawData(stratum_id, count_value) AS (
SELECT 
	de.drug_concept_id AS stratum_id,
	CAST(de.quantity AS FLOAT) AS count_value
FROM 
	@FullySpecifiedCdmSchema.drug_exposure de
JOIN 
	@FullySpecifiedCdmSchema.observation_period op 
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
    CAST(stdev(count_value) AS FLOAT) as stdev_value,
    min(count_value) as min_value,
    max(count_value) as max_value,
    count_big(*) as total
  FROM rawData
	group by stratum_id
),
statsView (stratum_id, count_value, total, rn) as
(
  select stratum_id, count_value, count_big(*) as total, row_number() over (order by count_value) as rn
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
select 717 as analysis_id,
  CAST(o.stratum_id AS VARCHAR(255)) AS stratum_id,
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
into #tempResults_717
from priorStats p
join overallStats o on p.stratum_id = o.stratum_id
GROUP BY o.stratum_id, o.total, o.min_value, o.max_value, o.avg_value, o.stdev_value
;

--HINT DISTRIBUTE_ON_KEY(stratum_1)
select analysis_id, stratum_id as stratum_1, 
cast(null as varchar(255)) as stratum_2, cast(null as varchar(255)) as stratum_3, cast(null as varchar(255)) as stratum_4, cast(null as varchar(255)) as stratum_5,
count_value, min_value, max_value, avg_value, stdev_value, median_value, p10_value, p25_value, p75_value, p90_value
into #s_tmpach_dist_717
from #tempResults_717
;

truncate table #tempResults_717;
drop table #tempResults_717;


-- 720	Number of drug exposure records by condition occurrence start month

--HINT DISTRIBUTE_ON_KEY(stratum_1)
WITH rawData AS (
SELECT 
	YEAR(de.drug_exposure_start_date) * 100 + MONTH(de.drug_exposure_start_date) AS stratum_1,
	COUNT_BIG(de.person_id) AS count_value
FROM 
	@FullySpecifiedCdmSchema.drug_exposure de
JOIN 
	@FullySpecifiedCdmSchema.observation_period op 
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
	CAST(stratum_1 AS VARCHAR(255)) AS stratum_1,
	CAST(NULL AS VARCHAR(255)) AS stratum_2,
	CAST(NULL AS VARCHAR(255)) AS stratum_3,
	CAST(NULL AS VARCHAR(255)) AS stratum_4,
	CAST(NULL AS VARCHAR(255)) AS stratum_5,
	count_value
INTO 
	#s_tmpach_720
FROM 
	rawData;


-- 725	Number of drug_exposure records, by drug_source_concept_id

--HINT DISTRIBUTE_ON_KEY(stratum_1)
SELECT 
	725 AS analysis_id,
	CAST(de.drug_source_concept_id AS VARCHAR(255)) AS stratum_1,
	CAST(NULL AS VARCHAR(255)) AS stratum_2,
	CAST(NULL AS VARCHAR(255)) AS stratum_3,
	CAST(NULL AS VARCHAR(255)) AS stratum_4,
	CAST(NULL AS VARCHAR(255)) AS stratum_5,
	COUNT_BIG(*) AS count_value
INTO 
	#s_tmpach_725
FROM 
	@FullySpecifiedCdmSchema.drug_exposure de
JOIN 
	@FullySpecifiedCdmSchema.observation_period op 
ON 
	de.person_id = op.person_id
AND 
	de.drug_exposure_start_date >= op.observation_period_start_date
AND 
	de.drug_exposure_start_date <= op.observation_period_end_date
GROUP BY 
	de.drug_source_concept_id;


-- 791	Number of total persons that have at least x drug exposures

--HINT DISTRIBUTE_ON_KEY(stratum_1)
SELECT 
	791 AS analysis_id,
	CAST(de.drug_concept_id AS VARCHAR(255)) AS stratum_1,
	CAST(de.drg_cnt AS VARCHAR(255)) AS stratum_2,
	CAST(NULL AS VARCHAR(255)) AS stratum_3,
	CAST(NULL AS VARCHAR(255)) AS stratum_4,
	CAST(NULL AS VARCHAR(255)) AS stratum_5,
	SUM(COUNT(de.person_id)) OVER (PARTITION BY de.drug_concept_id ORDER BY de.drg_cnt DESC) AS count_value
INTO 
	#s_tmpach_791
FROM (
	SELECT 
		de.drug_concept_id,
		COUNT(de.drug_exposure_id) AS drg_cnt,
		de.person_id
	FROM 
		@FullySpecifiedCdmSchema.drug_exposure de
	JOIN 
		@FullySpecifiedCdmSchema.observation_period op 
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
	de.drg_cnt
;


-- 800	Number of persons with at least one observation occurrence, by observation_concept_id

--HINT DISTRIBUTE_ON_KEY(stratum_1)
SELECT 
	800 AS analysis_id,
	CAST(o.observation_concept_id AS VARCHAR(255)) AS stratum_1,
	CAST(NULL AS VARCHAR(255)) AS stratum_2,
	CAST(NULL AS VARCHAR(255)) AS stratum_3,
	CAST(NULL AS VARCHAR(255)) AS stratum_4,
	CAST(NULL AS VARCHAR(255)) AS stratum_5,
	COUNT_BIG(DISTINCT o.person_id) AS count_value
INTO 
	#s_tmpach_800
FROM 
	@FullySpecifiedCdmSchema.observation o
JOIN 
	@FullySpecifiedCdmSchema.observation_period op 
ON 
	o.person_id = op.person_id
AND 
	o.observation_date >= op.observation_period_start_date
AND 
	o.observation_date <= op.observation_period_end_date
GROUP BY 
	o.observation_concept_id;


-- 801	Number of observation occurrence records, by observation_concept_id

--HINT DISTRIBUTE_ON_KEY(stratum_1)
SELECT 
	801 AS analysis_id,
	CAST(o.observation_concept_id AS VARCHAR(255)) AS stratum_1,
	CAST(NULL AS VARCHAR(255)) AS stratum_2,
	CAST(NULL AS VARCHAR(255)) AS stratum_3,
	CAST(NULL AS VARCHAR(255)) AS stratum_4,
	CAST(NULL AS VARCHAR(255)) AS stratum_5,
	COUNT_BIG(o.person_id) AS count_value
INTO 
	#s_tmpach_801
FROM 
	@FullySpecifiedCdmSchema.observation o
JOIN 
	@FullySpecifiedCdmSchema.observation_period op 
ON 
	o.person_id = op.person_id
AND 
	o.observation_date >= op.observation_period_start_date
AND 
	o.observation_date <= op.observation_period_end_date
GROUP BY 
	o.observation_concept_id;


-- 802	Number of persons by observation occurrence start month, by observation_concept_id

--HINT DISTRIBUTE_ON_KEY(stratum_1)
WITH rawData AS (
SELECT 
	o.observation_concept_id AS stratum_1,
	YEAR(o.observation_date) * 100 + MONTH(o.observation_date) AS stratum_2,
	COUNT_BIG(DISTINCT o.person_id) AS count_value
FROM 
	@FullySpecifiedCdmSchema.observation o
JOIN 
	@FullySpecifiedCdmSchema.observation_period op 
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
  CAST(stratum_1 AS VARCHAR(255)) AS stratum_1,
  CAST(stratum_2 AS VARCHAR(255)) AS stratum_2,
  CAST(NULL AS VARCHAR(255)) AS stratum_3,
  CAST(NULL AS VARCHAR(255)) AS stratum_4,
  CAST(NULL AS VARCHAR(255)) AS stratum_5,
  count_value
INTO 
	#s_tmpach_802
FROM 
	rawData;


-- 803	Number of distinct observation occurrence concepts per person

--HINT DISTRIBUTE_ON_KEY(count_value)
WITH rawData(count_value) AS
(
SELECT 
	COUNT_BIG(DISTINCT o.observation_concept_id) AS count_value
FROM 
	@FullySpecifiedCdmSchema.observation o
JOIN 
	@FullySpecifiedCdmSchema.observation_period op 
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
    CAST(stdev(count_value) AS FLOAT) as stdev_value,
    min(count_value) as min_value,
    max(count_value) as max_value,
    count_big(*) as total
  from rawData
),
statsView (count_value, total, rn) as
(
  select count_value, 
  	count_big(*) as total, 
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
select 803 as analysis_id,
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
into #tempResults_803
from priorStats p
CROSS JOIN overallStats o
GROUP BY o.total, o.min_value, o.max_value, o.avg_value, o.stdev_value
;

--HINT DISTRIBUTE_ON_KEY(count_value)
select analysis_id, 
cast(null as varchar(255)) as stratum_1, cast(null as varchar(255)) as stratum_2, cast(null as varchar(255)) as stratum_3, cast(null as varchar(255)) as stratum_4, cast(null as varchar(255)) as stratum_5,
count_value, min_value, max_value, avg_value, stdev_value, median_value, p10_value, p25_value, p75_value, p90_value
into #s_tmpach_dist_803
from #tempResults_803
;

truncate table #tempResults_803;

drop table #tempResults_803;


-- 804	Number of persons with at least one observation occurrence, by observation_concept_id by calendar year by gender by age decile

--HINT DISTRIBUTE_ON_KEY(stratum_1)
WITH rawData AS (
SELECT 
	o.observation_concept_id AS stratum_1,
	YEAR(o.observation_date) AS stratum_2,
	p.gender_concept_id AS stratum_3,
	FLOOR((YEAR(o.observation_date) - p.year_of_birth) / 10) AS stratum_4,
	COUNT_BIG(DISTINCT p.person_id) AS count_value
FROM 
	@FullySpecifiedCdmSchema.person p
JOIN 
	@FullySpecifiedCdmSchema.observation o 
ON 
	p.person_id = o.person_id
JOIN 
	@FullySpecifiedCdmSchema.observation_period op 
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
	CAST(stratum_1 AS VARCHAR(255)) AS stratum_1,
	CAST(stratum_2 AS VARCHAR(255)) AS stratum_2,
	CAST(stratum_3 AS VARCHAR(255)) AS stratum_3,
	CAST(stratum_4 AS VARCHAR(255)) AS stratum_4,
	CAST(NULL AS VARCHAR(255)) AS stratum_5,
	count_value
INTO 
	#s_tmpach_804
FROM 
	rawData;


-- 805	Number of observation occurrence records, by observation_concept_id by observation_type_concept_id

--HINT DISTRIBUTE_ON_KEY(stratum_1)
SELECT 
	805 AS analysis_id,
	CAST(o.observation_concept_id AS VARCHAR(255)) AS stratum_1,
	CAST(o.observation_type_concept_id AS VARCHAR(255)) AS stratum_2,
	CAST(NULL AS VARCHAR(255)) AS stratum_3,
	CAST(NULL AS VARCHAR(255)) AS stratum_4,
	CAST(NULL AS VARCHAR(255)) AS stratum_5,
	COUNT_BIG(o.person_id) AS count_value
INTO 
	#s_tmpach_805
FROM 
	@FullySpecifiedCdmSchema.observation o
JOIN 
	@FullySpecifiedCdmSchema.observation_period op 
ON 
	o.person_id = op.person_id
AND 
	o.observation_date >= op.observation_period_start_date
AND 
	o.observation_date <= op.observation_period_end_date
GROUP BY 
	o.observation_concept_id,
	o.observation_type_concept_id;


-- 806	Distribution of age by observation_concept_id

--HINT DISTRIBUTE_ON_KEY(subject_id)
SELECT 
	o.observation_concept_id AS subject_id,
	p.gender_concept_id,
	o.observation_start_year - p.year_of_birth AS count_value
INTO 
	#rawData_806
FROM 
	@FullySpecifiedCdmSchema.person p
JOIN (
	SELECT 
		o.person_id,
		o.observation_concept_id,
		MIN(YEAR(o.observation_date)) AS observation_start_year
	FROM 
		@FullySpecifiedCdmSchema.observation o
	JOIN 
		@FullySpecifiedCdmSchema.observation_period op 
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


--HINT DISTRIBUTE_ON_KEY(stratum1_id)
with overallStats (stratum1_id, stratum2_id, avg_value, stdev_value, min_value, max_value, total) as
(
  select subject_id as stratum1_id,
    gender_concept_id as stratum2_id,
    CAST(avg(1.0 * count_value) AS FLOAT) as avg_value,
    CAST(stdev(count_value) AS FLOAT) as stdev_value,
    min(count_value) as min_value,
    max(count_value) as max_value,
    count_big(*) as total
  FROM #rawData_806
	group by subject_id, gender_concept_id
),
statsView (stratum1_id, stratum2_id, count_value, total, rn) as
(
  select subject_id as stratum1_id, gender_concept_id as stratum2_id, count_value, count_big(*) as total, row_number() over (partition by subject_id, gender_concept_id order by count_value) as rn
  FROM #rawData_806
  group by subject_id, gender_concept_id, count_value
),
priorStats (stratum1_id, stratum2_id, count_value, total, accumulated) as
(
  select s.stratum1_id, s.stratum2_id, s.count_value, s.total, sum(p.total) as accumulated
  from statsView s
  join statsView p on s.stratum1_id = p.stratum1_id and s.stratum2_id = p.stratum2_id and p.rn <= s.rn
  group by s.stratum1_id, s.stratum2_id, s.count_value, s.total, s.rn
)
select 806 as analysis_id,
  CAST(o.stratum1_id AS VARCHAR(255)) AS stratum1_id,
  CAST(o.stratum2_id AS VARCHAR(255)) AS stratum2_id,
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
into #tempResults_806
from priorStats p
join overallStats o on p.stratum1_id = o.stratum1_id and p.stratum2_id = o.stratum2_id 
GROUP BY o.stratum1_id, o.stratum2_id, o.total, o.min_value, o.max_value, o.avg_value, o.stdev_value
;

--HINT DISTRIBUTE_ON_KEY(stratum_1)
select analysis_id, stratum1_id as stratum_1, stratum2_id as stratum_2, 
cast(null as varchar(255)) as stratum_3, cast(null as varchar(255)) as stratum_4, cast(null as varchar(255)) as stratum_5,
count_value, min_value, max_value, avg_value, stdev_value, median_value, p10_value, p25_value, p75_value, p90_value
into #s_tmpach_dist_806
from #tempResults_806
;

truncate table #rawData_806;
drop table #rawData_806;

truncate table #tempResults_806;
drop table #tempResults_806;


-- 807	Number of observation occurrence records, by observation_concept_id and unit_concept_id

--HINT DISTRIBUTE_ON_KEY(stratum_1)
SELECT 
	807 AS analysis_id,
	CAST(o.observation_concept_id AS VARCHAR(255)) AS stratum_1,
	CAST(o.unit_concept_id AS VARCHAR(255)) AS stratum_2,
	CAST(NULL AS VARCHAR(255)) AS stratum_3,
	CAST(NULL AS VARCHAR(255)) AS stratum_4,
	CAST(NULL AS VARCHAR(255)) AS stratum_5,
	COUNT_BIG(o.person_id) AS count_value
INTO 
	#s_tmpach_807
FROM 
	@FullySpecifiedCdmSchema.observation o
JOIN 
	@FullySpecifiedCdmSchema.observation_period op 
ON 
	o.person_id = op.person_id
AND 
	o.observation_date >= op.observation_period_start_date
AND 
	o.observation_date <= op.observation_period_end_date
GROUP BY 
	o.observation_CONCEPT_ID,
	o.unit_concept_id;


-- 814	Number of observation records with no value (numeric, string, or concept)


select 814 as analysis_id,  
	cast(null as varchar(255)) as stratum_1, cast(null as varchar(255)) as stratum_2, cast(null as varchar(255)) as stratum_3, cast(null as varchar(255)) as stratum_4, cast(null as varchar(255)) as stratum_5,
	COUNT_BIG(o1.PERSON_ID) as count_value
into #s_tmpach_814
from
	@FullySpecifiedCdmSchema.observation o1
where o1.value_as_number is null
	and o1.value_as_string is null
	and o1.value_as_concept_id is null
;



--HINT DISTRIBUTE_ON_KEY(stratum1_id)
SELECT 
	o.subject_id AS stratum1_id,
	o.unit_concept_id AS stratum2_id,
	CAST(AVG(1.0 * o.count_value) AS FLOAT) AS avg_value,
	CAST(STDEV(count_value) AS FLOAT) AS stdev_value,
	MIN(o.count_value) AS min_value,
	MAX(o.count_value) AS max_value,
	COUNT_BIG(*) AS total
INTO 
	#overallStats_815
FROM (
	SELECT 
		o.observation_concept_id AS subject_id,
		o.unit_concept_id,
		CAST(o.value_as_number AS FLOAT) AS count_value
	FROM 
		@FullySpecifiedCdmSchema.observation o
	JOIN 
		@FullySpecifiedCdmSchema.observation_period op 
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

--HINT DISTRIBUTE_ON_KEY(stratum1_id)
SELECT 
	o.subject_id AS stratum1_id,
	o.unit_concept_id AS stratum2_id,
	o.count_value,
	COUNT_BIG(*) AS total,
	ROW_NUMBER() OVER (PARTITION BY o.subject_id,o.unit_concept_id ORDER BY o.count_value) AS rn
INTO 
	#statsView_815
FROM (
	SELECT 
		o.observation_concept_id AS subject_id,
		o.unit_concept_id,
		CAST(o.value_as_number AS FLOAT) AS count_value
	FROM 
		@FullySpecifiedCdmSchema.observation o
	JOIN 
		@FullySpecifiedCdmSchema.observation_period op 
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

--HINT DISTRIBUTE_ON_KEY(stratum1_id)
with priorStats (stratum1_id, stratum2_id, count_value, total, accumulated) as
(
  select s.stratum1_id, s.stratum2_id, s.count_value, s.total, sum(p.total) as accumulated
  from #statsView_815 s
  join #statsView_815 p on s.stratum1_id = p.stratum1_id and s.stratum2_id = p.stratum2_id and p.rn <= s.rn
  group by s.stratum1_id, s.stratum2_id, s.count_value, s.total, s.rn
)
select 815 as analysis_id,
  CAST(o.stratum1_id AS VARCHAR(255)) AS stratum1_id,
  CAST(o.stratum2_id AS VARCHAR(255)) AS stratum2_id,
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
into #tempResults_815
from priorStats p
join #overallStats_815 o on p.stratum1_id = o.stratum1_id and p.stratum2_id = o.stratum2_id 
GROUP BY o.stratum1_id, o.stratum2_id, o.total, o.min_value, o.max_value, o.avg_value, o.stdev_value
;

--HINT DISTRIBUTE_ON_KEY(stratum_1)
select analysis_id, stratum1_id as stratum_1, stratum2_id as stratum_2, 
cast(null as varchar(255)) as stratum_3, cast(null as varchar(255)) as stratum_4, cast(null as varchar(255)) as stratum_5,
count_value, min_value, max_value, avg_value, stdev_value, median_value, p10_value, p25_value, p75_value, p90_value
into #s_tmpach_dist_815
from #tempResults_815
;

truncate table #overallStats_815;
drop table #overallStats_815;

truncate table #statsView_815;
drop table #statsView_815;

truncate table #tempResults_815;
drop table #tempResults_815;


-- 820	Number of observation records by condition occurrence start month

--HINT DISTRIBUTE_ON_KEY(stratum_1)
WITH rawData AS (
SELECT 
	YEAR(o.observation_date) * 100 + MONTH(o.observation_date) AS stratum_1,
	COUNT_BIG(o.person_id) AS count_value
FROM 
	@FullySpecifiedCdmSchema.observation o
JOIN 
	@FullySpecifiedCdmSchema.observation_period op 
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
	CAST(stratum_1 AS VARCHAR(255)) AS stratum_1,
	CAST(NULL AS VARCHAR(255)) AS stratum_2,
	CAST(NULL AS VARCHAR(255)) AS stratum_3,
	CAST(NULL AS VARCHAR(255)) AS stratum_4,
	CAST(NULL AS VARCHAR(255)) AS stratum_5,
	count_value
INTO 
	#s_tmpach_820
FROM 
	rawData;



-- 822	Number of observation records, by observation_concept_id and value_as_concept_id, observation_concept_id

--HINT DISTRIBUTE_ON_KEY(stratum_1)
SELECT 
	822 AS analysis_id,
	CAST(o.observation_concept_id AS VARCHAR(255)) AS stratum_1,
	CAST(o.value_as_concept_id AS VARCHAR(255)) AS stratum_2,
	CAST(NULL AS VARCHAR(255)) AS stratum_3,
	CAST(NULL AS VARCHAR(255)) AS stratum_4,
	CAST(NULL AS VARCHAR(255)) AS stratum_5,
	COUNT_BIG(*) AS count_value
INTO 
	#s_tmpach_822
FROM 
	@FullySpecifiedCdmSchema.observation o
JOIN 
	@FullySpecifiedCdmSchema.observation_period op 
ON 
	o.person_id = op.person_id
AND 
	o.observation_date >= op.observation_period_start_date
AND 
	o.observation_date <= op.observation_period_end_date
GROUP BY 
	o.observation_concept_id,
	o.value_as_concept_id;



-- 823	Number of observation records, by observation_concept_id and qualifier_concept_id

--HINT DISTRIBUTE_ON_KEY(stratum_1)
SELECT 
	823 AS analysis_id,
	CAST(o.observation_concept_id AS VARCHAR(255)) AS stratum_1,
	CAST(o.qualifier_concept_id AS VARCHAR(255)) AS stratum_2,
	CAST(NULL AS VARCHAR(255)) AS stratum_3,
	CAST(NULL AS VARCHAR(255)) AS stratum_4,
	CAST(NULL AS VARCHAR(255)) AS stratum_5,
	COUNT_BIG(*) AS count_value
INTO 
	#s_tmpach_823
FROM 
	@FullySpecifiedCdmSchema.observation o
JOIN 
	@FullySpecifiedCdmSchema.observation_period op 
ON 
	o.person_id = op.person_id
AND 
	o.observation_date >= op.observation_period_start_date
AND 
	o.observation_date <= op.observation_period_end_date
GROUP BY 
	o.observation_concept_id,
	o.qualifier_concept_id;


-- 825	Number of observation records, by observation_source_concept_id

--HINT DISTRIBUTE_ON_KEY(stratum_1)
SELECT 
	825 AS analysis_id,
	CAST(o.observation_source_concept_id AS VARCHAR(255)) AS stratum_1,
	CAST(NULL AS VARCHAR(255)) AS stratum_2,
	CAST(NULL AS VARCHAR(255)) AS stratum_3,
	CAST(NULL AS VARCHAR(255)) AS stratum_4,
	CAST(NULL AS VARCHAR(255)) AS stratum_5,
	COUNT_BIG(*) AS count_value
INTO 
	#s_tmpach_825
FROM 
	@FullySpecifiedCdmSchema.observation o
JOIN 
	@FullySpecifiedCdmSchema.observation_period op 
ON 
	o.person_id = op.person_id
AND 
	o.observation_date >= op.observation_period_start_date
AND 
	o.observation_date <= op.observation_period_end_date
GROUP BY 
	o.observation_source_concept_id;


-- 826	Number of observation records, by value_as_concept_id

--HINT DISTRIBUTE_ON_KEY(stratum_1)
SELECT 
	826 AS analysis_id,
	CAST(o.value_as_concept_id AS VARCHAR(255)) AS stratum_1,
	CAST(NULL AS VARCHAR(255)) AS stratum_2,
	CAST(NULL AS VARCHAR(255)) AS stratum_3,
	CAST(NULL AS VARCHAR(255)) AS stratum_4,
	CAST(NULL AS VARCHAR(255)) AS stratum_5,
	COUNT_BIG(*) AS count_value
INTO 
	#s_tmpach_826
FROM 
	@FullySpecifiedCdmSchema.observation o
JOIN 
	@FullySpecifiedCdmSchema.observation_period op 
ON 
	o.person_id = op.person_id
AND 
	o.observation_date >= op.observation_period_start_date
AND 
	o.observation_date <= op.observation_period_end_date
GROUP BY 
	o.value_as_concept_id;


-- 827	Number of observation records, by unit_concept_id

--HINT DISTRIBUTE_ON_KEY(stratum_1)
SELECT 
	827 AS analysis_id,
	CAST(o.unit_concept_id AS VARCHAR(255)) AS stratum_1,
	CAST(NULL AS VARCHAR(255)) AS stratum_2,
	CAST(NULL AS VARCHAR(255)) AS stratum_3,
	CAST(NULL AS VARCHAR(255)) AS stratum_4,
	CAST(NULL AS VARCHAR(255)) AS stratum_5,
	COUNT_BIG(*) AS count_value
INTO 
	#s_tmpach_827
FROM 
	@FullySpecifiedCdmSchema.observation o
JOIN 
	@FullySpecifiedCdmSchema.observation_period op 
ON 
	o.person_id = op.person_id
AND 
	o.observation_date >= op.observation_period_start_date
AND 
	o.observation_date <= op.observation_period_end_date
GROUP BY 
	o.unit_concept_id;
 


-- 891	Number of total persons that have at least x observations

--HINT DISTRIBUTE_ON_KEY(stratum_1)
SELECT 
	891 AS analysis_id,
	CAST(o.observation_concept_id AS VARCHAR(255)) AS stratum_1,
	CAST(o.obs_cnt AS VARCHAR(255)) AS stratum_2,
	CAST(NULL AS VARCHAR(255)) AS stratum_3,
	CAST(NULL AS VARCHAR(255)) AS stratum_4,
	CAST(NULL AS VARCHAR(255)) AS stratum_5,
	SUM(COUNT(o.person_id)) OVER (PARTITION BY o.observation_concept_id ORDER BY o.obs_cnt DESC) AS count_value
INTO 
	#s_tmpach_891
FROM (
	SELECT 
		o.observation_concept_id,
		COUNT(o.observation_id) AS obs_cnt,
		o.person_id
	FROM 
		@FullySpecifiedCdmSchema.observation o
	JOIN 
		@FullySpecifiedCdmSchema.observation_period op 
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
	o.obs_cnt
;


-- 900	Number of persons with at least one drug occurrence, by drug_concept_id

--HINT DISTRIBUTE_ON_KEY(stratum_1)
SELECT 
	900 AS analysis_id,
	CAST(de.drug_concept_id AS VARCHAR(255)) AS stratum_1,
	CAST(NULL AS VARCHAR(255)) AS stratum_2,
	CAST(NULL AS VARCHAR(255)) AS stratum_3,
	CAST(NULL AS VARCHAR(255)) AS stratum_4,
	CAST(NULL AS VARCHAR(255)) AS stratum_5,
	COUNT_BIG(DISTINCT de.person_id) AS count_value
INTO 
	#s_tmpach_900
FROM 
	@FullySpecifiedCdmSchema.drug_era de
JOIN 
	@FullySpecifiedCdmSchema.observation_period op 
ON 
	de.person_id = op.person_id
AND 
	de.drug_era_start_date >= op.observation_period_start_date
AND 
	de.drug_era_start_date <= op.observation_period_end_date
GROUP BY 
	de.drug_concept_id;


-- 901	Number of drug occurrence records, by drug_concept_id

--HINT DISTRIBUTE_ON_KEY(stratum_1)
SELECT 
	901 AS analysis_id,
	CAST(de.drug_concept_id AS VARCHAR(255)) AS stratum_1,
	CAST(NULL AS VARCHAR(255)) AS stratum_2,
	CAST(NULL AS VARCHAR(255)) AS stratum_3,
	CAST(NULL AS VARCHAR(255)) AS stratum_4,
	CAST(NULL AS VARCHAR(255)) AS stratum_5,
	COUNT_BIG(de.person_id) AS count_value
INTO 
	#s_tmpach_901
FROM 
	@FullySpecifiedCdmSchema.drug_era de
JOIN 
	@FullySpecifiedCdmSchema.observation_period op 
ON 
	de.person_id = op.person_id
AND 
	de.drug_era_start_date >= op.observation_period_start_date
AND 
	de.drug_era_start_date <= op.observation_period_end_date
GROUP BY 
	de.drug_concept_id;


-- 902	Number of persons by drug occurrence start month, by drug_concept_id

--HINT DISTRIBUTE_ON_KEY(stratum_1)
WITH rawData AS (
SELECT 
	de.drug_concept_id AS stratum_1,
	YEAR(de.drug_era_start_date) * 100 + MONTH(de.drug_era_start_date) AS stratum_2,
	COUNT_BIG(DISTINCT de.person_id) AS count_value
FROM 
	@FullySpecifiedCdmSchema.drug_era de
JOIN 
	@FullySpecifiedCdmSchema.observation_period op 
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
  CAST(stratum_1 AS VARCHAR(255)) AS stratum_1,
  CAST(stratum_2 AS VARCHAR(255)) AS stratum_2,
  CAST(NULL AS VARCHAR(255)) AS stratum_3,
  CAST(NULL AS VARCHAR(255)) AS stratum_4,
  CAST(NULL AS VARCHAR(255)) AS stratum_5,
  count_value
INTO 
	#s_tmpach_902
FROM 
	rawData;


-- 903	Number of distinct drug era concepts per person

--HINT DISTRIBUTE_ON_KEY(count_value)
WITH rawData(count_value) AS (
SELECT
	COUNT_BIG(DISTINCT de.drug_concept_id) AS count_value
FROM 
	@FullySpecifiedCdmSchema.drug_era de
JOIN 
	@FullySpecifiedCdmSchema.observation_period op 
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
    CAST(stdev(count_value) AS FLOAT) as stdev_value,
    min(count_value) as min_value,
    max(count_value) as max_value,
    count_big(*) as total
  from rawData
),
statsView (count_value, total, rn) as
(
  select count_value, 
  	count_big(*) as total, 
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
select 903 as analysis_id,
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
into #tempResults_903
from priorStats p
CROSS JOIN overallStats o
GROUP BY o.total, o.min_value, o.max_value, o.avg_value, o.stdev_value
;

--HINT DISTRIBUTE_ON_KEY(count_value)
select analysis_id, 
cast(null as varchar(255)) as stratum_1, cast(null as varchar(255)) as stratum_2, cast(null as varchar(255)) as stratum_3, cast(null as varchar(255)) as stratum_4, cast(null as varchar(255)) as stratum_5,
count_value, min_value, max_value, avg_value, stdev_value, median_value, p10_value, p25_value, p75_value, p90_value
into #s_tmpach_dist_903
from #tempResults_903
;

truncate table #tempResults_903;
drop table #tempResults_903;


-- 904	Number of persons with at least one drug occurrence, by drug_concept_id by calendar year by gender by age decile

--HINT DISTRIBUTE_ON_KEY(stratum_1)
WITH rawData AS (
SELECT 
	de.drug_concept_id AS stratum_1,
	YEAR(de.drug_era_start_date) AS stratum_2,
	p.gender_concept_id AS stratum_3,
	FLOOR((YEAR(de.drug_era_start_date) - p.year_of_birth) / 10) AS stratum_4,
	COUNT_BIG(DISTINCT p.person_id) AS count_value
FROM 
	@FullySpecifiedCdmSchema.person p
JOIN 
	@FullySpecifiedCdmSchema.drug_era de 
ON 
	p.person_id = de.person_id
JOIN 
	@FullySpecifiedCdmSchema.observation_period op 
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
	CAST(stratum_1 AS VARCHAR(255)) AS stratum_1,
	CAST(stratum_2 AS VARCHAR(255)) AS stratum_2,
	CAST(stratum_3 AS VARCHAR(255)) AS stratum_3,
	CAST(stratum_4 AS VARCHAR(255)) AS stratum_4,
	CAST(NULL AS VARCHAR(255)) AS stratum_5,
	count_value
INTO 
	#s_tmpach_904
FROM 
	rawData;


-- 906	Distribution of age by drug_concept_id

--HINT DISTRIBUTE_ON_KEY(subject_id)
SELECT 
	de.drug_concept_id AS subject_id,
	p.gender_concept_id,
	de.drug_start_year - p.year_of_birth AS count_value
INTO 
	#rawData_906
FROM 
	@FullySpecifiedCdmSchema.person p
JOIN (
	SELECT 
		de.person_id,
		de.drug_concept_id,
		MIN(YEAR(de.drug_era_start_date)) AS drug_start_year
	FROM 
		@FullySpecifiedCdmSchema.drug_era de
	JOIN 
		@FullySpecifiedCdmSchema.observation_period op 
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

--HINT DISTRIBUTE_ON_KEY(stratum1_id)
with overallStats (stratum1_id, stratum2_id, avg_value, stdev_value, min_value, max_value, total) as
(
  select subject_id as stratum1_id,
    gender_concept_id as stratum2_id,
    CAST(avg(1.0 * count_value) AS FLOAT) as avg_value,
    CAST(stdev(count_value) AS FLOAT) as stdev_value,
    min(count_value) as min_value,
    max(count_value) as max_value,
    count_big(*) as total
  FROM #rawData_906
	group by subject_id, gender_concept_id
),
statsView (stratum1_id, stratum2_id, count_value, total, rn) as
(
  select subject_id as stratum1_id, gender_concept_id as stratum2_id, count_value, count_big(*) as total, row_number() over (partition by subject_id, gender_concept_id order by count_value) as rn
  FROM #rawData_906
  group by subject_id, gender_concept_id, count_value
),
priorStats (stratum1_id, stratum2_id, count_value, total, accumulated) as
(
  select s.stratum1_id, s.stratum2_id, s.count_value, s.total, sum(p.total) as accumulated
  from statsView s
  join statsView p on s.stratum1_id = p.stratum1_id and s.stratum2_id = p.stratum2_id and p.rn <= s.rn
  group by s.stratum1_id, s.stratum2_id, s.count_value, s.total, s.rn
)
select 906 as analysis_id,
  CAST(o.stratum1_id AS VARCHAR(255)) AS stratum1_id,
  CAST(o.stratum2_id AS VARCHAR(255)) AS stratum2_id,
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
into #tempResults_906
from priorStats p
join overallStats o on p.stratum1_id = o.stratum1_id and p.stratum2_id = o.stratum2_id 
GROUP BY o.stratum1_id, o.stratum2_id, o.total, o.min_value, o.max_value, o.avg_value, o.stdev_value
;

--HINT DISTRIBUTE_ON_KEY(stratum_1)
select analysis_id, stratum1_id as stratum_1, stratum2_id as stratum_2, 
cast(null as varchar(255)) as stratum_3, cast(null as varchar(255)) as stratum_4, cast(null as varchar(255)) as stratum_5,
count_value, min_value, max_value, avg_value, stdev_value, median_value, p10_value, p25_value, p75_value, p90_value
into #s_tmpach_dist_906
from #tempResults_906
;


truncate table #rawData_906;
drop table #rawData_906;

truncate table #tempResults_906;
drop table #tempResults_906;


-- 907	Distribution of drug era length, by drug_concept_id

--HINT DISTRIBUTE_ON_KEY(stratum_1)
WITH rawData(stratum1_id, count_value) AS
(
SELECT 
	de.drug_concept_id AS stratum1_id,
	DATEDIFF(dd, de.drug_era_start_date, de.drug_era_end_date) AS count_value
FROM 
	@FullySpecifiedCdmSchema.drug_era de
JOIN 
	@FullySpecifiedCdmSchema.observation_period op 
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
    CAST(stdev(count_value) AS FLOAT) as stdev_value,
    min(count_value) as min_value,
    max(count_value) as max_value,
    count_big(*) as total
  from rawData
  group by stratum1_id
),
statsView (stratum1_id, count_value, total, rn) as
(
  select stratum1_id, 
		count_value, 
  	count_big(*) as total, 
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
select 907 as analysis_id,
  CAST(p.stratum1_id AS VARCHAR(255)) as stratum_1,
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
into #tempResults_907
from priorStats p
join overallStats o on p.stratum1_id = o.stratum1_id
GROUP BY p.stratum1_id, o.total, o.min_value, o.max_value, o.avg_value, o.stdev_value
;

--HINT DISTRIBUTE_ON_KEY(stratum_1)
select analysis_id, stratum_1, 
cast(null as varchar(255)) as stratum_2, cast(null as varchar(255)) as stratum_3, cast(null as varchar(255)) as stratum_4, cast(null as varchar(255)) as stratum_5,
count_value, min_value, max_value, avg_value, stdev_value, median_value, p10_value, p25_value, p75_value, p90_value
into #s_tmpach_dist_907
from #tempResults_907
;

truncate table #tempResults_907;
drop table #tempResults_907;


-- 920	Number of drug era records by drug era start month

--HINT DISTRIBUTE_ON_KEY(stratum_1)
WITH rawData AS (
SELECT 
	YEAR(de.drug_era_start_date) * 100 + MONTH(de.drug_era_start_date) AS stratum_1,
	COUNT_BIG(de.person_id) AS count_value
FROM 
	@FullySpecifiedCdmSchema.drug_era de
JOIN 
	@FullySpecifiedCdmSchema.observation_period op 
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
	CAST(stratum_1 AS VARCHAR(255)) AS stratum_1,
	CAST(NULL AS VARCHAR(255)) AS stratum_2,
	CAST(NULL AS VARCHAR(255)) AS stratum_3,
	CAST(NULL AS VARCHAR(255)) AS stratum_4,
	CAST(NULL AS VARCHAR(255)) AS stratum_5,
	count_value
INTO 
	#s_tmpach_920
FROM 
	rawData;


-- 1000	Number of persons with at least one condition occurrence, by condition_concept_id

--HINT DISTRIBUTE_ON_KEY(stratum_1)
SELECT 
	1000 AS analysis_id,
	CAST(ce.condition_concept_id AS VARCHAR(255)) AS stratum_1,
	CAST(NULL AS VARCHAR(255)) AS stratum_2,
	CAST(NULL AS VARCHAR(255)) AS stratum_3,
	CAST(NULL AS VARCHAR(255)) AS stratum_4,
	CAST(NULL AS VARCHAR(255)) AS stratum_5,
	COUNT_BIG(DISTINCT ce.person_id) AS count_value
INTO 
	#s_tmpach_1000
FROM 
	@FullySpecifiedCdmSchema.condition_era ce
JOIN 
	@FullySpecifiedCdmSchema.observation_period op 
ON 
	ce.person_id = op.person_id
AND 
	ce.condition_era_start_date >= op.observation_period_start_date
AND 
	ce.condition_era_start_date <= op.observation_period_end_date	
GROUP BY 
	ce.condition_concept_id;


-- 1001	Number of condition occurrence records, by condition_concept_id

--HINT DISTRIBUTE_ON_KEY(stratum_1)
SELECT 
	1001 AS analysis_id,
	CAST(ce.condition_concept_id AS VARCHAR(255)) AS stratum_1,
	CAST(NULL AS VARCHAR(255)) AS stratum_2,
	CAST(NULL AS VARCHAR(255)) AS stratum_3,
	CAST(NULL AS VARCHAR(255)) AS stratum_4,
	CAST(NULL AS VARCHAR(255)) AS stratum_5,
	COUNT_BIG(ce.person_id) AS count_value
INTO 
	#s_tmpach_1001
FROM 
	@FullySpecifiedCdmSchema.condition_era ce
JOIN 
	@FullySpecifiedCdmSchema.observation_period op 
ON 
	ce.person_id = op.person_id
AND 
	ce.condition_era_start_date >= op.observation_period_start_date
AND 
	ce.condition_era_start_date <= op.observation_period_end_date	
GROUP BY 
	ce.condition_concept_id;


-- 1002	Number of persons by condition occurrence start month, by condition_concept_id

--HINT DISTRIBUTE_ON_KEY(stratum_1)
WITH rawData AS (
SELECT 
	ce.condition_concept_id AS stratum_1,
	YEAR(ce.condition_era_start_date) * 100 + MONTH(ce.condition_era_start_date) AS stratum_2,
	COUNT_BIG(DISTINCT ce.person_id) AS count_value
FROM 
	@FullySpecifiedCdmSchema.condition_era ce
JOIN 
	@FullySpecifiedCdmSchema.observation_period op 
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
  CAST(stratum_1 AS VARCHAR(255)) AS stratum_1,
  CAST(stratum_2 AS VARCHAR(255)) AS stratum_2,
  CAST(NULL AS VARCHAR(255)) AS stratum_3,
  CAST(NULL AS VARCHAR(255)) AS stratum_4,
  CAST(NULL AS VARCHAR(255)) AS stratum_5,
  count_value
INTO 
	#s_tmpach_1002
FROM 
	rawData;


-- 1003	Number of distinct condition era concepts per person

--HINT DISTRIBUTE_ON_KEY(count_value)
WITH rawData(count_value) AS
(
SELECT 
	COUNT_BIG(DISTINCT ce.condition_concept_id) AS count_value
FROM 
	@FullySpecifiedCdmSchema.condition_era ce
JOIN 
	@FullySpecifiedCdmSchema.observation_period op 
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
    CAST(stdev(count_value) AS FLOAT) as stdev_value,
    min(count_value) as min_value,
    max(count_value) as max_value,
    count_big(*) as total
  from rawData
),
statsView (count_value, total, rn) as
(
  select count_value, 
  	count_big(*) as total, 
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
select 1003 as analysis_id,
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
into #tempResults_1003
from priorStats p
CROSS JOIN overallStats o
GROUP BY o.total, o.min_value, o.max_value, o.avg_value, o.stdev_value
;

--HINT DISTRIBUTE_ON_KEY(count_value)
select analysis_id, 
cast(null as varchar(255)) as stratum_1, cast(null as varchar(255)) as stratum_2, cast(null as varchar(255)) as stratum_3, cast(null as varchar(255)) as stratum_4, cast(null as varchar(255)) as stratum_5,
count_value, min_value, max_value, avg_value, stdev_value, median_value, p10_value, p25_value, p75_value, p90_value
into #s_tmpach_dist_1003
from #tempResults_1003
;

truncate table #tempResults_1003;
drop table #tempResults_1003;


-- 1004	Number of persons with at least one condition occurrence, by condition_concept_id by calendar year by gender by age decile

--HINT DISTRIBUTE_ON_KEY(stratum_1)
WITH rawData AS (
SELECT 
	ce.condition_concept_id AS stratum_1,
	YEAR(ce.condition_era_start_date) AS stratum_2,
	p.gender_concept_id AS stratum_3,
	FLOOR((YEAR(ce.condition_era_start_date) - p.year_of_birth) / 10) AS stratum_4,
	COUNT_BIG(DISTINCT p.person_id) AS count_value
FROM 
	@FullySpecifiedCdmSchema.person p
JOIN 
	@FullySpecifiedCdmSchema.condition_era ce 
ON 
	p.person_id = ce.person_id
JOIN 
	@FullySpecifiedCdmSchema.observation_period op 
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
	CAST(stratum_1 AS VARCHAR(255)) AS stratum_1,
	CAST(stratum_2 AS VARCHAR(255)) AS stratum_2,
	CAST(stratum_3 AS VARCHAR(255)) AS stratum_3,
	CAST(stratum_4 AS VARCHAR(255)) AS stratum_4,
	CAST(NULL AS VARCHAR(255)) AS stratum_5,
	count_value
INTO 
	#s_tmpach_1004
FROM 
	rawData;


-- 1006	Distribution of age by condition_concept_id

--HINT DISTRIBUTE_ON_KEY(subject_id)
SELECT 
	ce.condition_concept_id AS subject_id,
	p.gender_concept_id,
	ce.condition_start_year - p.year_of_birth AS count_value
INTO 
	#rawData_1006
FROM 
	@FullySpecifiedCdmSchema.person p
JOIN (
	SELECT 
		ce.person_id,
		ce.condition_concept_id,
		MIN(YEAR(ce.condition_era_start_date)) AS condition_start_year
	FROM 
		@FullySpecifiedCdmSchema.condition_era ce
	JOIN 
		@FullySpecifiedCdmSchema.observation_period op 
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

--HINT DISTRIBUTE_ON_KEY(stratum1_id)
with overallStats (stratum1_id, stratum2_id, avg_value, stdev_value, min_value, max_value, total) as
(
  select subject_id as stratum1_id,
    gender_concept_id as stratum2_id,
    CAST(avg(1.0 * count_value) AS FLOAT) as avg_value,
    CAST(stdev(count_value) AS FLOAT) as stdev_value,
    min(count_value) as min_value,
    max(count_value) as max_value,
    count_big(*) as total
  FROM #rawData_1006
	group by subject_id, gender_concept_id
),
statsView (stratum1_id, stratum2_id, count_value, total, rn) as
(
  select subject_id as stratum1_id, gender_concept_id as stratum2_id, count_value, count_big(*) as total, row_number() over (partition by subject_id, gender_concept_id order by count_value) as rn
  FROM #rawData_1006
  group by subject_id, gender_concept_id, count_value
),
priorStats (stratum1_id, stratum2_id, count_value, total, accumulated) as
(
  select s.stratum1_id, s.stratum2_id, s.count_value, s.total, sum(p.total) as accumulated
  from statsView s
  join statsView p on s.stratum1_id = p.stratum1_id and s.stratum2_id = p.stratum2_id and p.rn <= s.rn
  group by s.stratum1_id, s.stratum2_id, s.count_value, s.total, s.rn
)
select 1006 as analysis_id,
  CAST(o.stratum1_id AS VARCHAR(255)) AS stratum1_id,
  CAST(o.stratum2_id AS VARCHAR(255)) AS stratum2_id,
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
into #tempResults_1006
from priorStats p
join overallStats o on p.stratum1_id = o.stratum1_id and p.stratum2_id = o.stratum2_id 
GROUP BY o.stratum1_id, o.stratum2_id, o.total, o.min_value, o.max_value, o.avg_value, o.stdev_value
;

--HINT DISTRIBUTE_ON_KEY(stratum_1)
select analysis_id, stratum1_id as stratum_1, stratum2_id as stratum_2, 
cast(null as varchar(255)) as stratum_3, cast(null as varchar(255)) as stratum_4, cast(null as varchar(255)) as stratum_5,
count_value, min_value, max_value, avg_value, stdev_value, median_value, p10_value, p25_value, p75_value, p90_value
into #s_tmpach_dist_1006
from #tempResults_1006
;

truncate table #rawData_1006;
drop table #rawData_1006;

truncate table #tempResults_1006;
drop table #tempResults_1006;


-- 1007	Distribution of condition era length, by condition_concept_id

--HINT DISTRIBUTE_ON_KEY(stratum_1)
WITH rawData(stratum1_id, count_value) AS
(
SELECT 
	ce.condition_concept_id AS stratum1_id,
	DATEDIFF(dd, ce.condition_era_start_date, ce.condition_era_end_date) AS count_value
FROM 
	@FullySpecifiedCdmSchema.condition_era ce
JOIN 
	@FullySpecifiedCdmSchema.observation_period op 
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
    CAST(stdev(count_value) AS FLOAT) as stdev_value,
    min(count_value) as min_value,
    max(count_value) as max_value,
    count_big(*) as total
  from rawData
  group by stratum1_id
),
statsView (stratum1_id, count_value, total, rn) as
(
  select stratum1_id, 
		count_value, 
  	count_big(*) as total, 
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
select 1007 as analysis_id,
  CAST(p.stratum1_id AS VARCHAR(255)) as stratum_1,
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
into #tempResults_1007
from priorStats p
join overallStats o on p.stratum1_id = o.stratum1_id
GROUP BY p.stratum1_id, o.total, o.min_value, o.max_value, o.avg_value, o.stdev_value
;

--HINT DISTRIBUTE_ON_KEY(stratum_1)
select analysis_id, stratum_1, 
cast(null as varchar(255)) as stratum_2, cast(null as varchar(255)) as stratum_3, cast(null as varchar(255)) as stratum_4, cast(null as varchar(255)) as stratum_5,
count_value, min_value, max_value, avg_value, stdev_value, median_value, p10_value, p25_value, p75_value, p90_value
into #s_tmpach_dist_1007
from #tempResults_1007
;

truncate table #tempResults_1007;
drop table #tempResults_1007;


-- 1020	Number of condition era records by condition era start month

--HINT DISTRIBUTE_ON_KEY(stratum_1)
WITH rawData AS (
SELECT 
	YEAR(ce.condition_era_start_date) * 100 + MONTH(ce.condition_era_start_date) AS stratum_1,
	COUNT_BIG(ce.person_id) AS count_value
FROM 
	@FullySpecifiedCdmSchema.condition_era ce
JOIN 
	@FullySpecifiedCdmSchema.observation_period op 
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
	CAST(stratum_1 AS VARCHAR(255)) AS stratum_1,
	CAST(NULL AS VARCHAR(255)) AS stratum_2,
	CAST(NULL AS VARCHAR(255)) AS stratum_3,
	CAST(NULL AS VARCHAR(255)) AS stratum_4,
	CAST(NULL AS VARCHAR(255)) AS stratum_5,
	count_value
INTO 
	#s_tmpach_1020
FROM 
	rawData;


-- 1100	Number of persons by location 3-digit zip

--HINT DISTRIBUTE_ON_KEY(stratum_1)
WITH rawData AS (
  select
    left(l1.zip,3) as stratum_1,
    COUNT_BIG(distinct person_id) as count_value
  from @FullySpecifiedCdmSchema.person p1
    inner join @FullySpecifiedCdmSchema.location l1
    on p1.location_id = l1.location_id
  where p1.location_id is not null
    and l1.zip is not null
  group by left(l1.zip,3)
)
SELECT
  1100 as analysis_id,
  CAST(stratum_1 AS VARCHAR(255)) as stratum_1,
  cast(null as varchar(255)) as stratum_2,
  cast(null as varchar(255)) as stratum_3,
  cast(null as varchar(255)) as stratum_4,
  cast(null as varchar(255)) as stratum_5,
  count_value
into #s_tmpach_1100
FROM rawData;


-- 1101	Number of persons by location state

--HINT DISTRIBUTE_ON_KEY(stratum_1)
select 1101 as analysis_id,  
	CAST(l1.state AS VARCHAR(255)) as stratum_1, 
	cast(null as varchar(255)) as stratum_2, cast(null as varchar(255)) as stratum_3, cast(null as varchar(255)) as stratum_4, cast(null as varchar(255)) as stratum_5,
	COUNT_BIG(distinct person_id) as count_value
into #s_tmpach_1101
from @FullySpecifiedCdmSchema.person p1
	inner join @FullySpecifiedCdmSchema.location l1
	on p1.location_id = l1.location_id
where p1.location_id is not null
	and l1.state is not null
group by l1.state;


-- 1102	Number of care sites by location 3-digit zip

--HINT DISTRIBUTE_ON_KEY(stratum_1)
WITH rawData AS (
  select
    left(l1.zip,3) as stratum_1,
    COUNT_BIG(distinct care_site_id) as count_value
  from @FullySpecifiedCdmSchema.care_site cs1
    inner join @FullySpecifiedCdmSchema.location l1
    on cs1.location_id = l1.location_id
  where cs1.location_id is not null
    and l1.zip is not null
  group by left(l1.zip,3)
)
SELECT
  1102 as analysis_id,
  CAST(stratum_1 AS VARCHAR(255)) as stratum_1,
  cast(null as varchar(255)) as stratum_2,
  cast(null as varchar(255)) as stratum_3,
  cast(null as varchar(255)) as stratum_4,
  cast(null as varchar(255)) as stratum_5,
  count_value
into #s_tmpach_1102
FROM rawData;


-- 1103	Number of care sites by location state

--HINT DISTRIBUTE_ON_KEY(stratum_1)
select 1103 as analysis_id,  
	CAST(l1.state AS VARCHAR(255)) as stratum_1, 
	cast(null as varchar(255)) as stratum_2, cast(null as varchar(255)) as stratum_3, cast(null as varchar(255)) as stratum_4, cast(null as varchar(255)) as stratum_5,
	COUNT_BIG(distinct care_site_id) as count_value
into #s_tmpach_1103
from @FullySpecifiedCdmSchema.care_site cs1
	inner join @FullySpecifiedCdmSchema.location l1
	on cs1.location_id = l1.location_id
where cs1.location_id is not null
	and l1.state is not null
group by l1.state;


-- 1200	Number of persons by place of service

--HINT DISTRIBUTE_ON_KEY(stratum_1)
select 1200 as analysis_id,  
	CAST(cs1.place_of_service_concept_id AS VARCHAR(255)) as stratum_1, 
	cast(null as varchar(255)) as stratum_2, cast(null as varchar(255)) as stratum_3, cast(null as varchar(255)) as stratum_4, cast(null as varchar(255)) as stratum_5,
	COUNT_BIG(person_id) as count_value
into #s_tmpach_1200
from @FullySpecifiedCdmSchema.person p1
	inner join @FullySpecifiedCdmSchema.care_site cs1
	on p1.care_site_id = cs1.care_site_id
where p1.care_site_id is not null
	and cs1.place_of_service_concept_id is not null
group by cs1.place_of_service_concept_id;


-- 1201	Number of visits by place of service

--HINT DISTRIBUTE_ON_KEY(stratum_1)
select 1201 as analysis_id,  
	CAST(cs1.place_of_service_concept_id AS VARCHAR(255)) as stratum_1, 
	cast(null as varchar(255)) as stratum_2, cast(null as varchar(255)) as stratum_3, cast(null as varchar(255)) as stratum_4, cast(null as varchar(255)) as stratum_5,
	COUNT_BIG(visit_occurrence_id) as count_value
into #s_tmpach_1201
from @FullySpecifiedCdmSchema.visit_occurrence vo1
	inner join @FullySpecifiedCdmSchema.care_site cs1
	on vo1.care_site_id = cs1.care_site_id
where vo1.care_site_id is not null
	and cs1.place_of_service_concept_id is not null
group by cs1.place_of_service_concept_id;


-- 1202	Number of care sites by place of service

--HINT DISTRIBUTE_ON_KEY(stratum_1)
select 1202 as analysis_id,  
	CAST(cs1.place_of_service_concept_id AS VARCHAR(255)) as stratum_1,
	cast(null as varchar(255)) as stratum_2, cast(null as varchar(255)) as stratum_3, cast(null as varchar(255)) as stratum_4, cast(null as varchar(255)) as stratum_5,
	COUNT_BIG(care_site_id) as count_value
into #s_tmpach_1202
from @FullySpecifiedCdmSchema.care_site cs1
where cs1.place_of_service_concept_id is not null
group by cs1.place_of_service_concept_id;


-- 1203	Number of visits by place of service discharge type

--HINT DISTRIBUTE_ON_KEY(stratum_1)
SELECT 
	1203 AS analysis_id,
	CAST(vo.discharge_to_concept_id AS VARCHAR(255)) AS stratum_1,
	CAST(NULL AS VARCHAR(255)) AS stratum_2,
	CAST(NULL AS VARCHAR(255)) AS stratum_3,
	CAST(NULL AS VARCHAR(255)) AS stratum_4,
	CAST(NULL AS VARCHAR(255)) AS stratum_5,
	COUNT_BIG(*) AS count_value
INTO 
	#s_tmpach_1203
FROM 
	@FullySpecifiedCdmSchema.visit_occurrence vo
JOIN 
	@FullySpecifiedCdmSchema.observation_period op 
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



-- 1300	Number of persons with at least one visit detail, by visit_detail_concept_id
-- restricted to visits overlapping with observation period

--HINT DISTRIBUTE_ON_KEY(stratum_1)
SELECT 
	1300 AS analysis_id,
	CAST(vd.visit_detail_concept_id AS VARCHAR(255)) AS stratum_1,
	CAST(NULL AS VARCHAR(255)) AS stratum_2,
	CAST(NULL AS VARCHAR(255)) AS stratum_3,
	CAST(NULL AS VARCHAR(255)) AS stratum_4,
	CAST(NULL AS VARCHAR(255)) AS stratum_5,
	COUNT_BIG(DISTINCT vd.person_id) AS count_value
INTO 
	#s_tmpach_1300
FROM
	@FullySpecifiedCdmSchema.visit_detail vd
JOIN 
    @FullySpecifiedCdmSchema.observation_period op 
ON 
	vd.person_id = op.person_id
AND	
	vd.visit_detail_start_date >= op.observation_period_start_date  
AND 
	vd.visit_detail_start_date <= op.observation_period_end_date
GROUP BY 
	vd.visit_detail_concept_id
;


-- 1301	Number of visit detail records, by visit_detail_concept_id
-- restricted to visits overlapping with observation period

--HINT DISTRIBUTE_ON_KEY(stratum_1)
SELECT 
	1301 AS analysis_id,
	CAST(vd.visit_detail_concept_id AS VARCHAR(255)) AS stratum_1,
	CAST(NULL AS VARCHAR(255)) AS stratum_2,
	CAST(NULL AS VARCHAR(255)) AS stratum_3,
	CAST(NULL AS VARCHAR(255)) AS stratum_4,
	CAST(NULL AS VARCHAR(255)) AS stratum_5,
	COUNT_BIG(vd.person_id) AS count_value
INTO 
	#s_tmpach_1301
FROM 
	@FullySpecifiedCdmSchema.visit_detail vd
JOIN 
	@FullySpecifiedCdmSchema.observation_period op 
ON 
	vd.person_id = op.person_id
AND	
	vd.visit_detail_start_date >= op.observation_period_start_date  
AND 
	vd.visit_detail_start_date <= op.observation_period_end_date
GROUP BY 
	vd.visit_detail_concept_id;


-- 1302	Number of persons by visit detail start month, by visit_detail_concept_id

--HINT DISTRIBUTE_ON_KEY(stratum_1)
WITH rawData AS (
SELECT
	vd.visit_detail_concept_id AS stratum_1,
	YEAR(vd.visit_detail_start_date)*100 + MONTH(vd.visit_detail_start_date) AS stratum_2,
	COUNT_BIG(DISTINCT vd.person_id) AS count_value
FROM
	@FullySpecifiedCdmSchema.visit_detail vd 
JOIN 
	@FullySpecifiedCdmSchema.observation_period op 
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
	CAST(stratum_1 AS VARCHAR(255)) AS stratum_1,
	CAST(stratum_2 AS VARCHAR(255)) AS stratum_2,
	CAST(NULL AS VARCHAR(255)) AS stratum_3,
	CAST(NULL AS VARCHAR(255)) AS stratum_4,
	CAST(NULL AS VARCHAR(255)) AS stratum_5,
	count_value
INTO 
	#s_tmpach_1302
FROM 
	rawData;


-- 1303	Number of distinct visit detail concepts per person

--HINT DISTRIBUTE_ON_KEY(count_value)
with rawData(person_id, count_value) as
(
SELECT 
	vd.person_id,
	COUNT_BIG(DISTINCT vd.visit_detail_concept_id) AS count_value
FROM 
	@FullySpecifiedCdmSchema.visit_detail vd
JOIN 
	@FullySpecifiedCdmSchema.observation_period op 
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
	CAST(stdev(count_value) AS FLOAT) AS stdev_value,
	MIN(count_value) AS min_value,
	MAX(count_value) AS max_value,
	COUNT_BIG(*) AS total
FROM 
	rawData
),
statsView (count_value, total, rn) AS
(
SELECT 
	count_value,
	COUNT_BIG(*) AS total,
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
INTO 
	#tempResults_1303
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

--HINT DISTRIBUTE_ON_KEY(count_value)
SELECT 
	analysis_id,
	CAST(NULL AS VARCHAR(255)) AS stratum_1,
	CAST(NULL AS VARCHAR(255)) AS stratum_2,
	CAST(NULL AS VARCHAR(255)) AS stratum_3,
	CAST(NULL AS VARCHAR(255)) AS stratum_4,
	CAST(NULL AS VARCHAR(255)) AS stratum_5,
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
INTO 
	#s_tmpach_dist_1303
FROM 
	#tempResults_1303
;

TRUNCATE TABLE #tempResults_1303;
DROP TABLE #tempResults_1303;


-- 1304	Number of persons with at least one visit detail, by visit_detail_concept_id by calendar year by gender by age decile

--HINT DISTRIBUTE_ON_KEY(stratum_1)
WITH rawData AS (
SELECT 
	vd.visit_detail_concept_id AS stratum_1,
	YEAR(vd.visit_detail_start_date) AS stratum_2,
	p.gender_concept_id AS stratum_3,
	FLOOR((YEAR(vd.visit_detail_start_date) - p.year_of_birth) / 10) AS stratum_4,
	COUNT_BIG(DISTINCT p.person_id) AS count_value
FROM 
	@FullySpecifiedCdmSchema.person p
JOIN 
	@FullySpecifiedCdmSchema.visit_detail vd 
ON 
	p.person_id = vd.person_id 
JOIN
	@FullySpecifiedCdmSchema.observation_period op 
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
	CAST(stratum_1 AS VARCHAR(255)) AS stratum_1,
	CAST(stratum_2 AS VARCHAR(255)) AS stratum_2,
	CAST(stratum_3 AS VARCHAR(255)) AS stratum_3,
	CAST(stratum_4 AS VARCHAR(255)) AS stratum_4,
	CAST(NULL AS VARCHAR(255)) AS stratum_5,
	count_value
INTO 
	#s_tmpach_1304
FROM 
	rawData;


-- 1306	Distribution of age by visit_detail_concept_id

--HINT DISTRIBUTE_ON_KEY(stratum1_id)
WITH rawData (stratum1_id, stratum2_id, count_value) AS
(
SELECT 
	vd.visit_detail_concept_id AS stratum1_id,
	p.gender_concept_id AS stratum2_id,
	vd.visit_detail_start_year - p.year_of_birth AS count_value
FROM 
	@FullySpecifiedCdmSchema.person p
JOIN (
	SELECT 
		vd.person_id,
		vd.visit_detail_concept_id,
		MIN(YEAR(vd.visit_detail_start_date)) AS visit_detail_start_year
	FROM 
		@FullySpecifiedCdmSchema.visit_detail vd
	JOIN 
		@FullySpecifiedCdmSchema.observation_period op 
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
	CAST(STDEV(count_value) AS FLOAT) AS stdev_value,
	MIN(count_value) AS min_value,
	MAX(count_value) AS max_value,
	COUNT_BIG(*) AS total
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
	COUNT_BIG(*) AS total,
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
	CAST(o.stratum1_id AS VARCHAR(255)) AS stratum1_id,
	CAST(o.stratum2_id AS VARCHAR(255)) AS stratum2_id,
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
INTO 
	#tempResults_1306
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

--HINT DISTRIBUTE_ON_KEY(stratum_1)
SELECT 
	analysis_id,
	stratum1_id AS stratum_1,
	stratum2_id AS stratum_2,
	CAST(NULL AS VARCHAR(255)) AS stratum_3,
	CAST(NULL AS VARCHAR(255)) AS stratum_4,
	CAST(NULL AS VARCHAR(255)) AS stratum_5,
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
INTO 
	#s_tmpach_dist_1306
FROM 
	#tempResults_1306
;

TRUNCATE TABLE #tempResults_1306;
DROP TABLE #tempResults_1306;


-- 1312	Number of persons with at least one visit detail by calendar year by gender by age decile

--HINT DISTRIBUTE_ON_KEY(stratum_1)
WITH rawData AS (
SELECT 
	YEAR(vd.visit_detail_start_date) AS stratum_1,
	p.gender_concept_id AS stratum_2,
	FLOOR((YEAR(vd.visit_detail_start_date) - p.year_of_birth) / 10) AS stratum_3,
	COUNT_BIG(DISTINCT vd.person_id) AS count_value
FROM 
	@FullySpecifiedCdmSchema.person p
JOIN 
	@FullySpecifiedCdmSchema.visit_detail vd 
ON 
	vd.person_id = p.person_id
JOIN 
	@FullySpecifiedCdmSchema.observation_period op 
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
	CAST(stratum_1 AS VARCHAR(255)) AS stratum_1,
	CAST(stratum_2 AS varchar(255)) AS stratum_2,
	CAST(stratum_3 AS varchar(255)) AS stratum_3,
	CAST(NULL AS VARCHAR(255)) AS stratum_4,
	CAST(NULL AS VARCHAR(255)) AS stratum_5,
	count_value
INTO 
	#s_tmpach_1312
FROM 
	rawData;


-- 1313	Distribution of length of stay by visit_detail_concept_id
-- restrict to visits inside observation period

--HINT DISTRIBUTE_ON_KEY(stratum_id) 
WITH rawData(stratum_id, count_value) AS
(
SELECT 
	vd.visit_detail_concept_id AS stratum_id,
	DATEDIFF(dd, vd.visit_detail_start_date, vd.visit_detail_END_date) AS count_value
FROM 
	@FullySpecifiedCdmSchema.visit_detail vd
JOIN 
	@FullySpecifiedCdmSchema.observation_period op 
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
	CAST(STDEV(count_value) AS FLOAT) AS stdev_value,
	MIN(count_value) AS min_value,
	MAX(count_value) AS max_value,
	COUNT_BIG(*) AS total
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
	COUNT_BIG(*) AS total,
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
select 
	1313 AS analysis_id,
	CAST(o.stratum_id AS VARCHAR(255)) AS stratum_id,
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
INTO 
	#tempResults_1313
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

--HINT DISTRIBUTE_ON_KEY(stratum_1) 
SELECT 
	analysis_id,
	stratum_id AS stratum_1,
	CAST(NULL AS VARCHAR(255)) AS stratum_2,
	CAST(NULL AS VARCHAR(255)) AS stratum_3,
	CAST(NULL AS VARCHAR(255)) AS stratum_4,
	CAST(NULL AS VARCHAR(255)) AS stratum_5,
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
INTO 
	#s_tmpach_dist_1313
FROM 
	#tempResults_1313;

TRUNCATE TABLE #tempResults_1313;
DROP TABLE #tempResults_1313;


-- 1320	Number of visit detail records by visit detail start month

--HINT DISTRIBUTE_ON_KEY(stratum_1)
WITH rawData AS (
SELECT 
	YEAR(vd.visit_detail_start_date) * 100 + MONTH(vd.visit_detail_start_date) AS stratum_1,
	COUNT_BIG(vd.person_id) AS count_value
FROM 
	@FullySpecifiedCdmSchema.visit_detail vd
JOIN 
	@FullySpecifiedCdmSchema.observation_period op 
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
	CAST(stratum_1 AS VARCHAR(255)) AS stratum_1,
	CAST(NULL AS VARCHAR(255)) AS stratum_2,
	CAST(NULL AS VARCHAR(255)) AS stratum_3,
	CAST(NULL AS VARCHAR(255)) AS stratum_4,
	CAST(NULL AS VARCHAR(255)) AS stratum_5,
	count_value
INTO 
	#s_tmpach_1320
FROM 
	rawData;


-- 1321	Number of persons by visit start year 

--HINT DISTRIBUTE_ON_KEY(stratum_1)
WITH rawData AS (
SELECT 
	YEAR(vd.visit_detail_start_date) AS stratum_1,
	COUNT_BIG(DISTINCT vd.person_id) AS count_value
FROM 
	@FullySpecifiedCdmSchema.visit_detail vd
JOIN 
	@FullySpecifiedCdmSchema.observation_period op 
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
  CAST(stratum_1 AS VARCHAR(255)) AS stratum_1,
  CAST(NULL AS VARCHAR(255)) AS stratum_2,
  CAST(NULL AS VARCHAR(255)) AS stratum_3,
  CAST(NULL AS VARCHAR(255)) AS stratum_4,
  CAST(NULL AS VARCHAR(255)) AS stratum_5,
  count_value
INTO 
	#s_tmpach_1321
FROM 
	rawData;


-- 1325	Number of visit_detail records, by visit_detail_source_concept_id

--HINT DISTRIBUTE_ON_KEY(stratum_1)
SELECT 
	1325 AS analysis_id,
	CAST(vd.visit_detail_source_concept_id AS VARCHAR(255)) AS stratum_1,
	CAST(NULL AS VARCHAR(255)) AS stratum_2,
	CAST(NULL AS VARCHAR(255)) AS stratum_3,
	CAST(NULL AS VARCHAR(255)) AS stratum_4,
	CAST(NULL AS VARCHAR(255)) AS stratum_5,
	COUNT_BIG(*) AS count_value
INTO 
	#s_tmpach_1325
FROM 
	@FullySpecifiedCdmSchema.visit_detail vd
JOIN 
	@FullySpecifiedCdmSchema.observation_period op 
ON 
	vd.person_id = op.person_id
AND	
	vd.visit_detail_start_date >= op.observation_period_start_date  
AND 
	vd.visit_detail_start_date <= op.observation_period_end_date
GROUP BY 
	visit_detail_source_concept_id;


-- 1326	Number of records by domain by visit detail concept id

SELECT 
	1326 AS analysis_id,
	CAST(v.visit_detail_concept_id AS VARCHAR(255)) AS stratum_1,
	v.cdm_table AS stratum_2,
	CAST(NULL AS VARCHAR(255)) AS stratum_3,
	CAST(NULL AS VARCHAR(255)) AS stratum_4,
	CAST(NULL AS VARCHAR(255)) AS stratum_5,
	v.record_count AS count_value
INTO 
	#s_tmpach_1326
FROM (
	SELECT 'drug_exposure' cdm_table,
		COALESCE(vd.visit_detail_concept_id, 0) visit_detail_concept_id,
		COUNT(*) record_count
	FROM 
		@FullySpecifiedCdmSchema.drug_exposure de
	LEFT JOIN 
		@FullySpecifiedCdmSchema.visit_detail vd 
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
		@FullySpecifiedCdmSchema.condition_occurrence co
	LEFT JOIN 
		@FullySpecifiedCdmSchema.visit_detail vd 
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
		@FullySpecifiedCdmSchema.device_exposure de
	LEFT JOIN 
		@FullySpecifiedCdmSchema.visit_detail vd 
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
		@FullySpecifiedCdmSchema.procedure_occurrence po
	LEFT JOIN 
		@FullySpecifiedCdmSchema.visit_detail vd 
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
		@FullySpecifiedCdmSchema.measurement m
	LEFT JOIN 
		@FullySpecifiedCdmSchema.visit_detail vd 
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
		@FullySpecifiedCdmSchema.observation o
	LEFT JOIN 
		@FullySpecifiedCdmSchema.visit_detail vd 
	ON 
		o.visit_occurrence_id = vd.visit_occurrence_id
	GROUP BY 
		vd.visit_detail_concept_id

	) v;


-- 1406	Length of payer plan (days) of first payer plan period by gender

--HINT DISTRIBUTE_ON_KEY(stratum_1)
with rawData(stratum1_id, count_value) as
(
  select p1.gender_concept_id as stratum1_id,
    DATEDIFF(dd,ppp1.payer_plan_period_start_date, ppp1.payer_plan_period_end_date) as count_value
  from @FullySpecifiedCdmSchema.person p1
	inner join 
	(select person_id, 
		payer_plan_period_START_DATE, 
		payer_plan_period_END_DATE, 
		ROW_NUMBER() over (PARTITION by person_id order by payer_plan_period_start_date asc) as rn1
		 from @FullySpecifiedCdmSchema.payer_plan_period
	) ppp1
	on p1.PERSON_ID = ppp1.PERSON_ID
	where ppp1.rn1 = 1
),
overallStats (stratum1_id, avg_value, stdev_value, min_value, max_value, total) as
(
  select stratum1_id, 
    CAST(avg(1.0 * count_value) AS FLOAT) as avg_value,
    CAST(stdev(count_value) AS FLOAT) as stdev_value,
    min(count_value) as min_value,
    max(count_value) as max_value,
    count_big(*) as total
  from rawData
  group by stratum1_id
),
statsView (stratum1_id, count_value, total, rn) as
(
  select stratum1_id, 
  	count_value, 
  	count_big(*) as total, 
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
select 1406 as analysis_id,
  CAST(p.stratum1_id AS VARCHAR(255)) as stratum_1,
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
into #tempResults_1406
from priorStats p
join overallStats o on p.stratum1_id = o.stratum1_id
GROUP BY p.stratum1_id, o.total, o.min_value, o.max_value, o.avg_value, o.stdev_value
;

--HINT DISTRIBUTE_ON_KEY(stratum_1)
select analysis_id, stratum_1, 
cast(null as varchar(255)) as stratum_2, cast(null as varchar(255)) as stratum_3, cast(null as varchar(255)) as stratum_4, cast(null as varchar(255)) as stratum_5,
count_value, min_value, max_value, avg_value, stdev_value, median_value, p10_value, p25_value, p75_value, p90_value
into #s_tmpach_dist_1406
from #tempResults_1406
;

truncate table #tempResults_1406;
drop table #tempResults_1406;


-- 1407	Length of payer plan (days) of first payer plan period by age decile

--HINT DISTRIBUTE_ON_KEY(stratum_id)
with rawData(stratum_id, count_value) as
(
  select floor((year(ppp1.payer_plan_period_START_DATE) - p1.YEAR_OF_BIRTH)/10) as stratum_id,
    DATEDIFF(dd,ppp1.payer_plan_period_start_date, ppp1.payer_plan_period_end_date) as count_value
  from @FullySpecifiedCdmSchema.person p1
	inner join 
	(select person_id, 
		payer_plan_period_START_DATE, 
		payer_plan_period_END_DATE, 
		ROW_NUMBER() over (PARTITION by person_id order by payer_plan_period_start_date asc) as rn1
		 from @FullySpecifiedCdmSchema.payer_plan_period
	) ppp1
	on p1.PERSON_ID = ppp1.PERSON_ID
	where ppp1.rn1 = 1
),
overallStats (stratum_id, avg_value, stdev_value, min_value, max_value, total) as
(
  select stratum_id,
    CAST(avg(1.0 * count_value) AS FLOAT) as avg_value,
    CAST(stdev(count_value) AS FLOAT) as stdev_value,
    min(count_value) as min_value,
    max(count_value) as max_value,
    count_big(*) as total
  FROM rawData
  group by stratum_id
),
statsView (stratum_id, count_value, total, rn) as
(
  select stratum_id, count_value, count_big(*) as total, row_number() over (order by count_value) as rn
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
select 1407 as analysis_id,
  CAST(o.stratum_id AS VARCHAR(255)) AS stratum_id,
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
into #tempResults_1407
from priorStats p
join overallStats o on p.stratum_id = o.stratum_id
GROUP BY o.stratum_id, o.total, o.min_value, o.max_value, o.avg_value, o.stdev_value
;

--HINT DISTRIBUTE_ON_KEY(stratum_1)
select analysis_id, stratum_id as stratum_1, 
cast(null as varchar(255)) as stratum_2, cast(null as varchar(255)) as stratum_3, cast(null as varchar(255)) as stratum_4, cast(null as varchar(255)) as stratum_5,
count_value, min_value, max_value, avg_value, stdev_value, median_value, p10_value, p25_value, p75_value, p90_value
into #s_tmpach_dist_1407
from #tempResults_1407
;

truncate table #tempResults_1407;
drop table #tempResults_1407;


-- 1408	Number of persons by length of payer plan period, in 30d increments

--HINT DISTRIBUTE_ON_KEY(stratum_1)
select 1408 as analysis_id,  
	CAST(floor(DATEDIFF(dd, ppp1.payer_plan_period_start_date, ppp1.payer_plan_period_end_date)/30) AS VARCHAR(255)) as stratum_1,
	cast(null as varchar(255)) as stratum_2, cast(null as varchar(255)) as stratum_3, cast(null as varchar(255)) as stratum_4, cast(null as varchar(255)) as stratum_5,
	COUNT_BIG(distinct p1.person_id) as count_value
into #s_tmpach_1408
from @FullySpecifiedCdmSchema.person p1
	inner join 
	(select person_id, 
		payer_plan_period_START_DATE, 
		payer_plan_period_END_DATE, 
		ROW_NUMBER() over (PARTITION by person_id order by payer_plan_period_start_date asc) as rn1
		 from @FullySpecifiedCdmSchema.payer_plan_period
	) ppp1
	on p1.PERSON_ID = ppp1.PERSON_ID
	where ppp1.rn1 = 1
group by CAST(floor(DATEDIFF(dd, ppp1.payer_plan_period_start_date, ppp1.payer_plan_period_end_date)/30) AS VARCHAR(255))
;


-- 1409	Number of persons with continuous payer plan in each year
-- Note: using temp table instead of nested query because this gives vastly improved



select distinct 
  YEAR(payer_plan_period_start_date) as obs_year 
INTO
  #temp_dates_1409
from 
  @FullySpecifiedCdmSchema.payer_plan_period
;

--HINT DISTRIBUTE_ON_KEY(stratum_1)
select 1409 as analysis_id,  
	CAST(t1.obs_year AS VARCHAR(255)) as stratum_1, 
	cast(null as varchar(255)) as stratum_2, cast(null as varchar(255)) as stratum_3, cast(null as varchar(255)) as stratum_4, cast(null as varchar(255)) as stratum_5,
	COUNT_BIG(distinct p1.PERSON_ID) as count_value
into #s_tmpach_1409
from
	@FullySpecifiedCdmSchema.person p1
	inner join 
    @FullySpecifiedCdmSchema.payer_plan_period ppp1
	on p1.person_id = ppp1.person_id
	,
	#temp_dates_1409 t1 
where year(ppp1.payer_plan_period_START_DATE) <= t1.obs_year
	and year(ppp1.payer_plan_period_END_DATE) >= t1.obs_year
group by t1.obs_year
;

truncate table #temp_dates_1409;
drop table #temp_dates_1409;


-- 1410	Number of persons with continuous payer plan in each month
-- Note: using temp table instead of nested query because this gives vastly improved performance in Oracle

--HINT DISTRIBUTE_ON_KEY(obs_month)
SELECT DISTINCT 
  YEAR(payer_plan_period_start_date)*100 + MONTH(payer_plan_period_start_date) AS obs_month,
  DATEFROMPARTS(YEAR(payer_plan_period_start_date), MONTH(payer_plan_period_start_date), 1) as obs_month_start,
  EOMONTH(payer_plan_period_start_date) as obs_month_end
INTO
  #temp_dates_1410
FROM 
  @FullySpecifiedCdmSchema.payer_plan_period
;

--HINT DISTRIBUTE_ON_KEY(stratum_1)
select 
  1410 as analysis_id, 
	CAST(obs_month AS VARCHAR(255)) as stratum_1,
	cast(null as varchar(255)) as stratum_2, cast(null as varchar(255)) as stratum_3, cast(null as varchar(255)) as stratum_4, cast(null as varchar(255)) as stratum_5,
	COUNT_BIG(distinct p1.PERSON_ID) as count_value
into #s_tmpach_1410
from
	@FullySpecifiedCdmSchema.person p1
	inner join 
  @FullySpecifiedCdmSchema.payer_plan_period ppp1
	on p1.person_id = ppp1.person_id
	,
	#temp_dates_1410
where ppp1.payer_plan_period_START_DATE <= obs_month_start
	and ppp1.payer_plan_period_END_DATE >= obs_month_end
group by obs_month
;

TRUNCATE TABLE #temp_dates_1410;
DROP TABLE #temp_dates_1410;


-- 1411	Number of persons by payer plan period start month

--HINT DISTRIBUTE_ON_KEY(stratum_1)
select 1411 as analysis_id, 
	DATEFROMPARTS(YEAR(payer_plan_period_start_date), MONTH(payer_plan_period_start_date), 1) as stratum_1,
	cast(null as varchar(255)) as stratum_2, cast(null as varchar(255)) as stratum_3, cast(null as varchar(255)) as stratum_4, cast(null as varchar(255)) as stratum_5,
	COUNT_BIG(distinct p1.PERSON_ID) as count_value
into #s_tmpach_1411
from
	@FullySpecifiedCdmSchema.person p1
	inner join @FullySpecifiedCdmSchema.payer_plan_period ppp1
	on p1.person_id = ppp1.person_id
group by DATEFROMPARTS(YEAR(payer_plan_period_start_date), MONTH(payer_plan_period_start_date), 1)
;


-- 1412	Number of persons by payer plan period end month

--HINT DISTRIBUTE_ON_KEY(stratum_1)
select 1412 as analysis_id,  
	DATEFROMPARTS(YEAR(payer_plan_period_start_date), MONTH(payer_plan_period_start_date), 1) as stratum_1,
	cast(null as varchar(255)) as stratum_2, cast(null as varchar(255)) as stratum_3, cast(null as varchar(255)) as stratum_4, cast(null as varchar(255)) as stratum_5,
	COUNT_BIG(distinct p1.PERSON_ID) as count_value
into #s_tmpach_1412
from
	@FullySpecifiedCdmSchema.person p1
	inner join @FullySpecifiedCdmSchema.payer_plan_period ppp1
	on p1.person_id = ppp1.person_id
group by DATEFROMPARTS(YEAR(payer_plan_period_start_date), MONTH(payer_plan_period_start_date), 1)
;


-- 1413	Number of persons by number of payer plan periods

--HINT DISTRIBUTE_ON_KEY(stratum_1)
select 1413 as analysis_id,  
	CAST(ppp1.num_periods AS VARCHAR(255)) as stratum_1,
	cast(null as varchar(255)) as stratum_2, cast(null as varchar(255)) as stratum_3, cast(null as varchar(255)) as stratum_4, cast(null as varchar(255)) as stratum_5,
	COUNT_BIG(distinct p1.PERSON_ID) as count_value
into #s_tmpach_1413
from
	@FullySpecifiedCdmSchema.person p1
	inner join (select person_id, COUNT_BIG(payer_plan_period_start_date) as num_periods from @FullySpecifiedCdmSchema.payer_plan_period group by PERSON_ID) ppp1
	on p1.person_id = ppp1.person_id
group by ppp1.num_periods
;


-- 1425	Number of payer_plan_period records, by payer_source_concept_id

--HINT DISTRIBUTE_ON_KEY(stratum_1)
select 1425 as analysis_id,
       cast(payer_source_concept_id AS varchar(255)) AS stratum_1,
       cast(null AS varchar(255)) AS stratum_2,
       cast(null as varchar(255)) as stratum_3,
       cast(null as varchar(255)) as stratum_4,
       cast(null as varchar(255)) as stratum_5,
       count_big(*) AS count_value
  into #s_tmpach_1425 
  from @FullySpecifiedCdmSchema.payer_plan_period
 group by payer_source_concept_id;



-- 1800	Number of persons with at least one measurement occurrence, by measurement_concept_id

--HINT DISTRIBUTE_ON_KEY(stratum_1)
SELECT 
	1800 AS analysis_id,
	CAST(m.measurement_concept_id AS VARCHAR(255)) AS stratum_1,
	CAST(NULL AS VARCHAR(255)) AS stratum_2,
	CAST(NULL AS VARCHAR(255)) AS stratum_3,
	CAST(NULL AS VARCHAR(255)) AS stratum_4,
	CAST(NULL AS VARCHAR(255)) AS stratum_5,
	COUNT_BIG(DISTINCT m.person_id) AS count_value
INTO 
	#s_tmpach_1800
FROM 
	@FullySpecifiedCdmSchema.measurement m
JOIN 
	@FullySpecifiedCdmSchema.observation_period op 
ON 
	m.person_id = op.person_id
AND 
	m.measurement_date >= op.observation_period_start_date
AND 
	m.measurement_date <= op.observation_period_end_date	
GROUP BY 
	m.measurement_concept_id;


-- 1801	Number of measurement occurrence records, by measurement_concept_id

--HINT DISTRIBUTE_ON_KEY(stratum_1)
SELECT 
	1801 AS analysis_id,
	CAST(m.measurement_concept_id AS VARCHAR(255)) AS stratum_1,
	CAST(NULL AS VARCHAR(255)) AS stratum_2,
	CAST(NULL AS VARCHAR(255)) AS stratum_3,
	CAST(NULL AS VARCHAR(255)) AS stratum_4,
	CAST(NULL AS VARCHAR(255)) AS stratum_5,
	COUNT_BIG(m.person_id) AS count_value
INTO 
	#s_tmpach_1801
FROM 
	@FullySpecifiedCdmSchema.measurement m
JOIN 
	@FullySpecifiedCdmSchema.observation_period op 
ON 
	m.person_id = op.person_id
AND 
	m.measurement_date >= op.observation_period_start_date
AND 
	m.measurement_date <= op.observation_period_end_date	
GROUP BY 
	m.measurement_concept_id;


-- 1802	Number of persons by measurement occurrence start month, by measurement_concept_id

--HINT DISTRIBUTE_ON_KEY(stratum_1)
WITH rawData AS (
SELECT 
	m.measurement_concept_id AS stratum_1,
	YEAR(m.measurement_date) * 100 + MONTH(m.measurement_date) AS stratum_2,
	COUNT_BIG(DISTINCT m.person_id) AS count_value
FROM 
	@FullySpecifiedCdmSchema.measurement m
JOIN 
	@FullySpecifiedCdmSchema.observation_period op 
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
  CAST(stratum_1 AS VARCHAR(255)) AS stratum_1,
  CAST(stratum_2 AS VARCHAR(255)) AS stratum_2,
  CAST(NULL AS VARCHAR(255)) AS stratum_3,
  CAST(NULL AS VARCHAR(255)) AS stratum_4,
  CAST(NULL AS VARCHAR(255)) AS stratum_5,
  count_value
INTO 
	#s_tmpach_1802
FROM 
	rawData;


-- 1803	Number of distinct measurement occurrence concepts per person

--HINT DISTRIBUTE_ON_KEY(count_value)
with rawData(count_value) as
(
SELECT 
	COUNT_BIG(DISTINCT m.measurement_concept_id) AS count_value
FROM 
	@FullySpecifiedCdmSchema.measurement m
JOIN 
	@FullySpecifiedCdmSchema.observation_period op 
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
    CAST(stdev(count_value) AS FLOAT) as stdev_value,
    min(count_value) as min_value,
    max(count_value) as max_value,
    count_big(*) as total
  from rawData
),
statsView (count_value, total, rn) as
(
  select count_value, 
  	count_big(*) as total, 
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
select 1803 as analysis_id,
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
into #tempResults_1803
from priorStats p
CROSS JOIN overallStats o
GROUP BY o.total, o.min_value, o.max_value, o.avg_value, o.stdev_value
;

--HINT DISTRIBUTE_ON_KEY(count_value)
select analysis_id, 
cast(null as varchar(255)) as stratum_1, cast(null as varchar(255)) as stratum_2, cast(null as varchar(255)) as stratum_3, cast(null as varchar(255)) as stratum_4, cast(null as varchar(255)) as stratum_5,
count_value, min_value, max_value, avg_value, stdev_value, median_value, p10_value, p25_value, p75_value, p90_value
into #s_tmpach_dist_1803
from #tempResults_1803
;

truncate table #tempResults_1803;

drop table #tempResults_1803;


-- 1804	Number of persons with at least one measurement occurrence, by measurement_concept_id by calendar year by gender by age decile

--HINT DISTRIBUTE_ON_KEY(stratum_1)
WITH rawData AS (
SELECT 
	m.measurement_concept_id AS stratum_1,
	YEAR(m.measurement_date) AS stratum_2,
	p.gender_concept_id AS stratum_3,
	FLOOR((YEAR(m.measurement_date) - p.year_of_birth) / 10) AS stratum_4,
	COUNT_BIG(DISTINCT p.person_id) AS count_value
FROM 
	@FullySpecifiedCdmSchema.person p
JOIN 
	@FullySpecifiedCdmSchema.measurement m 
ON 
	p.person_id = m.person_id
JOIN 
	@FullySpecifiedCdmSchema.observation_period op 
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
	CAST(stratum_1 AS VARCHAR(255)) AS stratum_1,
	CAST(stratum_2 AS VARCHAR(255)) AS stratum_2,
	CAST(stratum_3 AS VARCHAR(255)) AS stratum_3,
	CAST(stratum_4 AS VARCHAR(255)) AS stratum_4,
	CAST(NULL AS VARCHAR(255)) AS stratum_5,
	count_value
INTO 
	#s_tmpach_1804
FROM 
	rawData;


-- 1805	Number of measurement records, by measurement_concept_id by measurement_type_concept_id

--HINT DISTRIBUTE_ON_KEY(stratum_1)
SELECT 
	1805 AS analysis_id,
	CAST(m.measurement_concept_id AS VARCHAR(255)) AS stratum_1,
	CAST(m.measurement_type_concept_id AS VARCHAR(255)) AS stratum_2,
	CAST(NULL AS VARCHAR(255)) AS stratum_3,
	CAST(NULL AS VARCHAR(255)) AS stratum_4,
	CAST(NULL AS VARCHAR(255)) AS stratum_5,
	COUNT_BIG(m.person_id) AS count_value
INTO 
	#s_tmpach_1805
FROM 
	@FullySpecifiedCdmSchema.measurement m
	JOIN 
		@FullySpecifiedCdmSchema.observation_period op 
	ON 
		m.person_id = op.person_id
	AND 
		m.measurement_date >= op.observation_period_start_date
	AND 
		m.measurement_date <= op.observation_period_end_date		
GROUP BY 
	m.measurement_concept_id,
	m.measurement_type_concept_id;


-- 1806	Distribution of age by measurement_concept_id

--HINT DISTRIBUTE_ON_KEY(subject_id)
SELECT 
	o.measurement_concept_id AS subject_id,
	p.gender_concept_id,
	o.measurement_start_year - p.year_of_birth AS count_value
INTO 
	#rawData_1806
FROM 
	@FullySpecifiedCdmSchema.person p
JOIN (
	SELECT 
		m.person_id,
		m.measurement_concept_id,
		MIN(YEAR(m.measurement_date)) AS measurement_start_year
	FROM 
		@FullySpecifiedCdmSchema.measurement m
	JOIN 
		@FullySpecifiedCdmSchema.observation_period op 
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

--HINT DISTRIBUTE_ON_KEY(stratum1_id)
with overallStats (stratum1_id, stratum2_id, avg_value, stdev_value, min_value, max_value, total) as
(
  select subject_id as stratum1_id,
    gender_concept_id as stratum2_id,
    CAST(avg(1.0 * count_value) AS FLOAT) as avg_value,
    CAST(stdev(count_value) AS FLOAT) as stdev_value,
    min(count_value) as min_value,
    max(count_value) as max_value,
    count_big(*) as total
  FROM #rawData_1806
	group by subject_id, gender_concept_id
),
statsView (stratum1_id, stratum2_id, count_value, total, rn) as
(
  select subject_id as stratum1_id, gender_concept_id as stratum2_id, count_value, count_big(*) as total, row_number() over (partition by subject_id, gender_concept_id order by count_value) as rn
  FROM #rawData_1806
  group by subject_id, gender_concept_id, count_value
),
priorStats (stratum1_id, stratum2_id, count_value, total, accumulated) as
(
  select s.stratum1_id, s.stratum2_id, s.count_value, s.total, sum(p.total) as accumulated
  from statsView s
  join statsView p on s.stratum1_id = p.stratum1_id and s.stratum2_id = p.stratum2_id and p.rn <= s.rn
  group by s.stratum1_id, s.stratum2_id, s.count_value, s.total, s.rn
)
select 1806 as analysis_id,
  CAST(o.stratum1_id AS VARCHAR(255)) AS stratum1_id,
  CAST(o.stratum2_id AS VARCHAR(255)) AS stratum2_id,
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
into #tempResults_1806
from priorStats p
join overallStats o on p.stratum1_id = o.stratum1_id and p.stratum2_id = o.stratum2_id 
GROUP BY o.stratum1_id, o.stratum2_id, o.total, o.min_value, o.max_value, o.avg_value, o.stdev_value
;

--HINT DISTRIBUTE_ON_KEY(stratum_1)
select analysis_id, stratum1_id as stratum_1, stratum2_id as stratum_2, 
cast(null as varchar(255)) as stratum_3, cast(null as varchar(255)) as stratum_4, cast(null as varchar(255)) as stratum_5,
count_value, min_value, max_value, avg_value, stdev_value, median_value, p10_value, p25_value, p75_value, p90_value
into #s_tmpach_dist_1806
from #tempResults_1806
;

truncate table #rawData_1806;
drop table #rawData_1806;

truncate table #tempResults_1806;
drop table #tempResults_1806;


-- 1807	Number of measurement occurrence records, by measurement_concept_id and unit_concept_id

--HINT DISTRIBUTE_ON_KEY(stratum_1)
SELECT 
	1807 AS analysis_id,
	CAST(m.measurement_concept_id AS VARCHAR(255)) AS stratum_1,
	CAST(m.unit_concept_id AS VARCHAR(255)) AS stratum_2,
	CAST(NULL AS VARCHAR(255)) AS stratum_3,
	CAST(NULL AS VARCHAR(255)) AS stratum_4,
	CAST(NULL AS VARCHAR(255)) AS stratum_5,
	COUNT_BIG(m.person_id) AS count_value
INTO 
	#s_tmpach_1807
FROM 
	@FullySpecifiedCdmSchema.measurement m
JOIN 
	@FullySpecifiedCdmSchema.observation_period op 
ON 
	m.person_id = op.person_id
AND 
	m.measurement_date >= op.observation_period_start_date
AND 
	m.measurement_date <= op.observation_period_end_date		
GROUP BY 
	m.measurement_concept_id,
	m.unit_concept_id;


-- 1811	Number of measurement records with a value (with a mapped, non-null value_as_number)


SELECT 
	1811 AS analysis_id,
	CAST(NULL AS VARCHAR(255)) AS stratum_1,
	CAST(NULL AS VARCHAR(255)) AS stratum_2,
	CAST(NULL AS VARCHAR(255)) AS stratum_3,
	CAST(NULL AS VARCHAR(255)) AS stratum_4,
	CAST(NULL AS VARCHAR(255)) AS stratum_5,
	COUNT_BIG(m.person_id) AS count_value
INTO 
	#s_tmpach_1811
FROM 
	@FullySpecifiedCdmSchema.measurement m
JOIN 
	@FullySpecifiedCdmSchema.observation_period op 
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



-- 1814	Number of measurement records with no value (numeric or concept)


SELECT 
	1814 AS analysis_id,
	CAST(NULL AS VARCHAR(255)) AS stratum_1,
	CAST(NULL AS VARCHAR(255)) AS stratum_2,
	CAST(NULL AS VARCHAR(255)) AS stratum_3,
	CAST(NULL AS VARCHAR(255)) AS stratum_4,
	CAST(NULL AS VARCHAR(255)) AS stratum_5,
	COUNT_BIG(m.person_id) AS count_value
INTO 
	#s_tmpach_1814
FROM 
	@FullySpecifiedCdmSchema.measurement m
JOIN 
	@FullySpecifiedCdmSchema.observation_period op 
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


-- 1815  Distribution of numeric values, by measurement_concept_id and unit_concept_id

--HINT DISTRIBUTE_ON_KEY(stratum1_id)
SELECT 
	m.subject_id AS stratum1_id,
	m.unit_concept_id AS stratum2_id,
	m.count_value,
	COUNT_BIG(*) AS total,
	ROW_NUMBER() OVER (PARTITION BY m.subject_id,m.unit_concept_id ORDER BY m.count_value) AS rn
INTO 
	#statsView_1815
FROM (
	SELECT 
		m.measurement_concept_id AS subject_id,
		m.unit_concept_id,
		CAST(m.value_as_number AS FLOAT) AS count_value
	FROM 
		@FullySpecifiedCdmSchema.measurement m
	JOIN 
		@FullySpecifiedCdmSchema.observation_period op 
	ON 
		m.person_id = op.person_id
	AND 
		m.measurement_date >= op.observation_period_start_date
	AND 
		m.measurement_date <= op.observation_period_end_date		
	WHERE 
		m.value_as_number IS NOT NULL
	) m
GROUP BY 
	m.subject_id, 
	m.unit_concept_id, 
	m.count_value
;

--HINT DISTRIBUTE_ON_KEY(stratum1_id)
select 1815 as analysis_id,
  CAST(o.stratum1_id AS VARCHAR(255)) AS stratum1_id,
  CAST(o.stratum2_id AS VARCHAR(255)) AS stratum2_id,
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
into #tempResults_1815
from 
(
  select s.stratum1_id, s.stratum2_id, s.count_value, s.total, sum(p.total) as accumulated
  from #statsView_1815 s
  join #statsView_1815 p on s.stratum1_id = p.stratum1_id and s.stratum2_id = p.stratum2_id and p.rn <= s.rn
  group by s.stratum1_id, s.stratum2_id, s.count_value, s.total, s.rn
) p
join 
(
	SELECT 
		m.subject_id AS stratum1_id,
		m.unit_concept_id AS stratum2_id,
		CAST(AVG(1.0 * m.count_value) AS FLOAT) AS avg_value,
		CAST(stdev(m.count_value) AS FLOAT) AS stdev_value,
		MIN(m.count_value) AS min_value,
		MAX(m.count_value) AS max_value,
		COUNT_BIG(*) AS total
	FROM 
	(
		SELECT 
			m.measurement_concept_id AS subject_id,
			m.unit_concept_id,
			CAST(m.value_as_number AS FLOAT) AS count_value
		FROM 
			@FullySpecifiedCdmSchema.measurement m
		JOIN 
			@FullySpecifiedCdmSchema.observation_period op 
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

--HINT DISTRIBUTE_ON_KEY(stratum_1)
select analysis_id, stratum1_id as stratum_1, stratum2_id as stratum_2, 
cast(null as varchar(255)) as stratum_3, cast(null as varchar(255)) as stratum_4, cast(null as varchar(255)) as stratum_5,
count_value, min_value, max_value, avg_value, stdev_value, median_value, p10_value, p25_value, p75_value, p90_value
into #s_tmpach_dist_1815
from #tempResults_1815
;

truncate table #statsView_1815;
drop table #statsView_1815;

truncate table #tempResults_1815;
drop table #tempResults_1815;


-- 1816	Distribution of low range, by measurement_concept_id and unit_concept_id


--HINT DISTRIBUTE_ON_KEY(stratum1_id)
SELECT 
	m.subject_id AS stratum1_id,
	m.unit_concept_id AS stratum2_id,
	CAST(AVG(1.0 * m.count_value) AS FLOAT) AS avg_value,
	CAST(STDEV(m.count_value) AS FLOAT) AS stdev_value,
	MIN(m.count_value) AS min_value,
	MAX(m.count_value) AS max_value,
	COUNT_BIG(*) AS total
INTO 
	#overallStats_1816
FROM (
	SELECT 
		m.measurement_concept_id AS subject_id,
		m.unit_concept_id,
		CAST(m.range_low AS FLOAT) AS count_value
	FROM 
		@FullySpecifiedCdmSchema.measurement m
	JOIN 
		@FullySpecifiedCdmSchema.observation_period op 
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

--HINT DISTRIBUTE_ON_KEY(stratum1_id)
SELECT 
	m.subject_id AS stratum1_id,
	m.unit_concept_id AS stratum2_id,
	m.count_value,
	COUNT_BIG(*) AS total,
	ROW_NUMBER() OVER (PARTITION BY m.subject_id,m.unit_concept_id ORDER BY m.count_value) AS rn
INTO 
	#statsView_1816
FROM (
	SELECT 
		m.measurement_concept_id AS subject_id,
		m.unit_concept_id,
		CAST(m.range_low AS FLOAT) AS count_value
	FROM 
		@FullySpecifiedCdmSchema.measurement m
  	JOIN 
		@FullySpecifiedCdmSchema.observation_period op 
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

--HINT DISTRIBUTE_ON_KEY(stratum1_id)
select 1816 as analysis_id,
  CAST(o.stratum1_id AS VARCHAR(255)) AS stratum1_id,
  CAST(o.stratum2_id AS VARCHAR(255)) AS stratum2_id,
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
into #tempResults_1816
from 
(
  select s.stratum1_id, s.stratum2_id, s.count_value, s.total, sum(p.total) as accumulated
  from #statsView_1816 s
  join #statsView_1816 p on s.stratum1_id = p.stratum1_id and s.stratum2_id = p.stratum2_id and p.rn <= s.rn
  group by s.stratum1_id, s.stratum2_id, s.count_value, s.total, s.rn
) p
join #overallStats_1816 o on p.stratum1_id = o.stratum1_id and p.stratum2_id = o.stratum2_id 
GROUP BY o.stratum1_id, o.stratum2_id, o.total, o.min_value, o.max_value, o.avg_value, o.stdev_value
;

--HINT DISTRIBUTE_ON_KEY(stratum_1)
select 
  analysis_id, stratum1_id as stratum_1, stratum2_id as stratum_2, 
  cast(null as varchar(255)) as stratum_3, cast(null as varchar(255)) as stratum_4, cast(null as varchar(255)) as stratum_5,
  count_value, min_value, max_value, avg_value, stdev_value, median_value, p10_value, p25_value, p75_value, p90_value
into #s_tmpach_dist_1816
from #tempResults_1816
;

truncate table #overallStats_1816;
drop table #overallStats_1816;

truncate table #statsView_1816;
drop table #statsView_1816;

truncate table #tempResults_1816;
drop table #tempResults_1816;


-- 1817	Distribution of high range, by observation_concept_id and unit_concept_id


--HINT DISTRIBUTE_ON_KEY(stratum1_id)
SELECT 
	m.subject_id AS stratum1_id,
	m.unit_concept_id AS stratum2_id,
	CAST(AVG(1.0 * m.count_value) AS FLOAT) AS avg_value,
	CAST(STDEV(m.count_value) AS FLOAT) AS stdev_value,
	MIN(m.count_value) AS min_value,
	MAX(m.count_value) AS max_value,
	COUNT_BIG(*) AS total
INTO 
	#overallStats_1817
FROM (
	SELECT 
		measurement_concept_id AS subject_id,
		unit_concept_id,
		CAST(range_high AS FLOAT) AS count_value
	FROM 
		@FullySpecifiedCdmSchema.measurement m
	JOIN 
		@FullySpecifiedCdmSchema.observation_period op 
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

--HINT DISTRIBUTE_ON_KEY(stratum1_id)
SELECT 
	m.subject_id AS stratum1_id,
	m.unit_concept_id AS stratum2_id,
	m.count_value,
	COUNT_BIG(*) AS total,
	ROW_NUMBER() OVER (PARTITION BY m.subject_id,m.unit_concept_id ORDER BY m.count_value) AS rn
INTO 
	#statsView_1817
FROM (
	SELECT 
		m.measurement_concept_id AS subject_id,
		m.unit_concept_id,
		CAST(m.range_high AS FLOAT) AS count_value
	FROM 
		@FullySpecifiedCdmSchema.measurement m
	JOIN 
		@FullySpecifiedCdmSchema.observation_period op 
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

--HINT DISTRIBUTE_ON_KEY(stratum1_id)
select 1817 as analysis_id,
  CAST(o.stratum1_id AS VARCHAR(255)) AS stratum1_id,
  CAST(o.stratum2_id AS VARCHAR(255)) AS stratum2_id,
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
into #tempResults_1817
from 
(
  select s.stratum1_id, s.stratum2_id, s.count_value, s.total, sum(p.total) as accumulated
  from #statsView_1817 s
  join #statsView_1817 p on s.stratum1_id = p.stratum1_id and s.stratum2_id = p.stratum2_id and p.rn <= s.rn
  group by s.stratum1_id, s.stratum2_id, s.count_value, s.total, s.rn
) p
join #overallStats_1817 o on p.stratum1_id = o.stratum1_id and p.stratum2_id = o.stratum2_id
GROUP BY o.stratum1_id, o.stratum2_id, o.total, o.min_value, o.max_value, o.avg_value, o.stdev_value
;

--HINT DISTRIBUTE_ON_KEY(stratum_1)
select 
  analysis_id, stratum1_id as stratum_1, stratum2_id as stratum_2, 
  cast(null as varchar(255)) as stratum_3, cast(null as varchar(255)) as stratum_4, cast(null as varchar(255)) as stratum_5,
  count_value, min_value, max_value, avg_value, stdev_value, median_value, p10_value, p25_value, p75_value, p90_value
into #s_tmpach_dist_1817
from #tempResults_1817
;

truncate table #overallStats_1817;
drop table #overallStats_1817;

truncate table #statsView_1817;
drop table #statsView_1817;

truncate table #tempResults_1817;
drop table #tempResults_1817;


-- 1818	Number of observation records below/within/above normal range, by observation_concept_id and unit_concept_id


--HINT DISTRIBUTE_ON_KEY(person_id)
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
			END AS VARCHAR(255)) AS stratum_3
INTO 
	#rawData_1818
FROM 
	@FullySpecifiedCdmSchema.measurement m
JOIN 
	@FullySpecifiedCdmSchema.observation_period op 
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

--HINT DISTRIBUTE_ON_KEY(stratum_1)
SELECT 
	1818 AS analysis_id,
	CAST(measurement_concept_id AS VARCHAR(255)) AS stratum_1,
	CAST(unit_concept_id AS VARCHAR(255)) AS stratum_2,
	CAST(stratum_3 AS VARCHAR(255)) AS stratum_3,
	CAST(NULL AS VARCHAR(255)) AS stratum_4,
	CAST(NULL AS VARCHAR(255)) AS stratum_5,
	COUNT_BIG(person_id) AS count_value
INTO 
	#s_tmpach_1818
FROM 
	#rawData_1818
GROUP BY 
	measurement_concept_id,
	unit_concept_id,
	stratum_3;

TRUNCATE TABLE #rawData_1818;

DROP TABLE #rawData_1818;


-- 1819	Number of measurement records, by concept_id, with a value (with a mapped, non-null value_as_number)


SELECT 
	1819 AS analysis_id,
	CAST(m.measurement_concept_id AS VARCHAR(255)) AS stratum_1,
	CAST(NULL AS VARCHAR(255)) AS stratum_2,
	CAST(NULL AS VARCHAR(255)) AS stratum_3,
	CAST(NULL AS VARCHAR(255)) AS stratum_4,
	CAST(NULL AS VARCHAR(255)) AS stratum_5,
	COUNT_BIG(m.person_id) AS count_value
INTO 
	#s_tmpach_1819
FROM 
	@FullySpecifiedCdmSchema.measurement m
JOIN 
	@FullySpecifiedCdmSchema.observation_period op 
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



-- 1820	Number of measurement records  by measurement start month

--HINT DISTRIBUTE_ON_KEY(stratum_1)
WITH rawData AS (
SELECT 
	YEAR(m.measurement_date) * 100 + MONTH(m.measurement_date) AS stratum_1,
	COUNT_BIG(m.person_id) AS count_value
FROM 
	@FullySpecifiedCdmSchema.measurement m
JOIN 
	@FullySpecifiedCdmSchema.observation_period op 
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
	CAST(stratum_1 AS VARCHAR(255)) AS stratum_1,
	CAST(NULL AS VARCHAR(255)) AS stratum_2,
	CAST(NULL AS VARCHAR(255)) AS stratum_3,
	CAST(NULL AS VARCHAR(255)) AS stratum_4,
	CAST(NULL AS VARCHAR(255)) AS stratum_5,
	count_value
INTO 
	#s_tmpach_1820
FROM 
	rawData;


-- 1821	Number of measurement records with no numeric value


select 1821 as analysis_id,  
	cast(null as varchar(255)) as stratum_1, cast(null as varchar(255)) as stratum_2, cast(null as varchar(255)) as stratum_3, cast(null as varchar(255)) as stratum_4, cast(null as varchar(255)) as stratum_5,
	COUNT_BIG(m.PERSON_ID) as count_value
into #s_tmpach_1821
from
	@FullySpecifiedCdmSchema.measurement m
where m.value_as_number is null
;



-- 1822	Number of measurement records, by measurement_concept_id and value_as_concept_id

--HINT DISTRIBUTE_ON_KEY(stratum_1)
SELECT 
	1822 AS analysis_id,
	CAST(m.measurement_concept_id AS VARCHAR(255)) AS stratum_1,
	CAST(m.value_as_concept_id AS VARCHAR(255)) AS stratum_2,
	CAST(NULL AS VARCHAR(255)) AS stratum_3,
	CAST(NULL AS VARCHAR(255)) AS stratum_4,
	CAST(NULL AS VARCHAR(255)) AS stratum_5,
	COUNT_BIG(*) AS count_value
INTO 
	#s_tmpach_1822
FROM 
	@FullySpecifiedCdmSchema.measurement m
JOIN 
	@FullySpecifiedCdmSchema.observation_period op 
ON 
	m.person_id = op.person_id
AND 
	m.measurement_date >= op.observation_period_start_date
AND 
	m.measurement_date <= op.observation_period_end_date		
GROUP BY 
	m.measurement_concept_id,
	m.value_as_concept_id;



-- 1823	Number of measurement records, by measurement_concept_id and operator_concept_id

--HINT DISTRIBUTE_ON_KEY(stratum_1)
SELECT 
	1823 AS analysis_id,
	CAST(m.measurement_concept_id AS VARCHAR(255)) AS stratum_1,
	CAST(m.operator_concept_id AS VARCHAR(255)) AS stratum_2,
	CAST(NULL AS VARCHAR(255)) AS stratum_3,
	CAST(NULL AS VARCHAR(255)) AS stratum_4,
	CAST(NULL AS VARCHAR(255)) AS stratum_5,
	COUNT_BIG(*) AS count_value
INTO 
	#s_tmpach_1823
FROM 
	@FullySpecifiedCdmSchema.measurement m
JOIN 
	@FullySpecifiedCdmSchema.observation_period op 
ON 
	m.person_id = op.person_id
AND 
	m.measurement_date >= op.observation_period_start_date
AND 
	m.measurement_date <= op.observation_period_end_date		
GROUP BY 
	m.measurement_concept_id,
	m.operator_concept_id;


-- 1825	Number of measurement records, by measurement_source_concept_id

--HINT DISTRIBUTE_ON_KEY(stratum_1)
SELECT 
	1825 AS analysis_id,
	CAST(m.measurement_source_concept_id AS VARCHAR(255)) AS stratum_1,
	CAST(NULL AS VARCHAR(255)) AS stratum_2,
	CAST(NULL AS VARCHAR(255)) AS stratum_3,
	CAST(NULL AS VARCHAR(255)) AS stratum_4,
	CAST(NULL AS VARCHAR(255)) AS stratum_5,
	COUNT_BIG(*) AS count_value
INTO 
	#s_tmpach_1825
FROM 
	@FullySpecifiedCdmSchema.measurement m
JOIN 
	@FullySpecifiedCdmSchema.observation_period op 
ON 
	m.person_id = op.person_id
AND 
	m.measurement_date >= op.observation_period_start_date
AND 
	m.measurement_date <= op.observation_period_end_date		
GROUP BY 
	m.measurement_source_concept_id;


-- 1826	Number of measurement records, by value_as_concept_id

--HINT DISTRIBUTE_ON_KEY(stratum_1)
SELECT 
	1826 AS analysis_id,
	CAST(m.value_as_concept_id AS VARCHAR(255)) AS stratum_1,
	CAST(NULL AS VARCHAR(255)) AS stratum_2,
	CAST(NULL AS VARCHAR(255)) AS stratum_3,
	CAST(NULL AS VARCHAR(255)) AS stratum_4,
	CAST(NULL AS VARCHAR(255)) AS stratum_5,
	COUNT_BIG(*) AS count_value
INTO 
	#s_tmpach_1826
FROM 
	@FullySpecifiedCdmSchema.measurement m
JOIN 
	@FullySpecifiedCdmSchema.observation_period op 
ON 
	m.person_id = op.person_id
AND 
	m.measurement_date >= op.observation_period_start_date
AND 
	m.measurement_date <= op.observation_period_end_date		
GROUP BY 
	m.value_as_concept_id;
 


-- 1827	Number of measurement records, by unit_concept_id

--HINT DISTRIBUTE_ON_KEY(stratum_1)
SELECT 
	1827 AS analysis_id,
	CAST(m.unit_concept_id AS VARCHAR(255)) AS stratum_1,
	CAST(NULL AS VARCHAR(255)) AS stratum_2,
	CAST(NULL AS VARCHAR(255)) AS stratum_3,
	CAST(NULL AS VARCHAR(255)) AS stratum_4,
	CAST(NULL AS VARCHAR(255)) AS stratum_5,
	COUNT_BIG(*) AS count_value
INTO 
	#s_tmpach_1827
FROM 
	@FullySpecifiedCdmSchema.measurement m
JOIN 
	@FullySpecifiedCdmSchema.observation_period op 
ON 
	m.person_id = op.person_id
AND 
	m.measurement_date >= op.observation_period_start_date
AND 
	m.measurement_date <= op.observation_period_end_date		
GROUP BY 
	m.unit_concept_id;


-- 1891	Number of total persons that have at least x measurements

--HINT DISTRIBUTE_ON_KEY(stratum_1)
SELECT 
	1891 AS analysis_id,
	CAST(m.measurement_concept_id AS VARCHAR(255)) AS stratum_1,
	CAST(m.meas_cnt AS VARCHAR(255)) AS stratum_2,
	CAST(NULL AS VARCHAR(255)) AS stratum_3,
	CAST(NULL AS VARCHAR(255)) AS stratum_4,
	CAST(NULL AS VARCHAR(255)) AS stratum_5,
	SUM(COUNT(m.person_id)) OVER (PARTITION BY m.measurement_concept_id ORDER BY m.meas_cnt DESC) AS count_value
INTO 
	#s_tmpach_1891
FROM (
	SELECT 
		m.measurement_concept_id,
		COUNT(m.measurement_id) AS meas_cnt,
		m.person_id
	FROM 
		@FullySpecifiedCdmSchema.measurement m
	JOIN 
		@FullySpecifiedCdmSchema.observation_period op 
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


-- 1900	completeness report

--HINT DISTRIBUTE_ON_KEY(stratum_1)
select 1900 as analysis_id, 
  cast(table_name as varchar(255)) as stratum_1, 
  cast(column_name as varchar(255)) as stratum_2, 
  source_value as stratum_3, 
  cast(null as varchar(255)) as stratum_4, 
  cast(null as varchar(255)) as stratum_5,
cnt as count_value
 into #s_tmpach_1900
 from (
  select 'measurement' as table_name, 'measurement_source_value' as column_name, measurement_source_value as source_value, count_big(*) as cnt from @FullySpecifiedCdmSchema.measurement where measurement_concept_id = 0 group by measurement_source_value 
  union
  select 'measurement' as table_name, 'unit_source_value' as column_name, unit_source_value as source_value, count_big(*) as cnt from @FullySpecifiedCdmSchema.measurement where unit_concept_id = 0 group by unit_source_value 
  union
  select 'procedure_occurrence' as table_name,'procedure_source_value' as column_name, procedure_source_value as source_value, count_big(*) as cnt from @FullySpecifiedCdmSchema.procedure_occurrence where procedure_concept_id = 0 group by procedure_source_value 
  union
  select 'procedure_occurrence' as table_name,'modifier_source_value' as column_name, modifier_source_value as source_value, count_big(*) as cnt from @FullySpecifiedCdmSchema.procedure_occurrence where modifier_concept_id = 0 group by modifier_source_value 
  union
  select 'drug_exposure' as table_name, 'drug_source_value' as column_name, drug_source_value as source_value, count_big(*) as cnt from @FullySpecifiedCdmSchema.drug_exposure where drug_concept_id = 0 group by drug_source_value 
  union
  select 'drug_exposure' as table_name, 'route_source_value' as column_name, route_source_value as source_value, count_big(*) as cnt from @FullySpecifiedCdmSchema.drug_exposure where route_concept_id = 0 group by route_source_value 
  union
  select 'condition_occurrence' as table_name, 'condition_source_value' as column_name, condition_source_value as source_value, count_big(*) as cnt from @FullySpecifiedCdmSchema.condition_occurrence where condition_concept_id = 0 group by condition_source_value 
  union
  select 'condition_occurrence' as table_name, 'condition_status_source_value' as column_name, condition_status_source_value as source_value, count_big(*) as cnt from @FullySpecifiedCdmSchema.condition_occurrence where condition_status_concept_id = 0 group by condition_status_source_value 
  union
  select 'observation' as table_name, 'observation_source_value' as column_name, observation_source_value as source_value, count_big(*) as cnt from @FullySpecifiedCdmSchema.observation where observation_concept_id = 0 group by observation_source_value                  
  union
  select 'observation' as table_name, 'unit_source_value' as column_name, unit_source_value as source_value, count_big(*) as cnt from @FullySpecifiedCdmSchema.observation where unit_concept_id = 0 group by unit_source_value                  
  union
  select 'observation' as table_name, 'qualifier_source_value' as column_name, qualifier_source_value as source_value, count_big(*) as cnt from @FullySpecifiedCdmSchema.observation where qualifier_concept_id = 0 group by qualifier_source_value
  union
  select 'payer_plan_period' as table_name, 'payer_source_value' as column_name, payer_source_value as source_value, count_big(*) as cnt from @FullySpecifiedCdmSchema.payer_plan_period where payer_concept_id = 0 group by payer_source_value                    
  union
  select 'payer_plan_period' as table_name, 'plan_source_value' as column_name, plan_source_value as source_value, count_big(*) as cnt from @FullySpecifiedCdmSchema.payer_plan_period where plan_concept_id = 0 group by plan_source_value                    
  union
  select 'payer_plan_period' as table_name, 'sponsor_source_value' as column_name, sponsor_source_value as source_value, count_big(*) as cnt from @FullySpecifiedCdmSchema.payer_plan_period where sponsor_concept_id = 0 group by sponsor_source_value                    
  union
  select 'payer_plan_period' as table_name, 'stop_reason_source_value' as column_name, stop_reason_source_value as source_value, count_big(*) as cnt from @FullySpecifiedCdmSchema.payer_plan_period where stop_reason_concept_id = 0 group by stop_reason_source_value                    
  union
  select 'provider' as table_name, 'specialty_source_value' as column_name, specialty_source_value as source_value, count_big(*) as cnt from @FullySpecifiedCdmSchema.provider where specialty_concept_id = 0 group by specialty_source_value
  union  
  select 'provider' as table_name, 'gender_source_value' as column_name, gender_source_value as source_value, count_big(*) as cnt from @FullySpecifiedCdmSchema.provider where gender_concept_id = 0 group by gender_source_value
  union  
  select 'person' as table_name, 'gender_source_value' as column_name, gender_source_value as source_value, count_big(*) as cnt from @FullySpecifiedCdmSchema.person where gender_concept_id = 0 group by gender_source_value                    
  union
  select 'person' as table_name, 'race_source_value' as column_name, race_source_value as source_value, count_big(*) as cnt from @FullySpecifiedCdmSchema.person where race_concept_id = 0 group by race_source_value                    
  union
  select 'person' as table_name, 'ethnicity_source_value' as column_name, ethnicity_source_value as source_value, count_big(*) as cnt from @FullySpecifiedCdmSchema.person where ethnicity_concept_id = 0 group by ethnicity_source_value                    
  union
  select 'specimen' as table_name, 'specimen_source_value' as column_name, specimen_source_value as source_value, count_big(*) as cnt from @FullySpecifiedCdmSchema.specimen where specimen_concept_id = 0 group by specimen_source_value                    
  union
  select 'specimen' as table_name, 'unit_source_value' as column_name, unit_source_value as source_value, count_big(*) as cnt from @FullySpecifiedCdmSchema.specimen where unit_concept_id = 0 group by unit_source_value                    
  union
  select 'specimen' as table_name, 'anatomic_site_source_value' as column_name, anatomic_site_source_value as source_value, count_big(*) as cnt from @FullySpecifiedCdmSchema.specimen where anatomic_site_concept_id = 0 group by anatomic_site_source_value                    
  union
  select 'specimen' as table_name, 'disease_status_source_value' as column_name, disease_status_source_value as source_value, count_big(*) as cnt from @FullySpecifiedCdmSchema.specimen where disease_status_concept_id = 0 group by disease_status_source_value                    
  
  union
  select 'visit_detail' as table_name, 'visit_detail_source_value' as column_name, visit_detail_source_value as source_value, count_big(*) as cnt from @FullySpecifiedCdmSchema.visit_detail where visit_detail_concept_id = 0 group by visit_detail_source_value
  union
  
  select 'visit_detail' as table_name, 'admitting_source_value' as column_name, admitting_source_value as source_value, count_big(*) as cnt from @FullySpecifiedCdmSchema.visit_detail where admitting_source_concept_id = 0 group by admitting_source_value
  union
  select 'visit_detail' as table_name, 'discharge_to_source_value' as column_name, discharge_to_source_value as source_value, count_big(*) as cnt from @FullySpecifiedCdmSchema.visit_detail where discharge_to_concept_id = 0 group by discharge_to_source_value
	
  
  union
  select 'visit_occurrence' as table_name, 'visit_source_value' as column_name, visit_source_value as source_value, count_big(*) as cnt from @FullySpecifiedCdmSchema.visit_occurrence where visit_concept_id = 0 group by visit_source_value
  union
  
	select 'visit_occurrence' as table_name, 'admitting_source_value' as column_name, admitting_source_value as source_value, count_big(*) as cnt from @FullySpecifiedCdmSchema.visit_occurrence where admitting_source_concept_id = 0 group by admitting_source_value
  union
  select 'visit_occurrence' as table_name, 'discharge_to_source_value' as column_name, discharge_to_source_value as source_value, count_big(*) as cnt from @FullySpecifiedCdmSchema.visit_occurrence where discharge_to_concept_id = 0 group by discharge_to_source_value
			
  union
  select 'device_exposure' as table_name, 'device_source_value' as column_name, device_source_value as source_value, count_big(*) as cnt from @FullySpecifiedCdmSchema.device_exposure where device_concept_id = 0 group by device_source_value
  union
  select 'death' as table_name, 'cause_source_value' as column_name, cause_source_value as source_value, count_big(*) as cnt from @FullySpecifiedCdmSchema.death where cause_concept_id = 0 group by cause_source_value
) a
where cnt >= 1 
;


-- 2000	patients with at least 1 Dx and 1 Rx

SELECT
	2000 AS analysis_id,
	CAST(NULL AS VARCHAR(255)) AS stratum_1,
	CAST(NULL AS VARCHAR(255)) AS stratum_2,
	CAST(NULL AS VARCHAR(255)) AS stratum_3,
	CAST(NULL AS VARCHAR(255)) AS stratum_4,
	CAST(NULL AS VARCHAR(255)) AS stratum_5,
	CAST(d.cnt AS BIGINT) AS count_value
INTO
	#s_tmpach_2000
FROM (
SELECT COUNT_BIG(*) cnt
FROM (
  SELECT DISTINCT person_id
  FROM (
    SELECT
      co.person_id
    FROM
      @FullySpecifiedCdmSchema.condition_occurrence co
    JOIN
      @FullySpecifiedCdmSchema.observation_period op
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
      @FullySpecifiedCdmSchema.drug_exposure de
    JOIN
      @FullySpecifiedCdmSchema.observation_period op
    ON
      de.person_id = op.person_id
    AND
      de.drug_exposure_start_date >= op.observation_period_start_date
    AND
      de.drug_exposure_start_date <= op.observation_period_end_date
    ) b
	) c
) d;


-- 2001	patients with at least 1 Dx and 1 Proc


SELECT 
	2001 AS analysis_id,
	CAST(NULL AS VARCHAR(255)) AS stratum_1,
	CAST(NULL AS VARCHAR(255)) AS stratum_2,
	CAST(NULL AS VARCHAR(255)) AS stratum_3,
	CAST(NULL AS VARCHAR(255)) AS stratum_4,
	CAST(NULL AS VARCHAR(255)) AS stratum_5,
	CAST(d.cnt AS BIGINT) AS count_value
INTO 
	#s_tmpach_2001
FROM (
SELECT COUNT_BIG(*) cnt
FROM (
  SELECT DISTINCT person_id
	FROM (
    SELECT
      co.person_id
    FROM
      @FullySpecifiedCdmSchema.condition_occurrence co
    JOIN
      @FullySpecifiedCdmSchema.observation_period op
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
      @FullySpecifiedCdmSchema.procedure_occurrence po
    JOIN
      @FullySpecifiedCdmSchema.observation_period op
    ON
      po.person_id = op.person_id
    AND
      po.procedure_date >= op.observation_period_start_date
    AND
      po.procedure_date <= op.observation_period_end_date
    ) b
	) c
) d;


-- 2002	patients with at least 1 Mes and 1 Dx and 1 Rx


SELECT 
	2002 AS analysis_id,
	CAST(NULL AS VARCHAR(255)) AS stratum_1,
	CAST(NULL AS VARCHAR(255)) AS stratum_2,
	CAST(NULL AS VARCHAR(255)) AS stratum_3,
	CAST(NULL AS VARCHAR(255)) AS stratum_4,
	CAST(NULL AS VARCHAR(255)) AS stratum_5,
	CAST(e.cnt AS BIGINT) AS count_value
INTO 
	#s_tmpach_2002
FROM (
SELECT COUNT_BIG(*) cnt
FROM (
	SELECT DISTINCT person_id
	FROM (
    SELECT
      m.person_id
    FROM
      @FullySpecifiedCdmSchema.measurement m
    JOIN
      @FullySpecifiedCdmSchema.observation_period op
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
      @FullySpecifiedCdmSchema.condition_occurrence co
    JOIN
      @FullySpecifiedCdmSchema.observation_period op
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
      @FullySpecifiedCdmSchema.drug_exposure de
    JOIN
      @FullySpecifiedCdmSchema.observation_period op
    ON
      de.person_id = op.person_id
    AND
      de.drug_exposure_start_date >= op.observation_period_start_date
    AND
      de.drug_exposure_start_date <= op.observation_period_end_date
    ) c
	) d
) e;


-- 2003	Patients with at least one visit
-- this analysis is in fact redundant, since it is possible to get it via
-- dist analysis 203 and query select count_value from achilles_results_dist where analysis_id = 203;


SELECT 
	2003 AS analysis_id,
	CAST(NULL AS VARCHAR(255)) AS stratum_1,
	CAST(NULL AS VARCHAR(255)) AS stratum_2,
	CAST(NULL AS VARCHAR(255)) AS stratum_3,
	CAST(NULL AS VARCHAR(255)) AS stratum_4,
	CAST(NULL AS VARCHAR(255)) AS stratum_5,
	COUNT_BIG(DISTINCT vo.person_id) AS count_value
INTO 
	#s_tmpach_2003
FROM 
	@FullySpecifiedCdmSchema.visit_occurrence vo
JOIN 
	@FullySpecifiedCdmSchema.observation_period op 
ON 
	vo.person_id = op.person_id
AND 
	vo.visit_start_date >= op.observation_period_start_date
AND 
	vo.visit_start_date <= op.observation_period_end_date;



-- Analysis 2004: Number of distinct patients that overlap between specific domains
-- Bit String Breakdown:   1) Condition Occurrence 2) Drug Exposure 3) Device Exposure 4) Measurement 5) Death 6) Procedure Occurrence 7) Observation

select distinct person_id into #conoc from @FullySpecifiedCdmSchema.condition_occurrence;
select distinct person_id into #drexp from @FullySpecifiedCdmSchema.drug_exposure;
select distinct person_id into #dvexp from @FullySpecifiedCdmSchema.device_exposure;
select distinct person_id into #msmt from @FullySpecifiedCdmSchema.measurement;
select distinct person_id into #death from @FullySpecifiedCdmSchema.death;
select distinct person_id into #prococ from @FullySpecifiedCdmSchema.procedure_occurrence;
select distinct person_id into #obs from @FullySpecifiedCdmSchema.observation;

with rawData as (
select 2004 as analysis_id,
       CAST('0000001' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #obs) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('0000010' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #prococ) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('0000011' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #prococ intersect select person_id from #obs) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('0000100' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #death) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('0000101' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #death intersect select person_id from #obs) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('0000110' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #death intersect select person_id from #prococ) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('0000111' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #death intersect select person_id from #prococ intersect select person_id from #obs) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('0001000' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #msmt) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('0001001' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #msmt intersect select person_id from #obs) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('0001010' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #msmt intersect select person_id from #prococ) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('0001011' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #msmt intersect select person_id from #prococ intersect select person_id from #obs) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('0001100' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #msmt intersect select person_id from #death) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('0001101' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #msmt intersect select person_id from #death intersect select person_id from #obs) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('0001110' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #msmt intersect select person_id from #death intersect select person_id from #prococ) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('0001111' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #msmt intersect select person_id from #death intersect select person_id from #prococ intersect select person_id from #obs) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('0010000' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #dvexp) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('0010001' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #dvexp intersect select person_id from #obs) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('0010010' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #dvexp intersect select person_id from #prococ) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('0010011' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #dvexp intersect select person_id from #prococ intersect select person_id from #obs) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('0010100' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #dvexp intersect select person_id from #death) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('0010101' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #dvexp intersect select person_id from #death intersect select person_id from #obs) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('0010110' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #dvexp intersect select person_id from #death intersect select person_id from #prococ) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('0010111' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #dvexp intersect select person_id from #death intersect select person_id from #prococ intersect select person_id from #obs) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('0011000' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #dvexp intersect select person_id from #msmt) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('0011001' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #dvexp intersect select person_id from #msmt intersect select person_id from #obs) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('0011010' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #dvexp intersect select person_id from #msmt intersect select person_id from #prococ) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('0011011' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #dvexp intersect select person_id from #msmt intersect select person_id from #prococ intersect select person_id from #obs) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('0011100' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #dvexp intersect select person_id from #msmt intersect select person_id from #death) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('0011101' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #dvexp intersect select person_id from #msmt intersect select person_id from #death intersect select person_id from #obs) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('0011110' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #dvexp intersect select person_id from #msmt intersect select person_id from #death intersect select person_id from #prococ) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('0011111' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #dvexp intersect select person_id from #msmt intersect select person_id from #death intersect select person_id from #prococ intersect select person_id from #obs) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('0100000' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #drexp) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('0100001' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #drexp intersect select person_id from #obs) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('0100010' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #drexp intersect select person_id from #prococ) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('0100011' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #drexp intersect select person_id from #prococ intersect select person_id from #obs) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('0100100' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #drexp intersect select person_id from #death) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('0100101' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #drexp intersect select person_id from #death intersect select person_id from #obs) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('0100110' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #drexp intersect select person_id from #death intersect select person_id from #prococ) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('0100111' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #drexp intersect select person_id from #death intersect select person_id from #prococ intersect select person_id from #obs) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('0101000' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #drexp intersect select person_id from #msmt) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('0101001' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #drexp intersect select person_id from #msmt intersect select person_id from #obs) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('0101010' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #drexp intersect select person_id from #msmt intersect select person_id from #prococ) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('0101011' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #drexp intersect select person_id from #msmt intersect select person_id from #prococ intersect select person_id from #obs) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('0101100' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #drexp intersect select person_id from #msmt intersect select person_id from #death) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('0101101' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #drexp intersect select person_id from #msmt intersect select person_id from #death intersect select person_id from #obs) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('0101110' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #drexp intersect select person_id from #msmt intersect select person_id from #death intersect select person_id from #prococ) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('0101111' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #drexp intersect select person_id from #msmt intersect select person_id from #death intersect select person_id from #prococ intersect select person_id from #obs) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('0110000' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #drexp intersect select person_id from #dvexp) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('0110001' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #drexp intersect select person_id from #dvexp intersect select person_id from #obs) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('0110010' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #drexp intersect select person_id from #dvexp intersect select person_id from #prococ) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('0110011' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #drexp intersect select person_id from #dvexp intersect select person_id from #prococ intersect select person_id from #obs) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('0110100' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #drexp intersect select person_id from #dvexp intersect select person_id from #death) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('0110101' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #drexp intersect select person_id from #dvexp intersect select person_id from #death intersect select person_id from #obs) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('0110110' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #drexp intersect select person_id from #dvexp intersect select person_id from #death intersect select person_id from #prococ) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('0110111' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #drexp intersect select person_id from #dvexp intersect select person_id from #death intersect select person_id from #prococ intersect select person_id from #obs) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('0111000' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #drexp intersect select person_id from #dvexp intersect select person_id from #msmt) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('0111001' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #drexp intersect select person_id from #dvexp intersect select person_id from #msmt intersect select person_id from #obs) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('0111010' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #drexp intersect select person_id from #dvexp intersect select person_id from #msmt intersect select person_id from #prococ) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('0111011' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #drexp intersect select person_id from #dvexp intersect select person_id from #msmt intersect select person_id from #prococ intersect select person_id from #obs) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('0111100' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #drexp intersect select person_id from #dvexp intersect select person_id from #msmt intersect select person_id from #death) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('0111101' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #drexp intersect select person_id from #dvexp intersect select person_id from #msmt intersect select person_id from #death intersect select person_id from #obs) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('0111110' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #drexp intersect select person_id from #dvexp intersect select person_id from #msmt intersect select person_id from #death intersect select person_id from #prococ) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('0111111' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #drexp intersect select person_id from #dvexp intersect select person_id from #msmt intersect select person_id from #death intersect select person_id from #prococ intersect select person_id from #obs) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('1000000' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #conoc) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('1000001' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #conoc intersect select person_id from #obs) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('1000010' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #conoc intersect select person_id from #prococ) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('1000011' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #conoc intersect select person_id from #prococ intersect select person_id from #obs) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('1000100' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #conoc intersect select person_id from #death) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('1000101' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #conoc intersect select person_id from #death intersect select person_id from #obs) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('1000110' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #conoc intersect select person_id from #death intersect select person_id from #prococ) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('1000111' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #conoc intersect select person_id from #death intersect select person_id from #prococ intersect select person_id from #obs) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('1001000' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #conoc intersect select person_id from #msmt) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('1001001' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #conoc intersect select person_id from #msmt intersect select person_id from #obs) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('1001010' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #conoc intersect select person_id from #msmt intersect select person_id from #prococ) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('1001011' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #conoc intersect select person_id from #msmt intersect select person_id from #prococ intersect select person_id from #obs) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('1001100' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #conoc intersect select person_id from #msmt intersect select person_id from #death) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('1001101' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #conoc intersect select person_id from #msmt intersect select person_id from #death intersect select person_id from #obs) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('1001110' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #conoc intersect select person_id from #msmt intersect select person_id from #death intersect select person_id from #prococ) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('1001111' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #conoc intersect select person_id from #msmt intersect select person_id from #death intersect select person_id from #prococ intersect select person_id from #obs) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('1010000' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #conoc intersect select person_id from #dvexp) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('1010001' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #conoc intersect select person_id from #dvexp intersect select person_id from #obs) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('1010010' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #conoc intersect select person_id from #dvexp intersect select person_id from #prococ) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('1010011' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #conoc intersect select person_id from #dvexp intersect select person_id from #prococ intersect select person_id from #obs) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('1010100' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #conoc intersect select person_id from #dvexp intersect select person_id from #death) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('1010101' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #conoc intersect select person_id from #dvexp intersect select person_id from #death intersect select person_id from #obs) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('1010110' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #conoc intersect select person_id from #dvexp intersect select person_id from #death intersect select person_id from #prococ) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('1010111' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #conoc intersect select person_id from #dvexp intersect select person_id from #death intersect select person_id from #prococ intersect select person_id from #obs) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('1011000' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #conoc intersect select person_id from #dvexp intersect select person_id from #msmt) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('1011001' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #conoc intersect select person_id from #dvexp intersect select person_id from #msmt intersect select person_id from #obs) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('1011010' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #conoc intersect select person_id from #dvexp intersect select person_id from #msmt intersect select person_id from #prococ) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('1011011' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #conoc intersect select person_id from #dvexp intersect select person_id from #msmt intersect select person_id from #prococ intersect select person_id from #obs) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('1011100' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #conoc intersect select person_id from #dvexp intersect select person_id from #msmt intersect select person_id from #death) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('1011101' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #conoc intersect select person_id from #dvexp intersect select person_id from #msmt intersect select person_id from #death intersect select person_id from #obs) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('1011110' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #conoc intersect select person_id from #dvexp intersect select person_id from #msmt intersect select person_id from #death intersect select person_id from #prococ) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('1011111' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #conoc intersect select person_id from #dvexp intersect select person_id from #msmt intersect select person_id from #death intersect select person_id from #prococ intersect select person_id from #obs) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('1100000' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #conoc intersect select person_id from #drexp) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('1100001' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #conoc intersect select person_id from #drexp intersect select person_id from #obs) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('1100010' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #conoc intersect select person_id from #drexp intersect select person_id from #prococ) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('1100011' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #conoc intersect select person_id from #drexp intersect select person_id from #prococ intersect select person_id from #obs) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('1100100' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #conoc intersect select person_id from #drexp intersect select person_id from #death) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('1100101' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #conoc intersect select person_id from #drexp intersect select person_id from #death intersect select person_id from #obs) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('1100110' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #conoc intersect select person_id from #drexp intersect select person_id from #death intersect select person_id from #prococ) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('1100111' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #conoc intersect select person_id from #drexp intersect select person_id from #death intersect select person_id from #prococ intersect select person_id from #obs) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('1101000' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #conoc intersect select person_id from #drexp intersect select person_id from #msmt) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('1101001' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #conoc intersect select person_id from #drexp intersect select person_id from #msmt intersect select person_id from #obs) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('1101010' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #conoc intersect select person_id from #drexp intersect select person_id from #msmt intersect select person_id from #prococ) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('1101011' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #conoc intersect select person_id from #drexp intersect select person_id from #msmt intersect select person_id from #prococ intersect select person_id from #obs) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('1101100' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #conoc intersect select person_id from #drexp intersect select person_id from #msmt intersect select person_id from #death) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('1101101' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #conoc intersect select person_id from #drexp intersect select person_id from #msmt intersect select person_id from #death intersect select person_id from #obs) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('1101110' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #conoc intersect select person_id from #drexp intersect select person_id from #msmt intersect select person_id from #death intersect select person_id from #prococ) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('1101111' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #conoc intersect select person_id from #drexp intersect select person_id from #msmt intersect select person_id from #death intersect select person_id from #prococ intersect select person_id from #obs) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('1110000' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #conoc intersect select person_id from #drexp intersect select person_id from #dvexp) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('1110001' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #conoc intersect select person_id from #drexp intersect select person_id from #dvexp intersect select person_id from #obs) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('1110010' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #conoc intersect select person_id from #drexp intersect select person_id from #dvexp intersect select person_id from #prococ) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('1110011' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #conoc intersect select person_id from #drexp intersect select person_id from #dvexp intersect select person_id from #prococ intersect select person_id from #obs) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('1110100' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #conoc intersect select person_id from #drexp intersect select person_id from #dvexp intersect select person_id from #death) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('1110101' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #conoc intersect select person_id from #drexp intersect select person_id from #dvexp intersect select person_id from #death intersect select person_id from #obs) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('1110110' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #conoc intersect select person_id from #drexp intersect select person_id from #dvexp intersect select person_id from #death intersect select person_id from #prococ) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('1110111' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #conoc intersect select person_id from #drexp intersect select person_id from #dvexp intersect select person_id from #death intersect select person_id from #prococ intersect select person_id from #obs) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('1111000' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #conoc intersect select person_id from #drexp intersect select person_id from #dvexp intersect select person_id from #msmt) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('1111001' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #conoc intersect select person_id from #drexp intersect select person_id from #dvexp intersect select person_id from #msmt intersect select person_id from #obs) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('1111010' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #conoc intersect select person_id from #drexp intersect select person_id from #dvexp intersect select person_id from #msmt intersect select person_id from #prococ) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('1111011' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #conoc intersect select person_id from #drexp intersect select person_id from #dvexp intersect select person_id from #msmt intersect select person_id from #prococ intersect select person_id from #obs) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('1111100' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #conoc intersect select person_id from #drexp intersect select person_id from #dvexp intersect select person_id from #msmt intersect select person_id from #death) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('1111101' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #conoc intersect select person_id from #drexp intersect select person_id from #dvexp intersect select person_id from #msmt intersect select person_id from #death intersect select person_id from #obs) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('1111110' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #conoc intersect select person_id from #drexp intersect select person_id from #dvexp intersect select person_id from #msmt intersect select person_id from #death intersect select person_id from #prococ) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) totalPersonsDb UNION ALL
select 2004 as analysis_id,
       CAST('1111111' AS VARCHAR(255)) as stratum_1,
       cast((1.0 * personIntersection.count_value / totalPersonsDb.totalPersons) as varchar(255)) as stratum_2,
       CAST(NULL AS VARCHAR(255)) as stratum_3,
       CAST(NULL AS VARCHAR(255)) as stratum_4,
       CAST(NULL AS VARCHAR(255)) as stratum_5,
       personIntersection.count_value
      from
      (select count(*) as count_value from(select person_id from #conoc intersect select person_id from #drexp intersect select person_id from #dvexp intersect select person_id from #msmt intersect select person_id from #death intersect select person_id from #prococ intersect select person_id from #obs) subquery) personIntersection,
  (select count(distinct(person_id)) as totalPersons from @FullySpecifiedCdmSchema.person) as totalPersonsDb) select * INTO #s_tmpach_2004 from rawData;

drop table #conoc;
drop table #drexp;
drop table #dvexp;
drop table #msmt;
drop table #death;
drop table #prococ;
drop table #obs;


-- 2100	Number of persons with at least one device exposure , by device_concept_id

--HINT DISTRIBUTE_ON_KEY(stratum_1)
SELECT 
	2100 AS analysis_id,
	CAST(de.device_concept_id AS VARCHAR(255)) AS stratum_1,
	CAST(NULL AS VARCHAR(255)) AS stratum_2,
	CAST(NULL AS VARCHAR(255)) AS stratum_3,
	CAST(NULL AS VARCHAR(255)) AS stratum_4,
	CAST(NULL AS VARCHAR(255)) AS stratum_5,
	COUNT_BIG(DISTINCT de.person_id) AS count_value
INTO 
	#s_tmpach_2100
FROM 
	@FullySpecifiedCdmSchema.device_exposure de
JOIN 
	@FullySpecifiedCdmSchema.observation_period op 
ON 
	de.person_id = op.person_id
AND 
	de.device_exposure_start_date >= op.observation_period_start_date
AND 
	de.device_exposure_start_date <= op.observation_period_end_date		
GROUP BY 
	de.device_concept_id;


-- 2101	Number of device exposure  records, by device_concept_id

--HINT DISTRIBUTE_ON_KEY(stratum_1)
SELECT 
	2101 AS analysis_id,
	CAST(de.device_concept_id AS VARCHAR(255)) AS stratum_1,
	CAST(NULL AS VARCHAR(255)) AS stratum_2,
	CAST(NULL AS VARCHAR(255)) AS stratum_3,
	CAST(NULL AS VARCHAR(255)) AS stratum_4,
	CAST(NULL AS VARCHAR(255)) AS stratum_5,
	COUNT_BIG(de.person_id) AS count_value
INTO 
	#s_tmpach_2101
FROM 
	@FullySpecifiedCdmSchema.device_exposure de
JOIN 
	@FullySpecifiedCdmSchema.observation_period op 
ON 
	de.person_id = op.person_id
AND 
	de.device_exposure_start_date >= op.observation_period_start_date
AND 
	de.device_exposure_start_date <= op.observation_period_end_date		
GROUP BY 
	de.device_concept_id;


-- 2102	Number of persons by device by  start month, by device_concept_id

--HINT DISTRIBUTE_ON_KEY(stratum_1)
WITH rawData AS (
SELECT 
	de.device_concept_id AS stratum_1,
	YEAR(de.device_exposure_start_date) * 100 + MONTH(de.device_exposure_start_date) AS stratum_2,
	COUNT_BIG(DISTINCT de.person_id) AS count_value
FROM 
	@FullySpecifiedCdmSchema.device_exposure de
JOIN 
	@FullySpecifiedCdmSchema.observation_period op 
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
  CAST(stratum_1 AS VARCHAR(255)) AS stratum_1,
  CAST(stratum_2 AS VARCHAR(255)) AS stratum_2,
  CAST(NULL AS VARCHAR(255)) AS stratum_3,
  CAST(NULL AS VARCHAR(255)) AS stratum_4,
  CAST(NULL AS VARCHAR(255)) AS stratum_5,
  count_value
INTO 
	#s_tmpach_2102
FROM 
	rawData;


-- 2104	Number of persons with at least one device occurrence, by device_concept_id by calendar year by gender by age decile

--HINT DISTRIBUTE_ON_KEY(stratum_1)
WITH rawData AS (
SELECT 
	de.device_concept_id AS stratum_1,
	YEAR(de.device_exposure_start_date) AS stratum_2,
	p.gender_concept_id AS stratum_3,
	FLOOR((YEAR(de.device_exposure_start_date) - p.year_of_birth) / 10) AS stratum_4,
	COUNT_BIG(DISTINCT p.person_id) AS count_value
FROM 
	@FullySpecifiedCdmSchema.person p
JOIN 
	@FullySpecifiedCdmSchema.device_exposure de 
ON 
	p.person_id = de.person_id
JOIN 
	@FullySpecifiedCdmSchema.observation_period op 
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
	CAST(stratum_1 AS VARCHAR(255)) AS stratum_1,
	CAST(stratum_2 AS VARCHAR(255)) AS stratum_2,
	CAST(stratum_3 AS VARCHAR(255)) AS stratum_3,
	CAST(stratum_4 AS VARCHAR(255)) AS stratum_4,
	CAST(NULL AS VARCHAR(255)) AS stratum_5,
	count_value
INTO 
	#s_tmpach_2104
FROM 
	rawData;


-- 2105	Number of exposure records by device_concept_id by device_type_concept_id

--HINT DISTRIBUTE_ON_KEY(stratum_1)
SELECT 
	2105 AS analysis_id,
	CAST(de.device_concept_id AS VARCHAR(255)) AS stratum_1,
	CAST(de.device_type_concept_id AS VARCHAR(255)) AS stratum_2,
	CAST(NULL AS VARCHAR(255)) AS stratum_3,
	CAST(NULL AS VARCHAR(255)) AS stratum_4,
	CAST(NULL AS VARCHAR(255)) AS stratum_5,
	COUNT_BIG(de.person_id) AS count_value
INTO 
	#s_tmpach_2105
FROM 
	@FullySpecifiedCdmSchema.device_exposure de
JOIN 
	@FullySpecifiedCdmSchema.observation_period op 
ON 
	de.person_id = op.person_id
AND 
	de.device_exposure_start_date >= op.observation_period_start_date
AND 
	de.device_exposure_start_date <= op.observation_period_end_date		
GROUP BY 
	de.device_concept_id,
	de.device_type_concept_id;


-- 2106	Distribution of age by device_concept_id

--HINT DISTRIBUTE_ON_KEY(subject_id)
SELECT 
	o.device_concept_id AS subject_id,
	p.gender_concept_id,
	o.device_exposure_start_year - p.year_of_birth AS count_value
INTO 
	#rawData_2106
FROM 
	@FullySpecifiedCdmSchema.person p
JOIN (
	SELECT 
		d.person_id,
		d.device_concept_id,
		MIN(YEAR(d.device_exposure_start_date)) AS device_exposure_start_year
	FROM 
		@FullySpecifiedCdmSchema.device_exposure d
	JOIN 
		@FullySpecifiedCdmSchema.observation_period op 
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

--HINT DISTRIBUTE_ON_KEY(stratum1_id)
with overallStats (stratum1_id, stratum2_id, avg_value, stdev_value, min_value, max_value, total) as
(
  select subject_id as stratum1_id,
    gender_concept_id as stratum2_id,
    CAST(avg(1.0 * count_value) AS FLOAT) as avg_value,
    CAST(stdev(count_value) AS FLOAT) as stdev_value,
    min(count_value) as min_value,
    max(count_value) as max_value,
    count_big(*) as total
  FROM #rawData_2106
	group by subject_id, gender_concept_id
),
statsView (stratum1_id, stratum2_id, count_value, total, rn) as
(
  select subject_id as stratum1_id, gender_concept_id as stratum2_id, count_value, count_big(*) as total, row_number() over (partition by subject_id, gender_concept_id order by count_value) as rn
  FROM #rawData_2106
  group by subject_id, gender_concept_id, count_value
),
priorStats (stratum1_id, stratum2_id, count_value, total, accumulated) as
(
  select s.stratum1_id, s.stratum2_id, s.count_value, s.total, sum(p.total) as accumulated
  from statsView s
  join statsView p on s.stratum1_id = p.stratum1_id and s.stratum2_id = p.stratum2_id and p.rn <= s.rn
  group by s.stratum1_id, s.stratum2_id, s.count_value, s.total, s.rn
)
select 2106 as analysis_id,
  CAST(o.stratum1_id AS VARCHAR(255)) AS stratum1_id,
  CAST(o.stratum2_id AS VARCHAR(255)) AS stratum2_id,
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
into #tempResults_2106
from priorStats p
join overallStats o on p.stratum1_id = o.stratum1_id and p.stratum2_id = o.stratum2_id 
GROUP BY o.stratum1_id, o.stratum2_id, o.total, o.min_value, o.max_value, o.avg_value, o.stdev_value
;

--HINT DISTRIBUTE_ON_KEY(stratum_1)
select analysis_id, stratum1_id as stratum_1, stratum2_id as stratum_2, 
cast(null as varchar(255)) as stratum_3, cast(null as varchar(255)) as stratum_4, cast(null as varchar(255)) as stratum_5,
count_value, min_value, max_value, avg_value, stdev_value, median_value, p10_value, p25_value, p75_value, p90_value
into #s_tmpach_dist_2106
from #tempResults_2106
;

truncate table #rawData_2106;
drop table #rawData_2106;

truncate table #tempResults_2106;
drop table #tempResults_2106;


-- 2120	Number of device exposure records by device exposure start month

--HINT DISTRIBUTE_ON_KEY(stratum_1)
WITH rawData AS (
SELECT 
	YEAR(de.device_exposure_start_date) * 100 + MONTH(de.device_exposure_start_date) AS stratum_1,
	COUNT_BIG(de.person_id) AS count_value
FROM 
	@FullySpecifiedCdmSchema.device_exposure de
JOIN 
	@FullySpecifiedCdmSchema.observation_period op 
ON 
	de.person_id = op.person_id
AND 
	de.device_exposure_start_date >= op.observation_period_start_date
AND 
	de.device_exposure_start_date <= op.observation_period_end_date	
GROUP BY 
	YEAR(de.device_exposure_start_date)*100 + MONTH(de.device_exposure_start_date)
)
SELECT
	2120 AS analysis_id,
	CAST(stratum_1 AS VARCHAR(255)) AS stratum_1,
	CAST(NULL AS VARCHAR(255)) AS stratum_2,
	CAST(NULL AS VARCHAR(255)) AS stratum_3,
	CAST(NULL AS VARCHAR(255)) AS stratum_4,
	CAST(NULL AS VARCHAR(255)) AS stratum_5,
	count_value
INTO 
	#s_tmpach_2120
FROM 
	rawData;


-- 2125	Number of device_exposure records, by device_source_concept_id

--HINT DISTRIBUTE_ON_KEY(stratum_1)
SELECT 
	2125 AS analysis_id,
	CAST(de.device_source_concept_id AS VARCHAR(255)) AS stratum_1,
	CAST(NULL AS VARCHAR(255)) AS stratum_2,
	CAST(NULL AS VARCHAR(255)) AS stratum_3,
	CAST(NULL AS VARCHAR(255)) AS stratum_4,
	CAST(NULL AS VARCHAR(255)) AS stratum_5,
	COUNT_BIG(*) AS count_value
INTO 
	#s_tmpach_2125
FROM 
	@FullySpecifiedCdmSchema.device_exposure de
JOIN 
	@FullySpecifiedCdmSchema.observation_period op 
ON 
	de.person_id = op.person_id
AND 
	de.device_exposure_start_date >= op.observation_period_start_date
AND 
	de.device_exposure_start_date <= op.observation_period_end_date		
GROUP BY 
	de.device_source_concept_id;


-- 2191	Number of total persons that have at least x measurements

--HINT DISTRIBUTE_ON_KEY(stratum_1)
SELECT 
	2191 AS analysis_id,
	CAST(d.device_concept_id AS VARCHAR(255)) AS stratum_1,
	CAST(d.device_count AS VARCHAR(255)) AS stratum_2,
	CAST(NULL AS VARCHAR(255)) AS stratum_3,
	CAST(NULL AS VARCHAR(255)) AS stratum_4,
	CAST(NULL AS VARCHAR(255)) AS stratum_5,
	SUM(COUNT(d.person_id)) OVER (PARTITION BY d.device_concept_id ORDER BY d.device_count DESC) AS count_value
INTO 
	#s_tmpach_2191
FROM (
	SELECT 
		d.device_concept_id,
		COUNT(d.device_exposure_id) AS device_count,
		d.person_id
	FROM 
		@FullySpecifiedCdmSchema.device_exposure d
	JOIN 
		@FullySpecifiedCdmSchema.observation_period op 
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


-- 2200	Number of persons with at least one note , by note_type_concept_id

--HINT DISTRIBUTE_ON_KEY(stratum_1)
select 2200 as analysis_id, 
	CAST(m.note_type_CONCEPT_ID AS VARCHAR(255)) as stratum_1,
	cast(null as varchar(255)) as stratum_2, cast(null as varchar(255)) as stratum_3, cast(null as varchar(255)) as stratum_4, cast(null as varchar(255)) as stratum_5,
	COUNT_BIG(distinct m.PERSON_ID) as count_value
into #s_tmpach_2200
from
	@FullySpecifiedCdmSchema.note m
group by m.note_type_CONCEPT_ID
;


-- 2201	Number of note records, by note_type_concept_id

--HINT DISTRIBUTE_ON_KEY(stratum_1)
select 2201 as analysis_id, 
    CAST(m.note_type_CONCEPT_ID AS VARCHAR(255)) as stratum_1,
	cast(null as varchar(255)) as stratum_2, cast(null as varchar(255)) as stratum_3, cast(null as varchar(255)) as stratum_4, cast(null as varchar(255)) as stratum_5,
	COUNT_BIG(m.PERSON_ID) as count_value
into #s_tmpach_2201
from
	@FullySpecifiedCdmSchema.note m
group by m.note_type_CONCEPT_ID
;



  IF OBJECT_ID('@FullySpecifiedAchillesResultsSchema.achilles_results', 'U') IS NOT NULL
    drop table @FullySpecifiedAchillesResultsSchema.achilles_results;

--HINT DISTRIBUTE_ON_KEY(analysis_id)

select analysis_id, stratum_1, stratum_2, stratum_3, stratum_4, stratum_5, count_value

  into @FullySpecifiedAchillesResultsSchema.achilles_results

from 
(
  select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_0 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_1 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_2 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_3 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_4 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_5 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_10 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_11 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_12 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_101 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_102 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_108 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_109 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_110 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_111 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_112 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_113 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_116 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_117 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_119 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_200 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_201 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_202 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_204 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_207 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_209 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_210 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_212 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_220 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_221 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_225 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_226 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_300 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_301 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_303 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_325 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_400 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_401 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_402 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_404 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_405 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_414 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_415 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_416 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_420 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_425 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_500 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_501 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_502 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_504 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_505 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_525 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_600 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_601 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_602 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_604 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_605 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_620 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_625 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_630 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_691 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_700 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_701 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_702 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_704 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_705 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_720 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_725 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_791 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_800 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_801 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_802 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_804 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_805 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_807 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_814 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_820 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_822 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_823 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_825 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_826 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_827 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_891 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_900 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_901 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_902 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_904 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_920 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_1000 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_1001 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_1002 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_1004 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_1020 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_1100 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_1101 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_1102 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_1103 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_1200 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_1201 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_1202 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_1203 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_1300 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_1301 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_1302 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_1304 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_1312 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_1320 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_1321 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_1325 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_1326 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_1408 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_1409 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_1410 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_1411 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_1412 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_1413 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_1425 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_1800 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_1801 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_1802 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_1804 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_1805 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_1807 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_1811 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_1814 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_1818 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_1819 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_1820 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_1821 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_1822 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_1823 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_1825 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_1826 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_1827 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_1891 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_1900 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_2000 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_2001 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_2002 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_2003 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_2004 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_2100 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_2101 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_2102 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_2104 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_2105 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_2120 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_2125 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_2191 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_2200 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value from
                   #s_tmpach_2201
) Q

  where count_value > 5

;



  IF OBJECT_ID('@FullySpecifiedAchillesResultsSchema.achilles_results_dist', 'U') IS NOT NULL
    drop table @FullySpecifiedAchillesResultsSchema.achilles_results_dist;

--HINT DISTRIBUTE_ON_KEY(analysis_id)

select analysis_id, stratum_1, stratum_2, stratum_3, stratum_4, stratum_5, count_value, min_value, max_value, avg_value, stdev_value, median_value, p10_value, p25_value, p75_value, p90_value

  into @FullySpecifiedAchillesResultsSchema.achilles_results_dist

from 
(
  select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value, cast(min_value as float) as min_value, cast(max_value as float) as max_value, cast(avg_value as float) as avg_value, cast(stdev_value as float) as stdev_value, cast(median_value as float) as median_value, cast(p10_value as float) as p10_value, cast(p25_value as float) as p25_value, cast(p75_value as float) as p75_value, cast(p90_value as float) as p90_value from
                   #s_tmpach_dist_0 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value, cast(min_value as float) as min_value, cast(max_value as float) as max_value, cast(avg_value as float) as avg_value, cast(stdev_value as float) as stdev_value, cast(median_value as float) as median_value, cast(p10_value as float) as p10_value, cast(p25_value as float) as p25_value, cast(p75_value as float) as p75_value, cast(p90_value as float) as p90_value from
                   #s_tmpach_dist_103 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value, cast(min_value as float) as min_value, cast(max_value as float) as max_value, cast(avg_value as float) as avg_value, cast(stdev_value as float) as stdev_value, cast(median_value as float) as median_value, cast(p10_value as float) as p10_value, cast(p25_value as float) as p25_value, cast(p75_value as float) as p75_value, cast(p90_value as float) as p90_value from
                   #s_tmpach_dist_104 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value, cast(min_value as float) as min_value, cast(max_value as float) as max_value, cast(avg_value as float) as avg_value, cast(stdev_value as float) as stdev_value, cast(median_value as float) as median_value, cast(p10_value as float) as p10_value, cast(p25_value as float) as p25_value, cast(p75_value as float) as p75_value, cast(p90_value as float) as p90_value from
                   #s_tmpach_dist_105 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value, cast(min_value as float) as min_value, cast(max_value as float) as max_value, cast(avg_value as float) as avg_value, cast(stdev_value as float) as stdev_value, cast(median_value as float) as median_value, cast(p10_value as float) as p10_value, cast(p25_value as float) as p25_value, cast(p75_value as float) as p75_value, cast(p90_value as float) as p90_value from
                   #s_tmpach_dist_106 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value, cast(min_value as float) as min_value, cast(max_value as float) as max_value, cast(avg_value as float) as avg_value, cast(stdev_value as float) as stdev_value, cast(median_value as float) as median_value, cast(p10_value as float) as p10_value, cast(p25_value as float) as p25_value, cast(p75_value as float) as p75_value, cast(p90_value as float) as p90_value from
                   #s_tmpach_dist_107 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value, cast(min_value as float) as min_value, cast(max_value as float) as max_value, cast(avg_value as float) as avg_value, cast(stdev_value as float) as stdev_value, cast(median_value as float) as median_value, cast(p10_value as float) as p10_value, cast(p25_value as float) as p25_value, cast(p75_value as float) as p75_value, cast(p90_value as float) as p90_value from
                   #s_tmpach_dist_203 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value, cast(min_value as float) as min_value, cast(max_value as float) as max_value, cast(avg_value as float) as avg_value, cast(stdev_value as float) as stdev_value, cast(median_value as float) as median_value, cast(p10_value as float) as p10_value, cast(p25_value as float) as p25_value, cast(p75_value as float) as p75_value, cast(p90_value as float) as p90_value from
                   #s_tmpach_dist_206 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value, cast(min_value as float) as min_value, cast(max_value as float) as max_value, cast(avg_value as float) as avg_value, cast(stdev_value as float) as stdev_value, cast(median_value as float) as median_value, cast(p10_value as float) as p10_value, cast(p25_value as float) as p25_value, cast(p75_value as float) as p75_value, cast(p90_value as float) as p90_value from
                   #s_tmpach_dist_213 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value, cast(min_value as float) as min_value, cast(max_value as float) as max_value, cast(avg_value as float) as avg_value, cast(stdev_value as float) as stdev_value, cast(median_value as float) as median_value, cast(p10_value as float) as p10_value, cast(p25_value as float) as p25_value, cast(p75_value as float) as p75_value, cast(p90_value as float) as p90_value from
                   #s_tmpach_dist_403 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value, cast(min_value as float) as min_value, cast(max_value as float) as max_value, cast(avg_value as float) as avg_value, cast(stdev_value as float) as stdev_value, cast(median_value as float) as median_value, cast(p10_value as float) as p10_value, cast(p25_value as float) as p25_value, cast(p75_value as float) as p75_value, cast(p90_value as float) as p90_value from
                   #s_tmpach_dist_406 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value, cast(min_value as float) as min_value, cast(max_value as float) as max_value, cast(avg_value as float) as avg_value, cast(stdev_value as float) as stdev_value, cast(median_value as float) as median_value, cast(p10_value as float) as p10_value, cast(p25_value as float) as p25_value, cast(p75_value as float) as p75_value, cast(p90_value as float) as p90_value from
                   #s_tmpach_dist_506 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value, cast(min_value as float) as min_value, cast(max_value as float) as max_value, cast(avg_value as float) as avg_value, cast(stdev_value as float) as stdev_value, cast(median_value as float) as median_value, cast(p10_value as float) as p10_value, cast(p25_value as float) as p25_value, cast(p75_value as float) as p75_value, cast(p90_value as float) as p90_value from
                   #s_tmpach_dist_511 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value, cast(min_value as float) as min_value, cast(max_value as float) as max_value, cast(avg_value as float) as avg_value, cast(stdev_value as float) as stdev_value, cast(median_value as float) as median_value, cast(p10_value as float) as p10_value, cast(p25_value as float) as p25_value, cast(p75_value as float) as p75_value, cast(p90_value as float) as p90_value from
                   #s_tmpach_dist_512 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value, cast(min_value as float) as min_value, cast(max_value as float) as max_value, cast(avg_value as float) as avg_value, cast(stdev_value as float) as stdev_value, cast(median_value as float) as median_value, cast(p10_value as float) as p10_value, cast(p25_value as float) as p25_value, cast(p75_value as float) as p75_value, cast(p90_value as float) as p90_value from
                   #s_tmpach_dist_513 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value, cast(min_value as float) as min_value, cast(max_value as float) as max_value, cast(avg_value as float) as avg_value, cast(stdev_value as float) as stdev_value, cast(median_value as float) as median_value, cast(p10_value as float) as p10_value, cast(p25_value as float) as p25_value, cast(p75_value as float) as p75_value, cast(p90_value as float) as p90_value from
                   #s_tmpach_dist_514 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value, cast(min_value as float) as min_value, cast(max_value as float) as max_value, cast(avg_value as float) as avg_value, cast(stdev_value as float) as stdev_value, cast(median_value as float) as median_value, cast(p10_value as float) as p10_value, cast(p25_value as float) as p25_value, cast(p75_value as float) as p75_value, cast(p90_value as float) as p90_value from
                   #s_tmpach_dist_515 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value, cast(min_value as float) as min_value, cast(max_value as float) as max_value, cast(avg_value as float) as avg_value, cast(stdev_value as float) as stdev_value, cast(median_value as float) as median_value, cast(p10_value as float) as p10_value, cast(p25_value as float) as p25_value, cast(p75_value as float) as p75_value, cast(p90_value as float) as p90_value from
                   #s_tmpach_dist_603 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value, cast(min_value as float) as min_value, cast(max_value as float) as max_value, cast(avg_value as float) as avg_value, cast(stdev_value as float) as stdev_value, cast(median_value as float) as median_value, cast(p10_value as float) as p10_value, cast(p25_value as float) as p25_value, cast(p75_value as float) as p75_value, cast(p90_value as float) as p90_value from
                   #s_tmpach_dist_606 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value, cast(min_value as float) as min_value, cast(max_value as float) as max_value, cast(avg_value as float) as avg_value, cast(stdev_value as float) as stdev_value, cast(median_value as float) as median_value, cast(p10_value as float) as p10_value, cast(p25_value as float) as p25_value, cast(p75_value as float) as p75_value, cast(p90_value as float) as p90_value from
                   #s_tmpach_dist_703 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value, cast(min_value as float) as min_value, cast(max_value as float) as max_value, cast(avg_value as float) as avg_value, cast(stdev_value as float) as stdev_value, cast(median_value as float) as median_value, cast(p10_value as float) as p10_value, cast(p25_value as float) as p25_value, cast(p75_value as float) as p75_value, cast(p90_value as float) as p90_value from
                   #s_tmpach_dist_706 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value, cast(min_value as float) as min_value, cast(max_value as float) as max_value, cast(avg_value as float) as avg_value, cast(stdev_value as float) as stdev_value, cast(median_value as float) as median_value, cast(p10_value as float) as p10_value, cast(p25_value as float) as p25_value, cast(p75_value as float) as p75_value, cast(p90_value as float) as p90_value from
                   #s_tmpach_dist_715 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value, cast(min_value as float) as min_value, cast(max_value as float) as max_value, cast(avg_value as float) as avg_value, cast(stdev_value as float) as stdev_value, cast(median_value as float) as median_value, cast(p10_value as float) as p10_value, cast(p25_value as float) as p25_value, cast(p75_value as float) as p75_value, cast(p90_value as float) as p90_value from
                   #s_tmpach_dist_716 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value, cast(min_value as float) as min_value, cast(max_value as float) as max_value, cast(avg_value as float) as avg_value, cast(stdev_value as float) as stdev_value, cast(median_value as float) as median_value, cast(p10_value as float) as p10_value, cast(p25_value as float) as p25_value, cast(p75_value as float) as p75_value, cast(p90_value as float) as p90_value from
                   #s_tmpach_dist_717 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value, cast(min_value as float) as min_value, cast(max_value as float) as max_value, cast(avg_value as float) as avg_value, cast(stdev_value as float) as stdev_value, cast(median_value as float) as median_value, cast(p10_value as float) as p10_value, cast(p25_value as float) as p25_value, cast(p75_value as float) as p75_value, cast(p90_value as float) as p90_value from
                   #s_tmpach_dist_803 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value, cast(min_value as float) as min_value, cast(max_value as float) as max_value, cast(avg_value as float) as avg_value, cast(stdev_value as float) as stdev_value, cast(median_value as float) as median_value, cast(p10_value as float) as p10_value, cast(p25_value as float) as p25_value, cast(p75_value as float) as p75_value, cast(p90_value as float) as p90_value from
                   #s_tmpach_dist_806 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value, cast(min_value as float) as min_value, cast(max_value as float) as max_value, cast(avg_value as float) as avg_value, cast(stdev_value as float) as stdev_value, cast(median_value as float) as median_value, cast(p10_value as float) as p10_value, cast(p25_value as float) as p25_value, cast(p75_value as float) as p75_value, cast(p90_value as float) as p90_value from
                   #s_tmpach_dist_815 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value, cast(min_value as float) as min_value, cast(max_value as float) as max_value, cast(avg_value as float) as avg_value, cast(stdev_value as float) as stdev_value, cast(median_value as float) as median_value, cast(p10_value as float) as p10_value, cast(p25_value as float) as p25_value, cast(p75_value as float) as p75_value, cast(p90_value as float) as p90_value from
                   #s_tmpach_dist_903 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value, cast(min_value as float) as min_value, cast(max_value as float) as max_value, cast(avg_value as float) as avg_value, cast(stdev_value as float) as stdev_value, cast(median_value as float) as median_value, cast(p10_value as float) as p10_value, cast(p25_value as float) as p25_value, cast(p75_value as float) as p75_value, cast(p90_value as float) as p90_value from
                   #s_tmpach_dist_906 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value, cast(min_value as float) as min_value, cast(max_value as float) as max_value, cast(avg_value as float) as avg_value, cast(stdev_value as float) as stdev_value, cast(median_value as float) as median_value, cast(p10_value as float) as p10_value, cast(p25_value as float) as p25_value, cast(p75_value as float) as p75_value, cast(p90_value as float) as p90_value from
                   #s_tmpach_dist_907 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value, cast(min_value as float) as min_value, cast(max_value as float) as max_value, cast(avg_value as float) as avg_value, cast(stdev_value as float) as stdev_value, cast(median_value as float) as median_value, cast(p10_value as float) as p10_value, cast(p25_value as float) as p25_value, cast(p75_value as float) as p75_value, cast(p90_value as float) as p90_value from
                   #s_tmpach_dist_1003 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value, cast(min_value as float) as min_value, cast(max_value as float) as max_value, cast(avg_value as float) as avg_value, cast(stdev_value as float) as stdev_value, cast(median_value as float) as median_value, cast(p10_value as float) as p10_value, cast(p25_value as float) as p25_value, cast(p75_value as float) as p75_value, cast(p90_value as float) as p90_value from
                   #s_tmpach_dist_1006 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value, cast(min_value as float) as min_value, cast(max_value as float) as max_value, cast(avg_value as float) as avg_value, cast(stdev_value as float) as stdev_value, cast(median_value as float) as median_value, cast(p10_value as float) as p10_value, cast(p25_value as float) as p25_value, cast(p75_value as float) as p75_value, cast(p90_value as float) as p90_value from
                   #s_tmpach_dist_1007 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value, cast(min_value as float) as min_value, cast(max_value as float) as max_value, cast(avg_value as float) as avg_value, cast(stdev_value as float) as stdev_value, cast(median_value as float) as median_value, cast(p10_value as float) as p10_value, cast(p25_value as float) as p25_value, cast(p75_value as float) as p75_value, cast(p90_value as float) as p90_value from
                   #s_tmpach_dist_1303 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value, cast(min_value as float) as min_value, cast(max_value as float) as max_value, cast(avg_value as float) as avg_value, cast(stdev_value as float) as stdev_value, cast(median_value as float) as median_value, cast(p10_value as float) as p10_value, cast(p25_value as float) as p25_value, cast(p75_value as float) as p75_value, cast(p90_value as float) as p90_value from
                   #s_tmpach_dist_1306 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value, cast(min_value as float) as min_value, cast(max_value as float) as max_value, cast(avg_value as float) as avg_value, cast(stdev_value as float) as stdev_value, cast(median_value as float) as median_value, cast(p10_value as float) as p10_value, cast(p25_value as float) as p25_value, cast(p75_value as float) as p75_value, cast(p90_value as float) as p90_value from
                   #s_tmpach_dist_1313 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value, cast(min_value as float) as min_value, cast(max_value as float) as max_value, cast(avg_value as float) as avg_value, cast(stdev_value as float) as stdev_value, cast(median_value as float) as median_value, cast(p10_value as float) as p10_value, cast(p25_value as float) as p25_value, cast(p75_value as float) as p75_value, cast(p90_value as float) as p90_value from
                   #s_tmpach_dist_1406 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value, cast(min_value as float) as min_value, cast(max_value as float) as max_value, cast(avg_value as float) as avg_value, cast(stdev_value as float) as stdev_value, cast(median_value as float) as median_value, cast(p10_value as float) as p10_value, cast(p25_value as float) as p25_value, cast(p75_value as float) as p75_value, cast(p90_value as float) as p90_value from
                   #s_tmpach_dist_1407 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value, cast(min_value as float) as min_value, cast(max_value as float) as max_value, cast(avg_value as float) as avg_value, cast(stdev_value as float) as stdev_value, cast(median_value as float) as median_value, cast(p10_value as float) as p10_value, cast(p25_value as float) as p25_value, cast(p75_value as float) as p75_value, cast(p90_value as float) as p90_value from
                   #s_tmpach_dist_1803 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value, cast(min_value as float) as min_value, cast(max_value as float) as max_value, cast(avg_value as float) as avg_value, cast(stdev_value as float) as stdev_value, cast(median_value as float) as median_value, cast(p10_value as float) as p10_value, cast(p25_value as float) as p25_value, cast(p75_value as float) as p75_value, cast(p90_value as float) as p90_value from
                   #s_tmpach_dist_1806 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value, cast(min_value as float) as min_value, cast(max_value as float) as max_value, cast(avg_value as float) as avg_value, cast(stdev_value as float) as stdev_value, cast(median_value as float) as median_value, cast(p10_value as float) as p10_value, cast(p25_value as float) as p25_value, cast(p75_value as float) as p75_value, cast(p90_value as float) as p90_value from
                   #s_tmpach_dist_1815 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value, cast(min_value as float) as min_value, cast(max_value as float) as max_value, cast(avg_value as float) as avg_value, cast(stdev_value as float) as stdev_value, cast(median_value as float) as median_value, cast(p10_value as float) as p10_value, cast(p25_value as float) as p25_value, cast(p75_value as float) as p75_value, cast(p90_value as float) as p90_value from
                   #s_tmpach_dist_1816 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value, cast(min_value as float) as min_value, cast(max_value as float) as max_value, cast(avg_value as float) as avg_value, cast(stdev_value as float) as stdev_value, cast(median_value as float) as median_value, cast(p10_value as float) as p10_value, cast(p25_value as float) as p25_value, cast(p75_value as float) as p75_value, cast(p90_value as float) as p90_value from
                   #s_tmpach_dist_1817 
union all
 select cast(analysis_id as int) as analysis_id, cast(stratum_1 as varchar(255)) as stratum_1, cast(stratum_2 as varchar(255)) as stratum_2, cast(stratum_3 as varchar(255)) as stratum_3, cast(stratum_4 as varchar(255)) as stratum_4, cast(stratum_5 as varchar(255)) as stratum_5, cast(count_value as bigint) as count_value, cast(min_value as float) as min_value, cast(max_value as float) as max_value, cast(avg_value as float) as avg_value, cast(stdev_value as float) as stdev_value, cast(median_value as float) as median_value, cast(p10_value as float) as p10_value, cast(p25_value as float) as p25_value, cast(p75_value as float) as p75_value, cast(p90_value as float) as p90_value from
                   #s_tmpach_dist_2106
) Q

  where count_value > 5

;


drop index if exists @FullySpecifiedAchillesResultsSchema.idx_ar_aid;

drop index if exists @FullySpecifiedAchillesResultsSchema.idx_ar_s1;

drop index if exists @FullySpecifiedAchillesResultsSchema.idx_ar_s2;

drop index if exists @FullySpecifiedAchillesResultsSchema.idx_ar_aid_s1;

drop index if exists @FullySpecifiedAchillesResultsSchema.idx_ar_aid_s1234;

drop index if exists @FullySpecifiedAchillesResultsSchema.idx_ard_aid;

drop index if exists @FullySpecifiedAchillesResultsSchema.idx_ard_s1;

drop index if exists @FullySpecifiedAchillesResultsSchema.idx_ard_s2;

create index idx_ar_aid on @FullySpecifiedAchillesResultsSchema.achilles_results (analysis_id);

create index idx_ar_s1 on @FullySpecifiedAchillesResultsSchema.achilles_results (stratum_1);

create index idx_ar_s2 on @FullySpecifiedAchillesResultsSchema.achilles_results (stratum_2);

create index idx_ar_aid_s1 on @FullySpecifiedAchillesResultsSchema.achilles_results (analysis_id,stratum_1);

create index idx_ar_aid_s1234 on @FullySpecifiedAchillesResultsSchema.achilles_results (analysis_id,stratum_1,stratum_2,stratum_3,stratum_4);

create index idx_ard_aid on @FullySpecifiedAchillesResultsSchema.achilles_results_dist (analysis_id);

create index idx_ard_s1 on @FullySpecifiedAchillesResultsSchema.achilles_results_dist (stratum_1);

create index idx_ard_s2 on @FullySpecifiedAchillesResultsSchema.achilles_results_dist (stratum_2);