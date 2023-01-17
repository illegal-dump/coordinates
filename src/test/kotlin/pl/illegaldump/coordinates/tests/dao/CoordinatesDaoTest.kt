package pl.illegaldump.coordinates.tests.dao

import io.quarkus.logging.Log
import io.quarkus.test.TestTransaction
import io.quarkus.test.junit.QuarkusTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import pl.illegaldump.coordinates.dao.CoordinatesDao
import pl.illegaldump.coordinates.domain.Boundary
import pl.illegaldump.coordinates.entity.CoordinateEntity
import javax.inject.Inject
import javax.transaction.Transactional

@QuarkusTest
@Transactional
@TestTransaction
class CoordinatesDaoTest {

    @Inject
    lateinit var coordinatesDao: CoordinatesDao

    @Test
    fun `check list all coordinates`() {
        val all = coordinatesDao.listAll()
        Log.info("coordinates ${all}")
        Assertions.assertFalse(all.isEmpty())
    }

    @Test
    fun `check persisting coordinate`() {
        val entity = CoordinateEntity(lat = 12.10, lng = 13.11, label = "label")
        coordinatesDao.persist(entity)
        Log.info("entity ${entity}")
        Assertions.assertTrue(entity.id > 0)
        Assertions.assertEquals(12.10, entity.lat)
        Assertions.assertEquals(13.11, entity.lng)
        Assertions.assertEquals("label", entity.label)
        Assertions.assertNotNull(entity.timestamp)

        val all = coordinatesDao.listAll()
        Assertions.assertEquals(3, all.size)
    }

    @Test
    fun `check searching within boundary`() {
        listOf(
            CoordinateEntity(lat = 60.777035184138974, lng = 26.3007289565269, label = "label 1"),
            CoordinateEntity(lat = 61.11863308725864, lng = 27.032369972179776, label = "label 2"),
            CoordinateEntity(lat = 60.23694871652907, lng = 29.01825272895186, label = "label 3"),
            CoordinateEntity(lat = 60.04435999659743, lng = 29.93280399851795, label = "label 4"),
            CoordinateEntity(lat = 62.23255573572826, lng = 30.98453795851896, label = "label 5"),
            CoordinateEntity(lat = 61.106329885890425, lng = 33.51261896796237, label = "label 6")
        ).forEach { coordinatesDao.persist(it) }
        val all = coordinatesDao.listAll()
        Assertions.assertEquals(8, all.size)

        val list = coordinatesDao.search(Boundary(60.03, 60.43, 29.01, 29.92)) //1 results
        Assertions.assertEquals(1, list.size)
    }

}