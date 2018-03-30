package patterns.creational.prototype;

import java.util.HashMap;
import java.util.Map;

public class Registry {
    private Map <ItemType, Item> items = new HashMap<>();

    public Registry() {
        loadItem();
    }

    public Item createItem(ItemType type) {
        Item item = null;

        try {
            item = (Item)items.get(type).clone();
        } catch(CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return item;
    }

    private void loadItem() {
        Movie movie = new Movie();
        movie.setTitle("Basic movie");
        movie.setPrice(9.99);
        movie.setRuntime("1 hour 30 minutes");

        items.put(ItemType.MOVIE, movie);

        Book book = new Book();
        book.setTitle("Basic Book");
        book.setPrice(1.99);
        book.setNumberOfPages(500);

        items.put(ItemType.BOOK, book);
    }
}
