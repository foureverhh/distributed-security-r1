package com.foureverhh.security.distributed.order.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;


public class EncryptUtil {
    private static final Logger logger = LoggerFactory.getLogger(EncryptUtil.class);

    public static String encodeBase64(byte[] bytes){
        return Base64.getEncoder().encodeToString(bytes);
    }

    public static byte[] decodeBase64(String str){
        byte[] bytes;
        bytes = Base64.getDecoder().decode(str);
        return bytes;
    }

    public static String encodeUTF8StringBase64(String str){
        String encode;
        encode = Base64.getEncoder().encodeToString(str.getBytes(StandardCharsets.UTF_8));
        return encode;
    }

    public static String decodeUTF8StringBase64(String str){
        logger.error("Enter decodeUTF8StringBase64 as "+str);
        String decoded = null;
        byte[] bytes = Base64.getDecoder().decode(str);
        decoded = new String(bytes, StandardCharsets.UTF_8);
        logger.error("DecodeUTF8StringBase64  result "+decoded);
        return decoded;
    }

    public static String encodeURL(String url){
        String encoded = null;
        try {
            encoded = URLEncoder.encode(url,"utf-8");
        } catch (UnsupportedEncodingException e) {
            logger.warn("Unsupported format",e);
        }
        return encoded;
    }

    public static String decodeURL(String url){
        String decoded = null;
        try {
            decoded = URLDecoder.decode(url,"utf-8");
        } catch (UnsupportedEncodingException e) {
            logger.warn("Unsupported format",e);
        }
        return decoded;
    }
}
