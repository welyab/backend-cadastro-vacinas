package dev.welyab.ufma.ecp.devweb.cadastrovacinas.core.entities

data class Applicator(
    val id: String,
    val name: String,
    val cpf: Long,
    val coren: String
)
