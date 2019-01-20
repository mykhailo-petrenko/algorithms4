package patterns.behavioral;

import patterns.behavioral.state.Fan;

public class StateDemo {
    public static void main(String[] args) {
        System.out.println("Behavioral: State.");

        Fan fan = new Fan();

        System.out.println("The Initial Fan State: " + fan);

        int n = 5;
        while (n-- > 0) {
            System.out.println("\nTap \"Go Next\" Button");
            fan.goNext();
            System.out.println("Fan State: " + fan);
        }
    }
}
