import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        Menu menu = new Menu();
        boolean runApp = true;

        java.io.File pasta = new java.io.File("data");
        if (!pasta.exists()) {
            pasta.mkdirs();
        }

        // Classes auxiliares
        Funcionario funcionarioAuxiliar = new Funcionario("", "", 0, "");
        Hospede hospedeAuxiliar = new Hospede("", "", 0, "");
        var listaFuncionarios = funcionarioAuxiliar.listar();
        var listaHospedes = hospedeAuxiliar.listar();
        ArrayList<Produto> listaProdutos = Produto.listar();
        ArrayList<Quarto> listaQuartos = Quarto.listar();
        ArrayList<Reserva> listaReservas = Reserva.listar();

        while (runApp) {
            menu.mostrarMenu();
            int op = scanner.nextInt();
            scanner.nextLine();

            switch (op) {
                case 1:
                    Funcionario f = Funcionario.criarFuncionario(scanner);
                    if (f.inserir()) {
                        System.out.println("_".repeat(30));
                        System.out.println("Funcionário salvo com sucesso!");
                    } else {
                        System.out.println("_".repeat(30));
                        System.out.println("Erro ao salvar funcionário.");
                    }
                    break;

                case 2:
                    System.out.println("=== Lista de Funcionários ===");
                    listaFuncionarios = funcionarioAuxiliar.listar();

                    if (listaFuncionarios.isEmpty()) {
                        System.out.println("Nenhum funcionário cadastrado.");
                    } else {
                        for (Pessoa p : listaFuncionarios) {
                            System.out.println(p);
                        }
                    }
                    System.out.println("_".repeat(30));
                    break;
                    
                case 4: 
                    break;

                case 5:
                    System.out.println("Remover Funcionário");
                    listaFuncionarios = funcionarioAuxiliar.listar();

                    if (listaFuncionarios.isEmpty()) {
                        System.out.println("Nenhum funcionário cadastrado.");
                    } else {
                        for (Pessoa p : listaFuncionarios) {
                            System.out.println(p);
                        }
                    }

                    System.out.println("Digite o cpf do funcionário que deseja remover: ");
                    String cpfFuncionarioASerRemovido = scanner.nextLine();
                    funcionarioAuxiliar.deletar(cpfFuncionarioASerRemovido);
                    break;

                case 11: 
                    System.out.println("=== Adicionar Hóspede ===");
                    Hospede hospede = Hospede.criarHospede(scanner);
                    if (hospede.inserir()) {
                        System.out.println("_".repeat(30));
                        System.out.println("Hospede salvo com sucesso!");
                    } else {
                        System.out.println("_".repeat(30));
                        System.out.println("Erro ao salvar hospede.");
                    }
                    break;

                case 12:
                    System.out.println("=== Lista de Hóspedes ===");
                    listaHospedes = hospedeAuxiliar.listar();

                    if (listaHospedes.isEmpty()) {
                        System.out.println("Nenhum Hóspede cadastrado.");
                    } else {
                        for (var hospedeCadastrado : listaHospedes) {
                            System.out.println(hospedeCadastrado);
                        }
                    }
                    System.out.println("_".repeat(30));
                    break;
                    
                case 13:
                    System.out.println("=== Consultar de Hóspede ===");
                    System.out.println("Digite o cpf do hospede que quer consultar: ");
                    String cpfHospedeConsulta = scanner.nextLine();
                    
                
                case 14: 
                    break;


                
                case 15:
                    System.out.println("Remover Hóspede");
                    listaHospedes = hospedeAuxiliar.listar();

                    if (listaHospedes.isEmpty()) {
                        System.out.println("Nenhum Hóspede cadastrado.");
                    } else {
                        for (Pessoa p : listaHospedes) {
                            System.out.println(p);
                        }
                    }

                    System.out.println("Digite o cpf do hóspede que deseja remover: ");
                    String cpfHospedeASerRemovido = scanner.nextLine();
                    if (hospedeAuxiliar.deletar(cpfHospedeASerRemovido)) {
                        System.out.println("Hóspede removido com sucesso!");
                    } else {
                        System.out.println("Não foi possível remover o hóspede.");
                    }
                    break;

                case 21: 
                    System.out.println("Adicionar produto");
                    Produto produto = Produto.criarProduto(scanner);
                    if (produto.inserir()) {
                        System.out.println("_".repeat(30));
                        System.out.println("Produto salvo com sucesso!");
                    } else {
                        System.out.println("_".repeat(30));
                        System.out.println("Erro ao salvar Produto.");
                    }
                    break;
                
                case 22: 
                    System.out.println("=== Lista de Produtos ===");
                    listaProdutos = Produto.listar();

                    if (listaProdutos.isEmpty()) {
                        System.out.println("Nenhum produto cadastrado.");
                    } else {
                        for (Produto produtoCadastrado: listaProdutos) {
                            System.out.println(produtoCadastrado);
                        }
                    }
                    System.out.println("_".repeat(30));
                    break;

                case 23:
                    System.out.println("=== Consultar produto ===");
                    System.out.println("Digite o id do produto que quer consultar: ");
                    int idProdutoConsulta = Integer.parseInt(scanner.nextLine());

                    Produto produtoConsultado = Produto.consultar(idProdutoConsulta);

                    if (produtoConsultado != null) {
                        System.out.println(produtoConsultado.toString());
                    } else {
                        System.out.println("Não foi possível encontrar o produto");
                    }

                    break;
                case 24:
                    System.out.println("=== Editar Produto ===");
                    System.out.println("Digite o id do produto que deseja editar.");
                    listaProdutos = Produto.listar();

                    if (listaProdutos.isEmpty()) {
                        System.out.println("Nenhum produto cadastrado.");
                    } else {
                        for (Produto produtoCadastrado: listaProdutos) {
                            System.out.println(produtoCadastrado);
                        }
                    }

                    int idProdutoEdit = Integer.parseInt(scanner.nextLine());

                    if (Produto.editar(idProdutoEdit, scanner)) { 
                        System.out.println("Produto editado com sucesso!");
                    } else {
                        System.out.println("Erro ao editar produto.");
                    }
                    break;

                case 25:
                    System.out.println("=== Deletar Produto ===");
                    System.out.println("Digite o ID do produto que quer remover");
                    listaProdutos = Produto.listar();

                    if (listaProdutos.isEmpty()) {
                        System.out.println("Nenhum produto cadastrado.");
                    } else {
                        for (Produto produtoCadastrado: listaProdutos) {
                            System.out.println(produtoCadastrado);
                        }
                    }

                    int idProduto = Integer.parseInt(scanner.nextLine());
                    if (Produto.deletar(idProduto)) { 
                        System.out.println("Produto deletado com sucesso!");
                    } else {
                        System.out.println("Erro ao deletar produto.");
                    }
                    break;

                case 31: 
                    System.out.println("=== Adicionar Quarto ===");

                    Quarto quarto = Quarto.criarQuarto(scanner);
                    if (quarto.inserir()) {
                        System.out.println("_".repeat(30));
                        System.out.println("Quarto salvo com sucesso!");
                    } else {
                        System.out.println("_".repeat(30));
                        System.out.println("Erro ao salvar Quarto.");
                    }
                    break;

                case 32: 
                    System.out.println("=== Lista de Quartos ===");
                    listaQuartos = Quarto.listar();

                    if (listaQuartos.isEmpty()) {
                        System.out.println("Nenhum Quarto cadastrado.");
                    } else {
                        for (Quarto quartoCadastrado: listaQuartos) {
                            System.out.println(quartoCadastrado);
                        }
                    }
                    System.out.println("_".repeat(30));
                    break;

                case 33:
                    System.out.println("=== Consultar Quarto ===");
                    System.out.println("Digite o id do Quarto que quer consultar: ");
                    int idQuartoConsulta = Integer.parseInt(scanner.nextLine());

                    Quarto quartoConsultado = Quarto.consultar(idQuartoConsulta);

                    if (quartoConsultado != null) {
                        System.out.println(quartoConsultado.toString());
                    } else {
                        System.out.println("Não foi possível encontrar o Quarto");
                    }
                    break;

                case 34:
                    System.out.println("=== Editar Quarto ===");
                    System.out.println("Digite o id do quarto que deseja editar.");
                    listaQuartos = Quarto.listar();

                    if (listaQuartos.isEmpty()) {
                        System.out.println("Nenhum quarto cadastrado.");
                    } else {
                        for (Quarto quartoCadastrado: listaQuartos) {
                            System.out.println(quartoCadastrado);
                        }
                    }

                    int idQuartoEdit = Integer.parseInt(scanner.nextLine());

                    if (Quarto.editar(idQuartoEdit, scanner)) { 
                        System.out.println("Produto editado com sucesso!");
                    } else {
                        System.out.println("Erro ao editar produto.");
                    }
                    break;

                case 35:
                    System.out.println("=== Deletar Quarto ===");
                    System.out.println("Digite o ID do quarto que quer remover:");
                    listaQuartos = Quarto.listar();

                    if (listaQuartos.isEmpty()) {
                        System.out.println("Nenhum quarto cadastrado.");
                    } else {
                        for (Quarto quartoCadastrado: listaQuartos) {
                            System.out.println(quartoCadastrado);
                        }
                    }

                    int idQuartoToDelete = Integer.parseInt(scanner.nextLine());
                    if (Quarto.deletar(idQuartoToDelete)) { 
                        System.out.println("Quarto deletado com sucesso!");
                    } else {
                        System.out.println("Erro ao deletar quarto.");
                    }
                    break;

                    

                case 41: 
                    System.out.println("=== Adicionar Reserva ===");

                    try {
                        Reserva reserva = Reserva.criarReserva(scanner);
                        if (reserva.inserir()) {
                            System.out.println("_".repeat(30));
                            System.out.println("Reserva salva com sucesso!");
                        } else {
                            System.out.println("_".repeat(30));
                            System.out.println("Erro ao salvar Reserva.");
                        }
                    } catch (NullPointerException e) {
                        e.getMessage();
                    }

                    break;

                case 42: 
                    System.out.println("=== Lista de Reservas ===");
                    listaReservas = Reserva.listar();

                    if (listaReservas.isEmpty()) {
                        System.out.println("Nenhuma Reserva cadastrada.");
                    } else {
                        for (Reserva reservaCadastrada: listaReservas) {
                            System.out.println(reservaCadastrada);
                        }
                    }
                    System.out.println("_".repeat(30));
                    break;

                case 43:
                    System.out.println("=== Consultar Reserva ===");
                    System.out.println("Digite o id da Reserva que quer consultar: ");
                    int idReservaConsulta = Integer.parseInt(scanner.nextLine());

                    Reserva reservaConsultada = Reserva.consultar(idReservaConsulta);

                    if (reservaConsultada != null) {
                        System.out.println(reservaConsultada);
                    } else {
                        System.out.println("Não foi possível encontrar a Reserva");
                    }
                    break;

                // case 44:
                //     System.out.println("=== Editar Reserva ===");
                //     System.out.println("Digite o id da reserva que deseja editar.");
                //     listaReservas = Reserva.listar();

                //     if (listaReservas.isEmpty()) {
                //         System.out.println("Nenhuma reserva cadastrada.");
                //     } else {
                //         for (Reserva reservaCadastrada: listaReservas) {
                //             System.out.println(reservaCadastrada);
                //         }
                //     }

                //     int idReservaEdit = Integer.parseInt(scanner.nextLine());

                //     if (Reserva.editar(idReservaEdit, scanner)) { // criar depois //
                //         System.out.println("Produto editado com sucesso!");
                //     } else {
                //         System.out.println("Erro ao editar produto.");
                //     }
                //     break;

                case 45:
                    System.out.println("=== Deletar Reserva ===");
                    System.out.println("Lista de reservas:");
                    listaReservas = Reserva.listar();
                    
                    if (listaReservas.isEmpty()) {
                        System.out.println("Nenhuma reserva cadastrada.");
                    } else {
                        for (Reserva reservaCadastrada: listaReservas) {
                            System.out.println(reservaCadastrada);
                        }
                    }
                    System.out.println("_".repeat(30));
                    System.out.println("Digite o ID da reserva que quer remover:");

                    int idReservaToDelete = Integer.parseInt(scanner.nextLine());
                    if (Reserva.deletar(idReservaToDelete)) { 
                        System.out.println("Reserva deletada com sucesso!");
                    } else {
                        System.out.println("Erro ao deletar Reserva.");
                    }
                    break;

                case 0:
                    System.out.println("Até logo.");
                    System.out.println("Fechando o Aplicativo.");
                    runApp = false;
                    break;

                default:
                    System.out.println("Opção inválida.");

                    break;
            }
        }

        scanner.close();
    }
}


// TODO: Criar editar funcionario...
// TODO: Criar listar, editar e remover produto..