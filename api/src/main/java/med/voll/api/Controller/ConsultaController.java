package med.voll.api.Controller;

import jakarta.validation.Valid;
import med.voll.api.domain.Consulta.DadosAgendamentos;
import med.voll.api.domain.Consulta.DadosDetalhamentoConsulta;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("consultas")
public class ConsultaController {

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoConsulta> agendar(@RequestBody @Valid DadosAgendamentos dados){
        System.out.println("Agendamento do Consulta: " + dados);

        return ResponseEntity.ok().body(new DadosDetalhamentoConsulta(null,null,null,null));

    }
}
