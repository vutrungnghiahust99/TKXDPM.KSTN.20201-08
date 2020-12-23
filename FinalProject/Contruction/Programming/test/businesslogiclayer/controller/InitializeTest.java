package businesslogiclayer.controller;

import dataaccesslayer.BikeDAO;
import entities.Bike;
import entities.Dock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class InitializeTest {
    private InitializeController initializeController;
    @BeforeEach

    @Test
    public void testGetDocks() {
        ArrayList<Dock> s = InitializeController.getDocks();
        assertEquals(12, s.size());
    }

    @ParameterizedTest
    @CsvSource({
            "C1,4",
            "HUST,5",
            "HLU,4",
            "FTU,2"
    })
    public void testGetBikes(String dockID, int expectNumberOfBikes){
        ArrayList<Bike> s = InitializeController.getBikes(dockID);
        assertEquals(expectNumberOfBikes, s.size());
    }

    @ParameterizedTest
    @CsvSource({
            "C1,4",
            "HUST,5",
            "HLU,4",
            "FTU,2"
    })
    public void testTableToBikes(String dockID, int expectNumberOfBikes){
        ArrayList<ArrayList<String>> bikeTable = BikeDAO.queryWithDockID(dockID);
        ArrayList<Bike> s =  InitializeController.tableToBikes(bikeTable);
        assertEquals(expectNumberOfBikes, s.size());
    }
}
