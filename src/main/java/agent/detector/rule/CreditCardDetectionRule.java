package agent.detector.rule;

import agent.detector.DetectionRule;

public class CreditCardDetectionRule implements DetectionRule {
    @Override
    public boolean check(String line) {
        return line.contains("CCNum");
    }

    @Override
    public String mask(String line) {
        return line.replace("CCNum", "CREDIT_CARD");
    }
}
