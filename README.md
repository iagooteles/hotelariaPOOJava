Para a avaliação teremos os seguintes critérios: 

Todos os métodos devem escrever e ler em txt;
Tratamento de exceção diferente do IOException em pelo menos 5 classes ; 
Implementação da classe abstrata; 
Implementação de herança em 2 classes;
Método toString implementado em todos os objetos;
Uso de encapsulamento em todos os objetos; 

OBS: A execução do sistema desenvolvido deve coletar informações de entrada do usuário, realizar o processamento da mesma e em seguida, realizar a exibição das informações processadas. Para realizar esta tarefa, o sistema precisa conter um MENU funcional que realiza a gestão de todos os requisitos descritos anteriormente. A avaliação do trabalho está condicionada ao envio do código fonte sem erros de compilação e pela arguição realizada em sala de aula de forma individual. Nesta arguição, o aluno deverá responder perguntas e explicar sobre as implementações do sistema. Caso haja o descumprimento de alguma das exigências anteriores, será atribuída nota zero para o critério. Além disso, o aluno que não saiba explicar ou responder perguntas sobre o código fonte desenvolvido, também será atribuída nota zero ao trabalho.

Requisitos:

Classe Abstrata Pessoa com atributos protected cpf do tipo String, nome do tipo String e idade do tipo int;

Classe Funcionario que herda de Pessoa. Adicionar atributos privados  funcao do tipo String;

Classe Hospede que herda de Pessoa e adiciona atributos privados rg do tipo String, fidelidade do tipo Boolean;

Classe Quarto com atributos privados idQuarto  do tipo int, descQuarto  do tipo String;

Classe Produto com atributos privados idProduto   do tipo int, descProduto  do tipo String e valor do tipo Double;

Classe Reserva que tem um relacionamento associação com Quarto e Hospede e tem atributos privados idReserva do tipo int, valor do tipo Double, dataEntrada do tipo String, dataSaida do tipo String, checkin do tipo Boolean, checkout do tipo Boolean, quarto do tipo Quarto e hospede do tipo Hospede;

Classe Consumo que tem associação com Reserva e Produto, atributos privados idConsumo do tipo int, produto do tipo Produto, reserva do tipo Reserva e quantidade do tipo Double;


Todas as classes terão os métodos de públicos da seguinte forma:

método construtor recebendo todos os atributos;
método inserir sem argumento com retorno Boolean;
método editar sem argumento com retorno Boolean;
método listar sem argumento com retorno ArrayList da classe;
método consultar com argumento do id e tipo int com retorno do objeto da classe;
método mostrar com todos os atributos apresentados.

