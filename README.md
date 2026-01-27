# 🏥 Clínica VollMed API

API REST desenvolvida em Spring Boot para gerenciamento de uma clínica médica. O sistema permite o cadastro, listagem e atualização de médicos, incluindo suas especialidades e endereços.

## 📋 Sobre o Projeto

A API VollMed é um sistema de gerenciamento de clínica médica que oferece funcionalidades completas de CRUD (Create, Read, Update, Delete) para médicos. O projeto implementa as melhores práticas de desenvolvimento com Spring Boot, incluindo validação de dados, paginação, e persistência com JPA/Hibernate.

## 🚀 Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.5.7**
- **Spring Data JPA** - Para persistência de dados
- **Spring Validation** - Para validação de dados
- **Flyway** - Para controle de versão do banco de dados
- **MySQL** - Banco de dados relacional
- **Lombok** - Para redução de código boilerplate
- **Maven** - Gerenciamento de dependências

## 📦 Funcionalidades

### Médicos
- ✅ Cadastro de médicos com validação de dados
- ✅ Listagem paginada de médicos
- ✅ Atualização de dados de médicos
- ✅ Especialidades: Ortopedia, Cardiologia, Ginecologia, Dermatologia

### Validações Implementadas
- Nome, email, telefone e CRM obrigatórios
- Validação de formato de email
- CRM com 4 a 6 dígitos
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

### Cadastrar Médico
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

### Listar Médicos (Paginado)
```http
GET /medicos?pagina=0&tamanho=5&ordem=nome
```

**Parâmetros de paginação:**
- `pagina` - Número da página (padrão: 0)
- `tamanho` - Tamanho da página (padrão: 5)
- `ordem` - Campo para ordenação (padrão: nome)

### Atualizar Médico
```http
PUT /medicos
Content-Type: application/json

{
  "id": 1,
  "nome": "Dr. João Silva Atualizado",
  "telefone": "11999998888",
  "endereco": {
    "logradouro": "Rua Nova",
    "numero": "200"
  }
}
```

## 📁 Estrutura do Projeto

```
api/
├── src/
│   ├── main/
│   │   ├── java/med/voll/api/
│   │   │   ├── ApiApplication.java
│   │   │   ├── Controller/
│   │   │   │   └── MedicoController.java
│   │   │   ├── Medico/
│   │   │   │   ├── Medico.java
│   │   │   │   ├── MedicoRepository.java
│   │   │   │   ├── DadosCadastroMedico.java
│   │   │   │   ├── DadosListarMedico.java
│   │   │   │   ├── DadosAtualizaMedico.java
│   │   │   │   └── Especialidade.java
│   │   │   └── Endereco/
│   │   │       ├── Endereco.java
│   │   │       └── DadosEndereco.java
│   │   └── resources/
│   │       ├── application.properties
│   │       └── db/migration/
│   │           ├── V1__create-table-medico.sql
│   │           └── V2__alter-table-medicos-add-column-telefone.sql
│   └── test/
└── pom.xml
```

## 🔍 Migrations do Flyway

O projeto utiliza Flyway para controle de versão do banco de dados. As migrations são executadas automaticamente na inicialização da aplicação.

- **V1**: Criação da tabela de médicos
- **V2**: Adição da coluna telefone

## 🛠️ Desenvolvimento

### Padrões Utilizados
- **Records** para DTOs (Data Transfer Objects)
- **Repository Pattern** com Spring Data JPA
- **Bean Validation** para validação de entrada
- **Lombok** para redução de código boilerplate

## 📝 Licença

Este projeto está sob a licença especificada no arquivo [LICENSE](LICENSE).

## 👨‍💻 Autor
Marlysson S. Dantas

Desenvolvido como projeto de estudo em Spring Boot.

---

⭐ Se este projeto foi útil para você, considere dar uma estrela no repositório!
