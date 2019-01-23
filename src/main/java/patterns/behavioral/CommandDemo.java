package patterns.behavioral;

import patterns.behavioral.command.*;

import java.util.ArrayList;

public class CommandDemo {
    public static void main(String[] args) {
        Light kitchenLight = new Light("Kitchen");
        Light roomLight = new Light("Room #1");

        ArrayList<Light> ligths = new ArrayList<>();
        ligths.add(kitchenLight);
        ligths.add(roomLight);

        Switch lightSwitch = new Switch();

        Command on = new On(kitchenLight);
        Command off = new Off(kitchenLight);

        lightSwitch.storeAndExecute(on);
        lightSwitch.storeAndExecute(off);

        Command onAll = new On(ligths);
        Command offAll = new Off(ligths);

        lightSwitch.storeAndExecute(onAll);
        lightSwitch.storeAndExecute(offAll);
    }
}
