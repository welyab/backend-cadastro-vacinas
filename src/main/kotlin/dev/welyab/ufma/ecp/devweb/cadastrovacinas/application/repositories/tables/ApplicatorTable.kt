package dev.welyab.ufma.ecp.devweb.cadastrovacinas.application.repositories.tables

import dev.welyab.ufma.ecp.devweb.cadastrovacinas.core.entities.Applicator
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "applicator")
data class ApplicatorTable(
    @Id
    @Column(name = "id")
    val id: String,
    @Column(name = "name")
    val name: String,
    @Column(name = "cpf")
    val cpf: Long,
    @Column(name = "coren")
    val coren: String
)

fun ApplicatorTable.toApplicator() = Applicator(
    id = id,
    name = name,
    cpf = cpf,
    coren = coren
)

fun Applicator.toApplicatorTable() = ApplicatorTable(
    id = id,
    name = name,
    cpf = cpf,
    coren = coren
)
