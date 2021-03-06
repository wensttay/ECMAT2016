package io.github.herocode.ecmat.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Victor Hugo <victor.hugo.origins@gmail.com>
 */
public class DateUtils {

    public static LocalDate getLocalDateFromString(String sDate) {

        if (sDate == null || sDate.trim().isEmpty()) {

            return null;
        }

        DateTimeFormatter dateTimeFormatter;
        LocalDate lDate = null;

        try {

            dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            lDate = LocalDate.parse(sDate, dateTimeFormatter);
        } catch (Exception ex1) {

            try {

                dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                lDate = LocalDate.parse(sDate, dateTimeFormatter);
            } catch (Exception ex2) {

                try {

                    dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    lDate = LocalDate.parse(sDate, dateTimeFormatter);
                } catch (Exception ex3) {

                    try {

                        dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                        lDate = LocalDate.parse(sDate, dateTimeFormatter);
                    } catch (Exception ex4) {

                        try {

                            dateTimeFormatter = DateTimeFormatter.ofPattern("ddMMyyyy");
                            lDate = LocalDate.parse(sDate, dateTimeFormatter);
                        } catch (Exception ex5) {

                        }
                    }
                }
            }
        }

        return lDate;
    }

}
