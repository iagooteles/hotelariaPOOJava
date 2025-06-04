import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Reserva {
    private int idReserva;
    private Quarto quarto;
    private Hospede hospede;
    private double valor;
    private String dataEntrada;
    private String dataSaida;
    private boolean checkin;
    private boolean checkout;

    public Reserva(int idReserva, Quarto quarto, Hospede hospede, double valor, String dataEntrada, String dataSaida, boolean checkin, boolean checkout) {
        this.idReserva = idReserva;
        this.quarto = quarto;
        this.hospede = hospede;
        this.valor = valor;
        this.dataEntrada = dataEntrada;
        this.dataSaida = dataSaida;
        this.checkin = checkin;
        this.checkout = checkout;
    }

    public int getIdReserva() {
        return this.idReserva;
    }

    private String toLinha() {
        return this.idReserva + ";" + 
               this.quarto.getIdQuarto() + ";" + 
               this.hospede.getCpf() + ";" + 
               this.valor + ";" + 
               this.dataEntrada + ";" + 
               this.dataSaida + ";" + 
               this.checkin + ";" + 
               this.checkout;
    }

    public boolean inserir() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("data/reserva.txt", true))) {
            bw.write(toLinha());
            bw.newLine();
            return true;
        } catch (IOException e) {
            System.out.println("Erro ao inserir: " + e.getMessage());
            return false;
        }
    }

    public static ArrayList<Reserva> listar() {
        try (BufferedReader br = new BufferedReader(new FileReader("data/reserva.txt"))) {
            ArrayList<Reserva> reservas = new ArrayList<>();
            String linha;

            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");

                int idReserva = Integer.parseInt(partes[0]);
                int idQuarto = Integer.parseInt(partes[1]);
                String cpfHospede = partes[2];
                double valor = Double.parseDouble(partes[3]);
                String dataEntrada = partes[4];
                String dataSaida = partes[5];
                boolean checkin = Boolean.parseBoolean(partes[6]);
                boolean checkout = Boolean.parseBoolean(partes[7]);

                Quarto quarto = Quarto.consultar(idQuarto);
                Hospede hospede = Hospede.buscarPorCpf(cpfHospede);

                if (quarto != null && hospede != null) {
                    reservas.add(new Reserva(idReserva, quarto, hospede, valor, dataEntrada, dataSaida, checkin, checkout));
                }
            }

            return reservas;
        } catch (IOException e) {
            System.out.println("Erro ao listar: " + e.getMessage());
            return null;
        }
    }

    public static Reserva consultar(int idReservaConsultada) {
        try (BufferedReader br = new BufferedReader(new FileReader("data/reserva.txt"))) {
            String linha;

            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");

                int idReserva = Integer.parseInt(partes[0]);
                if (idReserva == idReservaConsultada) {
                    int idQuarto = Integer.parseInt(partes[1]);
                    String cpfHospede = partes[2];
                    double valor = Double.parseDouble(partes[3]);
                    String dataEntrada = partes[4];
                    String dataSaida = partes[5];
                    boolean checkin = Boolean.parseBoolean(partes[6]);
                    boolean checkout = Boolean.parseBoolean(partes[7]);

                    Quarto quarto = Quarto.consultar(idQuarto);
                    Hospede hospede = Hospede.buscarPorCpf(cpfHospede);

                    if (quarto != null && hospede != null) {
                        return new Reserva(idReserva, quarto, hospede, valor, dataEntrada, dataSaida, checkin, checkout);
                    }
                }
            }

            return null;
        } catch (IOException e) {
            System.out.println("Erro ao consultar: " + e.getMessage());
            return null;
        }
    }

    public static boolean editar(int idReservaEdit, Scanner scanner) {
        try (BufferedReader br = new BufferedReader(new FileReader("data/reserva.txt"))) {
            ArrayList<String> linhas = new ArrayList<>();
            String linha;
            boolean reservaEncontrada = false;
            
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");

                int idReserva = Integer.parseInt(partes[0]);
                int idQuarto = Integer.parseInt(partes[1]);
                String cpfHospede = partes[2];
                double valor = Double.parseDouble(partes[3]);
                String dataEntrada = partes[4];
                String dataSaida = partes[5];
                boolean checkin = Boolean.parseBoolean(partes[6]);
                boolean checkout = Boolean.parseBoolean(partes[7]);

                if (idReservaEdit == idReserva) {
                    reservaEncontrada = true;
                    System.out.println("Digite o novo ID do quarto(valor atual: " + idQuarto + ") :" );
                    int novoIdQuarto = Integer.parseInt(scanner.nextLine());

                    System.out.println("Digite o novo cpf do hospede:(valor atual: " + cpfHospede + ") :" );
                    String novoCpfHospede = scanner.nextLine();

                    System.out.println("Digite o novo valor (valor atual: " + valor + ") :" );
                    double novoValor = Double.parseDouble(scanner.nextLine());

                    System.out.println("Digite a nova data de entrada:(valor atual: " + dataEntrada + ") :" );
                    String novaDataEntrada = scanner.nextLine();

                    System.out.println("Digite a nova data de saída:(valor atual: " + dataSaida + ") :" );
                    String novaDataSaida = scanner.nextLine();

                    System.out.println("checkin( true / false ) :" );
                    boolean novoCheckin = Boolean.parseBoolean(scanner.nextLine());

                    System.out.println("checkout( true / false ) :" );
                    boolean novoCheckout = Boolean.parseBoolean(scanner.nextLine());

                    linhas.add(idReserva + ";" + novoIdQuarto + ";" + novoCpfHospede + ";" + novoValor + ";" + novaDataEntrada + ";" + novaDataSaida + ";" + novoCheckin + ";" + novoCheckout);
                    
                } else {
                    linhas.add(linha);
                }
            }

            if (!reservaEncontrada) {
                return false;
            }

            try (BufferedWriter bw = new BufferedWriter(new FileWriter("data/reserva.txt"))) {
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
    
    public static boolean deletar(int idReservaDeletar) {
        try (BufferedReader br = new BufferedReader(new FileReader("data/reserva.txt"))) {
            ArrayList<String> linhas = new ArrayList<>();
            String linha;
            boolean reservaEncontrada = false;

            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");
                int idReserva = Integer.parseInt(partes[0]);

                if (idReserva == idReservaDeletar) {
                    reservaEncontrada = true;
                    continue;
                } else {
                    linhas.add(linha);
                }
            }

            if (!reservaEncontrada) {
                return false;
            }

            try (BufferedWriter bw = new BufferedWriter(new FileWriter("data/reserva.txt"))) {
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

    public static Reserva criarReserva(Scanner sc) {
        int ultimoID = 0;
        String linha;

        try (BufferedReader br = new BufferedReader(new FileReader("data/reserva.txt"))) {
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");
                ultimoID = Integer.parseInt(partes[0]);
            }
        } catch (IOException e) {
            System.out.println("Arquivo não encontrado, será criado.");
        }

        int idReserva = ultimoID + 1;

        System.out.println("Digite o ID do quarto:");
        int idQuarto = Integer.parseInt(sc.nextLine());
        Quarto quarto = Quarto.consultar(idQuarto);

        if (quarto == null) {
            System.out.println("Não foi possível encontrar um quarto com este ID. \nTente novamente.");

            return null;
        }

        System.out.println("Digite o cpf do hóspede:");
        String cpfHospede = sc.nextLine();
        Hospede hospede = Hospede.buscarPorCpf(cpfHospede);

        if (hospede == null) {
            System.out.println("Não foi possível encontrar um Hospede com este cpf. \nTente novamente.");
            
            return null;
        }

        System.out.print("Digite o valor da reserva: ");
        double valor = Double.parseDouble(sc.nextLine());

        System.out.print("Digite a data de entrada (DD/MM/AAAA): ");
        String dataEntrada = sc.nextLine();

        System.out.print("Digite a data de saída (DD/MM/AAAA): ");
        String dataSaida = sc.nextLine();

        return new Reserva(idReserva, quarto, hospede, valor, dataEntrada, dataSaida, false, false);
    }

    @Override
    public String toString() {
        return "Reserva ID: " + idReserva + " | Quarto ID: " + quarto.getIdQuarto() + 
               " | Hospede CPF: " + hospede.getCpf() + 
               " | Valor: " + valor + 
               " | Entrada: " + dataEntrada + 
               " | Saída: " + dataSaida + 
               " | Check-in: " + checkin + 
               " | Check-out: " + checkout;
    }
}
