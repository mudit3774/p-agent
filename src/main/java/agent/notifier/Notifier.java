package agent.notifier;

import agent.detector.Alert;

import java.util.Set;

public interface Notifier {
    void notify(Set<Alert> alert);
}
