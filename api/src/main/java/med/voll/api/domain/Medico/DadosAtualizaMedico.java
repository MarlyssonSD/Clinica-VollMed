package med.voll.api.domain.Medico;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.Endereco.DadosEndereco;

public record DadosAtualizaMedico (
        @NotNull Long id, String nome, String telefone, DadosEndereco endereco){
}
