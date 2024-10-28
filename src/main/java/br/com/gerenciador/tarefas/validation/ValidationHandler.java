package br.com.gerenciador.tarefas.validation;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.ConstraintViolation;
import lombok.SneakyThrows;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@ControllerAdvice
public class ValidationHandler {

    private final MessageSource messageSource;

    public ValidationHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidations(MethodArgumentNotValidException exception) {

        List<Map<String, String>> listErrors = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> {
                    Map<String, String> errors = new HashMap<>();
                    errors.put("field", getPropertyName(error));
                    errors.put("description", messageSource.getMessage(error, Locale.forLanguageTag("pt-BR")));
                    return errors;
                })
                .toList();

        ErrorResponse response = ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.toString())
                .errors(listErrors)
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @SneakyThrows
    private String getPropertyName(final FieldError error) {

        if (error.contains(ConstraintViolation.class)) {

            final ConstraintViolation<?> violation = error.unwrap(ConstraintViolation.class);
            final Field field = violation.getRootBeanClass().getDeclaredField(error.getField());
            final JsonProperty jsonProperty = field.getAnnotation(JsonProperty.class);

            if (jsonProperty != null && !jsonProperty.value().isEmpty()) {
                return jsonProperty.value();
            }
        }
        return error.getField();
    }
}
