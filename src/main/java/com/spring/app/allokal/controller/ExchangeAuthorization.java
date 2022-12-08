package com.spring.app.allokal.controller;

import com.spring.app.allokal.logging.Logging;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.AlgorithmParameters;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

@RestController
public class ExchangeAuthorization {
    public static Logging logging = new Logging();
    @Value("${clientId}")
    private String clientId;
    @Value("${access_token}")
    private String access_token;
    @Value("${encKey}")
    private String encKey;
    @Value("${entrCd}")
    private String entrCd;
    @Value("${app_key}")
    private String app_key;

    @Value("${app_url}")
    String app_url;

    @RequestMapping(value = "/api/exchangeRate", method = RequestMethod.GET)
    @ResponseBody
    public String getRate() {
        Date currentDate = new Date();
        long unixTime = currentDate.getTime() / 1000;
        //encKey = encKey + entrCd + "@@";
        String key = encKey + entrCd + "@@";
        BigDecimal Drate = null;
        String rate = null;
        System.out.println("Client ID : "+clientId);
        try {
            String StringToken = access_token + ":" + unixTime + ":" + clientId;
            String bearerToken = "bearer " + encrypt(key, StringToken);
            Drate = exchangeRateAPI(bearerToken, entrCd, app_key,app_url);
            rate = Drate.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rate;
    }

    public static String encrypt(String key, String msg) {

        byte[] bEncrypt = null;

        try {
            logging.info(msg);
            SecureRandom random = new SecureRandom();
            byte[] bytes = new byte[20];
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


    public static BigDecimal exchangeRateAPI(String authorization, String entrCd, String app_key,String app_url) {

        System.out.println("---------------------API 통신 테스트 시작-----------------------");
        // 실제 API 호출

        BigDecimal decimal = null;
        try {
            URL url = new URL(app_url);

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setConnectTimeout(5000); // Connection Timeout 설정
            con.setReadTimeout(5000); // Read Timeout 설정
            con.setDoOutput(true); // OutPutStream 사용
            con.setDoInput(true); // Input Stream 사용
            con.setRequestProperty("Accept", "application/json");
            con.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            logging.info(authorization);
            con.setRequestProperty("Authorization", authorization);
            con.setRequestProperty("ENC_NEW", "Y");
            con.setRequestProperty("ENTR_CD", entrCd);
            con.setRequestProperty("APP_KEY", app_key);

            // post request pram값 입력
            String jsonDataBody = "{\n" +
                    "    \"dataBody\": {\n" +
                    "        \"curCd\": \"VND\",\n" +
                    "        \"notiDiv\": \"0\",\n" +
                    "        \"notiCnt\": \"\"\n" +
                    "    },\n" +
                    "    \"dataHeader\": {\n" +
                    "        \"CLNT_IP_ADDR\": \"3.35.188.48\",\n" +
                    "        \"CNTY_CD\": \"kr\",\n" +
                    "        \"ENTR_CD\": \"ALL5880350\"\n" +
                    "    }\n" +
                    "}";
            OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
            wr.write(jsonDataBody);
            System.out.println("jsonbody : " + jsonDataBody);
            wr.flush();

            StringBuilder sb = new StringBuilder();

            if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line).append("\n");
                }
                br.close();

                JSONParser jsonParser = new JSONParser();
                // import org.json.simple.parser.JSONParser; (json-simple 1.1 라이브러리 필요)

                Map responseBody = (Map) jsonParser.parse(sb.toString());

                String a = responseBody.get("dataBody").toString();
                String[] jsondata = a.split(",");
                String[] str = null;

                for (int i = 0; i < jsondata.length; i++) {
                    System.out.println("[DataBody]: " + jsondata[i]);
                    if (jsondata[i].contains("cashBuyRate")) {
                        str = jsondata[i].split(":");
                        str[1] = str[1].replace("\"", "");
                        str[1] = str[1].replace("}]", "");

                        decimal = new BigDecimal(str[1]);
                        BigDecimal num = new BigDecimal("10000000000");
                        System.out.println(decimal);
                        decimal = decimal.divide(num);

                    }
                }

            } else {
                System.out.println(con.getResponseMessage());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("---------------------API 통신 테스트 완료-----------------------");
        return decimal;
    }

}
