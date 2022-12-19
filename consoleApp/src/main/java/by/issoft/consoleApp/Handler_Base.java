package by.issoft.consoleApp;

public abstract class Handler_Base implements Handler {
    private final Handler_Base next;

    public Handler_Base(Handler_Base next) { this.next = next; }

    public Handler_Base getNext() {
        return next;
    }

    @Override
    public abstract void handleRequest(String input);
}
