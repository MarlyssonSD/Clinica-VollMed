package med.voll.api.Paciente;

import med.voll.api.Endereco.Endereco;

public record dadosDetalhamentoPaciente(Long id, String nome, String telefone, String email,
                                        Endereco endereco, String cpf) {
    public dadosDetalhamentoPaciente(Paciente paciente){
        this(paciente.getId(), paciente.getNome(), paciente.getTelefone(),paciente.getEmail(),
                paciente.getEndereco(),paciente.getCpf());
    }
}
