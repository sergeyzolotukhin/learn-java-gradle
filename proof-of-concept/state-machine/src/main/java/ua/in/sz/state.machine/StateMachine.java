package ua.in.sz.state.machine;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(staticName = "of")
public class StateMachine {
    private State state = new InitState();

    public void send(Event event) {
        state = state.handle(event);
    }

    @Override
    public String toString() {
        return state.getClass().getSimpleName();
    }

    public enum Event {
        UP,
        DO,
        DOWN
    }

    interface State {
        State handle(Event event);
    }

    static class InitState implements State {
        public State handle(Event event) {
            if (Event.UP.equals(event)) {
                return new DoState();
            }
            return this;
        }
    }

    static class DoState implements State {
        public State handle(Event event) {
            if (Event.DO.equals(event)) {
                return new EndState();
            }
            return this;
        }
    }

    static class EndState implements State {
        public State handle(Event event) {
            if (event == Event.DOWN) {
                return new InitState();
            }
            return this;
        }
    }
}
