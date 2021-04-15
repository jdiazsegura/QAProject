package co.meli.qaproject.services;

import co.meli.qaproject.dto.HotelDTO;
import co.meli.qaproject.repositories.HotelsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
