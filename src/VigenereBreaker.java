import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import edu.duke.FileResource;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder sb = new StringBuilder();
        for (int i = whichSlice; i < message.length(); i += totalSlices) {
            sb.append(message.charAt(i));
        }
        return sb.toString();
    }

    public ArrayList<Integer> tryKeyLength(String encrypted, int keyLength, char mostCommon) {
        ArrayList<Integer> keys = new ArrayList<Integer>();
        for (int i = 0; i < keyLength; i++) {
            String slice = sliceString(encrypted, i, keyLength);
            CaesarCracker cc = new CaesarCracker();
            int sliceKey = cc.getKey(slice);
            keys.add(i, sliceKey);
        }
        return keys;
    }

    public void breakVigenere() {
        FileResource fr = new FileResource();
        String fileContents = fr.asString();
        ArrayList<Integer> keys = tryKeyLength(fileContents, 5, 'e');
        int[] key = new int[keys.size()];
        for (int i = 0; i < keys.size(); i++) {
            key[i] = keys.get(i);
        }
        VigenereCipher vc = new VigenereCipher(key);
        String decrypted = vc.decrypt(fileContents);
        System.out.println(decrypted);
    }

}
