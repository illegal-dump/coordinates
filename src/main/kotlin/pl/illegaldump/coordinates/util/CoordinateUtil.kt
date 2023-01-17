package pl.illegaldump.coordinates.util

import pl.illegaldump.coordinates.domain.Boundary
import pl.illegaldump.coordinates.domain.Coordinate
import pl.illegaldump.coordinates.domain.Direction

object CoordinateUtil {
    private const val earthRadius = 6356752.314245
    private const val circumferenceOfEarthMeters = 40075017

    /**
     * Calculate new coordinate distant at angle
     *
     * @param coordinate
     * @param distance: in meters
     * @param angle: clockwise from north
     */
    fun calculate(coordinate: Coordinate, distance: Double, angle: Double): Coordinate {
        val lat = Math.toRadians(coordinate.lat)
        val lng = Math.toRadians(coordinate.lng)
        val angle = Math.toRadians(angle)
        val normalizedDistance = distance / earthRadius
        val phi2 = Math.asin(
            Math.sin(lat) * Math.cos(normalizedDistance) + Math.cos(lat) * Math.sin(normalizedDistance) * Math.cos(angle)
        )
        val lamda2 = lng + Math.atan2(
            Math.sin(angle) * Math.sin(normalizedDistance) * Math.cos(lat),
            Math.cos(normalizedDistance) - Math.sin(lat) * Math.sin(phi2)
        )
        val lambda2H = (lamda2 + 3 * Math.PI) % (2 * Math.PI) - Math.PI
        return Coordinate(Math.toDegrees(phi2), Math.toDegrees(lambda2H))
    }

    /**
     * Calculate boundaries for center, zoom and size
     */
    fun getBoundary(center: Coordinate, zoom: Int, size: Int): Boundary {
        val north = calculate(center, metersPerPixel(zoom) * size, Direction.NORTH.engle)
        val east = calculate(center, metersPerPixel(zoom) * size, Direction.EAST.engle)
        val south = calculate(center, metersPerPixel(zoom) * size, Direction.SOUTH.engle)
        val west = calculate(center, metersPerPixel(zoom) * size, Direction.WEST.engle)
        return Boundary(south.lat, north.lat, west.lng, east.lng)
    }

    private fun metersPerPixel(zoom: Int): Double {
        return circumferenceOfEarthMeters / Math.pow(2.0, (zoom + 8).toDouble());
    }
}