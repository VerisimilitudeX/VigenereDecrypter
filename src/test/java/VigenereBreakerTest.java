import static org.assertj.core.api.Assertions.assertThat;

import edu.duke.FileResource;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Test;

public class VigenereBreakerTest {
  @Test
  public void breakVigenereAndTryKeyLength() {
    var vigenereBreaker = new VigenereBreaker();

    assertThat(vigenereBreaker.breakVigenere())
        .isEqualTo(
            "Elk eeyu kejyehqrz znef snc'b elr. Ms kepy ge aejmnt map dopk ee wfbx we exhjtoqwu eqi"
                + " vzee ey bzew xgi eehf xbeu.\n");

    var secretMessage = new FileResource("assets/messages/secretmessage2.txt");
    var result = vigenereBreaker.tryKeyLength(secretMessage.asString(), 38, 'e');
    var expected =
        new ArrayList<>(
            Arrays.asList(
                8, 23, 20, 14, 23, 10, 9, 6, 25, 4, 18, 16, 12, 15, 11, 19, 13, 21, 4, 8, 15, 10,
                14, 23, 3, 9, 6, 25, 4, 18, 16, 12, 19, 11, 19, 0, 21, 4));
    assertThat(result).isEqualTo(expected);
  }

  @Test
  public void breakForLanguage() {
    var vigenereBreaker = new VigenereBreaker();
    var secretMessage = new FileResource("assets/messages/secretmessage2.txt");
    var dictResource = new FileResource("assets/dictionaries/English");
    var dictContent = vigenereBreaker.readDictionary(dictResource);
    var decryptedMessage =
        vigenereBreaker.breakForLanguage(secretMessage.asString(), dictContent, 'e');

    assertThat(decryptedMessage)
        .isEqualTo(
            "The quick brown fox jumps over the lazy dog. This is a test message to check the"
                + " correctness of the VigenereBreaker class.");
  }

  @Test
  public void countWords() {
    var vigenereBreaker = new VigenereBreaker();
    var secretMessage = new FileResource("assets/messages/secretmessage2.txt");
    var dictResource = new FileResource("assets/dictionaries/English");
    var dictContent = vigenereBreaker.readDictionary(dictResource);
    var wordCount = vigenereBreaker.countWords(secretMessage.asString(), dictContent);

    assertThat(wordCount).isEqualTo(12);
  }
}
