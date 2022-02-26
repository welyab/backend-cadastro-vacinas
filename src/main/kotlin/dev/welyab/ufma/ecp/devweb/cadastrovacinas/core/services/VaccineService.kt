package dev.welyab.ufma.ecp.devweb.cadastrovacinas.core.services

import dev.welyab.ufma.ecp.devweb.cadastrovacinas.core.entities.Vaccine
import org.springframework.data.domain.Page

interface VaccineService {

    fun findVaccines(pageNumber: Int, pageSize: Int): Page<Vaccine>

    fun findVaccine(id: String): Vaccine

    fun deleteVaccine(id: String): Vaccine

    fun saveVaccine(vaccine: Vaccine): Vaccine

    fun updateVaccine(vaccine: Vaccine)
}
