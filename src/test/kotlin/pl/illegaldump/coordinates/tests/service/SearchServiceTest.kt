package pl.illegaldump.coordinates.tests.service

import io.mockk.every
import io.mockk.slot
import io.quarkiverse.test.junit.mockk.InjectMock
import io.quarkus.test.junit.QuarkusTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import pl.illegaldump.coordinates.dao.CoordinatesDao
import pl.illegaldump.coordinates.domain.Boundary
import pl.illegaldump.coordinates.domain.Coordinate
import pl.illegaldump.coordinates.entity.CoordinateEntity
import pl.illegaldump.coordinates.service.SearchService
import java.sql.Timestamp
import java.time.LocalDateTime
import java.time.ZoneOffset
import javax.inject.Inject

@QuarkusTest
class SearchServiceTest {

    @InjectMock
    lateinit var dao: CoordinatesDao

    @Inject
    lateinit var service: SearchService

    @Test
    fun `check searching`() {
        //given
        val center = Coordinate(lat = 50.23694871652907, lng = 19.01825272895186, label = "katowice")
        val now = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli()
        val boundarySlot = slot<Boundary>()

        every { dao.search(capture(boundarySlot)) } returns listOf(
            CoordinateEntity(1, lat = 1.1, lng = 1.2, Timestamp(now), "label 1"),
            CoordinateEntity(2, lat = 2.1, lng = 2.2, Timestamp(now), "label 2")
        )

        //when
        val coordinates = service.getCoordinates(center, 10, 300)

        //then
        Assertions.assertEquals(2, coordinates.size)
        val first = coordinates.first()
        Assertions.assertEquals(1.1, first.lat)
        Assertions.assertEquals(1.2, first.lng)
        Assertions.assertEquals("label 1", first.label)

        val (south, north, west, east) = boundarySlot.captured
        Assertions.assertEquals(50.65032198675791,north)
        Assertions.assertEquals(49.82357544630023,south)
        Assertions.assertEquals(18.37198351813226,west)
        Assertions.assertEquals(19.664521939771507,east)
    }
}