package med.voll.api.domain.Paciente;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.Endereco.Endereco;

@Table(name = "pacientes")
@Entity(name = "Paciente")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String nome;
    String email;
    String telefone;
    String cpf;

    @Embedded
    Endereco endereco;

    public Paciente(DadosCadastroPaciente paciente) {
        this.nome = paciente.nome();
        this.email = paciente.email();
        this.telefone = paciente.telefone();
        this.cpf = paciente.cpf();
        this.endereco = paciente.endereco();
    }

    public void atualizarDados(DadosAtualizaPaciente dadosPaciente){
        if (nome != null){
            this.nome = dadosPaciente.nome();
        }
        if (telefone != null){
            this.telefone = dadosPaciente.telefone();
        }

        if (dadosPaciente.endereco() != null){
            this.endereco.atualizaDados(dadosPaciente.endereco());
        }
    }
}
