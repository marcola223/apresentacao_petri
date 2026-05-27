import java.util.Scanner;

public class MainTeste {
    public static void main(String[] args) {
        ArvoreBinaria arvoreBinaria = new ArvoreBinaria();

        Scanner scanner = new Scanner(System.in);
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n===================================");
            System.out.println("       MENU ÁRVORE BINÁRIA");
            System.out.println("===================================");
            System.out.println("1. Inserir Valor(es)");
            System.out.println("2. Remover Valor");
            System.out.println("3. Mostrar Pré-ordem (Raiz -> Filhos)");
            System.out.println("4. Mostrar Em-ordem");
            System.out.println("5. Mostrar Pós-ordem");
            System.out.println("6. Desenhar Árvore Estruturada");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());

                switch (opcao) {
                    case 1:
                        System.out.print("-> Quantos elementos deseja inserir? ");
                        int quantidade = Integer.parseInt(scanner.nextLine());
                        for (int i = 1; i <= quantidade; i++) {
                            System.out.print("   Elemento " + i + ": ");
                            int vInserir = Integer.parseInt(scanner.nextLine());
                            arvoreBinaria.inserir(vInserir);
                        }
                        break;
                    case 2:
                        System.out.print("-> Digite o valor para remover: ");
                        int vRemover = Integer.parseInt(scanner.nextLine());
                        arvoreBinaria.remover(vRemover);
                        break;
                    case 3:
                        arvoreBinaria.percurso("Pre");
                        break;
                    case 4:
                        arvoreBinaria.percurso("Em");
                        break;
                    case 5:
                        arvoreBinaria.percurso("Pos");
                        break;
                    case 6:
                        arvoreBinaria.desenharArvore();
                        break;
                    case 0:
                        System.out.println("Saindo do sistema...");
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: Digite apenas números inteiros.");
            }
        }
        scanner.close();
    }
}