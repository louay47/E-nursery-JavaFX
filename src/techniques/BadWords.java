/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techniques;

import java.util.List;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author mahjoub
 */
public class BadWords {
    private static BadWords badwords;
     JSONObject jsonObject;

    private BadWords() {
        JSONParser parser = new JSONParser();
        
        
        try {
            
            
            Object obj = parser.parse(new FileReader("src/techniques/Words.json"));
            jsonObject = (JSONObject) obj;
            
            
        } catch (IOException ex) {
            Logger.getLogger(BadWords.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(BadWords.class.getName()).log(Level.SEVERE, null, ex);
        }

          
    }
    
    
    
    public static BadWords getInstance()
    {
        if(badwords ==null)
        {
            badwords=new BadWords();
        }
        return badwords;
    }
    
    
    public void  ShowBadWords() {
        
        JSONArray msg = (JSONArray) jsonObject.get("bad");
            Iterator<String> iterator = msg.iterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }
    }
    
    public void edit(String ss)
    {
        String [] s=ss.trim().split(" ");
        JSONArray list = (JSONArray) jsonObject.get("bad");
        for(int i=0; i< s.length; i++)
         {
            list.add(s[i]);
          }
       
        

        jsonObject.put("bad", list);

        try (FileWriter file = new FileWriter("src/techniques/Words.json")) {

            file.write(jsonObject.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

   
    }
    
    public  boolean existe(String s)
    {
        JSONArray msg = (JSONArray) jsonObject.get("bad");
            Iterator<String> iterator = msg.iterator();
            while (iterator.hasNext()) {
                if(s.contains(iterator.next()))
                return true;
            }
       
        return false;
    }
    public  int nb(String s)
    {
        
        int j=0;
        
        
        JSONArray msg = (JSONArray) jsonObject.get("bad");
            Iterator<String> iterator = msg.iterator();
            while (iterator.hasNext()) {
                if(s.indexOf(iterator.next())>=0)
                j++;
            }
       
        
        return j;
    }
    
}
