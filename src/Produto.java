import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Produto {
    private int idProduto;
    private String descProduto;
    private double valor;

    public Produto(int idProduto, String descProduto, double valor) {
        this.idProduto = idProduto;
        this.descProduto = descProduto;
        this.valor = valor;
    }

    public int getIdProduto() {
        return this.idProduto;
    }

    private String toLinha() {
        return this.idProduto + ";" + this.descProduto + ";" + this.valor;
    }

    public boolean inserir() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("data/produto.txt", true))) {
            bw.write(toLinha());
            bw.newLine();

            return true;
        } catch (IOException e) {
            System.out.println("Erro ao inserir: " + e.getMessage());

            return false;
        }
    }

    public static ArrayList<Produto> listar() {
        try (BufferedReader br = new BufferedReader(new FileReader("data/produto.txt"))) {
            
            ArrayList<Produto> produtos = new ArrayList<>();
            String linha;

            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");
                int idProduto = Integer.parseInt(partes[0]);
                String descProduto = partes[1];
                double valor = Double.parseDouble(partes[2]);

                produtos.add(new Produto(idProduto, descProduto, valor));
            }

            return produtos;
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado.");
            e.getMessage();

            return null;
        } catch (IOException e) {
            e.getMessage();

            return null;
        }
    }

    public static Produto consultar(int id) {
        try (BufferedReader br = new BufferedReader(new FileReader("data/produto.txt"))) {
            Produto produto = null;
            String linha;
            boolean produtoEncontrado = false;
            
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");

                int idProduto = Integer.parseInt(partes[0]);
                String descProduto = partes[1];
                double valor = Double.parseDouble(partes[2]);

                if (idProduto == id) {
                    produtoEncontrado = true;
                    produto = new Produto(idProduto, descProduto, valor);
                } 
            }
            
            if (!produtoEncontrado) {
                return null;
            }

            return produto;
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado.");
            e.getMessage();

            return null;
        } catch (IOException e) {
            e.getMessage();

            return null;
        }
    }

    public static boolean editar(int idProdutoEdit, Scanner scanner) {
        try (BufferedReader br = new BufferedReader(new FileReader("data/produto.txt"))) {
            ArrayList<String> linhas = new ArrayList<>();
            String linha;
            boolean produtoEncontrado = false;
            
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");

                int idProduto = Integer.parseInt(partes[0]);
                String descProduto = partes[1];
                double valor = Double.parseDouble(partes[2]);

                if (idProduto == idProdutoEdit) {
                    produtoEncontrado = true;
                    System.out.println("Digite a nova descrição do produto(valor atual: " + descProduto + ") :" );
                    String novoDescProduto = scanner.nextLine();
                    System.out.println("Digite o novo valor do produto:(valor atual: " + valor + ") :" );
                    String novoValorProduto = scanner.nextLine();

                    linhas.add(idProduto + ";" + novoDescProduto + ";" + novoValorProduto);
                    
                } else {
                    linhas.add(linha);
                }
            }

            if (!produtoEncontrado) {
                return false;
            }

            try (BufferedWriter bw = new BufferedWriter(new FileWriter("data/produto.txt"))) {
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

    public static boolean deletar(int idProdutoDeletar) {
        try (BufferedReader br = new BufferedReader(new FileReader("data/produto.txt"))) {
            ArrayList<String> linhas = new ArrayList<>();
            String linha;
            boolean produtoEncontrado = false;

            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");
                int idProduto = Integer.parseInt(partes[0]);

                if (idProduto == idProdutoDeletar) {
                    produtoEncontrado = true;
                    continue;
                } else {
                    linhas.add(linha);
                }
            }

            if (!produtoEncontrado) {
                return false;
            }

            try (BufferedWriter bw = new BufferedWriter(new FileWriter("data/produto.txt"))) {
                for (String l : linhas) {
                    bw.write(l);
                    bw.newLine();
                }

                return true;
            } catch (IOException e) {
                System.out.println("Erro ao salvar as alterações: " + e.getMessage());
                return false;
            }

        }catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado.");
            e.getMessage();

            return false;
        } catch (IOException e) {
            e.getMessage();

            return false;
        }
    }

    public static Produto criarProduto(Scanner sc) {
        int ultimoID = 0;
        String linha;

        try (BufferedReader br = new BufferedReader(new FileReader("data/produto.txt"))) { 
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");
                ultimoID = Integer.parseInt(partes[0]);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado.");
            e.getMessage();
        } catch (IOException e) {
            e.getMessage();
        }

        int idProduto = ultimoID + 1;
        
        System.out.print("Digite a descrição do produto: ");
        String descProduto = sc.nextLine();
        
        System.out.print("Digite o valor do produto: ");
        double valor = Double.parseDouble(sc.nextLine());
        
        return new Produto(idProduto, descProduto, valor);
    }

    @Override
    public String toString() {
        return this.idProduto + " | " + this.descProduto + " | valor: " + this.valor;
    }
}
