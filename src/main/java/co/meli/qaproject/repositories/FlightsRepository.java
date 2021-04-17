package co.meli.qaproject.repositories;

import co.meli.qaproject.dto.FlightDTO;
import lombok.SneakyThrows;

import java.util.List;

public interface FlightsRepository {
    @SneakyThrows
    List<FlightDTO> loadDatabase();

    List<FlightDTO> getAll();
}
