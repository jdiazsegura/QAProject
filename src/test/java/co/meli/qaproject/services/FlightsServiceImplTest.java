package co.meli.qaproject.services;

import co.meli.qaproject.dto.flights.FlightDTO;
import co.meli.qaproject.dto.flights.FlightNormDTO;
import co.meli.qaproject.dto.flights.PayloadFlightReservDTO;
import co.meli.qaproject.dto.flights.ResponseFlightReservDTO;
import co.meli.qaproject.repositories.FlightsRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;

class FlightsServiceImplTest {

    private FlightsRepository flightsRepository = mock(FlightsRepository.class);

    @InjectMocks
    private FlightsService flightsService;
    private List<FlightDTO> flightsTestList;
    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() throws IOException {
        flightsService = new FlightsServiceImpl(flightsRepository);
        flightsTestList = objectMapper.readValue(
                new File("src/test/resources/FlightsTests/dbFlights.json"),
                new TypeReference<List<FlightDTO>>() {
                });
    }

    @Test
    @DisplayName("Get all flights test")
    void getAll() {
        when(flightsRepository.getAll()).thenReturn(flightsTestList);
        Assertions.assertNotNull(flightsService.getAll());
    }

    @Test
    @DisplayName("Get all available flights test")
    void getAvailableFlights() throws IOException {
        var flightForTest = objectMapper.readValue(
                new File("src/test/resources/FlightsTests/GetByFiltersTest.json"),
                new TypeReference<List<FlightDTO>>() {
                });
        when(flightsRepository.getFlightByFilters(any(),any(),any(),any(),any())).thenReturn(flightForTest);
        Map<String,String> allParams = new HashMap<>();
        allParams.put("dateFrom","23/01/2021");
        allParams.put("dateTo","31/01/2021");
        allParams.put("origin","Cartagena");
        allParams.put("destination","Medellín");
        var flag = flightsService.getAvailableFlights(allParams);

        Assertions.assertEquals(flightForTest,flag);
    }

    @Test
    @DisplayName("Flight normalization test")
    void flightNorm() {
        Map<String,String> allParams = new HashMap<>();
        allParams.put("dateFrom","23/01/2021");
        allParams.put("dateTo","31/01/2021");
        allParams.put("origin","Cartagena");
        allParams.put("destination","Medellín");

        FlightNormDTO flightNormDTO = new FlightNormDTO("23/01/2021","31/01/2021","Cartagena","Medellín");

        Assertions.assertEquals(flightNormDTO,flightsService.flightNorm(allParams));
    }

    @Test
    @DisplayName("Reserve a flight test")
    void reserveFlight() throws IOException {
        var flightForTest = objectMapper.readValue(
                new File("src/test/resources/FlightsTests/bodyForTest.json"),
                new TypeReference<PayloadFlightReservDTO>() {
                });
        var flight = objectMapper.readValue(
                new File("src/test/resources/FlightsTests/FlightForTest.json"),
                new TypeReference<FlightDTO>() {
                });
        var resultForTest = objectMapper.readValue(
                new File("src/test/resources/FlightsTests/resultForTest.json"),
                new TypeReference<ResponseFlightReservDTO>() {
                });

        when(flightsRepository.getFlightByNumber(any(),any())).thenReturn(flight);
        var reserveForTest = flightsService.reserveFlight(flightForTest);

        Assertions.assertEquals(resultForTest,reserveForTest);
    }

    @Test
    @DisplayName("Value of interest of flights payment test")
    void valueOfInterest() {
        var valForTest = flightsService.valueOfInterest("DEBIT",1);
        var valFoTest2 = flightsService.valueOfInterest("CREDIT",2);
        var valForTest3 = flightsService.valueOfInterest("CREDIT",4);
        Assertions.assertEquals(0,valForTest);
        Assertions.assertEquals(0.05,valFoTest2);
        Assertions.assertEquals(0.10,valForTest3);
    }

    @Test
    @DisplayName("Total value of flight reservation")
    void getTotalValue() {
        var valForTest = flightsService.getTotalValue(13000.0,1.0);
        Assertions.assertEquals(26000.0,valForTest);
    }
}