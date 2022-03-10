package dev.welyab.ufma.ecp.devweb.cadastrovacinas.core.services.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

class DuplicatedVaccineApplicationException(
    status: HttpStatus = HttpStatus.CONFLICT,
    reason: String = "Vaccine application already registered to that use in informed day",
    cause: Throwable? = null
) : ResponseStatusException(
    status,
    reason,
    cause
)
