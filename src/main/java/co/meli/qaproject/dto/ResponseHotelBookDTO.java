package co.meli.qaproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseHotelBookDTO {
    String username;
    Double amount;
    Double interest;
    Double total;
    BookingDTO booking;
    StatusCodeDTO statusCode;

    public ResponseHotelBookDTO(PayloadHotelBookDTO payloadHotelBook) {
        this.username = payloadHotelBook.getUserName();
        this.booking = payloadHotelBook.getBooking();
    }
}
