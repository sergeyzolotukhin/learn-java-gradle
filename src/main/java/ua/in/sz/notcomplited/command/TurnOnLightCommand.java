package ua.in.sz.notcomplited.command;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TurnOnLightCommand implements Command {
    private final LightReceiver light;

    TurnOnLightCommand(LightReceiver light) {
        this.light = light;
    }

    @Override
    public void execute() {
       light.turnOn();
    }
}
