import java.util.*;
import edu.duke.*;
import java.io.*;
public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        //REPLACE WITH YOUR CODE
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

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
       /* int[] key = new int[klength];
        //WRITE YOUR CODE HERE
        return key;
        
        */
       
        //ArrayList<Integer> keyHolder = new ArrayList<Integer>();
        int[] key = new int[klength];
    for(int i=0; i<klength;i+=1){
    
  String  currString = sliceString(encrypted, i, klength);
    
    // break currString
CaesarCracker cc= new  CaesarCracker(mostCommon) ;
    int currKey = cc.getKey(currString);
    //keyHolder.add(currKey);
    key[i]=currKey;
    
}
return key;
       
       
    }

    public void breakVigenere_V1 () {
        //WRITE YOUR CODE HERE
        
        FileResource fr = new FileResource ();
        String encrypted = fr.asString();
        int [] keyArray = tryKeyLength(encrypted, 4, 'e') ;
        //System.out.println(keyArray);
        System.out.println(keyArray[0]+","+keyArray[1]+","+keyArray[2]+","+keyArray[3]);
        
        
        VigenereCipher vC = new VigenereCipher(keyArray);
        String decryptedMessage = vC.decrypt(encrypted);
        
        System.out.println(decryptedMessage);
        
    }
    
    public void breakVigenere_v2(){
        FileResource fr = new FileResource(); // select file to decrypt
        String encrypted = fr.asString(); // get the file resource as string
        
        HashSet<String> dictionary = readDictionary(); 
        
        String decryptedString = breakForLanguage(encrypted, dictionary);
        System.out.println(decryptedString );
    }
    
     public void breakVigenere_v3(){
        System.out.println("Input encoded file");
         FileResource fr = new FileResource(); // select file to decrypt
        String encrypted = fr.asString(); // get the file resource as string
        
        //HashSet<String> dictionary = readDictionary(); 
        HashMap<String, HashSet<String>> languages = createLanguagesHashmap(); //. creates hashmap of languages
        breakForAllLangs(encrypted , languages);
        
        
    }
    
    public HashSet<String> readDictionary(){
        HashSet<String> dictionaryHashSet = new HashSet<String>();
        
        //FileResource fr = new FileResource("dictionaries/English");// selects the dictionary
         FileResource fr = new FileResource();// selects the dictionary
        //fr.words();
        for(String word: fr.words()){
            
            word = word.toLowerCase();
            dictionaryHashSet.add(word);
        }
        
        //System.out.println(dictionaryHashSet.size());
        return dictionaryHashSet;
        
    }
    
    public HashSet<String> readDictionaryFile(String f){
        HashSet<String> dictionaryHashSet = new HashSet<String>();
        
        //FileResource fr = new FileResource("dictionaries/English");// selects the dictionary
         FileResource fr = new FileResource(f);// selects the dictionary
        //fr.words();
        for(String word: fr.words()){
            
            word = word.toLowerCase();
            dictionaryHashSet.add(word);
        }
        
        //System.out.println(dictionaryHashSet.size());
        return dictionaryHashSet;
        
    }
    
    public int  countWords(String message, HashSet<String> dictionary ){
        int counter = 0;
        for(String s: message.split("\\W+")){
            
            s=s.toLowerCase();
            
         if(dictionary.contains(s)){
             counter+=1;
            }
        }
        
        return counter;
    }
    
    public String breakForLanguage(String encrypted, HashSet<String> dictionary){
        int maxDictionaryHits = 0;
        int likelyLeyLength=0;
        for(int i=1;i<100; i+=1){
            
            //int [] currKey = tryKeyLength(encrypted, i, 'e');
            int [] currKey = tryKeyLength(encrypted, i, mostCommonCharIn(dictionary));
             
            
            VigenereCipher vC = new VigenereCipher(currKey);
            String currDecrtypString = vC.decrypt(encrypted);
            
            int currCountWords = countWords(currDecrtypString , readDictionary() );
            //int currCountWords = countWords(currDecrtypString , readDictionaryFile("dictionaries/"+f) );
            if(currCountWords > maxDictionaryHits ){
                
                maxDictionaryHits = currCountWords;
                likelyLeyLength = i;
            }
        }
        
        int [] likelyKeyArray = tryKeyLength(encrypted, likelyLeyLength, 'e');
        
        
        VigenereCipher vCMostLikely = new VigenereCipher(likelyKeyArray);
        
        System.out.println("likelyKeyLength="+likelyLeyLength);
        String printKeyArray ="";
        for(int j= 0;j<likelyLeyLength;j+=1){
            if(printKeyArray==""){
        printKeyArray= printKeyArray+likelyKeyArray[j];
    }
    
    else {
        printKeyArray= printKeyArray+","+likelyKeyArray[j];
    }
    
    }
    System.out.println("printKeyArray="+printKeyArray);
    // get the count of words in encrypted
    
    int countOfWordsInEncrypted=0;
    for(String s: encrypted.split("\\W+")){
        countOfWordsInEncrypted +=1;
    }
    System.out.println("This file contains "+maxDictionaryHits+" valid words out of "+countOfWordsInEncrypted);
    
        return vCMostLikely.decrypt(encrypted);
    }
    
        public String breakForLanguage2(String encrypted, HashSet<String> dictionary, String lang){
        int maxDictionaryHits = 0;
        int likelyLeyLength=0;
        for(int i=1;i<100; i+=1){
            
            //int [] currKey = tryKeyLength(encrypted, i, 'e');
            int [] currKey = tryKeyLength(encrypted, i, mostCommonCharIn(dictionary));
             
            
            VigenereCipher vC = new VigenereCipher(currKey);
            String currDecrtypString = vC.decrypt(encrypted);
            
            //int currCountWords = countWords(currDecrtypString , readDictionary() );
            int currCountWords = countWords(currDecrtypString , readDictionaryFile("dictionaries/"+lang) );
            if(currCountWords > maxDictionaryHits ){
                
                maxDictionaryHits = currCountWords;
                likelyLeyLength = i;
            }
        }
        
        int [] likelyKeyArray = tryKeyLength(encrypted, likelyLeyLength, 'e');
        
        
        VigenereCipher vCMostLikely = new VigenereCipher(likelyKeyArray);
        
        System.out.println("likelyKeyLength="+likelyLeyLength);
        String printKeyArray ="";
        for(int j= 0;j<likelyLeyLength;j+=1){
            if(printKeyArray==""){
        printKeyArray= printKeyArray+likelyKeyArray[j];
    }
    
    else {
        printKeyArray= printKeyArray+","+likelyKeyArray[j];
    }
    
    }
    System.out.println("printKeyArray="+printKeyArray);
    // get the count of words in encrypted
    
    int countOfWordsInEncrypted=0;
    for(String s: encrypted.split("\\W+")){
        countOfWordsInEncrypted +=1;
    }
    System.out.println("This file contains "+maxDictionaryHits+" valid words out of "+countOfWordsInEncrypted);
    
        return vCMostLikely.decrypt(encrypted);
    }
    
    public char mostCommonCharIn(HashSet<String> dictionary){
       char mostCommonCharIn='!' ;
        HashMap<Character,Integer> hm= new HashMap<Character,Integer>();
        for(String s: dictionary){
            for(char c: s.toCharArray()){
                if(!hm.containsKey(c)){
                    
                    hm.put(c,1);
                }
                
                else{
                    hm.put(c,hm.get(c)+1);
                }
            }
            int maxValue=0;
             
            for(char c: hm.keySet()){
            if(hm.get(c)>maxValue){
               maxValue = hm.get(c);
               mostCommonCharIn=c;
            }
                
            }
            
        }
        
        return  mostCommonCharIn;
    }
    
    public HashMap<String, HashSet<String>> createLanguagesHashmap(){
        HashMap<String, HashSet<String>> hm= new HashMap<String, HashSet<String>>();
        System.out.println("Select Dictionary folder");
        DirectoryResource dr = new DirectoryResource();
        for(File f: dr.selectedFiles()){
            String currName = f.getName();
            HashSet<String> currHashSet =  readDictionaryFile("dictionaries/"+currName);
            hm.put(currName,currHashSet);
        }
        return hm;
    }
     
    public void smallTest(){
         /*HashSet<String> dictionary=  readDictionary();
        
        char mostCommonCharIn = mostCommonCharIn(dictionary);
        System.out.println("mostCommonCharIn="+mostCommonCharIn);
         */
        
        HashMap<String, HashSet<String>> languages = createLanguagesHashmap();
        
        breakForAllLangs("car" , languages);
        
        
         
        
    }
    
    public void breakForAllLangs(String encrypted , HashMap<String,HashSet<String>> languages){
     
        int maxCurrWords=0;
        String maxLanguageHits="";
        String decryptedString = "";
        for(String L: languages.keySet()){
             System.out.println("Processing for "+ L);
             //String breakForLanguage(String encrypted, HashSet<String> dictionary)
            String breakForLanguage = breakForLanguage2(encrypted, languages.get(L), L);
            int  currCountWords = countWords(breakForLanguage, languages.get(L));
            String currLanguage = L;
            if(currCountWords >maxCurrWords){
                maxCurrWords = currCountWords;
                maxLanguageHits=currLanguage ;
                decryptedString=breakForLanguage;
            }
            
            
        }
        
        System.out.println("decrypted language =" + maxLanguageHits);
        System.out.println("Language hits =" + maxCurrWords);
        
        System.out.println("decryptedString="+decryptedString);
   
    }
}
