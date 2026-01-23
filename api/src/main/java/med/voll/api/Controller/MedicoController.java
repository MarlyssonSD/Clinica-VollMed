package med.voll.api.Controller;

import jakarta.validation.Valid;
import med.voll.api.Medico.DadosCadastroMedico;
import med.voll.api.Medico.DadosListarMedico;
import med.voll.api.Medico.Medico;
import med.voll.api.Medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping
    @Transactional
    public void cadastrarMedico(@RequestBody @Valid DadosCadastroMedico dadosMed){
        medicoRepository.save(new Medico(dadosMed));
    }

    @GetMapping
    public List<DadosListarMedico> listarMedico(DadosListarMedico dadosListarMedico){
        return medicoRepository.findAll().stream().map(DadosListarMedico::new).toList();
    }

}
