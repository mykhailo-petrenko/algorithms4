package patterns.behavioral.command;

import java.util.ArrayList;

/**
 * Concrete Command
 */
public class On implements Command {
    private ArrayList<Light> lights;

    public On(Light light) {
        this.lights = new ArrayList<>();
        this.lights.add(light);
    }

    public On(ArrayList<Light> lights) {
        this.lights = lights;
    }

    @Override
    public void execute() {
        for (Light light: lights) {
            light.on();
        }
    }
}
