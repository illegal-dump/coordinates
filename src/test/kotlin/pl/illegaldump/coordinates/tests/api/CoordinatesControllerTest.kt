package pl.illegaldump.coordinates.tests.api

import io.mockk.every
import io.mockk.slot
import io.quarkiverse.test.junit.mockk.InjectMock
import io.quarkus.test.junit.QuarkusTest
import io.quarkus.test.junit.TestProfile
import io.restassured.module.kotlin.extensions.Extract
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import pl.illegaldump.coordinates.api.dto.CoordinateDto
import pl.illegaldump.coordinates.config.DefaultsProperties
import pl.illegaldump.coordinates.domain.Coordinate
import pl.illegaldump.coordinates.service.SearchService
import pl.illegaldump.coordinates.tests.TestProfiles
import javax.inject.Inject
import javax.ws.rs.core.Response

@QuarkusTest
@TestProfile(TestProfiles.Simple::class)
class CoordinatesControllerTest {

    @InjectMock
    lateinit var searchService: SearchService

    @Inject
    lateinit var defaults: DefaultsProperties

    @Test
    fun `when api called and search service returns coordinates then OK`() {
        val coordinateSlot = slot<Coordinate>()

        every { searchService.getCoordinates(capture(coordinateSlot), any(), any()) } returns listOf(
            Coordinate(1.0, 1.1, "label1"),
            Coordinate(2.0, 2.1, "label2")
        )

        val list: List<CoordinateDto> = Given {
            queryParam("size", 5)
            queryParam("lat", 3.3)
            queryParam("lng", 4.5)
        } When {
            get("/api/coordinates")
        } Then {
            statusCode(Response.Status.OK.statusCode)
        } Extract {
            path("coordinates")
        }

        Assertions.assertEquals(2, list.size)
        Assertions.assertEquals(3.3, coordinateSlot.captured.lat)
        Assertions.assertEquals(4.5, coordinateSlot.captured.lng)
    }

    @Test
    fun `when api called without query params then defaults used`() {
        val coordinateSlot = slot<Coordinate>()
        val zoomSlot = slot<Int>()
        val sizeSlot = slot<Int>()

        every { searchService.getCoordinates(capture(coordinateSlot), capture(zoomSlot), capture(sizeSlot)) } returns listOf(
            Coordinate(1.0, 1.1, "label1"),
            Coordinate(2.0, 2.1, "label2")
        )

        When {
            get("/api/coordinates")
        } Then {
            statusCode(Response.Status.OK.statusCode)
        }

        Assertions.assertEquals(defaults.center().lat(), coordinateSlot.captured.lat)
        Assertions.assertEquals(defaults.center().lng(), coordinateSlot.captured.lng)
        Assertions.assertEquals(defaults.zoom(),zoomSlot.captured)
        Assertions.assertEquals(defaults.size(),sizeSlot.captured)
    }

    @Test
    fun `when search service throws exception then UNPROCESSABLE_ENTITY returned`() {
        every { searchService.getCoordinates(any(), any(), any()) } throws RuntimeException()

        When {
            get("/api/coordinates")
        } Then {
            statusCode(422)
        }
    }

    @Test
    fun `check query params`(){
        val coordinateSlot = slot<Coordinate>()
        val zoomSlot = slot<Int>()
        val sizeSlot = slot<Int>()

        every { searchService.getCoordinates(capture(coordinateSlot), capture(zoomSlot), capture(sizeSlot)) } returns listOf(
            Coordinate(1.0, 1.1, "label1"),
            Coordinate(2.0, 2.1, "label2")
        )

        val list: List<CoordinateDto> = Given {
            queryParam("lat", 3.3)
            queryParam("lng", 4.5)
            queryParam("zoom", 5)
            queryParam("size", 100)
        } When {
            get("/api/coordinates")
        } Then {
            statusCode(Response.Status.OK.statusCode)
        } Extract {
            path("coordinates")
        }

        Assertions.assertEquals(3.3, coordinateSlot.captured.lat)
        Assertions.assertEquals(4.5, coordinateSlot.captured.lng)
        Assertions.assertEquals(5, zoomSlot.captured)
        Assertions.assertEquals(100, sizeSlot.captured)
    }

}