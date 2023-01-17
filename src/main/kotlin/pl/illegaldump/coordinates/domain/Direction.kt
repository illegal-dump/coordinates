package pl.illegaldump.coordinates.domain

enum class Direction(val engle: Double) {
    NORTH(0.0),
    SOUTH(180.0),
    WEST(270.0),
    EAST(90.0)
}