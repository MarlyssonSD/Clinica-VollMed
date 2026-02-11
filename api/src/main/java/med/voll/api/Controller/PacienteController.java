package med.voll.api.Controller;

import jakarta.validation.Valid;
import med.voll.api.Paciente.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    PacienteRepository pacienteRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<dadosDetalhamentoPaciente> cadastrar(@RequestBody  @Valid DadosCadastroPaciente dadosPaciente, UriComponentsBuilder uriBuilder) {
        var paciente = new Paciente(dadosPaciente);
        pacienteRepository.save(paciente);
        var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();

        return ResponseEntity.created(uri).body(new dadosDetalhamentoPaciente(paciente));
    }

    @GetMapping
    ResponseEntity<Page<DadosListarPaciente>> listar(@PageableDefault(size = 5, sort = "nome") Pageable paginacao) {
        var page = pacienteRepository.findAll(paginacao).map(DadosListarPaciente::new);

        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    ResponseEntity<dadosDetalhamentoPaciente> detalhar(@PathVariable Long id) {
        var paciente = pacienteRepository.getReferenceById(id);

        return ResponseEntity.ok(new dadosDetalhamentoPaciente(paciente));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<dadosDetalhamentoPaciente> atualizar(@RequestBody DadosAtualizaPaciente dadosPaciente){
        var paciente = pacienteRepository.getReferenceById(dadosPaciente.id());
        paciente.atualizarDados(dadosPaciente);

        return ResponseEntity.ok(new dadosDetalhamentoPaciente(paciente));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> excluir(@PathVariable Long id){
        pacienteRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
