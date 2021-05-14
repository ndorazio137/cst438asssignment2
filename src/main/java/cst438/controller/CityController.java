package cst438.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import cst438.domain.City;
import cst438.domain.CityInfo;
import cst438.domain.Country;
import cst438.domain.TimeAndTemp;
import cst438.service.CityService;

@Controller
public class CityController {
   
   @Autowired
   private CityService cityService;
   
   @GetMapping("/cities/{city}")
   public String getCityInfo(@PathVariable("city") String cityName,
         Model model) {
      // Get a CityInfo object from cityService checking the database.
      CityInfo cityInfo = cityService.getCityInfo(cityName);
      
      // if no city object render 404 error page.
      if (cityInfo == null) {
         return "page_not_found";
      }
      
      // when city object exists render city.html page.
      model.addAttribute("cityInfoModel", cityInfo);
      return "city";
   }
   
   @GetMapping("/404")
   public String getError() {
      return "page_not_found";
   }
   
   @GetMapping("/city")
   public String getCityPage(Model model) {
      Country country = new Country("TST", "Test Country");
      City city = new City(1, "TestCity", "DistrictTest", 300000, country);
      TimeAndTemp timeAndTemp = new TimeAndTemp(284.78,1000000001,-14200);
      
      // Create Expected return value from CityService
      model.addAttribute("cityInfoModel", new CityInfo(city, country, timeAndTemp));
      return "city";
   }
}