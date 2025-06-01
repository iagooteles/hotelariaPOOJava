import java.io.BufferedReader;
import java.io.BufferedWriter;
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

    // public static Reserva consultar(int id) {
    //     try (BufferedReader br = new BufferedReader(new FileReader("data/reserva.txt"))) {
    //         String linha;

    //         while ((linha = br.readLine()) != null) {
    //             String[] partes = linha.split(";");

    //             int idReserva = Integer.parseInt(partes[0]);
    //             if (idReserva == id) {
    //                 int idQuarto = Integer.parseInt(partes[1]);
    //                 String cpfHospede = partes[2];
    //                 double valor = Double.parseDouble(partes[3]);
    //                 String dataEntrada = partes[4];
    //                 String dataSaida = partes[5];
    //                 boolean checkin = Boolean.parseBoolean(partes[6]);
    //                 boolean checkout = Boolean.parseBoolean(partes[7]);

    //                 Quarto quarto = Quarto.consultar(idQuarto);
    //                 Hospede hospede = Hospede.buscarPorCpf(cpfHospede);

    //                 if (quarto != null && hospede != null) {
    //                     return new Reserva(quarto, hospede, idReserva, valor, dataEntrada, dataSaida, checkin, checkout);
    //                 }
    //             }
    //         }

    //         return null;
    //     } catch (IOException e) {
    //         System.out.println("Erro ao consultar: " + e.getMessage());
    //         return null;
    //     }
    // }

    // public static boolean deletar(int idReservaDeletar) {
    //     try (BufferedReader br = new BufferedReader(new FileReader("data/reserva.txt"))) {
    //         ArrayList<String> linhas = new ArrayList<>();
    //         String linha;
    //         boolean reservaEncontrada = false;

    //         while ((linha = br.readLine()) != null) {
    //             String[] partes = linha.split(";");
    //             int idReserva = Integer.parseInt(partes[0]);

    //             if (idReserva == idReservaDeletar) {
    //                 reservaEncontrada = true;
    //                 continue;
    //             } else {
    //                 linhas.add(linha);
    //             }
    //         }

    //         if (!reservaEncontrada) {
    //             return false;
    //         }

    //         try (BufferedWriter bw = new BufferedWriter(new FileWriter("data/reserva.txt"))) {
    //             for (String l : linhas) {
    //                 bw.write(l);
    //                 bw.newLine();
    //             }
    //             return true;
    //         } catch (IOException e) {
    //             System.out.println("Erro ao salvar: " + e.getMessage());
    //             return false;
    //         }

    //     } catch (IOException e) {
    //         System.out.println("Erro: " + e.getMessage());
    //         return false;
    //     }
    // }

    // public static Reserva criarReserva(Scanner sc) {
    //     int ultimoID = 0;
    //     String linha;

    //     try (BufferedReader br = new BufferedReader(new FileReader("data/reserva.txt"))) {
    //         while ((linha = br.readLine()) != null) {
    //             String[] partes = linha.split(";");
    //             ultimoID = Integer.parseInt(partes[0]);
    //         }
    //     } catch (IOException e) {
    //         System.out.println("Arquivo não encontrado, será criado.");
    //     }

    //     int idReserva = ultimoID + 1;

    //     System.out.println("Digite o ID do quarto:");
    //     int idQuarto = Integer.parseInt(sc.nextLine());
    //     Quarto quarto = Quarto.consultar(idQuarto);

    //     if (quarto == null) {
    //         System.out.println("Não foi possível encontrar um quarto com este ID. \nTente novamente.");

    //         return null;
    //     }

    //     System.out.println("Digite o cpf do hóspede:");
    //     String cpfHospede = sc.nextLine();
    //     Hospede hospede = Hospede.buscarPorCpf(cpfHospede);

    //     if (hospede == null) {
    //         System.out.println("Não foi possível encontrar um Hospede com este cpf. \nTente novamente.");
            
    //         return null;
    //     }

    //     System.out.print("Digite o valor da reserva: ");
    //     double valor = Double.parseDouble(sc.nextLine());

    //     System.out.print("Digite a data de entrada (DD/MM/AAAA): ");
    //     String dataEntrada = sc.nextLine();

    //     System.out.print("Digite a data de saída (DD/MM/AAAA): ");
    //     String dataSaida = sc.nextLine();

    //     return new Reserva(quarto, hospede, idReserva, valor, dataEntrada, dataSaida, false, false);
    // }

    // @Override
    // public String toString() {
    //     return "Reserva ID: " + idReserva + " | Quarto ID: " + quarto.getIdQuarto() + 
    //            " | Hospede CPF: " + hospede.getCpf() + 
    //            " | Valor: " + valor + 
    //            " | Entrada: " + dataEntrada + 
    //            " | Saída: " + dataSaida + 
    //            " | Check-in: " + checkin + 
    //            " | Check-out: " + checkout;
    // }
}
