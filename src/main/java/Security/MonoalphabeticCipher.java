package Security;

import java.util.*;

public class MonoalphabeticCipher {

    // TODO: Implement this method to generate a substitution map from A-Z using the provided key
    private static Map<Character, Character> generateEncryptionMap(String key) {
        Map<Character, Character> encryptionMap = new HashMap<>();
        Set<Character> usedSet=new HashSet<Character>();
        int start=0 ;
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        key = key.toUpperCase();
        // Students should complete this loop
        for (int i = 0; i < alphabet.length(); i++) {
            // encryptionMap // Hint: Map plaintext letter to cipher letter
            if(i<key.length()){
                encryptionMap.put(alphabet.charAt(i),key.charAt(i));
                usedSet.add(key.charAt(i));
            }
            else{
                boolean found=false;
                while(!found){
                    if(!usedSet.contains(alphabet.charAt(start))){
                        encryptionMap.put(alphabet.charAt(i),alphabet.charAt(start));
                        usedSet.add(alphabet.charAt(start));
                        found=true;
                    }
                    start++;
                }
            }
        }
        return encryptionMap;
    }

    // TODO: Implement this method to reverse the encryption map (ciphertext -> plaintext)
    private static Map<Character, Character> generateDecryptionMap(String key) {
        Map<Character, Character> decryptionMap = new HashMap<>();
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        key = key.toUpperCase();
        int start=0 ;
        Set<Character> usedSet=new HashSet<>();
        // Students should complete this loop
        for (int i = 0; i < alphabet.length(); i++) {
            // decryptionMap // Hint: Reverse mapping
            if(i<key.length()){
                decryptionMap.put(key.charAt(i),alphabet.charAt(i));
            }
            else{
                boolean found=false;
                while (!found){
                    if(!usedSet.contains(alphabet.charAt(start))){
                        usedSet.add(alphabet.charAt(start));
                        found=true;
                        decryptionMap.put(alphabet.charAt(start),alphabet.charAt(i));
                    }
                    start++;
                }
            }
        }
        return decryptionMap;
    }

    public static String encrypt(String plaintext, String key) {
        Map<Character, Character> encryptionMap = generateEncryptionMap(key);
        plaintext = plaintext.toUpperCase();
        StringBuilder encryptedText = new StringBuilder();

        for (char c : plaintext.toCharArray()) {
            if(c==' '){
                encryptedText.append(' ');
                continue;
            }
            encryptedText.append(encryptionMap.get(c));
        }
        return encryptedText.toString();
    }

    public static String decrypt(String ciphertext, String key) {
        Map<Character, Character> decryptionMap = generateDecryptionMap(key);
        ciphertext = ciphertext.toUpperCase();
        StringBuilder decryptedText = new StringBuilder();

        for (char c : ciphertext.toCharArray()) {
            // TODO: Use the decryption map to convert each letter
            if(c==' '){
                decryptedText.append(' ');
                continue;
            }
            decryptedText.append(decryptionMap.get(c));

        }
        return decryptedText.toString();
    }

    public static String findKey(String plaintext, String ciphertext) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char[] keyMap = new char[26];
        Arrays.fill(keyMap, ' ');
        Set<Character> usedSet=new HashSet<>();
        plaintext = plaintext.toUpperCase();
        ciphertext = ciphertext.toUpperCase();

        for (int i = 0; i < plaintext.length(); i++) {
            char plainChar = plaintext.charAt(i);
            char cipherChar = ciphertext.charAt(i);

            if (Character.isLetter(plainChar)&&!usedSet.contains(plainChar)) {
                int index = alphabet.indexOf(plainChar);
                keyMap[index]=cipherChar;

            }
        }

        return new String(keyMap);
    }
}
