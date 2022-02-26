package dev.welyab.ufma.ecp.devweb.cadastrovacinas.application.repositories.tables

import dev.welyab.ufma.ecp.devweb.cadastrovacinas.core.entities.Vaccine
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "vaccine")
data class VacinaTable(
    @Id
    @Column(name = "id")
    val id: String,
    @Column(name = "name")
    val name: String,
    @Column(name = "doses")
    val doses: Int,
    @Column(name = "batch_number")
    val batchNumber: String
)

fun VacinaTable.toVaccine() = Vaccine(
    id = id,
    name = name,
    doses = doses,
    batchNumber = batchNumber
)

fun Vaccine.toVaccineTable() = VacinaTable(
    id = id,
    name = name,
    batchNumber = batchNumber,
    doses = doses
)
