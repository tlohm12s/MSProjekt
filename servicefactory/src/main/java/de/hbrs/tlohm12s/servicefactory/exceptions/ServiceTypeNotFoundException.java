package de.hbrs.tlohm12s.servicefactory.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "The given ServiceType does not Exist.")
public class ServiceTypeNotFoundException extends RuntimeException {
}
