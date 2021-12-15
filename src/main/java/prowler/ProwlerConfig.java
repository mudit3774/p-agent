package prowler;

import agent.AgentConfig;
import agent.detector.DetectionRule;
import agent.detector.rule.CreditCardDetectionRule;

import java.util.ArrayList;
import java.util.List;

public class ProwlerConfig extends AgentConfig {
    @Override
    public List<DetectionRule> getRules() {
        //Configure Rules
        DetectionRule creditCardDetectionRule = new CreditCardDetectionRule();
        List<DetectionRule> ruleList = new ArrayList<>();
        ruleList.add(creditCardDetectionRule);
        return ruleList;
    }
}
