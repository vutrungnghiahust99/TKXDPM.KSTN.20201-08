package businesslogiclayer.barcodconvertersubsystem;

public class BarcodeConverterController implements IBarcodeConverter{
    public int convertBarcodeToBikeCode(int Barcode){
        int BikeCode = Barcode;
        System.out.println("Đã convert Barcode");
        return BikeCode;
    }
}
