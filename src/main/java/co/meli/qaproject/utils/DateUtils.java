package co.meli.qaproject.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    public LocalDate normaliceDate(String date){
        var formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(date,formatter);
    }

    public Integer comparable(LocalDate date, LocalDate date2){
        if(date.compareTo(date2) == 0){
            return 0;
        }else if(date.compareTo(date2) >0){
            return 1;
        }else if(date.compareTo(date2) < 0){
            return -1;
        }
        return 0;
    }
}
