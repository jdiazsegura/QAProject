package co.meli.qaproject.utils;

import co.meli.qaproject.dto.hotels.HotelDTO;
import co.meli.qaproject.dto.hotels.PayloadHotelBookingDTO;
import co.meli.qaproject.exceptions.ApiException;

public interface IValidations {
    void validateEmail(String email) throws ApiException;

    void validateHotelRoom(HotelDTO hotel, String roomType) throws ApiException;

    void validateDestination(String destination, String validDestination) throws ApiException;

    void validOrigin(String origin, String validOrigin) throws ApiException;

    void validateDateFrom(String date, HotelDTO hotel) throws ApiException;

    void validateDateTo(String date, HotelDTO hotel) throws ApiException;

    void validateFlightDate(String date, String validDate) throws ApiException;

    void validateSeatType(String seat, String validSeat) throws ApiException;

    void validateRoomTypeAndPeople(String roomType, Integer peopleAmount) throws ApiException;

    void validateBooking(PayloadHotelBookingDTO payloadHotelBook) throws ApiException;

    void validateFlightReservation(co.meli.qaproject.dto.flights.PayloadFlightReservDTO flightReserv) throws ApiException;
}
