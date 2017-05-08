package ua.pp.disik.tt.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by disik on 5/8/17.
 */
@ResponseStatus(HttpStatus.FORBIDDEN)
public class HttpForbiddenException extends RuntimeException {
    public HttpForbiddenException(String message) {
        super(message);
    }
}
