package co.meli.qaproject.repositories;

import co.meli.qaproject.dto.HotelDTO;
import co.meli.qaproject.utils.DateUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;
import com.fasterxml.jackson.core.type.TypeReference;


import java.io.File;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class HotelsRepositoryImpl implements HotelsRepository{


    @Value("${hotel_path:resources/dbHotels.json}")
    private String path;
    private DateUtils dateUtils = new DateUtils();

    public HotelsRepositoryImpl() {
    }

    public HotelsRepositoryImpl(String path) {
        this.path = path;
    }

    @Override
    public List<HotelDTO> loadDatabase() {
        File file = null;
        try {
            file = ResourceUtils.getFile(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<HotelDTO>> typeRef = new TypeReference<>() {};
        List<HotelDTO> hotelDTOS = null;
        try {
            hotelDTOS = objectMapper.readValue(file, typeRef);
        }catch (Exception notFound){
            notFound.printStackTrace();
        }
        return hotelDTOS;
    }

    @Override
    public List<HotelDTO> getAll(){
        return loadDatabase();
    }

    @Override
    public HotelDTO getHotelByCode(List<HotelDTO>list,String code){
        return list.stream()
                .filter(hotelDTO -> hotelDTO.getCodeHotel()
                .equals(code))
                .findFirst().get();
    }
    @Override
    public HotelDTO getHotelByName(List<HotelDTO> list, String name){
        return list.stream()
            .filter(hotelDTO -> hotelDTO.getName().equals(name))
            .findFirst().get();
    }

    @Override
    public List<HotelDTO> getHotelsByCity(List<HotelDTO> list, String city){
        return list.stream()
                .filter(hotelDTO -> hotelDTO.getCity()
                .equals(city))
                .collect(Collectors.toList());
    }

    @Override
    public List<HotelDTO> getHotelsByRoomType(List<HotelDTO> list, String roomType){
        return list.stream()
                .filter(hotelDTO -> hotelDTO.getRoomType()
                .equals(roomType))
                .collect(Collectors.toList());
    }

    @Override
    public List<HotelDTO> getHotelsByPrice(List<HotelDTO> list, Integer price){
        return list.stream()
                .filter(hotelDTO -> hotelDTO.getNightPrice()
                .equals(price))
                .collect(Collectors.toList());
    }

    @Override
    public List<HotelDTO> getHotelByReserved(List<HotelDTO> list, Boolean reserved){
        return list.stream()
                .filter(hotelDTO -> hotelDTO.getReserved()
                .equals(reserved))
                .collect(Collectors.toList());
    }

    @Override
    public List<HotelDTO> getHotelByDateTo(List<HotelDTO> list, String date){
        return list.stream()
                .filter(hotelDTO -> dateUtils.normaliceDate(hotelDTO.getDateTo())
                .equals(dateUtils.normaliceDate(date)))
                .collect(Collectors.toList());
    }

    @Override
    public List<HotelDTO> getHotelByDateFrom(List<HotelDTO> list, String date){
        return list.stream()
                .filter(hotelDTO -> dateUtils.normaliceDate(hotelDTO.getDateFrom())
                .equals(dateUtils.normaliceDate(date)))
                .collect(Collectors.toList());
    }

    @Override
    public List<HotelDTO> getHotelByDate(List<HotelDTO> list, String dateTo, String dateFrom){
        var dateToF = dateUtils.normaliceDate(dateTo);
        var dateFromF = dateUtils.normaliceDate(dateFrom);
        return list.stream()
                .filter(hotelDTO -> dateUtils.normaliceDate(hotelDTO.getDateTo()).isAfter(dateToF))
                .filter(hotelDTO -> dateUtils.normaliceDate(hotelDTO.getDateFrom()).isBefore(dateFromF))
                .collect(Collectors.toList());
    }

    @Override
    public List<HotelDTO> getHotelByDateAndCity(List<HotelDTO> list, String dateTo, String dateFrom, String city){
        var dateToF = dateUtils.normaliceDate(dateTo);
        var dateFromF = dateUtils.normaliceDate(dateFrom);
        return list.stream()
                .filter(hotelDTO -> dateUtils.normaliceDate(hotelDTO.getDateTo()).isAfter(dateToF))
                .filter(hotelDTO -> dateUtils.normaliceDate(hotelDTO.getDateFrom()).isBefore(dateFromF))
                .filter(hotelDTO -> hotelDTO.getCity().equals(city))
                .collect(Collectors.toList());
    }
}
