/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techniques;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
/**
 *
 * @author mahjoub
 */
public class SMS {
    public static final String ACCOUNT_SID = "ACbf5f236db556a91fa3e9bdb7a8f9e4f4";
    public static final String AUTH_TOKEN = "f575394a273d288be31676bc3ab2dd9b";

    public static void SMS(int num,String mess) {
    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        
        Message message;
        message= Message
                .creator(new PhoneNumber("+216"+num), new PhoneNumber("+19723621879"), mess).create();
                
    }

    
}
