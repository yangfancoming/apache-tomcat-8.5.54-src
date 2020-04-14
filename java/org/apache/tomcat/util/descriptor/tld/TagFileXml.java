
package org.apache.tomcat.util.descriptor.tld;

/**
 * Bare-bone model of a tag file loaded from a TLD.
 * This does not contain the tag-specific attributes that requiring parsing
 * the actual tag file to derive.
 */
public class TagFileXml {
    private String name;
    private String path;
    private String displayName;
    private String smallIcon;
    private String largeIcon;
    private String info;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getSmallIcon() {
        return smallIcon;
    }

    public void setSmallIcon(String smallIcon) {
        this.smallIcon = smallIcon;
    }

    public String getLargeIcon() {
        return largeIcon;
    }

    public void setLargeIcon(String largeIcon) {
        this.largeIcon = largeIcon;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
