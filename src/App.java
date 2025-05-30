import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        Menu menu = new Menu();
        boolean runApp = true;

        while (runApp) {
            menu.mostrarMenu();
            int op = scanner.nextInt();
            scanner.nextLine();

            switch (op) {
                case 1:
                    System.out.println("Nothing here.");
                    break;

                case 0:
                    System.out.println("Até logo.");
                    System.out.println("Fechando o Aplicativo.");
                    runApp = false;
                    break;
            
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
    }
}
