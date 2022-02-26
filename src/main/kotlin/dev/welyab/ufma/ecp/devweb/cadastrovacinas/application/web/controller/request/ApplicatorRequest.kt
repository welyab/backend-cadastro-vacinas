package dev.welyab.ufma.ecp.devweb.cadastrovacinas.application.web.controller.request

import dev.welyab.ufma.ecp.devweb.cadastrovacinas.core.entities.Applicator

data class ApplicatorRequest(
    val nome: String,
    val cpf: Long,
    val registroCoren: String
)

fun ApplicatorRequest.toAplicador() = Applicator(
    id = "",
    name = nome,
    cpf = cpf,
    coren = registroCoren
)
