package co.meli.qaproject.repositories;

import co.meli.qaproject.dto.HotelDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;
import com.fasterxml.jackson.core.type.TypeReference;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

@Repository
public class HotelsRepositoryImpl implements HotelsRepository{


    @Value("${hotel_path:resources/dbHotels.json}")
    private String path;

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

}
