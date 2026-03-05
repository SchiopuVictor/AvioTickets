package tickets.aviotickets.exceptions;

public class PassagerNotFoundException extends RuntimeException {
    public PassagerNotFoundException() {
        super("Passager not found");
    }
}
