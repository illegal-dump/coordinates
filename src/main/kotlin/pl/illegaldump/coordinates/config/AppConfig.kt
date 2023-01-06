package pl.illegaldump.coordinates.config

import pl.illegaldump.coordinates.api.CoordinatesController
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
    fun searchService(): SearchService {
        return SearchServiceImpl()
    }
}