package pl.illegaldump.coordinates.extenstion

import pl.illegaldump.coordinates.api.dto.CoordinateDto
import pl.illegaldump.coordinates.domain.Coordinate

fun Coordinate.toCoordinateDto(): CoordinateDto = CoordinateDto(lat.toBigDecimal(), lng.toBigDecimal(), label)