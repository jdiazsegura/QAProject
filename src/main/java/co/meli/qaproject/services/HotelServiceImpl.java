package co.meli.qaproject.services;

import co.meli.qaproject.dto.*;
import co.meli.qaproject.repositories.HotelsRepository;
import co.meli.qaproject.utils.DateUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotelServiceImpl implements HotelService{

    private final HotelsRepository hotelsRepository;
    private DateUtils dateUtils = new DateUtils();

    public HotelServiceImpl(HotelsRepository hotelsRepository) {
        this.hotelsRepository = hotelsRepository;
    }

    @Override
    public List<HotelDTO> getAll(){
        return hotelsRepository.getAll();
    }

    @Override
    public List<HotelDTO> getAllByDateAndCity(String dateTo, String dateFor, String city){
        var result = hotelsRepository.getHotelByDateAndCity(hotelsRepository.getAll(),dateTo,dateFor,city);
        return result.stream()
                .filter(hotelDTO -> !hotelDTO.getReserved())
                .collect(Collectors.toList());
    }

    @Override
    public ResponseHotelBookDTO bookHotel(PayloadHotelBookDTO payloadHotelBook) {
        ResponseHotelBookDTO payloadResult = new ResponseHotelBookDTO(payloadHotelBook);
        var booking = payloadHotelBook.getBooking();
        var hotel = hotelsRepository.getHotelByCode(hotelsRepository.getAll(),booking.getHotelCode());
        var nights = calculateNights(booking.getDateTo(), booking.getDateFrom());
        payloadResult.setAmount(priceByNights(nights, Double.valueOf(hotel.getNightPrice())));
        payloadResult.setInterest(valueOfInterest(booking.getPaymentMethod().getType(),booking.getPaymentMethod().getDues())*10);
        payloadResult.setTotal(getTotalValue(payloadResult.getAmount(), payloadResult.getInterest()/10));
        return payloadResult;
    }

    @Override
    public Integer calculateNights(String DateTo, String DateFrom){
        LocalDate DateToFinal = dateUtils.normaliceDate(DateTo);
        LocalDate DateFromFinal = dateUtils.normaliceDate(DateFrom);
        // TODO Arreglar el JSON
        return Math.toIntExact(DateToFinal.until(DateFromFinal, ChronoUnit.DAYS)) ;
    }

    @Override
    public Double priceByNights(Integer numNights, Double nightValue){
        return Double.valueOf(numNights) * nightValue;
    }

    @Override
    public Double valueOfInterest(String type, Integer dues) {
        var flag = 0.0;
        if (type.equals("DEBIT")) {
            return flag;
        } else if (type.equals("CREDIT")) {
            if (dues < 3) {
                flag = 0.05;
            } else if (dues < 6) {
                flag = 0.10;
            }
        }
        return flag;
    }

    @Override
    public Double getTotalValue(Double priceByNights, Double valueOfInterest){
        return priceByNights+(priceByNights*valueOfInterest);
    }
}
