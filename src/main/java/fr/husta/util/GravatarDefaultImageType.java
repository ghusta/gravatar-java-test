package fr.husta.util;

public enum GravatarDefaultImageType
{

    _404("404", "do not load any image if none is associated with the email hash, instead return an HTTP 404 (File Not Found) response"),
    mm("mm", "(mystery-man) a simple, cartoon-style silhouetted outline of a person (does not vary by email hash)"),
    identicon("identicon", "a geometric pattern based on an email hash"),
    monsterid("monsterid", "a generated 'monster' with different colors, faces, etc"),
    wavatar("wavatar", ""),
    retro("retro", "awesome generated, 8-bit arcade-style pixelated faces"),
    blank("blank", "a transparent PNG image (border added to HTML below for demonstration purposes)");

    private String queryParam;
    private String description;

    GravatarDefaultImageType(String queryParam, String description)
    {
        this.queryParam = queryParam;
        this.description = description;
    }

    public String getQueryParam()
    {
        return queryParam;
    }

    public String getDescription()
    {
        return description;
    }
}
