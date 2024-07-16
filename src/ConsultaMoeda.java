import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaMoeda {
    private static final String[] SUPPORTED_CURRENCIES = {"ARS", "BOB", "BRL", "CLP", "COP", "USD"};

    public Moeda verificarMoeda(String moedaBase, float valor) {
        if (!isValidCurrency(moedaBase)) {
            throw new RuntimeException("Moeda não suportada");
        }

        URI uri = URI.create("https://v6.exchangerate-api.com/v6/898cdb46ead4d50235374779/latest/" + moedaBase);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .build();

        try {
            HttpResponse<String> response = HttpClient
                    .newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofString());
            Moeda moeda = new Gson().fromJson(response.body(), Moeda.class);
            return converterMoeda(moeda, valor);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isValidCurrency(String currencyCode) {
        for (String supportedCurrency : SUPPORTED_CURRENCIES) {
            if (supportedCurrency.equals(currencyCode)) {
                return true;
            }
        }
        return false;
    }

    private Moeda converterMoeda(Moeda moeda, float valor) {
        Moeda resultado = new Moeda();
        resultado.setBase_code(moeda.getBase_code());
        Rates rates = moeda.getConversion_rates();
        Rates resultadoRates = new Rates();

        switch (moeda.getBase_code()) {
            case "USD":
                resultado.setValor(valor);
                resultadoRates.setUSD(valor);
                resultadoRates.setEUR(valor * rates.getEUR());
                resultadoRates.setBRL(valor * rates.getBRL());
                resultadoRates.setARS(valor * rates.getARS());
                break;
            case "EUR":
                resultado.setValor(valor / rates.getEUR());
                resultadoRates.setUSD(valor / rates.getEUR());
                resultadoRates.setEUR(valor);
                resultadoRates.setBRL(valor * rates.getBRL() / rates.getEUR());
                resultadoRates.setARS(valor * rates.getARS() / rates.getEUR());
                break;
            case "BRL":
                resultado.setValor(valor / rates.getBRL());
                resultadoRates.setUSD(valor / rates.getBRL());
                resultadoRates.setEUR(valor / rates.getBRL() * rates.getEUR());
                resultadoRates.setBRL(valor);
                resultadoRates.setARS(valor * rates.getARS() / rates.getBRL());
                break;
            case "ARS":
                resultado.setValor(valor / rates.getARS());
                resultadoRates.setUSD(valor / rates.getARS());
                resultadoRates.setEUR(valor / rates.getARS() * rates.getEUR());
                resultadoRates.setBRL(valor / rates.getARS() * rates.getBRL());
                resultadoRates.setARS(valor);
                break;
        }

        resultado.setConversion_rates(resultadoRates);
        return resultado;
    }


}