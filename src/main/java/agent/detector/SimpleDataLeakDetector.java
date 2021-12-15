package agent.detector;

import agent.detector.Alert;
import agent.detector.DataLeakDetector;
import agent.detector.DetectionRule;

import java.util.List;
import java.util.Optional;

public class SimpleDataLeakDetector implements DataLeakDetector {

    private final List<DetectionRule> rules;
    private final String host;
    private final String app;
    private final String filename;

    public SimpleDataLeakDetector(List<DetectionRule> rules, String host, String app, String filename) {
        this.rules = rules;
        this.host = host;
        this.app = app;
        this.filename = filename;
    }

    @Override
    public Optional<Alert> detect(String logLine) {
        String line = logLine;
        boolean isAlerted = false;

        for(DetectionRule rule:rules) {
            if(rule.check(line)) {
                line = rule.mask(line);
                isAlerted = !isAlerted;
            }
        }
        if(isAlerted) {
            return Optional.of(new Alert(host, app, line,
                    System.currentTimeMillis(), filename));
        }

        return Optional.empty();
    }
}
