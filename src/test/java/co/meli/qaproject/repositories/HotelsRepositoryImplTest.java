package co.meli.qaproject.repositories;

import co.meli.qaproject.dto.HotelDTO;
import co.meli.qaproject.utils.DateUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


class HotelsRepositoryImplTest {

    private HotelsRepository hotelsRepository;
    private ObjectMapper objectMapper = new ObjectMapper();
    private DateUtils dateUtils = new DateUtils();

    @BeforeEach
    void setUp() {
        hotelsRepository = new HotelsRepositoryImpl("src/main/resources/dbHotels.json");
    }


    @Test
    void loadDatabase() throws IOException {
        List<HotelDTO> listForTest = objectMapper.readValue(
                new File("src/main/resources/dbHotels.json"),
                new TypeReference<List<HotelDTO>>() {
                });

        Assertions.assertNotNull(listForTest);
        Assertions.assertEquals(listForTest, hotelsRepository.loadDatabase());
    }

    @Test
    @Disabled
    void loadDatabaseExceptionTest() {
        hotelsRepository = new HotelsRepositoryImpl("asd");
        Exception notFound = Assertions.assertThrows(FileNotFoundException.class,
                () -> hotelsRepository.loadDatabase());
    }

    @Test
    void getAll() throws IOException {
        List<HotelDTO> listForTest = objectMapper.readValue(
                new File("src/main/resources/dbHotels.json"),
                new TypeReference<List<HotelDTO>>() {
                });

        Assertions.assertNotNull(listForTest);
        Assertions.assertEquals(listForTest, hotelsRepository.getAll());
    }

    @Test
    void getHotelByCode() throws IOException {
        List<HotelDTO> listForTest = objectMapper.readValue(
                new File("src/test/resources/GetByHotelCodeTest.json"),
                new TypeReference<List<HotelDTO>>() {
                });
        var HotelFromDb = hotelsRepository.getHotelByCode(hotelsRepository.getAll(),"CH-0002");

        Assertions.assertEquals(listForTest.get(0),HotelFromDb);
    }

    @Test
    void getHotelByName() throws IOException {
        List<HotelDTO> listForTest = objectMapper.readValue(
                new File("src/test/resources/GetByNameTest.json"),
                new TypeReference<List<HotelDTO>>() {
                });

        var HotelFromDb = hotelsRepository.getHotelByName(hotelsRepository.getAll(),"Hotel Bristol");
        Assertions.assertEquals(listForTest.get(0),HotelFromDb);
    }

    @Test
    void getHotelsByCity() throws IOException {
        List<HotelDTO> listForTest = objectMapper.readValue(
                new File("src/test/resources/GetByCityTest.json"),
                new TypeReference<List<HotelDTO>>() {
                });

        var HotelsFromDb = hotelsRepository.getHotelsByCity(hotelsRepository.getAll(),"Bogotá");
        Assertions.assertEquals(listForTest,HotelsFromDb);
    }

    @Test
    void getHotelsByRoomType() throws IOException {
        List<HotelDTO> listForTest = objectMapper.readValue(
                new File("src/test/resources/GetByRoomTypeTest.json"),
                new TypeReference<List<HotelDTO>>() {
                });

        var HotelsFromDb = hotelsRepository.getHotelsByRoomType(hotelsRepository.getAll(),"Doble");
        Assertions.assertEquals(listForTest,HotelsFromDb);
    }

    @Test
    void getHotelsByPrice() throws IOException {
        List<HotelDTO> listForTest = objectMapper.readValue(
                new File("src/test/resources/GetByPriceTest.json"),
                new TypeReference<List<HotelDTO>>() {
                });

        var HotelFromDb = hotelsRepository.getHotelsByPrice(hotelsRepository.getAll(),6400);
        Assertions.assertEquals(listForTest,HotelFromDb);
    }

    @Test
    void getHotelByReserved() throws IOException {
        List<HotelDTO> listForTest = objectMapper.readValue(
                new File("src/test/resources/GetByReservedTest.json"),
                new TypeReference<List<HotelDTO>>() {
                });

        var HotelFromDb = hotelsRepository.getHotelByReserved(hotelsRepository.getAll(),false);
        Assertions.assertEquals(listForTest,HotelFromDb);
    }

    @Test
    void getHotelByDateTo() throws IOException {
        List<HotelDTO> listForTest = objectMapper.readValue(
                new File("src/test/resources/GetByDateToTest.json"),
                new TypeReference<List<HotelDTO>>() {
                });
        String dateForTest = "20/03/2021";
        var HotelFromDb = hotelsRepository.getHotelByDateTo(hotelsRepository.getAll(),dateForTest);

        Assertions.assertEquals(listForTest,HotelFromDb);
    }

    @Test
    void getHotelByDateFrom() throws IOException {
        List<HotelDTO> listForTest = objectMapper.readValue(
                new File("src/test/resources/GetByDateFromTest.json"),
                new TypeReference<List<HotelDTO>>() {
                });
        String dateForTest = "10/02/2021";
        var HotelFromDb = hotelsRepository.getHotelByDateFrom(hotelsRepository.getAll(),dateForTest);

        Assertions.assertEquals(listForTest,HotelFromDb);
    }

    @Test
    void getHotelByDate() throws IOException {
        List<HotelDTO> listForTest = objectMapper.readValue(
                new File("src/test/resources/GetByDateTest.json"),
                new TypeReference<List<HotelDTO>>() {
                });
        String dateFromForTest = "09/02/2021";
        String dateToForTest = "21/03/2021";
        var HotelFromDb = hotelsRepository.getHotelByDate(hotelsRepository.getAll(),dateFromForTest,dateToForTest);

        Assertions.assertEquals(listForTest,HotelFromDb);
    }

    @Test
    void getHotelByDateAndCity() throws IOException {
        List<HotelDTO> listForTest = objectMapper.readValue(
                new File("src/test/resources/GetByDateAndCity.json"),
                new TypeReference<List<HotelDTO>>() {
                });
        String dateFromForTest = "09/02/2021";
        String dateToForTest = "21/03/2021";

        var HotelFromDb = hotelsRepository.
                getHotelByDateAndCity(hotelsRepository.getAll(),dateFromForTest,dateToForTest,"Buenos Aires");

        Assertions.assertEquals(listForTest,HotelFromDb);
    }

    @Test
    void changeStatusForHotel() throws IOException {
        List<HotelDTO> listForTest = objectMapper.readValue(
                new File("src/test/resources/ChangeStatusTest.json"),
                new TypeReference<List<HotelDTO>>() {
                });

        var listChangeTest = hotelsRepository.getAll();
        String hotelCodeTest = "CH-0002";
        hotelsRepository.changeStatusForHotel(listChangeTest,hotelCodeTest,true);
        Assertions.assertEquals(listForTest,listChangeTest);
        hotelsRepository.changeStatusForHotel(listChangeTest,hotelCodeTest,false);

    }
}