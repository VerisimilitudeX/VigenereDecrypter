import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class CaesarCrackerTest {
  @Test
  public void testGetKeyWithMostCommonE() {
    CaesarCracker cracker = new CaesarCracker('e');
    String encrypted = "GIEWIVrGMTLIVrHIQS"; // Encrypted with key 4
    int key = cracker.getKey(encrypted);
    assertThat(key).isEqualTo(4);
  }

  @Test
  public void testGetKeyWithMostCommonA() {
    CaesarCracker cracker = new CaesarCracker('a');
    String encrypted = "JGRGJGrJQGRJGrJQGR"; // Encrypted with key 4
    int key = cracker.getKey(encrypted);
    assertThat(key).isEqualTo(4);
  }

  @Test
  public void testDecryptWithMostCommonE() {
    CaesarCracker cracker = new CaesarCracker('e');
    String encrypted = "GIEWIVrGMTLIVrHIQS"; // Encrypted with key 4
    String decrypted = cracker.decrypt(encrypted);
    assertThat(decrypted).isEqualTo("CAESARcIPHERcTEST");
  }

  @Test
  public void testDecryptWithMostCommonA() {
    CaesarCracker cracker = new CaesarCracker('a');
    String encrypted = "JGRGJGrJQGRJGrJQGR"; // Encrypted with key 4
    String decrypted = cracker.decrypt(encrypted);
    assertThat(decrypted).isEqualTo("CAESARcIPHERcTEST");
  }
}
