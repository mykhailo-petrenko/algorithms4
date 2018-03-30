package patterns.creational.singleton;

public class Single {
    private static Single instance;

    private Single() {

    }

    public static Single getSingle() {
        if (instance == null) {
            synchronized (Single.class) {
                if (instance == null) {
                    instance = new Single();
                }
            }
        }

        return instance;
    }
}
