package med.voll.api.domain.Consulta.Validacoes;

import med.voll.api.domain.Consulta.ConsultaRepository;
import med.voll.api.domain.Consulta.DadosAgendamentosConsulta;
import med.voll.api.domain.ValidacaoException;

public class ValidadorSemOutraConsultaNoDia {

    private ConsultaRepository consultaRepository;

    public void validar(DadosAgendamentosConsulta dados){

        var primeiroHorario = dados.data().withHour(7);
        var ultimoHorario = dados.data().withHour(18);
        var pacientePossuiOutraConsultaNoDia =  consultaRepository.existsByPacienteIdAndDataBetween(dados.idPaciente(),primeiroHorario,ultimoHorario);
        if (pacientePossuiOutraConsultaNoDia){
            throw new ValidacaoException("Paciente já possui uma consulta agendada nesse dia");
        }
    }
}
