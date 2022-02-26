package dev.welyab.ufma.ecp.devweb.cadastrovacinas.application.web.controller.request

import com.fasterxml.jackson.annotation.JsonProperty

data class AtualizarAplicadorRequest(
    @JsonProperty("name")
    val nome: String,
    @JsonProperty("cpf")
    val cpf: Long,
    @JsonProperty("coren")
    val coren: String
)
