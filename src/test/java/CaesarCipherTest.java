import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

public class CaesarCipherTest {
    @Test
    public void cipherPlainText() {
        var caesarCipher = new CaesarCipher(1);

        assertThat(caesarCipher.encrypt("defend the east wall of the castle"))
                .isEqualTo("efgfoe uif fbtu xbmm pg uif dbtumf");
    }
}
