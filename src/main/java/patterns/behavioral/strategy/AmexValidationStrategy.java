package patterns.behavioral.strategy;

public class AmexValidationStrategy extends ValidationStrategy {
    @Override
    public boolean isValid(CreditCard creditCard) {
        String number = creditCard.getNumber();

        boolean isValid = (number.startsWith("37") || number.startsWith("34"));

        if (isValid) {
            isValid = number.length() == 15;
        }

        if (isValid) {
            isValid = this.validateCreditCardNumber(number);
        }

        return isValid;
    }
}
