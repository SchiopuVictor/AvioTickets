package tickets.aviotickets.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleException(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            errors.put(ex.getObjectName(), error.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(FlightNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleException(FlightNotFoundException ex){
        Map<String, String> errors = new HashMap<>();
        errors.put("Flight not found", ex.getMessage());
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(PassagerNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleException(PassagerNotFoundException ex){
        Map<String, String> errors = new HashMap<>();
        errors.put("Passager not found", ex.getMessage());
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(TicketNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleException(TicketNotFoundException ex){
        Map<String, String> errors = new HashMap<>();
        errors.put("Ticket not found", ex.getMessage());
        return ResponseEntity.badRequest().body(errors);
    }


}
