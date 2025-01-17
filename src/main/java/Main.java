import edu.duke.FileResource;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
  public static void main(String[] args) {
    String filePath = "assets/messages/secretmessage2.txt";
    File file = new File(filePath);
    if (!file.exists() || !file.isFile()) {
      System.err.println("Error: File path is invalid or file does not exist.");
      return;
    }

    FileResource fr = new FileResource(file);

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
