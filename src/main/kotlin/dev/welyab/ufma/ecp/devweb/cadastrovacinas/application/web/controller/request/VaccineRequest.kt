package dev.welyab.ufma.ecp.devweb.cadastrovacinas.application.web.controller.request

import com.fasterxml.jackson.annotation.JsonProperty
import dev.welyab.ufma.ecp.devweb.cadastrovacinas.core.entities.Vaccine

data class VaccineRequest(
    @JsonProperty("name")
    val name: String,
    @JsonProperty("doses")
    val doses: Int,
    @JsonProperty("batch-number")
    val batchNumber: String
)

fun VaccineRequest.toVaccine() = Vaccine(
    id = "",
    name = name,
    doses = doses,
    batchNumber = batchNumber
)
