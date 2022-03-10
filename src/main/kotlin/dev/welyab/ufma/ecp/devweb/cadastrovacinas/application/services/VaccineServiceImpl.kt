package dev.welyab.ufma.ecp.devweb.cadastrovacinas.application.services

import dev.welyab.ufma.ecp.devweb.cadastrovacinas.application.repositories.VaccineRepository
import dev.welyab.ufma.ecp.devweb.cadastrovacinas.application.repositories.tables.VacinaTable
import dev.welyab.ufma.ecp.devweb.cadastrovacinas.application.repositories.tables.toVaccineTable
import dev.welyab.ufma.ecp.devweb.cadastrovacinas.application.repositories.tables.toVaccine
import dev.welyab.ufma.ecp.devweb.cadastrovacinas.core.entities.Vaccine
import dev.welyab.ufma.ecp.devweb.cadastrovacinas.core.services.VaccineService
import dev.welyab.ufma.ecp.devweb.cadastrovacinas.core.services.exceptions.VaccineNotFoundException
import java.util.UUID
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class VaccineServiceImpl(
    @Autowired
    private val vaccineRepository: VaccineRepository
) : VaccineService {

    override fun findVaccines(
        pageNumber: Int,
        pageSize: Int
    ) = vaccineRepository
        .findAll(PageRequest.of(pageNumber, pageSize, Sort.by(VacinaTable::name.name)))
        .map { it.toVaccine() }

    override fun findVaccine(id: String): Vaccine =
        vaccineRepository
            .findById(id)
            .map { it.toVaccine() }
            .orElseThrow { VaccineNotFoundException(id) }

    override fun deleteVaccine(
        id: String
    ): Vaccine = findVaccine(id)
        .apply { vaccineRepository.deleteById(id) }

    override fun saveVaccine(
        vaccine: Vaccine
    ) = vaccine.copy(id = UUID.randomUUID().toString())
        .apply { vaccineRepository.save(this.toVaccineTable()) }

    override fun updateVaccine(
        vaccine: Vaccine
    ): Unit = vaccine.toVaccineTable()
        .let { vaccineRepository.save(it) }
}
