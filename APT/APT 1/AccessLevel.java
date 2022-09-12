public class AccessLevel {
    public String canAccess(int[] rights, int minPermission) {
        String result = "";
        for(int i = 0; i < rights.length; i++){
                if(rights[i] >= minPermission){
                    result += "A";
                }
                else{
                    result += "D";
                }


           }
        return result;
    }
 }