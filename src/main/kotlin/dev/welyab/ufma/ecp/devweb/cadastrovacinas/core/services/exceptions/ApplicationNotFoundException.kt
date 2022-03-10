package dev.welyab.ufma.ecp.devweb.cadastrovacinas.core.services.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

class ApplicationNotFoundException(
    status: HttpStatus = HttpStatus.NOT_FOUND,
    reason: String = "",
    cause: Throwable? = null
) : ResponseStatusException(
    status,
    reason,
    cause
)
