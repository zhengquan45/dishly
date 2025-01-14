package com.zhengquan.dishly.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class WeChatUtils {

    /**
     * 解密微信加密数据，获取用户手机号等信息
     *
     * @param encryptedData 加密数据
     * @param iv 初始化向量
     * @param sessionKey 会话密钥
     * @return 解密后的明文数据（JSON 格式字符串）
     */
    public static String decryptPhoneData(String encryptedData, String iv, String sessionKey) {
        try {
            // Base64 解码
            byte[] dataByte = Base64.getDecoder().decode(encryptedData);
            byte[] keyByte = Base64.getDecoder().decode(sessionKey);
            byte[] ivByte = Base64.getDecoder().decode(iv);

            // 检查密钥长度是否为 16 字节
            if (keyByte.length != 16) {
                throw new IllegalArgumentException("SessionKey 长度错误");
            }

            // 设置解密参数
            SecretKeySpec secretKeySpec = new SecretKeySpec(keyByte, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(ivByte);
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);

            // 解密
            byte[] resultByte = cipher.doFinal(dataByte);
            if (resultByte != null && resultByte.length > 0) {
                return new String(resultByte, StandardCharsets.UTF_8);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
