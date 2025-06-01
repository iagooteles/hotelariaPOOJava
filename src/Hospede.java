import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Hospede extends Pessoa {
    private String rg;
    private boolean fidelidade;

    // Perguntar: Posso usar esse construtor?
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

}
