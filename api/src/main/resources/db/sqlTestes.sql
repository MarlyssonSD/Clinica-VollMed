select * from medicos;
select * from pacientes;

-- visualiza migrations realizadas
select * from flyway_schema_history;

-- Excluir uma versão específica
DELETE FROM flyway_schema_history WHERE version = 'numero da versão';

-- Excluirá todas as migrations falhadas
delete from flyway_schema_history where success = 0;

