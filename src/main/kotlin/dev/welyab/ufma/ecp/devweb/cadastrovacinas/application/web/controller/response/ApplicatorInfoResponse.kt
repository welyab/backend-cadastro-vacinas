package dev.welyab.ufma.ecp.devweb.cadastrovacinas.application.web.controller.response

import com.fasterxml.jackson.annotation.JsonProperty
import dev.welyab.ufma.ecp.devweb.cadastrovacinas.core.entities.ApplicatorInfo

data class ApplicatorInfoResponse(
    @JsonProperty("cpf")
    val cpf: Long,
    @JsonProperty("name")
    val name: String,
    @JsonProperty("coren")
    val coren: String
)

fun ApplicatorInfo.toApplicatorInfoResponse() = ApplicatorInfoResponse(
    cpf = cpf,
    name = name,
    coren = coren
)