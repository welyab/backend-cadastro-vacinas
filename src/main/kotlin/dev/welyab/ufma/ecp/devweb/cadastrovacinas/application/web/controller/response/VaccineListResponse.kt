package dev.welyab.ufma.ecp.devweb.cadastrovacinas.application.web.controller.response

import org.springframework.hateoas.RepresentationModel

data class VaccineListResponse(val vacians: List<VaccineResponse>) : RepresentationModel<VaccineListResponse>()
