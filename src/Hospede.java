public class Hospede extends Pessoa {
    private String rg;
    private boolean fidelidade;

    public Hospede(String cpf, String nome, int idade, String rg, boolean fidelidade) {
        super(cpf, nome, idade);
        this.rg = rg;
        this.fidelidade = fidelidade;
    }

    @Override
    protected String getNomeArquivotxt() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getNomeArquivotxt'");
    }

    @Override
    protected String toLinha() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toLinha'");
    }

    @Override
    protected Pessoa fromLinha(String linha) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'fromLinha'");
    }
}
