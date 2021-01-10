package businesslogiclayer.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class CheckBarcodeControllerTest {


    @ParameterizedTest
    @CsvSource({
            ",false",
            "34ff23f23f, false",
            "20200001,true",
            "123413,false"
    })
    void checkBarcodeAndGetBikeIfTrue(String barcode, Boolean expected) {
        CheckBarcodeController checkBarcodeController = new CheckBarcodeController();
        Boolean isValid = checkBarcodeController.checkBarcodeAndGetBikeIfTrue(barcode).getKey();
        assertEquals(expected, isValid);
    }
}