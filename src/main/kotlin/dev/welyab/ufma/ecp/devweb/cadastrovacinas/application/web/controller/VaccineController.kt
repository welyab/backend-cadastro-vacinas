package dev.welyab.ufma.ecp.devweb.cadastrovacinas.application.web.controller

import dev.welyab.ufma.ecp.devweb.cadastrovacinas.application.web.controller.ApiDocTags.TAG_VACCINE
import dev.welyab.ufma.ecp.devweb.cadastrovacinas.application.web.controller.request.VaccineRequest
import dev.welyab.ufma.ecp.devweb.cadastrovacinas.application.web.controller.request.toVaccine
import dev.welyab.ufma.ecp.devweb.cadastrovacinas.application.web.controller.response.VaccineListResponse
import dev.welyab.ufma.ecp.devweb.cadastrovacinas.application.web.controller.response.VaccineResponse
import dev.welyab.ufma.ecp.devweb.cadastrovacinas.application.web.controller.response.toVaccineResponse
import dev.welyab.ufma.ecp.devweb.cadastrovacinas.core.services.VaccineService
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
@RequestMapping("vaccines")
class VaccineController(
    @Autowired
    private val vaccineService: VaccineService
) {

    @Operation(
        summary = "Updates vaccine information",
        tags = [TAG_VACCINE]
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "Vaccine updated successfully",
                content = [
                    Content(
                        mediaType = APPLICATION_JSON_VALUE,
                        schema = Schema(implementation = VaccineResponse::class)
                    )
                ]
            ),
            ApiResponse(
                responseCode = "404",
                description = "Vaccine not found"
            )
        ]
    )
    @PutMapping(path = ["{id}"])
    fun updateVaccine(
        @Parameter(description = "Vaccine ID")
        @PathVariable id: String,
        @RequestBody vacinaRequest: VaccineRequest
    ) = vaccineService
        .findVaccine(id)
        .copy(
            name = vacinaRequest.name,
            batchNumber = vacinaRequest.batchNumber,
            doses = vacinaRequest.doses
        )
        .apply { vaccineService.updateVaccine(this) }
        .toVaccineResponse()
        .applyHateoas()
        .let { ok(it) }

    @Operation(
        summary = "Register vaccine",
        tags = [TAG_VACCINE]
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "Vaccine registered successfully",
                content = [
                    Content(
                        mediaType = APPLICATION_JSON_VALUE,
                        schema = Schema(implementation = VaccineResponse::class)
                    )
                ]
            )
        ]
    )
    @PostMapping
    fun saveVaccine(
        @RequestBody vaccineRequest: VaccineRequest
    ) = vaccineService
        .saveVaccine(vaccineRequest.toVaccine())
        .toVaccineResponse()
        .applyHateoas()
        .let { ok(it) }

    @Operation(
        summary = "Find vaccines",
        tags = [TAG_VACCINE]
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "Vaccines listed successfully",
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
    fun findVaccines(
        @Parameter(description = "Page number (0-indexed)")
        @RequestParam(required = false, defaultValue = "-1")
        pageNumber: Int,
        @Parameter(description = "Page size")
        @RequestParam(required = false, defaultValue = "0")
        pageSize: Int
    ) = vaccineService
        .findVaccines(
            max(pageNumber, 0),
            min(pageSize.takeIf { it >= 1 } ?: MAX_PAGE_SIZE, MAX_PAGE_SIZE)
        )
        .map { it.toVaccineResponse().applyHateoas() }
        .let {
            VaccineListResponse(it.content).applyHateoas(it)
        }
        .let { ok(it) }

    @Operation(
        summary = "Find vaccine information",
        tags = [TAG_VACCINE]
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "Vaccine found successfully",
                content = [
                    Content(
                        mediaType = APPLICATION_JSON_VALUE,
                        schema = Schema(implementation = VaccineResponse::class)
                    )
                ]
            ),
            ApiResponse(
                responseCode = "404",
                description = "Vaccine not found"
            )
        ]
    )
    @GetMapping(path = ["{id}"])
    fun findVaccine(
        @Parameter(description = "The ID vaccine")
        @PathVariable id: String
    ) = vaccineService
        .findVaccine(id)
        .toVaccineResponse()
        .applyHateoas()
        .let { ok(it) }

    @Operation(
        summary = "Deletes vaccine",
        tags = [TAG_VACCINE]
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "Vaccine deleted successfully",
                content = [
                    Content(
                        mediaType = APPLICATION_JSON_VALUE,
                        schema = Schema(implementation = VaccineResponse::class)
                    )
                ]
            ),
            ApiResponse(
                responseCode = "204",
                description = "No vaccine to delete"
            )
        ]
    )
    @DeleteMapping(path = ["{id}"])
    fun removerVacina(
        @Parameter(description = "The vaccine's ID")
        @PathVariable id: String
    ) = vaccineService
        .deleteVaccine(id)
        .toVaccineResponse()
        .let { ok(it) }

    private fun VaccineResponse.applyHateoas() =
        add(linkTo(VaccineController::class.java).slash(id).withSelfRel())

    private fun VaccineListResponse.applyHateoas(page: Page<VaccineResponse>): VaccineListResponse {
        addIf(page.hasPrevious()) {
            page.previousPageable().let {
                linkTo(
                    methodOn(VaccineController::class.java).findVaccines(it.pageNumber, it.pageSize)
                ).withRel(
                    IanaLinkRelations.PREVIOUS
                )
            }
        }
        add(
            linkTo(methodOn(VaccineController::class.java).findVaccines(page.number, page.size)).withRel(
                IanaLinkRelations.SELF
            )
        )
        addIf(page.hasNext()) {
            page.nextPageable().let {
                linkTo(methodOn(VaccineController::class.java).findVaccines(it.pageNumber, it.pageSize)).withRel(
                    IanaLinkRelations.NEXT
                )
            }
        }
        return this
    }
}
