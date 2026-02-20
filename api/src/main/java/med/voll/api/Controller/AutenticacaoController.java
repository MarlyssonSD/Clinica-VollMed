package med.voll.api.Controller;

import jakarta.validation.Valid;
import med.voll.api.domain.Usuario.DadosAutenticacao;
import med.voll.api.domain.Usuario.Usuario;
import med.voll.api.domain.Usuario.UsuarioRepository;
import med.voll.api.infra.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<String> efetuarLogin(@RequestBody @Valid DadosAutenticacao dadosAutenticacao) {

        var token = new UsernamePasswordAuthenticationToken(dadosAutenticacao.login(), dadosAutenticacao.senha());
        var authentication = manager.authenticate(token);

        return ResponseEntity.ok(tokenService.gerarToken((Usuario) authentication.getPrincipal()));
    }
}
