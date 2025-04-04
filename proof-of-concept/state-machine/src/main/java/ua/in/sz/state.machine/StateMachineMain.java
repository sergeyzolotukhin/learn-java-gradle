package ua.in.sz.state.machine;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StateMachineMain {
    @SneakyThrows
    public static void main(String[] args) {
        StateMachine machine = StateMachine.of();
        log.info("State machine {}", machine);

        machine.send(StateMachine.Events.UP);
        log.info("State machine {}", machine);
        machine.send(StateMachine.Events.UP);
        log.info("State machine {}", machine);

        machine.send(StateMachine.Events.DO);
        log.info("State machine {}", machine);
    }
}
