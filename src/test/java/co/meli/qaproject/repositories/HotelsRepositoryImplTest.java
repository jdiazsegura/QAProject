package co.meli.qaproject.repositories;

import co.meli.qaproject.dto.hotels.HotelDTO;
import co.meli.qaproject.utils.DateUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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
    @DisplayName("LoadDb Test")
    void loadDatabase() throws IOException {
        List<HotelDTO> listForTest = objectMapper.readValue(
                new File("src/main/resources/dbHotels.json"),
                new TypeReference<List<HotelDTO>>() {
                });

        Assertions.assertNotNull(listForTest);
        Assertions.assertEquals(listForTest, hotelsRepository.loadDatabase());
    }

    @Test
    @DisplayName("LoadDb Exception test")
    @Disabled
    void loadDatabaseExceptionTest() {
        hotelsRepository = new HotelsRepositoryImpl("asd");
        Exception notFound = Assertions.assertThrows(FileNotFoundException.class,
                () -> hotelsRepository.loadDatabase());
    }

    @Test
    @DisplayName("GetAll hotels from db test")
    void getAll() throws IOException {
        List<HotelDTO> listForTest = objectMapper.readValue(
                new File("src/main/resources/dbHotels.json"),
                new TypeReference<List<HotelDTO>>() {
                });

        Assertions.assertNotNull(listForTest);
        Assertions.assertEquals(listForTest, hotelsRepository.getAll());
    }

    @Test
    @DisplayName("Get a hotel by Code from db test")
    void getHotelByCode() throws IOException {
        List<HotelDTO> listForTest = objectMapper.readValue(
                new File("src/test/resources/GetByHotelCodeTest.json"),
                new TypeReference<List<HotelDTO>>() {
                });
        var HotelFromDb = hotelsRepository.getHotelByCode(hotelsRepository.getAll(),"CH-0002");

        Assertions.assertEquals(listForTest.get(0),HotelFromDb);
    }

    @Test
    @DisplayName("Get a hotel by name from db test")
    void getHotelByName() throws IOException {
        List<HotelDTO> listForTest = objectMapper.readValue(
                new File("src/test/resources/GetByNameTest.json"),
                new TypeReference<List<HotelDTO>>() {
                });

        var HotelFromDb = hotelsRepository.getHotelByName(hotelsRepository.getAll(),"Hotel Bristol");
        Assertions.assertEquals(listForTest.get(0),HotelFromDb);
    }

    @Test
    @DisplayName("Get hotel by City from db test")
    void getHotelsByCity() throws IOException {
        List<HotelDTO> listForTest = objectMapper.readValue(
                new File("src/test/resources/GetByCityTest.json"),
                new TypeReference<List<HotelDTO>>() {
                });

        var HotelsFromDb = hotelsRepository.getHotelsByCity(hotelsRepository.getAll(),"Bogotá");
        Assertions.assertEquals(listForTest,HotelsFromDb);
    }

    @Test
    @DisplayName("Get hotel by RoomType from db test")
    void getHotelsByRoomType() throws IOException {
        List<HotelDTO> listForTest = objectMapper.readValue(
                new File("src/test/resources/GetByRoomTypeTest.json"),
                new TypeReference<List<HotelDTO>>() {
                });

        var HotelsFromDb = hotelsRepository.getHotelsByRoomType(hotelsRepository.getAll(),"Doble");
        Assertions.assertEquals(listForTest,HotelsFromDb);
    }

    @Test
    @DisplayName("Get hotel by Price from db test")
    void getHotelsByPrice() throws IOException {
        List<HotelDTO> listForTest = objectMapper.readValue(
                new File("src/test/resources/GetByPriceTest.json"),
                new TypeReference<List<HotelDTO>>() {
                });

        var HotelFromDb = hotelsRepository.getHotelsByPrice(hotelsRepository.getAll(),6400);
        Assertions.assertEquals(listForTest,HotelFromDb);
    }

    @Test
    @DisplayName("Get hotel by Reserved Status from db test")
    void getHotelByReserved() throws IOException {
        List<HotelDTO> listForTest = objectMapper.readValue(
                new File("src/test/resources/GetByReservedTest.json"),
                new TypeReference<List<HotelDTO>>() {
                });

        var HotelFromDb = hotelsRepository.getHotelByReserved(hotelsRepository.getAll(),false);
        Assertions.assertEquals(listForTest,HotelFromDb);
    }

    @Test
    @DisplayName("Get hotel by DateTo from db test")
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
    @DisplayName("Get hotel by DateFrom from db test")
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
    @DisplayName("Get hotel by dates from db test")
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
    @DisplayName("Get hotel by Date & City from db test")
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
    @DisplayName("Change Status of a hotel test")
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