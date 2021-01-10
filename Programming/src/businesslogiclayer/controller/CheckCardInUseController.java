package businesslogiclayer.controller;

import dataaccesslayer.CardDAO;

import java.util.ArrayList;

/**
 * Kiem tra xem the co dang duoc su dung hay khong
 */
public class CheckCardInUseController {
    /**
     * @param cardCode chua thong tin the nguoi dung
     * @return ket qua kiem tra xem the co dang duoc su dung hay khong
     */
    public boolean checkCardInUse(String cardCode){
        boolean check = false;
        ArrayList<ArrayList<String>> cardInUse = CardDAO.getAllCardInUse();
        for (ArrayList<String> card : cardInUse){
            if (card.get(0).equals(cardCode)){
                check = true;
                break;
            }
        }
        return check;
    }
}
