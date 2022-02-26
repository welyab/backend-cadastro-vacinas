package dev.welyab.ufma.ecp.devweb.cadastrovacinas.core.services

import dev.welyab.ufma.ecp.devweb.cadastrovacinas.core.entities.ApplicationInfo
import dev.welyab.ufma.ecp.devweb.cadastrovacinas.core.entities.Applicator
import dev.welyab.ufma.ecp.devweb.cadastrovacinas.core.entities.ApplicatorInfo
import dev.welyab.ufma.ecp.devweb.cadastrovacinas.core.entities.Person
import dev.welyab.ufma.ecp.devweb.cadastrovacinas.core.entities.Vaccine
import java.time.LocalDate

interface ApplicationService {

    fun findApplication(id: String): ApplicationInfo

    fun saveApplication(
        person: Person,
        applicator: Applicator,
        vaccine: Vaccine,
        applicationDate: LocalDate
    ): ApplicationInfo

    fun updateApplication(
        id: String,
        person: Person,
        applicator: Applicator,
        vaccine: Vaccine,
        applicationDate: LocalDate
    ): ApplicationInfo

    fun findApplication(personCpf: Long, applicationDate: LocalDate): ApplicationInfo

    fun findApplications(personCpf: Long): List<ApplicationInfo>
}
