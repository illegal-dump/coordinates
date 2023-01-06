package pl.illegaldump.coordinates.service

import pl.illegaldump.coordinates.domain.Coordinate

interface SearchService {
    /**
     * Get coordinates based on center and area size
     */
    fun getCoordinates(center: Coordinate, size: Int) : List<Coordinate>
}