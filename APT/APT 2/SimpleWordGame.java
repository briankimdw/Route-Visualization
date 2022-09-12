import java.util.*;
public class SimpleWordGame {
    public int points(String[] player, 
                      String[] dictionary) {

        int score = 0;
        
        Set<String> set = new HashSet<>();
        for(String s: player){
            set.add(s);
        }

        for(String s: set){
            for(int i = 0; i < dictionary.length; i ++){
                if(s.equals(dictionary[i])){
                    score += dictionary[i].length() * dictionary[i].length();
                }
            }
        }
        return score;
    }
}
        
