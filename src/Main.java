import java.util.ArrayList;

import edu.duke.FileResource;

public class Main {
    public static void main(String[] args) {
        FileResource fr = new FileResource("messages/titus-small.txt");

        // Test CaesarCipher
        CaesarCipher cc = new CaesarCipher(5);

        String encrypted = cc.encrypt(fr.asString()); // encrypt the file
        System.out.println(encrypted);
        String decrypted = cc.decrypt(encrypted); // decrypt the file
        System.out.println(decrypted);

        String c = "a";
        System.out.println(cc.encrypt(c)); // encrypt the character
        System.out.println(cc.decrypt(cc.encrypt(c))); // decrypt the character

        // Test CaesarCracker
        CaesarCracker cracker = new CaesarCracker('e');
        System.out.println(cracker.decrypt(encrypted)); // decrypt the file without key

        // Test VigenereCipher
        int[] key = { 17, 14, 12, 4 };
        VigenereCipher vc = new VigenereCipher(key);
        System.out.println(vc.encrypt(fr.asString())); // encrypt the file
        System.out.println(vc.decrypt(vc.encrypt(fr.asString()))); // decrypt the file
        System.out.println(vc.toString()); // print the ciphers

        // Test VigenereBreaker
        fr = new FileResource("messages/athens_keyflute.txt");
        VigenereBreaker vb = new VigenereBreaker();
        ArrayList<Integer> slice = vb.tryKeyLength(fr.asString(), 5, 'e'); // get the list of keys
        System.out.println(slice.toString());
        vb.breakVigenere(); // break the file
    }
}
