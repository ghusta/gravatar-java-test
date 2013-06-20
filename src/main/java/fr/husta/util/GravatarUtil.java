package fr.husta.util;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.lang3.StringUtils;

public class GravatarUtil
{

    public static String GRAVATAR_IMAGE_REQUEST_URL_PREFIX = "http://www.gravatar.com/avatar/";

    /**
     * Inspired from <a href="https://fr.gravatar.com/site/implement/hash/">Creating the Hash</a> (Gravatar.com).
     * <ul>
     * <li>Trim leading and trailing whitespace from an email address</li>
     * <li>Force all characters to lower-case</li>
     * <li>md5 hash the final string</li>
     * </ul>
     * 
     * @param email Email address.
     * @return MD5 hash
     */
    public static String getMD5ForEmail(final String email)
    {
        String normalizedEmail = StringUtils.lowerCase(StringUtils.trim(email));
        return MD5Util.md5Hex(normalizedEmail);
    }

    /**
     * Inspired from <a href="https://fr.gravatar.com/site/implement/images/">Image Requests</a> (Gravatar.com).
     * 
     * @param emailHash Must be MD5 hash for email.
     * @return
     */
    public static URL getImageURL(final String emailHash)
    {
        try
        {
            return new URL(GRAVATAR_IMAGE_REQUEST_URL_PREFIX + emailHash);
        }
        catch (MalformedURLException e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * Inspired from <a href="https://fr.gravatar.com/site/implement/images/">Image Requests</a> (Gravatar.com).
     * 
     * @param emailHash Must be MD5 hash for email.
     * @return Return the HTML <code>img</code> tag with <code>src</code> attribute.
     */
    public static String getHtmlImageTag(final String emailHash)
    {
        final String IMG_EXTENSION = ".jpg";
        final boolean addImgExtension = false;
        URL imageUrl = getImageURL(emailHash);
        return "<img src=\"" + imageUrl.toString() + (addImgExtension ? IMG_EXTENSION : "") + "\" />";
    }

}
