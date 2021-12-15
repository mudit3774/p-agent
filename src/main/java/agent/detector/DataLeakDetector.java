package agent.detector;

import agent.detector.Alert;

import java.util.Optional;

public interface DataLeakDetector {
    Optional<Alert> detect(String logLine);
}