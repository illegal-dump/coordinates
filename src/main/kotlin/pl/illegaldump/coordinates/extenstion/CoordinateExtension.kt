package pl.illegaldump.coordinates.extenstion

import pl.illegaldump.coordinates.api.dto.CoordinateDto
import pl.illegaldump.coordinates.domain.Coordinate
import pl.illegaldump.coordinates.entity.CoordinateEntity

fun Coordinate.toCoordinateDto(): CoordinateDto = CoordinateDto(lat.toBigDecimal(), lng.toBigDecimal(), label)

fun CoordinateEntity.toCoordinate(): Coordinate = Coordinate(lat, lng, label ?: "-")