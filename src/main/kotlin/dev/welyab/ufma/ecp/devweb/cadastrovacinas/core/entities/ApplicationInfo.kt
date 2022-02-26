package dev.welyab.ufma.ecp.devweb.cadastrovacinas.core.entities

import java.time.LocalDate

data class ApplicationInfo(
    val id: String,
    val person: Person,
    val applicator: ApplicatorInfo,
    val vaccine: VaccineInfo,
    val applicationDate: LocalDate
)
