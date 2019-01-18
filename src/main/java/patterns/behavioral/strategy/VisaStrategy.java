package patterns.behavioral.strategy;

public class VisaStrategy extends ValidationStrategy {
    @Override
    public boolean isValid(CreditCard creditCard) {
        String number = creditCard.getNumber();

        boolean isValid = number.startsWith("4");

        if (isValid) {
            isValid = number.length() == 16;
        }

        if (isValid) {
            isValid = validateCreditCardNumber(number);
        }

        return isValid;
    }
}
