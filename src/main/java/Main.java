import edu.duke.FileResource;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
  public static void main(String[] args) {
    FileResource fr = new FileResource("assets/messages/secretmessage2.txt");

    // Test VigenereCipher
    ArrayList<Integer> key = new ArrayList<Integer>(Arrays.asList(3, 20, 10, 4));
    VigenereCipher vc = new VigenereCipher(key);
    System.out.println(vc.encrypt(fr.asString())); // encrypt the file

    // Test VigenereBreaker
    VigenereBreaker vb = new VigenereBreaker();
    System.out.println(vb.breakVigenere());
    vb.tryKeyLength(fr.asString(), 38, 'e');
  }
}
