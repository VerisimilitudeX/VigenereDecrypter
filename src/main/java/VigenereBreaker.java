import edu.duke.*;
import java.util.*;

public class VigenereBreaker {

  public String sliceString(String message, int whichSlice, int totalSlices) {
    StringBuilder slicing = new StringBuilder(message);
    String result = new String();
    for (int k = whichSlice; k < slicing.length(); k += totalSlices) {
      result += slicing.charAt(k);
    }
    return result;
  }

  public ArrayList<Integer> tryKeyLength(String encrypted, int klength, char mostCommon) {
    CaesarCracker CaesarCracker = new CaesarCracker('e');
    int aKey;
    ArrayList<Integer> key = new ArrayList<Integer>(klength);
    for (int k = 0; k < klength; k++) {
      aKey = CaesarCracker.getKey(sliceString(encrypted, k, klength));
      key.add(k, aKey);
    }
    return key;
  }

  public HashSet<String> readDictionary(FileResource fr) {
    HashSet<String> h = new HashSet<String>();
    for (String line : fr.lines()) {
      h.add(line.toLowerCase());
    }

    return h;
  }

  public int countWords(String message, HashSet<String> dict) {
    int counts = 0;
    ArrayList<String> MessageInWords = new ArrayList<String>(Arrays.asList(message.split("\\W")));
    for (int i = 0; i < MessageInWords.size(); i++) {
      if (dict.contains(MessageInWords.get(i).toLowerCase())) {
        counts += 1;
      }
    }
    return counts;
  }

  public String breakForLanguage(String encrypted, HashSet<String> dict) {
    int max = 0;
    ArrayList<Integer> keyReturn = new ArrayList<Integer>(100);
    int KeyLength = 0;
    String aMessage = new String();
    String largestDecryption = new String();
    for (int klength = 1; klength < 100; klength++) {
      keyReturn = tryKeyLength(encrypted, klength, 'e');
      VigenereCipher VCipher = new VigenereCipher(keyReturn);
      aMessage = VCipher.decrypt(encrypted);
      int counts = countWords(aMessage, dict);
      if (counts > max) {
        max = counts;
        largestDecryption = aMessage;
        KeyLength = klength;
      }
    }
    System.out.println("\nValid words:" + max);
    System.out.println("Key length: " + KeyLength);
    return largestDecryption;
  }

  public String breakForLanguageQuizz(String encrypted, HashSet<String> dict) {
    int max = 0;
    ArrayList<Integer> keyReturn = new ArrayList<Integer>(100);
    String aMessage = new String();
    String largestDecryption = new String();
    keyReturn = tryKeyLength(encrypted, 38, 'e');
    VigenereCipher VCipher = new VigenereCipher(keyReturn);
    aMessage = VCipher.decrypt(encrypted);
    int counts = countWords(aMessage, dict);
    if (counts > max) {
      max = counts;
      largestDecryption = aMessage;
    }
    System.out.println("Max counts:" + max);
    return largestDecryption;
  }

  public String breakVigenere() {
    String MaxDecryption = new String();
    FileResource resource = new FileResource("assets/messages/secretmessage5.txt");
    String message = resource.asString();
    HashSet<String> DictContent = new HashSet<String>();
    FileResource dictResource = new FileResource("assets/dictionaries/English");
    DictContent = readDictionary(dictResource);
    MaxDecryption = breakForLanguage(message, DictContent);
    return MaxDecryption;
  }
}
