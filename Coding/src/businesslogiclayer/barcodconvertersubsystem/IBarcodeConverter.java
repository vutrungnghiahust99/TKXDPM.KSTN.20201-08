package businesslogiclayer.barcodconvertersubsystem;

/**
 * interface cho Interbank Subsystem
 * Có 2 method để hệ thống giao tiếp đó là:
 * processTransaction : xử lý giao dịch
 * reset : reset tài khoản người dùng
 */
public interface IBarcodeConverter {
    int convertBarcodeToBikeCode(int Barcode);

}
