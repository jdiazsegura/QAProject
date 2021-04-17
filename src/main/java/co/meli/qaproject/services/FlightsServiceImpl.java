package co.meli.qaproject.services;

import co.meli.qaproject.dto.FlightDTO;
import co.meli.qaproject.repositories.FlightsRepository;
import co.meli.qaproject.utils.DateUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightsServiceImpl implements FlightsService{

    private final FlightsRepository flightsRepository;
    private DateUtils dateUtils = new DateUtils();

    public FlightsServiceImpl(FlightsRepository flightsRepository) {
        this.flightsRepository = flightsRepository;
    }

    @Override
    public List<FlightDTO> getAll(){
        return flightsRepository.getAll();
    }
}
