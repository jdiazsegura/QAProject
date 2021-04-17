package co.meli.qaproject.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidationsTest {

    Validations validations = new Validations();
    @Test
    void validateDateTrue() {
        String date = "01/02/1997";
        assertEquals(true,validations.validateDate(date));
    }

    @Test
    void validateDateFalse(){
        String date = "01/02/19997";
        String date2 = "01/002/1997";
        String date3 ="001/02/1997";
        assertEquals(false,validations.validateDate(date));
        assertEquals(false,validations.validateDate(date2));
        assertEquals(false,validations.validateDate(date3));
    }
}