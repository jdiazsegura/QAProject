package co.meli.qaproject.services;

import co.meli.qaproject.dto.HotelDTO;
import co.meli.qaproject.repositories.HotelsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotelServiceImpl implements HotelService{

    private final HotelsRepository hotelsRepository;

    public HotelServiceImpl(HotelsRepository hotelsRepository) {
        this.hotelsRepository = hotelsRepository;
    }

    @Override
    public List<HotelDTO> getAll(){
        return hotelsRepository.getAll();
    }

    @Override
    public List<HotelDTO> getAllByDateAndCity(String dateTo,String dateFor,String city){
        var result = hotelsRepository.getHotelByDateAndCity(hotelsRepository.getAll(),dateTo,dateFor,city);
        return result.stream()
                .filter(hotelDTO -> !hotelDTO.getReserved())
                .collect(Collectors.toList());
    }
}
