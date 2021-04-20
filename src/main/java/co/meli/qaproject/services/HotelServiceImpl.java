package co.meli.qaproject.services;

import co.meli.qaproject.dto.*;
import co.meli.qaproject.dto.hotels.HotelDTO;
import co.meli.qaproject.dto.hotels.HotelNormDTO;
import co.meli.qaproject.dto.hotels.PayloadHotelBookingDTO;
import co.meli.qaproject.dto.hotels.ResponseHotelBookDTO;
import co.meli.qaproject.exceptions.ApiException;
import co.meli.qaproject.repositories.HotelsRepository;
import co.meli.qaproject.utils.DateUtils;
import co.meli.qaproject.utils.Validations;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class HotelServiceImpl implements HotelService{

    private final HotelsRepository hotelsRepository;
    private DateUtils dateUtils = new DateUtils();
    private Validations validations;

    public HotelServiceImpl(HotelsRepository hotelsRepository) {
        this.hotelsRepository = hotelsRepository;
        this.validations = new Validations(hotelsRepository);
    }

    @Override
    public List<HotelDTO> getAll(){
        return hotelsRepository.getAll().stream().filter(hotelDTO ->!hotelDTO.getReserved()).collect(Collectors.toList());
    }

    @Override
    public List<HotelDTO> getAllByDateAndCity(Map<String,String> allParams){
        var normalization = normHotel(allParams);
        var result = hotelsRepository.getHotelByDateAndCity(
                hotelsRepository.getAll(),normalization.getDateFrom(), normalization.getDateTo(), normalization.getCity());
        return result.stream()
                .filter(hotelDTO -> !hotelDTO.getReserved())
                .collect(Collectors.toList());
    }

    public HotelNormDTO normHotel(Map<String,String> allParams){
        return new HotelNormDTO(allParams.get("dateFrom"),allParams.get("dateTo"),allParams.get("destination"));
    }

    @Override
    public ResponseHotelBookDTO bookHotel(PayloadHotelBookingDTO payloadHotelBook) throws ApiException {
        ResponseHotelBookDTO payloadResult = new ResponseHotelBookDTO(payloadHotelBook);
        validations.validateBooking(payloadHotelBook);
        var booking = payloadHotelBook.getBooking();
        var hotel = hotelsRepository.getHotelByCode(hotelsRepository.getAll(),booking.getHotelCode());
        var nights = calculateNights(booking.getDateFrom(), booking.getDateTo());

        payloadResult.setAmount(priceByNights(nights, Double.valueOf(hotel.getNightPrice())));
        payloadResult.setInterest(valueOfInterest(booking.getPaymentMethod().getType(),booking.getPaymentMethod().getDues())*10);
        payloadResult.setTotal(getTotalValue(payloadResult.getAmount(), payloadResult.getInterest()/10));
        payloadResult.setStatusCode(new StatusCodeDTO(200,"Successfully Booked"));
        hotelsRepository.changeStatusForHotel(hotelsRepository.getAll(),hotel.getCodeHotel(),true);
        return payloadResult;
    }

    @Override
    public Integer calculateNights(String DateFrom, String DateTo){
        LocalDate DateToFinal = dateUtils.normaliceDate(DateTo);
        LocalDate DateFromFinal = dateUtils.normaliceDate(DateFrom);
        return Math.toIntExact(DateFromFinal.until(DateToFinal, ChronoUnit.DAYS)) ;
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
