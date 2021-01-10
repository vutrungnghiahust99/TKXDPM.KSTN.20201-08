package businesslogiclayer.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class CheckCardInUseControllerTest {

    @BeforeEach
    void setUp() {

    }
    @ParameterizedTest
    @CsvSource({
            "118131_group7_2020, true",
            "118131_group8_2020, false"
    })

    void checkCardInUse(String cardCode, Boolean expected) {
        CheckCardInUseController checkCardInUseController = new CheckCardInUseController();
        Boolean check = checkCardInUseController.checkCardInUse(cardCode);
        assertEquals(expected, check);
    }


}