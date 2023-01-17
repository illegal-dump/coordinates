package pl.illegaldump.coordinates.tests.config

import io.quarkus.test.junit.QuarkusTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import pl.illegaldump.coordinates.config.DefaultsProperties
import javax.inject.Inject

@QuarkusTest
class DefaultsPropertiesTest {

    @Inject
    lateinit var defaultsProperties: DefaultsProperties

    @Test
    fun `check if test properties file properly loaded`(){
        Assertions.assertEquals(301,defaultsProperties.size())
    }
}