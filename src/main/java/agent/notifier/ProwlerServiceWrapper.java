package agent.notifier;

import agent.detector.Alert;

import java.util.List;

public interface ProwlerServiceWrapper {
    void sendAlerts(List<Alert> alert);
}
