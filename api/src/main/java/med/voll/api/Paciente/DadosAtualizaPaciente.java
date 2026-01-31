package med.voll.api.Paciente;

import jakarta.validation.constraints.NotNull;
import med.voll.api.Endereco.DadosEndereco;

public record DadosAtualizaPaciente(
        @NotNull Long id, String nome, String telefone, DadosEndereco endereco) {
}
