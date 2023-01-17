package pl.illegaldump.coordinates.domain

/**
 *
 * @see [link](https://en.wikipedia.org/wiki/Geographic_coordinate_system)
 */
data class Coordinate(
    /**
     * Latitude
     */
    val lat: Double,

    /**
     * Longitude
     */
    val lng: Double,

    /**
     * Label
     */
    val label: String = "-",
) {


}