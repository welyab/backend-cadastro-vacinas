package dev.welyab.ufma.ecp.devweb.cadastrovacinas.application.web.controller.response

import org.springframework.hateoas.RepresentationModel

data class ApplicatorListResponse(val vaccines: List<ApplicatorResponse>) : RepresentationModel<ApplicatorListResponse>()
