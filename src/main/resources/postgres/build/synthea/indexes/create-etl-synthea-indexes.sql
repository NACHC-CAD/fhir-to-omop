SET search_path = @cdm_schema, "$user", public; 

-- 
-- indexes used for query for condition_occurrence
--

drop index if exists source_to_standard_vocab_map_sctditvisvitsctir;
create index source_to_standard_vocab_map_sctditvisvitsctir on source_to_standard_vocab_map(
	source_code,
	target_domain_id,
 	target_vocabulary_id,
	source_vocabulary_id,
	target_standard_concept,
	target_invalid_reason
);

drop index if exists source_to_standard_vocab_map_scsvsd;
create index source_to_standard_vocab_map_scsvsd on source_to_standard_vocab_map (
	source_code,
	source_vocabulary_id,
	source_domain_id
);

drop index if exists source_to_source_vocab_map_scsvisdi;
create index source_to_source_vocab_map_scsvisdi on source_to_source_vocab_map (
	source_code,
	source_vocabulary_id,
	source_domain_id
);

drop index if exists provider_psv;
create index provider_psv on provider (
	provider_source_value
);

drop index if exists person_psv;
create index person_psv on person (
	person_source_value
);


-- 
-- indexes used for query for cost
--

SET search_path = etl_synthea_1k_synthea_native, "$user", public; 

-- procedure

drop index if exists procedures_ep1;
create index procedures_ep1 on procedures (
	encounter,
	patient
);

drop index if exists procedures_pe1;
create index procedures_pe1 on procedures (
	patient,
	encounter
);

drop index if exists procedures_code1;
create index procedures_code1 on procedures (
	code
);

-- immunizations

drop index if exists immunizations_ep1;
create index immunizations_ep1 on immunizations (
	encounter,
	patient
);

drop index if exists immunizations_pe1;
create index immunizations_pe1 on immunizations (
	patient,
	encounter
);

drop index if exists immunizations_code1;
create index immunizations_code1 on immunizations (
	code
);

-- medications
drop index if exists medications_ep1;
create index medications_ep1 on medications (
	encounter,
	patient
);

drop index if exists medications_pe1;
create index medications_pe1 on medications (
	patient,
	encounter
);

drop index if exists medications_code1;
create index medications_code1 on medications (
	code
);






