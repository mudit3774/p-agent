package agent;

import agent.detector.DataLeakDetector;
import agent.detector.DetectionRule;
import agent.detector.SimpleDataLeakDetector;
import agent.notifier.Notifier;
import agent.notifier.SimpleNotifier;

import java.util.List;

public abstract class AgentConfig {

    // Returns the list of rules to be applied
    public abstract List<DetectionRule> getRules();

    // Returns the type of notification method
    public Notifier getNotifier(String endpoint) {
        return new SimpleNotifier();
    }

    // Return the data leak detector logic
    public DataLeakDetector getDataLeakDetector(String host, String app, String logFile) {
        return new SimpleDataLeakDetector(getRules(), host, app, logFile);
    }
}
