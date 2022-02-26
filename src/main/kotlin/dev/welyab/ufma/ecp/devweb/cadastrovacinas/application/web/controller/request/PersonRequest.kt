package dev.welyab.ufma.ecp.devweb.cadastrovacinas.application.web.controller.request

import com.fasterxml.jackson.annotation.JsonProperty
import dev.welyab.ufma.ecp.devweb.cadastrovacinas.core.entities.Person
import java.time.LocalDate

data class PersonRequest(
    @JsonProperty("name")
    val name: String,
    @JsonProperty("cpf")
    val cpf: Long,
    @JsonProperty("birth_date")
    val birthDate: LocalDate
)

fun PersonRequest.toRegistroPessoa() = Person(
    name = name,
    cpf = cpf,
    birthDate = birthDate
)
