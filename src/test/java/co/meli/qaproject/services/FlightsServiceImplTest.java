package co.meli.qaproject.services;

import co.meli.qaproject.dto.FlightDTO;
import co.meli.qaproject.repositories.FlightsRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.io.File;
import java.io.IOException;
import java.util.List;

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
}