package abysalto.order.handler;

public class CurrencyNotSupportedException extends RuntimeException{

    public CurrencyNotSupportedException(String currency) {
        super("Unsupported currency: " + currency);
    }

}
