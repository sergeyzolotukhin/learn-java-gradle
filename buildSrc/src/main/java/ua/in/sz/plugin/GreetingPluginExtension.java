package ua.in.sz.plugin;

@SuppressWarnings("LombokGetterMayBeUsed")
public class GreetingPluginExtension {
    private String message = "Default Greeting from Gradle";

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}