/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techniques;

import java.io.FileOutputStream;
import com.voicerss.tts.AudioCodec;
import com.voicerss.tts.AudioFormat;
import com.voicerss.tts.Languages;
import com.voicerss.tts.SpeechDataEvent;
import com.voicerss.tts.SpeechDataEventListener;
import com.voicerss.tts.SpeechErrorEvent;
import com.voicerss.tts.SpeechErrorEventListener;
import com.voicerss.tts.VoiceParameters;
import com.voicerss.tts.VoiceProvider;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

/**
 *
 * @author mahjoub
 */
public class VOICE_RSS {
/**
 * 
 * @param text ==> speech text
 * @param lang ==> speech language {fr  OR eng}
 */
    public VOICE_RSS(String text,String lang) {
        VoiceProvider tts = new VoiceProvider("3ca81d6832ef4ae4b910a00790b8610c");
        VoiceParameters params= null;
        if(lang.equals("fr")){
             params = new VoiceParameters(text, Languages.French_France);
        }
         
        if(lang.equals("eng")){
             params = new VoiceParameters(text, Languages.English_UnitedStates);
        }
        params.setCodec(AudioCodec.MP3);
        params.setFormat(AudioFormat.Format_44KHZ.AF_44khz_16bit_stereo);
        params.setBase64(false);
        params.setSSML(false);
        params.setRate(0);
		
        byte[] voice;
        try {
            voice = tts.speech(params);
            FileOutputStream fos = new FileOutputStream("a.mp3");
            fos.write(voice, 0, voice.length);
            fos.flush();
            fos.close();
        } catch (Exception ex) {
            Logger.getLogger(VOICE_RSS.class.getName()).log(Level.SEVERE, null, ex);
        }
		
        
	
         try
         {
          
          FileInputStream fis = new FileInputStream("a.mp3");
         
             Player player =new Player(fis);
             player.play();
             System.out.println("nice work");
         }
         catch(JavaLayerException ex)
         {
             System.out.println(ex.getMessage());
         }
         catch(IOException e)
         {
             System.out.println(e.getMessage());
         }
    }
    
    
    /**
     * 
     * @param path ==> absolute path of file mp3
     */
     public VOICE_RSS(String path) {       
         try
         {
          
          FileInputStream fis = new FileInputStream(path+".mp3");
         
             Player player =new Player(fis);
             player.play();
             System.out.println("nice work");
         }
         catch(JavaLayerException ex)
         {
             System.out.println(ex.getMessage());
         }
         catch(IOException e)
         {
             System.out.println(e.getMessage());
         }
    }
}
