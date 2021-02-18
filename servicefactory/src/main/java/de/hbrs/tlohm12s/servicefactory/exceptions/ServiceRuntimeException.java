package de.hbrs.tlohm12s.servicefactory.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "A Service Instance could not be maintained, read the server logs for more details.")
public class ServiceRuntimeException extends RuntimeException {
}
