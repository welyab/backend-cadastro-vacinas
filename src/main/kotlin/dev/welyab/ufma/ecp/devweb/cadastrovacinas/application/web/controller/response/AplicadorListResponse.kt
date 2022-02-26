package dev.welyab.ufma.ecp.devweb.cadastrovacinas.application.web.controller.response

import org.springframework.hateoas.RepresentationModel

data class AplicadorListResponse(val vacians: List<AplicadorResponse>) : RepresentationModel<AplicadorListResponse>()
