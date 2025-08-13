import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static ConversaoMoeda menuMoedas() {
        Scanner input = new Scanner(System.in);

        System.out.println("""
                ------------ MOEDAS ------------
                USD - Dólar americano
                EUR - Euro
                JPY - Yen japonês
                GBP - Libra esterlina
                CNY - Yuan chinês
                BRL - Real brasileiro
                RUB - Rublo russo
                AED - Dirham dos Emirados Árabes Unidos
                COP - Peso colombiano
                ARS - Peso argentino (inconsistente)
                
                Insira o código da moeda inicial (a ser convertida).
                --------------------------------""");

        String moedaInicial = input.nextLine().toUpperCase();
        System.out.println("Insira a quantia a ser convertida: ");
        double quantiaMoeda = input.nextDouble();
        input.nextLine();

        System.out.println("""
                ------------ MOEDAS ------------
                USD - Dólar americano
                EUR - Euro
                JPY - Yen japonês
                GBP - Libra esterlina
                CNY - Yuan chinês
                BRL - Real brasileiro
                RUB - Rublo russo
                AED - Dirham dos Emirados Árabes Unidos
                COP - Peso colombiano
                ARS - Peso argentino (inconsistente)
                
                Insira o código da moeda final (conversão).
                --------------------------------""");
        String moedaFinal = input.nextLine().toUpperCase();
        ConversaoMoeda conversorMoeda = new ConversaoMoeda();
        return conversorMoeda.ConverterMoeda(moedaInicial, moedaFinal, quantiaMoeda);
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        SalvarLog salvarLog = new SalvarLog();
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        List<ConversaoMoeda> historicoConversao = new ArrayList<>();
        int menuConversor = 0;

        while (menuConversor != 3) {
            System.out.println(
                    """
                            ------------------- CONVERSOR DE MOEDAS -------------------
                            Bem-vindo(a) ao conversor de moedas.
                            1. Iniciar
                            2. Histórico de conversão
                            3. Sair.
                            Escolha uma opção válida:
                            -----------------------------------------------------------""");
            menuConversor = input.nextInt();
            if (menuConversor == 3) {
                salvarLog.salvarArquivo(gson.toJson(historicoConversao));
                System.out.println("Obrigado por usar o conversor de moedas.\nSaindo...");
                break;
            }

            switch (menuConversor) {
                case 1:
                    ConversaoMoeda conversao = menuMoedas();
                    System.out.println(conversao.toString());
                    historicoConversao.add(conversao);
                    break;
                case 2:
                    for (ConversaoMoeda s : historicoConversao) {
                        System.out.println(historicoConversao.indexOf(s)+1 + " - " + s.toString());
                    }
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
            try {
                for (int i = 0; i < 4; i++) {
                    Thread.sleep(750);
                    System.out.print(".");
                }
                System.out.println("\n");
            } catch (Exception e) {
                System.out.println("Erro durante a execução do programa." + e.getMessage());
            }
        }
    }
}