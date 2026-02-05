package med.voll.api.Controller;

import jakarta.validation.Valid;
import med.voll.api.Medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<dadosDetalhamentoMedico> cadastrar(@RequestBody @Valid DadosCadastroMedico dadosMed, UriComponentsBuilder uriBuilder ){
        var medico = new Medico(dadosMed);
        medicoRepository.save(medico);
        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();

        return ResponseEntity.created(uri).body(new dadosDetalhamentoMedico(medico));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListarMedico>> listar(@PageableDefault(size = 5, sort = "nome") Pageable paginacao){
        var page = medicoRepository.findAllByAtivoTrue(paginacao).map(DadosListarMedico::new);

        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<dadosDetalhamentoMedico> atualizar(@RequestBody DadosAtualizaMedico dadosMed){
        var medico = medicoRepository.getReferenceById(dadosMed.id());
        medico.atualizarDados(dadosMed);

        return ResponseEntity.ok(new dadosDetalhamentoMedico(medico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> desativar(@PathVariable Long id){
        var medico = medicoRepository.getReferenceById(id);
        medico.desativaMedico();

        return ResponseEntity.noContent().build();
    }
}
