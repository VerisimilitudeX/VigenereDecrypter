import edu.duke.FileResource;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class VigenereCipherTest {
    @Test
    public void cipherPlainText() {
        var secretMessage = new FileResource("assets/messages/secretmessage2.txt");
        var key = new ArrayList<>(Arrays.asList(3, 20, 10, 4));
        var vigenereCipher = new VigenereCipher(key);
        var encryptedSecretMessage = new FileResource("assets/messages/encryptedSecretMessage2.txt");

        assertThat(vigenereCipher.encrypt(secretMessage.asString()))
                .isEqualTo(encryptedSecretMessage.asString());
    }
}
