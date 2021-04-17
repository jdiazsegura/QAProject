package co.meli.qaproject.repositories;

import co.meli.qaproject.dto.FlightDTO;
import co.meli.qaproject.dto.HotelDTO;
import co.meli.qaproject.utils.DateUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.util.List;

@Repository
public class FlightsRepositoryImpl implements FlightsRepository {

    @Value("${flight_path:resources/dbFlights.json}")
    private String path;
    private final DateUtils dateUtils = new DateUtils();

    public FlightsRepositoryImpl() {
    }

    public FlightsRepositoryImpl(String path) {
        this.path = path;
    }

    @SneakyThrows
    @Override
    public List<FlightDTO> loadDatabase(){
        ObjectMapper objectMapper = new ObjectMapper();
        File file = ResourceUtils.getFile(path);
        TypeReference<List<FlightDTO>> typeRef = new TypeReference<>() {};
        List<FlightDTO> flights = objectMapper.readValue(file,typeRef);
        return flights;
    }

    @Override
    public List<FlightDTO> getAll(){
        return loadDatabase();
    }
}
