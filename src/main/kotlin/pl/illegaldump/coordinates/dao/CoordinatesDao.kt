package pl.illegaldump.coordinates.dao

import io.quarkus.hibernate.orm.panache.PanacheRepository
import pl.illegaldump.coordinates.domain.Boundary
import pl.illegaldump.coordinates.entity.CoordinateEntity

class CoordinatesDao : PanacheRepository<CoordinateEntity> {

    fun search(boundary: Boundary): List<CoordinateEntity> {
        val list = list(
            "lat >= ?1 and lat <= ?2 and lng >= ?3 and lng <= ?4",
            boundary.south, boundary.north, boundary.west, boundary.east
        )
        return list
    }
}