package patterns.behavioral.strategy;

public class CreditCard {
    private String number;
    private String date;
    private String cvv;
    private ValidationStrategy validation;

    public CreditCard(ValidationStrategy validation) {
        this.validation = validation;
    }

    public boolean isValid() {
        return this.validation.isValid(this);
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }
}
