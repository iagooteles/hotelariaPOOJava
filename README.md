# 🏨 Sistema de Gestão Hoteleira em Java

Este projeto é um sistema de gestão para hotelaria, desenvolvido em Java com persistência de dados em arquivos .txt. O sistema permite o cadastro, consulta, edição e listagem de hóspedes, funcionários, quartos, reservas, produtos e consumos.

O sistema foi desenvolvido como parte de uma avaliação, atendendo aos seguintes critérios:

✅ Leitura e escrita em arquivos .txt

✅ Tratamento de exceções além de IOException em pelo menos 5 classes

✅ Implementação de classe abstrata

✅ Herança aplicada em duas classes

✅ Método toString() implementado em todas as classes

✅ Encapsulamento aplicado em todos os objetos

✅ Menu funcional para interação com o usuário

## 📦 Funcionalidades
- 🧑‍💼 Gestão de Funcionários
- 🧑‍🎓 Gestão de Hóspedes
- 🏨 Gestão de Quartos
- 📦 Gestão de Produtos
- 📅 Gestão de Reservas (associadas a hóspedes e quartos)
- 🍽️ Gestão de Consumos (associados a reservas e produtos)

## 🏗️ Estrutura de Classes
🔷 Classe Abstrata
Pessoa
Atributos: cpf (String), nome (String), idade (int)
✔️ Definida como abstrata para generalizar características comuns a funcionários e hóspedes.

🧑‍💼 Classe Funcionario (herda Pessoa)
Atributo específico: funcao (String)

🧑‍🎓 Classe Hospede (herda Pessoa)
Atributos específicos: rg (String), fidelidade (Boolean)

🏨 Classe Quarto
Atributos: idQuarto (int), descQuarto (String)

🛍️ Classe Produto
Atributos: idProduto (int), descProduto (String), valor (Double)

📅 Classe Reserva
Atributos:
idReserva (int), valor (Double), dataEntrada (String), dataSaida (String), checkin (Boolean), checkout (Boolean)

Associações:
quarto (Quarto), hospede (Hospede)

🍽️ Classe Consumo
Atributos:
idConsumo (int), quantidade (Double)

Associações:
produto (Produto), reserva (Reserva)

## 🧠 Implementações Técnicas
✔️ Encapsulamento: Todos os atributos são privados ou protegidos com acesso via getters e setters.

✔️ Herança: Aplicada nas classes Funcionario e Hospede a partir da classe abstrata Pessoa.

✔️ Classe Abstrata: Pessoa contém atributos e comportamentos comuns.

✔️ Método toString(): Implementado em todas as classes para exibir informações legíveis dos objetos.

✔️ Manipulação de Arquivos: Todos os dados são lidos e gravados em arquivos .txt.

✔️ Tratamento de Exceções: Implementado além de IOException (como NumberFormatException, NullPointerException, InputMismatchException, entre outros) em pelo menos 5 classes.

✔️ Menu Interativo: Sistema apresenta um menu funcional no console, permitindo a navegação e utilização de todas as funções do sistema.

## 🚀 Execução do Projeto

✔️ Pré-requisitos:
Java JDK instalado (versão 8 ou superior)

▶️ Como executar:
Clone este repositório:

```bash
git clone https://github.com/iagooteles/hotelariaPOOJava.git
```

Compile os arquivos Java:
```bash
javac src/*.java
```

Execute o sistema:
```bash
java src.Menu
```

## 📂 Estrutura de Arquivos
Os dados são armazenados na pasta data/, em arquivos .txt separados por entidade, como:

data/
├── funcionario.txt  
├── hospede.txt  
├── produto.txt  
├── quarto.txt  
├── reserva.txt  
└── consumo.txt  


## 📝 Funcionalidades do Menu
✅ Cadastrar (Funcionário, Hóspede, Quarto, Produto, Reserva, Consumo)

✅ Editar registros existentes

✅ Listar todos os registros

✅ Consultar por ID (ou CPF no caso de pessoas)

✅ Mostrar detalhes de registros
