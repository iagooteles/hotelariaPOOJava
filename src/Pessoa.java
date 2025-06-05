import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public abstract class Pessoa {
    protected String cpf;
    protected String nome;
    protected int idade;

    public Pessoa(String cpf, String nome, int idade) {
        this.cpf = cpf;
        this.nome = nome;
        this.idade = idade;
    }

    public String getCpf() {
        return this.cpf;
    }

    protected abstract String getNomeArquivotxt();
    protected abstract String toLinha();
    protected abstract Pessoa fromLinha(String linha);

    public boolean inserir() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(getNomeArquivotxt(), true))) {
            bw.write(toLinha());
            bw.newLine();

            return true;
        } catch (IOException e) {
            System.out.println("Erro ao inserir: " + e.getMessage());

            return false;
        }
    }

    public Pessoa consultar(String cpfConsultado) {
        try (BufferedReader br = new BufferedReader(new FileReader(getNomeArquivotxt()))) {
            String linha;

            while ((linha = br.readLine()) != null) {
                Pessoa pessoa = fromLinha(linha);
                if (pessoa != null && pessoa.getCpf().equals(cpfConsultado)) {
                    return pessoa;
                }
            }

            return null;
        } catch (IOException e) {
            System.out.println("Erro ao consultar: " + e.getMessage());
            return null;
        }
    }

    public ArrayList<Pessoa> listar() {
        ArrayList<Pessoa> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(getNomeArquivotxt()))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                Pessoa p = fromLinha(linha);
                if (p != null) {
                    lista.add(p);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado.");
            e.getMessage();

        } catch (IOException e) {
            System.out.println("Erro ao listar: " + e.getMessage());
        }

        return lista;
    }

    public boolean deletar(String cpf) {
        ArrayList<Pessoa> lista = listar();
        boolean encontrado = false;

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).cpf.equals(cpf)) {
                lista.remove(i);
                encontrado = true;
                break;
            }
        }

        if (encontrado) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(getNomeArquivotxt()))) {
                for (Pessoa p : lista) {
                    bw.write(p.toLinha());
                    bw.newLine();
                }
                return true;
            } catch (IOException e) {
                System.out.println("Erro ao salvar arquivo: " + e.getMessage());
                return false;
            }
        }

        return false;
    }

    @Override
    public String toString() {
        return this.nome + " | cpf: " + this.cpf + " | idade: " + this.idade;
    }
}
