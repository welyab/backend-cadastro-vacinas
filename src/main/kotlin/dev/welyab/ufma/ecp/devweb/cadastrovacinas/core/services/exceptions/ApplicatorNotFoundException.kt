package dev.welyab.ufma.ecp.devweb.cadastrovacinas.core.services.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

class ApplicatorNotFoundException(
    applicatorId: String,
    status: HttpStatus = HttpStatus.NOT_FOUND,
    reason: String = "Applicator with ID = $applicatorId not found",
    cause: Throwable? = null
) : ResponseStatusException(
    status,
    reason,
    cause
)
