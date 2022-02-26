package dev.welyab.ufma.ecp.devweb.cadastrovacinas.application.web.controller.response

import dev.welyab.ufma.ecp.devweb.cadastrovacinas.core.entities.VaccineInfo

data class VaccineInfoResponse(
    val name: String,
    val batchNumber: String
)

fun VaccineInfo.toVaccineInfoResponse() = VaccineInfoResponse(
    name = name,
    batchNumber = batchNumber
)
