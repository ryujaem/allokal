package com.spring.app.allokal.security;

import com.spring.app.allokal.logging.Logging;
import org.jetbrains.annotations.NotNull;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.AlgorithmParameters;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;

public class AuthToken {

    public static Logging logging = new Logging();

    // AccesToken 발급 API 샘플
    public static String getAccessToken() {
        Date currentDate = new Date();
        long unixTime = currentDate.getTime() / 1000;
        String serverUrl = "https://apis.hanafnapimarket.com:22001/onegw/hbk/api/oauth/oauth/token";
        String clientId = "d0ab3cd8-f3f2-4e92-b8d4-a0acf2070439";
        String clientSecret = "5bb9cb1a9daba6991a1c4a2e67127c4a";
        String encKey = "@allokal22R220902003";
        String entrCd = "ALL5880350";
        encKey = encKey + entrCd + "@@"; // encKey 32byte 재조립 encKey + entrCd + @@
        try {
//----------------------------------------------
// 01. Authorization 생성한다. clientId + clientSecret + unixTime (AES256 암호화, BASE64 인코딩) //----------------------------------------------
            String authorization = clientId + ":" + clientSecret + ":" + unixTime;
            authorization = "Basic " + encrypt(encKey, authorization);
            System.out.println("authorization : " + authorization);
//----------------------------------------------
// 02. AccesToken 발급 API 호출 //----------------------------------------------
            String response = getAccesTokenAPI(authorization, entrCd, serverUrl);
            System.out.println("response:" + response);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String encrypt(String key, String msg) {
        byte[] bEncrypt = null;
        try {
            logging.info(msg);
            SecureRandom random = new SecureRandom();
            byte bytes[] = new byte[20];
            random.nextBytes(bytes);
            byte[] saltBytes = bytes;

            SecretKeySpec secret = new SecretKeySpec(key.getBytes("UTF-8"), "AES");


            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secret);
            AlgorithmParameters params = cipher.getParameters();

            byte[] ivBytes = params.getParameterSpec(IvParameterSpec.class).getIV();
            byte[] encByte = cipher.doFinal(msg.getBytes("UTF-8"));
            bEncrypt = new byte[saltBytes.length + ivBytes.length + encByte.length];
            System.arraycopy(saltBytes, 0, bEncrypt, 0, saltBytes.length);
            System.arraycopy(ivBytes, 0, bEncrypt, saltBytes.length, ivBytes.length);
            System.arraycopy(encByte, 0, bEncrypt, saltBytes.length + ivBytes.length, encByte.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Base64.getEncoder().encodeToString(bEncrypt);
    }

    @NotNull
    public static String getAccesTokenAPI(String authorization, String entrCd, String serverUrl) {
        HttpURLConnection httpUrlConn = null;
        BufferedReader postRes = null;
        String resultData = null;
        StringBuffer resultBuffer = new StringBuffer();
        URL url = null;
        try {
//연결시작하기
            url = new URL(serverUrl);
            httpUrlConn = (HttpURLConnection) url.openConnection();
//연결값 세팅하기
            httpUrlConn.setRequestMethod("POST");
            httpUrlConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            httpUrlConn.setRequestProperty("ENC_NEW", "Y");
            postRes = null;
            resultData = null;
            resultBuffer = new StringBuffer();
            httpUrlConn.setRequestProperty("ENTR_CD", entrCd);
            httpUrlConn.setRequestProperty("Authorization", authorization);
            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setConnectTimeout(1000 * 60);
//메시지 전송
            String sendData = "grant_type=client_credentials";
            OutputStream os = httpUrlConn.getOutputStream();
            os.write(sendData.getBytes());
            os.flush();
            os.close();
//전문 수신
            postRes = new BufferedReader(new InputStreamReader(httpUrlConn.getInputStream(), "utf-8"));
            while ((resultData = postRes.readLine()) != null) {
                resultBuffer.append(resultData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultBuffer.toString();
    }
}