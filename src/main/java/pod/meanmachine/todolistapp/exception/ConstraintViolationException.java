package pod.meanmachine.todolistapp.exception;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Builder
public class ConstraintViolationException extends RuntimeException{
    private final HttpStatus status;
    private final String message;
}
