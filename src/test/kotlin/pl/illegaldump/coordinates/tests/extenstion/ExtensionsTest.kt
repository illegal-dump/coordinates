package pl.illegaldump.coordinates.tests.extenstion

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import pl.illegaldump.coordinates.api.dto.CoordinateDto
import pl.illegaldump.coordinates.domain.Coordinate
import pl.illegaldump.coordinates.extenstion.toCoordinateDto
import java.math.BigDecimal

class ExtensionsTest {

    @Test
    fun `check mappings for coordinates`() {
        val coordinate = Coordinate(1.02, 2.02, "sth2")
        val mapped = coordinate.toCoordinateDto()
        Assertions.assertEquals(BigDecimal("1.02"), mapped.lat)
        Assertions.assertEquals(BigDecimal("2.02"), mapped.lng)
        Assertions.assertEquals("sth2", mapped.label)
    }

}