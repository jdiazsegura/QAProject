package co.meli.qaproject.controllers;

import co.meli.qaproject.dto.flights.FlightDTO;
import co.meli.qaproject.dto.hotels.HotelDTO;
import co.meli.qaproject.dto.hotels.PayloadHotelBookingDTO;
import co.meli.qaproject.dto.hotels.ResponseHotelBookDTO;
import co.meli.qaproject.services.FlightsService;
import co.meli.qaproject.services.HotelService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = MainController.class)
class MainControllerTest {

    @MockBean
    private HotelService hotelService;// = mock(Hotel// Service.class);

    @MockBean
    private FlightsService flightsService;

    @InjectMocks
    private MainController mainController;

    private ObjectMapper objectMapper = new ObjectMapper();
    private List<HotelDTO> testList;
    private List<FlightDTO> flightsTestList;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() throws IOException {
        mainController = new MainController();
        testList = objectMapper.readValue(
                new File("src/main/resources/dbHotels.json"),
                new TypeReference<>() {
                }
        );
        flightsTestList = objectMapper.readValue(
                new File("src/main/resources/dbFlights.json"),
                new TypeReference<>() {
                }
        );
    }

    @Test
    void getHotels() throws Exception {
        when(hotelService.getAll()).thenReturn(testList);
        mockMvc.perform(MockMvcRequestBuilders.get(
                "/api/v1/hotels"))
                .andExpect(status().isOk());
    }

    @Test
    void bookingHotel() throws Exception {
        PayloadHotelBookingDTO payloadHotel = objectMapper.readValue(
                new File("src/test/resources/ValidationsTest/PayloadForTest.json"),
                new TypeReference<>() {
                }
        );
        ResponseHotelBookDTO responseHotelBook = objectMapper.readValue(
                new File("src/test/resources/ControllerTest/ResponseBookForTest.json"),
                new TypeReference<>() {
                }
        );
        when(hotelService.bookHotel(any())).thenReturn(responseHotelBook);
        mockMvc.perform(MockMvcRequestBuilders.post(
                "/api/v1/booking")
                .contentType(APPLICATION_JSON_UTF8)
                .content(String.valueOf(payloadHotel)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getFlights() throws Exception {
        when(flightsService.getAll()).thenReturn(flightsTestList);
        mockMvc.perform(MockMvcRequestBuilders.get(
                "/api/v1/flights"))
                .andExpect(status().isOk());
    }

    @Test
    void reserveFlight() {
    }
}