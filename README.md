About 
=====

p-agent is expected to run as a deamon (using something like supervisord) on all application hosts. 
It continuously tails logs (using Apace Tailer) and look for data breach according to the 
data breach rules configured. It can notify endpoints on breach. The present implemntation 
publishes a Kafka message in format that [p-server](https://github.com/mudit3774/p-server) understands.
Users can leverage p-server to ingest and search alerts.

Requirements 
============

* Kafka 2.12+
* Java 1.8+
* Gradle 4+

Setup
===== 

* git clone https://github.com/mudit3774/p-agent
* $> gradle clean build
* $> java -jar build/libs/p-agent-1.0-SNAPSHOT.jar 

Testing
=======

Create a topic `socalerts` in kafka :

` bin/kafka-topics.sh --create --partitions 1 --replication-factor 1 --topic socalerts --bootstrap-server localhost:9092`

Once the agent has started, create a file `app.log` and insert log lines using echo `"CCNum is \n" >> app.log`
In the default rule, an alert will be generated if the log line has `CCNum`. Alert will be 
published in `socalerts` topic in Kafka.

If [p-server](https://github.com/mudit3774/p-server) is running, alerts should be 
queryable using : 

`
curl -X GET \
http://localhost:8080/search \
-H 'cache-control: no-cache' \
-H 'content-type: application/x-www-form-urlencoded' \
-H 'postman-token: 8ef1a0bb-fb79-9826-b7e0-fc8884f533b5'
`

Usage
=====

Implement this interface to model a detection rule :


    public interface DetectionRule {
    
        // Checks if the given line has a data leak
        boolean check(String line);

        // Masks sensitive data in the given line
        String mask(String line);
    }

A sample rule is provided `CreditCardDetectionRule.java`

Implement this abstract class to configure the agent :

    
    public abstract class AgentConfig {

        // Returns the list of rules to be applied 
        public abstract List<DetectionRule> getRules();

        ...
        ...
    }

Bind the implementation in `Agent.java`

Users can optionally update notification endpoint, methods and detection rule application logic.

Configurations
==============

The following arguments can be provided while starting the agent

* HOST - Host id for the service
* APP - App name for the service
* LOG_FILE - LogFile to monitor
* ENDPOINT - Endpoint to record the alert (TODO)

Driver program (to be evolved to integ tests) : https://gist.github.com/mudit3774/67f222890468bcd83cb7f0b4656f496d
