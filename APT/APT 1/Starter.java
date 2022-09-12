import java.util.*;
public class Starter {
    public int begins(String[] words, String first) {
        int count = 0;
        Set<String> set = new HashSet<>();
        for(String s: words){
            set.add(s);
        }
        for(String s: set){
            if(s.indexOf(first) == 0){
                count ++;

            }
        }
        return count;
    }
}

