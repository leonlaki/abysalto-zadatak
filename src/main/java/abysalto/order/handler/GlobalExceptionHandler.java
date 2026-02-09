package abysalto.order.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CurrencyNotSupportedException.class)
    public ResponseEntity<?> handleCurrencyNotSupported(CurrencyNotSupportedException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("timestamp", LocalDateTime.now(),
                "status", 400, "error", "Bad request", "message", exception.getMessage()));
    }

}
