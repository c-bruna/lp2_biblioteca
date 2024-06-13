# Sistema de Gerenciamento de Biblioteca

Este programa em Java permite gerenciar operações básicas em uma biblioteca, como adicionar e remover livros, realizar empréstimos e devoluções, buscar livros por título ou autor, listar empréstimos ativos de usuários e cadastrar novos usuários.

## Funcionalidades

### Adicionar Livro

Permite adicionar um novo livro ao catálogo da biblioteca. São solicitadas informações como título, autor, ano de lançamento, assunto e quantidade em estoque.

### Remover Livro

Remove um livro existente no catálogo da biblioteca. É necessário fornecer o título do livro a ser removido.

### Realizar Empréstimo

Permite emprestar um livro a um usuário cadastrado na biblioteca. São solicitados o CPF do usuário e o título do livro desejado. O sistema verifica se o livro está disponível para empréstimo e se o usuário atende aos critérios de empréstimo.

### Realizar Devolução

Realiza a devolução de um livro emprestado. É necessário fornecer o título do livro a ser devolvido. O sistema marca o livro como disponível novamente e remove o empréstimo da lista de empréstimos ativos.

### Buscar Livro

Busca um livro pelo título ou autor. O sistema exibe informações detalhadas do livro encontrado ou informa se o livro não está no catálogo.

### Listar Empréstimos Ativos de um Usuário

Exibe todos os empréstimos ativos de um usuário, identificado pelo CPF. Se o usuário não tiver empréstimos ativos, o sistema informa que não há empréstimos registrados para esse usuário.

### Cadastrar Usuário

Permite cadastrar novos usuários na biblioteca. É necessário especificar o tipo de usuário (Estudante, Professor ou Bibliotecário) e fornecer informações pessoais como nome, CPF, matrícula, data de nascimento, e, para o caso de professores e bibliotecários, o departamento ou login/senha, respectivamente.

## Também na busca do livro tem-se a opção de leitura de arquivo .txt

- **Busca de Livro por Nome em Arquivo .txt**
    - Adicionamos a capacidade de buscar livros pelo nome em um arquivo `.txt` contendo uma lista de títulos de livros. O usuário fornece o nome do arquivo e o título do livro desejado. O sistema verifica o arquivo e retorna informações básicas do livro encontrado, se aplicável.

## Como Usar

Para usar o programa, siga as instruções apresentadas no console:

1. Escolha a operação desejada digitando o número correspondente no menu principal.
2. Siga as instruções fornecidas pelo programa para cada operação selecionada.
3. Para operações que exigem a entrada de dados do usuário, como adicionar livros, realizar empréstimos, cadastrar usuários, entre outras, siga as instruções específicas fornecidas pelo programa.

## Requisitos

- Java Development Kit (JDK) instalado no sistema.
- Ambiente de desenvolvimento Java configurado (por exemplo, IntelliJ IDEA, Eclipse).

## Como Executar

1. Clone ou baixe o repositório do projeto.
2. Abra o projeto em seu ambiente de desenvolvimento Java.
3. Execute a classe `Main.java` para iniciar o programa.
4. Siga as instruções apresentadas no console para interagir com o sistema.


