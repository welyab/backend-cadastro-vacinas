package dev.welyab.ufma.ecp.devweb.cadastrovacinas.application.web.controller.response

import dev.welyab.ufma.ecp.devweb.cadastrovacinas.core.entities.Vaccine
import org.springframework.hateoas.RepresentationModel

data class VaccineResponse(
    val id: String,
    val nome: String,
    val doses: Int,
    val batchNumber: String
) : RepresentationModel<VaccineResponse>()

fun Vaccine.toVaccineResponse() = VaccineResponse(
    id,
    name,
    doses,
    batchNumber
)
