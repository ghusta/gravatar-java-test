package fr.husta.util;

import java.net.URL;


import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class GravatarUtilTest
{

    private static final String EMAIL_TOTO = "toto@site.com";
    private static final String EMAIL_GUILLAUME_HUSTA = "guillaume.husta@gmail.com";

    @BeforeClass
    public static void setUpBeforeClass() throws Exception
    {
    }

    @Test
    public void testGetMD5ForEmail() throws Exception
    {
        String email;
        String hash;

        email = EMAIL_TOTO;
        hash = GravatarUtil.getMD5ForEmail(email);
        Assert.assertNotNull(hash);
        System.out.println("--> " + hash);
    }

    @Test
    public void testGetMD5ForEmail_null() throws Exception
    {
        String email;
        String hash;

        email = null;
        hash = GravatarUtil.getMD5ForEmail(email);
        Assert.assertNotNull(hash);
        System.out.println("--> " + hash);
    }

    @Test
    public void testGetMD5ForEmail_empty() throws Exception
    {
        String email;
        String hash;

        email = "";
        hash = GravatarUtil.getMD5ForEmail(email);
        Assert.assertNotNull(hash);
        System.out.println("--> " + hash);
    }

    @Test
    public void testGetImageURL() throws Exception
    {
        String email = null;
        URL url = null;

        // TEST #1
        email = EMAIL_TOTO;
        url = GravatarUtil.getImageURL(GravatarUtil.getMD5ForEmail(email));
        Assert.assertNotNull(url);
        System.out.println("--> " + url);
    }

    @Test
    public void testGetImageURL_forGHUSTA() throws Exception
    {
        String email = null;
        URL url = null;

        email = EMAIL_GUILLAUME_HUSTA;
        url = GravatarUtil.getImageURL(GravatarUtil.getMD5ForEmail(email));
        Assert.assertNotNull(url);
        System.out.println("GH --> " + url);
    }

    @Test
    public void testGetImageURL_forGHUSTA_size400() throws Exception
    {
        String email = null;
        URL url = null;

        email = EMAIL_GUILLAUME_HUSTA;
        url = GravatarUtil.getImageURLWithSize(GravatarUtil.getMD5ForEmail(email), 400);
        Assert.assertNotNull(url);
        System.out.println("GH (400 px) --> " + url);
    }

    @Test
    public void testGetImageURL_withDefault() throws Exception
    {
        String email = null;
        URL url = null;

        url = GravatarUtil.getImageURLWithDefault("123", GravatarDefaultImageType.mm);
        Assert.assertNotNull(url);
        System.out.println("Default Avatar --> " + url);

        url = GravatarUtil.getImageURLWithDefault("123", GravatarDefaultImageType.retro);
        Assert.assertNotNull(url);
        System.out.println("Default Avatar (retro) --> " + url);

        url = GravatarUtil.getImageURLWithDefault("123", GravatarDefaultImageType.identicon);
        Assert.assertNotNull(url);
        System.out.println("Default Avatar (identicon) --> " + url);
    }

    @Test
    public void testGetImageURL_withSizeAndDefault() throws Exception
    {
        String email = null;
        URL url = null;

        url = GravatarUtil.getImageURLWithSizeAndDefault("123", 40, GravatarDefaultImageType.mm);
        Assert.assertNotNull(url);
        System.out.println("Default Avatar (40 px) --> " + url);
    }

    @Test
    public void testGetProfileURL_forGHUSTA() throws Exception
    {
        String email = null;
        URL url = null;

        email = EMAIL_GUILLAUME_HUSTA;
        url = GravatarUtil.getProfileURL(GravatarUtil.getMD5ForEmail(email));
        Assert.assertNotNull(url);
        System.out.println("GH (Profile) --> " + url);
    }

    @Test
    public void testGetProfileURL_forGHUSTA_JSON() throws Exception
    {
        String email = null;
        URL url = null;

        email = EMAIL_GUILLAUME_HUSTA;
        url = GravatarUtil.getProfileURLFormatJson(GravatarUtil.getMD5ForEmail(email));
        Assert.assertNotNull(url);
        System.out.println("GH (Profile) --> " + url);
    }

    @Test
    public void testGetProfileURL_forGHUSTA_QRCode() throws Exception
    {
        String email = null;
        URL url = null;

        email = EMAIL_GUILLAUME_HUSTA;
        url = GravatarUtil.getProfileURLFormatQRCode(GravatarUtil.getMD5ForEmail(email));
        Assert.assertNotNull(url);
        System.out.println("GH (QRCode) --> " + url);
    }

    @Test
    public void testGetHtmlImageTag() throws Exception
    {
        String email = null;
        String htmlImgTag = null;

        // TEST #1
        email = EMAIL_TOTO;
        htmlImgTag = GravatarUtil.getHtmlImageTag(GravatarUtil.getMD5ForEmail(email));
        Assert.assertNotNull(htmlImgTag);
        System.out.println("--> " + htmlImgTag);

    }

}
