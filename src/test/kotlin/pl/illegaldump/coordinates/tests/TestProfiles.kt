package pl.illegaldump.coordinates.tests

import io.quarkus.test.junit.QuarkusTestProfile

object TestProfiles {
    class Simple : QuarkusTestProfile{
        override fun getConfigProfile(): String {
            return "test"
        }
    }
}