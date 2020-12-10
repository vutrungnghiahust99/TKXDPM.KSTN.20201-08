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
            return processErrorCode.process(errorCode);

        }catch (Exception e) {
            System.out.println("Xử lý giao dịch lỗi anh ơi!");
            return "Lỗi khi xử lý giao dịch!";
        }
    }

    @Override
    public int viewBalance(){
        return 0;
    }

    @Override
    public String reset(){
        JsonObject body = new JsonObject();
        body = convertToTransaction.resetTransaction(body);
        InterbankBoundary interbank = new InterbankBoundary();
        String errorCode = interbank.reset(body);
        return processErrorCode.process(errorCode);
    }
}
