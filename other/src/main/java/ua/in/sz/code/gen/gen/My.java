package ua.in.sz.code.gen.gen;

public class My {
    String myString = null;
    String myString2 = null;

    {/* Contents of the indented section (1) *//* Contents of the indented section (2) */}

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Require more than one argument");
        } else if (args.length == 1) {
            doSomething();
        } else {
            System.out.println("Too many arguments");
        }
    }

    private static void doSomething() {
    }
}