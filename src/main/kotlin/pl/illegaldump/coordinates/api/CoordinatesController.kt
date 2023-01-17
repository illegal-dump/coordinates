package pl.illegaldump.coordinates.api

import pl.illegaldump.coordinates.api.dto.CoordinateRspDto
import pl.illegaldump.coordinates.config.DefaultsProperties
import pl.illegaldump.coordinates.domain.Coordinate
import pl.illegaldump.coordinates.extenstion.toCoordinateDto
import pl.illegaldump.coordinates.service.SearchService
import java.math.BigDecimal
import javax.ws.rs.core.Response
import javax.ws.rs.ext.ExceptionMapper
import javax.ws.rs.ext.Provider


class CoordinatesController(
    private val searchService: SearchService,
    private val defaults: DefaultsProperties
) : CoordinatesApi {

    override fun getCoordinates(lat: BigDecimal?, lng: BigDecimal?, zoom: Int?, size: Int?): CoordinateRspDto {
        val center =
            Coordinate(lat?.toDouble() ?: defaults.center().lat(), lng?.toDouble() ?: defaults.center().lng(), "center")
        val list = searchService.getCoordinates(center, zoom ?: defaults.zoom(), size ?: defaults.size())
        return CoordinateRspDto(list.map { it.toCoordinateDto() })
    }

    @Provider
    class RuntimeExceptionHandler : ExceptionMapper<RuntimeException> {
        override fun toResponse(exception: RuntimeException?): Response {
            return Response.status(422).build()
        }
    }
}