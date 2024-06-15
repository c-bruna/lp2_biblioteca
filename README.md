# Sistema de Gerenciamento de Biblioteca

## Pré-requisitos

- **Java Development Kit (JDK) instalado.** Recomendado JDK 8 ou superior.
- **IDE Java** (recomendado IntelliJ IDEA, Eclipse, NetBeans, etc.) ou compilador Java para executar a partir da linha de comando.

## Como Rodar o Programa

1. Clone o repositório para sua máquina local:
   ```bash
   git clone https://github.com/seu-usuario/sistema-biblioteca.git
2. Abra o projeto na sua IDE Java preferida.
3. Compile e execute o arquivo Main.java para iniciar o programa.
## Funcionalidades
### Login do Bibliotecário
Você acessará o usuário da bibliotecária Cleide.
- Login: admin
- Senha: senha123
Utilize estas credenciais para acessar o sistema.

### Adicionar Livro
Permite adicionar um novo livro ao sistema, informando título, autor, assunto, ano de publicação e quantidade de cópias.

### Realizar Empréstimo
Realiza o empréstimo de um livro para um usuário, informando o título do livro e o CPF do usuário.

### Realizar Devolução
Realiza a devolução de um livro emprestado, informando o título do livro e o CPF do usuário.

### Pesquisar Livro por Título
Permite pesquisar um livro no sistema pelo título.

### Remover Livro
Remove um livro do sistema, informando o título do livro a ser removido.

### Verificar Situação do Usuário por CPF
Verifica a situação de um usuário no sistema, informando o CPF do usuário.

### Listar Empréstimos Ativos de um Usuário por CPF
Lista os empréstimos ativos de um usuário, informando o CPF do usuário.

## Programação
### Arquivo binário
Utiliza-se um arquivo binário para armazenar os dados, houveram dificuldades nessa etapa.

### Enumerações
O enum foi utilizado para indicar a disponibilidade do livro.

### Herança
Foi utilizada a Herança entre Pessoa, Usuário, Estudante, Bibliotecário e Professor.

### Collections
ArrayList e List

### LocalDate
Foi utilizado para as datas dos empréstimos

### Dados iniciais
O trabalho inicializa com 6 livros, 1 bibliotecário, 3 estudantes e 1 professor.

## Autora
Bruna Caroliny Custódio da Silva









