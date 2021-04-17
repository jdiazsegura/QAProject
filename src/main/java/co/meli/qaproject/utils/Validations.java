package co.meli.qaproject.utils;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Validations {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public Boolean validateDate(String date){
        try{
            LocalDate.parse(date,formatter);
        }catch (DateTimeException e){
            return false;
        }
        return true;
    }

    public Boolean validateDateTo(String dateTo, String dateFor){
        var dateVal = LocalDate.parse(dateTo,formatter);
        var date2Val = LocalDate.parse(dateFor,formatter);
        if( dateVal.isBefore(date2Val)){
            return true;
        }
        return false;
    }
    public Boolean validateDateFor(String dateTo, String dateFor){
        var dateVal = LocalDate.parse(dateTo,formatter);
        var date2Val = LocalDate.parse(dateFor,formatter);
        if(date2Val.isAfter(dateVal)){
            return true;
        }
        return false;
    }
}
