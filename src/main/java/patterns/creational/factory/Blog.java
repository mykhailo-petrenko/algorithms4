package patterns.creational.factory;

public class Blog extends WebSite {
    @Override
    void createWebsite() {
        pages.add(new PostPage());
        pages.add(new AboutPage());
        pages.add(new ContactPage());
    }
}
