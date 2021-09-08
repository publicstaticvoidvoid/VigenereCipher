import edu.duke.*;
import java.util.*;
/**
 * Write a description of testerVigenereCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class testerVigenereCipher {

    public void testmethod(){
    int[] key = {17, 14, 12, 4};
    VigenereCipher vC = new VigenereCipher(key);
    
    //String testString = FileResource("VigenereTestData/titus-small.txt").asString();
    FileResource fr = new FileResource("VigenereTestData/titus-small.txt");
    String testString = fr.asString();
    
    String enc = vC.encrypt(testString);
     
    System.out.println("enc="+enc);
    
    String dec = vC.decrypt(enc);
    
    System.out.println("dec="+dec);
    
    String keyVc = vC.toString();
    
    System.out.println("keyVc="+keyVc);
    
    System.out.println(vC.sliceString("abcdefghijklm", 0, 3));
    System.out.println(vC.sliceString("abcdefghijklm", 1, 3));
    
    FileResource fr3 = new FileResource("VigenereTestData/athens_keyflute.txt");
    String encrypted = fr3.asString();
    int klength= "flute".length();
    char mostCommon = 'e';
    
    ArrayList<Integer> keyArray =  vC.tryKeyLength(encrypted,klength , mostCommon);
    
    System.out.println(keyArray);
}
}
