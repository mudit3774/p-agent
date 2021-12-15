package agent.util;

import java.util.HashMap;
import java.util.Map;

import static agent.constants.Constants.*;

public class ConfigUtil {

    private ConfigUtil() {
    }

    // TODO : Take override values with actual arguments
    public static Map<String, String> getConfig(String[] args) {
        Map<String, String> config = new HashMap<>();
        config.put(LOG_FILE, DEFAULT_FILE_PATH);
        config.put(HOST, DEFAULT_HOST);
        config.put(APP, DEFAULT_APP);

        return config;
    }
}
