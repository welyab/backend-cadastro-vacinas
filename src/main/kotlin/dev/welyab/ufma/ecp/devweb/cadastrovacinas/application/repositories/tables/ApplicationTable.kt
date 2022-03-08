package dev.welyab.ufma.ecp.devweb.cadastrovacinas.application.repositories.tables

import dev.welyab.ufma.ecp.devweb.cadastrovacinas.core.entities.ApplicatorInfo
import dev.welyab.ufma.ecp.devweb.cadastrovacinas.core.entities.Person
import dev.welyab.ufma.ecp.devweb.cadastrovacinas.core.entities.VaccineInfo
import dev.welyab.ufma.ecp.devweb.cadastrovacinas.core.entities.ApplicationInfo
import dev.welyab.ufma.ecp.devweb.cadastrovacinas.core.entities.Applicator
import dev.welyab.ufma.ecp.devweb.cadastrovacinas.core.entities.Vaccine
import java.time.LocalDate
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "application")
data class ApplicationTable(
    @Id
    @Column(name = "id")
    val id: String,
    @Column(name = "person_name")
    val personName: String,
    @Column(name = "person_cpf")
    val personCpf: Long,
    @Column(name = "person_birth_date")
    val personBirthDate: LocalDate,
    @Column(name = "applicator_name")
    val applicatorName: String,
    @Column(name = "applicator_cpf")
    val applicatorCpf: Long,
    @Column(name = "coren")
    val coren: String,
    @Column(name = "vaccine_name")
    val vaccineName: String,
    @Column(name = "batch_number")
    val batchNumber: String,
    @Column(name = "application_date")
    val applicationDate: LocalDate
)

fun ApplicationTable.toApplicationInfo() = ApplicationInfo(
    id = id,
    person = Person(
        name = personName,
        cpf = personCpf,
        birthDate = personBirthDate
    ),
    applicator = ApplicatorInfo(
        cpf = applicatorCpf,
        name = applicatorName,
        coren = coren
    ),
    vaccine = VaccineInfo(
        name = vaccineName,
        batchNumber = batchNumber
    ),
    applicationDate = applicationDate
)

fun createApplicationTable(
    id: String,
    person: Person,
    applicator: Applicator,
    vaccine: Vaccine,
    applicationDate: LocalDate
) = ApplicationTable(
    id = id,
    personName = person.name,
    personCpf = person.cpf,
    personBirthDate = person.birthDate,
    applicatorName = applicator.name,
    applicatorCpf = applicator.cpf,
    coren = applicator.coren,
    vaccineName = vaccine.name,
    batchNumber = vaccine.batchNumber,
    applicationDate = applicationDate
)
