package fr.husta.util;

import java.net.URL;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;

public class GravatarUtilTest
{

    @BeforeClass
    public static void setUpBeforeClass() throws Exception
    {
    }

    @Test
    public void testGetMD5ForEmail() throws Exception
    {
        String email = null;
        String hash = null;

        // TEST #1
        email = "toto@site.com";
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
        email = "toto@site.com";
        url = GravatarUtil.getImageURL(GravatarUtil.getMD5ForEmail(email));
        Assert.assertNotNull(url);
        System.out.println("--> " + url);

    }

    @Test
    public void testGetHtmlImageTag() throws Exception
    {
        String email = null;
        String htmlImgTag = null;

        // TEST #1
        email = "toto@site.com";
        htmlImgTag = GravatarUtil.getHtmlImageTag(GravatarUtil.getMD5ForEmail(email));
        Assert.assertNotNull(htmlImgTag);
        System.out.println("--> " + htmlImgTag);

    }

}
