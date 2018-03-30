package patterns.creational.factory;

import java.util.ArrayList;
import java.util.List;

public abstract class WebSite {
    List<Page> pages = new ArrayList<>();

    public WebSite() {
        this.createWebsite();
    }

    abstract void createWebsite();

    public List<Page> getPages() {
        return pages;
    }
}
