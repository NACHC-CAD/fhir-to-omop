use synthea_omop;

create index idx_concept_voc_cod_std on concept (
	vocabulary_id,
	concept_code,
	standard_concept
);

