import java.util.Base64;

public class EncryptorDecryptor {
    public String encrypt(String input) {
        return Base64.getEncoder().encodeToString(input.getBytes());
    }

    public String decrypt(String encryptedInput) {
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedInput);
        return new String(decodedBytes);
    }
}
