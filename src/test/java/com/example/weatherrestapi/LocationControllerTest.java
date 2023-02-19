package com.example.weatherrestapi;

import com.example.weatherrestapi.controller.LocationController;
import com.example.weatherrestapi.entity.Location;
import com.example.weatherrestapi.service.LocationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(LocationController.class)
public class LocationControllerTest {
    private static final String END_POINT_PATH = "/v1/locations";
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    LocationService locationService;

    @Test
    public void testAddShouldReturn400BadRequest() throws Exception {
        Location location = new Location();

        String bodyContent = objectMapper.writeValueAsString(location);

        mockMvc.perform(post(END_POINT_PATH).contentType("application/json").content(bodyContent))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }
    @Test
    public void testAddShouldReturn201Created() throws Exception {
        Location location = new Location();
        location.setCode("VN12");
        location.setEnabled(true);
        location.setCityName("Ha Noi");
        location.setCountryName("VN");
        location.setCountryCode("HNI");
        location.setRegionName("RegionName");

        Mockito.when(locationService.add(location)).thenReturn(location);

        String bodyContent = objectMapper.writeValueAsString(location);

        mockMvc.perform(post(END_POINT_PATH).contentType("application/json").content(bodyContent))
                .andExpect(status().isCreated())
                .andExpect(content().contentType("application/json"))
                .andDo(print());

    }
}
