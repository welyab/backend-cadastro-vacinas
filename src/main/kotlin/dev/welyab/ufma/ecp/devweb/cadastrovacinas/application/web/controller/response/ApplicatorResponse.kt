package dev.welyab.ufma.ecp.devweb.cadastrovacinas.application.web.controller.response

import com.fasterxml.jackson.annotation.JsonProperty
import dev.welyab.ufma.ecp.devweb.cadastrovacinas.core.entities.Applicator
import org.springframework.hateoas.RepresentationModel

data class ApplicatorResponse(
    @JsonProperty("id")
    val id: String,
    @JsonProperty("name")
    val name: String,
    @JsonProperty("cpf")
    val cpf: Long,
    @JsonProperty("coren")
    val coren: String
) : RepresentationModel<ApplicatorResponse>()

fun Applicator.toAplicadorResponse() = ApplicatorResponse(
    id = id,
    name = name,
    cpf = cpf,
    coren = coren
)
