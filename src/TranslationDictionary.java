
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * This is a Dictionary class for Translation objects,
 * it contains index and searching functions
 * @author orion
 */
public class TranslationDictionary {
    private ArrayList<Translation> dic;
    
    TranslationDictionary(Translation trans) {
        dic = new ArrayList();
        dic.add(trans);
    }
    
    TranslationDictionary(Translation[] transList) {
        dic = new ArrayList();
        for (Translation tran : transList) {
            dic.add(tran);
        }
    }
    
    TranslationDictionary() {
        dic = new ArrayList();
    }
    
    /* Linear Search */
    public String getPictureAddress(String meaning) {
        try {
            for (Translation tran : dic) {
                if (tran.getMeaning() == meaning)
                    return tran.getPictureAddress();
            }
        } catch (NullPointerException ex) { //In case there is no member in 
            //the ArrayList
            return "";
        }
        return "";
    }
}
