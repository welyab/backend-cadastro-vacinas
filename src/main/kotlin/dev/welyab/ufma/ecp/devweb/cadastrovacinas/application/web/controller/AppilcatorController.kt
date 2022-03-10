package dev.welyab.ufma.ecp.devweb.cadastrovacinas.application.web.controller

import dev.welyab.ufma.ecp.devweb.cadastrovacinas.application.web.controller.ApiDocTags.TAG_APPLICATOR
import dev.welyab.ufma.ecp.devweb.cadastrovacinas.application.web.controller.request.AtualizarAplicadorRequest
import dev.welyab.ufma.ecp.devweb.cadastrovacinas.application.web.controller.request.ApplicatorRequest
import dev.welyab.ufma.ecp.devweb.cadastrovacinas.application.web.controller.request.toAplicador
import dev.welyab.ufma.ecp.devweb.cadastrovacinas.application.web.controller.response.ApplicatorResponse
import dev.welyab.ufma.ecp.devweb.cadastrovacinas.application.web.controller.response.ApplicatorListResponse
import dev.welyab.ufma.ecp.devweb.cadastrovacinas.application.web.controller.response.toAplicadorResponse
import dev.welyab.ufma.ecp.devweb.cadastrovacinas.core.services.ApplicatorService
import dev.welyab.ufma.ecp.devweb.cadastrovacinas.core.services.exceptions.ApplicatorNotFoundException
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import kotlin.math.max
import kotlin.math.min
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.hateoas.IanaLinkRelations
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.http.ResponseEntity.noContent
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("applicators")
class AppilcatorController(
    @Autowired
    private val applicatorService: ApplicatorService
) {

    @Operation(
        summary = "Updates applicator information",
        tags = [TAG_APPLICATOR]
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "Applicator saved successfully",
                content = [
                    Content(
                        mediaType = APPLICATION_JSON_VALUE,
                        schema = Schema(implementation = ApplicatorResponse::class)
                    )
                ]
            ),
            ApiResponse(
                responseCode = "404",
                description = "Applicator not found"
            )
        ]
    )
    @PutMapping(path = ["{id}"])
    fun updateApplicator(
        @Parameter(description = "Applicator's ID")
        @PathVariable id: String,
        @RequestBody applicatorRequest: AtualizarAplicadorRequest
    ) = applicatorService
        .findApplicator(id)
        .copy(
            name = applicatorRequest.nome,
            cpf = applicatorRequest.cpf,
            coren = applicatorRequest.coren
        )
        .apply { applicatorService.updateApplicator(this) }
        .toAplicadorResponse()
        .applyHateoas()
        .let { ok(it) }

    @Operation(
        summary = "Saves an applicator information",
        tags = [TAG_APPLICATOR]
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "Applicator saved successfully",
                content = [
                    Content(
                        mediaType = APPLICATION_JSON_VALUE,
                        schema = Schema(implementation = ApplicatorResponse::class)
                    )
                ]
            )
        ]
    )
    @PostMapping
    fun saveApplicator(
        @RequestBody applicatorRequest: ApplicatorRequest
    ) = applicatorService
        .saveApplicator(applicatorRequest.toAplicador())
        .toAplicadorResponse()
        .applyHateoas()
        .let { ok(it) }

    @Operation(
        summary = "Lists applicators",
        tags = [TAG_APPLICATOR]
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "Applicators listed successfully",
                content = [
                    Content(
                        mediaType = APPLICATION_JSON_VALUE,
                        schema = Schema(implementation = List::class)
                    )
                ]
            )
        ]
    )
    @GetMapping
    fun listarAplicadores(
        @Parameter(description = "Page number - 0 indexed")
        @RequestParam(required = false, defaultValue = "-1")
        pageNumber: Int,
        @Parameter(description = "Page size")
        @RequestParam(required = false, defaultValue = "0")
        pageSize: Int
    ) = applicatorService
        .findApplicators(
            max(pageNumber, 0),
            min(pageSize.takeIf { it >= 1 } ?: MAX_PAGE_SIZE, MAX_PAGE_SIZE)
        )
        .map { it.toAplicadorResponse().applyHateoas() }
        .let { ApplicatorListResponse(it.content).apply { this.applyHateoas(it) } }
        .let { ok(it) }

    @Operation(
        summary = "Searches an applicator",
        tags = [TAG_APPLICATOR]
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "Applicator found successfully",
                content = [
                    Content(
                        mediaType = APPLICATION_JSON_VALUE,
                        schema = Schema(implementation = ApplicatorResponse::class)
                    )
                ]
            ),
            ApiResponse(
                responseCode = "404",
                description = "Applicator not found"
            )
        ]
    )
    @GetMapping(path = ["{id}"])
    fun findApplicator(
        @Parameter(description = "Applicator's ID")
        @PathVariable id: String
    ) = applicatorService
        .findApplicator(id)
        .toAplicadorResponse()
        .applyHateoas()
        .let { ok(it) }

    @Operation(
        summary = "Deletes an applicator informatino",
        tags = [TAG_APPLICATOR]
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "Applicator deleted successfully",
                content = [
                    Content(
                        mediaType = APPLICATION_JSON_VALUE,
                        schema = Schema(implementation = ApplicatorResponse::class)
                    )
                ]
            ),
            ApiResponse(
                responseCode = "204",
                description = "No applicator to be deleted"
            )
        ]
    )
    @DeleteMapping(path = ["{id}"])
    fun removerAplicador(
        @Parameter(description = "Deletes an applicator")
        @PathVariable id: String
    ) = kotlin.runCatching { applicatorService.deleteApplicator(id) }
        .onFailure { if (it !is ApplicatorNotFoundException) throw it }
        .map { it.toAplicadorResponse() }
        .getOrNull()
        ?.let { ok(it) }
        ?: noContent().build()

    private fun ApplicatorResponse.applyHateoas() =
        add(linkTo(AppilcatorController::class.java).slash(id).withSelfRel())

    private fun ApplicatorListResponse.applyHateoas(page: Page<ApplicatorResponse>): ApplicatorListResponse {
        addIf(page.hasPrevious()) {
            page.previousPageable().let {
                linkTo(
                    methodOn(AppilcatorController::class.java).listarAplicadores(it.pageNumber, it.pageSize)
                ).withRel(
                    IanaLinkRelations.PREVIOUS
                )
            }
        }
        add(
            linkTo(methodOn(AppilcatorController::class.java).listarAplicadores(page.number, page.size)).withRel(
                IanaLinkRelations.SELF
            )
        )
        addIf(page.hasNext()) {
            page.nextPageable().let {
                linkTo(
                    methodOn(AppilcatorController::class.java).listarAplicadores(
                        it.pageNumber,
                        it.pageSize
                    )
                ).withRel(
                    IanaLinkRelations.NEXT
                )
            }
        }
        return this
    }
}
