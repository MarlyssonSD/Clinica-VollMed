package med.voll.api.domain.Paciente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.Endereco.Endereco;

public record DadosCadastroPaciente(

        @NotBlank
        String nome,

        @NotBlank
        String email,

        @NotBlank
        String telefone,

        @NotBlank
//        @CPF
        String cpf,

        @NotNull @Valid
        Endereco endereco
) {
}
