/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author orion
 */
public class Translation {
    private String meaning;
    private String pictureAddress;
    
    Translation(String address, String meaning) {
        meaning = meaning;
        pictureAddress = address;
    }
    
    String getMeaning() {
        return meaning;
    }
    
    String getPictureAddress() {
        return pictureAddress;
    }
    
    
}
