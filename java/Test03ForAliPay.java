
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;


public class Test03ForAliPay {
    public static void main(String[] args) {
        withdraw();
    }


    private static final String ALGORITHM = "RSA";

    private static final String SIGN_ALGORITHMS = "SHA1WithRSA";

    private static final String DEFAULT_CHARSET = "UTF-8";

    private static final String privateKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAIUYp9b0VZVz5+/GQ1/bmL0ROof96q9DLcumO9aa+sGaGKdTG1P5/94uzdSxyrNKkEzA1jZY6MmAWz5jPS4AB9pMffzsBY0kVXSV2QPJlvUMCx+BKxPnGlf0hNRGPfOX1J7ICVWfU0iiTNoGxrq1ixsnKKU3Bh9uqF9ZSMCp4gIJAgMBAAECgYAv+AblzgHab3+0RzyZG8gcvEzgHn+q0wd6UltWqVye0i74f1dmGMBDD5NXiQyJOeJwB0AtLjYYCY7ZGWM/ZPtvBmw9FQnMHx6dHexksFBC7HwtxTLo/5lkqFFnnzwkksvlzTJwtw2DHqg02a53iG/bpRf93Fe2H3FAEK/e3zIwQQJBAOCMM6uV3JMSoddiPOIoH51K09b+LdJlK9LLCsr9QjcaAcJH669feHtLwuWpAQDHidqEpuhqpeoyQKypSV/OLi0CQQCXvTRG0kMXR3WdoRrI29Tnf84ZaXuI/4UpEsqlImQiVrirfeRv3Pxe5Mk/Ks4M82KZCF371Eiq7CHch13O0CjNAkAUymZuWK3XoD4D50Cj3/stYSREH7y2Yk+wcMRLDy/2Uh4fkvErh7dsExO0l/VT0SXeabHaiyVZjdWXJwV4LWrxAkAFZHLsJWK+/Aog/O2vv6g0ReV0H7yFJNd9my5aaft3AO/1IDczBn5tfOHt/b5u1WNmntaAMY4XjOazKJhb1iftAkAm9uu1VdFFGIuaz+RLM4LyDFGDp58NnMgOnMpOTeJGd3fXHUUsvCIRWvZuapkGA6GGqVAOWalU9rvZg86YFl5U";

//    public static void main(String[] args) {
//
//        if (args[0].equals("T")) {
//            transfer();
//        }
//
//        if (args[0].equals("W")) {
//            withdraw();
//        }
//
//    }

    private static void transfer() {
        Map<String, Object> requestIncomeRegisterAccountParamsMap = new TreeMap<String, Object>();
        requestIncomeRegisterAccountParamsMap.put("userId", 45996);
        requestIncomeRegisterAccountParamsMap.put("merchantOrderNo", "2020122211250000224");
        requestIncomeRegisterAccountParamsMap.put("amount", "0.1");
        requestIncomeRegisterAccountParamsMap.put("cashBackBusinessTypeId", "11");
        requestIncomeRegisterAccountParamsMap.put("incomeAndExpensesRecordTypeId", "1");
        requestIncomeRegisterAccountParamsMap.put("timestamp", new Date().getTime());

        String queryParamsString = buildQueryString(requestIncomeRegisterAccountParamsMap);
        String sign = sign(queryParamsString, privateKey);
        requestIncomeRegisterAccountParamsMap.put("signature", sign);

        String result = "{";
        for (Map.Entry entry : requestIncomeRegisterAccountParamsMap.entrySet()) {
            result += "\"" + entry.getKey() + "\"" + ":" + "\"" + entry.getValue() + "\"" + ",";
        }
        result = result.substring(0, result.length() - 1);
        result += "}";

        System.out.println(result);
    }

    private static void withdraw() {
        Map<String, Object> cashBackRecordParamsMap = new TreeMap<String, Object>();
        cashBackRecordParamsMap.put("cashBackBusinessTypeId", "11");
        cashBackRecordParamsMap.put("merchantOrderNo", "2020" + new Date().getTime());
        cashBackRecordParamsMap.put("userId", 45996);
        cashBackRecordParamsMap.put("amount", "0.01");

        String queryParamsString = buildQueryString(cashBackRecordParamsMap);
        String sign = sign(queryParamsString, privateKey);
        cashBackRecordParamsMap.put("signature", sign);

        String result = "{";
        for (Map.Entry entry : cashBackRecordParamsMap.entrySet()) {
            result += "\"" + entry.getKey() + "\"" + ":" + "\"" + entry.getValue() + "\"" + ",";
        }
        result = result.substring(0, result.length() - 1);
        result += "}";

        System.out.println(result);
    }

    public static String sign(String content, String rsaPrivateStr) {
        try {

            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);

            BASE64Decoder decoder = new BASE64Decoder();
            BASE64Encoder encoder = new BASE64Encoder();
            PrivateKey priKey = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(decoder.decodeBuffer(rsaPrivateStr)));
            Signature privateSignature = Signature.getInstance(SIGN_ALGORITHMS);
            privateSignature.initSign(priKey);

            privateSignature.update(content.getBytes(DEFAULT_CHARSET));
            return encoder.encode(privateSignature.sign()).replaceAll("[\n\r]", "");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static boolean verify(String content, String sign, String rsaPublicKeyStr) {
        try {

            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);

            BASE64Decoder decoder = new BASE64Decoder();

            PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(decoder.decodeBuffer(rsaPublicKeyStr)));
            Signature publicSignature = Signature.getInstance(SIGN_ALGORITHMS);
            publicSignature.initVerify(pubKey);

            publicSignature.update(content.getBytes(DEFAULT_CHARSET));
            boolean result = publicSignature.verify(decoder.decodeBuffer(sign));


            return result;
        } catch (Exception e) {
        }
        return false;
    }

    public static String buildQueryString(Map<String, Object> requestParamsMap) {

        StringBuffer signContentBuffer = new StringBuffer();
        requestParamsMap.forEach((paramName, paramValue) -> {
            signContentBuffer.append(paramName);
            signContentBuffer.append("=");
            signContentBuffer.append(paramValue);
            signContentBuffer.append("&");
        });

        signContentBuffer.deleteCharAt(signContentBuffer.length() - 1);

        return signContentBuffer.toString();
    }
}


