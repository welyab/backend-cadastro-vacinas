package dev.welyab.ufma.ecp.devweb.cadastrovacinas.application.web.controller.request

import dev.welyab.ufma.ecp.devweb.cadastrovacinas.core.entities.Vaccine

data class VaccineRequest(
    val nome: String,
    val doses: Int,
    val batchNumber: String
)

fun VaccineRequest.toVaccine() = Vaccine(
    id = "",
    name = nome,
    doses = doses,
    batchNumber = batchNumber
)
