import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Hospede extends Pessoa {
    private String rg;
    private boolean fidelidade;

    public Hospede(String cpf, String nome, int idade, String rg) {
        super(cpf, nome, idade);
        this.rg = rg;
        this.fidelidade = false;
    }

    public Hospede(String cpf, String nome, int idade, String rg, boolean fidelidade) {
        super(cpf, nome, idade);
        this.rg = rg;
        this.fidelidade = fidelidade;
    }

    @Override
    protected String getNomeArquivotxt() {
        return "data/hospede.txt";
    }

    @Override
    protected String toLinha() {
        return cpf + ";" + nome + ";" + idade + ";" + rg + ";" + fidelidade;
    }

    @Override
    protected Pessoa fromLinha(String linha) {
        String[] partes = linha.split(";");
        if (partes.length == 5) {
            return new Hospede(partes[0], partes[1], Integer.parseInt(partes[2]), partes[3],
                    Boolean.parseBoolean(partes[4]));
        }

        return null;
    }

    public static Hospede criarHospede(Scanner sc) {
        System.out.println("=== Cadastro de Hospede ===");
        System.out.print("Digite o CPF: ");
        String cpf = sc.nextLine();

        System.out.print("Digite o Nome: ");
        String nome = sc.nextLine();

        System.out.print("Digite a Idade: ");
        int idade = Integer.parseInt(sc.nextLine());

        System.out.print("Digite o RG: ");
        String rg = sc.nextLine();

        return new Hospede(cpf, nome, idade, rg);
    }

    public static Hospede buscarPorCpf(String cpfBuscado) {
        Hospede exemplo = new Hospede("", "", 0, "");
        ArrayList<Pessoa> pessoas = exemplo.listar(); 

        for (Pessoa pessoa : pessoas) {
            Hospede hospede = (Hospede) pessoa;
            if (hospede.getCpf().equals(cpfBuscado)) {
                return hospede;
            }
        }

        return null;
    }

    @Override
    public String toString() {
        return super.toString() + " | Função: " + this.rg + " | Fidelidade: " + this.fidelidade;
    }

    public static boolean editar(String cpfEdit, Scanner scanner) {
        ArrayList<String> linhas = new ArrayList<>();
        boolean hospedeEncontrado = false;

        try (BufferedReader br = new BufferedReader(new FileReader("data/hospede.txt"))) {
            String linha;

            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");

                String cpf = partes[0];
                String nome = partes[1];
                int idade = Integer.parseInt(partes[2]);
                String rg = partes[3];
                boolean fidelidade = Boolean.parseBoolean(partes[4]);

                if (cpf.equals(cpfEdit)) {
                    hospedeEncontrado = true;
                    System.out.println("=== Editando Hóspede CPF: " + cpf + " ===");

                    System.out.print("Novo nome (atual: " + nome + "): ");
                    String novoNome = scanner.nextLine();

                    System.out.print("Nova idade (atual: " + idade + "): ");
                    int novaIdade = Integer.parseInt(scanner.nextLine());

                    System.out.print("Novo RG (atual: " + rg + "): ");
                    String novoRg = scanner.nextLine();

                    System.out.print("Participa do programa de fidelidade? (atual: " + fidelidade + ") (true/false): ");
                    boolean novoFidelidade = Boolean.parseBoolean(scanner.nextLine());

                    String novaLinha = cpf + ";" + novoNome + ";" + novaIdade + ";" + novoRg + ";" + novoFidelidade;
                    linhas.add(novaLinha);
                } else {
                    linhas.add(linha);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo: " + e.getMessage());
            return false;
        }

        if (!hospedeEncontrado) {
            System.out.println("Hóspede não encontrado.");
            return false;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("data/hospede.txt"))) {
            for (String l : linhas) {
                bw.write(l);
                bw.newLine();
            }

            return true;
        } catch (IOException e) {
            System.out.println("Erro ao salvar arquivo: " + e.getMessage());
            return false;
        }
    }
}
