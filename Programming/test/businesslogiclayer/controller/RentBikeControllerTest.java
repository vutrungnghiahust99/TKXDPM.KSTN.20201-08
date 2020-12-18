package businesslogiclayer.controller;

import entities.Bike;
import javafx.util.Pair;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class RentBikeControllerTest {

//    @BeforeEach
//    void setUp() {
//    }

    @ParameterizedTest
    @CsvSource({
            "20200001,true",
            "123413,false"
    })
    void checkBarcodeAndGetBikeIfTrue(int barcode, Boolean expected) {
        Boolean isValid = RentBikeController.checkBarcodeAndGetBikeIfTrue(barcode).getKey();
        assertEquals(expected, isValid);
    }

    @ParameterizedTest
    @CsvSource({
            "20200001, 20200001",
            "20200010, 20200010",
    })
    void convertBikeCodeToRentalCode(int bikeCode, String expected) {
        String isValid  = RentBikeController.convertBikeCodeToRentalCode(bikeCode);
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = df.format(date);
        System.out.println(isValid + " " + expected + dateString);
        assertEquals(expected+dateString, isValid);
    }
}