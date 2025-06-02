package taxiapp.driver.controller;

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.beans.factory.annotation.Autowired
import taxiapp.driver.service.DriversService

@RestController
@RequestMapping("api/drivers")
class DriversController @Autowired constructor(
    private val driversService: DriversService 
){    
    @GetMapping("/verified")
    fun getVerifiedDrivers(): ResponseEntity<Any> {
        return ResponseEntity.ok(driversService.getVerifiedDrivers());
    }

    @GetMapping("/all")
    fun getAll(): ResponseEntity<Any> { 
        return ResponseEntity.ok(driversService.getAllDrivers());
    }

    @GetMapping("/health")
    fun health(): ResponseEntity<Map<String, String>> {
        return ResponseEntity.ok(mapOf(
            "status" to "UP",
            "service" to "driver-service"
        ))
    }

}
