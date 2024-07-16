import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {

        Scanner leitura = new Scanner(System.in);

        System.out.println("============================================\n");
        System.out.println("Seja bem vindo(a) ao Conversor de Moedas!\n");


        System.out.println("Digite o valor: ");
        var valor = leitura.nextFloat();

        System.out.println("Converter de: ");
        System.out.println("1 - Dólar dos Estados Unidos");
        System.out.println("2 - Euro");
        System.out.println("3 - Real");
        System.out.println("4 - Peso argentino");
        System.out.println("5 - Won sul coreano");
        System.out.println("6 - Iene");
        System.out.println("\n");

        System.out.println("Escolha uma opção: ");
        var escolha1 = leitura.nextInt();

        System.out.println("Para: ");
        System.out.println("1 - Dólar dos Estados Unidos");
        System.out.println("2 - Euro");
        System.out.println("3 - Real");
        System.out.println("4 - Peso argentino");
        System.out.println("5 - Won sul coreano");
        System.out.println("6 - Iene");
        System.out.println("\n");

        System.out.println("Escolha uma opção: ");
        var escolha2 = leitura.nextInt();

        ConsultaMoeda consultaMoeda = new ConsultaMoeda();
        Moeda moeda = consultaMoeda.verificarMoeda(getMoedaBase(escolha1), valor);

        System.out.println("Resultado: " + valor + " " + getMoedaBase(escolha1) + " = " + moeda.getValor() + " " + getMoedaBase(escolha2));
    }

    private static String getMoedaBase(int escolha) {
        switch (escolha) {
            case 1:
                return "USD";
            case 2:
                return "EUR";
            case 3:
                return "BRL";
            case 4:
                return "ARS";
            case 5:
                return "KRW";
            case 6:
                return "JPY";
            default:
                return "";
        }
    }
}