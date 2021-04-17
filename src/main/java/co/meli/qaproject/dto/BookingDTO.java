package co.meli.qaproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDTO {
    String dateFrom;
    String dateTo;
    String destination;
    String hotelCode;
    Integer peopleAmount;
    String roomType;
    List<PeopleDTO> people;
    PaymentMethodDTO paymentMethod;
}
