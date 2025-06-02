import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Consumo {
    private int idConsumo;
    private Reserva reserva;
    private Produto produto;
    private double quantidade;

    public Consumo(int idConsumo, Reserva reserva, Produto produto, double quantidade) {
        this.idConsumo = idConsumo;
        this.reserva = reserva;
        this.produto = produto;
        this.quantidade = quantidade;
    }

    private String toLinha() {
        return this.idConsumo + ";" + 
               this.reserva.getIdReserva() + ";" + 
               this.produto.getIdProduto() + ";" + 
               this.quantidade + ";";
    }

    public boolean inserir() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("data/consumo.txt", true))) {
            bw.write(toLinha());
            bw.newLine();
            return true;
        } catch (IOException e) {
            System.out.println("Erro ao inserir: " + e.getMessage());
            return false;
        }
    }

    public static ArrayList<Consumo> listar() {
        try (BufferedReader br = new BufferedReader(new FileReader("data/consumo.txt"))) {
            ArrayList<Consumo> consumos = new ArrayList<>();
            String linha;
            
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");

                int idConsumo = Integer.parseInt(partes[0]);
                int idReserva = Integer.parseInt(partes[1]);
                int idProduto = Integer.parseInt(partes[2]);
                double quantidade = Double.parseDouble(partes[3]);

                Reserva reserva = Reserva.consultar(idReserva);
                Produto produto = Produto.consultar(idProduto);

                if (reserva != null && produto != null) {
                    consumos.add(new Consumo(idConsumo, reserva, produto, quantidade));
                }
            }

            return consumos;
        } catch (IOException e) {
            System.out.println("Erro ao listar: " + e.getMessage());
            return null;
        }
    }

    public static Consumo consultar(int idConsumoConsultado) {
        try (BufferedReader br = new BufferedReader(new FileReader("data/consumo.txt"))) {
            String linha;

            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");

                int idConsumo = Integer.parseInt(partes[0]);
                if (idConsumo == idConsumoConsultado) {
                    int idReserva = Integer.parseInt(partes[1]);
                    int idProduto = Integer.parseInt(partes[2]);
                    double quantidade = Double.parseDouble(partes[3]);

                    Reserva reserva = Reserva.consultar(idReserva);
                    Produto produto = Produto.consultar(idProduto);

                    if (reserva != null && produto != null) {
                        return new Consumo(idConsumo, reserva, produto, quantidade);
                    }
                }
            }

            return null;
        } catch (IOException e) {
            System.out.println("Erro ao consultar: " + e.getMessage());
            return null;
        }
    }

    public static boolean editar(int idConsumoEdit, Scanner scanner) {
        try (BufferedReader br = new BufferedReader(new FileReader("data/consumo.txt"))) {
            ArrayList<String> linhas = new ArrayList<>();
            String linha;
            boolean consumoEncontrado = false;

            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");

                int idConsumo = Integer.parseInt(partes[0]);

                String reservaId = partes[1];
                String produtoId = partes[2];
                String quatidade = partes[3];

                if (idConsumo == idConsumoEdit) {
                    consumoEncontrado = true;
                    System.out.println("Digite a nova reserva ID (valor atual: " + reservaId + "):");
                    String novaReservaId = scanner.nextLine();

                    System.out.println("Digite um novo produto ID (valor atual: " + produtoId + "):");
                    String novoProdutoId = scanner.nextLine();

                    System.out.println("Digite uma nova quantidade (valor atual: " + quatidade + "):");
                    String novaQuantidade = scanner.nextLine();

                    linhas.add(idConsumo + ";" + novaReservaId + ";" + novoProdutoId + ";" + novaQuantidade);
                } else {
                    linhas.add(linha);
                }
            }

            if (!consumoEncontrado) {
                return false;
            }

            try (BufferedWriter bw = new BufferedWriter(new FileWriter("data/consumo.txt"))) {
                for (String l : linhas) {
                    bw.write(l);
                    bw.newLine();
                }
                return true;
            } catch (IOException e) {
                System.out.println("Erro ao salvar as alterações: " + e.getMessage());
                return false;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado.");
            e.getMessage();
            return false;
        } catch (IOException e) {
            e.getMessage();
            return false;
        }
    }

    public static boolean deletar(int idConsumoDeletar) {
        try (BufferedReader br = new BufferedReader(new FileReader("data/consumo.txt"))) {
            ArrayList<String> linhas = new ArrayList<>();
            String linha;
            boolean consumoEncontrado = false;

            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");
                int idConsumo = Integer.parseInt(partes[0]);

                if (idConsumo == idConsumoDeletar) {
                    consumoEncontrado = true;
                    continue;
                } else {
                    linhas.add(linha);
                }
            }

            if (!consumoEncontrado) {
                return false;
            }

            try (BufferedWriter bw = new BufferedWriter(new FileWriter("data/consumo.txt"))) {
                for (String l : linhas) {
                    bw.write(l);
                    bw.newLine();
                }

                return true;
            } catch (IOException e) {
                System.out.println("Erro ao salvar: " + e.getMessage());

                return false;
            }

        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());
            return false;
        }
    }

    public static Consumo criarConsumo(Scanner sc) {
        int ultimoID = 0;
        String linha;

        try (BufferedReader br = new BufferedReader(new FileReader("data/consumo.txt"))) {
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");
                ultimoID = Integer.parseInt(partes[0]);
            }
        } catch (IOException e) {
            System.out.println("Arquivo não encontrado, será criado.");
        }

        int idConsumo = ultimoID + 1;

        System.out.println("Digite o ID da Reserva:");
        int idReserva = Integer.parseInt(sc.nextLine());
        Reserva reserva = Reserva.consultar(idReserva);

        if (reserva == null) {
            System.out.println("Não foi possível encontrar uma reserva com este ID. \nTente novamente.");

            return null;
        }

        System.out.println("Digite o id do Produto:");
        int idProduto = Integer.parseInt(sc.nextLine());
        Produto produto = Produto.consultar(idProduto);

        if (produto == null) {
            System.out.println("Não foi possível encontrar um Produto com este ID. \nTente novamente.");
            
            return null;
        }

        System.out.print("Digite a quantidade: ");
        double quantidade = Double.parseDouble(sc.nextLine());

        return new Consumo(idConsumo, reserva, produto, quantidade);
    }

    @Override
    public String toString() {
        return "Consumo ID: " + this.idConsumo + 
               " | Reserva ID: " + this.reserva.getIdReserva() + 
               " | Produto ID: " + this.produto.getIdProduto() + 
               " | Quantidade: " + this.quantidade;
    }
}
