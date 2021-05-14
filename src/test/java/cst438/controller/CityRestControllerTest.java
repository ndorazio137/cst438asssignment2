package cst438.controller;

//********* Static imports ***************
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//******** Non-Static imports ***********
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import cst438.domain.City;
import cst438.domain.CityInfo;
import cst438.domain.CityRepository;
import cst438.domain.Country;
import cst438.domain.TimeAndTemp;
import cst438.service.CityService;
import cst438.service.CityServiceTest;
import cst438.service.WeatherService;

@WebMvcTest(CityServiceTest.class)
public class CityRestControllerTest {
   //*********MOCKBEANS*************
   @MockBean
   private CityService cityService;
   
   // This class is used for make simulated HTTP requests to the class
   // being tested.
   @Autowired
   private MockMvc mvc;
   
   // JacksonTester is used to convert between JSON responses and java objects
   // These objects will be magically initialized by the initFields method below.
   private JacksonTester<CityInfo> json;
   
   // SetUp is done before each test
   @BeforeEach
   public void setUpEach() {
      JacksonTester.initFields(this, new ObjectMapper());
   }
   
   @Test
   public void testCityRestController() throws Exception {
   // Create test City
      Country country = new Country("TST", "Test Country");
      City city = new City(1, "TestCity", "DistrictTest", 100000, country);
      
      // Create Test weather
      TimeAndTemp timeAndTemp = new TimeAndTemp(284.78,1000000001,-14200);
      
      // Create Expected return value from CityService
      CityInfo expected = new CityInfo(city, country, timeAndTemp);
      
      // Test return results of each dependency
      given(cityService.getCityInfo("TestCity")).willReturn(expected);

      // perform the test by making simulated HTTP get using URL of "/city/TestCity"
      MockHttpServletResponse response = mvc.perform(get("/city/TestCity"))
            .andReturn().getResponse();
            
      // verify that result is as expected
      assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
      
      // convert returned data from JSON string format to City object
      assertThat(response.getContentAsString()).isEqualTo(json.write(expected).getJson());
   }
}
