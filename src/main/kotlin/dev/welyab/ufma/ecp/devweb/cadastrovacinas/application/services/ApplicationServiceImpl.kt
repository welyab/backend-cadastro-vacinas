package dev.welyab.ufma.ecp.devweb.cadastrovacinas.application.services

import dev.welyab.ufma.ecp.devweb.cadastrovacinas.application.repositories.ApplicationRepository
import dev.welyab.ufma.ecp.devweb.cadastrovacinas.application.repositories.ApplicatorRepository
import dev.welyab.ufma.ecp.devweb.cadastrovacinas.application.repositories.VaccineRepository
import dev.welyab.ufma.ecp.devweb.cadastrovacinas.application.repositories.tables.createApplicationTable
import dev.welyab.ufma.ecp.devweb.cadastrovacinas.application.repositories.tables.toApplicationInfo
import dev.welyab.ufma.ecp.devweb.cadastrovacinas.core.entities.ApplicationInfo
import dev.welyab.ufma.ecp.devweb.cadastrovacinas.core.entities.Applicator
import dev.welyab.ufma.ecp.devweb.cadastrovacinas.core.entities.Person
import dev.welyab.ufma.ecp.devweb.cadastrovacinas.core.entities.Vaccine
import dev.welyab.ufma.ecp.devweb.cadastrovacinas.core.services.ApplicationService
import dev.welyab.ufma.ecp.devweb.cadastrovacinas.core.services.exceptions.DuplicatedVaccineApplicationException
import dev.welyab.ufma.ecp.devweb.cadastrovacinas.core.services.exceptions.ApplicationNotFoundException
import dev.welyab.ufma.ecp.devweb.cadastrovacinas.core.services.exceptions.ApplicatorNotFoundException
import dev.welyab.ufma.ecp.devweb.cadastrovacinas.core.services.exceptions.VaccineNotFoundException
import java.time.LocalDate
import java.util.UUID
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ApplicationServiceImpl(
    @Autowired
    private val applicationRepository: ApplicationRepository,
    @Autowired
    private val applicatorRepository: ApplicatorRepository,
    @Autowired
    private val vaccineRepository: VaccineRepository
) : ApplicationService {

    override fun findApplication(id: String): ApplicationInfo =
        applicationRepository.findById(id)
            .map { it.toApplicationInfo() }
            .orElseThrow { ApplicationNotFoundException() }

    override fun findApplication(
        personCpf: Long,
        applicationDate: LocalDate
    ): ApplicationInfo = applicationRepository
        .findByPersonCpfAndApplicationDate(personCpf, applicationDate)
        .map { it.toApplicationInfo() }
        .orElseThrow { ApplicationNotFoundException() }

    override fun saveApplication(
        person: Person,
        applicator: Applicator,
        vaccine: Vaccine,
        applicationDate: LocalDate
    ): ApplicationInfo {
        return updateApplication(
            UUID.randomUUID().toString(),
            person,
            applicator,
            vaccine,
            applicationDate
        )
    }

    override fun updateApplication(
        id: String,
        person: Person,
        applicator: Applicator,
        vaccine: Vaccine,
        applicationDate: LocalDate
    ): ApplicationInfo {
        if (!applicatorRepository.existsById(applicator.id)) throw ApplicatorNotFoundException(applicator.id)
        if (!vaccineRepository.existsById(vaccine.id)) throw VaccineNotFoundException(vaccine.id)

        if (applicationRepository.existsByPersonCpfAndApplicationDate(
                person.cpf,
                applicationDate
            )
        ) throw DuplicatedVaccineApplicationException()

        return applicationRepository.save(
            createApplicationTable(
                id,
                person,
                applicator,
                vaccine,
                applicationDate
            )
        ).toApplicationInfo()
    }

    override fun findApplications(personCpf: Long) =
        applicationRepository
            .findByPersonCpf(personCpf)
            .map { it.toApplicationInfo() }
}
