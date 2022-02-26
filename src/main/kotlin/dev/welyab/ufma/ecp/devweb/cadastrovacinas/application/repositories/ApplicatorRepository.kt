package dev.welyab.ufma.ecp.devweb.cadastrovacinas.application.repositories

import dev.welyab.ufma.ecp.devweb.cadastrovacinas.application.repositories.tables.ApplicatorTable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ApplicatorRepository : JpaRepository<ApplicatorTable, String>
