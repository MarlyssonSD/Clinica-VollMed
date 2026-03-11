package med.voll.api.domain.Consulta.Validacoes;

import med.voll.api.domain.Consulta.ConsultaRepository;
import med.voll.api.domain.Consulta.DadosAgendamentosConsulta;
import med.voll.api.domain.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoComOutraConsultaNoMesmoHorario implements ValidadorAgendamentoDeConsulta {

    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar(DadosAgendamentosConsulta dados){
        var medicoPossuiOutraConsultaNoMesmoHorario = consultaRepository.existsByMedicoIdAndData(dados.idMedico(), dados.data());

        if (medicoPossuiOutraConsultaNoMesmoHorario){
            throw new ValidacaoException("Médico ja possui outra consulta agendada nesse horário");
        }
    }
}
