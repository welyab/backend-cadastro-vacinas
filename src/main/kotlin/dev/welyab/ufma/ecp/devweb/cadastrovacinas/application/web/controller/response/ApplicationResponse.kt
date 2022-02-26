package dev.welyab.ufma.ecp.devweb.cadastrovacinas.application.web.controller.response

import com.fasterxml.jackson.annotation.JsonProperty
import dev.welyab.ufma.ecp.devweb.cadastrovacinas.core.entities.ApplicationInfo
import java.time.LocalDate

data class ApplicationResponse(
    @JsonProperty("id")
    val id: String,
    @JsonProperty("person")
    val person: PersonResponse,
    @JsonProperty("applicator_info")
    val applicator: ApplicatorInfoResponse,
    @JsonProperty("vaccine_info")
    val vaccine: VaccineInfoResponse,
    @JsonProperty("application_date")
    val applicationDate: LocalDate
)

fun ApplicationInfo.toApplicationResponse() = ApplicationResponse(
    id = id,
    person = person.toPersonResponse(),
    applicator = applicator.toApplicatorInfoResponse(),
    vaccine = vaccine.toVaccineInfoResponse(),
    applicationDate = applicationDate
)
