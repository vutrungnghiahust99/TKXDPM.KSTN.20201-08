package businesslogiclayer.barcodconvertersubsystem;

import com.google.gson.JsonObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class BarcodeConverterBoundaryTest {

    @BeforeEach
    void setUp() {
    }

    @ParameterizedTest
    @CsvSource({
            "20200001, 20200001"
    })
    void convertBarcodeToBikeCode(int barcode, String expected) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("barcode", String.valueOf(barcode));
        String bikeCode = BarcodeConverterBoundary.convertBarcodeToBikeCode(jsonObject);
        assertEquals(bikeCode, expected);
    }
}