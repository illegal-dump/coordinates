package pl.illegaldump.coordinates.entity

import java.sql.Timestamp
import java.time.LocalDateTime
import java.time.ZoneOffset
import javax.persistence.*

/**
 * default values to avoid hibernate default constructor requirement
 */
@Entity
@Table(name = "coordinate")
data class CoordinateEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false, name = "lat")
    val lat: Double = 0.0,

    @Column(nullable = false, name = "lng")
    val lng: Double = 0.0,

    @Column(nullable = false, name = "created")
    val timestamp: Timestamp = Timestamp(LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli()),

    @Column(name = "label")
    val label: String? = null
) {}