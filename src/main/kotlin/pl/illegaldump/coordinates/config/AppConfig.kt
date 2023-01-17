package pl.illegaldump.coordinates.config

import pl.illegaldump.coordinates.api.CoordinatesController
import pl.illegaldump.coordinates.dao.CoordinatesDao
import pl.illegaldump.coordinates.service.SearchService
import pl.illegaldump.coordinates.service.SearchServiceImpl
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.inject.Produces

class AppConfig {

    @Produces
    @ApplicationScoped
    fun coordinatesController(searchService: SearchService, defaults: DefaultsProperties): CoordinatesController {
        return CoordinatesController(searchService, defaults)
    }

    @Produces
    @ApplicationScoped
    fun coordinatesDao(): CoordinatesDao {
        return CoordinatesDao()
    }

    @Produces
    @ApplicationScoped
    fun searchService(coordinatesDao: CoordinatesDao): SearchService {
        return SearchServiceImpl(coordinatesDao)
    }


}