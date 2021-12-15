package prerotate;

public class CannotRotateFile extends RuntimeException {
    public CannotRotateFile(String message) {
        super(message);
    }
}
