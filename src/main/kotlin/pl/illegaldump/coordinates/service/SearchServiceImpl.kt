package pl.illegaldump.coordinates.service

import pl.illegaldump.coordinates.domain.Coordinate
import javax.enterprise.context.ApplicationScoped

class SearchServiceImpl : SearchService{

    override fun getCoordinates(center: Coordinate, size: Int): List<Coordinate> {
//        TODO("Not yet implemented")
        return listOf(
            Coordinate(50.0,19.0,"label 1"),
            Coordinate(50.1,19.1,"label 2")
        )
    }
}