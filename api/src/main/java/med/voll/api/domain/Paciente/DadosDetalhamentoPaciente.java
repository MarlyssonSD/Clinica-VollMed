package med.voll.api.domain.Paciente;

import med.voll.api.domain.Endereco.Endereco;

public record DadosDetalhamentoPaciente(Long id, String nome, String telefone, String email,
                                        Endereco endereco, String cpf) {
    public DadosDetalhamentoPaciente(Paciente paciente){
        this(paciente.getId(), paciente.getNome(), paciente.getTelefone(),paciente.getEmail(),
                paciente.getEndereco(),paciente.getCpf());
    }
}
