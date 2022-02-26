insert into  vaccine(                                    id,                         name, doses, batch_number)
              values('ea857b89-a36b-4cc4-ba1e-10c355aae756', 'AstraZeneca Oxford Vaccine',     2,  '202202121'),
                    ('b4a3954e-66db-421a-a4e1-881686c83351', 'AstraZeneca Oxford Vaccine',     2,  '985132154'),
                    ('5fc96480-3295-4931-99a7-3b895c78ccee',    'Pfizer BioNTech Vaccine',     2,       '2154');

insert into applicator(                                    id,              nome,         cpf,     coren)
               values ('16da31e6-7bd2-421d-8348-87852dad5664', 'Chico Pessevejo', 12345678999, 'CRN-952'),
                      ('c65c13fd-e500-4e6b-9e5b-8eae49813716', 'João das Pedras', 98765432133, 'CRN-231'),
                      ('36eb6f7e-14b9-4460-8b1e-91855190e65c', 'Fulano Beltrano', 98765465498, 'CRN-987');

--insert into application (                                    id,       person_name,  person_cpf, person_birth_date,   applicator_name, cpf_aplicador,     coren,                 vaccine_name, batch_number, application_date)
--                 values ('29f400dc-ad6d-42c5-9da3-409a651d1009',      'Pedro José', 32165498733,      '2020-01-01', 'Chico Pessevejo',   12345678999, 'CRN-952', 'AstraZeneca Oxford Vaccine',  '202202121',     '2022-01-03'),
--                        ('04cdc0b1-b473-4929-9eb1-7a11c08173e1',      'João Paulo', 65498723144,      '1999-02-05', 'João das Pedras',   98765432133, 'CRN-231', 'AstraZeneca Oxford Vaccine',  '985132154',     '2002-02-03'),
--                        ('ce570e30-5132-434d-be79-adf828398548',   'Maria Antônia', 65498732199,      '1998-03-10', 'Fulano Beltrano',   98765465498, 'CRN-987',    'Pfizer BioNTech Vaccine',       '2154',     '2022-02-03'),
--                        ('cf3c25dd-fb1f-4880-8724-9dc3f4cec1c5', 'Fernanda Amanda', 98732198766,      '1997-04-23', 'Chico Pessevejo',   12345678999, 'CRN-952',    'Pfizer BioNTech Vaccine',       '2154',     '2022-02-09');

