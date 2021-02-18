package de.hbrs.tlohm12s.MSProjekt.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "The Service with the given Application ID was not found.")
public class ServiceNotFoundException extends RuntimeException {
}
