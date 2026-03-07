package med.voll.api.domain.Consulta.Validacoes;

import med.voll.api.domain.Consulta.DadosAgendamentosConsulta;
import med.voll.api.domain.Paciente.PacienteRepository;
import med.voll.api.domain.ValidacaoException;

public class ValidadorPacienteAtivo {

    private PacienteRepository pacienteRepository;

    public void validar(DadosAgendamentosConsulta dados){
        var pacienteAtivo = pacienteRepository.findAtivoById(dados.idPaciente());
        if(!pacienteAtivo){
            throw new ValidacaoException("Consulta não pode ser agendada com paciente excluído!");
        }
    }
}
