package pl.illegaldump.coordinates.tests.util

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import pl.illegaldump.coordinates.domain.Coordinate
import pl.illegaldump.coordinates.domain.Direction
import pl.illegaldump.coordinates.util.CoordinateUtil

class CoordinateUtilTest {

    @Test
    fun `check coordinates calculation`() {
        val coordinate = Coordinate(lat = 50.23694871652907, lng = 19.01825272895186)
        val calculated = CoordinateUtil.calculate(coordinate, 100.0, Direction.EAST.engle)
        Assertions.assertEquals(50.2369487080087, calculated.lat)
        Assertions.assertEquals(19.019661918135856, calculated.lng)
    }

    @Test
    fun `check boundaries calculation`() {
        val center = Coordinate(lat = 50.23694871652907, lng = 19.01825272895186)
        val pixels = 300


        val (south, north, west, east) = CoordinateUtil.getBoundary(center, 9, pixels)
        Assertions.assertEquals(49.410202176071394, south)
        Assertions.assertEquals(51.06369525698675, north)
        Assertions.assertEquals(17.725811458173524, west)
        Assertions.assertEquals(20.31069399973014, east)

        val zero = CoordinateUtil.getBoundary(center, 0, pixels)
        println(zero)
    }

}