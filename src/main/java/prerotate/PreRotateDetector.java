package prerotate;

import agent.AgentConfig;
import agent.detector.Alert;
import agent.detector.DataLeakDetector;
import agent.util.ConfigUtil;
import prowler.ProwlerConfig;

import java.io.*;
import java.util.Map;
import java.util.Optional;

import static agent.constants.Constants.*;
import static agent.constants.Constants.LOG_FILE;

public class PreRotateDetector {
    public static void main(String[] args) throws IOException {
        System.out.println("Starting Pre-rotate check ...");

        // Gets Config
        Map<String, String> config = ConfigUtil.getConfig(args);

        // Bind custom rules and implementations
        AgentConfig agentConfig = new ProwlerConfig();

        // Build dependencies
        DataLeakDetector detector = agentConfig.getDataLeakDetector(config.get(HOST),
                config.get(APP), config.get(LOG_FILE));

        // Check if file can be rotated
        File file = new File(config.get(LOG_FILE));
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        while ((line = br.readLine()) != null) {
            Optional<Alert> alert = detector.detect(line);
            if (alert.isPresent()) {
                throw new CannotRotateFile(LOG_FILE);
            }
        }
    }
}
