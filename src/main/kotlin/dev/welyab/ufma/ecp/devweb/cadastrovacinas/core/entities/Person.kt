package dev.welyab.ufma.ecp.devweb.cadastrovacinas.core.entities

import java.time.LocalDate

data class Person(
    val name: String,
    val cpf: Long,
    val birthDate: LocalDate
)
