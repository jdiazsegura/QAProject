package co.meli.qaproject.services;

import co.meli.qaproject.dto.HotelDTO;
import co.meli.qaproject.repositories.HotelsRepository;
import co.meli.qaproject.repositories.HotelsRepositoryImpl;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.io.File;
import java.io.IOException;
import java.util.List;

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
    void getAll() throws IOException {

        when(hotelsRepository.getAll()).thenReturn(testList);

        Assertions.assertNotNull(hotelService.getAll());
    }

    @Test
    void getAllByDateAndCity() throws IOException {
        List<HotelDTO> listExpected = objectMapper.readValue(
                new File("src/test/resources/GetByDateAndCity.json"),
                new TypeReference<List<HotelDTO>>() {
                });
        //when(hotelsRepository.getAll()).thenReturn(testList);
        when(hotelsRepository.getHotelByDateAndCity(any(),any(),any(),any())).thenReturn(listExpected);
        String dateToForTest = "09/02/2021";
        String dateFromForTest = "21/03/2021";
        var HotelForTest = hotelService.getAllByDateAndCity(dateToForTest,dateFromForTest,"Buenos Aires");
        Assertions.assertEquals(listExpected,HotelForTest);
    }

    @Test
    void calculateNights() {
        var testFlag = hotelService.calculateNights("10/11/2021","20/11/2021");

        assertEquals(10,testFlag);
    }

    @Test
    void priceByNights() {
        var testFLag = hotelService.priceByNights(10,6300.00);
        assertEquals(63000,testFLag);
    }

    @Test
    void valueOfInterest() {
        var testFlag = hotelService.valueOfInterest("CREDIT",2);
        var testFlag2 = hotelService.valueOfInterest("DEBIT",1);
        var testFlag3 = hotelService.valueOfInterest("CREDIT",5);
        assertEquals(0.05,testFlag);
        assertEquals(0,testFlag2);
        assertEquals(0.10,testFlag3);

    }

    @Test
    void getTotalValue() {
        var testFlag = hotelService.getTotalValue(63000.0,0.05);
        assertEquals(66150,testFlag);
    }
}