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




