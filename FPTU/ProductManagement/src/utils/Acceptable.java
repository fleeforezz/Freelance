/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

/**
 *
 * @author jso
 */
public class Acceptable {
    public static final String CLUB_VALID_ID = "^CL-\\d{4}$"; // CL-xxxx (e.g., CL-0001) 
    
    public static final String PLAYER_VALID_ID = "^P\\d{4}$"; // Pxxxx (e.g., P0001)
    
    public static final String[] AVAILABLE_POSITION = {
        "Goalkeeper",
        "Defender",
        "Midfielder",
        "Forward",
        "Winger"
    };

    public static boolean isValid(String data, String pattern) {
        return data.matches(pattern);
    }
}
