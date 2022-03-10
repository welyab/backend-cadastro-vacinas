package dev.welyab.ufma.ecp.devweb.cadastrovacinas.core.services.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

class VaccineNotFoundException(
    vaccineId: String,
    status: HttpStatus = HttpStatus.INTERNAL_SERVER_ERROR,
    reason: String = "Vaccine with ID = $vaccineId was not found",
    cause: Throwable? = null
) : ResponseStatusException(
    status,
    reason,
    cause
)
