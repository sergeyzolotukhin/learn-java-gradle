package ua.in.sz.h2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachine;

@SuppressWarnings({"unchecked", "deprecation"})
@Slf4j
@Configuration
public class SimpleStateMachineMain {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("ua.in.sz.h2");
        context.refresh();

        StateMachine<String, String> stateMachine = context.getBean(StateMachine.class);
        stateMachine.start();

        log.info("State: [{}]", stateMachine.getState().getId());
        stateMachine.sendEvent("E3");
        stateMachine.sendEvent("E4");
        stateMachine.sendEvent("E5");
        stateMachine.sendEvent("end");
        log.info("State: [{}]", stateMachine.getState().getId());

        stateMachine.stop();

        context.close();
    }
}