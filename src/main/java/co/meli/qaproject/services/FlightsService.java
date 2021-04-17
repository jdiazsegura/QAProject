package co.meli.qaproject.services;

import co.meli.qaproject.dto.FlightDTO;

import java.util.List;

public interface FlightsService {
    List<FlightDTO> getAll();
}
