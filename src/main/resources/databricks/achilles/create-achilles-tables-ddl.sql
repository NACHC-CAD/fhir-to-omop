/**
 * 
 * This file was created by deploying an instance of Atlas and then running this in a browser:
 * http://localhost:8080/WebAPI/ddl/results?dialect=spark&schema=<ACHILLES_RESULTS_SCHEMA_NAME>&vocabSchema=<VOCAB_SCHEMA_NAME>&tempSchema=<ACHILLES_TEMP_SCHEMA_NAME>&initConceptHierarchy=true
 * 
 */

/**
 * 
 * This file was created by deploying an instance of Atlas and then running this in a browser:
 * http://127.0.0.1/WebAPI/ddl/results?dialect=spark&schema=<DB_NAME>_ach_res&vocabSchema=<DB_NAME>&tempSchema=<DB_NAME>_ach_tmp&initConceptHierarchy=true
 * 
 */

CREATE TABLE IF NOT EXISTS <DB_NAME>_ach_res.cohort
USING DELTA
AS
SELECT
CAST(NULL AS int) AS cohort_definition_id,
	CAST(NULL AS bigint) AS subject_id,
	CAST(NULL AS date) AS cohort_start_date,
	CAST(NULL AS date) AS cohort_end_date  WHERE 1 = 0;

CREATE TABLE IF NOT EXISTS <DB_NAME>_ach_res.cohort_censor_stats 
USING DELTA
AS
SELECT
CAST(NULL AS int) AS cohort_definition_id,
	CAST(NULL AS bigint) AS lost_count  WHERE 1 = 0;

CREATE TABLE IF NOT EXISTS <DB_NAME>_ach_res.cohort_inclusion 
USING DELTA
AS
SELECT
CAST(NULL AS int) AS cohort_definition_id,
	CAST(NULL AS int) AS design_hash,
	CAST(NULL AS int) AS rule_sequence,
	CAST(NULL AS STRING) AS name,
	CAST(NULL AS STRING) AS description  WHERE 1 = 0;

CREATE TABLE IF NOT EXISTS <DB_NAME>_ach_res.cohort_inclusion_result 
USING DELTA
AS
SELECT
CAST(NULL AS int) AS cohort_definition_id,
	CAST(NULL AS int) AS mode_id,
	CAST(NULL AS bigint) AS inclusion_rule_mask,
	CAST(NULL AS bigint) AS person_count  WHERE 1 = 0;

CREATE TABLE IF NOT EXISTS <DB_NAME>_ach_res.cohort_inclusion_stats 
USING DELTA
AS
SELECT
CAST(NULL AS int) AS cohort_definition_id,
	CAST(NULL AS int) AS rule_sequence,
	CAST(NULL AS int) AS mode_id,
	CAST(NULL AS bigint) AS person_count,
	CAST(NULL AS bigint) AS gain_count,
	CAST(NULL AS bigint) AS person_total  WHERE 1 = 0;

CREATE TABLE IF NOT EXISTS <DB_NAME>_ach_res.cohort_summary_stats 
USING DELTA
AS
SELECT
CAST(NULL AS int) AS cohort_definition_id,
	CAST(NULL AS int) AS mode_id,
	CAST(NULL AS bigint) AS base_count,
	CAST(NULL AS bigint) AS final_count  WHERE 1 = 0;

CREATE TABLE IF NOT EXISTS <DB_NAME>_ach_res.cohort_cache  
USING DELTA
AS
SELECT
CAST(NULL AS int) AS design_hash,
	CAST(NULL AS bigint) AS subject_id,
	CAST(NULL AS date) AS cohort_start_date,
	CAST(NULL AS date) AS cohort_end_date  WHERE 1 = 0;

CREATE TABLE IF NOT EXISTS <DB_NAME>_ach_res.cohort_censor_stats_cache  
USING DELTA
AS
SELECT
CAST(NULL AS int) AS design_hash,
	CAST(NULL AS bigint) AS lost_count  WHERE 1 = 0;

CREATE TABLE IF NOT EXISTS <DB_NAME>_ach_res.cohort_inclusion_result_cache  
USING DELTA
AS
SELECT
CAST(NULL AS int) AS design_hash,
	CAST(NULL AS int) AS mode_id,
	CAST(NULL AS bigint) AS inclusion_rule_mask,
	CAST(NULL AS bigint) AS person_count  WHERE 1 = 0;

CREATE TABLE IF NOT EXISTS <DB_NAME>_ach_res.cohort_inclusion_stats_cache  
USING DELTA
AS
SELECT
CAST(NULL AS int) AS design_hash,
	CAST(NULL AS int) AS rule_sequence,
	CAST(NULL AS int) AS mode_id,
	CAST(NULL AS bigint) AS person_count,
	CAST(NULL AS bigint) AS gain_count,
	CAST(NULL AS bigint) AS person_total  WHERE 1 = 0;

CREATE TABLE IF NOT EXISTS <DB_NAME>_ach_res.cohort_summary_stats_cache  
USING DELTA
AS
SELECT
CAST(NULL AS int) AS design_hash,
	CAST(NULL AS int) AS mode_id,
	CAST(NULL AS bigint) AS base_count,
	CAST(NULL AS bigint) AS final_count  WHERE 1 = 0;

CREATE TABLE IF NOT EXISTS <DB_NAME>_ach_res.feas_study_inclusion_stats 
USING DELTA
AS
SELECT
CAST(NULL AS int) AS study_id,
	CAST(NULL AS int) AS rule_sequence,
	CAST(NULL AS STRING) AS name,
	CAST(NULL AS bigint) AS person_count,
	CAST(NULL AS bigint) AS gain_count,
	CAST(NULL AS bigint) AS person_total  WHERE 1 = 0;

CREATE TABLE IF NOT EXISTS <DB_NAME>_ach_res.feas_study_index_stats 
USING DELTA
AS
SELECT
CAST(NULL AS int) AS study_id,
	CAST(NULL AS bigint) AS person_count,
	CAST(NULL AS bigint) AS match_count  WHERE 1 = 0;

CREATE TABLE IF NOT EXISTS <DB_NAME>_ach_res.feas_study_result 
USING DELTA
AS
SELECT
CAST(NULL AS int) AS study_id,
	CAST(NULL AS bigint) AS inclusion_rule_mask,
	CAST(NULL AS bigint) AS person_count  WHERE 1 = 0;

CREATE TABLE IF NOT EXISTS <DB_NAME>_ach_res.heracles_analysis
USING DELTA
AS
SELECT
CAST(NULL AS int) AS analysis_id,
	CAST(NULL AS STRING) AS analysis_name,
	CAST(NULL AS STRING) AS stratum_1_name,
	CAST(NULL AS STRING) AS stratum_2_name,
	CAST(NULL AS STRING) AS stratum_3_name,
	CAST(NULL AS STRING) AS stratum_4_name,
	CAST(NULL AS STRING) AS stratum_5_name,
	CAST(NULL AS STRING) AS analysis_type  WHERE 1 = 0;

CREATE TABLE IF NOT EXISTS <DB_NAME>_ach_res.HERACLES_HEEL_results 
USING DELTA
AS
SELECT
CAST(NULL AS int) AS cohort_definition_id,
	CAST(NULL AS int) AS analysis_id,
	CAST(NULL AS STRING) AS heracles_heel_warning  WHERE 1 = 0;

--HINT PARTITION(cohort_definition_id int)
CREATE TABLE IF NOT EXISTS <DB_NAME>_ach_res.heracles_results
USING DELTA
AS
SELECT
CAST(NULL AS int) AS cohort_definition_id,
	CAST(NULL AS int) AS analysis_id,
	CAST(NULL AS STRING) AS stratum_1,
	CAST(NULL AS STRING) AS stratum_2,
	CAST(NULL AS STRING) AS stratum_3,
	CAST(NULL AS STRING) AS stratum_4,
	CAST(NULL AS STRING) AS stratum_5,
	CAST(NULL AS bigint) AS count_value,
	CAST(NULL AS TIMESTAMP) AS last_update_time  WHERE 1 = 0;

--HINT PARTITION(cohort_definition_id int)
CREATE TABLE IF NOT EXISTS <DB_NAME>_ach_res.heracles_results_dist
USING DELTA
AS
SELECT
CAST(NULL AS int) AS cohort_definition_id,
	CAST(NULL AS int) AS analysis_id,
	CAST(NULL AS STRING) AS stratum_1,
	CAST(NULL AS STRING) AS stratum_2,
	CAST(NULL AS STRING) AS stratum_3,
	CAST(NULL AS STRING) AS stratum_4,
	CAST(NULL AS STRING) AS stratum_5,
	CAST(NULL AS bigint) AS count_value,
	CAST(NULL AS float) AS min_value,
	CAST(NULL AS float) AS max_value,
	CAST(NULL AS float) AS avg_value,
	CAST(NULL AS float) AS stdev_value,
	CAST(NULL AS float) AS median_value,
	CAST(NULL AS float) AS p10_value,
	CAST(NULL AS float) AS p25_value,
	CAST(NULL AS float) AS p75_value,
	CAST(NULL AS float) AS p90_value,
	CAST(NULL AS TIMESTAMP) AS last_update_time  WHERE 1 = 0;

CREATE TABLE IF NOT EXISTS <DB_NAME>_ach_res.heracles_periods
USING DELTA
AS
SELECT
CAST(NULL AS int) AS period_id,
	CAST(NULL AS int) AS period_order,
	CAST(NULL AS STRING) AS period_name,
	CAST(NULL AS STRING) AS period_type,
	CAST(NULL AS date) AS period_start_date,
	CAST(NULL AS date) AS period_end_date  WHERE 1 = 0;

CREATE TABLE IF NOT EXISTS <DB_NAME>_ach_res.cohort_sample_element 
USING DELTA
AS
SELECT
CAST(NULL AS int) AS cohort_sample_id,
	CAST(NULL AS int) AS rank_value,
	CAST(NULL AS bigint) AS person_id,
	CAST(NULL AS int) AS age,
	CAST(NULL AS int) AS gender_concept_id  WHERE 1 = 0;

CREATE TABLE IF NOT EXISTS <DB_NAME>_ach_res.ir_analysis_dist  
USING DELTA
AS
SELECT
CAST(NULL AS int) AS analysis_id,
	CAST(NULL AS int) AS target_id,
	CAST(NULL AS int) AS outcome_id,
	CAST(NULL AS int) AS strata_sequence,
	CAST(NULL AS int) AS dist_type,
	CAST(NULL AS bigint) AS total,
	CAST(NULL AS float) AS avg_value,
	CAST(NULL AS float) AS std_dev,
	CAST(NULL AS int) AS min_value,
	CAST(NULL AS int) AS p10_value,
	CAST(NULL AS int) AS p25_value,
	CAST(NULL AS int) AS median_value,
	CAST(NULL AS int) AS p75_value,
	CAST(NULL AS int) AS p90_value,
	CAST(NULL AS int) AS max_value  WHERE 1 = 0;

CREATE TABLE IF NOT EXISTS <DB_NAME>_ach_res.ir_analysis_result 
USING DELTA
AS
SELECT
CAST(NULL AS int) AS analysis_id,
	CAST(NULL AS int) AS target_id,
	CAST(NULL AS int) AS outcome_id,
	CAST(NULL AS bigint) AS strata_mask,
	CAST(NULL AS bigint) AS person_count,
	CAST(NULL AS bigint) AS time_at_risk,
	CAST(NULL AS bigint) AS cases  WHERE 1 = 0;

CREATE TABLE IF NOT EXISTS <DB_NAME>_ach_res.ir_analysis_strata_stats 
USING DELTA
AS
SELECT
CAST(NULL AS int) AS analysis_id,
	CAST(NULL AS int) AS target_id,
	CAST(NULL AS int) AS outcome_id,
	CAST(NULL AS int) AS strata_sequence,
	CAST(NULL AS bigint) AS person_count,
	CAST(NULL AS bigint) AS time_at_risk,
	CAST(NULL AS bigint) AS cases  WHERE 1 = 0;

CREATE TABLE IF NOT EXISTS <DB_NAME>_ach_res.ir_strata 
USING DELTA
AS
SELECT
CAST(NULL AS int) AS analysis_id,
	CAST(NULL AS int) AS strata_sequence,
	CAST(NULL AS STRING) AS name,
	CAST(NULL AS STRING) AS description  WHERE 1 = 0;

CREATE TABLE IF NOT EXISTS <DB_NAME>_ach_res.cc_results
USING DELTA
AS
SELECT
CAST(NULL AS STRING) AS type,
	CAST(NULL AS STRING) AS fa_type,
	CAST(NULL AS bigint) AS cc_generation_id,
	CAST(NULL AS integer) AS analysis_id,
	CAST(NULL AS STRING) AS analysis_name,
	CAST(NULL AS bigint) AS covariate_id,
	CAST(NULL AS STRING) AS covariate_name,
	CAST(NULL AS bigint) AS strata_id,
	CAST(NULL AS STRING) AS strata_name,
	CAST(NULL AS STRING) AS time_window,
	CAST(NULL AS integer) AS concept_id,
	CAST(NULL AS bigint) AS count_value,
	CAST(NULL AS double) AS avg_value,
	CAST(NULL AS double) AS stdev_value,
	CAST(NULL AS double) AS min_value,
	CAST(NULL AS double) AS p10_value,
	CAST(NULL AS double) AS p25_value,
	CAST(NULL AS double) AS median_value,
	CAST(NULL AS double) AS p75_value,
	CAST(NULL AS double) AS p90_value,
	CAST(NULL AS double) AS max_value,
	CAST(NULL AS bigint) AS cohort_definition_id,
	CAST(NULL AS integer) AS aggregate_id,
	CAST(NULL AS STRING) AS aggregate_name,
	CAST(NULL AS integer) AS missing_means_zero  WHERE 1 = 0;

CREATE TABLE IF NOT EXISTS <DB_NAME>_ach_res.pathway_analysis_codes
USING DELTA
AS
SELECT
CAST(NULL AS bigint) AS pathway_analysis_generation_id,
	CAST(NULL AS bigint) AS code,
	CAST(NULL AS STRING) AS name,
	CAST(NULL AS int) AS is_combo  WHERE 1 = 0;

CREATE TABLE IF NOT EXISTS <DB_NAME>_ach_res.pathway_analysis_events
USING DELTA
AS
SELECT
CAST(NULL AS bigint) AS pathway_analysis_generation_id,
	CAST(NULL AS integer) AS target_cohort_id,
	CAST(NULL AS bigint) AS combo_id,
	CAST(NULL AS bigint) AS subject_id,
	CAST(NULL AS integer) AS ordinal,
	CAST(NULL AS TIMESTAMP) AS cohort_start_date,
	CAST(NULL AS TIMESTAMP) AS cohort_end_date  WHERE 1 = 0;

CREATE TABLE IF NOT EXISTS <DB_NAME>_ach_res.pathway_analysis_paths
USING DELTA
AS
SELECT
CAST(NULL AS bigint) AS pathway_analysis_generation_id,
	CAST(NULL AS integer) AS target_cohort_id,
	CAST(NULL AS bigint) AS step_1,
	CAST(NULL AS bigint) AS step_2,
	CAST(NULL AS bigint) AS step_3,
	CAST(NULL AS bigint) AS step_4,
	CAST(NULL AS bigint) AS step_5,
	CAST(NULL AS bigint) AS step_6,
	CAST(NULL AS bigint) AS step_7,
	CAST(NULL AS bigint) AS step_8,
	CAST(NULL AS bigint) AS step_9,
	CAST(NULL AS bigint) AS step_10,
	CAST(NULL AS bigint) AS count_value  WHERE 1 = 0;

CREATE TABLE IF NOT EXISTS <DB_NAME>_ach_res.pathway_analysis_stats
USING DELTA
AS
SELECT
CAST(NULL AS bigint) AS pathway_analysis_generation_id,
	CAST(NULL AS integer) AS target_cohort_id,
	CAST(NULL AS bigint) AS target_cohort_count,
	CAST(NULL AS bigint) AS pathways_count  WHERE 1 = 0;

CREATE TABLE IF NOT EXISTS <DB_NAME>_ach_res.concept_hierarchy
USING DELTA
AS
SELECT
CAST(NULL AS int) AS concept_id,
	CAST(NULL AS STRING) AS concept_name,
	CAST(NULL AS STRING) AS treemap,
	CAST(NULL AS STRING) AS concept_hierarchy_type,
	CAST(NULL AS STRING) AS level1_concept_name,
	CAST(NULL AS STRING) AS level2_concept_name,
	CAST(NULL AS STRING) AS level3_concept_name,
	CAST(NULL AS STRING) AS level4_concept_name  WHERE 1 = 0;

TRUNCATE TABLE <DB_NAME>_ach_res.concept_hierarchy;

WITH insertion_temp AS (
(SELECT snomed.concept_id,
 snomed.concept_name AS concept_name,
 CAST('Condition' AS STRING) AS treemap,
 CAST(NULL AS STRING) as concept_hierarchy_type,
 pt_to_hlt.pt_concept_name as level1_concept_name,
 hlt_to_hlgt.hlt_concept_name as level2_concept_name,
 hlgt_to_soc.hlgt_concept_name as level3_concept_name,
 soc.concept_name AS level4_concept_name
FROM (
 SELECT
 concept_id,
 concept_name
 FROM <DB_NAME>.concept
 WHERE domain_id = 'Condition'
) snomed
LEFT JOIN (
 SELECT
 c1.concept_id AS snomed_concept_id,
 max(c2.concept_id) AS pt_concept_id
 FROM <DB_NAME>.concept c1
 INNER JOIN <DB_NAME>.concept_ancestor ca1 ON c1.concept_id = ca1.descendant_concept_id
 AND c1.domain_id = 'Condition'
 AND ca1.min_levels_of_separation = 1
 INNER JOIN <DB_NAME>.concept c2 ON ca1.ancestor_concept_id = c2.concept_id
 AND c2.vocabulary_id = 'MedDRA'
 GROUP BY c1.concept_id
) snomed_to_pt ON snomed.concept_id = snomed_to_pt.snomed_concept_id
LEFT JOIN (
 SELECT
 c1.concept_id AS pt_concept_id,
 c1.concept_name AS pt_concept_name,
 max(c2.concept_id) AS hlt_concept_id
 FROM <DB_NAME>.concept c1
 INNER JOIN <DB_NAME>.concept_ancestor ca1 ON c1.concept_id = ca1.descendant_concept_id
 AND c1.vocabulary_id = 'MedDRA'
 AND ca1.min_levels_of_separation = 1
 INNER JOIN <DB_NAME>.concept c2 ON ca1.ancestor_concept_id = c2.concept_id
 AND c2.vocabulary_id = 'MedDRA'
 GROUP BY c1.concept_id, c1.concept_name
) pt_to_hlt ON snomed_to_pt.pt_concept_id = pt_to_hlt.pt_concept_id
LEFT JOIN (
 SELECT
 c1.concept_id AS hlt_concept_id,
 c1.concept_name AS hlt_concept_name,
 max(c2.concept_id) AS hlgt_concept_id
 FROM <DB_NAME>.concept c1
 INNER JOIN <DB_NAME>.concept_ancestor ca1 ON c1.concept_id = ca1.descendant_concept_id
 AND c1.vocabulary_id = 'MedDRA'
 AND ca1.min_levels_of_separation = 1
 INNER JOIN <DB_NAME>.concept c2 ON ca1.ancestor_concept_id = c2.concept_id
 AND c2.vocabulary_id = 'MedDRA'
 GROUP BY c1.concept_id, c1.concept_name
) hlt_to_hlgt ON pt_to_hlt.hlt_concept_id = hlt_to_hlgt.hlt_concept_id
LEFT JOIN (
 SELECT
 c1.concept_id AS hlgt_concept_id,
 c1.concept_name AS hlgt_concept_name,
 max(c2.concept_id) AS soc_concept_id
 FROM <DB_NAME>.concept c1
 INNER JOIN <DB_NAME>.concept_ancestor ca1 ON c1.concept_id = ca1.descendant_concept_id
 AND c1.vocabulary_id = 'MedDRA'
 AND ca1.min_levels_of_separation = 1
 INNER JOIN <DB_NAME>.concept c2 ON ca1.ancestor_concept_id = c2.concept_id
 AND c2.vocabulary_id = 'MedDRA'
 GROUP BY c1.concept_id, c1.concept_name
) hlgt_to_soc ON hlt_to_hlgt.hlgt_concept_id = hlgt_to_soc.hlgt_concept_id
LEFT JOIN <DB_NAME>.concept soc ON hlgt_to_soc.soc_concept_id = soc.concept_id) UNION ALL (SELECT concept_id, concept_name, treemap, concept_hierarchy_type, level1_concept_name, level2_concept_name, level3_concept_name, level4_concept_name FROM <DB_NAME>_ach_res.concept_hierarchy
 ))
INSERT OVERWRITE TABLE <DB_NAME>_ach_res.concept_hierarchy
  (concept_id, concept_name, treemap, concept_hierarchy_type, level1_concept_name, level2_concept_name, level3_concept_name, level4_concept_name) SELECT * FROM insertion_temp;

WITH insertion_temp AS (
(SELECT rxnorm.concept_id,
 rxnorm.concept_name AS concept_name,
 CAST('Drug' AS STRING) AS treemap,
 CAST(NULL AS STRING) as concept_hierarchy_type,
 rxnorm.rxnorm_ingredient_concept_name as level1_concept_name,
 atc5_to_atc3.atc5_concept_name as level2_concept_name,
 atc3_to_atc1.atc3_concept_name as level3_concept_name,
 atc1.concept_name AS level4_concept_name
FROM (
 SELECT
 c1.concept_id,
 c1.concept_name,
 c2.concept_id AS rxnorm_ingredient_concept_id,
 c2.concept_name AS RxNorm_ingredient_concept_name
 FROM <DB_NAME>.concept c1
 INNER JOIN <DB_NAME>.concept_ancestor ca1 ON c1.concept_id = ca1.descendant_concept_id
 AND c1.domain_id = 'Drug'
 INNER JOIN <DB_NAME>.concept c2 ON ca1.ancestor_concept_id = c2.concept_id
 AND c2.domain_id = 'Drug'
 AND c2.concept_class_id = 'Ingredient'
) rxnorm
LEFT JOIN (
 SELECT
 c1.concept_id AS rxnorm_ingredient_concept_id,
 max(c2.concept_id) AS atc5_concept_id
 FROM <DB_NAME>.concept c1
 INNER JOIN <DB_NAME>.concept_ancestor ca1 ON c1.concept_id = ca1.descendant_concept_id
 AND c1.domain_id = 'Drug'
 AND c1.concept_class_id = 'Ingredient'
 INNER JOIN <DB_NAME>.concept c2 ON ca1.ancestor_concept_id = c2.concept_id
 AND c2.vocabulary_id = 'ATC'
 AND c2.concept_class_id = 'ATC 4th'
 GROUP BY c1.concept_id
) rxnorm_to_atc5 ON rxnorm.rxnorm_ingredient_concept_id = rxnorm_to_atc5.rxnorm_ingredient_concept_id
LEFT JOIN (
 SELECT
 c1.concept_id AS atc5_concept_id,
 c1.concept_name AS atc5_concept_name,
 max(c2.concept_id) AS atc3_concept_id
 FROM <DB_NAME>.concept c1
 INNER JOIN <DB_NAME>.concept_ancestor ca1 ON c1.concept_id = ca1.descendant_concept_id
 AND c1.vocabulary_id = 'ATC'
 AND c1.concept_class_id = 'ATC 4th'
 INNER JOIN <DB_NAME>.concept c2 ON ca1.ancestor_concept_id = c2.concept_id
 AND c2.vocabulary_id = 'ATC'
 AND c2.concept_class_id = 'ATC 2nd'
 GROUP BY c1.concept_id, c1.concept_name
) atc5_to_atc3 ON rxnorm_to_atc5.atc5_concept_id = atc5_to_atc3.atc5_concept_id
LEFT JOIN (
 SELECT
 c1.concept_id AS atc3_concept_id,
 c1.concept_name AS atc3_concept_name,
 max(c2.concept_id) AS atc1_concept_id
 FROM <DB_NAME>.concept c1
 INNER JOIN <DB_NAME>.concept_ancestor ca1 ON c1.concept_id = ca1.descendant_concept_id
 AND c1.vocabulary_id = 'ATC'
 AND c1.concept_class_id = 'ATC 2nd'
 INNER JOIN <DB_NAME>.concept c2 ON ca1.ancestor_concept_id = c2.concept_id
 AND c2.vocabulary_id = 'ATC'
 AND c2.concept_class_id = 'ATC 1st'
 GROUP BY c1.concept_id, c1.concept_name
) atc3_to_atc1 ON atc5_to_atc3.atc3_concept_id = atc3_to_atc1.atc3_concept_id
LEFT JOIN <DB_NAME>.concept atc1 ON atc3_to_atc1.atc1_concept_id = atc1.concept_id) UNION ALL (SELECT concept_id, concept_name, treemap, concept_hierarchy_type, level1_concept_name, level2_concept_name, level3_concept_name, level4_concept_name FROM <DB_NAME>_ach_res.concept_hierarchy
 ))
INSERT OVERWRITE TABLE <DB_NAME>_ach_res.concept_hierarchy
  (concept_id, concept_name, treemap, concept_hierarchy_type, level1_concept_name, level2_concept_name, level3_concept_name, level4_concept_name) SELECT * FROM insertion_temp;

WITH insertion_temp AS (
(SELECT rxnorm.rxnorm_ingredient_concept_id as concept_id,
 rxnorm.rxnorm_ingredient_concept_name as concept_name,
 CAST('Drug Era' AS STRING) AS treemap,
 CAST(NULL AS STRING) as concept_hierarchy_type,
 atc5_to_atc3.atc5_concept_name as level1_concept_name,
 atc3_to_atc1.atc3_concept_name as level2_concept_name,
 atc1.concept_name AS level3_concept_name,
 CAST(NULL AS STRING) as level4_concept_name
FROM (
 SELECT
 c2.concept_id AS rxnorm_ingredient_concept_id,
 c2.concept_name AS RxNorm_ingredient_concept_name
 FROM <DB_NAME>.concept c2
 WHERE c2.domain_id = 'Drug'
 AND c2.concept_class_id = 'Ingredient'
) rxnorm
LEFT JOIN (
 SELECT
 c1.concept_id AS rxnorm_ingredient_concept_id,
 max(c2.concept_id) AS atc5_concept_id
 FROM <DB_NAME>.concept c1
 INNER JOIN <DB_NAME>.concept_ancestor ca1 ON c1.concept_id = ca1.descendant_concept_id
 AND c1.domain_id = 'Drug'
 AND c1.concept_class_id = 'Ingredient'
 INNER JOIN <DB_NAME>.concept c2 ON ca1.ancestor_concept_id = c2.concept_id
 AND c2.vocabulary_id = 'ATC'
 AND c2.concept_class_id = 'ATC 4th'
 GROUP BY c1.concept_id
) rxnorm_to_atc5 ON rxnorm.rxnorm_ingredient_concept_id = rxnorm_to_atc5.rxnorm_ingredient_concept_id
LEFT JOIN (
 SELECT
 c1.concept_id AS atc5_concept_id,
 c1.concept_name AS atc5_concept_name,
 max(c2.concept_id) AS atc3_concept_id
 FROM <DB_NAME>.concept c1
 INNER JOIN <DB_NAME>.concept_ancestor ca1 ON c1.concept_id = ca1.descendant_concept_id
 AND c1.vocabulary_id = 'ATC'
 AND c1.concept_class_id = 'ATC 4th'
 INNER JOIN <DB_NAME>.concept c2 ON ca1.ancestor_concept_id = c2.concept_id
 AND c2.vocabulary_id = 'ATC'
 AND c2.concept_class_id = 'ATC 2nd'
 GROUP BY c1.concept_id, c1.concept_name
) atc5_to_atc3 ON rxnorm_to_atc5.atc5_concept_id = atc5_to_atc3.atc5_concept_id
LEFT JOIN (
 SELECT
 c1.concept_id AS atc3_concept_id,
 c1.concept_name AS atc3_concept_name,
 max(c2.concept_id) AS atc1_concept_id
 FROM <DB_NAME>.concept c1
 INNER JOIN <DB_NAME>.concept_ancestor ca1 ON c1.concept_id = ca1.descendant_concept_id
 AND c1.vocabulary_id = 'ATC'
 AND c1.concept_class_id = 'ATC 2nd'
 INNER JOIN <DB_NAME>.concept c2 ON ca1.ancestor_concept_id = c2.concept_id
 AND c2.vocabulary_id = 'ATC'
 AND c2.concept_class_id = 'ATC 1st'
 GROUP BY c1.concept_id, c1.concept_name
) atc3_to_atc1 ON atc5_to_atc3.atc3_concept_id = atc3_to_atc1.atc3_concept_id
LEFT JOIN <DB_NAME>.concept atc1 ON atc3_to_atc1.atc1_concept_id = atc1.concept_id) UNION ALL (SELECT concept_id, concept_name, treemap, concept_hierarchy_type, level1_concept_name, level2_concept_name, level3_concept_name, level4_concept_name FROM <DB_NAME>_ach_res.concept_hierarchy
 ))
INSERT OVERWRITE TABLE <DB_NAME>_ach_res.concept_hierarchy
  (concept_id, concept_name, treemap, concept_hierarchy_type, level1_concept_name, level2_concept_name, level3_concept_name, level4_concept_name) SELECT * FROM insertion_temp;

WITH insertion_temp AS (
(SELECT m.concept_id,
 m.concept_name AS concept_name,
 CAST('Measurement' AS STRING) AS treemap,
 CAST(NULL AS STRING) as concept_hierarchy_type,
 CAST(max(c1.concept_name) AS STRING) AS level1_concept_name,
 CAST(max(c2.concept_name) AS STRING) AS level2_concept_name,
 CAST(max(c3.concept_name) AS STRING) AS level3_concept_name,
 CAST(NULL AS STRING) as level4_concept_name
FROM (
 SELECT DISTINCT
 concept_id,
 concept_name
 FROM <DB_NAME>.concept c
 WHERE domain_id = 'Measurement'
) m
LEFT JOIN <DB_NAME>.concept_ancestor ca1 ON M.concept_id = ca1.DESCENDANT_CONCEPT_ID AND ca1.min_levels_of_separation = 1
LEFT JOIN <DB_NAME>.concept c1 ON ca1.ANCESTOR_CONCEPT_ID = c1.concept_id
LEFT JOIN <DB_NAME>.concept_ancestor ca2 ON c1.concept_id = ca2.DESCENDANT_CONCEPT_ID AND ca2.min_levels_of_separation = 1
LEFT JOIN <DB_NAME>.concept c2 ON ca2.ANCESTOR_CONCEPT_ID = c2.concept_id
LEFT JOIN <DB_NAME>.concept_ancestor ca3 ON c2.concept_id = ca3.DESCENDANT_CONCEPT_ID AND ca3.min_levels_of_separation = 1
LEFT JOIN <DB_NAME>.concept c3 ON ca3.ANCESTOR_CONCEPT_ID = c3.concept_id
GROUP BY M.concept_id, M.concept_name) UNION ALL (SELECT concept_id, concept_name, treemap, concept_hierarchy_type, level1_concept_name, level2_concept_name, level3_concept_name, level4_concept_name FROM <DB_NAME>_ach_res.concept_hierarchy
 ))
INSERT OVERWRITE TABLE <DB_NAME>_ach_res.concept_hierarchy
  (concept_id, concept_name, treemap, concept_hierarchy_type, level1_concept_name, level2_concept_name, level3_concept_name, level4_concept_name) SELECT * FROM insertion_temp;

WITH insertion_temp AS (
(SELECT obs.concept_id,
 obs.concept_name AS concept_name,
 CAST('Observation' AS STRING) AS treemap,
 CAST(NULL AS STRING) as concept_hierarchy_type,
 CAST(max(c1.concept_name) AS STRING) AS level1_concept_name,
 CAST(max(c2.concept_name) AS STRING) AS level2_concept_name,
 CAST(max(c3.concept_name) AS STRING) AS level3_concept_name,
 CAST(NULL AS STRING) as level4_concept_name
FROM (
 SELECT
 concept_id,
 concept_name
 FROM <DB_NAME>.concept
 WHERE domain_id = 'Observation'
) obs
LEFT JOIN <DB_NAME>.concept_ancestor ca1 ON obs.concept_id = ca1.DESCENDANT_CONCEPT_ID AND ca1.min_levels_of_separation = 1
LEFT JOIN <DB_NAME>.concept c1 ON ca1.ANCESTOR_CONCEPT_ID = c1.concept_id
LEFT JOIN <DB_NAME>.concept_ancestor ca2 ON c1.concept_id = ca2.DESCENDANT_CONCEPT_ID AND ca2.min_levels_of_separation = 1
LEFT JOIN <DB_NAME>.concept c2 ON ca2.ANCESTOR_CONCEPT_ID = c2.concept_id
LEFT JOIN <DB_NAME>.concept_ancestor ca3 ON c2.concept_id = ca3.DESCENDANT_CONCEPT_ID AND ca3.min_levels_of_separation = 1
LEFT JOIN <DB_NAME>.concept c3 ON ca3.ANCESTOR_CONCEPT_ID = c3.concept_id
GROUP BY obs.concept_id, obs.concept_name) UNION ALL (SELECT concept_id, concept_name, treemap, concept_hierarchy_type, level1_concept_name, level2_concept_name, level3_concept_name, level4_concept_name FROM <DB_NAME>_ach_res.concept_hierarchy
 ))
INSERT OVERWRITE TABLE <DB_NAME>_ach_res.concept_hierarchy
  (concept_id, concept_name, treemap, concept_hierarchy_type, level1_concept_name, level2_concept_name, level3_concept_name, level4_concept_name) SELECT * FROM insertion_temp;

WITH insertion_temp AS (
(SELECT procs.concept_id,
 CAST(procs.proc_concept_name AS STRING) AS concept_name,
 CAST('Procedure' AS STRING) AS treemap,
 CAST(NULL AS STRING) as concept_hierarchy_type,
 CAST(max(proc_hierarchy.os3_concept_name) AS STRING) AS level1_concept_name,
 CAST(max(proc_hierarchy.os2_concept_name) AS STRING) AS level2_concept_name,
 CAST(max(proc_hierarchy.os1_concept_name) AS STRING) AS level3_concept_name,
 CAST(NULL AS STRING) as level4_concept_name
FROM
(
 SELECT
 c1.concept_id,
 CONCAT(v1.vocabulary_name, ' ', c1.concept_code, ': ', c1.concept_name) AS proc_concept_name
 FROM <DB_NAME>.concept c1
 INNER JOIN <DB_NAME>.vocabulary v1 ON c1.vocabulary_id = v1.vocabulary_id
 WHERE c1.domain_id = 'Procedure'
) procs
LEFT JOIN (
 SELECT
 ca0.DESCENDANT_CONCEPT_ID,
 max(ca0.ancestor_concept_id) AS ancestor_concept_id
 FROM <DB_NAME>.concept_ancestor ca0
 INNER JOIN (
 SELECT DISTINCT c2.concept_id AS os3_concept_id
 FROM <DB_NAME>.concept_ancestor ca1
 INNER JOIN <DB_NAME>.concept c1 ON ca1.DESCENDANT_CONCEPT_ID = c1.concept_id
 INNER JOIN <DB_NAME>.concept_ancestor ca2 ON c1.concept_id = ca2.ANCESTOR_CONCEPT_ID
 INNER JOIN <DB_NAME>.concept c2 ON ca2.DESCENDANT_CONCEPT_ID = c2.concept_id
 WHERE ca1.ancestor_concept_id = 4040390
 AND ca1.Min_LEVELS_OF_SEPARATION = 2
 AND ca2.MIN_LEVELS_OF_SEPARATION = 1
 ) t1 ON ca0.ANCESTOR_CONCEPT_ID = t1.os3_concept_id
 GROUP BY ca0.descendant_concept_id
) ca1 ON procs.concept_id = ca1.DESCENDANT_CONCEPT_ID
LEFT JOIN (
 SELECT
 proc_by_os1.os1_concept_name,
 proc_by_os2.os2_concept_name,
 proc_by_os3.os3_concept_name,
 proc_by_os3.os3_concept_id
 FROM (
 SELECT
 DESCENDANT_CONCEPT_ID AS os1_concept_id,
 concept_name AS os1_concept_name
 FROM <DB_NAME>.concept_ancestor ca1
 INNER JOIN <DB_NAME>.concept c1 ON ca1.DESCENDANT_CONCEPT_ID = c1.concept_id
 WHERE ancestor_concept_id = 4040390
 AND Min_LEVELS_OF_SEPARATION = 1
 ) proc_by_os1
 INNER JOIN (
 SELECT
 max(c1.CONCEPT_ID) AS os1_concept_id,
 c2.concept_id AS os2_concept_id,
 c2.concept_name AS os2_concept_name
 FROM <DB_NAME>.concept_ancestor ca1
 INNER JOIN <DB_NAME>.concept c1 ON ca1.DESCENDANT_CONCEPT_ID = c1.concept_id
 INNER JOIN <DB_NAME>.concept_ancestor ca2 ON c1.concept_id = ca2.ANCESTOR_CONCEPT_ID
 INNER JOIN <DB_NAME>.concept c2 ON ca2.DESCENDANT_CONCEPT_ID = c2.concept_id
 WHERE ca1.ancestor_concept_id = 4040390
 AND ca1.Min_LEVELS_OF_SEPARATION = 1
 AND ca2.MIN_LEVELS_OF_SEPARATION = 1
 GROUP BY c2.concept_id, c2.concept_name
 ) proc_by_os2 ON proc_by_os1.os1_concept_id = proc_by_os2.os1_concept_id
 INNER JOIN (
 SELECT
 max(c1.CONCEPT_ID) AS os2_concept_id,
 c2.concept_id AS os3_concept_id,
 c2.concept_name AS os3_concept_name
 FROM <DB_NAME>.concept_ancestor ca1
 INNER JOIN <DB_NAME>.concept c1 ON ca1.DESCENDANT_CONCEPT_ID = c1.concept_id
 INNER JOIN <DB_NAME>.concept_ancestor ca2 ON c1.concept_id = ca2.ANCESTOR_CONCEPT_ID
 INNER JOIN <DB_NAME>.concept c2 ON ca2.DESCENDANT_CONCEPT_ID = c2.concept_id
 WHERE ca1.ancestor_concept_id = 4040390
 AND ca1.Min_LEVELS_OF_SEPARATION = 2
 AND ca2.MIN_LEVELS_OF_SEPARATION = 1
 GROUP BY c2.concept_id, c2.concept_name
 ) proc_by_os3 ON proc_by_os2.os2_concept_id = proc_by_os3.os2_concept_id
) proc_hierarchy ON ca1.ancestor_concept_id = proc_hierarchy.os3_concept_id
GROUP BY procs.concept_id, procs.proc_concept_name) UNION ALL (SELECT concept_id, concept_name, treemap, concept_hierarchy_type, level1_concept_name, level2_concept_name, level3_concept_name, level4_concept_name FROM <DB_NAME>_ach_res.concept_hierarchy
 ))
INSERT OVERWRITE TABLE <DB_NAME>_ach_res.concept_hierarchy
  (concept_id, concept_name, treemap, concept_hierarchy_type, level1_concept_name, level2_concept_name, level3_concept_name, level4_concept_name) SELECT * FROM insertion_temp;

TRUNCATE TABLE <DB_NAME>_ach_res.heracles_analysis;

WITH insertion_temp AS (
(SELECT 0 as analysis_id,
CAST('Source name' as STRING) as analysis_name,
CAST(NULL AS STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('PERSON' as STRING) as analysis_type
union all
select 1 as analysis_id,
CAST('Number of persons' as STRING) as analysis_name,
CAST(NULL AS STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('PERSON' as STRING) as analysis_type
union all
select 2 as analysis_id,
CAST('Number of persons by gender' as STRING) as analysis_name,
CAST('gender_concept_id' as STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('PERSON' as STRING) as analysis_type
union all
select 3 as analysis_id,
CAST('Number of persons by year of birth' as STRING) as analysis_name,
CAST('year_of_birth' as STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('PERSON' as STRING) as analysis_type
union all
select 4 as analysis_id,
CAST('Number of persons by race' as STRING) as analysis_name,
CAST('race_concept_id' as STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('PERSON' as STRING) as analysis_type
union all
select 5 as analysis_id,
CAST('Number of persons by ethnicity' as STRING) as analysis_name,
CAST('ethnicity_concept_id' as STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('PERSON' as STRING) as analysis_type
union all
select 7 as analysis_id,
CAST('Number of persons with invalid provider_id' as STRING) as analysis_name,
CAST(NULL AS STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('PERSON' as STRING) as analysis_type
union all
select 8 as analysis_id,
CAST('Number of persons with invalid location_id' as STRING) as analysis_name,
CAST(NULL AS STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('PERSON' as STRING) as analysis_type
union all
select 9 as analysis_id,
CAST('Number of persons with invalid care_site_id' as STRING) as analysis_name,
CAST(NULL AS STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('PERSON' as STRING) as analysis_type
union all
select 101 as analysis_id,
CAST('Number of persons by age, with age at first observation period' as STRING) as analysis_name,
CAST('age' as STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('OBSERVATION' as STRING) as analysis_type
union all
select 102 as analysis_id,
CAST('Number of persons by gender by age, with age at first observation period' as STRING) as analysis_name,
CAST('gender_concept_id' as STRING) as stratum_1_name,
CAST('age' as STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('OBSERVATION' as STRING) as analysis_type
union all
select 103 as analysis_id,
CAST('Distribution of age at first observation period' as STRING) as analysis_name,
CAST(NULL AS STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('OBSERVATION' as STRING) as analysis_type
union all
select 104 as analysis_id,
CAST('Distribution of age at first observation period by gender' as STRING) as analysis_name,
CAST('gender_concept_id' as STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('OBSERVATION' as STRING) as analysis_type
union all
select 105 as analysis_id,
CAST('Length of observation (days) of first observation period' as STRING) as analysis_name,
CAST(NULL AS STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('OBSERVATION' as STRING) as analysis_type
union all
select 106 as analysis_id,
CAST('Length of observation (days) of first observation period by gender' as STRING) as analysis_name,
CAST('gender_concept_id' as STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('OBSERVATION' as STRING) as analysis_type
union all
select 107 as analysis_id,
CAST('Length of observation (days) of first observation period by age decile' as STRING) as analysis_name,
CAST('age decile' as STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('OBSERVATION' as STRING) as analysis_type
union all
select 108 as analysis_id,
CAST('Number of persons by length of first observation period, in 30d increments' as STRING) as analysis_name,
CAST('Observation period length 30d increments' as STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('OBSERVATION' as STRING) as analysis_type
union all
select 109 as analysis_id,
CAST('Number of persons with continuous observation in each year' as STRING) as analysis_name,
CAST('calendar year' as STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('OBSERVATION' as STRING) as analysis_type
union all
select 110 as analysis_id,
CAST('Number of persons with continuous observation in each month' as STRING) as analysis_name,
CAST('calendar month' as STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('OBSERVATION' as STRING) as analysis_type
union all
select 111 as analysis_id,
CAST('Number of persons by observation period start month' as STRING) as analysis_name,
CAST('calendar month' as STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('OBSERVATION' as STRING) as analysis_type
union all
select 112 as analysis_id,
CAST('Number of persons by observation period end month' as STRING) as analysis_name,
CAST('calendar month' as STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('OBSERVATION' as STRING) as analysis_type
union all
select 113 as analysis_id,
CAST('Number of persons by number of observation periods' as STRING) as analysis_name,
CAST('number of observation periods' as STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('OBSERVATION' as STRING) as analysis_type
union all
select 114 as analysis_id,
CAST('Number of persons with observation period before year-of-birth' as STRING) as analysis_name,
CAST(NULL AS STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('OBSERVATION' as STRING) as analysis_type
union all
select 115 as analysis_id,
CAST('Number of persons with observation period end < observation period start' as STRING) as analysis_name,
CAST(NULL AS STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('OBSERVATION' as STRING) as analysis_type
union all
select 116 as analysis_id,
CAST('Number of persons with at least one day of observation in each year by gender and age decile' as STRING) as analysis_name,
CAST('calendar year' as STRING) as stratum_1_name,
CAST('gender_concept_id' as STRING) as stratum_2_name,
CAST('age decile' as STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('OBSERVATION' as STRING) as analysis_type
union all
select 117 as analysis_id,
CAST('Number of persons with at least one day of observation in each month' as STRING) as analysis_name,
CAST('calendar month' as STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('OBSERVATION' as STRING) as analysis_type
union all
select 200 as analysis_id,
CAST('Number of persons with at least one visit occurrence, by visit_concept_id' as STRING) as analysis_name,
CAST('visit_concept_id' as STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('VISITS' as STRING) as analysis_type
union all
select 201 as analysis_id,
CAST('Number of visit occurrence records, by visit_concept_id' as STRING) as analysis_name,
CAST('visit_concept_id' as STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('VISITS' as STRING) as analysis_type
union all
select 202 as analysis_id,
CAST('Number of persons by visit occurrence start month, by visit_concept_id' as STRING) as analysis_name,
CAST('visit_concept_id' as STRING) as stratum_1_name,
CAST('calendar month' as STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('VISITS' as STRING) as analysis_type
union all
select 203 as analysis_id,
CAST('Number of distinct visit occurrence concepts per person' as STRING) as analysis_name,
CAST(NULL AS STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('VISITS' as STRING) as analysis_type
union all
select 204 as analysis_id,
CAST('Number of persons with at least one visit occurrence, by visit_concept_id by calendar year by gender by age decile' as STRING) as analysis_name,
CAST('visit_concept_id' as STRING) as stratum_1_name,
CAST('calendar year' as STRING) as stratum_2_name,
CAST('gender_concept_id' as STRING) as stratum_3_name,
CAST('age decile' as STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('VISITS' as STRING) as analysis_type
union all
select 206 as analysis_id,
CAST('Distribution of age by visit_concept_id' as STRING) as analysis_name,
CAST('visit_concept_id' as STRING) as stratum_1_name,
CAST('gender_concept_id' as STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('VISITS' as STRING) as analysis_type
union all
select 207 as analysis_id,
CAST('Number of visit records with invalid person_id' as STRING) as analysis_name,
CAST(NULL AS STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('VISITS' as STRING) as analysis_type
union all
select 208 as analysis_id,
CAST('Number of visit records outside valid observation period' as STRING) as analysis_name,
CAST(NULL AS STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('VISITS' as STRING) as analysis_type
union all
select 209 as analysis_id,
CAST('Number of visit records with end date < start date' as STRING) as analysis_name,
CAST(NULL AS STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('VISITS' as STRING) as analysis_type
union all
select 210 as analysis_id,
CAST('Number of visit records with invalid care_site_id' as STRING) as analysis_name,
CAST(NULL AS STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('VISITS' as STRING) as analysis_type
union all
select 211 as analysis_id,
CAST('Distribution of length of stay by visit_concept_id' as STRING) as analysis_name,
CAST('visit_concept_id' as STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('VISITS' as STRING) as analysis_type
union all
select 220 as analysis_id,
CAST('Number of visit occurrence records by visit occurrence start month' as STRING) as analysis_name,
CAST('calendar month' as STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('VISITS' as STRING) as analysis_type
union all
select 400 as analysis_id,
CAST('Number of persons with at least one condition occurrence, by condition_concept_id' as STRING) as analysis_name,
CAST('condition_concept_id' as STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('CONDITION' as STRING) as analysis_type
union all
select 401 as analysis_id,
CAST('Number of condition occurrence records, by condition_concept_id' as STRING) as analysis_name,
CAST('condition_concept_id' as STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('CONDITION' as STRING) as analysis_type
union all
select 402 as analysis_id,
CAST('Number of persons by condition occurrence start month, by condition_concept_id' as STRING) as analysis_name,
CAST('condition_concept_id' as STRING) as stratum_1_name,
CAST('calendar month' as STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('CONDITION' as STRING) as analysis_type
union all
select 403 as analysis_id,
CAST('Number of distinct condition occurrence concepts per person' as STRING) as analysis_name,
CAST(NULL AS STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('CONDITION' as STRING) as analysis_type
union all
select 404 as analysis_id,
CAST('Number of persons with at least one condition occurrence, by condition_concept_id by calendar year by gender by age decile' as STRING) as analysis_name,
CAST('condition_concept_id' as STRING) as stratum_1_name,
CAST('calendar year' as STRING) as stratum_2_name,
CAST('gender_concept_id' as STRING) as stratum_3_name,
CAST('age decile' as STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('CONDITION' as STRING) as analysis_type
union all
select 405 as analysis_id,
CAST('Number of condition occurrence records, by condition_concept_id by condition_type_concept_id' as STRING) as analysis_name,
CAST('condition_concept_id' as STRING) as stratum_1_name,
CAST('condition_type_concept_id' as STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('CONDITION' as STRING) as analysis_type
union all
select 406 as analysis_id,
CAST('Distribution of age by condition_concept_id' as STRING) as analysis_name,
CAST('condition_concept_id' as STRING) as stratum_1_name,
CAST('gender_concept_id' as STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('CONDITION' as STRING) as analysis_type
union all
select 409 as analysis_id,
CAST('Number of condition occurrence records with invalid person_id' as STRING) as analysis_name,
CAST(NULL AS STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('CONDITION' as STRING) as analysis_type
union all
select 410 as analysis_id,
CAST('Number of condition occurrence records outside valid observation period' as STRING) as analysis_name,
CAST(NULL AS STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('CONDITION' as STRING) as analysis_type
union all
select 411 as analysis_id,
CAST('Number of condition occurrence records with end date < start date' as STRING) as analysis_name,
CAST(NULL AS STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('CONDITION' as STRING) as analysis_type
union all
select 412 as analysis_id,
CAST('Number of condition occurrence records with invalid provider_id' as STRING) as analysis_name,
CAST(NULL AS STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('CONDITION' as STRING) as analysis_type
union all
select 413 as analysis_id,
CAST('Number of condition occurrence records with invalid visit_id' as STRING) as analysis_name,
CAST(NULL AS STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('CONDITION' as STRING) as analysis_type
union all
select 420 as analysis_id,
CAST('Number of condition occurrence records by condition occurrence start month' as STRING) as analysis_name,
CAST('calendar month' as STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('CONDITION' as STRING) as analysis_type
union all
select 500 as analysis_id,
CAST('Number of persons with death, by cause_of_death_concept_id' as STRING) as analysis_name,
CAST('cause_of_death_concept_id' as STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('DEATH' as STRING) as analysis_type
union all
select 501 as analysis_id,
CAST('Number of records of death, by cause_of_death_concept_id' as STRING) as analysis_name,
CAST('cause_of_death_concept_id' as STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('DEATH' as STRING) as analysis_type
union all
select 502 as analysis_id,
CAST('Number of persons by death month' as STRING) as analysis_name,
CAST('calendar month' as STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('DEATH' as STRING) as analysis_type
union all
select 504 as analysis_id,
CAST('Number of persons with a death, by calendar year by gender by age decile' as STRING) as analysis_name,
CAST('calendar year' as STRING) as stratum_1_name,
CAST('gender_concept_id' as STRING) as stratum_2_name,
CAST('age decile' as STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('DEATH' as STRING) as analysis_type
union all
select 505 as analysis_id,
CAST('Number of death records, by death_type_concept_id' as STRING) as analysis_name,
CAST('death_type_concept_id' as STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('DEATH' as STRING) as analysis_type
union all
select 506 as analysis_id,
CAST('Distribution of age at death by gender' as STRING) as analysis_name,
CAST('gender_concept_id' as STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('DEATH' as STRING) as analysis_type
union all
select 509 as analysis_id,
CAST('Number of death records with invalid person_id' as STRING) as analysis_name,
CAST(NULL AS STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('DEATH' as STRING) as analysis_type
union all
select 510 as analysis_id,
CAST('Number of death records outside valid observation period' as STRING) as analysis_name,
CAST(NULL AS STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('DEATH' as STRING) as analysis_type
union all
select 511 as analysis_id,
CAST('Distribution of time from death to last condition' as STRING) as analysis_name,
CAST(NULL AS STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('DEATH' as STRING) as analysis_type
union all
select 512 as analysis_id,
CAST('Distribution of time from death to last drug' as STRING) as analysis_name,
CAST(NULL AS STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('DEATH' as STRING) as analysis_type
union all
select 513 as analysis_id,
CAST('Distribution of time from death to last visit' as STRING) as analysis_name,
CAST(NULL AS STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('DEATH' as STRING) as analysis_type
union all
select 514 as analysis_id,
CAST('Distribution of time from death to last procedure' as STRING) as analysis_name,
CAST(NULL AS STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('DEATH' as STRING) as analysis_type
union all
select 515 as analysis_id,
CAST('Distribution of time from death to last observation' as STRING) as analysis_name,
CAST(NULL AS STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('DEATH' as STRING) as analysis_type
union all
select 600 as analysis_id,
CAST('Number of persons with at least one procedure occurrence, by procedure_concept_id' as STRING) as analysis_name,
CAST('procedure_concept_id' as STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('PROCEDURE' as STRING) as analysis_type
union all
select 601 as analysis_id,
CAST('Number of procedure occurrence records, by procedure_concept_id' as STRING) as analysis_name,
CAST('procedure_concept_id' as STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('PROCEDURE' as STRING) as analysis_type
union all
select 602 as analysis_id,
CAST('Number of persons by procedure occurrence start month, by procedure_concept_id' as STRING) as analysis_name,
CAST('procedure_concept_id' as STRING) as stratum_1_name,
CAST('calendar month' as STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('PROCEDURE' as STRING) as analysis_type
union all
select 603 as analysis_id,
CAST('Number of distinct procedure occurrence concepts per person' as STRING) as analysis_name,
CAST(NULL AS STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('PROCEDURE' as STRING) as analysis_type
union all
select 604 as analysis_id,
CAST('Number of persons with at least one procedure occurrence, by procedure_concept_id by calendar year by gender by age decile' as STRING) as analysis_name,
CAST('procedure_concept_id' as STRING) as stratum_1_name,
CAST('calendar year' as STRING) as stratum_2_name,
CAST('gender_concept_id' as STRING) as stratum_3_name,
CAST('age decile' as STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('PROCEDURE' as STRING) as analysis_type
union all
select 605 as analysis_id,
CAST('Number of procedure occurrence records, by procedure_concept_id by procedure_type_concept_id' as STRING) as analysis_name,
CAST('procedure_concept_id' as STRING) as stratum_1_name,
CAST('procedure_type_concept_id' as STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('PROCEDURE' as STRING) as analysis_type
union all
select 606 as analysis_id,
CAST('Distribution of age by procedure_concept_id' as STRING) as analysis_name,
CAST('procedure_concept_id' as STRING) as stratum_1_name,
CAST('gender_concept_id' as STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('PROCEDURE' as STRING) as analysis_type
union all
select 609 as analysis_id,
CAST('Number of procedure occurrence records with invalid person_id' as STRING) as analysis_name,
CAST(NULL AS STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('PROCEDURE' as STRING) as analysis_type
union all
select 610 as analysis_id,
CAST('Number of procedure occurrence records outside valid observation period' as STRING) as analysis_name,
CAST(NULL AS STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('PROCEDURE' as STRING) as analysis_type
union all
select 612 as analysis_id,
CAST('Number of procedure occurrence records with invalid provider_id' as STRING) as analysis_name,
CAST(NULL AS STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('PROCEDURE' as STRING) as analysis_type
union all
select 613 as analysis_id,
CAST('Number of procedure occurrence records with invalid visit_id' as STRING) as analysis_name,
CAST(NULL AS STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('PROCEDURE' as STRING) as analysis_type
union all
select 620 as analysis_id,
CAST('Number of procedure occurrence records by procedure occurrence start month' as STRING) as analysis_name,
CAST('calendar month' as STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('PROCEDURE' as STRING) as analysis_type
union all
select 700 as analysis_id,
CAST('Number of persons with at least one drug exposure, by drug_concept_id' as STRING) as analysis_name,
CAST('drug_concept_id' as STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('DRUG' as STRING) as analysis_type
union all
select 701 as analysis_id,
CAST('Number of drug exposure records, by drug_concept_id' as STRING) as analysis_name,
CAST('drug_concept_id' as STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('DRUG' as STRING) as analysis_type
union all
select 702 as analysis_id,
CAST('Number of persons by drug exposure start month, by drug_concept_id' as STRING) as analysis_name,
CAST('drug_concept_id' as STRING) as stratum_1_name,
CAST('calendar month' as STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('DRUG' as STRING) as analysis_type
union all
select 703 as analysis_id,
CAST('Number of distinct drug exposure concepts per person' as STRING) as analysis_name,
CAST(NULL AS STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('DRUG' as STRING) as analysis_type
union all
select 704 as analysis_id,
CAST('Number of persons with at least one drug exposure, by drug_concept_id by calendar year by gender by age decile' as STRING) as analysis_name,
CAST('drug_concept_id' as STRING) as stratum_1_name,
CAST('calendar year' as STRING) as stratum_2_name,
CAST('gender_concept_id' as STRING) as stratum_3_name,
CAST('age decile' as STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('DRUG' as STRING) as analysis_type
union all
select 705 as analysis_id,
CAST('Number of drug exposure records, by drug_concept_id by drug_type_concept_id' as STRING) as analysis_name,
CAST('drug_concept_id' as STRING) as stratum_1_name,
CAST('drug_type_concept_id' as STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('DRUG' as STRING) as analysis_type
union all
select 706 as analysis_id,
CAST('Distribution of age by drug_concept_id' as STRING) as analysis_name,
CAST('drug_concept_id' as STRING) as stratum_1_name,
CAST('gender_concept_id' as STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('DRUG' as STRING) as analysis_type
union all
select 709 as analysis_id,
CAST('Number of drug exposure records with invalid person_id' as STRING) as analysis_name,
CAST(NULL AS STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('DRUG' as STRING) as analysis_type
union all
select 710 as analysis_id,
CAST('Number of drug exposure records outside valid observation period' as STRING) as analysis_name,
CAST(NULL AS STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('DRUG' as STRING) as analysis_type
union all
select 711 as analysis_id,
CAST('Number of drug exposure records with end date < start date' as STRING) as analysis_name,
CAST(NULL AS STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('DRUG' as STRING) as analysis_type
union all
select 712 as analysis_id,
CAST('Number of drug exposure records with invalid provider_id' as STRING) as analysis_name,
CAST(NULL AS STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('DRUG' as STRING) as analysis_type
union all
select 713 as analysis_id,
CAST('Number of drug exposure records with invalid visit_id' as STRING) as analysis_name,
CAST(NULL AS STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('DRUG' as STRING) as analysis_type
union all
select 715 as analysis_id,
CAST('Distribution of days_supply by drug_concept_id' as STRING) as analysis_name,
CAST('drug_concept_id' as STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('DRUG' as STRING) as analysis_type
union all
select 716 as analysis_id,
CAST('Distribution of refills by drug_concept_id' as STRING) as analysis_name,
CAST('drug_concept_id' as STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('DRUG' as STRING) as analysis_type
union all
select 717 as analysis_id,
CAST('Distribution of quantity by drug_concept_id' as STRING) as analysis_name,
CAST('drug_concept_id' as STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('DRUG' as STRING) as analysis_type
union all
select 720 as analysis_id,
CAST('Number of drug exposure records by drug exposure start month' as STRING) as analysis_name,
CAST('calendar month' as STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('DRUG' as STRING) as analysis_type
union all
select 800 as analysis_id,
CAST('Number of persons with at least one observation occurrence, by observation_concept_id' as STRING) as analysis_name,
CAST('observation_concept_id' as STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('OBSERVATION' as STRING) as analysis_type
union all
select 801 as analysis_id,
CAST('Number of observation occurrence records, by observation_concept_id' as STRING) as analysis_name,
CAST('observation_concept_id' as STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('OBSERVATION' as STRING) as analysis_type
union all
select 802 as analysis_id,
CAST('Number of persons by observation occurrence start month, by observation_concept_id' as STRING) as analysis_name,
CAST('observation_concept_id' as STRING) as stratum_1_name,
CAST('calendar month' as STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('OBSERVATION' as STRING) as analysis_type
union all
select 803 as analysis_id,
CAST('Number of distinct observation occurrence concepts per person' as STRING) as analysis_name,
CAST(NULL AS STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('OBSERVATION' as STRING) as analysis_type
union all
select 804 as analysis_id,
CAST('Number of persons with at least one observation occurrence, by observation_concept_id by calendar year by gender by age decile' as STRING) as analysis_name,
CAST('observation_concept_id' as STRING) as stratum_1_name,
CAST('calendar year' as STRING) as stratum_2_name,
CAST('gender_concept_id' as STRING) as stratum_3_name,
CAST('age decile' as STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('OBSERVATION' as STRING) as analysis_type
union all
select 805 as analysis_id,
CAST('Number of observation occurrence records, by observation_concept_id by observation_type_concept_id' as STRING) as analysis_name,
CAST('observation_concept_id' as STRING) as stratum_1_name,
CAST('observation_type_concept_id' as STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('OBSERVATION' as STRING) as analysis_type
union all
select 806 as analysis_id,
CAST('Distribution of age by observation_concept_id' as STRING) as analysis_name,
CAST('observation_concept_id' as STRING) as stratum_1_name,
CAST('gender_concept_id' as STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('OBSERVATION' as STRING) as analysis_type
union all
select 807 as analysis_id,
CAST('Number of observation occurrence records, by observation_concept_id and unit_concept_id' as STRING) as analysis_name,
CAST('observation_concept_id' as STRING) as stratum_1_name,
CAST('unit_concept_id' as STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('OBSERVATION' as STRING) as analysis_type
union all
select 809 as analysis_id,
CAST('Number of observation records with invalid person_id' as STRING) as analysis_name,
CAST(NULL AS STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('OBSERVATION' as STRING) as analysis_type
union all
select 810 as analysis_id,
CAST('Number of observation records outside valid observation period' as STRING) as analysis_name,
CAST(NULL AS STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('OBSERVATION' as STRING) as analysis_type
union all
select 812 as analysis_id,
CAST('Number of observation records with invalid provider_id' as STRING) as analysis_name,
CAST(NULL AS STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('OBSERVATION' as STRING) as analysis_type
union all
select 813 as analysis_id,
CAST('Number of observation records with invalid visit_id' as STRING) as analysis_name,
CAST(NULL AS STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('OBSERVATION' as STRING) as analysis_type
union all
select 814 as analysis_id,
CAST('Number of observation records with no value (numeric, string, or concept)' as STRING) as analysis_name,
CAST(NULL AS STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('OBSERVATION' as STRING) as analysis_type
union all
select 815 as analysis_id,
CAST('Distribution of numeric values, by observation_concept_id and unit_concept_id' as STRING) as analysis_name,
CAST(NULL AS STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('OBSERVATION' as STRING) as analysis_type
union all
select 816 as analysis_id,
CAST('Distribution of low range, by observation_concept_id and unit_concept_id' as STRING) as analysis_name,
CAST(NULL AS STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('OBSERVATION' as STRING) as analysis_type
union all
select 817 as analysis_id,
CAST('Distribution of high range, by observation_concept_id and unit_concept_id' as STRING) as analysis_name,
CAST(NULL AS STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('OBSERVATION' as STRING) as analysis_type
union all
select 818 as analysis_id,
CAST('Number of observation records below/within/above normal range, by observation_concept_id and unit_concept_id' as STRING) as analysis_name,
CAST(NULL AS STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('OBSERVATION' as STRING) as analysis_type
union all
select 820 as analysis_id,
CAST('Number of observation records by observation start month' as STRING) as analysis_name,
CAST('calendar month' as STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('OBSERVATION' as STRING) as analysis_type
union all
select 900 as analysis_id,
CAST('Number of persons with at least one drug era, by drug_concept_id' as STRING) as analysis_name,
CAST('drug_concept_id' as STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('DRUG_ERA' as STRING) as analysis_type
union all
select 901 as analysis_id,
CAST('Number of drug era records, by drug_concept_id' as STRING) as analysis_name,
CAST('drug_concept_id' as STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('DRUG_ERA' as STRING) as analysis_type
union all
select 902 as analysis_id,
CAST('Number of persons by drug era start month, by drug_concept_id' as STRING) as analysis_name,
CAST('drug_concept_id' as STRING) as stratum_1_name,
CAST('calendar month' as STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('DRUG_ERA' as STRING) as analysis_type
union all
select 903 as analysis_id,
CAST('Number of distinct drug era concepts per person' as STRING) as analysis_name,
CAST(NULL AS STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('DRUG_ERA' as STRING) as analysis_type
union all
select 904 as analysis_id,
CAST('Number of persons with at least one drug era, by drug_concept_id by calendar year by gender by age decile' as STRING) as analysis_name,
CAST('drug_concept_id' as STRING) as stratum_1_name,
CAST('calendar year' as STRING) as stratum_2_name,
CAST('gender_concept_id' as STRING) as stratum_3_name,
CAST('age decile' as STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('DRUG_ERA' as STRING) as analysis_type
union all
select 906 as analysis_id,
CAST('Distribution of age by drug_concept_id' as STRING) as analysis_name,
CAST('drug_concept_id' as STRING) as stratum_1_name,
CAST('gender_concept_id' as STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('DRUG_ERA' as STRING) as analysis_type
union all
select 907 as analysis_id,
CAST('Distribution of drug era length, by drug_concept_id' as STRING) as analysis_name,
CAST('drug_concept_id' as STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('DRUG_ERA' as STRING) as analysis_type
union all
select 908 as analysis_id,
CAST('Number of drug eras without valid person' as STRING) as analysis_name,
CAST(NULL AS STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('DRUG_ERA' as STRING) as analysis_type
union all
select 909 as analysis_id,
CAST('Number of drug eras outside valid observation period' as STRING) as analysis_name,
CAST(NULL AS STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('DRUG_ERA' as STRING) as analysis_type
union all
select 910 as analysis_id,
CAST('Number of drug eras with end date < start date' as STRING) as analysis_name,
CAST(NULL AS STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('DRUG_ERA' as STRING) as analysis_type
union all
select 920 as analysis_id,
CAST('Number of drug era records by drug era start month' as STRING) as analysis_name,
CAST('calendar month' as STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('DRUG_ERA' as STRING) as analysis_type
union all
select 1000 as analysis_id,
CAST('Number of persons with at least one condition era, by condition_concept_id' as STRING) as analysis_name,
CAST('condition_concept_id' as STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('CONDITION_ERA' as STRING) as analysis_type
union all
select 1001 as analysis_id,
CAST('Number of condition era records, by condition_concept_id' as STRING) as analysis_name,
CAST('condition_concept_id' as STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('CONDITION_ERA' as STRING) as analysis_type
union all
select 1002 as analysis_id,
CAST('Number of persons by condition era start month, by condition_concept_id' as STRING) as analysis_name,
CAST('condition_concept_id' as STRING) as stratum_1_name,
CAST('calendar month' as STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('CONDITION_ERA' as STRING) as analysis_type
union all
select 1003 as analysis_id,
CAST('Number of distinct condition era concepts per person' as STRING) as analysis_name,
CAST(NULL AS STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('CONDITION_ERA' as STRING) as analysis_type
union all
select 1004 as analysis_id,
CAST('Number of persons with at least one condition era, by condition_concept_id by calendar year by gender by age decile' as STRING) as analysis_name,
CAST('condition_concept_id' as STRING) as stratum_1_name,
CAST('calendar year' as STRING) as stratum_2_name,
CAST('gender_concept_id' as STRING) as stratum_3_name,
CAST('age decile' as STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('CONDITION_ERA' as STRING) as analysis_type
union all
select 1006 as analysis_id,
CAST('Distribution of age by condition_concept_id' as STRING) as analysis_name,
CAST('condition_concept_id' as STRING) as stratum_1_name,
CAST('gender_concept_id' as STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('CONDITION_ERA' as STRING) as analysis_type
union all
select 1007 as analysis_id,
CAST('Distribution of condition era length, by condition_concept_id' as STRING) as analysis_name,
CAST('condition_concept_id' as STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('CONDITION_ERA' as STRING) as analysis_type
union all
select 1008 as analysis_id,
CAST('Number of condition eras without valid person' as STRING) as analysis_name,
CAST(NULL AS STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('CONDITION_ERA' as STRING) as analysis_type
union all
select 1009 as analysis_id,
CAST('Number of condition eras outside valid observation period' as STRING) as analysis_name,
CAST(NULL AS STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('CONDITION_ERA' as STRING) as analysis_type
union all
select 1010 as analysis_id,
CAST('Number of condition eras with end date < start date' as STRING) as analysis_name,
CAST(NULL AS STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('CONDITION_ERA' as STRING) as analysis_type
union all
select 1020 as analysis_id,
CAST('Number of condition era records by condition era start month' as STRING) as analysis_name,
CAST('calendar month' as STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('CONDITION_ERA' as STRING) as analysis_type
union all
select 1100 as analysis_id,
CAST('Number of persons by location 3-digit zip' as STRING) as analysis_name,
CAST('3-digit zip' as STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('LOCATION' as STRING) as analysis_type
union all
select 1101 as analysis_id,
CAST('Number of persons by location state' as STRING) as analysis_name,
CAST('state' as STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('LOCATION' as STRING) as analysis_type
union all
select 1200 as analysis_id,
CAST('Number of persons by place of service' as STRING) as analysis_name,
CAST('place_of_service_concept_id' as STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('CARE_SITE' as STRING) as analysis_type
union all
select 1201 as analysis_id,
CAST('Number of visits by place of service' as STRING) as analysis_name,
CAST('place_of_service_concept_id' as STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('CARE_SITE' as STRING) as analysis_type
union all
select 1300 as analysis_id,
CAST('Number of persons with at least one measurement occurrence, by measurement_concept_id' as STRING) as analysis_name,
CAST('measurement_concept_id' as STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('MEASUREMENT' as STRING) as analysis_type
union all
select 1301 as analysis_id,
CAST('Number of measurement occurrence records, by measurement_concept_id' as STRING) as analysis_name,
CAST('measurement_concept_id' as STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('MEASUREMENT' as STRING) as analysis_type
union all
select 1302 as analysis_id,
CAST('Number of persons by measurement occurrence start month, by measurement_concept_id' as STRING) as analysis_name,
CAST('measurement_concept_id' as STRING) as stratum_1_name,
CAST('calendar month' as STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('MEASUREMENT' as STRING) as analysis_type
union all
select 1303 as analysis_id,
CAST('Number of distinct measurement occurrence concepts per person' as STRING) as analysis_name,
CAST(NULL AS STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('MEASUREMENT' as STRING) as analysis_type
union all
select 1304 as analysis_id,
CAST('Number of persons with at least one measurement occurrence, by measurement_concept_id by calendar year by gender by age decile' as STRING) as analysis_name,
CAST('measurement_concept_id' as STRING) as stratum_1_name,
CAST('calendar year' as STRING) as stratum_2_name,
CAST('gender_concept_id' as STRING) as stratum_3_name,
CAST('age decile' as STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('MEASUREMENT' as STRING) as analysis_type
union all
select 1305 as analysis_id,
CAST('Number of measurement occurrence records, by measurement_concept_id by measurement_type_concept_id' as STRING) as analysis_name,
CAST('measurement_concept_id' as STRING) as stratum_1_name,
CAST('measurement_type_concept_id' as STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('MEASUREMENT' as STRING) as analysis_type
union all
select 1306 as analysis_id,
CAST('Distribution of age by measurement_concept_id' as STRING) as analysis_name,
CAST('measurement_concept_id' as STRING) as stratum_1_name,
CAST('gender_concept_id' as STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('MEASUREMENT' as STRING) as analysis_type
union all
select 1307 as analysis_id,
CAST('Number of measurement occurrence records, by measurement_concept_id and unit_concept_id' as STRING) as analysis_name,
CAST('measurement_concept_id' as STRING) as stratum_1_name,
CAST('unit_concept_id' as STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('MEASUREMENT' as STRING) as analysis_type
union all
select 1309 as analysis_id,
CAST('Number of measurement records with invalid person_id' as STRING) as analysis_name,
CAST(NULL AS STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('MEASUREMENT' as STRING) as analysis_type
union all
select 1310 as analysis_id,
CAST('Number of measurement records outside valid measurement period' as STRING) as analysis_name,
CAST(NULL AS STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('MEASUREMENT' as STRING) as analysis_type
union all
select 1312 as analysis_id,
CAST('Number of measurement records with invalid provider_id' as STRING) as analysis_name,
CAST(NULL AS STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('MEASUREMENT' as STRING) as analysis_type
union all
select 1313 as analysis_id,
CAST('Number of measurement records with invalid visit_id' as STRING) as analysis_name,
CAST(NULL AS STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('MEASUREMENT' as STRING) as analysis_type
union all
select 1314 as analysis_id,
CAST('Number of measurement records with no value (numeric, string, or concept)' as STRING) as analysis_name,
CAST(NULL AS STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('MEASUREMENT' as STRING) as analysis_type
union all
select 1315 as analysis_id,
CAST('Distribution of numeric values, by measurement_concept_id and unit_concept_id' as STRING) as analysis_name,
CAST(NULL AS STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('MEASUREMENT' as STRING) as analysis_type
union all
select 1316 as analysis_id,
CAST('Distribution of low range, by measurement_concept_id and unit_concept_id' as STRING) as analysis_name,
CAST(NULL AS STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('MEASUREMENT' as STRING) as analysis_type
union all
select 1317 as analysis_id,
CAST('Distribution of high range, by measurement_concept_id and unit_concept_id' as STRING) as analysis_name,
CAST(NULL AS STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('MEASUREMENT' as STRING) as analysis_type
union all
select 1318 as analysis_id,
CAST('Number of measurement records below/within/above normal range, by measurement_concept_id and unit_concept_id' as STRING) as analysis_name,
CAST(NULL AS STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('MEASUREMENT' as STRING) as analysis_type
union all
select 1320 as analysis_id,
CAST('Number of measurement records by measurement start month' as STRING) as analysis_name,
CAST('calendar month' as STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('MEASUREMENT' as STRING) as analysis_type
union all
select 1700 as analysis_id,
CAST('Number of records by cohort_definition_id' as STRING) as analysis_name,
CAST('cohort_definition_id' as STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('COHORT' as STRING) as analysis_type
union all
select 1701 as analysis_id,
CAST('Number of records with cohort end date < cohort start date' as STRING) as analysis_name,
CAST(NULL AS STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('COHORT' as STRING) as analysis_type
union all
select 1800 as analysis_id,
CAST('Number of persons by age, with age at cohort start' as STRING) as analysis_name,
CAST('age' as STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('COHORT_SPECIFIC_ANALYSES' as STRING) as analysis_type
union all
select 1801 as analysis_id,
CAST('Distribution of age at cohort start' as STRING) as analysis_name,
CAST(NULL AS STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('COHORT_SPECIFIC_ANALYSES' as STRING) as analysis_type
union all
select 1802 as analysis_id,
CAST('Distribution of age at cohort start by gender' as STRING) as analysis_name,
CAST('gender_concept_id' as STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('COHORT_SPECIFIC_ANALYSES' as STRING) as analysis_type
union all
select 1803 as analysis_id,
CAST('Distribution of age at cohort start by cohort start year' as STRING) as analysis_name,
CAST('calendar year' as STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('COHORT_SPECIFIC_ANALYSES' as STRING) as analysis_type
union all
select 1804 as analysis_id,
CAST('Number of persons by duration from cohort start to cohort end, in 30d increments' as STRING) as analysis_name,
CAST('Cohort period length 30d increments' as STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('COHORT_SPECIFIC_ANALYSES' as STRING) as analysis_type
union all
select 1805 as analysis_id,
CAST('Number of persons by duration from observation start to cohort start, in 30d increments' as STRING) as analysis_name,
CAST('Baseline period length 30d increments' as STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('COHORT_SPECIFIC_ANALYSES' as STRING) as analysis_type
union all
select 1806 as analysis_id,
CAST('Number of persons by duration from cohort start to observation end, in 30d increments' as STRING) as analysis_name,
CAST('Follow-up period length 30d increments' as STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('COHORT_SPECIFIC_ANALYSES' as STRING) as analysis_type
union all
select 1807 as analysis_id,
CAST('Number of persons by duration from cohort end to observation end, in 30d increments' as STRING) as analysis_name,
CAST('Post-cohort period length 30d increments' as STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('COHORT_SPECIFIC_ANALYSES' as STRING) as analysis_type
union all
select 1808 as analysis_id,
CAST('Distribution of duration (days) from cohort start to cohort end' as STRING) as analysis_name,
CAST(NULL AS STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('COHORT_SPECIFIC_ANALYSES' as STRING) as analysis_type
union all
select 1809 as analysis_id,
CAST('Distribution of duration (days) from cohort start to cohort end, by gender' as STRING) as analysis_name,
CAST('gender_concept_id' as STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('COHORT_SPECIFIC_ANALYSES' as STRING) as analysis_type
union all
select 1810 as analysis_id,
CAST('Distribution of duration (days) from cohort start to cohort end, by age decile' as STRING) as analysis_name,
CAST('age decile' as STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('COHORT_SPECIFIC_ANALYSES' as STRING) as analysis_type
union all
select 1811 as analysis_id,
CAST('Distribution of duration (days) from observation start to cohort start' as STRING) as analysis_name,
CAST(NULL AS STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('COHORT_SPECIFIC_ANALYSES' as STRING) as analysis_type
union all
select 1812 as analysis_id,
CAST('Distribution of duration (days) from cohort start to observation end' as STRING) as analysis_name,
CAST(NULL AS STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('COHORT_SPECIFIC_ANALYSES' as STRING) as analysis_type
union all
select 1813 as analysis_id,
CAST('Distribution of duration (days) from cohort end to observation end' as STRING) as analysis_name,
CAST(NULL AS STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('COHORT_SPECIFIC_ANALYSES' as STRING) as analysis_type
union all
select 1814 as analysis_id,
CAST('Number of persons by cohort start year by gender by age decile' as STRING) as analysis_name,
CAST('calendar year' as STRING) as stratum_1_name,
CAST('gender_concept_id' as STRING) as stratum_2_name,
CAST('age decile' as STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('COHORT_SPECIFIC_ANALYSES' as STRING) as analysis_type
union all
select 1815 as analysis_id,
CAST('Number of persons by cohort start month' as STRING) as analysis_name,
CAST('calendar month' as STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('COHORT_SPECIFIC_ANALYSES' as STRING) as analysis_type
union all
select 1816 as analysis_id,
CAST('Number of persons by number of cohort periods' as STRING) as analysis_name,
CAST('number of cohort periods' as STRING) as stratum_1_name,
CAST(NULL AS STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('COHORT_SPECIFIC_ANALYSES' as STRING) as analysis_type
union all
select 1820 as analysis_id,
CAST('Number of persons by duration from cohort start to first occurrence of condition occurrence, by condition_concept_id' as STRING) as analysis_name,
CAST('condition_concept_id' as STRING) as stratum_1_name,
CAST('time-to-event 30d increments' as STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('COHORT_SPECIFIC_ANALYSES' as STRING) as analysis_type
union all
select 1821 as analysis_id,
CAST('Number of events by duration from cohort start to all occurrences of condition occurrence, by condition_concept_id' as STRING) as analysis_name,
CAST('condition_concept_id' as STRING) as stratum_1_name,
CAST('time-to-event 30d increments' as STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('COHORT_SPECIFIC_ANALYSES' as STRING) as analysis_type
union all
select 1830 as analysis_id,
CAST('Number of persons by duration from cohort start to first occurrence of procedure occurrence, by procedure_concept_id' as STRING) as analysis_name,
CAST('procedure_concept_id' as STRING) as stratum_1_name,
CAST('time-to-event 30d increments' as STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('COHORT_SPECIFIC_ANALYSES' as STRING) as analysis_type
union all
select 1831 as analysis_id,
CAST('Number of events by duration from cohort start to all occurrences of procedure occurrence, by procedure_concept_id' as STRING) as analysis_name,
CAST('procedure_concept_id' as STRING) as stratum_1_name,
CAST('time-to-event 30d increments' as STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('COHORT_SPECIFIC_ANALYSES' as STRING) as analysis_type
union all
select 1840 as analysis_id,
CAST('Number of persons by duration from cohort start to first occurrence of drug exposure, by drug_concept_id' as STRING) as analysis_name,
CAST('drug_concept_id' as STRING) as stratum_1_name,
CAST('time-to-event 30d increments' as STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('COHORT_SPECIFIC_ANALYSES' as STRING) as analysis_type
union all
select 1841 as analysis_id,
CAST('Number of events by duration from cohort start to all occurrences of drug exposure, by drug_concept_id' as STRING) as analysis_name,
CAST('drug_concept_id' as STRING) as stratum_1_name,
CAST('time-to-event 30d increments' as STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('COHORT_SPECIFIC_ANALYSES' as STRING) as analysis_type
union all
select 1850 as analysis_id,
CAST('Number of persons by duration from cohort start to first occurrence of observation, by observation_concept_id' as STRING) as analysis_name,
CAST('observation_concept_id' as STRING) as stratum_1_name,
CAST('time-to-event 30d increments' as STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('COHORT_SPECIFIC_ANALYSES' as STRING) as analysis_type
union all
select 1851 as analysis_id,
CAST('Number of events by duration from cohort start to all occurrences of observation, by observation_concept_id' as STRING) as analysis_name,
CAST('observation_concept_id' as STRING) as stratum_1_name,
CAST('time-to-event 30d increments' as STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('COHORT_SPECIFIC_ANALYSES' as STRING) as analysis_type
union all
select 1860 as analysis_id,
CAST('Number of persons by duration from cohort start to first occurrence of condition era, by condition_concept_id' as STRING) as analysis_name,
CAST('condition_concept_id' as STRING) as stratum_1_name,
CAST('time-to-event 30d increments' as STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('COHORT_SPECIFIC_ANALYSES' as STRING) as analysis_type
union all
select 1861 as analysis_id,
CAST('Number of events by duration from cohort start to all occurrences of condition era, by condition_concept_id' as STRING) as analysis_name,
CAST('condition_concept_id' as STRING) as stratum_1_name,
CAST('time-to-event 30d increments' as STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('COHORT_SPECIFIC_ANALYSES' as STRING) as analysis_type
union all
select 1870 as analysis_id,
CAST('Number of persons by duration from cohort start to first occurrence of drug era, by drug_concept_id' as STRING) as analysis_name,
CAST('drug_concept_id' as STRING) as stratum_1_name,
CAST('time-to-event 30d increments' as STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('COHORT_SPECIFIC_ANALYSES' as STRING) as analysis_type
union all
select 1871 as analysis_id,
CAST('Number of events by duration from cohort start to all occurrences of drug era, by drug_concept_id' as STRING) as analysis_name,
CAST('drug_concept_id' as STRING) as stratum_1_name,
CAST('time-to-event 30d increments' as STRING) as stratum_2_name,
CAST(NULL AS STRING) as stratum_3_name,
CAST(NULL AS STRING) as stratum_4_name,
CAST(NULL AS STRING) as stratum_5_name,
CAST('COHORT_SPECIFIC_ANALYSES' as STRING) as analysis_type
union all
select 4000 as analysis_id,
CAST('Distribution of observation period days by period_id in the 365 days prior to first cohort_start_date' as STRING) as analysis_name,
NULL as stratum_1_name,
NULL as stratum_2_name,
NULL as stratum_3_name,
NULL as stratum_4_name,
NULL as stratum_5_name,
CAST('HEALTHCARE_UTILIZATION' as STRING) as analysis_type
union all
select 4001 as analysis_id,
CAST('Number of subjects with visits by period_id, by visit_concept_id, by visit_type_concept_id in the 365d prior to first cohort start date' as STRING) as analysis_name,
NULL as stratum_1_name,
NULL as stratum_2_name,
NULL as stratum_3_name,
NULL as stratum_4_name,
NULL as stratum_5_name,
CAST('HEALTHCARE_UTILIZATION' as STRING) as analysis_type
union all
select 4002 as analysis_id,
CAST('Distribution of number of visit occurrence records per subject by period_id, by visit_concept_id, by visit_type_concept_id in 365d prior to cohort start date' as STRING) as analysis_name,
NULL as stratum_1_name,
NULL as stratum_2_name,
NULL as stratum_3_name,
NULL as stratum_4_name,
NULL as stratum_5_name,
CAST('HEALTHCARE_UTILIZATION' as STRING) as analysis_type
union all
select 4003 as analysis_id,
CAST('Distribution of number of visit dates per subject by period_id, by visit_concept_id, by visit_type_concept_id in 365d prior to first cohort start date' as STRING) as analysis_name,
NULL as stratum_1_name,
NULL as stratum_2_name,
NULL as stratum_3_name,
NULL as stratum_4_name,
NULL as stratum_5_name,
CAST('HEALTHCARE_UTILIZATION' as STRING) as analysis_type
union all
select 4003 as analysis_id,
CAST('Distribution of number of visit dates per subject by period_id, by visit_concept_id, by visit_type_concept_id in 365d prior to first cohort start date' as STRING) as analysis_name,
NULL as stratum_1_name,
NULL as stratum_2_name,
NULL as stratum_3_name,
NULL as stratum_4_name,
NULL as stratum_5_name,
CAST('HEALTHCARE_UTILIZATION' as STRING) as analysis_type
union all
select 4004 as analysis_id,
CAST('Distribution of number of care_site+visit dates per subject by period_id, by visit_concept_id, by visit_type_concept_id in 365d prior to first cohort start date' as STRING) as analysis_name,
NULL as stratum_1_name,
NULL as stratum_2_name,
NULL as stratum_3_name,
NULL as stratum_4_name,
NULL as stratum_5_name,
CAST('HEALTHCARE_UTILIZATION' as STRING) as analysis_type
union all
select 4005 as analysis_id,
CAST('Distribution of length of stay for inpatient visits per subject by period_id, by visit_concept_id, by visit_type_concept_id in the 365 days prior to first cohort_start_date' as STRING) as analysis_name,
NULL as stratum_1_name,
NULL as stratum_2_name,
NULL as stratum_3_name,
NULL as stratum_4_name,
NULL as stratum_5_name,
CAST('HEALTHCARE_UTILIZATION' as STRING) as analysis_type
union all
select 4006 as analysis_id,
CAST('Distribution of observation period days per subject, by period_id during cohort period' as STRING) as analysis_name,
NULL as stratum_1_name,
NULL as stratum_2_name,
NULL as stratum_3_name,
NULL as stratum_4_name,
NULL as stratum_5_name,
CAST('HEALTHCARE_UTILIZATION' as STRING) as analysis_type
union all
select 4007 as analysis_id,
CAST('Number of subjects with visits by period_id, by visit_concept_id, by visit_type_concept_id during the cohort period' as STRING) as analysis_name,
NULL as stratum_1_name,
NULL as stratum_2_name,
NULL as stratum_3_name,
NULL as stratum_4_name,
NULL as stratum_5_name,
CAST('HEALTHCARE_UTILIZATION' as STRING) as analysis_type
union all
select 4008 as analysis_id,
CAST('Distribution of number of visit occurrence records per subject by period_id, by visit_concept_id, by visit_type_concept_id during the cohort period' as STRING) as analysis_name,
NULL as stratum_1_name,
NULL as stratum_2_name,
NULL as stratum_3_name,
NULL as stratum_4_name,
NULL as stratum_5_name,
CAST('HEALTHCARE_UTILIZATION' as STRING) as analysis_type
union all
select 4009 as analysis_id,
CAST('Distribution of number of visit dates per subject by period_id, by visit_concept_id, by visit_type_concept_id during the cohort period' as STRING) as analysis_name,
NULL as stratum_1_name,
NULL as stratum_2_name,
NULL as stratum_3_name,
NULL as stratum_4_name,
NULL as stratum_5_name,
CAST('HEALTHCARE_UTILIZATION' as STRING) as analysis_type
union all
select 4010 as analysis_id,
CAST('Distribution of number of care_site+visit dates per subject by period_id, by visit_concept_id, by visit_type_concept_id during the cohort period' as STRING) as analysis_name,
NULL as stratum_1_name,
NULL as stratum_2_name,
NULL as stratum_3_name,
NULL as stratum_4_name,
NULL as stratum_5_name,
CAST('HEALTHCARE_UTILIZATION' as STRING) as analysis_type
union all
select 4011 as analysis_id,
CAST('Distribution of length of stay for inpatient visits per subject by period_id, by visit_concept_id, by visit_type_concept_id during cohort period' as STRING) as analysis_name,
NULL as stratum_1_name,
NULL as stratum_2_name,
NULL as stratum_3_name,
NULL as stratum_4_name,
NULL as stratum_5_name,
CAST('HEALTHCARE_UTILIZATION' as STRING) as analysis_type
union all
select 4012 as analysis_id,
CAST('Number of subjects with Drug Exposure by period_id, by drug_concept_id, by drug_type_concept_id in the 365d prior to first cohort start date' as STRING) as analysis_name,
NULL as stratum_1_name,
NULL as stratum_2_name,
NULL as stratum_3_name,
NULL as stratum_4_name,
NULL as stratum_5_name,
CAST('HEALTHCARE_UTILIZATION' as STRING) as analysis_type
union all
select 4013 as analysis_id,
CAST('Distribution of number of Drug Exposure records per subject, by period_id, by drug_concept_id in 365d prior to first cohort start date' as STRING) as analysis_name,
NULL as stratum_1_name,
NULL as stratum_2_name,
NULL as stratum_3_name,
NULL as stratum_4_name,
NULL as stratum_5_name,
CAST('HEALTHCARE_UTILIZATION' as STRING) as analysis_type
union all
select 4014 as analysis_id,
CAST('Distribution of greater than 0 drug day supply per subject by period_id, by drug_concept_id in the 365d prior to first cohort start date' as STRING) as analysis_name,
NULL as stratum_1_name,
NULL as stratum_2_name,
NULL as stratum_3_name,
NULL as stratum_4_name,
NULL as stratum_5_name,
CAST('HEALTHCARE_UTILIZATION' as STRING) as analysis_type
union all
select 4015 as analysis_id,
CAST('Distribution of greater than 0 drug quantity per subject by period_id, by drug_concept_id in the 365d prior to first cohort start date' as STRING) as analysis_name,
NULL as stratum_1_name,
NULL as stratum_2_name,
NULL as stratum_3_name,
NULL as stratum_4_name,
NULL as stratum_5_name,
CAST('HEALTHCARE_UTILIZATION' as STRING) as analysis_type
union all
select 4016 as analysis_id,
CAST('Number of subjects with Drug Exposure by period_id, by drug_concept_id, by drug_type_concept_id during the cohort period' as STRING) as analysis_name,
NULL as stratum_1_name,
NULL as stratum_2_name,
NULL as stratum_3_name,
NULL as stratum_4_name,
NULL as stratum_5_name,
CAST('HEALTHCARE_UTILIZATION' as STRING) as analysis_type
union all
select 4017 as analysis_id,
CAST('Distribution of number of Drug Exposure records per subject, by period_id, by drug_concept_id during the cohort period' as STRING) as analysis_name,
NULL as stratum_1_name,
NULL as stratum_2_name,
NULL as stratum_3_name,
NULL as stratum_4_name,
NULL as stratum_5_name,
CAST('HEALTHCARE_UTILIZATION' as STRING) as analysis_type
union all
select 4018 as analysis_id,
CAST('Distribution of greater than 0 drug day supply per subject by period_id, by drug_concept_id during the cohort period' as STRING) as analysis_name,
NULL as stratum_1_name,
NULL as stratum_2_name,
NULL as stratum_3_name,
NULL as stratum_4_name,
NULL as stratum_5_name,
CAST('HEALTHCARE_UTILIZATION' as STRING) as analysis_type
union all
select 4019 as analysis_id,
CAST('Distribution of greater than 0 drug quantity per subject by period_id, by drug_concept_id during the cohort period' as STRING) as analysis_name,
NULL as stratum_1_name,
NULL as stratum_2_name,
NULL as stratum_3_name,
NULL as stratum_4_name,
NULL as stratum_5_name,
CAST('HEALTHCARE_UTILIZATION' as STRING) as analysis_type
union all
select 4020 as analysis_id,
CAST('Distribution of greater than 0 US$ cost per subject by period_id, by visit_concept_id, by visit_type_concept_id, by cost_concept_id, by cost_type_concept_id in the 365d prior to first cohort start date' as STRING) as analysis_name,
NULL as stratum_1_name,
NULL as stratum_2_name,
NULL as stratum_3_name,
NULL as stratum_4_name,
NULL as stratum_5_name,
CAST('HEALTHCARE_UTILIZATION' as STRING) as analysis_type
union all
select 4021 as analysis_id,
CAST('Distribution of greater than 0 US$ cost per subject by period_id, by visit_concept_id, by visit_type_concept_id, by cost_concept_id, by cost_type_concept_id during the cohort period' as STRING) as analysis_name,
NULL as stratum_1_name,
NULL as stratum_2_name,
NULL as stratum_3_name,
NULL as stratum_4_name,
NULL as stratum_5_name,
CAST('HEALTHCARE_UTILIZATION' as STRING) as analysis_type
union all
select 4022 as analysis_id,
CAST('Distribution of greater than 0 US$ cost per subject by period_id, by drug_concept_id, by drug_type_concept_id, by cost_concept_id, by cost_type_concept_id in the 365d prior to first cohort start date' as STRING) as analysis_name,
NULL as stratum_1_name,
NULL as stratum_2_name,
NULL as stratum_3_name,
NULL as stratum_4_name,
NULL as stratum_5_name,
CAST('HEALTHCARE_UTILIZATION' as STRING) as analysis_type
union all
select 4023 as analysis_id,
CAST('Distribution of greater than 0 US$ cost per subject by period_id, by drug_concept_id, by drug_type_concept_id, by cost_concept_id, by cost_type_concept_id, by cost_type_concept_id during the cohort period' as STRING) as analysis_name,
NULL as stratum_1_name,
NULL as stratum_2_name,
NULL as stratum_3_name,
NULL as stratum_4_name,
NULL as stratum_5_name,
CAST('HEALTHCARE_UTILIZATION' as STRING) as analysis_type
) UNION ALL (SELECT analysis_id,analysis_name,stratum_1_name,stratum_2_name,stratum_3_name,stratum_4_name,stratum_5_name,analysis_type FROM <DB_NAME>_ach_res.heracles_analysis
))
INSERT OVERWRITE TABLE <DB_NAME>_ach_res.heracles_analysis
 (analysis_id,analysis_name,stratum_1_name,stratum_2_name,stratum_3_name,stratum_4_name,stratum_5_name,analysis_type) SELECT * FROM insertion_temp;

CREATE TABLE <DB_NAME>_ach_tmp.pggdlwq6digits
USING DELTA
AS
SELECT
digits.n 
FROM
(
 select 0 as n union all select 1 union all select 2 union all select 3 union all select 4 union all select 5 union all select 6 union all select 7 union all select 8 union all select 9
) digits;

CREATE TABLE <DB_NAME>_ach_tmp.pggdlwq6generate_dates
USING DELTA
AS
SELECT
y1.n + (10*y10.n) + (100*y100.n) + (1000*y1000.n) AS d_years,
 mths.n as d_months
FROM
<DB_NAME>_ach_tmp.pggdlwq6digits y1,
<DB_NAME>_ach_tmp.pggdlwq6digits y10,
(select 0 n union all select 1 union all select 9) y100,
(select 1 n union all select 2) y1000,
(select 1 n union all select 2 union all select 3 union all select 4 union all select 5 union all select 6 union all select 7 union all select 8 union all select 9 union all select 10 union all select 11 union all select 12) mths
 where y1.n + (10*y10.n) + (100*y100.n) + (1000*y1000.n) >= 1900 and y1.n + (10*y10.n) + (100*y100.n) + (1000*y1000.n) < 2100
;

CREATE TABLE <DB_NAME>_ach_tmp.pggdlwq6yearly_dates
USING DELTA
AS
SELECT
to_date(cast(d_years as string) || '-' || cast(d_months as string) || '-' || cast(01 as string)) as generated_date
FROM
<DB_NAME>_ach_tmp.pggdlwq6generate_dates
where d_months = 1
;

CREATE TABLE <DB_NAME>_ach_tmp.pggdlwq6monthly_dates
USING DELTA
AS
SELECT
to_date(cast(d_years as string) || '-' || cast(d_months as string) || '-' || cast(01 as string)) as generated_date
FROM
<DB_NAME>_ach_tmp.pggdlwq6generate_dates
;

CREATE TABLE <DB_NAME>_ach_tmp.pggdlwq6weekly_dates
USING DELTA
AS
SELECT
date_add(to_date(cast(1900 as string) || '-' || cast(1 as string) || '-' || cast(7 as string)), (7 * seq.rn)) as generated_date -- first sunday in 1900
FROM
(
 select d1.n + (10 * d10.n) + (100 * d100.n) + (1000 * d1000.n) as rn
 from <DB_NAME>_ach_tmp.pggdlwq6digits d1, <DB_NAME>_ach_tmp.pggdlwq6digits d10, <DB_NAME>_ach_tmp.pggdlwq6digits d100, <DB_NAME>_ach_tmp.pggdlwq6digits d1000
) seq;

CREATE TABLE <DB_NAME>_ach_tmp.pggdlwq6quarterly_dates
USING DELTA
AS
SELECT
to_date(cast(d_years as string) || '-' || cast(d_months as string) || '-' || cast(1 as string)) as generated_date
FROM
<DB_NAME>_ach_tmp.pggdlwq6generate_dates
 where d_months in (1,4,7,10)
;

CREATE TABLE <DB_NAME>_ach_tmp.pggdlwq6temp_period
USING DELTA
AS
SELECT
*
FROM
(
select CAST('Monthly' AS STRING) as period_name
 , 1 as period_order
 , CAST( 'mm' AS STRING) as period_type
 , md.generated_date as period_start_date
 , (md.generated_date + INTERVAL 1 month) as period_end_date
from <DB_NAME>_ach_tmp.pggdlwq6monthly_dates md
UNION ALL
select CAST('Weekly' AS STRING) as period_name
 , 2 as period_order
 , CAST('ww' AS STRING) as period_type
 , wd.generated_date as period_start_date
 , date_add(wd.generated_date, 7) as period_end_date
from <DB_NAME>_ach_tmp.pggdlwq6weekly_dates wd
where wd.generated_date >= to_date(cast(1900 as string) || '-' || cast(1 as string) || '-' || cast(1 as string)) and wd.generated_date < to_date(cast(2100 as string) || '-' || cast(1 as string) || '-' || cast(1 as string))
UNION ALL
select CAST('Quarterly' AS STRING) as period_name
 , 3 as period_order
 , CAST('qq' AS STRING) as period_type
 , qd.generated_date as period_start_date
 , (qd.generated_date + INTERVAL 3 month) as period_end_date
from <DB_NAME>_ach_tmp.pggdlwq6quarterly_dates qd
UNION ALL
select CAST('Yearly' AS STRING) as period_name
 , 4 as period_order
 , CAST('yy' AS STRING) as period_type
 , yd.generated_date as period_start_date
 , (yd.generated_date + INTERVAL 1 year) as period_end_date
from <DB_NAME>_ach_tmp.pggdlwq6yearly_dates yd
-- ADD UNION ALLs for additional period definitions
) monthlyDates;

TRUNCATE TABLE <DB_NAME>_ach_res.heracles_periods;

WITH insertion_temp AS (
(SELECT CAST(row_number() over (order by period_order, period_start_date) AS INT) as period_id
 , period_name, period_order, period_type, period_start_date, period_end_date
from <DB_NAME>_ach_tmp.pggdlwq6temp_period) UNION ALL (SELECT period_id, period_name, period_order, period_type, period_start_date, period_end_date FROM <DB_NAME>_ach_res.heracles_periods ))
INSERT OVERWRITE TABLE <DB_NAME>_ach_res.heracles_periods  (period_id, period_name, period_order, period_type, period_start_date, period_end_date) SELECT * FROM insertion_temp;

truncate table <DB_NAME>_ach_tmp.pggdlwq6digits;

drop table <DB_NAME>_ach_tmp.pggdlwq6digits;

truncate table <DB_NAME>_ach_tmp.pggdlwq6generate_dates;

drop table <DB_NAME>_ach_tmp.pggdlwq6generate_dates;

truncate table <DB_NAME>_ach_tmp.pggdlwq6yearly_dates;

drop table <DB_NAME>_ach_tmp.pggdlwq6yearly_dates;

truncate table <DB_NAME>_ach_tmp.pggdlwq6quarterly_dates;

drop table <DB_NAME>_ach_tmp.pggdlwq6quarterly_dates;

truncate table <DB_NAME>_ach_tmp.pggdlwq6monthly_dates;

drop table <DB_NAME>_ach_tmp.pggdlwq6monthly_dates;

truncate table <DB_NAME>_ach_tmp.pggdlwq6weekly_dates;

drop table <DB_NAME>_ach_tmp.pggdlwq6weekly_dates;

TRUNCATE TABLE <DB_NAME>_ach_tmp.pggdlwq6temp_period;

DROP TABLE <DB_NAME>_ach_tmp.pggdlwq6temp_period;


