package patterns.behavioral.state;

public class MedState extends State {
    public MedState(Fan fan) {
        super(fan);
    }

    @Override
    public void goNext() {
        System.out.println("Switching to HI");
        this.fan.setState(this.fan.getHiState());
    }

    @Override
    public String toString() {
        return "MED State";
    }
}
