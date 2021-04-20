package co.meli.qaproject.repositories;

import co.meli.qaproject.dto.flights.FlightDTO;
import lombok.SneakyThrows;

import java.util.List;

public interface FlightsRepository {
    @SneakyThrows
    List<FlightDTO> loadDatabase();

    List<FlightDTO> getAll();


    List<FlightDTO> getFlightByFilters(List<FlightDTO> flights, String dateFrom, String dateTo, String origin, String destination);

    FlightDTO getFlightByNumber(List<FlightDTO> list, String code);
}
