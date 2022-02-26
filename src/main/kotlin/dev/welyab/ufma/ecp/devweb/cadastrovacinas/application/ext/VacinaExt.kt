package dev.welyab.ufma.ecp.devweb.cadastrovacinas.application.ext

import dev.welyab.ufma.ecp.devweb.cadastrovacinas.core.entities.VaccineInfo
import dev.welyab.ufma.ecp.devweb.cadastrovacinas.core.entities.Vaccine

fun Vaccine.toVaccineInfo() = VaccineInfo(
    name = name,
    batchNumber = batchNumber,
)
