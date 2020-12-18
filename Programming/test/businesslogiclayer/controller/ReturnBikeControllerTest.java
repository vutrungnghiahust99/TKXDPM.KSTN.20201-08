package businesslogiclayer.controller;

import entities.Bike;
import entities.RentBikeTransaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class ReturnBikeControllerTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void processReturnBike() {
    }

    @ParameterizedTest
    @CsvSource({
            "20200000,XDD-200000",
            "20200001,XDD-200001",
    })
    public void testGetBike(int bikeCode, String licensePlate){
        Bike bike = ReturnBikeController.getBike(bikeCode);
        assertEquals(licensePlate, bike.getLicensePlate());
    }

    @ParameterizedTest
    @CsvSource({
            "202000012020-12-12 10:09:39,20200001",
            "202000012020-12-12 12:09:39,20200006",
    })
    public void testGetRentBikeTransaction(String rentalCode, int expectBikeCode){
        RentBikeTransaction rentBikeTransaction = ReturnBikeController.getRentBikeTransaction(rentalCode);
        assertEquals(expectBikeCode, rentBikeTransaction.getBikeCode());
    }

    @Test
    public void testEstimateCost() {
        RentBikeTransaction rentBikeTransaction = new RentBikeTransaction(
                "yyyy-MM-dd HH:mm:ss", 20200001, "Xe Dap Dien", -1, "Group 8",
                15000, 5000, "2020-12-12 10:09:39", "", 480000
        );

        int cost = ReturnBikeController.estimateCost(rentBikeTransaction, true);
        assertEquals(25000, cost);
    }

    @Test
    void TestGetCurrentLocalDateTimeStamp() {
        String currentTime = ReturnBikeController.getCurrentLocalDateTimeStamp("yyyy-MM-dd HH:mm:ss");
        assertEquals(19, currentTime.length());
    }
}