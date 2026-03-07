package med.voll.api.domain.Consulta.Validacoes;

import med.voll.api.domain.Consulta.DadosAgendamentosConsulta;
import med.voll.api.domain.Medico.MedicoRepository;
import med.voll.api.domain.ValidacaoException;

public class ValidadorMedicoAtivo {

    private MedicoRepository medicoRepository;

    public void validar(DadosAgendamentosConsulta dados) {

        if(dados.idMedico() == null){
            return;
        }

        var medicoEstaAtivo = medicoRepository.findAtivoById(dados.idMedico());
        if(!medicoEstaAtivo){
            throw new ValidacaoException("Consulta não pode ser agendada com um médico inativo");
        }
    }
}
