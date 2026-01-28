package med.voll.api.Paciente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import med.voll.api.Endereco.Endereco;
import org.hibernate.validator.constraints.br.CPF;

public record DadosCadastroPaciente(

        @NotBlank
        String nome,

        @NotBlank
        String email,

        @NotBlank
        String telefone,

        @NotBlank
        @CPF
        String cpf,

        @NotNull @Valid
        Endereco endereco
) {
}
