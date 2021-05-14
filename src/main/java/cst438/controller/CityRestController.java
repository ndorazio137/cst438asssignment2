package cst438.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import cst438.domain.CityInfo;
import cst438.service.CityService;

@RestController
public class CityRestController {
   
   @Autowired
   private CityService cityService;
   
   @GetMapping("/api/cities/{city}")
   public ResponseEntity<CityInfo> getWeather(@PathVariable("city") String cityName) {
      try {
         // Get a CityInfo object from cityService checking the database.
         CityInfo cityInfo = cityService.getCityInfo(cityName);
         return ResponseEntity.status(HttpStatus.OK).body(cityInfo);
         
      } catch(Exception e) {
         // On error return 404 error and null cityInfo Object to user.
         CityInfo cityInfo = null;
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(cityInfo);
      }
   }
}
