package med.voll.api.Controller;

import med.voll.api.domain.Consulta.AgendaDeConsultas;
import med.voll.api.domain.Consulta.DadosAgendamentosConsulta;
import med.voll.api.domain.Consulta.DadosDetalhamentoConsulta;
import med.voll.api.domain.Medico.Especialidade;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class ConsultaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AgendaDeConsultas agendaDeConsultas;

    @Autowired
    private JacksonTester<DadosAgendamentosConsulta> jsonEntrada;

    @Autowired
    private JacksonTester<DadosDetalhamentoConsulta> jsonSaida;

    @Test
    @DisplayName("Deve retornar código 400 quando com informações inválidas")
    @WithMockUser
    void agendarCenario1() throws Exception {
        var responde = mockMvc.perform(post("/consultas").with(csrf())).andReturn().getResponse();
        assertThat(responde.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deve retornar código 200 quando com informações válidas")
    @WithMockUser
    void agendarCenario2() throws Exception {
        var data = LocalDateTime.now().plusHours(1);
        var especialidade = Especialidade.CARDIOLOGIA;
        var dadosDetalhamento =  new DadosDetalhamentoConsulta(null, 2l, 5l, data );

        when(agendaDeConsultas.agendar(any())).thenReturn(dadosDetalhamento);

        var response = mockMvc.perform(post("/consultas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonEntrada
                                .write(new DadosAgendamentosConsulta(2l, 5l, data, especialidade))
                                .getJson())
                )
                .andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var jsonEsperado = jsonSaida.write(
                new DadosDetalhamentoConsulta(null, 2l,5l, data)).getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }
}