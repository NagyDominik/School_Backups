/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package questioner;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dominik
 */
public class EntryStorage {
    private static List<String> entries = new ArrayList<>();
    
    private boolean found = false;
    //private static String[] entries = new String[100];
    //private Questioner qtr = new Questioner();
     
    public void addToList(String name, int score){
        if (entries.size() > 0) {
            for (int i = 0; i < entries.size(); i++) {
                if (entries.get(i).toString().contains(name)) {
                    entries.set(i, name + ": " + score);
                    found = true;
                    break;
                }
            }
        }
        if (!found) {
            entries.add(name + ": " + score);
        }
        
    }
    
    public List<String> getEntries(){
        return entries;
    }
    
}
