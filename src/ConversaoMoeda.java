import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.ToNumberPolicy;
import excecoes.ErroContatoServidorException;
import excecoes.ErroConversaoMoedaException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ConversaoMoeda {
    private String moedaInicial;
    private String moedaFinal;
    private String dataConversao; // timestamp
    private Double valorInicial;
    private Double valorFinal;

    public ConversaoMoeda() {

    };

    public ConversaoMoeda(String moedaInicial, String moedaFinal, String dataConversao, Double valorInicial, Double valorFinal) {
        this.moedaInicial = moedaInicial;
        this.moedaFinal = moedaFinal;
        this.dataConversao = dataConversao;
        this.valorInicial = valorInicial;
        this.valorFinal = valorFinal;
    }

    public ConversaoMoeda ConverterMoeda(String moedaInicial, String moedaFinal, Double valor) throws RuntimeException {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://v6.exchangerate-api.com/v6/bce2ef9be7f03b04add646a9/latest/" + moedaInicial))
                .build();

        try{
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            TaxaConversaoMoedas moedas = gson.fromJson(response.body(), TaxaConversaoMoedas.class);

            DecimalFormat df = new DecimalFormat("0.000");
            return new ConversaoMoeda(moedaInicial, moedaFinal, LocalDateTime.now().toString(), valor, valor * (Double) moedas.conversion_rates().get(moedaFinal));

        } catch (IOException | InterruptedException e) {
            throw new ErroContatoServidorException("Um erro com o servidor foi encontrado. Tente novamente mais tarde.\n" + e.getMessage());
        } catch (ClassCastException e) {
            throw new ErroConversaoMoedaException("Erro ao converter moeda. Valor recebido inválido. Tente novamente mais tarde.\n" + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Um erro desconhecido foi encontrado. Tente novamente mais tarde.\n" + e.getMessage());
        }
    }

    @Override
    public String toString() {
        return moedaInicial + " " + valorInicial + " -> " + moedaFinal + " " + valorFinal;
    }
}
