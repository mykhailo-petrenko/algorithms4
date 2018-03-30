package patterns.creational;

import patterns.creational.factory.WebSite;
import patterns.creational.factory.WebSiteType;
import patterns.creational.factory.WebsiteFactory;

public class FactoryDemo {
    public static void main(String[] args) {
        WebSite blog = WebsiteFactory.getWebsite(WebSiteType.BLOG);
        WebSite shop = WebsiteFactory.getWebsite(WebSiteType.SHOP);

        System.out.println(blog);
        System.out.println(shop);
    }
}
