package patterns.creational.builder;

public class LunchBean {

    public static class Builder {
        private String bread;
        private String meat;
        private String drink;
        private String salad;

        public Builder bread(String bread) {
            this.bread = bread;
            return this;
        }

        public Builder meat(String meat) {
            this.meat = meat;
            return this;
        }

        public Builder drink(String drink) {
            this.drink = drink;
            return this;
        }

        public Builder salad(String salad) {
            this.salad = salad;
            return this;
        }

        public LunchBean build() {
            return new LunchBean(this);
        }
    }

    private final String bread;
    private final String meat;
    private final String drink;
    private final String salad;

    private LunchBean(Builder builder) {
        this.bread = builder.bread;
        this.meat = builder.meat;
        this.drink = builder.drink;
        this.salad = builder.salad!=null ? builder.salad : "";
    }

    public String getBread() {
        return bread;
    }

    public String getMeat() {
        return meat;
    }

    public String getDrink() {
        return drink;
    }

    public String getSalad() {
        return salad;
    }
}
