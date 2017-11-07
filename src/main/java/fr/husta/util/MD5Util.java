package fr.husta.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Inspired from <a href="https://fr.gravatar.com/site/implement/images/java/">Java Image Requests</a> (Gravatar.com).
 * 
 */
public class MD5Util
{

    private static String hex(byte[] array)
    {
        StringBuilder sb = new StringBuilder();
        for (byte b : array) {
            sb.append(Integer.toHexString((b & 0xFF) | 0x100).substring(1, 3));
        }
        return sb.toString();
    }

    public static String md5Hex(String message)
    {
        try
        {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return hex(md.digest(message.getBytes("CP1252")));
        }
        catch (NoSuchAlgorithmException | UnsupportedEncodingException e)
        {
        }
        return null;
    }

}
