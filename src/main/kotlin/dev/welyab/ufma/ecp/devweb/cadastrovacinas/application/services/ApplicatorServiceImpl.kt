package dev.welyab.ufma.ecp.devweb.cadastrovacinas.application.services

import dev.welyab.ufma.ecp.devweb.cadastrovacinas.application.repositories.ApplicatorRepository
import dev.welyab.ufma.ecp.devweb.cadastrovacinas.application.repositories.tables.ApplicatorTable
import dev.welyab.ufma.ecp.devweb.cadastrovacinas.application.repositories.tables.toApplicator
import dev.welyab.ufma.ecp.devweb.cadastrovacinas.application.repositories.tables.toApplicatorTable
import dev.welyab.ufma.ecp.devweb.cadastrovacinas.core.entities.Applicator
import dev.welyab.ufma.ecp.devweb.cadastrovacinas.core.services.ApplicatorService
import dev.welyab.ufma.ecp.devweb.cadastrovacinas.core.services.exceptions.ApplicatorNotFoundException
import java.util.UUID
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class ApplicatorServiceImpl(
    @Autowired
    private val applicatorRepository: ApplicatorRepository
) : ApplicatorService {

    override fun findApplicators(
        pageNumber: Int,
        pageSize: Int
    ) = applicatorRepository
        .findAll(PageRequest.of(pageNumber, pageSize, Sort.by(ApplicatorTable::name.name)))
        .map { it.toApplicator() }

    override fun findApplicator(id: String): Applicator =
        applicatorRepository
            .findById(id)
            .map { it.toApplicator() }
            .orElseThrow { ApplicatorNotFoundException() }

    override fun deleteApplicator(
        id: String
    ) = findApplicator(id)
        .apply { applicatorRepository.deleteById(id) }

    override fun saveApplicator(
        applicator: Applicator
    ) = applicator.copy(id = UUID.randomUUID().toString())
        .apply { applicatorRepository.save(this.toApplicatorTable()) }

    override fun updateApplicator(
        applicator: Applicator
    ): Unit = applicator.toApplicatorTable()
        .let { applicatorRepository.save(it) }
}
