# 🏥 Clínica VollMed API

API REST desenvolvida em Spring Boot para gerenciamento de uma clínica médica. O sistema permite o cadastro, listagem, atualização e exclusão de médicos e pacientes, incluindo suas especialidades e endereços.

## 📋 Sobre o Projeto

A API VollMed é um sistema completo de gerenciamento de clínica médica que oferece funcionalidades de CRUD (Create, Read, Update, Delete) para médicos e pacientes. O projeto implementa as melhores práticas de desenvolvimento com Spring Boot, incluindo validação de dados, paginação, desativação lógica de registros, e persistência com JPA/Hibernate.

## 🚀 Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.5.7**
- **Spring Data JPA** - Para persistência de dados
- **Spring Validation** - Para validação de dados
- **Flyway** - Para controle de versão do banco de dados
- **MySQL** - Banco de dados relacional
- **Lombok** - Para redução de código boilerplate
- **Maven** - Gerenciamento de dependências
- **Hibernate Validator** - Para validação de CPF e email

## 📦 Funcionalidades

### Médicos
- ✅ Cadastro de médicos com validação de dados
- ✅ Listagem paginada de médicos ativos
- ✅ Atualização de dados de médicos
- ✅ Exclusão lógica de médicos (desativação)
- ✅ Especialidades: Ortopedia, Cardiologia, Ginecologia, Dermatologia
- ✅ Campo de status (ativo/inativo)

### Pacientes
- ✅ Cadastro de pacientes com validação de CPF
- ✅ Listagem paginada de pacientes
- ✅ Atualização de dados de pacientes
- ✅ Exclusão física de pacientes

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

## 📡 Endpoints da API

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

## 📁 Estrutura do Projeto

```
api/
├── src/
│   ├── main/
│   │   ├── java/med/voll/api/
│   │   │   ├── ApiApplication.java
│   │   │   ├── Controller/
│   │   │   │   ├── MedicoController.java
│   │   │   │   └── PacienteController.java
│   │   │   ├── Medico/
│   │   │   │   ├── Medico.java
│   │   │   │   ├── MedicoRepository.java
│   │   │   │   ├── DadosCadastroMedico.java
│   │   │   │   ├── DadosListarMedico.java
│   │   │   │   ├── DadosAtualizaMedico.java
│   │   │   │   ├── Especialidade.java
│   │   │   │   └── DadosEndereco.java
│   │   │   ├── Paciente/
│   │   │   │   ├── Paciente.java
│   │   │   │   ├── PacienteRepository.java
│   │   │   │   ├── DadosCadastroPaciente.java
│   │   │   │   ├── DadosListarPaciente.java
│   │   │   │   └── DadosAtualizaPaciente.java
│   │   │   └── Endereco/
│   │   │       ├── Endereco.java
│   │   │       └── DadosEndereco.java
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── application.properties.example
│   │       └── db/migration/
│   │           ├── V1__create-table-medico.sql
│   │           ├── V2__alter-table-medicos-add-column-telefone.sql
│   │           ├── V3__create-table-pacientes.sql
│   │           └── V4__alter-table-medicos-add-ativo.sql
│   └── test/
│       └── java/med/voll/api/
│           └── ApiApplicationTests.java
└── pom.xml
```

## 🔍 Migrations do Flyway

O projeto utiliza Flyway para controle de versão do banco de dados. As migrations são executadas automaticamente na inicialização da aplicação.

- **V1**: Criação da tabela de médicos
- **V2**: Adição da coluna telefone em médicos
- **V3**: Criação da tabela de pacientes
- **V4**: Adição do campo `ativo` em médicos (para exclusão lógica)

## 🛠️ Desenvolvimento

### Padrões Utilizados
- **Records** para DTOs (Data Transfer Objects)
- **Repository Pattern** com Spring Data JPA
- **Bean Validation** para validação de entrada
- **Lombok** para redução de código boilerplate
- **Desativação Lógica** para médicos (soft delete)
- **Exclusão Física** para pacientes

### Boas Práticas Implementadas
- Separação de responsabilidades (Controllers, Services, Repositories)
- DTOs para comunicação com a API
- Validação em camadas com annotations
- Transações gerenciadas pelo Spring
- Paginação para melhor performance
- Tratamento de dados sensíveis

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

## 📚 Exemplos de Uso com cURL

### Cadastrar Médico
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

### Listar Médicos
```bash
curl http://localhost:8080/medicos?pagina=0&tamanho=10
```

### Deletar Médico
```bash
curl -X DELETE http://localhost:8080/medicos/1
```

### Cadastrar Paciente
```bash
curl -X POST http://localhost:8080/pacientes \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "Maria Silva",
    "email": "maria@example.com",
    "telefone": "11987654321",
    "cpf": "12345678901",
    "endereco": {
      "logradouro": "Rua das Flores",
      "numero": "200",
      "bairro": "Centro",
      "cep": "12345-678",
      "cidade": "São Paulo",
      "uf": "SP"
    }
  }'
```

### Listar Pacientes
```bash
curl http://localhost:8080/pacientes?pagina=0&tamanho=10
```

### Deletar Paciente
```bash
curl -X DELETE http://localhost:8080/pacientes/1
```

## 📝 Licença

Este projeto está sob a licença especificada no arquivo [LICENSE](LICENSE).

## 👨‍💻 Autor

Desenvolvido como projeto de estudo em Spring Boot.

---

⭐ Se este projeto foi útil para você, considere dar uma estrela no repositório!
