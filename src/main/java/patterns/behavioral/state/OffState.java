package patterns.behavioral.state;

public class OffState extends State {
    public OffState(Fan fan) {
        super(fan);
    }

    @Override
    public void goNext() {
        System.out.println("Switching to LOW");
        this.fan.setState(this.fan.getLowState());
    }

    @Override
    public String toString() {
        return "OFF State";
    }
}
