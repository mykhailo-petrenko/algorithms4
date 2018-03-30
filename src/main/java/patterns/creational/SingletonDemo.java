package patterns.creational;

import patterns.creational.singleton.Single;

public class SingletonDemo {
    public static void main(String[] args) {
        Single s1 = Single.getSingle();
        Single s2 = Single.getSingle();

        System.out.println(s1);
        System.out.println(s2);

        if (s1==s2) {
            System.out.println("the same");
        }
    }
}
