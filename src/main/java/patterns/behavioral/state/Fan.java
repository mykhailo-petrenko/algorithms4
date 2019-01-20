package patterns.behavioral.state;

public class Fan {
    private State state;

    // State Registry
    private State offState;
    private State lowState;
    private State medState;
    private State hiState;

    public Fan() {
        // Init State Registry
        offState = new OffState(this);
        lowState = new LowState(this);
        medState = new MedState(this);
        hiState = new HiState(this);

        // Set Default State
        this.state = this.offState;
    }

    public void goNext() { // or pullChain()
        this.state.goNext();
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getOffState() {
        return offState;
    }

    public State getLowState() {
        return lowState;
    }

    public State getMedState() {
        return medState;
    }

    public State getHiState() {
        return hiState;
    }

    @Override
    public String toString() {
        if (this.state == null) {
            return "Fan state is undefined :o(";
        }

        return this.state.toString();
    }
}
