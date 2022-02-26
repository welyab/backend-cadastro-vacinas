package dev.welyab.ufma.ecp.devweb.cadastrovacinas.application.web.controller.request

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDate

data class AtuailzarAplicacaoVacinaRequest(
    @JsonProperty("pessoa")
    val pessoa: PersonRequest,
    @JsonProperty("id_aplicador")
    val idAplicador: String,
    @JsonProperty("id_vacina")
    val idVacina: String,
    @JsonProperty("dt_aplic_vacina")
    val dataAplicacao: LocalDate
)
