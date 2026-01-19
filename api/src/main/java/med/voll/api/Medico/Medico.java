package med.voll.api.Medico;

import jakarta.persistence.*;
import lombok.*;
import med.voll.api.Endereco.Endereco;

@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String crm;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded
    private Endereco endereco;

    public Medico(DadosCadastroMedico dadosMed) {
        this.nome = dadosMed.nome();
        this.email = dadosMed.email();
        this.crm = dadosMed.crm();
        this.especialidade = dadosMed.especialidade();
        this.endereco = new Endereco(dadosMed.endereco());
    }
}
