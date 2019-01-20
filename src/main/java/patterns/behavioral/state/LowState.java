package patterns.behavioral.state;

public class LowState extends State {
    public LowState(Fan fan) {
        super(fan);
    }

    @Override
    public void goNext() {
        System.out.println("Switching to MED");
        this.fan.setState(this.fan.getMedState());
    }

    @Override
    public String toString() {
        return "LOW State";
    }
}
