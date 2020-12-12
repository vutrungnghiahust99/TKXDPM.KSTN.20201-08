package businesslogiclayer.interbanksubsystem;

import businesslogiclayer.interbanksubsystem.*;
import entities.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class InterbankSubsysController implements IInterbank {
//    private Card card = Card.getInstance()
    public ConvertToTransaction convertToTransaction = new ConvertToTransaction();
    public ProcessErrorCode processErrorCode = new ProcessErrorCode();

    @Override
    public String processTransaction(int cost, String command, String content){
        try {
            InterbankTransaction transaction;
            transaction = convertToTransaction.convertToPaymentTransaction(cost, command, content);
            // Chuyển transaction sang Json object
            String transactString = new ObjectMapper().writeValueAsString(transaction);
            JsonObject transactionBody = new JsonParser().parse(transactString).getAsJsonObject();
            JsonObject transToHash = new JsonObject();

            // tạo request transaction gửi lên API
            JsonObject sentJson = convertToTransaction.requestTransaction(transToHash, transactionBody);
            InterbankBoundary interbank = new InterbankBoundary();
            String errorCode = interbank.processTransaction(sentJson);
            processErrorCode.process(errorCode);
            return errorCode; // return errorCode

        }catch (Exception e) {
            System.out.println("Xử lý giao dịch lỗi anh ơi!, intercontroller");
            return "08";
        }
    }

    @Override
    public int viewBalance(){
        return 0;
    }

    @Override
    public void reset(){
        JsonObject body = new JsonObject();
        body = convertToTransaction.resetTransaction(body);
        InterbankBoundary interbank = new InterbankBoundary();
        String errorCode = interbank.reset(body);
        if (errorCode.equals("00")) {
            System.out.println("Reset balance thành công rồi sếp ơi!");
        }
        else{
            System.out.println("Lối reset!");
        }
    }
}
