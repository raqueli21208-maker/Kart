import java.util.Random;
import java.util.Scanner;

public class Kart {

    static Scanner entrada = new Scanner(System.in);
    static Random random = new Random();

    public static void main(String[] args) {

        System.out.print("Digite o nome do piloto: ");
        String nome = entrada.nextLine();

        int habilidade = 50;
        int pontos = 0;
        int saldo = 500;
        int corridas = 0;
        int opcao;

        int[] historico = new int[5];
        int quantidade = 0;

        do {

            opcao = exibirMenu();

            switch (opcao) {

                case 1:
                    mostrarPiloto(nome, habilidade, pontos,
                            saldo, corridas);
                    break;

                case 2:

                    if (saldo >= 200 && habilidade < 100) {

                        saldo -= 200;

                        int resultado = random.nextInt(10) + 1;

                        habilidade = treinar(habilidade, resultado);

                        System.out.println("Resultado do treino: " + resultado);
                        System.out.println("Habilidade atual: " + habilidade);

                    } else {
                        System.out.println("Nao e possivel treinar!");
                    }

                    break;

                case 3:

                    System.out.println("Escolha a estrategia:");
                    System.out.println("1 - Economica");
                    System.out.println("2 - Equilibrada");
                    System.out.println("3 - Agressiva");

                    int estrategia = entrada.nextInt();
                    int bonus = 0;

                    if (estrategia == 1) {
                        bonus = 0;
                    } else if (estrategia == 2) {
                        bonus = 10;
                    } else if (estrategia == 3) {
                        bonus = 20;
                    } else {
                        System.out.println("Estrategia invalida!");
                        break;
                    }

                    int sorte = random.nextInt(11);

                    int desempenho = calcularDesempenho(
                            habilidade, bonus, sorte
                    );

                    int pontosCorrida = calcularPontos(desempenho);
                    int premio = calcularPremio(desempenho);

                    pontos += pontosCorrida;
                    saldo += premio;
                    corridas++;

                    if (quantidade < 5) {
                        historico[quantidade] = desempenho;
                        quantidade++;
                    } else {
                        for (int i = 0; i < 4; i++) {
                            historico[i] = historico[i + 1];
                        }

                        historico[4] = desempenho;
                    }

                    System.out.println("\n===== RESULTADO DA CORRIDA =====");
                    System.out.println("Desempenho: " + desempenho);
                    System.out.println("Pontos ganhos: " + pontosCorrida);
                    System.out.println("Premio: R$ " + premio);
                    System.out.println("Sorte: " + sorte);

                    break;

                case 4:

                    System.out.println("\n===== RESULTADOS =====");
                    System.out.println("Corridas: " + corridas);
                    System.out.println("Total de pontos: " + pontos);
                    System.out.println("Saldo: R$ " + saldo);

                    break;

                case 0:
                    System.out.println("Programa encerrado!");
                    break;

                default:
                    System.out.println("Opcao invalida!");

            }

        } while (opcao != 0);

        entrada.close();
    }

    public static int exibirMenu() {

        System.out.println("\n===== CAMPEONATO DE KART =====");
        System.out.println("1 - Visualizar piloto");
        System.out.println("2 - Treinar");
        System.out.println("3 - Participar de corrida");
        System.out.println("4 - Consultar resultados");
        System.out.println("0 - Sair");
        System.out.print("Escolha: ");

        return entrada.nextInt();
    }

    public static void mostrarPiloto(
            String nome, int habilidade, int pontos,
            int saldo, int corridas
    ) {

        System.out.println("\n===== DADOS DO PILOTO =====");
        System.out.println("Nome: " + nome);
        System.out.println("Habilidade: " + habilidade);
        System.out.println("Pontos: " + pontos);
        System.out.println("Saldo: R$ " + saldo);
        System.out.println("Corridas: " + corridas);
    }

    public static int treinar(int habilidade, int resultado) {

        if (resultado >= 6) {

            habilidade += 10;

            if (habilidade > 100) {
                habilidade = 100;
            }

            System.out.println("Treinamento realizado com sucesso!");

        } else {

            System.out.println("Treinamento sem sucesso!");

        }

        return habilidade;
    }

    public static int calcularDesempenho(
            int habilidade, int bonus, int sorte
    ) {

        return habilidade + bonus + sorte;
    }

    public static int calcularPontos(int desempenho) {

        if (desempenho >= 90) {
            return 30;
        } else if (desempenho >= 75) {
            return 20;
        } else if (desempenho >= 60) {
            return 10;
        } else {
            return 5;
        }
    }

    public static int calcularPremio(int desempenho) {

        if (desempenho >= 90) {
            return 1000;
        } else if (desempenho >= 75) {
            return 700;
        } else if (desempenho >= 60) {
            return 400;
        } else {
            return 200;
        }
    }
}
