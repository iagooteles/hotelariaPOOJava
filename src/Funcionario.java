public class Funcionario extends Pessoa {
    private String funcao;

    public Funcionario(String cpf, String nome, int idade, String funcao) {
        super(cpf, nome, idade);
        this.funcao = funcao;
    }
    
}
