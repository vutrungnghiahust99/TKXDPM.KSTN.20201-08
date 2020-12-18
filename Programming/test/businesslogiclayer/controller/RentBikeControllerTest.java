package businesslogiclayer.controller;

import entities.Bike;
import javafx.util.Pair;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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

//    @Test
//    void convertBikeCodeToRentalCode() {
//    }
//
//    @Test
//    void calculateDeposit() {
//    }
}