package co.meli.qaproject.services;

import co.meli.qaproject.dto.FlightDTO;
import co.meli.qaproject.dto.FlightNormDTO;
import co.meli.qaproject.repositories.FlightsRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
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
    void getAll() {
        when(flightsRepository.getAll()).thenReturn(flightsTestList);
        Assertions.assertNotNull(flightsService.getAll());
    }

    @Test
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
    void flightNorm() {
        Map<String,String> allParams = new HashMap<>();
        allParams.put("dateFrom","23/01/2021");
        allParams.put("dateTo","31/01/2021");
        allParams.put("origin","Cartagena");
        allParams.put("destination","Medellín");

        FlightNormDTO flightNormDTO = new FlightNormDTO("23/01/2021","31/01/2021","Cartagena","Medellín");

        Assertions.assertEquals(flightNormDTO,flightsService.flightNorm(allParams));
    }
}