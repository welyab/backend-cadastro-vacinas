package dev.welyab.ufma.ecp.devweb.cadastrovacinas.core.services

import dev.welyab.ufma.ecp.devweb.cadastrovacinas.core.entities.Applicator
import org.springframework.data.domain.Page

interface ApplicatorService {

    fun findApplicators(pageNumber: Int, pageSize: Int): Page<Applicator>

    fun findApplicator(id: String): Applicator

    fun deleteApplicator(id: String): Applicator

    fun saveApplicator(applicator: Applicator): Applicator

    fun updateApplicator(applicator: Applicator)
}
