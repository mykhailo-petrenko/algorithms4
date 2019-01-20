package patterns.behavioral.state;

public class HiState extends State {
    public HiState(Fan fan) {
        super(fan);
    }

    @Override
    public void goNext() {
        System.out.println("Switching to OFF");
        this.fan.setState(this.fan.getOffState());
    }

    @Override
    public String toString() {
        return "HIGH State";
    }
}
