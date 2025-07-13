# Sistema de Biblioteca - MATA62 Engenharia de Software I

Este projeto é uma implementação de um sistema de gestão de biblioteca através da linha de comandos, desenvolvido como parte do trabalho prático da disciplina MATA62 - Engenharia de Software I. O objetivo principal é aplicar conceitos de Programação Orientada a Objetos e Padrões de Projeto (Design Patterns) a um problema prático.

## Arquitetura e Padrões de Projeto

A arquitetura do sistema foi projetada para ser modular e extensível, com uma clara separação de responsabilidades. Os seguintes padrões de projeto foram fundamentais na sua construção:

### 1. Singleton

* **Propósito**: Garantir que exista apenas uma única instância do repositório de dados em toda a aplicação, fornecendo um ponto de acesso global.
* **Implementação**: A classe `mata62.biblioteca.domain.repository.Repositorio` implementa o padrão Singleton. Possui um construtor privado e um método estático `getInstance()` que devolve a única instância existente.

### 2. Strategy

* **Propósito**: Encapsular os diferentes algoritmos para as regras de empréstimo, permitindo que a regra correta seja selecionada em tempo de execução sem o uso de condicionais (`if/else`) baseados no tipo do utilizador.
* **Implementação**:
    * **Strategy**: A interface `mata62.biblioteca.domain.strategies.RegraEmprestimo` define o contrato com o método `validar()`.
    * **Concrete Strategies**: As classes `RegraEmprestimoAluno` e `RegraEmprestimoProfessor` implementam a interface, cada uma com a sua lógica de validação específica.
    * **Context**: A classe abstrata `Usuario` e as suas subclasses atuam como o contexto, onde cada tipo de utilizador sabe qual é a sua estratégia de empréstimo através do método `getRegraEmprestimo()`.

### 3. Command

* **Propósito**: Encapsular cada solicitação do utilizador (como "emprestar", "devolver", etc.) num objeto. Isso desacopla quem invoca a ação (a classe `Main`) de quem executa a ação (o objeto de comando).
* **Implementação**:
    * **Command**: A interface `mata62.biblioteca.domain.commands.Comando` define o contrato com o método `executar()`.
    * **Concrete Commands**: Classes como `ComandoEmprestar`, `ComandoConsultarLivro`, etc., implementam a interface e contêm toda a lógica para realizar uma operação específica.
    * **Invoker**: A classe `Main` atua como o *Invoker*, mantendo um `Map` que associa a string do comando (ex: "emp") ao seu objeto de comando correspondente.

### 4. Observer

* **Propósito**: Criar um mecanismo de notificação que permite que objetos (Observadores) se registem para serem notificados automaticamente sobre eventos noutro objeto (Observado), sem que ambos estejam fortemente acoplados.
* **Implementação**:
    * **Subject (Observado)**: A classe `Livro` atua como o *Subject*. Ela mantém uma lista de observadores e notifica-os quando o número de reservas ultrapassa dois.
    * **Observer (Observador)**: A classe `Professor` atua como o *Observer*. Possui um método `notificar()` que é chamado pelo `Livro` e incrementa um contador interno.

## Estrutura do Projeto

```
biblioteca-MATA62/
├── pom.xml
├── README.md
└── src/
    └── main/
        └── java/
            └── mata62/
                └── biblioteca/
                    ├── Main.java
                    ├── data/
                    │   └── Setup.java
                    └── domain/
                        ├── commands/
                        │   ├── Comando.java
                        │   ├── ComandoConsultarLivro.java
                        │   ├── ComandoConsultarNotificacoes.java
                        │   ├── ComandoConsultarUsuario.java
                        │   ├── ComandoDevolver.java
                        │   ├── ComandoEmprestar.java
                        │   ├── ComandoObservar.class
                        │   └── ComandoReservar.class
                        ├── models/
                        │   ├── Usuario.java
                        │   ├── AlunoGraduacao.class
                        │   ├── AlunoPosgraduacao.class
                        │   ├── Emprestimo.class
                        │   ├── Exemplar.class
                        │   ├── Livro.class
                        │   ├── Professor.class
                        │   └── Reserva.class
                        ├── repository/
                        │   └── Repositorio.java
                        └── strategies/
                            ├── RegraEmprestimo.java
                            ├── RegraEmprestimoAluno.class
                            ├── RegraEmprestimoException.class
                            └── RegraEmprestimoProfessor.class
```

## Pré-requisitos

* Java JDK 11 ou superior
* Apache Maven

## Como Compilar e Executar

1.  Clone este repositório.
2.  Abra um terminal na pasta raiz do projeto (`biblioteca-MATA62/`).
3.  Compile e empacote o projeto com o seguinte comando Maven:
    ```sh
    mvn clean package
    ```
4.  Após a compilação bem-sucedida, execute a aplicação com:
    ```sh
    java -jar target/biblioteca-MATA62-1.0-SNAPSHOT.jar
    ```

## Comandos Disponíveis

| Comando | Formato                               | Descrição                                                                                 |
| :------ | :------------------------------------ | :---------------------------------------------------------------------------------------- |
| `emp`   | `emp <cod_usuario> <cod_livro>`       | Realiza o empréstimo de um livro para um utilizador.                                     |
| `dev`   | `dev <cod_usuario> <cod_livro>`       | Devolve um livro que estava emprestado.                                            |
| `res`   | `res <cod_usuario> <cod_livro>`       | Reserva um livro para um utilizador.                                                    |
| `liv`   | `liv <cod_livro>`                     | Consulta as informações de um livro, as suas reservas e o estado dos seus exemplares.   |
| `usu`   | `usu <cod_usuario>`                   | Consulta as informações de um utilizador, o seu histórico de empréstimos e reservas.       |
| `obs`   | `obs <cod_usuario> <cod_livro>`       | Regista um professor como observador de um livro.                                 |
| `ntf`   | `ntf <cod_usuario>`                   | Consulta o número de notificações recebidas por um professor observador.         |
| `sai`   | `sai`                                 | Encerra o sistema.                                                               |

---

**Autor(es)**

* Carlos Eduardo Lima Botelho Vasconcelos
* Fernando Costa