package patterns.creational.factory;

public class WebsiteFactory {
    public static WebSite getWebsite(WebSiteType siteType) {
        switch (siteType) {
            case BLOG:
                return new Blog();
            case SHOP:
                return new Shop();
            default:
                return null;
        }
    }
}
