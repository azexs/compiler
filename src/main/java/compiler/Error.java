package compiler;

public class Error {
    String message;
    int line;

    public Error(String message, int line) {
        this.message = message;
        this.line = line;
    }

    @Override
    public String toString() {
        return "Error: " +
                message +
                ", line=" + line;
    }
}
