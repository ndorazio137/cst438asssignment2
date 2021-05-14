package cst438.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cst438.domain.City;
import cst438.domain.CityInfo;
import cst438.domain.CityRepository;
import cst438.domain.TimeAndTemp;

@Service
public class CityService {
   //Dependent on:
   @Autowired
   private CityRepository cityRepository;
   @Autowired
   private WeatherService weatherService;

   public CityService(CityRepository cityRepository,
         WeatherService weatherService)
   {
      this.cityRepository = cityRepository;
      this.weatherService = weatherService;
   }

   public CityInfo getCityInfo(String cityName) {
      // Get a list of cities from the database
      List<City> cities = cityRepository.findByName(cityName);
      
      // Return null on empty list
      if (cities.size() == 0) {
         return null;
      }
      // Take the first city if multiple are returned
      City city = cities.get(0);
      // Get the time and temperature from the API
      TimeAndTemp timeAndTemp = weatherService.getTempAndTime(cityName);
      
      return new CityInfo(city, city.getCountry(), timeAndTemp);
   }
}
