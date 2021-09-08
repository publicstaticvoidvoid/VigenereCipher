import edu.duke.*;
import java.util.*;

public class VigenereCipher {
    CaesarCipher[] ciphers;
    
    public VigenereCipher(int[] key) {
        ciphers = new CaesarCipher[key.length];
        for (int i = 0; i < key.length; i++) {
            ciphers[i] = new CaesarCipher(key[i]);
        }
    }
    

    public String encrypt(String input) {
        StringBuilder answer = new StringBuilder();
        int i = 0;
        for (char c : input.toCharArray()) {
            int cipherIndex = i % ciphers.length;
            CaesarCipher thisCipher = ciphers[cipherIndex];
            answer.append(thisCipher.encryptLetter(c));
            i++;
        }
        return answer.toString();
    }
    
    public String decrypt(String input) {
        StringBuilder answer = new StringBuilder();
        int i = 0;
        for (char c : input.toCharArray()) {
            int cipherIndex = i % ciphers.length;
            CaesarCipher thisCipher = ciphers[cipherIndex];
            answer.append(thisCipher.decryptLetter(c));
            i++;
        }
        return answer.toString();
    }
    
    public String toString() {
        return Arrays.toString(ciphers);
    }
    
    public String sliceString(String message, int whichSlice, int totalSlices){
      int i=0;
       StringBuilder sb = new StringBuilder();
        while(i<message.length()){
            //ArrayList<Character> charHolder = new ArrayList<Character>();
           
        for(i=whichSlice;i<message.length();i+=totalSlices){
            
           char currChar= message.charAt(i);
           sb.append(currChar);
        }
        
    }
    return sb.toString();
}
    
public ArrayList<Integer> tryKeyLength(String encrypted, int klength , char mostCommon){
  ArrayList<Integer> keyHolder = new ArrayList<Integer>();
    for(int i=0; i<klength;i+=1){
    
  String  currString = sliceString(encrypted, i, klength);
    
    // break currString
CaesarCracker cc= new  CaesarCracker(mostCommon) ;
    int currKey = cc.getKey(currString);
    keyHolder.add(currKey);
    
}
return keyHolder;

}


}
