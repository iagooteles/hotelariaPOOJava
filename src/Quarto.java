import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Quarto {
    private int idQuarto;
    private String descQuarto;

    public Quarto(int idQuarto, String descQuarto) {
        this.idQuarto = idQuarto;
        this.descQuarto = descQuarto;
    }

    public int getIdQuarto() {
        return this.idQuarto;
    }

    private String toLinha() {
        return this.idQuarto + ";" + this.descQuarto;
    }

    public boolean inserir() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("data/quarto.txt", true))) {
            bw.write(toLinha());
            bw.newLine();
            return true;
        } catch (IOException e) {
            System.out.println("Erro ao inserir: " + e.getMessage());
            return false;
        }
    }

    public static ArrayList<Quarto> listar() {
        ArrayList<Quarto> quartos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("data/quarto.txt"))) {
            String linha;

            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");
                int idQuarto = Integer.parseInt(partes[0]);
                String descQuarto = partes[1];

                quartos.add(new Quarto(idQuarto, descQuarto));
            }

        } catch (FileNotFoundException e) {
            e.getMessage();
        } catch (IOException e) {
            e.getMessage();
        }

        return quartos;
    }

    public static Quarto consultar(int id) {
        try (BufferedReader br = new BufferedReader(new FileReader("data/quarto.txt"))) {
            String linha;

            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");

                int idQuarto = Integer.parseInt(partes[0]);
                String descQuarto = partes[1];

                if (idQuarto == id) {
                    return new Quarto(idQuarto, descQuarto);
                }
            }

            return null;
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado.");
            e.getMessage();
            return null;
        } catch (IOException e) {
            e.getMessage();
            return null;
        } catch (NullPointerException e) {
            e.getMessage();
            return null;
        }
    }

    public static boolean editar(int idQuartoEdit, Scanner scanner) {
        try (BufferedReader br = new BufferedReader(new FileReader("data/quarto.txt"))) {
            ArrayList<String> linhas = new ArrayList<>();
            String linha;
            boolean quartoEncontrado = false;

            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");

                int idQuarto = Integer.parseInt(partes[0]);
                String descQuarto = partes[1];

                if (idQuarto == idQuartoEdit) {
                    quartoEncontrado = true;
                    System.out.println("Digite a nova descrição do quarto (valor atual: " + descQuarto + "):");
                    String novoDescQuarto = scanner.nextLine();

                    linhas.add(idQuarto + ";" + novoDescQuarto);
                } else {
                    linhas.add(linha);
                }
            }

            if (!quartoEncontrado) {
                return false;
            }

            try (BufferedWriter bw = new BufferedWriter(new FileWriter("data/quarto.txt"))) {
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

    public static boolean deletar(int idQuartoDeletar) {
        try (BufferedReader br = new BufferedReader(new FileReader("data/quarto.txt"))) {
            ArrayList<String> linhas = new ArrayList<>();
            String linha;
            boolean quartoEncontrado = false;

            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");
                int idQuarto = Integer.parseInt(partes[0]);

                if (idQuarto == idQuartoDeletar) {
                    quartoEncontrado = true;
                    continue;
                } else {
                    linhas.add(linha);
                }
            }

            if (!quartoEncontrado) {
                return false;
            }

            try (BufferedWriter bw = new BufferedWriter(new FileWriter("data/quarto.txt"))) {
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

    public static Quarto criarQuarto(Scanner sc) {
        int ultimoID = 0;
        String linha;

        try (BufferedReader br = new BufferedReader(new FileReader("data/quarto.txt"))) {
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

        int idQuarto = ultimoID + 1;

        System.out.print("Digite a descrição do quarto: ");
        String descQuarto = sc.nextLine();

        return new Quarto(idQuarto, descQuarto);
    }

    @Override
    public String toString() {
        return this.idQuarto + " | " + this.descQuarto;
    }
 
}
