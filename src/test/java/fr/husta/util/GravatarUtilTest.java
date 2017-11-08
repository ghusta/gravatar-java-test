package fr.husta.util;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;

public class GravatarUtilTest
{

    private static final String EMAIL_TOTO = "toto@site.com";
    private static final String EMAIL_GUILLAUME_HUSTA = "guillaume.husta@gmail.com";

    private static OkHttpClient okHttpClient;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception
    {
        System.out.println("init OkHttpClient");
        okHttpClient = new OkHttpClient.Builder()
                .followRedirects(true)
                .followSslRedirects(true)
                .proxy(detectProxy())
                .build();
        System.out.println("followRedirects: " + okHttpClient.followRedirects());
        System.out.println("followSslRedirects: " + okHttpClient.followSslRedirects());
        System.out.println("proxy: " + okHttpClient.proxy());
        System.out.println("proxySelector: " + okHttpClient.proxySelector());
    }

    private static Proxy detectProxy()
    {
        // look for system property : "http.proxyHost" and "http.proxyPort"
        // See : https://docs.oracle.com/javase/8/docs/technotes/guides/net/proxies.html
        String propHttpProxyHost = System.getProperty("http.proxyHost");
        String propHttpProxyPort = System.getProperty("http.proxyPort");
        if (propHttpProxyHost != null)
        {
            // check / normalization
            if (propHttpProxyHost.startsWith("http://"))
            {
                propHttpProxyHost = propHttpProxyHost.substring("http://".length());
            }
            if (propHttpProxyHost.startsWith("https://"))
            {
                propHttpProxyHost = propHttpProxyHost.substring("https://".length());
            }

            int proxyPort = (propHttpProxyPort == null ? 80 : Integer.parseInt(propHttpProxyPort));
            return new Proxy(Proxy.Type.HTTP, new InetSocketAddress(propHttpProxyHost, proxyPort));
        }

        return Proxy.NO_PROXY;
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
        String hash;

        hash = GravatarUtil.getMD5ForEmail(null);
        Assert.assertNotNull(hash);
        System.out.println("--> " + hash);
    }

    @Test
    public void testGetMD5ForEmail_empty() throws Exception
    {
        String hash;

        hash = GravatarUtil.getMD5ForEmail("");
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
        assertThat(url).isNotNull();
        System.out.println("GH (Profile) --> " + url);

        // test URL is valid
        int responseCode = getURLResponseCode(url);
        assertThat(responseCode).isEqualTo(200);
    }

    @Test
    public void testGetProfileURL_forGHUSTA_JSON() throws Exception
    {
        String email = null;
        URL url = null;

        email = EMAIL_GUILLAUME_HUSTA;
        url = GravatarUtil.getProfileURLFormatJson(GravatarUtil.getMD5ForEmail(email));
        assertThat(url).isNotNull();
        System.out.println("GH (Profile) --> " + url);

        // test URL is valid
        int responseCode = getURLResponseCode(url);
        assertThat(responseCode).isEqualTo(200);
    }

    @Test
    public void testGetProfileURL_forGHUSTA_QRCode() throws Exception
    {
        String email;
        URL url;

        email = EMAIL_GUILLAUME_HUSTA;
        url = GravatarUtil.getProfileURLFormatQRCode(GravatarUtil.getMD5ForEmail(email));
        Assert.assertNotNull(url);
        System.out.println("GH (QRCode) --> " + url);
    }

    @Test
    public void testGetProfileURL_forToto_JSON() throws Exception
    {
        String username;
        URL url;

        username = "toto";
        url = GravatarUtil.getProfileURLFormatJson(username);
        assertThat(url).isNotNull();
        System.out.println("Toto (Profile) --> " + url);

        // test URL is valid
        int responseCode = getURLResponseCode(url);
        assertThat(responseCode).isEqualTo(200);
    }

    @Test
    public void testGetProfileURL_forBeau_JSON() throws Exception
    {
        String username;
        URL url;

        username = "beau";
        url = GravatarUtil.getProfileURLFormatJson(username);
        assertThat(url).isNotNull();
        System.out.println("Beau (Profile) --> " + url);

        // test URL is valid
        int responseCode = getURLResponseCode(url);
        assertThat(responseCode).isEqualTo(200);
    }

    @Test
    public void testGetProfileURL_forNotFound404_JSON() throws Exception
    {
        String username;
        URL url;

        username = "unknown_unknown_unknown";
        url = GravatarUtil.getProfileURLFormatJson(username);
        assertThat(url).isNotNull();
        System.out.println("??? (Profile) --> " + url);

        // test URL is not valid
        int responseCode = getURLResponseCode(url);
        assertThat(responseCode).isEqualTo(404);
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

    private static int getURLResponseCode(URL url)
    {
        // HTTP method = GET
        Request request = new Request.Builder()
                .get()
                .url(url)
                .build();
        Call call = okHttpClient.newCall(request);
        int responseCode;
        try
        {
            Response response = call.execute();
            responseCode = response.code();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        return responseCode;

    }

}
