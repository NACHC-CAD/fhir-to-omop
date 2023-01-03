create index idx_concept_voc_cod_std on concept (
	vocabulary_id,
	concept_code,
	standard_concept
);

select * from concept where vocabulary_id = 'UCUM' and concept_code = 'mmHg' and standard_concept = 'S';
