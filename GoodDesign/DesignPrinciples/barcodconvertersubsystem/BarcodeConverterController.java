package businesslogiclayer.barcodconvertersubsystem;

import com.google.gson.JsonObject;

public class BarcodeConverterController implements IBarcodeConverter{
    public int convertBarcodeToBikeCode(int barcode){
        JsonObject body = new JsonObject();
        body.addProperty("barcode", String.valueOf(barcode));

        String bikeCode = BarcodeConverterBoundary.convertBarcodeToBikeCode(body);
        return Integer.parseInt(bikeCode);
    }
}
