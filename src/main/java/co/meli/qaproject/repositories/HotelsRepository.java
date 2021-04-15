package co.meli.qaproject.repositories;

import co.meli.qaproject.dto.HotelDTO;

import java.io.FileNotFoundException;
import java.util.List;

public interface HotelsRepository {
    List<HotelDTO> loadDatabase() throws FileNotFoundException;

    List<HotelDTO> getAll();
}
