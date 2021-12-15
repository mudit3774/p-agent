package agent;

import agent.detector.DataLeakDetector;
import agent.detector.DetectionRule;
import agent.detector.SimpleDataLeakDetector;
import agent.notifier.Notifier;
import agent.notifier.SimpleNotifier;

import java.util.List;

public abstract class AgentConfig {
    public abstract List<DetectionRule> getRules();

    public Notifier getNotifier(String endpoint) {
        return new SimpleNotifier();
    }

    public DataLeakDetector getDataLeakDetector(String host, String app, String logFile) {
        return new SimpleDataLeakDetector(getRules(), host, app, logFile);
    }
}
