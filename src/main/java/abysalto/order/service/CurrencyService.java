package abysalto.order.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

@Service
public class CurrencyService {

    private final RestTemplate restTemplate = new RestTemplate();
    private static final String BASE_CURRENCY = "EUR";
    private static final String FRANKFURTER_URL = "https://api.frankfurter.app/latest?from=EUR";

    public BigDecimal convertFromEuro(BigDecimal amount, String targetCurrency) {

        if(amount == null) {
            return BigDecimal.ZERO;
        }

        if(targetCurrency == null || targetCurrency.equalsIgnoreCase(BASE_CURRENCY)) {
            return amount;
        }

        Map<String, Object> response = restTemplate.getForObject(FRANKFURTER_URL, Map.class);

        if(response == null || response.get("rates") == null) {
            throw new RuntimeException("Currency conversion service unavailable");
        }

        Map<String, Double> rates = (Map<String, Double>) response.get("rates");

        Double rate = rates.get(targetCurrency.toUpperCase());

        if(rate == null) {
            throw new IllegalArgumentException("Unsupported currency: " + targetCurrency);
        }

        return amount.multiply(BigDecimal.valueOf(rate)).setScale(2, RoundingMode.HALF_UP);
    }

}
