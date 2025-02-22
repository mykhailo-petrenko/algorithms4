package patterns.behavioral.command;

import java.util.ArrayList;

/**
 * Concrete Command
 */
public class Off implements Command {
    private ArrayList<Light> lights;

    public Off(Light light) {
        this.lights = new ArrayList<>();
        this.lights.add(light);
    }

    public Off(ArrayList<Light> lights) {
        this.lights = lights;
    }

    @Override
    public void execute() {
        for (Light light: lights) {
            light.off();
        }
    }
}
