select * from medicos;
select * from pacientes;
select * from usuarios;
.

-- visualiza migrations realizadas
select * from flyway_schema_history;

-- Excluir uma versão específica
DELETE FROM flyway_schema_history WHERE version = 'numero da versão';

-- Excluirá todas as migrations falhadas
delete from flyway_schema_history where success = 0;

-- Ativa todos os médicos
UPDATE medicos med
SET med.ativo = 1
WHERE med.ativo = 0;

UPDATE medicos med
SET med.ativo = 1
WHERE med.nome = 'marlay'


insert into usuarios values(1,'marly1','$2a$12$kt0B5/cEWPiz6iPzOjfpru8xoYifEsEH0ccL1l52.KhBsqnFhARKu');

SELECT m from medicos m
            where m.ativo = true
                and m.especialidade = :especialidade
                and m.id not in (SELECT c.medico.id FROM consulta c
									where c.data = :data )
                order by rand()
                limit 1