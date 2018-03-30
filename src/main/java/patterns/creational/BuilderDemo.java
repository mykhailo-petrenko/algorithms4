package patterns.creational;

import patterns.creational.builder.LunchBean;

public class BuilderDemo {
    public static void main(String[] args) {
        LunchBean.Builder lunchBuilder = new LunchBean.Builder();

        LunchBean myLunch = lunchBuilder
                .bread("Toast")
                .drink("Pepsi")
                .meat("Chicken")
                .build();

        LunchBean branch = lunchBuilder
                .salad("Letuce")
                .build();

        System.out.println("== my lunch:");
        System.out.println(myLunch.getBread());
        System.out.println(myLunch.getMeat());
        System.out.println(myLunch.getSalad());
        System.out.println(myLunch.getDrink());

        System.out.println("== branch:");
        System.out.println(branch.getBread());
        System.out.println(branch.getMeat());
        System.out.println(branch.getSalad());
        System.out.println(branch.getDrink());

    }
}
