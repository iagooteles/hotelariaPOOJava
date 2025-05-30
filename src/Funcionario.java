
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
}
