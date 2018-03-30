package patterns.creational.factory;

public class Shop extends WebSite {
    @Override
    void createWebsite() {
        pages.add(new ProductPage());
        pages.add(new CartPage());
        pages.add(new SearchPage());
        pages.add(new AboutPage());
    }
}
