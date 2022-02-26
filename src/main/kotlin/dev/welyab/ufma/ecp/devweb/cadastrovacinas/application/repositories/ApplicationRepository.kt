package dev.welyab.ufma.ecp.devweb.cadastrovacinas.application.repositories

import dev.welyab.ufma.ecp.devweb.cadastrovacinas.application.repositories.tables.ApplicationTable
import java.time.LocalDate
import java.util.Optional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ApplicationRepository : JpaRepository<ApplicationTable, String> {

    fun findByPersonCpfAndApplicationDate(
        personCpf: Long,
        applicationDate: LocalDate
    ): Optional<ApplicationTable>

    fun findByPersonCpf(personCpf: Long): List<ApplicationTable>

    fun existsByPersonCpfAndApplicationDate(cpf: Long, applicationDate: LocalDate): Boolean
}
