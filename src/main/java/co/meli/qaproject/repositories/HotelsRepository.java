package co.meli.qaproject.repositories;

import co.meli.qaproject.dto.hotels.HotelDTO;

import java.io.FileNotFoundException;
import java.util.List;

public interface HotelsRepository {

    List<HotelDTO> loadDatabase() throws FileNotFoundException;

    void saveDatabase(List<HotelDTO> list);

    List<HotelDTO> getAll();

    HotelDTO getHotelByCode(List<HotelDTO> list, String code);

    HotelDTO getHotelByName(List<HotelDTO> list, String name);

    List<HotelDTO> getHotelsByCity(List<HotelDTO> list, String city);

    List<HotelDTO> getHotelsByRoomType(List<HotelDTO> list, String roomType);

    List<HotelDTO> getHotelsByPrice(List<HotelDTO> list, Integer price);

    List<HotelDTO> getHotelByReserved(List<HotelDTO> list, Boolean reserved);

    List<HotelDTO> getHotelByDateTo(List<HotelDTO> list, String date);

    List<HotelDTO> getHotelByDateFrom(List<HotelDTO> list, String date);

    List<HotelDTO> getHotelByDate(List<HotelDTO> list, String dateTo, String dateFrom);

    List<HotelDTO> getHotelByDateAndCity(List<HotelDTO> list, String dateTo, String dateFrom, String city);

    void changeStatusForHotel(List<HotelDTO> list, String hotelId, Boolean status);
}
