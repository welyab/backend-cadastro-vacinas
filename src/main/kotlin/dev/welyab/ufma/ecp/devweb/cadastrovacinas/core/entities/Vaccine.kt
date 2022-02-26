package dev.welyab.ufma.ecp.devweb.cadastrovacinas.core.entities

data class Vaccine(
    val id: String,
    val name: String,
    val doses: Int,
    val batchNumber: String
)
