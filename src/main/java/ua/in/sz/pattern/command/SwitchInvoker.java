package ua.in.sz.pattern.command;

class SwitchInvoker {
    void execute(Command command) {
       command.execute();
    }
}
