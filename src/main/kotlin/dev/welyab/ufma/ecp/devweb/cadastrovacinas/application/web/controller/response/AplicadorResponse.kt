package dev.welyab.ufma.ecp.devweb.cadastrovacinas.application.web.controller.response

import com.fasterxml.jackson.annotation.JsonProperty
import dev.welyab.ufma.ecp.devweb.cadastrovacinas.core.entities.Applicator
import org.springframework.hateoas.RepresentationModel

data class AplicadorResponse(
    val id: String,
    val nome: String,
    val cpf: Long,
    @JsonProperty("registro_coren")
    val registroCoren: String
) : RepresentationModel<AplicadorResponse>()

fun Applicator.toAplicadorResponse() = AplicadorResponse(
    id = id,
    nome = name,
    cpf = cpf,
    registroCoren = coren
)
