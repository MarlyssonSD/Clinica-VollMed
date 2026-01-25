package med.voll.api.Medico;

import jakarta.validation.constraints.NotNull;
import med.voll.api.Endereco.DadosEndereco;
import med.voll.api.Endereco.Endereco;

public record DadosAtualizaMedico (
        @NotNull Long id, String nome, String telefone, DadosEndereco endereco){
}
