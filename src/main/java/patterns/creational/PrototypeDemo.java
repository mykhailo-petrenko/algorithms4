package patterns.creational;

import patterns.creational.prototype.*;

public class PrototypeDemo {
    public static void main(String[] args) {
        Registry registry = new Registry();

        Movie firstMovie = (Movie)registry.createItem(ItemType.MOVIE);
        firstMovie.setTitle("Scary movie");

        printMovie(firstMovie);

        Movie secondMovie = (Movie)registry.createItem(ItemType.MOVIE);
        secondMovie.setTitle("Advenced JavaSctipt");
        secondMovie.setRuntime("10 hours");

        printMovie(secondMovie);
    }

    private static void printMovie(Movie movie) {
        System.out.println(movie.getTitle());
        System.out.println(movie.getRuntime());
        System.out.println(movie.getPrice());
        System.out.println(movie.getUrl());

    }
}
