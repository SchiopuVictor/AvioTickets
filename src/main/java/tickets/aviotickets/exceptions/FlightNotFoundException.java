package tickets.aviotickets.exceptions;

public class FlightNotFoundException extends RuntimeException {
    public FlightNotFoundException() {
        super("Flight not found");
    }
}
