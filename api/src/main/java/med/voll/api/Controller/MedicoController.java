package med.voll.api.Controller;

import jakarta.validation.Valid;
import med.voll.api.Medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroMedico dadosMed){
        medicoRepository.save(new Medico(dadosMed));
    }

    @GetMapping
    public Page<DadosListarMedico> listar(@PageableDefault(size = 5, sort = "nome") Pageable paginacao){
        return medicoRepository.findAllByAtivoTrue(paginacao).map(DadosListarMedico::new);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody DadosAtualizaMedico dadosMed){
        var medico = medicoRepository.getReferenceById(dadosMed.id());
        medico.atualizarDados(dadosMed);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void desativar(@PathVariable Long id){
        var medico = medicoRepository.getReferenceById(id);
        medico.desativaMedico();
    }
}
