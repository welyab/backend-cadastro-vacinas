package dev.welyab.ufma.ecp.devweb.cadastrovacinas.application.web.controller.response

import com.fasterxml.jackson.annotation.JsonProperty
import dev.welyab.ufma.ecp.devweb.cadastrovacinas.core.entities.Person
import java.time.LocalDate

data class PersonResponse(
    @JsonProperty("cpf")
    val cpf: Long,
    @JsonProperty("name")
    val name: String,
    @JsonProperty("birth_date")
    val birthDate: LocalDate
)

fun Person.toPersonResponse() = PersonResponse(
    cpf = cpf,
    name = name,
    birthDate = birthDate
)
