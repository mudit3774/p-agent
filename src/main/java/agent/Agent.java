package agent;

import agent.detector.DataLeakDetector;
import agent.notifier.Notifier;
import agent.listener.LogListener;
import org.apache.commons.io.input.Tailer;
import org.apache.commons.io.input.TailerListener;
import agent.util.ConfigUtil;
import prowler.ProwlerConfig;

import java.io.File;
import java.util.Map;

import static agent.constants.Constants.*;

public class Agent {
    public static void main(String[] args) {
        System.out.println("Starting agent.Agent ...");

        // Gets Config
        Map<String, String> config = ConfigUtil.getConfig(args);

        // Bind custom rules and implementations
        AgentConfig agentConfig = new ProwlerConfig();

        // Build dependencies
        DataLeakDetector detector = agentConfig.getDataLeakDetector(config.get(HOST),
                config.get(APP), config.get(LOG_FILE));
        Notifier notifier = agentConfig.getNotifier("");

        // Start Listening
        TailerListener listener = new LogListener(detector, notifier);
        Tailer tailer = new Tailer(new File(config.get(LOG_FILE)), listener, 500);
        tailer.run();
    }
}
