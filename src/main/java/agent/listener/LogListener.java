package agent.listener;

import agent.detector.Alert;
import agent.detector.DataLeakDetector;
import agent.notifier.Notifier;
import org.apache.commons.io.input.TailerListenerAdapter;

import java.util.*;

public class LogListener extends TailerListenerAdapter {
    private static final int ALERT_SYNC_THRESHOLD = 2;

    private final DataLeakDetector detector;
    private final Notifier notifier;
    private Set<Alert> alerts;
    private int alertCount;

    public LogListener(DataLeakDetector detector, Notifier notifier) {
        this.detector = detector;
        this.notifier = notifier;
        this.alerts = new HashSet<>();
        this.alertCount = 0;
    }


    @Override
    public void handle(String line) {
        Optional<Alert> alert = detector.detect(line);

        if (alert.isPresent()) {
            System.out.println(alert.get());
            alerts.add(alert.get());
            alertCount++;
        }

        if (alertCount > ALERT_SYNC_THRESHOLD) {
            notifier.notify(alerts);
            alertCount = 0;
        }
    }
}

