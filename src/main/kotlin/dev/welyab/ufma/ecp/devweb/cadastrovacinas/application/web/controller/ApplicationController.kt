package dev.welyab.ufma.ecp.devweb.cadastrovacinas.application.web.controller

import dev.welyab.ufma.ecp.devweb.cadastrovacinas.application.web.controller.request.VaccineApplicationRequest
import dev.welyab.ufma.ecp.devweb.cadastrovacinas.application.web.controller.request.toRegistroPessoa
import dev.welyab.ufma.ecp.devweb.cadastrovacinas.application.web.controller.response.toApplicationResponse
import dev.welyab.ufma.ecp.devweb.cadastrovacinas.core.services.ApplicationService
import dev.welyab.ufma.ecp.devweb.cadastrovacinas.core.services.ApplicatorService
import dev.welyab.ufma.ecp.devweb.cadastrovacinas.core.services.VaccineService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("applications")
class ApplicationController(
    @Autowired
    private val applicatorService: ApplicatorService,
    @Autowired
    private val vaccineService: VaccineService,
    @Autowired
    private val applicationService: ApplicationService
) {

    @PostMapping(path = ["persons"])
    fun saveApplication(
        @RequestBody
        applicationRequest: VaccineApplicationRequest
    ) = applicationService.saveApplication(
        person = applicationRequest.person.toRegistroPessoa(),
        vaccine = vaccineService.findVaccine(applicationRequest.vaccineId),
        applicator = applicatorService.findApplicator(applicationRequest.applicatorId),
        applicationDate = applicationRequest.applicationDate
    ).let { ok(it.toApplicationResponse()) }

    @GetMapping(path = ["persons/{cpf}"])
    fun listApplications(
        @PathVariable cpf: Long
    ) = applicationService
        .findApplications(cpf)
        .map { it.toApplicationResponse() }
        .let { ok(it) }

    @PutMapping(path = ["/{id}"])
    fun updateApplication(
        @PathVariable
        id: String,
        @RequestBody
        applicationRequest: VaccineApplicationRequest
    ) = applicationService.updateApplication(
        id = id,
        person = applicationRequest.person.toRegistroPessoa(),
        vaccine = vaccineService.findVaccine(applicationRequest.vaccineId),
        applicator = applicatorService.findApplicator(applicationRequest.applicatorId),
        applicationDate = applicationRequest.applicationDate
    ).let { ok(it.toApplicationResponse()) }
}
