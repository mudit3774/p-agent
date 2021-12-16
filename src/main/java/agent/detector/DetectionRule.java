package agent.detector;

public interface DetectionRule {
    // Checks if the given line has a data leak
    boolean check(String line);

    // Masks sensitive data in the given line
    String mask(String line);
}
