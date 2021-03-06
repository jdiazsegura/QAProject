package co.meli.qaproject.services;

import co.meli.qaproject.dto.hotels.HotelDTO;
import co.meli.qaproject.repositories.HotelsRepository;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class HotelServiceImplTest {

    private HotelsRepository hotelsRepository = mock(HotelsRepository.class);

    @InjectMocks
    private HotelService hotelService;
    private List<HotelDTO> testList;

    private ObjectMapper objectMapper = new ObjectMapper();
    @BeforeEach
    void setUp() throws IOException {
        hotelService = new HotelServiceImpl(hotelsRepository);
        testList = objectMapper.readValue(
                new File("src/test/resources/dbHotels.json"),
                new TypeReference<>() {
                }
        );
    }

    @Test
    @DisplayName("Get all hotels test")
    void getAll() throws IOException {

        when(hotelsRepository.getAll()).thenReturn(testList);

        Assertions.assertNotNull(hotelService.getAll());
    }

    @Test
    @DisplayName("Get all hotels by Dates & City")
    void getAllByDateAndCity() throws IOException {
        List<HotelDTO> listExpected = objectMapper.readValue(
                new File("src/test/resources/GetByDateAndCity.json"),
                new TypeReference<List<HotelDTO>>() {
                });
        //when(hotelsRepository.getAll()).thenReturn(testList);
        when(hotelsRepository.getHotelByDateAndCity(any(),any(),any(),any())).thenReturn(listExpected);
        String dateTo = "09/02/2021";
        String dateFrom = "21/03/2021";
        String city = "Buenos Aires";
        Map<String,String> mapForTest = new HashMap<>();
        mapForTest.put("dateTo",dateTo);
        mapForTest.put("dateFrom",dateFrom);
        mapForTest.put("city",city);
        var HotelForTest = hotelService.getAllByDateAndCity(mapForTest);
        Assertions.assertEquals(listExpected,HotelForTest);
    }

    @Test
    @DisplayName("Calculate number of nights test")
    void calculateNights() {
        var testFlag = hotelService.calculateNights("10/11/2021","20/11/2021");

        assertEquals(10,testFlag);
    }

    @Test
    @DisplayName("Calculate prices by nights test")
    void priceByNights() {
        var testFLag = hotelService.priceByNights(10,6300.00);
        assertEquals(63000,testFLag);
    }

    @Test
    @DisplayName("Calculate value of interest test")
    void valueOfInterest() {
        var testFlag = hotelService.valueOfInterest("CREDIT",2);
        var testFlag2 = hotelService.valueOfInterest("DEBIT",1);
        var testFlag3 = hotelService.valueOfInterest("CREDIT",5);
        assertEquals(0.05,testFlag);
        assertEquals(0,testFlag2);
        assertEquals(0.10,testFlag3);
    }

    @Test
    @DisplayName("Calculate total value of booking test")
    void getTotalValue() {
        var testFlag = hotelService.getTotalValue(63000.0,0.05);
        assertEquals(66150,testFlag);
    }
}