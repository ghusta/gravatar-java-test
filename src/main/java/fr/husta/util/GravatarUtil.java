package fr.husta.util;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.lang3.StringUtils;

public class GravatarUtil
{

    public static final String GRAVATAR_IMAGE_REQUEST_URL_PREFIX = "http://www.gravatar.com/avatar/";
    public static final String GRAVATAR_PROFILE_REQUEST_URL_PREFIX = "http://www.gravatar.com/";

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
        String normalizedEmail =
                StringUtils.lowerCase(StringUtils.trim(StringUtils.defaultString(email)));
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
        } catch (MalformedURLException e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param emailHash Must be MD5 hash for email.
     * @param size      Size in pixels
     * @return
     */
    public static URL getImageURLWithSize(final String emailHash, final int size)
    {
        try
        {
            return new URL(GRAVATAR_IMAGE_REQUEST_URL_PREFIX + emailHash + "?size=" + size);
        } catch (MalformedURLException e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * Inspired from <a href="https://fr.gravatar.com/site/implement/images/">Image Requests</a> (Gravatar.com).
     *
     * @param emailHash    Must be MD5 hash for email.
     * @param defaultImage Default image type
     * @return
     */
    public static URL getImageURLWithDefault(final String emailHash, GravatarDefaultImageType defaultImage)
    {
        try
        {
            return new URL(GRAVATAR_IMAGE_REQUEST_URL_PREFIX + emailHash + "?default=" + defaultImage.getQueryParam());
        } catch (MalformedURLException e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * Inspired from <a href="https://fr.gravatar.com/site/implement/images/">Image Requests</a> (Gravatar.com).
     *
     * @param emailHash    Must be MD5 hash for email.
     * @param size         Size in pixels
     * @param defaultImage Default image type
     * @return
     */
    public static URL getImageURLWithSizeAndDefault(final String emailHash, final int size, GravatarDefaultImageType defaultImage)
    {
        try
        {
            return new URL(GRAVATAR_IMAGE_REQUEST_URL_PREFIX + emailHash + "?size=" + size + "&default=" + defaultImage.getQueryParam());
        } catch (MalformedURLException e)
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

    /**
     * Inspired from <a href="https://fr.gravatar.com/site/implement/profiles/">Profile Requests</a> (Gravatar.com).
     *
     * @param emailHash Must be MD5 hash for email.
     * @return
     */
    public static URL getProfileURL(final String emailHash)
    {
        try
        {
            return new URL(GRAVATAR_PROFILE_REQUEST_URL_PREFIX + emailHash);
        } catch (MalformedURLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public static URL getProfileURLFormatXml(final String emailHash)
    {
        try
        {
            return new URL(GRAVATAR_PROFILE_REQUEST_URL_PREFIX + emailHash + ".xml");
        } catch (MalformedURLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public static URL getProfileURLFormatJson(final String emailHash)
    {
        try
        {
            return new URL(GRAVATAR_PROFILE_REQUEST_URL_PREFIX + emailHash + ".json");
        } catch (MalformedURLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public static URL getProfileURLFormatQRCode(final String emailHash)
    {
        try
        {
            return new URL(GRAVATAR_PROFILE_REQUEST_URL_PREFIX + emailHash + ".qr");
        } catch (MalformedURLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public static URL getProfileURLFormatVCard(final String emailHash)
    {
        try
        {
            return new URL(GRAVATAR_PROFILE_REQUEST_URL_PREFIX + emailHash + ".vcf");
        } catch (MalformedURLException e)
        {
            throw new RuntimeException(e);
        }
    }

}
