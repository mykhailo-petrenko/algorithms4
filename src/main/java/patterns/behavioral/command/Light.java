package patterns.behavioral.command;

/**
 * Receiver
 **/
public class Light {
    private String name = "";
    private boolean isEnabled = false;

    public Light() {}

    public Light(String name) {
        this.name = name;
    }

    public void on() {
        isEnabled = true;
        System.out.println(this);
    }

    public void off() {
        isEnabled = false;
        System.out.println(this);
    }

    @Override
    public String toString() {
        return name + ": Light switched " + ((isEnabled) ? "ON" : "OFF");
    }
}
