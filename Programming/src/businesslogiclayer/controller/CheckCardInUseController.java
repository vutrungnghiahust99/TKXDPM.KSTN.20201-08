package businesslogiclayer.controller;

import dataaccesslayer.CardDAO;

import java.util.ArrayList;

public class CheckCardInUseController {
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
