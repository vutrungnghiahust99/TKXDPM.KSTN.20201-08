package businesslogiclayer.interbanksubsystem;

import entities.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class InterbankSubsysController implements IInterbank {
    public ConvertToTransaction convertToTransaction = new ConvertToTransaction();
//    public ProcessErrorCode processErrorCode = new ProcessErrorCode();

    /**
     * @param cost : giá tiền cần giao dịch
     * @param command : yêu cầu giao dịch {"pay", "refund"}
     * @param content : nội dung giao dịch
     * @return : mã error code mà api gửi lại cho hệ thống
     */

    @Override
    public String processTransaction(int cost, String command, String content){
        try {
            InterbankTransaction transaction;
            transaction = convertToTransaction.convertToPaymentTransaction(cost, command, content);
            String transactString = new ObjectMapper().writeValueAsString(transaction);
            JsonObject transactionBody = new JsonParser().parse(transactString).getAsJsonObject();
            JsonObject transToHash = new JsonObject();
            JsonObject sentJson = convertToTransaction.requestTransaction(transToHash, transactionBody);
            InterbankBoundary interbank = new InterbankBoundary();
            String errorCode = interbank.processTransaction(sentJson);
            return errorCode;

        }catch (Exception e) {
            System.out.println("Xử lý giao dịch lỗi!, intercontroller");
            return "08";
        }
    }


    /**
     * Reset tiền trong tài khoản người dùng
     */
    @Override
    public void reset(){
        JsonObject body = new JsonObject();
        body = convertToTransaction.resetTransaction(body);
        InterbankBoundary interbank = new InterbankBoundary();
        String errorCode = interbank.reset(body);
        if (errorCode.equals("00")) {
            System.out.println("Reset balance thành công!");
        }
        else{
            System.out.println("Lối reset!");
        }
    }
}
