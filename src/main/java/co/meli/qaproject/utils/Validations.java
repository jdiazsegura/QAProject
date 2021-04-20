package co.meli.qaproject.utils;

import co.meli.qaproject.dto.flights.FlightDTO;
import co.meli.qaproject.dto.flights.PayloadFlightReservDTO;
import co.meli.qaproject.dto.hotels.HotelDTO;
import co.meli.qaproject.dto.hotels.PayloadHotelBookingDTO;
import co.meli.qaproject.exceptions.ApiException;
import co.meli.qaproject.repositories.FlightsRepository;
import co.meli.qaproject.repositories.HotelsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class Validations implements IValidations {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private HotelsRepository hotelsRepository;
    private FlightsRepository flightsRepository;

    public Validations() {
    }

    public Validations(HotelsRepository hotelsRepository) {

        this.hotelsRepository = hotelsRepository;
    }

    public Validations(FlightsRepository flightsRepository) {
        this.flightsRepository = flightsRepository;
    }

    public Validations(HotelsRepository hotelsRepository, FlightsRepository flightsRepository) {
        this.hotelsRepository = hotelsRepository;
        this.flightsRepository = flightsRepository;
    }

    public Boolean validateDate(String date){
        try{
            LocalDate.parse(date,formatter);
        }catch (DateTimeException e){
            return false;
        }
        return true;
    }

    @Override
    public void validateEmail(String email) throws ApiException {
        String regex = "^[\\w!#$%&'+/=?`{|}~^-]+(?:\\.[\\w!#$%&'+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) throw new ApiException(HttpStatus.BAD_REQUEST, "Invalid email " + email);
    }

    @Override
    public void validateHotelRoom(HotelDTO hotel, String roomType) throws ApiException {
        if(roomType != null){
            if (!hotel.getRoomType().equals(roomType)){
                throw new ApiException(HttpStatus.BAD_REQUEST,"Invalid RoomType "+ roomType);
            }
        }else throw new ApiException(HttpStatus.BAD_REQUEST,"Missing Room Type");
    }

    @Override
    public void validateDestination(String destination, String validDestination) throws ApiException {
        if(destination != null){
            if (!validDestination.equals(destination)){
                throw new ApiException(HttpStatus.BAD_REQUEST,"Invalid Destination "+ destination);
            }
        }else throw new ApiException(HttpStatus.BAD_REQUEST,"Missing destination");
    }

    @Override
    public void validOrigin(String origin, String validOrigin) throws ApiException {
        if(origin != null){
            if(!validOrigin.equals(origin)){
                throw new ApiException(HttpStatus.BAD_REQUEST,"Invalid Origin "+ origin);
            }
        }else throw new ApiException(HttpStatus.BAD_REQUEST,"Missing Origin");
    }

    @Override
    public void validateDateFrom(String date, HotelDTO hotel) throws ApiException {
        String regex = "^(1[0-2]|0[1-9])/(3[01]|[12][0-9]|0[1-9])/[0-9]{4}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(date);
            if (matcher.matches()) {
                if (!hotel.getDateFrom().equals(date)) {
                    throw new ApiException(HttpStatus.BAD_REQUEST, "Invalid DateFrom " + date);
                }
            }else throw new ApiException(HttpStatus.BAD_REQUEST, "Invalid DateFrom " + date);

    }

    @Override
    public void validateDateTo(String date, HotelDTO hotel) throws ApiException {
        String regex = "^([0-2][0-9]||3[0-1])/(0[0-9]||1[0-2])/([0-9][0-9])?[0-9][0-9]$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(date);
            if (matcher.matches()) {
                if (!hotel.getDateTo().equals(date)) {
                    throw new ApiException(HttpStatus.BAD_REQUEST, "Invalid DateTo " + date);
                }
            }else throw new ApiException(HttpStatus.BAD_REQUEST,"Invalid DateTo "+ date);

    }

    @Override
    public void validateFlightDate(String date, String validDate) throws ApiException {
        String regex = "^([0-2][0-9]||3[0-1])/(0[0-9]||1[0-2])/([0-9][0-9])?[0-9][0-9]$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(date);
            if (matcher.matches()) {
                if (!validDate.equals(date)) {
                    throw new ApiException(HttpStatus.BAD_REQUEST, "Invalidate DateTo " + date);
                }
            }else throw new ApiException(HttpStatus.BAD_REQUEST,"Invalid Flight Date "+ date);

    }

    @Override
    public void validateSeatType(String seat, String validSeat) throws ApiException {
        if(seat != null){
            if(!validSeat.equals(seat)){
                throw new ApiException(HttpStatus.BAD_REQUEST,"Invalid SeatType "+ seat);
            }
        }
    }

    @Override
    public void validateRoomTypeAndPeople(String roomType, Integer peopleAmount) throws ApiException{
        if(peopleAmount != null){
            if(peopleAmount > 0 && peopleAmount <= 10 ){
                if(roomType != null){
                    if (!roomType.equals("")){
                        if(peopleAmount > 1 && roomType.equals("Single")){
                            throw new ApiException(HttpStatus.BAD_REQUEST,"Single rooms are for 1 person");
                        }
                        else if(peopleAmount > 2  && roomType.equals("Doble")){
                            throw new ApiException(HttpStatus.BAD_REQUEST,"Doble rooms are for up to 2 people");
                        }
                        else if(peopleAmount > 3 && roomType.equals("Triple")) {
                            throw new ApiException(HttpStatus.BAD_REQUEST,"Triple rooms are for up to 3 people");
                        }
                        else if(peopleAmount >= 10 && roomType.equals("Multiple")) {
                            throw new ApiException(HttpStatus.BAD_REQUEST,"Multiple rooms are for up to 4 people");
                        }
                    }else throw new ApiException(HttpStatus.BAD_REQUEST,"Empty roomType");
                }else throw new ApiException(HttpStatus.BAD_REQUEST,"Missing roomType Parameter");
            }else throw new ApiException(HttpStatus.BAD_REQUEST,"People amount must be between 1 and 10");
        }else throw new ApiException(HttpStatus.BAD_REQUEST,"Missin PeopleAmount");
    }

    @Override
    public void validateBooking(PayloadHotelBookingDTO payloadHotelBook) throws ApiException {
        HotelDTO hotel = new HotelDTO();
        if(payloadHotelBook.getBooking() != null){
            if (payloadHotelBook.getBooking().getHotelCode() != null){
                if(!payloadHotelBook.getBooking().getHotelCode().equals("")){
                    boolean hotelCodeMatch = false;
                    for (int i = 0; i < hotelsRepository.getAll().size(); i++) {
                        if(payloadHotelBook.getBooking().getHotelCode().equals(
                                hotelsRepository.getAll().get(i).getCodeHotel())){
                            hotelCodeMatch = true;
                            hotel = hotelsRepository.getAll().get(i);
                        }
                    }

                    if(!hotelCodeMatch) throw new ApiException(HttpStatus.BAD_REQUEST,"Invalid Hotel Code");
                    validateRoomTypeAndPeople(hotel.getRoomType(),payloadHotelBook.getBooking().getPeopleAmount());
                    validateDestination(payloadHotelBook.getBooking().getDestination(),hotel.getCity());
                    validateHotelRoom(hotel,payloadHotelBook.getBooking().getRoomType());
                    validateDateFrom(payloadHotelBook.getBooking().getDateFrom(),hotel);
                    validateDateTo(payloadHotelBook.getBooking().getDateTo(),hotel);
                }else throw new ApiException(HttpStatus.BAD_REQUEST,"Empty Hotel Code");
            }else throw new ApiException(HttpStatus.BAD_REQUEST,"Missing Hotel Code");
        }else throw new ApiException(HttpStatus.BAD_REQUEST,"Missing Booking");
    }

    @Override
    public void validateFlightReservation(PayloadFlightReservDTO flightReserv) throws ApiException {
        FlightDTO flight = new FlightDTO();

        if(flightReserv.getFlightReservation() != null) {
            if (flightReserv.getFlightReservation().getFlightNumber() != null) {
                if (!flightReserv.getFlightReservation().getFlightNumber().equals("")) {
                    boolean flightMatch = false;
                    for (int i = 0; i < flightsRepository.getAll().size(); i++) {
                        if (flightReserv.getFlightReservation().getFlightNumber()
                                .equals(flightsRepository.getAll().get(i).getFlightNumber())) {
                            flightMatch = true;
                            flight = flightsRepository.getAll().get(i);
                        }
                    }
                    if (!flightMatch) throw new ApiException(HttpStatus.BAD_REQUEST, "Invalid Flight Number");
                    validateFlightDate(flightReserv.getFlightReservation().getDateFrom(), flight.getDateFrom());
                    validateFlightDate(flightReserv.getFlightReservation().getDateTo(), flight.getDateTo());
                    validateDestination(flightReserv.getFlightReservation().getDestination(), flight.getDestination());
                    validOrigin(flightReserv.getFlightReservation().getOrigin(), flight.getOrigin());
                    validateSeatType(flightReserv.getFlightReservation().getSeatType(),flight.getSeatType());
                }else throw new ApiException(HttpStatus.BAD_REQUEST,"Empty Flight Number");
            }else throw new ApiException(HttpStatus.BAD_REQUEST,"Missing Flight Number");
        }else throw new ApiException(HttpStatus.BAD_REQUEST,"Missing Flight Reservation");
    }
}
