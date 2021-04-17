package co.meli.qaproject.services;

import co.meli.qaproject.dto.FlightDTO;
import co.meli.qaproject.dto.FlightNormDTO;
import co.meli.qaproject.repositories.FlightsRepository;
import co.meli.qaproject.utils.DateUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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

    @Override
    public List<FlightDTO> getAvailableFlights(Map<String,String> allParams){
        var flight = flightNorm(allParams);
        return flightsRepository.getFlightByFilters(
                getAll(), flight.getDateFrom(), flight.getDateTo(), flight.getOrigin(), flight.getDestination());
    }

    @Override
    public FlightNormDTO flightNorm(Map<String,String> allParams){
        return new FlightNormDTO(
                allParams.get("dateFrom")
                ,allParams.get("dateTo")
                ,allParams.get("origin")
                ,allParams.get("destination")
        );
    }


}
