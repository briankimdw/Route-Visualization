public class DNAMaxNucleotide {
    public String max(String[] strands, String nuc) {
      char a = nuc.charAt(0);
      String longest = "";
      int maxC = 0;
      int count = 0;
      for(int i = 0; i < strands.length; i++){
            for(int j = 0; j < strands[i].length(); j ++){
                  if(strands[i].charAt(j) == a){
                        count += 1;
                  }
            if(count > maxC){
                  maxC = count;
                  longest = strands[i];
            }
            else if(count == maxC & count != 0){
                  if(strands[i].length() > longest.length()){
                        longest = strands[i];
                  }
            }
            }
            
      }
      return longest;
    }
 }