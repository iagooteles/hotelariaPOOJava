public class Hospede extends Pessoa {
    private String rg;
    private boolean fidelidade;

    public Hospede(String cpf, String nome, int idade, String rg, boolean fidelidade) {
        super(cpf, nome, idade);
        this.rg = rg;
        this.fidelidade = fidelidade;
    }
}
