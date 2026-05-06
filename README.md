# 🏥 Clínica VollMed API

API REST completa desenvolvida em Spring Boot para gerenciamento de uma clínica médica. O sistema permite o cadastro, listagem, atualização e exclusão de médicos e pacientes, além do agendamento de consultas com autenticação JWT.

## 📋 Sobre o Projeto

A API VollMed é um sistema de gerenciamento de clínica médica que oferece funcionalidades completas de CRUD para médicos, pacientes e consultas. Inclui autenticação JWT, validações complexas de agendamento, paginação e persistência com JPA/Hibernate. O projeto implementa as melhores práticas de desenvolvimento com Spring Boot.

## 🚀 Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.5.7**
- **Spring Data JPA** - Para persistência de dados
- **Spring Validation** - Para validação de dados
- **Spring Security** - Autenticação e autorização
- **JWT (Auth0)** - Tokens de autenticação
- **Flyway** - Controle de versão do banco de dados
- **MySQL** - Banco de dados relacional
- **Lombok** - Redução de código boilerplate
- **Maven** - Gerenciamento de dependências
- **SpringDoc OpenAPI** - Documentação da API (Swagger)
- **Hibernate Validator** - Validação avançada de dados

## 📦 Funcionalidades

### 🩺 Médicos
- ✅ Cadastro de médicos com validação de dados
- ✅ Listagem paginada de médicos ativos
- ✅ Atualização de dados de médicos
- ✅ Exclusão lógica de médicos (desativação)
- ✅ Especialidades: Ortopedia, Cardiologia, Ginecologia, Dermatologia
- ✅ Campo de status (ativo/inativo)
- ✅ Escolha aleatória de médico por especialidade

### 👥 Pacientes
- ✅ Cadastro de pacientes com validação de CPF
- ✅ Listagem paginada de pacientes
- ✅ Atualização de dados de pacientes
- ✅ Exclusão física de pacientes
- ✅ Detalhamento de pacientes

### 📅 Consultas
- ✅ Agendamento de consultas
- ✅ Validações complexas de agendamento:
  - Médico ativo no período
  - Paciente ativo
  - Sem outras consultas no mesmo horário
  - Horários dentro do funcionamento da clínica (8h-18h)
  - Agendamento com mínimo de 30 minutos de antecedência
  - Paciente sem outra consulta no mesmo dia
- ✅ Escolha automática de médico por especialidade
- ✅ Detalhamento de consultas agendadas

### 🔐 Autenticação e Segurança
- ✅ Login de usuários
- ✅ Geração de tokens JWT
- ✅ Proteção de endpoints com Bearer Token
- ✅ Tokens com expiração configurável
- ✅ Validação de segurança em todas as rotas protegidas

### Validações Implementadas
**Médicos:**
- Nome, email, telefone e CRM obrigatórios
- Validação de formato de email
- CRM com 4 a 6 dígitos
- Validação de endereço completo
- Campo de especialidade obrigatório

**Pacientes:**
- Nome, email, telefone e CPF obrigatórios
- Validação de formato de email
- Validação de CPF (formato brasileiro)
- Validação de endereço completo

**Consultas:**
- Data no futuro obrigatória
- Paciente ID obrigatório
- Validações de negócio (ativas, disponibilidade, horários)

## 🗄️ Estrutura do Banco de Dados

### Tabela: medicos
```sql
- id (bigint, primary key, auto_increment)
- nome (varchar(100))
- email (varchar(100), unique)
- crm (varchar(6), unique)
- telefone (varchar)
- especialidade (varchar(100))
- logradouro (varchar(100))
- bairro (varchar(100))
- cep (varchar(9))
- complemento (varchar(100))
- numero (varchar(20))
- uf (char(2))
- cidade (varchar(100))
- ativo (tinyint, default: 1)
```

### Tabela: pacientes
```sql
- id (bigint, primary key, auto_increment)
- nome (varchar(100))
- email (varchar(100), unique)
- telefone (varchar(15))
- cpf (varchar(11), unique)
- logradouro (varchar(100))
- bairro (varchar(100))
- cep (varchar(9))
- complemento (varchar(100))
- numero (varchar(20))
- uf (char(2))
- cidade (varchar(100))
```

### Tabela: usuarios
```sql
- id (bigint, primary key, auto_increment)
- login (varchar(100))
- senha (varchar(255))
```

### Tabela: consultas
```sql
- id (bigint, primary key, auto_increment)
- medico_id (bigint, foreign key)
- paciente_id (bigint, foreign key)
- data (datetime)
```

## 🔧 Configuração e Instalação

### Pré-requisitos
- Java 17 ou superior
- Maven 3.6+
- MySQL 8.0+

### Passos para Execução

1. **Clone o repositório**
```bash
git clone https://github.com/seu-usuario/Clinica-VollMed.git
cd Clinica-VollMed/api
```

2. **Configure o banco de dados**

Crie um banco de dados MySQL:
```sql
CREATE DATABASE vollmed_api;
```

3. **Configure as credenciais**

Copie o arquivo de exemplo e configure suas credenciais:
```bash
cp src/main/resources/application.properties.example src/main/resources/application.properties
```

Edite o arquivo `application.properties`:
```properties
spring.application.name=apiVoliMed
spring.datasource.url=jdbc:mysql://localhost:3306/vollmed_api
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha

spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# Segurança JWT
api.security.token.secret=sua_chave_secreta_super_segura_aqui_com_minimo_32_caracteres
```

4. **Execute o projeto**
```bash
./mvnw spring-boot:run
```

Ou no Windows:
```bash
mvnw.cmd spring-boot:run
```

A API estará disponível em: `http://localhost:8080`

Acesse a documentação Swagger em: `http://localhost:8080/swagger-ui.html`

## 🔐 Autenticação

A API utiliza **JWT (JSON Web Tokens)** para autenticação. Para acessar os endpoints protegidos:

1. **Fazer login** via `/login`
2. **Copiar o token** retornado
3. **Incluir o token** no header `Authorization: Bearer {token}` das requisições protegidas

## 📡 Endpoints da API

### 🔑 AUTENTICAÇÃO

#### Login (Sem autenticação)
```http
POST /login
Content-Type: application/json

{
  "login": "seu_login",
  "senha": "sua_senha"
}
```

**Resposta:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

### 🩺 MÉDICOS

#### Cadastrar Médico

<details>
<summary><strong>Ver exemplo de requisição</strong></summary>

```http
POST /medicos
Content-Type: application/json

{
  "nome": "Dr. João Silva",
  "email": "joao.silva@vollmed.com",
  "telefone": "11987654321",
  "crm": "123456",
  "especialidade": "CARDIOLOGIA",
  "endereco": {
    "logradouro": "Rua das Flores",
    "bairro": "Centro",
    "cep": "12345-678",
    "cidade": "São Paulo",
    "uf": "SP",
    "numero": "100",
    "complemento": "Sala 10"
  }
}
```

</details>

**Especialidades disponíveis:** ORTOPEDIA, CARDIOLOGIA, GINECOLOGIA, DERMATOLOGIA

#### Listar Médicos (Paginado)

<details>
<summary><strong>Ver exemplo de requisição</strong></summary>

```http
GET /medicos?pagina=0&tamanho=5&ordem=nome
```

**Parâmetros de paginação:**
- `pagina` - Número da página (padrão: 0)
- `tamanho` - Tamanho da página (padrão: 5)
- `ordem` - Campo para ordenação (padrão: nome)

**Resposta:**
```json
{
  "content": [
    {
      "id": 1,
      "nome": "Dr. João Silva",
      "email": "joao.silva@vollmed.com",
      "crm": "123456",
      "especialidade": "CARDIOLOGIA",
      "endereco": {
        "logradouro": "Rua das Flores",
        "bairro": "Centro",
        "cep": "12345-678",
        "cidade": "São Paulo",
        "uf": "SP"
      }
    }
  ],
  "totalElements": 1,
  "totalPages": 1,
  "currentPage": 0
}
```

</details>

#### Atualizar Médico

<details>
<summary><strong>Ver exemplo de requisição</strong></summary>

```http
PUT /medicos
Content-Type: application/json

{
  "id": 1,
  "nome": "Dr. João Silva Atualizado",
  "telefone": "11999998888",
  "endereco": {
    "logradouro": "Rua Nova",
    "bairro": "Centro",
    "numero": "200"
  }
}
```

</details>

#### Excluir Médico (Desativação)
```http
DELETE /medicos/{id}
```

⚠️ **Nota:** A exclusão de médicos é lógica - o registro fica marcado como inativo e não aparece mais nas listagens.

---

### 👥 PACIENTES

#### Cadastrar Paciente

<details>
<summary><strong>Ver exemplo de requisição</strong></summary>

```http
POST /pacientes
Content-Type: application/json

{
  "nome": "Maria Silva",
  "email": "maria.silva@email.com",
  "telefone": "11987654321",
  "cpf": "12345678901",
  "endereco": {
    "logradouro": "Rua das Flores",
    "bairro": "Centro",
    "cep": "12345-678",
    "cidade": "São Paulo",
    "uf": "SP",
    "numero": "100",
    "complemento": "Apto 101"
  }
}
```

</details>

#### Listar Pacientes (Paginado)

<details>
<summary><strong>Ver exemplo de requisição</strong></summary>

```http
GET /pacientes?pagina=0&tamanho=5&ordem=nome
```

**Parâmetros de paginação:**
- `pagina` - Número da página (padrão: 0)
- `tamanho` - Tamanho da página (padrão: 5)
- `ordem` - Campo para ordenação (padrão: nome)

**Resposta:**
```json
{
  "content": [
    {
      "id": 1,
      "nome": "Maria Silva",
      "email": "maria.silva@email.com",
      "cpf": "12345678901"
    }
  ],
  "totalElements": 1,
  "totalPages": 1,
  "currentPage": 0
}
```

</details>

#### Atualizar Paciente

<details>
<summary><strong>Ver exemplo de requisição</strong></summary>

```http
PUT /pacientes
Content-Type: application/json

{
  "id": 1,
  "nome": "Maria Silva Atualizado",
  "telefone": "11999998888",
  "endereco": {
    "logradouro": "Rua Nova",
    "numero": "200"
  }
}
```

</details>

#### Deletar Paciente
```http
DELETE /pacientes/{id}
```

⚠️ **Nota:** A exclusão de pacientes é física - o registro é removido do banco de dados.

---

### 📅 CONSULTAS (Requer autenticação)

#### Agendar Consulta

<details>
<summary><strong>Ver exemplo de requisição</strong></summary>

```http
POST /consultas
Content-Type: application/json
Authorization: Bearer {token}

{
  "idMedico": null,
  "idPaciente": 1,
  "especialidade": "CARDIOLOGIA",
  "data": "2026-05-20T14:00:00"
}
```

**Ou com médico específico:**

```http
POST /consultas
Content-Type: application/json
Authorization: Bearer {token}

{
  "idMedico": 1,
  "idPaciente": 1,
  "data": "2026-05-20T14:00:00"
}
```

**Resposta (sucesso):**
```json
{
  "id": 1,
  "medico": {
    "id": 1,
    "nome": "Dr. João Silva",
    "email": "joao@example.com",
    "crm": "123456",
    "especialidade": "CARDIOLOGIA"
  },
  "paciente": {
    "id": 1,
    "nome": "Maria Silva",
    "email": "maria@example.com",
    "cpf": "12345678901"
  },
  "data": "2026-05-20T14:00:00"
}
```

**Possíveis erros:**
- Médico ou paciente não existe
- Paciente inativo
- Médico inativo
- Data no passado
- Consulta fora do horário de funcionamento (8h-18h)
- Médico com outra consulta no mesmo horário
- Paciente com outra consulta no mesmo dia
- Agendamento com menos de 30 minutos de antecedência

</details>

## 📁 Estrutura do Projeto

```
api/
├── src/
│   ├── main/
│   │   ├── java/med/voll/api/
│   │   │   ├── ApiApplication.java
│   │   │   ├── Controller/
│   │   │   │   ├── MedicoController.java
│   │   │   │   ├── PacienteController.java
│   │   │   │   ├── ConsultaController.java
│   │   │   │   └── AutenticacaoController.java
│   │   │   ├── domain/
│   │   │   │   ├── Medico/
│   │   │   │   │   ├── Medico.java
│   │   │   │   │   ├── MedicoRepository.java
│   │   │   │   │   ├── Especialidade.java
│   │   │   │   │   ├── DadosCadastroMedico.java
│   │   │   │   │   ├── DadosListarMedico.java
│   │   │   │   │   ├── DadosDetalhamentoMedico.java
│   │   │   │   │   ├── DadosAtualizaMedico.java
│   │   │   │   │   └── DadosEndereco.java
│   │   │   │   ├── Paciente/
│   │   │   │   │   ├── Paciente.java
│   │   │   │   │   ├── PacienteRepository.java
│   │   │   │   │   ├── DadosCadastroPaciente.java
│   │   │   │   │   ├── DadosListarPaciente.java
│   │   │   │   │   ├── DadosDetalhamentoPaciente.java
│   │   │   │   │   └── DadosAtualizaPaciente.java
│   │   │   │   ├── Consulta/
│   │   │   │   │   ├── Consulta.java
│   │   │   │   │   ├── ConsultaRepository.java
│   │   │   │   │   ├── AgendaDeConsultas.java
│   │   │   │   │   ├── DadosAgendamentosConsulta.java
│   │   │   │   │   ├── DadosDetalhamentoConsulta.java
│   │   │   │   │   └── Validacoes/
│   │   │   │   │       ├── ValidadorAgendamentoDeConsulta.java
│   │   │   │   │       ├── ValidadorMedicoAtivo.java
│   │   │   │   │       ├── ValidadorPacienteAtivo.java
│   │   │   │   │       ├── ValidadorMedicoComOutraConsultaNoMesmoHorario.java
│   │   │   │   │       ├── ValidadorSemOutraConsultaNoDia.java
│   │   │   │   │       ├── ValidadorHorarioFuncionamentoClinica.java
│   │   │   │   │       └── ValidadorHorarioAntecedencia.java
│   │   │   │   ├── Usuario/
│   │   │   │   │   ├── Usuario.java
│   │   │   │   │   ├── UsuarioRepository.java
│   │   │   │   │   ├── DadosAutenticacao.java
│   │   │   │   │   └── AutenticacaoService.java
│   │   │   │   ├── Endereco/
│   │   │   │   │   ├── Endereco.java
│   │   │   │   │   └── DadosEndereco.java
│   │   │   │   └── ValidacaoException.java
│   │   │   └── infra/
│   │   │       ├── security/
│   │   │       │   ├── SecurityConfigurations.java
│   │   │       │   ├── SecurityFilter.java
│   │   │       │   ├── TokenService.java
│   │   │       │   └── DadosTokenJWT.java
│   │   │       ├── exception/
│   │   │       │   └── TratadorDeErros.java
│   │   │       └── springdoc/
│   │   │           └── SpringDocConfiguration.java
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── application.properties.example
│   │       └── db/migration/
│   │           ├── V1__create-table-medico.sql
│   │           ├── V2__alter-table-medicos-add-column-telefone.sql
│   │           ├── V3__create-table-pacientes.sql
│   │           ├── V4__alter-table-medicos-add-ativo.sql
│   │           ├── V5__create-table-usuario.sql
│   │           └── V6__create-table-consultas.sql
│   └── test/
│       └── java/med/voll/api/
│           ├── Controller/
│           │   └── ConsultaControllerTest.java
│           └── domain/
│               └── Medico/
│                   └── MedicoRepositoryTest.java
└── pom.xml
```

## 🔍 Migrations do Flyway

O projeto utiliza Flyway para controle de versão do banco de dados. As migrations são executadas automaticamente na inicialização da aplicação.

- **V1**: Criação da tabela de médicos
- **V2**: Adição da coluna telefone em médicos
- **V3**: Criação da tabela de pacientes
- **V4**: Adição do campo `ativo` em médicos (para exclusão lógica)
- **V5**: Criação da tabela de usuários (para autenticação)
- **V6**: Criação da tabela de consultas com relacionamentos

## 🛠️ Desenvolvimento

### Padrões Utilizados
- **Records** para DTOs (Data Transfer Objects)
- **Repository Pattern** com Spring Data JPA
- **Bean Validation** para validação de entrada
- **Lombok** para redução de código boilerplate
- **Desativação Lógica** para médicos (soft delete)
- **Exclusão Física** para pacientes
- **Strategy Pattern** para validações de agendamento
- **Spring Security** com JWT para autenticação
- **ServiceLayer** com `AgendaDeConsultas`

### Boas Práticas Implementadas
- Separação de responsabilidades (Controllers, Services, Repositories, Validators)
- DTOs para comunicação com a API
- Validação em camadas com annotations
- Transações gerenciadas pelo Spring
- Paginação para melhor performance
- Tratamento de erros centralizado
- Segurança em endpoints protegidos
- Documentação automática com Swagger
- Testes unitários para repositórios e controllers

## 🔐 Diferenças de Exclusão

### Médicos - Exclusão Lógica (Soft Delete)
- Quando um médico é "deletado", apenas o campo `ativo` é alterado para `false`
- O registro permanece no banco de dados
- Não aparece nas listagens
- Dados históricos são preservados

### Pacientes - Exclusão Física (Hard Delete)
- Quando um paciente é deletado, o registro é completamente removido do banco de dados
- A operação é irreversível
- Use com cautela em ambiente de produção

## 🔐 Segurança e Autenticação

### JWT (JSON Web Tokens)
- Tokens com validade de 2 horas
- Geração via biblioteca Auth0
- Verificação em endpoints protegidos
- Payload contém ID do usuário e login

### Spring Security
- Filtros de segurança customizados
- Proteção CSRF desabilitada (API stateless)
- Apenas endpoints de autenticação sem proteção
- Todos os endpoints de negócio protegidos com `@SecurityRequirement`

## 📚 Exemplos de Uso com cURL

### 1. Fazer Login
```bash
curl -X POST http://localhost:8080/login \
  -H "Content-Type: application/json" \
  -d '{
    "login": "seu_login",
    "senha": "sua_senha"
  }'
```

**Resposta:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

### 2. Cadastrar Médico (Sem autenticação obrigatória neste exemplo)
```bash
curl -X POST http://localhost:8080/medicos \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "Dr. João Silva",
    "email": "joao@example.com",
    "telefone": "11987654321",
    "crm": "123456",
    "especialidade": "CARDIOLOGIA",
    "endereco": {
      "logradouro": "Rua das Flores",
      "numero": "100",
      "bairro": "Centro",
      "cep": "12345-678",
      "cidade": "São Paulo",
      "uf": "SP"
    }
  }'
```

### 3. Agendar Consulta (Com autenticação)
```bash
curl -X POST http://localhost:8080/consultas \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer SEU_TOKEN_JWT" \
  -d '{
    "idMedico": 1,
    "idPaciente": 1,
    "data": "2026-05-20T14:00:00"
  }'
```

Ou deixando a API escolher o médico:
```bash
curl -X POST http://localhost:8080/consultas \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer SEU_TOKEN_JWT" \
  -d '{
    "idPaciente": 1,
    "especialidade": "CARDIOLOGIA",
    "data": "2026-05-20T14:00:00"
  }'
```

### 4. Listar Médicos
```bash
curl "http://localhost:8080/medicos?pagina=0&tamanho=10"
```

### 5. Listar Pacientes
```bash
curl "http://localhost:8080/pacientes?pagina=0&tamanho=10"
```

## 📖 Documentação Interativa

Acesse a documentação Swagger em seu navegador:
```
http://localhost:8080/swagger-ui.html
```

A documentação fornece:
- Lista completa de endpoints
- Modelos de requisição e resposta
- Testes diretos dos endpoints
- Parâmetros obrigatórios e opcionais

## 🚀 Deploy

### Buildando a Aplicação
```bash
./mvnw clean package -DskipTests
```

### JAR Executável
```bash
java -jar target/api-0.0.1-SNAPSHOT.jar
```

## 🐛 Tratamento de Erros

A API possui tratamento centralizado de erros com as seguintes respostas:

### Validação
```json
{
  "campo": "email",
  "mensagem": "deve ser um endereço de e-mail bem formado"
}
```

### Erro de Negócio
```json
{
  "mensagem": "Médico não disponível nesta data"
}
```

### Erro de Autenticação
```json
{
  "mensagem": "Token JWT inválido ou expirado!"
}
```

## 🧪 Testes

O projeto inclui testes para:
- Repositórios de dados
- Controllers de consultas
- Validações de agendamento

Execute os testes com:
```bash
./mvnw test
```
- Tratamento de dados sensíveis

##  Licença

Este projeto está sob a licença especificada no arquivo [LICENSE](LICENSE).

## 👨‍💻 Autor

Desenvolvido como projeto de estudo em Spring Boot.

---

⭐ Se este projeto foi útil para você, considere dar uma estrela no repositório!
