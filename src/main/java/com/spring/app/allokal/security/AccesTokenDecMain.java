package com.spring.app.allokal.security;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;
import java.util.Base64;

public class AccesTokenDecMain {

    // access_token 복호화
    public static String getToken(){
        String access_token = "9NS1rTaknLkt7dmfdxYvMrWdr6O3utplVAl4YpIg/HF5Avh01wyAR265jTtRqeOxvPTnEr6fwRye4/0lZiWXorq3rM9c5N3WmfMTcl8gGxsRgEzz";
        //Zwz/aq7Mx4HRRvEFyug32sNG5J9wGHi+QxlnIdJ5cWtQ+y1+ISibaLRLuYA8ihbxhTJQtSE+v3P8WqePN66NwR72zPXdmWvCmfyNC3o7VKpHyGYX
        String encKey = "@allokal22R220902003";
        String entrCd = "ALL5880350";
        encKey = encKey + entrCd + "@@"; // encKey 32byte 재조립 encKey + entrCd + @@
        try {
            // token 복호화 (예:cd519d75-af9f-4195-a1b2-f0eaa781e9ac:1583454610)
            String[] decToken = decrypt(encKey, access_token).split(":");
            System.out.println("decrypt :: " + decrypt(encKey, access_token));
            System.out.println("token :: " + decToken[0]);
            System.out.println("unixTime :: " + decToken[1]);
            // decrypt :: 93f47c01-70ea-4d26-8ce2-d9bf46a6b9d4:1599456143
            // token :: 93f47c01-70ea-4d26-8ce2-d9bf46a6b9d4
            // unixTime :: 1599456143
            return decToken[0];
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String decrypt(String key, String msg) {
        byte[] bDecrypt = null;
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            ByteBuffer buffer = ByteBuffer.wrap(Base64.getDecoder().decode(msg));
            byte[] saltBytes = new byte[20];
            buffer.get(saltBytes, 0, saltBytes.length);
            byte[] ivBytes = new byte[cipher.getBlockSize()];
            buffer.get(ivBytes, 0, ivBytes.length);
            byte[] encryptedTextBytes = new byte[buffer.capacity() - saltBytes.length - ivBytes.length];
            buffer.get(encryptedTextBytes);
            SecretKeySpec secret = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
            cipher.init(Cipher.DECRYPT_MODE, secret, new IvParameterSpec(ivBytes));
            bDecrypt = cipher.doFinal(encryptedTextBytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new String(bDecrypt);
    }
}
