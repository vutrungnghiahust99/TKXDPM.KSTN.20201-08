package businesslogiclayer.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

public class ValidateInitializeController {
    private InitializeController initializeController;
    @BeforeEach
//    void setUp() throws Exception {
////        placeOrderController = new PlaceOrderController();
//    }

    @ParameterizedTest
    @CsvSource({
            "10,10",
            "20,20",
            "30,30",
            "40,40"
    })
    public void test(int x, int expect) {
        int y = InitializeController.dummy(x);
        assertEquals(expect, x);
    }
}
