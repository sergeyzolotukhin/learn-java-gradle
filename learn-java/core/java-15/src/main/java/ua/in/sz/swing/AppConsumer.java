package ua.in.sz.swing;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Slf4j
public class AppConsumer {
    public static void main(String[] args) {
        Derivation derivation = Derivation.builder().name("NAME").build();

        List<Consumer<Derivation>> commands = new ArrayList<>();
        commands.add(new UpdateCommand("+"));
        commands.add(new UpdateCommand("+"));
        commands.add(new UpdateCommand("-"));
        commands.add(new UpdateCommand("-"));
        commands.add(new UpdateCommand("@"));
        commands.add(new UpdateCommand("@"));

        commands.forEach(c -> c.accept(derivation));

        log.info("Name: {}", derivation.getName());
    }

    private record UpdateCommand(String prefix) implements Consumer<Derivation> {
        @Override
        public void accept(Derivation derivation) {
            derivation.setName(prefix + derivation.getName());
        }
    }

    @Getter
    @Setter
    @Builder
    private static class Derivation {
        private String name;
    }
}
