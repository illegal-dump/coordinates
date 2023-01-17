package pl.illegaldump.coordinates.config

import io.quarkus.runtime.annotations.StaticInitSafe
import io.smallrye.config.ConfigMapping

@ConfigMapping(prefix = "coordinates.defaults")
@StaticInitSafe
interface DefaultsProperties {

    fun size(): Int

    fun center(): Center

    fun zoom(): Int

    @ConfigMapping(prefix = "center")
    interface Center {
        fun lat(): Double
        fun lng(): Double
    }
}