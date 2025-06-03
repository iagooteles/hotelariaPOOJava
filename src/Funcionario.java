
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Funcionario extends Pessoa {
    private String funcao;
    
    public Funcionario(String cpf, String nome, int idade, String funcao) {
        super(cpf, nome, idade);
        this.funcao = funcao;
    }

    @Override
    protected String getNomeArquivotxt() {
        return "data/funcionario.txt";
    }

    @Override
    protected String toLinha() {
        return cpf + ";" + nome + ";" + idade + ";" + funcao;
    }

    @Override
    protected Pessoa fromLinha(String linha) {
        String[] partes = linha.split(";");
        if (partes.length == 4) {
            return new Funcionario(partes[0], partes[1], Integer.parseInt(partes[2]), partes[3]);
        }

        return null;
    }

    public static Funcionario criarFuncionario(Scanner sc) {
        System.out.println("=== Cadastro de Funcionário ===");
        System.out.print("Digite o CPF: ");
        String cpf = sc.nextLine();
        
        System.out.print("Digite o Nome: ");
        String nome = sc.nextLine();
        
        System.out.print("Digite a Idade: ");
        int idade = Integer.parseInt(sc.nextLine());
        
        System.out.print("Digite a Função: ");
        String funcao = sc.nextLine();
        
        return new Funcionario(cpf, nome, idade, funcao);
    }

    @Override
    public String toString() {
        return super.toString() + " | Função: " + this.funcao;
    }

    public static boolean editar(String cpfEdit, Scanner scanner) {
        ArrayList<String> linhas = new ArrayList<>();
        boolean funcionarioEncontrado = false;

        try (BufferedReader br = new BufferedReader(new FileReader("data/funcionario.txt"))) {
            String linha;

            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");

                String cpf = partes[0];
                String nome = partes[1];
                int idade = Integer.parseInt(partes[2]);
                String funcao = partes[3];

                if (cpf.equals(cpfEdit)) {
                    funcionarioEncontrado = true;
                    System.out.println("=== Editando Funcionário CPF: " + cpf + " ===");

                    System.out.print("Novo nome (atual: " + nome + "): ");
                    String novoNome = scanner.nextLine();

                    System.out.print("Nova idade (atual: " + idade + "): ");
                    int novaIdade = Integer.parseInt(scanner.nextLine());

                    System.out.print("Nova função (atual: " + funcao + "): ");
                    String novaFuncao = scanner.nextLine();

                    String novaLinha = cpf + ";" + novoNome + ";" + novaIdade + ";" + novaFuncao;
                    linhas.add(novaLinha);
                } else {
                    linhas.add(linha);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo: " + e.getMessage());
            return false;
        }

        if (!funcionarioEncontrado) {
            System.out.println("Funcionário não encontrado.");
            return false;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("data/funcionario.txt"))) {
            for (String linha : linhas) {
                bw.write(linha);
                bw.newLine();
            }

            return true;
        } catch (IOException e) {
            System.out.println("Erro ao salvar arquivo: " + e.getMessage());
            return false;
        }
    }
}
