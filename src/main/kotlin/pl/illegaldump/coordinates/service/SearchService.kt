package pl.illegaldump.coordinates.service

import pl.illegaldump.coordinates.domain.Coordinate

interface SearchService {
    /**
     * Get coordinates based on center and area size and zoom
     */
    fun getCoordinates(center: Coordinate, zoom: Int, size: Int): List<Coordinate>
}