package patterns.behavioral;

import patterns.behavioral.strategy.AmexValidationStrategy;
import patterns.behavioral.strategy.CreditCard;
import patterns.behavioral.strategy.VisaStrategy;

public class StrategyDemo {
    public static void main(String[] args) {
        // American Express
        CreditCard amexCard = new CreditCard(new AmexValidationStrategy());
        amexCard.setNumber("349246846368131");
        amexCard.setDate("06/2023");
        amexCard.setCvv("926");

        System.out.println("AMEX Is Valid " + amexCard.isValid());

        // Invalid American Express
        CreditCard invalidAmexCard = new CreditCard(new AmexValidationStrategy());
        invalidAmexCard.setNumber("34924684636813");
        invalidAmexCard.setDate("06/2023");
        invalidAmexCard.setCvv("926");

        System.out.println("AMEX Is Valid " + invalidAmexCard.isValid());

        // Visa Card
        CreditCard visa = new CreditCard(new VisaStrategy());
        visa.setNumber("4525838166648221");
        visa.setDate("02/2024");
        visa.setCvv("775");

        System.out.println("Visa is valid: " + visa.isValid());

        // Invalid Visa Card
        CreditCard invalidVisa = new CreditCard(new VisaStrategy());
        invalidVisa.setNumber("3225838166648221");
        invalidVisa.setDate("02/2024");
        invalidVisa.setCvv("775");

        System.out.println("Visa is valid: " + invalidVisa.isValid());
    }
}
