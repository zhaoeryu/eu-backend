package cn.eu.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @author zhaoeryu
 * @since 2023/6/5
 */
@Slf4j
public class RsaUtil {

    /**
     * 私钥解密
     * @param privateKeyText 私钥
     * @param text          密文
     * @return  解密后的字符串
     */
    public static String decryptByPrivateKey(String privateKeyText, String text) {
        try {
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec5 = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKeyText));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec5);
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] result = cipher.doFinal(Base64.decodeBase64(text));
            return new String(result);
        } catch (Exception e) {
            log.error("解密失败", e);
        }
        return text;
    }

    /**
     * 公钥加密
     * @param publicKeyText 公钥
     * @param text         明文
     * @return 密文
     */
    public static String encryptByPublicKey(String publicKeyText, String text) {
        try {
            X509EncodedKeySpec x509EncodedKeySpec2 = new X509EncodedKeySpec(Base64.decodeBase64(publicKeyText));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec2);
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] result = cipher.doFinal(text.getBytes());
            return Base64.encodeBase64String(result);
        } catch (Exception e) {
            log.error("加密失败", e);
        }
        return text;
    }

    public static void main(String[] args) throws Exception {
        String data = "admin";
        String encryptedText = encryptByPublicKey("MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAJ7ajt/v+aA25UPRgU/wh3+jjIk4VVJA1mMp7tBktPSOF7jxzm+G+2VK5MIYpomN7LPb90XraofsD7NT1iK0j/0CAwEAAQ==", data);
        String decryptedText = decryptByPrivateKey("MIIBVAIBADANBgkqhkiG9w0BAQEFAASCAT4wggE6AgEAAkEAntqO3+/5oDblQ9GBT/CHf6OMiThVUkDWYynu0GS09I4XuPHOb4b7ZUrkwhimiY3ss9v3Retqh+wPs1PWIrSP/QIDAQABAkAU4RlCaeJfopeD0He0sCK/Lhn8t0DPrOIA6rQPBA2czxNWaDMpzZiG6JFDWXPLW7ndJ0WRYU51gfSRwOPdlSNpAiEA0Wx0xxtezLQuzrcezGMUq5P0hU4EZ3Qj8ATXJhBubj8CIQDCLubmlAfHX5ZnmC4BPIoZNyuKMyLe+WD/DwfX/+/qwwIhAJt/4n3s7skkiRTol6+/ahu0cn2A3nhKnlyb23zh+n1VAiAh+21DGtLFrE+472PVqY+NXB9NfydyF/hGio3X2h1VWwIgbI9GpVKcCoA4z9uprOqL82XYlLNpPc5N6jumx8dFFo0=", encryptedText);
        System.out.println("加密后：" + encryptedText);
        System.out.println("解密后：" + decryptedText);
    }
}
