package med.voll.api.domain.Paciente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.Endereco.Endereco;

public record DadosCadastroPaciente(

        @NotBlank(message = "Nome é obrigatório")
        String nome,


        @NotBlank(message = "Email é obrigatório")
        @Email(message = "Formato do email é inválido")
        String email,

        @NotBlank(message = "Telefone é obrigatório")
        String telefone,

        @NotBlank(message = "CPF é obrigatório")
//        @CPF
        String cpf,

        @NotNull(message = "Dados do endereço são obrigatórios")
        @Valid
        Endereco endereco
) {
}
