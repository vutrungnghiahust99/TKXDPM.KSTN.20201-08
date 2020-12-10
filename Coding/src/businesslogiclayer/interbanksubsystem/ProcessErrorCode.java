package businesslogiclayer.interbanksubsystem;

public class ProcessErrorCode {
    public String process(String code){
        if (code.equals("00")){
            return "Giao dịch thành công";
        }
        else if (code.equals("01")){
            return "Thẻ không hợp lệ";
        }
        else if (code.equals("02")){
            return "Thẻ không đủ số dư";
        }
        else if (code.equals("03")){
            return "Internal Server Error";
        }
        else if (code.equals("04")){
            return "Giao dịch bị nghi ngờ gian lận";
        }
        else if (code.equals("05")){
            return "Không đủ thông tin giao dịch";
        }
        else if (code.equals("06")){
            return "Thiếu thông tin version";
        }
        else if (code.equals("07")){
            return "Amount không hợp lệ";
        } else {
            return "Something wrong !!!" ;
        }
    }
}
