package med.voll.api.Controller;

import jakarta.validation.Valid;
import med.voll.api.Paciente.DadosCadastroPaciente;
import med.voll.api.Paciente.DadosListarPaciente;
import med.voll.api.Paciente.Paciente;
import med.voll.api.Paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    PacienteRepository pacienteRepository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody  @Valid DadosCadastroPaciente paciente) {
        pacienteRepository.save(new Paciente(paciente));
    }

    @GetMapping
    Page<DadosListarPaciente> listarPaciente(@PageableDefault(size = 5, sort = "nome") Pageable paginacao) {
        return pacienteRepository.findAll(paginacao).map(DadosListarPaciente::new);

    }
}
