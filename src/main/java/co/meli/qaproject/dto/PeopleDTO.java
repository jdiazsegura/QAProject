package co.meli.qaproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PeopleDTO {
    String dni;
    String name;
    String lastName;
    String birthDate;
    String mail;
}
