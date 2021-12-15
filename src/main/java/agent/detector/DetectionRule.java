package agent.detector;

public interface DetectionRule {
    boolean check(String line);
    String mask(String line);
}
