package patterns.behavioral.state;

public abstract class State {
    protected Fan fan;

    State(Fan fan) {
        this.fan = fan;
    }

    public abstract void goNext(); // or handleRequest
}
