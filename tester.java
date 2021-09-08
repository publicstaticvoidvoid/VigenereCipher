import edu.duke.*;
/**
 * Write a description of tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class tester {
    
    public void testerMethod(){
        
        CaesarCipher cc = new CaesarCipher(2);
        char inputTest = 'c';
        
        System.out.println("***Here we test a single character,"+ inputTest +" and encrypt");
        
        char encryptedInputTest= cc.encryptLetter(inputTest);
        System.out.println("encryptedInputTest="+encryptedInputTest);
        
        char decryptInputTest = cc.decryptLetter(encryptedInputTest);
        System.out.println("decryptInputTest ="+decryptInputTest);
        
        System.out.println("***Now we encrypt and decrypt the titus-small.txt");
        FileResource fr = new FileResource("VigenereTestData/titus-small.txt");
        String input = fr.asString();
        System.out.print("input="+input);
        String inputEncrypt= cc.encrypt(input);
        
        System.out.println("inputEncrypt="+inputEncrypt);
        
        String inputDecrypt = cc.decrypt(inputEncrypt);
        
        System.out.println("inputDecrypt="+inputDecrypt);
        
        System.out.println("***Here we try the CaesarCracker Method to get a key and decrypt a message");
        CaesarCracker cCracker = new CaesarCracker();
        //int cCrackerkey1 = cCracker.getKey(inputEncrypt);
        //System.out.println("cCrackerkey1="+cCrackerkey1);
        
        
        FileResource fr2 = new FileResource("VigenereTestData/titus-small_key5.txt");
        String stringTitusSmall_key5 = fr2.asString();
        
        int cCrackerkeytitusSmall_key5 = cCracker.getKey(stringTitusSmall_key5);
        
        System.out.println("cCrackerkeytitusSmall_key5 ="+cCrackerkeytitusSmall_key5 );
        String stringDecryptTitusSmall_key5 = cCracker.decrypt(stringTitusSmall_key5);
        
        System.out.println("stringDecryptTitusSmall_key5 ="+stringDecryptTitusSmall_key5 );
        
        System.out.println("***We will now decrypt the portugese file oslusiadas_key17.txt");
        CaesarCracker  cCrackerPortuguese = new CaesarCracker('a');
        FileResource fr3 = new FileResource("VigenereTestData/oslusiadas_key17.txt");
        String stringOslusiadas= fr3.asString();
        int keyStringOslusiadas= cCrackerPortuguese.getKey(stringOslusiadas);
    
        
        
        String decryptStringOslusiadas = cCrackerPortuguese.decrypt(stringOslusiadas);
        
        System.out.println("keyStringOslusiadas="+keyStringOslusiadas);
        System.out.println("decryptStringOslusiadas="+decryptStringOslusiadas);
        
    }

}
