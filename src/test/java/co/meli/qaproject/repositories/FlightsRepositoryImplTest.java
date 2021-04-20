package co.meli.qaproject.repositories;

import co.meli.qaproject.dto.flights.FlightDTO;
import co.meli.qaproject.utils.DateUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

class FlightsRepositoryImplTest {
    private FlightsRepository flightsRepository;
    private ObjectMapper objectMapper = new ObjectMapper();
    private DateUtils dateUtils = new DateUtils();
    List<FlightDTO> listForTest;

    @BeforeEach
    void setUp() throws IOException {
        listForTest = objectMapper.readValue(
                new File("src/test/resources/FlightsTests/dbFlights.json"),
                new TypeReference<List<FlightDTO>>() {
                });
        flightsRepository = new FlightsRepositoryImpl("src/test/resources/FlightsTests/dbFlights.json");
    }

    @Test
    @DisplayName("Load db test")
    void loadDatabase() throws IOException {

        Assertions.assertNotNull(listForTest);
        Assertions.assertEquals(listForTest,flightsRepository.loadDatabase());
    }

    @Test
    @DisplayName("GetAll elements of db test")
    void getAll() throws IOException {

        Assertions.assertNotNull(listForTest);
        Assertions.assertEquals(listForTest,flightsRepository.getAll());
    }

    @Test
    @DisplayName("Get a flight by filters of db test")
    void getFlightByFilters() throws IOException {
        List<FlightDTO> listOfTest = objectMapper.readValue(
                new File("src/test/resources/FlightsTests/GetByFiltersTest.json"),
                new TypeReference<List<FlightDTO>>() {
                });
        var testing = flightsRepository.getFlightByFilters(listForTest,"22/01/2021","01/02/2021","Cartagena","Medellín");

        Assertions.assertEquals(listOfTest,testing);
    }

    @Test
    @DisplayName("Get a flight by number of flight by db test")
    void getFlightByNumber() throws IOException {
        List<FlightDTO> listOfTest = objectMapper.readValue(
                new File("src/test/resources/FlightsTests/GetFlightByNumberCopy.json"),
                new TypeReference<List<FlightDTO>>() {
                });

        var testing = flightsRepository.getFlightByNumber(listForTest,"CAME-0321");

        Assertions.assertEquals(listOfTest.get(0),testing);
    }
}