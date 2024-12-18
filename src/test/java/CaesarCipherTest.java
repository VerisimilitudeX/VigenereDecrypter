import static org.assertj.core.api.Assertions.*;

import org.junit.Test;

public class CaesarCipherTest {
  @Test
  public void cipherPlainText() {
    var caesarCipher = new CaesarCipher(1);

    assertThat(caesarCipher.encrypt("defend the east wall of the castle"))
        .isEqualTo("efgfoe uif fbtu xbmm pg uif dbtumf");
  }

  @Test
  public void cipherPlainTextWithKey() {
    var caesarCipher = new CaesarCipher(4);

    assertThat(caesarCipher.encrypt("efgfoe uif fbtu xbmm pg uif dbtumf"))
        .isEqualTo("ijkjsi ymj jfxy bfqq tk ymj hfxyqj");
  }

  @Test
  public void convertToString() {
    var caesarCipher = new CaesarCipher(1);

    assertThat(caesarCipher.toString()).isEqualTo("1");
  }
}
