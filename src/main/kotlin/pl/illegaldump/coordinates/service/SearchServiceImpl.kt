package pl.illegaldump.coordinates.service

import io.quarkus.logging.Log
import pl.illegaldump.coordinates.dao.CoordinatesDao
import pl.illegaldump.coordinates.domain.Coordinate
import pl.illegaldump.coordinates.extenstion.toCoordinate
import pl.illegaldump.coordinates.util.CoordinateUtil

class SearchServiceImpl(
    private val coordinatesDao: CoordinatesDao
) : SearchService {

    override fun getCoordinates(center: Coordinate, zoom: Int, size: Int): List<Coordinate> {
        Log.debug("Searching coordinates for center: ${center}, zoom: ${zoom}, size: ${size}")
        val boundary = CoordinateUtil.getBoundary(center, zoom, size)
        return coordinatesDao.search(boundary).map { it.toCoordinate() }
    }
}