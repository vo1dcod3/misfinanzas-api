package cl.manuel.misfinanzas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * Centraliza el manejo de errores de la API: convierte excepciones en respuestas HTTP uniformes
 * ({@link ErrorResponseDTO}), evitando duplicar try/catch en cada controlador.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /** Atrapa las fallas de validación de {@code @Valid} y devuelve 400 con el detalle por campo. */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> manejarValidacion(MethodArgumentNotValidException ex) {
        Map<String, String> errores = new HashMap<>();
        ex.getBindingResult()
                .getFieldErrors()
                .forEach(e -> errores.put(e.getField(), e.getDefaultMessage()));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponseDTO(400, "Validacion fallida", errores));
    }

}
