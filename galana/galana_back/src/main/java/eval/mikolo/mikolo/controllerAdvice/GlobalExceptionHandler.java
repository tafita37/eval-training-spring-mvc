package eval.mikolo.mikolo.controllerAdvice;

import java.util.Hashtable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Hashtable<String, Object>> handleException(Exception ex) {
        Hashtable<String, Object> result = new Hashtable<>();
        result.put("message", ex.getMessage().replaceAll("JSON parse error: ", ""));
        int status=HttpStatus.INTERNAL_SERVER_ERROR.value();
        ex.printStackTrace();
        return ResponseEntity.status(status).body(result);
    }
}