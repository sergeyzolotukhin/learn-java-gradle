package ua.in.sz.h2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.Arrays;
import java.util.HashSet;

@Slf4j
@Configuration
@EnableStateMachine
public class SimpleStateMachineConfiguration extends StateMachineConfigurerAdapter<String, String> {


    @Override
    public void configure(StateMachineConfigurationConfigurer<String, String> config) throws Exception {
        config.withConfiguration().autoStartup(true);
    }

    @Override
    public void configure(StateMachineStateConfigurer<String, String> states) throws Exception {
        states.withStates()
                .initial("SI")
                .end("SF")
                .states(new HashSet<>(Arrays.asList("S1", "S2")))
                .stateEntry("S3", entryAction())
                .stateExit("S3", exitAction())
                .state("S4", executeAction(), errorAction())
                .state("S5", executeAction(), errorAction());

    }

    @Override
    public void configure(StateMachineTransitionConfigurer<String, String> transitions) throws Exception {
        transitions
                .withExternal()
                .source("SI")
                .target("S1")
                .event("E1")
                .action(initAction())
                .and()
                .withExternal()
                .source("S1")
                .target("S2")
                .event("E2")
                .and()

                .withExternal()
                .source("SI")
                .target("S3")
                .event("E3")
                .and()
                .withExternal()
                .source("S3")
                .target("S4")
                .event("E4")
                .and()
                .withExternal()
                .source("S4")
                .target("S5")
                .event("E5")
                .and()
                .withExternal()
                .source("S5")
                .target("SF")
                .event("end")
        ;
    }

    @Bean
    public Action<String, String> entryAction() {
        return ctx -> log.info("Entry {}", ctx.getTarget().getId());
    }

    @Bean
    public Action<String, String> doAction() {
        return ctx -> log.info("Do {}", ctx.getTarget().getId());
    }

    @Bean
    public Action<String, String> executeAction() {
        return ctx -> {
            log.info("Execute {}", ctx.getTarget().getId());
            int approvals = (int) ctx.getExtendedState()
                    .getVariables().getOrDefault("approvalCount", 0);
            approvals++;
            ctx.getExtendedState()
                    .getVariables().put("approvalCount", approvals);
        };
    }

    @Bean
    public Action<String, String> exitAction() {
        return ctx -> log.info("Exit {} -> {}", ctx.getSource().getId(), ctx.getTarget().getId());
    }

    @Bean
    public Action<String, String> errorAction() {
        return ctx -> log.info("Error {}:", ctx.getSource().getId(), ctx.getException());
    }

    @Bean
    public Action<String, String> initAction() {
        return ctx -> log.info(ctx.getTarget().getId());
    }
}