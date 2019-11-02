package ua.in.sz.pattern.command;

public class Client {
    public static void main(String[] args) {
        LightReceiver light = new LightReceiver();
        SwitchInvoker switcher = new SwitchInvoker();

        Command turnOn = new TurnOnLightCommand(light);
        Command turnOff = new TurnOffLightCommand(light);

        switcher.execute(turnOn);
        switcher.execute(turnOff);
    }
}
