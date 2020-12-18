package businesslogiclayer.barcodconvertersubsystem;

/**
 * interface cho Barcode Converter Subsystem
 */
public interface IBarcodeConverter {
    /**
     * Chuyển barcode thành bikeCode
     *
     * @param barcode : barcode người dùng nhập vào
     * @return bikeCode
     */
    int convertBarcodeToBikeCode(int barcode);
}
