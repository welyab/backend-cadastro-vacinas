package dev.welyab.ufma.ecp.devweb.cadastrovacinas.application.web.controller.request

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDate

data class VaccineApplicationRequest(
    @JsonProperty("person")
    val person: PersonRequest,
    @JsonProperty("applicator_id")
    val applicatorId: String,
    @JsonProperty("vaccine_id")
    val vaccineId: String,
    @JsonProperty("application_dt")
    val applicationDate: LocalDate
)
