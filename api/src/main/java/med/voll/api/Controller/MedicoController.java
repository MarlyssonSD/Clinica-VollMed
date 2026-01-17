package med.voll.api.Controller;

import med.voll.api.Medico.DadosCadastroMedico;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @PostMapping
    public void cadastrarMedico(@RequestBody DadosCadastroMedico jsonMedico){
    System.out.println(jsonMedico);
    }

}
